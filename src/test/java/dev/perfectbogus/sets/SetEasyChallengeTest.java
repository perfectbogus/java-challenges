package dev.perfectbogus.sets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetEasyChallengeTest {

    private static Set<Integer> set(Integer... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    private static Set<Character> letters(String text) {
        Set<Character> result = new HashSet<>();
        for (char c : text.toCharArray()) {
            result.add(c);
        }
        return result;
    }

    private static int[] range(int from, int to) {
        int[] result = new int[to - from];
        for (int i = 0; i < result.length; i++) {
            result[i] = from + i;
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: countDistinct
    // ==========================================================
    @Nested
    class CountDistinctTests {

        @Test
        void testRepeatedValues() {
            assertEquals(3, SetEasyChallenge.countDistinct(new int[] {1, 2, 2, 3, 3, 3}));
        }

        @Test
        void testRepeatedValuesExtended() {
            assertEquals(3, SetEasyChallenge.countDistinctExtended(new int[] {1, 2, 2, 3, 3, 3}));
        }

        @Test
        void testAllTheSame() {
            assertEquals(1, SetEasyChallenge.countDistinct(new int[] {5, 5, 5}));
        }

        @Test
        void testNegativeValues() {
            assertEquals(2, SetEasyChallenge.countDistinct(new int[] {4, -4}));
            assertEquals(1, SetEasyChallenge.countDistinct(new int[] {-4, -4}));
        }

        @Test
        void testEmpty() {
            assertEquals(0, SetEasyChallenge.countDistinct(new int[] {}));
        }

        @Test
        void testOneValue() {
            assertEquals(1, SetEasyChallenge.countDistinct(new int[] {7}));
        }

        @Test
        void testAllDifferent() {
            assertEquals(5, SetEasyChallenge.countDistinct(new int[] {5, 4, 3, 2, 1}));
        }

        @Test
        void testZero() {
            assertEquals(2, SetEasyChallenge.countDistinct(new int[] {0, 1, 0}));
        }

        @Test
        void testRepeatedValuesAreNotNextToEachOther() {
            assertEquals(2, SetEasyChallenge.countDistinct(new int[] {1, 2, 1, 2, 1, 2}));
        }

        @Test
        void testExtremeValues() {
            assertEquals(3, SetEasyChallenge.countDistinct(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0,
                    Integer.MAX_VALUE}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(2, SetEasyChallenge.countDistinct(new int[] {1000, 1000, 100_000, 100_000}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {3, 1, 3};
            SetEasyChallenge.countDistinct(nums);
            assertArrayEquals(new int[] {3, 1, 3}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i % 1000;
            }
            assertEquals(1000, SetEasyChallenge.countDistinct(nums));
        }
    }

    // ==========================================================
    // CHALLENGE 2: hasDuplicates
    // ==========================================================
    @Nested
    class HasDuplicatesTests {

        @Test
        void testRepeatedWord() {
            assertTrue(SetEasyChallenge.hasDuplicates(new String[] {"a", "b", "a"}));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"a", "A"}));
        }

        @Test
        void testOneWord() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"x"}));
        }

        @Test
        void testEmpty() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {}));
        }

        @Test
        void testTwoEqualWords() {
            assertTrue(SetEasyChallenge.hasDuplicates(new String[] {"go", "go"}));
        }

        @Test
        void testRepeatedAtTheEnd() {
            assertTrue(SetEasyChallenge.hasDuplicates(new String[] {"a", "b", "c", "d", "a"}));
        }

        @Test
        void testAllDifferent() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"a", "b", "c", "d"}));
        }

        @Test
        void testWordsWithTheSameStartOrEnd() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"ab", "ac", "bc", "abc"}));
        }

        @Test
        void testEmptyWords() {
            assertTrue(SetEasyChallenge.hasDuplicates(new String[] {"", "a", ""}));
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"", "a"}));
        }

        @Test
        void testWordsEqualByContentNotByReference() {
            String first = new String("hello");
            String second = new String("hello");
            assertTrue(SetEasyChallenge.hasDuplicates(new String[] {first, second}));
        }

        @Test
        void testWordsWithSpaces() {
            assertFalse(SetEasyChallenge.hasDuplicates(new String[] {"a b", "ab", "a  b"}));
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "w" + i;
            }
            assertFalse(SetEasyChallenge.hasDuplicates(words));
            words[99_999] = "w0";
            assertTrue(SetEasyChallenge.hasDuplicates(words));
        }
    }

    // ==========================================================
    // CHALLENGE 3: commonElements
    // ==========================================================
    @Nested
    class CommonElementsTests {

        @Test
        void testTwoCommonValues() {
            assertEquals(set(2, 3), SetEasyChallenge.commonElements(new int[] {1, 2, 3}, new int[] {2, 3, 4}));
        }

        @Test
        void testRepeatedValuesAppearOnce() {
            assertEquals(set(1), SetEasyChallenge.commonElements(new int[] {1, 1, 2}, new int[] {1, 3}));
        }

        @Test
        void testNoCommonValues() {
            assertEquals(set(), SetEasyChallenge.commonElements(new int[] {1, 2}, new int[] {3, 4}));
        }

        @Test
        void testOneEmptyArray() {
            assertEquals(set(), SetEasyChallenge.commonElements(new int[] {}, new int[] {1}));
            assertEquals(set(), SetEasyChallenge.commonElements(new int[] {1}, new int[] {}));
        }

        @Test
        void testBothEmpty() {
            assertEquals(set(), SetEasyChallenge.commonElements(new int[] {}, new int[] {}));
        }

        @Test
        void testSameArrays() {
            assertEquals(set(1, 2, 3), SetEasyChallenge.commonElements(new int[] {1, 2, 3}, new int[] {3, 2, 1}));
        }

        @Test
        void testNegativeValues() {
            assertEquals(set(-1), SetEasyChallenge.commonElements(new int[] {-1, 1}, new int[] {-1, 2}));
        }

        @Test
        void testSeveralCommonValuesStartingTheSame() {
            assertEquals(set(1, 2, 3), SetEasyChallenge.commonElements(new int[] {1, 2, 3}, new int[] {1, 2, 3, 4}));
        }

        @Test
        void testRepeatedInBoth() {
            assertEquals(set(2), SetEasyChallenge.commonElements(new int[] {2, 2, 2}, new int[] {2, 2}));
        }

        @Test
        void testOneArrayInsideTheOther() {
            assertEquals(set(2, 3), SetEasyChallenge.commonElements(new int[] {2, 3}, new int[] {1, 2, 3, 4}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(set(1000, 100_000),
                    SetEasyChallenge.commonElements(new int[] {1000, 100_000, 5}, new int[] {100_000, 1000}));
        }

        @Test
        void testOriginalsAreNotModified() {
            int[] a = {1, 2, 3};
            int[] b = {2, 3, 4};
            SetEasyChallenge.commonElements(a, b);
            assertArrayEquals(new int[] {1, 2, 3}, a);
            assertArrayEquals(new int[] {2, 3, 4}, b);
        }

        @Test
        void testBigArrays() {
            Set<Integer> result = SetEasyChallenge.commonElements(range(0, 200_000), range(100_000, 300_000));
            assertEquals(100_000, result.size());
            assertTrue(result.contains(100_000));
            assertTrue(result.contains(199_999));
            assertFalse(result.contains(99_999));
            assertFalse(result.contains(200_000));
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortedUniqueWords
    // ==========================================================
    @Nested
    class SortedUniqueWordsTests {

        @Test
        void testRepeatedWordWithDifferentCase() {
            assertArrayEquals(new String[] {"hello", "world"}, SetEasyChallenge.sortedUniqueWords("Hello hello World"));
        }

        @Test
        void testRepeatedWordWithDifferentCaseSimplified() {
            assertArrayEquals(new String[] {"hello", "world"}, SetEasyChallenge.sortedUniqueWordsSimplified("Hello hello World"));
        }

        @Test
        void testOrderIgnoresTheCase() {
            assertArrayEquals(new String[] {"a", "b"}, SetEasyChallenge.sortedUniqueWords("b A a"));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"zed"}, SetEasyChallenge.sortedUniqueWords("Zed"));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new String[] {}, SetEasyChallenge.sortedUniqueWords(""));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new String[] {"a", "b", "c"}, SetEasyChallenge.sortedUniqueWords("a b c"));
        }

        @Test
        void testReversed() {
            assertArrayEquals(new String[] {"a", "b", "c"}, SetEasyChallenge.sortedUniqueWords("c b a"));
        }

        @Test
        void testAllTheSameWord() {
            assertArrayEquals(new String[] {"go"}, SetEasyChallenge.sortedUniqueWords("go GO Go gO"));
        }

        @Test
        void testUppercaseWordsAreReturnedInLowercase() {
            assertArrayEquals(new String[] {"abc", "xyz"}, SetEasyChallenge.sortedUniqueWords("XYZ ABC"));
        }

        @Test
        void testLongerWordsAfterTheirPrefix() {
            assertArrayEquals(new String[] {"a", "ab", "abc"}, SetEasyChallenge.sortedUniqueWords("abc a ab"));
        }

        @Test
        void testAlphabeticalNotByLength() {
            assertArrayEquals(new String[] {"apple", "z"}, SetEasyChallenge.sortedUniqueWords("z apple"));
        }

        @Test
        void testWordsWithDigits() {
            assertArrayEquals(new String[] {"a1", "a10", "a2"}, SetEasyChallenge.sortedUniqueWords("a2 a10 a1"));
        }

        @Test
        void testSeveralRepeatedWords() {
            assertArrayEquals(new String[] {"be", "not", "or", "to"},
                    SetEasyChallenge.sortedUniqueWords("to be or not to be"));
        }

        @Test
        void testBigText() {
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 100_000; i++) {
                text.append(i == 0 ? "" : " ").append(i % 2 == 0 ? "W" : "w").append(i % 1000);
            }
            String[] result = SetEasyChallenge.sortedUniqueWords(text.toString());
            assertEquals(1000, result.length);
            assertEquals("w0", result[0]);
            assertEquals("w1", result[1]);
            assertEquals("w10", result[2]);
            assertEquals("w999", result[999]);
        }
    }

    // ==========================================================
    // CHALLENGE 5: onlyInFirst
    // ==========================================================
    @Nested
    class OnlyInFirstTests {

        @Test
        void testRemovesTheValueOfTheSecond() {
            assertEquals(set(1, 3), SetEasyChallenge.onlyInFirst(new int[] {1, 2, 3}, new int[] {2}));
        }

        @Test
        void testRepeatedValuesAppearOnce() {
            assertEquals(set(1, 2), SetEasyChallenge.onlyInFirst(new int[] {1, 1, 2}, new int[] {3}));
        }

        @Test
        void testSecondHasEverything() {
            assertEquals(set(), SetEasyChallenge.onlyInFirst(new int[] {1, 2}, new int[] {1, 2, 3}));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(set(), SetEasyChallenge.onlyInFirst(new int[] {}, new int[] {1, 2}));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(set(4, 5), SetEasyChallenge.onlyInFirst(new int[] {4, 5, 5}, new int[] {}));
        }

        @Test
        void testTheOrderMatters() {
            assertEquals(set(1), SetEasyChallenge.onlyInFirst(new int[] {1, 2}, new int[] {2, 3}));
            assertEquals(set(3), SetEasyChallenge.onlyInFirst(new int[] {2, 3}, new int[] {1, 2}));
        }

        @Test
        void testSameArrays() {
            assertEquals(set(), SetEasyChallenge.onlyInFirst(new int[] {1, 2, 3}, new int[] {3, 2, 1}));
        }

        @Test
        void testNoCommonValues() {
            assertEquals(set(1, 2), SetEasyChallenge.onlyInFirst(new int[] {1, 2}, new int[] {3, 4}));
        }

        @Test
        void testValueRepeatedInTheSecond() {
            assertEquals(set(1), SetEasyChallenge.onlyInFirst(new int[] {1, 2}, new int[] {2, 2, 2}));
        }

        @Test
        void testNegativeValues() {
            assertEquals(set(-2), SetEasyChallenge.onlyInFirst(new int[] {-1, -2}, new int[] {-1}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(set(100_000), SetEasyChallenge.onlyInFirst(new int[] {1000, 100_000}, new int[] {1000}));
        }

        @Test
        void testOriginalsAreNotModified() {
            int[] a = {1, 2, 3};
            int[] b = {2};
            SetEasyChallenge.onlyInFirst(a, b);
            assertArrayEquals(new int[] {1, 2, 3}, a);
            assertArrayEquals(new int[] {2}, b);
        }

        @Test
        void testBigArrays() {
            Set<Integer> result = SetEasyChallenge.onlyInFirst(range(0, 200_000), range(100_000, 300_000));
            assertEquals(100_000, result.size());
            assertTrue(result.contains(0));
            assertTrue(result.contains(99_999));
            assertFalse(result.contains(100_000));
        }
    }

    // ==========================================================
    // CHALLENGE 6: missingLetters
    // ==========================================================
    @Nested
    class MissingLettersTests {

        @Test
        void testOnlyZIsMissing() {
            assertEquals(letters("z"), SetEasyChallenge.missingLetters("abcdefghijklmnopqrstuvwxy"));
        }

        @Test
        void testPangram() {
            assertEquals(letters(""), SetEasyChallenge.missingLetters("thequickbrownfoxjumpsoverthelazydog"));
        }

        @Test
        void testShortText() {
            assertEquals(letters("cdefghijklmnopqrstuvwxyz"), SetEasyChallenge.missingLetters("aab"));
        }

        @Test
        void testEmpty() {
            assertEquals(letters("abcdefghijklmnopqrstuvwxyz"), SetEasyChallenge.missingLetters(""));
        }

        @Test
        void testOnlyAIsMissing() {
            assertEquals(letters("a"), SetEasyChallenge.missingLetters("bcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void testOnlyTheMiddleLetterIsMissing() {
            assertEquals(letters("m"), SetEasyChallenge.missingLetters("abcdefghijklnopqrstuvwxyz"));
        }

        @Test
        void testRepeatedLetters() {
            assertEquals(letters("abcdefghijklmnopqrstuvwxy"), SetEasyChallenge.missingLetters("zzzzzz"));
        }

        @Test
        void testOneLetter() {
            assertEquals(letters("abcdefghijklmnopqrstuvwxy"), SetEasyChallenge.missingLetters("z"));
            assertEquals(letters("bcdefghijklmnopqrstuvwxyz"), SetEasyChallenge.missingLetters("a"));
        }

        @Test
        void testSizeOfTheResult() {
            assertEquals(23, SetEasyChallenge.missingLetters("abc").size());
            assertEquals(26, SetEasyChallenge.missingLetters("").size());
            assertEquals(0, SetEasyChallenge.missingLetters("abcdefghijklmnopqrstuvwxyz").size());
        }

        @Test
        void testLettersInReverseOrder() {
            assertEquals(letters("a"), SetEasyChallenge.missingLetters("zyxwvutsrqponmlkjihgfedcb"));
        }

        @Test
        void testBigText() {
            String text = "abcdefghijklm".repeat(100_000);
            assertEquals(letters("nopqrstuvwxyz"), SetEasyChallenge.missingLetters(text));
        }
    }

    // ==========================================================
    // CHALLENGE 7: firstRepeated
    // ==========================================================
    @Nested
    class FirstRepeatedTests {

        @Test
        void testSecondAppearanceComesFirst() {
            assertEquals(1, SetEasyChallenge.firstRepeated(new int[] {3, 1, 2, 1, 3}));
        }

        @Test
        void testNoRepeatedValue() {
            assertEquals(-1, SetEasyChallenge.firstRepeated(new int[] {1, 2, 3}));
        }

        @Test
        void testTwoEqualValues() {
            assertEquals(5, SetEasyChallenge.firstRepeated(new int[] {5, 5}));
        }

        @Test
        void testEmpty() {
            assertEquals(-1, SetEasyChallenge.firstRepeated(new int[] {}));
        }

        @Test
        void testOneValue() {
            assertEquals(-1, SetEasyChallenge.firstRepeated(new int[] {0}));
        }

        @Test
        void testZeroCanBeRepeated() {
            assertEquals(0, SetEasyChallenge.firstRepeated(new int[] {1, 0, 2, 0}));
        }

        @Test
        void testNotTheValueThatAppearsFirst() {
            assertEquals(2, SetEasyChallenge.firstRepeated(new int[] {1, 2, 2, 1}));
            assertEquals(2, SetEasyChallenge.firstRepeated(new int[] {1, 2, 3, 2, 1}));
        }

        @Test
        void testNotTheValueWithMoreAppearances() {
            assertEquals(2, SetEasyChallenge.firstRepeated(new int[] {3, 2, 2, 3, 3, 3, 1, 1}));
        }

        @Test
        void testValueRepeatedManyTimes() {
            assertEquals(7, SetEasyChallenge.firstRepeated(new int[] {7, 7, 7, 7}));
        }

        @Test
        void testRepeatedValueAtTheEnd() {
            assertEquals(9, SetEasyChallenge.firstRepeated(new int[] {9, 1, 2, 3, 4, 9}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(100_000, SetEasyChallenge.firstRepeated(new int[] {100_000, 1000, 100_000}));
            assertEquals(1000, SetEasyChallenge.firstRepeated(new int[] {1000, 5, 1000}));
        }

        @Test
        void testBigArray() {
            int[] nums = range(0, 100_000);
            assertEquals(-1, SetEasyChallenge.firstRepeated(nums));
            int[] withRepeated = Arrays.copyOf(nums, 100_001);
            withRepeated[100_000] = 5;
            assertEquals(5, SetEasyChallenge.firstRepeated(withRepeated));
        }
    }

    // ==========================================================
    // CHALLENGE 8: uniqueInOrder
    // ==========================================================
    @Nested
    class UniqueInOrderTests {

        @Test
        void testRepeatedWords() {
            assertArrayEquals(new String[] {"b", "a", "c"},
                    SetEasyChallenge.uniqueInOrder(new String[] {"b", "a", "b", "c", "a"}));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"a", "A"}, SetEasyChallenge.uniqueInOrder(new String[] {"a", "A", "a"}));
        }

        @Test
        void testAllTheSame() {
            assertArrayEquals(new String[] {"x"}, SetEasyChallenge.uniqueInOrder(new String[] {"x", "x", "x"}));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new String[] {}, SetEasyChallenge.uniqueInOrder(new String[] {}));
        }

        @Test
        void testNothingToRemove() {
            assertArrayEquals(new String[] {"c", "a", "b"},
                    SetEasyChallenge.uniqueInOrder(new String[] {"c", "a", "b"}));
        }

        @Test
        void testNotSorted() {
            assertArrayEquals(new String[] {"z", "a"}, SetEasyChallenge.uniqueInOrder(new String[] {"z", "a", "z"}));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"hi"}, SetEasyChallenge.uniqueInOrder(new String[] {"hi"}));
        }

        @Test
        void testFirstAppearanceIsKept() {
            assertArrayEquals(new String[] {"a", "b", "c"},
                    SetEasyChallenge.uniqueInOrder(new String[] {"a", "b", "c", "c", "b", "a"}));
        }

        @Test
        void testEmptyWords() {
            assertArrayEquals(new String[] {"", "a"}, SetEasyChallenge.uniqueInOrder(new String[] {"", "a", ""}));
        }

        @Test
        void testWordsEqualByContentNotByReference() {
            String[] result = SetEasyChallenge.uniqueInOrder(new String[] {new String("hello"), new String("hello")});
            assertEquals(1, result.length);
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"b", "a", "b"};
            SetEasyChallenge.uniqueInOrder(words);
            assertArrayEquals(new String[] {"b", "a", "b"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[200_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "w" + (i % 1000);
            }
            String[] result = SetEasyChallenge.uniqueInOrder(words);
            assertEquals(1000, result.length);
            assertEquals("w0", result[0]);
            assertEquals("w1", result[1]);
            assertEquals("w10", result[10]);
            assertEquals("w999", result[999]);
        }
    }

    // ==========================================================
    // CHALLENGE 9: isSubset
    // ==========================================================
    @Nested
    class IsSubsetTests {

        @Test
        void testAllValuesAreInTheBigArray() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {1, 2}, new int[] {3, 2, 1}));
        }

        @Test
        void testRepeatedValuesDoNotMatter() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {1, 1}, new int[] {1}));
        }

        @Test
        void testOneValueIsMissing() {
            assertFalse(SetEasyChallenge.isSubset(new int[] {1, 4}, new int[] {1, 2, 3}));
        }

        @Test
        void testBothEmpty() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {}, new int[] {}));
        }

        @Test
        void testEmptySmall() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {}, new int[] {1, 2}));
        }

        @Test
        void testEmptyBig() {
            assertFalse(SetEasyChallenge.isSubset(new int[] {1}, new int[] {}));
        }

        @Test
        void testSameValues() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {1, 2, 3}, new int[] {3, 2, 1}));
        }

        @Test
        void testBigIsNotASubsetOfSmall() {
            assertFalse(SetEasyChallenge.isSubset(new int[] {1, 2, 3}, new int[] {1, 2}));
        }

        @Test
        void testNegativeValues() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {-1}, new int[] {-1, 0}));
            assertFalse(SetEasyChallenge.isSubset(new int[] {-1}, new int[] {1}));
        }

        @Test
        void testSmallRepeatedMoreThanBig() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {2, 2, 2}, new int[] {2, 3}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertTrue(SetEasyChallenge.isSubset(new int[] {100_000}, new int[] {5, 100_000}));
            assertFalse(SetEasyChallenge.isSubset(new int[] {100_000}, new int[] {5, 100_001}));
        }

        @Test
        void testOriginalsAreNotModified() {
            int[] small = {2, 1};
            int[] big = {3, 2, 1};
            SetEasyChallenge.isSubset(small, big);
            assertArrayEquals(new int[] {2, 1}, small);
            assertArrayEquals(new int[] {3, 2, 1}, big);
        }

        @Test
        void testBigArrays() {
            assertTrue(SetEasyChallenge.isSubset(range(0, 100_000), range(0, 200_000)));
            assertFalse(SetEasyChallenge.isSubset(range(0, 100_001), range(1, 200_000)));
            assertFalse(SetEasyChallenge.isSubset(range(0, 200_000), range(0, 199_999)));
        }
    }

    // ==========================================================
    // CHALLENGE 10: symmetricDifference
    // ==========================================================
    @Nested
    class SymmetricDifferenceTests {

        @Test
        void testSomeCommonValues() {
            assertEquals(set(1, 2, 4), SetEasyChallenge.symmetricDifference(new int[] {1, 2, 3}, new int[] {3, 4}));
        }

        @Test
        void testSameValuesRepeated() {
            assertEquals(set(), SetEasyChallenge.symmetricDifference(new int[] {1, 1}, new int[] {1}));
        }

        @Test
        void testNoCommonValues() {
            assertEquals(set(1, 2), SetEasyChallenge.symmetricDifference(new int[] {1}, new int[] {2}));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(set(5), SetEasyChallenge.symmetricDifference(new int[] {}, new int[] {5}));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(set(5, 6), SetEasyChallenge.symmetricDifference(new int[] {5, 6, 6}, new int[] {}));
        }

        @Test
        void testBothEmpty() {
            assertEquals(set(), SetEasyChallenge.symmetricDifference(new int[] {}, new int[] {}));
        }

        @Test
        void testSameArrays() {
            assertEquals(set(), SetEasyChallenge.symmetricDifference(new int[] {1, 2, 3}, new int[] {3, 2, 1}));
        }

        @Test
        void testOrderDoesNotMatter() {
            assertEquals(SetEasyChallenge.symmetricDifference(new int[] {1, 2}, new int[] {2, 3}),
                    SetEasyChallenge.symmetricDifference(new int[] {2, 3}, new int[] {1, 2}));
            assertEquals(set(1, 3), SetEasyChallenge.symmetricDifference(new int[] {1, 2}, new int[] {2, 3}));
        }

        @Test
        void testOneArrayInsideTheOther() {
            assertEquals(set(1, 4), SetEasyChallenge.symmetricDifference(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
        }

        @Test
        void testNegativeValues() {
            assertEquals(set(-1, 1), SetEasyChallenge.symmetricDifference(new int[] {-1, 0}, new int[] {0, 1}));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(set(1000, 100_000),
                    SetEasyChallenge.symmetricDifference(new int[] {1000, 100_001}, new int[] {100_000, 100_001}));
        }

        @Test
        void testOriginalsAreNotModified() {
            int[] a = {1, 2, 3};
            int[] b = {3, 4};
            SetEasyChallenge.symmetricDifference(a, b);
            assertArrayEquals(new int[] {1, 2, 3}, a);
            assertArrayEquals(new int[] {3, 4}, b);
        }

        @Test
        void testBigArrays() {
            Set<Integer> result = SetEasyChallenge.symmetricDifference(range(0, 100_000), range(50_000, 150_000));
            assertEquals(100_000, result.size());
            assertTrue(result.contains(0));
            assertTrue(result.contains(49_999));
            assertFalse(result.contains(50_000));
            assertFalse(result.contains(99_999));
            assertTrue(result.contains(100_000));
            assertTrue(result.contains(149_999));
        }
    }
}