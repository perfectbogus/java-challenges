package dev.perfectbogus.comparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixComparatorsChallenge {

    // In all the challenges, a grid is an array of rows, and a row is an array of numbers. In the examples, a grid is
    // written like this: [[3, 1], [2]] (two rows: the first one has 3 and 1, the second one has 2).

    // CHALLENGE 1
    // Sorts the array itself (it does not return anything): after the call, the rows are ordered by their first number,
    // from the greatest to the smallest. Rows with the same first number keep the order they had. Every row has at
    // least one number. The rows themselves do not change, only their position in the array.
    // Example: rows = [[1, 5], [3, 2], [2, 9]]  becomes  [[3, 2], [2, 9], [1, 5]]
    //          rows = [[4, 1], [4, 0]]          becomes  [[4, 1], [4, 0]]
    //          rows = [[7]]                     becomes  [[7]]
    //          rows = []                        becomes  []
    public static void sortByFirstDescending(int[][] rows) {
        Comparator<int[]> byFirstNum = Comparator.comparing(a -> a[0], Comparator.reverseOrder());
        Arrays.sort(rows, byFirstNum);
    }

    public static void sortByFirstDescendingSimplified(int[][] rows) {
        Arrays.sort(rows, Comparator.comparing(a -> a[0], Comparator.reverseOrder()));
    }

    // CHALLENGE 2
    // Returns a new grid with the same rows, ordered by their last number from the smallest to the greatest. If two
    // rows have the same last number, the one with the smaller first number goes first. If both numbers are the same,
    // they keep the order they have in the original grid. Every row has at least one number (in a row with one number,
    // the first and the last number are the same). The original grid is not modified.
    // Example: sortByLastThenFirst([[5, 1], [2, 1], [0, 0, 9]])  returns [[2, 1], [5, 1], [0, 0, 9]]
    //          sortByLastThenFirst([[3], [1]])                   returns [[1], [3]]
    //          sortByLastThenFirst([[1, 9, 2], [1, 0, 2]])       returns [[1, 9, 2], [1, 0, 2]]
    //          sortByLastThenFirst([])                           returns []
    public static int[][] sortByLastThenFirst(int[][] rows) {
        Comparator<int[]> byLast = Comparator.comparingInt(a -> a[a.length - 1]);
        Comparator<int[]> byFirst = Comparator.comparingInt(a -> a[0]);
        return Arrays.stream(rows).sorted(byLast.thenComparing(byFirst)).toArray(int[][]::new);
    }

    public static int[][] sortByLastThenFirstSimplified(int[][] rows) {
        int[][] result = rows.clone();
        Arrays.sort(result, Comparator.<int[]>comparingInt(row -> row[row.length - 1])
                .thenComparingInt(row -> row[0]));
        return result;
    }

    // CHALLENGE 3
    // Each row of the grid is a point of the plane, written as [x, y]. Returns a new grid with the same points,
    // ordered by their distance to the point [0, 0], from the closest to the farthest. Two points at the same distance
    // keep the order they have in the original grid. The coordinates are between -10000 and 10000. The original grid
    // is not modified.
    // Example: sortPointsByDistance([[3, 4], [1, 1], [0, -2]])  returns [[1, 1], [0, -2], [3, 4]]
    //          sortPointsByDistance([[-5, 0], [0, 5]])          returns [[-5, 0], [0, 5]]
    //          sortPointsByDistance([[3, 3], [0, 5]])           returns [[3, 3], [0, 5]]
    //          sortPointsByDistance([])                         returns []
    public static int[][] sortPointsByDistanceExtended(int[][] points) {
        return Arrays.stream(points)
                .sorted(Comparator.comparingDouble(a ->
                        Math.abs(Math.sqrt((a[0] * a[0]) + (a[1] * a[1])))))
                .toArray(int[][]::new);
    }

    public static int[][] sortPointsByDistance(int[][] points) {
        Map<int[], Double> pre = Arrays.stream(points).collect(Collectors.toMap(
                Function.identity(),
                a -> Math.abs(Math.sqrt((a[0] * a[0]) + (a[1] * a[1])))
        ));
        return Arrays.stream(points).sorted(Comparator.comparingDouble(pre::get)).toArray(int[][]::new);
    }

    // CHALLENGE 4
    // Each row of the grid is an interval, written as [start, end], where start is not greater than end. Returns a new
    // grid with the same intervals, ordered by their start from the smallest to the greatest. If two intervals have
    // the same start, the one with the smaller end goes first. The original grid is not modified.
    // Example: sortIntervals([[3, 5], [1, 9], [1, 2]])  returns [[1, 2], [1, 9], [3, 5]]
    //          sortIntervals([[2, 3], [1, 100]])        returns [[1, 100], [2, 3]]
    //          sortIntervals([[-1, 0], [-5, 3]])        returns [[-5, 3], [-1, 0]]
    //          sortIntervals([])                        returns []
    public static int[][] sortIntervals(int[][] intervals) {
        return Arrays.stream(intervals)
                .sorted(Comparator.<int[]>comparingInt(a -> a[0])
                        .thenComparingInt(a -> a[1]))
                .toArray(int[][]::new);
    }

    // CHALLENGE 5
    // Returns a new grid with the same rows, ordered by the number they have in the column "col": from the smallest to
    // the greatest if "descending" is false, and from the greatest to the smallest if "descending" is true. Rows with
    // the same number in that column keep the order they have in the original grid, also when "descending" is true.
    // Every row has more than "col" numbers, and col is zero or greater. The original grid is not modified.
    // Example: sortByColumn([[3, 1], [1, 9], [2, 5]], 0, false)  returns [[1, 9], [2, 5], [3, 1]]
    //          sortByColumn([[3, 1], [1, 9], [2, 5]], 0, true)   returns [[3, 1], [2, 5], [1, 9]]
    //          sortByColumn([[4, 2], [4, 1]], 0, true)           returns [[4, 2], [4, 1]]
    //          sortByColumn([], 0, false)                        returns []
    public static int[][] sortByColumnBad(int[][] rows, int col, boolean descending) {
        Comparator<int[]> byCol = Comparator.comparingInt(a -> a[col]);
        if (descending) {
            byCol = byCol.reversed();
        }
        return Arrays.stream(rows).sorted(byCol).toArray(int[][]::new);
    }

    public static int[][] sortByColumn(int[][] rows, int col, boolean descending) {
        Comparator<int[]> byCol = descending ?
                Comparator.<int[]>comparingInt(a -> a[col]).reversed() :
                Comparator.comparingInt(a -> a[col]);
        return Arrays.stream(rows).sorted(byCol).toArray(int[][]::new);
    }

    // CHALLENGE 6
    // Every row has exactly 3 numbers. Returns a new grid with the same rows, ordered by their first number from the
    // smallest to the greatest. If two rows have the same first number, the one with the greater second number goes
    // first. If they also have the same second number, the one with the smaller third number goes first. The original
    // grid is not modified.
    // Example: sortByThreeColumns([[1, 2, 3], [1, 5, 0], [0, 0, 0]])  returns [[0, 0, 0], [1, 5, 0], [1, 2, 3]]
    //          sortByThreeColumns([[1, 2, 9], [1, 2, 3]])             returns [[1, 2, 3], [1, 2, 9]]
    //          sortByThreeColumns([[2, 2, 2]])                        returns [[2, 2, 2]]
    //          sortByThreeColumns([])                                 returns []
    public static int[][] sortByThreeColumns(int[][] rows) {
        Comparator<int[]> byFirst = Comparator.comparingInt(a -> a[0]);
        Comparator<int[]> bySecondDesc = Comparator.comparing(a -> a[1], Comparator.reverseOrder());
        Comparator<int[]> byThird = Comparator.comparingInt(a -> a[2]);
        return Arrays.stream(rows)
                .sorted(byFirst
                        .thenComparing(bySecondDesc)
                        .thenComparing(byThird))
                .toArray(int[][]::new);
    }

    public static int[][] sortByThreeColumnsSimplified(int[][] rows) {
        return Arrays.stream(rows)
                .sorted(Comparator.<int[]>comparingInt(a -> a[0])
                        .thenComparing(a -> a[1], Comparator.reverseOrder())
                        .thenComparingInt(a -> a[2]))
                .toArray(int[][]::new);
    }

    // CHALLENGE 7
    // Returns a new grid with the same rows, ordered by their length (how many numbers they have) from the longest to
    // the shortest. If two rows have the same length, the one with the smaller sum goes first. If they also have the
    // same sum, they keep the order they have in the original grid. A row can be empty (its sum is 0). The sums fit in
    // an int. The original grid is not modified.
    // Example: sortByLengthThenSum([[5], [1, 1], [0], [2, 2, 2], [1, 0]])
    //                                                        returns [[2, 2, 2], [1, 0], [1, 1], [0], [5]]
    //          sortByLengthThenSum([[], [0]])                returns [[0], []]
    //          sortByLengthThenSum([[1, 2], [2, 1], [0, 3]]) returns [[1, 2], [2, 1], [0, 3]]
    //          sortByLengthThenSum([])                       returns []
    public static int[][] sortByLengthThenSum(int[][] rows) {
        return Arrays.stream(rows)
                .sorted(Comparator.comparing((int[] a) -> a.length, Comparator.reverseOrder())
                        .thenComparingInt(a -> Arrays.stream(a).sum()))
                .toArray(int[][]::new);
    }

    public static int[][] sortByLengthThenSumPerformance(int[][] rows) {
        Map<int[], Integer> map = Arrays.stream(rows).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.summingInt(a -> Arrays.stream(a).sum())
        ));
        return Arrays.stream(rows)
                .sorted(Comparator.comparing((int[] a) -> a.length, Comparator.reverseOrder()).thenComparingInt(map::get))
                .toArray(int[][]::new);
    }

    // CHALLENGE 8
    // Each row of the grid is a record with two texts, written as [name, city]. Returns a new grid with the same
    // records, ordered by name in alphabetical order, ignoring the case ("a" and "A" are the same letter). If two
    // records have the same name (ignoring the case), the one with the city that comes first alphabetically (also
    // ignoring the case) goes first. If both are the same, they keep the order they have in the original grid. The
    // original grid is not modified.
    // Example: sortRecordsByName([[bob, Quito], [Ana, Lima], [ana, Cairo]])  returns [[ana, Cairo], [Ana, Lima],
    //                                                                                  [bob, Quito]]
    //          sortRecordsByName([[a, x], [B, y]])                            returns [[a, x], [B, y]]
    //          sortRecordsByName([[ana, Lima], [Ana, LIMA]])                  returns [[ana, Lima], [Ana, LIMA]]
    //          sortRecordsByName([])                                          returns []
    public static String[][] sortRecordsByName(String[][] records) {
        Comparator<String[]> byName = (a, b) -> a[0].compareToIgnoreCase(b[0]);
        Comparator<String[]> byCity = ( a, b) -> a[1].compareToIgnoreCase(b[1]);
        return Arrays.stream(records)
                .sorted(byName.thenComparing(byCity))
                .toArray(String[][]::new);
    }

    public static String[][] sortRecordsByNameExtended(String[][] records) {
        Comparator<String[]> byName = Comparator.comparing(s -> s[0], String.CASE_INSENSITIVE_ORDER);
        Comparator<String[]> byCity = Comparator.comparing(s -> s[1], String.CASE_INSENSITIVE_ORDER);
        return Arrays.stream(records)
                .sorted(byName.thenComparing(byCity))
                .toArray(n -> new String[n][]);
    }

    // CHALLENGE 9
    // Returns the positions (indexes) of the rows of the grid, ordered by the sum of the numbers of each row from the
    // smallest sum to the greatest. If two rows have the same sum, the one with the smaller position goes first. A row
    // can be empty (its sum is 0). The sums fit in an int. The grid is not modified.
    // Example: sortedRowIndices([[5, 5], [1], [2, 3]])  returns [1, 2, 0]     (the sums are 10, 1 and 5)
    //          sortedRowIndices([[1], [1]])             returns [0, 1]
    //          sortedRowIndices([[], [-1]])             returns [1, 0]
    //          sortedRowIndices([])                     returns []
    public static int[] sortedRowIndicesExtended(int[][] rows) {
        Integer[] indices = new Integer[rows.length];
        for (int i = 0; i < rows.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, Comparator.comparingInt((Integer i) -> Arrays.stream(rows[i]).sum()));
        int[] result = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            result[i] = indices[i];
        }
        return result;
    }

    public static int[] sortedRowIndices(int[][] rows) {
        return IntStream.range(0, rows.length)
                .boxed()
                .sorted(Comparator.comparingInt(i -> Arrays.stream(rows[i]).sum()))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // CHALLENGE 10
    // Returns a Comparator for rows with at least two numbers. It compares two rows by their second number (the
    // number in position 1) from the smallest to the greatest, and if those are equal, by their first number (the
    // number in position 0) from the smallest to the greatest. It returns a negative number if the first row goes
    // before the second one, a positive number if it goes after, and 0 if they have the same two numbers. The
    // comparator must work with any int, also the biggest and the smallest ones.
    // Example: with rows = [[5, 1], [2, 3], [1, 3]]
    //          Arrays.sort(rows, bySecondThenFirst())      leaves  [[5, 1], [1, 3], [2, 3]]
    //          bySecondThenFirst().compare([1, 2], [9, 3]) is negative
    //          bySecondThenFirst().compare([4, 4], [4, 4]) is 0
    public static Comparator<int[]> bySecondThenFirst() {
        Comparator<int[]> byFirst = Comparator.comparingInt(a -> a[1]);
        Comparator<int[]> bySecond = Comparator.comparingInt(a -> a[0]);
        return byFirst.thenComparing(bySecond);
    }

    public static Comparator<int[]> bySecondThenFirstSimplified() {
        return Comparator.<int[]>comparingInt(a -> a[1]).thenComparingInt(a -> a[0]);
    }
}
