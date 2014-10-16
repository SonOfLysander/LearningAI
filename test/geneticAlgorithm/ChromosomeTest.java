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
		fail("Not yet implemented");
	}

}
