package io.paulbaker.ai;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by paulbaker on 4/12/16.
 */
public class NeuralNet {
    private int inputCount;
    private int outputCount;

    private List<NeuralLayer> neuralLayers;

    public NeuralNet(int inputCount, int hiddenLayerCount, int neuronCountPerLayer) {
        neuralLayers = IntStream
                .range(0, hiddenLayerCount + 2) // Input, Output, and hidden layers
                .mapToObj(i -> new NeuralLayer(neuronCountPerLayer, inputCount))
                .collect(Collectors.toList());
    }

    private NeuralNet(List<NeuralLayer> layers) {
        this.neuralLayers = layers;
    }

    public NeuralNet process() {
        return null;
    }


}
