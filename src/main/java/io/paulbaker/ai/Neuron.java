package io.paulbaker.ai;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by paulbaker on 4/12/16.
 */
public class Neuron {
  private static final Random random = new Random();
  private int inputCount;
  private List<Double> inputWeights;

  public Neuron(int inputCount) {
    this.inputCount = inputCount;
    inputWeights = DoubleStream
        .generate(random::nextDouble)
        .limit(inputCount + 1)
        .boxed()
        .collect(Collectors.toList());
  }

}
