package dev.perfectbogus.regex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexPeekAheadChallengeTest {

    // ==========================================================
    // CHALLENGE 1: wordsBeforeColon
    // ==========================================================
    @Nested
    class WordsBeforeColonTests {

        @Test
        void testTwoKeys() {
            assertEquals(List.of("name", "age"), RegexPeekAheadChallenge.wordsBeforeColon("name:John age:30"));
        }

        @Test
        void testChainedColons() {
            assertEquals(List.of("a", "b"), RegexPeekAheadChallenge.wordsBeforeColon("a:b:c"));
        }

        @Test
        void testSpaceBeforeColonDoesNotCount() {
            assertEquals(List.of(), RegexPeekAheadChallenge.wordsBeforeColon("key : value"));
        }

        @Test
        void testDigitBeforeColonDoesNotCount() {
            assertEquals(List.of(), RegexPeekAheadChallenge.wordsBeforeColon("x1:"));
        }

        @Test
        void testWholeWordIsReturned() {
            assertEquals(List.of("hello"), RegexPeekAheadChallenge.wordsBeforeColon("hello:"));
        }

        @Test
        void testNoColon() {
            assertEquals(List.of(), RegexPeekAheadChallenge.wordsBeforeColon("hello world"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexPeekAheadChallenge.wordsBeforeColon(""));
        }

        @Test
        void testOnlyColon() {
            assertEquals(List.of(), RegexPeekAheadChallenge.wordsBeforeColon(":"));
        }

        @Test
        void testColonAtTheStart() {
            assertEquals(List.of("b"), RegexPeekAheadChallenge.wordsBeforeColon(":a b:"));
        }

        @Test
        void testUpperAndLowerCase() {
            assertEquals(List.of("Key", "VALUE"), RegexPeekAheadChallenge.wordsBeforeColon("Key:1 VALUE:2"));
        }

        @Test
        void testWordAfterDigitsIsNotCut() {
            assertEquals(List.of("abc"), RegexPeekAheadChallenge.wordsBeforeColon("12abc:"));
        }

        @Test
        void testDoubleColonStillCountsOnce() {
            assertEquals(List.of("a"), RegexPeekAheadChallenge.wordsBeforeColon("a::"));
        }

        @Test
        void testLastWordWithoutColonIsIgnored() {
            assertEquals(List.of("a"), RegexPeekAheadChallenge.wordsBeforeColon("a:b"));
        }

        @Test
        void testUnderscoreBreaksTheWord() {
            assertEquals(List.of("b"), RegexPeekAheadChallenge.wordsBeforeColon("a_b:"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: removeLeadingZeros
    // ==========================================================
    @Nested
    class RemoveLeadingZerosTests {

        @Test
        void testTwoZeros() {
            assertEquals("120", RegexPeekAheadChallenge.removeLeadingZeros("00120"));
        }

        @Test
        void testNoLeadingZeros() {
            assertEquals("1000", RegexPeekAheadChallenge.removeLeadingZeros("1000"));
        }

        @Test
        void testOnlyZerosKeepOne() {
            assertEquals("0", RegexPeekAheadChallenge.removeLeadingZeros("000"));
        }

        @Test
        void testSingleZero() {
            assertEquals("0", RegexPeekAheadChallenge.removeLeadingZeros("0"));
        }

        @Test
        void testSingleDigit() {
            assertEquals("7", RegexPeekAheadChallenge.removeLeadingZeros("7"));
        }

        @Test
        void testOneLeadingZero() {
            assertEquals("5", RegexPeekAheadChallenge.removeLeadingZeros("05"));
        }

        @Test
        void testZerosInsideAreKept() {
            assertEquals("101", RegexPeekAheadChallenge.removeLeadingZeros("0101"));
        }

        @Test
        void testTrailingZerosAreKept() {
            assertEquals("100", RegexPeekAheadChallenge.removeLeadingZeros("000100"));
        }

        @Test
        void testManyZerosThenDigit() {
            assertEquals("9", RegexPeekAheadChallenge.removeLeadingZeros("0000000009"));
        }

        @Test
        void testTwoZerosOnly() {
            assertEquals("0", RegexPeekAheadChallenge.removeLeadingZeros("00"));
        }

        @Test
        void testLongNumberWithoutZeros() {
            assertEquals("123456789", RegexPeekAheadChallenge.removeLeadingZeros("123456789"));
        }

        @Test
        void testZeroInTheMiddleOnlyFirstRun() {
            assertEquals("1001", RegexPeekAheadChallenge.removeLeadingZeros("001001"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: addSpaceAfterComma
    // ==========================================================
    @Nested
    class AddSpaceAfterCommaTests {

        @Test
        void testMixed() {
            assertEquals("a, b, c, d", RegexPeekAheadChallenge.addSpaceAfterComma("a,b, c,d"));
        }

        @Test
        void testDoubleComma() {
            assertEquals("a, , b", RegexPeekAheadChallenge.addSpaceAfterComma("a,,b"));
        }

        @Test
        void testTrailingComma() {
            assertEquals("x,", RegexPeekAheadChallenge.addSpaceAfterComma("x,"));
        }

        @Test
        void testLineBreakAfterComma() {
            assertEquals("a,\nb", RegexPeekAheadChallenge.addSpaceAfterComma("a,\nb"));
        }

        @Test
        void testTabAfterComma() {
            assertEquals("a,\tb", RegexPeekAheadChallenge.addSpaceAfterComma("a,\tb"));
        }

        @Test
        void testAlreadyFormatted() {
            assertEquals("a, b, c", RegexPeekAheadChallenge.addSpaceAfterComma("a, b, c"));
        }

        @Test
        void testNoCommas() {
            assertEquals("abc def", RegexPeekAheadChallenge.addSpaceAfterComma("abc def"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", RegexPeekAheadChallenge.addSpaceAfterComma(""));
        }

        @Test
        void testOnlyComma() {
            assertEquals(",", RegexPeekAheadChallenge.addSpaceAfterComma(","));
        }

        @Test
        void testCommaAtTheStart() {
            assertEquals(", a", RegexPeekAheadChallenge.addSpaceAfterComma(",a"));
        }

        @Test
        void testTripleComma() {
            assertEquals("a, , , b", RegexPeekAheadChallenge.addSpaceAfterComma("a,,,b"));
        }

        @Test
        void testNumbers() {
            assertEquals("1, 2, 3, 4", RegexPeekAheadChallenge.addSpaceAfterComma("1,2,3,4"));
        }

        @Test
        void testSpaceAlreadyThereIsNotDoubled() {
            assertEquals("a, b", RegexPeekAheadChallenge.addSpaceAfterComma("a, b"));
        }

        @Test
        void testSpaceBeforeCommaIsKept() {
            assertEquals("a , b", RegexPeekAheadChallenge.addSpaceAfterComma("a ,b"));
        }

        @Test
        void testCommaThenCommaThenSpace() {
            assertEquals("a, , b", RegexPeekAheadChallenge.addSpaceAfterComma("a,, b"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: kilogramAmounts
    // ==========================================================
    @Nested
    class KilogramAmountsTests {

        @Test
        void testTwoAmounts() {
            assertEquals(List.of("5", "10"), RegexPeekAheadChallenge.kilogramAmounts("5 kg and 10 kg, 3 g"));
        }

        @Test
        void testKgsIsNotKg() {
            assertEquals(List.of("7"), RegexPeekAheadChallenge.kilogramAmounts("5 kgs and 7 kg."));
        }

        @Test
        void testNoSpaceOrTwoSpaces() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5kg and 6  kg"));
        }

        @Test
        void testKgFollowedByDigit() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5 kg3"));
        }

        @Test
        void testKgFollowedByUnderscore() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5 kg_x"));
        }

        @Test
        void testKgAtTheEnd() {
            assertEquals(List.of("42"), RegexPeekAheadChallenge.kilogramAmounts("42 kg"));
        }

        @Test
        void testKgFollowedByPunctuation() {
            assertEquals(List.of("1", "2", "3"), RegexPeekAheadChallenge.kilogramAmounts("1 kg, 2 kg; 3 kg!"));
        }

        @Test
        void testWholeDigitSequenceIsReturned() {
            assertEquals(List.of("123"), RegexPeekAheadChallenge.kilogramAmounts("123 kg"));
        }

        @Test
        void testGramsAreIgnored() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("3 g and 4 mg"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts(""));
        }

        @Test
        void testUppercaseKgDoesNotCount() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5 KG"));
        }

        @Test
        void testKgFollowedByLetterInsideWord() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5 kgx"));
        }

        @Test
        void testKgFollowedByLineBreak() {
            assertEquals(List.of("8", "9"), RegexPeekAheadChallenge.kilogramAmounts("8 kg\n9 kg"));
        }

        @Test
        void testNoNumberBeforeKg() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("many kg"));
        }

        @Test
        void testNumberWithDecimalPointGivesDigitsOnly() {
            assertEquals(List.of("5"), RegexPeekAheadChallenge.kilogramAmounts("2.5 kg"));
        }

        @Test
        void testNumberAfterLetterStillCounts() {
            assertEquals(List.of("5"), RegexPeekAheadChallenge.kilogramAmounts("x5 kg"));
        }

        @Test
        void testTabInsteadOfSpace() {
            assertEquals(List.of(), RegexPeekAheadChallenge.kilogramAmounts("5\tkg"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: groupInFours
    // ==========================================================
    @Nested
    class GroupInFoursTests {

        @Test
        void testSixteenDigits() {
            assertEquals("1234 5678 1234 5678", RegexPeekAheadChallenge.groupInFours("1234567812345678"));
        }

        @Test
        void testEightDigits() {
            assertEquals("1234 5678", RegexPeekAheadChallenge.groupInFours("12345678"));
        }

        @Test
        void testFourDigitsNoSpace() {
            assertEquals("1234", RegexPeekAheadChallenge.groupInFours("1234"));
        }

        @Test
        void testTwelveDigits() {
            assertEquals("1111 2222 3333", RegexPeekAheadChallenge.groupInFours("111122223333"));
        }

        @Test
        void testSameDigits() {
            assertEquals("0000 0000", RegexPeekAheadChallenge.groupInFours("00000000"));
        }

        @Test
        void testNoSpaceAtTheEnd() {
            assertFalse(RegexPeekAheadChallenge.groupInFours("12345678").endsWith(" "));
        }

        @Test
        void testNoSpaceAtTheStart() {
            assertFalse(RegexPeekAheadChallenge.groupInFours("12345678").startsWith(" "));
        }

        @Test
        void testTwentyDigits() {
            assertEquals("1234 5678 9012 3456 7890", RegexPeekAheadChallenge.groupInFours("12345678901234567890"));
        }

        @Test
        void testRepeatedPattern() {
            assertEquals("9876 9876 9876", RegexPeekAheadChallenge.groupInFours("987698769876"));
        }

        @Test
        void testGroupsAreOfFourFromTheLeft() {
            assertEquals("1000 0001", RegexPeekAheadChallenge.groupInFours("10000001"));
        }

        @Test
        void testTwentyFourDigits() {
            assertEquals("1234 5678 9012 3456 7890 1234",
                    RegexPeekAheadChallenge.groupInFours("123456789012345678901234"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: splitBeforeCapitals
    // ==========================================================
    @Nested
    class SplitBeforeCapitalsTests {

        @Test
        void testThreeWords() {
            assertEquals(List.of("Hello", "World", "Foo"),
                    RegexPeekAheadChallenge.splitBeforeCapitals("HelloWorldFoo"));
        }

        @Test
        void testStartsWithLowercase() {
            assertEquals(List.of("hello", "World"), RegexPeekAheadChallenge.splitBeforeCapitals("helloWorld"));
        }

        @Test
        void testAllCapitals() {
            assertEquals(List.of("A", "B", "C"), RegexPeekAheadChallenge.splitBeforeCapitals("ABC"));
        }

        @Test
        void testAllLowercase() {
            assertEquals(List.of("hello"), RegexPeekAheadChallenge.splitBeforeCapitals("hello"));
        }

        @Test
        void testSingleCapital() {
            assertEquals(List.of("A"), RegexPeekAheadChallenge.splitBeforeCapitals("A"));
        }

        @Test
        void testSingleLowercase() {
            assertEquals(List.of("a"), RegexPeekAheadChallenge.splitBeforeCapitals("a"));
        }

        @Test
        void testOnlyFirstIsCapital() {
            assertEquals(List.of("Hello"), RegexPeekAheadChallenge.splitBeforeCapitals("Hello"));
        }

        @Test
        void testCapitalAtTheEnd() {
            assertEquals(List.of("abc", "D"), RegexPeekAheadChallenge.splitBeforeCapitals("abcD"));
        }

        @Test
        void testNoEmptyPieceAtTheStart() {
            assertEquals(1, RegexPeekAheadChallenge.splitBeforeCapitals("Word").size());
        }

        @Test
        void testTwoCapitalsInARow() {
            assertEquals(List.of("get", "H", "T", "T", "P"), RegexPeekAheadChallenge.splitBeforeCapitals("getHTTP"));
        }

        @Test
        void testManyPieces() {
            assertEquals(List.of("one", "Two", "Three", "Four"),
                    RegexPeekAheadChallenge.splitBeforeCapitals("oneTwoThreeFour"));
        }

        @Test
        void testPiecesKeepAllLetters() {
            List<String> pieces = RegexPeekAheadChallenge.splitBeforeCapitals("helloWorldFoo");
            assertEquals("helloWorldFoo", String.join("", pieces));
        }
    }

    // ==========================================================
    // CHALLENGE 7: isValidUsername
    // ==========================================================
    @Nested
    class IsValidUsernameTests {

        @Test
        void testNormal() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("john_doe"));
        }

        @Test
        void testStartsWithUnderscore() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("_ab"));
        }

        @Test
        void testStartsWithDigit() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("1john"));
        }

        @Test
        void testDoubleUnderscore() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("a__b"));
        }

        @Test
        void testTooShort() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("jo"));
        }

        @Test
        void testSpace() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("john doe"));
        }

        @Test
        void testExactlyThreeCharacters() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("abc"));
        }

        @Test
        void testExactlyTwelveCharacters() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("abcdefghijkl"));
        }

        @Test
        void testThirteenCharacters() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("abcdefghijklm"));
        }

        @Test
        void testEmpty() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername(""));
        }

        @Test
        void testDigitsAfterFirstAreFine() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("user123"));
        }

        @Test
        void testDoubleUnderscoreAtTheEnd() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("ab__"));
        }

        @Test
        void testDoubleUnderscoreAtTheStart() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("__ab"));
        }

        @Test
        void testTripleUnderscore() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("a___b"));
        }

        @Test
        void testSingleUnderscoresAreFine() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("a_b_c_d"));
        }

        @Test
        void testSymbolsAreNotAllowed() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("john-doe"));
            assertFalse(RegexPeekAheadChallenge.isValidUsername("john.doe"));
            assertFalse(RegexPeekAheadChallenge.isValidUsername("john@doe"));
        }

        @Test
        void testUppercaseAllowed() {
            assertTrue(RegexPeekAheadChallenge.isValidUsername("JohnDOE"));
        }

        @Test
        void testStartingWithAnyDigitIsInvalid() {
            for (char c = '0'; c <= '9'; c++) {
                assertFalse(RegexPeekAheadChallenge.isValidUsername(c + "abc"));
            }
        }

        @Test
        void testOnlyDigits() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("12345"));
        }

        @Test
        void testOnlyUnderscores() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("___"));
        }

        @Test
        void testSingleUnderscoreOnlyAndLettersTooShort() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("_a"));
        }

        @Test
        void testAccentedLettersAreNotAllowed() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("jos\u00e9"));
        }

        @Test
        void testLongWithDoubleUnderscoreInsideLimit() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("abcdef__ghij"));
        }

        @Test
        void testDigitFirstEvenIfLongEnough() {
            assertFalse(RegexPeekAheadChallenge.isValidUsername("0abcdef"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: errorDatabaseLines
    // ==========================================================
    @Nested
    class ErrorDatabaseLinesTests {

        @Test
        void testExample() {
            assertEquals(List.of("db error", "error in db"),
                    RegexPeekAheadChallenge.errorDatabaseLines("db error\nok\nerror in db\nERROR db"));
        }

        @Test
        void testInsideWords() {
            assertEquals(List.of("mydbserror"), RegexPeekAheadChallenge.errorDatabaseLines("mydbserror"));
        }

        @Test
        void testOnlyOneOfThem() {
            assertEquals(List.of(), RegexPeekAheadChallenge.errorDatabaseLines("only error\nonly db"));
        }

        @Test
        void testBothOnTheSameLineInEitherOrder() {
            assertEquals(List.of("error db", "db error"),
                    RegexPeekAheadChallenge.errorDatabaseLines("error db\ndb error"));
        }

        @Test
        void testWordsOnDifferentLinesDoNotCombine() {
            assertEquals(List.of(), RegexPeekAheadChallenge.errorDatabaseLines("error\ndb"));
        }

        @Test
        void testUppercaseDoesNotCount() {
            assertEquals(List.of(), RegexPeekAheadChallenge.errorDatabaseLines("ERROR DB"));
        }

        @Test
        void testMixedCaseDoesNotCount() {
            assertEquals(List.of(), RegexPeekAheadChallenge.errorDatabaseLines("error DB"));
        }

        @Test
        void testEmptyText() {
            assertEquals(List.of(), RegexPeekAheadChallenge.errorDatabaseLines(""));
        }

        @Test
        void testLineIsReturnedExactly() {
            assertEquals(List.of("  db: error!  "), RegexPeekAheadChallenge.errorDatabaseLines("  db: error!  "));
        }

        @Test
        void testOrderIsKept() {
            assertEquals(List.of("b db error", "a error db"),
                    RegexPeekAheadChallenge.errorDatabaseLines("b db error\nx\na error db"));
        }

        @Test
        void testManyLines() {
            assertEquals(List.of("l2 error db", "l4 db error"),
                    RegexPeekAheadChallenge.errorDatabaseLines("l1 error\nl2 error db\nl3 db\nl4 db error\nl5"));
        }

        @Test
        void testErrorAtTheStartAndDbAtTheEnd() {
            assertEquals(List.of("error...db"), RegexPeekAheadChallenge.errorDatabaseLines("error...db"));
        }

        @Test
        void testDbAtTheStartAndErrorAtTheEnd() {
            assertEquals(List.of("db...error"), RegexPeekAheadChallenge.errorDatabaseLines("db...error"));
        }

        @Test
        void testTouchingWords() {
            assertEquals(List.of("dberror", "errordb"), RegexPeekAheadChallenge.errorDatabaseLines("dberror\nerrordb"));
        }

        @Test
        void testEmptyLinesInTheMiddleAreIgnored() {
            assertEquals(List.of("db error"), RegexPeekAheadChallenge.errorDatabaseLines("\n\ndb error\n\n"));
        }

        @Test
        void testRepeatedWordsStillOneLine() {
            assertEquals(List.of("db db error error"), RegexPeekAheadChallenge.errorDatabaseLines("db db error error"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: allDifferentCharacters
    // ==========================================================
    @Nested
    class AllDifferentCharactersTests {

        @Test
        void testDifferent() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters("abc"));
        }

        @Test
        void testRepeatedAtTheEnd() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("aba"));
        }

        @Test
        void testCaseMatters() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters("aA"));
        }

        @Test
        void testSpacesCount() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("a a"));
        }

        @Test
        void testEmpty() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters(""));
        }

        @Test
        void testSingleCharacter() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters("x"));
        }

        @Test
        void testTwoEqualCharacters() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("xx"));
        }

        @Test
        void testRepeatAtTheStartAndEnd() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("abca"));
        }

        @Test
        void testRepeatInTheMiddle() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("abccd"));
        }

        @Test
        void testRepeatFarAway() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("abcdefghijkla"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters("a1!b2?"));
        }

        @Test
        void testRepeatedDigit() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("1231"));
        }

        @Test
        void testSingleSpace() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters(" "));
        }

        @Test
        void testTwoSpaces() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("  "));
        }

        @Test
        void testRepeatedSymbol() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("a.b.c"));
        }

        @Test
        void testRepeatedCapital() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("ABA"));
        }

        @Test
        void testAlphabet() {
            assertTrue(RegexPeekAheadChallenge.allDifferentCharacters("abcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void testAlphabetWithRepeatedLast() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("abcdefghijklmnopqrstuvwxyzz"));
        }

        @Test
        void testRepeatedOnlyOnceOfMany() {
            assertFalse(RegexPeekAheadChallenge.allDifferentCharacters("qwertyuiopq"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: countOverlapping
    // ==========================================================
    @Nested
    class CountOverlappingTests {

        @Test
        void testThreeLetters() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("aaa", "aa"));
        }

        @Test
        void testBanana() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("banana", "ana"));
        }

        @Test
        void testFourLetters() {
            assertEquals(3, RegexPeekAheadChallenge.countOverlapping("aaaa", "aa"));
        }

        @Test
        void testNoOccurrence() {
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("abc", "d"));
        }

        @Test
        void testDotIsLiteral() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("a.a.a", "a.a"));
        }

        @Test
        void testDotDoesNotMatchAnyCharacter() {
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("abc", "a.c"));
        }

        @Test
        void testStarIsLiteral() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("a*a*a", "a*a"));
        }

        @Test
        void testStarDoesNotRepeat() {
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("aaa", "a*"));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(4, RegexPeekAheadChallenge.countOverlapping("abcabcab a", "a"));
        }

        @Test
        void testCaseMatters() {
            assertEquals(1, RegexPeekAheadChallenge.countOverlapping("aAa", "A"));
        }

        @Test
        void testWholeTextIsThePart() {
            assertEquals(1, RegexPeekAheadChallenge.countOverlapping("abc", "abc"));
        }

        @Test
        void testPartLongerThanText() {
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("ab", "abc"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("", "a"));
        }

        @Test
        void testManyOverlaps() {
            assertEquals(5, RegexPeekAheadChallenge.countOverlapping("aaaaaa", "aa"));
        }

        @Test
        void testLongOverlap() {
            assertEquals(3, RegexPeekAheadChallenge.countOverlapping("aaaaa", "aaa"));
        }

        @Test
        void testNoOverlapNeeded() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("abxxab", "ab"));
        }

        @Test
        void testOverlapOfOneCharacter() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("abcbcb", "bcb"));
        }

        @Test
        void testPlusIsLiteral() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("1+1+1", "1+1"));
        }

        @Test
        void testBracketsAreLiteral() {
            assertEquals(1, RegexPeekAheadChallenge.countOverlapping("x[a]y", "[a]"));
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("xay", "[a]"));
        }

        @Test
        void testBackslashIsLiteral() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("a\\b\\b", "\\b"));
        }

        @Test
        void testQuestionMarkIsLiteral() {
            assertEquals(1, RegexPeekAheadChallenge.countOverlapping("a?b", "a?"));
            assertEquals(0, RegexPeekAheadChallenge.countOverlapping("ab", "a?"));
        }

        @Test
        void testParenthesisAreLiteral() {
            assertEquals(1, RegexPeekAheadChallenge.countOverlapping("f(x)", "(x)"));
        }

        @Test
        void testSpaces() {
            assertEquals(2, RegexPeekAheadChallenge.countOverlapping("a  a", " "));
        }
    }
}