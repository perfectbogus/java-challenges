package dev.perfectbogus.characters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class CharShiftChallengeTest {

    private static String expectedColumn(long n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.append((char) ('A' + n % 26));
            n /= 26;
        }
        return result.reverse().toString();
    }

    // ==========================================================
    // CHALLENGE 1: nextLetter
    // ==========================================================
    @Nested
    class NextLetterTests {

        @Test
        void testFirstLetters() {
            assertEquals('b', CharShiftChallenge.nextLetter('a'));
            assertEquals('c', CharShiftChallenge.nextLetter('b'));
        }

        @Test
        void testMiddleLetter() {
            assertEquals('n', CharShiftChallenge.nextLetter('m'));
            assertEquals('N', CharShiftChallenge.nextLetter('M'));
        }

        @Test
        void testFirstLettersSimplified() {
            assertEquals('b', CharShiftChallenge.nextLetterSimplified('a'));
            assertEquals('c', CharShiftChallenge.nextLetterSimplified('b'));
        }

        @Test
        void testMiddleLetterSimplified() {
            assertEquals('n', CharShiftChallenge.nextLetterSimplified('m'));
            assertEquals('N', CharShiftChallenge.nextLetterSimplified('M'));
        }

        @Test
        void testWrapLowercase() {
            assertEquals('a', CharShiftChallenge.nextLetter('z'));
        }

        @Test
        void testWrapUppercase() {
            assertEquals('A', CharShiftChallenge.nextLetter('Z'));
        }

        @Test
        void testLastLettersBeforeTheWrap() {
            assertEquals('z', CharShiftChallenge.nextLetter('y'));
            assertEquals('Z', CharShiftChallenge.nextLetter('Y'));
        }

        @Test
        void testUppercaseStaysUppercase() {
            assertEquals('B', CharShiftChallenge.nextLetter('A'));
            assertEquals('C', CharShiftChallenge.nextLetter('B'));
        }

        @Test
        void testEveryLowercaseLetter() {
            for (char c = 'a'; c < 'z'; c++) {
                assertEquals((char) (c + 1), CharShiftChallenge.nextLetter(c));
            }
        }

        @Test
        void testEveryUppercaseLetter() {
            for (char c = 'A'; c < 'Z'; c++) {
                assertEquals((char) (c + 1), CharShiftChallenge.nextLetter(c));
            }
        }

        @Test
        void testTheLetterAfterTheRealOneIsNotASymbol() {
            assertTrue(Character.isLetter(CharShiftChallenge.nextLetter('Z')));
            assertTrue(Character.isLetter(CharShiftChallenge.nextLetter('z')));
            assertTrue(Character.isUpperCase(CharShiftChallenge.nextLetter('Z')));
            assertTrue(Character.isLowerCase(CharShiftChallenge.nextLetter('z')));
        }

        @Test
        void testTheResultIsADifferentLetter() {
            for (char c = 'a'; c <= 'z'; c++) {
                assertTrue(CharShiftChallenge.nextLetter(c) != c);
            }
        }

        @Test
        void testAppliedTwentySixTimesGivesTheSameLetter() {
            char c = 'q';
            for (int i = 0; i < 26; i++) {
                c = CharShiftChallenge.nextLetter(c);
            }
            assertEquals('q', c);
        }
    }

    // ==========================================================
    // CHALLENGE 2: previousLetter
    // ==========================================================
    @Nested
    class PreviousLetterTests {

        @Test
        void testFirstLetters() {
            assertEquals('a', CharShiftChallenge.previousLetter('b'));
            assertEquals('b', CharShiftChallenge.previousLetter('c'));
        }

        @Test
        void testMiddleLetter() {
            assertEquals('m', CharShiftChallenge.previousLetter('n'));
            assertEquals('M', CharShiftChallenge.previousLetter('N'));
        }

        @Test
        void testWrapLowercase() {
            assertEquals('z', CharShiftChallenge.previousLetter('a'));
        }

        @Test
        void testWrapUppercase() {
            assertEquals('Z', CharShiftChallenge.previousLetter('A'));
        }

        @Test
        void testLastLetters() {
            assertEquals('y', CharShiftChallenge.previousLetter('z'));
            assertEquals('Y', CharShiftChallenge.previousLetter('Z'));
        }

        @Test
        void testUppercaseStaysUppercase() {
            assertEquals('A', CharShiftChallenge.previousLetter('B'));
        }

        @Test
        void testEveryLowercaseLetter() {
            for (char c = 'b'; c <= 'z'; c++) {
                assertEquals((char) (c - 1), CharShiftChallenge.previousLetter(c));
            }
        }

        @Test
        void testEveryUppercaseLetter() {
            for (char c = 'B'; c <= 'Z'; c++) {
                assertEquals((char) (c - 1), CharShiftChallenge.previousLetter(c));
            }
        }

        @Test
        void testTheLetterBeforeTheFirstIsNotASymbol() {
            assertTrue(Character.isLetter(CharShiftChallenge.previousLetter('a')));
            assertTrue(Character.isLetter(CharShiftChallenge.previousLetter('A')));
            assertTrue(Character.isLowerCase(CharShiftChallenge.previousLetter('a')));
            assertTrue(Character.isUpperCase(CharShiftChallenge.previousLetter('A')));
        }

        @Test
        void testItUndoesNextLetter() {
            for (char c = 'a'; c <= 'z'; c++) {
                assertEquals(c, CharShiftChallenge.previousLetter(CharShiftChallenge.nextLetter(c)));
            }
            for (char c = 'A'; c <= 'Z'; c++) {
                assertEquals(c, CharShiftChallenge.nextLetter(CharShiftChallenge.previousLetter(c)));
            }
        }

        @Test
        void testAppliedTwentySixTimesGivesTheSameLetter() {
            char c = 'Q';
            for (int i = 0; i < 26; i++) {
                c = CharShiftChallenge.previousLetter(c);
            }
            assertEquals('Q', c);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sumOfDigits
    // ==========================================================
    @Nested
    class SumOfDigitsTests {

        @Test
        void testExample() {
            assertEquals(6, CharShiftChallenge.sumOfDigits("123"));
        }

        @Test
        void testNines() {
            assertEquals(27, CharShiftChallenge.sumOfDigits("999"));
        }

        @Test
        void testZero() {
            assertEquals(0, CharShiftChallenge.sumOfDigits("0"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, CharShiftChallenge.sumOfDigits(""));
        }

        @Test
        void testOneDigit() {
            assertEquals(7, CharShiftChallenge.sumOfDigits("7"));
        }

        @Test
        void testManyZeros() {
            assertEquals(5, CharShiftChallenge.sumOfDigits("000500"));
        }

        @Test
        void testEveryDigit() {
            assertEquals(45, CharShiftChallenge.sumOfDigits("0123456789"));
        }

        @Test
        void testEachDigitSeparately() {
            for (int d = 0; d <= 9; d++) {
                assertEquals(d, CharShiftChallenge.sumOfDigits(String.valueOf(d)));
            }
        }

        @Test
        void testTheOrderDoesNotMatter() {
            assertEquals(CharShiftChallenge.sumOfDigits("1928"), CharShiftChallenge.sumOfDigits("8291"));
            assertEquals(20, CharShiftChallenge.sumOfDigits("1928"));
        }

        @Test
        void testDigitsAreNotTheirCodes() {
            assertEquals(1, CharShiftChallenge.sumOfDigits("1"));
            assertEquals(2, CharShiftChallenge.sumOfDigits("11"));
        }

        @Test
        void testTheSumIsBiggerThanNine() {
            assertEquals(18, CharShiftChallenge.sumOfDigits("99"));
            assertEquals(12, CharShiftChallenge.sumOfDigits("48"));
        }

        @Test
        void testBigText() {
            assertEquals(9_000_000, CharShiftChallenge.sumOfDigits("9".repeat(1_000_000)));
            assertEquals(1_000_000, CharShiftChallenge.sumOfDigits("1".repeat(1_000_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 4: letterSum
    // ==========================================================
    @Nested
    class LetterSumTests {

        @Test
        void testFirstLetters() {
            assertEquals(6, CharShiftChallenge.letterSum("abc"));
        }

        @Test
        void testMixedCase() {
            assertEquals(52, CharShiftChallenge.letterSum("Hello"));
            assertEquals(6, CharShiftChallenge.letterSum("AbC"));
        }

        @Test
        void testUppercaseAndLowercaseAreEqual() {
            assertEquals(52, CharShiftChallenge.letterSum("Zz"));
            assertEquals(CharShiftChallenge.letterSum("hello"), CharShiftChallenge.letterSum("HELLO"));
        }

        @Test
        void testDigitsAreIgnored() {
            assertEquals(3, CharShiftChallenge.letterSum("a1b2"));
        }

        @Test
        void testEmpty() {
            assertEquals(0, CharShiftChallenge.letterSum(""));
        }

        @Test
        void testOnlySymbols() {
            assertEquals(0, CharShiftChallenge.letterSum("!? 123"));
        }

        @Test
        void testFirstAndLastLetters() {
            assertEquals(1, CharShiftChallenge.letterSum("a"));
            assertEquals(1, CharShiftChallenge.letterSum("A"));
            assertEquals(26, CharShiftChallenge.letterSum("z"));
            assertEquals(26, CharShiftChallenge.letterSum("Z"));
        }

        @Test
        void testWholeAlphabet() {
            assertEquals(351, CharShiftChallenge.letterSum("abcdefghijklmnopqrstuvwxyz"));
            assertEquals(351, CharShiftChallenge.letterSum("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }

        @Test
        void testLettersWithAccentsAreIgnored() {
            assertEquals(1, CharShiftChallenge.letterSum("éañ"));
        }

        @Test
        void testSymbolsNextToTheLetterRanges() {
            assertEquals(0, CharShiftChallenge.letterSum("@[`{"));
            assertEquals(2, CharShiftChallenge.letterSum("@A[`a{"));
        }

        @Test
        void testSentence() {
            assertEquals(CharShiftChallenge.letterSum("helloworld"), CharShiftChallenge.letterSum("Hello, World!"));
            assertEquals(124, CharShiftChallenge.letterSum("helloworld"));
        }

        @Test
        void testBigText() {
            assertEquals(26_000_000, CharShiftChallenge.letterSum("z".repeat(1_000_000)));
            assertEquals(1_000_000, CharShiftChallenge.letterSum("A".repeat(1_000_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 5: toBase
    // ==========================================================
    @Nested
    class ToBaseTests {

        @Test
        void testBinary() {
            assertEquals("1010", CharShiftChallenge.toBase(10, 2));
        }

        @Test
        void testHexadecimalWithLetters() {
            assertEquals("FF", CharShiftChallenge.toBase(255, 16));
            assertEquals("A", CharShiftChallenge.toBase(10, 16));
            assertEquals("F", CharShiftChallenge.toBase(15, 16));
        }

        @Test
        void testZero() {
            assertEquals("0", CharShiftChallenge.toBase(0, 2));
            assertEquals("0", CharShiftChallenge.toBase(0, 16));
        }

        @Test
        void testBaseTen() {
            assertEquals("100", CharShiftChallenge.toBase(100, 10));
        }

        @Test
        void testOctal() {
            assertEquals("377", CharShiftChallenge.toBase(255, 8));
            assertEquals("10", CharShiftChallenge.toBase(8, 8));
        }

        @Test
        void testNoExtraZerosAtTheStart() {
            assertEquals("10", CharShiftChallenge.toBase(16, 16));
            assertEquals("1", CharShiftChallenge.toBase(1, 2));
        }

        @Test
        void testDigitsBelowTenInHexadecimal() {
            assertEquals("23", CharShiftChallenge.toBase(35, 16));
            assertEquals("9", CharShiftChallenge.toBase(9, 16));
        }

        @Test
        void testBaseThree() {
            assertEquals("101", CharShiftChallenge.toBase(10, 3));
        }

        @Test
        void testLettersAreUppercase() {
            assertEquals("6F56DF77", CharShiftChallenge.toBase(0x6F56DF77, 16));
            assertEquals("BEEF", CharShiftChallenge.toBase(0xBEEF, 16));
            assertEquals("ABCDEF", CharShiftChallenge.toBase(0xABCDEF, 16));
        }

        @Test
        void testBiggestInt() {
            assertEquals("7FFFFFFF", CharShiftChallenge.toBase(Integer.MAX_VALUE, 16));
            assertEquals("1".repeat(31), CharShiftChallenge.toBase(Integer.MAX_VALUE, 2));
        }

        @Test
        void testEveryNumberInEveryBase() {
            for (int base = 2; base <= 16; base++) {
                for (int n = 0; n <= 1000; n++) {
                    assertEquals(Integer.toString(n, base).toUpperCase(), CharShiftChallenge.toBase(n, base));
                }
            }
        }

        @Test
        void testBigNumbersInEveryBase() {
            for (int base = 2; base <= 16; base++) {
                for (int n = 2_000_000_000; n < 2_000_000_050; n++) {
                    assertEquals(Integer.toString(n, base).toUpperCase(), CharShiftChallenge.toBase(n, base));
                }
            }
        }
    }

    // ==========================================================
    // CHALLENGE 6: fromBase
    // ==========================================================
    @Nested
    class FromBaseTests {

        @Test
        void testBinary() {
            assertEquals(10, CharShiftChallenge.fromBase("1010", 2));
        }

        @Test
        void testHexadecimalWithLetters() {
            assertEquals(255, CharShiftChallenge.fromBase("FF", 16));
            assertEquals(10, CharShiftChallenge.fromBase("A", 16));
            assertEquals(15, CharShiftChallenge.fromBase("F", 16));
        }

        @Test
        void testZero() {
            assertEquals(0, CharShiftChallenge.fromBase("0", 2));
            assertEquals(0, CharShiftChallenge.fromBase("000", 16));
        }

        @Test
        void testBiggestInt() {
            assertEquals(Integer.MAX_VALUE, CharShiftChallenge.fromBase("7FFFFFFF", 16));
            assertEquals(Integer.MAX_VALUE, CharShiftChallenge.fromBase("1".repeat(31), 2));
        }

        @Test
        void testOctal() {
            assertEquals(255, CharShiftChallenge.fromBase("377", 8));
        }

        @Test
        void testBaseTen() {
            assertEquals(12345, CharShiftChallenge.fromBase("12345", 10));
        }

        @Test
        void testLeadingZeros() {
            assertEquals(5, CharShiftChallenge.fromBase("00101", 2));
        }

        @Test
        void testAllTheHexadecimalDigits() {
            assertEquals(0xABCDEF, CharShiftChallenge.fromBase("ABCDEF", 16));
            assertEquals(0x1234567, CharShiftChallenge.fromBase("1234567", 16));
            assertEquals(0x89, CharShiftChallenge.fromBase("89", 16));
        }

        @Test
        void testOneDigit() {
            for (int d = 0; d < 16; d++) {
                assertEquals(d, CharShiftChallenge.fromBase(Integer.toString(d, 16).toUpperCase(), 16));
            }
        }

        @Test
        void testBaseThree() {
            assertEquals(10, CharShiftChallenge.fromBase("101", 3));
        }

        @Test
        void testEveryNumberInEveryBase() {
            for (int base = 2; base <= 16; base++) {
                for (int n = 0; n <= 1000; n++) {
                    assertEquals(n, CharShiftChallenge.fromBase(Integer.toString(n, base).toUpperCase(), base));
                }
            }
        }

        @Test
        void testBigNumbersInEveryBase() {
            for (int base = 2; base <= 16; base++) {
                for (int n = 2_000_000_000; n < 2_000_000_050; n++) {
                    assertEquals(n, CharShiftChallenge.fromBase(Integer.toString(n, base).toUpperCase(), base));
                }
            }
        }
    }

    // ==========================================================
    // CHALLENGE 7: shiftByPosition
    // ==========================================================
    @Nested
    class ShiftByPositionTests {

        @Test
        void testAllA() {
            assertEquals("abc", CharShiftChallenge.shiftByPosition("aaa"));
        }

        @Test
        void testExample() {
            assertEquals("ace", CharShiftChallenge.shiftByPosition("abc"));
        }

        @Test
        void testWrap() {
            assertEquals("zab", CharShiftChallenge.shiftByPosition("zzz"));
        }

        @Test
        void testOneLetter() {
            assertEquals("a", CharShiftChallenge.shiftByPosition("a"));
            assertEquals("q", CharShiftChallenge.shiftByPosition("q"));
        }

        @Test
        void testEmpty() {
            assertEquals("", CharShiftChallenge.shiftByPosition(""));
        }

        @Test
        void testAllB() {
            assertEquals("bcde", CharShiftChallenge.shiftByPosition("bbbb"));
        }

        @Test
        void testTwoLetters() {
            assertEquals("aa", CharShiftChallenge.shiftByPosition("az"));
            assertEquals("za", CharShiftChallenge.shiftByPosition("zz"));
        }

        @Test
        void testPositionsBiggerThanTheAlphabet() {
            assertEquals("abcdefghijklmnopqrstuvwxyzabcd", CharShiftChallenge.shiftByPosition("a".repeat(30)));
        }

        @Test
        void testLastPositionsOfTheAlphabet() {
            String result = CharShiftChallenge.shiftByPosition("a".repeat(27));
            assertEquals('z', result.charAt(25));
            assertEquals('a', result.charAt(26));
        }

        @Test
        void testFirstLetterNeverMoves() {
            for (char c = 'a'; c <= 'z'; c++) {
                assertEquals(String.valueOf(c), CharShiftChallenge.shiftByPosition(String.valueOf(c)));
            }
        }

        @Test
        void testSecondLetterMovesOnePlace() {
            assertEquals("ab", CharShiftChallenge.shiftByPosition("aa"));
            assertEquals("bc", CharShiftChallenge.shiftByPosition("bb"));
            assertEquals("ya", CharShiftChallenge.shiftByPosition("yz"));
        }

        @Test
        void testBigText() {
            String result = CharShiftChallenge.shiftByPosition("a".repeat(1_000_000));
            assertEquals(1_000_000, result.length());
            assertEquals((char) ('a' + 999_999 % 26), result.charAt(999_999));
            assertEquals((char) ('a' + 500_000 % 26), result.charAt(500_000));
        }
    }

    // ==========================================================
    // CHALLENGE 8: atbash
    // ==========================================================
    @Nested
    class AtbashTests {

        @Test
        void testFirstLetters() {
            assertEquals("zyx", CharShiftChallenge.atbash("abc"));
        }

        @Test
        void testUppercase() {
            assertEquals("ZYX", CharShiftChallenge.atbash("ABC"));
        }

        @Test
        void testSentence() {
            assertEquals("Svool, Dliow!", CharShiftChallenge.atbash("Hello, World!"));
        }

        @Test
        void testDigitsDoNotChange() {
            assertEquals("123", CharShiftChallenge.atbash("123"));
        }

        @Test
        void testEmpty() {
            assertEquals("", CharShiftChallenge.atbash(""));
        }

        @Test
        void testMiddleLetters() {
            assertEquals("n", CharShiftChallenge.atbash("m"));
            assertEquals("m", CharShiftChallenge.atbash("n"));
            assertEquals("N", CharShiftChallenge.atbash("M"));
        }

        @Test
        void testTwiceGivesTheOriginal() {
            assertEquals("Hello, World!", CharShiftChallenge.atbash(CharShiftChallenge.atbash("Hello, World!")));
        }

        @Test
        void testTheWholeAlphabet() {
            assertEquals("zyxwvutsrqponmlkjihgfedcba", CharShiftChallenge.atbash("abcdefghijklmnopqrstuvwxyz"));
            assertEquals("ZYXWVUTSRQPONMLKJIHGFEDCBA", CharShiftChallenge.atbash("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }

        @Test
        void testLettersWithAccentsDoNotChange() {
            assertEquals("ézñ", CharShiftChallenge.atbash("éañ"));
        }

        @Test
        void testSymbolsNextToTheLetterRanges() {
            assertEquals("@[`{", CharShiftChallenge.atbash("@[`{"));
            assertEquals("@Z[`z{", CharShiftChallenge.atbash("@A[`a{"));
        }

        @Test
        void testSpacesAndMixedCase() {
            assertEquals("zA zA", CharShiftChallenge.atbash("aZ aZ"));
        }

        @Test
        void testBigText() {
            assertEquals("z".repeat(1_000_000), CharShiftChallenge.atbash("a".repeat(1_000_000)));
            assertEquals("Ab".repeat(500_000), CharShiftChallenge.atbash("Zy".repeat(500_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: lettersBetween
    // ==========================================================
    @Nested
    class LettersBetweenTests {

        @Test
        void testLowercase() {
            assertEquals("abcde", CharShiftChallenge.lettersBetween('a', 'e'));
        }

        @Test
        void testFromIsBigger() {
            assertEquals("", CharShiftChallenge.lettersBetween('e', 'a'));
        }

        @Test
        void testSameCharacter() {
            assertEquals("x", CharShiftChallenge.lettersBetween('x', 'x'));
        }

        @Test
        void testDigits() {
            assertEquals("0123456789", CharShiftChallenge.lettersBetween('0', '9'));
        }

        @Test
        void testUppercase() {
            assertEquals("ABCDEF", CharShiftChallenge.lettersBetween('A', 'F'));
        }

        @Test
        void testFromUppercaseToLowercase() {
            assertEquals("YZ[\\]^_`ab", CharShiftChallenge.lettersBetween('Y', 'b'));
        }

        @Test
        void testWholeAlphabet() {
            assertEquals("abcdefghijklmnopqrstuvwxyz", CharShiftChallenge.lettersBetween('a', 'z'));
        }

        @Test
        void testTwoCharacters() {
            assertEquals("ab", CharShiftChallenge.lettersBetween('a', 'b'));
        }

        @Test
        void testLastCharacterIsIncluded() {
            assertEquals('z', CharShiftChallenge.lettersBetween('a', 'z').charAt(25));
            assertEquals(26, CharShiftChallenge.lettersBetween('a', 'z').length());
        }

        @Test
        void testTheLastPossibleCharacter() {
            assertEquals("￾￿", CharShiftChallenge.lettersBetween('￾', '￿'));
            assertEquals("￿", CharShiftChallenge.lettersBetween('￿', '￿'));
        }

        @Test
        void testTheFirstPossibleCharacter() {
            assertEquals("\u0000\u0001\u0002", CharShiftChallenge.lettersBetween('\u0000', '\u0002'));
        }

        @Test
        void testAllTheCharacters() {
            String all = CharShiftChallenge.lettersBetween('\u0000', '￿');
            assertEquals(65_536, all.length());
            assertEquals('\u0000', all.charAt(0));
            assertEquals('￿', all.charAt(65_535));
            assertEquals('A', all.charAt(65));
        }
    }

    // ==========================================================
    // CHALLENGE 10: isConsecutive
    // ==========================================================
    @Nested
    class IsConsecutiveTests {

        @Test
        void testLetters() {
            assertTrue(CharShiftChallenge.isConsecutive("abc"));
        }

        @Test
        void testGap() {
            assertFalse(CharShiftChallenge.isConsecutive("abd"));
        }

        @Test
        void testDescending() {
            assertFalse(CharShiftChallenge.isConsecutive("cba"));
        }

        @Test
        void testDigits() {
            assertTrue(CharShiftChallenge.isConsecutive("789"));
        }

        @Test
        void testAfterZ() {
            assertTrue(CharShiftChallenge.isConsecutive("yz{"));
        }

        @Test
        void testRepeated() {
            assertFalse(CharShiftChallenge.isConsecutive("aab"));
            assertFalse(CharShiftChallenge.isConsecutive("aa"));
        }

        @Test
        void testEmpty() {
            assertTrue(CharShiftChallenge.isConsecutive(""));
        }

        @Test
        void testOneCharacter() {
            assertTrue(CharShiftChallenge.isConsecutive("a"));
            assertTrue(CharShiftChallenge.isConsecutive("#"));
        }

        @Test
        void testTwoCharacters() {
            assertTrue(CharShiftChallenge.isConsecutive("ab"));
            assertFalse(CharShiftChallenge.isConsecutive("ba"));
            assertFalse(CharShiftChallenge.isConsecutive("ac"));
        }

        @Test
        void testOnlyTheLastPairIsWrong() {
            assertFalse(CharShiftChallenge.isConsecutive("abcdeg"));
        }

        @Test
        void testOnlyTheFirstPairIsWrong() {
            assertFalse(CharShiftChallenge.isConsecutive("acdef"));
        }

        @Test
        void testUppercaseAndLowercase() {
            assertFalse(CharShiftChallenge.isConsecutive("aB"));
            assertTrue(CharShiftChallenge.isConsecutive("YZ[\\]"));
        }

        @Test
        void testBigText() {
            String lettersBetween = CharShiftChallenge.lettersBetween('Ϩ', '倀');
            assertTrue(CharShiftChallenge.isConsecutive(lettersBetween));
            assertFalse(CharShiftChallenge.isConsecutive(lettersBetween + "倂"));
            assertFalse(CharShiftChallenge.isConsecutive("x" + lettersBetween));
        }
    }

    // ==========================================================
    // CHALLENGE 11: columnName
    // ==========================================================
    @Nested
    class ColumnNameTests {

        @Test
        void testFirstColumns() {
            assertEquals("A", CharShiftChallenge.columnName(1));
            assertEquals("B", CharShiftChallenge.columnName(2));
        }

        @Test
        void testLastSingleLetter() {
            assertEquals("Z", CharShiftChallenge.columnName(26));
        }

        @Test
        void testFirstTwoLetterNames() {
            assertEquals("AA", CharShiftChallenge.columnName(27));
            assertEquals("AB", CharShiftChallenge.columnName(28));
        }

        @Test
        void testEndOfTheFirstTwoLetterGroup() {
            assertEquals("AZ", CharShiftChallenge.columnName(52));
            assertEquals("BA", CharShiftChallenge.columnName(53));
            assertEquals("BZ", CharShiftChallenge.columnName(78));
        }

        @Test
        void testLastTwoLetterName() {
            assertEquals("ZZ", CharShiftChallenge.columnName(702));
        }

        @Test
        void testFirstThreeLetterNames() {
            assertEquals("AAA", CharShiftChallenge.columnName(703));
            assertEquals("AAB", CharShiftChallenge.columnName(704));
        }

        @Test
        void testWellKnownColumn() {
            assertEquals("XFD", CharShiftChallenge.columnName(16_384));
        }

        @Test
        void testGroupBoundaries() {
            assertEquals("ZZZ", CharShiftChallenge.columnName(18_278));
            assertEquals("AAAA", CharShiftChallenge.columnName(18_279));
        }

        @Test
        void testBigNumbers() {
            assertEquals("EQXD", CharShiftChallenge.columnName(100_000));
            assertEquals("BDWGN", CharShiftChallenge.columnName(1_000_000));
        }

        @Test
        void testBiggestInt() {
            assertEquals("FXSHRXW", CharShiftChallenge.columnName(Integer.MAX_VALUE));
        }

        @Test
        void testManyColumns() {
            for (int n = 1; n <= 20_000; n++) {
                assertEquals(expectedColumn(n), CharShiftChallenge.columnName(n));
            }
        }

        @Test
        void testEveryZColumn() {
            assertEquals("ZZZZ", CharShiftChallenge.columnName(475_254));
            assertEquals(expectedColumn(2_000_000_000), CharShiftChallenge.columnName(2_000_000_000));
        }
    }

    // ==========================================================
    // CHALLENGE 12: columnNumber
    // ==========================================================
    @Nested
    class ColumnNumberTests {

        @Test
        void testFirstColumns() {
            assertEquals(1, CharShiftChallenge.columnNumber("A"));
            assertEquals(2, CharShiftChallenge.columnNumber("B"));
        }

        @Test
        void testLastSingleLetter() {
            assertEquals(26, CharShiftChallenge.columnNumber("Z"));
        }

        @Test
        void testFirstTwoLetterNames() {
            assertEquals(27, CharShiftChallenge.columnNumber("AA"));
            assertEquals(28, CharShiftChallenge.columnNumber("AB"));
        }

        @Test
        void testEndOfTheFirstTwoLetterGroup() {
            assertEquals(52, CharShiftChallenge.columnNumber("AZ"));
            assertEquals(53, CharShiftChallenge.columnNumber("BA"));
            assertEquals(78, CharShiftChallenge.columnNumber("BZ"));
        }

        @Test
        void testLastTwoLetterName() {
            assertEquals(702, CharShiftChallenge.columnNumber("ZZ"));
        }

        @Test
        void testFirstThreeLetterNames() {
            assertEquals(703, CharShiftChallenge.columnNumber("AAA"));
            assertEquals(704, CharShiftChallenge.columnNumber("AAB"));
        }

        @Test
        void testWellKnownColumn() {
            assertEquals(16_384, CharShiftChallenge.columnNumber("XFD"));
        }

        @Test
        void testGroupBoundaries() {
            assertEquals(18_278, CharShiftChallenge.columnNumber("ZZZ"));
            assertEquals(18_279, CharShiftChallenge.columnNumber("AAAA"));
        }

        @Test
        void testBigNumbers() {
            assertEquals(100_000, CharShiftChallenge.columnNumber("EQXD"));
            assertEquals(1_000_000, CharShiftChallenge.columnNumber("BDWGN"));
        }

        @Test
        void testBiggestInt() {
            assertEquals(Integer.MAX_VALUE, CharShiftChallenge.columnNumber("FXSHRXW"));
        }

        @Test
        void testManyColumns() {
            for (int n = 1; n <= 20_000; n++) {
                assertEquals(n, CharShiftChallenge.columnNumber(expectedColumn(n)));
            }
        }

        @Test
        void testEveryLetterAlone() {
            for (char c = 'A'; c <= 'Z'; c++) {
                assertEquals(c - 'A' + 1, CharShiftChallenge.columnNumber(String.valueOf(c)));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 13: vigenere
    // ==========================================================
    @Nested
    class VigenereTests {

        @Test
        void testKeyWithOneLetter() {
            assertEquals("buubdl", CharShiftChallenge.vigenere("attack", "b"));
        }

        @Test
        void testKeyIsRepeated() {
            assertEquals("hflmo", CharShiftChallenge.vigenere("hello", "ab"));
        }

        @Test
        void testKeyA() {
            assertEquals("abc", CharShiftChallenge.vigenere("abc", "a"));
        }

        @Test
        void testKeyZ() {
            assertEquals("zab", CharShiftChallenge.vigenere("abc", "z"));
        }

        @Test
        void testClassicExample() {
            assertEquals("wiyca", CharShiftChallenge.vigenere("lemon", "lemon"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", CharShiftChallenge.vigenere("", "key"));
        }

        @Test
        void testKeyLongerThanTheText() {
            assertEquals("bd", CharShiftChallenge.vigenere("ab", "bcdefgh"));
        }

        @Test
        void testKeyOfTheSameLength() {
            assertEquals("bd", CharShiftChallenge.vigenere("ab", "bc"));
        }

        @Test
        void testWrapInTheMiddle() {
            assertEquals("aaa", CharShiftChallenge.vigenere("zzz", "b"));
            assertEquals("xzy", CharShiftChallenge.vigenere("xyz", "abz"));
        }

        @Test
        void testKeyIsRepeatedManyTimes() {
            assertEquals("abcabcabc", CharShiftChallenge.vigenere("aaaaaaaaa", "abc"));
        }

        @Test
        void testEveryLetterOfTheKey() {
            for (char k = 'a'; k <= 'z'; k++) {
                assertEquals(String.valueOf(k), CharShiftChallenge.vigenere("a", String.valueOf(k)));
            }
        }

        @Test
        void testBigText() {
            assertEquals("b".repeat(1_000_000), CharShiftChallenge.vigenere("a".repeat(1_000_000), "b"));
            assertEquals("abc".repeat(100_000), CharShiftChallenge.vigenere("a".repeat(300_000), "abc"));
        }
    }

    // ==========================================================
    // CHALLENGE 14: isShiftOf
    // ==========================================================
    @Nested
    class IsShiftOfTests {

        @Test
        void testShiftByOne() {
            assertTrue(CharShiftChallenge.isShiftOf("abc", "bcd"));
        }

        @Test
        void testDifferentShifts() {
            assertFalse(CharShiftChallenge.isShiftOf("abc", "bdd"));
            assertFalse(CharShiftChallenge.isShiftOf("ab", "bd"));
            assertFalse(CharShiftChallenge.isShiftOf("aa", "bc"));
        }

        @Test
        void testWrap() {
            assertTrue(CharShiftChallenge.isShiftOf("xyz", "abc"));
            assertTrue(CharShiftChallenge.isShiftOf("az", "ba"));
        }

        @Test
        void testSameText() {
            assertTrue(CharShiftChallenge.isShiftOf("abc", "abc"));
            assertTrue(CharShiftChallenge.isShiftOf("a", "a"));
        }

        @Test
        void testDifferentLengths() {
            assertFalse(CharShiftChallenge.isShiftOf("a", "aa"));
            assertFalse(CharShiftChallenge.isShiftOf("ab", "b"));
            assertFalse(CharShiftChallenge.isShiftOf("", "a"));
        }

        @Test
        void testEmptyTexts() {
            assertTrue(CharShiftChallenge.isShiftOf("", ""));
        }

        @Test
        void testShiftBackwards() {
            assertTrue(CharShiftChallenge.isShiftOf("def", "abc"));
            assertTrue(CharShiftChallenge.isShiftOf("b", "a"));
        }

        @Test
        void testBiggestShift() {
            assertTrue(CharShiftChallenge.isShiftOf("bcd", "abc"));
            assertTrue(CharShiftChallenge.isShiftOf("a", "z"));
        }

        @Test
        void testOnlyTheLastLetterIsWrong() {
            assertFalse(CharShiftChallenge.isShiftOf("abcd", "bcdf"));
        }

        @Test
        void testOnlyTheFirstLetterIsWrong() {
            assertFalse(CharShiftChallenge.isShiftOf("abcd", "ccde"));
        }

        @Test
        void testEveryShift() {
            for (int move = 0; move < 26; move++) {
                StringBuilder shifted = new StringBuilder();
                for (char c : "thequickbrownfox".toCharArray()) {
                    shifted.append((char) ('a' + (c - 'a' + move) % 26));
                }
                assertTrue(CharShiftChallenge.isShiftOf("thequickbrownfox", shifted.toString()));
            }
        }

        @Test
        void testBigTexts() {
            String a = "ab".repeat(500_000);
            String b = "bc".repeat(500_000);
            assertTrue(CharShiftChallenge.isShiftOf(a, b));
            assertFalse(CharShiftChallenge.isShiftOf(a, "bc".repeat(499_999) + "bd"));
        }
    }

    // ==========================================================
    // CHALLENGE 15: toUpper
    // ==========================================================
    @Nested
    class ToUpperTests {

        @Test
        void testMixed() {
            assertEquals("HELLO", CharShiftChallenge.toUpper("Hello"));
        }

        @Test
        void testWithDigits() {
            assertEquals("ABC123XYZ", CharShiftChallenge.toUpper("abc123xyz"));
        }

        @Test
        void testLettersWithAccentsDoNotChange() {
            assertEquals("éA", CharShiftChallenge.toUpper("éa"));
        }

        @Test
        void testEmpty() {
            assertEquals("", CharShiftChallenge.toUpper(""));
        }

        @Test
        void testAlreadyUppercase() {
            assertEquals("ABC", CharShiftChallenge.toUpper("ABC"));
        }

        @Test
        void testSymbolsNextToTheLetterRange() {
            assertEquals("`A{", CharShiftChallenge.toUpper("`a{"));
            assertEquals("XYZ{", CharShiftChallenge.toUpper("xyz{"));
            assertEquals("@[", CharShiftChallenge.toUpper("@["));
        }

        @Test
        void testFirstAndLastLetters() {
            assertEquals("AZ", CharShiftChallenge.toUpper("az"));
        }

        @Test
        void testWholeAlphabet() {
            assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", CharShiftChallenge.toUpper("abcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void testSpacesAndSymbols() {
            assertEquals("A B! C?", CharShiftChallenge.toUpper("a b! c?"));
        }

        @Test
        void testOneCharacter() {
            assertEquals("Q", CharShiftChallenge.toUpper("q"));
            assertEquals("5", CharShiftChallenge.toUpper("5"));
        }

        @Test
        void testNonEnglishLowercaseLettersDoNotChange() {
            assertEquals("ñçé", CharShiftChallenge.toUpper("ñçé"));
        }

        @Test
        void testBigText() {
            assertEquals("AB".repeat(500_000), CharShiftChallenge.toUpper("aB".repeat(500_000)));
            assertEquals("Z".repeat(1_000_000), CharShiftChallenge.toUpper("z".repeat(1_000_000)));
        }
    }
}