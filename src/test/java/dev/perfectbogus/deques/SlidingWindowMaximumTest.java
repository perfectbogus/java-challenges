package dev.perfectbogus.deques;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowMaximumTest {

    @Test
    void solve() {
        int[] data = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] results = SlidingWindowMaximum.solve(data, k);
        int[] expected = {3, 3, 5, 5, 6, 7};
        assertArrayEquals(expected, results);
    }

    @Test
    void testWindowSizeOne() {
        int[] data = {4, 2, 7};
        assertArrayEquals(new int[]{4,2,7} , SlidingWindowMaximum.solve(data, 1));
    }

    @Test
    void testWindowEqualsArray() {
        int[] data = {4, 2, 7};
        assertArrayEquals(new int[]{7}, SlidingWindowMaximum.solve(data, 3));
    }

    @Test
    void testAllSameValues() {
        int[] data = {5, 5, 5, 5};
        assertArrayEquals(new int[]{5, 5, 5}, SlidingWindowMaximum.solve(data, 2));
    }

    @Test
    void testDescendingArray() {
        int[] data = {5, 4, 3, 2, 1};
        assertArrayEquals(new int[]{5 , 4, 3}, SlidingWindowMaximum.solve(data, 3));
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> SlidingWindowMaximum.solve(null, 3));
    }

    @Test
    void testInvalidK() {
        assertThrows(IllegalArgumentException.class, () -> SlidingWindowMaximum.solve(new int[]{1, 2, 3}, 0));
    }
}