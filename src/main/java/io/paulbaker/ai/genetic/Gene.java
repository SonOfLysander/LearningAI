package io.paulbaker.ai.genetic;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;

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

    public static Function<Long, Double> DefaultFitnessFunction = (dna) -> {
        // Place the bytes in the correct order, that we can iterate through next
        Deque<Byte> byteStack = new LinkedList<>();
        for (int i = 0; i < Long.SIZE; i += 4) {
            byte currentByte = 0;
            currentByte = (byte) (dna & 0b1111);
            {
                String s = toBinaryString(dna);
                String s1 = toBinaryString((long) currentByte);
                System.out.println();
            }
            byteStack.push(currentByte);
            dna = dna >> 4;
        }

        // Define which bytes are what
        Map<Byte, Double> binValues = new HashMap<>();
        Map<Byte, BinaryOperator<Double>> binOperators = new HashMap<>();
        {
            binValues.put((byte)0b0000, 0d);
            binValues.put((byte)0b0001, 1d);
            binValues.put((byte)0b0010, 2d);
            binValues.put((byte)0b0011, 3d);
            binValues.put((byte)0b0100, 4d);
            binValues.put((byte)0b0101, 5d);
            binValues.put((byte)0b0110, 6d);
            binValues.put((byte)0b0111, 7d);
            binValues.put((byte)0b1000, 8d);
            binValues.put((byte)0b1001, 9d);
            binOperators.put((byte)0b1010, (a, b) -> a + b);
            binOperators.put((byte)0b1011, (a, b) -> a - b);
            binOperators.put((byte)0b1100, (a, b) -> a * b);
            binOperators.put((byte)0b1101, (a, b) -> a / b);
        }

        // Iterate through all the values and figure out
        BinaryOperator<Double> currentOperator = null;
        Double total = null;
        while (!byteStack.isEmpty()){
            Byte pop = byteStack.pop();
            if (binValues.keySet().contains(pop)) {
                Double currentValue = binValues.get(pop);
                if (total == null) {
                    total = currentValue;
                    continue;
                }
                if (currentOperator == null)
                    continue;
                total = currentOperator.apply(total, currentValue);
                currentOperator = null;
            } else if (binOperators.keySet().contains(pop)) {
                if (currentOperator != null)
                    continue;
                currentOperator = binOperators.get(pop);
            }
        }

        return total == null ? 0 : total;
    };

  public double fitness(){
    return fitness(DefaultFitnessFunction);
  }

  public double fitness(Function<Long, Double> fitnessFunction){
    return fitnessFunction.apply(this.dna);
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
