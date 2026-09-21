package dev.perfectbogus.strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringIntermediateChallenge {

    // CHALLENGE 1
    // Returns a new string with the words of sentence in reverse order,
    // separated by a single space. Leading, trailing, and repeated
    // whitespace between words is collapsed away. Empty or all-whitespace
    // input results in an empty string.
    public static String reverseWords(String sentence) {
        String[] split = sentence.trim().replaceAll("\\s+", " ").split(" ");
        String[] result = new String[split.length];

        int r = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            result[r++] = split[i];
        }

        return String.join(" ", result);
    }

    // CHALLENGE 2
    // Returns whether s reads the same forwards and backwards, ignoring
    // case and ignoring any character that is not a letter or digit.
    public static boolean isPalindrome(String s) {
        String norm = s.replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
        int l = 0;
        int r = norm.length() - 1;
        while (l <= r) {
            if (norm.charAt(l++) != norm.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    // CHALLENGE 3
    // Returns whether a and b are anagrams of each other, ignoring case
    // and ignoring spaces.
    public static boolean isAnagram(String a, String b) {
        String aNorm = a.replaceAll("\\s+", "").toLowerCase();
        String bNorm = b.replaceAll("\\s+", "").toLowerCase();
        char[] aChars = aNorm.toCharArray();
        Arrays.sort(aChars);
        char[] bChars = bNorm.toCharArray();
        Arrays.sort(bChars);
        return Arrays.equals(aChars, bChars);
    }

    // CHALLENGE 4
    // Returns how many vowels (a, e, i, o, u) appear in s, counting both
    // uppercase and lowercase.
    public static int countVowels(String s) {
        int[] alpha = new int[128];

        for (char c : s.toCharArray()) {
            alpha[c]++;
        }

        int lower = alpha['a'] + alpha['e'] + alpha['i'] + alpha['o'] + alpha['u'];
        int upper = alpha['A'] + alpha['E'] + alpha['I'] + alpha['O'] + alpha['U'];
        return lower + upper;
    }

    // CHALLENGE 5
    // Returns s with the first letter of every space-separated word
    // uppercased and every other letter in that word lowercased.
    public static String capitalizeWords(String s) {
        if (s.isBlank()) return "";

        String[] split = s.toLowerCase().split(" ");
        String[] results = new String[split.length];

        for (int i = 0; i < split.length; i++) {
            char[] wChars = split[i].toCharArray();
            results[i] = Character.toUpperCase(wChars[0]) + String.valueOf(wChars, 1, wChars.length - 1);
        }

        return String.join(" ", results);
    }

    // CHALLENGE 6
    // Returns how many non-overlapping times target appears in text.
    // target is guaranteed to be non-empty.
    public static int countOccurrences(String text, String target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Compresses s using run-length encoding, replacing each run of
    // consecutive identical characters with the character followed by
    // the run's length (for example "aaabbbcc" becomes "a3b3c2"). If the
    // compressed form is not strictly shorter than s, returns s
    // unchanged instead.
    public static String compressString(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns whether s2 is a rotation of s1 (i.e. s2 can be formed by
    // moving some prefix of s1 to its end). Strings of different lengths
    // are never rotations of each other.
    public static boolean isRotation(String s1, String s2) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Masks the portion of email before '@', keeping the first and last
    // character visible and replacing every character between them with
    // '*'. The domain portion (including '@') is left unchanged. If the
    // portion before '@' has 2 or fewer characters, it is returned
    // unchanged.
    public static String maskEmail(String email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns number formatted with a comma as a thousands separator
    // (for example 1234567 becomes "1,234,567").
    public static String formatWithThousandsSeparator(long number) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Joins every non-blank string in parts using delimiter, skipping
    // any element that is blank (empty or containing only whitespace).
    public static String joinNonBlank(List<String> parts, String delimiter) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns pattern repeated times times, back to back with no
    // separator.
    public static String repeatPattern(String pattern, int times) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns how many uppercase letters appear in s.
    public static int countUppercaseLetters(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns every starting index at which target occurs within text,
    // in ascending order. Occurrences may overlap. target is guaranteed
    // to be non-empty.
    public static List<Integer> findAllIndexesOf(String text, String target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Converts camelCase to snake_case by inserting an underscore before
    // every uppercase letter (except when it is the first character) and
    // lowercasing the entire result. For example "thisIsATest" becomes
    // "this_is_a_test".
    public static String toSnakeCase(String camelCase) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
