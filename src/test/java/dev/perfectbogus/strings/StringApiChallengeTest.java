package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringApiChallengeTest {

    // ==========================================================
    // CHALLENGE 1: isPalindrome
    // ==========================================================
    @Nested
    class IsPalindromeTests {

        @Test
        void testIgnoresTheCase() {
            assertTrue(StringApiChallenge.isPalindrome("Level"));
        }

        @Test
        void testSpaceInTheMiddle() {
            assertTrue(StringApiChallenge.isPalindrome("ab ba"));
        }

        @Test
        void testNotAPalindrome() {
            assertFalse(StringApiChallenge.isPalindrome("abca"));
        }

        @Test
        void testEmptyText() {
            assertTrue(StringApiChallenge.isPalindrome(""));
        }

        @Test
        void testSingleCharacter() {
            assertTrue(StringApiChallenge.isPalindrome("a"));
        }

        @Test
        void testTwoEqualCharactersInDifferentCase() {
            assertTrue(StringApiChallenge.isPalindrome("Aa"));
        }

        @Test
        void testSpacesCount() {
            assertFalse(StringApiChallenge.isPalindrome("a b"));
            assertFalse(StringApiChallenge.isPalindrome("A man"));
        }

        @Test
        void testMixedCase() {
            assertTrue(StringApiChallenge.isPalindrome("RaceCar"));
            assertTrue(StringApiChallenge.isPalindrome("abBA"));
        }

        @Test
        void testSpacesAreNotIgnored() {
            assertFalse(StringApiChallenge.isPalindrome("ab a"));
            assertFalse(StringApiChallenge.isPalindrome(" aba"));
        }

        @Test
        void testPunctuationIsNotIgnored() {
            assertFalse(StringApiChallenge.isPalindrome("ab,a"));
            assertFalse(StringApiChallenge.isPalindrome("A man, a plan, a canal: Panama"));
        }

        @Test
        void testDigits() {
            assertTrue(StringApiChallenge.isPalindrome("12321"));
            assertFalse(StringApiChallenge.isPalindrome("1231"));
        }

        @Test
        void testOnlyTheMiddleDiffers() {
            assertFalse(StringApiChallenge.isPalindrome("abcdeba"));
        }

        @Test
        void testOnlyTheEndsDiffer() {
            assertFalse(StringApiChallenge.isPalindrome("xabay"));
        }

        @Test
        void testEvenLength() {
            assertTrue(StringApiChallenge.isPalindrome("abccba"));
            assertFalse(StringApiChallenge.isPalindrome("abccbb"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: reverseWords
    // ==========================================================
    @Nested
    class ReverseWordsTests {

        @Test
        void testThreeWords() {
            assertEquals("three two one", StringApiChallenge.reverseWords("one two three"));
        }

        @Test
        void testSingleWord() {
            assertEquals("solo", StringApiChallenge.reverseWords("solo"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.reverseWords(""));
        }

        @Test
        void testTwoWords() {
            assertEquals("b a", StringApiChallenge.reverseWords("a b"));
        }

        @Test
        void testFourWords() {
            assertEquals("four three two one", StringApiChallenge.reverseWords("one two three four"));
        }

        @Test
        void testLettersInsideTheWordsAreNotReversed() {
            assertEquals("x hello", StringApiChallenge.reverseWords("hello x"));
        }

        @Test
        void testNoSpacesAtTheBeginningOrTheEnd() {
            String result = StringApiChallenge.reverseWords("a b c");
            assertEquals("c b a", result);
            assertEquals(result.trim(), result);
        }

        @Test
        void testRepeatedWords() {
            assertEquals("go go stop", StringApiChallenge.reverseWords("stop go go"));
        }

        @Test
        void testCaseIsKept() {
            assertEquals("World Hello", StringApiChallenge.reverseWords("Hello World"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testBanana() {
            assertEquals(2, StringApiChallenge.countOccurrences("banana", "an"));
        }

        @Test
        void testBananaImproved() {
            assertEquals(2, StringApiChallenge.countOccurrencesImproved("banana", "an"));
        }

        @Test
        void testNonOverlappingEven() {
            assertEquals(2, StringApiChallenge.countOccurrences("aaaa", "aa"));
        }

        @Test
        void testNonOverlappingOdd() {
            assertEquals(1, StringApiChallenge.countOccurrences("aaa", "aa"));
        }

        @Test
        void testNotFound() {
            assertEquals(0, StringApiChallenge.countOccurrences("abc", "d"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, StringApiChallenge.countOccurrences("", "a"));
        }

        @Test
        void testWholeText() {
            assertEquals(1, StringApiChallenge.countOccurrences("abc", "abc"));
        }

        @Test
        void testTwoCopies() {
            assertEquals(2, StringApiChallenge.countOccurrences("abcabc", "abc"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(1, StringApiChallenge.countOccurrences("Abab", "ab"));
        }

        @Test
        void testOverlapIsNotCounted() {
            assertEquals(1, StringApiChallenge.countOccurrences("ababab", "aba"));
        }

        @Test
        void testSpecialCharactersAreLiteral() {
            assertEquals(2, StringApiChallenge.countOccurrences("a.a.a", "."));
        }

        @Test
        void testPartLongerThanText() {
            assertEquals(0, StringApiChallenge.countOccurrences("ab", "abc"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(2, StringApiChallenge.countOccurrences("mississippi", "p"));
            assertEquals(4, StringApiChallenge.countOccurrences("mississippi", "s"));
        }

        @Test
        void testAtTheBeginningAndAtTheEnd() {
            assertEquals(2, StringApiChallenge.countOccurrences("xaax", "x"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: capitalize
    // ==========================================================
    @Nested
    class CapitalizeTests {

        @Test
        void testMixedCase() {
            assertEquals("Hello", StringApiChallenge.capitalize("hELLO"));
        }

        @Test
        void testMixedCaseImproved() {
            assertEquals("Hello", StringApiChallenge.capitalizeImproved("hELLO"));
        }

        @Test
        void testSingleLetter() {
            assertEquals("A", StringApiChallenge.capitalize("a"));
        }

        @Test
        void testDigitsAtTheStart() {
            assertEquals("123abc", StringApiChallenge.capitalize("123ABC"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.capitalize(""));
        }

        @Test
        void testSpaceAtTheStart() {
            assertEquals(" hi", StringApiChallenge.capitalize(" HI"));
        }

        @Test
        void testAllUppercase() {
            assertEquals("Hello", StringApiChallenge.capitalize("HELLO"));
        }

        @Test
        void testAllLowercase() {
            assertEquals("Hello", StringApiChallenge.capitalize("hello"));
        }

        @Test
        void testAlreadyCapitalized() {
            assertEquals("Hello", StringApiChallenge.capitalize("Hello"));
        }

        @Test
        void testSingleUppercaseLetter() {
            assertEquals("Z", StringApiChallenge.capitalize("Z"));
        }

        @Test
        void testOnlyTheFirstLetterIsUppercase() {
            assertEquals("Hello world", StringApiChallenge.capitalize("hello WORLD"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: titleCase
    // ==========================================================
    @Nested
    class TitleCaseTests {

        @Test
        void testTwoWords() {
            assertEquals("Hello World", StringApiChallenge.titleCase("hello WORLD"));
        }

        @Test
        void testSingleLetters() {
            assertEquals("A B C", StringApiChallenge.titleCase("a b c"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.titleCase(""));
        }

        @Test
        void testSingleWord() {
            assertEquals("One", StringApiChallenge.titleCase("one"));
        }

        @Test
        void testMixedCase() {
            assertEquals("Mixed Case Words", StringApiChallenge.titleCase("mIxEd cAsE words"));
        }

        @Test
        void testDigitsAtTheStartOfAWord() {
            assertEquals("123 Abc", StringApiChallenge.titleCase("123 abc"));
        }

        @Test
        void testAllUppercase() {
            assertEquals("Abc Def", StringApiChallenge.titleCase("ABC DEF"));
        }

        @Test
        void testNoSpacesAtTheEnd() {
            String result = StringApiChallenge.titleCase("a b");
            assertEquals("A B", result);
            assertEquals(3, result.length());
        }

        @Test
        void testManyWords() {
            assertEquals("The Quick Brown Fox", StringApiChallenge.titleCase("the quick brown fox"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: padLeft
    // ==========================================================
    @Nested
    class PadLeftTests {

        @Test
        void testZeros() {
            assertEquals("007", StringApiChallenge.padLeft("7", 3, '0'));
        }

        @Test
        void testZerosImproved() {
            assertEquals("007", StringApiChallenge.padLeftImproved("7", 3, '0'));
        }

        @Test
        void testLongerThanWidth() {
            assertEquals("abc", StringApiChallenge.padLeft("abc", 2, '*'));
        }

        @Test
        void testEmptyText() {
            assertEquals("---", StringApiChallenge.padLeft("", 3, '-'));
        }

        @Test
        void testExactlyWidth() {
            assertEquals("ab", StringApiChallenge.padLeft("ab", 2, '0'));
        }

        @Test
        void testSpaces() {
            assertEquals("    x", StringApiChallenge.padLeft("x", 5, ' '));
        }

        @Test
        void testNumberText() {
            assertEquals("000012", StringApiChallenge.padLeft("12", 6, '0'));
        }

        @Test
        void testWidthZero() {
            assertEquals("abc", StringApiChallenge.padLeft("abc", 0, '*'));
        }

        @Test
        void testTheTextStaysAtTheEnd() {
            String result = StringApiChallenge.padLeft("end", 8, '.');
            assertEquals(".....end", result);
            assertTrue(result.endsWith("end"));
        }

        @Test
        void testOnlyOnePaddingCharacterNeeded() {
            assertEquals("*abc", StringApiChallenge.padLeft("abc", 4, '*'));
        }

        @Test
        void testTheResultHasTheRightLength() {
            assertEquals(10, StringApiChallenge.padLeft("abcd", 10, '#').length());
        }
    }

    // ==========================================================
    // CHALLENGE 7: repeatWithSeparator
    // ==========================================================
    @Nested
    class RepeatWithSeparatorTests {

        @Test
        void testThreeTimes() {
            assertEquals("ab, ab, ab", StringApiChallenge.repeatWithSeparator("ab", 3, ", "));
        }

        @Test
        void testOnce() {
            assertEquals("ab", StringApiChallenge.repeatWithSeparator("ab", 1, ", "));
        }

        @Test
        void testZeroTimes() {
            assertEquals("", StringApiChallenge.repeatWithSeparator("ab", 0, ", "));
        }

        @Test
        void testEmptyText() {
            assertEquals(",,", StringApiChallenge.repeatWithSeparator("", 3, ","));
        }

        @Test
        void testEmptySeparator() {
            assertEquals("xx", StringApiChallenge.repeatWithSeparator("x", 2, ""));
        }

        @Test
        void testFourTimes() {
            assertEquals("a-a-a-a", StringApiChallenge.repeatWithSeparator("a", 4, "-"));
        }

        @Test
        void testLongSeparator() {
            assertEquals("ab--ab", StringApiChallenge.repeatWithSeparator("ab", 2, "--"));
        }

        @Test
        void testNoSeparatorAtTheEnds() {
            String result = StringApiChallenge.repeatWithSeparator("x", 3, "|");
            assertFalse(result.startsWith("|"));
            assertFalse(result.endsWith("|"));
            assertEquals("x|x|x", result);
        }

        @Test
        void testZeroTimesWithEmptyText() {
            assertEquals("", StringApiChallenge.repeatWithSeparator("", 0, ","));
        }
    }

    // ==========================================================
    // CHALLENGE 8: removeDigits
    // ==========================================================
    @Nested
    class RemoveDigitsTests {

        @Test
        void testMixed() {
            assertEquals("abc", StringApiChallenge.removeDigits("a1b22c"));
        }

        @Test
        void testOnlyDigits() {
            assertEquals("", StringApiChallenge.removeDigits("123"));
        }

        @Test
        void testNoDigits() {
            assertEquals("a b", StringApiChallenge.removeDigits("a b"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.removeDigits(""));
        }

        @Test
        void testSpacesStay() {
            assertEquals(" apples and  pears", StringApiChallenge.removeDigits("9 apples and 10 pears"));
        }

        @Test
        void testZeroIsADigit() {
            assertEquals("a", StringApiChallenge.removeDigits("0a0"));
        }

        @Test
        void testOtherCharactersStay() {
            assertEquals("no digits!", StringApiChallenge.removeDigits("no digits!"));
        }

        @Test
        void testAllTheDigits() {
            assertEquals("x", StringApiChallenge.removeDigits("0123456789x"));
        }

        @Test
        void testOrderIsKept() {
            assertEquals("zyx", StringApiChallenge.removeDigits("z1y2x3"));
        }

        @Test
        void testPunctuationAndDigitsTogether() {
            assertEquals("a-b.c", StringApiChallenge.removeDigits("a-1b.2c3"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: isAnagram
    // ==========================================================
    @Nested
    class IsAnagramTests {

        @Test
        void testListenSilent() {
            assertTrue(StringApiChallenge.isAnagram("Listen", "Silent"));
        }

        @Test
        void testSameLettersDifferentCounts() {
            assertFalse(StringApiChallenge.isAnagram("aab", "abb"));
        }

        @Test
        void testDifferentLengths() {
            assertFalse(StringApiChallenge.isAnagram("abc", "abcd"));
        }

        @Test
        void testBothEmpty() {
            assertTrue(StringApiChallenge.isAnagram("", ""));
        }

        @Test
        void testDifferentLetters() {
            assertFalse(StringApiChallenge.isAnagram("abc", "abd"));
        }

        @Test
        void testSpacesCount() {
            assertTrue(StringApiChallenge.isAnagram("a b", "ab "));
            assertFalse(StringApiChallenge.isAnagram("a b", "ab"));
        }

        @Test
        void testMixedCase() {
            assertTrue(StringApiChallenge.isAnagram("AbC", "cBa"));
        }

        @Test
        void testRepeatedLetters() {
            assertTrue(StringApiChallenge.isAnagram("aabb", "abab"));
        }

        @Test
        void testDifferentLettersWithTheSameSum() {
            assertFalse(StringApiChallenge.isAnagram("ad", "bc"));
            assertFalse(StringApiChallenge.isAnagram("ac", "bb"));
        }

        @Test
        void testSingleLetterDifferentCase() {
            assertTrue(StringApiChallenge.isAnagram("a", "A"));
        }

        @Test
        void testSameTextIsAnAnagramOfItself() {
            assertTrue(StringApiChallenge.isAnagram("hello", "hello"));
        }

        @Test
        void testOneEmptyOneNot() {
            assertFalse(StringApiChallenge.isAnagram("", "a"));
            assertFalse(StringApiChallenge.isAnagram("a", ""));
        }

        @Test
        void testSameLengthDifferentCharacters() {
            assertFalse(StringApiChallenge.isAnagram("abcd", "abce"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: compress
    // ==========================================================
    @Nested
    class CompressTests {

        @Test
        void testExample() {
            assertEquals("a3b1c2", StringApiChallenge.compress("aaabcc"));
        }

        @Test
        void testSameLetterAppearsAgain() {
            assertEquals("a2b2a2", StringApiChallenge.compress("aabbaa"));
        }

        @Test
        void testTwoDigitCount() {
            assertEquals("a12", StringApiChallenge.compress("aaaaaaaaaaaa"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.compress(""));
        }

        @Test
        void testSingleCharacter() {
            assertEquals("a1", StringApiChallenge.compress("a"));
        }

        @Test
        void testAllDifferent() {
            assertEquals("a1b1c1", StringApiChallenge.compress("abc"));
        }

        @Test
        void testCaseMatters() {
            assertEquals("a1A1", StringApiChallenge.compress("aA"));
        }

        @Test
        void testExactlyTen() {
            assertEquals("a10", StringApiChallenge.compress("aaaaaaaaaa"));
        }

        @Test
        void testSpaces() {
            assertEquals(" 2", StringApiChallenge.compress("  "));
        }

        @Test
        void testDigitsInTheText() {
            assertEquals("1221", StringApiChallenge.compress("112"));
        }

        @Test
        void testLastGroupIsIncluded() {
            assertEquals("a1b3", StringApiChallenge.compress("abbb"));
        }

        @Test
        void testFirstGroupIsIncluded() {
            assertEquals("a3b1", StringApiChallenge.compress("aaab"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: middleCharacters
    // ==========================================================
    @Nested
    class MiddleCharactersTests {

        @Test
        void testOdd() {
            assertEquals("b", StringApiChallenge.middleCharacters("abc"));
        }

        @Test
        void testEven() {
            assertEquals("bc", StringApiChallenge.middleCharacters("abcd"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals("a", StringApiChallenge.middleCharacters("a"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.middleCharacters(""));
        }

        @Test
        void testTwoCharacters() {
            assertEquals("ab", StringApiChallenge.middleCharacters("ab"));
        }

        @Test
        void testFiveCharacters() {
            assertEquals("c", StringApiChallenge.middleCharacters("abcde"));
        }

        @Test
        void testSixCharacters() {
            assertEquals("cd", StringApiChallenge.middleCharacters("abcdef"));
        }

        @Test
        void testSpacesAround() {
            assertEquals("x", StringApiChallenge.middleCharacters("  x  "));
        }

        @Test
        void testSevenCharacters() {
            assertEquals("d", StringApiChallenge.middleCharacters("abcdefg"));
        }

        @Test
        void testEightCharacters() {
            assertEquals("de", StringApiChallenge.middleCharacters("abcdefgh"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: maskCard
    // ==========================================================
    @Nested
    class MaskCardTests {

        @Test
        void testSixteenDigits() {
            assertEquals("************3456", StringApiChallenge.maskCard("1234567890123456"));
        }

        @Test
        void testFiveCharacters() {
            assertEquals("*2345", StringApiChallenge.maskCard("12345"));
        }

        @Test
        void testExactlyFour() {
            assertEquals("1234", StringApiChallenge.maskCard("1234"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringApiChallenge.maskCard(""));
        }

        @Test
        void testShorterThanFour() {
            assertEquals("123", StringApiChallenge.maskCard("123"));
        }

        @Test
        void testSpacesAreMaskedToo() {
            assertEquals("***************3456", StringApiChallenge.maskCard("1234 5678 9012 3456"));
        }

        @Test
        void testDashesAreMaskedToo() {
            assertEquals("*****2222", StringApiChallenge.maskCard("1111-2222"));
        }

        @Test
        void testLengthIsKept() {
            assertEquals(16, StringApiChallenge.maskCard("1234567890123456").length());
        }

        @Test
        void testOnlyTheLastFourStay() {
            String result = StringApiChallenge.maskCard("abcdefgh");
            assertEquals("****efgh", result);
        }

        @Test
        void testMaskedPartHasOnlyStars() {
            String result = StringApiChallenge.maskCard("9876543210");
            assertEquals("******3210", result);
        }
    }

    // ==========================================================
    // CHALLENGE 13: formatCents
    // ==========================================================
    @Nested
    class FormatCentsTests {

        @Test
        void testNormalAmount() {
            assertEquals("12.05", StringApiChallenge.formatCents(1205));
        }

        @Test
        void testLessThanTenCents() {
            assertEquals("0.05", StringApiChallenge.formatCents(5));
        }

        @Test
        void testZero() {
            assertEquals("0.00", StringApiChallenge.formatCents(0));
        }

        @Test
        void testExactEuro() {
            assertEquals("1.00", StringApiChallenge.formatCents(100));
        }

        @Test
        void testNoThousandsSeparator() {
            assertEquals("12345.67", StringApiChallenge.formatCents(1234567));
        }

        @Test
        void testNinetyNine() {
            assertEquals("0.99", StringApiChallenge.formatCents(99));
        }

        @Test
        void testTenCents() {
            assertEquals("0.10", StringApiChallenge.formatCents(10));
        }

        @Test
        void testBigAmount() {
            assertEquals("100000000.00", StringApiChallenge.formatCents(10_000_000_000L));
        }

        @Test
        void testFiftyCents() {
            assertEquals("0.50", StringApiChallenge.formatCents(50));
        }

        @Test
        void testRoundTens() {
            assertEquals("10.00", StringApiChallenge.formatCents(1000));
        }

        @Test
        void testOneCent() {
            assertEquals("0.01", StringApiChallenge.formatCents(1));
        }

        @Test
        void testVeryBigAmountKeepsAllTheDigits() {
            assertEquals("1234567890123456.78", StringApiChallenge.formatCents(123456789012345678L));
            assertEquals("90071992547409.93", StringApiChallenge.formatCents(9_007_199_254_740_993L));
        }

        @Test
        void testUsesADotAndNotAComma() {
            assertFalse(StringApiChallenge.formatCents(1234).contains(","));
            assertTrue(StringApiChallenge.formatCents(1234).contains("."));
        }
    }

    // ==========================================================
    // CHALLENGE 14: joinNonBlank
    // ==========================================================
    @Nested
    class JoinNonBlankTests {

        @Test
        void testSkipsNullAndBlank() {
            assertEquals("a-b", StringApiChallenge.joinNonBlank(Arrays.asList("a", null, "  ", "b"), "-"));
        }

        @Test
        void testSpacesOfTheKeptPartsAreKept() {
            assertEquals(" x +y", StringApiChallenge.joinNonBlank(List.of(" x ", "y"), "+"));
        }

        @Test
        void testNothingLeft() {
            assertEquals("", StringApiChallenge.joinNonBlank(Arrays.asList(null, ""), ","));
        }

        @Test
        void testEmptyList() {
            assertEquals("", StringApiChallenge.joinNonBlank(new ArrayList<>(), ","));
        }

        @Test
        void testSinglePart() {
            assertEquals("a", StringApiChallenge.joinNonBlank(List.of("a"), ","));
        }

        @Test
        void testEmptySeparator() {
            assertEquals("abc", StringApiChallenge.joinNonBlank(List.of("a", "b", "c"), ""));
        }

        @Test
        void testLongSeparator() {
            assertEquals("a, b, c", StringApiChallenge.joinNonBlank(List.of("a", "b", "c"), ", "));
        }

        @Test
        void testOrderIsKept() {
            assertEquals("z,a,m", StringApiChallenge.joinNonBlank(Arrays.asList("z", null, "a", "", "m"), ","));
        }

        @Test
        void testRepeatedPartsAreKept() {
            assertEquals("a,b,a", StringApiChallenge.joinNonBlank(List.of("a", "b", "a"), ","));
            assertEquals("x x", StringApiChallenge.joinNonBlank(Arrays.asList("x", null, "x"), " "));
        }

        @Test
        void testNoSeparatorAtTheEnds() {
            String result = StringApiChallenge.joinNonBlank(Arrays.asList(null, "a", null), "|");
            assertEquals("a", result);
        }

        @Test
        void testBlankAtTheEnd() {
            assertEquals("a,b", StringApiChallenge.joinNonBlank(Arrays.asList("a", "b", "   "), ","));
        }

        @Test
        void testBlankAtTheStart() {
            assertEquals("a,b", StringApiChallenge.joinNonBlank(Arrays.asList("  ", "a", "b"), ","));
        }

        @Test
        void testListIsNotModified() {
            List<String> parts = new ArrayList<>(Arrays.asList("a", null, ""));
            StringApiChallenge.joinNonBlank(parts, ",");
            assertEquals(3, parts.size());
        }
    }

    // ==========================================================
    // CHALLENGE 15: wordCount
    // ==========================================================
    @Nested
    class WordCountTests {

        @Test
        void testTwoWords() {
            assertEquals(2, StringApiChallenge.wordCount("one two"));
        }

        @Test
        void testExtraSpaces() {
            assertEquals(2, StringApiChallenge.wordCount("  one   two  "));
        }

        @Test
        void testSingleWord() {
            assertEquals(1, StringApiChallenge.wordCount("one"));
        }

        @Test
        void testOnlySpaces() {
            assertEquals(0, StringApiChallenge.wordCount("   "));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, StringApiChallenge.wordCount(""));
        }

        @Test
        void testSpacesAroundOneWord() {
            assertEquals(1, StringApiChallenge.wordCount(" a "));
        }

        @Test
        void testFiveWords() {
            assertEquals(5, StringApiChallenge.wordCount("a b c d e"));
        }

        @Test
        void testDoubleSpaceBetweenWords() {
            assertEquals(2, StringApiChallenge.wordCount("word  word"));
        }

        @Test
        void testOneSpace() {
            assertEquals(0, StringApiChallenge.wordCount(" "));
        }

        @Test
        void testSpacesOnlyAtTheStart() {
            assertEquals(3, StringApiChallenge.wordCount("   x y z"));
        }

        @Test
        void testSpacesOnlyAtTheEnd() {
            assertEquals(3, StringApiChallenge.wordCount("x y z   "));
        }

        @Test
        void testLongWords() {
            assertEquals(2, StringApiChallenge.wordCount("extraordinary   circumstances"));
        }
    }
}