package dev.perfectbogus.mix;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MixedBasicsChallenge {

    // CHALLENGE 1 (Characters)
    // Returns how many characters of the text are letters. Letters of any alphabet count, also the ones with accents
    // (like 'e' with an accent or 'n' with a tilde). Digits, spaces and symbols do not count.
    // Example: countLetters("ab1 c!")  returns 3
    //          countLetters("123")     returns 0
    //          countLetters("")        returns 0
    public static int countLetters(String text) {
        return (int) text.chars().mapToObj(c -> (char)c).filter(Character::isLetter).count();
    }

    // CHALLENGE 2 (Characters)
    // Returns the text with every uppercase letter changed to lowercase and every lowercase letter changed to
    // uppercase. The characters that are not letters do not change.
    // Example: swapCase("aBc1")       returns "AbC1"
    //          swapCase("Hello World") returns "hELLO wORLD"
    //          swapCase("123 !")       returns "123 !"
    public static String swapCase(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3 (Characters)
    // Returns the sum of the digits (0 to 9) that appear in the text. Each digit counts by itself, so "22" counts as
    // 2 + 2. The characters that are not digits are ignored. A text without digits gives 0.
    // Example: digitSum("a1b22")  returns 5
    //          digitSum("abc")    returns 0
    //          digitSum("9 9 9")  returns 27
    public static int digitSum(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4 (Streams)
    // Returns the squares of the even numbers, in the same order they have in the list. Zero is even. The numbers are
    // between -1000 and 1000. If there are no even numbers, the result is an empty list.
    // Example: evenSquares([1, 2, 3, 4])  returns [4, 16]
    //          evenSquares([-2, 0, 5])    returns [4, 0]
    //          evenSquares([1, 3])        returns []
    public static List<Integer> evenSquares(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5 (Streams)
    // Returns the length of the longest word. An empty list gives 0.
    // Example: longestLength([a, abc, ab])  returns 3
    //          longestLength([hello])       returns 5
    //          longestLength([])            returns 0
    public static int longestLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6 (Streams)
    // Returns the first three words that have 4 letters or more, in the same order they have in the list. If there
    // are fewer than three of them, all of them are returned.
    // Example: firstThreeLongWords([a, abcd, xyz, hello, world, java])  returns [abcd, hello, world]
    //          firstThreeLongWords([abc, abcd])                         returns [abcd]
    //          firstThreeLongWords([])                                  returns []
    public static List<String> firstThreeLongWords(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7 (Strings)
    // Returns true if the text reads the same from left to right and from right to left, ignoring the difference
    // between uppercase and lowercase letters and ignoring the spaces. An empty text, or a text with only spaces,
    // gives true. Other characters count normally.
    // Example: isPalindrome("Never odd or even")  returns true
    //          isPalindrome("abca")               returns false
    //          isPalindrome("A")                  returns true
    //          isPalindrome("ab,ba")              returns true
    public static boolean isPalindrome(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8 (Strings)
    // The sentence has words separated by exactly one space, with no spaces at the beginning or at the end. Returns
    // the sentence with the words in the opposite order. The words themselves do not change. An empty sentence gives
    // an empty text.
    // Example: reverseWords("one two three")  returns "three two one"
    //          reverseWords("hello")          returns "hello"
    //          reverseWords("")               returns ""
    public static String reverseWords(String sentence) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9 (Strings)
    // Compresses the text (only letters) by writing each letter followed by the number of times it appears in a row.
    // A letter that appears once gets a 1. The case matters ('a' and 'A' are different letters). An empty text gives
    // an empty text.
    // Example: compress("aaabcc")  returns "a3b1c2"
    //          compress("abc")     returns "a1b1c1"
    //          compress("aAa")     returns "a1A1a1"
    //          compress("")        returns ""
    public static String compress(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10 (Collectors)
    // Returns a map from each length to the list of the words that have that length. The words in each list keep the
    // same order they have in the original list. Only lengths that appear are in the map. An empty list gives an empty
    // map.
    // Example: groupByLength([a, bb, c, dd, eee])  returns {1=[a, c], 2=[bb, dd], 3=[eee]}
    //          groupByLength([hello])              returns {5=[hello]}
    //          groupByLength([])                   returns {}
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11 (Collectors)
    // Returns a map from each first letter to the number of words that start with it. No word is empty. The case
    // matters ('a' and 'A' are different keys). Only letters that appear are in the map. An empty list gives an empty
    // map.
    // Example: countByFirstLetter([apple, avocado, banana])  returns {a=2, b=1}
    //          countByFirstLetter([Apple, apple])            returns {A=1, a=1}
    //          countByFirstLetter([])                        returns {}
    public static Map<Character, Long> countByFirstLetter(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12 (Collectors)
    // Returns the words without repetitions, in alphabetical order (the normal comparison of texts, so any uppercase
    // letter comes before any lowercase letter), joined in one text with ", " between them. An empty list gives an
    // empty text.
    // Example: joinedSortedDistinct([b, a, b])  returns "a, b"
    //          joinedSortedDistinct([x])        returns "x"
    //          joinedSortedDistinct([b, A])     returns "A, b"
    //          joinedSortedDistinct([])         returns ""
    public static String joinedSortedDistinct(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
