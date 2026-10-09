package dev.perfectbogus.regex;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegexLookaroundPracticeChallengeTest {

    // ==========================================================
    // CHALLENGE 1: extractBracketContents
    // ==========================================================
    @Nested
    class ExtractBracketContentsTests {
        @Test
        void testReturnsTheAlphanumericContents() {
            assertEquals(List.of("ab", "1"),
                    RegexLookaroundPracticeChallenge.extractBracketContents("[ab] [c d] [] [1]"));
            assertEquals(List.of("abc", "def"),
                    RegexLookaroundPracticeChallenge.extractBracketContents("x[abc]y[def]"));
            assertEquals(List.of("Z9", "007"), RegexLookaroundPracticeChallenge.extractBracketContents("[Z9] [007]"));
        }

        @Test
        void testOtherContentsAreSkipped() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("[a-b] [a_b] [a.b]"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("[a b]"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("[é]"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("[]"));
        }

        @Test
        void testNestedAndBrokenBrackets() {
            assertEquals(List.of("ab"), RegexLookaroundPracticeChallenge.extractBracketContents("[[ab]]"));
            assertEquals(List.of("b"), RegexLookaroundPracticeChallenge.extractBracketContents("[a[b]"));
            assertEquals(List.of("a"), RegexLookaroundPracticeChallenge.extractBracketContents("[a]b]"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("[ab"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("ab]"));
        }

        @Test
        void testNoBrackets() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents("plain text 123"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractBracketContents(""));
        }
    }

    // ==========================================================
    // CHALLENGE 2: hashDigitsAfterLetters
    // ==========================================================
    @Nested
    class HashDigitsAfterLettersTests {
        @Test
        void testReplacesOnlyTheDigitRightAfterALetter() {
            assertEquals("a#2b#", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("a12b3"));
            assertEquals("ab#2cd", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("ab12cd"));
            assertEquals("x#y#z#", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("x9y8z7"));
        }

        @Test
        void testDigitsNotAfterLettersStay() {
            assertEquals("123", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("123"));
            assertEquals("a# 2", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("a1 2"));
            assertEquals("_1", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("_1"));
            assertEquals("-5", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("-5"));
        }

        @Test
        void testTextWithoutDigitsOrLetters() {
            assertEquals("abc", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("abc"));
            assertEquals("", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters(""));
            assertEquals("A#", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("A1"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("é1", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("é1"));
            assertEquals("aé1", RegexLookaroundPracticeChallenge.hashDigitsAfterLetters("aé1"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: underscoreLettersBeforeDigits
    // ==========================================================
    @Nested
    class UnderscoreLettersBeforeDigitsTests {
        @Test
        void testReplacesTheLetterRightBeforeADigit() {
            assertEquals("_1_22", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("a1b22"));
            assertEquals("a_1", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("ab1"));
            assertEquals("_9_8_7", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("x9y8z7"));
            assertEquals("_1_2", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("A1B2"));
        }

        @Test
        void testLettersNotBeforeDigitsStay() {
            assertEquals("abc", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("abc"));
            assertEquals("1a", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("1a"));
            assertEquals("a 1", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("a 1"));
            assertEquals("a-1", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("a-1"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("é1", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("é1"));
            assertEquals("bé1", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("bé1"));
        }

        @Test
        void testEmptyAndDigitsOnly() {
            assertEquals("", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits(""));
            assertEquals("123", RegexLookaroundPracticeChallenge.underscoreLettersBeforeDigits("123"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: splitBeforeUppercase
    // ==========================================================
    @Nested
    class SplitBeforeUppercaseTests {
        @Test
        void testSplitsBeforeEveryUppercaseLetter() {
            assertEquals(List.of("Hello", "World", "Foo"),
                    RegexLookaroundPracticeChallenge.splitBeforeUppercase("HelloWorldFoo"));
            assertEquals(List.of("hello", "World"),
                    RegexLookaroundPracticeChallenge.splitBeforeUppercase("helloWorld"));
            assertEquals(List.of("a", "Bc", "D"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("aBcD"));
        }

        @Test
        void testEveryLetterCapital() {
            assertEquals(List.of("A", "B", "C"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("ABC"));
        }

        @Test
        void testNoUppercaseOrNoSplitNeeded() {
            assertEquals(List.of("abc"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("abc"));
            assertEquals(List.of("Abc"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("Abc"));
            assertEquals(List.of("a"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("a"));
        }

        @Test
        void testOtherCharactersStayInThePieces() {
            assertEquals(List.of("a1", "B2"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("a1B2"));
            assertEquals(List.of("Hello ", "World"),
                    RegexLookaroundPracticeChallenge.splitBeforeUppercase("Hello World"));
            assertEquals(List.of("aÉb"), RegexLookaroundPracticeChallenge.splitBeforeUppercase("aÉb"));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.splitBeforeUppercase(""));
        }
    }

    // ==========================================================
    // CHALLENGE 5: splitAfterCommas
    // ==========================================================
    @Nested
    class SplitAfterCommasTests {
        @Test
        void testEveryPieceKeepsItsComma() {
            assertEquals(List.of("a,", "b,", "c"), RegexLookaroundPracticeChallenge.splitAfterCommas("a,b,c"));
            assertEquals(List.of("a,", " b"), RegexLookaroundPracticeChallenge.splitAfterCommas("a, b"));
        }

        @Test
        void testSeveralCommasInARow() {
            assertEquals(List.of("a,", ",", "b"), RegexLookaroundPracticeChallenge.splitAfterCommas("a,,b"));
            assertEquals(List.of(",", ",", ","), RegexLookaroundPracticeChallenge.splitAfterCommas(",,,"));
        }

        @Test
        void testCommaAtTheStartOrAtTheEnd() {
            assertEquals(List.of("a,"), RegexLookaroundPracticeChallenge.splitAfterCommas("a,"));
            assertEquals(List.of(","), RegexLookaroundPracticeChallenge.splitAfterCommas(","));
            assertEquals(List.of(",", "a"), RegexLookaroundPracticeChallenge.splitAfterCommas(",a"));
        }

        @Test
        void testNoCommasOrEmptyInput() {
            assertEquals(List.of("abc"), RegexLookaroundPracticeChallenge.splitAfterCommas("abc"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.splitAfterCommas(""));
        }
    }

    // ==========================================================
    // CHALLENGE 6: countIsolatedDigits
    // ==========================================================
    @Nested
    class CountIsolatedDigitsTests {
        @Test
        void testCountsDigitsWithoutDigitNeighbours() {
            assertEquals(2, RegexLookaroundPracticeChallenge.countIsolatedDigits("a1b22c333d4"));
            assertEquals(1, RegexLookaroundPracticeChallenge.countIsolatedDigits("7"));
            assertEquals(2, RegexLookaroundPracticeChallenge.countIsolatedDigits("1 2"));
            assertEquals(3, RegexLookaroundPracticeChallenge.countIsolatedDigits("1.2.3"));
        }

        @Test
        void testRunsOfSeveralDigitsDoNotCount() {
            assertEquals(0, RegexLookaroundPracticeChallenge.countIsolatedDigits("12"));
            assertEquals(0, RegexLookaroundPracticeChallenge.countIsolatedDigits("123456"));
            assertEquals(2, RegexLookaroundPracticeChallenge.countIsolatedDigits("1a22b3"));
        }

        @Test
        void testLineBreaksSeparateDigits() {
            assertEquals(2, RegexLookaroundPracticeChallenge.countIsolatedDigits("1\n2"));
            assertEquals(1, RegexLookaroundPracticeChallenge.countIsolatedDigits("12\n3"));
        }

        @Test
        void testNoDigits() {
            assertEquals(0, RegexLookaroundPracticeChallenge.countIsolatedDigits("abc"));
            assertEquals(0, RegexLookaroundPracticeChallenge.countIsolatedDigits(""));
        }
    }

    // ==========================================================
    // CHALLENGE 7: startsWithLetterEndsWithDigit
    // ==========================================================
    @Nested
    class StartsWithLetterEndsWithDigitTests {
        @Test
        void testLetterFirstAndDigitLast() {
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("abc1"));
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a1"));
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("Z9"));
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a 1"));
        }

        @Test
        void testWrongFirstOrLastCharacter() {
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("1abc"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("abc"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a1 "));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("_a1"));
        }

        @Test
        void testSingleCharactersAndEmptyText() {
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("1"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit(""));
        }

        @Test
        void testLineBreaks() {
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a\n1"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a1\n"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("\na1"));
            assertTrue(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a\n\n1"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("é1"));
            assertFalse(RegexLookaroundPracticeChallenge.startsWithLetterEndsWithDigit("a9é"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: isVisibleTextFile
    // ==========================================================
    @Nested
    class IsVisibleTextFileTests {
        @Test
        void testAcceptsTextFiles() {
            assertTrue(RegexLookaroundPracticeChallenge.isVisibleTextFile("notes.txt"));
            assertTrue(RegexLookaroundPracticeChallenge.isVisibleTextFile("my file.txt"));
            assertTrue(RegexLookaroundPracticeChallenge.isVisibleTextFile("x.txt"));
            assertTrue(RegexLookaroundPracticeChallenge.isVisibleTextFile("a.b.txt"));
            assertTrue(RegexLookaroundPracticeChallenge.isVisibleTextFile("a.txt.txt"));
        }

        @Test
        void testRejectsHiddenFiles() {
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile(".hidden.txt"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile(".txt"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile(".a.txt"));
        }

        @Test
        void testRejectsOtherExtensions() {
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("a.TXT"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("a.Txt"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("a.txt.bak"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("atxt"));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("txt"));
        }

        @Test
        void testRejectsNamesThatDoNotEndWithTheExtension() {
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("notes.txt "));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile("notes.txt."));
            assertFalse(RegexLookaroundPracticeChallenge.isVisibleTextFile(""));
        }
    }

    // ==========================================================
    // CHALLENGE 9: removeSpacesBeforePunctuation
    // ==========================================================
    @Nested
    class RemoveSpacesBeforePunctuationTests {
        @Test
        void testRemovesSpacesBeforePunctuation() {
            assertEquals("Hello, world!",
                    RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("Hello , world !"));
            assertEquals("a. b? c", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a . b ? c"));
        }

        @Test
        void testRemovesWholeRunsOfSpaces() {
            assertEquals("a,b", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a  ,b"));
            assertEquals("a.", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a    ."));
            assertEquals(".", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation(" ."));
        }

        @Test
        void testSpacesAfterPunctuationStay() {
            assertEquals("a,  b", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a ,  b"));
            assertEquals("a,, b", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a , , b"));
        }

        @Test
        void testOtherCharactersAreNotAffected() {
            assertEquals("x ;y", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("x ;y"));
            assertEquals("a \t,", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("a \t,"));
            assertEquals("no change here",
                    RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("no change here"));
            assertEquals("", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation(""));
        }

        @Test
        void testSeveralPunctuationMarks() {
            assertEquals("wait...", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("wait ..."));
            assertEquals("what?!", RegexLookaroundPracticeChallenge.removeSpacesBeforePunctuation("what ?!"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: spaceBetweenLowerAndUpper
    // ==========================================================
    @Nested
    class SpaceBetweenLowerAndUpperTests {
        @Test
        void testInsertsASpaceBetweenLowerAndUpper() {
            assertEquals("hello World", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("helloWorld"));
            assertEquals("foo Bar Baz", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("fooBarBaz"));
            assertEquals("a B c D", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("aB cD"));
        }

        @Test
        void testRunsOfCapitalsAreKept() {
            assertEquals("ABC", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("ABC"));
            assertEquals("Hello WORLD", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("HelloWORLD"));
            assertEquals("get HTTPResponse",
                    RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("getHTTPResponse"));
        }

        @Test
        void testNothingToInsert() {
            assertEquals("abc", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("abc"));
            assertEquals("Abc", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("Abc"));
            assertEquals("a1B", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("a1B"));
            assertEquals("", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper(""));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("éB", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("éB"));
            assertEquals("aÉ", RegexLookaroundPracticeChallenge.spaceBetweenLowerAndUpper("aÉ"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: maskInnerLetters
    // ==========================================================
    @Nested
    class MaskInnerLettersTests {
        @Test
        void testMasksTheInnerLettersOfEveryWord() {
            assertEquals("h***o w***d", RegexLookaroundPracticeChallenge.maskInnerLetters("hello world"));
            assertEquals("a**d", RegexLookaroundPracticeChallenge.maskInnerLetters("abcd"));
            assertEquals("H***o-W***d", RegexLookaroundPracticeChallenge.maskInnerLetters("Hello-World"));
        }

        @Test
        void testShortWordsAreNotChanged() {
            assertEquals("a", RegexLookaroundPracticeChallenge.maskInnerLetters("a"));
            assertEquals("ab", RegexLookaroundPracticeChallenge.maskInnerLetters("ab"));
            assertEquals("ab c*e", RegexLookaroundPracticeChallenge.maskInnerLetters("ab cde"));
            assertEquals("x y z", RegexLookaroundPracticeChallenge.maskInnerLetters("x y z"));
        }

        @Test
        void testDigitsEndAWord() {
            assertEquals("a*c123d*f", RegexLookaroundPracticeChallenge.maskInnerLetters("abc123def"));
            assertEquals("a1b", RegexLookaroundPracticeChallenge.maskInnerLetters("a1b"));
            assertEquals("12ab34", RegexLookaroundPracticeChallenge.maskInnerLetters("12ab34"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("aéb", RegexLookaroundPracticeChallenge.maskInnerLetters("aéb"));
            assertEquals("abéc", RegexLookaroundPracticeChallenge.maskInnerLetters("abéc"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexLookaroundPracticeChallenge.maskInnerLetters(""));
        }
    }

    // ==========================================================
    // CHALLENGE 12: groupDigitsWithCommas
    // ==========================================================
    @Nested
    class GroupDigitsWithCommasTests {
        @Test
        void testGroupsThreeDigitsFromTheRight() {
            assertEquals("1,234,567", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("1234567"));
            assertEquals("1,234", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("1234"));
            assertEquals("12,345", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("12345"));
            assertEquals("123,456", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("123456"));
        }

        @Test
        void testShortNumbersAreUnchanged() {
            assertEquals("123", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("123"));
            assertEquals("12", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("12"));
            assertEquals("1", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("1"));
            assertEquals("", RegexLookaroundPracticeChallenge.groupDigitsWithCommas(""));
        }

        @Test
        void testLongNumbers() {
            assertEquals("1,234,567,890", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("1234567890"));
            assertEquals("100,000", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("100000"));
            assertEquals("0,001,234", RegexLookaroundPracticeChallenge.groupDigitsWithCommas("0001234"));
        }
    }

    // ==========================================================
    // CHALLENGE 13: isValidPin
    // ==========================================================
    @Nested
    class IsValidPinTests {
        @Test
        void testAcceptsValidPins() {
            assertTrue(RegexLookaroundPracticeChallenge.isValidPin("1234"));
            assertTrue(RegexLookaroundPracticeChallenge.isValidPin("1112"));
            assertTrue(RegexLookaroundPracticeChallenge.isValidPin("1000"));
            assertTrue(RegexLookaroundPracticeChallenge.isValidPin("2111"));
            assertTrue(RegexLookaroundPracticeChallenge.isValidPin("1211"));
        }

        @Test
        void testRejectsPinsStartingWithZero() {
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("0123"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("0000"));
        }

        @Test
        void testRejectsPinsWithFourEqualDigits() {
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("1111"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("7777"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("9999"));
        }

        @Test
        void testRejectsWrongLengths() {
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("123"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("12345"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin(""));
        }

        @Test
        void testRejectsOtherCharacters() {
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("12a4"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin(" 1234"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("1234\n"));
            assertFalse(RegexLookaroundPracticeChallenge.isValidPin("12 4"));
        }
    }

    // ==========================================================
    // CHALLENGE 14: hasNoDigitRepeatedInARow
    // ==========================================================
    @Nested
    class HasNoDigitRepeatedInARowTests {
        @Test
        void testNoRepeatedDigits() {
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1213"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("123123"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1a1"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("abc"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow(""));
        }

        @Test
        void testRepeatedDigits() {
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1223"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("00"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("9887"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("3 33"));
        }

        @Test
        void testOtherCharactersBetweenEqualDigits() {
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1 1"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1.1"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("a11b"));
        }

        @Test
        void testLineBreaks() {
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("x9\n9"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("ab\n11"));
            assertFalse(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("x\n\n99\n"));
            assertTrue(RegexLookaroundPracticeChallenge.hasNoDigitRepeatedInARow("1\n\n1"));
        }
    }

    // ==========================================================
    // CHALLENGE 15: collapseSpaces
    // ==========================================================
    @Nested
    class CollapseSpacesTests {
        @Test
        void testCollapsesRunsOfSpaces() {
            assertEquals("a b", RegexLookaroundPracticeChallenge.collapseSpaces("a   b"));
            assertEquals("a b c", RegexLookaroundPracticeChallenge.collapseSpaces("a  b   c"));
            assertEquals(" ", RegexLookaroundPracticeChallenge.collapseSpaces("     "));
        }

        @Test
        void testSpacesAtTheEnds() {
            assertEquals(" a", RegexLookaroundPracticeChallenge.collapseSpaces("  a"));
            assertEquals("a ", RegexLookaroundPracticeChallenge.collapseSpaces("a  "));
            assertEquals(" ", RegexLookaroundPracticeChallenge.collapseSpaces("  "));
        }

        @Test
        void testSingleSpacesAreUnchanged() {
            assertEquals("a b", RegexLookaroundPracticeChallenge.collapseSpaces("a b"));
            assertEquals("a b c", RegexLookaroundPracticeChallenge.collapseSpaces("a b c"));
            assertEquals(" a ", RegexLookaroundPracticeChallenge.collapseSpaces(" a "));
        }

        @Test
        void testOtherWhitespaceIsNotCollapsed() {
            assertEquals("a\t\tb", RegexLookaroundPracticeChallenge.collapseSpaces("a\t\tb"));
            assertEquals("a \t b", RegexLookaroundPracticeChallenge.collapseSpaces("a \t b"));
            assertEquals("a\n\nb", RegexLookaroundPracticeChallenge.collapseSpaces("a\n\nb"));
        }

        @Test
        void testSpacesAroundLineBreaks() {
            assertEquals("a \n b", RegexLookaroundPracticeChallenge.collapseSpaces("a \n  b"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexLookaroundPracticeChallenge.collapseSpaces(""));
        }
    }

    // ==========================================================
    // CHALLENGE 16: underscoreSpacesInParentheses
    // ==========================================================
    @Nested
    class UnderscoreSpacesInParenthesesTests {
        @Test
        void testReplacesTheSpacesInsideParentheses() {
            assertEquals("a (b_c) d (e_f)",
                    RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("a (b c) d (e f)"));
            assertEquals("(a_b) (c_d)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a b) (c d)"));
        }

        @Test
        void testSpacesNextToTheParentheses() {
            assertEquals("(_a_)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("( a )"));
            assertEquals("x (_) y", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("x ( ) y"));
            assertEquals("(a_b)c d", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a b)c d"));
        }

        @Test
        void testSeveralSpacesInside() {
            assertEquals("(a__b)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a  b)"));
            assertEquals("(a_b_c_d)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a b c d)"));
        }

        @Test
        void testNoSpacesToReplace() {
            assertEquals("no parens at all",
                    RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("no parens at all"));
            assertEquals("()", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("()"));
            assertEquals("(ab)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(ab)"));
            assertEquals("", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses(""));
        }

        @Test
        void testLineBreaksInside() {
            assertEquals("(a\nb_c)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a\nb c)"));
            assertEquals("(a_b\nc)", RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("(a b\nc)"));
            assertEquals("a\n(b_c)\nd e",
                    RegexLookaroundPracticeChallenge.underscoreSpacesInParentheses("a\n(b c)\nd e"));
        }
    }

    // ==========================================================
    // CHALLENGE 17: splitSemicolonsOutsideParentheses
    // ==========================================================
    @Nested
    class SplitSemicolonsOutsideParenthesesTests {
        @Test
        void testSplitsAtSemicolons() {
            assertEquals(List.of("a", "b", "c"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("a;b;c"));
            assertEquals(List.of("no semicolons"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("no semicolons"));
        }

        @Test
        void testSemicolonsInsideParenthesesAreKept() {
            assertEquals(List.of("a", "(b;c)", "d"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("a;(b;c);d"));
            assertEquals(List.of("(a;b)"), RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("(a;b)"));
            assertEquals(List.of("x", "(a;b)", "(c;d)", "y"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("x;(a;b);(c;d);y"));
            assertEquals(List.of("f(x;y)", "g(z)"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("f(x;y);g(z)"));
        }

        @Test
        void testEmptyPiecesInTheMiddle() {
            assertEquals(List.of("a", "", "b"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("a;;b"));
            assertEquals(List.of("a", "", "", "b"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("a;;;b"));
        }

        @Test
        void testEmptyPiecesAtTheEnds() {
            assertEquals(List.of("a", ""), RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("a;"));
            assertEquals(List.of("", ""), RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses(";"));
            assertEquals(List.of("(a;b)", ""),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses("(a;b);"));
            assertEquals(List.of("", "(a;b)"),
                    RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses(";(a;b)"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(""), RegexLookaroundPracticeChallenge.splitSemicolonsOutsideParentheses(""));
        }
    }

    // ==========================================================
    // CHALLENGE 18: extractRepeatedWords
    // ==========================================================
    @Nested
    class ExtractRepeatedWordsTests {
        @Test
        void testFindsImmediateRepetitions() {
            assertEquals(List.of("the"), RegexLookaroundPracticeChallenge.extractRepeatedWords("the the cat"));
            assertEquals(List.of("is", "fine"),
                    RegexLookaroundPracticeChallenge.extractRepeatedWords("it is is fine fine"));
            assertEquals(List.of("bb"), RegexLookaroundPracticeChallenge.extractRepeatedWords("bb bb"));
        }

        @Test
        void testSeveralRepetitionsInARow() {
            assertEquals(List.of("a", "a"), RegexLookaroundPracticeChallenge.extractRepeatedWords("a a a"));
            assertEquals(List.of("no", "no", "no"),
                    RegexLookaroundPracticeChallenge.extractRepeatedWords("no no no no"));
        }

        @Test
        void testDifferentCaseOrDifferentWordsAreNotRepetitions() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("Hello hello"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("the then"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("ab abc"));
        }

        @Test
        void testTheWordsMustBeWholeWords() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("athe the"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("abab ab"));
            assertEquals(List.of("the"), RegexLookaroundPracticeChallenge.extractRepeatedWords("the the."));
        }

        @Test
        void testOnlyASingleSpaceCounts() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("a  a"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("a, a"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("a\na"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("a\ta"));
        }

        @Test
        void testNothingToFind() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords(""));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractRepeatedWords("one two three"));
        }
    }

    // ==========================================================
    // CHALLENGE 19: extractCapitalizedMidSentence
    // ==========================================================
    @Nested
    class ExtractCapitalizedMidSentenceTests {
        @Test
        void testSkipsTheFirstWordOfEachSentence() {
            assertEquals(List.of("World", "Paris"),
                    RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("Hello World. This is Paris"));
            assertEquals(List.of(),
                    RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("hello. world. Next"));
            assertEquals(List.of("B", "C"), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("A B C"));
        }

        @Test
        void testWordsThatAreNotCapitalized() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("iPhone and iPad"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("one two three"));
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("x Éclair"));
        }

        @Test
        void testWholeWordsAreReturned() {
            assertEquals(List.of("NASA"),
                    RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("NASA launched. The NASA team"));
            assertEquals(List.of("Dick", "Harry"),
                    RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("Tom, Dick and Harry"));
        }

        @Test
        void testOnlyAPeriodAndOneSpaceStartsASentence() {
            assertEquals(List.of("Now"), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("Go! Now"));
            assertEquals(List.of("There"), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("Hi.There"));
            assertEquals(List.of("Next"), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("end.\nNext"));
            assertEquals(List.of("Ann"),
                    RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence("Mr. Smith met Ann"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexLookaroundPracticeChallenge.extractCapitalizedMidSentence(""));
        }
    }

    // ==========================================================
    // CHALLENGE 20: findLastWord
    // ==========================================================
    @Nested
    class FindLastWordTests {
        @Test
        void testReturnsTheLastWord() {
            assertEquals(Optional.of("world"), RegexLookaroundPracticeChallenge.findLastWord("Hello big world!"));
            assertEquals(Optional.of("a"), RegexLookaroundPracticeChallenge.findLastWord("a"));
            assertEquals(Optional.of("word"), RegexLookaroundPracticeChallenge.findLastWord("word  "));
            assertEquals(Optional.of("word"), RegexLookaroundPracticeChallenge.findLastWord("  word"));
        }

        @Test
        void testDigitsAndPunctuationEndAWord() {
            assertEquals(Optional.of("abc"), RegexLookaroundPracticeChallenge.findLastWord("abc1"));
            assertEquals(Optional.of("z"), RegexLookaroundPracticeChallenge.findLastWord("x y1 z2"));
            assertEquals(Optional.of("b"), RegexLookaroundPracticeChallenge.findLastWord("a-b"));
            assertEquals(Optional.of("caf"), RegexLookaroundPracticeChallenge.findLastWord("café"));
        }

        @Test
        void testLineBreaks() {
            assertEquals(Optional.of("last"), RegexLookaroundPracticeChallenge.findLastWord("first\nlast"));
            assertEquals(Optional.of("end"), RegexLookaroundPracticeChallenge.findLastWord("end.\n\n"));
            assertEquals(Optional.of("three"), RegexLookaroundPracticeChallenge.findLastWord("one two\nthree\n"));
        }

        @Test
        void testNoWords() {
            assertEquals(Optional.empty(), RegexLookaroundPracticeChallenge.findLastWord("123"));
            assertEquals(Optional.empty(), RegexLookaroundPracticeChallenge.findLastWord(""));
            assertEquals(Optional.empty(), RegexLookaroundPracticeChallenge.findLastWord("!!!"));
            assertEquals(Optional.empty(), RegexLookaroundPracticeChallenge.findLastWord("1 2\n3"));
        }
    }
}