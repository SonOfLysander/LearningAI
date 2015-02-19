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

  public PopulationMember() {
    this(RANDOM.nextLong());
  }

  public PopulationMember(long chromosome) {
    this.chromosome = chromosome;
    parsedValue = null;
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
              // we continue until we find a valid option
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

}
