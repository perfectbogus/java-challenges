package dev.perfectbogus.arrays;

public class GridPracticeChallenge {

    // CHALLENGE 1
    // Returns the sum of all the numbers of the grid. The rows can have different lengths and can be empty. A grid
    // without rows gives 0. The grid is not modified.
    // Example: sumAll([[1, 2], [3, 4]])  returns 10
    //          sumAll([[5], [], [1, 1]])  returns 7
    //          sumAll([[-3, 3]])          returns 0
    //          sumAll([])                 returns 0
    public static int sumAll(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns an array with the sum of each row, in the same order as the rows. The rows can have different lengths
    // and a row without numbers gives 0. A grid without rows gives an empty array. The grid is not modified.
    // Example: rowSums([[1, 2], [3, 4]])      returns [3, 7]
    //          rowSums([[5], [], [1, 1, 1]])  returns [5, 0, 3]
    //          rowSums([[-1, -2]])            returns [-3]
    //          rowSums([])                    returns []
    public static int[] rowSums(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // All the rows of the grid have the same length. Returns an array with the sum of each column, from left to right.
    // A grid without rows gives an empty array. The grid is not modified.
    // Example: columnSums([[1, 2], [3, 4]])        returns [4, 6]
    //          columnSums([[1, 2, 3]])             returns [1, 2, 3]
    //          columnSums([[1], [2], [3]])         returns [6]
    //          columnSums([])                      returns []
    public static int[] columnSums(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the greatest number of the grid. The grid has at least one number, but the rows can have different
    // lengths and some of them can be empty. The numbers can be negative. The grid is not modified.
    // Example: maxElement([[1, 9], [4, 3]])  returns 9
    //          maxElement([[-5, -2], [-9]])  returns -2
    //          maxElement([[], [3], []])     returns 3
    //          maxElement([[7]])             returns 7
    public static int maxElement(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // The grid is square: it has n rows and each row has n numbers. Returns the numbers of the main diagonal, which
    // goes from the top left corner to the bottom right corner. A grid without rows gives an empty array.
    // Example: mainDiagonal([[1, 2], [3, 4]])                  returns [1, 4]
    //          mainDiagonal([[1, 2, 3], [4, 5, 6], [7, 8, 9]]) returns [1, 5, 9]
    //          mainDiagonal([[7]])                             returns [7]
    //          mainDiagonal([])                                returns []
    public static int[] mainDiagonal(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // The grid is square. Returns true if it is symmetric: the number in row i and column j is equal to the number in
    // row j and column i, for every i and j. A grid without rows is symmetric.
    // Example: isSymmetric([[1, 2], [2, 1]])                  returns true
    //          isSymmetric([[1, 2], [3, 1]])                  returns false
    //          isSymmetric([[1, 2, 3], [2, 5, 6], [3, 6, 9]]) returns true
    //          isSymmetric([])                                returns true
    public static boolean isSymmetric(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns all the numbers of the grid in one array, row by row, each row from left to right. The rows can have
    // different lengths and can be empty. The grid is not modified.
    // Example: flatten([[1, 2], [3, 4]])    returns [1, 2, 3, 4]
    //          flatten([[1], [], [2, 3]])   returns [1, 2, 3]
    //          flatten([[], []])            returns []
    //          flatten([])                  returns []
    public static int[] flatten(int[][] grid) {
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
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