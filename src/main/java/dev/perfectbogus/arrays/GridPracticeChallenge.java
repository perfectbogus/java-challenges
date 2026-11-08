package dev.perfectbogus.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class GridPracticeChallenge {

    // CHALLENGE 1
    // Returns the sum of all the numbers of the grid. The rows can have different lengths and can be empty. A grid
    // without rows gives 0. The grid is not modified.
    // Example: sumAll([[1, 2], [3, 4]])  returns 10
    //          sumAll([[5], [], [1, 1]])  returns 7
    //          sumAll([[-3, 3]])          returns 0
    //          sumAll([])                 returns 0
    public static int sumAll(int[][] grid) {
        return Arrays.stream(grid).mapToInt(i -> Arrays.stream(i).sum()).sum();
    }

    // CHALLENGE 2
    // Returns an array with the sum of each row, in the same order as the rows. The rows can have different lengths
    // and a row without numbers gives 0. A grid without rows gives an empty array. The grid is not modified.
    // Example: rowSums([[1, 2], [3, 4]])      returns [3, 7]
    //          rowSums([[5], [], [1, 1, 1]])  returns [5, 0, 3]
    //          rowSums([[-1, -2]])            returns [-3]
    //          rowSums([])                    returns []
    public static int[] rowSums(int[][] grid) {
        return Arrays.stream(grid).mapToInt(a -> Arrays.stream(a).sum()).toArray();
    }

    // CHALLENGE 3
    // All the rows of the grid have the same length. Returns an array with the sum of each column, from left to right.
    // A grid without rows gives an empty array. The grid is not modified.
    // Example: columnSums([[1, 2], [3, 4]])        returns [4, 6]
    //          columnSums([[1, 2, 3]])             returns [1, 2, 3]
    //          columnSums([[1], [2], [3]])         returns [6]
    //          columnSums([])                      returns []
    public static int[] columnSums(int[][] grid) {
        if (grid.length == 0) return new int[0];
        int n = grid[0].length;
        int[] res = new int[grid[0].length];
        for (int col = 0; col < n; col++) {
            int sum = 0;
            for (int[] ints : grid) {
                sum += ints[col];
            }
            res[col] = sum;
        }
        return res;
    }

    // CHALLENGE 4
    // All the rows of the grid have the same length. Returns a new grid where the rows of the original are the
    // columns, and the columns are the rows. The grid received is not modified. A grid without rows gives a grid
    // without rows.
    // Example: transpose([[1, 2, 3], [4, 5, 6]])  returns [[1, 4], [2, 5], [3, 6]]
    //          transpose([[1, 2], [3, 4]])        returns [[1, 3], [2, 4]]
    //          transpose([[7]])                   returns [[7]]
    //          transpose([])                      returns []
    public static int[][] transpose(int[][] grid) {
        int m = grid.length;
        if (m == 0) return new int[0][0];
        int n = grid[0].length;

        int[][] res = new int[n][m];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                res[col][row] = grid[row][col];
            }
        }

        return res;
    }

    // CHALLENGE 5
    // Returns the greatest number of the grid. The grid has at least one number, but the rows can have different
    // lengths and some of them can be empty. The numbers can be negative. The grid is not modified.
    // Example: maxElement([[1, 9], [4, 3]])  returns 9
    //          maxElement([[-5, -2], [-9]])  returns -2
    //          maxElement([[], [3], []])     returns 3
    //          maxElement([[7]])             returns 7
    public static int maxElement(int[][] grid) {
        int max = Integer.MIN_VALUE;
        for (int[] row: grid) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    // CHALLENGE 6
    // The grid is square: it has n rows and each row has n numbers. Returns the numbers of the main diagonal, which
    // goes from the top left corner to the bottom right corner. A grid without rows gives an empty array.
    // Example: mainDiagonal([[1, 2], [3, 4]])                  returns [1, 4]
    //          mainDiagonal([[1, 2, 3], [4, 5, 6], [7, 8, 9]]) returns [1, 5, 9]
    //          mainDiagonal([[7]])                             returns [7]
    //          mainDiagonal([])                                returns []
    public static int[] mainDiagonal(int[][] grid) {
        if (grid.length == 0) return new int[0];
        int[] res = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            res[i] = grid[i][i];
        }
        return res;
    }

    // CHALLENGE 7
    // The grid is square. Returns true if it is symmetric: the number in row i and column j is equal to the number in
    // row j and column i, for every i and j. A grid without rows is symmetric.
    // Example: isSymmetric([[1, 2], [2, 1]])                  returns true
    //          isSymmetric([[1, 2], [3, 1]])                  returns false
    //          isSymmetric([[1, 2, 3], [2, 5, 6], [3, 6, 9]]) returns true
    //          isSymmetric([])                                returns true
    public static boolean isSymmetric(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == j) continue;
                if (grid[i][j] != grid[j][i]) return false;
            }
        }
        return true;
    }

    // CHALLENGE 8
    // Returns all the numbers of the grid in one array, row by row, each row from left to right. The rows can have
    // different lengths and can be empty. The grid is not modified.
    // Example: flatten([[1, 2], [3, 4]])    returns [1, 2, 3, 4]
    //          flatten([[1], [], [2, 3]])   returns [1, 2, 3]
    //          flatten([[], []])            returns []
    //          flatten([])                  returns []
    public static int[] flatten(int[][] grid) {
        return Arrays.stream(grid).flatMapToInt(a -> Arrays.stream(a)).toArray();
    }

    public static int[] flattenSimplified(int[][] grid) {
        return Arrays.stream(grid).flatMapToInt(Arrays::stream).toArray();
    }

    // CHALLENGE 9
    // All the rows of the grid have the same length. Returns a new grid with the original rotated 90 degrees
    // clockwise: the first row of the original becomes the last column of the result. The grid received is not
    // modified. A grid without rows gives a grid without rows.
    // Example: rotateClockwise([[1, 2, 3], [4, 5, 6]])  returns [[4, 1], [5, 2], [6, 3]]
    //          rotateClockwise([[1, 2], [3, 4]])        returns [[3, 1], [4, 2]]
    //          rotateClockwise([[7]])                   returns [[7]]
    //          rotateClockwise([])                      returns []
    public static int[][] rotateClockwise(int[][] grid) {
        if (grid.length == 0) return new int[0][];

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = grid[i][j];
            }
        }

    }

    // CHALLENGE 10
    // Looks for the target in the grid, going row by row and each row from left to right. Returns the position of the
    // first one found as an array [row, column], both starting at 0. If the target is not in the grid, returns
    // [-1, -1]. The rows can have different lengths and can be empty.
    // Example: findValue([[1, 2], [3, 2]], 2)  returns [0, 1]
    //          findValue([[1, 2], [3, 4]], 3)  returns [1, 0]
    //          findValue([[], [5]], 5)         returns [1, 0]
    //          findValue([[1, 2]], 9)          returns [-1, -1]
    public static int[] findValue(int[][] grid, int target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}