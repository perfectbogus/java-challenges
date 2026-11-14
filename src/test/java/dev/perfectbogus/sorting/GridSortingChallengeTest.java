package dev.perfectbogus.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GridSortingChallengeTest {

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int r = 0; r < grid.length; r++) {
            result[r] = grid[r].clone();
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: sortEachRow
    // ==========================================================
    @Nested
    class SortEachRowTests {

        @Test
        void testTwoRows() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {8, 9}},
                    GridSortingChallenge.sortEachRow(new int[][] {{3, 1, 2}, {9, 8}}));
        }

        @Test
        void testOneNumber() {
            assertArrayEquals(new int[][] {{5}}, GridSortingChallenge.sortEachRow(new int[][] {{5}}));
        }

        @Test
        void testEmptyRow() {
            assertArrayEquals(new int[][] {{}, {1, 2}}, GridSortingChallenge.sortEachRow(new int[][] {{}, {2, 1}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortEachRow(new int[][] {}));
        }

        @Test
        void testRowsAreNotMixed() {
            assertArrayEquals(new int[][] {{7, 8, 9}, {1, 2, 3}},
                    GridSortingChallenge.sortEachRow(new int[][] {{9, 7, 8}, {3, 1, 2}}));
        }

        @Test
        void testRowsKeepTheirPosition() {
            assertArrayEquals(new int[][] {{5, 6}, {1, 2}, {3, 4}},
                    GridSortingChallenge.sortEachRow(new int[][] {{6, 5}, {2, 1}, {4, 3}}));
        }

        @Test
        void testAlreadySortedRows() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}},
                    GridSortingChallenge.sortEachRow(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        }

        @Test
        void testDescendingRows() {
            assertArrayEquals(new int[][] {{1, 2, 3, 4}},
                    GridSortingChallenge.sortEachRow(new int[][] {{4, 3, 2, 1}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-5, -1, 0, 3}, {-9, -8}},
                    GridSortingChallenge.sortEachRow(new int[][] {{3, -1, 0, -5}, {-8, -9}}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[][] {{1, 1, 2, 2}, {0, 0}},
                    GridSortingChallenge.sortEachRow(new int[][] {{2, 1, 2, 1}, {0, 0}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[][] {{2}, {1, 3}, {}, {1, 2, 3, 4}},
                    GridSortingChallenge.sortEachRow(new int[][] {{2}, {3, 1}, {}, {4, 2, 3, 1}}));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}},
                    GridSortingChallenge.sortEachRow(new int[][] {{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{3, 1, 2}, {9, 8}};
            GridSortingChallenge.sortEachRow(grid);
            assertArrayEquals(new int[][] {{3, 1, 2}, {9, 8}}, grid);
        }

        @Test
        void testResultDoesNotShareRowsWithTheOriginal() {
            int[][] grid = {{1, 2}, {3, 4}};
            int[][] result = GridSortingChallenge.sortEachRow(grid);
            result[0][0] = 99;
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}}, grid);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[1000][1000];
            for (int r = 0; r < 1000; r++) {
                for (int c = 0; c < 1000; c++) {
                    grid[r][c] = 999 - c + r;
                }
            }
            int[][] result = GridSortingChallenge.sortEachRow(grid);
            assertEquals(0, result[0][0]);
            assertEquals(999, result[0][999]);
            assertEquals(1998, result[999][999]);
            assertEquals(999, result[999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortEachColumn
    // ==========================================================
    @Nested
    class SortEachColumnTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{1, 1}, {2, 4}, {3, 5}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{3, 1}, {2, 5}, {1, 4}}));
        }

        @Test
        void testOneRowStaysTheSame() {
            assertArrayEquals(new int[][] {{9, 8, 7}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{9, 8, 7}}));
        }

        @Test
        void testOneColumn() {
            assertArrayEquals(new int[][] {{1}, {2}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{2}, {1}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortEachColumn(new int[][] {}));
        }

        @Test
        void testRowsWithoutNumbers() {
            assertArrayEquals(new int[][] {{}, {}, {}}, GridSortingChallenge.sortEachColumn(new int[3][0]));
        }

        @Test
        void testOneNumber() {
            assertArrayEquals(new int[][] {{4}}, GridSortingChallenge.sortEachColumn(new int[][] {{4}}));
        }

        @Test
        void testColumnsAreNotMixed() {
            assertArrayEquals(new int[][] {{1, 10}, {2, 20}, {3, 30}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{3, 30}, {1, 10}, {2, 20}}));
        }

        @Test
        void testRowsAreNotKeptTogether() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{3, 4}, {1, 2}}));
            assertArrayEquals(new int[][] {{1, 3}, {2, 4}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{1, 4}, {2, 3}}));
        }

        @Test
        void testNotSquareWide() {
            assertArrayEquals(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{5, 2, 7, 4}, {1, 6, 3, 8}}));
        }

        @Test
        void testNotSquareTall() {
            assertArrayEquals(new int[][] {{1, 1}, {2, 2}, {3, 3}, {4, 4}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{4, 2}, {3, 4}, {2, 1}, {1, 3}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-3, -7}, {0, 0}, {5, 2}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{0, 2}, {5, -7}, {-3, 0}}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[][] {{1, 2}, {1, 2}, {3, 2}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{3, 2}, {1, 2}, {1, 2}}));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}, {5, 6}},
                    GridSortingChallenge.sortEachColumn(new int[][] {{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{3, 1}, {2, 5}, {1, 4}};
            GridSortingChallenge.sortEachColumn(grid);
            assertArrayEquals(new int[][] {{3, 1}, {2, 5}, {1, 4}}, grid);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[1000][1000];
            for (int r = 0; r < 1000; r++) {
                for (int c = 0; c < 1000; c++) {
                    grid[r][c] = (999 - r) * 1000 + c;
                }
            }
            int[][] result = GridSortingChallenge.sortEachColumn(grid);
            assertEquals(0, result[0][0]);
            assertEquals(999, result[0][999]);
            assertEquals(999_000, result[999][0]);
            assertEquals(999_999, result[999][999]);
            assertEquals(500_500, result[500][500]);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortRowsBySum
    // ==========================================================
    @Nested
    class SortRowsBySumTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{1}, {2, 3}, {5, 5}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{5, 5}, {1}, {2, 3}}));
        }

        @Test
        void testSameSumsKeepTheOrder() {
            assertArrayEquals(new int[][] {{1, 2}, {3}, {0, 3}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{1, 2}, {3}, {0, 3}}));
        }

        @Test
        void testEmptyRowAndNegativeSum() {
            assertArrayEquals(new int[][] {{-1}, {}}, GridSortingChallenge.sortRowsBySum(new int[][] {{}, {-1}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortRowsBySum(new int[][] {}));
        }

        @Test
        void testNumbersInsideTheRowsDoNotChange() {
            assertArrayEquals(new int[][] {{3, 1}, {9, 8, 7}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{9, 8, 7}, {3, 1}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{4, 2}}, GridSortingChallenge.sortRowsBySum(new int[][] {{4, 2}}));
        }

        @Test
        void testSumIsNotTheLength() {
            assertArrayEquals(new int[][] {{1, 1, 1}, {10}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{10}, {1, 1, 1}}));
        }

        @Test
        void testSumIsNotTheFirstNumber() {
            assertArrayEquals(new int[][] {{9, -9}, {1, 1}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{1, 1}, {9, -9}}));
        }

        @Test
        void testSumIsNotTheLastNumber() {
            assertArrayEquals(new int[][] {{5, 0}, {1, 9}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{1, 9}, {5, 0}}));
        }

        @Test
        void testNegativeSums() {
            assertArrayEquals(new int[][] {{-5, -5}, {-3}, {0}, {2}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{2}, {-3}, {-5, -5}, {0}}));
        }

        @Test
        void testTiesAtTheEnd() {
            assertArrayEquals(new int[][] {{1}, {5, 0}, {0, 5}, {3, 2}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{5, 0}, {0, 5}, {3, 2}, {1}}));
        }

        @Test
        void testTiesInTheMiddleKeepTheOrder() {
            assertArrayEquals(new int[][] {{0}, {4, 0}, {2, 2}, {1, 3}, {9}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{9}, {4, 0}, {2, 2}, {0}, {1, 3}}));
        }

        @Test
        void testEmptyRowsKeepTheOrderWithZeroSums() {
            assertArrayEquals(new int[][] {{}, {0}, {1, -1}, {}},
                    GridSortingChallenge.sortRowsBySum(new int[][] {{}, {0}, {1, -1}, {}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{5, 5}, {1}, {2, 3}};
            GridSortingChallenge.sortRowsBySum(grid);
            assertArrayEquals(new int[][] {{5, 5}, {1}, {2, 3}}, grid);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[100_000][];
            for (int i = 0; i < grid.length; i++) {
                grid[i] = new int[] {grid.length - i, 0};
            }
            int[][] result = GridSortingChallenge.sortRowsBySum(grid);
            assertEquals(1, result[0][0]);
            assertEquals(50_000, result[49_999][0]);
            assertEquals(100_000, result[99_999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortRowsByColumn
    // ==========================================================
    @Nested
    class SortRowsByColumnTests {

        @Test
        void testFirstColumn() {
            assertArrayEquals(new int[][] {{1, 9}, {2, 5}, {3, 1}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 0));
        }

        @Test
        void testSecondColumn() {
            assertArrayEquals(new int[][] {{3, 1}, {2, 5}, {1, 9}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 1));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{4, 2}, {4, 1}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{4, 2}, {4, 1}}, 0));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortRowsByColumn(new int[][] {}, 0));
        }

        @Test
        void testLastColumnOfAWideGrid() {
            assertArrayEquals(new int[][] {{9, 9, 1}, {5, 5, 2}, {1, 1, 3}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{1, 1, 3}, {9, 9, 1}, {5, 5, 2}}, 2));
        }

        @Test
        void testMiddleColumn() {
            assertArrayEquals(new int[][] {{0, 1, 9}, {9, 2, 0}, {5, 3, 5}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{5, 3, 5}, {0, 1, 9}, {9, 2, 0}}, 1));
        }

        @Test
        void testOtherColumnsDoNotAffectTheOrder() {
            assertArrayEquals(new int[][] {{1, 100}, {2, 0}, {3, 50}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{3, 50}, {2, 0}, {1, 100}}, 0));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-5, 0}, {-1, 7}, {2, -3}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{2, -3}, {-5, 0}, {-1, 7}}, 0));
        }

        @Test
        void testRowsDifferentLengthsWithColumnOne() {
            assertArrayEquals(new int[][] {{9, 1}, {0, 3, 4}, {5, 8, 0, 0}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{5, 8, 0, 0}, {9, 1}, {0, 3, 4}}, 1));
        }

        @Test
        void testTiesWithThreeRowsKeepTheOrder() {
            assertArrayEquals(new int[][] {{1, 5}, {1, 3}, {1, 4}, {2, 0}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{2, 0}, {1, 5}, {1, 3}, {1, 4}}, 0));
        }

        @Test
        void testTiesInTheSecondColumnKeepTheOrder() {
            assertArrayEquals(new int[][] {{7, 1}, {5, 2}, {3, 2}, {9, 2}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{5, 2}, {7, 1}, {3, 2}, {9, 2}}, 1));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 1}, {0, 2}, {Integer.MAX_VALUE, 3}},
                    GridSortingChallenge.sortRowsByColumn(new int[][] {{Integer.MAX_VALUE, 3}, {Integer.MIN_VALUE, 1},
                            {0, 2}}, 0));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{8, 8}}, GridSortingChallenge.sortRowsByColumn(new int[][] {{8, 8}}, 1));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{3, 1}, {1, 9}, {2, 5}};
            GridSortingChallenge.sortRowsByColumn(grid, 0);
            assertArrayEquals(new int[][] {{3, 1}, {1, 9}, {2, 5}}, grid);
        }

        @Test
        void testRowsAreNotModified() {
            int[][] grid = {{3, 1}, {1, 9}};
            int[][] expected = copy(grid);
            int[][] result = GridSortingChallenge.sortRowsByColumn(grid, 1);
            assertArrayEquals(expected, grid);
            assertArrayEquals(new int[][] {{3, 1}, {1, 9}}, result);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[100_000][];
            for (int i = 0; i < grid.length; i++) {
                grid[i] = new int[] {i, grid.length - i};
            }
            int[][] result = GridSortingChallenge.sortRowsByColumn(grid, 1);
            assertEquals(99_999, result[0][0]);
            assertEquals(0, result[99_999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortRowsLexicographically
    // ==========================================================
    @Nested
    class SortRowsLexicographicallyTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{0, 9}, {1, 2, 5}, {1, 3}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{1, 3}, {1, 2, 5}, {0, 9}}));
        }

        @Test
        void testShorterRowGoesFirst() {
            assertArrayEquals(new int[][] {{1}, {1, 2}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{1, 2}, {1}}));
        }

        @Test
        void testEmptyRowGoesFirst() {
            assertArrayEquals(new int[][] {{}, {0}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{}, {0}}));
            assertArrayEquals(new int[][] {{}, {0}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{0}, {}}));
        }

        @Test
        void testRepeatedRows() {
            assertArrayEquals(new int[][] {{1}, {2}, {2}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{2}, {1}, {2}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortRowsLexicographically(new int[][] {}));
        }

        @Test
        void testLengthIsNotTheFirstCriterion() {
            assertArrayEquals(new int[][] {{1, 1, 1, 1}, {2}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{2}, {1, 1, 1, 1}}));
        }

        @Test
        void testSumIsNotTheCriterion() {
            assertArrayEquals(new int[][] {{1, 100}, {2, 0}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{2, 0}, {1, 100}}));
        }

        @Test
        void testLastNumberIsNotTheCriterion() {
            assertArrayEquals(new int[][] {{1, 9}, {2, 1}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{2, 1}, {1, 9}}));
        }

        @Test
        void testFirstDifferenceIsInTheSecondPosition() {
            assertArrayEquals(new int[][] {{5, 1, 9}, {5, 2, 0}, {5, 3}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{5, 3}, {5, 2, 0}, {5, 1, 9}}));
        }

        @Test
        void testFirstDifferenceIsInTheThirdPosition() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {1, 2, 4}, {1, 2, 4, 0}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{1, 2, 4, 0}, {1, 2, 4}, {1, 2, 3}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-5, 9}, {-5, 10}, {-1}, {0, -3}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{0, -3}, {-5, 10}, {-1}, {-5, 9}}));
        }

        @Test
        void testPrefixesInAChain() {
            assertArrayEquals(new int[][] {{}, {3}, {3, 3}, {3, 3, 3}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{3, 3, 3}, {3}, {}, {3, 3}}));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE}, {0}, {Integer.MAX_VALUE}},
                    GridSortingChallenge.sortRowsLexicographically(
                            new int[][] {{Integer.MAX_VALUE}, {Integer.MIN_VALUE}, {0}}));
        }

        @Test
        void testNumbersAreNotComparedAsText() {
            assertArrayEquals(new int[][] {{9}, {10}, {100}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{100}, {9}, {10}}));
        }

        @Test
        void testNumbersInsideTheRowsDoNotChange() {
            assertArrayEquals(new int[][] {{2, 1}, {3, 0}},
                    GridSortingChallenge.sortRowsLexicographically(new int[][] {{3, 0}, {2, 1}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{1, 3}, {1, 2, 5}, {0, 9}};
            GridSortingChallenge.sortRowsLexicographically(grid);
            assertArrayEquals(new int[][] {{1, 3}, {1, 2, 5}, {0, 9}}, grid);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[100_000][];
            for (int i = 0; i < grid.length; i++) {
                grid[i] = new int[] {grid.length - i, 7};
            }
            int[][] result = GridSortingChallenge.sortRowsLexicographically(grid);
            assertEquals(1, result[0][0]);
            assertEquals(100_000, result[99_999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortWholeGrid
    // ==========================================================
    @Nested
    class SortWholeGridTests {

        @Test
        void testTwoByTwo() {
            assertArrayEquals(new int[][] {{1, 3}, {7, 9}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{9, 3}, {1, 7}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{3, 4, 5}}, GridSortingChallenge.sortWholeGrid(new int[][] {{5, 4, 3}}));
        }

        @Test
        void testOneColumn() {
            assertArrayEquals(new int[][] {{1}, {2}, {3}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{2}, {1}, {3}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, GridSortingChallenge.sortWholeGrid(new int[][] {}));
        }

        @Test
        void testRowsWithoutNumbers() {
            assertArrayEquals(new int[][] {{}, {}}, GridSortingChallenge.sortWholeGrid(new int[2][0]));
        }

        @Test
        void testOneNumber() {
            assertArrayEquals(new int[][] {{7}}, GridSortingChallenge.sortWholeGrid(new int[][] {{7}}));
        }

        @Test
        void testThreeByThree() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}}));
        }

        @Test
        void testNumbersMoveBetweenRows() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {4, 5, 6}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{4, 5, 6}, {1, 2, 3}}));
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{1, 4}, {2, 3}}));
        }

        @Test
        void testNotSquareWide() {
            assertArrayEquals(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{8, 1, 6, 3}, {4, 5, 2, 7}}));
        }

        @Test
        void testNotSquareTall() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{8, 1}, {6, 3}, {4, 5}, {2, 7}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-9, -3, 0}, {0, 4, 8}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{4, -3, 0}, {8, 0, -9}}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[][] {{1, 1}, {1, 2}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{2, 1}, {1, 1}}));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}},
                    GridSortingChallenge.sortWholeGrid(new int[][] {{1, 2}, {3, 4}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] grid = {{9, 3}, {1, 7}};
            GridSortingChallenge.sortWholeGrid(grid);
            assertArrayEquals(new int[][] {{9, 3}, {1, 7}}, grid);
        }

        @Test
        void testBigGrid() {
            int[][] grid = new int[1000][1000];
            for (int r = 0; r < 1000; r++) {
                for (int c = 0; c < 1000; c++) {
                    grid[r][c] = 999_999 - (r * 1000 + c);
                }
            }
            int[][] result = GridSortingChallenge.sortWholeGrid(grid);
            assertEquals(0, result[0][0]);
            assertEquals(999, result[0][999]);
            assertEquals(1000, result[1][0]);
            assertEquals(500_500, result[500][500]);
            assertEquals(999_999, result[999][999]);
        }
    }

    // ==========================================================
    // CHALLENGE 7: isFullySorted
    // ==========================================================
    @Nested
    class IsFullySortedTests {

        @Test
        void testSorted() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{1, 2}, {2, 5}}));
        }

        @Test
        void testSecondRowStartsBelowTheEndOfTheFirst() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{1, 2}, {1, 5}}));
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{1, 2}, {2, 5}}));
        }

        @Test
        void testEmptyRowInTheMiddle() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{1}, {}, {2, 3}}));
        }

        @Test
        void testRowStartsBelowThePreviousRowEnd() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{3, 4}, {5}, {4}}));
        }

        @Test
        void testNoRows() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {}));
        }

        @Test
        void testOnlyEmptyRows() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{}, {}}));
        }

        @Test
        void testOneNumber() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{5}}));
        }

        @Test
        void testRowNotSortedInsideItself() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{2, 1}}));
        }

        @Test
        void testEachRowSortedButNotTheWholeGrid() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{1, 3}, {2, 4}}));
        }

        @Test
        void testOnlyTheLastNumberIsWrong() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{1, 2}, {3, 4}, {5, 4}}));
        }

        @Test
        void testOnlyTheFirstPairIsWrong() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{2, 1}, {3, 4}}));
        }

        @Test
        void testEqualNumbersAreAllowed() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{2, 2}, {2, 2}, {2}}));
        }

        @Test
        void testEmptyRowsDoNotHideAProblem() {
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{5}, {}, {}, {4}}));
        }

        @Test
        void testEmptyRowAtTheStartAndAtTheEnd() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{}, {1, 2}, {3}, {}}));
        }

        @Test
        void testNegativeNumbers() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{-5, -3}, {-3, 0}, {7}}));
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{-3, -5}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertTrue(GridSortingChallenge.isFullySorted(new int[][] {{1}, {2, 3, 4}, {5, 6}}));
            assertFalse(GridSortingChallenge.isFullySorted(new int[][] {{1}, {2, 3, 4}, {3, 6}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] grid = {{3, 4}, {5}, {4}};
            GridSortingChallenge.isFullySorted(grid);
            assertArrayEquals(new int[][] {{3, 4}, {5}, {4}}, grid);
        }

        @Test
        void testBigSortedGrid() {
            int[][] grid = new int[1000][1000];
            for (int r = 0; r < 1000; r++) {
                for (int c = 0; c < 1000; c++) {
                    grid[r][c] = r * 1000 + c;
                }
            }
            assertTrue(GridSortingChallenge.isFullySorted(grid));
            grid[999][999] = 5;
            assertFalse(GridSortingChallenge.isFullySorted(grid));
        }
    }
}