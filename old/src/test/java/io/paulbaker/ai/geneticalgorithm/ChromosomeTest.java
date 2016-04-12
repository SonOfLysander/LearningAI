package io.paulbaker.ai.geneticalgorithm;

import io.paulbaker.ai.MyMachineLearningApplication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;

/**
 * Created by paulbaker on 11/4/15.
 */
@SpringApplicationConfiguration(classes = MyMachineLearningApplication.class)
public class ChromosomeTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private ChromosomeFactory factory;

  @Autowired
  private Random random;

  private String[][] generateStrings(int count, int length, Supplier<Character> lambda) {
    String[][] strings = new String[count][1];
    for(int i = 0; i < strings.length; i++) {
      StringBuilder sb = new StringBuilder(length);
      for(int j = 0; j < length; j++) {
        sb.append(lambda.get());
      }
//      // We can't have a 1 in the msb, so we replace it with a 0.
//      if (sb.charAt(0) == '1') {
//        sb.replace(0, 1, "0");
//      }
      strings[i] = new String[]{sb.toString()};
    }
    return strings;
  }

  @DataProvider(name = "geneStrings")
  public Object[][] geneStrings() {
    // Generates a proper string of 0s and 1s.
    return generateStrings(100, Long.SIZE, () -> random.nextBoolean() ? '1': '0');
  }

  @DataProvider(name = "malformedStrings")
  public Object[][] malformedStrings() {
    // Generates strings of correct length, but using digits [2,9].
    return generateStrings(100, Long.SIZE, () -> (char) (random.nextInt(8) + 50));
  }

  @DataProvider(name = "shortStrings")
  public Object[][] shortStrings() {
    return generateStrings(100, Long.SIZE - 1, () -> random.nextBoolean() ? '1': '0');
  }

  @DataProvider(name = "longStrings")
  public Object[][] longStrings() {
    return generateStrings(100, Long.SIZE + 1, () -> random.nextBoolean() ? '1': '0');
  }

  @DataProvider(name = "breedableChromosomes")
  public Object[][] breedableChromosomes() {
    Chromosome[][] chromosomes = new Chromosome[100][2];
    for(int i = 0; i < chromosomes.length; i++) {
      chromosomes[i] = new Chromosome[]{factory.generateNewGene(), factory.generateNewGene()};
    }
    return chromosomes;
  }

  @Test(dataProvider = "geneStrings")
  public void testConstructedFromString(String string) {
    Chromosome chromosome = Chromosome.fromString(string);
    assertEquals(chromosome.toString(), string, "Chromosome does not parse from string correctly.");
  }

  @Test(dataProvider = "malformedStrings", expectedExceptions = NumberFormatException.class)
  public void testMalformedStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test(dataProvider = "shortStrings", expectedExceptions = NumberFormatException.class)
  public void testShortStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test(dataProvider = "longStrings", expectedExceptions = NumberFormatException.class)
  public void testLongStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test
  public void testInvalidBinaryString() {
    // This string is an invalid two's compliment string.
    String badBinaryString = "1000000000000000000000000000000000000000000000000000000000000000";
    Chromosome.fromString(badBinaryString);
  }

  @Test(dataProvider = "breedableChromosomes")
  public void testChromosomeBreedingOccurs(Chromosome a, Chromosome b) {
    Chromosome[] chromosomes = factory.breedChromosomes(a, b);
//    int atobDistance = StringUtils.getLevenshteinDistance(chromosomes[0].toString(), a.toString());
//    int btoaDistance = StringUtils.getLevenshteinDistance(chromosomes[1].toString(), b.toString());
    int atobDistance = StringUtils.getLevenshteinDistance(chromosomes[0].toString(), b.toString());
    int btoaDistance = StringUtils.getLevenshteinDistance(chromosomes[1].toString(), a.toString());

    assertEquals(atobDistance, btoaDistance, "These distances should be equal. If they are not, something wrong happened during the process."); // I think...

    int cDistance = StringUtils.getLevenshteinDistance(chromosomes[1].toString(), chromosomes[0].toString());
    int dDistance = StringUtils.getLevenshteinDistance(chromosomes[0].toString(), chromosomes[1].toString());

    System.out.println("... I'm honestly unsure how to perform assertions on this. I can debug it and see that it is performing as expected, but I don't know how to prove it.");
  }

  //  @Test
  public void testLongRange() {
    for(long i = Long.MIN_VALUE; i < 0; i++) {
      StringBuilder stringBuilder = new StringBuilder(Long.toBinaryString(i));
      while (stringBuilder.length() < Long.SIZE) {
        stringBuilder.insert(0, '0');
      }
      String binaryString = stringBuilder.toString();
      Chromosome chromosome = Chromosome.fromString(binaryString);
      assertEquals(chromosome.toString(), binaryString, "Long didn't parse from string correctly");
    }
    for(long i = 0l; i >= 0; i++) {
      StringBuilder stringBuilder = new StringBuilder(Long.toBinaryString(i));
      while (stringBuilder.length() < Long.SIZE) {
        stringBuilder.insert(0, '0');
      }
      String binaryString = stringBuilder.toString();
      Chromosome chromosome = Chromosome.fromString(binaryString);
      assertEquals(chromosome.toString(), binaryString, "Long didn't parse from string correctly");
    }
  }

}
