package dev.perfectbogus.sets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SetsPracticeChallenge {

    // ==========================================================
    // HASHSET
    // ==========================================================

    // CHALLENGE 1 (HashSet)
    // Returns a set with the different words of the list. The case matters ("a" and "A" are different words). An empty
    // list gives an empty set.
    // Example: uniqueWords([a, b, a])  returns {a, b}
    //          uniqueWords([a, A])     returns {a, A}
    //          uniqueWords([x, x, x])  returns {x}
    //          uniqueWords([])         returns {}
    public static HashSet<String> uniqueWords(List<String> words) {
        return words.stream().collect(Collectors.toCollection(() -> new HashSet<>()));
    }

    // CHALLENGE 2 (HashSet)
    // Returns a new set with the elements that are in the first set, in the second set, or in both. Neither of the
    // sets received is modified.
    // Example: union({1, 2, 3}, {3, 4})  returns {1, 2, 3, 4}
    //          union({1}, {})            returns {1}
    //          union({}, {})             returns {}
    public static HashSet<Integer> union(Set<Integer> first, Set<Integer> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3 (HashSet)
    // Returns a new set with the elements that are in both sets. Neither of the sets received is modified.
    // Example: intersection({1, 2, 3}, {2, 3, 4})  returns {2, 3}
    //          intersection({1, 2}, {3, 4})        returns {}
    //          intersection({1, 2}, {})            returns {}
    public static HashSet<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4 (HashSet)
    // Returns a new set with the elements that are in the first set and are not in the second set. Neither of the
    // sets received is modified.
    // Example: difference({1, 2, 3}, {2, 5})  returns {1, 3}
    //          difference({1, 2}, {1, 2, 3})  returns {}
    //          difference({1, 2}, {})         returns {1, 2}
    public static HashSet<Integer> difference(Set<Integer> first, Set<Integer> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5 (HashSet)
    // Returns the first word of the list that has already appeared before in the list, that is, the word whose second
    // appearance comes first. If no word is repeated, returns null. The case matters.
    // Example: firstRepeatedWord([a, b, c, b, a])  returns "b"
    //          firstRepeatedWord([a, b, c])        returns null
    //          firstRepeatedWord([x, x, y, y])     returns "x"
    //          firstRepeatedWord([a, A])           returns null
    public static String firstRepeatedWord(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // LINKEDHASHSET
    // ==========================================================

    // CHALLENGE 6 (LinkedHashSet)
    // Returns a set with the different words of the list, in the order in which each word appears for the first time.
    // Example: distinctInOrder([b, a, b, c, a])  returns {b, a, c}
    //          distinctInOrder([x, x])           returns {x}
    //          distinctInOrder([])               returns {}
    public static LinkedHashSet<String> distinctInOrder(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7 (LinkedHashSet)
    // Returns a set with the numbers that appear in both lists, without repetitions, in the order in which they appear
    // for the first time in the first list. Neither of the lists received is modified.
    // Example: commonInOrder([3, 1, 2, 3], [2, 3])  returns {3, 2}
    //          commonInOrder([5, 6], [7])           returns {}
    //          commonInOrder([1, 2, 3], [3, 2, 1])  returns {1, 2, 3}
    public static LinkedHashSet<Integer> commonInOrder(List<Integer> first, List<Integer> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8 (LinkedHashSet)
    // Returns a set with the different characters of the text, in the order in which each character appears for the
    // first time. The case matters and spaces count like any other character.
    // Example: uniqueChars("banana")  returns {b, a, n}
    //          uniqueChars("aAa")     returns {a, A}
    //          uniqueChars("a b a")   returns {a, ' ', b}
    //          uniqueChars("")        returns {}
    public static LinkedHashSet<Character> uniqueChars(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9 (LinkedHashSet)
    // Returns a set with the words that appear exactly once in the list, in the same order they have in the list.
    // The case matters.
    // Example: appearOnce([a, b, a, c])     returns {b, c}
    //          appearOnce([x, x, x])        returns {}
    //          appearOnce([a, b, c])        returns {a, b, c}
    //          appearOnce([a, a, b, a, c])  returns {b, c}
    public static LinkedHashSet<String> appearOnce(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10 (LinkedHashSet)
    // Returns a new set with all the elements of the first set, followed by the elements of the second set that are
    // not in the first one. Each group keeps the order in which its set gives its elements. Neither of the sets
    // received is modified.
    // Example: mergeKeepingOrder({c, a}, {b, a, d})  returns {c, a, b, d}
    //          mergeKeepingOrder({x}, {})            returns {x}
    //          mergeKeepingOrder({}, {y, x})         returns {y, x}
    public static LinkedHashSet<String> mergeKeepingOrder(Set<String> first, Set<String> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // TREESET
    // ==========================================================

    // CHALLENGE 11 (TreeSet)
    // Returns a set with the different numbers of the list, ordered from the smallest to the greatest.
    // Example: sortedDistinct([3, 1, 3, 2])  returns {1, 2, 3}
    //          sortedDistinct([-1, 0, -1])   returns {-1, 0}
    //          sortedDistinct([])            returns {}
    public static TreeSet<Integer> sortedDistinct(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12 (TreeSet)
    // Returns the smallest element of the set that is greater than n (strictly greater). If there is none, returns -1.
    // All the elements of the set are zero or greater. n can be any int value. The set is not modified.
    // Example: with {2, 5, 9}
    //          nextBigger(set, 4)   returns 5
    //          nextBigger(set, 5)   returns 9
    //          nextBigger(set, 9)   returns -1
    //          nextBigger(set, -3)  returns 2
    public static int nextBigger(TreeSet<Integer> set, int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13 (TreeSet)
    // Returns how many elements of the set are between "from" and "to", both included. If "from" is greater than
    // "to", the result is 0. The set is not modified.
    // Example: with {1, 5, 9, 12}
    //          countBetween(set, 5, 12)  returns 3
    //          countBetween(set, 2, 4)   returns 0
    //          countBetween(set, 9, 9)   returns 1
    //          countBetween(set, 12, 5)  returns 0
    public static int countBetween(TreeSet<Integer> set, int from, int to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14 (TreeSet)
    // Returns a set with the different words of the list, ordered by length (the shortest first). Words with the same
    // length are ordered alphabetically (the normal comparison of texts, so any uppercase letter comes before any
    // lowercase letter).
    // Example: byLengthThenAlphabetical([bb, a, ccc, aa, a])  returns {a, aa, bb, ccc}
    //          byLengthThenAlphabetical([b, A])               returns {A, b}
    //          byLengthThenAlphabetical([])                   returns {}
    public static TreeSet<String> byLengthThenAlphabetical(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15 (TreeSet)
    // Removes from the set the "count" greatest elements, and returns them from the greatest to the smallest. If the
    // set has fewer than "count" elements, all of them are removed. A count of 0 changes nothing. The count is never
    // negative.
    // Example: each one starts with the set {3, 1, 2}
    //          removeLargest(set, 2)  returns [3, 2]     and the set is now {1}
    //          removeLargest(set, 0)  returns []         and the set does not change
    //          removeLargest(set, 5)  returns [3, 2, 1]  and the set is now {}
    public static List<Integer> removeLargest(TreeSet<Integer> set, int count) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}