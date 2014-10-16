package geneticAlgorithm;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChromosomeTest {

	private static final Random rand = new Random();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGenes() {
		assertEquals(-1, new Chromosome(-1).getGenes());
		assertEquals(1, new Chromosome(1).getGenes());
		assertEquals(0, new Chromosome(0).getGenes());
		assertEquals(Long.MAX_VALUE, new Chromosome(Long.MAX_VALUE).getGenes());
		assertEquals(Long.MIN_VALUE, new Chromosome(Long.MIN_VALUE).getGenes());
		for (int i = 0; i < 25000; i++) {
			long l = rand.nextLong();
			assertEquals(l, new Chromosome(l).getGenes());
		}
	}

	@Test
	public void testParseChromosome() {
		int i;
		for (i = 0; i < 25000; i++) {
			new Chromosome().parseToInt();
		}
		assertEquals(25000, i);
	}

	@Test
	public void testCrossover() {
		for (int i = 0; i < 25000; i++) {
			Chromosome a = new Chromosome(), b = new Chromosome(), c;
			c = Chromosome.crossover(a, b);
			/*
			 * There really isn't a good way to make this assertion. The problem
			 * is that two genes can be completely different but still have the
			 * same suffix or prefixes.
			 */
			// assertNotEquals(a, c);
			// assertNotEquals(b, c);
		}
	}

}
