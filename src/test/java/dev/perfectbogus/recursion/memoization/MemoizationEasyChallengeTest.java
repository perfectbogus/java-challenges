package dev.perfectbogus.recursion.memoization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class MemoizationEasyChallengeTest {

    private static boolean[][] grid(int rows, int cols) {
        return new boolean[rows][cols];
    }

    // ==========================================================
    // CHALLENGE 1: fibonacci
    // ==========================================================
    @Nested
    class FibonacciTests {

        @Test
        void testZero() {
            assertEquals(0L, MemoizationEasyChallenge.fibonacci(0));
        }

        @Test
        void testOne() {
            assertEquals(1L, MemoizationEasyChallenge.fibonacci(1));
        }

        @Test
        void testTwo() {
            assertEquals(1L, MemoizationEasyChallenge.fibonacci(2));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(2L, MemoizationEasyChallenge.fibonacci(3));
            assertEquals(3L, MemoizationEasyChallenge.fibonacci(4));
            assertEquals(5L, MemoizationEasyChallenge.fibonacci(5));
            assertEquals(8L, MemoizationEasyChallenge.fibonacci(6));
        }

        @Test
        void testTen() {
            assertEquals(55L, MemoizationEasyChallenge.fibonacci(10));
        }

        @Test
        void testTwenty() {
            assertEquals(6765L, MemoizationEasyChallenge.fibonacci(20));
        }

        @Test
        void testThirty() {
            assertEquals(832040L, MemoizationEasyChallenge.fibonacci(30));
        }

        @Test
        void testFifty() {
            assertEquals(12586269025L, MemoizationEasyChallenge.fibonacci(50));
        }

        @Test
        void testSeventy() {
            assertEquals(190392490709135L, MemoizationEasyChallenge.fibonacci(70));
        }

        @Test
        void testNinety() {
            assertEquals(2880067194370816120L, MemoizationEasyChallenge.fibonacci(90));
        }

        @Test
        void testEveryNumberIsTheSumOfThePreviousTwo() {
            for (int n = 2; n <= 90; n++) {
                assertEquals(MemoizationEasyChallenge.fibonacci(n - 1) + MemoizationEasyChallenge.fibonacci(n - 2),
                        MemoizationEasyChallenge.fibonacci(n));
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(12586269025L, MemoizationEasyChallenge.fibonacci(50));
            assertEquals(55L, MemoizationEasyChallenge.fibonacci(10));
            assertEquals(12586269025L, MemoizationEasyChallenge.fibonacci(50));
            assertEquals(0L, MemoizationEasyChallenge.fibonacci(0));
        }
    }

    // ==========================================================
    // CHALLENGE 2: climbStairs
    // ==========================================================
    @Nested
    class ClimbStairsTests {

        @Test
        void testZeroSteps() {
            assertEquals(1L, MemoizationEasyChallenge.climbStairs(0));
        }

        @Test
        void testOneStep() {
            assertEquals(1L, MemoizationEasyChallenge.climbStairs(1));
        }

        @Test
        void testTwoSteps() {
            assertEquals(2L, MemoizationEasyChallenge.climbStairs(2));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(3L, MemoizationEasyChallenge.climbStairs(3));
            assertEquals(5L, MemoizationEasyChallenge.climbStairs(4));
            assertEquals(8L, MemoizationEasyChallenge.climbStairs(5));
            assertEquals(13L, MemoizationEasyChallenge.climbStairs(6));
            assertEquals(21L, MemoizationEasyChallenge.climbStairs(7));
        }

        @Test
        void testTen() {
            assertEquals(89L, MemoizationEasyChallenge.climbStairs(10));
        }

        @Test
        void testThirty() {
            assertEquals(1346269L, MemoizationEasyChallenge.climbStairs(30));
        }

        @Test
        void testFifty() {
            assertEquals(20365011074L, MemoizationEasyChallenge.climbStairs(50));
        }

        @Test
        void testNinety() {
            assertEquals(4660046610375530309L, MemoizationEasyChallenge.climbStairs(90));
        }

        @Test
        void testEveryNumberIsTheSumOfThePreviousTwo() {
            for (int n = 2; n <= 90; n++) {
                assertEquals(MemoizationEasyChallenge.climbStairs(n - 1) + MemoizationEasyChallenge.climbStairs(n - 2),
                        MemoizationEasyChallenge.climbStairs(n));
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(20365011074L, MemoizationEasyChallenge.climbStairs(50));
            assertEquals(8L, MemoizationEasyChallenge.climbStairs(5));
            assertEquals(20365011074L, MemoizationEasyChallenge.climbStairs(50));
        }
    }

    // ==========================================================
    // CHALLENGE 3: tribonacci
    // ==========================================================
    @Nested
    class TribonacciTests {

        @Test
        void testZero() {
            assertEquals(0L, MemoizationEasyChallenge.tribonacci(0));
        }

        @Test
        void testOneAndTwo() {
            assertEquals(1L, MemoizationEasyChallenge.tribonacci(1));
            assertEquals(1L, MemoizationEasyChallenge.tribonacci(2));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(2L, MemoizationEasyChallenge.tribonacci(3));
            assertEquals(4L, MemoizationEasyChallenge.tribonacci(4));
            assertEquals(7L, MemoizationEasyChallenge.tribonacci(5));
            assertEquals(13L, MemoizationEasyChallenge.tribonacci(6));
            assertEquals(24L, MemoizationEasyChallenge.tribonacci(7));
            assertEquals(44L, MemoizationEasyChallenge.tribonacci(8));
            assertEquals(81L, MemoizationEasyChallenge.tribonacci(9));
        }

        @Test
        void testTen() {
            assertEquals(149L, MemoizationEasyChallenge.tribonacci(10));
        }

        @Test
        void testTwenty() {
            assertEquals(66012L, MemoizationEasyChallenge.tribonacci(20));
        }

        @Test
        void testForty() {
            assertEquals(12960201916L, MemoizationEasyChallenge.tribonacci(40));
        }

        @Test
        void testSeventy() {
            assertEquals(1127444240280152749L, MemoizationEasyChallenge.tribonacci(70));
        }

        @Test
        void testEveryNumberIsTheSumOfThePreviousThree() {
            for (int n = 3; n <= 70; n++) {
                assertEquals(MemoizationEasyChallenge.tribonacci(n - 1) + MemoizationEasyChallenge.tribonacci(n - 2)
                        + MemoizationEasyChallenge.tribonacci(n - 3), MemoizationEasyChallenge.tribonacci(n));
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(12960201916L, MemoizationEasyChallenge.tribonacci(40));
            assertEquals(149L, MemoizationEasyChallenge.tribonacci(10));
            assertEquals(12960201916L, MemoizationEasyChallenge.tribonacci(40));
        }
    }

    // ==========================================================
    // CHALLENGE 4: countWithoutConsecutiveOnes
    // ==========================================================
    @Nested
    class CountWithoutConsecutiveOnesTests {

        @Test
        void testZero() {
            assertEquals(1L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(0));
        }

        @Test
        void testOne() {
            assertEquals(2L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(1));
        }

        @Test
        void testTwo() {
            assertEquals(3L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(2));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(5L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(3));
            assertEquals(8L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(4));
            assertEquals(13L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(5));
            assertEquals(21L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(6));
        }

        @Test
        void testTen() {
            assertEquals(144L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(10));
        }

        @Test
        void testForty() {
            assertEquals(267914296L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(40));
        }

        @Test
        void testNinety() {
            assertEquals(7540113804746346429L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(90));
        }

        @Test
        void testEveryNumberIsTheSumOfThePreviousTwo() {
            for (int n = 2; n <= 90; n++) {
                assertEquals(MemoizationEasyChallenge.countWithoutConsecutiveOnes(n - 1)
                                + MemoizationEasyChallenge.countWithoutConsecutiveOnes(n - 2),
                        MemoizationEasyChallenge.countWithoutConsecutiveOnes(n));
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(267914296L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(40));
            assertEquals(8L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(4));
            assertEquals(267914296L, MemoizationEasyChallenge.countWithoutConsecutiveOnes(40));
        }
    }

    // ==========================================================
    // CHALLENGE 5: gridPaths
    // ==========================================================
    @Nested
    class GridPathsTests {

        @Test
        void testOneCell() {
            assertEquals(1L, MemoizationEasyChallenge.gridPaths(1, 1));
        }

        @Test
        void testOneCellString() {
            assertEquals(1L, MemoizationEasyChallenge.gridPathsString(1, 1));
        }

        @Test
        void testOneRowOrOneColumn() {
            assertEquals(1L, MemoizationEasyChallenge.gridPaths(1, 5));
            assertEquals(1L, MemoizationEasyChallenge.gridPaths(5, 1));
            assertEquals(1L, MemoizationEasyChallenge.gridPaths(1, 30));
            assertEquals(1L, MemoizationEasyChallenge.gridPaths(30, 1));
        }

        @Test
        void testTwoByTwo() {
            assertEquals(2L, MemoizationEasyChallenge.gridPaths(2, 2));
        }

        @Test
        void testThreeByThree() {
            assertEquals(6L, MemoizationEasyChallenge.gridPaths(3, 3));
        }

        @Test
        void testRectangles() {
            assertEquals(28L, MemoizationEasyChallenge.gridPaths(3, 7));
            assertEquals(28L, MemoizationEasyChallenge.gridPaths(7, 3));
            assertEquals(330L, MemoizationEasyChallenge.gridPaths(5, 8));
            assertEquals(12376L, MemoizationEasyChallenge.gridPaths(12, 7));
        }

        @Test
        void testFourByFour() {
            assertEquals(20L, MemoizationEasyChallenge.gridPaths(4, 4));
        }

        @Test
        void testTenByTen() {
            assertEquals(48620L, MemoizationEasyChallenge.gridPaths(10, 10));
        }

        @Test
        void testEighteenByEighteen() {
            assertEquals(2333606220L, MemoizationEasyChallenge.gridPaths(18, 18));
        }

        @Test
        void testTwoRowsAndManyColumns() {
            assertEquals(30L, MemoizationEasyChallenge.gridPaths(2, 30));
            assertEquals(30L, MemoizationEasyChallenge.gridPaths(30, 2));
        }

        @Test
        void testBiggestGrid() {
            assertEquals(30067266499541040L, MemoizationEasyChallenge.gridPaths(30, 30));
        }

        @Test
        void testRowsAndColumnsAreSymmetric() {
            for (int r = 1; r <= 15; r++) {
                for (int c = 1; c <= 15; c++) {
                    assertEquals(MemoizationEasyChallenge.gridPaths(r, c), MemoizationEasyChallenge.gridPaths(c, r));
                }
            }
        }
    }

    // ==========================================================
    // CHALLENGE 6: binomial
    // ==========================================================
    @Nested
    class BinomialTests {

        @Test
        void testZeroZero() {
            assertEquals(1L, MemoizationEasyChallenge.binomial(0, 0));
        }

        @Test
        void testChooseNone() {
            assertEquals(1L, MemoizationEasyChallenge.binomial(5, 0));
            assertEquals(1L, MemoizationEasyChallenge.binomial(60, 0));
        }

        @Test
        void testChooseAll() {
            assertEquals(1L, MemoizationEasyChallenge.binomial(5, 5));
            assertEquals(1L, MemoizationEasyChallenge.binomial(60, 60));
        }

        @Test
        void testChooseOne() {
            assertEquals(7L, MemoizationEasyChallenge.binomial(7, 1));
            assertEquals(60L, MemoizationEasyChallenge.binomial(60, 1));
        }

        @Test
        void testSmallValues() {
            assertEquals(10L, MemoizationEasyChallenge.binomial(5, 2));
            assertEquals(20L, MemoizationEasyChallenge.binomial(6, 3));
            assertEquals(252L, MemoizationEasyChallenge.binomial(10, 5));
        }

        @Test
        void testSymmetry() {
            assertEquals(120L, MemoizationEasyChallenge.binomial(10, 3));
            assertEquals(120L, MemoizationEasyChallenge.binomial(10, 7));
        }

        @Test
        void testCardHands() {
            assertEquals(2598960L, MemoizationEasyChallenge.binomial(52, 5));
        }

        @Test
        void testMiddleValues() {
            assertEquals(184756L, MemoizationEasyChallenge.binomial(20, 10));
            assertEquals(155117520L, MemoizationEasyChallenge.binomial(30, 15));
            assertEquals(137846528820L, MemoizationEasyChallenge.binomial(40, 20));
        }

        @Test
        void testBiggestValue() {
            assertEquals(118264581564861424L, MemoizationEasyChallenge.binomial(60, 30));
        }

        @Test
        void testPascalRule() {
            for (int n = 2; n <= 40; n++) {
                for (int k = 1; k < n; k++) {
                    assertEquals(MemoizationEasyChallenge.binomial(n - 1, k - 1)
                            + MemoizationEasyChallenge.binomial(n - 1, k), MemoizationEasyChallenge.binomial(n, k));
                }
            }
        }
    }

    // ==========================================================
    // CHALLENGE 7: catalan
    // ==========================================================
    @Nested
    class CatalanTests {

        @Test
        void testZero() {
            assertEquals(1L, MemoizationEasyChallenge.catalan(0));
        }

        @Test
        void testOne() {
            assertEquals(1L, MemoizationEasyChallenge.catalan(1));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(2L, MemoizationEasyChallenge.catalan(2));
            assertEquals(5L, MemoizationEasyChallenge.catalan(3));
            assertEquals(14L, MemoizationEasyChallenge.catalan(4));
            assertEquals(42L, MemoizationEasyChallenge.catalan(5));
            assertEquals(132L, MemoizationEasyChallenge.catalan(6));
            assertEquals(429L, MemoizationEasyChallenge.catalan(7));
            assertEquals(1430L, MemoizationEasyChallenge.catalan(8));
        }

        @Test
        void testTen() {
            assertEquals(16796L, MemoizationEasyChallenge.catalan(10));
        }

        @Test
        void testTwenty() {
            assertEquals(6564120420L, MemoizationEasyChallenge.catalan(20));
        }

        @Test
        void testTwentyFive() {
            assertEquals(4861946401452L, MemoizationEasyChallenge.catalan(25));
        }

        @Test
        void testThirty() {
            assertEquals(3814986502092304L, MemoizationEasyChallenge.catalan(30));
        }

        @Test
        void testRule() {
            for (int n = 1; n <= 30; n++) {
                long total = 0;
                for (int i = 0; i < n; i++) {
                    total += MemoizationEasyChallenge.catalan(i) * MemoizationEasyChallenge.catalan(n - 1 - i);
                }
                assertEquals(total, MemoizationEasyChallenge.catalan(n));
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(3814986502092304L, MemoizationEasyChallenge.catalan(30));
            assertEquals(14L, MemoizationEasyChallenge.catalan(4));
            assertEquals(3814986502092304L, MemoizationEasyChallenge.catalan(30));
        }
    }

    // ==========================================================
    // CHALLENGE 8: minSquares
    // ==========================================================
    @Nested
    class MinSquaresTests {

        @Test
        void testZero() {
            assertEquals(0, MemoizationEasyChallenge.minSquares(0));
        }

        @Test
        void testSmallNumbers() {
            assertEquals(1, MemoizationEasyChallenge.minSquares(1));
            assertEquals(2, MemoizationEasyChallenge.minSquares(2));
            assertEquals(3, MemoizationEasyChallenge.minSquares(3));
            assertEquals(1, MemoizationEasyChallenge.minSquares(4));
        }

        @Test
        void testSeven() {
            assertEquals(4, MemoizationEasyChallenge.minSquares(7));
        }

        @Test
        void testTwelve() {
            assertEquals(3, MemoizationEasyChallenge.minSquares(12));
        }

        @Test
        void testThirteen() {
            assertEquals(2, MemoizationEasyChallenge.minSquares(13));
        }

        @Test
        void testPerfectSquares() {
            assertEquals(1, MemoizationEasyChallenge.minSquares(25));
            assertEquals(1, MemoizationEasyChallenge.minSquares(100));
            assertEquals(1, MemoizationEasyChallenge.minSquares(961));
        }

        @Test
        void testGreedyIsNotEnough() {
            assertEquals(2, MemoizationEasyChallenge.minSquares(18));
            assertEquals(3, MemoizationEasyChallenge.minSquares(99));
        }

        @Test
        void testMoreValues() {
            assertEquals(2, MemoizationEasyChallenge.minSquares(8));
            assertEquals(4, MemoizationEasyChallenge.minSquares(23));
            assertEquals(3, MemoizationEasyChallenge.minSquares(30));
            assertEquals(2, MemoizationEasyChallenge.minSquares(50));
            assertEquals(4, MemoizationEasyChallenge.minSquares(63));
        }

        @Test
        void testBigValues() {
            assertEquals(4, MemoizationEasyChallenge.minSquares(999));
            assertEquals(2, MemoizationEasyChallenge.minSquares(1000));
        }

        @Test
        void testNeverMoreThanFour() {
            for (int n = 0; n <= 1000; n++) {
                assertTrue(MemoizationEasyChallenge.minSquares(n) <= 4);
            }
        }

        @Test
        void testSeveralCallsGiveTheSameResult() {
            assertEquals(2, MemoizationEasyChallenge.minSquares(1000));
            assertEquals(3, MemoizationEasyChallenge.minSquares(12));
            assertEquals(2, MemoizationEasyChallenge.minSquares(1000));
        }
    }

    // ==========================================================
    // CHALLENGE 9: houseRobber
    // ==========================================================
    @Nested
    class HouseRobberTests {

        @Test
        void testNoHouses() {
            assertEquals(0L, MemoizationEasyChallenge.houseRobber(new int[] {}));
        }

        @Test
        void testOneHouse() {
            assertEquals(5L, MemoizationEasyChallenge.houseRobber(new int[] {5}));
        }

        @Test
        void testTwoHouses() {
            assertEquals(5L, MemoizationEasyChallenge.houseRobber(new int[] {5, 3}));
            assertEquals(5L, MemoizationEasyChallenge.houseRobber(new int[] {3, 5}));
            assertEquals(5L, MemoizationEasyChallenge.houseRobber(new int[] {5, 5}));
        }

        @Test
        void testFiveHouses() {
            assertEquals(12L, MemoizationEasyChallenge.houseRobber(new int[] {2, 7, 9, 3, 1}));
        }

        @Test
        void testFourHouses() {
            assertEquals(4L, MemoizationEasyChallenge.houseRobber(new int[] {1, 2, 3, 1}));
            assertEquals(4L, MemoizationEasyChallenge.houseRobber(new int[] {2, 1, 1, 2}));
        }

        @Test
        void testBestIsBothEnds() {
            assertEquals(20L, MemoizationEasyChallenge.houseRobber(new int[] {10, 1, 1, 10}));
        }

        @Test
        void testAllZero() {
            assertEquals(0L, MemoizationEasyChallenge.houseRobber(new int[] {0, 0}));
        }

        @Test
        void testThreeEqualHouses() {
            assertEquals(10L, MemoizationEasyChallenge.houseRobber(new int[] {5, 5, 5}));
        }

        @Test
        void testSevenHouses() {
            assertEquals(41L, MemoizationEasyChallenge.houseRobber(new int[] {6, 7, 1, 30, 8, 2, 4}));
        }

        @Test
        void testSkippingTwoHousesCanBeBest() {
            assertEquals(14L, MemoizationEasyChallenge.houseRobber(new int[] {7, 1, 1, 7, 0, 0}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] values = {2, 7, 9, 3, 1};
            MemoizationEasyChallenge.houseRobber(values);
            assertArrayEquals(new int[] {2, 7, 9, 3, 1}, values);
        }

        @Test
        void testManyHouses() {
            int[] values = new int[1000];
            for (int i = 0; i < values.length; i++) {
                values[i] = i % 7 * 10 + i % 3;
            }
            assertEquals(17559L, MemoizationEasyChallenge.houseRobber(values));
        }

        @Test
        void testManyEqualHouses() {
            int[] values = new int[1000];
            java.util.Arrays.fill(values, 5);
            assertEquals(2500L, MemoizationEasyChallenge.houseRobber(values));
            int[] odd = new int[999];
            java.util.Arrays.fill(odd, 100);
            assertEquals(50_000L, MemoizationEasyChallenge.houseRobber(odd));
        }
    }

    // ==========================================================
    // CHALLENGE 10: minCoins
    // ==========================================================
    @Nested
    class MinCoinsTests {

        @Test
        void testThreeCoins() {
            assertEquals(3, MemoizationEasyChallenge.minCoins(new int[] {1, 2, 5}, 11));
        }

        @Test
        void testNotPossible() {
            assertEquals(-1, MemoizationEasyChallenge.minCoins(new int[] {2}, 3));
            assertEquals(-1, MemoizationEasyChallenge.minCoins(new int[] {2, 5}, 3));
        }

        @Test
        void testGreedyIsNotEnough() {
            assertEquals(2, MemoizationEasyChallenge.minCoins(new int[] {1, 3, 4}, 6));
            assertEquals(2, MemoizationEasyChallenge.minCoins(new int[] {9, 6, 5, 1}, 11));
            assertEquals(3, MemoizationEasyChallenge.minCoins(new int[] {5, 3}, 11));
            assertEquals(3, MemoizationEasyChallenge.minCoins(new int[] {7, 3}, 13));
        }

        @Test
        void testAmountZero() {
            assertEquals(0, MemoizationEasyChallenge.minCoins(new int[] {5}, 0));
            assertEquals(0, MemoizationEasyChallenge.minCoins(new int[] {}, 0));
        }

        @Test
        void testOneCoinIsEnough() {
            assertEquals(1, MemoizationEasyChallenge.minCoins(new int[] {5}, 5));
        }

        @Test
        void testSameCoinManyTimes() {
            assertEquals(2, MemoizationEasyChallenge.minCoins(new int[] {2}, 4));
            assertEquals(7, MemoizationEasyChallenge.minCoins(new int[] {1}, 7));
        }

        @Test
        void testNoCoins() {
            assertEquals(-1, MemoizationEasyChallenge.minCoins(new int[] {}, 5));
        }

        @Test
        void testCoinsInAnyOrder() {
            assertEquals(3, MemoizationEasyChallenge.minCoins(new int[] {5, 1, 2}, 11));
            assertEquals(3, MemoizationEasyChallenge.minCoins(new int[] {2, 5, 1}, 11));
        }

        @Test
        void testCoinBiggerThanTheAmount() {
            assertEquals(2, MemoizationEasyChallenge.minCoins(new int[] {100, 1}, 2));
        }

        @Test
        void testBigAmounts() {
            assertEquals(200, MemoizationEasyChallenge.minCoins(new int[] {1, 2, 5}, 1000));
            assertEquals(45, MemoizationEasyChallenge.minCoins(new int[] {1, 5, 10, 25}, 999));
            assertEquals(8, MemoizationEasyChallenge.minCoins(new int[] {186, 419, 83, 408}, 1000));
        }

        @Test
        void testBigAmountsWithEvenCoins() {
            assertEquals(250, MemoizationEasyChallenge.minCoins(new int[] {2, 4}, 1000));
            assertEquals(-1, MemoizationEasyChallenge.minCoins(new int[] {2, 4}, 999));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] coins = {5, 1, 2};
            MemoizationEasyChallenge.minCoins(coins, 11);
            assertArrayEquals(new int[] {5, 1, 2}, coins);
        }
    }

    // ==========================================================
    // CHALLENGE 11: countWays
    // ==========================================================
    @Nested
    class CountWaysTests {

        @Test
        void testZero() {
            assertEquals(1L, MemoizationEasyChallenge.countWays(0, new int[] {1, 2}));
            assertEquals(1L, MemoizationEasyChallenge.countWays(0, new int[] {3}));
        }

        @Test
        void testOne() {
            assertEquals(1L, MemoizationEasyChallenge.countWays(1, new int[] {1, 2}));
        }

        @Test
        void testSmallValues() {
            assertEquals(3L, MemoizationEasyChallenge.countWays(3, new int[] {1, 2}));
            assertEquals(5L, MemoizationEasyChallenge.countWays(4, new int[] {1, 2}));
        }

        @Test
        void testStepsOneAndThree() {
            assertEquals(2L, MemoizationEasyChallenge.countWays(3, new int[] {1, 3}));
        }

        @Test
        void testNotPossible() {
            assertEquals(0L, MemoizationEasyChallenge.countWays(5, new int[] {2}));
            assertEquals(0L, MemoizationEasyChallenge.countWays(1, new int[] {2, 3}));
        }

        @Test
        void testOneStepSize() {
            assertEquals(1L, MemoizationEasyChallenge.countWays(4, new int[] {2}));
            assertEquals(1L, MemoizationEasyChallenge.countWays(10, new int[] {5}));
            assertEquals(1L, MemoizationEasyChallenge.countWays(9, new int[] {3}));
        }

        @Test
        void testTheOrderMatters() {
            assertEquals(3L, MemoizationEasyChallenge.countWays(7, new int[] {2, 3}));
            assertEquals(4L, MemoizationEasyChallenge.countWays(6, new int[] {4, 1}));
        }

        @Test
        void testStepsInAnyOrder() {
            assertEquals(274L, MemoizationEasyChallenge.countWays(10, new int[] {1, 2, 3}));
            assertEquals(274L, MemoizationEasyChallenge.countWays(10, new int[] {3, 1, 2}));
        }

        @Test
        void testThreeSteps() {
            assertEquals(13L, MemoizationEasyChallenge.countWays(5, new int[] {1, 2, 3}));
            assertEquals(55L, MemoizationEasyChallenge.countWays(8, new int[] {1, 2, 4}));
        }

        @Test
        void testManyStepSizes() {
            assertEquals(492L, MemoizationEasyChallenge.countWays(10, new int[] {1, 2, 3, 4, 5, 6}));
            assertEquals(2000L, MemoizationEasyChallenge.countWays(12, new int[] {6, 5, 4, 3, 2, 1, 7}));
        }

        @Test
        void testStepBiggerThanN() {
            assertEquals(1L, MemoizationEasyChallenge.countWays(2, new int[] {1, 5}));
        }

        @Test
        void testBigValues() {
            assertEquals(20365011074L, MemoizationEasyChallenge.countWays(50, new int[] {1, 2}));
            assertEquals(10562230626642L, MemoizationEasyChallenge.countWays(50, new int[] {1, 2, 3}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] steps = {3, 1, 2};
            MemoizationEasyChallenge.countWays(10, steps);
            assertArrayEquals(new int[] {3, 1, 2}, steps);
        }
    }

    // ==========================================================
    // CHALLENGE 12: canMakeSum
    // ==========================================================
    @Nested
    class CanMakeSumTests {

        @Test
        void testPossible() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {3, 34, 4, 12, 5, 2}, 9));
        }

        @Test
        void testNotPossible() {
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {3, 34}, 10));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {3, 34, 4, 12, 5, 2}, 30));
        }

        @Test
        void testTargetZero() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {}, 0));
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {5}, 0));
        }

        @Test
        void testNoNumbers() {
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {}, 1));
        }

        @Test
        void testOneNumber() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {5}, 5));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {5}, 4));
        }

        @Test
        void testEachNumberIsUsedOnce() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {2, 2}, 2));
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {2, 2}, 4));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {2, 2}, 6));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {5}, 10));
        }

        @Test
        void testAllNumbers() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {3, 34, 4, 12, 5, 2}, 60));
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {1, 2, 3}, 6));
        }

        @Test
        void testTargetBiggerThanTheTotal() {
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {1, 2, 3}, 7));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {1, 2, 3}, 100));
        }

        @Test
        void testTheBiggestNumberIsNeeded() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {3, 34, 4, 12, 5, 2}, 55));
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {4, 6}, 10));
            assertFalse(MemoizationEasyChallenge.canMakeSum(new int[] {4, 6}, 5));
        }

        @Test
        void testZeros() {
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {0, 0}, 0));
            assertTrue(MemoizationEasyChallenge.canMakeSum(new int[] {0, 3}, 3));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {3, 34, 4, 12, 5, 2};
            MemoizationEasyChallenge.canMakeSum(nums, 9);
            assertArrayEquals(new int[] {3, 34, 4, 12, 5, 2}, nums);
        }

        @Test
        void testManyNumbers() {
            int[] nums = new int[40];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i + 1;
            }
            assertTrue(MemoizationEasyChallenge.canMakeSum(nums, 800));
            assertTrue(MemoizationEasyChallenge.canMakeSum(nums, 820));
            assertFalse(MemoizationEasyChallenge.canMakeSum(nums, 821));
        }

        @Test
        void testManyEvenNumbers() {
            int[] nums = new int[40];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 2 * (i + 1);
            }
            assertFalse(MemoizationEasyChallenge.canMakeSum(nums, 801));
            assertTrue(MemoizationEasyChallenge.canMakeSum(nums, 820));
        }
    }

    // ==========================================================
    // CHALLENGE 13: gridPathsWithBlocks
    // ==========================================================
    @Nested
    class GridPathsWithBlocksTests {

        @Test
        void testNoBlocks() {
            assertEquals(6L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(3, 3)));
            assertEquals(2L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(2, 2)));
        }

        @Test
        void testBlockedCenter() {
            boolean[][] blocked = grid(3, 3);
            blocked[1][1] = true;
            assertEquals(2L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testBlockedStart() {
            boolean[][] blocked = grid(2, 2);
            blocked[0][0] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testBlockedEnd() {
            boolean[][] blocked = grid(2, 2);
            blocked[1][1] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testOneCell() {
            assertEquals(1L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(1, 1)));
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(new boolean[][] {{true}}));
        }

        @Test
        void testOneRowOrColumn() {
            assertEquals(1L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(1, 6)));
            assertEquals(1L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(6, 1)));
            boolean[][] row = grid(1, 6);
            row[0][3] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(row));
            boolean[][] column = grid(6, 1);
            column[2][0] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(column));
        }

        @Test
        void testStartIsSurrounded() {
            boolean[][] blocked = grid(3, 3);
            blocked[0][1] = true;
            blocked[1][0] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testOnlyOnePathLeft() {
            boolean[][] blocked = grid(3, 3);
            blocked[1][1] = true;
            blocked[0][2] = true;
            assertEquals(1L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testRectangularGrid() {
            boolean[][] blocked = grid(3, 4);
            blocked[1][1] = true;
            blocked[2][2] = true;
            assertEquals(2L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
            assertEquals(10L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(3, 4)));
        }

        @Test
        void testBlockedCellOnTheLastRowOrColumn() {
            boolean[][] blocked = grid(3, 3);
            blocked[2][1] = true;
            assertEquals(3L, MemoizationEasyChallenge.gridPathsWithBlocks(blocked));
        }

        @Test
        void testOriginalIsNotModified() {
            boolean[][] blocked = grid(3, 3);
            blocked[1][1] = true;
            MemoizationEasyChallenge.gridPathsWithBlocks(blocked);
            assertTrue(blocked[1][1]);
            assertFalse(blocked[0][0]);
            assertFalse(blocked[2][2]);
        }

        @Test
        void testBigGridWithoutBlocks() {
            assertEquals(32247603683100L, MemoizationEasyChallenge.gridPathsWithBlocks(grid(25, 25)));
        }

        @Test
        void testBigGridWithBlocks() {
            boolean[][] center = grid(25, 25);
            center[12][12] = true;
            assertEquals(24935144010764L, MemoizationEasyChallenge.gridPathsWithBlocks(center));
            boolean[][] wall = grid(25, 25);
            for (int c = 0; c < 24; c++) {
                wall[12][c] = true;
            }
            assertEquals(417225900L, MemoizationEasyChallenge.gridPathsWithBlocks(wall));
            boolean[][] closed = grid(25, 25);
            closed[1][0] = true;
            closed[0][1] = true;
            assertEquals(0L, MemoizationEasyChallenge.gridPathsWithBlocks(closed));
        }
    }

    // ==========================================================
    // CHALLENGE 14: maxTrianglePath
    // ==========================================================
    @Nested
    class MaxTrianglePathTests {

        @Test
        void testFourRows() {
            assertEquals(21, MemoizationEasyChallenge.maxTrianglePath(
                    new int[][] {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}}));
        }

        @Test
        void testOneRow() {
            assertEquals(5, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{5}}));
        }

        @Test
        void testTwoRows() {
            assertEquals(4, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{1}, {2, 3}}));
            assertEquals(11, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{1}, {10, 2}}));
            assertEquals(11, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{1}, {2, 10}}));
        }

        @Test
        void testAllZeros() {
            assertEquals(0, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{0}, {0, 0}}));
        }

        @Test
        void testAnotherTriangle() {
            assertEquals(27, MemoizationEasyChallenge.maxTrianglePath(
                    new int[][] {{5}, {9, 6}, {4, 6, 8}, {0, 7, 1, 5}}));
        }

        @Test
        void testOnlyTheLeftEdgeIsGood() {
            assertEquals(3, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{1}, {1, 0}, {1, 0, 0}}));
        }

        @Test
        void testOnlyTheRightEdgeIsGood() {
            assertEquals(30, MemoizationEasyChallenge.maxTrianglePath(new int[][] {{10}, {0, 10}, {0, 0, 10}}));
        }

        @Test
        void testTheBestPathBendsInTheMiddle() {
            assertEquals(105, MemoizationEasyChallenge.maxTrianglePath(
                    new int[][] {{1}, {2, 3}, {1, 100, 1}, {1, 1, 1, 1}}));
        }

        @Test
        void testGreedyIsNotEnough() {
            assertEquals(1002, MemoizationEasyChallenge.maxTrianglePath(
                    new int[][] {{1}, {2, 1}, {1, 1, 1000}}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] triangle = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
            MemoizationEasyChallenge.maxTrianglePath(triangle);
            assertArrayEquals(new int[][] {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}}, triangle);
        }

        @Test
        void testFortyRowsOfOnes() {
            int[][] triangle = new int[40][];
            for (int r = 0; r < 40; r++) {
                triangle[r] = new int[r + 1];
                java.util.Arrays.fill(triangle[r], 1);
            }
            assertEquals(40, MemoizationEasyChallenge.maxTrianglePath(triangle));
        }

        @Test
        void testFortyRowsWithGrowingRows() {
            int[][] triangle = new int[40][];
            for (int r = 0; r < 40; r++) {
                triangle[r] = new int[r + 1];
                java.util.Arrays.fill(triangle[r], r + 1);
            }
            assertEquals(820, MemoizationEasyChallenge.maxTrianglePath(triangle));
        }

        @Test
        void testFortyRowsWithDifferentValues() {
            int[][] triangle = new int[40][];
            for (int r = 0; r < 40; r++) {
                triangle[r] = new int[r + 1];
                for (int c = 0; c <= r; c++) {
                    triangle[r][c] = r * c % 10;
                }
            }
            assertEquals(222, MemoizationEasyChallenge.maxTrianglePath(triangle));
        }
    }

    // ==========================================================
    // CHALLENGE 15: longestCommonSubsequence
    // ==========================================================
    @Nested
    class LongestCommonSubsequenceTests {

        @Test
        void testSubsequence() {
            assertEquals(3, MemoizationEasyChallenge.longestCommonSubsequence("abcde", "ace"));
        }

        @Test
        void testNothingInCommon() {
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("abc", "def"));
        }

        @Test
        void testEmptyTexts() {
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("", "abc"));
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("abc", ""));
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("", ""));
        }

        @Test
        void testSameTexts() {
            assertEquals(3, MemoizationEasyChallenge.longestCommonSubsequence("abc", "abc"));
            assertEquals(1, MemoizationEasyChallenge.longestCommonSubsequence("a", "a"));
        }

        @Test
        void testClassicExamples() {
            assertEquals(4, MemoizationEasyChallenge.longestCommonSubsequence("AGGTAB", "GXTXAYB"));
            assertEquals(4, MemoizationEasyChallenge.longestCommonSubsequence("abcbdab", "bdcaba"));
        }

        @Test
        void testOrderMatters() {
            assertEquals(1, MemoizationEasyChallenge.longestCommonSubsequence("ab", "ba"));
            assertEquals(1, MemoizationEasyChallenge.longestCommonSubsequence("abc", "cba"));
        }

        @Test
        void testRepeatedCharacters() {
            assertEquals(2, MemoizationEasyChallenge.longestCommonSubsequence("aaaa", "aa"));
            assertEquals(2, MemoizationEasyChallenge.longestCommonSubsequence("aa", "aaaa"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("abc", "ABC"));
        }

        @Test
        void testTheArgumentsCanBeSwapped() {
            assertEquals(MemoizationEasyChallenge.longestCommonSubsequence("AGGTAB", "GXTXAYB"),
                    MemoizationEasyChallenge.longestCommonSubsequence("GXTXAYB", "AGGTAB"));
            assertEquals(MemoizationEasyChallenge.longestCommonSubsequence("abcbdab", "bdcaba"),
                    MemoizationEasyChallenge.longestCommonSubsequence("bdcaba", "abcbdab"));
        }

        @Test
        void testOneTextInsideTheOther() {
            assertEquals(3, MemoizationEasyChallenge.longestCommonSubsequence("xxabcxx", "abc"));
            assertEquals(3, MemoizationEasyChallenge.longestCommonSubsequence("abc", "xaxbxcx"));
        }

        @Test
        void testLongTextsWithTheSameCharacter() {
            assertEquals(100, MemoizationEasyChallenge.longestCommonSubsequence("a".repeat(100), "a".repeat(100)));
            assertEquals(0, MemoizationEasyChallenge.longestCommonSubsequence("a".repeat(100), "b".repeat(100)));
        }

        @Test
        void testLongTexts() {
            assertEquals(50, MemoizationEasyChallenge.longestCommonSubsequence("abcdefghij".repeat(10),
                    "acegikmoqs".repeat(10)));
            assertEquals(65, MemoizationEasyChallenge.longestCommonSubsequence("abc".repeat(33), "cba".repeat(33)));
        }
    }
}