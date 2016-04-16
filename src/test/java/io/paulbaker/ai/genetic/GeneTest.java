package io.paulbaker.ai.genetic;

import io.paulbaker.ai.genetic.Gene;
import io.paulbaker.ai.genetic.Gene.GeneTuple;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by thewookie on 4/12/16.
 */
public class GeneTest {

    @Test(invocationCount = 5)
    public void binaryTransformations(){
        Gene gene = new Gene();
        long originalDna = gene.getDna();
        long outputDna = Gene.fromBinaryString(Gene.toBinaryString(originalDna));
        assertEquals(originalDna, outputDna, "The DNA was changed, despite the fact the methods should only have transformed not mutated.");
    }

    @Test
    public void binaryMinValue(){
        Gene gene = new Gene(Long.MIN_VALUE);
        long originalDna = gene.getDna();
        long outputDna = Gene.fromBinaryString(Gene.toBinaryString(originalDna));
        assertEquals(originalDna, outputDna, "The DNA was changed, despite the fact the methods should only have transformed not mutated.");
    }

    @Test
    public void binaryMaxValue(){
        Gene gene = new Gene(Long.MAX_VALUE);
        long originalDna = gene.getDna();
        long outputDna = Gene.fromBinaryString(Gene.toBinaryString(originalDna));
        assertEquals(originalDna, outputDna, "The DNA was changed, despite the fact the methods should only have transformed not mutated.");
    }

    @Test
    public void binaryNegOneValue(){
        Gene gene = new Gene(-1L);
        long originalDna = gene.getDna();
        long outputDna = Gene.fromBinaryString(Gene.toBinaryString(originalDna));
        assertEquals(originalDna, outputDna, "The DNA was changed, despite the fact the methods should only have transformed not mutated.");
    }

    @Test
    public void binaryZeroValue(){
        Gene gene = new Gene(0L);
        long originalDna = gene.getDna();
        long outputDna = Gene.fromBinaryString(Gene.toBinaryString(originalDna));
        assertEquals(originalDna, outputDna, "The DNA was changed, despite the fact the methods should only have transformed not mutated.");
    }

    @Test
    public void crossover(){
        Gene ones = new Gene(-1);
        Gene zeros = new Gene(0);

        GeneTuple crossover = Gene.crossover(ones, zeros);
        assertNotEquals(crossover.getA(), crossover.getB());
        assertNotEquals(crossover.getA(), ones);
        assertNotEquals(crossover.getA(), zeros);
        assertNotEquals(crossover.getB(), ones);
        assertNotEquals(crossover.getB(), zeros);
    }

    @Test
    public void asdf(){
        Gene gene = new Gene();
        double fitness = gene.fitness();
    }

}
