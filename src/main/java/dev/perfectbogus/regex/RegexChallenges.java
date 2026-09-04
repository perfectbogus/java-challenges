package dev.perfectbogus.regex;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class RegexChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — String.matches()
    // Validate an email address using regex.
    // A valid email:
    // → one or more word chars, dots, hyphens or plus before @
    //   [a-zA-Z0-9._%+-]+
    // → @ symbol
    // → one or more word chars or dots after @
    //   [a-zA-Z0-9.-]+
    // → a dot
    // → two or more letters for the domain extension
    //   [a-zA-Z]{2,}
    //
    // Input:  "alice@example.com"  → true
    // Input:  "alice@"             → false
    // Input:  "alice.bob@mail.org" → true
    // Input:  "no-at-sign"         → false
    // Input:  "a@b.c"              → false (extension too short!)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge1(String email) {
        if (email == null) return false;
        return email.matches("([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+).([a-zA-Z]{2,})");
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Pattern + Matcher + find()
    // Extract ALL integers from a string (positive and negative).
    // Return them as List<Integer> in order of appearance.
    // Use find() in a loop to collect all matches.
    //
    // Input:  "abc 123 def -45 ghi 0 jkl 999"
    // Output: [123, -45, 0, 999]
    //
    // Input:  "no numbers here"
    // Output: []
    //
    // Input:  "Price: $200 Discount: -30 Total: 170"
    // Output: [200, -30, 170]
    //
    // Pattern: -?\\d+
    //   -?   → optional minus sign
    //   \\d+ → one or more digits
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge2(String text) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(text);

        List<Integer> results = new ArrayList<>();
        while (m.find()) {
            int i = Integer.parseInt(m.group());
            results.add(i);
        }

        return results;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — replaceAll()
    // Normalize whitespace in a string:
    // → Replace ALL sequences of whitespace (spaces, tabs, newlines)
    //   with a SINGLE space
    // → Also strip leading and trailing whitespace
    //
    // Input:  "hello   world"          → "hello world"
    // Input:  "  hello\t\tworld\n  "   → "hello world"
    // Input:  "one  two   three"       → "one two three"
    // Input:  "already normal"         → "already normal"
    //
    // Key operations: replaceAll("\\s+", " ") then strip()
    // ─────────────────────────────────────────────────────────────
    public static String challenge3(String text) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        return text.replaceAll("\\s+", " ").strip();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Capturing groups with Matcher
    // Parse a date string in format "YYYY-MM-DD" and return
    // record DateParts(int year, int month, int day).
    // Use capturing groups to extract each part.
    // Throw IllegalArgumentException if format is invalid.
    //
    // Pattern: (\\d{4})-(\\d{2})-(\\d{2})
    //   group(1) = year
    //   group(2) = month
    //   group(3) = day
    //
    // Input:  "2024-03-15"  → DateParts(2024, 3, 15)
    // Input:  "1999-12-31"  → DateParts(1999, 12, 31)
    // Input:  "not-a-date"  → throws IllegalArgumentException
    // Input:  "24-3-15"     → throws IllegalArgumentException
    // ─────────────────────────────────────────────────────────────
    record DateParts(int year, int month, int day) {}

    public static DateParts challenge4(String date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        Pattern p = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher m = p.matcher(date);
        if (!m.matches()) throw new IllegalArgumentException();
        int year = Integer.parseInt(m.group(1));
        int month = Integer.parseInt(m.group(2));
        int day = Integer.parseInt(m.group(3));
        return new DateParts(year, month, day);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — replaceAll() with backreference
    // Convert camelCase strings to snake_case.
    // Insert an underscore before each uppercase letter
    // then lowercase the whole string.
    //
    // Backreference in replacement:
    //   replaceAll("([A-Z])", "_$1")
    //   → $1 refers to group 1 (the captured uppercase letter)
    //
    // Input:  "camelCase"           → "camel_case"
    // Input:  "myVariableName"      → "my_variable_name"
    // Input:  "getHTTPResponse"     → "get_h_t_t_p_response"
    // Input:  "alreadylower"        → "alreadylower"
    // Input:  "HTMLParser"          → "_h_t_m_l_parser"
    //
    // Rules:
    // → replace each uppercase letter with _lowercase
    // → lowercase entire result
    // → do NOT add leading underscore if first char is uppercase
    //   (strip leading _ if present!)
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(String camel) {
        if (camel == null) throw new IllegalArgumentException("Input cannot be null");
        return camel.replaceAll("([A-Z])", "_$1").replaceAll("^_", "").toLowerCase();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — matches() with complex pattern
    // Validate an IPv4 address.
    // A valid IPv4:
    // → exactly four groups of 1-3 digits separated by dots
    // → each group: 0-255
    //   (0|[1-9]\\d?|[1-9]\\d{2}|1\\d{2}|2[0-4]\\d|25[0-5])
    //
    // Simple pattern approach: "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"
    // Then validate each octet is 0-255 programmatically!
    //
    // Input:  "192.168.1.1"   → true
    // Input:  "255.255.255.0" → true
    // Input:  "256.0.0.1"     → false (256 > 255!)
    // Input:  "0.0.0.0"       → true
    // Input:  "192.168.1"     → false (only 3 groups!)
    // Input:  "abc.def.ghi.jkl"→ false (not digits!)
    // Input:  "1.2.3.4.5"     → false (5 groups!)
    //
    // Rules:
    // → use regex to check format first
    // → then split and validate each octet 0-255
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge6(String ip) {
        if (ip == null) return false;
        Pattern p = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        Matcher m = p.matcher(ip);

        if (!m.matches()) {
            return false;
        }

        String[] split = ip.split("\\.");

        return Arrays.stream(split).mapToInt(Integer::parseInt).allMatch(i -> i >= 0 && i < 256 );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — find() collecting matches
    // Extract all HASHTAGS from a text.
    // A hashtag starts with # followed by one or more word characters.
    // Return hashtags WITHOUT the # symbol, sorted alphabetically.
    //
    // Pattern: #(\\w+)
    //   group(1) = word after #
    //
    // Input:  "I love #Java and #Coding every #day"
    // Output: ["Coding","Java","day"]  ← sorted alpha!
    //
    // Input:  "no hashtags here"
    // Output: []
    //
    // Input:  "#hello #world #hello"   ← duplicates kept!
    // Output: ["hello","hello","world"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge7(String text) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — replaceAll() masking
    // Mask a credit card number keeping ONLY the last 4 digits visible.
    // Replace all digits EXCEPT the last 4 with '*'.
    // Preserve spaces and dashes in their original positions.
    //
    // Input:  "1234 5678 9012 3456" → "**** **** **** 3456"
    // Input:  "1234-5678-9012-3456" → "****-****-****-3456"
    // Input:  "1234567890123456"    → "************3456"
    //
    // Approach:
    // → count total digits → keep last 4 → replace rest with *
    // → use replaceAll with a counter or clever pattern
    //
    // Key: replace each digit that is NOT one of the last 4 digits
    //   Step 1 — count total digits
    //   Step 2 — replace first (total-4) digits with *
    //             using a Pattern + Matcher + StringBuffer/StringBuilder
    //             or replaceAll with limit
    // ─────────────────────────────────────────────────────────────
    public static String challenge8(String cardNumber) {
        if (cardNumber == null)
            throw new IllegalArgumentException("Card number cannot be null");
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — split() with regex
    // Split a string by ANY punctuation character followed by
    // optional spaces, returning only NON-EMPTY words.
    //
    // Punctuation: [.,!?;:]
    // Pattern: "[.,!?;:]\\s*"
    //
    // Input:  "Hello, World! How are you?"
    // Output: ["Hello","World","How","are","you"]
    //
    // Input:  "one; two: three, four"
    // Output: ["one","two","three","four"]
    //
    // Input:  "no punctuation"
    // Output: ["no punctuation"]
    //
    // Rules:
    // → split by punctuation + optional whitespace
    // → filter out empty strings
    // → return as List<String> in order
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge9(String text) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Matcher.results() Java 9+
    // Extract all words that contain BOTH at least one vowel
    // AND at least one consonant (mixed words).
    // Use Matcher.results() to stream matches.
    //
    // A "word" = sequence of only letters: [a-zA-Z]+
    // Mixed = has at least one [aeiouAEIOU] AND at least one non-vowel letter
    //
    // Input:  "sky aeiou Hello 123 rhythm try"
    //   sky    → s,k,y (no vowel!) ← no!
    //   aeiou  → all vowels ← no!
    //   Hello  → vowels: e,o + consonants: H,l,l ← YES!
    //   123    → not a word
    //   rhythm → r,h,y,t,h,m (no vowel) ← no!
    //   try    → t,r,y (no vowel) ← no!
    //
    // Output: ["Hello"]
    //
    // Input:  "cat dog fly gym"
    //   cat → c,a,t ← YES! (has vowel a, consonants c,t)
    //   dog → d,o,g ← YES!
    //   fly → no vowel ← no
    //   gym → no vowel ← no
    // Output: ["cat","dog"]
    //
    // Key operations:
    //   Pattern.compile("[a-zA-Z]+").matcher(text).results()
    //   .map(MatchResult::group)
    //   .filter(word -> hasVowel(word) && hasConsonant(word))
    //   .toList()
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge10(String text) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        return new ArrayList<>();
    }
}