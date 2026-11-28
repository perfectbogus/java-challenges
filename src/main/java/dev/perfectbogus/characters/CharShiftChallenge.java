package dev.perfectbogus.characters;

/**
 * 15 easy challenges about characters: adding and subtracting to move between characters,
 * like (char) ('a' + (c - 'a' + move) % 26).
 *
 * Unless the text says otherwise, the case matters. Some texts can be very long (up to 1,000,000 characters).
 */
public class CharShiftChallenge {

    // ==========================================================
    // CHALLENGE 1: nextLetter
    // ==========================================================
    // c is a letter from a to z or from A to Z. Return the next letter of the alphabet, with the same case.
    // After 'z' comes 'a' and after 'Z' comes 'A'.
    //
    // 'a' -> 'b'
    // 'm' -> 'n'
    // 'z' -> 'a'
    // 'Y' -> 'Z'
    // 'Z' -> 'A'
    public static char nextLetter(char c) {
        if (c >= 'a' && c <= 'y') {
            return (char) (c + 1);
        } else if (c == 'z') {
            return 'a';
        } else if (c >= 'A' && c <= 'Y') {
            return (char) (c + 1);
        } else if (c == 'Z') {
            return 'A';
        } else {
            return c;
        }
    }

    // ==========================================================
    // CHALLENGE 2: previousLetter
    // ==========================================================
    // c is a letter from a to z or from A to Z. Return the previous letter of the alphabet, with the same case.
    // Before 'a' comes 'z' and before 'A' comes 'Z'.
    //
    // 'b' -> 'a'
    // 'n' -> 'm'
    // 'a' -> 'z'
    // 'Z' -> 'Y'
    // 'A' -> 'Z'
    public static char previousLetter(char c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 3: sumOfDigits
    // ==========================================================
    // The text only has the characters from '0' to '9'. Return the sum of the digits.
    //
    // "123"   -> 6
    // "999"   -> 27
    // "0"     -> 0
    // ""      -> 0
    public static int sumOfDigits(String digits) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 4: letterSum
    // ==========================================================
    // Every letter from a to z has a value: a = 1, b = 2, ..., z = 26. Uppercase and lowercase letters
    // have the same value. Return the sum of the values of all the letters of the text.
    // Other characters (digits, spaces, symbols, letters with accents) are ignored.
    //
    // "abc"     -> 6
    // "Hello"   -> 52
    // "Zz"      -> 52
    // "a1b2"    -> 3
    // ""        -> 0
    public static int letterSum(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 5: toBase
    // ==========================================================
    // Write the number n (n >= 0) in the given base (from 2 to 16) with the digits 0-9 and then the
    // UPPERCASE letters A-F for the values 10 to 15. There are no extra zeros at the start (except for 0 itself).
    //
    // (10, 2)           -> "1010"
    // (255, 16)         -> "FF"
    // (0, 2)            -> "0"
    // (100, 10)         -> "100"
    // (255, 8)          -> "377"
    public static String toBase(int n, int base) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 6: fromBase
    // ==========================================================
    // The opposite of the previous challenge. The text is a number written in the given base (from 2 to 16)
    // with the digits 0-9 and the UPPERCASE letters A-F. It is never empty and the value fits in an int.
    //
    // ("1010", 2)        -> 10
    // ("FF", 16)         -> 255
    // ("0", 2)           -> 0
    // ("7FFFFFFF", 16)   -> 2147483647
    // ("377", 8)         -> 255
    public static int fromBase(String text, int base) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 7: shiftByPosition
    // ==========================================================
    // The text only has lowercase letters. Move every letter forward in the alphabet by its position in the text
    // (the first letter moves 0 places, the second 1 place, the third 2 places, ...). After 'z' comes 'a'.
    //
    // "aaa"     -> "abc"
    // "abc"     -> "ace"
    // "zzz"     -> "zab"
    // "a"       -> "a"
    // ""        -> ""
    public static String shiftByPosition(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 8: atbash
    // ==========================================================
    // Swap every letter with the letter in the same place counting from the other end of the alphabet:
    // a <-> z, b <-> y, c <-> x, ... The case is kept. Other characters do not change.
    //
    // "abc"             -> "zyx"
    // "Hello, World!"   -> "Svool, Dliow!"
    // "123"             -> "123"
    // ""                -> ""
    public static String atbash(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 9: lettersBetween
    // ==========================================================
    // Return a text with all the characters from "from" to "to" (both included), in the order of their codes.
    // If "from" is bigger than "to", return the empty text.
    //
    // ('a', 'e')   -> "abcde"
    // ('e', 'a')   -> ""
    // ('x', 'x')   -> "x"
    // ('0', '9')   -> "0123456789"
    // ('Y', 'b')   -> "YZ[\]^_`ab"
    public static String lettersBetween(char from, char to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 10: isConsecutive
    // ==========================================================
    // Return true if every character is exactly the next character (by its code) of the previous one.
    // The empty text and the texts with one character are consecutive.
    //
    // "abc"    -> true
    // "abd"    -> false
    // "cba"    -> false
    // "789"    -> true
    // "yz{"    -> true
    // "aab"    -> false
    public static boolean isConsecutive(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 11: columnName
    // ==========================================================
    // Spreadsheet columns are named A, B, ..., Z, then AA, AB, ..., AZ, BA, ..., ZZ, then AAA, ...
    // Return the name of the column number n (n >= 1, the first column is number 1). n can be up to
    // 2,147,483,647.
    //
    // 1    -> "A"
    // 26   -> "Z"
    // 27   -> "AA"
    // 52   -> "AZ"
    // 53   -> "BA"
    // 702  -> "ZZ"
    // 703  -> "AAA"
    public static String columnName(int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12: columnNumber
    // ==========================================================
    // The opposite of the previous challenge. The name only has uppercase letters from A to Z, it is never empty
    // and the number fits in an int.
    //
    // "A"    -> 1
    // "Z"    -> 26
    // "AA"   -> 27
    // "AZ"   -> 52
    // "ZZ"   -> 702
    // "AAA"  -> 703
    public static int columnNumber(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13: vigenere
    // ==========================================================
    // The text and the key only have lowercase letters and the key is not empty.
    // Move every letter of the text forward in the alphabet by the value of the matching letter of the key
    // (a = 0, b = 1, ..., z = 25). The key is repeated again and again if the text is longer. After 'z' comes 'a'.
    //
    // ("attack", "b")    -> "buubdl"
    // ("hello", "ab")    -> "hflmo"
    // ("abc", "a")       -> "abc"
    // ("abc", "z")       -> "zab"
    // ("lemon", "lemon") -> "wiyca"
    // ("", "key")        -> ""
    public static String vigenere(String text, String key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14: isShiftOf
    // ==========================================================
    // Both texts only have lowercase letters. Return true if b is the same as a with EVERY letter moved forward
    // in the alphabet by the same number of places (from 0 to 25). After 'z' comes 'a'. The texts must have
    // the same length.
    //
    // ("abc", "bcd")   -> true
    // ("abc", "bdd")   -> false
    // ("xyz", "abc")   -> true
    // ("abc", "abc")   -> true
    // ("a", "aa")      -> false
    // ("", "")         -> true
    public static boolean isShiftOf(String a, String b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15: toUpper
    // ==========================================================
    // Return the text with every lowercase English letter (a to z) changed to uppercase.
    // Every other character does not change (also the letters with accents).
    // Do it with arithmetic on the characters.
    //
    // "Hello"       -> "HELLO"
    // "abc123xyz"   -> "ABC123XYZ"
    // "éa"     -> "éA"
    // ""            -> ""
    public static String toUpper(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
