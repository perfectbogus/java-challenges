package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegexAdvancedChallengeTest {

    // ==========================================================
    // CHALLENGE 1: isValidEmail
    // ==========================================================
    @Nested
    class IsValidEmailTests {

        @Test
        void testAcceptsValidAddress() {
            assertTrue(RegexAdvancedChallenge.isValidEmail("john.doe+test@sub.example.com"));
        }

        @Test
        void testRejectsMissingAtSign() {
            assertFalse(RegexAdvancedChallenge.isValidEmail("no-at-sign.com"));
        }

        @Test
        void testRejectsDomainWithoutDot() {
            assertFalse(RegexAdvancedChallenge.isValidEmail("user@domain"));
        }

        @Test
        void testRejectsEmptyLabel() {
            assertFalse(RegexAdvancedChallenge.isValidEmail("user@.com"));
        }

        @Test
        void testRejectsEmptyLocalPart() {
            assertFalse(RegexAdvancedChallenge.isValidEmail("@domain.com"));
        }

        @Test
        void testRejectsTooShortTld() {
            assertFalse(RegexAdvancedChallenge.isValidEmail("user@domain.c"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: extractAllIntegers
    // ==========================================================
    @Nested
    class ExtractAllIntegersTests {

        @Test
        void testExtractsPositiveAndNegativeNumbersInOrder() {
            List<Integer> expected = List.of(10, -5, 23, -100, 7);
            assertEquals(expected, RegexAdvancedChallenge.extractAllIntegers("Values: 10, -5, 23, -100 and 7"));
        }

        @Test
        void testReturnsEmptyListWhenNoNumbersPresent() {
            assertEquals(List.of(), RegexAdvancedChallenge.extractAllIntegers("no numbers here"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: parseDateComponents
    // ==========================================================
    @Nested
    class ParseDateComponentsTests {

        @Test
        void testReturnsYearMonthDay() {
            Map<String, String> expected = Map.of("year", "2024", "month", "03", "day", "15");
            assertEquals(expected, RegexAdvancedChallenge.parseDateComponents("2024-03-15"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: findRepeatedWord
    // ==========================================================
    @Nested
    class FindRepeatedWordTests {

        @Test
        void testFindsImmediatelyRepeatedWord() {
            assertEquals(Optional.of("is"), RegexAdvancedChallenge.findRepeatedWord("This is is a test"));
        }

        @Test
        void testReturnsEmptyWhenNoRepetition() {
            assertEquals(Optional.empty(), RegexAdvancedChallenge.findRepeatedWord("the cat sat on the mat"));
        }

        @Test
        void testComparisonIsCaseSensitive() {
            assertEquals(Optional.empty(), RegexAdvancedChallenge.findRepeatedWord("Hello hello world"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: isStrongPassword
    // ==========================================================
    @Nested
    class IsStrongPasswordTests {

        @Test
        void testAcceptsPasswordMeetingAllRequirements() {
            assertTrue(RegexAdvancedChallenge.isStrongPassword("Abc123@x"));
        }

        @Test
        void testRejectsPasswordMissingDigitUppercaseAndSpecial() {
            assertFalse(RegexAdvancedChallenge.isStrongPassword("abcdefgh"));
        }

        @Test
        void testRejectsPasswordMissingLowercaseAndSpecial() {
            assertFalse(RegexAdvancedChallenge.isStrongPassword("ABC12345"));
        }

        @Test
        void testRejectsPasswordTooShort() {
            assertFalse(RegexAdvancedChallenge.isStrongPassword("Short1@"));
        }

        @Test
        void testAcceptsAnotherValidPassword() {
            assertTrue(RegexAdvancedChallenge.isStrongPassword("Valid#Pass1"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: maskAllButLastFourDigits
    // ==========================================================
    @Nested
    class MaskAllButLastFourDigitsTests {

        @Test
        void testMasksAllButLastFourOfSixteenDigits() {
            assertEquals("************5678", RegexAdvancedChallenge.maskAllButLastFourDigits("1234567812345678"));
        }

        @Test
        void testLeavesExactlyFourDigitsUnchanged() {
            assertEquals("1234", RegexAdvancedChallenge.maskAllButLastFourDigits("1234"));
        }

        @Test
        void testMasksSingleLeadingDigitOfFiveDigitNumber() {
            assertEquals("*2345", RegexAdvancedChallenge.maskAllButLastFourDigits("12345"));
        }

        @Test
        void testHandlesEmptyString() {
            assertEquals("", RegexAdvancedChallenge.maskAllButLastFourDigits(""));
        }
    }

    // ==========================================================
    // CHALLENGE 7: extractAmountsAfterDollarSign
    // ==========================================================
    @Nested
    class ExtractAmountsAfterDollarSignTests {

        @Test
        void testExtractsAmountsImmediatelyAfterDollarSigns() {
            List<String> expected = List.of("12.50", "3", "9.99");
            String input = "Price: $12.50, discount $3, total $9.99!";
            assertEquals(expected, RegexAdvancedChallenge.extractAmountsAfterDollarSign(input));
        }

        @Test
        void testReturnsEmptyListWhenNoDollarSignsPresent() {
            assertEquals(List.of(), RegexAdvancedChallenge.extractAmountsAfterDollarSign("No prices here"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: extractWordsNotEndingInIng
    // ==========================================================
    @Nested
    class ExtractWordsNotEndingInIngTests {

        @Test
        void testExcludesOnlyWordsEndingInIng() {
            List<String> expected = List.of("I", "am", "and", "while", "about");
            String input = "I am running and jumping while thinking about singing";
            assertEquals(expected, RegexAdvancedChallenge.extractWordsNotEndingInIng(input));
        }
    }

    // ==========================================================
    // CHALLENGE 9: extractFirstMatchingTagContent
    // ==========================================================
    @Nested
    class ExtractFirstMatchingTagContentTests {

        @Test
        void testExtractsContentOfFirstTagPair() {
            String input = "<b>Hello</b> <i>World</i>";
            assertEquals(Optional.of("Hello"), RegexAdvancedChallenge.extractFirstMatchingTagContent(input));
        }

        @Test
        void testDoesNotGreedilySpanIntoLaterTagOfSameName() {
            String input = "<p>first</p><p>second</p>";
            assertEquals(Optional.of("first"), RegexAdvancedChallenge.extractFirstMatchingTagContent(input));
        }

        @Test
        void testReturnsEmptyWhenNoTagsPresent() {
            assertEquals(Optional.empty(), RegexAdvancedChallenge.extractFirstMatchingTagContent("no tags here"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: splitOnMultipleDelimiters
    // ==========================================================
    @Nested
    class SplitOnMultipleDelimitersTests {

        @Test
        void testSplitsOnMixedCommaSemicolonAndWhitespaceRuns() {
            assertEquals(List.of("a", "b", "c", "d"), RegexAdvancedChallenge.splitOnMultipleDelimiters("a, b;;c   d"));
        }

        @Test
        void testIgnoresLeadingAndTrailingDelimiters() {
            List<String> expected = List.of("lead", "trail");
            assertEquals(expected, RegexAdvancedChallenge.splitOnMultipleDelimiters("  lead,,trail  "));
        }
    }
}