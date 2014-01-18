package attempt02;

import java.util.Random;

public class Member {
	private static Random rand = new Random();
	private static float crossoverRate = 0.7f;
	private static float mutationRate = 0.001f;
	private static int chromosomeLength;

	private String chromosome;
	private float fitness;

	public Member(int target) {
	}
	public Member(int target, String chromosome){
	}
}
