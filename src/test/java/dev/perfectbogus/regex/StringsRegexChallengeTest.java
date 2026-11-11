package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringsRegexChallengeTest {

    // ==========================================================
    // CHALLENGE 1: isHexColor
    // ==========================================================
    @Nested
    class IsHexColorTests {

        @Test
        void testMixedCase() {
            assertTrue(StringsRegexChallenge.isHexColor("#FF00aa"));
        }

        @Test
        void testOnlyDigits() {
            assertTrue(StringsRegexChallenge.isHexColor("#123456"));
            assertTrue(StringsRegexChallenge.isHexColor("#000000"));
        }

        @Test
        void testOnlyLowercaseLetters() {
            assertTrue(StringsRegexChallenge.isHexColor("#abcdef"));
        }

        @Test
        void testOnlyUppercaseLetters() {
            assertTrue(StringsRegexChallenge.isHexColor("#ABCDEF"));
        }

        @Test
        void testThreeDigitsIsNotValid() {
            assertFalse(StringsRegexChallenge.isHexColor("#FFF"));
        }

        @Test
        void testNoHash() {
            assertFalse(StringsRegexChallenge.isHexColor("FF00aa"));
        }

        @Test
        void testLetterOutsideTheRange() {
            assertFalse(StringsRegexChallenge.isHexColor("#12345g"));
            assertFalse(StringsRegexChallenge.isHexColor("#GGGGGG"));
        }

        @Test
        void testTooLong() {
            assertFalse(StringsRegexChallenge.isHexColor("#1234567"));
        }

        @Test
        void testTooShort() {
            assertFalse(StringsRegexChallenge.isHexColor("#12345"));
        }

        @Test
        void testEmptyText() {
            assertFalse(StringsRegexChallenge.isHexColor(""));
        }

        @Test
        void testOnlyTheHash() {
            assertFalse(StringsRegexChallenge.isHexColor("#"));
        }

        @Test
        void testTwoHashes() {
            assertFalse(StringsRegexChallenge.isHexColor("##123456"));
        }

        @Test
        void testSpacesAround() {
            assertFalse(StringsRegexChallenge.isHexColor(" #123456"));
            assertFalse(StringsRegexChallenge.isHexColor("#123456 "));
        }

        @Test
        void testSpaceInside() {
            assertFalse(StringsRegexChallenge.isHexColor("#12 456"));
        }

        @Test
        void testNewLineAtTheEnd() {
            assertFalse(StringsRegexChallenge.isHexColor("#123456\n"));
        }

        @Test
        void testOnlyDigitsFromZeroToNine() {
            assertFalse(StringsRegexChallenge.isHexColor("#\u0661\u0662\u0663\u0664\u0665\u0666"));
            assertFalse(StringsRegexChallenge.isHexColor("#\uFF11\uFF12\uFF13\uFF14\uFF15\uFF16"));
        }

        @Test
        void testHashInTheMiddle() {
            assertFalse(StringsRegexChallenge.isHexColor("12#3456"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: extractNumbers
    // ==========================================================
    @Nested
    class ExtractNumbersTests {

        @Test
        void testNumbersBetweenLetters() {
            assertEquals(List.of(12, 3), StringsRegexChallenge.extractNumbers("a12b3"));
        }

        @Test
        void testMinusIsIgnoredAndLeadingZeros() {
            assertEquals(List.of(5, 7), StringsRegexChallenge.extractNumbers("-5 and 007"));
        }

        @Test
        void testNoNumbers() {
            assertEquals(List.of(), StringsRegexChallenge.extractNumbers("no numbers"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), StringsRegexChallenge.extractNumbers(""));
        }

        @Test
        void testNineDigits() {
            assertEquals(List.of(123456789), StringsRegexChallenge.extractNumbers("123456789"));
        }

        @Test
        void testDigitsAreNotSplit() {
            assertEquals(List.of(12, 34), StringsRegexChallenge.extractNumbers("12 34"));
        }

        @Test
        void testDecimalNumberGivesTwoNumbers() {
            assertEquals(List.of(3, 14), StringsRegexChallenge.extractNumbers("3.14"));
        }

        @Test
        void testZero() {
            assertEquals(List.of(0), StringsRegexChallenge.extractNumbers("0"));
            assertEquals(List.of(0), StringsRegexChallenge.extractNumbers("00"));
        }

        @Test
        void testNumberAtTheStartAndAtTheEnd() {
            assertEquals(List.of(1, 2), StringsRegexChallenge.extractNumbers("1a2"));
            assertEquals(List.of(7, 8), StringsRegexChallenge.extractNumbers("7 x 8"));
        }

        @Test
        void testNumberInsideLetters() {
            assertEquals(List.of(123), StringsRegexChallenge.extractNumbers("abc123def"));
        }

        @Test
        void testManyNumbers() {
            assertEquals(List.of(1, 22, 333), StringsRegexChallenge.extractNumbers("x1y22z333"));
        }

        @Test
        void testLeadingZerosDoNotMatter() {
            assertEquals(List.of(1), StringsRegexChallenge.extractNumbers("0001"));
        }

        @Test
        void testOrderIsTheOrderOfAppearance() {
            assertEquals(List.of(9, 1, 5), StringsRegexChallenge.extractNumbers("9 1 5"));
        }

        @Test
        void testRepeatedNumbersStay() {
            assertEquals(List.of(4, 4, 4), StringsRegexChallenge.extractNumbers("4,4,4"));
        }

        @Test
        void testOnlyDigitsFromZeroToNine() {
            assertEquals(List.of(), StringsRegexChallenge.extractNumbers("٣٤"));
        }

        @Test
        void testNumbersAcrossLines() {
            assertEquals(List.of(1, 2), StringsRegexChallenge.extractNumbers("1\n2"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: reformatDates
    // ==========================================================
    @Nested
    class ReformatDatesTests {

        @Test
        void testDateInASentence() {
            assertEquals("Born 03/02/2001.", StringsRegexChallenge.reformatDates("Born 2001-02-03."));
        }

        @Test
        void testTwoDates() {
            assertEquals("11/11/2026 and 02/01/2027",
                    StringsRegexChallenge.reformatDates("2026-11-11 and 2027-01-02"));
        }

        @Test
        void testShortMonthAndDayAreNotDates() {
            assertEquals("2026-1-1", StringsRegexChallenge.reformatDates("2026-1-1"));
            assertEquals("2026-11-1", StringsRegexChallenge.reformatDates("2026-11-1"));
        }

        @Test
        void testNoDate() {
            assertEquals("no date", StringsRegexChallenge.reformatDates("no date"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringsRegexChallenge.reformatDates(""));
        }

        @Test
        void testTwoDigitYearIsNotADate() {
            assertEquals("26-11-11", StringsRegexChallenge.reformatDates("26-11-11"));
        }

        @Test
        void testDateAlreadyWithSlashes() {
            assertEquals("11/11/2026", StringsRegexChallenge.reformatDates("11/11/2026"));
        }

        @Test
        void testZeros() {
            assertEquals("00/00/0000", StringsRegexChallenge.reformatDates("0000-00-00"));
        }

        @Test
        void testMonthAndDayAreNotChecked() {
            assertEquals("99/99/2026", StringsRegexChallenge.reformatDates("2026-99-99"));
        }

        @Test
        void testDatesSeparatedByCommas() {
            assertEquals("01/02/2000,03/04/2001", StringsRegexChallenge.reformatDates("2000-02-01,2001-04-03"));
        }

        @Test
        void testOtherHyphensDoNotChange() {
            assertEquals("a-b-c", StringsRegexChallenge.reformatDates("a-b-c"));
        }

        @Test
        void testDateBetweenLetters() {
            assertEquals("x03/02/2001y", StringsRegexChallenge.reformatDates("x2001-02-03y"));
        }

        @Test
        void testOtherSeparatorsAreNotDates() {
            assertEquals("2026/11/11", StringsRegexChallenge.reformatDates("2026/11/11"));
            assertEquals("2026-11/11", StringsRegexChallenge.reformatDates("2026-11/11"));
            assertEquals("2026.11.11", StringsRegexChallenge.reformatDates("2026.11.11"));
        }

        @Test
        void testSpacesAroundTheDateStay() {
            assertEquals("  03/02/2001  ", StringsRegexChallenge.reformatDates("  2001-02-03  "));
        }

        @Test
        void testOnlyTheDate() {
            assertEquals("31/12/1999", StringsRegexChallenge.reformatDates("1999-12-31"));
        }

        @Test
        void testSameDateTwice() {
            assertEquals("03/02/2001 03/02/2001", StringsRegexChallenge.reformatDates("2001-02-03 2001-02-03"));
        }

        @Test
        void testDateAcrossLines() {
            assertEquals("a\n03/02/2001\nb", StringsRegexChallenge.reformatDates("a\n2001-02-03\nb"));
        }

        @Test
        void testDayAndMonthAreNotMixed() {
            assertEquals("25/12/2024", StringsRegexChallenge.reformatDates("2024-12-25"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: splitWords
    // ==========================================================
    @Nested
    class SplitWordsTests {

        @Test
        void testMixedSeparators() {
            assertEquals(List.of("a", "b", "c", "d"), StringsRegexChallenge.splitWords("a, b;c  d"));
        }

        @Test
        void testSeparatorsAtTheStartAndAtTheEnd() {
            assertEquals(List.of("x"), StringsRegexChallenge.splitWords(",,x,"));
        }

        @Test
        void testOnlySeparators() {
            assertEquals(List.of(), StringsRegexChallenge.splitWords(" ; , "));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), StringsRegexChallenge.splitWords(""));
        }

        @Test
        void testNoSeparators() {
            assertEquals(List.of("hello"), StringsRegexChallenge.splitWords("hello"));
        }

        @Test
        void testOnlyCommas() {
            assertEquals(List.of(), StringsRegexChallenge.splitWords(",,,"));
        }

        @Test
        void testOnlySemicolons() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.splitWords("a;b"));
        }

        @Test
        void testOnlySpaces() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.splitWords("a b"));
            assertEquals(List.of(), StringsRegexChallenge.splitWords("   "));
        }

        @Test
        void testLongRunOfSeparators() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.splitWords("a , ;,; b"));
        }

        @Test
        void testTabIsPartOfTheWord() {
            assertEquals(List.of("a\tb", "c"), StringsRegexChallenge.splitWords("a\tb c"));
        }

        @Test
        void testDotAndHyphenAreNotSeparators() {
            assertEquals(List.of("a.b", "c-d"), StringsRegexChallenge.splitWords("a.b, c-d"));
        }

        @Test
        void testSpaceAtTheStart() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.splitWords(" a b"));
        }

        @Test
        void testSeparatorAtTheEnd() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.splitWords("a b;"));
        }

        @Test
        void testSingleCharacterWords() {
            assertEquals(List.of("a", "b", "c"), StringsRegexChallenge.splitWords("a,b,c"));
        }

        @Test
        void testRepeatedWordsStay() {
            assertEquals(List.of("x", "x", "x"), StringsRegexChallenge.splitWords("x,x;x"));
        }

        @Test
        void testWordsKeepTheOrderOfTheText() {
            assertEquals(List.of("c", "a", "b"), StringsRegexChallenge.splitWords("c, a;b"));
        }

        @Test
        void testNumbersAreWords() {
            assertEquals(List.of("1", "22", "333"), StringsRegexChallenge.splitWords("1, 22; 333"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: isValidTime
    // ==========================================================
    @Nested
    class IsValidTimeTests {

        @Test
        void testNormalTime() {
            assertTrue(StringsRegexChallenge.isValidTime("09:30"));
        }

        @Test
        void testLastMinuteOfTheDay() {
            assertTrue(StringsRegexChallenge.isValidTime("23:59"));
        }

        @Test
        void testMidnight() {
            assertTrue(StringsRegexChallenge.isValidTime("00:00"));
        }

        @Test
        void testHoursFromTenToNineteen() {
            assertTrue(StringsRegexChallenge.isValidTime("10:00"));
            assertTrue(StringsRegexChallenge.isValidTime("19:59"));
        }

        @Test
        void testHoursFromTwentyToTwentyThree() {
            assertTrue(StringsRegexChallenge.isValidTime("20:00"));
            assertTrue(StringsRegexChallenge.isValidTime("23:00"));
        }

        @Test
        void testHourTwentyFour() {
            assertFalse(StringsRegexChallenge.isValidTime("24:00"));
            assertFalse(StringsRegexChallenge.isValidTime("24:59"));
        }

        @Test
        void testHourTooBig() {
            assertFalse(StringsRegexChallenge.isValidTime("29:00"));
            assertFalse(StringsRegexChallenge.isValidTime("30:00"));
            assertFalse(StringsRegexChallenge.isValidTime("99:00"));
        }

        @Test
        void testMinutesSixty() {
            assertFalse(StringsRegexChallenge.isValidTime("12:60"));
            assertFalse(StringsRegexChallenge.isValidTime("19:60"));
        }

        @Test
        void testHourWithOneDigit() {
            assertFalse(StringsRegexChallenge.isValidTime("9:30"));
        }

        @Test
        void testMinutesWithOneDigit() {
            assertFalse(StringsRegexChallenge.isValidTime("09:5"));
            assertFalse(StringsRegexChallenge.isValidTime("1:1"));
        }

        @Test
        void testEmptyText() {
            assertFalse(StringsRegexChallenge.isValidTime(""));
        }

        @Test
        void testWrongSeparator() {
            assertFalse(StringsRegexChallenge.isValidTime("09-30"));
            assertFalse(StringsRegexChallenge.isValidTime("0930"));
        }

        @Test
        void testSpacesAround() {
            assertFalse(StringsRegexChallenge.isValidTime("09:30 "));
            assertFalse(StringsRegexChallenge.isValidTime(" 09:30"));
        }

        @Test
        void testTooManyDigits() {
            assertFalse(StringsRegexChallenge.isValidTime("009:30"));
            assertFalse(StringsRegexChallenge.isValidTime("09:300"));
        }

        @Test
        void testLetters() {
            assertFalse(StringsRegexChallenge.isValidTime("ab:cd"));
            assertFalse(StringsRegexChallenge.isValidTime("09:3a"));
        }

        @Test
        void testWithSeconds() {
            assertFalse(StringsRegexChallenge.isValidTime("09:30:00"));
        }

        @Test
        void testMinutesFiftyNine() {
            assertTrue(StringsRegexChallenge.isValidTime("10:59"));
            assertTrue(StringsRegexChallenge.isValidTime("00:59"));
        }

        @Test
        void testMinutesSixtyToSixtyNine() {
            assertFalse(StringsRegexChallenge.isValidTime("10:61"));
            assertFalse(StringsRegexChallenge.isValidTime("10:99"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: swapNames
    // ==========================================================
    @Nested
    class SwapNamesTests {

        @Test
        void testOneName() {
            assertEquals("John Smith", StringsRegexChallenge.swapNames("Smith, John"));
        }

        @Test
        void testTwoNamesInASentence() {
            assertEquals("John Smith and Jane Doe", StringsRegexChallenge.swapNames("Smith, John and Doe, Jane"));
        }

        @Test
        void testNoSpaceAfterTheComma() {
            assertEquals("Smith,John", StringsRegexChallenge.swapNames("Smith,John"));
        }

        @Test
        void testTwoSpacesAfterTheComma() {
            assertEquals("Smith,  John", StringsRegexChallenge.swapNames("Smith,  John"));
        }

        @Test
        void testChainOfWords() {
            assertEquals("cd ab, ef", StringsRegexChallenge.swapNames("ab, cd, ef"));
        }

        @Test
        void testFourWords() {
            assertEquals("B A, D C", StringsRegexChallenge.swapNames("A, B, C, D"));
        }

        @Test
        void testLowercaseLettersWork() {
            assertEquals("john smith", StringsRegexChallenge.swapNames("smith, john"));
        }

        @Test
        void testTextAfterTheName() {
            assertEquals("Jane Doe.", StringsRegexChallenge.swapNames("Doe, Jane."));
        }

        @Test
        void testAnyTwoWordsWithACommaAreSwapped() {
            assertEquals("world Hello", StringsRegexChallenge.swapNames("Hello, world"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringsRegexChallenge.swapNames(""));
        }

        @Test
        void testNoNames() {
            assertEquals("no names here", StringsRegexChallenge.swapNames("no names here"));
        }

        @Test
        void testDigitsAreNotLetters() {
            assertEquals("1, 2", StringsRegexChallenge.swapNames("1, 2"));
        }

        @Test
        void testSpaceBeforeTheComma() {
            assertEquals("Smith ,John", StringsRegexChallenge.swapNames("Smith ,John"));
            assertEquals("Smith , John", StringsRegexChallenge.swapNames("Smith , John"));
        }

        @Test
        void testSecondNameIsTakenAsLongAsPossible() {
            assertEquals("John Smith Doe", StringsRegexChallenge.swapNames("Smith, John Doe"));
        }

        @Test
        void testOnlyACommaMakesAName() {
            assertEquals("Smith; John", StringsRegexChallenge.swapNames("Smith; John"));
            assertEquals("Smith. John", StringsRegexChallenge.swapNames("Smith. John"));
            assertEquals("Smith - John", StringsRegexChallenge.swapNames("Smith - John"));
        }

        @Test
        void testSingleLetters() {
            assertEquals("b a", StringsRegexChallenge.swapNames("a, b"));
        }

        @Test
        void testTextBeforeTheName() {
            assertEquals("Name: John Smith", StringsRegexChallenge.swapNames("Name: Smith, John"));
        }

        @Test
        void testLastNameIsTakenAsLongAsPossible() {
            assertEquals("Mary Anne Smith", StringsRegexChallenge.swapNames("Mary Smith, Anne"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: countWholeWord
    // ==========================================================
    @Nested
    class CountWholeWordTests {

        @Test
        void testTwoTimes() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("the cat and the hat", "the"));
        }

        @Test
        void testInsideAnotherWordDoesNotCount() {
            assertEquals(1, StringsRegexChallenge.countWholeWord("other the", "the"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(1, StringsRegexChallenge.countWholeWord("The the", "the"));
        }

        @Test
        void testHyphenIsABoundaryButDigitIsNot() {
            assertEquals(1, StringsRegexChallenge.countWholeWord("the-end, the2", "the"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("", "a"));
        }

        @Test
        void testTheWordIsTheWholeText() {
            assertEquals(1, StringsRegexChallenge.countWholeWord("hello", "hello"));
        }

        @Test
        void testNoOccurrences() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("a cat", "dog"));
        }

        @Test
        void testWordAsPrefixOfAnotherWord() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("then", "the"));
        }

        @Test
        void testWordAsSuffixOfAnotherWord() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("bathe", "the"));
        }

        @Test
        void testUnderscoreIsPartOfAWord() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("the_cat", "the"));
            assertEquals(0, StringsRegexChallenge.countWholeWord("cat_the", "the"));
        }

        @Test
        void testDigitBeforeTheWord() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("2the", "the"));
        }

        @Test
        void testPunctuationNextToTheWord() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("the,the", "the"));
            assertEquals(1, StringsRegexChallenge.countWholeWord("(the)", "the"));
            assertEquals(1, StringsRegexChallenge.countWholeWord("the.", "the"));
        }

        @Test
        void testThreeInARow() {
            assertEquals(3, StringsRegexChallenge.countWholeWord("the the the", "the"));
        }

        @Test
        void testSingleLetterWord() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("a ba a", "a"));
        }

        @Test
        void testWordSeparatedByNewLines() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("the\nthe", "the"));
        }

        @Test
        void testWordAtTheStartAndAtTheEnd() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("Java is Java", "Java"));
        }

        @Test
        void testLongerWordWithTheWordInside() {
            assertEquals(2, StringsRegexChallenge.countWholeWord("Java javaJava Java.", "Java"));
        }

        @Test
        void testNotCountingOverlaps() {
            assertEquals(0, StringsRegexChallenge.countWholeWord("aaa", "aa"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: hasRepeatedWord
    // ==========================================================
    @Nested
    class HasRepeatedWordTests {

        @Test
        void testRepeatedWord() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("the the cat"));
        }

        @Test
        void testPrefixOfTheNextWord() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("the theory"));
        }

        @Test
        void testDifferentCase() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("The the"));
            assertFalse(StringsRegexChallenge.hasRepeatedWord("Go go"));
        }

        @Test
        void testTwoSpacesBetweenTheWords() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("go  go"));
        }

        @Test
        void testSingleLetterWord() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("a a"));
        }

        @Test
        void testThreeInARow() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("go go go"));
        }

        @Test
        void testNoRepetition() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("this is a test"));
        }

        @Test
        void testPrefixOfTheNextWordWithBath() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("bath bathe"));
        }

        @Test
        void testRepeatedWordAfterAnotherWordEndingTheSame() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("xa a"));
        }

        @Test
        void testNextWordIsAPrefixOfTheFirst() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("bathe bath"));
            assertFalse(StringsRegexChallenge.hasRepeatedWord("abc ab"));
        }

        @Test
        void testEmptyText() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord(""));
        }

        @Test
        void testOneWord() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("hello"));
        }

        @Test
        void testRepeatedWordInTheMiddle() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("one two two three"));
        }

        @Test
        void testRepeatedWordAtTheEnd() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("x y y"));
        }

        @Test
        void testSameWordNotNextToEachOther() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("a b a"));
        }

        @Test
        void testUppercaseWords() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("GO GO"));
        }

        @Test
        void testTrailingSpace() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("go go "));
        }

        @Test
        void testRepeatedWordAtTheStart() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("hi hi there"));
        }

        @Test
        void testOnlySpaces() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("   "));
        }

        @Test
        void testLongerWordsRepeated() {
            assertTrue(StringsRegexChallenge.hasRepeatedWord("hello hello world"));
        }

        @Test
        void testSimilarWords() {
            assertFalse(StringsRegexChallenge.hasRepeatedWord("ab abc"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: isValidEmail
    // ==========================================================
    @Nested
    class IsValidEmailTests {

        @Test
        void testNormalAddress() {
            assertTrue(StringsRegexChallenge.isValidEmail("ana.lopez@mail.com"));
        }

        @Test
        void testTwoLetterEnding() {
            assertTrue(StringsRegexChallenge.isValidEmail("a@b.co"));
        }

        @Test
        void testFourLetterEnding() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.info"));
        }

        @Test
        void testTwoDotsInTheDomain() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.com.mx"));
        }

        @Test
        void testOneLetterEnding() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.c"));
        }

        @Test
        void testUppercaseLetters() {
            assertTrue(StringsRegexChallenge.isValidEmail("A.B@C.DE"));
            assertTrue(StringsRegexChallenge.isValidEmail("a@b.COM"));
        }

        @Test
        void testDigits() {
            assertTrue(StringsRegexChallenge.isValidEmail("1@2.com"));
        }

        @Test
        void testDigitsInTheEnding() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.c0m"));
        }

        @Test
        void testNoAt() {
            assertFalse(StringsRegexChallenge.isValidEmail("ab.com"));
        }

        @Test
        void testNothingBeforeTheAt() {
            assertFalse(StringsRegexChallenge.isValidEmail("@b.com"));
        }

        @Test
        void testNothingBetweenTheAtAndTheDot() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@.com"));
        }

        @Test
        void testNoDotInTheDomain() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b"));
        }

        @Test
        void testTwoAts() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@@b.com"));
            assertFalse(StringsRegexChallenge.isValidEmail("a@b@c.com"));
        }

        @Test
        void testSpaceInside() {
            assertFalse(StringsRegexChallenge.isValidEmail("a b@c.com"));
        }

        @Test
        void testHyphenInTheDomain() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b-c.com"));
        }

        @Test
        void testUnderscoreAndPlusInTheLocalPart() {
            assertFalse(StringsRegexChallenge.isValidEmail("a_b@c.com"));
            assertFalse(StringsRegexChallenge.isValidEmail("a+b@c.com"));
        }

        @Test
        void testDotsInTheLocalPart() {
            assertTrue(StringsRegexChallenge.isValidEmail("a..b@c.com"));
            assertTrue(StringsRegexChallenge.isValidEmail(".a@b.com"));
        }

        @Test
        void testTwoDotsInTheDomainPart() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b..com"));
        }

        @Test
        void testTheDotMustBeADot() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@bxcom"));
            assertFalse(StringsRegexChallenge.isValidEmail("a@b_com"));
            assertFalse(StringsRegexChallenge.isValidEmail("a@b com"));
        }

        @Test
        void testEndsWithADot() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.com."));
        }

        @Test
        void testNewLineAtTheEnd() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.com\n"));
        }

        @Test
        void testEmptyText() {
            assertFalse(StringsRegexChallenge.isValidEmail(""));
            assertFalse(StringsRegexChallenge.isValidEmail("@"));
        }

        @Test
        void testThreeLetterEnding() {
            assertTrue(StringsRegexChallenge.isValidEmail("user@site.org"));
        }

        @Test
        void testLongLocalPartAndDomain() {
            assertTrue(StringsRegexChallenge.isValidEmail("first.last.1234@company2024.net"));
        }

        @Test
        void testSpaceAtTheEnd() {
            assertFalse(StringsRegexChallenge.isValidEmail("a@b.com "));
        }
    }

    // ==========================================================
    // CHALLENGE 10: camelToSnake
    // ==========================================================
    @Nested
    class CamelToSnakeTests {

        @Test
        void testThreeWords() {
            assertEquals("hello_world_foo", StringsRegexChallenge.camelToSnake("helloWorldFoo"));
        }

        @Test
        void testNoUppercase() {
            assertEquals("hello", StringsRegexChallenge.camelToSnake("hello"));
        }

        @Test
        void testOneUppercaseLetter() {
            assertEquals("a_b", StringsRegexChallenge.camelToSnake("aB"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringsRegexChallenge.camelToSnake(""));
        }

        @Test
        void testConsecutiveUppercaseLetters() {
            assertEquals("get_h_t_t_p_code", StringsRegexChallenge.camelToSnake("getHTTPCode"));
        }

        @Test
        void testSingleLetter() {
            assertEquals("x", StringsRegexChallenge.camelToSnake("x"));
        }

        @Test
        void testUppercaseAtTheEnd() {
            assertEquals("abc_d", StringsRegexChallenge.camelToSnake("abcD"));
        }

        @Test
        void testTwoWords() {
            assertEquals("is_valid", StringsRegexChallenge.camelToSnake("isValid"));
        }

        @Test
        void testRestOfTheWordStaysLowercase() {
            assertEquals("a_bc", StringsRegexChallenge.camelToSnake("aBc"));
        }

        @Test
        void testTwoUppercaseLettersInARow() {
            assertEquals("a_b_cd", StringsRegexChallenge.camelToSnake("aBCd"));
        }

        @Test
        void testLongName() {
            assertEquals("my_very_long_variable_name",
                    StringsRegexChallenge.camelToSnake("myVeryLongVariableName"));
        }

        @Test
        void testTheFirstLetterGetsNoUnderscore() {
            assertEquals("hello_world", StringsRegexChallenge.camelToSnake("helloWorld"));
        }

        @Test
        void testUnderscoresAreOnlyBeforeUppercaseLetters() {
            assertFalse(StringsRegexChallenge.camelToSnake("abcdef").contains("_"));
        }

        @Test
        void testLowercaseResult() {
            assertEquals("abc_def_ghi", StringsRegexChallenge.camelToSnake("abcDefGhi"));
        }

        @Test
        void testA() {
            assertEquals("a_a", StringsRegexChallenge.camelToSnake("aA"));
        }

        @Test
        void testZ() {
            assertEquals("a_z", StringsRegexChallenge.camelToSnake("aZ"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: extractHashtags
    // ==========================================================
    @Nested
    class ExtractHashtagsTests {

        @Test
        void testTwoHashtags() {
            assertEquals(List.of("java", "fun"), StringsRegexChallenge.extractHashtags("#java is #fun"));
        }

        @Test
        void testHashAlone() {
            assertEquals(List.of(), StringsRegexChallenge.extractHashtags("# alone"));
        }

        @Test
        void testHashInTheMiddleAndDoubleHash() {
            assertEquals(List.of("b_1", "c"), StringsRegexChallenge.extractHashtags("a#b_1 ##c"));
        }

        @Test
        void testNoTags() {
            assertEquals(List.of(), StringsRegexChallenge.extractHashtags("no tags"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), StringsRegexChallenge.extractHashtags(""));
        }

        @Test
        void testOnlyAHash() {
            assertEquals(List.of(), StringsRegexChallenge.extractHashtags("#"));
        }

        @Test
        void testDigitsAndUnderscores() {
            assertEquals(List.of("a_1", "B"), StringsRegexChallenge.extractHashtags("#a_1 #B"));
        }

        @Test
        void testOnlyDigits() {
            assertEquals(List.of("123"), StringsRegexChallenge.extractHashtags("#123"));
        }

        @Test
        void testOnlyUnderscore() {
            assertEquals(List.of("_"), StringsRegexChallenge.extractHashtags("#_"));
        }

        @Test
        void testHashtagsGlued() {
            assertEquals(List.of("tag", "other"), StringsRegexChallenge.extractHashtags("#tag#other"));
        }

        @Test
        void testPunctuationEndsTheHashtag() {
            assertEquals(List.of("tag"), StringsRegexChallenge.extractHashtags("#tag."));
            assertEquals(List.of("tag"), StringsRegexChallenge.extractHashtags("#tag!"));
            assertEquals(List.of("tag"), StringsRegexChallenge.extractHashtags("#tag-name"));
        }

        @Test
        void testOnlyLettersFromAToZ() {
            assertEquals(List.of("a"), StringsRegexChallenge.extractHashtags("#año"));
        }

        @Test
        void testRepeatedHashtagsStay() {
            assertEquals(List.of("a", "a"), StringsRegexChallenge.extractHashtags("#a #a"));
        }

        @Test
        void testUppercaseIsKept() {
            assertEquals(List.of("Java"), StringsRegexChallenge.extractHashtags("#Java"));
        }

        @Test
        void testHashFollowedBySpace() {
            assertEquals(List.of(), StringsRegexChallenge.extractHashtags("text # tag"));
        }

        @Test
        void testHashtagAtTheEnd() {
            assertEquals(List.of("end"), StringsRegexChallenge.extractHashtags("the #end"));
        }

        @Test
        void testHashtagsOnSeparateLines() {
            assertEquals(List.of("a", "b"), StringsRegexChallenge.extractHashtags("#a\n#b"));
        }

        @Test
        void testLongHashtag() {
            assertEquals(List.of("very_long_Tag_123"), StringsRegexChallenge.extractHashtags("#very_long_Tag_123"));
        }
    }
}