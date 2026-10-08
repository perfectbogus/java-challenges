package dev.perfectbogus.regex;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexLookaroundChallengeTest {

    // ==========================================================
    // CHALLENGE 1: separateLettersAndDigits
    // ==========================================================
    @Nested
    class SeparateLettersAndDigitsTests {

        @Test
        void testSeparatesLettersFromDigitsInBothDirections() {
            assertEquals("abc 123 def", RegexLookaroundChallenge.separateLettersAndDigits("abc123def"));
            assertEquals("a 1 b 2", RegexLookaroundChallenge.separateLettersAndDigits("a1b2"));
        }

        @Test
        void testOtherCharactersAreNotSeparated() {
            assertEquals("a-1 x_2", RegexLookaroundChallenge.separateLettersAndDigits("a-1 x_2"));
            assertEquals("v 2.5 beta", RegexLookaroundChallenge.separateLettersAndDigits("v 2.5 beta"));
        }

        @Test
        void testNoSpaceIsAddedAtTheEnds() {
            assertEquals("room 42", RegexLookaroundChallenge.separateLettersAndDigits("room42"));
            assertEquals("42 room", RegexLookaroundChallenge.separateLettersAndDigits("42room"));
            assertEquals("7", RegexLookaroundChallenge.separateLettersAndDigits("7"));
            assertEquals("", RegexLookaroundChallenge.separateLettersAndDigits(""));
        }

        @Test
        void testExistingSpacesAreKept() {
            assertEquals("a  1", RegexLookaroundChallenge.separateLettersAndDigits("a  1"));
            assertEquals("ab 12 cd 34 ef", RegexLookaroundChallenge.separateLettersAndDigits("ab12 cd34ef"));
        }

        @Test
        void testOnlyAsciiLettersCount() {
            assertEquals("é1", RegexLookaroundChallenge.separateLettersAndDigits("é1"));
            assertEquals("x 1é", RegexLookaroundChallenge.separateLettersAndDigits("x1é"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: wordsAfterNot
    // ==========================================================
    @Nested
    class WordsAfterNotTests {

        @Test
        void testReturnsTheWordAfterNot() {
            assertEquals(List.of("good", "bad"),
                    RegexLookaroundChallenge.wordsAfterNot("This is not good and that is not bad at all"));
        }

        @Test
        void testNotIsMatchedIgnoringCase() {
            assertEquals(List.of("here", "there"), RegexLookaroundChallenge.wordsAfterNot("NOT here, Not there"));
        }

        @Test
        void testNotMustBeAWholeWord() {
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("a knot tied, we cannot stop, snot bubble"));
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("nothing here, notable fact"));
            assertEquals(List.of("fine"), RegexLookaroundChallenge.wordsAfterNot("x_not bad, (not fine)"));
        }

        @Test
        void testExactlyOneSpaceIsRequired() {
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("not  very, not\tvery, not, not-very"));
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("not 5 times, not 'this'"));
        }

        @Test
        void testTheWholeWordIsReturnedAndNotCanFollowNot() {
            assertEquals(List.of("not", "good"), RegexLookaroundChallenge.wordsAfterNot("not not good"));
            assertEquals(List.of("extraordinarily"), RegexLookaroundChallenge.wordsAfterNot("not extraordinarily!"));
        }

        @Test
        void testNothingToFind() {
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot(""));
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("it is not"));
            assertEquals(List.of(), RegexLookaroundChallenge.wordsAfterNot("it is not "));
        }
    }

    // ==========================================================
    // CHALLENGE 3: maskEmailLocalPart
    // ==========================================================
    @Nested
    class MaskEmailLocalPartTests {

        @Test
        void testMasksTheMiddleOfTheLocalPart() {
            assertEquals("j******e@mail.com", RegexLookaroundChallenge.maskEmailLocalPart("john.doe@mail.com"));
            assertEquals("a*c@x.org", RegexLookaroundChallenge.maskEmailLocalPart("abc@x.org"));
            assertEquals("a**d@x.org", RegexLookaroundChallenge.maskEmailLocalPart("abcd@x.org"));
        }

        @Test
        void testShortLocalPartsStayUnchanged() {
            assertEquals("a@mail.com", RegexLookaroundChallenge.maskEmailLocalPart("a@mail.com"));
            assertEquals("ab@mail.com", RegexLookaroundChallenge.maskEmailLocalPart("ab@mail.com"));
        }

        @Test
        void testTheDomainIsNeverChanged() {
            assertEquals("t**t@long.sub.example.co.uk",
                    RegexLookaroundChallenge.maskEmailLocalPart("test@long.sub.example.co.uk"));
            assertEquals("a*c@a1b2c3d4.com", RegexLookaroundChallenge.maskEmailLocalPart("abc@a1b2c3d4.com"));
        }

        @Test
        void testSpecialCharactersInTheLocalPart() {
            assertEquals("a***e@b.com", RegexLookaroundChallenge.maskEmailLocalPart("a+.*e@b.com"));
            assertEquals("*****@b.com", RegexLookaroundChallenge.maskEmailLocalPart("*...*@b.com"));
            assertEquals("-**-@b.com", RegexLookaroundChallenge.maskEmailLocalPart("-ab-@b.com"));
        }

        @Test
        void testSingleCharacterDomain() {
            assertEquals("a**e@b", RegexLookaroundChallenge.maskEmailLocalPart("abce@b"));
            assertEquals("x@y", RegexLookaroundChallenge.maskEmailLocalPart("x@y"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: extractDoubleAngleContents
    // ==========================================================
    @Nested
    class ExtractDoubleAngleContentsTests {

        @Test
        void testExtractsEveryContentInOrder() {
            assertEquals(List.of("one", "two", "three"),
                    RegexLookaroundChallenge.extractDoubleAngleContents("<<one>> and <<two>>,<<three>>"));
        }

        @Test
        void testStopsAtTheNearestClosingMarker() {
            assertEquals(List.of("a", "b"), RegexLookaroundChallenge.extractDoubleAngleContents("<<a>> x <<b>> y >>"));
            assertEquals(List.of("a"), RegexLookaroundChallenge.extractDoubleAngleContents("<<a>>b>>"));
        }

        @Test
        void testContentMayContainSingleAngleBrackets() {
            assertEquals(List.of("a>b"), RegexLookaroundChallenge.extractDoubleAngleContents("<<a>b>>"));
            assertEquals(List.of("x<y", "z"), RegexLookaroundChallenge.extractDoubleAngleContents("<<x<y>><<z>>"));
        }

        @Test
        void testEmptyContentsAreNotReturned() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractDoubleAngleContents("<<>>"));
            assertEquals(List.of(">"), RegexLookaroundChallenge.extractDoubleAngleContents("<<>>>"));
            assertEquals(List.of(">> <<a"), RegexLookaroundChallenge.extractDoubleAngleContents("<<>> <<a>>"));
        }

        @Test
        void testOverlappingMarkersDoNotReturnTheSameCharactersTwice() {
            assertEquals(List.of("<a"), RegexLookaroundChallenge.extractDoubleAngleContents("<<<a>>"));
            assertEquals(List.of("a", "b"), RegexLookaroundChallenge.extractDoubleAngleContents("<<a>><<b>>"));
        }

        @Test
        void testLineBreaksAreNotAllowedInsideAContent() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractDoubleAngleContents("<<a\nb>>"));
            assertEquals(List.of("ok"), RegexLookaroundChallenge.extractDoubleAngleContents("<<a\nb>>\n<<ok>>"));
            assertEquals(List.of(), RegexLookaroundChallenge.extractDoubleAngleContents("no markers, <<unclosed"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: splitCamelCase
    // ==========================================================
    @Nested
    class SplitCamelCaseTests {

        @Test
        void testSplitsLowerToUpperBoundaries() {
            assertEquals(List.of("hello", "World"), RegexLookaroundChallenge.splitCamelCase("helloWorld"));
            assertEquals(List.of("Hello", "World", "Again"),
                    RegexLookaroundChallenge.splitCamelCase("HelloWorldAgain"));
        }

        @Test
        void testKeepsRunsOfCapitalsTogether() {
            assertEquals(List.of("parse", "HTTP", "Response"),
                    RegexLookaroundChallenge.splitCamelCase("parseHTTPResponse"));
            assertEquals(List.of("HTTP", "Server"), RegexLookaroundChallenge.splitCamelCase("HTTPServer"));
            assertEquals(List.of("get", "URL"), RegexLookaroundChallenge.splitCamelCase("getURL"));
        }

        @Test
        void testWordsWithoutBoundaries() {
            assertEquals(List.of("word"), RegexLookaroundChallenge.splitCamelCase("word"));
            assertEquals(List.of("Word"), RegexLookaroundChallenge.splitCamelCase("Word"));
            assertEquals(List.of("ABC"), RegexLookaroundChallenge.splitCamelCase("ABC"));
            assertEquals(List.of("x"), RegexLookaroundChallenge.splitCamelCase("x"));
        }

        @Test
        void testShortAndMixedPatterns() {
            assertEquals(List.of("a", "B"), RegexLookaroundChallenge.splitCamelCase("aB"));
            assertEquals(List.of("A", "Bc", "D"), RegexLookaroundChallenge.splitCamelCase("ABcD"));
            assertEquals(List.of("a", "B", "Cd"), RegexLookaroundChallenge.splitCamelCase("aBCd"));
        }

        @Test
        void testRunOfCapitalsInTheMiddleOfWords() {
            assertEquals(List.of("a", "BC", "Dd"), RegexLookaroundChallenge.splitCamelCase("aBCDd"));
            assertEquals(List.of("my", "XML", "Parser", "V"), RegexLookaroundChallenge.splitCamelCase("myXMLParserV"));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), RegexLookaroundChallenge.splitCamelCase(""));
        }
    }

    // ==========================================================
    // CHALLENGE 6: tokenizeExpression
    // ==========================================================
    @Nested
    class TokenizeExpressionTests {

        @Test
        void testSplitsNumbersAndOperators() {
            assertEquals(List.of("12", "+", "3", "*", "4"), RegexLookaroundChallenge.tokenizeExpression("12+3*4"));
            assertEquals(List.of("1", "-", "22", "/", "333"), RegexLookaroundChallenge.tokenizeExpression("1-22/333"));
        }

        @Test
        void testOperatorsAtTheEnds() {
            assertEquals(List.of("-", "5", "+", "3"), RegexLookaroundChallenge.tokenizeExpression("-5+3"));
            assertEquals(List.of("5", "+"), RegexLookaroundChallenge.tokenizeExpression("5+"));
            assertEquals(List.of("*", "7", "/"), RegexLookaroundChallenge.tokenizeExpression("*7/"));
        }

        @Test
        void testConsecutiveOperators() {
            assertEquals(List.of("-", "-", "3"), RegexLookaroundChallenge.tokenizeExpression("--3"));
            assertEquals(List.of("4", "*", "-", "2"), RegexLookaroundChallenge.tokenizeExpression("4*-2"));
            assertEquals(List.of("+", "+", "+"), RegexLookaroundChallenge.tokenizeExpression("+++"));
        }

        @Test
        void testSingleTokens() {
            assertEquals(List.of("12345"), RegexLookaroundChallenge.tokenizeExpression("12345"));
            assertEquals(List.of("/"), RegexLookaroundChallenge.tokenizeExpression("/"));
        }

        @Test
        void testEmptyExpression() {
            assertEquals(List.of(), RegexLookaroundChallenge.tokenizeExpression(""));
        }
    }

    // ==========================================================
    // CHALLENGE 7: extractNumbersNotPercentages
    // ==========================================================
    @Nested
    class ExtractNumbersNotPercentagesTests {

        @Test
        void testSkipsNumbersFollowedByPercent() {
            assertEquals(List.of("200"), RegexLookaroundChallenge.extractNumbersNotPercentages("50% of 200"));
            assertEquals(List.of("10", "30"),
                    RegexLookaroundChallenge.extractNumbersNotPercentages("10 and 20% and 30"));
        }

        @Test
        void testASkippedNumberIsNotReturnedPartially() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractNumbersNotPercentages("100%"));
            assertEquals(List.of(), RegexLookaroundChallenge.extractNumbersNotPercentages("12345%"));
            assertEquals(List.of("7"), RegexLookaroundChallenge.extractNumbersNotPercentages("7 99%"));
        }

        @Test
        void testNumbersAreMaximalRuns() {
            assertEquals(List.of("12345"), RegexLookaroundChallenge.extractNumbersNotPercentages("n=12345."));
            assertEquals(List.of("3"), RegexLookaroundChallenge.extractNumbersNotPercentages("3.5%"));
            assertEquals(List.of("3", "5"), RegexLookaroundChallenge.extractNumbersNotPercentages("3.5"));
        }

        @Test
        void testOnlyAnImmediatePercentSignExcludes() {
            assertEquals(List.of("50"), RegexLookaroundChallenge.extractNumbersNotPercentages("50 %"));
            assertEquals(List.of("5"), RegexLookaroundChallenge.extractNumbersNotPercentages("5 percent %"));
            assertEquals(List.of("42"), RegexLookaroundChallenge.extractNumbersNotPercentages("%42"));
        }

        @Test
        void testNoNumbers() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractNumbersNotPercentages(""));
            assertEquals(List.of(), RegexLookaroundChallenge.extractNumbersNotPercentages("no digits, 100%!"));
        }

        @Test
        void testNumbersInsideWordsAreStillNumbers() {
            assertEquals(List.of("2", "10"), RegexLookaroundChallenge.extractNumbersNotPercentages("a2b 10c"));
            assertEquals(List.of("8"), RegexLookaroundChallenge.extractNumbersNotPercentages("abc8%x 8"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: extractAmountsWithCurrency
    // ==========================================================
    @Nested
    class ExtractAmountsWithCurrencyTests {

        @Test
        void testExtractsAmountsAfterEveryMarker() {
            assertEquals(List.of("12.50", "7", "300", "4.25"),
                    RegexLookaroundChallenge.extractAmountsWithCurrency("Pay $12.50, then €7, USD 300 and EUR 4.25."));
        }

        @Test
        void testNumbersWithoutAMarkerAreIgnored() {
            assertEquals(List.of("5"), RegexLookaroundChallenge.extractAmountsWithCurrency("3 apples, 9.99 total, $5"));
            assertEquals(List.of(), RegexLookaroundChallenge.extractAmountsWithCurrency("only 100 and 2.5 here"));
        }

        @Test
        void testMarkersAreExactAndCaseSensitive() {
            assertEquals(List.of(),
                    RegexLookaroundChallenge.extractAmountsWithCurrency("USD5, usd 5, EUR  5, $ 5, Eur 5"));
            assertEquals(List.of("5"), RegexLookaroundChallenge.extractAmountsWithCurrency("price: USD 5"));
        }

        @Test
        void testAmountFollowedByALetterIsRejectedCompletely() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractAmountsWithCurrency("$12.5k"));
            assertEquals(List.of(), RegexLookaroundChallenge.extractAmountsWithCurrency("$12k and USD 100M"));
            assertEquals(List.of("8"), RegexLookaroundChallenge.extractAmountsWithCurrency("$99x $8"));
        }

        @Test
        void testTrailingPunctuationIsNotPartOfTheAmount() {
            assertEquals(List.of("12"), RegexLookaroundChallenge.extractAmountsWithCurrency("pay $12."));
            assertEquals(List.of("12", "3"), RegexLookaroundChallenge.extractAmountsWithCurrency("$12, EUR 3;"));
            assertEquals(List.of("45"), RegexLookaroundChallenge.extractAmountsWithCurrency("($45)"));
        }

        @Test
        void testTheWholeNumberIsReturned() {
            assertEquals(List.of("1234567.0125"),
                    RegexLookaroundChallenge.extractAmountsWithCurrency("€1234567.0125 total"));
            assertEquals(List.of("007"), RegexLookaroundChallenge.extractAmountsWithCurrency("USD 007"));
        }

        @Test
        void testNoText() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractAmountsWithCurrency(""));
            assertEquals(List.of(), RegexLookaroundChallenge.extractAmountsWithCurrency("$"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: indexesOfOverlapping
    // ==========================================================
    @Nested
    class IndexesOfOverlappingTests {

        @Test
        void testCountsOverlappingOccurrences() {
            assertEquals(List.of(0, 1, 2), RegexLookaroundChallenge.indexesOfOverlapping("aaaa", "aa"));
            assertEquals(List.of(0, 2, 4), RegexLookaroundChallenge.indexesOfOverlapping("abababa", "aba"));
        }

        @Test
        void testNonOverlappingOccurrences() {
            assertEquals(List.of(0, 4, 8), RegexLookaroundChallenge.indexesOfOverlapping("abc_abc_abc", "abc"));
            assertEquals(List.of(2), RegexLookaroundChallenge.indexesOfOverlapping("xxabcxx", "abc"));
        }

        @Test
        void testTargetIsLiteralText() {
            assertEquals(List.of(1, 3), RegexLookaroundChallenge.indexesOfOverlapping("a.b.c", "."));
            assertEquals(List.of(0, 2), RegexLookaroundChallenge.indexesOfOverlapping("x+x+x", "x+x"));
            assertEquals(List.of(), RegexLookaroundChallenge.indexesOfOverlapping("abc", ".*"));
            assertEquals(List.of(3), RegexLookaroundChallenge.indexesOfOverlapping("a(a[b]", "[b]"));
            assertEquals(List.of(1), RegexLookaroundChallenge.indexesOfOverlapping("a\\Eb", "\\E"));
        }

        @Test
        void testNoOccurrences() {
            assertEquals(List.of(), RegexLookaroundChallenge.indexesOfOverlapping("hello", "xyz"));
            assertEquals(List.of(), RegexLookaroundChallenge.indexesOfOverlapping("", "a"));
            assertEquals(List.of(), RegexLookaroundChallenge.indexesOfOverlapping("ab", "abc"));
        }

        @Test
        void testWholeTextAndEdges() {
            assertEquals(List.of(0), RegexLookaroundChallenge.indexesOfOverlapping("abc", "abc"));
            assertEquals(List.of(0, 1, 2, 3), RegexLookaroundChallenge.indexesOfOverlapping("aaaa", "a"));
            assertEquals(List.of(3), RegexLookaroundChallenge.indexesOfOverlapping("abcd", "d"));
        }

        @Test
        void testIsCaseSensitive() {
            assertEquals(List.of(1), RegexLookaroundChallenge.indexesOfOverlapping("aAa", "A"));
            assertEquals(List.of(0, 2), RegexLookaroundChallenge.indexesOfOverlapping("aAa", "a"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: isValidUsername
    // ==========================================================
    @Nested
    class IsValidUsernameTests {

        @Test
        void testAcceptsValidUsernames() {
            assertTrue(RegexLookaroundChallenge.isValidUsername("john_doe"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("Alice99"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("a_b_c_d"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("abcd"));
        }

        @Test
        void testLengthLimits() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("abc"));
            assertFalse(RegexLookaroundChallenge.isValidUsername(""));
            assertTrue(RegexLookaroundChallenge.isValidUsername("abcdefghijklmnop"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("abcdefghijklmnopq"));
        }

        @Test
        void testAllowedCharactersOnly() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("john-doe"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("john doe"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("jöhn_doe"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("john.doe"));
        }

        @Test
        void testMustStartWithALetter() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("1abcd"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("_abcd"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("aB12_cd"));
        }

        @Test
        void testUnderscoreRules() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("abcd_"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("ab__cd"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("abc___d"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("ab_cd_ef"));
        }

        @Test
        void testForbiddenWordInAnyCase() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("admin"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("ADMIN_01"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("superAdMin"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("xadminx"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("admi_n"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("adm1n"));
        }

        @Test
        void testAtMostThreeDigits() {
            assertTrue(RegexLookaroundChallenge.isValidUsername("user123"));
            assertTrue(RegexLookaroundChallenge.isValidUsername("a1b2c3"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("user1234"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("a1b2c3d4"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("u_1_2_3_4"));
        }

        @Test
        void testLineBreaksAreNotAccepted() {
            assertFalse(RegexLookaroundChallenge.isValidUsername("abcd\n"));
            assertFalse(RegexLookaroundChallenge.isValidUsername("ab\ncd"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: isIsogram
    // ==========================================================
    @Nested
    class IsIsogramTests {

        @Test
        void testWordsWithoutRepeatedLetters() {
            assertTrue(RegexLookaroundChallenge.isIsogram("Dermatoglyphics"));
            assertTrue(RegexLookaroundChallenge.isIsogram("abc"));
            assertTrue(RegexLookaroundChallenge.isIsogram("lumberjacks"));
        }

        @Test
        void testWordsWithRepeatedLetters() {
            assertFalse(RegexLookaroundChallenge.isIsogram("Hello"));
            assertFalse(RegexLookaroundChallenge.isIsogram("moOse"));
            assertFalse(RegexLookaroundChallenge.isIsogram("aba"));
            assertFalse(RegexLookaroundChallenge.isIsogram("Aa"));
        }

        @Test
        void testNonLettersAreIgnored() {
            assertTrue(RegexLookaroundChallenge.isIsogram("six-year-old"));
            assertTrue(RegexLookaroundChallenge.isIsogram("a b c"));
            assertTrue(RegexLookaroundChallenge.isIsogram("1 1 2 2 3 3"));
            assertTrue(RegexLookaroundChallenge.isIsogram("--  --"));
            assertTrue(RegexLookaroundChallenge.isIsogram("b1_1_b".substring(1)));
        }

        @Test
        void testRepeatedLettersFarApartAreDetected() {
            assertFalse(RegexLookaroundChallenge.isIsogram("a bcdefghijklmnopqrstuvwxyz a"));
            assertFalse(RegexLookaroundChallenge.isIsogram("x-yz-x"));
            assertTrue(RegexLookaroundChallenge.isIsogram("quick brown"));
        }

        @Test
        void testLineBreaksInsideTheText() {
            assertFalse(RegexLookaroundChallenge.isIsogram("ab\ncd\nae"));
            assertTrue(RegexLookaroundChallenge.isIsogram("ab\ncd\nef\n"));
            assertTrue(RegexLookaroundChallenge.isIsogram("\n\n"));
        }

        @Test
        void testEmptyAndFullAlphabet() {
            assertTrue(RegexLookaroundChallenge.isIsogram(""));
            assertTrue(RegexLookaroundChallenge.isIsogram("abcdefghijklmnopqrstuvwxyz"));
            assertFalse(RegexLookaroundChallenge.isIsogram("abcdefghijklmnopqrstuvwxyzA"));
        }

        @Test
        void testNonAsciiLettersAreIgnored() {
            assertTrue(RegexLookaroundChallenge.isIsogram("éé"));
            assertTrue(RegexLookaroundChallenge.isIsogram("ñañ"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: formatWithThousands
    // ==========================================================
    @Nested
    class FormatWithThousandsTests {

        @Test
        void testGroupsTheIntegerPart() {
            assertEquals("1,234,567", RegexLookaroundChallenge.formatWithThousands("1234567"));
            assertEquals("123,456", RegexLookaroundChallenge.formatWithThousands("123456"));
            assertEquals("1,000", RegexLookaroundChallenge.formatWithThousands("1000"));
            assertEquals("12,345", RegexLookaroundChallenge.formatWithThousands("12345"));
        }

        @Test
        void testShortNumbersStayUnchanged() {
            assertEquals("0", RegexLookaroundChallenge.formatWithThousands("0"));
            assertEquals("12", RegexLookaroundChallenge.formatWithThousands("12"));
            assertEquals("999", RegexLookaroundChallenge.formatWithThousands("999"));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals("-1,234", RegexLookaroundChallenge.formatWithThousands("-1234"));
            assertEquals("-123", RegexLookaroundChallenge.formatWithThousands("-123"));
            assertEquals("-123,456", RegexLookaroundChallenge.formatWithThousands("-123456"));
        }

        @Test
        void testTheFractionalPartIsNeverGrouped() {
            assertEquals("1,234.5678", RegexLookaroundChallenge.formatWithThousands("1234.5678"));
            assertEquals("0.123456", RegexLookaroundChallenge.formatWithThousands("0.123456"));
            assertEquals("12.345", RegexLookaroundChallenge.formatWithThousands("12.345"));
            assertEquals("-1,234,567.891234", RegexLookaroundChallenge.formatWithThousands("-1234567.891234"));
        }

        @Test
        void testLongFractionalParts() {
            assertEquals("1.123456789012345678901234567890",
                    RegexLookaroundChallenge.formatWithThousands("1.123456789012345678901234567890"));
            assertEquals("123,456.123456789012345678901234567890123456",
                    RegexLookaroundChallenge.formatWithThousands("123456.123456789012345678901234567890123456"));
        }

        @Test
        void testLongIntegerParts() {
            assertEquals("1,234,567,890,123,456,789",
                    RegexLookaroundChallenge.formatWithThousands("1234567890123456789"));
            assertEquals("100,000,000,000,000,000,000,000",
                    RegexLookaroundChallenge.formatWithThousands("100000000000000000000000"));
        }

        @Test
        void testLeadingZerosAreGroupedLikeAnyOtherDigit() {
            assertEquals("0,001,234", RegexLookaroundChallenge.formatWithThousands("0001234"));
        }
    }

    // ==========================================================
    // CHALLENGE 13: containsAllWords
    // ==========================================================
    @Nested
    class ContainsAllWordsTests {

        @Test
        void testFindsAllWordsInAnyOrder() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("the quick brown fox", List.of("fox", "quick")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("a b c", List.of("c", "b", "a")));
        }

        @Test
        void testFailsWhenAWordIsMissing() {
            assertFalse(RegexLookaroundChallenge.containsAllWords("the quick brown fox", List.of("fox", "dog")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("", List.of("a")));
        }

        @Test
        void testWholeWordsOnly() {
            assertFalse(RegexLookaroundChallenge.containsAllWords("I like javascript", List.of("java")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("scalable", List.of("scala")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("my_java", List.of("java")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("java8", List.of("java")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("(java), [kotlin]!", List.of("java", "kotlin")));
        }

        @Test
        void testIgnoresCase() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("Java AND Kotlin", List.of("java", "and", "KOTLIN")));
        }

        @Test
        void testWordsAreLiteralText() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("I know c++ and c#", List.of("c++", "c#")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("I know c and cc", List.of("c++")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("see a.b here", List.of("a.b")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("see axb here", List.of("a.b")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("price (x|y) [z]", List.of("(x|y)", "[z]")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("use \\E now", List.of("\\E")));
        }

        @Test
        void testWordWithNonWordEdgesNeedsNoLetterAroundIt() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("c++ rocks", List.of("c++")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("c++x rocks", List.of("c++")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("c++,c++;", List.of("c++")));
        }

        @Test
        void testEdgeCasesWithTheWordsList() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("anything at all", List.of()));
            assertTrue(RegexLookaroundChallenge.containsAllWords("", List.of()));
            assertTrue(RegexLookaroundChallenge.containsAllWords("repeat", List.of("repeat", "repeat")));
        }

        @Test
        void testTextWithLineBreaks() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("first line\nsecond line\nthird",
                    List.of("third", "first")));
            assertFalse(RegexLookaroundChallenge.containsAllWords("first\nsecond", List.of("firstsecond")));
        }

        @Test
        void testAWordMayAppearAfterAFalseStart() {
            assertTrue(RegexLookaroundChallenge.containsAllWords("javascript java", List.of("java")));
            assertTrue(RegexLookaroundChallenge.containsAllWords("xcat cat", List.of("cat")));
        }
    }

    // ==========================================================
    // CHALLENGE 14: extractIdentifiersExcludingKeywords
    // ==========================================================
    @Nested
    class ExtractIdentifiersExcludingKeywordsTests {

        @Test
        void testExcludesKeywordsAndKeepsIdentifiers() {
            assertEquals(List.of("x", "y", "x"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "if x then y else x", List.of("if", "then", "else")));
        }

        @Test
        void testTokensThatMerelyContainAKeywordAreKept() {
            assertEquals(List.of("iffy", "elsewhere", "doing", "undo"),
                    RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                            "iffy if elsewhere else doing undo do", List.of("if", "else", "do")));
        }

        @Test
        void testTokensStartingWithADigitAreNotIdentifiers() {
            assertEquals(List.of("a", "b2"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "a 2fast 3d 99 b2 1_x", List.of()));
        }

        @Test
        void testEmptyKeywordsKeepsEveryIdentifier() {
            assertEquals(List.of("int", "count", "_tmp", "count2"),
                    RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                            "int count = _tmp + count2;", List.of()));
        }

        @Test
        void testKeywordsAreCaseSensitive() {
            assertEquals(List.of("If", "IF", "x"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "If IF if x", List.of("if")));
        }

        @Test
        void testPunctuationSeparatesTokens() {
            assertEquals(List.of("obj", "method", "arg"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "return obj.method(arg);", List.of("return")));
            assertEquals(List.of("a", "b"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "a+if-b*new", List.of("if", "new")));
        }

        @Test
        void testKeywordFollowedByDigitsOrUnderscoreIsAnIdentifier() {
            assertEquals(List.of("if2", "if_", "_if"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "if2 if if_ _if", List.of("if")));
        }

        @Test
        void testNothingToReturn() {
            assertEquals(List.of(), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords("", List.of("if")));
            assertEquals(List.of(), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "if else", List.of("if", "else")));
            assertEquals(List.of(), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords("1 22 +", List.of()));
        }

        @Test
        void testRepetitionsAreKept() {
            assertEquals(List.of("a", "a", "a"), RegexLookaroundChallenge.extractIdentifiersExcludingKeywords(
                    "a a if a", List.of("if")));
        }
    }

    // ==========================================================
    // CHALLENGE 15: replaceSpacesOutsideQuotes
    // ==========================================================
    @Nested
    class ReplaceSpacesOutsideQuotesTests {

        @Test
        void testReplacesOnlySpacesOutsideQuotes() {
            assertEquals("say_\"hello world\"_now",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("say \"hello world\" now"));
            assertEquals("a_b_c", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("a b c"));
        }

        @Test
        void testSeveralQuotedSections() {
            assertEquals("\"a b\"_x_\"c d\"_\"e f\"",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\"a b\" x \"c d\" \"e f\""));
            assertEquals("p_\"q r\"_s_\"t\"_u",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("p \"q r\" s \"t\" u"));
        }

        @Test
        void testNoQuotes() {
            assertEquals("no_spaces_changed_here",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("no spaces changed here"));
            assertEquals("nospaces", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("nospaces"));
            assertEquals("", RegexLookaroundChallenge.replaceSpacesOutsideQuotes(""));
        }

        @Test
        void testEveryCharacterIsInsideOneQuotedSection() {
            assertEquals("\"all of it is quoted\"",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\"all of it is quoted\""));
            assertEquals("\" \"", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\" \""));
            assertEquals("\"\"_\"\"", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\"\" \"\""));
        }

        @Test
        void testSpacesRightNextToQuotes() {
            assertEquals("_\"a\"_", RegexLookaroundChallenge.replaceSpacesOutsideQuotes(" \"a\" "));
            assertEquals("\" a \"", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\" a \""));
        }

        @Test
        void testOtherWhitespaceIsNeverChanged() {
            assertEquals("a\tb_c\nd_e", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("a\tb c\nd e"));
            assertEquals("x_\"a\tb c\"", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("x \"a\tb c\""));
        }

        @Test
        void testTextEndingWithALineBreak() {
            assertEquals("a_b\n", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("a b\n"));
            assertEquals("a_\"b c\"\n", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("a \"b c\"\n"));
            assertEquals("\"a b\"\n_c", RegexLookaroundChallenge.replaceSpacesOutsideQuotes("\"a b\"\n c"));
        }

        @Test
        void testQuotedSectionsSpanningLineBreaks() {
            assertEquals("x_\"first line\nsecond line\"_y",
                    RegexLookaroundChallenge.replaceSpacesOutsideQuotes("x \"first line\nsecond line\" y"));
        }

        @Test
        void testLongerText() {
            StringBuilder in = new StringBuilder();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < 40; i++) {
                in.append("w").append(i).append(" \"q ").append(i).append("\" ");
                out.append("w").append(i).append("_\"q ").append(i).append("\"_");
            }
            assertEquals(out.toString(), RegexLookaroundChallenge.replaceSpacesOutsideQuotes(in.toString()));
        }
    }
}