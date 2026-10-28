package dev.perfectbogus.collectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class CollectingAndThenChallenge {

    // CHALLENGE 1
    // Returns a copy of the list that cannot be changed (add, remove and set must fail with an
    // UnsupportedOperationException). It is a copy: if the original list changes later, the result does not change.
    // The order is kept.
    // Example: unmodifiableCopy([a, b, c])  returns [a, b, c]  (and result.add("d") fails)
    //          unmodifiableCopy([])         returns []
    public static List<String> unmodifiableCopy(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns how many different words there are. The case matters. An empty list gives 0.
    // Example: countDistinct([a, b, a, c, b])  returns 3
    //          countDistinct([x, X])           returns 2
    //          countDistinct([])               returns 0
    public static int countDistinct(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns the different words, without repetitions, in alphabetical order (the normal comparison of texts, so
    // "B" comes before "a"). The result is a new list that can be changed.
    // Example: sortedUnique([pear, apple, pear, fig])  returns [apple, fig, pear]
    //          sortedUnique([b, B, a])                 returns [B, a, b]
    //          sortedUnique([])                        returns []
    public static List<String> sortedUnique(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns the longest word. If two words have the same length, the first of them in the list is returned. An empty
    // list gives an empty text "".
    // Example: longestWord([a, abc, ab])   returns "abc"
    //          longestWord([ab, cd, e])    returns "ab"
    //          longestWord([])             returns ""
    public static String longestWord(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the average of the numbers, rounded to the nearest whole number like Math.round does (2.5 goes to 3,
    // -2.5 goes to -2). An empty list gives 0.
    // Example: averageRounded([1, 2, 4])  returns 2   (the average is 2.33)
    //          averageRounded([1, 2])     returns 2   (the average is 1.5)
    //          averageRounded([])         returns 0
    public static long averageRounded(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Joins the words with a comma (no spaces) and puts the result between square brackets.
    // Example: bracketed([a, b, c])  returns "[a,b,c]"
    //          bracketed([x])        returns "[x]"
    //          bracketed([])         returns "[]"
    public static String bracketed(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Groups the words by their length. Every length has the list of its words, in the same order they have in words.
    // The returned map cannot be changed (put and remove must fail with an UnsupportedOperationException).
    // Example: groupByLengthReadOnly([a, bb, c, dd, eee])  returns {1=[a, c], 2=[bb, dd], 3=[eee]}
    //          groupByLengthReadOnly([])                   returns {}
    public static Map<Integer, List<String>> groupByLengthReadOnly(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns true if there are no repeated words in the list (the case matters). An empty list, or a list with one
    // word, gives true.
    // Example: allDistinct([a, b, c])  returns true
    //          allDistinct([a, b, a])  returns false
    //          allDistinct([])         returns true
    public static boolean allDistinct(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns the three greatest numbers, from the greatest to the smallest. Repeated numbers count each time they
    // appear. If there are fewer than three numbers, all of them are returned in the same order. numbers is not
    // modified.
    // Example: topThree([5, 1, 9, 3, 7])  returns [9, 7, 5]
    //          topThree([4, 4, 4, 1])     returns [4, 4, 4]
    //          topThree([2, 8])           returns [8, 2]
    //          topThree([])               returns []
    public static List<Integer> topThree(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the greatest number of the list, or defaultValue if the list is empty.
    // Example: maxOrDefault([3, 9, 4], 0)  returns 9
    //          maxOrDefault([-5, -2], 0)   returns -2
    //          maxOrDefault([], 42)        returns 42
    public static int maxOrDefault(List<Integer> numbers, int defaultValue) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns how many words have minLength characters or more, as an int (not a long).
    // Example: countLongWords([a, abc, ab, abcd], 3)  returns 2
    //          countLongWords([a, b], 2)              returns 0
    //          countLongWords([], 1)                  returns 0
    public static int countLongWords(List<String> words, int minLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns the first word and the last word of the list, joined with a dash. With one word, that word is both the
    // first and the last. An empty list gives an empty text "".
    // Example: firstAndLast([a, b, c])  returns "a-c"
    //          firstAndLast([x])        returns "x-x"
    //          firstAndLast([])         returns ""
    public static String firstAndLast(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns a collector that gives the sum of the numbers of a stream multiplied by 2. An empty stream gives 0.
    // Example: Stream.of(1, 2, 3).collect(doubledSum())             returns 12
    //          Stream.of(-4, 1).collect(doubledSum())               returns -6
    //          Stream.<Integer>empty().collect(doubledSum())        returns 0
    public static Collector<Integer, ?, Integer> doubledSum() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns a collector that joins the texts of a stream with a dash and writes the whole result in uppercase. An
    // empty stream gives an empty text.
    // Example: Stream.of("ab", "cd").collect(upperJoined())          returns "AB-CD"
    //          Stream.of("x").collect(upperJoined())                 returns "X"
    //          Stream.<String>empty().collect(upperJoined())         returns ""
    public static Collector<CharSequence, ?, String> upperJoined() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns a collector that gives a list with the numbers of a stream in the opposite order. An empty stream gives
    // an empty list.
    // Example: Stream.of(1, 2, 3).collect(reversedList())            returns [3, 2, 1]
    //          Stream.of(7).collect(reversedList())                  returns [7]
    //          Stream.<Integer>empty().collect(reversedList())       returns []
    public static Collector<Integer, ?, List<Integer>> reversedList() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
