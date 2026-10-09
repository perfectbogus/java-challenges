package dev.perfectbogus.regex;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexLookaroundBasicsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: extractWordsBeforeColon
    // ==========================================================
    @Nested
    class ExtractWordsBeforeColonTests {

        @Test
        void testReturnsTheWordsBeforeEveryColon() {
            assertEquals(List.of("name", "age"),
                    RegexLookaroundBasicsChallenge.extractWordsBeforeColon("name: Ann, age: 30"));
            assertEquals(List.of("a", "b", "c"), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("a:b:c:"));
        }

        @Test
        void testWordsNotFollowedByAColonAreSkipped() {
            assertEquals(List.of("key"),
                    RegexLookaroundBasicsChallenge.extractWordsBeforeColon("hello world key: value"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("no colons here"));
        }

        @Test
        void testASpaceBeforeTheColonIsNotDirectlyFollowed() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("name : Ann"));
            assertEquals(List.of("b"), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("a : b:"));
        }

        @Test
        void testTheWholeWordIsReturned() {
            assertEquals(List.of("longerword"), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("longerword:"));
            assertEquals(List.of("Title"), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("1. Title: x"));
        }

        @Test
        void testADigitBeforeTheColonMeansTheWordIsNotDirectlyFollowedByIt() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("abc1: x"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon("12:30"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon(""));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractWordsBeforeColon(":::"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: extractNumbersBeforeKg
    // ==========================================================
    @Nested
    class ExtractNumbersBeforeKgTests {

        @Test
        void testReturnsTheNumbersBeforeKg() {
            assertEquals(List.of("5", "12"),
                    RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("5kg of rice and 12kg of beans"));
        }

        @Test
        void testTheWholeNumberIsReturned() {
            assertEquals(List.of("1234"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("weight=1234kg"));
            assertEquals(List.of("100"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("100kg"));
        }

        @Test
        void testOtherUnitsAndFormatsAreSkipped() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("5 kg"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("5KG and 7Kg"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("5g, 6k, 7lb"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("kg 5"));
        }

        @Test
        void testSeveralNumbersInARow() {
            assertEquals(List.of("3"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("1 2 3kg 4"));
            assertEquals(List.of("8", "9"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("8kg9kg"));
        }

        @Test
        void testWhatComesAfterKgDoesNotMatter() {
            assertEquals(List.of("7"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("7kgs"));
            assertEquals(List.of("2"), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg("2kg."));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractNumbersBeforeKg(""));
        }
    }

    // ==========================================================
    // CHALLENGE 3: extractIdValues
    // ==========================================================
    @Nested
    class ExtractIdValuesTests {

        @Test
        void testReturnsTheNumbersAfterId() {
            assertEquals(List.of("42", "7"), RegexLookaroundBasicsChallenge.extractIdValues("id=42 and id=7"));
        }

        @Test
        void testOnlyTheDigitsAreReturned() {
            assertEquals(List.of("12"), RegexLookaroundBasicsChallenge.extractIdValues("id=12ab"));
            assertEquals(List.of("007"), RegexLookaroundBasicsChallenge.extractIdValues("id=007;"));
            assertEquals(List.of("5", "6"), RegexLookaroundBasicsChallenge.extractIdValues("[id=5][id=6]"));
        }

        @Test
        void testNumbersWithoutIdAreSkipped() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractIdValues("age=30 count=5 99"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractIdValues("id 42, id:42, id =42, id= 42"));
        }

        @Test
        void testIdWithoutADigit() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractIdValues("id=abc id= id="));
            assertEquals(List.of("3"), RegexLookaroundBasicsChallenge.extractIdValues("id=x id=3"));
        }

        @Test
        void testTheIdPrefixIsCaseSensitive() {
            assertEquals(List.of("1"), RegexLookaroundBasicsChallenge.extractIdValues("ID=9 Id=8 id=1"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractIdValues(""));
        }
    }

    // ==========================================================
    // CHALLENGE 4: hideQWithoutU
    // ==========================================================
    @Nested
    class HideQWithoutUTests {

        @Test
        void testQFollowedByUStays() {
            assertEquals("queen quick quiet", RegexLookaroundBasicsChallenge.hideQWithoutU("queen quick quiet"));
        }

        @Test
        void testQNotFollowedByUIsReplaced() {
            assertEquals("#at and #i", RegexLookaroundBasicsChallenge.hideQWithoutU("qat and qi"));
            assertEquals("#1 #-", RegexLookaroundBasicsChallenge.hideQWithoutU("q1 q-"));
        }

        @Test
        void testQAtTheEndOfTheText() {
            assertEquals("queen and Ira#", RegexLookaroundBasicsChallenge.hideQWithoutU("queen and Iraq"));
            assertEquals("#", RegexLookaroundBasicsChallenge.hideQWithoutU("q"));
        }

        @Test
        void testSeveralQsInARow() {
            assertEquals("##", RegexLookaroundBasicsChallenge.hideQWithoutU("qq"));
            assertEquals("#qu", RegexLookaroundBasicsChallenge.hideQWithoutU("qqu"));
            assertEquals("qu#", RegexLookaroundBasicsChallenge.hideQWithoutU("quq"));
        }

        @Test
        void testOtherCharactersAreNeverChanged() {
            assertEquals("QAT Q u U", RegexLookaroundBasicsChallenge.hideQWithoutU("QAT Q u U"));
            assertEquals("Qu #U", RegexLookaroundBasicsChallenge.hideQWithoutU("Qu qU"));
            assertEquals("", RegexLookaroundBasicsChallenge.hideQWithoutU(""));
        }

        @Test
        void testTheSpaceAfterQCountsAsNotU() {
            assertEquals("# u", RegexLookaroundBasicsChallenge.hideQWithoutU("q u"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: removeCommasNotAfterDigits
    // ==========================================================
    @Nested
    class RemoveCommasNotAfterDigitsTests {

        @Test
        void testKeepsTheCommasAfterDigits() {
            assertEquals("1,000 apples pears",
                    RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("1,000 apples, pears"));
            assertEquals("1,234,567", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("1,234,567"));
        }

        @Test
        void testRemovesTheCommasAfterLettersAndSpaces() {
            assertEquals("a b c", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("a, b, c"));
            assertEquals("x  y", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("x , y"));
        }

        @Test
        void testCommasAtTheStartOrAfterAnotherComma() {
            assertEquals("abc", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits(",abc"));
            assertEquals("a b", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("a,, b"));
            assertEquals("1,", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("1,,"));
        }

        @Test
        void testTheCommaAfterADigitStaysEvenAtTheEnd() {
            assertEquals("page 3, page 4,",
                    RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("page 3, page 4,"));
            assertEquals("a1,b", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("a1,b"));
        }

        @Test
        void testTextWithoutCommas() {
            assertEquals("no commas 123", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits("no commas 123"));
            assertEquals("", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits(""));
            assertEquals("", RegexLookaroundBasicsChallenge.removeCommasNotAfterDigits(",,,"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: dashBetweenDigits
    // ==========================================================
    @Nested
    class DashBetweenDigitsTests {

        @Test
        void testSeparatesEveryPairOfDigits() {
            assertEquals("1-2-3-4", RegexLookaroundBasicsChallenge.dashBetweenDigits("1234"));
            assertEquals("5-5", RegexLookaroundBasicsChallenge.dashBetweenDigits("55"));
        }

        @Test
        void testDigitsNextToLettersGetNothing() {
            assertEquals("ab1-2cd3-4-5", RegexLookaroundBasicsChallenge.dashBetweenDigits("ab12cd345"));
            assertEquals("a1b2c3", RegexLookaroundBasicsChallenge.dashBetweenDigits("a1b2c3"));
        }

        @Test
        void testNothingAtTheEnds() {
            assertEquals("7", RegexLookaroundBasicsChallenge.dashBetweenDigits("7"));
            assertEquals("", RegexLookaroundBasicsChallenge.dashBetweenDigits(""));
            assertEquals("-1-2-", RegexLookaroundBasicsChallenge.dashBetweenDigits("-12-"));
        }

        @Test
        void testOtherCharactersBetweenDigits() {
            assertEquals("1 2 3", RegexLookaroundBasicsChallenge.dashBetweenDigits("1 2 3"));
            assertEquals("1.5", RegexLookaroundBasicsChallenge.dashBetweenDigits("1.5"));
            assertEquals("1-2.5", RegexLookaroundBasicsChallenge.dashBetweenDigits("12.5"));
        }

        @Test
        void testExistingDashesAreKept() {
            assertEquals("1-2--3-4", RegexLookaroundBasicsChallenge.dashBetweenDigits("12--34"));
            assertEquals("0-0-0-0", RegexLookaroundBasicsChallenge.dashBetweenDigits("0000"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: spaceAfterCommas
    // ==========================================================
    @Nested
    class SpaceAfterCommasTests {

        @Test
        void testAddsAMissingSpace() {
            assertEquals("a, b, c", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,b,c"));
            assertEquals("1, 2, 3, 4", RegexLookaroundBasicsChallenge.spaceAfterCommas("1,2,3,4"));
        }

        @Test
        void testExistingSpacesAreLeftAlone() {
            assertEquals("a, b, c", RegexLookaroundBasicsChallenge.spaceAfterCommas("a, b, c"));
            assertEquals("a,  b", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,  b"));
            assertEquals("a,   b, c", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,   b,c"));
        }

        @Test
        void testCommaAtTheEnd() {
            assertEquals("a, ", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,"));
            assertEquals(", ", RegexLookaroundBasicsChallenge.spaceAfterCommas(","));
        }

        @Test
        void testSeveralCommasInARow() {
            assertEquals(", , ", RegexLookaroundBasicsChallenge.spaceAfterCommas(",,"));
            assertEquals("a, , b", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,,b"));
        }

        @Test
        void testOtherWhitespaceDoesNotCountAsASpace() {
            assertEquals("a, \tb", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,\tb"));
            assertEquals("a, \nb", RegexLookaroundBasicsChallenge.spaceAfterCommas("a,\nb"));
        }

        @Test
        void testNothingToChange() {
            assertEquals("no commas here", RegexLookaroundBasicsChallenge.spaceAfterCommas("no commas here"));
            assertEquals("", RegexLookaroundBasicsChallenge.spaceAfterCommas(""));
            assertEquals("a , b", RegexLookaroundBasicsChallenge.spaceAfterCommas("a ,b"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: extractFirstNamesBeforeSmith
    // ==========================================================
    @Nested
    class ExtractFirstNamesBeforeSmithTests {

        @Test
        void testReturnsTheWordsBeforeSmith() {
            assertEquals(List.of("John", "Mary"),
                    RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("John Smith and Mary Smith"));
        }

        @Test
        void testSmithMustBeAWholeWord() {
            assertEquals(List.of("Ann"),
                    RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Bob Smithson, Ann Smith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Tom Smithy"));
        }

        @Test
        void testExactlyOneSpaceIsRequired() {
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Anna  Smith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Mr. Smith, Dr.Smith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Ann\tSmith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("AnnSmith"));
        }

        @Test
        void testIsCaseSensitive() {
            assertEquals(List.of("Joe"),
                    RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Joe smith, Jim SMITH, Joe Smith"));
        }

        @Test
        void testSmithCanBeTheOnlyWordBeforeAnotherSmith() {
            assertEquals(List.of("Smith"), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Smith Smith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Smith"));
            assertEquals(List.of(), RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith(""));
        }

        @Test
        void testSmithFollowedByPunctuationIsFine() {
            assertEquals(List.of("Kim", "Lee"),
                    RegexLookaroundBasicsChallenge.extractFirstNamesBeforeSmith("Kim Smith, then Lee Smith."));
        }
    }

    // ==========================================================
    // CHALLENGE 9: containsLetterAndDigit
    // ==========================================================
    @Nested
    class ContainsLetterAndDigitTests {

        @Test
        void testLetterAndDigitInAnyOrder() {
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("abc1"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("1abc"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("a-1"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("9 ... Z"));
        }

        @Test
        void testOnlyLettersOrOnlyDigits() {
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("abc"));
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("12345"));
        }

        @Test
        void testNeitherLettersNorDigits() {
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit(""));
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("!?- _"));
        }

        @Test
        void testLineBreaksInTheText() {
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("abc\n123"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("1\n\n\nx"));
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("abc\ndef"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("é1"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("é1a"));
        }

        @Test
        void testSingleCharacters() {
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("a"));
            assertFalse(RegexLookaroundBasicsChallenge.containsLetterAndDigit("1"));
            assertTrue(RegexLookaroundBasicsChallenge.containsLetterAndDigit("a1"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: hasDigitAndNoWhitespace
    // ==========================================================
    @Nested
    class HasDigitAndNoWhitespaceTests {

        @Test
        void testDigitAndNoWhitespace() {
            assertTrue(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("abc1"));
            assertTrue(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("12345"));
            assertTrue(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("a-b_c.d9"));
        }

        @Test
        void testNoDigit() {
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("abc"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("!?-_"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace(""));
        }

        @Test
        void testAnyWhitespaceRejectsTheText() {
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("ab 1"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace(" 1"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("1 "));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("a\tb1"));
        }

        @Test
        void testLineBreaksRejectTheText() {
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("1\n"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("abc\n123"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("12\r\n"));
        }

        @Test
        void testWhitespaceFarFromTheDigit() {
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("1abcdefghij klm"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("abc defghijklm 1"));
        }

        @Test
        void testSingleCharacters() {
            assertTrue(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace("7"));
            assertFalse(RegexLookaroundBasicsChallenge.hasDigitAndNoWhitespace(" "));
        }
    }
}