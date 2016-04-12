package io.paulbaker.ai;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by paulbaker on 4/12/16.
 */
public class NeuralLayer {
  private List<Neuron> neurons;

  public NeuralLayer(int neuronCount, int neuronInputCount) {
    neurons = IntStream
        .range(0, neuronCount)
        .mapToObj(i -> new Neuron(neuronInputCount))
        .collect(Collectors.toList());
  }

//  public List<Double> getWeights() {
//    neurons.stream().map(neuron -> neuron)
//  }
}
