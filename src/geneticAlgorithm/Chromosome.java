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

	public Chromosome crossover(Chromosome other) {
		byte[] geneArray = longToBytes(getGenes());
		byte[] otherArray = longToBytes(other.getGenes());
		int crossoverIndex = rand.nextInt(geneArray.length);
		byte[] crossedGenes = new byte[geneArray.length];
		for (int i = 0; i < crossedGenes.length; i++) {
			crossedGenes[i] = i < crossoverIndex ? geneArray[i] : otherArray[i];
		}
		return new Chromosome(bytesToLong(crossedGenes));
	}
	
	@Override
	public String toString() {
		return Long.toBinaryString(genes);
	}

	/**
	 * Taken from StackOverflow. Simple utility method to convert a long into a
	 * byte[].
	 * 
	 * @author http://stackoverflow.com/a/4485196/1478636
	 * 
	 * @param x
	 * @return
	 */
	private byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
		buffer.putLong(x);
		return buffer.array();
	}

	/**
	 * Taken from StackOverflow. Simple utility method to convert a byte[] into
	 * a long
	 * 
	 * @author http://stackoverflow.com/a/4485196/1478636
	 * 
	 * @param bytes
	 * @return
	 */
	private long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
		buffer.put(bytes);
		buffer.flip();// need flip
		return buffer.getLong();
	}
}
