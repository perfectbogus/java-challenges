package dev.perfectbogus.leetcode.sliding.window;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxSumSubarrayTest {

    @Test
    void testBasicCase() {
        assertEquals(9,
                MaxSumSubarray.solve(
                        new int[]{2, 1, 5, 1, 3, 2}, 3));
    }

    @Test
    void testWindowEqualsArray() {
        assertEquals(15,
                MaxSumSubarray.solve(
                        new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    void testWindowSizeOne() {
        assertEquals(9,
                MaxSumSubarray.solve(
                        new int[]{2, 9, 1, 3, 5}, 1));
    }

    @Test
    void testAllSameValues() {
        assertEquals(9,
                MaxSumSubarray.solve(
                        new int[]{3, 3, 3, 3, 3}, 3));
    }

    @Test
    void testWithNegatives() {
        assertEquals(4,
                MaxSumSubarray.solve(
                        new int[]{-1, 2, 3, -4, 5, 1}, 3));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> MaxSumSubarray.solve(null, 3));
    }

    @Test
    void testKGreaterThanLength() {
        assertThrows(IllegalArgumentException.class,
                () -> MaxSumSubarray.solve(
                        new int[]{1, 2}, 5));
    }
}