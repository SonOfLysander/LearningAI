package geneticAlgorithm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
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

	public int parseToInt() {
		int parsedNumber = 0, opperator = 10;
		boolean lookingForNumber = true;
		for (int i = 0; i < Long.SIZE / 4; i++) {
			int currentNumber = (int) ((genes >> (Long.SIZE - Long.SIZE - ((i + 1) * 4))) & 0xF);
			if (currentNumber >= 0 && currentNumber <= 9) {
				if (lookingForNumber) {
					switch (opperator) {
						case 10 :
							parsedNumber += currentNumber;
							break;
						case 11 :
							parsedNumber -= currentNumber;
							break;
						case 12 :
							parsedNumber *= currentNumber;
							break;
						case 13 :
							if (currentNumber != 0) // avoid divide by 0
								parsedNumber /= currentNumber;
							break;
						default :
							continue;
					}
					lookingForNumber = false;
				}
			} else if (currentNumber >= 10 && currentNumber <= 13) {
				if (!lookingForNumber) {
					opperator = currentNumber;
					lookingForNumber = true;
				}
			}
		}
		return parsedNumber;
	}
	@Override
	public String toString() {
		return Long.toBinaryString(genes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (genes ^ (genes >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Chromosome))
			return false;
		Chromosome other = (Chromosome) obj;
		if (genes != other.genes)
			return false;
		return true;
	}
}
