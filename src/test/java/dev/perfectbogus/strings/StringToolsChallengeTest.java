package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class StringToolsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: capitalizeWords
    // ==========================================================
    @Nested
    class CapitalizeWordsTests {

        @Test
        void testExample() {
            assertEquals("Hello World", StringToolsChallenge.capitalizeWords("hello WORLD"));
        }

        @Test
        void testOneLetter() {
            assertEquals("A", StringToolsChallenge.capitalizeWords("a"));
        }

        @Test
        void testMixedCase() {
            assertEquals("Mixed Case", StringToolsChallenge.capitalizeWords("mIxEd cAsE"));
        }

        @Test
        void testWordThatStartsWithADigit() {
            assertEquals("3d Printer", StringToolsChallenge.capitalizeWords("3d printer"));
            assertEquals("3d Printer", StringToolsChallenge.capitalizeWords("3D PRINTER"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.capitalizeWords(""));
        }

        @Test
        void testAllUppercase() {
            assertEquals("All Caps Here", StringToolsChallenge.capitalizeWords("ALL CAPS HERE"));
        }

        @Test
        void testAlreadyCapitalized() {
            assertEquals("Hello World", StringToolsChallenge.capitalizeWords("Hello World"));
        }

        @Test
        void testOneLetterWords() {
            assertEquals("A B C", StringToolsChallenge.capitalizeWords("a b c"));
        }

        @Test
        void testLettersWithAccents() {
            assertEquals("Élan Vital", StringToolsChallenge.capitalizeWords("élan vital"));
        }

        @Test
        void testSymbolsAtTheStart() {
            assertEquals("!hello -world", StringToolsChallenge.capitalizeWords("!HELLO -WORLD"));
        }

        @Test
        void testSeveralWords() {
            assertEquals("The Quick Brown Fox", StringToolsChallenge.capitalizeWords("the quick brown fox"));
        }

        @Test
        void testBigText() {
            String input = String.join(" ", Collections.nCopies(300_000, "aB"));
            String expected = String.join(" ", Collections.nCopies(300_000, "Ab"));
            assertEquals(expected, StringToolsChallenge.capitalizeWords(input));
        }
    }

    // ==========================================================
    // CHALLENGE 2: countVowels
    // ==========================================================
    @Nested
    class CountVowelsTests {

        @Test
        void testExample() {
            assertEquals(3, StringToolsChallenge.countVowels("Hello World"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, StringToolsChallenge.countVowels(""));
        }

        @Test
        void testUppercase() {
            assertEquals(5, StringToolsChallenge.countVowels("AEIOU"));
        }

        @Test
        void testLowercase() {
            assertEquals(5, StringToolsChallenge.countVowels("aeiou"));
        }

        @Test
        void testNoVowels() {
            assertEquals(0, StringToolsChallenge.countVowels("rhythm"));
        }

        @Test
        void testYIsNotAVowel() {
            assertEquals(0, StringToolsChallenge.countVowels("yY"));
        }

        @Test
        void testSentence() {
            assertEquals(5, StringToolsChallenge.countVowels("Programming is fun"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals(0, StringToolsChallenge.countVowels("123!? "));
        }

        @Test
        void testRepeatedVowels() {
            assertEquals(4, StringToolsChallenge.countVowels("aaaa"));
        }

        @Test
        void testEveryVowelIsCounted() {
            assertEquals(1, StringToolsChallenge.countVowels("a"));
            assertEquals(1, StringToolsChallenge.countVowels("e"));
            assertEquals(1, StringToolsChallenge.countVowels("i"));
            assertEquals(1, StringToolsChallenge.countVowels("o"));
            assertEquals(1, StringToolsChallenge.countVowels("u"));
            assertEquals(1, StringToolsChallenge.countVowels("A"));
            assertEquals(1, StringToolsChallenge.countVowels("E"));
            assertEquals(1, StringToolsChallenge.countVowels("I"));
            assertEquals(1, StringToolsChallenge.countVowels("O"));
            assertEquals(1, StringToolsChallenge.countVowels("U"));
        }

        @Test
        void testLettersWithAccentsAreNotCounted() {
            assertEquals(0, StringToolsChallenge.countVowels("éáí"));
        }

        @Test
        void testBigText() {
            assertEquals(400_000, StringToolsChallenge.countVowels("abcde".repeat(200_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: compress
    // ==========================================================
    @Nested
    class CompressTests {

        @Test
        void testExample() {
            assertEquals("a3b1c2", StringToolsChallenge.compress("aaabcc"));
        }

        @Test
        void testOneLetter() {
            assertEquals("a1", StringToolsChallenge.compress("a"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.compress(""));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals("a1A1", StringToolsChallenge.compress("aA"));
        }

        @Test
        void testGroupWithTwoDigits() {
            assertEquals("a12", StringToolsChallenge.compress("aaaaaaaaaaaa"));
            assertEquals("z10", StringToolsChallenge.compress("zzzzzzzzzz"));
        }

        @Test
        void testAllDifferent() {
            assertEquals("a1b1c1", StringToolsChallenge.compress("abc"));
        }

        @Test
        void testSameLetterInSeveralGroups() {
            assertEquals("a2b2a2", StringToolsChallenge.compress("aabbaa"));
        }

        @Test
        void testBigGroupInTheMiddle() {
            assertEquals("a1b11c1", StringToolsChallenge.compress("abbbbbbbbbbbc"));
        }

        @Test
        void testTwoLetters() {
            assertEquals("a1b1", StringToolsChallenge.compress("ab"));
            assertEquals("a2", StringToolsChallenge.compress("aa"));
        }

        @Test
        void testLastGroupIsWritten() {
            assertEquals("a1b3", StringToolsChallenge.compress("abbb"));
            assertEquals("a3b1", StringToolsChallenge.compress("aaab"));
        }

        @Test
        void testBigGroup() {
            assertEquals("a1000000", StringToolsChallenge.compress("a".repeat(1_000_000)));
        }

        @Test
        void testBigTextWithManyGroups() {
            assertEquals("a1b1".repeat(300_000), StringToolsChallenge.compress("ab".repeat(300_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 4: isRotation
    // ==========================================================
    @Nested
    class IsRotationTests {

        @Test
        void testRotation() {
            assertTrue(StringToolsChallenge.isRotation("abcde", "cdeab"));
        }

        @Test
        void testNotARotation() {
            assertFalse(StringToolsChallenge.isRotation("abcde", "abced"));
        }

        @Test
        void testRepeatedLetters() {
            assertTrue(StringToolsChallenge.isRotation("aab", "aba"));
            assertTrue(StringToolsChallenge.isRotation("aab", "baa"));
        }

        @Test
        void testDifferentLengths() {
            assertFalse(StringToolsChallenge.isRotation("abc", "abcd"));
            assertFalse(StringToolsChallenge.isRotation("abcd", "abc"));
            assertFalse(StringToolsChallenge.isRotation("a", ""));
        }

        @Test
        void testEmptyTexts() {
            assertTrue(StringToolsChallenge.isRotation("", ""));
        }

        @Test
        void testSameText() {
            assertTrue(StringToolsChallenge.isRotation("abc", "abc"));
        }

        @Test
        void testOneLetter() {
            assertTrue(StringToolsChallenge.isRotation("a", "a"));
            assertFalse(StringToolsChallenge.isRotation("a", "b"));
        }

        @Test
        void testTwoLetters() {
            assertTrue(StringToolsChallenge.isRotation("ab", "ba"));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(StringToolsChallenge.isRotation("abc", "BCA"));
            assertTrue(StringToolsChallenge.isRotation("aBc", "Bca"));
        }

        @Test
        void testSameLettersButNotARotation() {
            assertFalse(StringToolsChallenge.isRotation("abcd", "acbd"));
            assertFalse(StringToolsChallenge.isRotation("abcd", "dbca"));
        }

        @Test
        void testTheLengthsMustBeEqual() {
            assertFalse(StringToolsChallenge.isRotation("abc", "bcab"));
            assertFalse(StringToolsChallenge.isRotation("ab", "aba"));
            assertFalse(StringToolsChallenge.isRotation("ab", "b"));
        }

        @Test
        void testBigTexts() {
            String a = "a".repeat(499_999) + "b";
            assertTrue(StringToolsChallenge.isRotation(a, "b" + "a".repeat(499_999)));
            assertTrue(StringToolsChallenge.isRotation(a, "a".repeat(499_998) + "ba"));
            assertFalse(StringToolsChallenge.isRotation(a, "b" + "a".repeat(499_998) + "b"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: longestWord
    // ==========================================================
    @Nested
    class LongestWordTests {

        @Test
        void testTieReturnsTheFirst() {
            assertEquals("quick", StringToolsChallenge.longestWord("the quick brown fox"));
        }

        @Test
        void testLongestAtTheEnd() {
            assertEquals("ccc", StringToolsChallenge.longestWord("a bb ccc"));
        }

        @Test
        void testLongestAtTheStart() {
            assertEquals("ccc", StringToolsChallenge.longestWord("ccc bb a"));
        }

        @Test
        void testSymbolsCount() {
            assertEquals("there!", StringToolsChallenge.longestWord("hi, there!"));
        }

        @Test
        void testOneWord() {
            assertEquals("hello", StringToolsChallenge.longestWord("hello"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.longestWord(""));
        }

        @Test
        void testAllTheSameLength() {
            assertEquals("ab", StringToolsChallenge.longestWord("ab cd ef"));
        }

        @Test
        void testTieAfterAShorterWord() {
            assertEquals("bb", StringToolsChallenge.longestWord("a bb cc"));
        }

        @Test
        void testFirstOfTheLongest() {
            assertEquals("xyz", StringToolsChallenge.longestWord("xyz abc d"));
        }

        @Test
        void testNotTheBiggestAlphabetically() {
            assertEquals("aaaa", StringToolsChallenge.longestWord("zz aaaa y"));
        }

        @Test
        void testOneLetterWords() {
            assertEquals("a", StringToolsChallenge.longestWord("a b c"));
        }

        @Test
        void testEveryCharacterCountsInTheLength() {
            assertEquals("----", StringToolsChallenge.longestWord("abc ----"));
            assertEquals("!!!!!!", StringToolsChallenge.longestWord("hello !!!!!!"));
        }

        @Test
        void testBigText() {
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 100_000; i++) {
                text.append(i == 0 ? "" : " ").append("w").append(i);
            }
            assertEquals("w10000", StringToolsChallenge.longestWord(text.toString()));
        }
    }

    // ==========================================================
    // CHALLENGE 6: caesar
    // ==========================================================
    @Nested
    class CaesarTests {

        @Test
        void testShiftOne() {
            assertEquals("bcd", StringToolsChallenge.caesar("abc", 1));
        }

        @Test
        void testWrapAround() {
            assertEquals("abc", StringToolsChallenge.caesar("xyz", 3));
        }

        @Test
        void testSentence() {
            assertEquals("Khoor, Zruog!", StringToolsChallenge.caesar("Hello, World!", 3));
        }

        @Test
        void testNegativeShift() {
            assertEquals("zab", StringToolsChallenge.caesar("abc", -1));
            assertEquals("uvw", StringToolsChallenge.caesar("xyz", -3));
        }

        @Test
        void testShiftBiggerThanTheAlphabet() {
            assertEquals("bcd", StringToolsChallenge.caesar("abc", 27));
            assertEquals("abc", StringToolsChallenge.caesar("abc", 26));
        }

        @Test
        void testNegativeShiftBiggerThanTheAlphabet() {
            assertEquals("zab", StringToolsChallenge.caesar("abc", -27));
            assertEquals("abc", StringToolsChallenge.caesar("abc", -26));
        }

        @Test
        void testZero() {
            assertEquals("abc", StringToolsChallenge.caesar("abc", 0));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.caesar("", 5));
        }

        @Test
        void testUppercase() {
            assertEquals("ABC", StringToolsChallenge.caesar("XYZ", 3));
            assertEquals("Ba", StringToolsChallenge.caesar("Az", 1));
        }

        @Test
        void testOtherCharactersDoNotChange() {
            assertEquals("b1 c2", StringToolsChallenge.caesar("a1 b2", 1));
            assertEquals("é !", StringToolsChallenge.caesar("é !", 5));
        }

        @Test
        void testHugeShifts() {
            assertEquals("mno", StringToolsChallenge.caesar("abc", 1_000_000_000));
            assertEquals("opq", StringToolsChallenge.caesar("abc", -1_000_000_000));
        }

        @Test
        void testBigText() {
            assertEquals("bcd".repeat(300_000), StringToolsChallenge.caesar("abc".repeat(300_000), 1));
        }
    }

    // ==========================================================
    // CHALLENGE 7: toCamelCase
    // ==========================================================
    @Nested
    class ToCamelCaseTests {

        @Test
        void testTwoWords() {
            assertEquals("helloWorld", StringToolsChallenge.toCamelCase("hello_world"));
        }

        @Test
        void testOneLetterWords() {
            assertEquals("aBC", StringToolsChallenge.toCamelCase("a_b_c"));
            assertEquals("aB", StringToolsChallenge.toCamelCase("a_b"));
        }

        @Test
        void testDigits() {
            assertEquals("item2Name", StringToolsChallenge.toCamelCase("item_2_name"));
        }

        @Test
        void testOneWord() {
            assertEquals("hello", StringToolsChallenge.toCamelCase("hello"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.toCamelCase(""));
        }

        @Test
        void testOneLetter() {
            assertEquals("x", StringToolsChallenge.toCamelCase("x"));
        }

        @Test
        void testThreeWords() {
            assertEquals("getUserName", StringToolsChallenge.toCamelCase("get_user_name"));
        }

        @Test
        void testLongName() {
            assertEquals("aVeryLongVariableName", StringToolsChallenge.toCamelCase("a_very_long_variable_name"));
        }

        @Test
        void testOnlyTheFirstLetterOfEachWordChanges() {
            assertEquals("abCd", StringToolsChallenge.toCamelCase("ab_cd"));
            assertEquals("abCdEf", StringToolsChallenge.toCamelCase("ab_cd_ef"));
        }

        @Test
        void testFirstWordIsNotChanged() {
            assertEquals("firstSecond", StringToolsChallenge.toCamelCase("first_second"));
        }

        @Test
        void testWordsThatStartWithADigit() {
            assertEquals("a1B2", StringToolsChallenge.toCamelCase("a1_b2"));
            assertEquals("v2Final", StringToolsChallenge.toCamelCase("v2_final"));
        }

        @Test
        void testBigText() {
            String snake = String.join("_", Collections.nCopies(300_000, "ab"));
            assertEquals("ab" + "Ab".repeat(299_999), StringToolsChallenge.toCamelCase(snake));
        }
    }

    // ==========================================================
    // CHALLENGE 8: toSnakeCase
    // ==========================================================
    @Nested
    class ToSnakeCaseTests {

        @Test
        void testTwoWords() {
            assertEquals("hello_world", StringToolsChallenge.toSnakeCase("helloWorld"));
        }

        @Test
        void testSeveralUppercaseLetters() {
            assertEquals("a_b_c", StringToolsChallenge.toSnakeCase("aBC"));
        }

        @Test
        void testDigits() {
            assertEquals("item2_name", StringToolsChallenge.toSnakeCase("item2Name"));
        }

        @Test
        void testOneWord() {
            assertEquals("hello", StringToolsChallenge.toSnakeCase("hello"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.toSnakeCase(""));
        }

        @Test
        void testThreeWords() {
            assertEquals("get_user_name", StringToolsChallenge.toSnakeCase("getUserName"));
        }

        @Test
        void testOneLetter() {
            assertEquals("x", StringToolsChallenge.toSnakeCase("x"));
        }

        @Test
        void testTwoLetters() {
            assertEquals("a_b", StringToolsChallenge.toSnakeCase("aB"));
        }

        @Test
        void testLongName() {
            assertEquals("xml_http_request", StringToolsChallenge.toSnakeCase("xmlHttpRequest"));
        }

        @Test
        void testEndsWithAnUppercaseLetter() {
            assertEquals("value_x", StringToolsChallenge.toSnakeCase("valueX"));
        }

        @Test
        void testRoundTripWithCamelCase() {
            assertEquals("fooBarBaz", StringToolsChallenge.toCamelCase(StringToolsChallenge.toSnakeCase("fooBarBaz")));
            assertEquals("a_b_c", StringToolsChallenge.toSnakeCase(StringToolsChallenge.toCamelCase("a_b_c")));
        }

        @Test
        void testBigText() {
            assertEquals("a_b".repeat(100_000), StringToolsChallenge.toSnakeCase("aB".repeat(100_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: longestCommonPrefix
    // ==========================================================
    @Nested
    class LongestCommonPrefixTests {

        @Test
        void testExample() {
            assertEquals("fl", StringToolsChallenge.longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
        }

        @Test
        void testNoPrefix() {
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {"dog", "racecar", "car"}));
        }

        @Test
        void testNoWords() {
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {}));
        }

        @Test
        void testOneWord() {
            assertEquals("abc", StringToolsChallenge.longestCommonPrefix(new String[] {"abc"}));
        }

        @Test
        void testEmptyWord() {
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {"", "a"}));
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {"a", ""}));
        }

        @Test
        void testSameWords() {
            assertEquals("abc", StringToolsChallenge.longestCommonPrefix(new String[] {"abc", "abc", "abc"}));
        }

        @Test
        void testOneWordIsThePrefix() {
            assertEquals("ab", StringToolsChallenge.longestCommonPrefix(new String[] {"ab", "abc"}));
            assertEquals("ab", StringToolsChallenge.longestCommonPrefix(new String[] {"abc", "ab"}));
            assertEquals("a", StringToolsChallenge.longestCommonPrefix(new String[] {"abc", "ab", "a"}));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {"Abc", "abc"}));
        }

        @Test
        void testManyWords() {
            assertEquals("inter", StringToolsChallenge.longestCommonPrefix(
                    new String[] {"interview", "internet", "interval", "internal"}));
        }

        @Test
        void testTheShortestWordIsInTheMiddle() {
            assertEquals("ab", StringToolsChallenge.longestCommonPrefix(new String[] {"abcd", "ab", "abcde"}));
        }

        @Test
        void testOneLetterWords() {
            assertEquals("", StringToolsChallenge.longestCommonPrefix(new String[] {"a", "b"}));
            assertEquals("a", StringToolsChallenge.longestCommonPrefix(new String[] {"a", "a"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"flower", "flow", "flight"};
            StringToolsChallenge.longestCommonPrefix(words);
            assertArrayEquals(new String[] {"flower", "flow", "flight"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "common" + i;
            }
            assertEquals("common", StringToolsChallenge.longestCommonPrefix(words));
        }
    }

    // ==========================================================
    // CHALLENGE 10: maskCard
    // ==========================================================
    @Nested
    class MaskCardTests {

        @Test
        void testCardNumber() {
            assertEquals("************3456", StringToolsChallenge.maskCard("1234567890123456"));
        }

        @Test
        void testFiveCharacters() {
            assertEquals("*2345", StringToolsChallenge.maskCard("12345"));
            assertEquals("*bcde", StringToolsChallenge.maskCard("abcde"));
        }

        @Test
        void testFourCharacters() {
            assertEquals("1234", StringToolsChallenge.maskCard("1234"));
        }

        @Test
        void testShorterThanFour() {
            assertEquals("123", StringToolsChallenge.maskCard("123"));
            assertEquals("1", StringToolsChallenge.maskCard("1"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.maskCard(""));
        }

        @Test
        void testWithSpaces() {
            assertEquals("***************3456", StringToolsChallenge.maskCard("1234 5678 9012 3456"));
        }

        @Test
        void testSixCharacters() {
            assertEquals("**cdef", StringToolsChallenge.maskCard("abcdef"));
        }

        @Test
        void testSameLastDigits() {
            assertEquals("****1111", StringToolsChallenge.maskCard("11111111"));
        }

        @Test
        void testSymbolsAreMaskedToo() {
            assertEquals("****-123", StringToolsChallenge.maskCard("ab#!-123"));
        }

        @Test
        void testStarsInTheTextAreNotSpecial() {
            assertEquals("****cdef", StringToolsChallenge.maskCard("**abcdef"));
        }

        @Test
        void testTheLastFourAreExact() {
            assertEquals("*****6789", StringToolsChallenge.maskCard("123456789"));
        }

        @Test
        void testBigText() {
            String number = "9".repeat(999_996) + "1234";
            assertEquals("*".repeat(999_996) + "1234", StringToolsChallenge.maskCard(number));
        }
    }

    // ==========================================================
    // CHALLENGE 11: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testOverlappingAppearances() {
            assertEquals(3, StringToolsChallenge.countOccurrences("aaaa", "aa"));
        }

        @Test
        void testBanana() {
            assertEquals(2, StringToolsChallenge.countOccurrences("banana", "ana"));
        }

        @Test
        void testOneLetter() {
            assertEquals(2, StringToolsChallenge.countOccurrences("hello", "l"));
        }

        @Test
        void testNoAppearance() {
            assertEquals(0, StringToolsChallenge.countOccurrences("hello", "xyz"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(1, StringToolsChallenge.countOccurrences("Aa", "a"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, StringToolsChallenge.countOccurrences("", "a"));
        }

        @Test
        void testPatternLongerThanTheText() {
            assertEquals(0, StringToolsChallenge.countOccurrences("abc", "abcd"));
            assertEquals(0, StringToolsChallenge.countOccurrences("aaaa", "aaaaa"));
        }

        @Test
        void testPatternEqualToTheText() {
            assertEquals(1, StringToolsChallenge.countOccurrences("aaaa", "aaaa"));
        }

        @Test
        void testSeveralPlaces() {
            assertEquals(3, StringToolsChallenge.countOccurrences("abababa", "aba"));
            assertEquals(2, StringToolsChallenge.countOccurrences("abab", "ab"));
        }

        @Test
        void testMississippi() {
            assertEquals(2, StringToolsChallenge.countOccurrences("mississippi", "issi"));
            assertEquals(4, StringToolsChallenge.countOccurrences("mississippi", "s"));
        }

        @Test
        void testEveryLetter() {
            assertEquals(3, StringToolsChallenge.countOccurrences("aaa", "a"));
        }

        @Test
        void testBigText() {
            assertEquals(999_999, StringToolsChallenge.countOccurrences("a".repeat(1_000_000), "aa"));
            assertEquals(500_000, StringToolsChallenge.countOccurrences("ab".repeat(500_000), "ab"));
            assertEquals(1, StringToolsChallenge.countOccurrences("a".repeat(200_000) + "b", "ab"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: swapCase
    // ==========================================================
    @Nested
    class SwapCaseTests {

        @Test
        void testExample() {
            assertEquals("hELLO wORLD 1", StringToolsChallenge.swapCase("Hello World 1"));
        }

        @Test
        void testMixed() {
            assertEquals("AbC", StringToolsChallenge.swapCase("aBc"));
        }

        @Test
        void testOnlySymbols() {
            assertEquals("123!?", StringToolsChallenge.swapCase("123!?"));
        }

        @Test
        void testEmpty() {
            assertEquals("", StringToolsChallenge.swapCase(""));
        }

        @Test
        void testAllLowercase() {
            assertEquals("ABC", StringToolsChallenge.swapCase("abc"));
        }

        @Test
        void testAllUppercase() {
            assertEquals("abc", StringToolsChallenge.swapCase("ABC"));
        }

        @Test
        void testLettersWithAccents() {
            assertEquals("Éé", StringToolsChallenge.swapCase("éÉ"));
        }

        @Test
        void testSpaces() {
            assertEquals("A B", StringToolsChallenge.swapCase("a b"));
        }

        @Test
        void testTwiceGivesTheOriginal() {
            assertEquals("Hello", StringToolsChallenge.swapCase(StringToolsChallenge.swapCase("Hello")));
        }

        @Test
        void testOneLetter() {
            assertEquals("A", StringToolsChallenge.swapCase("a"));
            assertEquals("z", StringToolsChallenge.swapCase("Z"));
        }

        @Test
        void testFirstAndLastLetters() {
            assertEquals("Az", StringToolsChallenge.swapCase("aZ"));
            assertEquals("aZ", StringToolsChallenge.swapCase("Az"));
        }

        @Test
        void testBigText() {
            assertEquals("Ab".repeat(500_000), StringToolsChallenge.swapCase("aB".repeat(500_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 13: padLeft
    // ==========================================================
    @Nested
    class PadLeftTests {

        @Test
        void testZeros() {
            assertEquals("007", StringToolsChallenge.padLeft("7", 3, '0'));
        }

        @Test
        void testSpaces() {
            assertEquals("  abc", StringToolsChallenge.padLeft("abc", 5, ' '));
        }

        @Test
        void testAlreadyLonger() {
            assertEquals("abcd", StringToolsChallenge.padLeft("abcd", 2, '0'));
        }

        @Test
        void testEmptyText() {
            assertEquals("xxx", StringToolsChallenge.padLeft("", 3, 'x'));
        }

        @Test
        void testSameLength() {
            assertEquals("abc", StringToolsChallenge.padLeft("abc", 3, '0'));
        }

        @Test
        void testOneMore() {
            assertEquals("0abc", StringToolsChallenge.padLeft("abc", 4, '0'));
        }

        @Test
        void testWidthZero() {
            assertEquals("", StringToolsChallenge.padLeft("", 0, 'x'));
            assertEquals("abc", StringToolsChallenge.padLeft("abc", 0, 'x'));
        }

        @Test
        void testOtherCharacters() {
            assertEquals("--ab", StringToolsChallenge.padLeft("ab", 4, '-'));
            assertEquals("éa", StringToolsChallenge.padLeft("a", 2, 'é'));
        }

        @Test
        void testThePaddingGoesAtTheStart() {
            assertEquals("00123", StringToolsChallenge.padLeft("123", 5, '0'));
            assertFalse(StringToolsChallenge.padLeft("123", 5, '0').endsWith("0"));
        }

        @Test
        void testTheTextWithThePadCharacter() {
            assertEquals("000000", StringToolsChallenge.padLeft("000", 6, '0'));
        }

        @Test
        void testOneCharacterTextAndWidthOne() {
            assertEquals("5", StringToolsChallenge.padLeft("5", 1, 'x'));
        }

        @Test
        void testBigWidth() {
            assertEquals("0".repeat(999_999) + "x", StringToolsChallenge.padLeft("x", 1_000_000, '0'));
            assertEquals(1_000_000, StringToolsChallenge.padLeft("", 1_000_000, 'q').length());
        }
    }

    // ==========================================================
    // CHALLENGE 14: isPangram
    // ==========================================================
    @Nested
    class IsPangramTests {

        @Test
        void testClassicPangram() {
            assertTrue(StringToolsChallenge.isPangram("The quick brown fox jumps over the lazy dog"));
        }

        @Test
        void testShortText() {
            assertFalse(StringToolsChallenge.isPangram("hello"));
        }

        @Test
        void testMissingTheLastLetter() {
            assertFalse(StringToolsChallenge.isPangram("abcdefghijklmnopqrstuvwxy"));
        }

        @Test
        void testMissingTheFirstLetter() {
            assertFalse(StringToolsChallenge.isPangram("bcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void testEmpty() {
            assertFalse(StringToolsChallenge.isPangram(""));
        }

        @Test
        void testAlphabet() {
            assertTrue(StringToolsChallenge.isPangram("abcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void testUppercaseAlphabet() {
            assertTrue(StringToolsChallenge.isPangram("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }

        @Test
        void testMixedCase() {
            assertTrue(StringToolsChallenge.isPangram("ABCDEFGHIJKLMnopqrstuvwxyz"));
            assertTrue(StringToolsChallenge.isPangram("Pack my box with five dozen liquor jugs"));
        }

        @Test
        void testOtherCharactersAreAllowed() {
            assertTrue(StringToolsChallenge.isPangram("1234567890 abcdefghijklmnopqrstuvwxyz !?"));
        }

        @Test
        void testAlmostAPangram() {
            assertFalse(StringToolsChallenge.isPangram("The quick brown fox jumps over the lazy cat"));
        }

        @Test
        void testLettersWithAccentsDoNotCount() {
            assertFalse(StringToolsChallenge.isPangram("abcdefghijklmnopqrstuvwxyé"));
            assertFalse(StringToolsChallenge.isPangram("abcdefghijklmnopqrstuvwxyÿ"));
        }

        @Test
        void testBigText() {
            assertTrue(StringToolsChallenge.isPangram("a".repeat(1_000_000) + "bcdefghijklmnopqrstuvwxyz"));
            assertFalse(StringToolsChallenge.isPangram("a".repeat(1_000_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 15: splitIntoChunks
    // ==========================================================
    @Nested
    class SplitIntoChunksTests {

        @Test
        void testLastPartIsShorter() {
            assertArrayEquals(new String[] {"abc", "def", "g"}, StringToolsChallenge.splitIntoChunks("abcdefg", 3));
        }

        @Test
        void testExactParts() {
            assertArrayEquals(new String[] {"abc", "def"}, StringToolsChallenge.splitIntoChunks("abcdef", 3));
        }

        @Test
        void testSizeBiggerThanTheText() {
            assertArrayEquals(new String[] {"ab"}, StringToolsChallenge.splitIntoChunks("ab", 5));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new String[] {}, StringToolsChallenge.splitIntoChunks("", 3));
        }

        @Test
        void testSizeOne() {
            assertArrayEquals(new String[] {"a", "b", "c"}, StringToolsChallenge.splitIntoChunks("abc", 1));
        }

        @Test
        void testSizeEqualToTheText() {
            assertArrayEquals(new String[] {"abc"}, StringToolsChallenge.splitIntoChunks("abc", 3));
        }

        @Test
        void testTwoParts() {
            assertArrayEquals(new String[] {"abcd", "efgh"}, StringToolsChallenge.splitIntoChunks("abcdefgh", 4));
        }

        @Test
        void testOneCharacterLeft() {
            assertArrayEquals(new String[] {"abcd", "efgh", "i"},
                    StringToolsChallenge.splitIntoChunks("abcdefghi", 4));
            assertArrayEquals(new String[] {"ab", "cd", "e"}, StringToolsChallenge.splitIntoChunks("abcde", 2));
        }

        @Test
        void testSpacesAreKept() {
            assertArrayEquals(new String[] {"a ", "b ", "c"}, StringToolsChallenge.splitIntoChunks("a b c", 2));
        }

        @Test
        void testOneCharacterText() {
            assertArrayEquals(new String[] {"a"}, StringToolsChallenge.splitIntoChunks("a", 1));
            assertArrayEquals(new String[] {"a"}, StringToolsChallenge.splitIntoChunks("a", 100));
        }

        @Test
        void testNumberOfParts() {
            assertEquals(4, StringToolsChallenge.splitIntoChunks("abcdefghij", 3).length);
            assertEquals(3, StringToolsChallenge.splitIntoChunks("abcdefghi", 3).length);
        }

        @Test
        void testBigText() {
            String[] result = StringToolsChallenge.splitIntoChunks("ab".repeat(150_000), 3);
            assertEquals(100_000, result.length);
            assertEquals("aba", result[0]);
            assertEquals("bab", result[1]);
            assertEquals("bab", result[99_999]);
            assertEquals(300_000, StringToolsChallenge.splitIntoChunks("a".repeat(300_000), 1).length);
        }
    }
}