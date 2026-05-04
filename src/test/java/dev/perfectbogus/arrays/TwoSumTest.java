package dev.perfectbogus.arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TwoSumTest {

    @Test
    void testBasicCase() {
        assertArrayEquals(new int[]{0, 1}, TwoSumSenior.solve(new int[]{2, 7, 11, 15}, 9));
    }

    @Test
    void testDuplicateNumbers() {
        assertArrayEquals(new int[]{0, 1}, TwoSumSenior.solve(new int[]{3, 3}, 6));
    }

    @Test
    void testNegativeNumbers() {
        assertArrayEquals(new int[]{0, 2}, TwoSumSenior.solve(new int[]{-3, 1, 4}, 1));
    }

    @Test
    void testNoValidPair() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> TwoSumSenior.solve(new int[]{1, 2, 3}, 100)
        );

        assertEquals("No valid pair found for target: 100", exception.getMessage());
    }
}