package dev.perfectbogus.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringWorkoutChallenge {

    // CHALLENGE 1
    // Returns how many vowels the text has. The vowels are a, e, i, o and u, in lowercase or uppercase. Other letters,
    // digits and symbols do not count.
    // Example: countVowels("Hello World")  returns 3
    //          countVowels("AEIOU")        returns 5
    //          countVowels("rhythm")       returns 0
    //          countVowels("")             returns 0
    public static int countVowels(String text) {
        String vowels = "aAeEiIoOuU";
        return (int) text.chars().filter(c -> vowels.indexOf(c) != -1).count();
    }

    // CHALLENGE 2
    // The sentence has words separated by exactly one space, with no spaces at the beginning or at the end. Returns
    // the sentence with every word starting with an uppercase letter and the rest of its letters in lowercase. An
    // empty sentence gives an empty text.
    // Example: capitalizeWords("hELLO wORLD")  returns "Hello World"
    //          capitalizeWords("a bc")         returns "A Bc"
    //          capitalizeWords("JAVA")         returns "Java"
    //          capitalizeWords("")             returns ""
    public static String capitalizeWords(String sentence) {
        if (sentence.isEmpty()) return "";
        return Arrays.stream(sentence.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static String capitalizeWordsFunctional(String sentence) {
        if (sentence.isEmpty()) return "";
        String[] split = sentence.split(" ");
        List<String> result = new ArrayList<>();
        for (String s : split) {
            result.add(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
        }
        return String.join(" ", result);
    }

    // CHALLENGE 3
    // Returns true if both texts have the same letters the same number of times, in any order. The difference between
    // uppercase and lowercase is ignored and the spaces are ignored. Other characters count normally. Two empty texts
    // (or texts with only spaces) are anagrams.
    // Example: isAnagram("Listen", "Silent")             returns true
    //          isAnagram("a gentleman", "elegant man")   returns true
    //          isAnagram("abc", "abcc")                  returns false
    //          isAnagram("aab", "abb")                   returns false
    public static boolean isAnagram(String first, String second) {
        char[] charsFirst = first.replaceAll(" ", "").toLowerCase().toCharArray();
        char[] charsSecond = second.replaceAll(" ", "").toLowerCase().toCharArray();
        Arrays.sort(charsFirst);
        Arrays.sort(charsSecond);
        return Arrays.equals(charsFirst, charsSecond);
    }

    // CHALLENGE 4
    // Returns the text keeping only the first appearance of each character. The characters that stay keep their
    // order. The case matters ('a' and 'A' are different characters) and spaces count like any other character.
    // Example: removeDuplicateChars("banana")  returns "ban"
    //          removeDuplicateChars("aAa")     returns "aA"
    //          removeDuplicateChars("a b a")   returns "a b"
    //          removeDuplicateChars("")        returns ""
    public static String removeDuplicateChars(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // The sentence has words separated by exactly one space, with no spaces at the beginning or at the end. Returns
    // the longest word. If several words have the longest length, the first one is returned. An empty sentence gives
    // an empty text.
    // Example: longestWord("I love programming")  returns "programming"
    //          longestWord("one two six")         returns "one"
    //          longestWord("a")                   returns "a"
    //          longestWord("")                    returns ""
    public static String longestWord(String sentence) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns how many times "part" appears in "text", without overlapping: after finding one, the search continues
    // after its last character. part is never empty. The case matters.
    // Example: countOccurrences("abcabc", "bc")  returns 2
    //          countOccurrences("aaaa", "aa")    returns 2
    //          countOccurrences("aaa", "aa")     returns 1
    //          countOccurrences("abc", "d")      returns 0
    public static int countOccurrences(String text, String part) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the text with the character "fill" added at the beginning as many times as needed to reach the given
    // width. If the text already has "width" characters or more, it does not change. The width can be zero or
    // negative.
    // Example: padLeft("42", 5, '0')    returns "00042"
    //          padLeft("hello", 3, '*') returns "hello"
    //          padLeft("abc", 3, '-')   returns "abc"
    //          padLeft("", 2, 'x')      returns "xx"
    public static String padLeft(String text, int width, char fill) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the character that appears the most times in the text. If two or more characters appear the same
    // number of times, the one that appears first in the text is returned. The text is never empty. The case matters
    // and spaces count like any other character.
    // Example: mostFrequentChar("abca")   returns 'a'
    //          mostFrequentChar("abcbc")  returns 'b'
    //          mostFrequentChar("aabb")   returns 'a'
    //          mostFrequentChar("bbaa")   returns 'b'
    public static char mostFrequentChar(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}