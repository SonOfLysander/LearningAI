package io.paulbaker.ai.simple_genetic_algorithm;

/**
 * Created by paulbaker on 2/19/15.
 */
public abstract class FitnessFunctor {

  public static final double TARGET_DISCOVERED = 999d;

  /**
   * Determine how fit the population member is and return its value
   *
   * @param populationMember
   * @param target
   * @return
   */
  public abstract double determineFitness(PopulationMember populationMember, int target);
}
