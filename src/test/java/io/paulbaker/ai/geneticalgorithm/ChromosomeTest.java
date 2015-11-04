package io.paulbaker.ai.geneticalgorithm;

import io.paulbaker.ai.MyMachineLearningApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

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

  @DataProvider(name = "geneStrings")
  public Object[][] generateStrings() {
    Object[][] objects = new Object[100][];
    for (int i = 0; i < objects.length; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = 0; j < Long.SIZE; j++) {
        stringBuilder.append(random.nextBoolean() ? "1" : "0");
      }

      objects[i] = new Object[]{stringBuilder.toString()};
    }
    return objects;
  }

  @Test(dataProvider = "geneStrings")
  public void testConstructedFromString(String string) {
    Chromosome chromosome = Chromosome.fromString(string);
    assertEquals("Chromosome does not parse from string correctly", string, chromosome.toString());
  }

}
