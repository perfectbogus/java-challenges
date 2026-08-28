package dev.perfectbogus.strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StringChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Reverse each word, keep word order
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            assertEquals("olleH dlroW avaJ",
                    StringChallenges.challenge1("Hello World Java"));
        }

        @Test
        void singleCharWords() {
            assertEquals("I evol smaerts",
                    StringChallenges.challenge1("I love streams"));
        }

        @Test
        void singleWord() {
            assertEquals("olleh",
                    StringChallenges.challenge1("hello"));
        }

        @Test
        void palindromeWords() {
            // reversing palindromes gives same word
            assertEquals("racecar level",
                    StringChallenges.challenge1("racecar level"));
        }

        @Test
        void singleCharSentence() {
            assertEquals("a b c",
                    StringChallenges.challenge1("a b c"));
        }

        @Test
        void emptyString() {
            assertEquals("", StringChallenges.challenge1(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Count vowels and consonants
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            StringChallenges.CharCount result = StringChallenges.challenge2("Hello World");
            assertEquals(3, result.vowels());      // e, o, o
            assertEquals(7, result.consonants());  // H, l, l, W, r, l, d
        }

        @Test
        void withDigitsAndSpecialChars() {
            StringChallenges.CharCount result = StringChallenges.challenge2("Java 123!");
            assertEquals(2, result.vowels());     // a, a
            assertEquals(2, result.consonants()); // J, v
        }

        @Test
        void allVowels() {
            StringChallenges.CharCount result = StringChallenges.challenge2("aeiou");
            assertEquals(5, result.vowels());
            assertEquals(0, result.consonants());
        }

        @Test
        void allConsonants() {
            StringChallenges.CharCount result = StringChallenges.challenge2("bcd");
            assertEquals(0, result.vowels());
            assertEquals(3, result.consonants());
        }

        @Test
        void caseInsensitive() {
            StringChallenges.CharCount result = StringChallenges.challenge2("HELLO");
            assertEquals(2, result.vowels());     // E, O
            assertEquals(3, result.consonants()); // H, L, L
        }

        @Test
        void onlySpaces() {
            StringChallenges.CharCount result = StringChallenges.challenge2("   ");
            assertEquals(0, result.vowels());
            assertEquals(0, result.consonants());
        }

        @Test
        void emptyString() {
            StringChallenges.CharCount result = StringChallenges.challenge2("");
            assertEquals(0, result.vowels());
            assertEquals(0, result.consonants());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Check if two strings are anagrams
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicAnagram() {
            assertTrue(StringChallenges.challenge3("listen", "silent"));
        }

        @Test
        void notAnagram() {
            assertFalse(StringChallenges.challenge3("hello", "world"));
        }

        @Test
        void withSpacesIgnored() {
            assertTrue(StringChallenges.challenge3("Astronomer", "Moon starer"));
        }

        @Test
        void caseInsensitive() {
            assertTrue(StringChallenges.challenge3("Listen", "SILENT"));
        }

        @Test
        void differentLengths() {
            assertFalse(StringChallenges.challenge3("abc", "ab"));
        }

        @Test
        void sameString() {
            assertTrue(StringChallenges.challenge3("hello", "hello"));
        }

        @Test
        void singleChars() {
            assertTrue(StringChallenges.challenge3("a", "a"));
            assertFalse(StringChallenges.challenge3("a", "b"));
        }

        @Test
        void emptyStrings() {
            assertTrue(StringChallenges.challenge3("", ""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge3(null, "hello"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Caesar cipher shift
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            assertEquals("Khoor, Zruog!",
                    StringChallenges.challenge4("Hello, World!", 3));
        }

        @Test
        void wrapAroundLowercase() {
            assertEquals("abc", StringChallenges.challenge4("xyz", 3));
        }

        @Test
        void wrapAroundUppercase() {
            assertEquals("ABC", StringChallenges.challenge4("XYZ", 3));
        }

        @Test
        void zeroShift() {
            assertEquals("Hello", StringChallenges.challenge4("Hello", 0));
        }

        @Test
        void nonLettersUnchanged() {
            assertEquals("Khoor, 123!", StringChallenges.challenge4("Hello, 123!", 3));
        }

        @Test
        void fullRotation26() {
            // shifting by 26 = same string!
            assertEquals("Hello", StringChallenges.challenge4("Hello", 26));
        }

        @Test
        void shiftOne() {
            assertEquals("BCD", StringChallenges.challenge4("ABC", 1));
        }

        @Test
        void emptyString() {
            assertEquals("", StringChallenges.challenge4("", 5));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge4(null, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Run-length encoding
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            assertEquals("a3b3c2d4e2",
                    StringChallenges.challenge5("aaabbbccddddee"));
        }

        @Test
        void allDifferent() {
            assertEquals("a1b1c1d1",
                    StringChallenges.challenge5("abcd"));
        }

        @Test
        void allSame() {
            assertEquals("a6", StringChallenges.challenge5("aaaaaa"));
        }

        @Test
        void mixed() {
            assertEquals("a2b1c3", StringChallenges.challenge5("aabccc"));
        }

        @Test
        void singleChar() {
            assertEquals("a1", StringChallenges.challenge5("a"));
        }

        @Test
        void twoSameChars() {
            assertEquals("a2", StringChallenges.challenge5("aa"));
        }

        @Test
        void emptyString() {
            assertEquals("", StringChallenges.challenge5(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Count non-overlapping substring occurrences
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            assertEquals(3, StringChallenges.challenge6("hello hello hello","hello"));
        }

        @Test
        void nonOverlapping() {
            // "aaaa" → "aa" at [0,2), then "aa" at [2,4) → 2
            assertEquals(2, StringChallenges.challenge6("aaaa","aa"));
        }

        @Test
        void noMatch() {
            assertEquals(0, StringChallenges.challenge6("hello","xyz"));
        }

        @Test
        void patternLongerThanText() {
            assertEquals(0, StringChallenges.challenge6("hi","hello"));
        }

        @Test
        void singleOccurrence() {
            assertEquals(1, StringChallenges.challenge6("abcdef","abc"));
        }

        @Test
        void twoOccurrences() {
            assertEquals(2, StringChallenges.challenge6("abcabc","abc"));
        }

        @Test
        void patternIsEmpty() {
            assertEquals(0, StringChallenges.challenge6("hello",""));
        }

        @Test
        void textEqualsPattern() {
            assertEquals(1, StringChallenges.challenge6("hello","hello"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge6(null,"hello"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Capitalize first letter of each word
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            assertEquals("Hello World Java",
                    StringChallenges.challenge7("hello world java"));
        }

        @Test
        void mixedCase() {
            assertEquals("The Quick Brown Fox",
                    StringChallenges.challenge7("the QUICK brown FOX"));
        }

        @Test
        void singleWord() {
            assertEquals("Hello", StringChallenges.challenge7("hello"));
        }

        @Test
        void alreadyCapitalized() {
            assertEquals("Hello World",
                    StringChallenges.challenge7("Hello World"));
        }

        @Test
        void allUppercase() {
            assertEquals("Hello World",
                    StringChallenges.challenge7("HELLO WORLD"));
        }

        @Test
        void singleCharWords() {
            assertEquals("I A B C",
                    StringChallenges.challenge7("i a b c"));
        }

        @Test
        void emptyString() {
            assertEquals("", StringChallenges.challenge7(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Remove duplicate characters, keep first occurrence
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            assertEquals("progamin",
                    StringChallenges.challenge8("programming"));
        }

        @Test
        void allDuplicates() {
            assertEquals("abc", StringChallenges.challenge8("aabbcc"));
        }

        @Test
        void interleavedDuplicates() {
            assertEquals("abc", StringChallenges.challenge8("abcabc"));
        }

        @Test
        void singleDuplicate() {
            assertEquals("helo", StringChallenges.challenge8("hello"));
        }

        @Test
        void noDuplicates() {
            assertEquals("abcde", StringChallenges.challenge8("abcde"));
        }

        @Test
        void singleChar() {
            assertEquals("a", StringChallenges.challenge8("a"));
        }

        @Test
        void allSameChar() {
            assertEquals("a", StringChallenges.challenge8("aaaa"));
        }

        @Test
        void emptyString() {
            assertEquals("", StringChallenges.challenge8(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Check if string is a pangram
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void classicPangram() {
            assertTrue(StringChallenges.challenge9(
                    "The quick brown fox jumps over the lazy dog"));
        }

        @Test
        void notPangram() {
            assertFalse(StringChallenges.challenge9("Hello World"));
        }

        @Test
        void anotherPangram() {
            assertTrue(StringChallenges.challenge9(
                    "Pack my box with five dozen liquor jugs"));
        }

        @Test
        void allLetters() {
            assertTrue(StringChallenges.challenge9("abcdefghijklmnopqrstuvwxyz"));
        }

        @Test
        void allLettersUppercase() {
            assertTrue(StringChallenges.challenge9("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }

        @Test
        void missingOneLetter() {
            // all letters except 'z'
            assertFalse(StringChallenges.challenge9("abcdefghijklmnopqrstuvwxy"));
        }

        @Test
        void emptyString() {
            assertFalse(StringChallenges.challenge9(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Longest common prefix
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            assertEquals("fl",
                    StringChallenges.challenge10(List.of("flower","flow","flight")));
        }

        @Test
        void noCommonPrefix() {
            assertEquals("",
                    StringChallenges.challenge10(List.of("dog","racecar","car")));
        }

        @Test
        void longerPrefix() {
            assertEquals("inter",
                    StringChallenges.challenge10(
                            List.of("interview","intercom","interest")));
        }

        @Test
        void singleWord() {
            assertEquals("apple",
                    StringChallenges.challenge10(List.of("apple")));
        }

        @Test
        void emptyStringInList() {
            assertEquals("",
                    StringChallenges.challenge10(List.of("","abc","abcd")));
        }

        @Test
        void allSameWord() {
            assertEquals("hello",
                    StringChallenges.challenge10(List.of("hello","hello","hello")));
        }

        @Test
        void commonPrefixIsFullWord() {
            // "flow" is prefix of "flower"
            assertEquals("flow",
                    StringChallenges.challenge10(List.of("flow","flower","flowing")));
        }

        @Test
        void emptyList() {
            assertEquals("",
                    StringChallenges.challenge10(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StringChallenges.challenge10(null));
        }
    }
}