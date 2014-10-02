package geneticAlgorithm;

import java.nio.ByteBuffer;
import java.util.Stack;

public class Main {

	private static class ToTwentyThreeFitness extends FitnessFunctor {

		private ToTwentyThreeFitness() {
		}

		@Override
		public int getFitness(long gene) {
			int fitness = 0;
			boolean onOpperator = false;
			char currentOpperator = '+';
			byte[] bytes = ByteBuffer.allocate(8).putLong(gene).array();
			for (int i = 0; i < bytes.length; i++) {
				if (onOpperator == false) {
					if (bytes[i] >= 0 && bytes[i] < 10) {
						onOpperator = true;
						switch (currentOpperator) {
						case '+':
							fitness += bytes[i];
							break;
						case '-':
							fitness -= bytes[i];
							break;
						case '*':
							fitness *= bytes[i];
							break;
						case '/':
							fitness /= bytes[i];
							break;
						}
					} else {
						continue;
					}
				} else {
					switch (bytes[i]) {
					case 10:
						currentOpperator = '+';
						onOpperator = false;
						break;
					case 11:
						currentOpperator = '-';
						onOpperator = false;
						break;
					case 12:
						currentOpperator = '*';
						onOpperator = false;
						break;
					case 13:
						currentOpperator = '/';
						onOpperator = false;
						break;
					default:
						continue;
					}
				}
			}

			return fitness;
		}
	}

	public static void main(String[] args) {
		Chromosome testChrome = new Chromosome();
		System.out.println(testChrome);
		long genes = testChrome.getGenes();
		for (int i = Long.SIZE / 4; i >= 0; i--) {

		}
		// for (int i = 0; i < ; i++) {
		// byte tmp = (byte) ((genes >> Long.SIZE - ) & 0xF);
		// System.out.print(Integer.toBinaryString(tmp) + " ");
		// }
		// System.out.println(new Chromosome());
		// System.out.println(new Chromosome());
	}
}
