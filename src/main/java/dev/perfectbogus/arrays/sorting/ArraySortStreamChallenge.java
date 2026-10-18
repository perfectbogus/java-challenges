package dev.perfectbogus.arrays.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySortStreamChallenge {

    // CHALLENGE 1
    // Returns a new array with the numbers sorted from the smallest to the greatest. numbers is not modified.
    // Example: sortedAscending([5, 3, 9, 1]) returns [1, 3, 5, 9]
    //          sortedAscending([2, 2, -4]) returns [-4, 2, 2]
    //          sortedAscending([])         returns []
    public static int[] sortedAscending(int[] numbers) {
        return Arrays.stream(numbers).sorted().toArray();
    }

    // CHALLENGE 2
    // Returns a new array with the numbers sorted from the greatest to the smallest. numbers is not modified.
    // Example: sortedDescending([5, 3, 9, 1]) returns [9, 5, 3, 1]
    //          sortedDescending([2, 2, -4]) returns [2, 2, -4]
    //          sortedDescending([])         returns []
    public static int[] sortedDescending(int[] numbers) {
        return Arrays.stream(numbers).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
    }

    // CHALLENGE 3
    // Returns a new array with the words sorted from the shortest to the longest. Words with the same length stay in
    // the same order they have in words. words is not modified.
    // Example: sortedByLength([pear, fig, apple, kiwi]) returns [fig, pear, kiwi, apple]
    //          sortedByLength([bb, aa, c])              returns [c, bb, aa]
    public static String[] sortedByLength(String[] words) {
        return Arrays.stream(words).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
    }

    // CHALLENGE 4
    // Returns a new array with the words in alphabetical order, ignoring upper and lower case. Words that are equal
    // when the case is ignored stay in the same order they have in words. words is not modified.
    // Example: sortedIgnoringCase([banana, Apple, cherry]) returns [Apple, banana, cherry]
    //          sortedIgnoringCase([b, A, B, a])            returns [A, a, b, B]
    public static String[] sortedIgnoringCase(String[] words) {
        return Arrays.stream(words).sorted(String::compareToIgnoreCase).toArray(String[]::new);
    }

    // CHALLENGE 5
    // Returns a new array with the numbers sorted by absolute value, from the smallest to the greatest. If two numbers
    // have the same absolute value, the smaller number goes first. The values are small, so they never overflow when
    // their sign is changed. numbers is not modified.
    // Example: sortedByAbsoluteValue([3, -1, -3, 2]) returns [-1, 2, -3, 3]
    //          sortedByAbsoluteValue([-5, 4, 0])     returns [0, 4, -5]
    public static int[] sortedByAbsoluteValue(int[] numbers) {
        Comparator<Integer> byAbsValue = Comparator.comparingInt(Math::abs);
        Comparator<Integer> byAsc = Comparator.naturalOrder();
        return Arrays.stream(numbers).boxed().sorted(byAbsValue.thenComparing(byAsc)).mapToInt(Integer::intValue).toArray();
    }

    // CHALLENGE 6
    // Returns the three greatest numbers, from the greatest to the smallest. Repeated numbers count as many times as
    // they appear. If there are fewer than three numbers, returns all of them, also from the greatest to the smallest.
    // numbers is not modified.
    // Example: topThree([4, 9, 1, 7, 3]) returns [9, 7, 4]
    //          topThree([5, 5, 5, 1])    returns [5, 5, 5]
    //          topThree([2, 8])          returns [8, 2]
    public static int[] topThree(int[] numbers) {
        return Arrays.stream(numbers).boxed().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }

    // CHALLENGE 7
    // Returns a new array with the numbers sorted from the smallest to the greatest, without repeated numbers.
    // Example: sortedDistinct([4, 1, 4, 3, 1]) returns [1, 3, 4]
    //          sortedDistinct([7, 7, 7])        returns [7]
    //          sortedDistinct([])               returns []
    public static int[] sortedDistinct(int[] numbers) {
        return Arrays.stream(numbers).sorted().distinct().toArray();
    }

    // CHALLENGE 8
    // Returns a new array with the words sorted from the longest to the shortest. Words with the same length are in
    // alphabetical order (the normal order of Strings). words is not modified.
    // Example: sortedByLengthThenAlphabet([fig, apple, kiwi, pear, plum]) returns [apple, kiwi, pear, plum, fig]
    //          sortedByLengthThenAlphabet([b, a, cc])                      returns [cc, a, b]
    public static String[] sortedByLengthThenAlphabet(String[] words) {
        Comparator<String> byLengthDesc = Comparator.comparing(String::length, Comparator.reverseOrder());
        Comparator<String> byAlpha = Comparator.naturalOrder();
        return Arrays.stream(words).sorted(byLengthDesc.thenComparing(byAlpha)).toArray(String[]::new);
    }

    // CHALLENGE 9
    // Returns a new array with all the even numbers first and then all the odd numbers. The even numbers are sorted
    // from the smallest to the greatest, and so are the odd numbers. Negative numbers can be even or odd too (-4 is
    // even, -3 is odd). numbers is not modified.
    // Example: evensThenOdds([5, 2, 8, 3, 1, 4]) returns [2, 4, 8, 1, 3, 5]
    //          evensThenOdds([-3, -2, 7, 0])      returns [-2, 0, -3, 7]
    public static int[] evensThenOdds(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns true when every number is less than or equal to the next one. An empty array and an array with one
    // number are sorted.
    // Example: isSortedAscending([1, 2, 2, 5]) returns true
    //          isSortedAscending([1, 3, 2])    returns false
    //          isSortedAscending([])           returns true
    public static boolean isSortedAscending(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns the second greatest different value of numbers. If numbers has fewer than two different values,
    // returns null.
    // Example: secondLargest([5, 9, 9, 1]) returns 5
    //          secondLargest([4, 4, 4])    returns null
    //          secondLargest([-7, -2])     returns -7
    //          secondLargest([])           returns null
    public static Integer secondLargest(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // The text has whole numbers separated by commas, with spaces allowed before and after each number. Returns the
    // numbers sorted from the smallest to the greatest, as numbers (not as text). An empty text, or a text with only
    // spaces, gives an empty array.
    // Example: sortedNumbersFromText("10, 3, 25,1") returns [1, 3, 10, 25]
    //          sortedNumbersFromText(" -4 , 7 ")    returns [-4, 7]
    //          sortedNumbersFromText("")            returns []
    public static int[] sortedNumbersFromText(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
