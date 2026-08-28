package dev.perfectbogus.strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — isLetter + isDigit → classify characters
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<String, Long> result = CharacterChallenges.challenge1("Hello World 123!");
            assertEquals(10L, result.get("LETTER")); // H,e,l,l,o,W,o,r,l,d
            assertEquals(3L,  result.get("DIGIT"));  // 1,2,3
            assertEquals(3L,  result.get("OTHER"));  // space,space,!
        }

        @Test
        void onlyLetters() {
            Map<String, Long> result = CharacterChallenges.challenge1("Hello");
            assertEquals(5L, result.get("LETTER"));
            assertEquals(0L, result.get("DIGIT"));
            assertEquals(0L, result.get("OTHER"));
        }

        @Test
        void onlyDigits() {
            Map<String, Long> result = CharacterChallenges.challenge1("12345");
            assertEquals(0L, result.get("LETTER"));
            assertEquals(5L, result.get("DIGIT"));
            assertEquals(0L, result.get("OTHER"));
        }

        @Test
        void onlySpecialChars() {
            Map<String, Long> result = CharacterChallenges.challenge1("!@#");
            assertEquals(0L, result.get("LETTER"));
            assertEquals(0L, result.get("DIGIT"));
            assertEquals(3L, result.get("OTHER"));
        }

        @Test
        void emptyString() {
            Map<String, Long> result = CharacterChallenges.challenge1("");
            assertEquals(0L, result.get("LETTER"));
            assertEquals(0L, result.get("DIGIT"));
            assertEquals(0L, result.get("OTHER"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — isUpperCase + isLowerCase + toggle case
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            assertEquals("hELLO wORLD! 123",
                    CharacterChallenges.challenge2("Hello World! 123"));
        }

        @Test
        void basicCase_2() {
            assertEquals("hELLO wORLD! 123",
                    CharacterChallenges.challenge2_2("Hello World! 123"));
        }

        @Test
        void mixedCase() {
            assertEquals("jAVA is fUN",
                    CharacterChallenges.challenge2("Java IS Fun"));
        }

        @Test
        void allUppercase() {
            assertEquals("hello world",
                    CharacterChallenges.challenge2("HELLO WORLD"));
        }

        @Test
        void allLowercase() {
            assertEquals("HELLO WORLD",
                    CharacterChallenges.challenge2("hello world"));
        }

        @Test
        void nonLettersUnchanged() {
            assertEquals("123 !@#",
                    CharacterChallenges.challenge2("123 !@#"));
        }

        @Test
        void emptyString() {
            assertEquals("", CharacterChallenges.challenge2(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — getNumericValue() to sum digits
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            // 1+2+3+4+5+6 = 21
            assertEquals(21, CharacterChallenges.challenge3("abc 123 def 456"));
        }

        @Test
        void noDigits() {
            assertEquals(0, CharacterChallenges.challenge3("no digits here!"));
        }

        @Test
        void allDigits() {
            assertEquals(45, CharacterChallenges.challenge3("123456789")); // 1+2+...+9
        }

        @Test
        void singleDigit() {
            assertEquals(7, CharacterChallenges.challenge3("abc7def"));
        }

        @Test
        void zeros() {
            assertEquals(0, CharacterChallenges.challenge3("000"));
        }

        @Test
        void emptyString() {
            assertEquals(0, CharacterChallenges.challenge3(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — isLetter + isLetterOrDigit → valid identifier
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void validSimple() {
            assertTrue(CharacterChallenges.challenge4("myVariable"));
        }

        @Test
        void validWithUnderscore() {
            assertTrue(CharacterChallenges.challenge4("my_var123"));
        }

        @Test
        void validWithDollar() {
            assertTrue(CharacterChallenges.challenge4("$price"));
        }

        @Test
        void validUnderscoreStart() {
            assertTrue(CharacterChallenges.challenge4("_myVar"));
        }

        @Test
        void invalidStartsWithDigit() {
            assertFalse(CharacterChallenges.challenge4("123abc"));
        }

        @Test
        void invalidContainsHyphen() {
            assertFalse(CharacterChallenges.challenge4("my-variable"));
        }

        @Test
        void invalidContainsSpace() {
            assertFalse(CharacterChallenges.challenge4("my variable"));
        }

        @Test
        void invalidEmpty() {
            assertFalse(CharacterChallenges.challenge4(""));
        }

        @Test
        void singleLetter() {
            assertTrue(CharacterChallenges.challenge4("x"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Character.forDigit() → decimal to hex
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            assertEquals("ff", CharacterChallenges.challenge5(255));
        }

        @Test
        void sixteenIsHexTen() {
            assertEquals("10", CharacterChallenges.challenge5(16));
        }

        @Test
        void zero() {
            assertEquals("0", CharacterChallenges.challenge5(0));
        }

        @Test
        void powerOf256() {
            assertEquals("100", CharacterChallenges.challenge5(256));
        }

        @Test
        void singleHexDigit() {
            assertEquals("a", CharacterChallenges.challenge5(10));
            assertEquals("f", CharacterChallenges.challenge5(15));
            assertEquals("9", CharacterChallenges.challenge5(9));
        }

        @Test
        void largerNumber() {
            assertEquals("1a3", CharacterChallenges.challenge5(419));
        }

        @Test
        void negativeInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge5(-1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Character.digit() → hex to decimal
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            assertEquals(255, CharacterChallenges.challenge6("ff"));
        }

        @Test
        void uppercase() {
            assertEquals(255, CharacterChallenges.challenge6("FF"));
        }

        @Test
        void mixedCase() {
            assertEquals(255, CharacterChallenges.challenge6("Ff"));
        }

        @Test
        void hexTen() {
            assertEquals(16, CharacterChallenges.challenge6("10"));
        }

        @Test
        void largerHex() {
            assertEquals(419, CharacterChallenges.challenge6("1a3"));
        }

        @Test
        void zero() {
            assertEquals(0, CharacterChallenges.challenge6("0"));
        }

        @Test
        void singleDigit() {
            assertEquals(15, CharacterChallenges.challenge6("f"));
            assertEquals(10, CharacterChallenges.challenge6("a"));
        }

        @Test
        void invalidCharacter() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge6("xyz"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — isWhitespace() → count words
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            assertEquals(3, CharacterChallenges.challenge7("hello world java"));
        }

        @Test
        void multipleSpaces() {
            assertEquals(2, CharacterChallenges.challenge7("  hello   world  "));
        }

        @Test
        void singleWord() {
            assertEquals(1, CharacterChallenges.challenge7("one"));
        }

        @Test
        void onlySpaces() {
            assertEquals(0, CharacterChallenges.challenge7("   "));
        }

        @Test
        void emptyString() {
            assertEquals(0, CharacterChallenges.challenge7(""));
        }

        @Test
        void tabsAndNewlines() {
            assertEquals(3, CharacterChallenges.challenge7("hello\tworld\njava"));
        }

        @Test
        void singleChar() {
            assertEquals(1, CharacterChallenges.challenge7("a"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Character.compare() → find closest char
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            // a=7, f=2, z=18, m=5 → closest is f
            Optional<Character> result = CharacterChallenges.challenge8(
                    List.of('a','f','z','m'), 'h');
            assertTrue(result.isPresent());
            assertEquals('f', result.get());
        }

        @Test
        void tieBreakAlpha() {
            // 'a' distance=1, 'c' distance=1 → tie → 'a' first alpha
            Optional<Character> result = CharacterChallenges.challenge8(
                    List.of('c','a'), 'b');
            assertEquals('a', result.get());
        }

        @Test
        void exactMatch() {
            Optional<Character> result = CharacterChallenges.challenge8(
                    List.of('a','b','c'), 'b');
            assertEquals('b', result.get()); // distance=0
        }

        @Test
        void singleElement() {
            Optional<Character> result = CharacterChallenges.challenge8(
                    List.of('z'), 'a');
            assertTrue(result.isPresent());
            assertEquals('z', result.get());
        }

        @Test
        void emptyList() {
            Optional<Character> result = CharacterChallenges.challenge8(
                    List.of(), 'a');
            assertTrue(result.isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge8(null, 'a'));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — isAlphabetic() + toLowerCase() → palindrome check
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void classicPalindrome() {
            assertTrue(CharacterChallenges.challenge9(
                    "A man a plan a canal Panama"));
        }

        @Test
        void notPalindrome() {
            assertFalse(CharacterChallenges.challenge9("race a car"));
        }

        @Test
        void simplePalindrome() {
            assertTrue(CharacterChallenges.challenge9("racecar"));
        }

        @Test
        void withPunctuation() {
            assertTrue(CharacterChallenges.challenge9("Was it a car or a cat I saw?"));
        }

        @Test
        void singleChar() {
            assertTrue(CharacterChallenges.challenge9("a"));
        }

        @Test
        void emptyString() {
            assertTrue(CharacterChallenges.challenge9(""));
        }

        @Test
        void allSameChar() {
            assertTrue(CharacterChallenges.challenge9("aaa"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — All Character methods combined → password validator
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void validPassword() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("Hello1!World");
            assertTrue(result.valid());
            assertTrue(result.violations().isEmpty());
        }

        @Test
        void tooShort() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("Hello1!");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must be at least 8 characters"));
        }

        @Test
        void noUppercase() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("hello1!world");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must contain at least 1 uppercase letter"));
        }

        @Test
        void noLowercase() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("HELLO1!WORLD");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must contain at least 1 lowercase letter"));
        }

        @Test
        void noDigit() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("Hello!World");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must contain at least 1 digit"));
        }

        @Test
        void noSpecialChar() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("Hello1World");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must contain at least 1 special character"));
        }

        @Test
        void hasWhitespace() {
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("Hello 1!World");
            assertFalse(result.valid());
            assertTrue(result.violations().contains("Must not contain whitespace"));
        }

        @Test
        void multipleViolations() {
            // too short + no uppercase + no digit + no special
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("hello");
            assertFalse(result.valid());
            assertTrue(result.violations().size() > 1);
        }

        @Test
        void allViolationsCollected() {
            // empty string → all rules violated except whitespace
            CharacterChallenges.PasswordResult result =
                    CharacterChallenges.challenge10("");
            assertFalse(result.valid());
            assertEquals(5, result.violations().size());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CharacterChallenges.challenge10(null));
        }
    }
}