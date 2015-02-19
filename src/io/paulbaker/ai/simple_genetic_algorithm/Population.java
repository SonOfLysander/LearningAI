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

  public long findTarget(int target, FitnessFunctor fitnessFunctor) {
    return -1;
  }

}
