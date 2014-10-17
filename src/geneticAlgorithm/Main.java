package geneticAlgorithm;

import java.nio.ByteBuffer;
import java.util.Stack;

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
		Chromosome testChrome = new Chromosome();
		System.out.println(testChrome);
		long genes = testChrome.getGenes();
		for (int i = Long.SIZE / 4; i >= 0; i--) {

		}
	}
}
