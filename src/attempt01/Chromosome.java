package attempt01;

import java.util.Random;

class Chromosome {

	static Random rand = new Random();
	private static char[] geneTable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/' };
	private static int geneLength = 5;
	private static double crossRate = 0.7;
	private static double mutationRate = 0.001;

	private StringBuffer chromo = new StringBuffer(geneLength * 4);
	public StringBuffer decodeChromo = new StringBuffer(geneLength * 4);
	private double score;
	public int summation;

	public Chromosome(int target) {
		// generate a random chromosome
		for (int i = 0; i < geneLength; i++) {
			// creates a binary string based on a random character in our geneTable.
			String binaryString = Integer.toBinaryString(rand.nextInt(geneTable.length));
			// to make sure that gene is the correct length regardless of the number
			// we add leading zeros as placeholders.
			int fillLength = 4 - binaryString.length();
			for (int j = 0; j < fillLength; j++) {
				chromo.append('0');
			}
			// we then append the rest of the gene to the chromosome.
			chromo.append(binaryString);
		}
	}

	public final void ScoreChromo(int target) {
		// summation = addUp();
	}

	private final int addUp() {
		return 0;
	}

	public final static String decodeChromo(StringBuffer chromosome) {
		StringBuffer decode = new StringBuffer(0);
		for (int i = 0; i < chromosome.length(); i += 4) {
			int parsedBits = Integer.parseInt(chromosome.substring(i, i + 4));
			if (parsedBits < geneTable.length) {
				decode.append(parsedBits);
			}
		}
		return decode.toString();
	}
	public double getScore(){
		return score;
	}
}