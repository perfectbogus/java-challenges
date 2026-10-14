package dev.perfectbogus.characters;

import java.util.stream.Collectors;

public class CharactersChallenge {

    // CHALLENGE 1
    // Returns how many characters of text are decimal digits. Digits of other
    // scripts count too (for example the Arabic-Indic digit three, ٣).
    // Letters, spaces and punctuation do not count.
    public static int countDigits(String text) {
        return text.chars().filter(Character::isDigit).map(c -> 1).sum();
    }

    // CHALLENGE 2
    // Returns how many characters of text are letters, of any alphabet
    // (for example 'a', 'Z', 'n' with a tilde, 'e' with an accent, or a Greek
    // letter). Digits, spaces and punctuation do not count.
    public static int countLetters(String text) {
        return text.chars().filter(Character::isLetter).map(c -> 1).sum();
    }

    // CHALLENGE 3
    // Returns how many characters of text are upper-case letters, of any
    // alphabet (an upper-case 'E' with an accent counts). Lower-case letters,
    // digits, spaces and punctuation do not count.
    public static int countUpperCase(String text) {
        return text.chars().filter(Character::isUpperCase).map(c -> 1).sum();
    }

    // CHALLENGE 4
    // Returns text with every upper-case letter converted to lower case and
    // every lower-case letter converted to upper case. Every character is
    // replaced by exactly one character, so the result always has the same
    // length as text. Characters that are not letters, and letters that have
    // no other case (such as the German sharp s), stay as they are.
    public static String swapCase(String text) {
        return text.chars()
                .map(c -> Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public static String swapCaseLoop(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // CHALLENGE 5
    // Returns how many characters of text are whitespace according to Java:
    // space, tab, newline, carriage return, form feed and so on. The no-break
    // space ( ) is NOT whitespace for this challenge.
    public static int countWhitespace(String text) {
        return text.chars().filter(Character::isWhitespace).map(c -> 1).sum();
    }

    // CHALLENGE 6
    // Returns true when text is not empty and every one of its characters is
    // a letter or a digit (of any alphabet or script). A space or any
    // punctuation makes it false. The empty text gives false.
    public static boolean isAlphanumeric(String text) {
        if (text.isEmpty()) return false;
        return text.chars().allMatch(Character::isLetterOrDigit);
    }

    // CHALLENGE 7
    // Returns the sum of the numeric values of all the decimal digits of text.
    // Digits of other scripts count with their own value (the Arabic-Indic
    // digit three, ٣, is worth 3). Any character that is not a digit is
    // ignored. Text without digits gives 0.
    public static int digitSum(String text) {
        return text.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();
    }

    // CHALLENGE 8
    // Returns the value, from 0 to 15, of the hexadecimal digit c. The valid
    // hexadecimal digits are '0' to '9', 'a' to 'f' and 'A' to 'F'. Any other
    // character gives -1.
    public static int hexValue(char c) {
        if (Character.isDigit(c) && c < 128) {
            return Character.getNumericValue(c);
        } else if (Character.isLetter(c)) {
            if (c == 'a' || c == 'A') {
                return 10;
            } else if (c == 'b' || c == 'B') {
                return 11;
            } else if (c == 'c' || c == 'C') {
                return 12;
            } else if (c == 'd' || c == 'D') {
                return 13;
            } else if (c == 'e' || c == 'E') {
                return 14;
            } else if (c == 'f' || c == 'F') {
                return 15;
            }
        }
        return -1;
    }

    public static int hexValueExtra(char c) {
        return c < 128 ? Character.digit(c, 16) : -1;
    }

    // CHALLENGE 9
    // Returns the lower-case hexadecimal digit that represents value: '0' to
    // '9' for 0 to 9 and 'a' to 'f' for 10 to 15. If value is less than 0 or
    // greater than 15 returns '?'.
    public static char hexDigit(int value) {
        if (value < 0 || value > 15 ) return '?';
        if (value <= 9) return (char) ('0' + value);
        if (value == 10) {
            return 'a';
        } else if (value == 11) {
            return 'b';
        } else if (value == 12) {
            return 'c';
        } else if (value == 13) {
            return 'd';
        } else if (value == 14) {
            return 'e';
        } else {
            return 'f';
        }
    }

    public static char hexDigitExtra(int value) {
        return Character.forDigit(value, 16);
    }

    // CHALLENGE 10
    // Returns text with the first character of every word in upper case and
    // all the other letters of the word in lower case. A word is a run of
    // characters that are not whitespace (whitespace as Java defines it).
    // The whitespace between words is kept exactly as it is, including
    // repeated spaces, tabs and new lines. Characters that are not letters
    // stay as they are.
    public static String capitalizeWords(String text) {
        StringBuilder sb = new StringBuilder();
        boolean startOfWord = true;
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                startOfWord = true;
                sb.append(c);
            } else {
                sb.append(startOfWord ? Character.toUpperCase(c) : Character.toLowerCase(c));
                startOfWord = false;
            }
        }
        return sb.toString();
    }

    // CHALLENGE 11
    // Returns true when text reads the same forwards and backwards if only its
    // letters and digits are considered and upper case and lower case are
    // treated as equal. Every other character is skipped. Text without any
    // letter or digit (including the empty text) is a palindrome.
    public static boolean isPalindrome(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns a String that contains exactly the Unicode character with the
    // given code point. Code points above 0xFFFF need two chars (a surrogate
    // pair), so for them the result has length 2. If codePoint is not a valid
    // Unicode code point (negative or greater than 0x10FFFF) throws an
    // IllegalArgumentException.
    public static String fromCodePoint(int codePoint) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Shifts every letter of the English alphabet ('a' to 'z' and 'A' to 'Z')
    // forward by shift positions, going back to 'a' after 'z' (and to 'A'
    // after 'Z') and keeping its case. shift can be any int: zero, negative,
    // or larger than 26. Every other character (digits, spaces, punctuation,
    // letters with accents) stays as it is.
    public static String caesar(String text, int shift) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns true when password has at least 8 characters and contains at
    // least one upper-case letter, one lower-case letter, one digit and one
    // symbol. A symbol is a character that is not a letter, not a digit and
    // not whitespace. A null password gives false.
    public static boolean isStrongPassword(String password) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns true when name is a valid Java identifier: it is not empty, its
    // first character can start an identifier (a letter, '_' or '$', for
    // example) and every other character can be part of an identifier (the
    // same ones, plus digits). Reserved words are not checked, so "class" is
    // accepted. A null name gives false.
    public static boolean isValidJavaIdentifier(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
