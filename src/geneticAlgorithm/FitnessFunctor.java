package geneticAlgorithm;

public abstract class FitnessFunctor {
	public int getFitness(Chromosome chromosome) {
		return getFitness(chromosome.getGenes());
	}

	public abstract int getFitness(long gene);
}
