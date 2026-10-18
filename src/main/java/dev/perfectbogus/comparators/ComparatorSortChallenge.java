package dev.perfectbogus.comparators;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSortChallenge {

    // Used by challenges 7 and 8. Write a student as name(grade) in the examples, for example Ana(70).
    public record Student(String name, int grade) {
    }

    // CHALLENGE 1
    // Sorts numbers in place, from the smallest to the greatest. The same array that was received ends up sorted,
    // nothing is returned.
    // Example: numbers = [5, 3, 9, 1]  after sortInPlace(numbers), numbers is [1, 3, 5, 9]
    //          numbers = [2, 2, -4]    after sortInPlace(numbers), numbers is [-4, 2, 2]
    public static void sortInPlace(int[] numbers) {
        Arrays.sort(numbers);
    }

    // CHALLENGE 2
    // Sorts, in place and from the smallest to the greatest, only the positions from "from" (included) to "to"
    // (excluded). The numbers in the other positions do not change. from and to are always valid positions, and from is
    // never greater than to.
    // Example: numbers = [9, 8, 7, 6, 5], sortRange(numbers, 1, 4) leaves numbers as [9, 6, 7, 8, 5]
    //          numbers = [4, 3, 2, 1],    sortRange(numbers, 0, 4) leaves numbers as [1, 2, 3, 4]
    //          numbers = [4, 3, 2, 1],    sortRange(numbers, 2, 2) leaves numbers as [4, 3, 2, 1]
    public static void sortRange(int[] numbers, int from, int to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a String with the characters of text sorted by their char value (the normal order of chars, so all the
    // uppercase letters come before the lowercase ones).
    // Example: sortedLetters("banana") returns "aaabnn"
    //          sortedLetters("Dog")    returns "Dgo"
    //          sortedLetters("")       returns ""
    public static String sortedLetters(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // sorted is in ascending order and has no repeated numbers. Returns the position of value in sorted. If value is
    // not there, returns the position where it would have to be inserted to keep sorted in ascending order (the
    // position of the first number greater than value, or the length of sorted if there is none). sorted is not
    // modified.
    // Example: positionToInsert([10, 20, 30, 40], 30) returns 2
    //          positionToInsert([10, 20, 30, 40], 25) returns 2
    //          positionToInsert([10, 20, 30, 40], 5)  returns 0
    //          positionToInsert([10, 20, 30, 40], 99) returns 4
    //          positionToInsert([], 7)                returns 0
    public static int positionToInsert(int[] sorted, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the median of numbers: the middle number once they are sorted. If there is an even amount of numbers,
    // the median is the average of the two middle ones. An empty array gives 0.0. numbers is not modified.
    // Example: median([5, 1, 3])    returns 3.0
    //          median([4, 1, 3, 2]) returns 2.5   (sorted: 1, 2, 3, 4  ->  the average of 2 and 3)
    //          median([7])          returns 7.0
    //          median([])           returns 0.0
    public static double median(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a new array with the words sorted by their last character (the normal order of chars). Words with the
    // same last character stay in the same order they have in words. Every word has at least one character. words is
    // not modified.
    // Example: sortedByLastLetter([dog, cat, bird, tea]) returns [tea, bird, dog, cat]
    //          sortedByLastLetter([ab, cb, aa])          returns [aa, ab, cb]
    public static String[] sortedByLastLetter(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns a new array with the students sorted from the highest grade to the lowest. Students with the same grade
    // stay in the same order they have in students. students is not modified.
    // Example: sortedByGrade([Ana(70), Bob(90), Cy(70), Di(85)]) returns [Bob(90), Di(85), Ana(70), Cy(70)]
    public static Student[] sortedByGrade(Student[] students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a new array with the students sorted from the highest grade to the lowest. Students with the same grade
    // are in alphabetical order by name (the normal order of Strings). students is not modified.
    // Example: sortedByGradeThenName([Cy(70), Bob(90), Ana(70), Di(90)]) returns [Bob(90), Di(90), Ana(70), Cy(70)]
    public static Student[] sortedByGradeThenName(Student[] students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a Comparator that puts first the number that is closer to target. If two numbers are equally close to
    // target, the smaller number goes first. The numbers are small, so they never overflow when they are subtracted.
    // Example: with closestTo(10), sorting [4, 12, 9, 15, 10, 8] gives [10, 9, 8, 12, 15, 4]
    //          with closestTo(0),  sorting [-3, 3, -1, 2] gives [-1, 2, -3, 3]
    public static Comparator<Integer> closestTo(int target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a Comparator for Strings that may be null. The Strings that are not null go first, in alphabetical order
    // (the normal order of Strings), and the null ones go last. Two null values are equal. The Comparator never throws.
    // Example: sorting [pear, null, apple, null, fig] gives [apple, fig, pear, null, null]
    //          sorting [b, null, B, a]                gives [B, a, b, null]
    public static Comparator<String> nullsLastAlphabetical() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
