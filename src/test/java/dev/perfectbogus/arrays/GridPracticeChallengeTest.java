package dev.perfectbogus.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class GridPracticeChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sumAll
    // ==========================================================
    @Nested
    class SumAllTests {

        @Test
        void testSquareGrid() {
            assertEquals(10, GridPracticeChallenge.sumAll(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testSingleNumber() {
            assertEquals(7, GridPracticeChallenge.sumAll(new int[][] {{7}}));
        }

        @Test
        void testNoRows() {
            assertEquals(0, GridPracticeChallenge.sumAll(new int[][] {}));
        }

        @Test
        void testOnlyEmptyRows() {
            assertEquals(0, GridPracticeChallenge.sumAll(new int[][] {{}, {}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertEquals(7, GridPracticeChallenge.sumAll(new int[][] {{5}, {}, {1, 1}}));
        }

        @Test
        void testEmptyRowsAtTheStartAndAtTheEnd() {
            assertEquals(6, GridPracticeChallenge.sumAll(new int[][] {{}, {1, 2, 3}, {}}));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-6, GridPracticeChallenge.sumAll(new int[][] {{-1, -2}, {-3}}));
        }

        @Test
        void testPositiveAndNegativeCancelOut() {
            assertEquals(0, GridPracticeChallenge.sumAll(new int[][] {{-3, 3}}));
        }

        @Test
        void testOneRow() {
            assertEquals(15, GridPracticeChallenge.sumAll(new int[][] {{1, 2, 3, 4, 5}}));
        }

        @Test
        void testOneColumn() {
            assertEquals(6, GridPracticeChallenge.sumAll(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testRectangularGrid() {
            assertEquals(21, GridPracticeChallenge.sumAll(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[50][50];
            for (int[] row : grid) {
                java.util.Arrays.fill(row, 2);
            }
            assertEquals(5000, GridPracticeChallenge.sumAll(grid));
        }

        @Test
        void testZeros() {
            assertEquals(0, GridPracticeChallenge.sumAll(new int[][] {{0, 0}, {0, 0}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3}};
            GridPracticeChallenge.sumAll(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 2: rowSums
    // ==========================================================
    @Nested
    class RowSumsTests {

        @Test
        void testSquareGrid() {
            assertArrayEquals(new int[] {3, 7}, GridPracticeChallenge.rowSums(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {}, GridPracticeChallenge.rowSums(new int[][] {}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[] {5, 0, 3}, GridPracticeChallenge.rowSums(new int[][] {{5}, {}, {1, 1, 1}}));
        }

        @Test
        void testOnlyEmptyRows() {
            assertArrayEquals(new int[] {0, 0}, GridPracticeChallenge.rowSums(new int[][] {{}, {}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[] {-3}, GridPracticeChallenge.rowSums(new int[][] {{-1, -2}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[] {10}, GridPracticeChallenge.rowSums(new int[][] {{1, 2, 3, 4}}));
        }

        @Test
        void testOneColumn() {
            assertArrayEquals(new int[] {1, 2, 3}, GridPracticeChallenge.rowSums(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testRowsThatCancelOut() {
            assertArrayEquals(new int[] {0, 4}, GridPracticeChallenge.rowSums(new int[][] {{5, -5}, {1, 3}}));
        }

        @Test
        void testRowsAreNotMixed() {
            assertArrayEquals(new int[] {100, 1}, GridPracticeChallenge.rowSums(new int[][] {{99, 1}, {0, 1}}));
        }

        @Test
        void testResultHasOneElementPerRow() {
            assertEquals(4, GridPracticeChallenge.rowSums(new int[][] {{1}, {2}, {3}, {4}}).length);
        }

        @Test
        void testEmptyRowAtTheEnd() {
            assertArrayEquals(new int[] {3, 0}, GridPracticeChallenge.rowSums(new int[][] {{1, 2}, {}}));
        }

        @Test
        void testRectangularGrid() {
            assertArrayEquals(new int[] {6, 15}, GridPracticeChallenge.rowSums(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3}};
            GridPracticeChallenge.rowSums(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 3: columnSums
    // ==========================================================
    @Nested
    class ColumnSumsTests {

        @Test
        void testSquareGrid() {
            assertArrayEquals(new int[] {4, 6}, GridPracticeChallenge.columnSums(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[] {1, 2, 3}, GridPracticeChallenge.columnSums(new int[][] {{1, 2, 3}}));
        }

        @Test
        void testOneColumn() {
            assertArrayEquals(new int[] {6}, GridPracticeChallenge.columnSums(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {}, GridPracticeChallenge.columnSums(new int[][] {}));
        }

        @Test
        void testMoreColumnsThanRows() {
            assertArrayEquals(new int[] {5, 7, 9},
                    GridPracticeChallenge.columnSums(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testMoreRowsThanColumns() {
            assertArrayEquals(new int[] {9, 12},
                    GridPracticeChallenge.columnSums(new int[][] {{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[] {-4, 0},
                    GridPracticeChallenge.columnSums(new int[][] {{-1, 2}, {-3, -2}}));
        }

        @Test
        void testColumnsAreNotMixed() {
            assertArrayEquals(new int[] {1, 100},
                    GridPracticeChallenge.columnSums(new int[][] {{1, 99}, {0, 1}}));
        }

        @Test
        void testResultHasOneElementPerColumn() {
            assertEquals(4, GridPracticeChallenge.columnSums(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}}).length);
        }

        @Test
        void testThreeByThree() {
            assertArrayEquals(new int[] {12, 15, 18},
                    GridPracticeChallenge.columnSums(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        }

        @Test
        void testColumnsThatCancelOut() {
            assertArrayEquals(new int[] {0, 0}, GridPracticeChallenge.columnSums(new int[][] {{5, -1}, {-5, 1}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3, 4}};
            GridPracticeChallenge.columnSums(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 4: transpose
    // ==========================================================
    @Nested
    class TransposeTests {

        @Test
        void testRectangularGrid() {
            assertArrayEquals(new int[][] {{1, 4}, {2, 5}, {3, 6}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testSquareGrid() {
            assertArrayEquals(new int[][] {{1, 3}, {2, 4}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[][] {{7}}, GridPracticeChallenge.transpose(new int[][] {{7}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridPracticeChallenge.transpose(new int[][] {}));
        }

        @Test
        void testOneRowBecomesOneColumn() {
            assertArrayEquals(new int[][] {{1}, {2}, {3}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2, 3}}));
        }

        @Test
        void testOneColumnBecomesOneRow() {
            assertArrayEquals(new int[][] {{1, 2, 3}},
                    GridPracticeChallenge.transpose(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testMoreRowsThanColumns() {
            assertArrayEquals(new int[][] {{1, 3, 5}, {2, 4, 6}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        void testSymmetricGridDoesNotChange() {
            assertArrayEquals(new int[][] {{1, 2}, {2, 1}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2}, {2, 1}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-1, -3}, {-2, 0}},
                    GridPracticeChallenge.transpose(new int[][] {{-1, -2}, {-3, 0}}));
        }

        @Test
        void testThreeByThree() {
            assertArrayEquals(new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
                    GridPracticeChallenge.transpose(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        }

        @Test
        void testTransposingTwiceGivesTheOriginal() {
            int[][] grid = {{1, 2, 3}, {4, 5, 6}};
            assertArrayEquals(grid, GridPracticeChallenge.transpose(GridPracticeChallenge.transpose(grid)));
        }

        @Test
        void testResultIsANewGrid() {
            int[][] grid = {{1, 2}, {3, 4}};
            int[][] result = GridPracticeChallenge.transpose(grid);
            result[0][0] = 99;
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, grid);
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2, 3}, {4, 5, 6}};
            GridPracticeChallenge.transpose(grid);
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 5: maxElement
    // ==========================================================
    @Nested
    class MaxElementTests {

        @Test
        void testGreatestInTheFirstRow() {
            assertEquals(9, GridPracticeChallenge.maxElement(new int[][] {{1, 9}, {4, 3}}));
        }

        @Test
        void testGreatestInTheLastRow() {
            assertEquals(9, GridPracticeChallenge.maxElement(new int[][] {{1, 2}, {4, 9}}));
        }

        @Test
        void testGreatestInTheMiddle() {
            assertEquals(8, GridPracticeChallenge.maxElement(new int[][] {{1, 2}, {3, 8}, {4, 5}}));
        }

        @Test
        void testAllNegative() {
            assertEquals(-2, GridPracticeChallenge.maxElement(new int[][] {{-5, -2}, {-9}}));
        }

        @Test
        void testEmptyRowsAreSkipped() {
            assertEquals(3, GridPracticeChallenge.maxElement(new int[][] {{}, {3}, {}}));
        }

        @Test
        void testSingleNumber() {
            assertEquals(7, GridPracticeChallenge.maxElement(new int[][] {{7}}));
        }

        @Test
        void testSingleNegativeNumber() {
            assertEquals(-7, GridPracticeChallenge.maxElement(new int[][] {{-7}}));
        }

        @Test
        void testAllZeros() {
            assertEquals(0, GridPracticeChallenge.maxElement(new int[][] {{0, 0}, {0}}));
        }

        @Test
        void testNegativesAndAZero() {
            assertEquals(0, GridPracticeChallenge.maxElement(new int[][] {{-1, 0}, {-3, -2}}));
        }

        @Test
        void testRepeatedGreatest() {
            assertEquals(5, GridPracticeChallenge.maxElement(new int[][] {{5, 1}, {5, 5}}));
        }

        @Test
        void testDifferentLengths() {
            assertEquals(10, GridPracticeChallenge.maxElement(new int[][] {{1}, {2, 3, 10}, {4, 5}}));
        }

        @Test
        void testExtremeValues() {
            assertEquals(Integer.MAX_VALUE,
                    GridPracticeChallenge.maxElement(new int[][] {{Integer.MIN_VALUE}, {Integer.MAX_VALUE}}));
        }

        @Test
        void testOnlyTheMinimumValue() {
            assertEquals(Integer.MIN_VALUE, GridPracticeChallenge.maxElement(new int[][] {{}, {Integer.MIN_VALUE}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 9}, {4}};
            GridPracticeChallenge.maxElement(grid);
            assertArrayEquals(new int[][] {{1, 9}, {4}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 6: mainDiagonal
    // ==========================================================
    @Nested
    class MainDiagonalTests {

        @Test
        void testTwoByTwo() {
            assertArrayEquals(new int[] {1, 4}, GridPracticeChallenge.mainDiagonal(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testThreeByThree() {
            assertArrayEquals(new int[] {1, 5, 9},
                    GridPracticeChallenge.mainDiagonal(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[] {7}, GridPracticeChallenge.mainDiagonal(new int[][] {{7}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {}, GridPracticeChallenge.mainDiagonal(new int[][] {}));
        }

        @Test
        void testFourByFour() {
            int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
            assertArrayEquals(new int[] {1, 6, 11, 16}, GridPracticeChallenge.mainDiagonal(grid));
        }

        @Test
        void testIsNotTheOtherDiagonal() {
            assertArrayEquals(new int[] {1, 1, 1},
                    GridPracticeChallenge.mainDiagonal(new int[][] {{1, 0, 9}, {0, 1, 0}, {9, 0, 1}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[] {-1, -4},
                    GridPracticeChallenge.mainDiagonal(new int[][] {{-1, -2}, {-3, -4}}));
        }

        @Test
        void testDiagonalWithZeros() {
            assertArrayEquals(new int[] {0, 0, 0},
                    GridPracticeChallenge.mainDiagonal(new int[][] {{0, 1, 2}, {3, 0, 4}, {5, 6, 0}}));
        }

        @Test
        void testIdentityMatrix() {
            assertArrayEquals(new int[] {1, 1, 1, 1},
                    GridPracticeChallenge.mainDiagonal(
                            new int[][] {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
        }

        @Test
        void testResultHasOneElementPerRow() {
            assertEquals(3, GridPracticeChallenge.mainDiagonal(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}).length);
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3, 4}};
            GridPracticeChallenge.mainDiagonal(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 7: isSymmetric
    // ==========================================================
    @Nested
    class IsSymmetricTests {

        @Test
        void testSymmetricTwoByTwo() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2}, {2, 1}}));
        }

        @Test
        void testNotSymmetricTwoByTwo() {
            assertFalse(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2}, {3, 1}}));
        }

        @Test
        void testSymmetricThreeByThree() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2, 3}, {2, 5, 6}, {3, 6, 9}}));
        }

        @Test
        void testNoRows() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {}));
        }

        @Test
        void testSingleNumber() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{5}}));
        }

        @Test
        void testOneDifferenceFarFromTheDiagonal() {
            assertFalse(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2, 3}, {2, 5, 6}, {4, 6, 9}}));
        }

        @Test
        void testOneDifferenceInTheLowerPart() {
            assertFalse(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2, 3}, {2, 5, 6}, {3, 7, 9}}));
        }

        @Test
        void testOneDifferenceInTheUpperPart() {
            assertFalse(GridPracticeChallenge.isSymmetric(new int[][] {{1, 2, 3}, {2, 5, 7}, {3, 6, 9}}));
        }

        @Test
        void testDifferenceInTheLastRows() {
            int[][] grid = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 2}, {0, 0, 3, 1}};
            assertFalse(GridPracticeChallenge.isSymmetric(grid));
        }

        @Test
        void testDifferenceInTheFirstRows() {
            int[][] grid = {{1, 2, 0, 0}, {3, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
            assertFalse(GridPracticeChallenge.isSymmetric(grid));
        }

        @Test
        void testOnlyTheOppositeSignDiffers() {
            assertFalse(GridPracticeChallenge.isSymmetric(new int[][] {{0, 4}, {-4, 0}}));
        }

        @Test
        void testDifferentDiagonalNumbersStillSymmetric() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{1, 0}, {0, 9}}));
        }

        @Test
        void testAllEqual() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}));
        }

        @Test
        void testSymmetricWithNegativeNumbers() {
            assertTrue(GridPracticeChallenge.isSymmetric(new int[][] {{-1, -2}, {-2, -3}}));
        }

        @Test
        void testSymmetricFourByFour() {
            int[][] grid = {{1, 2, 3, 4}, {2, 5, 6, 7}, {3, 6, 8, 9}, {4, 7, 9, 0}};
            assertTrue(GridPracticeChallenge.isSymmetric(grid));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3, 1}};
            GridPracticeChallenge.isSymmetric(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3, 1}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 8: flatten
    // ==========================================================
    @Nested
    class FlattenTests {

        @Test
        void testSquareGrid() {
            assertArrayEquals(new int[] {1, 2, 3, 4}, GridPracticeChallenge.flatten(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[] {1, 2, 3}, GridPracticeChallenge.flatten(new int[][] {{1}, {}, {2, 3}}));
        }

        @Test
        void testOnlyEmptyRows() {
            assertArrayEquals(new int[] {}, GridPracticeChallenge.flatten(new int[][] {{}, {}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {}, GridPracticeChallenge.flatten(new int[][] {}));
        }

        @Test
        void testOrderIsRowByRow() {
            assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6},
                    GridPracticeChallenge.flatten(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testOneColumn() {
            assertArrayEquals(new int[] {1, 2, 3}, GridPracticeChallenge.flatten(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[] {7}, GridPracticeChallenge.flatten(new int[][] {{7}}));
        }

        @Test
        void testNumbersAreNotSortedNorRemoved() {
            assertArrayEquals(new int[] {3, 1, 3, 2, 1},
                    GridPracticeChallenge.flatten(new int[][] {{3, 1}, {3}, {2, 1}}));
        }

        @Test
        void testRepeatedNumbersStayInPlace() {
            assertArrayEquals(new int[] {2, 2, 2, 0, 0},
                    GridPracticeChallenge.flatten(new int[][] {{2, 2}, {2, 0}, {0}}));
        }

        @Test
        void testEmptyRowsAtTheStartAndAtTheEnd() {
            assertArrayEquals(new int[] {5, 6}, GridPracticeChallenge.flatten(new int[][] {{}, {5, 6}, {}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[] {-1, 0, -2}, GridPracticeChallenge.flatten(new int[][] {{-1, 0}, {-2}}));
        }

        @Test
        void testLengthIsTheTotalOfNumbers() {
            assertEquals(7, GridPracticeChallenge.flatten(new int[][] {{1, 2, 3}, {4}, {5, 6, 7}}).length);
        }

        @Test
        void testResultIsANewArray() {
            int[][] grid = {{1, 2}, {3}};
            int[] result = GridPracticeChallenge.flatten(grid);
            result[0] = 99;
            assertArrayEquals(new int[][] {{1, 2}, {3}}, grid);
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3}};
            GridPracticeChallenge.flatten(grid);
            assertArrayEquals(new int[][] {{1, 2}, {3}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 9: rotateClockwise
    // ==========================================================
    @Nested
    class RotateClockwiseTests {

        @Test
        void testRectangularGrid() {
            assertArrayEquals(new int[][] {{4, 1}, {5, 2}, {6, 3}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testSquareGrid() {
            assertArrayEquals(new int[][] {{3, 1}, {4, 2}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testSingleNumber() {
            assertArrayEquals(new int[][] {{7}}, GridPracticeChallenge.rotateClockwise(new int[][] {{7}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridPracticeChallenge.rotateClockwise(new int[][] {}));
        }

        @Test
        void testThreeByThree() {
            assertArrayEquals(new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        }

        @Test
        void testOneRowBecomesOneColumn() {
            assertArrayEquals(new int[][] {{1}, {2}, {3}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2, 3}}));
        }

        @Test
        void testOneColumnBecomesOneRow() {
            assertArrayEquals(new int[][] {{3, 2, 1}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testMoreRowsThanColumns() {
            assertArrayEquals(new int[][] {{5, 3, 1}, {6, 4, 2}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        void testIsNotCounterclockwise() {
            assertFalse(java.util.Arrays.deepEquals(new int[][] {{2, 4}, {1, 3}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2}, {3, 4}})));
        }

        @Test
        void testIsNotTheTranspose() {
            assertFalse(java.util.Arrays.deepEquals(new int[][] {{1, 3}, {2, 4}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{1, 2}, {3, 4}})));
        }

        @Test
        void testFourRotationsGiveTheOriginal() {
            int[][] grid = {{1, 2, 3}, {4, 5, 6}};
            int[][] result = grid;
            for (int i = 0; i < 4; i++) {
                result = GridPracticeChallenge.rotateClockwise(result);
            }
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}}, result);
        }

        @Test
        void testTwoRotationsReverseEverything() {
            int[][] grid = {{1, 2, 3}, {4, 5, 6}};
            int[][] result = GridPracticeChallenge.rotateClockwise(GridPracticeChallenge.rotateClockwise(grid));
            assertArrayEquals(new int[][] {{6, 5, 4}, {3, 2, 1}}, result);
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-3, -1}, {-4, -2}},
                    GridPracticeChallenge.rotateClockwise(new int[][] {{-1, -2}, {-3, -4}}));
        }

        @Test
        void testResultIsANewGrid() {
            int[][] grid = {{1, 2}, {3, 4}};
            int[][] result = GridPracticeChallenge.rotateClockwise(grid);
            result[0][0] = 99;
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, grid);
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2, 3}, {4, 5, 6}};
            GridPracticeChallenge.rotateClockwise(grid);
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}}, grid);
        }
    }

    // ==========================================================
    // CHALLENGE 10: findValue
    // ==========================================================
    @Nested
    class FindValueTests {

        @Test
        void testFirstOfTwoInTheSameRow() {
            assertArrayEquals(new int[] {0, 1}, GridPracticeChallenge.findValue(new int[][] {{1, 2}, {3, 2}}, 2));
        }

        @Test
        void testSecondRow() {
            assertArrayEquals(new int[] {1, 0}, GridPracticeChallenge.findValue(new int[][] {{1, 2}, {3, 4}}, 3));
        }

        @Test
        void testAfterAnEmptyRow() {
            assertArrayEquals(new int[] {1, 0}, GridPracticeChallenge.findValue(new int[][] {{}, {5}}, 5));
        }

        @Test
        void testNotFound() {
            assertArrayEquals(new int[] {-1, -1}, GridPracticeChallenge.findValue(new int[][] {{1, 2}}, 9));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {-1, -1}, GridPracticeChallenge.findValue(new int[][] {}, 1));
        }

        @Test
        void testOnlyEmptyRows() {
            assertArrayEquals(new int[] {-1, -1}, GridPracticeChallenge.findValue(new int[][] {{}, {}}, 0));
        }

        @Test
        void testTopLeftCorner() {
            assertArrayEquals(new int[] {0, 0}, GridPracticeChallenge.findValue(new int[][] {{7, 1}, {2, 3}}, 7));
        }

        @Test
        void testBottomRightCorner() {
            assertArrayEquals(new int[] {1, 1}, GridPracticeChallenge.findValue(new int[][] {{1, 2}, {3, 4}}, 4));
        }

        @Test
        void testRowComesBeforeColumn() {
            assertArrayEquals(new int[] {0, 2},
                    GridPracticeChallenge.findValue(new int[][] {{1, 2, 5}, {5, 0, 0}}, 5));
        }

        @Test
        void testColumnIsNotMixedWithRow() {
            assertArrayEquals(new int[] {1, 2},
                    GridPracticeChallenge.findValue(new int[][] {{0, 0, 0}, {0, 0, 8}}, 8));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[] {2, 1},
                    GridPracticeChallenge.findValue(new int[][] {{1}, {2, 3, 4}, {5, 6}}, 6));
        }

        @Test
        void testNegativeTarget() {
            assertArrayEquals(new int[] {1, 0}, GridPracticeChallenge.findValue(new int[][] {{1, 2}, {-3, 4}}, -3));
        }

        @Test
        void testTargetZero() {
            assertArrayEquals(new int[] {0, 1}, GridPracticeChallenge.findValue(new int[][] {{1, 0}, {0, 0}}, 0));
        }

        @Test
        void testSingleNumberFound() {
            assertArrayEquals(new int[] {0, 0}, GridPracticeChallenge.findValue(new int[][] {{4}}, 4));
        }

        @Test
        void testSingleNumberNotFound() {
            assertArrayEquals(new int[] {-1, -1}, GridPracticeChallenge.findValue(new int[][] {{4}}, 5));
        }

        @Test
        void testNegativeOfTheValueIsNotFound() {
            assertArrayEquals(new int[] {-1, -1}, GridPracticeChallenge.findValue(new int[][] {{3, 4}}, -3));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{1, 2}, {3}};
            GridPracticeChallenge.findValue(grid, 3);
            assertArrayEquals(new int[][] {{1, 2}, {3}}, grid);
        }
    }
}