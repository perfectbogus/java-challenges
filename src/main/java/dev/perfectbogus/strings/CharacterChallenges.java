package dev.perfectbogus.strings;

import java.util.*;
import java.util.stream.*;

public class CharacterChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Character.isLetter() + Character.isDigit()
    //
    // Given a string, classify each character and return a Map
    // with three counts:
    // "LETTER"  → count of letter characters
    // "DIGIT"   → count of digit characters
    // "OTHER"   → count of everything else (spaces, punctuation, etc.)
    //
    // Input:  "Hello World 123!"
    // Output: {"LETTER"=10, "DIGIT"=3, "OTHER"=3}
    //          H,e,l,l,o,W,o,r,l,d = 10 letters
    //          1,2,3               = 3 digits
    //          (space),(space),(!) = 3 others
    //
    // Key operations: Character.isLetter(c), Character.isDigit(c)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Long> challenge1(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        long letterCount = 0;
        long digitCount = 0;
        long otherCount = 0;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            } else {
                otherCount++;
            }
        }

        return Map.of("LETTER", letterCount, "DIGIT", digitCount, "OTHER", otherCount);
    }

    public static Map<String, Long> challenge1_2(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot bu null");

        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> Character.isLetter(c) ? "LETTER"
                                : Character.isDigit(c) ? "DIGIT"
                                : "OTHER",
                        Collectors.counting()
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Character.isUpperCase() + Character.isLowerCase()
    //               + Character.toUpperCase() + Character.toLowerCase()
    //
    // Toggle the case of each letter in the string:
    // → UPPERCASE letters → lowercase
    // → lowercase letters → UPPERCASE
    // → non-letter characters → unchanged
    //
    // Input:  "Hello World! 123"
    // Output: "hELLO wORLD! 123"
    //
    // Input:  "Java IS Fun"
    // Output: "jAVA is fUN"
    //
    // Key operations: Character.isUpperCase(c) → toLowerCase(c)
    //                 Character.isLowerCase(c) → toUpperCase(c)
    // ─────────────────────────────────────────────────────────────
    public static String challenge2(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Character.getNumericValue()
    //
    // Given a string containing digits and other characters,
    // sum ALL digit values using Character.getNumericValue().
    // Non-digit characters contribute 0 to the sum.
    //
    // Character.getNumericValue('5') → 5
    // Character.getNumericValue('a') → 10 (hex value!)
    // Character.getNumericValue('Z') → 35
    // Character.getNumericValue('!') → -1 (not a digit)
    //
    // For this challenge ONLY count '0'-'9' digit characters!
    // Use Character.isDigit() to filter before getNumericValue().
    //
    // Input:  "abc 123 def 456"
    //   digits: 1,2,3,4,5,6 → sum=21
    // Output: 21
    //
    // Input:  "no digits here!"
    // Output: 0
    //
    // Key operations: Character.isDigit(c), Character.getNumericValue(c)
    // ─────────────────────────────────────────────────────────────
    public static int challenge3(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Character.isLetterOrDigit()
    //
    // Check if a string is a VALID JAVA IDENTIFIER:
    // → Must NOT be empty
    // → First character must be a letter or underscore '_' or dollar '$'
    // → Remaining characters must be letters, digits, '_' or '$'
    //
    // Use Character.isLetter() for first char check.
    // Use Character.isLetterOrDigit() for remaining chars.
    //
    // Input:  "myVariable"    → true
    // Input:  "my_var123"     → true
    // Input:  "$price"        → true
    // Input:  "123abc"        → false (starts with digit!)
    // Input:  "my-variable"   → false (hyphen not allowed!)
    // Input:  ""              → false (empty!)
    //
    // Key operations: Character.isLetter(c), Character.isLetterOrDigit(c)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge4(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Character.forDigit(digit, radix)
    //
    // Convert a decimal integer to its HEXADECIMAL string representation
    // using Character.forDigit(digit, radix).
    //
    // Character.forDigit(10, 16) → 'a'
    // Character.forDigit(15, 16) → 'f'
    // Character.forDigit(9,  16) → '9'
    //
    // Build the hex string manually using % 16 and / 16 operations.
    // Return lowercase hex string.
    //
    // Input:  255  → "ff"
    // Input:  16   → "10"
    // Input:  0    → "0"
    // Input:  256  → "100"
    //
    // Key operation: Character.forDigit(n % 16, 16) per digit
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        // TODO — use Character.forDigit(remainder, 16) in a loop
        //        prepend each hex digit to StringBuilder
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Character.digit(char, radix)
    //
    // Given a HEXADECIMAL string, convert it to its decimal value
    // using Character.digit(char, radix).
    //
    // Character.digit('f', 16) → 15
    // Character.digit('a', 16) → 10
    // Character.digit('9', 16) → 9
    // Character.digit('z', 16) → -1 (invalid hex digit!)
    //
    // Process each character from left to right:
    //   result = result * 16 + Character.digit(c, 16)
    //
    // Input:  "ff"  → 255
    // Input:  "10"  → 16
    // Input:  "1a3" → 419   (1*256 + 10*16 + 3)
    // Input:  "0"   → 0
    //
    // Throw IllegalArgumentException if hex string contains invalid chars.
    //
    // Key operation: Character.digit(c, 16)
    // ─────────────────────────────────────────────────────────────
    public static int challenge6(String hex) {
        if (hex == null || hex.isEmpty())
            throw new IllegalArgumentException("Hex string cannot be null or empty");
        // TODO — for each char: int d = Character.digit(c, 16)
        //        if d == -1 → throw IllegalArgumentException
        //        result = result * 16 + d
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Character.isWhitespace()
    //
    // Count the number of WORDS in a string using Character.isWhitespace().
    // A word is a sequence of non-whitespace characters.
    // Multiple consecutive whitespace characters count as ONE separator.
    //
    // Input:  "hello world java"      → 3
    // Input:  "  hello   world  "     → 2  (leading/trailing spaces ignored)
    // Input:  "one"                   → 1
    // Input:  "   "                   → 0  (only spaces)
    // Input:  ""                      → 0
    //
    // Key operation: Character.isWhitespace(c)
    //                track inWord boolean flag!
    // ─────────────────────────────────────────────────────────────
    public static int challenge7(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — track boolean inWord
        //        when !isWhitespace and !inWord → new word! count++, inWord=true
        //        when isWhitespace → inWord=false
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Character.compare()
    //
    // Given a list of characters and a TARGET character, find the
    // character in the list that is CLOSEST to the target
    // (by absolute difference in char values).
    // For ties → return the one that comes FIRST alphabetically.
    // Return Optional<Character>.
    //
    // Character.compare('a','b') → negative (a < b)
    // Math.abs(Character.compare('a','c')) → 2  (distance!)
    //
    // Input:  chars=['a','f','z','m'], target='h'
    //   distance: a=7, f=2, z=18, m=5
    //   Closest: f (distance=2)
    // Output: Optional['f']
    //
    // Input:  chars=['a','n'], target='h'
    //   distance: a=7, n=6
    //   Closest: n (distance=6)
    // Output: Optional['n']
    //
    // Key operation: Math.abs(Character.compare(c, target))
    // ─────────────────────────────────────────────────────────────
    public static Optional<Character> challenge8(List<Character> chars, char target) {
        if (chars == null) throw new IllegalArgumentException("Chars cannot be null");
        // TODO — compute distance using Math.abs(Character.compare(c, target))
        //        find minimum distance, handle ties with natural order
        return Optional.empty();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Character.isAlphabetic() + Character.toLowerCase()
    //
    // Given a string, extract ONLY alphabetic characters,
    // convert them all to lowercase, and return the result.
    // Then check if the resulting string is a PALINDROME.
    //
    // Character.isAlphabetic(c) → true for letters (broader than isLetter!)
    //
    // Input:  "A man a plan a canal Panama"
    //   alphabetic only → "amanaplanacanalpanama"
    //   lowercase       → "amanaplanacanalpanama"
    //   palindrome?     → YES! ✓
    // Output: true
    //
    // Input:  "race a car"
    //   alphabetic only → "raceacar"
    //   palindrome?     → NO ✗
    // Output: false
    //
    // Key operations: Character.isAlphabetic(c), Character.toLowerCase(c)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge9(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — filter isAlphabetic, toLowerCase, check palindrome
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — All Character methods combined
    //
    // Validate a PASSWORD using multiple Character rules.
    // Return a record PasswordResult(boolean valid, List<String> violations)
    //
    // Rules (check ALL and collect violations!):
    // → At least 8 characters total
    // → At least 1 UPPERCASE letter     (Character.isUpperCase)
    // → At least 1 LOWERCASE letter     (Character.isLowerCase)
    // → At least 1 DIGIT                (Character.isDigit)
    // → At least 1 special character    (!isLetterOrDigit)
    // → NO whitespace allowed           (Character.isWhitespace)
    //
    // Violation messages (EXACTLY as shown):
    // "Must be at least 8 characters"
    // "Must contain at least 1 uppercase letter"
    // "Must contain at least 1 lowercase letter"
    // "Must contain at least 1 digit"
    // "Must contain at least 1 special character"
    // "Must not contain whitespace"
    //
    // Input:  "Hello1!"
    //   length=7 → violation!
    // Output: PasswordResult(valid=false, violations=["Must be at least 8 characters"])
    //
    // Input:  "Hello World1!"
    //   has whitespace → violation!
    // Output: PasswordResult(valid=false, violations=["Must not contain whitespace"])
    //
    // Input:  "Hello1!World"
    // Output: PasswordResult(valid=true, violations=[])
    //
    // Key operations: isUpperCase, isLowerCase, isDigit, isLetterOrDigit, isWhitespace
    // ─────────────────────────────────────────────────────────────
    record PasswordResult(boolean valid, List<String> violations) {}

    public static PasswordResult challenge10(String password) {
        if (password == null) throw new IllegalArgumentException("Password cannot be null");
        // TODO — check each rule, collect violations into list
        //        valid = violations.isEmpty()
        return new PasswordResult(false, new ArrayList<>());
    }
}