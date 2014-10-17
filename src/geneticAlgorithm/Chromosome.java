package geneticAlgorithm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

public class Chromosome {

	private static final Random rand = new Random();
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

	private void setGenes(long newGene) {
		genes = newGene;
	}

	public int parseToValue() {
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

	public static void crossover(Chromosome a, Chromosome b) {
		crossover(0.7f, a, b);
	}

	public static void crossover(float crossoverRate, Chromosome a, Chromosome b) {
		if (crossoverRate < 0 || crossoverRate > 1)
			throw new IllegalArgumentException(
					"crossoverRate must be between 0 and 1");
		if (rand.nextFloat() >= crossoverRate)
			return;
		final int binarySingleBit = 0b1;
		long geneA = a.getGenes(), geneB = b.getGenes();
		long crossedGeneA = 0, crossedGeneB = 0;
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
			long longA = (i < crossoverIndex ? geneA : geneB);
			long longB = (i < crossoverIndex ? geneB : geneA);
			crossedGeneA = crossedGeneA << 1;
			crossedGeneB = crossedGeneB << 1;
			longA = longA >> (Long.SIZE - i) & binarySingleBit;
			longB = longB >> (Long.SIZE - i) & binarySingleBit;
			crossedGeneA |= longA;
			crossedGeneB |= longB;
		}
		// Give the chromosomes the newly swapped genes.
		a.setGenes(crossedGeneA);
		b.setGenes(crossedGeneB);
	}

	public static void mutate(float mutationRate, Chromosome a) {
		if (mutationRate < 0 || mutationRate > 1)
			throw new IllegalArgumentException(
					"crossoverRate must be between 0 and 1");
		if (rand.nextFloat() >= mutationRate)
			return;
		// We need to pick a random bit
		int randomPosition = rand.nextInt(Long.SIZE);
		// randomPosition = 32;
		long genes = a.getGenes();
		// Get the bit we will flip
		long thatBit = (genes >> randomPosition) & 0x1;
		// Flip the bit to it's inverse
		if (thatBit == 1) {
			genes ^= thatBit << randomPosition;
		} else {
			// genes = genes & ~(1 << randomPosition);
			genes = genes | (1l << randomPosition);
		}

		// SET AND DEBUG
		System.out.println(a);
		a.setGenes(genes);
		System.out.println(a);
		System.out.println("We're flipping " + thatBit + " at position "
				+ randomPosition);
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
