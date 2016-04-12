package io.paulbaker.ai;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by paulbaker on 4/12/16.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Gene {

  private static final Random random = new Random();

  private long dna;

  public Gene() {
    this(random.nextLong());
  }

  @AllArgsConstructor
  @Getter
  @ToString
  @EqualsAndHashCode
  public static class GeneTuple {
    private final Gene a;
    private final Gene b;
  }

  public static GeneTuple crossover(Gene a, Gene b) {
    return crossover(a, b, (left, right) -> {
      StringBuilder leftString = new StringBuilder(toBinaryString(left.dna));
      StringBuilder rightString = new StringBuilder(toBinaryString(right.dna));
      int index = random.nextInt(Long.SIZE - 2) + 1; // an index that isn't either end

      for (int i = 0; i < index; i++) {
        char c = leftString.charAt(i);
        leftString.setCharAt(i, rightString.charAt(i));
        rightString.setCharAt(i, c);
      }

      return new GeneTuple(
          new Gene(fromBinaryString(leftString.toString())),
          new Gene(fromBinaryString(rightString.toString()))
      );
    });
  }

  public static GeneTuple crossover(
      Gene a, Gene b, BiFunction<Gene, Gene, GeneTuple> xoverFn) {
    return xoverFn.apply(a, b);
  }

  public static Gene mutate(Gene original) {
    return mutate(original, (gene) -> {
      int index = random.nextInt(Long.SIZE);
      StringBuilder stringBuilder = new StringBuilder(Gene.toBinaryString(gene.dna));
      stringBuilder.setCharAt(index, stringBuilder.charAt(index) == '0' ? '1' : '0');
      return new Gene(fromBinaryString(stringBuilder.toString()));
    });
  }

  public static Gene mutate(Gene original, Function<Gene, Gene> mutationFn) {
    return mutationFn.apply(original);
  }

  static String toBinaryString(Long number) {
    StringBuilder stringBuilder = new StringBuilder(Long.toBinaryString(number));
    while (stringBuilder.length() < Long.SIZE) {
      stringBuilder.insert(0, '0');
    }
    return stringBuilder.toString();
  }

  static long fromBinaryString(String string) {
    return Long.parseUnsignedLong(string, 2);
  }

  @Override
  public String toString() {
    return toBinaryString(this.dna);
  }
}
