package dev.perfectbogus.strings;

import java.util.*;
import java.util.stream.*;

public class StringChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Reverse each WORD in a sentence individually,
    // keeping the original word ORDER intact.
    //
    // Input:  "Hello World Java"
    // Output: "olleH dlroW avaJ"
    //
    // Input:  "I love streams"
    // Output: "I evol smaerts"
    //
    // Rules:
    // → Words are separated by single spaces
    // → Each word is reversed independently
    // → Word order stays the same
    // ─────────────────────────────────────────────────────────────
    public static String challenge1(String sentence) {
        if (sentence == null) throw new IllegalArgumentException("Sentence cannot be null");
        // TODO
        return Arrays.stream(sentence.split(" "))
                .map(w -> new StringBuilder(w).reverse())
                .collect(Collectors.joining(" "));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Count VOWELS and CONSONANTS in a string.
    // Vowels = a, e, i, o, u (case insensitive)
    // Consonants = letters that are NOT vowels (case insensitive)
    // Ignore spaces, digits and special characters.
    //
    // Return record CharCount(int vowels, int consonants)
    //
    // Input:  "Hello World"
    //   H=consonant, e=vowel, l=consonant, l=consonant, o=vowel,
    //   (space ignored), W=consonant, o=vowel, r=consonant,
    //   l=consonant, d=consonant
    // Output: CharCount(vowels=3, consonants=7)
    //
    // Input:  "Java 123!"
    //   J=consonant, a=vowel, v=consonant, a=vowel
    //   (1,2,3,! ignored)
    // Output: CharCount(vowels=2, consonants=2)
    // ─────────────────────────────────────────────────────────────
    record CharCount(int vowels, int consonants) {}

    public static CharCount challenge2(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        Set<Character> vowels = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        Map<Boolean, Long> map = s.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .collect(Collectors.partitioningBy(
                        vowels::contains,
                        Collectors.counting()
                ));

        return new CharCount( map.get(Boolean.TRUE).intValue(), map.get(Boolean.FALSE).intValue());
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Check if two strings are ANAGRAMS.
    // Two strings are anagrams if they contain the same characters
    // in any order (case insensitive). Ignore spaces.
    //
    // Input:  "listen", "silent"   → true
    // Input:  "hello",  "world"    → false
    // Input:  "Astronomer", "Moon starer" → true (ignore spaces)
    // Input:  "abc", "ab"          → false (different lengths)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge3(String s1, String s2) {
        if (s1 == null || s2 == null) throw new IllegalArgumentException("Inputs cannot be null");
        // TODO
        char[] c1 = s1.toLowerCase().replaceAll("\\s+", "").toCharArray();
        char[] c2 = s2.toLowerCase().replaceAll("\\s+", "").toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        return Arrays.equals(c1, c2);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Implement a CAESAR CIPHER — shift each LETTER by n positions.
    // Wrap around: after 'z' comes 'a', after 'Z' comes 'A'.
    // Non-letter characters stay UNCHANGED.
    //
    // Input:  "Hello, World!", shift=3
    // H→K, e→h, l→o, l→o, o→r, (,→,), ( → ), W→Z, o→r,
    // r→u, l→o, d→g, (!→!)
    // Output: "Khoor, Zruog!"
    //
    // Input:  "xyz", shift=3  → "abc"  (wraps around!)
    // Input:  "ABC", shift=1  → "BCD"
    // Input:  "Hello", shift=0 → "Hello" (no shift)
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println((char) ('a' + 1));
        System.out.println((char) 98);
        System.out.println((int) 'A');
    }
    public static String challenge4(String s, int shift) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        int normalizedShift = shift % 26;
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ((c - 'A' + normalizedShift) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ((c - 'a' + normalizedShift) % 26 + 'a'));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Run-Length Encoding — compress consecutive identical characters.
    // Format: character followed by its count.
    // If count = 1 → still include the count!
    //
    // Input:  "aaabbbccddddee"  → "a3b3c2d4e2"
    // Input:  "abcd"            → "a1b1c1d1"
    // Input:  "aaaaaa"          → "a6"
    // Input:  "aabccc"          → "a2b1c3"
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isEmpty()) return "";
        // TODO
        int count = 0;
        char prev = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == prev) {
                count++;
            } else {
                sb.append(prev).append(count);
                prev = c;
                count = 1;
            }
        }

        sb.append(prev).append(count);

        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Count how many times a substring appears in a string
    // NON-OVERLAPPING.
    //
    // Input:  text="hello hello hello", pattern="hello" → 3
    // Input:  text="aaaa", pattern="aa"                 → 2
    //   "aaaa": first "aa" at [0,2), next "aa" at [2,4) → 2
    //   NOT 3 (overlapping "aa" at [1,3) doesn't count!)
    // Input:  text="abcabc", pattern="abc"              → 2
    // Input:  text="hello", pattern="xyz"               → 0
    // ─────────────────────────────────────────────────────────────
    public static int challenge6(String text, String pattern) {
        if (text == null || pattern == null)
            throw new IllegalArgumentException("Inputs cannot be null");
        if (pattern.isEmpty()) return 0;
        // TODO — use indexOf(pattern, fromIndex) in a loop

        int count = 0;
        int i = 0;
        while (i < text.length()) {
            int j = text.indexOf(pattern, i);
            if (j == -1) break;
            count++;
            i = j + pattern.length();
        }

        return count;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Capitalize the FIRST LETTER of each word in a sentence.
    // Lowercase all other letters of each word.
    //
    // Input:  "hello world java"      → "Hello World Java"
    // Input:  "the QUICK brown FOX"   → "The Quick Brown Fox"
    // Input:  "i love java streams"   → "I Love Java Streams"
    //
    // Rules:
    // → Words separated by single spaces
    // → First letter of each word → uppercase
    // → Rest of word → lowercase
    // ─────────────────────────────────────────────────────────────
    public static String challenge7(String sentence) {
        if (sentence == null) throw new IllegalArgumentException("Sentence cannot be null");
        if (sentence.isBlank()) return "";
        // TODO
        return Arrays.stream(sentence.split(" ")).map(
                w -> w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Remove DUPLICATE characters from a string, keeping only the
    // FIRST OCCURRENCE of each character. Preserve original order.
    //
    // Input:  "programming"
    //   p-r-o-g-r-a-m-m-i-n-g
    //   keep:  p,r,o,g,a,m,i,n  (skip 2nd r, 2nd m, 2nd g)
    // Output: "programing"
    //
    // Input:  "aabbcc"  → "abc"
    // Input:  "abcabc"  → "abc"
    // Input:  "hello"   → "helo"
    // ─────────────────────────────────────────────────────────────
    public static String challenge8(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Check if a string is a PANGRAM — contains every letter of the
    // alphabet at least once (case insensitive). Ignore non-letters.
    //
    // Input:  "The quick brown fox jumps over the lazy dog" → true
    // Input:  "Hello World"                                  → false
    // Input:  "Pack my box with five dozen liquor jugs"      → true
    // Input:  "abcdefghijklmnopqrstuvwxyz"                   → true
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge9(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Find the LONGEST COMMON PREFIX among a list of strings.
    // Return empty string "" if no common prefix exists.
    //
    // Input:  ["flower","flow","flight"]   → "fl"
    // Input:  ["dog","racecar","car"]      → ""
    // Input:  ["interview","intercom","interest"] → "inter"
    // Input:  ["apple"]                   → "apple" (single word)
    // Input:  ["","abc","abcd"]           → "" (empty string prefix)
    //
    // Approach: start with first word as prefix candidate
    // then trim prefix until each word starts with it
    // ─────────────────────────────────────────────────────────────
    public static String challenge10(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        if (words.isEmpty()) return "";
        // TODO — start with words.get(0) as prefix
        //        for each word: while !word.startsWith(prefix) → trim one char from end!
        return "";
    }
}