package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sort2DByRowSumTest {

    @Test
    void testBasicCase() {
        int[][] result = Sort2DByRowSum.sort(
                new int[][]{{3,1},{1,5},{2,2},{1,1}});

        assertArrayEquals(new int[]{1, 1}, result[0]);
        assertArrayEquals(new int[]{2, 2}, result[1]);
        assertArrayEquals(new int[]{3, 1}, result[2]);
        assertArrayEquals(new int[]{1, 5}, result[3]);
    }

    @Test
    void testAllSameSum() {
        int[][] result = Sort2DByRowSum.sort(
                new int[][]{{3,0},{1,2},{2,1}});
        // all sum=3, sort by first element
        assertArrayEquals(new int[]{1, 2}, result[0]);
        assertArrayEquals(new int[]{2, 1}, result[1]);
        assertArrayEquals(new int[]{3, 0}, result[2]);
    }

    @Test
    void testSingleRow() {
        int[][] result = Sort2DByRowSum.sort(
                new int[][]{{1, 2, 3}});
        assertArrayEquals(new int[]{1, 2, 3}, result[0]);
    }

    @Test
    void testWithNegatives() {
        int[][] result = Sort2DByRowSum.sort(
                new int[][]{{-1,2},{1,-3},{0,0}});
        // sums: [1, -2, 0]
        assertArrayEquals(new int[]{ 1,-3}, result[0]); // sum=-2
        assertArrayEquals(new int[]{ 0, 0}, result[1]); // sum=0
        assertArrayEquals(new int[]{-1, 2}, result[2]); // sum=1
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> Sort2DByRowSum.sort(null));
    }
}