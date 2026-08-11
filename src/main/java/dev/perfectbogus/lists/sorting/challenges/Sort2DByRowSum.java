package dev.perfectbogus.lists.sorting.challenges;

import java.util.Arrays;
import java.util.Comparator;

public class Sort2DByRowSum {

    public static int[][] sort(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("matrix is null");
        // TODO
        // comparingInt(rowSum) + thenComparingInt(firstElement)
        Arrays.sort(matrix, (a, b) -> {
            int suma = Arrays.stream(a).sum();
            int sumb = Arrays.stream(b).sum();
            if (suma != sumb) return Integer.compare(suma, sumb);
            return Integer.compare(a[0], b[0]);
        });
        return matrix;
    }

    public static int[][] sort2(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("matrix is null");

        Arrays.sort(matrix,
                Comparator.comparingInt((int[] row) -> Arrays.stream(row).sum())
                        .thenComparingInt(row -> row[0]));

        return matrix;
    }
}
