package dev.perfectbogus.mix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MixedBasicsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: countLetters
    // ==========================================================
    @Nested
    class CountLettersTests {

        @Test
        void testMixed() {
            assertEquals(3, MixedBasicsChallenge.countLetters("ab1 c!"));
        }

        @Test
        void testOnlyDigits() {
            assertEquals(0, MixedBasicsChallenge.countLetters("123"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, MixedBasicsChallenge.countLetters(""));
        }

        @Test
        void testOnlyLetters() {
            assertEquals(5, MixedBasicsChallenge.countLetters("Hello"));
        }

        @Test
        void testUppercaseAndLowercase() {
            assertEquals(4, MixedBasicsChallenge.countLetters("aBcD"));
        }

        @Test
        void testAccentedLetters() {
            assertEquals(4, MixedBasicsChallenge.countLetters("café"));
            assertEquals(4, MixedBasicsChallenge.countLetters("niño"));
        }

        @Test
        void testSpacesAndSymbols() {
            assertEquals(0, MixedBasicsChallenge.countLetters(" !?.,;-_ "));
        }

        @Test
        void testLettersAreCountedEverywhere() {
            assertEquals(6, MixedBasicsChallenge.countLetters("a1b2c3d4e5f6"));
        }

        @Test
        void testRepeatedLetters() {
            assertEquals(4, MixedBasicsChallenge.countLetters("aaaa"));
        }

        @Test
        void testNonLatinLetters() {
            assertEquals(3, MixedBasicsChallenge.countLetters("αβγ"));
        }

        @Test
        void testUnderscoreAndDigitsAreNotLetters() {
            assertEquals(2, MixedBasicsChallenge.countLetters("a_9_b"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: swapCase
    // ==========================================================
    @Nested
    class SwapCaseTests {

        @Test
        void testMixed() {
            assertEquals("AbC1", MixedBasicsChallenge.swapCase("aBc1"));
        }

        @Test
        void testSentence() {
            assertEquals("hELLO wORLD", MixedBasicsChallenge.swapCase("Hello World"));
        }

        @Test
        void testNoLetters() {
            assertEquals("123 !", MixedBasicsChallenge.swapCase("123 !"));
        }

        @Test
        void testEmpty() {
            assertEquals("", MixedBasicsChallenge.swapCase(""));
        }

        @Test
        void testAllLowercase() {
            assertEquals("ABC", MixedBasicsChallenge.swapCase("abc"));
        }

        @Test
        void testAllUppercase() {
            assertEquals("abc", MixedBasicsChallenge.swapCase("ABC"));
        }

        @Test
        void testAccentedLetters() {
            assertEquals("Éé", MixedBasicsChallenge.swapCase("éÉ"));
        }

        @Test
        void testTwiceGivesTheSameText() {
            String once = MixedBasicsChallenge.swapCase("Java 17 Is Fun");
            assertEquals("Java 17 Is Fun", MixedBasicsChallenge.swapCase(once));
        }

        @Test
        void testSymbolsBetweenLetters() {
            assertEquals("A-B_c.D", MixedBasicsChallenge.swapCase("a-b_C.d"));
        }

        @Test
        void testLengthIsKept() {
            assertEquals(11, MixedBasicsChallenge.swapCase("Hello World").length());
        }

        @Test
        void testSingleCharacters() {
            assertEquals("a", MixedBasicsChallenge.swapCase("A"));
            assertEquals("A", MixedBasicsChallenge.swapCase("a"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: digitSum
    // ==========================================================
    @Nested
    class DigitSumTests {

        @Test
        void testMixed() {
            assertEquals(5, MixedBasicsChallenge.digitSum("a1b22"));
        }

        @Test
        void testNoDigits() {
            assertEquals(0, MixedBasicsChallenge.digitSum("abc"));
        }

        @Test
        void testNines() {
            assertEquals(27, MixedBasicsChallenge.digitSum("9 9 9"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, MixedBasicsChallenge.digitSum(""));
        }

        @Test
        void testEachDigitCountsByItself() {
            assertEquals(6, MixedBasicsChallenge.digitSum("123"));
        }

        @Test
        void testZeros() {
            assertEquals(0, MixedBasicsChallenge.digitSum("000"));
        }

        @Test
        void testAllDigits() {
            assertEquals(45, MixedBasicsChallenge.digitSum("0123456789"));
        }

        @Test
        void testSingleDigit() {
            assertEquals(7, MixedBasicsChallenge.digitSum("7"));
        }

        @Test
        void testDigitsAroundSymbols() {
            assertEquals(10, MixedBasicsChallenge.digitSum("1-2+3*4"));
        }

        @Test
        void testLongText() {
            assertEquals(90, MixedBasicsChallenge.digitSum("0123456789".repeat(2)));
        }

        @Test
        void testOnlyDigitsFromZeroToNine() {
            assertEquals(3, MixedBasicsChallenge.digitSum("\u0663 3"));
        }

        @Test
        void testLettersThatLookLikeDigitsAreIgnored() {
            assertEquals(1, MixedBasicsChallenge.digitSum("lO1"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: evenSquares
    // ==========================================================
    @Nested
    class EvenSquaresTests {

        @Test
        void testMixed() {
            assertEquals(List.of(4, 16), MixedBasicsChallenge.evenSquares(List.of(1, 2, 3, 4)));
        }

        @Test
        void testNegativeAndZero() {
            assertEquals(List.of(4, 0), MixedBasicsChallenge.evenSquares(List.of(-2, 0, 5)));
        }

        @Test
        void testNoEven() {
            assertEquals(List.of(), MixedBasicsChallenge.evenSquares(List.of(1, 3)));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), MixedBasicsChallenge.evenSquares(List.of()));
        }

        @Test
        void testOrderIsKept() {
            assertEquals(List.of(36, 4, 16), MixedBasicsChallenge.evenSquares(List.of(6, 3, 2, 7, 4)));
        }

        @Test
        void testOddNegativeIsIgnored() {
            assertEquals(List.of(), MixedBasicsChallenge.evenSquares(List.of(-1, -3, -5)));
        }

        @Test
        void testNegativeEven() {
            assertEquals(List.of(16, 64), MixedBasicsChallenge.evenSquares(List.of(-4, -8)));
        }

        @Test
        void testRepeatedValues() {
            assertEquals(List.of(4, 4, 4), MixedBasicsChallenge.evenSquares(List.of(2, 2, 2)));
        }

        @Test
        void testLargeValues() {
            assertEquals(List.of(1000000, 1000000), MixedBasicsChallenge.evenSquares(List.of(1000, -1000)));
        }

        @Test
        void testOnlyEven() {
            assertEquals(List.of(4, 16, 36), MixedBasicsChallenge.evenSquares(List.of(2, 4, 6)));
        }

        @Test
        void testListIsNotModified() {
            List<Integer> in = new java.util.ArrayList<>(List.of(1, 2, 3));
            MixedBasicsChallenge.evenSquares(in);
            assertEquals(List.of(1, 2, 3), in);
        }
    }

    // ==========================================================
    // CHALLENGE 5: longestLength
    // ==========================================================
    @Nested
    class LongestLengthTests {

        @Test
        void testMixed() {
            assertEquals(3, MixedBasicsChallenge.longestLength(List.of("a", "abc", "ab")));
        }

        @Test
        void testSingleWord() {
            assertEquals(5, MixedBasicsChallenge.longestLength(List.of("hello")));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, MixedBasicsChallenge.longestLength(List.of()));
        }

        @Test
        void testLongestFirst() {
            assertEquals(4, MixedBasicsChallenge.longestLength(List.of("abcd", "a", "ab")));
        }

        @Test
        void testLongestLast() {
            assertEquals(4, MixedBasicsChallenge.longestLength(List.of("a", "ab", "abcd")));
        }

        @Test
        void testAllSameLength() {
            assertEquals(2, MixedBasicsChallenge.longestLength(List.of("ab", "cd", "ef")));
        }

        @Test
        void testEmptyWords() {
            assertEquals(0, MixedBasicsChallenge.longestLength(List.of("", "")));
        }

        @Test
        void testEmptyWordAndOthers() {
            assertEquals(1, MixedBasicsChallenge.longestLength(List.of("", "a", "")));
        }

        @Test
        void testSpacesCount() {
            assertEquals(5, MixedBasicsChallenge.longestLength(List.of("a b c", "abcd")));
        }

        @Test
        void testSpacesAtTheEndsCount() {
            assertEquals(4, MixedBasicsChallenge.longestLength(List.of(" ab ", "abc")));
        }

        @Test
        void testLongWord() {
            assertEquals(26, MixedBasicsChallenge.longestLength(List.of("a", "abcdefghijklmnopqrstuvwxyz")));
        }
    }

    // ==========================================================
    // CHALLENGE 6: firstThreeLongWords
    // ==========================================================
    @Nested
    class FirstThreeLongWordsTests {

        @Test
        void testMixed() {
            assertEquals(List.of("abcd", "hello", "world"),
                    MixedBasicsChallenge.firstThreeLongWords(List.of("a", "abcd", "xyz", "hello", "world", "java")));
        }

        @Test
        void testFewerThanThree() {
            assertEquals(List.of("abcd"), MixedBasicsChallenge.firstThreeLongWords(List.of("abc", "abcd")));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), MixedBasicsChallenge.firstThreeLongWords(List.of()));
        }

        @Test
        void testNoLongWords() {
            assertEquals(List.of(), MixedBasicsChallenge.firstThreeLongWords(List.of("a", "ab", "abc")));
        }

        @Test
        void testExactlyFourLettersCount() {
            assertEquals(List.of("abcd"), MixedBasicsChallenge.firstThreeLongWords(List.of("abcd")));
        }

        @Test
        void testThreeLettersDoNotCount() {
            assertEquals(List.of(), MixedBasicsChallenge.firstThreeLongWords(List.of("abc")));
        }

        @Test
        void testOnlyThreeAreReturned() {
            assertEquals(List.of("aaaa", "bbbb", "cccc"),
                    MixedBasicsChallenge.firstThreeLongWords(List.of("aaaa", "bbbb", "cccc", "dddd", "eeee")));
        }

        @Test
        void testExactlyThree() {
            assertEquals(List.of("aaaa", "bbbb", "cccc"),
                    MixedBasicsChallenge.firstThreeLongWords(List.of("aaaa", "bbbb", "cccc")));
        }

        @Test
        void testShortWordsAreSkippedBetween() {
            assertEquals(List.of("aaaa", "bbbb", "cccc"),
                    MixedBasicsChallenge.firstThreeLongWords(List.of("x", "aaaa", "y", "bbbb", "z", "cccc", "dddd")));
        }

        @Test
        void testOrderIsKept() {
            assertEquals(List.of("zzzz", "aaaa"), MixedBasicsChallenge.firstThreeLongWords(List.of("zzzz", "aaaa")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(List.of("java", "java", "java"),
                    MixedBasicsChallenge.firstThreeLongWords(List.of("java", "java", "java", "java")));
        }
    }

    // ==========================================================
    // CHALLENGE 7: isPalindrome
    // ==========================================================
    @Nested
    class IsPalindromeTests {

        @Test
        void testSentence() {
            assertTrue(MixedBasicsChallenge.isPalindrome("Never odd or even"));
        }

        @Test
        void testNotPalindrome() {
            assertFalse(MixedBasicsChallenge.isPalindrome("abca"));
        }

        @Test
        void testSingleLetter() {
            assertTrue(MixedBasicsChallenge.isPalindrome("A"));
        }

        @Test
        void testSymbolsCountNormally() {
            assertTrue(MixedBasicsChallenge.isPalindrome("ab,ba"));
            assertFalse(MixedBasicsChallenge.isPalindrome("a,b a"));
        }

        @Test
        void testEmpty() {
            assertTrue(MixedBasicsChallenge.isPalindrome(""));
        }

        @Test
        void testOnlySpaces() {
            assertTrue(MixedBasicsChallenge.isPalindrome("   "));
        }

        @Test
        void testCaseIsIgnored() {
            assertTrue(MixedBasicsChallenge.isPalindrome("Aa"));
            assertTrue(MixedBasicsChallenge.isPalindrome("RaceCar"));
        }

        @Test
        void testTwoDifferentLetters() {
            assertFalse(MixedBasicsChallenge.isPalindrome("ab"));
        }

        @Test
        void testEvenLength() {
            assertTrue(MixedBasicsChallenge.isPalindrome("abba"));
        }

        @Test
        void testOddLength() {
            assertTrue(MixedBasicsChallenge.isPalindrome("abcba"));
        }

        @Test
        void testSpacesAreIgnored() {
            assertTrue(MixedBasicsChallenge.isPalindrome("A man a plan a canal Panama"));
        }

        @Test
        void testSpacesAtTheEnds() {
            assertTrue(MixedBasicsChallenge.isPalindrome("  ab ba  "));
        }

        @Test
        void testDifferentInTheMiddle() {
            assertFalse(MixedBasicsChallenge.isPalindrome("abcda"));
        }

        @Test
        void testDigits() {
            assertTrue(MixedBasicsChallenge.isPalindrome("12321"));
            assertFalse(MixedBasicsChallenge.isPalindrome("1231"));
        }

        @Test
        void testOnlyFirstHalfEqual() {
            assertFalse(MixedBasicsChallenge.isPalindrome("abcabc"));
        }

        @Test
        void testSpaceChangesNothingButLettersDo() {
            assertFalse(MixedBasicsChallenge.isPalindrome("ab c a"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: reverseWords
    // ==========================================================
    @Nested
    class ReverseWordsTests {

        @Test
        void testThreeWords() {
            assertEquals("three two one", MixedBasicsChallenge.reverseWords("one two three"));
        }

        @Test
        void testSingleWord() {
            assertEquals("hello", MixedBasicsChallenge.reverseWords("hello"));
        }

        @Test
        void testEmpty() {
            assertEquals("", MixedBasicsChallenge.reverseWords(""));
        }

        @Test
        void testTwoWords() {
            assertEquals("b a", MixedBasicsChallenge.reverseWords("a b"));
        }

        @Test
        void testLettersInsideWordsKeepTheirOrder() {
            assertEquals("world hello", MixedBasicsChallenge.reverseWords("hello world"));
        }

        @Test
        void testManyWords() {
            assertEquals("5 4 3 2 1", MixedBasicsChallenge.reverseWords("1 2 3 4 5"));
        }

        @Test
        void testRepeatedWords() {
            assertEquals("a a b", MixedBasicsChallenge.reverseWords("b a a"));
        }

        @Test
        void testNoSpacesAtTheEnds() {
            String r = MixedBasicsChallenge.reverseWords("one two three");
            assertEquals(r.trim(), r);
        }

        @Test
        void testPunctuationStaysInTheWord() {
            assertEquals("world! hello,", MixedBasicsChallenge.reverseWords("hello, world!"));
        }

        @Test
        void testReverseTwiceGivesTheSame() {
            String s = "the quick brown fox";
            assertEquals(s, MixedBasicsChallenge.reverseWords(MixedBasicsChallenge.reverseWords(s)));
        }

        @Test
        void testLengthIsKept() {
            assertEquals(13, MixedBasicsChallenge.reverseWords("one two three").length());
        }

        @Test
        void testFourWords() {
            assertEquals("fox brown quick the", MixedBasicsChallenge.reverseWords("the quick brown fox"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: compress
    // ==========================================================
    @Nested
    class CompressTests {

        @Test
        void testMixed() {
            assertEquals("a3b1c2", MixedBasicsChallenge.compress("aaabcc"));
        }

        @Test
        void testAllDifferent() {
            assertEquals("a1b1c1", MixedBasicsChallenge.compress("abc"));
        }

        @Test
        void testCaseMatters() {
            assertEquals("a1A1a1", MixedBasicsChallenge.compress("aAa"));
        }

        @Test
        void testEmpty() {
            assertEquals("", MixedBasicsChallenge.compress(""));
        }

        @Test
        void testSingleLetter() {
            assertEquals("a1", MixedBasicsChallenge.compress("a"));
        }

        @Test
        void testOneLetterManyTimes() {
            assertEquals("a5", MixedBasicsChallenge.compress("aaaaa"));
        }

        @Test
        void testMoreThanNine() {
            assertEquals("a12", MixedBasicsChallenge.compress("aaaaaaaaaaaa"));
        }

        @Test
        void testLastGroupIsWritten() {
            assertEquals("a1b2", MixedBasicsChallenge.compress("abb"));
        }

        @Test
        void testFirstGroupIsWritten() {
            assertEquals("a2b1", MixedBasicsChallenge.compress("aab"));
        }

        @Test
        void testSameLetterAgainLater() {
            assertEquals("a2b1a2", MixedBasicsChallenge.compress("aabaa"));
        }

        @Test
        void testTwoGroups() {
            assertEquals("x3y3", MixedBasicsChallenge.compress("xxxyyy"));
        }

        @Test
        void testUpperAndLowerSideBySide() {
            assertEquals("a2A2", MixedBasicsChallenge.compress("aaAA"));
        }

        @Test
        void testAlternating() {
            assertEquals("a1b1a1b1", MixedBasicsChallenge.compress("abab"));
        }

        @Test
        void testTwoLetters() {
            assertEquals("z2", MixedBasicsChallenge.compress("zz"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: groupByLength
    // ==========================================================
    @Nested
    class GroupByLengthTests {

        @Test
        void testMixed() {
            assertEquals(Map.of(1, List.of("a", "c"), 2, List.of("bb", "dd"), 3, List.of("eee")),
                    MixedBasicsChallenge.groupByLength(List.of("a", "bb", "c", "dd", "eee")));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of(5, List.of("hello")), MixedBasicsChallenge.groupByLength(List.of("hello")));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MixedBasicsChallenge.groupByLength(List.of()));
        }

        @Test
        void testOrderInsideTheListIsKept() {
            assertEquals(Map.of(2, List.of("zz", "aa", "mm")),
                    MixedBasicsChallenge.groupByLength(List.of("zz", "aa", "mm")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(Map.of(1, List.of("a", "a", "a")),
                    MixedBasicsChallenge.groupByLength(List.of("a", "a", "a")));
        }

        @Test
        void testOnlyLengthsThatAppear() {
            assertEquals(2, MixedBasicsChallenge.groupByLength(List.of("a", "abcde")).size());
        }

        @Test
        void testEmptyWordHasLengthZero() {
            assertEquals(Map.of(0, List.of(""), 1, List.of("a")),
                    MixedBasicsChallenge.groupByLength(List.of("", "a")));
        }

        @Test
        void testAllDifferentLengths() {
            assertEquals(Map.of(1, List.of("a"), 2, List.of("bb"), 3, List.of("ccc")),
                    MixedBasicsChallenge.groupByLength(List.of("ccc", "a", "bb")));
        }

        @Test
        void testSpacesCountInTheLength() {
            assertEquals(Map.of(2, List.of(" a", "b ")), MixedBasicsChallenge.groupByLength(List.of(" a", "b ")));
        }

        @Test
        void testLongWords() {
            assertEquals(Map.of(6, List.of("simple", "stream"), 7, List.of("example")),
                    MixedBasicsChallenge.groupByLength(List.of("simple", "example", "stream")));
        }

        @Test
        void testListsKeepTheOriginalOrderWithMixedLengths() {
            Map<Integer, List<String>> r = MixedBasicsChallenge.groupByLength(List.of("bb", "a", "aa", "b", "cc"));
            assertEquals(List.of("bb", "aa", "cc"), r.get(2));
            assertEquals(List.of("a", "b"), r.get(1));
        }
    }

    // ==========================================================
    // CHALLENGE 11: countByFirstLetter
    // ==========================================================
    @Nested
    class CountByFirstLetterTests {

        @Test
        void testMixed() {
            assertEquals(Map.of('a', 2L, 'b', 1L),
                    MixedBasicsChallenge.countByFirstLetter(List.of("apple", "avocado", "banana")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(Map.of('A', 1L, 'a', 1L), MixedBasicsChallenge.countByFirstLetter(List.of("Apple", "apple")));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MixedBasicsChallenge.countByFirstLetter(List.of()));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of('x', 1L), MixedBasicsChallenge.countByFirstLetter(List.of("xylophone")));
        }

        @Test
        void testSameWordRepeated() {
            assertEquals(Map.of('a', 3L), MixedBasicsChallenge.countByFirstLetter(List.of("ant", "ant", "ant")));
        }

        @Test
        void testOnlyFirstLetterCounts() {
            assertEquals(Map.of('a', 1L), MixedBasicsChallenge.countByFirstLetter(List.of("aaaa")));
        }

        @Test
        void testSingleLetterWords() {
            assertEquals(Map.of('a', 1L, 'b', 1L, 'c', 1L),
                    MixedBasicsChallenge.countByFirstLetter(List.of("a", "b", "c")));
        }

        @Test
        void testManyWords() {
            assertEquals(Map.of('a', 2L, 'b', 3L, 'c', 1L),
                    MixedBasicsChallenge.countByFirstLetter(List.of("ab", "b", "bb", "c", "a", "bc")));
        }

        @Test
        void testDigitsAndSymbolsAsFirstCharacter() {
            assertEquals(Map.of('1', 2L, '#', 1L), MixedBasicsChallenge.countByFirstLetter(List.of("1a", "1b", "#c")));
        }

        @Test
        void testValuesAreLong() {
            Long v = MixedBasicsChallenge.countByFirstLetter(List.of("a", "ab")).get('a');
            assertEquals(2L, v.longValue());
        }

        @Test
        void testLastLetterIsNotCounted() {
            assertEquals(Map.of('a', 1L), MixedBasicsChallenge.countByFirstLetter(List.of("abz")));
        }
    }

    // ==========================================================
    // CHALLENGE 12: joinedSortedDistinct
    // ==========================================================
    @Nested
    class JoinedSortedDistinctTests {

        @Test
        void testMixed() {
            assertEquals("a, b", MixedBasicsChallenge.joinedSortedDistinct(List.of("b", "a", "b")));
        }

        @Test
        void testSingleWord() {
            assertEquals("x", MixedBasicsChallenge.joinedSortedDistinct(List.of("x")));
        }

        @Test
        void testUppercaseFirst() {
            assertEquals("A, b", MixedBasicsChallenge.joinedSortedDistinct(List.of("b", "A")));
        }

        @Test
        void testEmpty() {
            assertEquals("", MixedBasicsChallenge.joinedSortedDistinct(List.of()));
        }

        @Test
        void testAlreadySorted() {
            assertEquals("a, b, c", MixedBasicsChallenge.joinedSortedDistinct(List.of("a", "b", "c")));
        }

        @Test
        void testReverseOrder() {
            assertEquals("a, b, c", MixedBasicsChallenge.joinedSortedDistinct(List.of("c", "b", "a")));
        }

        @Test
        void testAllEqual() {
            assertEquals("a", MixedBasicsChallenge.joinedSortedDistinct(List.of("a", "a", "a")));
        }

        @Test
        void testCaseMakesWordsDifferent() {
            assertEquals("A, a", MixedBasicsChallenge.joinedSortedDistinct(List.of("a", "A", "a")));
        }

        @Test
        void testLongerWords() {
            assertEquals("apple, banana, cherry",
                    MixedBasicsChallenge.joinedSortedDistinct(List.of("cherry", "apple", "banana", "apple")));
        }

        @Test
        void testSeparatorHasOneSpace() {
            assertEquals(4, MixedBasicsChallenge.joinedSortedDistinct(List.of("a", "b")).length());
        }

        @Test
        void testNoSeparatorAtTheEnds() {
            String r = MixedBasicsChallenge.joinedSortedDistinct(List.of("b", "a"));
            assertFalse(r.startsWith(","));
            assertFalse(r.endsWith(", "));
        }

        @Test
        void testPrefixWordsComeFirst() {
            assertEquals("ab, abc, b", MixedBasicsChallenge.joinedSortedDistinct(List.of("b", "abc", "ab")));
        }
    }
}