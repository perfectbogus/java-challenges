package dev.perfectbogus.sliding.window;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowChallengeTest {

    private static int[] filled(int length, int value) {
        int[] array = new int[length];
        Arrays.fill(array, value);
        return array;
    }

    // ==========================================================
    // CHALLENGE 1: maxSumOfSize
    // ==========================================================
    @Nested
    class MaxSumOfSizeTests {

        @Test
        void testMixedNumbers() {
            assertEquals(9L, SlidingWindowChallenge.maxSumOfSize(new int[] {1, 3, -2, 5, 4}, 2));
        }

        @Test
        void testAllNegative() {
            assertEquals(-3L, SlidingWindowChallenge.maxSumOfSize(new int[] {-3, -1, -2}, 2));
        }

        @Test
        void testOneNumber() {
            assertEquals(5L, SlidingWindowChallenge.maxSumOfSize(new int[] {5}, 1));
        }

        @Test
        void testWholeArray() {
            assertEquals(6L, SlidingWindowChallenge.maxSumOfSize(new int[] {1, 2, 3}, 3));
        }

        @Test
        void testWholeArrayWithNegatives() {
            assertEquals(-6L, SlidingWindowChallenge.maxSumOfSize(new int[] {-1, -2, -3}, 3));
        }

        @Test
        void testSizeOneGivesTheGreatestNumber() {
            assertEquals(9L, SlidingWindowChallenge.maxSumOfSize(new int[] {3, 9, 2}, 1));
        }

        @Test
        void testSizeOneAllNegative() {
            assertEquals(-2L, SlidingWindowChallenge.maxSumOfSize(new int[] {-5, -2, -9}, 1));
        }

        @Test
        void testBestGroupAtTheStart() {
            assertEquals(18L, SlidingWindowChallenge.maxSumOfSize(new int[] {9, 9, 1, 1}, 2));
        }

        @Test
        void testBestGroupAtTheEnd() {
            assertEquals(18L, SlidingWindowChallenge.maxSumOfSize(new int[] {1, 1, 9, 9}, 2));
        }

        @Test
        void testBestGroupInTheMiddle() {
            assertEquals(18L, SlidingWindowChallenge.maxSumOfSize(new int[] {1, 9, 9, 1}, 2));
        }

        @Test
        void testNumbersLeavingTheGroupAreRemoved() {
            assertEquals(40L, SlidingWindowChallenge.maxSumOfSize(new int[] {10, -20, 30, -40, 50}, 3));
        }

        @Test
        void testSmallPositiveResult() {
            assertEquals(3L, SlidingWindowChallenge.maxSumOfSize(new int[] {4, -1, 2, -1}, 2));
        }

        @Test
        void testZeros() {
            assertEquals(0L, SlidingWindowChallenge.maxSumOfSize(new int[] {0, 0, 0}, 2));
        }

        @Test
        void testSumBiggerThanAnInt() {
            assertEquals(3_000_000_000L,
                    SlidingWindowChallenge.maxSumOfSize(new int[] {1_000_000_000, 1_000_000_000, 1_000_000_000,
                            -1_000_000_000}, 3));
        }

        @Test
        void testSumSmallerThanAnInt() {
            assertEquals(-2_000_000_000L,
                    SlidingWindowChallenge.maxSumOfSize(
                            new int[] {-1_000_000_000, -1_000_000_000, -1_000_000_000}, 2));
        }

        @Test
        void testVeryNegativeSum() {
            assertEquals(-3_000_000_000L, SlidingWindowChallenge.maxSumOfSize(
                    new int[] {-1_000_000_000, -1_000_000_000, -1_000_000_000}, 3));
        }

        @Test
        void testBigArrayOfOnes() {
            assertEquals(1_000_000L, SlidingWindowChallenge.maxSumOfSize(filled(2_000_000, 1), 1_000_000));
        }

        @Test
        void testBigArrayWithPattern() {
            int[] nums = new int[2_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (i % 7) - 3;
            }
            assertEquals(3L, SlidingWindowChallenge.maxSumOfSize(nums, 1_000_000));
        }

        @Test
        void testArrayIsNotModified() {
            int[] nums = {1, 3, -2, 5, 4};
            SlidingWindowChallenge.maxSumOfSize(nums, 2);
            assertArrayEquals(new int[] {1, 3, -2, 5, 4}, nums);
        }
    }

    // ==========================================================
    // CHALLENGE 2: windowSums
    // ==========================================================
    @Nested
    class WindowSumsTests {

        @Test
        void testSizeTwo() {
            assertArrayEquals(new int[] {3, 5, 7}, SlidingWindowChallenge.windowSums(new int[] {1, 2, 3, 4}, 2));
        }

        @Test
        void testWholeArray() {
            assertArrayEquals(new int[] {6}, SlidingWindowChallenge.windowSums(new int[] {1, 2, 3}, 3));
        }

        @Test
        void testSizeOne() {
            assertArrayEquals(new int[] {4, 5}, SlidingWindowChallenge.windowSums(new int[] {4, 5}, 1));
        }

        @Test
        void testSumsOfZero() {
            assertArrayEquals(new int[] {0, 0}, SlidingWindowChallenge.windowSums(new int[] {-1, 1, -1}, 2));
        }

        @Test
        void testOneNumber() {
            assertArrayEquals(new int[] {5}, SlidingWindowChallenge.windowSums(new int[] {5}, 1));
        }

        @Test
        void testSizeThree() {
            assertArrayEquals(new int[] {6, 9, 12},
                    SlidingWindowChallenge.windowSums(new int[] {1, 2, 3, 4, 5}, 3));
        }

        @Test
        void testSizeFour() {
            assertArrayEquals(new int[] {10, 14, 18},
                    SlidingWindowChallenge.windowSums(new int[] {1, 2, 3, 4, 5, 6}, 4));
        }

        @Test
        void testAlternatingNumbers() {
            assertArrayEquals(new int[] {0, 0, 0},
                    SlidingWindowChallenge.windowSums(new int[] {10, -10, 10, -10}, 2));
        }

        @Test
        void testEqualNumbers() {
            assertArrayEquals(new int[] {4, 4, 4}, SlidingWindowChallenge.windowSums(new int[] {2, 2, 2, 2}, 2));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[] {-10, -10},
                    SlidingWindowChallenge.windowSums(new int[] {-5, -5, -5}, 2));
        }

        @Test
        void testGrowingAndShrinkingSums() {
            assertArrayEquals(new int[] {10, 3, 12, 0},
                    SlidingWindowChallenge.windowSums(new int[] {9, 1, 2, 10, -10}, 2));
        }

        @Test
        void testBigNegativeSums() {
            assertArrayEquals(new int[] {-10_000_000, -10_000_000},
                    SlidingWindowChallenge.windowSums(new int[] {-5_000_000, -5_000_000, -5_000_000}, 2));
        }

        @Test
        void testResultLength() {
            assertEquals(7, SlidingWindowChallenge.windowSums(new int[10], 4).length);
        }

        @Test
        void testBigArray() {
            int[] result = SlidingWindowChallenge.windowSums(filled(1_000_000, 1), 500_000);
            assertEquals(500_001, result.length);
            assertEquals(500_000, result[0]);
            assertEquals(500_000, result[250_000]);
            assertEquals(500_000, result[500_000]);
        }

        @Test
        void testBigArrayWithPattern() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i % 2 == 0 ? 1 : 0;
            }
            int[] result = SlidingWindowChallenge.windowSums(nums, 500_000);
            assertEquals(500_001, result.length);
            assertEquals(250_000, result[0]);
            assertEquals(250_000, result[1]);
            assertEquals(250_000, result[500_000]);
        }

        @Test
        void testArrayIsNotModified() {
            int[] nums = {1, 2, 3, 4};
            SlidingWindowChallenge.windowSums(nums, 2);
            assertArrayEquals(new int[] {1, 2, 3, 4}, nums);
        }
    }

    // ==========================================================
    // CHALLENGE 3: countWindowsWithSumAtLeast
    // ==========================================================
    @Nested
    class CountWindowsWithSumAtLeastTests {

        @Test
        void testSomeWindows() {
            assertEquals(2, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {1, 2, 3, 4}, 2, 5));
        }

        @Test
        void testNoWindows() {
            assertEquals(0, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {1, 1, 1}, 2, 3));
        }

        @Test
        void testOneNumber() {
            assertEquals(1, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {5}, 1, 5));
        }

        @Test
        void testNegativeTarget() {
            assertEquals(1, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {-1, -2}, 1, -1));
        }

        @Test
        void testAllWindows() {
            assertEquals(2, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {3, 3, 3}, 2, 6));
        }

        @Test
        void testSizeOne() {
            assertEquals(2, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {1, 5, 3, 5}, 1, 5));
        }

        @Test
        void testWholeArrayExactSum() {
            assertEquals(1, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {1, 2, 3}, 3, 6));
        }

        @Test
        void testWholeArrayTooSmall() {
            assertEquals(0, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {1, 2, 3}, 3, 7));
        }

        @Test
        void testTheSumEqualToTheTargetCounts() {
            assertEquals(4, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {5, -5, 5, -5, 5}, 2, 0));
        }

        @Test
        void testWindowsAtTheTargetLimit() {
            assertEquals(4, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {2, 9, 1, 9, 2}, 2, 10));
            assertEquals(2, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {2, 9, 1, 9, 2}, 2, 11));
        }

        @Test
        void testZeros() {
            assertEquals(3, SlidingWindowChallenge.countWindowsWithSumAtLeast(new int[] {0, 0, 0}, 1, 0));
        }

        @Test
        void testQualifyingWindowsAtTheEnds() {
            assertEquals(2, SlidingWindowChallenge.countWindowsWithSumAtLeast(
                    new int[] {9, 9, 0, 0, 9, 9}, 2, 18));
        }

        @Test
        void testBigArrayAllWindows() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i % 1001;
            }
            assertEquals(500_001, SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 500_000, 249_874_750));
            assertEquals(499_501, SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 500_000, 249_874_751));
            assertEquals(250_251, SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 500_000, 250_000_000));
            assertEquals(500, SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 500_000, 250_125_250));
            assertEquals(0, SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 500_000, 250_125_251));
        }

        @Test
        void testArrayIsNotModified() {
            int[] nums = {1, 2, 3, 4};
            SlidingWindowChallenge.countWindowsWithSumAtLeast(nums, 2, 5);
            assertArrayEquals(new int[] {1, 2, 3, 4}, nums);
        }
    }

    // ==========================================================
    // CHALLENGE 4: maxVowelsInWindow
    // ==========================================================
    @Nested
    class MaxVowelsInWindowTests {

        @Test
        void testThreeVowelsInARow() {
            assertEquals(3, SlidingWindowChallenge.maxVowelsInWindow("abciiidef", 3));
        }

        @Test
        void testOnlyVowels() {
            assertEquals(2, SlidingWindowChallenge.maxVowelsInWindow("aeiou", 2));
        }

        @Test
        void testNoVowels() {
            assertEquals(0, SlidingWindowChallenge.maxVowelsInWindow("rhythm", 4));
        }

        @Test
        void testLeetcode() {
            assertEquals(2, SlidingWindowChallenge.maxVowelsInWindow("leetcode", 3));
        }

        @Test
        void testSizeOneWithAVowel() {
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("abc", 1));
        }

        @Test
        void testSizeOneWithoutVowels() {
            assertEquals(0, SlidingWindowChallenge.maxVowelsInWindow("bcd", 1));
        }

        @Test
        void testWholeText() {
            assertEquals(3, SlidingWindowChallenge.maxVowelsInWindow("banana", 6));
        }

        @Test
        void testAllTheSameVowel() {
            assertEquals(3, SlidingWindowChallenge.maxVowelsInWindow("aaaa", 3));
        }

        @Test
        void testVowelsAtTheEnd() {
            assertEquals(3, SlidingWindowChallenge.maxVowelsInWindow("bbbaaa", 3));
        }

        @Test
        void testVowelsAtTheStart() {
            assertEquals(3, SlidingWindowChallenge.maxVowelsInWindow("aaabbb", 3));
        }

        @Test
        void testSpreadVowels() {
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("abab", 2));
            assertEquals(2, SlidingWindowChallenge.maxVowelsInWindow("abababa", 3));
        }

        @Test
        void testOneCharacter() {
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("a", 1));
            assertEquals(0, SlidingWindowChallenge.maxVowelsInWindow("b", 1));
        }

        @Test
        void testEveryVowelCounts() {
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("bcdfe", 1));
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("bcdfo", 1));
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("bcdfu", 1));
            assertEquals(1, SlidingWindowChallenge.maxVowelsInWindow("bcdfi", 1));
        }

        @Test
        void testYIsNotAVowel() {
            assertEquals(0, SlidingWindowChallenge.maxVowelsInWindow("yyyy", 2));
        }

        @Test
        void testBestGroupInTheMiddle() {
            assertEquals(4, SlidingWindowChallenge.maxVowelsInWindow("bbaaeebb", 4));
        }

        @Test
        void testBigText() {
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 1_000_000; i++) {
                text.append((char) ('a' + i % 26));
            }
            assertEquals(96_154, SlidingWindowChallenge.maxVowelsInWindow(text.toString(), 500_000));
        }

        @Test
        void testBigTextOfVowels() {
            String text = "a".repeat(1_000_000);
            assertEquals(1_000_000, SlidingWindowChallenge.maxVowelsInWindow(text, 1_000_000));
            assertEquals(500_000, SlidingWindowChallenge.maxVowelsInWindow(text, 500_000));
        }
    }

    // ==========================================================
    // CHALLENGE 5: countIncreasingWindows
    // ==========================================================
    @Nested
    class CountIncreasingWindowsTests {

        @Test
        void testTwoWindows() {
            assertEquals(2, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 3, 1, 2, 3}, 3));
        }

        @Test
        void testEqualNumbersBreakTheGroup() {
            assertEquals(2, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 2, 3}, 2));
        }

        @Test
        void testDecreasing() {
            assertEquals(0, SlidingWindowChallenge.countIncreasingWindows(new int[] {3, 2, 1}, 2));
        }

        @Test
        void testSizeOneCountsEveryNumber() {
            assertEquals(2, SlidingWindowChallenge.countIncreasingWindows(new int[] {5, 5}, 1));
            assertEquals(3, SlidingWindowChallenge.countIncreasingWindows(new int[] {5, 4, 3}, 1));
        }

        @Test
        void testWholeArrayIncreasing() {
            assertEquals(1, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 3}, 3));
        }

        @Test
        void testSizeTwoIncreasing() {
            assertEquals(4, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 3, 4, 5}, 2));
        }

        @Test
        void testAllEqual() {
            assertEquals(0, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 1, 1}, 2));
        }

        @Test
        void testNotMonotonic() {
            assertEquals(0, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 3, 2, 4}, 3));
        }

        @Test
        void testTwoRuns() {
            assertEquals(2, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 3, 2, 3, 4}, 3));
        }

        @Test
        void testWholeArrayWithFourNumbers() {
            assertEquals(1, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 3, 4}, 4));
        }

        @Test
        void testGroupStartsAfterAnEqualPair() {
            assertEquals(1, SlidingWindowChallenge.countIncreasingWindows(new int[] {1, 2, 2, 3, 4}, 3));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(1, SlidingWindowChallenge.countIncreasingWindows(new int[] {-3, -2, -1}, 3));
        }

        @Test
        void testRunLongerThanTheGroup() {
            assertEquals(3, SlidingWindowChallenge.countIncreasingWindows(new int[] {5, 6, 7, 8, 9}, 3));
        }

        @Test
        void testOneNumber() {
            assertEquals(1, SlidingWindowChallenge.countIncreasingWindows(new int[] {7}, 1));
        }

        @Test
        void testBigIncreasingArray() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            assertEquals(500_001, SlidingWindowChallenge.countIncreasingWindows(nums, 500_000));
        }

        @Test
        void testBigArrayWithRepeatingRuns() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i % 1000;
            }
            assertEquals(1000, SlidingWindowChallenge.countIncreasingWindows(nums, 1000));
        }
    }

    // ==========================================================
    // CHALLENGE 6: hasNearbyDuplicate
    // ==========================================================
    @Nested
    class HasNearbyDuplicateTests {

        @Test
        void testDistanceThree() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3, 1}, 3));
        }

        @Test
        void testDistanceTooShort() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3, 1}, 2));
        }

        @Test
        void testNextToEachOther() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 0, 1, 1}, 1));
        }

        @Test
        void testZeroDistanceIsNeverEnough() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 1}, 0));
        }

        @Test
        void testEmptyArray() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {}, 3));
        }

        @Test
        void testOneNumber() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1}, 5));
        }

        @Test
        void testNoRepeatedNumbers() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3}, 10));
        }

        @Test
        void testDistanceTwo() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 1}, 2));
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 1}, 1));
        }

        @Test
        void testKBiggerThanTheArray() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 1}, 10));
        }

        @Test
        void testDuplicateLaterInTheArray() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3, 4, 1, 5, 5}, 1));
        }

        @Test
        void testRepeatedNumbersTooFarApart() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 2));
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 3));
        }

        @Test
        void testNegativeNumbersAndZero() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {-1, -1}, 1));
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {0, 1, 0}, 2));
        }

        @Test
        void testSameNumberThreeTimes() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {7, 0, 7, 0, 7}, 2));
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {7, 0, 1, 7}, 2));
        }

        @Test
        void testEarlierOccurrenceLeavesTheWindow() {
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {5, 1, 2, 3, 4, 6, 7}, 2));
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {5, 1, 2, 5}, 3));
        }

        @Test
        void testSecondPairIsClose() {
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(new int[] {7, 0, 0, 7}, 1));
        }

        @Test
        void testBigArrayWithoutDuplicates() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(nums, 999_999));
        }

        @Test
        void testBigArrayWithAFarDuplicate() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            nums[999_999] = 0;
            assertTrue(SlidingWindowChallenge.hasNearbyDuplicate(nums, 999_999));
            assertFalse(SlidingWindowChallenge.hasNearbyDuplicate(nums, 999_998));
        }
    }

    // ==========================================================
    // CHALLENGE 7: containsAnagram
    // ==========================================================
    @Nested
    class ContainsAnagramTests {

        @Test
        void testInTheMiddle() {
            assertTrue(SlidingWindowChallenge.containsAnagram("cbaebabacd", "abc"));
        }

        @Test
        void testAtTheEnd() {
            assertTrue(SlidingWindowChallenge.containsAnagram("abcd", "dc"));
        }

        @Test
        void testNotFound() {
            assertFalse(SlidingWindowChallenge.containsAnagram("abcd", "ca"));
        }

        @Test
        void testPatternLongerThanTheText() {
            assertFalse(SlidingWindowChallenge.containsAnagram("ab", "abc"));
        }

        @Test
        void testSameText() {
            assertTrue(SlidingWindowChallenge.containsAnagram("abc", "abc"));
        }

        @Test
        void testWholeTextInAnotherOrder() {
            assertTrue(SlidingWindowChallenge.containsAnagram("aab", "aba"));
        }

        @Test
        void testOneLetterPresent() {
            assertTrue(SlidingWindowChallenge.containsAnagram("xyz", "y"));
        }

        @Test
        void testOneLetterAbsent() {
            assertFalse(SlidingWindowChallenge.containsAnagram("xyz", "a"));
        }

        @Test
        void testRepeatedLettersMustMatchTheCount() {
            assertFalse(SlidingWindowChallenge.containsAnagram("abcd", "aab"));
            assertTrue(SlidingWindowChallenge.containsAnagram("abab", "aab"));
            assertTrue(SlidingWindowChallenge.containsAnagram("baa", "aab"));
        }

        @Test
        void testAtTheStart() {
            assertTrue(SlidingWindowChallenge.containsAnagram("cbaxxx", "abc"));
        }

        @Test
        void testSameLettersButNotInTheSameGroup() {
            assertFalse(SlidingWindowChallenge.containsAnagram("abbc", "abc"));
        }

        @Test
        void testRepeatedPatternInsideLongerRun() {
            assertTrue(SlidingWindowChallenge.containsAnagram("aaab", "aab"));
        }

        @Test
        void testEmptyText() {
            assertFalse(SlidingWindowChallenge.containsAnagram("", "a"));
        }

        @Test
        void testFirstWindowIsNotTheOnlyOneChecked() {
            assertTrue(SlidingWindowChallenge.containsAnagram("zzzzabc", "bca"));
        }

        @Test
        void testWindowLeavingLetters() {
            assertFalse(SlidingWindowChallenge.containsAnagram("abxcxab", "abc"));
        }

        @Test
        void testBigTextWithoutAnagram() {
            String text = "a".repeat(1_000_000);
            String pattern = "a".repeat(499_999) + "b";
            assertFalse(SlidingWindowChallenge.containsAnagram(text, pattern));
        }

        @Test
        void testBigTextWithAnagram() {
            String text = "a".repeat(1_000_000);
            assertTrue(SlidingWindowChallenge.containsAnagram(text, "a".repeat(500_000)));
        }

        @Test
        void testBigTextWithAnagramAcrossTheMiddle() {
            String text = "a".repeat(500_000) + "b".repeat(500_000);
            assertTrue(SlidingWindowChallenge.containsAnagram(text, "b".repeat(250_000) + "a".repeat(250_000)));
            assertFalse(SlidingWindowChallenge.containsAnagram(text, "a".repeat(250_000) + "c" + "b".repeat(249_999)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: minLengthWithSumAtLeast
    // ==========================================================
    @Nested
    class MinLengthWithSumAtLeastTests {

        @Test
        void testShortestAtTheEnd() {
            assertEquals(2, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {2, 3, 1, 2, 4, 3}, 7));
        }

        @Test
        void testNoGroup() {
            assertEquals(0, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 1, 1}, 5));
        }

        @Test
        void testOneNumberIsEnough() {
            assertEquals(1, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {10}, 5));
        }

        @Test
        void testOneNumberInTheMiddle() {
            assertEquals(1, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 4, 4}, 4));
        }

        @Test
        void testEmptyArray() {
            assertEquals(0, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {}, 1));
        }

        @Test
        void testTargetOne() {
            assertEquals(1, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1}, 1));
        }

        @Test
        void testWholeArrayNeeded() {
            assertEquals(3, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 1, 1}, 3));
        }

        @Test
        void testBigNumberAtTheStart() {
            assertEquals(1, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {5, 1, 1}, 5));
        }

        @Test
        void testBigNumberAtTheEnd() {
            assertEquals(1, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 1, 5}, 5));
        }

        @Test
        void testGrowingNumbers() {
            assertEquals(3, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 2, 3, 4, 5}, 11));
        }

        @Test
        void testTargetEqualsTheTotal() {
            assertEquals(5, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 2, 3, 4, 5}, 15));
        }

        @Test
        void testTargetAboveTheTotal() {
            assertEquals(0, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 2, 3, 4, 5}, 16));
        }

        @Test
        void testShorterGroupAfterALongerOne() {
            assertEquals(2, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {1, 1, 1, 4, 4}, 8));
            assertEquals(2, SlidingWindowChallenge.minLengthWithSumAtLeast(new int[] {3, 3, 1, 1, 9, 1}, 10));
        }

        @Test
        void testSumBiggerThanAnInt() {
            assertEquals(2, SlidingWindowChallenge.minLengthWithSumAtLeast(
                    new int[] {1_500_000_000, 1_500_000_000}, 2_000_000_000));
        }

        @Test
        void testBigArrayOfOnes() {
            assertEquals(500_000, SlidingWindowChallenge.minLengthWithSumAtLeast(filled(1_000_000, 1), 500_000));
            assertEquals(1_000_000, SlidingWindowChallenge.minLengthWithSumAtLeast(filled(1_000_000, 1), 1_000_000));
            assertEquals(0, SlidingWindowChallenge.minLengthWithSumAtLeast(filled(1_000_000, 1), 1_000_001));
        }

        @Test
        void testArrayIsNotModified() {
            int[] nums = {2, 3, 1, 2, 4, 3};
            SlidingWindowChallenge.minLengthWithSumAtLeast(nums, 7);
            assertArrayEquals(new int[] {2, 3, 1, 2, 4, 3}, nums);
        }
    }

    // ==========================================================
    // CHALLENGE 9: longestSubarrayWithSumAtMost
    // ==========================================================
    @Nested
    class LongestSubarrayWithSumAtMostTests {

        @Test
        void testGrowingNumbers() {
            assertEquals(2, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {1, 2, 3, 4}, 5));
        }

        @Test
        void testNothingFits() {
            assertEquals(0, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {5, 5}, 4));
        }

        @Test
        void testZeros() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {0, 0, 0}, 0));
        }

        @Test
        void testGroupInTheMiddle() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {2, 1, 1, 2}, 4));
        }

        @Test
        void testEmptyArray() {
            assertEquals(0, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {}, 5));
        }

        @Test
        void testLimitZeroWithPositives() {
            assertEquals(0, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {1, 2}, 0));
        }

        @Test
        void testWholeArrayFits() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {1, 2, 3}, 6));
        }

        @Test
        void testWholeArrayAlmostFits() {
            assertEquals(2, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {1, 2, 3}, 5));
        }

        @Test
        void testShrinkingTheLeftSide() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {3, 1, 2, 1, 1}, 4));
        }

        @Test
        void testZerosBetweenBigNumbers() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {4, 0, 0, 4}, 4));
        }

        @Test
        void testZerosAtTheEnd() {
            assertEquals(2, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {0, 5, 0, 0}, 0));
        }

        @Test
        void testZerosBeforeAndAfterABigNumber() {
            assertEquals(3, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {5, 0, 0, 0, 5}, 0));
        }

        @Test
        void testOneNumberFits() {
            assertEquals(1, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {3}, 3));
        }

        @Test
        void testOneNumberDoesNotFit() {
            assertEquals(0, SlidingWindowChallenge.longestSubarrayWithSumAtMost(new int[] {3}, 2));
        }

        @Test
        void testSumBiggerThanAnInt() {
            assertEquals(1, SlidingWindowChallenge.longestSubarrayWithSumAtMost(
                    new int[] {1_500_000_000, 1_500_000_000}, 2_000_000_000));
        }

        @Test
        void testBigArrayOfOnes() {
            assertEquals(500_000, SlidingWindowChallenge.longestSubarrayWithSumAtMost(filled(1_000_000, 1), 500_000));
            assertEquals(1_000_000,
                    SlidingWindowChallenge.longestSubarrayWithSumAtMost(filled(1_000_000, 1), 2_000_000));
        }

        @Test
        void testArrayIsNotModified() {
            int[] nums = {1, 2, 3, 4};
            SlidingWindowChallenge.longestSubarrayWithSumAtMost(nums, 5);
            assertArrayEquals(new int[] {1, 2, 3, 4}, nums);
        }
    }

    // ==========================================================
    // CHALLENGE 10: longestOnes
    // ==========================================================
    @Nested
    class LongestOnesTests {

        @Test
        void testNoZerosAllowed() {
            assertEquals(2, SlidingWindowChallenge.longestOnes(new int[] {1, 1, 0, 1}, 0));
        }

        @Test
        void testTwoZerosAllowed() {
            assertEquals(5, SlidingWindowChallenge.longestOnes(new int[] {1, 0, 0, 1, 1}, 2));
        }

        @Test
        void testOneZeroAllowedInAllZeros() {
            assertEquals(1, SlidingWindowChallenge.longestOnes(new int[] {0, 0}, 1));
        }

        @Test
        void testEmptyArray() {
            assertEquals(0, SlidingWindowChallenge.longestOnes(new int[] {}, 3));
        }

        @Test
        void testAllOnes() {
            assertEquals(5, SlidingWindowChallenge.longestOnes(filled(5, 1), 0));
        }

        @Test
        void testAllZerosWithNoFlips() {
            assertEquals(0, SlidingWindowChallenge.longestOnes(filled(5, 0), 0));
        }

        @Test
        void testAllZerosWithSomeFlips() {
            assertEquals(2, SlidingWindowChallenge.longestOnes(filled(5, 0), 2));
        }

        @Test
        void testMoreFlipsThanZeros() {
            assertEquals(3, SlidingWindowChallenge.longestOnes(new int[] {1, 0, 1}, 5));
        }

        @Test
        void testAlternatingWithOneFlip() {
            assertEquals(3, SlidingWindowChallenge.longestOnes(new int[] {1, 0, 1, 0, 1}, 1));
        }

        @Test
        void testAlternatingWithTwoFlips() {
            assertEquals(5, SlidingWindowChallenge.longestOnes(new int[] {1, 0, 1, 0, 1}, 2));
        }

        @Test
        void testZeroAtTheStart() {
            assertEquals(4, SlidingWindowChallenge.longestOnes(new int[] {0, 1, 1, 1, 0, 0}, 1));
        }

        @Test
        void testLongerExample() {
            assertEquals(7, SlidingWindowChallenge.longestOnes(new int[] {0, 0, 1, 1, 1, 0, 1, 1, 0, 0}, 2));
        }

        @Test
        void testSingleNumbers() {
            assertEquals(1, SlidingWindowChallenge.longestOnes(new int[] {1}, 0));
            assertEquals(0, SlidingWindowChallenge.longestOnes(new int[] {0}, 0));
            assertEquals(1, SlidingWindowChallenge.longestOnes(new int[] {0}, 1));
        }

        @Test
        void testZeroBreaksTheGroup() {
            assertEquals(3, SlidingWindowChallenge.longestOnes(new int[] {1, 1, 0, 1, 1, 1}, 0));
        }

        @Test
        void testBestGroupAtTheEndAfterShrinking() {
            assertEquals(4, SlidingWindowChallenge.longestOnes(new int[] {0, 0, 0, 1, 1, 1, 1}, 0));
        }

        @Test
        void testBigArrayOfOnes() {
            assertEquals(1_000_000, SlidingWindowChallenge.longestOnes(filled(1_000_000, 1), 0));
        }

        @Test
        void testBigArrayOfZeros() {
            assertEquals(3, SlidingWindowChallenge.longestOnes(filled(1_000_000, 0), 3));
        }

        @Test
        void testBigAlternatingArray() {
            int[] bits = new int[1_000_000];
            for (int i = 0; i < bits.length; i++) {
                bits[i] = i % 2 == 0 ? 1 : 0;
            }
            assertEquals(500_001, SlidingWindowChallenge.longestOnes(bits, 250_000));
        }

        @Test
        void testArrayIsNotModified() {
            int[] bits = {1, 0, 0, 1, 1};
            SlidingWindowChallenge.longestOnes(bits, 2);
            assertArrayEquals(new int[] {1, 0, 0, 1, 1}, bits);
        }
    }

    // ==========================================================
    // CHALLENGE 11: longestUniqueSubstring
    // ==========================================================
    @Nested
    class LongestUniqueSubstringTests {

        @Test
        void testAbcabcbb() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("abcabcbb"));
        }

        @Test
        void testSameCharacter() {
            assertEquals(1, SlidingWindowChallenge.longestUniqueSubstring("bbbb"));
        }

        @Test
        void testPwwkew() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("pwwkew"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(2, SlidingWindowChallenge.longestUniqueSubstring("aAa"));
            assertEquals(4, SlidingWindowChallenge.longestUniqueSubstring("aAbB"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, SlidingWindowChallenge.longestUniqueSubstring(""));
        }

        @Test
        void testAllDifferent() {
            assertEquals(6, SlidingWindowChallenge.longestUniqueSubstring("abcdef"));
        }

        @Test
        void testAbba() {
            assertEquals(2, SlidingWindowChallenge.longestUniqueSubstring("abba"));
        }

        @Test
        void testDvdf() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("dvdf"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(1, SlidingWindowChallenge.longestUniqueSubstring("x"));
            assertEquals(1, SlidingWindowChallenge.longestUniqueSubstring(" "));
        }

        @Test
        void testSpacesAreCharacters() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("a b a"));
        }

        @Test
        void testStaleRepeatedCharacterIsIgnored() {
            assertEquals(5, SlidingWindowChallenge.longestUniqueSubstring("tmmzuxt"));
        }

        @Test
        void testRepeatingPattern() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("abcabcabc"));
        }

        @Test
        void testAlternatingCase() {
            assertEquals(2, SlidingWindowChallenge.longestUniqueSubstring("AaAa"));
        }

        @Test
        void testDigits() {
            assertEquals(2, SlidingWindowChallenge.longestUniqueSubstring("112233"));
        }

        @Test
        void testTwoDifferentCharacters() {
            assertEquals(2, SlidingWindowChallenge.longestUniqueSubstring("au"));
        }

        @Test
        void testRepeatedCharacterAtTheEnd() {
            assertEquals(3, SlidingWindowChallenge.longestUniqueSubstring("abcb"));
        }

        @Test
        void testLongestAtTheEnd() {
            assertEquals(5, SlidingWindowChallenge.longestUniqueSubstring("aabcde"));
        }

        @Test
        void testBigTextWithBigAlphabet() {
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 120_000; i++) {
                text.append((char) (256 + i % 60_000));
            }
            assertEquals(60_000, SlidingWindowChallenge.longestUniqueSubstring(text.toString()));
        }

        @Test
        void testBigTextOfOneCharacter() {
            assertEquals(1, SlidingWindowChallenge.longestUniqueSubstring("z".repeat(1_000_000)));
        }
    }
}