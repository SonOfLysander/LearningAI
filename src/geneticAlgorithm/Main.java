package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	private static class ToFortyTwoFitness implements FitnessFunctor {

		private ToFortyTwoFitness() {
		}

		@Override
		public int evaluateFitness(Chromosome chromosome) {
			int geneValue = chromosome.parseToValue();
			if (geneValue == 42)
				return 42;
			return (int) (1 / (double) (42 - geneValue));
		}
	}

	public static void main(String[] args) {
		FitnessFunctor fitnessFunctor = new ToFortyTwoFitness();
		final int populationSize = 20;
		int generation = 0;
		Map<Boolean, Chromosome[]> populationPools = new TreeMap<Boolean, Chromosome[]>();
		populationPools.put(true, new Chromosome[populationSize]);

		// Initialize the first population pool. We will use the other pool for
		// buffering and we will alternate pools.
		{
			Chromosome[] originalPool = populationPools.get(true);
			for (int i = 0; i < originalPool.length; i++) {
				originalPool[i] = new Chromosome();
			}
		}

		boolean solutionFound = false;
		while (!solutionFound) {
			boolean buffer = generation++ % 2 == 0;
			Chromosome[] originalPool = populationPools.get(!buffer);
			Chromosome[] bufferPool = populationPools.get(buffer);
			
		}
	}
}
