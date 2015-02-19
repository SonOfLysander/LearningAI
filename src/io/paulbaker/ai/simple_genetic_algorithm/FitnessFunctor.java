package io.paulbaker.ai.simple_genetic_algorithm;

/**
 * Created by paulbaker on 2/19/15.
 */
public interface FitnessFunctor {
  /**
   * Determine how fit the population member is and return its value
   *
   * @param populationMember
   * @return
   */
  public int determineFitness(PopulationMember populationMember);
}
