package io.paulbaker.ai.simple_genetic_algorithm;

import java.util.Random;

/**
 * Created by paulbaker on 2/19/15.
 */
public class PopulationMember {

  private static final Random RANDOM;

  static {
    synchronized (PopulationMember.class) {
      RANDOM = new Random();
    }
  }

  private long chromosome;

  private Integer parsedValue;

  private double fitness;

  public PopulationMember() {
    this(RANDOM.nextLong());
  }

  public PopulationMember(long chromosome) {
    this.chromosome = chromosome;
    resetFitnessAndValue();
  }

  private void resetFitnessAndValue() {
    parsedValue = null;
    fitness = -1;
  }

  public int parse() {
    if (parsedValue == null) {
      parsedValue = 0;
      int currentOperator = 10;
      boolean onNumberNotOperator = true;
      for (int i = 0; i < Long.SIZE / 4; i++) {
        // Trivia note to self/others, a "NIBBLE" is the term for four bits.
        int nibble = (int) ((chromosome >> (((Long.SIZE / 4) - i + 1) * 4)) & 0xF);
        if (onNumberNotOperator && nibble >= 0 && nibble < 10) {
          switch (currentOperator) {
            case 10:
              parsedValue += nibble;
              break;
            case 11:
              parsedValue -= nibble;
              break;
            case 12:
              parsedValue *= nibble;
              break;
            case 13:
              // If the nibble is 0, we skip it. We can't divide by
              // zero and we don't flip our searching boolean because
              // we continue until we find a valid operation
              if (nibble == 0)
                continue;
              parsedValue /= nibble;
              break;
          }
          onNumberNotOperator = !onNumberNotOperator;
        }
        else if (onNumberNotOperator == false && nibble >= 10 && nibble < 14) {
          currentOperator = nibble;
          onNumberNotOperator = !onNumberNotOperator;
        }
      }
    }
    return parsedValue;
  }

  public boolean crossOver(PopulationMember otherMember, double crossOverRate) {
    if (RANDOM.nextDouble() > crossOverRate)
      return false;
    resetFitnessAndValue();
    final int binarySingleBit = 0b1;
    long geneA = this.chromosome, geneB = otherMember.chromosome;
    long crossedGeneA = 0, crossedGeneB = 0;
    // Randomly, we will switch genes around. We do this to prevent any bias
    // towards which one will be the prefix or suffix.
    if (RANDOM.nextBoolean()) {
      geneA ^= geneB;
      geneB ^= geneA;
      geneA ^= geneB;
    }
    // Determine the index for which we will switch two genes from
    int crossoverIndex = RANDOM.nextInt(Long.SIZE - 2) + 1;
    for (int i = 1; i < Long.SIZE + 1; i++) {
      long longA = (i < crossoverIndex ? geneA : geneB);
      long longB = (i < crossoverIndex ? geneB : geneA);
      crossedGeneA = crossedGeneA << 1;
      crossedGeneB = crossedGeneB << 1;
      longA = longA >> (Long.SIZE - i) & binarySingleBit;
      longB = longB >> (Long.SIZE - i) & binarySingleBit;
      crossedGeneA |= longA;
      crossedGeneB |= longB;
    }
    // Give the chromosomes the newly swapped genes.
    this.chromosome = crossedGeneA;
    otherMember.chromosome = crossedGeneB;
    return true;
  }

  public boolean mutate(double mutationRate) {
    boolean mutated = false;
    for (int i = 0; i < Long.SIZE; i++) {
      if (RANDOM.nextDouble() > mutationRate)
        continue;
      mutated = true;
      int inverseBit = (int) ((~(chromosome >> (Long.SIZE - i - 1))) & 0x1);
      chromosome &= ~(inverseBit << Long.SIZE - i - 1);
    }
    if (mutated)
      resetFitnessAndValue();
    return mutated;
  }

  public void assignFitness(FitnessFunctor fitnessFunctor, int target) {
    fitness = fitnessFunctor.determineFitness(this, target);
  }

  public long getChromosome() {
    return chromosome;
  }

  public double getFitness() {
    return fitness;
  }

  public PopulationMember getClone() {
    return new PopulationMember(getChromosome());
  }

}
