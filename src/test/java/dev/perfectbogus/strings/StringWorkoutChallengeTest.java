package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class StringWorkoutChallengeTest {

    // ==========================================================
    // CHALLENGE 1: countVowels
    // ==========================================================
    @Nested
    class CountVowelsTests {

        @Test
        void testSentence() {
            assertEquals(3, StringWorkoutChallenge.countVowels("Hello World"));
        }

        @Test
        void testAllUppercaseVowels() {
            assertEquals(5, StringWorkoutChallenge.countVowels("AEIOU"));
        }

        @Test
        void testNoVowels() {
            assertEquals(0, StringWorkoutChallenge.countVowels("rhythm"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, StringWorkoutChallenge.countVowels(""));
        }

        @Test
        void testAllLowercaseVowels() {
            assertEquals(5, StringWorkoutChallenge.countVowels("aeiou"));
        }

        @Test
        void testRepeatedVowels() {
            assertEquals(4, StringWorkoutChallenge.countVowels("aaaa"));
        }

        @Test
        void testMixedCase() {
            assertEquals(4, StringWorkoutChallenge.countVowels("AbEcIdO"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals(0, StringWorkoutChallenge.countVowels("123 !?"));
        }

        @Test
        void testYIsNotAVowel() {
            assertEquals(0, StringWorkoutChallenge.countVowels("yY"));
        }

        @Test
        void testEachVowelIsCounted() {
            assertEquals(1, StringWorkoutChallenge.countVowels("a"));
            assertEquals(1, StringWorkoutChallenge.countVowels("e"));
            assertEquals(1, StringWorkoutChallenge.countVowels("i"));
            assertEquals(1, StringWorkoutChallenge.countVowels("o"));
            assertEquals(1, StringWorkoutChallenge.countVowels("u"));
            assertEquals(1, StringWorkoutChallenge.countVowels("A"));
            assertEquals(1, StringWorkoutChallenge.countVowels("E"));
            assertEquals(1, StringWorkoutChallenge.countVowels("I"));
            assertEquals(1, StringWorkoutChallenge.countVowels("O"));
            assertEquals(1, StringWorkoutChallenge.countVowels("U"));
        }

        @Test
        void testConsonantsAreNotCounted() {
            assertEquals(0, StringWorkoutChallenge.countVowels("bcdfghjklmnpqrstvwxyz"));
        }

        @Test
        void testSpacesBetweenVowels() {
            assertEquals(3, StringWorkoutChallenge.countVowels("a e i"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: capitalizeWords
    // ==========================================================
    @Nested
    class CapitalizeWordsTests {

        @Test
        void testMixedCase() {
            assertEquals("Hello World", StringWorkoutChallenge.capitalizeWords("hELLO wORLD"));
        }

        @Test
        void testSingleLetterWord() {
            assertEquals("A Bc", StringWorkoutChallenge.capitalizeWords("a bc"));
        }

        @Test
        void testUppercaseWord() {
            assertEquals("Java", StringWorkoutChallenge.capitalizeWords("JAVA"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringWorkoutChallenge.capitalizeWords(""));
        }

        @Test
        void testAlreadyCorrect() {
            assertEquals("Hello World", StringWorkoutChallenge.capitalizeWords("Hello World"));
        }

        @Test
        void testAllLowercase() {
            assertEquals("The Quick Brown Fox", StringWorkoutChallenge.capitalizeWords("the quick brown fox"));
        }

        @Test
        void testSingleLetter() {
            assertEquals("A", StringWorkoutChallenge.capitalizeWords("a"));
            assertEquals("A", StringWorkoutChallenge.capitalizeWords("A"));
        }

        @Test
        void testRestOfWordBecomesLowercase() {
            assertEquals("Abcdef", StringWorkoutChallenge.capitalizeWords("aBCDEF"));
        }

        @Test
        void testSpacesAreKept() {
            assertEquals("A B C D", StringWorkoutChallenge.capitalizeWords("a b c d"));
        }

        @Test
        void testWordsWithDigitsAndSymbols() {
            assertEquals("1abc A-b", StringWorkoutChallenge.capitalizeWords("1ABC a-b"));
        }

        @Test
        void testLastWordIsAlsoChanged() {
            assertEquals("One Two", StringWorkoutChallenge.capitalizeWords("one two"));
        }

        @Test
        void testManyWords() {
            assertEquals("A B C D E F", StringWorkoutChallenge.capitalizeWords("a b c d e f"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: isAnagram
    // ==========================================================
    @Nested
    class IsAnagramTests {

        @Test
        void testCaseIsIgnored() {
            assertTrue(StringWorkoutChallenge.isAnagram("Listen", "Silent"));
        }

        @Test
        void testSpacesAreIgnored() {
            assertTrue(StringWorkoutChallenge.isAnagram("a gentleman", "elegant man"));
        }

        @Test
        void testExtraLetter() {
            assertFalse(StringWorkoutChallenge.isAnagram("abc", "abcc"));
        }

        @Test
        void testSameLettersDifferentCounts() {
            assertFalse(StringWorkoutChallenge.isAnagram("aab", "abb"));
        }

        @Test
        void testBothEmpty() {
            assertTrue(StringWorkoutChallenge.isAnagram("", ""));
        }

        @Test
        void testOnlySpaces() {
            assertTrue(StringWorkoutChallenge.isAnagram("   ", ""));
        }

        @Test
        void testEqualTexts() {
            assertTrue(StringWorkoutChallenge.isAnagram("abc", "abc"));
        }

        @Test
        void testReversed() {
            assertTrue(StringWorkoutChallenge.isAnagram("abcd", "dcba"));
        }

        @Test
        void testDifferentLetters() {
            assertFalse(StringWorkoutChallenge.isAnagram("abc", "abd"));
        }

        @Test
        void testOneEmpty() {
            assertFalse(StringWorkoutChallenge.isAnagram("a", ""));
        }

        @Test
        void testSymbolsCountNormally() {
            assertTrue(StringWorkoutChallenge.isAnagram("a-b", "b-a"));
            assertFalse(StringWorkoutChallenge.isAnagram("a-b", "ab"));
        }

        @Test
        void testDigitsCountNormally() {
            assertTrue(StringWorkoutChallenge.isAnagram("a1b2", "2b1a"));
            assertFalse(StringWorkoutChallenge.isAnagram("a1", "a2"));
        }

        @Test
        void testMixedCaseBothSides() {
            assertTrue(StringWorkoutChallenge.isAnagram("AbC", "cBa"));
        }

        @Test
        void testRepeatedLettersEverywhere() {
            assertTrue(StringWorkoutChallenge.isAnagram("aabbcc", "abcabc"));
            assertFalse(StringWorkoutChallenge.isAnagram("aabbcc", "aabbbc"));
        }

        @Test
        void testSameLengthDifferentLetters() {
            assertFalse(StringWorkoutChallenge.isAnagram("abcd", "abce"));
        }

        @Test
        void testSameLengthAndSameSumOfLetters() {
            assertFalse(StringWorkoutChallenge.isAnagram("ad", "bc"));
        }

        @Test
        void testSpaceCountDoesNotMatter() {
            assertTrue(StringWorkoutChallenge.isAnagram("a  b", "ba"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: removeDuplicateChars
    // ==========================================================
    @Nested
    class RemoveDuplicateCharsTests {

        @Test
        void testBanana() {
            assertEquals("ban", StringWorkoutChallenge.removeDuplicateChars("banana"));
        }

        @Test
        void testCaseMatters() {
            assertEquals("aA", StringWorkoutChallenge.removeDuplicateChars("aAa"));
        }

        @Test
        void testSpaceCounts() {
            assertEquals("a b", StringWorkoutChallenge.removeDuplicateChars("a b a"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringWorkoutChallenge.removeDuplicateChars(""));
        }

        @Test
        void testNoDuplicates() {
            assertEquals("abc", StringWorkoutChallenge.removeDuplicateChars("abc"));
        }

        @Test
        void testAllEqual() {
            assertEquals("a", StringWorkoutChallenge.removeDuplicateChars("aaaa"));
        }

        @Test
        void testFirstAppearanceIsKept() {
            assertEquals("cab", StringWorkoutChallenge.removeDuplicateChars("cabbac"));
        }

        @Test
        void testOrderIsKept() {
            assertEquals("zyx", StringWorkoutChallenge.removeDuplicateChars("zyxzyx"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals("1a-", StringWorkoutChallenge.removeDuplicateChars("1a-1a-"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals("x", StringWorkoutChallenge.removeDuplicateChars("x"));
        }

        @Test
        void testSpacesOnly() {
            assertEquals(" ", StringWorkoutChallenge.removeDuplicateChars("   "));
        }

        @Test
        void testSentence() {
            assertEquals("helo wrd", StringWorkoutChallenge.removeDuplicateChars("hello world"));
        }

        @Test
        void testNotJustNeighbours() {
            assertEquals("ab", StringWorkoutChallenge.removeDuplicateChars("abab"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: longestWord
    // ==========================================================
    @Nested
    class LongestWordTests {

        @Test
        void testSentence() {
            assertEquals("programming", StringWorkoutChallenge.longestWord("I love programming"));
        }

        @Test
        void testTieFirstWins() {
            assertEquals("one", StringWorkoutChallenge.longestWord("one two six"));
        }

        @Test
        void testSingleWord() {
            assertEquals("a", StringWorkoutChallenge.longestWord("a"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringWorkoutChallenge.longestWord(""));
        }

        @Test
        void testLongestFirst() {
            assertEquals("abcd", StringWorkoutChallenge.longestWord("abcd ab a"));
        }

        @Test
        void testLongestLast() {
            assertEquals("abcd", StringWorkoutChallenge.longestWord("a ab abcd"));
        }

        @Test
        void testTieInTheMiddle() {
            assertEquals("bb", StringWorkoutChallenge.longestWord("a bb cc d"));
        }

        @Test
        void testTieBetweenLastTwo() {
            assertEquals("bbb", StringWorkoutChallenge.longestWord("a bbb ccc"));
        }

        @Test
        void testPunctuationCounts() {
            assertEquals("hi!!!", StringWorkoutChallenge.longestWord("hi hi!!! abc"));
        }

        @Test
        void testSecondWordIsLonger() {
            assertEquals("bb", StringWorkoutChallenge.longestWord("a bb"));
        }

        @Test
        void testAllSameLength() {
            assertEquals("x", StringWorkoutChallenge.longestWord("x y z"));
        }

        @Test
        void testWordIsNotChanged() {
            assertEquals("Hello", StringWorkoutChallenge.longestWord("Hello is a"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testTwoOccurrences() {
            assertEquals(2, StringWorkoutChallenge.countOccurrences("abcabc", "bc"));
        }

        @Test
        void testNoOverlapping() {
            assertEquals(2, StringWorkoutChallenge.countOccurrences("aaaa", "aa"));
        }

        @Test
        void testOverlapAtTheEnd() {
            assertEquals(1, StringWorkoutChallenge.countOccurrences("aaa", "aa"));
        }

        @Test
        void testNoOccurrences() {
            assertEquals(0, StringWorkoutChallenge.countOccurrences("abc", "d"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, StringWorkoutChallenge.countOccurrences("", "a"));
        }

        @Test
        void testCaseMatters() {
            assertEquals(1, StringWorkoutChallenge.countOccurrences("aAa", "A"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(3, StringWorkoutChallenge.countOccurrences("banana", "a"));
        }

        @Test
        void testWholeText() {
            assertEquals(1, StringWorkoutChallenge.countOccurrences("abc", "abc"));
        }

        @Test
        void testPartLongerThanText() {
            assertEquals(0, StringWorkoutChallenge.countOccurrences("ab", "abc"));
        }

        @Test
        void testAnaInBanana() {
            assertEquals(1, StringWorkoutChallenge.countOccurrences("banana", "ana"));
        }

        @Test
        void testSixLetters() {
            assertEquals(3, StringWorkoutChallenge.countOccurrences("aaaaaa", "aa"));
            assertEquals(2, StringWorkoutChallenge.countOccurrences("aaaaaa", "aaa"));
            assertEquals(1, StringWorkoutChallenge.countOccurrences("aaaaa", "aaa"));
        }

        @Test
        void testSpecialCharactersAreNormal() {
            assertEquals(2, StringWorkoutChallenge.countOccurrences("a.a.", "."));
            assertEquals(0, StringWorkoutChallenge.countOccurrences("abc", "."));
        }

        @Test
        void testWordsInSentence() {
            assertEquals(2, StringWorkoutChallenge.countOccurrences("the cat and the dog", "the"));
        }

        @Test
        void testOccurrenceAtTheStartAndEnd() {
            assertEquals(2, StringWorkoutChallenge.countOccurrences("abxxab", "ab"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: padLeft
    // ==========================================================
    @Nested
    class PadLeftTests {

        @Test
        void testZeros() {
            assertEquals("00042", StringWorkoutChallenge.padLeft("42", 5, '0'));
        }

        @Test
        void testLongerThanWidth() {
            assertEquals("hello", StringWorkoutChallenge.padLeft("hello", 3, '*'));
        }

        @Test
        void testSameAsWidth() {
            assertEquals("abc", StringWorkoutChallenge.padLeft("abc", 3, '-'));
        }

        @Test
        void testEmptyText() {
            assertEquals("xx", StringWorkoutChallenge.padLeft("", 2, 'x'));
        }

        @Test
        void testWidthZero() {
            assertEquals("abc", StringWorkoutChallenge.padLeft("abc", 0, '-'));
        }

        @Test
        void testNegativeWidth() {
            assertEquals("abc", StringWorkoutChallenge.padLeft("abc", -5, '-'));
        }

        @Test
        void testOneCharacterMissing() {
            assertEquals("-abc", StringWorkoutChallenge.padLeft("abc", 4, '-'));
        }

        @Test
        void testSpaceAsFill() {
            assertEquals("  ab", StringWorkoutChallenge.padLeft("ab", 4, ' '));
        }

        @Test
        void testLengthOfResult() {
            assertEquals(10, StringWorkoutChallenge.padLeft("abc", 10, '.').length());
        }

        @Test
        void testTextStaysAtTheEnd() {
            assertTrue(StringWorkoutChallenge.padLeft("abc", 6, '#').endsWith("abc"));
            assertTrue(StringWorkoutChallenge.padLeft("abc", 6, '#').startsWith("###"));
        }

        @Test
        void testEmptyTextAndWidthZero() {
            assertEquals("", StringWorkoutChallenge.padLeft("", 0, 'x'));
        }

        @Test
        void testEmptyTextAndNegativeWidth() {
            assertEquals("", StringWorkoutChallenge.padLeft("", -1, 'x'));
        }

        @Test
        void testFillIsNotAddedToTheEnd() {
            assertEquals("0000a", StringWorkoutChallenge.padLeft("a", 5, '0'));
        }

        @Test
        void testFillIsTheSameAsTextCharacter() {
            assertEquals("aaaaa", StringWorkoutChallenge.padLeft("aa", 5, 'a'));
        }
    }

    // ==========================================================
    // CHALLENGE 8: mostFrequentChar
    // ==========================================================
    @Nested
    class MostFrequentCharTests {

        @Test
        void testFirstIsMostFrequent() {
            assertEquals('a', StringWorkoutChallenge.mostFrequentChar("abca"));
        }

        @Test
        void testMiddleCharacter() {
            assertEquals('b', StringWorkoutChallenge.mostFrequentChar("abcb"));
        }

        @Test
        void testTieFirstInTextWins() {
            assertEquals('a', StringWorkoutChallenge.mostFrequentChar("aabb"));
        }

        @Test
        void testTieOtherOrder() {
            assertEquals('b', StringWorkoutChallenge.mostFrequentChar("bbaa"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals('x', StringWorkoutChallenge.mostFrequentChar("x"));
        }

        @Test
        void testAllDifferentFirstWins() {
            assertEquals('z', StringWorkoutChallenge.mostFrequentChar("zyx"));
        }

        @Test
        void testCaseMatters() {
            assertEquals('a', StringWorkoutChallenge.mostFrequentChar("aAA a"));
        }

        @Test
        void testUppercaseAndLowercaseAreDifferent() {
            assertEquals('A', StringWorkoutChallenge.mostFrequentChar("aAA"));
        }

        @Test
        void testSpaceCanWin() {
            assertEquals(' ', StringWorkoutChallenge.mostFrequentChar("a b c d"));
        }

        @Test
        void testLastCharacterWins() {
            assertEquals('c', StringWorkoutChallenge.mostFrequentChar("abccc"));
        }

        @Test
        void testTieInterleaved() {
            assertEquals('a', StringWorkoutChallenge.mostFrequentChar("abab"));
            assertEquals('b', StringWorkoutChallenge.mostFrequentChar("baba"));
        }

        @Test
        void testThreeWayTie() {
            assertEquals('c', StringWorkoutChallenge.mostFrequentChar("cab"));
        }

        @Test
        void testLaterCharacterAppearsMore() {
            assertEquals('b', StringWorkoutChallenge.mostFrequentChar("abb"));
        }

        @Test
        void testSecondGroupMoreFrequent() {
            assertEquals('b', StringWorkoutChallenge.mostFrequentChar("aabbb"));
        }

        @Test
        void testDigits() {
            assertEquals('2', StringWorkoutChallenge.mostFrequentChar("1223"));
        }

        @Test
        void testSentence() {
            assertEquals('l', StringWorkoutChallenge.mostFrequentChar("hello world"));
        }
    }
}