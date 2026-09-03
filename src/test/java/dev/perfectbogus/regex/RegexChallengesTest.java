package dev.perfectbogus.regex;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RegexChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Email validation with matches()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void validSimpleEmail() {
            assertTrue(RegexChallenges.challenge1("alice@example.com"));
        }

        @Test
        void validEmailWithDots() {
            assertTrue(RegexChallenges.challenge1("alice.bob@mail.org"));
        }

        @Test
        void validEmailWithPlus() {
            assertTrue(RegexChallenges.challenge1("alice+tag@example.com"));
        }

        @Test
        void validEmailWithHyphen() {
            assertTrue(RegexChallenges.challenge1("alice-bob@example.co.uk"));
        }

        @Test
        void missingAtSign() {
            assertFalse(RegexChallenges.challenge1("no-at-sign"));
        }

        @Test
        void missingDomain() {
            assertFalse(RegexChallenges.challenge1("alice@"));
        }

        @Test
        void extensionTooShort() {
            assertFalse(RegexChallenges.challenge1("a@b.c")); // 1 char extension
        }

        @Test
        void validTwoCharExtension() {
            assertTrue(RegexChallenges.challenge1("alice@example.io"));
        }

        @Test
        void emptyString() {
            assertFalse(RegexChallenges.challenge1(""));
        }

        @Test
        void nullInput() {
            assertFalse(RegexChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Extract integers with find()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            assertEquals(List.of(123, -45, 0, 999),
                    RegexChallenges.challenge2("abc 123 def -45 ghi 0 jkl 999"));
        }

        @Test
        void noNumbers() {
            assertTrue(RegexChallenges.challenge2("no numbers here").isEmpty());
        }

        @Test
        void priceExample() {
            assertEquals(List.of(200, -30, 170),
                    RegexChallenges.challenge2("Price: $200 Discount: -30 Total: 170"));
        }

        @Test
        void singleNumber() {
            assertEquals(List.of(42),
                    RegexChallenges.challenge2("answer is 42"));
        }

        @Test
        void negativeOnly() {
            assertEquals(List.of(-5),
                    RegexChallenges.challenge2("temp is -5 degrees"));
        }

        @Test
        void emptyString() {
            assertTrue(RegexChallenges.challenge2("").isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Normalize whitespace with replaceAll()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void multipleSpaces() {
            assertEquals("hello world",
                    RegexChallenges.challenge3("hello   world"));
        }

        @Test
        void tabs() {
            assertEquals("hello world",
                    RegexChallenges.challenge3("hello\t\tworld"));
        }

        @Test
        void leadingAndTrailing() {
            assertEquals("hello world",
                    RegexChallenges.challenge3("  hello\t\tworld\n  "));
        }

        @Test
        void alreadyNormal() {
            assertEquals("already normal",
                    RegexChallenges.challenge3("already normal"));
        }

        @Test
        void multipleWords() {
            assertEquals("one two three",
                    RegexChallenges.challenge3("one  two   three"));
        }

        @Test
        void newlinesBetweenWords() {
            assertEquals("line one line two",
                    RegexChallenges.challenge3("line one\nline two"));
        }

        @Test
        void emptyString() {
            assertEquals("", RegexChallenges.challenge3(""));
        }

        @Test
        void onlyWhitespace() {
            assertEquals("", RegexChallenges.challenge3("   \t\n   "));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Capturing groups to parse date
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicDate() {
            RegexChallenges.DateParts result = RegexChallenges.challenge4("2024-03-15");
            assertEquals(2024, result.year());
            assertEquals(3,    result.month());
            assertEquals(15,   result.day());
        }

        @Test
        void endOfYear() {
            RegexChallenges.DateParts result = RegexChallenges.challenge4("1999-12-31");
            assertEquals(1999, result.year());
            assertEquals(12,   result.month());
            assertEquals(31,   result.day());
        }

        @Test
        void singleDigitMonthDay() {
            RegexChallenges.DateParts result = RegexChallenges.challenge4("2020-01-05");
            assertEquals(2020, result.year());
            assertEquals(1,    result.month());
            assertEquals(5,    result.day());
        }

        @Test
        void invalidFormatThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge4("not-a-date"));
        }

        @Test
        void shortYearThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge4("24-03-15"));
        }

        @Test
        void nullThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge4(null));
        }

        @Test
        void wrongSeparatorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge4("2024/03/15"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — camelCase to snake_case with backreference
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCamel() {
            assertEquals("camel_case",
                    RegexChallenges.challenge5("camelCase"));
        }

        @Test
        void multipleWords() {
            assertEquals("my_variable_name",
                    RegexChallenges.challenge5("myVariableName"));
        }

        @Test
        void alreadyLower() {
            assertEquals("alreadylower",
                    RegexChallenges.challenge5("alreadylower"));
        }

        @Test
        void startsWithUppercase() {
            assertEquals("html_parser",
                    RegexChallenges.challenge5("HTMLParser"));
        }

        @Test
        void singleWord() {
            assertEquals("hello",
                    RegexChallenges.challenge5("hello"));
        }

        @Test
        void singleUppercaseWord() {
            assertEquals("hello",
                    RegexChallenges.challenge5("Hello"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — IPv4 validation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void validPrivateIP() {
            assertTrue(RegexChallenges.challenge6("192.168.1.1"));
        }

        @Test
        void validSubnetMask() {
            assertTrue(RegexChallenges.challenge6("255.255.255.0"));
        }

        @Test
        void allZeros() {
            assertTrue(RegexChallenges.challenge6("0.0.0.0"));
        }

        @Test
        void maxValid() {
            assertTrue(RegexChallenges.challenge6("255.255.255.255"));
        }

        @Test
        void octetTooLarge() {
            assertFalse(RegexChallenges.challenge6("256.0.0.1"));
        }

        @Test
        void octetTooLargeMiddle() {
            assertFalse(RegexChallenges.challenge6("192.168.256.1"));
        }

        @Test
        void onlyThreeGroups() {
            assertFalse(RegexChallenges.challenge6("192.168.1"));
        }

        @Test
        void fiveGroups() {
            assertFalse(RegexChallenges.challenge6("1.2.3.4.5"));
        }

        @Test
        void letters() {
            assertFalse(RegexChallenges.challenge6("abc.def.ghi.jkl"));
        }

        @Test
        void nullInput() {
            assertFalse(RegexChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Extract hashtags with find()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<String> result = RegexChallenges.challenge7(
                    "I love #Java and #Coding every #day");
            assertEquals(List.of("Coding","Java","day"), result); // sorted!
        }

        @Test
        void noHashtags() {
            assertTrue(RegexChallenges.challenge7("no hashtags here").isEmpty());
        }

        @Test
        void duplicatesKept() {
            List<String> result = RegexChallenges.challenge7("#hello #world #hello");
            assertEquals(List.of("hello","hello","world"), result);
        }

        @Test
        void hashtagAtStart() {
            List<String> result = RegexChallenges.challenge7("#first word");
            assertEquals(List.of("first"), result);
        }

        @Test
        void hashtagAtEnd() {
            List<String> result = RegexChallenges.challenge7("word #last");
            assertEquals(List.of("last"), result);
        }

        @Test
        void hashtagWithNumbers() {
            List<String> result = RegexChallenges.challenge7("#Java21");
            assertEquals(List.of("Java21"), result);
        }

        @Test
        void emptyString() {
            assertTrue(RegexChallenges.challenge7("").isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Mask credit card number
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void withSpaces() {
            assertEquals("**** **** **** 3456",
                    RegexChallenges.challenge8("1234 5678 9012 3456"));
        }

        @Test
        void withDashes() {
            assertEquals("****-****-****-3456",
                    RegexChallenges.challenge8("1234-5678-9012-3456"));
        }

        @Test
        void noSeparators() {
            assertEquals("************3456",
                    RegexChallenges.challenge8("1234567890123456"));
        }

        @Test
        void exactlyFourDigits() {
            assertEquals("1234",
                    RegexChallenges.challenge8("1234")); // no masking needed!
        }

        @Test
        void fiveDigits() {
            assertEquals("*2345",
                    RegexChallenges.challenge8("12345")); // mask first 1
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — split() by punctuation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            assertEquals(List.of("Hello","World","How","are","you"),
                    RegexChallenges.challenge9("Hello, World! How are you?"));
        }

        @Test
        void semicolonAndColon() {
            assertEquals(List.of("one","two","three","four"),
                    RegexChallenges.challenge9("one; two: three, four"));
        }

        @Test
        void noPunctuation() {
            assertEquals(List.of("no punctuation"),
                    RegexChallenges.challenge9("no punctuation"));
        }

        @Test
        void multipleSpacesAfterPunctuation() {
            assertEquals(List.of("hello","world"),
                    RegexChallenges.challenge9("hello,   world"));
        }

        @Test
        void emptyString() {
            assertTrue(RegexChallenges.challenge9("").isEmpty() ||
                    RegexChallenges.challenge9("").equals(List.of("")));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Matcher.results() to find mixed words
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            assertEquals(List.of("Hello"),
                    RegexChallenges.challenge10("sky aeiou Hello 123 rhythm try"));
        }

        @Test
        void mixedWords() {
            assertEquals(List.of("cat","dog"),
                    RegexChallenges.challenge10("cat dog fly gym"));
        }

        @Test
        void noMixedWords() {
            // "sky","cry","gym" = no vowels; "aeiou" = all vowels
            assertTrue(RegexChallenges.challenge10("sky cry gym aeiou").isEmpty());
        }

        @Test
        void numbersIgnored() {
            assertEquals(List.of("hello"),
                    RegexChallenges.challenge10("hello 123 456"));
        }

        @Test
        void caseInsensitiveVowels() {
            // "HELLO" has vowels E,O
            assertEquals(List.of("HELLO"),
                    RegexChallenges.challenge10("HELLO GYM"));
        }

        @Test
        void emptyString() {
            assertTrue(RegexChallenges.challenge10("").isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> RegexChallenges.challenge10(null));
        }
    }
}