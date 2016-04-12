package io.paulbaker.ai.geneticalgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by paulbaker on 11/4/15.
 */
@Service
public class ChromosomeFactory {

  @Autowired
  private Random random;

  public Chromosome generateNewGene() {
    return Chromosome.fromLong(random.nextLong());
  }

  public Chromosome[] breedChromosomes(Chromosome a, Chromosome b) {
    String aString = a.toString();
    String bString = b.toString();
    int index = random.nextInt(aString.length() - 2) + 1;

    String oneString = aString.substring(0, index) + bString.substring(index);
    String twoString = bString.substring(0, index) + aString.substring(index);

    return new Chromosome[]{
      Chromosome.fromString(oneString), Chromosome.fromString(twoString)
    };
  }

}
