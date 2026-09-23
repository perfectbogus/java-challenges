package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RegexIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: isValidEmail
    // ==========================================================
    @Nested
    class IsValidEmailTests {

        @Test
        void testValidSimpleEmail() {
            assertTrue(RegexIntermediateChallenge.isValidEmail("john.doe@example.com"));
        }

        @Test
        void testValidWithHyphenatedDomain() {
            assertTrue(RegexIntermediateChallenge.isValidEmail("user@sub-domain.io"));
        }

        @Test
        void testMissingAtSign() {
            assertFalse(RegexIntermediateChallenge.isValidEmail("not-an-email"));
        }

        @Test
        void testInvalidCharacterInDomain() {
            assertFalse(RegexIntermediateChallenge.isValidEmail("bad@domain,com"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: extractNumbers
    // ==========================================================
    @Nested
    class ExtractNumbersTests {

        @Test
        void testMixedLettersAndDigits() {
            assertEquals(List.of("123", "45"), RegexIntermediateChallenge.extractNumbers("abc123def45"));
        }

        @Test
        void testNoDigits() {
            assertEquals(List.of(), RegexIntermediateChallenge.extractNumbers("no digits here"));
        }

        @Test
        void testEachDigitSeparated() {
            assertEquals(List.of("1", "2", "3"), RegexIntermediateChallenge.extractNumbers("a1b2c3"));
        }

        @Test
        void testLeadingMinusSignExcluded() {
            assertEquals(List.of("42"), RegexIntermediateChallenge.extractNumbers("-42"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: maskDigits
    // ==========================================================
    @Nested
    class MaskDigitsTests {

        @Test
        void testMixedContent() {
            assertEquals("abc***", RegexIntermediateChallenge.maskDigits("abc123"));
        }

        @Test
        void testNoDigits() {
            assertEquals("no digits", RegexIntermediateChallenge.maskDigits("no digits"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", RegexIntermediateChallenge.maskDigits(""));
        }

        @Test
        void testInterleavedDigitsAndLetters() {
            assertEquals("*a*b*c", RegexIntermediateChallenge.maskDigits("1a2b3c"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: countWords
    // ==========================================================
    @Nested
    class CountWordsTests {

        @Test
        void testSentenceWithPunctuationAndDigits() {
            assertEquals(2, RegexIntermediateChallenge.countWords("Hello, world! 123"));
        }

        @Test
        void testEmptyString() {
            assertEquals(0, RegexIntermediateChallenge.countWords(""));
        }

        @Test
        void testLettersSeparatedByDigits() {
            assertEquals(3, RegexIntermediateChallenge.countWords("one1two2three"));
        }

        @Test
        void testOnlyWhitespace() {
            assertEquals(0, RegexIntermediateChallenge.countWords("   "));
        }
    }

    // ==========================================================
    // CHALLENGE 5: isValidUSPhoneNumber
    // ==========================================================
    @Nested
    class IsValidUSPhoneNumberTests {

        @Test
        void testDashFormat() {
            assertTrue(RegexIntermediateChallenge.isValidUSPhoneNumber("123-456-7890"));
        }

        @Test
        void testParenthesesFormat() {
            assertTrue(RegexIntermediateChallenge.isValidUSPhoneNumber("(123) 456-7890"));
        }

        @Test
        void testDigitsOnlyIsInvalid() {
            assertFalse(RegexIntermediateChallenge.isValidUSPhoneNumber("1234567890"));
        }

        @Test
        void testMissingSpaceAfterParenIsInvalid() {
            assertFalse(RegexIntermediateChallenge.isValidUSPhoneNumber("(123)456-7890"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: extractHashtags
    // ==========================================================
    @Nested
    class ExtractHashtagsTests {

        @Test
        void testMultipleHashtags() {
            assertEquals(
                    List.of("java", "Streams_API"),
                    RegexIntermediateChallenge.extractHashtags("Loving #java and #Streams_API today")
            );
        }

        @Test
        void testNoHashtags() {
            assertEquals(List.of(), RegexIntermediateChallenge.extractHashtags("no tags here"));
        }

        @Test
        void testHashtagAtStartAndEnd() {
            assertEquals(List.of("start", "end"), RegexIntermediateChallenge.extractHashtags("#start middle #end"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: splitOnMultipleDelimiters
    // ==========================================================
    @Nested
    class SplitOnMultipleDelimitersTests {

        @Test
        void testMixedDelimiters() {
            assertEquals(
                    List.of("apple", "banana", "cherry", "date"),
                    RegexIntermediateChallenge.splitOnMultipleDelimiters("apple, banana;cherry  date")
            );
        }

        @Test
        void testLeadingAndTrailingDelimiters() {
            assertEquals(List.of("a", "b"), RegexIntermediateChallenge.splitOnMultipleDelimiters(",a,,b,"));
        }

        @Test
        void testEmptyString() {
            assertEquals(List.of(), RegexIntermediateChallenge.splitOnMultipleDelimiters(""));
        }

        @Test
        void testSingleToken() {
            assertEquals(List.of("single"), RegexIntermediateChallenge.splitOnMultipleDelimiters("single"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: isValidHexColor
    // ==========================================================
    @Nested
    class IsValidHexColorTests {

        @Test
        void testShortForm() {
            assertTrue(RegexIntermediateChallenge.isValidHexColor("#fff"));
        }

        @Test
        void testLongForm() {
            assertTrue(RegexIntermediateChallenge.isValidHexColor("#1a2b3c"));
        }

        @Test
        void testWrongDigitCount() {
            assertFalse(RegexIntermediateChallenge.isValidHexColor("#12345"));
        }

        @Test
        void testMissingHash() {
            assertFalse(RegexIntermediateChallenge.isValidHexColor("123456"));
        }

        @Test
        void testInvalidHexCharacters() {
            assertFalse(RegexIntermediateChallenge.isValidHexColor("#GGGGGG"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: collapseWhitespace
    // ==========================================================
    @Nested
    class CollapseWhitespaceTests {

        @Test
        void testLeadingTrailingAndRepeatedSpaces() {
            assertEquals("hello world", RegexIntermediateChallenge.collapseWhitespace("  hello   world  "));
        }

        @Test
        void testTabsAndNewlines() {
            assertEquals("a b c", RegexIntermediateChallenge.collapseWhitespace("a\tb\nc"));
        }

        @Test
        void testEmptyString() {
            assertEquals("", RegexIntermediateChallenge.collapseWhitespace(""));
        }

        @Test
        void testSingleWordUnchanged() {
            assertEquals("single", RegexIntermediateChallenge.collapseWhitespace("single"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: extractDates
    // ==========================================================
    @Nested
    class ExtractDatesTests {

        @Test
        void testMultipleDates() {
            assertEquals(
                    List.of("2024-01-15", "2024-03-02"),
                    RegexIntermediateChallenge.extractDates("Started 2024-01-15 and ended 2024-03-02.")
            );
        }

        @Test
        void testNoDates() {
            assertEquals(List.of(), RegexIntermediateChallenge.extractDates("no dates here"));
        }

        @Test
        void testSingleDigitMonthDoesNotMatch() {
            assertEquals(List.of(), RegexIntermediateChallenge.extractDates("2024-1-15"));
        }

        @Test
        void testDateSurroundedByPunctuation() {
            assertEquals(List.of("2023-11-30"), RegexIntermediateChallenge.extractDates("Meeting on 2023-11-30!"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: formatPhoneNumber
    // ==========================================================
    @Nested
    class FormatPhoneNumberTests {

        @Test
        void testTenDigitsFormatted() {
            assertEquals("123-456-7890", RegexIntermediateChallenge.formatPhoneNumber("1234567890"));
        }

        @Test
        void testTooFewDigitsUnchanged() {
            assertEquals("123", RegexIntermediateChallenge.formatPhoneNumber("123"));
        }

        @Test
        void testTooManyDigitsUnchanged() {
            assertEquals("12345678901", RegexIntermediateChallenge.formatPhoneNumber("12345678901"));
        }

        @Test
        void testAlreadyFormattedUnchanged() {
            assertEquals("123-456-7890", RegexIntermediateChallenge.formatPhoneNumber("123-456-7890"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: extractQuotedStrings
    // ==========================================================
    @Nested
    class ExtractQuotedStringsTests {

        @Test
        void testMultipleQuotedStrings() {
            assertEquals(
                    List.of("hello", "goodbye"),
                    RegexIntermediateChallenge.extractQuotedStrings("She said \"hello\" and \"goodbye\".")
            );
        }

        @Test
        void testNoQuotes() {
            assertEquals(List.of(), RegexIntermediateChallenge.extractQuotedStrings("no quotes"));
        }

        @Test
        void testEmptyQuotePair() {
            assertEquals(List.of(""), RegexIntermediateChallenge.extractQuotedStrings("empty \"\" quotes"));
        }

        @Test
        void testSingleQuotedString() {
            assertEquals(List.of("only one"), RegexIntermediateChallenge.extractQuotedStrings("\"only one\""));
        }
    }
}