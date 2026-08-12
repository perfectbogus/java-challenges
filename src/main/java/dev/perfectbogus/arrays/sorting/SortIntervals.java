package dev.perfectbogus.arrays.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class SortIntervals {

    public static int[][] sort(int[][] intervals) {
        if (intervals == null) throw new IllegalArgumentException("intervals is null");
        // TODO
        // comparingInt(start) + thenComparingInt(end reversed)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        return intervals;
    }

    public static int[][] sort2(int[][] intervals) {
        if (intervals == null) throw new IllegalArgumentException("intervals is null");

        Arrays.sort(intervals,
                Comparator.comparingInt((int[] a) -> a[0])
                        .thenComparing(
                                Comparator.comparingInt((int[] a) -> a[1])
                                        .reversed()
                        ));

        return intervals;
    }
}
