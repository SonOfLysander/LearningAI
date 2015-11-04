package io.paulbaker.ai.geneticalgorithm;

/**
 * Created by paulbaker on 11/4/15.
 */
public class Chromosome {

  private long gene;

  private Chromosome() {
    gene = 0l;
  }

  public static Chromosome fromString(String string) {
    if (string.length() != Long.SIZE) {
      String error = String.format("String must be %d characters long.", Long.SIZE);
      throw new IllegalArgumentException(error);
    }
    if (!string.chars().allMatch(i -> {
      char letter = (char) i;
      return letter == '0' || letter == '1';
    })) {
      throw new IllegalArgumentException("String must have only 0's and 1's.");
    }
    return fromLong(Long.parseLong(string, 2));
  }

  public static Chromosome fromLong(long number) {
    Chromosome chromosome = new Chromosome();
    chromosome.gene = number;
    return chromosome;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(Long.toBinaryString(gene));
    while (stringBuilder.length() < Long.SIZE) {
      stringBuilder.insert(0, '0');
    }
    return stringBuilder.toString();
  }

}
