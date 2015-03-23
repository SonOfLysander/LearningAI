package test.ai.simple_genetic_algorithm;

import io.paulbaker.ai.simple_genetic_algorithm.Population;
import io.paulbaker.ai.simple_genetic_algorithm.PopulationMember;
import org.junit.Test;

import java.util.Random;

/**
 * Created by paulbaker on 3/23/15.
 */
public class PopulationTest {

  private static final Random RANDOM = new Random();

  @Test
  public void testPopulationTo0(){
    Population population = new Population(100, Population.DEFAULT_MUTATION_RATE, Population.DEFAULT_CROSSOVER_RATE);
    PopulationMember member = population.findTarget(0);
    System.out.println("Target = 0, Member Binary = " + member.toString());
  }

  @Test
  public void testPopulationTo100(){
    Population population = new Population(100, Population.DEFAULT_MUTATION_RATE, Population.DEFAULT_CROSSOVER_RATE);
    PopulationMember member = population.findTarget(100);
    System.out.println("Target = 100, Member Binary = " + member.toString());
  }

  @Test
  public void testPopulationToRand(){
    Population population = new Population(100, Population.DEFAULT_MUTATION_RATE, Population.DEFAULT_CROSSOVER_RATE);
    int randomTarget = RANDOM.nextInt(50000);
    System.out.print("Target = " + randomTarget);
    PopulationMember member = population.findTarget(randomTarget);
    System.out.print(", Member Binary = " + member.toString());
  }

}
