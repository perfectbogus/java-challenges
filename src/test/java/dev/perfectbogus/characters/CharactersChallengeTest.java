package dev.perfectbogus.characters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class CharactersChallengeTest {

    // ==========================================================
    // CHALLENGE 1: countDigits
    // ==========================================================
    @Nested
    class CountDigitsTests {

        @Test
        void testCountsAsciiDigits() {
            assertEquals(3, CharactersChallenge.countDigits("a1b22c"));
            assertEquals(5, CharactersChallenge.countDigits("12345"));
        }

        @Test
        void testNoDigitsAndEmptyText() {
            assertEquals(0, CharactersChallenge.countDigits("abc !?"));
            assertEquals(0, CharactersChallenge.countDigits(""));
        }

        @Test
        void testDigitsOfOtherScriptsCount() {
            assertEquals(2, CharactersChallenge.countDigits("٣x٤"));
        }

        @Test
        void testSignsAndDecimalPointsAreNotDigits() {
            assertEquals(2, CharactersChallenge.countDigits("-3.5"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: countLetters
    // ==========================================================
    @Nested
    class CountLettersTests {

        @Test
        void testCountsOnlyLetters() {
            assertEquals(10, CharactersChallenge.countLetters("Hello, World 123!"));
        }

        @Test
        void testLettersWithAccentsAndOtherAlphabetsCount() {
            assertEquals(5, CharactersChallenge.countLetters("niño é"));
            assertEquals(3, CharactersChallenge.countLetters("αβγ"));
        }

        @Test
        void testNoLettersAndEmptyText() {
            assertEquals(0, CharactersChallenge.countLetters("123 !?_"));
            assertEquals(0, CharactersChallenge.countLetters(""));
        }
    }

    // ==========================================================
    // CHALLENGE 3: countUpperCase
    // ==========================================================
    @Nested
    class CountUpperCaseTests {

        @Test
        void testCountsUpperCaseLetters() {
            assertEquals(2, CharactersChallenge.countUpperCase("Hello World"));
            assertEquals(3, CharactersChallenge.countUpperCase("ABC"));
        }

        @Test
        void testLowerCaseDigitsAndSymbolsDoNotCount() {
            assertEquals(0, CharactersChallenge.countUpperCase("abc 123 !?"));
            assertEquals(0, CharactersChallenge.countUpperCase(""));
        }

        @Test
        void testUpperCaseLettersWithAccentsCount() {
            assertEquals(1, CharactersChallenge.countUpperCase("École"));
            assertEquals(2, CharactersChallenge.countUpperCase("ΑβΓ"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: swapCase
    // ==========================================================
    @Nested
    class SwapCaseTests {

        @Test
        void testSwapsTheCaseOfLetters() {
            assertEquals("hELLO wORLD", CharactersChallenge.swapCase("Hello World"));
        }

        @Test
        void testOtherCharactersStayAsTheyAre() {
            assertEquals("aBc 123 !?", CharactersChallenge.swapCase("AbC 123 !?"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", CharactersChallenge.swapCase(""));
        }

        @Test
        void testLettersWithAccents() {
            assertEquals("Éé", CharactersChallenge.swapCase("éÉ"));
        }

        @Test
        void testTheResultHasTheSameLengthAsTheText() {
            String result = CharactersChallenge.swapCase("straße");

            assertEquals(6, result.length());
            assertEquals("STRAßE", result);
        }
    }

    // ==========================================================
    // CHALLENGE 5: countWhitespace
    // ==========================================================
    @Nested
    class CountWhitespaceTests {

        @Test
        void testCountsSpacesTabsAndNewLines() {
            assertEquals(3, CharactersChallenge.countWhitespace("a b\tc\nd"));
            assertEquals(2, CharactersChallenge.countWhitespace("a\r\nb"));
        }

        @Test
        void testOnlyWhitespaceAndNoWhitespace() {
            assertEquals(3, CharactersChallenge.countWhitespace("   "));
            assertEquals(0, CharactersChallenge.countWhitespace("abc"));
            assertEquals(0, CharactersChallenge.countWhitespace(""));
        }

        @Test
        void testNoBreakSpaceIsNotWhitespace() {
            assertEquals(0, CharactersChallenge.countWhitespace("a b"));
            assertEquals(1, CharactersChallenge.countWhitespace("a  b"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: isAlphanumeric
    // ==========================================================
    @Nested
    class IsAlphanumericTests {

        @Test
        void testLettersAndDigits() {
            assertTrue(CharactersChallenge.isAlphanumeric("abc123"));
            assertTrue(CharactersChallenge.isAlphanumeric("ABC"));
            assertTrue(CharactersChallenge.isAlphanumeric("42"));
        }

        @Test
        void testSpacesAndPunctuationMakeItFalse() {
            assertFalse(CharactersChallenge.isAlphanumeric("abc 123"));
            assertFalse(CharactersChallenge.isAlphanumeric("abc!"));
            assertFalse(CharactersChallenge.isAlphanumeric("a_b"));
        }

        @Test
        void testEmptyTextIsFalse() {
            assertFalse(CharactersChallenge.isAlphanumeric(""));
        }

        @Test
        void testOtherAlphabetsAndScripts() {
            assertTrue(CharactersChallenge.isAlphanumeric("café"));
            assertTrue(CharactersChallenge.isAlphanumeric("٣"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: digitSum
    // ==========================================================
    @Nested
    class DigitSumTests {

        @Test
        void testSumsTheDigits() {
            assertEquals(6, CharactersChallenge.digitSum("a1b2c3"));
            assertEquals(27, CharactersChallenge.digitSum("999"));
        }

        @Test
        void testNoDigitsAndEmptyText() {
            assertEquals(0, CharactersChallenge.digitSum("abc"));
            assertEquals(0, CharactersChallenge.digitSum(""));
        }

        @Test
        void testLettersThatHaveANumericValueAreIgnored() {
            assertEquals(5, CharactersChallenge.digitSum("zx5y"));
            assertEquals(0, CharactersChallenge.digitSum("ABCDEF"));
        }

        @Test
        void testDigitsOfOtherScriptsCountWithTheirValue() {
            assertEquals(7, CharactersChallenge.digitSum("٣٤"));
            assertEquals(6, CharactersChallenge.digitSum("1٣x2"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: hexValue
    // ==========================================================
    @Nested
    class HexValueTests {

        @Test
        void testDecimalDigits() {
            assertEquals(0, CharactersChallenge.hexValue('0'));
            assertEquals(7, CharactersChallenge.hexValue('7'));
            assertEquals(9, CharactersChallenge.hexValue('9'));
        }

        @Test
        void testLowerCaseLetters() {
            assertEquals(10, CharactersChallenge.hexValue('a'));
            assertEquals(15, CharactersChallenge.hexValue('f'));
        }

        @Test
        void testUpperCaseLetters() {
            assertEquals(10, CharactersChallenge.hexValue('A'));
            assertEquals(15, CharactersChallenge.hexValue('F'));
        }

        @Test
        void testOtherCharactersGiveMinusOne() {
            assertEquals(-1, CharactersChallenge.hexValue('g'));
            assertEquals(-1, CharactersChallenge.hexValue('G'));
            assertEquals(-1, CharactersChallenge.hexValue('z'));
            assertEquals(-1, CharactersChallenge.hexValue(' '));
            assertEquals(-1, CharactersChallenge.hexValue('-'));
        }

        @Test
        void testOnlyTheAsciiHexadecimalDigitsAreValid() {
            assertEquals(-1, CharactersChallenge.hexValue('٣'));
            assertEquals(-1, CharactersChallenge.hexValue('１'));
            assertEquals(-1, CharactersChallenge.hexValue('Ａ'));
        }
    }

    // ==========================================================
    // CHALLENGE 9: hexDigit
    // ==========================================================
    @Nested
    class HexDigitTests {

        @Test
        void testDecimalDigits() {
            assertEquals('0', CharactersChallenge.hexDigit(0));
            assertEquals('9', CharactersChallenge.hexDigit(9));
        }

        @Test
        void testLettersAreLowerCase() {
            assertEquals('a', CharactersChallenge.hexDigit(10));
            assertEquals('f', CharactersChallenge.hexDigit(15));
        }

        @Test
        void testValuesOutOfRangeGiveAQuestionMark() {
            assertEquals('?', CharactersChallenge.hexDigit(-1));
            assertEquals('?', CharactersChallenge.hexDigit(16));
            assertEquals('?', CharactersChallenge.hexDigit(100));
            assertEquals('?', CharactersChallenge.hexDigit(Integer.MIN_VALUE));
        }
    }

    // ==========================================================
    // CHALLENGE 10: capitalizeWords
    // ==========================================================
    @Nested
    class CapitalizeWordsTests {

        @Test
        void testCapitalizesEachWord() {
            assertEquals("Hello World", CharactersChallenge.capitalizeWords("hello world"));
        }

        @Test
        void testTheRestOfTheWordGoesToLowerCase() {
            assertEquals("Hello   World", CharactersChallenge.capitalizeWords("hELLO   wORLD"));
        }

        @Test
        void testWhitespaceIsKeptExactlyAsItIs() {
            assertEquals("  Leading And Trailing  ", CharactersChallenge.capitalizeWords("  leading and trailing  "));
            assertEquals("Tab\tSeparated\nLines", CharactersChallenge.capitalizeWords("tab\tseparated\nlines"));
        }

        @Test
        void testEmptyTextAndSingleLetter() {
            assertEquals("", CharactersChallenge.capitalizeWords(""));
            assertEquals("A", CharactersChallenge.capitalizeWords("a"));
        }

        @Test
        void testWordsStartingWithAnythingButALetter() {
            assertEquals("123abc", CharactersChallenge.capitalizeWords("123ABC"));
            assertEquals("-hello", CharactersChallenge.capitalizeWords("-HELLO"));
        }

        @Test
        void testLettersWithAccents() {
            assertEquals("Élan Vital", CharactersChallenge.capitalizeWords("élan vITAL"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: isPalindrome
    // ==========================================================
    @Nested
    class IsPalindromeTests {

        @Test
        void testSentenceWithPunctuationAndCase() {
            assertTrue(CharactersChallenge.isPalindrome("A man, a plan, a canal: Panama"));
            assertTrue(CharactersChallenge.isPalindrome("No 'x' in Nixon"));
        }

        @Test
        void testNotAPalindrome() {
            assertFalse(CharactersChallenge.isPalindrome("race a car"));
            assertFalse(CharactersChallenge.isPalindrome("Ab"));
        }

        @Test
        void testDigitsAreConsidered() {
            assertTrue(CharactersChallenge.isPalindrome("12321"));
            assertFalse(CharactersChallenge.isPalindrome("12345"));
            assertFalse(CharactersChallenge.isPalindrome("0P"));
        }

        @Test
        void testCaseIsIgnored() {
            assertTrue(CharactersChallenge.isPalindrome("Aa"));
            assertTrue(CharactersChallenge.isPalindrome("RaceCar"));
        }

        @Test
        void testNothingToConsiderIsAPalindrome() {
            assertTrue(CharactersChallenge.isPalindrome(""));
            assertTrue(CharactersChallenge.isPalindrome("!!! ,,,"));
        }

        @Test
        void testSingleCharacter() {
            assertTrue(CharactersChallenge.isPalindrome("x"));
            assertTrue(CharactersChallenge.isPalindrome(" x "));
        }
    }

    // ==========================================================
    // CHALLENGE 12: fromCodePoint
    // ==========================================================
    @Nested
    class FromCodePointTests {

        @Test
        void testAsciiCodePoints() {
            assertEquals("A", CharactersChallenge.fromCodePoint(65));
            assertEquals("z", CharactersChallenge.fromCodePoint(122));
            assertEquals(" ", CharactersChallenge.fromCodePoint(32));
        }

        @Test
        void testCodePointsOfTheBasicPlane() {
            assertEquals("€", CharactersChallenge.fromCodePoint(0x20AC));
            assertEquals("é", CharactersChallenge.fromCodePoint(233));
        }

        @Test
        void testCodePointsAboveFFFFUseTwoChars() {
            String smile = CharactersChallenge.fromCodePoint(0x1F600);

            assertEquals(2, smile.length());
            assertEquals("😀", smile);
            assertEquals(2, CharactersChallenge.fromCodePoint(0x10FFFF).length());
        }

        @Test
        void testTheResultIsASingleCodePoint() {
            assertEquals(1, CharactersChallenge.fromCodePoint(0x1F600).codePointCount(0, 2));
            assertEquals(0x1F600, CharactersChallenge.fromCodePoint(0x1F600).codePointAt(0));
        }

        @Test
        void testInvalidCodePointsAreRejected() {
            assertThrows(IllegalArgumentException.class, () -> CharactersChallenge.fromCodePoint(-1));
            assertThrows(IllegalArgumentException.class, () -> CharactersChallenge.fromCodePoint(0x110000));
            assertThrows(IllegalArgumentException.class,
                    () -> CharactersChallenge.fromCodePoint(Integer.MAX_VALUE));
        }
    }

    // ==========================================================
    // CHALLENGE 13: caesar
    // ==========================================================
    @Nested
    class CaesarTests {

        @Test
        void testShiftsLetters() {
            assertEquals("bcd", CharactersChallenge.caesar("abc", 1));
            assertEquals("Khoor, Zruog!", CharactersChallenge.caesar("Hello, World!", 3));
        }

        @Test
        void testWrapsAroundTheAlphabet() {
            assertEquals("abc", CharactersChallenge.caesar("xyz", 3));
            assertEquals("ABC", CharactersChallenge.caesar("XYZ", 3));
        }

        @Test
        void testNegativeShifts() {
            assertEquals("zab", CharactersChallenge.caesar("abc", -1));
            assertEquals("xyz", CharactersChallenge.caesar("abc", -3));
        }

        @Test
        void testShiftsLargerThanTheAlphabet() {
            assertEquals("bcd", CharactersChallenge.caesar("abc", 27));
            assertEquals("zab", CharactersChallenge.caesar("abc", -27));
            assertEquals("abc", CharactersChallenge.caesar("abc", 52));
            assertEquals("abc", CharactersChallenge.caesar("abc", 0));
        }

        @Test
        void testHugeShifts() {
            assertEquals("xyz", CharactersChallenge.caesar("abc", Integer.MAX_VALUE));
            assertEquals("cde", CharactersChallenge.caesar("abc", Integer.MIN_VALUE));
            assertEquals("Ww", CharactersChallenge.caesar("Zz", Integer.MAX_VALUE));
        }

        @Test
        void testOtherCharactersStayAsTheyAre() {
            assertEquals("Dbgé 1!", CharactersChallenge.caesar("Café 1!", 1));
            assertEquals("", CharactersChallenge.caesar("", 5));
        }
    }

    // ==========================================================
    // CHALLENGE 14: isStrongPassword
    // ==========================================================
    @Nested
    class IsStrongPasswordTests {

        @Test
        void testStrongPasswords() {
            assertTrue(CharactersChallenge.isStrongPassword("Abcdef1!"));
            assertTrue(CharactersChallenge.isStrongPassword("Passw0rd#2024"));
        }

        @Test
        void testTooShort() {
            assertFalse(CharactersChallenge.isStrongPassword("Abcde1!"));
        }

        @Test
        void testEachKindOfCharacterIsRequired() {
            assertFalse(CharactersChallenge.isStrongPassword("abcdef1!"));
            assertFalse(CharactersChallenge.isStrongPassword("ABCDEF1!"));
            assertFalse(CharactersChallenge.isStrongPassword("Abcdefg!"));
            assertFalse(CharactersChallenge.isStrongPassword("Abcdefg1"));
        }

        @Test
        void testWhitespaceIsNotASymbol() {
            assertFalse(CharactersChallenge.isStrongPassword("Abc def1"));
            assertTrue(CharactersChallenge.isStrongPassword("Abcdef1 !"));
        }

        @Test
        void testNullAndEmpty() {
            assertFalse(CharactersChallenge.isStrongPassword(null));
            assertFalse(CharactersChallenge.isStrongPassword(""));
        }

        @Test
        void testLettersWithAccents() {
            assertTrue(CharactersChallenge.isStrongPassword("eeWcole12!"));
        }
    }

    // ==========================================================
    // CHALLENGE 15: isValidJavaIdentifier
    // ==========================================================
    @Nested
    class IsValidJavaIdentifierTests {

        @Test
        void testNormalIdentifiers() {
            assertTrue(CharactersChallenge.isValidJavaIdentifier("name"));
            assertTrue(CharactersChallenge.isValidJavaIdentifier("a1b2"));
            assertTrue(CharactersChallenge.isValidJavaIdentifier("MAX_VALUE"));
        }

        @Test
        void testUnderscoreAndDollarCanStartAnIdentifier() {
            assertTrue(CharactersChallenge.isValidJavaIdentifier("_x1"));
            assertTrue(CharactersChallenge.isValidJavaIdentifier("$a"));
        }

        @Test
        void testADigitCannotStartAnIdentifier() {
            assertFalse(CharactersChallenge.isValidJavaIdentifier("1x"));
            assertTrue(CharactersChallenge.isValidJavaIdentifier("x1"));
        }

        @Test
        void testInvalidCharacters() {
            assertFalse(CharactersChallenge.isValidJavaIdentifier("my-var"));
            assertFalse(CharactersChallenge.isValidJavaIdentifier("my var"));
            assertFalse(CharactersChallenge.isValidJavaIdentifier("a.b"));
        }

        @Test
        void testNullAndEmpty() {
            assertFalse(CharactersChallenge.isValidJavaIdentifier(null));
            assertFalse(CharactersChallenge.isValidJavaIdentifier(""));
        }

        @Test
        void testLettersWithAccentsAndReservedWords() {
            assertTrue(CharactersChallenge.isValidJavaIdentifier("café"));
            assertTrue(CharactersChallenge.isValidJavaIdentifier("class"));
        }
    }
}