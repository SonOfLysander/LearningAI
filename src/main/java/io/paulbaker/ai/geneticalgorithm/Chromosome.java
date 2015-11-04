package io.paulbaker.ai.geneticalgorithm;

/**
 * Created by paulbaker on 11/4/15.
 */
public class Chromosome {

  private long gene;

  private Chromosome() {
    gene = 0l;
  }

  /**
   * Generates a chromosome from a binary string.
   *
   * @param binaryString
   * @return
   */
  public static Chromosome fromString(String binaryString) {
    if (binaryString.length() != Long.SIZE) {
      String error = String.format("Binary string must be %d characters long.", Long.SIZE);
      throw new NumberFormatException(error);
    }
    if (binaryString.charAt(0) == '1') {
      throw new NumberFormatException("Binary string is of correct length, but starts with a 1");
    }
    if (!binaryString.chars().allMatch(i -> {
      char letter = (char) i;
      return letter == '0' || letter == '1';
    })) {
      throw new NumberFormatException("Binary string must have only 0's and 1's.");
    }
    return fromLong(Long.parseLong(binaryString, 2));
  }

  public static Chromosome fromLong(long number) {
    Chromosome chromosome = new Chromosome();
    chromosome.gene = number;
    return chromosome;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(Long.SIZE);
    String geneString = Long.toBinaryString(gene);
    for (int i = 0; i < Long.SIZE - geneString.length(); i++) {
      stringBuilder.append('0');
    }
    stringBuilder.append(geneString);
    return stringBuilder.toString();
  }

}
