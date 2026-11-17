package dev.perfectbogus.comparators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MatrixComparatorsChallengeTest {

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int r = 0; r < grid.length; r++) {
            result[r] = grid[r].clone();
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: sortByFirstDescending
    // ==========================================================
    @Nested
    class SortByFirstDescendingTests {

        private int[][] sorted(int[][] rows) {
            int[][] copy = copy(rows);
            MatrixComparatorsChallenge.sortByFirstDescending(copy);
            return copy;
        }

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{3, 2}, {2, 9}, {1, 5}}, sorted(new int[][] {{1, 5}, {3, 2}, {2, 9}}));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{4, 1}, {4, 0}}, sorted(new int[][] {{4, 1}, {4, 0}}));
            assertArrayEquals(new int[][] {{4, 0}, {4, 1}}, sorted(new int[][] {{4, 0}, {4, 1}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{7}}, sorted(new int[][] {{7}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, sorted(new int[][] {}));
        }

        @Test
        void testAscendingBecomesDescending() {
            assertArrayEquals(new int[][] {{3}, {2}, {1}}, sorted(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testAlreadyDescending() {
            assertArrayEquals(new int[][] {{3}, {2}, {1}}, sorted(new int[][] {{3}, {2}, {1}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{2, 0}, {0, 0}, {-1, 0}, {-5, 0}},
                    sorted(new int[][] {{-1, 0}, {-5, 0}, {2, 0}, {0, 0}}));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MAX_VALUE}, {0}, {Integer.MIN_VALUE}},
                    sorted(new int[][] {{Integer.MIN_VALUE}, {Integer.MAX_VALUE}, {0}}));
        }

        @Test
        void testSeveralTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{2, 1}, {2, 2}, {2, 3}, {1, 0}},
                    sorted(new int[][] {{2, 1}, {2, 2}, {1, 0}, {2, 3}}));
        }

        @Test
        void testOtherNumbersDoNotMatter() {
            assertArrayEquals(new int[][] {{2, 0}, {1, 100}}, sorted(new int[][] {{1, 100}, {2, 0}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[][] {{3, 4, 5}, {2, 2}, {1}}, sorted(new int[][] {{1}, {3, 4, 5}, {2, 2}}));
        }

        @Test
        void testFirstNumberNotLastNumber() {
            assertArrayEquals(new int[][] {{2, 1}, {1, 9}}, sorted(new int[][] {{1, 9}, {2, 1}}));
        }

        @Test
        void testTheSameArrayAndTheSameRowsAreUsed() {
            int[] first = {1, 5};
            int[] second = {3, 2};
            int[][] rows = {first, second};
            MatrixComparatorsChallenge.sortByFirstDescending(rows);
            assertSame(second, rows[0]);
            assertSame(first, rows[1]);
            assertArrayEquals(new int[] {1, 5}, first);
            assertArrayEquals(new int[] {3, 2}, second);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i, 0};
            }
            MatrixComparatorsChallenge.sortByFirstDescending(rows);
            assertEquals(99_999, rows[0][0]);
            assertEquals(50_000, rows[49_999][0]);
            assertEquals(0, rows[99_999][0]);
        }

        @Test
        void testBigGridWithManyTies() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i % 2, i};
            }
            MatrixComparatorsChallenge.sortByFirstDescending(rows);
            assertEquals(1, rows[0][1]);
            assertEquals(3, rows[1][1]);
            assertEquals(99_999, rows[49_999][1]);
            assertEquals(0, rows[50_000][1]);
            assertEquals(99_998, rows[99_999][1]);
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortByLastThenFirst
    // ==========================================================
    @Nested
    class SortByLastThenFirstTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{2, 1}, {5, 1}, {0, 0, 9}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{5, 1}, {2, 1}, {0, 0, 9}}));
        }

        @Test
        void testRowsWithOneNumber() {
            assertArrayEquals(new int[][] {{1}, {3}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{3}, {1}}));
        }

        @Test
        void testFullTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{1, 9, 2}, {1, 0, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 9, 2}, {1, 0, 2}}));
            assertArrayEquals(new int[][] {{1, 0, 2}, {1, 9, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 0, 2}, {1, 9, 2}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {}));
        }

        @Test
        void testSameFirstAndLastWithDifferentLengthsKeepTheOrder() {
            assertArrayEquals(new int[][] {{1, 5, 5, 2}, {1, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 5, 5, 2}, {1, 2}}));
            assertArrayEquals(new int[][] {{1, 2}, {1, 5, 5, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 2}, {1, 5, 5, 2}}));
        }

        @Test
        void testLastNumberIsTheMainCriterion() {
            assertArrayEquals(new int[][] {{9, 1}, {1, 9}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 9}, {9, 1}}));
        }

        @Test
        void testSameLastNumberOrderedByFirst() {
            assertArrayEquals(new int[][] {{1, 5}, {2, 5}, {3, 5}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{3, 5}, {1, 5}, {2, 5}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{0, -5}, {0, -1}, {0, 3}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{0, -1}, {0, -5}, {0, 3}}));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[][] {{0}, {9, 2}, {1, 2, 3}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 2, 3}, {9, 2}, {0}}));
        }

        @Test
        void testMiddleNumbersDoNotMatter() {
            assertArrayEquals(new int[][] {{1, 100, 1}, {1, 0, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(new int[][] {{1, 0, 2}, {1, 100, 1}}));
        }

        @Test
        void testExtremeLastNumbers() {
            assertArrayEquals(new int[][] {{0, Integer.MIN_VALUE}, {0, 0}, {0, Integer.MAX_VALUE}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(
                            new int[][] {{0, Integer.MAX_VALUE}, {0, Integer.MIN_VALUE}, {0, 0}}));
        }

        @Test
        void testExtremeFirstNumbers() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 1}, {Integer.MAX_VALUE, 1}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(
                            new int[][] {{Integer.MAX_VALUE, 1}, {Integer.MIN_VALUE, 1}}));
        }

        @Test
        void testMixedExample() {
            assertArrayEquals(new int[][] {{4, 0}, {1, 1}, {2, 1}, {0, 2}, {9, 2}},
                    MatrixComparatorsChallenge.sortByLastThenFirst(
                            new int[][] {{2, 1}, {9, 2}, {4, 0}, {0, 2}, {1, 1}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] rows = {{5, 1}, {2, 1}, {0, 0, 9}};
            MatrixComparatorsChallenge.sortByLastThenFirst(rows);
            assertArrayEquals(new int[][] {{5, 1}, {2, 1}, {0, 0, 9}}, rows);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i, 99_999 - i};
            }
            int[][] result = MatrixComparatorsChallenge.sortByLastThenFirst(rows);
            assertEquals(99_999, result[0][0]);
            assertEquals(0, result[99_999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortPointsByDistance
    // ==========================================================
    @Nested
    class SortPointsByDistanceTests {

        @Test
        void testThreePoints() {
            assertArrayEquals(new int[][] {{1, 1}, {0, -2}, {3, 4}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{3, 4}, {1, 1}, {0, -2}}));
        }

        @Test
        void testSameDistanceKeepsTheOrder() {
            assertArrayEquals(new int[][] {{-5, 0}, {0, 5}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{-5, 0}, {0, 5}}));
            assertArrayEquals(new int[][] {{0, 5}, {-5, 0}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{0, 5}, {-5, 0}}));
        }

        @Test
        void testNotTheManhattanDistance() {
            assertArrayEquals(new int[][] {{3, 3}, {0, 5}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{3, 3}, {0, 5}}));
            assertArrayEquals(new int[][] {{3, 3}, {0, 5}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{0, 5}, {3, 3}}));
        }

        @Test
        void testNoPoints() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {}));
        }

        @Test
        void testOnePoint() {
            assertArrayEquals(new int[][] {{4, 4}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{4, 4}}));
        }

        @Test
        void testTheOriginGoesFirst() {
            assertArrayEquals(new int[][] {{0, 0}, {1, 1}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{1, 1}, {0, 0}}));
        }

        @Test
        void testNegativeCoordinates() {
            assertArrayEquals(new int[][] {{2, 2}, {-3, -4}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{-3, -4}, {2, 2}}));
        }

        @Test
        void testNotTheSumOfTheCoordinates() {
            assertArrayEquals(new int[][] {{3, 3}, {5, -5}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{5, -5}, {3, 3}}));
        }

        @Test
        void testNotTheFirstCoordinate() {
            assertArrayEquals(new int[][] {{2, 0}, {1, 100}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{1, 100}, {2, 0}}));
        }

        @Test
        void testNotTheSecondCoordinate() {
            assertArrayEquals(new int[][] {{100, 0}, {0, 101}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{0, 101}, {100, 0}}));
        }

        @Test
        void testBigCoordinates() {
            assertArrayEquals(new int[][] {{-10000, 9999}, {10000, 10000}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{10000, 10000}, {-10000, 9999}}));
        }

        @Test
        void testFourPointsAtTheSameDistance() {
            assertArrayEquals(new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}));
        }

        @Test
        void testDistancesAreNotRoundedDown() {
            assertArrayEquals(new int[][] {{1, 0}, {1, 1}},
                    MatrixComparatorsChallenge.sortPointsByDistance(new int[][] {{1, 1}, {1, 0}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] points = {{3, 4}, {1, 1}, {0, -2}};
            MatrixComparatorsChallenge.sortPointsByDistance(points);
            assertArrayEquals(new int[][] {{3, 4}, {1, 1}, {0, -2}}, points);
        }

        @Test
        void testBigGrid() {
            int[][] points = new int[100_000][];
            for (int i = 0; i < points.length; i++) {
                points[i] = new int[] {(99_999 - i) % 10_000, 0};
            }
            int[][] result = MatrixComparatorsChallenge.sortPointsByDistance(points);
            assertEquals(0, result[0][0]);
            assertEquals(9_999, result[99_999][0]);
            assertEquals(1, result[10][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortIntervals
    // ==========================================================
    @Nested
    class SortIntervalsTests {

        @Test
        void testThreeIntervals() {
            assertArrayEquals(new int[][] {{1, 2}, {1, 9}, {3, 5}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{3, 5}, {1, 9}, {1, 2}}));
        }

        @Test
        void testStartIsTheMainCriterion() {
            assertArrayEquals(new int[][] {{1, 100}, {2, 3}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{2, 3}, {1, 100}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-5, 3}, {-1, 0}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{-1, 0}, {-5, 3}}));
        }

        @Test
        void testNoIntervals() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortIntervals(new int[][] {}));
        }

        @Test
        void testSameStartOrderedByEnd() {
            assertArrayEquals(new int[][] {{1, 2}, {1, 5}, {1, 9}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{1, 9}, {1, 2}, {1, 5}}));
        }

        @Test
        void testTouchingIntervals() {
            assertArrayEquals(new int[][] {{1, 5}, {5, 8}, {8, 9}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{5, 8}, {1, 5}, {8, 9}}));
        }

        @Test
        void testOneInterval() {
            assertArrayEquals(new int[][] {{2, 2}}, MatrixComparatorsChallenge.sortIntervals(new int[][] {{2, 2}}));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}, {5, 6}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        void testReverseOrder() {
            assertArrayEquals(new int[][] {{1, 2}, {3, 4}, {5, 6}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{5, 6}, {3, 4}, {1, 2}}));
        }

        @Test
        void testEndDoesNotDecideWhenStartsAreDifferent() {
            assertArrayEquals(new int[][] {{0, 10}, {1, 2}, {2, 3}},
                    MatrixComparatorsChallenge.sortIntervals(new int[][] {{2, 3}, {1, 2}, {0, 10}}));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 0}, {0, 0}, {Integer.MAX_VALUE - 1, Integer.MAX_VALUE}},
                    MatrixComparatorsChallenge.sortIntervals(
                            new int[][] {{Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, {Integer.MIN_VALUE, 0}, {0, 0}}));
        }

        @Test
        void testExtremeEnds() {
            assertArrayEquals(new int[][] {{0, Integer.MIN_VALUE + 1}, {0, Integer.MAX_VALUE}},
                    MatrixComparatorsChallenge.sortIntervals(
                            new int[][] {{0, Integer.MAX_VALUE}, {0, Integer.MIN_VALUE + 1}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] intervals = {{3, 5}, {1, 9}, {1, 2}};
            MatrixComparatorsChallenge.sortIntervals(intervals);
            assertArrayEquals(new int[][] {{3, 5}, {1, 9}, {1, 2}}, intervals);
        }

        @Test
        void testBigGrid() {
            int[][] intervals = new int[100_000][];
            for (int i = 0; i < intervals.length; i++) {
                intervals[i] = new int[] {99_999 - i, 200_000 - i};
            }
            int[][] result = MatrixComparatorsChallenge.sortIntervals(intervals);
            assertArrayEquals(new int[] {0, 100_001}, result[0]);
            assertArrayEquals(new int[] {99_999, 200_000}, result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortByColumn
    // ==========================================================
    @Nested
    class SortByColumnTests {

        @Test
        void testAscendingFirstColumn() {
            assertArrayEquals(new int[][] {{1, 9}, {2, 5}, {3, 1}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 0, false));
        }

        @Test
        void testDescendingFirstColumn() {
            assertArrayEquals(new int[][] {{3, 1}, {2, 5}, {1, 9}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 0, true));
        }

        @Test
        void testDescendingTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{4, 2}, {4, 1}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{4, 2}, {4, 1}}, 0, true));
            assertArrayEquals(new int[][] {{4, 1}, {4, 2}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{4, 1}, {4, 2}}, 0, true));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortByColumn(new int[][] {}, 0, false));
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortByColumn(new int[][] {}, 3, true));
        }

        @Test
        void testAscendingTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{4, 2}, {4, 1}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{4, 2}, {4, 1}}, 0, false));
            assertArrayEquals(new int[][] {{4, 1}, {4, 2}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{4, 1}, {4, 2}}, 0, false));
        }

        @Test
        void testSecondColumn() {
            assertArrayEquals(new int[][] {{3, 1}, {2, 5}, {1, 9}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 1, false));
            assertArrayEquals(new int[][] {{1, 9}, {2, 5}, {3, 1}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{3, 1}, {1, 9}, {2, 5}}, 1, true));
        }

        @Test
        void testThirdColumn() {
            assertArrayEquals(new int[][] {{9, 9, 1}, {5, 5, 2}, {1, 1, 3}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{1, 1, 3}, {9, 9, 1}, {5, 5, 2}}, 2, false));
        }

        @Test
        void testOtherColumnsDoNotMatter() {
            assertArrayEquals(new int[][] {{1, 100}, {2, 0}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{2, 0}, {1, 100}}, 0, false));
        }

        @Test
        void testSeveralTiesInDescendingOrder() {
            assertArrayEquals(new int[][] {{2, 2}, {2, 4}, {1, 1}, {1, 3}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{1, 1}, {2, 2}, {1, 3}, {2, 4}}, 0, true));
        }

        @Test
        void testSeveralTiesInAscendingOrder() {
            assertArrayEquals(new int[][] {{1, 1}, {1, 3}, {2, 2}, {2, 4}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{1, 1}, {2, 2}, {1, 3}, {2, 4}}, 0, false));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-5, 0}, {-1, 7}, {2, -3}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{2, -3}, {-5, 0}, {-1, 7}}, 0, false));
            assertArrayEquals(new int[][] {{2, -3}, {-1, 7}, {-5, 0}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{2, -3}, {-5, 0}, {-1, 7}}, 0, true));
        }

        @Test
        void testExtremeNumbers() {
            assertArrayEquals(new int[][] {{Integer.MAX_VALUE}, {0}, {Integer.MIN_VALUE}},
                    MatrixComparatorsChallenge.sortByColumn(
                            new int[][] {{Integer.MIN_VALUE}, {Integer.MAX_VALUE}, {0}}, 0, true));
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE}, {0}, {Integer.MAX_VALUE}},
                    MatrixComparatorsChallenge.sortByColumn(
                            new int[][] {{Integer.MAX_VALUE}, {Integer.MIN_VALUE}, {0}}, 0, false));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{8, 8}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{8, 8}}, 1, true));
        }

        @Test
        void testRowsWithDifferentLengths() {
            assertArrayEquals(new int[][] {{9, 1}, {0, 3, 4}, {5, 8, 0, 0}},
                    MatrixComparatorsChallenge.sortByColumn(new int[][] {{5, 8, 0, 0}, {9, 1}, {0, 3, 4}}, 1, false));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] rows = {{3, 1}, {1, 9}, {2, 5}};
            MatrixComparatorsChallenge.sortByColumn(rows, 0, true);
            MatrixComparatorsChallenge.sortByColumn(rows, 1, false);
            assertArrayEquals(new int[][] {{3, 1}, {1, 9}, {2, 5}}, rows);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i % 2, i};
            }
            int[][] descending = MatrixComparatorsChallenge.sortByColumn(rows, 0, true);
            assertEquals(1, descending[0][1]);
            assertEquals(3, descending[1][1]);
            assertEquals(0, descending[50_000][1]);
            int[][] ascending = MatrixComparatorsChallenge.sortByColumn(rows, 1, false);
            assertEquals(0, ascending[0][1]);
            assertEquals(99_999, ascending[99_999][1]);
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortByThreeColumns
    // ==========================================================
    @Nested
    class SortByThreeColumnsTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[][] {{0, 0, 0}, {1, 5, 0}, {1, 2, 3}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{1, 2, 3}, {1, 5, 0}, {0, 0, 0}}));
        }

        @Test
        void testThirdColumnAscending() {
            assertArrayEquals(new int[][] {{1, 2, 3}, {1, 2, 9}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{1, 2, 9}, {1, 2, 3}}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{2, 2, 2}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{2, 2, 2}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {}));
        }

        @Test
        void testFirstColumnIsTheMainCriterion() {
            assertArrayEquals(new int[][] {{1, 0, 0}, {2, 9, 9}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{2, 9, 9}, {1, 0, 0}}));
        }

        @Test
        void testSecondColumnDescending() {
            assertArrayEquals(new int[][] {{1, 3, 0}, {1, 2, 0}, {1, 1, 0}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{1, 1, 0}, {1, 3, 0}, {1, 2, 0}}));
        }

        @Test
        void testThirdColumnWithSeveralRows() {
            assertArrayEquals(new int[][] {{1, 1, 3}, {1, 1, 4}, {1, 1, 5}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{1, 1, 5}, {1, 1, 3}, {1, 1, 4}}));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{-1, -1, -3}, {-1, -2, -3}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{-1, -2, -3}, {-1, -1, -3}}));
        }

        @Test
        void testExtremeSecondColumn() {
            assertArrayEquals(new int[][] {{0, Integer.MAX_VALUE, 0}, {0, 0, 0}, {0, Integer.MIN_VALUE, 0}},
                    MatrixComparatorsChallenge.sortByThreeColumns(
                            new int[][] {{0, Integer.MIN_VALUE, 0}, {0, Integer.MAX_VALUE, 0}, {0, 0, 0}}));
        }

        @Test
        void testExtremeFirstColumn() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 0, 0}, {Integer.MAX_VALUE, 0, 0}},
                    MatrixComparatorsChallenge.sortByThreeColumns(
                            new int[][] {{Integer.MAX_VALUE, 0, 0}, {Integer.MIN_VALUE, 0, 0}}));
        }

        @Test
        void testExtremeThirdColumn() {
            assertArrayEquals(new int[][] {{0, 0, Integer.MIN_VALUE}, {0, 0, Integer.MAX_VALUE}},
                    MatrixComparatorsChallenge.sortByThreeColumns(
                            new int[][] {{0, 0, Integer.MAX_VALUE}, {0, 0, Integer.MIN_VALUE}}));
        }

        @Test
        void testMixedExample() {
            assertArrayEquals(new int[][] {{1, 2, 2}, {1, 1, 1}, {2, 2, 0}, {2, 2, 1}, {2, 1, 1}},
                    MatrixComparatorsChallenge.sortByThreeColumns(
                            new int[][] {{2, 1, 1}, {1, 1, 1}, {2, 2, 0}, {1, 2, 2}, {2, 2, 1}}));
        }

        @Test
        void testRepeatedRows() {
            assertArrayEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {2, 2, 2}},
                    MatrixComparatorsChallenge.sortByThreeColumns(new int[][] {{2, 2, 2}, {1, 1, 1}, {1, 1, 1}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] rows = {{1, 2, 3}, {1, 5, 0}, {0, 0, 0}};
            MatrixComparatorsChallenge.sortByThreeColumns(rows);
            assertArrayEquals(new int[][] {{1, 2, 3}, {1, 5, 0}, {0, 0, 0}}, rows);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i % 10, i % 100, i};
            }
            int[][] result = MatrixComparatorsChallenge.sortByThreeColumns(rows);
            assertArrayEquals(new int[] {0, 90, 90}, result[0]);
            assertArrayEquals(new int[] {0, 90, 190}, result[1]);
            assertArrayEquals(new int[] {9, 9, 99_909}, result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortByLengthThenSum
    // ==========================================================
    @Nested
    class SortByLengthThenSumTests {

        @Test
        void testFiveRows() {
            assertArrayEquals(new int[][] {{2, 2, 2}, {1, 0}, {1, 1}, {0}, {5}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(
                            new int[][] {{5}, {1, 1}, {0}, {2, 2, 2}, {1, 0}}));
        }

        @Test
        void testEmptyRowGoesLast() {
            assertArrayEquals(new int[][] {{0}, {}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{}, {0}}));
            assertArrayEquals(new int[][] {{5}, {}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{}, {5}}));
        }

        @Test
        void testFullTiesKeepTheOrder() {
            assertArrayEquals(new int[][] {{1, 2}, {2, 1}, {0, 3}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{1, 2}, {2, 1}, {0, 3}}));
            assertArrayEquals(new int[][] {{3, 0}, {0, 3}, {1, 2}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{3, 0}, {0, 3}, {1, 2}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {}));
        }

        @Test
        void testLongerRowsFirst() {
            assertArrayEquals(new int[][] {{1, 1}, {1}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{1}, {1, 1}}));
        }

        @Test
        void testSameLengthOrderedBySum() {
            assertArrayEquals(new int[][] {{1, 1}, {2, 2}, {3, 3}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{3, 3}, {1, 1}, {2, 2}}));
        }

        @Test
        void testSumNotFirstNumber() {
            assertArrayEquals(new int[][] {{5, 0}, {0, 9}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{0, 9}, {5, 0}}));
        }

        @Test
        void testNegativeSums() {
            assertArrayEquals(new int[][] {{-5, -5}, {1, 1}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{1, 1}, {-5, -5}}));
        }

        @Test
        void testSumIsNotTheMainCriterion() {
            assertArrayEquals(new int[][] {{50, 50, 50}, {-1, -1}, {100}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{100}, {-1, -1}, {50, 50, 50}}));
        }

        @Test
        void testSeveralLengths() {
            assertArrayEquals(new int[][] {{1, 1, 1}, {4, 4, 4}, {0, 0}, {9, 9}, {1}, {}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(
                            new int[][] {{}, {9, 9}, {1}, {4, 4, 4}, {0, 0}, {1, 1, 1}}));
        }

        @Test
        void testSeveralEmptyRows() {
            assertArrayEquals(new int[][] {{-1}, {0}, {}, {}},
                    MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{}, {0}, {}, {-1}}));
        }

        @Test
        void testSumOfAnEmptyRowIsZeroAmongRowsOfTheSameLength() {
            int[][] result = MatrixComparatorsChallenge.sortByLengthThenSum(new int[][] {{}, {}});
            assertEquals(2, result.length);
            assertEquals(0, result[0].length);
            assertEquals(0, result[1].length);
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] rows = {{5}, {1, 1}, {0}, {2, 2, 2}, {1, 0}};
            MatrixComparatorsChallenge.sortByLengthThenSum(rows);
            assertArrayEquals(new int[][] {{5}, {1, 1}, {0}, {2, 2, 2}, {1, 0}}, rows);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                int[] row = new int[i % 3 + 1];
                Arrays.fill(row, 99_999 - i);
                rows[i] = row;
            }
            int[][] result = MatrixComparatorsChallenge.sortByLengthThenSum(rows);
            assertArrayEquals(new int[] {1, 1, 1}, result[0]);
            assertArrayEquals(new int[] {99_999}, result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortRecordsByName
    // ==========================================================
    @Nested
    class SortRecordsByNameTests {

        @Test
        void testThreeRecords() {
            assertArrayEquals(new String[][] {{"ana", "Cairo"}, {"Ana", "Lima"}, {"bob", "Quito"}},
                    MatrixComparatorsChallenge.sortRecordsByName(
                            new String[][] {{"bob", "Quito"}, {"Ana", "Lima"}, {"ana", "Cairo"}}));
        }

        @Test
        void testTheCaseIsIgnored() {
            assertArrayEquals(new String[][] {{"a", "x"}, {"B", "y"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"B", "y"}, {"a", "x"}}));
        }

        @Test
        void testFullTiesKeepTheOrder() {
            assertArrayEquals(new String[][] {{"ana", "Lima"}, {"Ana", "LIMA"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"ana", "Lima"}, {"Ana", "LIMA"}}));
            assertArrayEquals(new String[][] {{"Ana", "LIMA"}, {"ana", "Lima"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"Ana", "LIMA"}, {"ana", "Lima"}}));
        }

        @Test
        void testNoRecords() {
            assertArrayEquals(new String[][] {}, MatrixComparatorsChallenge.sortRecordsByName(new String[][] {}));
        }

        @Test
        void testCityDecidesWhenNamesAreEqual() {
            assertArrayEquals(new String[][] {{"Ana", "Cairo"}, {"ana", "lima"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"ana", "lima"}, {"Ana", "Cairo"}}));
        }

        @Test
        void testCityIgnoresTheCaseToo() {
            assertArrayEquals(new String[][] {{"x", "apple"}, {"x", "Banana"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"x", "Banana"}, {"x", "apple"}}));
        }

        @Test
        void testWholeCityDecidesNotOnlyTheFirstLetter() {
            assertArrayEquals(new String[][] {{"x", "Lima"}, {"x", "Lisbon"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"x", "Lisbon"}, {"x", "Lima"}}));
        }

        @Test
        void testNameIsTheMainCriterion() {
            assertArrayEquals(new String[][] {{"amy", "Zurich"}, {"bob", "Atlanta"}},
                    MatrixComparatorsChallenge.sortRecordsByName(
                            new String[][] {{"bob", "Atlanta"}, {"amy", "Zurich"}}));
        }

        @Test
        void testUppercaseNameBeforeLowercase() {
            assertArrayEquals(new String[][] {{"amy", "y"}, {"Zed", "x"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"Zed", "x"}, {"amy", "y"}}));
        }

        @Test
        void testPrefixGoesFirst() {
            assertArrayEquals(new String[][] {{"an", "y"}, {"ann", "x"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"ann", "x"}, {"an", "y"}}));
        }

        @Test
        void testEmptyNameGoesFirst() {
            assertArrayEquals(new String[][] {{"", "y"}, {"a", "x"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"a", "x"}, {"", "y"}}));
        }

        @Test
        void testOneRecord() {
            assertArrayEquals(new String[][] {{"ana", "Lima"}},
                    MatrixComparatorsChallenge.sortRecordsByName(new String[][] {{"ana", "Lima"}}));
        }

        @Test
        void testSeveralRecordsWithTheSameName() {
            assertArrayEquals(new String[][] {{"ana", "A"}, {"ANA", "b"}, {"Ana", "C"}, {"bob", "A"}},
                    MatrixComparatorsChallenge.sortRecordsByName(
                            new String[][] {{"bob", "A"}, {"Ana", "C"}, {"ana", "A"}, {"ANA", "b"}}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[][] records = {{"bob", "Quito"}, {"Ana", "Lima"}, {"ana", "Cairo"}};
            MatrixComparatorsChallenge.sortRecordsByName(records);
            assertArrayEquals(new String[][] {{"bob", "Quito"}, {"Ana", "Lima"}, {"ana", "Cairo"}}, records);
        }

        @Test
        void testBigGrid() {
            String[][] records = new String[100_000][];
            for (int i = 0; i < records.length; i++) {
                records[i] = new String[] {String.format("N%06d", 99_999 - i), "city"};
            }
            String[][] result = MatrixComparatorsChallenge.sortRecordsByName(records);
            assertEquals("N000000", result[0][0]);
            assertEquals("N099999", result[99_999][0]);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortedRowIndices
    // ==========================================================
    @Nested
    class SortedRowIndicesTests {

        @Test
        void testThreeRows() {
            assertArrayEquals(new int[] {1, 2, 0},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{5, 5}, {1}, {2, 3}}));
        }

        @Test
        void testSameSumsKeepThePosition() {
            assertArrayEquals(new int[] {0, 1},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{1}, {1}}));
            assertArrayEquals(new int[] {0, 1, 2},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{2}, {1, 1}, {0, 2}}));
        }

        @Test
        void testEmptyRowAndNegativeSum() {
            assertArrayEquals(new int[] {1, 0}, MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{}, {-1}}));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[] {}, MatrixComparatorsChallenge.sortedRowIndices(new int[][] {}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[] {0}, MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{7, 7}}));
        }

        @Test
        void testSumIsNotTheLength() {
            assertArrayEquals(new int[] {0, 1},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{1, 1, 1}, {10}}));
            assertArrayEquals(new int[] {1, 0},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{10}, {1, 1, 1}}));
        }

        @Test
        void testSumIsNotTheFirstNumber() {
            assertArrayEquals(new int[] {1, 0},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{1, 9}, {5, 0}}));
        }

        @Test
        void testAlreadyOrdered() {
            assertArrayEquals(new int[] {0, 1, 2},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{1}, {2}, {3}}));
        }

        @Test
        void testReverseOrder() {
            assertArrayEquals(new int[] {2, 1, 0},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{3}, {2}, {1}}));
        }

        @Test
        void testNegativeSums() {
            assertArrayEquals(new int[] {2, 1, 3, 0},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{2}, {-3}, {-5, -5}, {0}}));
        }

        @Test
        void testEmptyRowsAmongZeroSums() {
            assertArrayEquals(new int[] {0, 1, 2, 3},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{}, {0}, {1, -1}, {}}));
        }

        @Test
        void testTiesAreByPositionNotByRowContent() {
            assertArrayEquals(new int[] {1, 0, 2},
                    MatrixComparatorsChallenge.sortedRowIndices(new int[][] {{0, 3}, {-1}, {3}}));
        }

        @Test
        void testGridIsNotModified() {
            int[][] rows = {{9, 1}, {1}, {3, 2}};
            MatrixComparatorsChallenge.sortedRowIndices(rows);
            assertArrayEquals(new int[][] {{9, 1}, {1}, {3, 2}}, rows);
        }

        @Test
        void testBigGrid() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {100_000 - i, 0};
            }
            int[] result = MatrixComparatorsChallenge.sortedRowIndices(rows);
            assertEquals(99_999, result[0]);
            assertEquals(0, result[99_999]);
        }

        @Test
        void testBigGridWithTies() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i % 2};
            }
            int[] result = MatrixComparatorsChallenge.sortedRowIndices(rows);
            assertEquals(0, result[0]);
            assertEquals(2, result[1]);
            assertEquals(1, result[50_000]);
            assertEquals(99_999, result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 10: bySecondThenFirst
    // ==========================================================
    @Nested
    class BySecondThenFirstTests {

        private int[][] sorted(int[][] rows) {
            int[][] copy = copy(rows);
            Arrays.sort(copy, MatrixComparatorsChallenge.bySecondThenFirst());
            return copy;
        }

        @Test
        void testSortExample() {
            assertArrayEquals(new int[][] {{5, 1}, {1, 3}, {2, 3}}, sorted(new int[][] {{5, 1}, {2, 3}, {1, 3}}));
        }

        @Test
        void testSecondNumberDecides() {
            assertTrue(MatrixComparatorsChallenge.bySecondThenFirst().compare(new int[] {1, 2}, new int[] {9, 3}) < 0);
            assertTrue(MatrixComparatorsChallenge.bySecondThenFirst().compare(new int[] {9, 3}, new int[] {1, 2}) > 0);
        }

        @Test
        void testSameRowsGiveZero() {
            assertEquals(0, MatrixComparatorsChallenge.bySecondThenFirst().compare(new int[] {4, 4}, new int[] {4, 4}));
        }

        @Test
        void testFirstNumberDecidesWhenSecondIsEqual() {
            assertTrue(MatrixComparatorsChallenge.bySecondThenFirst().compare(new int[] {1, 5}, new int[] {2, 5}) < 0);
            assertTrue(MatrixComparatorsChallenge.bySecondThenFirst().compare(new int[] {2, 5}, new int[] {1, 5}) > 0);
        }

        @Test
        void testExtremeSecondNumbers() {
            Comparator<int[]> comparator = MatrixComparatorsChallenge.bySecondThenFirst();
            assertTrue(comparator.compare(new int[] {0, Integer.MIN_VALUE}, new int[] {0, Integer.MAX_VALUE}) < 0);
            assertTrue(comparator.compare(new int[] {0, Integer.MAX_VALUE}, new int[] {0, Integer.MIN_VALUE}) > 0);
        }

        @Test
        void testExtremeFirstNumbers() {
            Comparator<int[]> comparator = MatrixComparatorsChallenge.bySecondThenFirst();
            assertTrue(comparator.compare(new int[] {Integer.MIN_VALUE, 5}, new int[] {Integer.MAX_VALUE, 5}) < 0);
            assertTrue(comparator.compare(new int[] {Integer.MAX_VALUE, 5}, new int[] {Integer.MIN_VALUE, 5}) > 0);
        }

        @Test
        void testRowsWithMoreNumbersOnlyUseTheFirstTwo() {
            Comparator<int[]> comparator = MatrixComparatorsChallenge.bySecondThenFirst();
            assertEquals(0, comparator.compare(new int[] {1, 2, 9}, new int[] {1, 2, 0}));
            assertTrue(comparator.compare(new int[] {1, 2, 9}, new int[] {1, 3, 0}) < 0);
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[][] {{3, -9}, {-1, -2}, {5, -2}, {0, 0}},
                    sorted(new int[][] {{5, -2}, {0, 0}, {3, -9}, {-1, -2}}));
        }

        @Test
        void testSortWithManyTies() {
            assertArrayEquals(new int[][] {{1, 1}, {2, 1}, {3, 1}, {0, 2}},
                    sorted(new int[][] {{3, 1}, {0, 2}, {1, 1}, {2, 1}}));
        }

        @Test
        void testFirstNumberAloneIsNotEnough() {
            assertArrayEquals(new int[][] {{9, 0}, {0, 9}}, sorted(new int[][] {{0, 9}, {9, 0}}));
        }

        @Test
        void testEmptyAndOneRowSort() {
            assertArrayEquals(new int[][] {}, sorted(new int[][] {}));
            assertArrayEquals(new int[][] {{1, 2}}, sorted(new int[][] {{1, 2}}));
        }

        @Test
        void testEveryCallGivesAWorkingComparator() {
            Comparator<int[]> first = MatrixComparatorsChallenge.bySecondThenFirst();
            Comparator<int[]> second = MatrixComparatorsChallenge.bySecondThenFirst();
            assertTrue(first.compare(new int[] {0, 1}, new int[] {0, 2}) < 0);
            assertTrue(second.compare(new int[] {0, 1}, new int[] {0, 2}) < 0);
        }

        @Test
        void testWorksWithListSort() {
            java.util.List<int[]> rows = new java.util.ArrayList<>();
            rows.add(new int[] {5, 1});
            rows.add(new int[] {2, 3});
            rows.add(new int[] {1, 3});
            rows.sort(MatrixComparatorsChallenge.bySecondThenFirst());
            assertArrayEquals(new int[] {5, 1}, rows.get(0));
            assertArrayEquals(new int[] {1, 3}, rows.get(1));
            assertArrayEquals(new int[] {2, 3}, rows.get(2));
        }

        @Test
        void testBigSort() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {i, (99_999 - i) / 2};
            }
            int[][] result = sorted(rows);
            assertArrayEquals(new int[] {99_998, 0}, result[0]);
            assertArrayEquals(new int[] {99_999, 0}, result[1]);
            assertArrayEquals(new int[] {0, 49_999}, result[99_998]);
            assertArrayEquals(new int[] {1, 49_999}, result[99_999]);
        }
    }
}