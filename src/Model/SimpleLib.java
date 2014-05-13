package Model;

import java.util.Random;

public class SimpleLib {
	public static class NotImplementedException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public NotImplementedException() {
		}
	}

	private static Random rand = new Random();

	public static int randMinMax(int min, int max) {
		if (max < min) {
			int swp = min;
			min = max;
			max = swp;
		}
		return rand.nextInt(max - min) + min;
	}
}
