package dev.perfectbogus.streams;

import java.util.List;
import java.util.Optional;

public class StreamPracticeChallenge {

    // ==========================================================
    // EASY
    // ==========================================================

    // CHALLENGE 1
    // Returns the squares of the odd numbers in nums, in the same order as in
    // nums. Negative numbers can be odd too (-3 is odd). Does not modify nums.
    public static List<Integer> squaresOfOdds(List<Integer> nums) {
        return nums.stream().filter(n -> n % 2 != 0).map(n -> n * n).toList();
    }

    // CHALLENGE 2
    // Returns the first three words of words that have at least minLength
    // characters, in the same order as in words. If fewer than three words
    // qualify, returns all that do.
    public static List<String> firstThreeLongWords(List<String> words, int minLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns one page of items. Pages are numbered from 1 and each page holds
    // pageSize items taken in order (page 1 holds the first pageSize items,
    // page 2 the next pageSize, and so on). The last page may hold fewer
    // items. A page that starts after the end of items is an empty list.
    // page and pageSize are always at least 1.
    public static List<String> pageOf(List<String> items, int page, int pageSize) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns true if every number in nums is between min and max, both ends
    // included. Returns true if nums is empty.
    public static boolean allWithinRange(List<Integer> nums, int min, int max) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the first word of words (in list order) that starts with prefix.
    // The comparison is case-sensitive. Returns an empty Optional if no word
    // matches.
    public static Optional<String> firstWithPrefix(List<String> words, String prefix) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns the sum of all positive integers strictly below limit that are
    // multiples of 3 or multiples of 5 (a number that is a multiple of both is
    // counted once). Returns 0 if limit is 0 or negative.
    public static long sumOfMultiples(int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns all the numbers of a and b together, without repeated values,
    // sorted in ascending order. Does not modify a or b.
    public static List<Integer> mergeDistinctSorted(List<Integer> a, List<Integer> b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the words sorted by length, shortest first. Words of the same
    // length are ordered alphabetically (natural String order). Does not modify
    // words.
    public static List<String> sortByLengthThenAlphabetical(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns how many different letters appear in text. Upper and lower case
    // versions of a letter count as the same letter. Characters that are not
    // letters (digits, spaces, punctuation) are ignored.
    public static long distinctLetterCount(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the first count multiples of base, starting with base itself:
    // base, 2*base, 3*base, ... Returns an empty list if count is 0.
    public static List<Integer> firstMultiples(int base, int count) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // INTERMEDIATE
    // ==========================================================

    // CHALLENGE 11
    // Returns the running totals of nums: the element at position i of the
    // result is the sum of nums[0] through nums[i]. The result has the same
    // size as nums. Does not modify nums.
    public static List<Integer> runningTotals(List<Integer> nums) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Splits every sentence into words (words are separated by one or more
    // whitespace characters; leading and trailing whitespace and blank
    // sentences produce no words; punctuation stays part of the word) and
    // returns the distinct words converted to lower case, sorted
    // alphabetically.
    public static List<String> uniqueWordsSorted(List<String> sentences) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns every pair of numbers of nums, taken from two different
    // positions i < j, whose sum is target. Each pair is a list of two
    // elements [nums[i], nums[j]]. The pairs are ordered by i and then by j.
    // Equal values at different positions form separate pairs.
    public static List<List<Integer>> pairsWithSum(List<Integer> nums, int target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns the average of every window of size consecutive numbers of nums,
    // moving one position at a time. The result has nums.size() - size + 1
    // elements. If size is larger than nums.size(), returns an empty list.
    // size is always at least 1.
    public static List<Double> windowAverages(List<Integer> nums, int size) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns the k most frequent words of words, most frequent first. Words
    // with the same frequency are ordered alphabetically (natural String
    // order). If there are fewer than k distinct words, returns all of them.
    public static List<String> topKFrequentWords(List<String> words, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
