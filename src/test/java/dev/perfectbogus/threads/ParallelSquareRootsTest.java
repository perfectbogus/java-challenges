package dev.perfectbogus.threads;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelSquareRootsTest {

    @Test
    void testOneNumber() {
        List<Double> results = ParallelSquareRoots.compute(List.of(4));
        assertEquals(List.of(2.0), results);
    }

    @Test
    void testSquareNumbers() {
        List<Double> results = ParallelSquareRoots.compute(List.of(4, 9, 16, 25, 36));
        assertEquals(List.of(2.0, 3.0, 4.0, 5.0, 6.0), results);
    }

    @Test
    void testNonPerfectSquare() {
        List<Double> results = ParallelSquareRoots.compute(List.of(2));
        assertEquals(1, results.size());
        assertEquals(Math.sqrt(2), results.get(0), 0.0001);
    }

    @Test
    void testListNull() {
        assertThrows(IllegalArgumentException.class, () -> ParallelSquareRoots.compute(null));
    }

    @Test
    void testEmptyList() {
        List<Double> results = ParallelSquareRoots.compute(List.of());
        assertEquals(List.of(), results);
    }

    @Test
    void testNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> ParallelSquareRoots.compute(List.of(-1)));
    }

}