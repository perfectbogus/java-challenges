package dev.perfectbogus.sets;

import java.util.*;
import java.util.stream.Collectors;

public class SetEasyChallenge {

    // ==========================================================
    // CHALLENGE 1
    // ==========================================================
    // Returns how many different values the array has.
    //
    // Examples:
    //   [1, 2, 2, 3, 3, 3]  ->  3
    //   [5, 5, 5]           ->  1
    //   [4, -4]             ->  2
    //   []                  ->  0
    public static int countDistinct(int[] nums) {
        return (int) Arrays.stream(nums).distinct().count();
    }

    public static int countDistinctExtended(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        return set.size();
    }

    // ==========================================================
    // CHALLENGE 2
    // ==========================================================
    // Returns true if at least one word appears more than once in the array. The comparison takes the case into
    // account, so "a" and "A" are different words.
    //
    // Examples:
    //   ["a", "b", "a"]  ->  true
    //   ["a", "A"]       ->  false
    //   ["x"]            ->  false
    //   []               ->  false
    public static boolean hasDuplicates(String[] words) {
        Set<String> set = new HashSet<>();
        for (String w : words) {
            if (!set.add(w)) {
                return true;
            }
        }
        return false;
    }

    // ==========================================================
    // CHALLENGE 3
    // ==========================================================
    // Returns the set of values that appear in both arrays.
    //
    // Examples:
    //   ([1, 2, 3], [2, 3, 4])  ->  {2, 3}
    //   ([1, 1, 2], [1, 3])     ->  {1}
    //   ([1, 2], [3, 4])        ->  {}
    //   ([], [1])               ->  {}
    public static Set<Integer> commonElements(int[] a, int[] b) {
        Set<Integer> res = new HashSet<>(Arrays.stream(a).boxed().toList());
        res.retainAll(new HashSet<>(Arrays.stream(b).boxed().toList()));
        return res;
    }

    // ==========================================================
    // CHALLENGE 4
    // ==========================================================
    // The text has words separated by single spaces (the empty text has no words). Returns the different words in
    // lowercase, in alphabetical order, ignoring the case when deciding if two words are the same.
    //
    // Examples:
    //   "Hello hello World"  ->  ["hello", "world"]
    //   "b A a"              ->  ["a", "b"]
    //   "Zed"                ->  ["zed"]
    //   ""                   ->  []
    public static String[] sortedUniqueWords(String text) {
        if (text.isEmpty()) return new String[0];

        String[] split = text.split(" ");
        Set<String> res = new TreeSet<>();
        for (String w : split) {
            res.add(w.toLowerCase());
        }
        return res.stream().toArray(String[]::new);
    }

    public static String[] sortedUniqueWordsSimplified(String text) {
        if (text.isEmpty()) return new String[0];
        Set<String> res = new TreeSet<>();
        for (String s : text.split(" ")) {
            res.add(s.toLowerCase());
        }
        return res.toArray(String[]::new);
    }

    // ==========================================================
    // CHALLENGE 5
    // ==========================================================
    // Returns the set of values that appear in the first array but not in the second one.
    //
    // Examples:
    //   ([1, 2, 3], [2])        ->  {1, 3}
    //   ([1, 1, 2], [3])        ->  {1, 2}
    //   ([1, 2], [1, 2, 3])     ->  {}
    //   ([], [1, 2])            ->  {}
    public static Set<Integer> onlyInFirst(int[] a, int[] b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 6
    // ==========================================================
    // The text only has lowercase letters from 'a' to 'z'. Returns the set of letters of the alphabet that do not
    // appear in the text.
    //
    // Examples:
    //   "abcdefghijklmnopqrstuvwxy"            ->  {'z'}
    //   "thequickbrownfoxjumpsoverthelazydog"  ->  {}
    //   "aab"                                  ->  every letter from 'c' to 'z'
    //   ""                                     ->  all the 26 letters
    public static Set<Character> missingLetters(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 7
    // ==========================================================
    // The values are not negative. Reading the array from left to right, returns the first value that was already
    // seen before. If no value is repeated, returns -1.
    //
    // Examples:
    //   [3, 1, 2, 1, 3]  ->  1
    //   [1, 2, 3]        ->  -1
    //   [5, 5]           ->  5
    //   []               ->  -1
    public static int firstRepeated(int[] nums) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 8
    // ==========================================================
    // Returns a new array without the repeated words. Each word stays in the position of its first appearance, and the
    // other words keep their relative order. The case matters.
    //
    // Examples:
    //   ["b", "a", "b", "c", "a"]  ->  ["b", "a", "c"]
    //   ["a", "A", "a"]            ->  ["a", "A"]
    //   ["x", "x", "x"]            ->  ["x"]
    //   []                         ->  []
    public static String[] uniqueInOrder(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 9
    // ==========================================================
    // Returns true if every value of small also appears in big. Repeated values do not matter, and an empty array
    // is a subset of any array.
    //
    // Examples:
    //   ([1, 2], [3, 2, 1])  ->  true
    //   ([1, 1], [1])        ->  true
    //   ([1, 4], [1, 2, 3])  ->  false
    //   ([], [])             ->  true
    public static boolean isSubset(int[] small, int[] big) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 10
    // ==========================================================
    // Returns the set of values that appear in exactly one of the two arrays: in the first or in the second one, but
    // not in both.
    //
    // Examples:
    //   ([1, 2, 3], [3, 4])  ->  {1, 2, 4}
    //   ([1, 1], [1])        ->  {}
    //   ([1], [2])           ->  {1, 2}
    //   ([], [5])            ->  {5}
    public static Set<Integer> symmetricDifference(int[] a, int[] b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
