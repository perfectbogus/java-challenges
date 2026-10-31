package dev.perfectbogus.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsBasicsChallenge {

    // CHALLENGE 1
    // Returns the whole numbers from "from" to "to", both included, in ascending order. If "from" is greater than
    // "to", the result is an empty list. The numbers can be negative.
    // Example: range(1, 4)    returns [1, 2, 3, 4]
    //          range(-2, 1)   returns [-2, -1, 0, 1]
    //          range(3, 3)    returns [3]
    //          range(5, 2)    returns []
    public static List<Integer> range(int from, int to) {
        return IntStream.rangeClosed(from, to).boxed().toList();
    }

    // CHALLENGE 2
    // Returns the words in uppercase, without repetitions. A word that appears again after being written in uppercase
    // is left out, so "a" and "A" count as the same word. The words are in the order of their first appearance.
    // Example: uniqueUppercase([a, b, A, c, b])  returns [A, B, C]
    //          uniqueUppercase([x, x, x])        returns [X]
    //          uniqueUppercase([])               returns []
    public static List<String> uniqueUppercase(List<String> words) {
        return words.stream().map(String::toUpperCase).distinct().toList();
    }

    // CHALLENGE 3
    // Joins all the inner lists in one list, keeping the order: first the elements of the first list, then the
    // elements of the second list, and so on. Inner lists can be empty. The lists are never null.
    // Example: flatten([[a, b], [c], [d, e]])  returns [a, b, c, d, e]
    //          flatten([[], [x], []])          returns [x]
    //          flatten([])                     returns []
    public static List<String> flatten(List<List<String>> lists) {
        return lists.stream().flatMap(Collection::stream).toList();
    }

    // CHALLENGE 4
    // Returns the sum of the squares of the numbers. The numbers are between -1000 and 1000 and there are at most
    // 1000 of them, so the result always fits in an int. An empty list gives 0.
    // Example: sumOfSquares([1, 2, 3])  returns 14
    //          sumOfSquares([-3, 3])    returns 18
    //          sumOfSquares([])         returns 0
    public static int sumOfSquares(List<Integer> numbers) {
        return numbers.stream().map(i -> i * i).reduce(0, Integer::sum);
    }

    public static int sumOfSquaresSimplified(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i * i).sum();
    }

    // CHALLENGE 5
    // Returns the average of the numbers as a double (the decimals are kept). An empty list gives 0.0.
    // Example: average([1, 2, 4])  returns 2.3333...
    //          average([1, 2])     returns 1.5
    //          average([])         returns 0.0
    public static double average(List<Integer> numbers) {
        return numbers.stream().mapToDouble(i -> i).average().orElse(0.0);
    }

    public static double averageSimplified(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // CHALLENGE 6
    // Returns the first "count" powers of two, starting with 1: [1, 2, 4, 8, ...]. count is between 0 and 30, so
    // the numbers always fit in an int. A count of 0 gives an empty list.
    // Example: powersOfTwo(5)  returns [1, 2, 4, 8, 16]
    //          powersOfTwo(1)  returns [1]
    //          powersOfTwo(0)  returns []
    public static List<Integer> powersOfTwo(int count) {
        return IntStream.range(0, count).map(i -> (int)Math.pow(2, i)).boxed().toList();
    }

    public static List<Integer> powersOfTwoSimplified(int count) {
        return Stream.iterate(1, n -> n * 2).limit(count).toList();
    }

    // CHALLENGE 7
    // Returns true if none of the numbers is negative. Zero is not negative. An empty list gives true.
    // Example: noneNegative([0, 3, 7])   returns true
    //          noneNegative([4, -1, 2])  returns false
    //          noneNegative([])          returns true
    public static boolean noneNegative(List<Integer> numbers) {
        return numbers.stream().allMatch(i -> i >= 0);
    }

    public static boolean noneNegativeSimplified(List<Integer> numbers) {
        return numbers.stream().noneMatch(i -> i < 0);
    }

    // CHALLENGE 8
    // Returns how many words there are in total in all the sentences. In a sentence, the words are separated by
    // exactly one space, and there are no spaces at the beginning or at the end. An empty sentence has 0 words.
    // Example: countWords([one two, three])  returns 3
    //          countWords([a b c d, , e])    returns 5
    //          countWords([])                returns 0
    public static int countWords(List<String> sentences) {
        return sentences.stream()
                .mapToInt(s -> (int) Arrays.stream(s.split(" ")).filter(str -> !str.isBlank()).count())
                .sum();
    }

    public static int countWordsSimplified(List<String> sentences) {
        return (int) sentences.stream()
                .filter(s -> !s.isEmpty())
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .count();
    }

    // CHALLENGE 9
    // Returns the product of all the numbers (all of them multiplied). The numbers are small, so the result always
    // fits in an int. An empty list gives 1.
    // Example: product([2, 3, 4])  returns 24
    //          product([5, 0, 9])  returns 0
    //          product([-2, 3])    returns -6
    //          product([])         returns 1
    public static int product(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the positions (indices, starting at 0) where the target appears in the list, in ascending order. The
    // case matters. If the target does not appear, the result is an empty list. The list is not modified.
    // Example: indicesOf([a, b, a, c, a], a)  returns [0, 2, 4]
    //          indicesOf([a, b], z)           returns []
    //          indicesOf([], a)               returns []
    public static List<Integer> indicesOf(List<String> words, String target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
