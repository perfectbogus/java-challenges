package dev.perfectbogus.threads.parallel_sum;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParallelSumTest {

    @Test
    void testSumOneToThousand() {
        int[] array = IntStream.rangeClosed(0, 1000).toArray();
        assertEquals(500_500L, ParallelSum.sum(array));
    }

    @Test
    void testUnevenSplit() {
        int[] array = IntStream.rangeClosed(1, 10).toArray();
        assertEquals(55L, ParallelSum.sum(array));
    }

    @Test
    void testSingleElement() {
        assertEquals(42L, ParallelSum.sum(new int[]{42}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0L, ParallelSum.sum(new int[0]));
    }

    @Test
    void testLargeValues() {
        int[] array = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertEquals((long) Integer.MAX_VALUE * 2, ParallelSum.sum(array));
    }
}