package dev.perfectbogus.strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringApiChallenge {

    // CHALLENGE 1
    // Returns true if the text reads the same from left to right and from right to left, ignoring the difference
    // between uppercase and lowercase letters. Every other character (spaces, punctuation) counts. An empty text and
    // a text with one character are palindromes.
    // Example: isPalindrome("Level")   returns true
    //          isPalindrome("ab ba")   returns true
    //          isPalindrome("abca")    returns false
    //          isPalindrome("")        returns true
    public static boolean isPalindrome(String text) {
        String norm = text.toLowerCase();
        int left = 0;
        int right = norm.length() - 1;
        while (left < right) {
            if (norm.charAt(left) != norm.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeImproved(String text) {
        return new StringBuilder(text).reverse().toString().equalsIgnoreCase(text);
    }

    // CHALLENGE 2
    // Returns the words of the sentence in the opposite order. The words are separated by exactly one space, and the
    // sentence has no spaces at the beginning or at the end. An empty sentence gives an empty text.
    // Example: reverseWords("one two three")  returns "three two one"
    //          reverseWords("solo")           returns "solo"
    //          reverseWords("")               returns ""
    public static String reverseWords(String sentence) {
        String[] split = sentence.split(" ");
        int left = 0;
        int right = split.length - 1;

        String[] result = new String[split.length];
        while (left < split.length) {
            result[left] = split[right];
            left++;
            right--;
        }

        return String.join(" ", result);
    }

    public static String reverseWordsImproved(String sentence) {
        if (sentence.isEmpty()) return "";
        List<String> words = Arrays.asList(sentence.split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    // CHALLENGE 3
    // Returns how many times part appears in text, without overlapping: after a match, the search continues after the
    // end of that match. part is never empty. The case matters.
    // Example: countOccurrences("banana", "an")  returns 2
    //          countOccurrences("aaaa", "aa")    returns 2
    //          countOccurrences("aaa", "aa")     returns 1
    //          countOccurrences("abc", "d")      returns 0
    public static int countOccurrences(String text, String part) {
        Matcher m = Pattern.compile(Pattern.quote(part)).matcher(text);
        int i = 0;
        while (m.find()) {
            i++;
        }
        return i;
    }

    public static int countOccurrencesImproved(String text, String part) {
        int count = 0;
        int index = text.indexOf(part);
        while (index >= 0) {
            count++;
            index = text.indexOf(part, index + part.length());
        }
        return count;
    }

    // CHALLENGE 4
    // Returns the text with its first character in uppercase and all the other characters in lowercase. An empty
    // text gives an empty text. Characters that are not letters do not change.
    // Example: capitalize("hELLO")   returns "Hello"
    //          capitalize("a")       returns "A"
    //          capitalize("123ABC")  returns "123abc"
    //          capitalize("")        returns ""
    public static String capitalize(String text) {
        if (text.isEmpty()) return "";
        return Character.toUpperCase(text.charAt(0)) +
                text.substring(1).toLowerCase();
    }

    public static String capitalizeImproved(String text) {
        if (text.isEmpty()) return "";
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    // CHALLENGE 5
    // Returns the sentence with every word written as in challenge 4: the first letter of each word in uppercase and
    // the other letters in lowercase. The words are separated by exactly one space, and there are no spaces at the
    // beginning or at the end. An empty sentence gives an empty text.
    // Example: titleCase("hello WORLD")  returns "Hello World"
    //          titleCase("a b c")        returns "A B C"
    //          titleCase("")             returns ""
    public static String titleCase(String sentence) {
        if (sentence.isEmpty()) return "";
        return Arrays.stream(sentence.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // CHALLENGE 6
    // Adds copies of the character fill at the beginning of the text until it has width characters. If the text already
    // has width characters or more, it is returned as it is, without cutting it.
    // Example: padLeft("7", 3, '0')    returns "007"
    //          padLeft("abc", 2, '*')  returns "abc"
    //          padLeft("", 3, '-')     returns "---"
    //          padLeft("ab", 2, '0')   returns "ab"
    public static String padLeft(String text, int width, char fill) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the text repeated the given number of times, with the separator between two consecutive copies (not
    // at the beginning and not at the end). times is never negative, and 0 gives an empty text.
    // Example: repeatWithSeparator("ab", 3, ", ")  returns "ab, ab, ab"
    //          repeatWithSeparator("ab", 1, ", ")  returns "ab"
    //          repeatWithSeparator("ab", 0, ", ")  returns ""
    //          repeatWithSeparator("", 3, ",")     returns ",,"
    public static String repeatWithSeparator(String text, int times, String separator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the text without its digits (the characters from '0' to '9'). All the other characters stay, in the
    // same order.
    // Example: removeDigits("a1b22c")  returns "abc"
    //          removeDigits("123")     returns ""
    //          removeDigits("a b")     returns "a b"
    //          removeDigits("")        returns ""
    public static String removeDigits(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns true if both texts have exactly the same characters, the same number of times each, in any order.
    // The difference between uppercase and lowercase letters is ignored. Spaces and other characters count like any
    // other character.
    // Example: isAnagram("Listen", "Silent")  returns true
    //          isAnagram("aab", "abb")        returns false
    //          isAnagram("abc", "abcd")       returns false
    //          isAnagram("", "")              returns true
    public static boolean isAnagram(String a, String b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Compresses the text: every group of equal consecutive characters is replaced by the character followed by the
    // number of characters in the group, always written, even when it is 1. The case matters.
    // Example: compress("aaabcc")        returns "a3b1c2"
    //          compress("aabbaa")        returns "a2b2a2"
    //          compress("aaaaaaaaaaaa")  returns "a12"
    //          compress("")              returns ""
    public static String compress(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns the character in the middle of the text if it has an odd number of characters, or the two characters
    // in the middle if it has an even number of characters. An empty text gives an empty text.
    // Example: middleCharacters("abc")   returns "b"
    //          middleCharacters("abcd")  returns "bc"
    //          middleCharacters("a")     returns "a"
    //          middleCharacters("")      returns ""
    public static String middleCharacters(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Replaces every character of the card number with '*', except the last 4 characters, which stay. If the number
    // has 4 characters or less, it is returned as it is. Spaces and dashes are characters too.
    // Example: maskCard("1234567890123456")  returns "************3456"
    //          maskCard("12345")             returns "*2345"
    //          maskCard("1234")              returns "1234"
    //          maskCard("")                  returns ""
    public static String maskCard(String number) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Writes an amount of money, given in cents, as a text with a dot and exactly two decimals. There are no
    // thousands separators and no currency symbol. cents is never negative.
    // Example: formatCents(1205)     returns "12.05"
    //          formatCents(5)        returns "0.05"
    //          formatCents(0)        returns "0.00"
    //          formatCents(100)      returns "1.00"
    //          formatCents(1234567)  returns "12345.67"
    public static String formatCents(long cents) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Joins the parts with the separator between them, but leaves out the parts that are null or blank (empty or with
    // only spaces). The other parts are kept exactly as they are, with their spaces, and in the same order. If
    // nothing is left, the result is an empty text. The list can contain null elements.
    // Example: joinNonBlank([a, null, "  ", b], "-")  returns "a-b"
    //          joinNonBlank([" x ", "y"], "+")        returns " x +y"
    //          joinNonBlank([null, ""], ",")          returns ""
    public static String joinNonBlank(List<String> parts, String separator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns how many words the text has. The words are separated by one or more spaces, and there can be spaces at
    // the beginning and at the end. Only the space character separates words. An empty text, or a text with only
    // spaces, has 0 words.
    // Example: wordCount("one two")        returns 2
    //          wordCount("  one   two  ")  returns 2
    //          wordCount("one")            returns 1
    //          wordCount("   ")            returns 0
    //          wordCount("")               returns 0
    public static int wordCount(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}