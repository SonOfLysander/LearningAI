package Model;

public class Population {
	private final int POP_SIZE;
	private final int MAX_GENERATION;
	// These two will act as a buffer we will switch between.
	private Chromosome[] populationA;
	private Chromosome[] populationB;

	public Population(int populationMax, int generationMax) {
		this.POP_SIZE = populationMax;
		this.MAX_GENERATION = generationMax;
		populationA = new Chromosome[this.POP_SIZE];
		populationB = new Chromosome[this.POP_SIZE];
	}
}
