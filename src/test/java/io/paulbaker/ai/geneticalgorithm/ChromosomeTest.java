package io.paulbaker.ai.geneticalgorithm;

import io.paulbaker.ai.MyMachineLearningApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.function.Supplier;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by paulbaker on 11/4/15.
 */
@SpringApplicationConfiguration(classes = MyMachineLearningApplication.class)
public class ChromosomeTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private ChromosomeFactory factory;

  @Autowired
  private Random random;

  private Object[][] generateStrings(int count, int length, Supplier<Character> lambda) {
    Object[][] strings = new Object[count][];
    for (int i = 0; i < strings.length; i++) {
      StringBuilder sb = new StringBuilder(length);
      for (int j = 0; j < length; j++) {
        sb.append(lambda.get());
      }
      // We can't have a 1 in the msb, so we replace it with a 0.
      if (sb.charAt(0) == '1') {
        sb.replace(0, 1, "0");
      }
      strings[i] = new Object[]{sb.toString()};
    }
    return strings;
  }

  @DataProvider(name = "geneStrings")
  public Object[][] geneStrings() {
    // Generates a proper string of 0s and 1s.
    return generateStrings(100, Long.SIZE, () -> random.nextBoolean() ? '1' : '0');
  }

  @DataProvider(name = "malformedStrings")
  public Object[][] malformedStrings() {
    // Generates strings of correct length, but using digits [2,9].
    return generateStrings(100, Long.SIZE, () -> (char) (random.nextInt(8) + 50));
  }

  @DataProvider(name = "shortStrings")
  public Object[][] shortStrings() {
    return generateStrings(100, Long.SIZE - 1, () -> random.nextBoolean() ? '1' : '0');
  }

  @DataProvider(name = "longStrings")
  public Object[][] longStrings() {
    return generateStrings(100, Long.SIZE + 1, () -> random.nextBoolean() ? '1' : '0');
  }

  @Test(dataProvider = "geneStrings")
  public void testConstructedFromString(String string) {
    Chromosome chromosome = Chromosome.fromString(string);
    assertEquals("Chromosome does not parse from string correctly", string, chromosome.toString());
  }

  @Test(dataProvider = "malformedStrings", expectedExceptions = IllegalArgumentException.class)
  public void testMalformedStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test(dataProvider = "shortStrings", expectedExceptions = IllegalArgumentException.class)
  public void testShortStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test(dataProvider = "longStrings", expectedExceptions = IllegalArgumentException.class)
  public void testLongStrings(String string) {
    Chromosome.fromString(string);
  }

  @Test(expectedExceptions = NumberFormatException.class)
  public void testInvalidString() {
    // This string is an invalid two's compliment string.
    String badBinaryString = "1000000000000000000000000000000000000000000000000000000000000000";
    Chromosome.fromString(badBinaryString);
  }

}
