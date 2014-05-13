package Model;

import java.util.Random;

public class SimpleLib {
	public static class NotImplementedException extends RuntimeException {
	    private static final long serialVersionUID = 1L;
	    public NotImplementedException(){}
	}

	private static Random rand = new Random();
	public static int randMinMax(int min, int max) throws Exception{
		throw new NotImplementedException();
	}
}
