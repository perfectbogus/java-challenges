package dev.perfectbogus.arrays.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortIntervalsTest {

    @Test
    void testBasicCase() {
        int[][] result = SortIntervals.sort(
                new int[][]{{1,4},{3,6},{1,8},{2,5},{3,3}});

        assertArrayEquals(new int[]{1, 8}, result[0]);
        assertArrayEquals(new int[]{1, 4}, result[1]);
        assertArrayEquals(new int[]{2, 5}, result[2]);
        assertArrayEquals(new int[]{3, 6}, result[3]);
        assertArrayEquals(new int[]{3, 3}, result[4]);
    }

    @Test
    void testBasicCaseSort2() {
        int[][] result = SortIntervals.sort2(
                new int[][]{{1,4},{3,6},{1,8},{2,5},{3,3}});

        assertArrayEquals(new int[]{1, 8}, result[0]);
        assertArrayEquals(new int[]{1, 4}, result[1]);
        assertArrayEquals(new int[]{2, 5}, result[2]);
        assertArrayEquals(new int[]{3, 6}, result[3]);
        assertArrayEquals(new int[]{3, 3}, result[4]);
    }

    @Test
    void testAllSameStart() {
        int[][] result = SortIntervals.sort(
                new int[][]{{1,3},{1,5},{1,1}});
        // same start → sort by end descending
        assertArrayEquals(new int[]{1, 5}, result[0]);
        assertArrayEquals(new int[]{1, 3}, result[1]);
        assertArrayEquals(new int[]{1, 1}, result[2]);
    }

    @Test
    void testNoTies() {
        int[][] result = SortIntervals.sort(
                new int[][]{{3,4},{1,2},{5,6}});
        assertArrayEquals(new int[]{1, 2}, result[0]);
        assertArrayEquals(new int[]{3, 4}, result[1]);
        assertArrayEquals(new int[]{5, 6}, result[2]);
    }

    @Test
    void testSingleInterval() {
        int[][] result = SortIntervals.sort(
                new int[][]{{1, 5}});
        assertArrayEquals(new int[]{1, 5}, result[0]);
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortIntervals.sort(null));
    }
}