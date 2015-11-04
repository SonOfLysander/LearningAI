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

  public Chromosome generateNewGene(){
    return Chromosome.fromLong(random.nextLong());
  }



}
