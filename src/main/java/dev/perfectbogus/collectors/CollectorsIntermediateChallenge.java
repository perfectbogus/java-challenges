package dev.perfectbogus.collectors;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsIntermediateChallenge {

    // CHALLENGE 1
    // Joins names into a single string, separated by ", ", wrapped in
    // square brackets. For example ["Ann", "Bo"] becomes "[Ann, Bo]".
    public static String joinWithBrackets(List<String> names) {
        return names.stream().collect(Collectors.joining(", ", "[", "]"));
    }

    // CHALLENGE 2
    // Splits numbers into two groups: true maps to every even number,
    // false maps to every odd number. Both lists preserve the original
    // relative order.
    public static Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.partitioningBy(
                (i) -> i % 2 == 0
        ));
    }

    // CHALLENGE 3
    // Returns a map from word length to how many words in the list have
    // that length.
    public static Map<Integer, Long> countByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.counting()
        ));
    }

    // CHALLENGE 4
    // Groups words by their length. Each group's words are stored
    // uppercased, in their original relative order.
    public static Map<Integer, List<String>> groupByLengthUppercase(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream().map(String::toUpperCase).toList()
                )
        ));
    }

    public static Map<Integer, List<String>> groupByLengthUppercase2(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.mapping(String::toUpperCase, Collectors.toList())
        ));
    }

    // CHALLENGE 5
    // Returns a map from each distinct word in words to how many times
    // it appears in the list.
    public static Map<String, Integer> countOccurrences(List<String> words) {
        return words.stream().collect(Collectors.toMap(
                Function.identity(),
                w -> 1,
                Integer::sum
        ));
    }

    // CHALLENGE 6
    // Returns summary statistics (count, sum, min, max, average) for the
    // given numbers.
    public static IntSummaryStatistics getStatistics(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));
    }

    // CHALLENGE 7
    // Returns an Optional containing the largest value in numbers, or
    // Optional.empty() if numbers is empty.
    public static Optional<Integer> reduceToMax(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns an unmodifiable list containing every word in words, in
    // the same order.
    public static List<String> toImmutableList(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns how many distinct first letters appear across all words in
    // the list.
    public static int countDistinctFirstLetters(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Groups words by their length, and within each group joins the
    // words (in original relative order) into a single comma-and-space
    // separated string.
    public static Map<Integer, String> groupAndJoinByLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
