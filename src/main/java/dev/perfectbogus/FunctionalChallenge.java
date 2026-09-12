package dev.perfectbogus;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalChallenge {

    /**
     * CHALLENGE 1: Apply Twice
     *
     * Applies the given function to x, then applies it again to the result.
     *
     * @param f the function to apply
     * @param x the initial value
     * @return f applied to (f applied to x)
     */
    public static int applyTwice(Function<Integer, Integer> f, int x) {
        if (f == null) throw new IllegalArgumentException("f cannot be null");
        Function<Integer, Integer> all = f.compose(f);
        return all.apply(x);
    }

    /**
     * CHALLENGE 2: All Match
     *
     * Checks whether every number in the list satisfies the given predicate.
     *
     * @param numbers a list of integers (never null, may be empty)
     * @param predicate the condition to test each number against
     * @return true if all numbers satisfy the predicate (or the list is empty), false otherwise
     */
    public static boolean allMatch(List<Integer> numbers, Predicate<Integer> predicate) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        if (predicate == null) throw new IllegalArgumentException("Predicate cannot be null");
        return numbers.stream().allMatch(predicate);
    }

    /**
     * CHALLENGE 3: Combine Strings
     *
     * Produces a single string by concatenating the results of two suppliers.
     *
     * @param first supplies the first string
     * @param second supplies the second string
     * @return the concatenation of first.get() and second.get()
     */
    public static String combineStrings(Supplier<String> first, Supplier<String> second) {
        if (first == null) throw new IllegalArgumentException("First cannot be null");
        if (second == null) throw new IllegalArgumentException("Second cannot be null");

        return first.get() + second.get();
    }

    /**
     * CHALLENGE 4: Compose And Apply
     *
     * Applies f to x, then applies g to that result (i.e. g(f(x))).
     *
     * @param f the first function to apply
     * @param g the second function to apply
     * @param x the initial value
     * @return g applied to (f applied to x)
     */
    public static int composeAndApply(Function<Integer, Integer> f, Function<Integer, Integer> g, int x) {
        Objects.requireNonNull(f, "f cannot be null");
        Objects.requireNonNull(g, "g cannot be null");
        Function<Integer, Integer> all = g.compose(f);
        return all.apply(x);
    }

    /**
     * CHALLENGE 5: Count Matching
     *
     * Counts how many words in the list satisfy the given predicate.
     *
     * @param words a list of words (never null, may be empty)
     * @param predicate the condition to test each word against
     * @return the number of words that satisfy the predicate
     */
    public static long countMatching(List<String> words, Predicate<String> predicate) {
        Objects.requireNonNull(words, "Words cannot be null");
        Objects.requireNonNull(predicate, "Predicate cannot be null");

        return words.stream().filter(predicate).count();
    }
}
