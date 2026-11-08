package dev.perfectbogus.recursion.memoization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class RecursionMemoChallengeTest {

    private static int[][] bigTriangle() {
        int[][] triangle = new int[40][];
        for (int i = 0; i < 40; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = ((i * 7 + j * 3) % 21) - 10;
            }
        }
        return triangle;
    }

    private static int[] manyHouses() {
        int[] houses = new int[60];
        for (int i = 0; i < 60; i++) {
            houses[i] = (i * 37) % 11 + 1;
        }
        return houses;
    }

    // ==========================================================
    // CHALLENGE 1: fibonacci
    // ==========================================================
    @Nested
    class FibonacciTests {

        @Test
        void testFirstNumbers() {
            assertEquals(0, RecursionMemoChallenge.fibonacci(0));
            assertEquals(1, RecursionMemoChallenge.fibonacci(1));
            assertEquals(1, RecursionMemoChallenge.fibonacci(2));
            assertEquals(2, RecursionMemoChallenge.fibonacci(3));
        }

        @Test
        void testFive() {
            assertEquals(5, RecursionMemoChallenge.fibonacci(5));
        }

        @Test
        void testSeven() {
            assertEquals(13, RecursionMemoChallenge.fibonacci(7));
        }

        @Test
        void testTen() {
            assertEquals(55, RecursionMemoChallenge.fibonacci(10));
        }

        @Test
        void testTwenty() {
            assertEquals(6765, RecursionMemoChallenge.fibonacci(20));
        }

        @Test
        void testFifty() {
            assertEquals(12586269025L, RecursionMemoChallenge.fibonacci(50));
        }

        @Test
        void testSixtyFour() {
            assertEquals(10610209857723L, RecursionMemoChallenge.fibonacci(64));
        }

        @Test
        void testNinety() {
            assertEquals(2880067194370816120L, RecursionMemoChallenge.fibonacci(90));
        }

        @Test
        void testSameValueTwice() {
            assertEquals(RecursionMemoChallenge.fibonacci(30), RecursionMemoChallenge.fibonacci(30));
        }

        @Test
        void testBigValueAfterSmallOne() {
            assertEquals(55, RecursionMemoChallenge.fibonacci(10));
            assertEquals(12586269025L, RecursionMemoChallenge.fibonacci(50));
            assertEquals(55, RecursionMemoChallenge.fibonacci(10));
        }

        @Test
        void testEachNumberIsTheSumOfTheTwoBefore() {
            for (int n = 2; n <= 90; n++) {
                assertEquals(RecursionMemoChallenge.fibonacci(n - 1) + RecursionMemoChallenge.fibonacci(n - 2),
                        RecursionMemoChallenge.fibonacci(n));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 2: climbStairs
    // ==========================================================
    @Nested
    class ClimbStairsTests {

        @Test
        void testNoSteps() {
            assertEquals(1, RecursionMemoChallenge.climbStairs(0));
        }

        @Test
        void testOneStep() {
            assertEquals(1, RecursionMemoChallenge.climbStairs(1));
        }

        @Test
        void testTwoSteps() {
            assertEquals(2, RecursionMemoChallenge.climbStairs(2));
        }

        @Test
        void testThreeSteps() {
            assertEquals(3, RecursionMemoChallenge.climbStairs(3));
        }

        @Test
        void testFourSteps() {
            assertEquals(5, RecursionMemoChallenge.climbStairs(4));
        }

        @Test
        void testFiveSteps() {
            assertEquals(8, RecursionMemoChallenge.climbStairs(5));
        }

        @Test
        void testTenSteps() {
            assertEquals(89, RecursionMemoChallenge.climbStairs(10));
        }

        @Test
        void testFortySteps() {
            assertEquals(165580141L, RecursionMemoChallenge.climbStairs(40));
        }

        @Test
        void testFiftySteps() {
            assertEquals(20365011074L, RecursionMemoChallenge.climbStairs(50));
        }

        @Test
        void testEightyFiveSteps() {
            assertEquals(420196140727489673L, RecursionMemoChallenge.climbStairs(85));
        }

        @Test
        void testSmallValueAfterBigOne() {
            assertEquals(20365011074L, RecursionMemoChallenge.climbStairs(50));
            assertEquals(8, RecursionMemoChallenge.climbStairs(5));
        }
    }

    // ==========================================================
    // CHALLENGE 3: tribonacci
    // ==========================================================
    @Nested
    class TribonacciTests {

        @Test
        void testFirstThreeNumbers() {
            assertEquals(0, RecursionMemoChallenge.tribonacci(0));
            assertEquals(1, RecursionMemoChallenge.tribonacci(1));
            assertEquals(1, RecursionMemoChallenge.tribonacci(2));
        }

        @Test
        void testNextNumbers() {
            assertEquals(2, RecursionMemoChallenge.tribonacci(3));
            assertEquals(4, RecursionMemoChallenge.tribonacci(4));
            assertEquals(7, RecursionMemoChallenge.tribonacci(5));
        }

        @Test
        void testSix() {
            assertEquals(13, RecursionMemoChallenge.tribonacci(6));
        }

        @Test
        void testTen() {
            assertEquals(149, RecursionMemoChallenge.tribonacci(10));
        }

        @Test
        void testThirty() {
            assertEquals(29249425L, RecursionMemoChallenge.tribonacci(30));
        }

        @Test
        void testFortyFive() {
            assertEquals(272809183135L, RecursionMemoChallenge.tribonacci(45));
        }

        @Test
        void testSixty() {
            assertEquals(2544489349890656L, RecursionMemoChallenge.tribonacci(60));
        }

        @Test
        void testSmallValueAfterBigOne() {
            assertEquals(29249425L, RecursionMemoChallenge.tribonacci(30));
            assertEquals(13, RecursionMemoChallenge.tribonacci(6));
        }

        @Test
        void testEachNumberIsTheSumOfTheThreeBefore() {
            for (int n = 3; n <= 60; n++) {
                assertEquals(RecursionMemoChallenge.tribonacci(n - 1) + RecursionMemoChallenge.tribonacci(n - 2)
                        + RecursionMemoChallenge.tribonacci(n - 3), RecursionMemoChallenge.tribonacci(n));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 4: gridPaths
    // ==========================================================
    @Nested
    class GridPathsTests {

        @Test
        void testOneCell() {
            assertEquals(1, RecursionMemoChallenge.gridPaths(1, 1));
        }

        @Test
        void testTwoByTwo() {
            assertEquals(2, RecursionMemoChallenge.gridPaths(2, 2));
        }

        @Test
        void testThreeByThree() {
            assertEquals(6, RecursionMemoChallenge.gridPaths(3, 3));
        }

        @Test
        void testTwoRowsFiveColumns() {
            assertEquals(5, RecursionMemoChallenge.gridPaths(2, 5));
        }

        @Test
        void testOneRow() {
            assertEquals(1, RecursionMemoChallenge.gridPaths(1, 5));
            assertEquals(1, RecursionMemoChallenge.gridPaths(1, 30));
        }

        @Test
        void testOneColumn() {
            assertEquals(1, RecursionMemoChallenge.gridPaths(5, 1));
            assertEquals(1, RecursionMemoChallenge.gridPaths(30, 1));
        }

        @Test
        void testThreeRowsFourColumns() {
            assertEquals(10, RecursionMemoChallenge.gridPaths(3, 4));
        }

        @Test
        void testFourRowsThreeColumns() {
            assertEquals(10, RecursionMemoChallenge.gridPaths(4, 3));
        }

        @Test
        void testSevenRowsThreeColumns() {
            assertEquals(28, RecursionMemoChallenge.gridPaths(7, 3));
        }

        @Test
        void testFiveByFive() {
            assertEquals(70, RecursionMemoChallenge.gridPaths(5, 5));
        }

        @Test
        void testTenByTen() {
            assertEquals(48620, RecursionMemoChallenge.gridPaths(10, 10));
        }

        @Test
        void testEighteenByEighteen() {
            assertEquals(2333606220L, RecursionMemoChallenge.gridPaths(18, 18));
        }

        @Test
        void testThirtyByThirty() {
            assertEquals(30067266499541040L, RecursionMemoChallenge.gridPaths(30, 30));
        }

        @Test
        void testThirtyRowsTwoColumns() {
            assertEquals(30, RecursionMemoChallenge.gridPaths(30, 2));
        }

        @Test
        void testDifferentSizesOneAfterAnother() {
            assertEquals(6, RecursionMemoChallenge.gridPaths(3, 3));
            assertEquals(10, RecursionMemoChallenge.gridPaths(3, 4));
            assertEquals(2, RecursionMemoChallenge.gridPaths(2, 2));
            assertEquals(6, RecursionMemoChallenge.gridPaths(3, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 5: binomial
    // ==========================================================
    @Nested
    class BinomialTests {

        @Test
        void testZeroOfZero() {
            assertEquals(1, RecursionMemoChallenge.binomial(0, 0));
        }

        @Test
        void testSmallRows() {
            assertEquals(1, RecursionMemoChallenge.binomial(1, 0));
            assertEquals(1, RecursionMemoChallenge.binomial(1, 1));
            assertEquals(2, RecursionMemoChallenge.binomial(2, 1));
        }

        @Test
        void testFiveChooseTwo() {
            assertEquals(10, RecursionMemoChallenge.binomial(5, 2));
        }

        @Test
        void testChooseNone() {
            assertEquals(1, RecursionMemoChallenge.binomial(4, 0));
            assertEquals(1, RecursionMemoChallenge.binomial(60, 0));
        }

        @Test
        void testChooseAll() {
            assertEquals(1, RecursionMemoChallenge.binomial(6, 6));
            assertEquals(1, RecursionMemoChallenge.binomial(60, 60));
        }

        @Test
        void testSixChooseThree() {
            assertEquals(20, RecursionMemoChallenge.binomial(6, 3));
        }

        @Test
        void testTenChooseThree() {
            assertEquals(120, RecursionMemoChallenge.binomial(10, 3));
        }

        @Test
        void testTenChooseSeven() {
            assertEquals(120, RecursionMemoChallenge.binomial(10, 7));
        }

        @Test
        void testTenChooseFive() {
            assertEquals(252, RecursionMemoChallenge.binomial(10, 5));
        }

        @Test
        void testTwentyChooseTen() {
            assertEquals(184756, RecursionMemoChallenge.binomial(20, 10));
        }

        @Test
        void testFortyChooseTwenty() {
            assertEquals(137846528820L, RecursionMemoChallenge.binomial(40, 20));
        }

        @Test
        void testSixtyChooseThirty() {
            assertEquals(118264581564861424L, RecursionMemoChallenge.binomial(60, 30));
        }

        @Test
        void testSixtyChooseOneAndFiftyNine() {
            assertEquals(60, RecursionMemoChallenge.binomial(60, 1));
            assertEquals(60, RecursionMemoChallenge.binomial(60, 59));
        }

        @Test
        void testDifferentValuesOneAfterAnother() {
            assertEquals(10, RecursionMemoChallenge.binomial(5, 2));
            assertEquals(20, RecursionMemoChallenge.binomial(6, 3));
            assertEquals(10, RecursionMemoChallenge.binomial(5, 2));
            assertEquals(120, RecursionMemoChallenge.binomial(10, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 6: catalan
    // ==========================================================
    @Nested
    class CatalanTests {

        @Test
        void testFirstTwoNumbers() {
            assertEquals(1, RecursionMemoChallenge.catalan(0));
            assertEquals(1, RecursionMemoChallenge.catalan(1));
        }

        @Test
        void testTwo() {
            assertEquals(2, RecursionMemoChallenge.catalan(2));
        }

        @Test
        void testThree() {
            assertEquals(5, RecursionMemoChallenge.catalan(3));
        }

        @Test
        void testFour() {
            assertEquals(14, RecursionMemoChallenge.catalan(4));
        }

        @Test
        void testFive() {
            assertEquals(42, RecursionMemoChallenge.catalan(5));
        }

        @Test
        void testSix() {
            assertEquals(132, RecursionMemoChallenge.catalan(6));
        }

        @Test
        void testEight() {
            assertEquals(1430, RecursionMemoChallenge.catalan(8));
        }

        @Test
        void testTen() {
            assertEquals(16796, RecursionMemoChallenge.catalan(10));
        }

        @Test
        void testFifteen() {
            assertEquals(9694845, RecursionMemoChallenge.catalan(15));
        }

        @Test
        void testTwenty() {
            assertEquals(6564120420L, RecursionMemoChallenge.catalan(20));
        }

        @Test
        void testTwentyFive() {
            assertEquals(4861946401452L, RecursionMemoChallenge.catalan(25));
        }

        @Test
        void testThirty() {
            assertEquals(3814986502092304L, RecursionMemoChallenge.catalan(30));
        }

        @Test
        void testSmallValueAfterBigOne() {
            assertEquals(6564120420L, RecursionMemoChallenge.catalan(20));
            assertEquals(14, RecursionMemoChallenge.catalan(4));
        }
    }

    // ==========================================================
    // CHALLENGE 7: maxTrianglePath
    // ==========================================================
    @Nested
    class MaxTrianglePathTests {

        @Test
        void testExampleTriangle() {
            assertEquals(13, RecursionMemoChallenge.maxTrianglePath(new int[][] {{2}, {3, 4}, {6, 5, 7}}));
        }

        @Test
        void testOneNumber() {
            assertEquals(5, RecursionMemoChallenge.maxTrianglePath(new int[][] {{5}}));
        }

        @Test
        void testOneNegativeNumber() {
            assertEquals(-5, RecursionMemoChallenge.maxTrianglePath(new int[][] {{-5}}));
        }

        @Test
        void testTwoRows() {
            assertEquals(4, RecursionMemoChallenge.maxTrianglePath(new int[][] {{1}, {2, 3}}));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-3, RecursionMemoChallenge.maxTrianglePath(new int[][] {{-1}, {-5, -2}}));
        }

        @Test
        void testAllNegativeTwoRows() {
            assertEquals(-1, RecursionMemoChallenge.maxTrianglePath(new int[][] {{1}, {-2, -3}}));
        }

        @Test
        void testFourRows() {
            assertEquals(23, RecursionMemoChallenge.maxTrianglePath(
                    new int[][] {{3}, {7, 4}, {2, 4, 6}, {8, 5, 9, 3}}));
        }

        @Test
        void testAllZeros() {
            assertEquals(0, RecursionMemoChallenge.maxTrianglePath(new int[][] {{0}, {0, 0}}));
        }

        @Test
        void testBestPathGoesAlwaysLeft() {
            assertEquals(102, RecursionMemoChallenge.maxTrianglePath(
                    new int[][] {{1}, {1, 1}, {100, 1, 1}}));
        }

        @Test
        void testBestPathGoesAlwaysRight() {
            assertEquals(102, RecursionMemoChallenge.maxTrianglePath(
                    new int[][] {{1}, {1, 1}, {1, 1, 100}}));
        }

        @Test
        void testGreedyChoiceIsNotTheBest() {
            assertEquals(7, RecursionMemoChallenge.maxTrianglePath(new int[][] {{1}, {5, 1}, {1, 1, 1}}));
        }

        @Test
        void testBestPathChangesSide() {
            assertEquals(-6, RecursionMemoChallenge.maxTrianglePath(new int[][] {{-5}, {-1, -9}}));
        }

        @Test
        void testAllOnes() {
            assertEquals(3, RecursionMemoChallenge.maxTrianglePath(new int[][] {{1}, {1, 1}, {1, 1, 1}}));
        }

        @Test
        void testFortyRows() {
            assertEquals(113, RecursionMemoChallenge.maxTrianglePath(bigTriangle()));
        }

        @Test
        void testFortyRowsOfOnes() {
            int[][] triangle = new int[40][];
            for (int i = 0; i < 40; i++) {
                triangle[i] = new int[i + 1];
                java.util.Arrays.fill(triangle[i], 1);
            }
            assertEquals(40, RecursionMemoChallenge.maxTrianglePath(triangle));
        }

        @Test
        void testDifferentTrianglesOneAfterAnother() {
            assertEquals(13, RecursionMemoChallenge.maxTrianglePath(new int[][] {{2}, {3, 4}, {6, 5, 7}}));
            assertEquals(5, RecursionMemoChallenge.maxTrianglePath(new int[][] {{5}}));
            assertEquals(4, RecursionMemoChallenge.maxTrianglePath(new int[][] {{1}, {2, 3}}));
        }

        @Test
        void testTriangleIsNotModified() {
            int[][] triangle = {{2}, {3, 4}, {6, 5, 7}};
            RecursionMemoChallenge.maxTrianglePath(triangle);
            assertArrayEquals(new int[][] {{2}, {3, 4}, {6, 5, 7}}, triangle);
        }
    }

    // ==========================================================
    // CHALLENGE 8: maxRobbery
    // ==========================================================
    @Nested
    class MaxRobberyTests {

        @Test
        void testExampleOne() {
            assertEquals(4, RecursionMemoChallenge.maxRobbery(new int[] {1, 2, 3, 1}));
        }

        @Test
        void testExampleTwo() {
            assertEquals(12, RecursionMemoChallenge.maxRobbery(new int[] {2, 7, 9, 3, 1}));
        }

        @Test
        void testOneHouse() {
            assertEquals(5, RecursionMemoChallenge.maxRobbery(new int[] {5}));
        }

        @Test
        void testNoHouses() {
            assertEquals(0, RecursionMemoChallenge.maxRobbery(new int[] {}));
        }

        @Test
        void testTwoHouses() {
            assertEquals(2, RecursionMemoChallenge.maxRobbery(new int[] {1, 2}));
            assertEquals(2, RecursionMemoChallenge.maxRobbery(new int[] {2, 1}));
        }

        @Test
        void testTakingTheTwoAtTheEnds() {
            assertEquals(6, RecursionMemoChallenge.maxRobbery(new int[] {3, 1, 3}));
        }

        @Test
        void testAllZeros() {
            assertEquals(0, RecursionMemoChallenge.maxRobbery(new int[] {0, 0, 0}));
        }

        @Test
        void testAllEqual() {
            assertEquals(10, RecursionMemoChallenge.maxRobbery(new int[] {5, 5, 5}));
        }

        @Test
        void testSkipTwoHousesInARow() {
            assertEquals(200, RecursionMemoChallenge.maxRobbery(new int[] {1, 100, 1, 1, 100}));
        }

        @Test
        void testEndsAreBetterThanTheMiddle() {
            assertEquals(20, RecursionMemoChallenge.maxRobbery(new int[] {10, 1, 1, 10}));
        }

        @Test
        void testEveryThirdHouse() {
            assertEquals(12, RecursionMemoChallenge.maxRobbery(new int[] {4, 1, 1, 4, 1, 1, 4}));
        }

        @Test
        void testTwoOptionsWithTheSameTotal() {
            assertEquals(4, RecursionMemoChallenge.maxRobbery(new int[] {2, 1, 1, 2}));
        }

        @Test
        void testSixtyHouses() {
            assertEquals(206, RecursionMemoChallenge.maxRobbery(manyHouses()));
        }

        @Test
        void testSixtyHousesAllOnes() {
            int[] houses = new int[60];
            java.util.Arrays.fill(houses, 1);
            assertEquals(30, RecursionMemoChallenge.maxRobbery(houses));
        }

        @Test
        void testDifferentStreetsOneAfterAnother() {
            assertEquals(4, RecursionMemoChallenge.maxRobbery(new int[] {1, 2, 3, 1}));
            assertEquals(12, RecursionMemoChallenge.maxRobbery(new int[] {2, 7, 9, 3, 1}));
            assertEquals(2, RecursionMemoChallenge.maxRobbery(new int[] {1, 2}));
        }

        @Test
        void testArrayIsNotModified() {
            int[] houses = {2, 7, 9, 3, 1};
            RecursionMemoChallenge.maxRobbery(houses);
            assertArrayEquals(new int[] {2, 7, 9, 3, 1}, houses);
        }
    }

    // ==========================================================
    // CHALLENGE 9: minCoins
    // ==========================================================
    @Nested
    class MinCoinsTests {

        @Test
        void testExampleOne() {
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {1, 2, 5}, 11));
        }

        @Test
        void testImpossible() {
            assertEquals(-1, RecursionMemoChallenge.minCoins(new int[] {2}, 3));
        }

        @Test
        void testGreedyIsNotTheBest() {
            assertEquals(2, RecursionMemoChallenge.minCoins(new int[] {1, 5, 6}, 10));
        }

        @Test
        void testGreedyIsNotTheBestAgain() {
            assertEquals(2, RecursionMemoChallenge.minCoins(new int[] {1, 3, 4}, 6));
        }

        @Test
        void testZeroAmount() {
            assertEquals(0, RecursionMemoChallenge.minCoins(new int[] {3}, 0));
            assertEquals(0, RecursionMemoChallenge.minCoins(new int[] {10}, 0));
        }

        @Test
        void testOnlyCoinsOfOne() {
            assertEquals(7, RecursionMemoChallenge.minCoins(new int[] {1}, 7));
        }

        @Test
        void testExactlyOneCoin() {
            assertEquals(1, RecursionMemoChallenge.minCoins(new int[] {2, 5, 10}, 5));
        }

        @Test
        void testSameCoinTwice() {
            assertEquals(2, RecursionMemoChallenge.minCoins(new int[] {5}, 10));
        }

        @Test
        void testImpossibleWithOneCoin() {
            assertEquals(-1, RecursionMemoChallenge.minCoins(new int[] {5}, 11));
        }

        @Test
        void testImpossibleBecauseAmountIsSmallerThanEveryCoin() {
            assertEquals(-1, RecursionMemoChallenge.minCoins(new int[] {2, 5}, 1));
        }

        @Test
        void testImpossibleWithTwoCoins() {
            assertEquals(-1, RecursionMemoChallenge.minCoins(new int[] {3, 7}, 11));
        }

        @Test
        void testTwoCoins() {
            assertEquals(2, RecursionMemoChallenge.minCoins(new int[] {2, 5}, 7));
        }

        @Test
        void testThreeCoinsOfTwoAndThree() {
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {2, 3}, 7));
        }

        @Test
        void testCoinsInAnyOrder() {
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {5, 2, 1}, 11));
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {2, 5, 1}, 11));
        }

        @Test
        void testBigAmount() {
            assertEquals(60, RecursionMemoChallenge.minCoins(new int[] {1, 2, 5}, 300));
        }

        @Test
        void testBigAmountWithBigCoins() {
            assertEquals(24, RecursionMemoChallenge.minCoins(new int[] {7, 13}, 300));
            assertEquals(23, RecursionMemoChallenge.minCoins(new int[] {7, 13}, 299));
        }

        @Test
        void testBigAmountWithTwoCoins() {
            assertEquals(35, RecursionMemoChallenge.minCoins(new int[] {4, 9}, 300));
            assertEquals(12, RecursionMemoChallenge.minCoins(new int[] {10, 25}, 300));
            assertEquals(30, RecursionMemoChallenge.minCoins(new int[] {9, 10}, 300));
        }

        @Test
        void testCoinsOfTheUsDollar() {
            assertEquals(16, RecursionMemoChallenge.minCoins(new int[] {1, 5, 10, 25}, 289));
        }

        @Test
        void testHundredWithThreeCoins() {
            assertEquals(20, RecursionMemoChallenge.minCoins(new int[] {1, 2, 5}, 100));
        }

        @Test
        void testDifferentCoinsOneAfterAnother() {
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {1, 2, 5}, 11));
            assertEquals(-1, RecursionMemoChallenge.minCoins(new int[] {2}, 11));
            assertEquals(2, RecursionMemoChallenge.minCoins(new int[] {1, 5, 6}, 10));
            assertEquals(3, RecursionMemoChallenge.minCoins(new int[] {1, 2, 5}, 11));
        }

        @Test
        void testArrayIsNotModified() {
            int[] coins = {5, 2, 1};
            RecursionMemoChallenge.minCoins(coins, 11);
            assertArrayEquals(new int[] {5, 2, 1}, coins);
        }
    }

    // ==========================================================
    // CHALLENGE 10: noConsecutiveOnes
    // ==========================================================
    @Nested
    class NoConsecutiveOnesTests {

        @Test
        void testEmptyText() {
            assertEquals(1, RecursionMemoChallenge.noConsecutiveOnes(0));
        }

        @Test
        void testLengthOne() {
            assertEquals(2, RecursionMemoChallenge.noConsecutiveOnes(1));
        }

        @Test
        void testLengthTwo() {
            assertEquals(3, RecursionMemoChallenge.noConsecutiveOnes(2));
        }

        @Test
        void testLengthThree() {
            assertEquals(5, RecursionMemoChallenge.noConsecutiveOnes(3));
        }

        @Test
        void testLengthFour() {
            assertEquals(8, RecursionMemoChallenge.noConsecutiveOnes(4));
        }

        @Test
        void testLengthFive() {
            assertEquals(13, RecursionMemoChallenge.noConsecutiveOnes(5));
        }

        @Test
        void testLengthTen() {
            assertEquals(144, RecursionMemoChallenge.noConsecutiveOnes(10));
        }

        @Test
        void testLengthThirty() {
            assertEquals(2178309L, RecursionMemoChallenge.noConsecutiveOnes(30));
        }

        @Test
        void testLengthSixty() {
            assertEquals(4052739537881L, RecursionMemoChallenge.noConsecutiveOnes(60));
        }

        @Test
        void testLengthEighty() {
            assertEquals(61305790721611591L, RecursionMemoChallenge.noConsecutiveOnes(80));
        }

        @Test
        void testSmallValueAfterBigOne() {
            assertEquals(4052739537881L, RecursionMemoChallenge.noConsecutiveOnes(60));
            assertEquals(8, RecursionMemoChallenge.noConsecutiveOnes(4));
        }

        @Test
        void testMatchesCountingByHandForSmallLengths() {
            for (int length = 0; length <= 12; length++) {
                int count = 0;
                for (int bits = 0; bits < (1 << length); bits++) {
                    if ((bits & (bits >> 1)) == 0) {
                        count++;
                    }
                }
                assertEquals(count, RecursionMemoChallenge.noConsecutiveOnes(length));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 11: waysToClimb
    // ==========================================================
    @Nested
    class WaysToClimbTests {

        @Test
        void testOneAndTwoSteps() {
            assertEquals(5, RecursionMemoChallenge.waysToClimb(4, new int[] {1, 2}));
        }

        @Test
        void testOneTwoAndThreeSteps() {
            assertEquals(4, RecursionMemoChallenge.waysToClimb(3, new int[] {1, 2, 3}));
        }

        @Test
        void testImpossible() {
            assertEquals(0, RecursionMemoChallenge.waysToClimb(5, new int[] {2}));
        }

        @Test
        void testNoStepsToClimb() {
            assertEquals(1, RecursionMemoChallenge.waysToClimb(0, new int[] {3}));
        }

        @Test
        void testOneStepToClimb() {
            assertEquals(1, RecursionMemoChallenge.waysToClimb(1, new int[] {1, 2}));
        }

        @Test
        void testOnlyOneKindOfMove() {
            assertEquals(1, RecursionMemoChallenge.waysToClimb(2, new int[] {2}));
            assertEquals(1, RecursionMemoChallenge.waysToClimb(6, new int[] {2}));
            assertEquals(1, RecursionMemoChallenge.waysToClimb(9, new int[] {3}));
        }

        @Test
        void testOnlyOneKindOfMoveThatDoesNotFit() {
            assertEquals(0, RecursionMemoChallenge.waysToClimb(7, new int[] {2}));
            assertEquals(0, RecursionMemoChallenge.waysToClimb(8, new int[] {3}));
            assertEquals(0, RecursionMemoChallenge.waysToClimb(50, new int[] {7}));
        }

        @Test
        void testOrderMatters() {
            assertEquals(2, RecursionMemoChallenge.waysToClimb(8, new int[] {3, 5}));
            assertEquals(1, RecursionMemoChallenge.waysToClimb(10, new int[] {3, 5}));
        }

        @Test
        void testTenStepsWithThreeMoves() {
            assertEquals(274, RecursionMemoChallenge.waysToClimb(10, new int[] {1, 2, 3}));
        }

        @Test
        void testTwelveStepsWithThreeMoves() {
            assertEquals(927, RecursionMemoChallenge.waysToClimb(12, new int[] {3, 2, 1}));
        }

        @Test
        void testTwentyStepsWithThreeMoves() {
            assertEquals(121415, RecursionMemoChallenge.waysToClimb(20, new int[] {1, 2, 3}));
        }

        @Test
        void testMovesInAnyOrder() {
            assertEquals(194, RecursionMemoChallenge.waysToClimb(30, new int[] {5, 2}));
            assertEquals(194, RecursionMemoChallenge.waysToClimb(30, new int[] {2, 5}));
        }

        @Test
        void testFiftyStepsWithTwoMoves() {
            assertEquals(20365011074L, RecursionMemoChallenge.waysToClimb(50, new int[] {1, 2}));
        }

        @Test
        void testFiftyStepsWithFourMoves() {
            assertEquals(100808458960497L, RecursionMemoChallenge.waysToClimb(50, new int[] {1, 2, 3, 4}));
        }

        @Test
        void testDifferentMovesOneAfterAnother() {
            assertEquals(5, RecursionMemoChallenge.waysToClimb(4, new int[] {1, 2}));
            assertEquals(0, RecursionMemoChallenge.waysToClimb(4, new int[] {3}));
            assertEquals(7, RecursionMemoChallenge.waysToClimb(4, new int[] {1, 2, 3}));
            assertEquals(5, RecursionMemoChallenge.waysToClimb(4, new int[] {1, 2}));
        }

        @Test
        void testArrayIsNotModified() {
            int[] steps = {3, 2, 1};
            RecursionMemoChallenge.waysToClimb(5, steps);
            assertArrayEquals(new int[] {3, 2, 1}, steps);
        }
    }
}