package dev.perfectbogus.sorting;

import java.util.*;

public class GridSortingChallenge {

    // In all the challenges, a grid is an array of rows, and a row is an array of numbers. In the examples, a grid is
    // written like this: [[3, 1], [2]] (two rows: the first one has 3 and 1, the second one has 2).

    // CHALLENGE 1
    // Returns a new grid where each row has the same numbers as the original row, sorted from the smallest to the
    // greatest. The rows keep their position. The rows can have different lengths and can be empty, and the grid can
    // have no rows. The original grid is not modified.
    // Example: sortEachRow([[3, 1, 2], [9, 8]])  returns [[1, 2, 3], [8, 9]]
    //          sortEachRow([[5]])                returns [[5]]
    //          sortEachRow([[], [2, 1]])         returns [[], [1, 2]]
    //          sortEachRow([])                   returns []
    public static int[][] sortEachRow(int[][] grid) {
        int[][] res = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            int[] tmp = grid[i].clone();
            Arrays.sort(tmp);
            res[i] = tmp;
        }
        return res;
    }

    // CHALLENGE 2
    // The grid is rectangular: all the rows have the same length. Returns a new grid where each column has the same
    // numbers as the original column, sorted from the top (smallest) to the bottom (greatest). The columns keep their
    // position. The grid can have no rows, and the rows can have no numbers. The original grid is not modified.
    // Example: sortEachColumn([[3, 1], [2, 5], [1, 4]])  returns [[1, 1], [2, 4], [3, 5]]
    //          sortEachColumn([[9, 8, 7]])               returns [[9, 8, 7]]
    //          sortEachColumn([[2], [1]])                returns [[1], [2]]
    //          sortEachColumn([])                        returns []
    public static int[][] sortEachColumn(int[][] grid) {
        if (grid.length == 0) return new int[0][];

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] result = new int[rows][cols];
        for (int c = 0; c < cols; c++) {
            int[] column = new int[rows];
            for (int r = 0; r < rows; r++) {
                column[r] = grid[r][c];
            }
            Arrays.sort(column);
            for (int r = 0; r < rows; r++) {
                result[r][c] = column[r];
            }
        }
        return result;
    }

    public static int[][] sortEachColumnIncomplete(int[][] grid) {
        int n = grid.length;
        if (n == 0) return new int[0][];
        int m = grid[0].length;
        int[][] res = new int[n][m];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int[] row : grid) {
            for (int i : row) {
                q.add(i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = q.poll();
            }
        }

        System.out.println(Arrays.deepToString(res));

        return res;
    }

    // CHALLENGE 3
    // Returns a new grid with the same rows, ordered by the sum of their numbers, from the smallest sum to the
    // greatest. The numbers inside each row do not change. Rows with the same sum keep the order they have in the
    // original grid. An empty row has a sum of 0. The sums fit in an int. The original grid is not modified.
    // Example: sortRowsBySum([[5, 5], [1], [2, 3]])  returns [[1], [2, 3], [5, 5]]
    //          sortRowsBySum([[1, 2], [3], [0, 3]])  returns [[1, 2], [3], [0, 3]]
    //          sortRowsBySum([[], [-1]])             returns [[-1], []]
    //          sortRowsBySum([])                     returns []
    public static int[][] sortRowsBySum(int[][] grid) {
        Map<int[], Integer> sumMap = new IdentityHashMap<>();
        for (int[] row : grid) {
            sumMap.put(row, Arrays.stream(row).sum());
        }
        Comparator<int[]> bySum = Comparator.comparingInt(sumMap::get);
        return Arrays.stream(grid).sorted(bySum).toArray(int[][]::new);
    }

    // CHALLENGE 4
    // Returns a new grid with the same rows, ordered by the number they have in the column "col", from the smallest
    // number to the greatest. The numbers inside each row do not change. Rows with the same number in that column
    // keep the order they have in the original grid. Every row has more than "col" numbers, and col is zero or
    // greater. The original grid is not modified.
    // Example: sortRowsByColumn([[3, 1], [1, 9], [2, 5]], 0)  returns [[1, 9], [2, 5], [3, 1]]
    //          sortRowsByColumn([[3, 1], [1, 9], [2, 5]], 1)  returns [[3, 1], [2, 5], [1, 9]]
    //          sortRowsByColumn([[4, 2], [4, 1]], 0)          returns [[4, 2], [4, 1]]
    //          sortRowsByColumn([], 0)                        returns []
    public static int[][] sortRowsByColumn(int[][] grid, int col) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a new grid with the same rows, ordered like the words of a dictionary: two rows are compared number by
    // number, and the row with the smaller number in the first position where they are different goes first. If one
    // row is the beginning of the other one, the shorter row goes first. The numbers inside each row do not change.
    // The rows can have different lengths and can be empty. The original grid is not modified.
    // Example: sortRowsLexicographically([[1, 3], [1, 2, 5], [0, 9]])  returns [[0, 9], [1, 2, 5], [1, 3]]
    //          sortRowsLexicographically([[1, 2], [1]])                returns [[1], [1, 2]]
    //          sortRowsLexicographically([[], [0]])                    returns [[], [0]]
    //          sortRowsLexicographically([[2], [1], [2]])              returns [[1], [2], [2]]
    public static int[][] sortRowsLexicographically(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // The grid is rectangular: all the rows have the same length. Returns a new grid with the same size, with all the
    // numbers of the original grid sorted from the smallest to the greatest, when the grid is read row by row (the
    // first row from left to right, then the second row, and so on). The grid can have no rows. The original grid is
    // not modified.
    // Example: sortWholeGrid([[9, 3], [1, 7]])  returns [[1, 3], [7, 9]]
    //          sortWholeGrid([[5, 4, 3]])       returns [[3, 4, 5]]
    //          sortWholeGrid([[2], [1], [3]])   returns [[1], [2], [3]]
    //          sortWholeGrid([])                returns []
    public static int[][] sortWholeGrid(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns true if, when the grid is read row by row (the first row from left to right, then the second row, and
    // so on), no number is smaller than the one before it (equal numbers are allowed). The rows can have different
    // lengths and can be empty, and the grid can have no rows. The grid is not modified.
    // Example: isFullySorted([[1, 2], [2, 5]])     returns true
    //          isFullySorted([[1, 2], [1, 5]])     returns false
    //          isFullySorted([[1], [], [2, 3]])    returns true
    //          isFullySorted([[3, 4], [5], [4]])   returns false
    public static boolean isFullySorted(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
