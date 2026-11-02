package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexRearViewChallengeTest {

    // ==========================================================
    // CHALLENGE 1: wordsAfterAt
    // ==========================================================
    @Nested
    class WordsAfterAtTests {

        @Test
        void testTwoMentions() {
            assertEquals(List.of("ana", "bob"), RegexRearViewChallenge.wordsAfterAt("hi @ana and @bob"));
        }

        @Test
        void testWordInTheMiddleOfText() {
            assertEquals(List.of("b"), RegexRearViewChallenge.wordsAfterAt("a@b1"));
        }

        @Test
        void testDigitAfterAt() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt("@1ab"));
        }

        @Test
        void testNoAt() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt("ana bob"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt(""));
        }

        @Test
        void testOnlyAt() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt("@"));
        }

        @Test
        void testSpaceAfterAt() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt("@ ana"));
        }

        @Test
        void testWholeWordIsReturned() {
            assertEquals(List.of("hello"), RegexRearViewChallenge.wordsAfterAt("@hello"));
        }

        @Test
        void testWordStopsAtNonLetter() {
            assertEquals(List.of("ab"), RegexRearViewChallenge.wordsAfterAt("@ab1c"));
        }

        @Test
        void testUpperAndLowerCase() {
            assertEquals(List.of("Ana", "BOB"), RegexRearViewChallenge.wordsAfterAt("@Ana @BOB"));
        }

        @Test
        void testTwoAtsInARow() {
            assertEquals(List.of("ab"), RegexRearViewChallenge.wordsAfterAt("@@ab"));
        }

        @Test
        void testSecondWordOfTheSentenceIsIgnored() {
            assertEquals(List.of("ana"), RegexRearViewChallenge.wordsAfterAt("@ana bob"));
        }

        @Test
        void testAtAtTheEnd() {
            assertEquals(List.of("x"), RegexRearViewChallenge.wordsAfterAt("hi @x @"));
        }

        @Test
        void testWordsWithAtInside() {
            assertEquals(List.of("example"), RegexRearViewChallenge.wordsAfterAt("me@example.com"));
        }

        @Test
        void testOtherSymbolsDoNotCount() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterAt("#ana $bob &cy"));
        }

        @Test
        void testSingleLetter() {
            assertEquals(List.of("a", "b"), RegexRearViewChallenge.wordsAfterAt("@a,@b"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: replaceCommasAfterDigits
    // ==========================================================
    @Nested
    class ReplaceCommasAfterDigitsTests {

        @Test
        void testMixed() {
            assertEquals("1;2; a,3", RegexRearViewChallenge.replaceCommasAfterDigits("1,2, a,3"));
        }

        @Test
        void testDoubleComma() {
            assertEquals("1;,2", RegexRearViewChallenge.replaceCommasAfterDigits("1,,2"));
        }

        @Test
        void testCommaAtTheStart() {
            assertEquals(",1", RegexRearViewChallenge.replaceCommasAfterDigits(",1"));
        }

        @Test
        void testLettersAroundComma() {
            assertEquals("a,b", RegexRearViewChallenge.replaceCommasAfterDigits("a,b"));
        }

        @Test
        void testCommaAtTheEnd() {
            assertEquals("12;", RegexRearViewChallenge.replaceCommasAfterDigits("12,"));
        }

        @Test
        void testNoCommas() {
            assertEquals("123 abc", RegexRearViewChallenge.replaceCommasAfterDigits("123 abc"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexRearViewChallenge.replaceCommasAfterDigits(""));
        }

        @Test
        void testDigitAfterTheCommaDoesNotMatter() {
            assertEquals("a,1", RegexRearViewChallenge.replaceCommasAfterDigits("a,1"));
        }

        @Test
        void testSpaceBeforeComma() {
            assertEquals("1 ,2", RegexRearViewChallenge.replaceCommasAfterDigits("1 ,2"));
        }

        @Test
        void testManyNumbers() {
            assertEquals("1;2;3;4", RegexRearViewChallenge.replaceCommasAfterDigits("1,2,3,4"));
        }

        @Test
        void testOnlyComma() {
            assertEquals(",", RegexRearViewChallenge.replaceCommasAfterDigits(","));
        }

        @Test
        void testTripleComma() {
            assertEquals("5;,,6", RegexRearViewChallenge.replaceCommasAfterDigits("5,,,6"));
        }

        @Test
        void testOtherSymbolsAreKept() {
            assertEquals("1.5;2.5", RegexRearViewChallenge.replaceCommasAfterDigits("1.5,2.5"));
        }

        @Test
        void testDotBeforeComma() {
            assertEquals("a.,b 1.5;2", RegexRearViewChallenge.replaceCommasAfterDigits("a.,b 1.5,2"));
        }

        @Test
        void testSemicolonAlreadyThere() {
            assertEquals("1;2;3", RegexRearViewChallenge.replaceCommasAfterDigits("1;2,3"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: maskAfterFirstThree
    // ==========================================================
    @Nested
    class MaskAfterFirstThreeTests {

        @Test
        void testSevenDigits() {
            assertEquals("123****", RegexRearViewChallenge.maskAfterFirstThree("1234567"));
        }

        @Test
        void testFourDigits() {
            assertEquals("123*", RegexRearViewChallenge.maskAfterFirstThree("1234"));
        }

        @Test
        void testThreeDigits() {
            assertEquals("123", RegexRearViewChallenge.maskAfterFirstThree("123"));
        }

        @Test
        void testTwoDigits() {
            assertEquals("12", RegexRearViewChallenge.maskAfterFirstThree("12"));
        }

        @Test
        void testOneDigit() {
            assertEquals("1", RegexRearViewChallenge.maskAfterFirstThree("1"));
        }

        @Test
        void testEmpty() {
            assertEquals("", RegexRearViewChallenge.maskAfterFirstThree(""));
        }

        @Test
        void testRepeatedDigits() {
            assertEquals("777*****", RegexRearViewChallenge.maskAfterFirstThree("77777777"));
        }

        @Test
        void testZeros() {
            assertEquals("000***", RegexRearViewChallenge.maskAfterFirstThree("000000"));
        }

        @Test
        void testTenDigits() {
            assertEquals("555*******", RegexRearViewChallenge.maskAfterFirstThree("5551234567"));
        }

        @Test
        void testLengthIsKept() {
            assertEquals(12, RegexRearViewChallenge.maskAfterFirstThree("123456789012").length());
        }

        @Test
        void testOnlyFirstThreeSurvive() {
            assertEquals("987******", RegexRearViewChallenge.maskAfterFirstThree("987654321"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: numbersNotAfterMinus
    // ==========================================================
    @Nested
    class NumbersNotAfterMinusTests {

        @Test
        void testMixed() {
            assertEquals(List.of("5", "10"), RegexRearViewChallenge.numbersNotAfterMinus("5 -3 10-2"));
        }

        @Test
        void testWholeSequenceIsLeftOut() {
            assertEquals(List.of(), RegexRearViewChallenge.numbersNotAfterMinus("-35"));
        }

        @Test
        void testAfterLetters() {
            assertEquals(List.of("12"), RegexRearViewChallenge.numbersNotAfterMinus("a12 b-7"));
        }

        @Test
        void testNoNumbers() {
            assertEquals(List.of(), RegexRearViewChallenge.numbersNotAfterMinus("abc"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexRearViewChallenge.numbersNotAfterMinus(""));
        }

        @Test
        void testAllPositive() {
            assertEquals(List.of("1", "22", "333"), RegexRearViewChallenge.numbersNotAfterMinus("1 22 333"));
        }

        @Test
        void testLongNegative() {
            assertEquals(List.of(), RegexRearViewChallenge.numbersNotAfterMinus("-123456"));
        }

        @Test
        void testSpaceAfterMinus() {
            assertEquals(List.of("5"), RegexRearViewChallenge.numbersNotAfterMinus("- 5"));
        }

        @Test
        void testTwoMinus() {
            assertEquals(List.of(), RegexRearViewChallenge.numbersNotAfterMinus("--5"));
        }

        @Test
        void testRangeKeepsTheFirstOnly() {
            assertEquals(List.of("10"), RegexRearViewChallenge.numbersNotAfterMinus("10-20"));
        }

        @Test
        void testLastOneNegative() {
            assertEquals(List.of("1", "2"), RegexRearViewChallenge.numbersNotAfterMinus("1 2 -3"));
        }

        @Test
        void testFirstOneNegative() {
            assertEquals(List.of("2", "3"), RegexRearViewChallenge.numbersNotAfterMinus("-1 2 3"));
        }

        @Test
        void testTwoDigitNegativeBetweenPositives() {
            assertEquals(List.of("7", "9"), RegexRearViewChallenge.numbersNotAfterMinus("7 -88 9"));
        }

        @Test
        void testDecimalPointGivesTwoNumbers() {
            assertEquals(List.of("1", "5"), RegexRearViewChallenge.numbersNotAfterMinus("1.5"));
        }

        @Test
        void testOtherSymbolBeforeNumber() {
            assertEquals(List.of("5", "6"), RegexRearViewChallenge.numbersNotAfterMinus("+5 _6"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: insertUnderscores
    // ==========================================================
    @Nested
    class InsertUnderscoresTests {

        @Test
        void testThreeWords() {
            assertEquals("hello_World_Foo", RegexRearViewChallenge.insertUnderscores("helloWorldFoo"));
        }

        @Test
        void testAllCapitals() {
            assertEquals("ABC", RegexRearViewChallenge.insertUnderscores("ABC"));
        }

        @Test
        void testCapitalsAtTheEnd() {
            assertEquals("get_HTTP", RegexRearViewChallenge.insertUnderscores("getHTTP"));
        }

        @Test
        void testFirstLetterCapital() {
            assertEquals("Hello", RegexRearViewChallenge.insertUnderscores("Hello"));
        }

        @Test
        void testAllLowercase() {
            assertEquals("hello", RegexRearViewChallenge.insertUnderscores("hello"));
        }

        @Test
        void testEmpty() {
            assertEquals("", RegexRearViewChallenge.insertUnderscores(""));
        }

        @Test
        void testSingleLetter() {
            assertEquals("a", RegexRearViewChallenge.insertUnderscores("a"));
        }

        @Test
        void testUppercaseThenLowercaseNeedsNothing() {
            assertEquals("Ab", RegexRearViewChallenge.insertUnderscores("Ab"));
        }

        @Test
        void testCapitalAfterCapitalGetsNothing() {
            assertEquals("a_BC", RegexRearViewChallenge.insertUnderscores("aBC"));
        }

        @Test
        void testTwoLowerOneUpper() {
            assertEquals("ab_C", RegexRearViewChallenge.insertUnderscores("abC"));
        }

        @Test
        void testLowerAfterUpperGetsNothing() {
            assertEquals("ABc", RegexRearViewChallenge.insertUnderscores("ABc"));
        }

        @Test
        void testAlternating() {
            assertEquals("a_Ba_Ba_B", RegexRearViewChallenge.insertUnderscores("aBaBaB"));
        }

        @Test
        void testLongName() {
            assertEquals("one_Two_Three_Four", RegexRearViewChallenge.insertUnderscores("oneTwoThreeFour"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: censorAfterFirstLetter
    // ==========================================================
    @Nested
    class CensorAfterFirstLetterTests {

        @Test
        void testTwoWords() {
            assertEquals("h**** b**", RegexRearViewChallenge.censorAfterFirstLetter("hello big"));
        }

        @Test
        void testApostrophe() {
            assertEquals("d**'t", RegexRearViewChallenge.censorAfterFirstLetter("don't"));
        }

        @Test
        void testDigitSplitsTheGroup() {
            assertEquals("a1b*", RegexRearViewChallenge.censorAfterFirstLetter("a1bc"));
        }

        @Test
        void testEmpty() {
            assertEquals("", RegexRearViewChallenge.censorAfterFirstLetter(""));
        }

        @Test
        void testSingleLetter() {
            assertEquals("a", RegexRearViewChallenge.censorAfterFirstLetter("a"));
        }

        @Test
        void testNoLetters() {
            assertEquals("123 !?", RegexRearViewChallenge.censorAfterFirstLetter("123 !?"));
        }

        @Test
        void testUppercaseLetters() {
            assertEquals("A***", RegexRearViewChallenge.censorAfterFirstLetter("ABCD"));
        }

        @Test
        void testMixedCase() {
            assertEquals("S*****", RegexRearViewChallenge.censorAfterFirstLetter("Secret"));
        }

        @Test
        void testHyphen() {
            assertEquals("a*-c*", RegexRearViewChallenge.censorAfterFirstLetter("ab-cd"));
        }

        @Test
        void testSpacesAreKept() {
            assertEquals("a  b", RegexRearViewChallenge.censorAfterFirstLetter("a  b"));
        }

        @Test
        void testLengthIsKept() {
            assertEquals(11, RegexRearViewChallenge.censorAfterFirstLetter("hello world").length());
        }

        @Test
        void testLetterAfterNewLine() {
            assertEquals("a*\nb*", RegexRearViewChallenge.censorAfterFirstLetter("ab\nbc"));
        }

        @Test
        void testSymbolsBetweenLetters() {
            assertEquals("a_b.c,d", RegexRearViewChallenge.censorAfterFirstLetter("a_b.c,d"));
        }

        @Test
        void testDigitsStayAndLettersAfterThemStay() {
            assertEquals("1a*2b*", RegexRearViewChallenge.censorAfterFirstLetter("1ab2bc"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: wordsAfterThe
    // ==========================================================
    @Nested
    class WordsAfterTheTests {

        @Test
        void testTwoWords() {
            assertEquals(List.of("cat", "dog"), RegexRearViewChallenge.wordsAfterThe("the cat saw the dog"));
        }

        @Test
        void testTheInsideAnotherWord() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("bathe cat"));
        }

        @Test
        void testTwoSpaces() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("the  cat"));
        }

        @Test
        void testCapitalThe() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("The cat"));
        }

        @Test
        void testTheAtTheEnd() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("see the"));
        }

        @Test
        void testTheWithSpaceAtTheEnd() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("see the "));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe(""));
        }

        @Test
        void testWordStartingWithThe() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("there cat"));
        }

        @Test
        void testWholeWordIsReturned() {
            assertEquals(List.of("elephant"), RegexRearViewChallenge.wordsAfterThe("the elephant"));
        }

        @Test
        void testTheTheDog() {
            assertEquals(List.of("the", "dog"), RegexRearViewChallenge.wordsAfterThe("the the dog"));
        }

        @Test
        void testTheBeforeTheory() {
            assertEquals(List.of("theory"), RegexRearViewChallenge.wordsAfterThe("the theory"));
        }

        @Test
        void testTheAfterPunctuation() {
            assertEquals(List.of("dog"), RegexRearViewChallenge.wordsAfterThe("yes,the dog"));
        }

        @Test
        void testTheAfterDigit() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("5the cat"));
        }

        @Test
        void testWordEndsAtNonLetter() {
            assertEquals(List.of("cat"), RegexRearViewChallenge.wordsAfterThe("the cat."));
        }

        @Test
        void testTabOrLineBreakIsNotASpace() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("the\tcat"));
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("the\ncat"));
        }

        @Test
        void testUppercaseWordAfterThe() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("the Cat"));
        }

        @Test
        void testOtherArticlesDoNotCount() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("a cat an owl"));
        }

        @Test
        void testTheAtTheEndOfAnotherWord() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("other cat"));
        }

        @Test
        void testNumberAfterTheIsNotAWord() {
            assertEquals(List.of(), RegexRearViewChallenge.wordsAfterThe("the 5"));
        }
    }
}