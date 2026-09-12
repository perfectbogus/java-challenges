package dev.perfectbogus.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamChallenge {

    /**
     * CHALLENGE 1: Group Words by Length
     *
     * Groups the given words by their length.
     *
     * @param words a list of words (never null, may be empty)
     * @return a map where each key is a word length and each value is
     *         the list of words (in original order) having that length
     */
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO: implement using Stream API (hint: Collectors.groupingBy)
        return words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.toList()
        ));
    }

    /**
     * CHALLENGE 2: Sum of Squares of Even Numbers
     *
     * Given a list of integers, return the sum of the squares of only
     * the even numbers.
     *
     * @param numbers a list of integers (never null, may be empty)
     * @return the sum of squares of the even numbers; 0 if there are none
     */
    public static int sumOfSquaresOfEvens(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO: implement using Stream API
        // (hint: filter -> map -> sum, or reduce)
        return numbers.stream().filter(i -> i % 2 == 0).mapToInt(Integer::intValue).map(i -> i * i).sum();
    }

    /**
     * CHALLENGE 3: Longest Word Starting With a Given Letter
     *
     * Given a list of words and a starting letter (case-insensitive),
     * return the longest word that starts with that letter.
     * If there are multiple words of the same max length, return the
     * first one encountered (in original order).
     *
     * @param words a list of words (never null, may be empty)
     * @param letter the starting letter to filter on (never null, single character)
     * @return an Optional containing the longest matching word,
     *         or Optional.empty() if no word matches
     */
    public static Optional<String> longestWordStartingWith(List<String> words, char letter) {
        // TODO: implement using Stream API
        // (hint: filter -> max with Comparator.comparingInt)
        throw new UnsupportedOperationException("Not implemented yet");
    }
}