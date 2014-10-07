package geneticAlgorithm;

import java.nio.ByteBuffer;
import java.util.Random;

public class Chromosome {

	private static final Random rand = new Random();
	private static final float crossoverRate = 0.7f;
	private static final double mutationRate = 0.001f;

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

	public static Chromosome crossover(Chromosome chromoA, Chromosome chromoB) {
		long geneA = chromoA.getGenes(), geneB = chromoB.getGenes();
		long crossedGene = 0, binaryOr = 0xF;
		int crossoverIndex = rand.nextInt(Long.SIZE / 4);
		// int crossoverIndex = rand.nextInt(geneArray.length);
		for (int i = 0; i < Long.SIZE / 4; i++) {
			long currentGene = (i < crossoverIndex ? geneA : geneB);
			crossedGene |= (currentGene >> (i * 4)) & binaryOr;
		}

		return new Chromosome(crossedGene);
	}

	@Override
	public String toString() {
		return Long.toBinaryString(genes);
	}
}
