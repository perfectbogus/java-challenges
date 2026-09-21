package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StringIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: reverseWords
    // ==========================================================
    @Nested
    class ReverseWordsTests {

        @Test
        void testBasicSentence() {
            assertEquals("blue is sky the", StringIntermediateChallenge.reverseWords("the sky is blue"));
        }

        @Test
        void testCollapsesRepeatedWhitespace() {
            assertEquals("world hello", StringIntermediateChallenge.reverseWords("  hello   world  "));
        }

        @Test
        void testSingleWord() {
            assertEquals("single", StringIntermediateChallenge.reverseWords("single"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", StringIntermediateChallenge.reverseWords(""));
        }
    }

    // ==========================================================
    // CHALLENGE 2: isPalindrome
    // ==========================================================
    @Nested
    class IsPalindromeTests {

        @Test
        void testSentenceWithPunctuationAndCase() {
            assertTrue(StringIntermediateChallenge.isPalindrome("A man, a plan, a canal: Panama"));
        }

        @Test
        void testNonPalindrome() {
            assertFalse(StringIntermediateChallenge.isPalindrome("race a car"));
        }

        @Test
        void testEmptyStringIsPalindrome() {
            assertTrue(StringIntermediateChallenge.isPalindrome(""));
        }

        @Test
        void testNumericPalindrome() {
            assertTrue(StringIntermediateChallenge.isPalindrome("12321"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: isAnagram
    // ==========================================================
    @Nested
    class IsAnagramTests {

        @Test
        void testSimpleAnagram() {
            assertTrue(StringIntermediateChallenge.isAnagram("listen", "silent"));
        }

        @Test
        void testNotAnagram() {
            assertFalse(StringIntermediateChallenge.isAnagram("hello", "world"));
        }

        @Test
        void testIgnoresCaseAndSpaces() {
            assertTrue(StringIntermediateChallenge.isAnagram("Dormitory", "Dirty Room"));
        }

        @Test
        void testDifferentLengthsAreNotAnagrams() {
            assertFalse(StringIntermediateChallenge.isAnagram("abc", "abcd"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: countVowels
    // ==========================================================
    @Nested
    class CountVowelsTests {

        @Test
        void testMixedCaseSentence() {
            assertEquals(3, StringIntermediateChallenge.countVowels("Hello World"));
        }

        @Test
        void testNoVowels() {
            assertEquals(0, StringIntermediateChallenge.countVowels("xyz"));
        }

        @Test
        void testEmptyString() {
            assertEquals(0, StringIntermediateChallenge.countVowels(""));
        }

        @Test
        void testAllVowelsBothCases() {
            assertEquals(10, StringIntermediateChallenge.countVowels("AEIOUaeiou"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: capitalizeWords
    // ==========================================================
    @Nested
    class CapitalizeWordsTests {

        @Test
        void testLowercaseWords() {
            assertEquals("Hello World", StringIntermediateChallenge.capitalizeWords("hello world"));
        }

        @Test
        void testUppercaseWords() {
            assertEquals("Java Programming", StringIntermediateChallenge.capitalizeWords("JAVA programming"));
        }

        @Test
        void testSingleLetterWord() {
            assertEquals("A", StringIntermediateChallenge.capitalizeWords("a"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", StringIntermediateChallenge.capitalizeWords(""));
        }
    }

    // ==========================================================
    // CHALLENGE 6: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testNonOverlappingCount() {
            assertEquals(3, StringIntermediateChallenge.countOccurrences("ababab", "ab"));
        }

        @Test
        void testOverlappingMatchesCountedOnce() {
            // "aaaa" -> matches at 0-1 and 2-3, non-overlapping -> 2
            assertEquals(2, StringIntermediateChallenge.countOccurrences("aaaa", "aa"));
        }

        @Test
        void testNoMatches() {
            assertEquals(0, StringIntermediateChallenge.countOccurrences("hello", "z"));
        }

        @Test
        void testTargetLongerThanText() {
            assertEquals(0, StringIntermediateChallenge.countOccurrences("ab", "abc"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: compressString
    // ==========================================================
    @Nested
    class CompressStringTests {

        @Test
        void testCompressesWhenShorter() {
            assertEquals("a3b3c2", StringIntermediateChallenge.compressString("aaabbbcc"));
        }

        @Test
        void testReturnsOriginalWhenNotShorter() {
            assertEquals("abcd", StringIntermediateChallenge.compressString("abcd"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", StringIntermediateChallenge.compressString(""));
        }

        @Test
        void testSingleRun() {
            assertEquals("a4", StringIntermediateChallenge.compressString("aaaa"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: isRotation
    // ==========================================================
    @Nested
    class IsRotationTests {

        @Test
        void testValidRotation() {
            assertTrue(StringIntermediateChallenge.isRotation("waterbottle", "erbottlewat"));
        }

        @Test
        void testAnotherValidRotation() {
            assertTrue(StringIntermediateChallenge.isRotation("hello", "llohe"));
        }

        @Test
        void testSameLengthButNotRotation() {
            assertFalse(StringIntermediateChallenge.isRotation("hello", "helol"));
        }

        @Test
        void testDifferentLengthsAreNeverRotations() {
            assertFalse(StringIntermediateChallenge.isRotation("abc", "abcd"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: maskEmail
    // ==========================================================
    @Nested
    class MaskEmailTests {

        @Test
        void testTypicalEmail() {
            assertEquals("j******e@example.com", StringIntermediateChallenge.maskEmail("john.doe@example.com"));
        }

        @Test
        void testTwoCharacterLocalPartUnchanged() {
            assertEquals("ab@example.com", StringIntermediateChallenge.maskEmail("ab@example.com"));
        }

        @Test
        void testSingleCharacterLocalPartUnchanged() {
            assertEquals("a@example.com", StringIntermediateChallenge.maskEmail("a@example.com"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: formatWithThousandsSeparator
    // ==========================================================
    @Nested
    class FormatWithThousandsSeparatorTests {

        @Test
        void testLargeNumber() {
            assertEquals("1,234,567", StringIntermediateChallenge.formatWithThousandsSeparator(1234567));
        }

        @Test
        void testSmallNumberNoSeparatorNeeded() {
            assertEquals("100", StringIntermediateChallenge.formatWithThousandsSeparator(100));
        }

        @Test
        void testZero() {
            assertEquals("0", StringIntermediateChallenge.formatWithThousandsSeparator(0));
        }

        @Test
        void testNegativeNumber() {
            assertEquals("-1,234", StringIntermediateChallenge.formatWithThousandsSeparator(-1234));
        }
    }

    // ==========================================================
    // CHALLENGE 11: joinNonBlank
    // ==========================================================
    @Nested
    class JoinNonBlankTests {

        @Test
        void testSkipsBlankElements() {
            List<String> parts = Arrays.asList("a", "", "b", "   ", "c");
            assertEquals("a-b-c", StringIntermediateChallenge.joinNonBlank(parts, "-"));
        }

        @Test
        void testEmptyListReturnsEmptyString() {
            assertEquals("", StringIntermediateChallenge.joinNonBlank(List.of(), ","));
        }

        @Test
        void testSingleElement() {
            assertEquals("only", StringIntermediateChallenge.joinNonBlank(List.of("only"), ","));
        }
    }

    // ==========================================================
    // CHALLENGE 12: repeatPattern
    // ==========================================================
    @Nested
    class RepeatPatternTests {

        @Test
        void testRepeatsMultipleTimes() {
            assertEquals("ababab", StringIntermediateChallenge.repeatPattern("ab", 3));
        }

        @Test
        void testZeroTimesReturnsEmptyString() {
            assertEquals("", StringIntermediateChallenge.repeatPattern("x", 0));
        }

        @Test
        void testEmptyPattern() {
            assertEquals("", StringIntermediateChallenge.repeatPattern("", 5));
        }
    }

    // ==========================================================
    // CHALLENGE 13: countUppercaseLetters
    // ==========================================================
    @Nested
    class CountUppercaseLettersTests {

        @Test
        void testMixedCaseSentence() {
            assertEquals(2, StringIntermediateChallenge.countUppercaseLetters("Hello World"));
        }

        @Test
        void testAllLowercase() {
            assertEquals(0, StringIntermediateChallenge.countUppercaseLetters("lowercase"));
        }

        @Test
        void testEmptyString() {
            assertEquals(0, StringIntermediateChallenge.countUppercaseLetters(""));
        }

        @Test
        void testAllUppercase() {
            assertEquals(3, StringIntermediateChallenge.countUppercaseLetters("ABC"));
        }
    }

    // ==========================================================
    // CHALLENGE 14: findAllIndexesOf
    // ==========================================================
    @Nested
    class FindAllIndexesOfTests {

        @Test
        void testOverlappingMatches() {
            assertEquals(List.of(0, 1), StringIntermediateChallenge.findAllIndexesOf("aaa", "aa"));
        }

        @Test
        void testNonOverlappingSpacedMatches() {
            assertEquals(List.of(0, 3, 6), StringIntermediateChallenge.findAllIndexesOf("abcabcabc", "abc"));
        }

        @Test
        void testNoMatches() {
            assertEquals(List.of(), StringIntermediateChallenge.findAllIndexesOf("abc", "z"));
        }
    }

    // ==========================================================
    // CHALLENGE 15: toSnakeCase
    // ==========================================================
    @Nested
    class ToSnakeCaseTests {

        @Test
        void testSimpleCamelCase() {
            assertEquals("hello_world", StringIntermediateChallenge.toSnakeCase("helloWorld"));
        }

        @Test
        void testMultipleWords() {
            assertEquals("this_is_a_test", StringIntermediateChallenge.toSnakeCase("thisIsATest"));
        }

        @Test
        void testAlreadySnakeCaseUnchanged() {
            assertEquals("already_snake", StringIntermediateChallenge.toSnakeCase("already_snake"));
        }

        @Test
        void testSingleUppercaseLetter() {
            assertEquals("a", StringIntermediateChallenge.toSnakeCase("A"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", StringIntermediateChallenge.toSnakeCase(""));
        }
    }
}