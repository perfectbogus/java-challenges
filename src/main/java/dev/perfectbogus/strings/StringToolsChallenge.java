package dev.perfectbogus.strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 15 easy challenges about Strings.
 *
 * Unless the text says otherwise, the case matters ("a" and "A" are different characters).
 * Some texts can be very long (up to 1,000,000 characters), so avoid building results with += in a loop.
 */
public class StringToolsChallenge {

    // ==========================================================
    // CHALLENGE 1: capitalizeWords
    // ==========================================================
    // The words are separated by exactly one space (no spaces at the start or the end).
    // In every word, the first character becomes uppercase and all the other characters become lowercase.
    // Characters that are not letters do not change.
    //
    // "hello WORLD"  -> "Hello World"
    // "3d printer"   -> "3d Printer"
    // "mIxEd cAsE"   -> "Mixed Case"
    // ""             -> ""
    public static String capitalizeWords(String text) {
        if (text.isEmpty()) return "";
        return Arrays.stream(text.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // ==========================================================
    // CHALLENGE 2: countVowels
    // ==========================================================
    // Count the vowels of the text: a, e, i, o, u, in lowercase or uppercase. The letter y is not a vowel.
    //
    // "Hello World"  -> 3
    // "AEIOU"        -> 5
    // "rhythm"       -> 0
    // ""             -> 0
    public static int countVowels(String text) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        return (int) text.chars().mapToObj(c -> (char)c).filter(vowels::contains).count();
    }

    // ==========================================================
    // CHALLENGE 3: compress
    // ==========================================================
    // The text only has letters. Replace every group of the same letter (one after the other) with the letter
    // followed by the size of the group. The count is always written, even when it is 1.
    //
    // "aaabcc"        -> "a3b1c2"
    // "a"             -> "a1"
    // "aA"            -> "a1A1"
    // "aaaaaaaaaaaa"  -> "a12"
    // ""              -> ""
    public static String compress(String text) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < text.length()) {
            char c = text.charAt(i);
            int count = 1;
            int j = i + 1;
            while (j < text.length() && c == text.charAt(j)) {
                count++;
                j++;
            }
            sb.append(c).append(count);
            i = j;
        }
        return sb.toString();
    }

    // ==========================================================
    // CHALLENGE 4: isRotation
    // ==========================================================
    // Return true if b can be made by moving some characters from the start of a to its end
    // (moving none or all of them also counts).
    //
    // ("abcde", "cdeab")  -> true
    // ("abcde", "abced")  -> false
    // ("aab", "aba")      -> true
    // ("abc", "abcd")     -> false
    // ("", "")            -> true
    public static boolean isRotation(String a, String b) {
        return a.length() == b.length() && (a + a).contains(b);
    }

    // ==========================================================
    // CHALLENGE 5: longestWord
    // ==========================================================
    // The words are separated by exactly one space (no spaces at the start or the end).
    // Return the longest word. If several words have the longest length, return the first of them.
    // For the empty text, return the empty text.
    //
    // "the quick brown fox"  -> "quick"
    // "a bb ccc"             -> "ccc"
    // "hi, there!"           -> "there!"
    // ""                     -> ""
    public static String longestWord(String sentence) {
        return Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).get();
    }

    // ==========================================================
    // CHALLENGE 6: caesar
    // ==========================================================
    // Move every letter forward in the alphabet by shift places, going back to "a" after "z".
    // Uppercase letters stay uppercase and lowercase letters stay lowercase.
    // Other characters do not change. The shift can be negative or very big (up to 1,000,000,000 in size).
    // The alphabet is the 26 letters from a to z.
    //
    // ("abc", 1)             -> "bcd"
    // ("xyz", 3)             -> "abc"
    // ("Hello, World!", 3)   -> "Khoor, Zruog!"
    // ("abc", -1)            -> "zab"
    // ("abc", 27)            -> "bcd"
    public static String caesar(String text, int shift) {
        int move = Math.floorMod(shift, 26);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'a' && c <= 'z') {
                result.append((char) ('a' + (c - 'a' + move) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                result.append((char) ('A' + (c - 'A' + move) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // ==========================================================
    // CHALLENGE 7: toCamelCase
    // ==========================================================
    // The text has lowercase words (letters and digits) separated by exactly one underscore.
    // Return it in camel case: the first word stays the same and the other words start with an uppercase letter,
    // with no underscores.
    //
    // "hello_world"   -> "helloWorld"
    // "a_b_c"         -> "aBC"
    // "item_2_name"   -> "item2Name"
    // "hello"         -> "hello"
    // ""              -> ""
    public static String toCamelCase(String snake) {
        if (snake.isEmpty()) return "";
        String[] words = snake.split("_");
        StringBuilder sb = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            sb.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1));
        }
        return sb.toString();
    }

    // ==========================================================
    // CHALLENGE 8: toSnakeCase
    // ==========================================================
    // The text is in camel case: it starts with a lowercase letter and has only letters and digits.
    // Return it in snake case: every uppercase letter becomes an underscore followed by the same letter
    // in lowercase.
    //
    // "helloWorld"   -> "hello_world"
    // "aBC"          -> "a_b_c"
    // "item2Name"    -> "item2_name"
    // "hello"        -> "hello"
    // ""             -> ""
    public static String toSnakeCase(String camel) {
        String[] words = camel.split("(?<=[a-zA-Z0-9])(?=[A-Z])");
        StringBuilder sb = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            sb.append("_").append(words[i].toLowerCase());
        }
        return sb.toString();
    }

    // ==========================================================
    // CHALLENGE 9: longestCommonPrefix
    // ==========================================================
    // Return the longest text that every word starts with. If there is none, or there are no words,
    // return the empty text. Up to 100,000 words. Do not modify the array.
    //
    // [flower, flow, flight]  -> "fl"
    // [dog, racecar, car]     -> ""
    // [abc, ab, a]            -> "a"
    // [abc]                   -> "abc"
    // []                      -> ""
    public static String longestCommonPrefix(String[] words) {
        if (words.length == 0) return "";

        String prefix = words[0];
        for (String word : words) {
            int i = 0;
            while (i < prefix.length() && i < word.length() && prefix.charAt(i) == word.charAt(i)) {
                i++;
            }
            prefix = prefix.substring(0, i);
        }
        return prefix;
    }

    // ==========================================================
    // CHALLENGE 10: maskCard
    // ==========================================================
    // Replace every character with '*' except the last 4 characters. If the text has 4 characters or fewer,
    // return it without changes.
    //
    // "1234567890123456"  -> "************3456"
    // "12345"             -> "*2345"
    // "1234"              -> "1234"
    // ""                  -> ""
    public static String maskCard(String number) {
        if (number.length() <= 4) return number;

        return "*".repeat(number.length() - 4) + number.substring(number.length() - 4);
    }

    // ==========================================================
    // CHALLENGE 11: countOccurrences
    // ==========================================================
    // Count the positions of the text where the pattern appears. Overlapping appearances all count.
    // The pattern is not empty.
    //
    // ("aaaa", "aa")        -> 3
    // ("banana", "ana")     -> 2
    // ("hello", "l")        -> 2
    // ("hello", "xyz")      -> 0
    // ("Aa", "a")           -> 1
    public static int countOccurrences(String text, String pattern) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12: swapCase
    // ==========================================================
    // Every uppercase letter becomes lowercase and every lowercase letter becomes uppercase.
    // Other characters do not change.
    //
    // "Hello World 1"  -> "hELLO wORLD 1"
    // "aBc"            -> "AbC"
    // "123!?"          -> "123!?"
    // ""               -> ""
    public static String swapCase(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13: padLeft
    // ==========================================================
    // Add the pad character at the start of the text until its length is width.
    // If the text already has width characters or more, return it without changes.
    // 0 <= width <= 1,000,000.
    //
    // ("7", 3, '0')      -> "007"
    // ("abc", 5, ' ')    -> "  abc"
    // ("abcd", 2, '0')   -> "abcd"
    // ("", 3, 'x')       -> "xxx"
    public static String padLeft(String text, int width, char pad) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14: isPangram
    // ==========================================================
    // Return true if the text has every letter from a to z at least once, ignoring the case.
    // Other characters are allowed. Letters with accents or from other alphabets do not count.
    //
    // "The quick brown fox jumps over the lazy dog"  -> true
    // "hello"                                        -> false
    // "abcdefghijklmnopqrstuvwxy"                    -> false
    // ""                                             -> false
    public static boolean isPangram(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15: splitIntoChunks
    // ==========================================================
    // Split the text into parts of size characters, in order. The last part can be shorter.
    // size >= 1. For the empty text, return an empty array.
    //
    // ("abcdefg", 3)  -> [abc, def, g]
    // ("abcdef", 3)   -> [abc, def]
    // ("ab", 5)       -> [ab]
    // ("", 3)         -> []
    public static String[] splitIntoChunks(String text, int size) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
