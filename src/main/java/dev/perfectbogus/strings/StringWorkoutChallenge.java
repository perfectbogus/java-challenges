package dev.perfectbogus.strings;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public static String removeDuplicateCharsExtended(String text) {
        Set<Character> seen = new LinkedHashSet<>();
        for (char c : text.toCharArray()) {
            seen.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : seen) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String removeDuplicateChars(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) < 0) {
                sb.append(c);
            }
        }
        return sb.toString();
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
        return Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).orElse("");
    }

    // CHALLENGE 6
    // Returns how many times "part" appears in "text", without overlapping: after finding one, the search continues
    // after its last character. part is never empty. The case matters.
    // Example: countOccurrences("abcabc", "bc")  returns 2
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

    public static int countOccurrencesString(String text, String part) {
        int count = 0;
        int index = text.indexOf(part);
        while (index >= 0) {
            count++;
            index = text.indexOf(part, index + part.length());
        }
        return count;
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
        if (text.length() >= width) {
            return text;
        }
        StringBuilder sb = new StringBuilder();
        sb.repeat(String.valueOf(fill), width - text.length());
        sb.append(text);
        return sb.toString();
    }

    public static String padLeftSimplified(String text, int width, char fill) {
        StringBuilder sb = new StringBuilder();
        for (int i = text.length(); i < width; i++) {
            sb.append(fill);
        }
        return sb.append(text).toString();
    }

    public static String padLeftStream(String text, int width, char fill) {
        return String.valueOf(fill).repeat(Math.max(0, width - text.length())) + text;
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
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (char c : text.toCharArray()) {
            count.merge(c, 1, Integer::sum);
        }
        char best = text.charAt(0);
        for (Map.Entry<Character, Integer> e : count.entrySet()) {
            if (e.getValue() > count.get(best)) {
                best = e.getKey();
            }
        }
        return best;
    }
}