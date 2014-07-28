package geneticAlgorithm;

import java.util.Random;

public class Chromosome {

	private static Random rand = new Random();
	private static float crossoverRate = 0.7f;
	private static double mutationRate = 0.001f;

	private long genes;

	public Chromosome() {
		this(rand.nextLong());
	}

	public Chromosome(long genes) {
		this.genes = genes;
	}

	public long getGenes() {
		return genes;
	}

	@Override
	public String toString() {
		return Long.toBinaryString(genes);
	}
}
