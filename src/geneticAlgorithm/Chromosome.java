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
		final int binarySingleBit = 0b1;
		long geneA = chromoA.getGenes(), geneB = chromoB.getGenes(), crossedGene = 0;
		// Randomly, we will switch genes around. We do this to prevent any bias
		// towards which one will be the prefix or suffix.
		if (rand.nextBoolean()) {
			geneA ^= geneB;
			geneB ^= geneA;
			geneA ^= geneB;
		}
		// Determine the index for which we will switch two genes from
		int crossoverIndex = rand.nextInt(Long.SIZE - 2) + 1;
		for (int i = 1; i < Long.SIZE + 1; i++) {
			long currentGene = (i < crossoverIndex ? geneA : geneB);
			crossedGene = crossedGene << 1;
			// System.out.println(Long.toBinaryString(currentGene));
			currentGene = currentGene >> (Long.SIZE - i) & binarySingleBit;
			// System.out.println(Long.toBinaryString(currentGene));
			crossedGene |= currentGene;
		}
		// Give us the new gene
		return new Chromosome(crossedGene);
	}
	public int parseToInt() {
		final int binaryFourBits = 0xF;
		int parsedNumber = 0, opperator = 10;
		boolean lookingForNumber = true;
		for (int i = 0; i < Long.SIZE / 4; i++) {
			int currentNumber = (int) ((genes >> (Long.SIZE - Long.SIZE - ((i + 1) * 4))) & binaryFourBits);
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
		StringBuilder result = new StringBuilder(64);
		String binaryString = Long.toBinaryString(genes);
		for (int i = 0, binaryLength = Long.SIZE - binaryString.length(); i < binaryLength; i++) {
			result.append("0");
		}
		result.append(binaryString);
		return result.toString();
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
