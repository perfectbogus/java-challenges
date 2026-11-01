package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexZeroWidthChallengeTest {

    // ==========================================================
    // CHALLENGE 1: addThousandsSeparators
    // ==========================================================
    @Nested
    class AddThousandsSeparatorsTests {

        @Test
        void testSevenDigits() {
            assertEquals("1,234,567", RegexZeroWidthChallenge.addThousandsSeparators("1234567"));
        }

        @Test
        void testFourDigits() {
            assertEquals("1,000", RegexZeroWidthChallenge.addThousandsSeparators("1000"));
        }

        @Test
        void testThreeDigitsNeedNoSeparator() {
            assertEquals("999", RegexZeroWidthChallenge.addThousandsSeparators("999"));
        }

        @Test
        void testZero() {
            assertEquals("0", RegexZeroWidthChallenge.addThousandsSeparators("0"));
        }

        @Test
        void testOneDigit() {
            assertEquals("7", RegexZeroWidthChallenge.addThousandsSeparators("7"));
        }

        @Test
        void testTwoDigits() {
            assertEquals("42", RegexZeroWidthChallenge.addThousandsSeparators("42"));
        }

        @Test
        void testSixDigitsHaveNoSeparatorAtTheStart() {
            assertEquals("123,456", RegexZeroWidthChallenge.addThousandsSeparators("123456"));
        }

        @Test
        void testNineDigits() {
            assertEquals("123,456,789", RegexZeroWidthChallenge.addThousandsSeparators("123456789"));
        }

        @Test
        void testFiveDigits() {
            assertEquals("12,345", RegexZeroWidthChallenge.addThousandsSeparators("12345"));
        }

        @Test
        void testEightDigits() {
            assertEquals("12,345,678", RegexZeroWidthChallenge.addThousandsSeparators("12345678"));
        }

        @Test
        void testRepeatedDigits() {
            assertEquals("1,000,000,000", RegexZeroWidthChallenge.addThousandsSeparators("1000000000"));
        }

        @Test
        void testMaxInt() {
            assertEquals("2,147,483,647", RegexZeroWidthChallenge.addThousandsSeparators("2147483647"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: numbersBeforePercent
    // ==========================================================
    @Nested
    class NumbersBeforePercentTests {

        @Test
        void testSeveralNumbers() {
            assertEquals(List.of("10", "5"), RegexZeroWidthChallenge.numbersBeforePercent("10% of 20 and 5%"));
        }

        @Test
        void testDecimalOnlyTheDigitsRightBeforeThePercent() {
            assertEquals(List.of("5"), RegexZeroWidthChallenge.numbersBeforePercent("3.5% and 100 %"));
        }

        @Test
        void testNoNumbers() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersBeforePercent("no numbers"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersBeforePercent(""));
        }

        @Test
        void testLongNumberIsNotCut() {
            assertEquals(List.of("1234"), RegexZeroWidthChallenge.numbersBeforePercent("1234%"));
        }

        @Test
        void testPercentWithoutNumber() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersBeforePercent("100 % and %"));
        }

        @Test
        void testNumberWithoutPercent() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersBeforePercent("10 20 30"));
        }

        @Test
        void testPercentNextToLetters() {
            assertEquals(List.of("12"), RegexZeroWidthChallenge.numbersBeforePercent("a12%b"));
        }

        @Test
        void testTwoPercentsInARow() {
            assertEquals(List.of("5"), RegexZeroWidthChallenge.numbersBeforePercent("5%%"));
        }

        @Test
        void testNumbersTogether() {
            assertEquals(List.of("1", "2", "3"), RegexZeroWidthChallenge.numbersBeforePercent("1%2%3%"));
        }

        @Test
        void testNumberFollowedByLetterThenPercent() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersBeforePercent("12a%"));
        }

        @Test
        void testMinusSignIsNotPartOfTheNumber() {
            assertEquals(List.of("5"), RegexZeroWidthChallenge.numbersBeforePercent("-5%"));
        }

        @Test
        void testRepeatedNumbersAreKept() {
            assertEquals(List.of("50", "50"), RegexZeroWidthChallenge.numbersBeforePercent("50% 50%"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: amountsAfterDollar
    // ==========================================================
    @Nested
    class AmountsAfterDollarTests {

        @Test
        void testSeveralAmounts() {
            assertEquals(List.of("5", "120"), RegexZeroWidthChallenge.amountsAfterDollar("$5 and $120"));
        }

        @Test
        void testOnlyTheDigitsRightAfterTheDollar() {
            assertEquals(List.of("12"), RegexZeroWidthChallenge.amountsAfterDollar("$12.50 and 30"));
        }

        @Test
        void testDollarAfterOrWithSpace() {
            assertEquals(List.of(), RegexZeroWidthChallenge.amountsAfterDollar("5$ and $ 7"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexZeroWidthChallenge.amountsAfterDollar(""));
        }

        @Test
        void testNoDollar() {
            assertEquals(List.of(), RegexZeroWidthChallenge.amountsAfterDollar("10 20 30"));
        }

        @Test
        void testLongNumberIsNotCut() {
            assertEquals(List.of("1234567"), RegexZeroWidthChallenge.amountsAfterDollar("total $1234567"));
        }

        @Test
        void testTwoDollars() {
            assertEquals(List.of("7"), RegexZeroWidthChallenge.amountsAfterDollar("$$7"));
        }

        @Test
        void testAmountsTogether() {
            assertEquals(List.of("1", "2", "3"), RegexZeroWidthChallenge.amountsAfterDollar("$1$2$3"));
        }

        @Test
        void testDollarWithoutNumber() {
            assertEquals(List.of(), RegexZeroWidthChallenge.amountsAfterDollar("$ and $a"));
        }

        @Test
        void testDigitsAfterTheFirstDigitAreNotNewMatches() {
            assertEquals(List.of("905"), RegexZeroWidthChallenge.amountsAfterDollar("$905"));
        }

        @Test
        void testRepeatedAmountsAreKept() {
            assertEquals(List.of("5", "5"), RegexZeroWidthChallenge.amountsAfterDollar("$5 $5"));
        }

        @Test
        void testDollarSignIsNotAWildcard() {
            assertEquals(List.of(), RegexZeroWidthChallenge.amountsAfterDollar("abc 123"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: wordsWithoutComma
    // ==========================================================
    @Nested
    class WordsWithoutCommaTests {

        @Test
        void testWordsWithoutComma() {
            assertEquals(List.of("green", "sun"), RegexZeroWidthChallenge.wordsWithoutComma("red, green blue, sun"));
        }

        @Test
        void testPeriodIsNotAComma() {
            assertEquals(List.of("one", "two"), RegexZeroWidthChallenge.wordsWithoutComma("one two."));
        }

        @Test
        void testAllWordsHaveComma() {
            assertEquals(List.of(), RegexZeroWidthChallenge.wordsWithoutComma("a, b, c,"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexZeroWidthChallenge.wordsWithoutComma(""));
        }

        @Test
        void testLongWordWithCommaIsNotCut() {
            assertEquals(List.of(), RegexZeroWidthChallenge.wordsWithoutComma("elephant,"));
        }

        @Test
        void testLongWordWithoutComma() {
            assertEquals(List.of("elephant"), RegexZeroWidthChallenge.wordsWithoutComma("elephant"));
        }

        @Test
        void testCommaAfterSpaceDoesNotCount() {
            assertEquals(List.of("a", "b"), RegexZeroWidthChallenge.wordsWithoutComma("a , b"));
        }

        @Test
        void testUppercaseLetters() {
            assertEquals(List.of("Cat"), RegexZeroWidthChallenge.wordsWithoutComma("Dog, Cat"));
        }

        @Test
        void testLastWordWithComma() {
            assertEquals(List.of("a"), RegexZeroWidthChallenge.wordsWithoutComma("a b,"));
        }

        @Test
        void testWordWithoutCommaAtTheEnd() {
            assertEquals(List.of("x", "y"), RegexZeroWidthChallenge.wordsWithoutComma("x y"));
        }

        @Test
        void testSingleLetterWords() {
            assertEquals(List.of("b"), RegexZeroWidthChallenge.wordsWithoutComma("a,b"));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(List.of("go", "go"), RegexZeroWidthChallenge.wordsWithoutComma("go go, go,go"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: isStrongPassword
    // ==========================================================
    @Nested
    class IsStrongPasswordTests {

        @Test
        void testStrong() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("Abcdefg1"));
        }

        @Test
        void testNoUppercase() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("abcdefg1"));
        }

        @Test
        void testTooShort() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("Ab1"));
        }

        @Test
        void testWithSymbols() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("Ab1!Ab1!"));
        }

        @Test
        void testNoLowercase() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("ABCDEFG1"));
        }

        @Test
        void testNoDigit() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("Abcdefgh"));
        }

        @Test
        void testExactlyEightCharacters() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("aB3aaaaa"));
        }

        @Test
        void testSevenCharactersIsNotEnough() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("aB3aaaa"));
        }

        @Test
        void testOrderDoesNotMatter() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("1aaaaaaB"));
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("Baaaaaa1"));
        }

        @Test
        void testEmptyPassword() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword(""));
        }

        @Test
        void testSpacesAreAllowed() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("Ab 1 xyz"));
        }

        @Test
        void testLongPassword() {
            assertTrue(RegexZeroWidthChallenge.isStrongPassword("Z" + "a".repeat(40) + "9"));
        }

        @Test
        void testOnlyDigitsAndSymbolsAreNotEnough() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("12345678!"));
        }

        @Test
        void testNonAsciiLettersAreNotUppercase() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("Éabcdef1"));
        }

        @Test
        void testNonAsciiDigitsAreNotDigits() {
            assertFalse(RegexZeroWidthChallenge.isStrongPassword("Abcdefg٣"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: splitCamelCase
    // ==========================================================
    @Nested
    class SplitCamelCaseTests {

        @Test
        void testSeveralWords() {
            assertEquals("hello World Foo Bar", RegexZeroWidthChallenge.splitCamelCase("helloWorldFooBar"));
        }

        @Test
        void testLowercaseThenUppercase() {
            assertEquals("i Phone", RegexZeroWidthChallenge.splitCamelCase("iPhone"));
        }

        @Test
        void testConsecutiveUppercaseLettersAreNotSeparated() {
            assertEquals("HTTPServer", RegexZeroWidthChallenge.splitCamelCase("HTTPServer"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexZeroWidthChallenge.splitCamelCase(""));
        }

        @Test
        void testNoUppercase() {
            assertEquals("hello", RegexZeroWidthChallenge.splitCamelCase("hello"));
        }

        @Test
        void testNoLowercase() {
            assertEquals("HELLO", RegexZeroWidthChallenge.splitCamelCase("HELLO"));
        }

        @Test
        void testTwoLetters() {
            assertEquals("a B", RegexZeroWidthChallenge.splitCamelCase("aB"));
        }

        @Test
        void testUppercaseFollowedByLowercaseIsNotSeparated() {
            assertEquals("Hello", RegexZeroWidthChallenge.splitCamelCase("Hello"));
        }

        @Test
        void testLowercaseThenSeveralUppercase() {
            assertEquals("abc DEF", RegexZeroWidthChallenge.splitCamelCase("abcDEF"));
        }

        @Test
        void testSeparatorOnlyBetweenLowerAndUpper() {
            assertEquals("ab CDe F", RegexZeroWidthChallenge.splitCamelCase("abCDeF"));
        }

        @Test
        void testDigitsAreNotLetters() {
            assertEquals("a1B", RegexZeroWidthChallenge.splitCamelCase("a1B"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("\u00E9B", RegexZeroWidthChallenge.splitCamelCase("\u00E9B"));
            assertEquals("a\u00C9", RegexZeroWidthChallenge.splitCamelCase("a\u00C9"));
        }

        @Test
        void testExistingSpacesAreKept() {
            assertEquals("one two Three", RegexZeroWidthChallenge.splitCamelCase("one twoThree"));
        }

        @Test
        void testNoSpaceIsAddedBeforeTheFirstLetter() {
            assertEquals("Ab", RegexZeroWidthChallenge.splitCamelCase("Ab"));
            assertEquals("ab C", RegexZeroWidthChallenge.splitCamelCase("abC"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: splitAfterSemicolon
    // ==========================================================
    @Nested
    class SplitAfterSemicolonTests {

        @Test
        void testSplit() {
            assertEquals(List.of("a;", "b;", "c"), RegexZeroWidthChallenge.splitAfterSemicolon("a;b;c"));
        }

        @Test
        void testEndsWithSemicolon() {
            assertEquals(List.of("a;", "b;"), RegexZeroWidthChallenge.splitAfterSemicolon("a;b;"));
        }

        @Test
        void testStartsWithSemicolon() {
            assertEquals(List.of(";", "a"), RegexZeroWidthChallenge.splitAfterSemicolon(";a"));
        }

        @Test
        void testNoSemicolon() {
            assertEquals(List.of("abc"), RegexZeroWidthChallenge.splitAfterSemicolon("abc"));
        }

        @Test
        void testOnlySemicolons() {
            assertEquals(List.of(";", ";"), RegexZeroWidthChallenge.splitAfterSemicolon(";;"));
        }

        @Test
        void testSingleSemicolon() {
            assertEquals(List.of(";"), RegexZeroWidthChallenge.splitAfterSemicolon(";"));
        }

        @Test
        void testLongPieces() {
            assertEquals(List.of("first part;", "second part;", "third"),
                    RegexZeroWidthChallenge.splitAfterSemicolon("first part;second part;third"));
        }

        @Test
        void testSpacesAreKept() {
            assertEquals(List.of("a;", " b"), RegexZeroWidthChallenge.splitAfterSemicolon("a; b"));
        }

        @Test
        void testThreeSemicolonsInARow() {
            assertEquals(List.of("a;", ";", ";", "b"), RegexZeroWidthChallenge.splitAfterSemicolon("a;;;b"));
        }

        @Test
        void testJoiningThePiecesGivesTheOriginalText() {
            String text = "x;y;;z;";
            assertEquals(text, String.join("", RegexZeroWidthChallenge.splitAfterSemicolon(text)));
        }

        @Test
        void testOneCharacterPieces() {
            assertEquals(List.of("a;", "b;", "c;", "d"), RegexZeroWidthChallenge.splitAfterSemicolon("a;b;c;d"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: fixArticles
    // ==========================================================
    @Nested
    class FixArticlesTests {

        @Test
        void testSomeArticlesChange() {
            assertEquals("an apple and a banana", RegexZeroWidthChallenge.fixArticles("a apple and a banana"));
        }

        @Test
        void testSeveralChanges() {
            assertEquals("an egg an orange", RegexZeroWidthChallenge.fixArticles("a egg a orange"));
        }

        @Test
        void testConsecutiveArticles() {
            assertEquals("an an a", RegexZeroWidthChallenge.fixArticles("a a a"));
        }

        @Test
        void testLetterAInsideAWordIsNotAnArticle() {
            assertEquals("extra apple", RegexZeroWidthChallenge.fixArticles("extra apple"));
        }

        @Test
        void testSingleA() {
            assertEquals("a", RegexZeroWidthChallenge.fixArticles("a"));
        }

        @Test
        void testNoVowelAfterwards() {
            assertEquals("a cat", RegexZeroWidthChallenge.fixArticles("a cat"));
        }

        @Test
        void testUppercaseAIsNotChanged() {
            assertEquals("A apple", RegexZeroWidthChallenge.fixArticles("A apple"));
        }

        @Test
        void testUppercaseVowelDoesNotCount() {
            assertEquals("a Apple", RegexZeroWidthChallenge.fixArticles("a Apple"));
        }

        @Test
        void testYIsNotAVowel() {
            assertEquals("a yellow", RegexZeroWidthChallenge.fixArticles("a yellow"));
        }

        @Test
        void testEveryVowel() {
            assertEquals("an ant an egg an ink an owl an urn",
                    RegexZeroWidthChallenge.fixArticles("a ant a egg a ink a owl a urn"));
        }

        @Test
        void testWordsStartingWithAAreNotChanged() {
            assertEquals("apple apple", RegexZeroWidthChallenge.fixArticles("apple apple"));
        }

        @Test
        void testWordEndingWithAAndNextVowel() {
            assertEquals("pizza and pasta", RegexZeroWidthChallenge.fixArticles("pizza and pasta"));
        }

        @Test
        void testArticleAtTheEnd() {
            assertEquals("see an owl a", RegexZeroWidthChallenge.fixArticles("see a owl a"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexZeroWidthChallenge.fixArticles(""));
        }
    }

    // ==========================================================
    // CHALLENGE 9: numbersWithoutMinus
    // ==========================================================
    @Nested
    class NumbersWithoutMinusTests {

        @Test
        void testIgnoresNegativeNumbers() {
            assertEquals(List.of("5", "10"), RegexZeroWidthChallenge.numbersWithoutMinus("5 -3 10"));
        }

        @Test
        void testLongNegativeNumberIsIgnoredAsAWhole() {
            assertEquals(List.of("12"), RegexZeroWidthChallenge.numbersWithoutMinus("-34 and 12"));
        }

        @Test
        void testMinusBetweenNumbers() {
            assertEquals(List.of("3"), RegexZeroWidthChallenge.numbersWithoutMinus("3-4"));
        }

        @Test
        void testOnlyNegativeNumbers() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersWithoutMinus("-1 -2"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersWithoutMinus(""));
        }

        @Test
        void testNoNumbers() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersWithoutMinus("none here - at all"));
        }

        @Test
        void testMinusWithSpaceDoesNotCount() {
            assertEquals(List.of("5"), RegexZeroWidthChallenge.numbersWithoutMinus("- 5"));
        }

        @Test
        void testLongNegativeNumbersWithManyDigits() {
            assertEquals(List.of("7"), RegexZeroWidthChallenge.numbersWithoutMinus("-123456 7"));
        }

        @Test
        void testNumbersNextToLetters() {
            assertEquals(List.of("12", "3"), RegexZeroWidthChallenge.numbersWithoutMinus("a12b3"));
        }

        @Test
        void testNegativeNumberNextToLetters() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersWithoutMinus("a-12b"));
        }

        @Test
        void testDoubleMinus() {
            assertEquals(List.of(), RegexZeroWidthChallenge.numbersWithoutMinus("--5"));
        }

        @Test
        void testPlusSignDoesNotExcludeTheNumber() {
            assertEquals(List.of("5", "6"), RegexZeroWidthChallenge.numbersWithoutMinus("+5 6"));
        }

        @Test
        void testSingleDigit() {
            assertEquals(List.of("0"), RegexZeroWidthChallenge.numbersWithoutMinus("0"));
        }

        @Test
        void testRepeatedNumbersAreKept() {
            assertEquals(List.of("8", "8"), RegexZeroWidthChallenge.numbersWithoutMinus("8 -8 8"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: maskEmailUser
    // ==========================================================
    @Nested
    class MaskEmailUserTests {

        @Test
        void testMasksTheUser() {
            assertEquals("j***@mail.com", RegexZeroWidthChallenge.maskEmailUser("john@mail.com"));
        }

        @Test
        void testTwoCharacterUser() {
            assertEquals("a*@x.org", RegexZeroWidthChallenge.maskEmailUser("ab@x.org"));
        }

        @Test
        void testOneCharacterUser() {
            assertEquals("a@x.org", RegexZeroWidthChallenge.maskEmailUser("a@x.org"));
        }

        @Test
        void testDomainIsNotChanged() {
            assertEquals("m****@longdomain.example.com",
                    RegexZeroWidthChallenge.maskEmailUser("maria@longdomain.example.com"));
        }

        @Test
        void testUserWithDotsAndDigits() {
            assertEquals("a*******@x.io", RegexZeroWidthChallenge.maskEmailUser("a.b_c-12@x.io"));
        }

        @Test
        void testFirstCharacterCanBeADigit() {
            assertEquals("1**@x.io", RegexZeroWidthChallenge.maskEmailUser("123@x.io"));
        }

        @Test
        void testUppercaseUser() {
            assertEquals("J***@x.io", RegexZeroWidthChallenge.maskEmailUser("JOHN@x.io"));
        }

        @Test
        void testEmptyDomain() {
            assertEquals("a**@", RegexZeroWidthChallenge.maskEmailUser("abc@"));
        }

        @Test
        void testDomainWithTheSameLetters() {
            assertEquals("a***@abcd.com", RegexZeroWidthChallenge.maskEmailUser("abcd@abcd.com"));
        }

        @Test
        void testLongUser() {
            String email = "a" + "b".repeat(30) + "@x.io";
            assertEquals("a" + "*".repeat(30) + "@x.io", RegexZeroWidthChallenge.maskEmailUser(email));
        }

        @Test
        void testStarsInTheDomainAreKept() {
            assertEquals("a*@x*y", RegexZeroWidthChallenge.maskEmailUser("ab@x*y"));
        }
    }
}