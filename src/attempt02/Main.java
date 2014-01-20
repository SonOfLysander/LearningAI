package attempt02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	// this must be an even number, for now
	private static int populationSize = 100;
	// The maximum number of generations before giving up.
	// 400 was used in original.
	private static int maxGenerationsBeforeAppocolypse = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Random rand = new Random();
		int target = 42;
		List<Member> poolA = new ArrayList<Member>(populationSize); // starting pool
		List<Member> poolB = new ArrayList<Member>(populationSize); // buffer pool
		for (int i = 0; i < poolA.size(); i++){
			poolA.add(new Member(target));
		}
	}
}
