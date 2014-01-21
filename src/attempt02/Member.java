package attempt02;

import java.util.Random;

public class Member {
	private static Random rand = new Random();
	private static float crossoverRate = 0.7f;
	private static float mutationRate = 0.001f;
	private static int chromosomeLength = 300;
	private static char[] genes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '*', '/' };

	private int target;
	private String chromosome;
	private float fitness;

	public Member(int target) {
		this(target, randomChromosome());
	}

	public Member(int target, String chromosome) {
		this.target = target;
		this.chromosome = chromosome;
		this.fitness = 0;
	}
        
        public float getFitness(){
            return fitness;
        }
        
        public float scoreFitness(){
            fitness = 0;
            return getFitness();
        }

	private static String randomChromosome() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < chromosomeLength; i++){
			result.append(genes[rand.nextInt(genes.length)]);
		}
		return result.toString();
	}
        
        /*@Override
        public String toString(){
            return chromosome;
        }*/
}
