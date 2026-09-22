package dev.perfectbogus.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamIntermediateChallenge {

    // CHALLENGE 1
    // Returns the sum of the squares of every number in nums, computed with
    // a stream. An empty list returns 0.
    public static int sumOfSquares(List<Integer> nums) {
        return nums.stream().map(n -> n * n).mapToInt(Integer::intValue).sum();
    }

    // CHALLENGE 2
    // Returns a new list containing only the even numbers from nums, sorted
    // in ascending order.
    public static List<Integer> filterAndSortEven(List<Integer> nums) {
        return nums.stream().filter(n -> n % 2 == 0).sorted().toList();
    }

    // CHALLENGE 3
    // Returns the elements of items joined together separated by ", ".
    // An empty list returns "".
    public static String joinWithCommas(List<String> items) {
        if (items.isEmpty()) return "";
        return items.stream().collect(Collectors.joining(", "));
    }

    // CHALLENGE 4
    // Returns how many strings in words have length strictly greater than
    // minLength.
    public static long countLongerThan(List<String> words, int minLength) {
        return words.stream().filter(w -> w.length() > minLength).count();
    }

    // CHALLENGE 5
    // Groups words by their length. Each key maps to the list of words
    // (in their original relative order) that have that length.
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length
        ));
    }

    // CHALLENGE 6
    // Returns the distinct words in words (case-insensitive — "Apple" and
    // "apple" are considered the same word), lowercased, sorted in
    // ascending alphabetical order.
    public static List<String> distinctSortedIgnoreCase(List<String> words) {
        return words.stream().map(String::toLowerCase).distinct().sorted().toList();
    }

    // CHALLENGE 7
    // Returns the average of the numbers in nums as a double. An empty
    // list returns 0.0.
    public static double average(List<Integer> nums) {
        return nums.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // CHALLENGE 8
    // Returns the longest word in words, wrapped in an Optional. If more
    // than one word shares the maximum length, the first one encountered
    // is returned. An empty list returns Optional.empty().
    public static Optional<String> longestWord(List<String> words) {
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        return words.stream().max(byLength).or(Optional::empty);
    }

    // CHALLENGE 9
    // Flattens a list of lists into a single list, preserving the overall
    // order of elements.
    public static List<Integer> flatten(List<List<Integer>> nested) {
        return nested.stream().flatMap(Collection::stream).toList();
    }

    // CHALLENGE 10
    // Partitions nums into two groups: the even numbers (key true) and the
    // odd numbers (key false), each preserving their original relative
    // order.
    public static Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> nums) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns a map from each distinct word in words to how many times it
    // appears.
    public static Map<String, Long> wordFrequency(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns the n keys of scores with the highest values, in descending
    // order of value. Ties are broken by comparing the keys alphabetically
    // in ascending order. If n is greater than or equal to the number of
    // entries, all keys are returned (ordered the same way).
    public static List<String> topNKeysByValue(Map<String, Integer> scores, int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns a string made of the uppercased first letter of every
    // non-empty word in words, concatenated with no separator, in order.
    // Empty strings in words are skipped.
    public static String firstLettersUppercase(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns the sum of the decimal digits of the absolute value of
    // number, computed by streaming over the characters of its string
    // representation.
    public static int sumOfDigits(int number) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns the product of every number in nums, computed with
    // stream reduction. An empty list returns 1.
    public static long product(List<Integer> nums) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
