package dev.perfectbogus.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTest {

    @Test
    void testEvenArray() {
        int[] array = {1, 2, 3, 4};
        int[] expected = {4, 3, 2, 1};
        assertArrayEquals(expected, Reverse.Solve(array));
    }

    @Test
    void testOddArray() {
        int[] array = {1, 2, 3};
        int[] expected = {3, 2, 1};
        assertArrayEquals(expected, Reverse.Solve(array));
    }

}