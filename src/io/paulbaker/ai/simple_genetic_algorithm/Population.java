package io.paulbaker.ai.simple_genetic_algorithm;

import geneticAlgorithm.*;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by paulbaker on 2/19/15.
 */
public class Population {

  private static final Random RANDOM;

  static {
    synchronized (Population.class) {
      RANDOM = new Random();
    }
  }

  private final double mutationRate;

  private final double crossOverRate;

  private PopulationMember[] populationMembers;

  public Population(int populationSize, double mutationRate, double crossOverRate) {
    populationMembers = new PopulationMember[populationSize];
    for (int i = 0; i < populationMembers.length; i++) {
      populationMembers[i] = new PopulationMember();
    }
    this.mutationRate = mutationRate;
    this.crossOverRate = crossOverRate;
  }

  public PopulationMember findTarget(int target) {
    FitnessFunctor fitnessFunctor = new FitnessFunctor() {
      @Override public double determineFitness(PopulationMember populationMember, int target) {
        int value = populationMember.parse();
        if (value == target)
          return TARGET_DISCOVERED;
        return 1d / Math.abs(target - value);
      }
    };
    return findTarget(target, fitnessFunctor);
  }

  public PopulationMember findTarget(int target, FitnessFunctor fitnessFunctor) {
    int generationCount = 0;

    generationLoop:
    while (true) {
      // Assign fitness values for each member
      float totalFitness = 0;
      fitnessLoop:
      for (PopulationMember populationMember : populationMembers) {
        populationMember.assignFitness(fitnessFunctor, target);
        totalFitness += populationMember.getFitness();
        if (populationMember.getFitness() == FitnessFunctor.TARGET_DISCOVERED) {
          return populationMember;
        }
      }
      PopulationMember[] temporaryPopulationPool = new PopulationMember[populationMembers.length];
      for (int i = 0; i < temporaryPopulationPool.length; i += 2) {
        PopulationMember memberA = roulette(totalFitness).getClone();
        PopulationMember memberB = roulette(totalFitness).getClone();
        memberA.setGeneration(generationCount);
        memberB.setGeneration(generationCount);
        memberA.crossOver(memberB, crossOverRate);
        memberA.mutate(mutationRate);
        memberB.mutate(mutationRate);
        temporaryPopulationPool[i] = memberA;
        temporaryPopulationPool[i + 1] = memberB;
      }
      populationMembers = temporaryPopulationPool;
      generationCount++;
    }
  }

  private PopulationMember roulette(float totalFitness) {
    float slice = randomFloat(0, totalFitness);
    float fitnessSoFar = 0f;
    for (int i = 0; i < populationMembers.length; i++) {
      fitnessSoFar += populationMembers[i].getFitness();
      if (fitnessSoFar > slice)
        return populationMembers[i];
    }
    return populationMembers[populationMembers.length - 1];
  }

  private float randomFloat(float a, float b) {
    if (b < a) {
      float c = a;
      a = b;
      b = c;
    }
    float rand = RANDOM.nextFloat();
    float diff = b - a;
    float result = (rand * diff) + a;
    return result;
  }
}
