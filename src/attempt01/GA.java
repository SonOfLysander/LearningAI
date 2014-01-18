package attempt01;

import java.util.ArrayList;
import java.util.Random;

public class GA {
	/*static random for obvious reasons*/
	static Random rand = new Random();
	/*this must be an even number in this implementation.*/
	private static int populationSize = 40;

	public static void main(String[] args) {
		new GA().goLive(42);
	}

	private void goLive(int target) {
		// our counter..
		int generation = 0;
		// these will be alternating buffers
		ArrayList<Chromosome> genePoolA = new ArrayList<Chromosome>(populationSize);
		ArrayList<Chromosome> genePoolB = new ArrayList<Chromosome>(populationSize);
		while (generation++ > 0) { /*if we overflow into negatives, then our code sucks so bad we should just stop and reconsider our life choices.*/
			ArrayList<Chromosome> pool, poolBuffer;
			/*we'll use the generation number to determine
			 which pool to use as a buffer and which to not*/
			if (generation % 2 == 0) {
				pool = genePoolA;
				poolBuffer = genePoolB;
			} else {
				pool = genePoolB;
				poolBuffer = genePoolA;
			}
			poolBuffer.clear();
			// iterating through for loop backwards, because we are moving members of
			// one pool into the other
			for (int i = pool.size() - 1; i >= 0; i -= 2) {
				Chromosome cOne = pullMemberFromPool(pool);
				Chromosome cTwo = pullMemberFromPool(pool);
			}
		}
	}

	private Chromosome pullMemberFromPool(ArrayList<Chromosome> pool) {
		// get the pool's total fitness.
		double total = 0d;
		for (Chromosome currentMember : pool) {
			total += currentMember.getScore();
		}
		// Generate random number between 0 & total fitness
		double slice = total * rand.nextDouble();

		// I'm stopping here. Reading off the java translation of the original code
		// is really bad. The person that did it didn't know java and probably
		// didn't know c++ either. Going directly to the C++ and translating myself
		// in package 'attempt2'.
		return null;
	}
}
