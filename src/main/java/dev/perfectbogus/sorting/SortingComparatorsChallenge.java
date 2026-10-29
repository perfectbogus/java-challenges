package dev.perfectbogus.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class SortingComparatorsChallenge {

    // CHALLENGE 1
    // Returns a new array with the same numbers in ascending order (the smallest first). The array received is not
    // modified.
    // Example: sortedAscending([3, 1, 2])   returns [1, 2, 3]
    //          sortedAscending([5, 5, -1])  returns [-1, 5, 5]
    //          sortedAscending([])          returns []
    public static int[] sortedAscending(int[] numbers) {
        int[] result = numbers.clone();
        Arrays.sort(result);
        return result;
    }

    public static int[] sortedAscendingStream(int[] numbers) {
        return Arrays.stream(numbers).sorted().toArray();
    }

    // CHALLENGE 2
    // Returns a new array with the same numbers in descending order (the greatest first). The array received is not
    // modified. The numbers can be any int value, including Integer.MIN_VALUE and Integer.MAX_VALUE.
    // Example: sortedDescending([3, 1, 2])   returns [3, 2, 1]
    //          sortedDescending([5, 5, -1])  returns [5, 5, -1]
    //          sortedDescending([])          returns []
    public static int[] sortedDescending(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // CHALLENGE 3
    // Returns a new array with the numbers ordered by their absolute value, from the smallest to the greatest. If two
    // numbers have the same absolute value, they keep the same order they have in numbers. The array received is not
    // modified. The numbers are never Integer.MIN_VALUE.
    // Example: sortedByAbsolute([-3, 2, -1, 3])  returns [-1, 2, -3, 3]
    //          sortedByAbsolute([3, -3, 3])      returns [3, -3, 3]
    //          sortedByAbsolute([])              returns []
    public static int[] sortedByAbsolute(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // CHALLENGE 4
    // Returns a new array with all the even numbers first and all the odd numbers after them. Inside each of the two
    // groups the numbers are in ascending order. Negative numbers can be even or odd too. The array received is not
    // modified.
    // Example: evensFirst([5, 2, 7, 4, 1])  returns [2, 4, 1, 5, 7]
    //          evensFirst([-3, -2, 0, 3])   returns [-2, 0, -3, 3]
    //          evensFirst([])               returns []
    public static int[] evensFirst(int[] numbers) {
        Comparator<Integer> byEvenFirst = Comparator.comparingInt((Integer n) -> n % 2 != 0 ? 1 : 0);
        Comparator<Integer> byNatural = Comparator.naturalOrder();
        return Arrays.stream(numbers)
                .boxed()
                .sorted(byEvenFirst.thenComparing(byNatural))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static int[] evensFirstHard(int[] numbers) {
        Comparator<Integer> byEvenFirst = (a, b) -> {
            if (a % 2 == 0 && b % 2 == 0) {
                return Integer.compare(a, b);
            } else if (a % 2 == 0 && b % 2 != 0) {
                return -1;
            } else if (a % 2 != 0 && b % 2 == 0){
                return 1;
            } else {
                return Integer.compare(a, b);
            }
        };
        return Arrays.stream(numbers)
                .boxed()
                .sorted(byEvenFirst)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // CHALLENGE 5
    // Returns a new array with the numbers ordered by their last digit (0 to 9), from the smallest digit to the
    // greatest. The last digit of a negative number is the last digit of its absolute value (-13 has last digit 3).
    // If two numbers have the same last digit, the smaller number goes first. The array received is not modified.
    // Example: sortedByLastDigit([13, 21, 32, 40])  returns [40, 21, 32, 13]
    //          sortedByLastDigit([23, 13, 3])       returns [3, 13, 23]
    //          sortedByLastDigit([-13, 2, -2])      returns [-2, 2, -13]
    public static int[] sortedByLastDigit(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a comparator for arrays of ints that compares two arrays by their first element only: the array with the
    // smaller first element is smaller. If both first elements are equal, the comparator returns 0. The arrays always
    // have at least one element. The first elements can be any int value, including Integer.MIN_VALUE and
    // Integer.MAX_VALUE.
    // Example: byFirstElement().compare([1, 9], [2, 0])  returns a negative number
    //          byFirstElement().compare([5], [3, 8])     returns a positive number
    //          byFirstElement().compare([4, 1], [4, 7])  returns 0
    public static Comparator<int[]> byFirstElement() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns a new array with the rows ordered by the sum of their numbers, from the greatest sum to the smallest.
    // If two rows have the same sum, they keep the same order they have in rows. A row can be empty (its sum is 0).
    // The sums always fit in an int. The array received is not modified (neither the outer array nor the rows).
    // Example: sortRowsBySum([[1, 1], [5], [2, 2, 2]])  returns [[2, 2, 2], [5], [1, 1]]
    //          sortRowsBySum([[3], [1, 2], [0]])        returns [[3], [1, 2], [0]]
    //          sortRowsBySum([])                        returns []
    public static int[][] sortRowsBySum(int[][] rows) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a new array with the rows ordered by their length, the shortest first. If two rows have the same length,
    // the one with the smaller first element goes first. If they also have the same first element, they keep the same
    // order they have in rows. The rows always have at least one element, and the first elements can be any int
    // value. The array received is not modified.
    // Example: sortRowsByLengthThenFirst([[5, 5], [9], [1, 1], [2]])  returns [[2], [9], [1, 1], [5, 5]]
    //          sortRowsByLengthThenFirst([[1, 2], [1, 1]])            returns [[1, 2], [1, 1]]
    //          sortRowsByLengthThenFirst([])                          returns []
    public static int[][] sortRowsByLengthThenFirst(int[][] rows) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Each row of pairs has exactly two numbers [a, b]. Returns a new array with the pairs ordered by a, from the
    // smallest to the greatest. If two pairs have the same a, the one with the greater b goes first. If both numbers
    // are equal, the pairs keep the same order they have in pairs. The numbers can be any int value. The array
    // received is not modified.
    // Example: sortedPairs([[2, 1], [1, 5], [2, 9], [1, 7]])  returns [[1, 7], [1, 5], [2, 9], [2, 1]]
    //          sortedPairs([[3, 3]])                          returns [[3, 3]]
    //          sortedPairs([])                                returns []
    public static int[][] sortedPairs(int[][] pairs) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the positions (indices) of the values, ordered by the value that is at each position, from the smallest
    // value to the greatest. If two positions have the same value, the smaller position goes first. The values can be
    // any int value. The array received is not modified.
    // Example: indicesSortedByValue([30, 10, 20])  returns [1, 2, 0]
    //          indicesSortedByValue([5, 5, 1])     returns [2, 0, 1]
    //          indicesSortedByValue([])            returns []
    public static int[] indicesSortedByValue(int[] values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
