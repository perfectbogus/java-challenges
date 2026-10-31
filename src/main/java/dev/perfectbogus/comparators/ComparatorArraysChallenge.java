package dev.perfectbogus.comparators;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorArraysChallenge {

    // A person with a name and an age. The name is never null. You do not have to change this record.
    public record Person(String name, int age) {
    }

    // CHALLENGE 1
    // Returns a new array with the words ordered by their length, the shortest first. If two words have the same
    // length, they keep the same order they have in words. The array received is not modified. No word is null.
    // Example: sortedByLength([ccc, a, bb])    returns [a, bb, ccc]
    //          sortedByLength([bb, aa, c])     returns [c, bb, aa]
    //          sortedByLength([])              returns []
    public static String[] sortedByLength(String[] words) {
        return Arrays.stream(words)
                .sorted(Comparator.comparingInt(String::length))
                .toArray(s -> new String[s]);
    }

    public static String[] sortedByLengthSimplified(String[] words) {
        return Arrays.stream(words)
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);
    }

    // CHALLENGE 2
    // Returns a new array with the words in alphabetical order, ignoring the difference between uppercase and
    // lowercase letters. If two words are equal when the case is ignored, they keep the same order they have in
    // words. The array received is not modified. No word is null.
    // Example: sortedIgnoringCase([b, A, c])     returns [A, b, c]
    //          sortedIgnoringCase([b, A, a, B])  returns [A, a, b, B]
    //          sortedIgnoringCase([])            returns []
    public static String[] sortedIgnoringCase(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a new array with the words ordered by their last character (compared as characters, so any uppercase
    // letter comes before any lowercase letter). If two words have the same last character, they are ordered
    // alphabetically using the normal comparison of texts. No word is empty or null. The array received is not
    // modified.
    // Example: sortedByLastCharacter([ab, ba, cb])  returns [ba, ab, cb]
    //          sortedByLastCharacter([zz, ba, aa])  returns [aa, ba, zz]
    //          sortedByLastCharacter([])            returns []
    public static String[] sortedByLastCharacter(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns a new array with the longest words first. If two words have the same length, they are ordered
    // alphabetically using the normal comparison of texts. No word is null. The array received is not modified.
    // Example: longestFirstThenAlphabetical([bb, a, ccc, aa])  returns [ccc, aa, bb, a]
    //          longestFirstThenAlphabetical([b, a])            returns [a, b]
    //          longestFirstThenAlphabetical([])                returns []
    public static String[] longestFirstThenAlphabetical(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a new array with the people ordered by age, the youngest first. If two people have the same age, they
    // keep the same order they have in people. The array received is not modified. Ages can be any int value.
    // Example: sortedByAge([Ana 30, Bob 20, Cy 25])  returns [Bob 20, Cy 25, Ana 30]
    //          sortedByAge([Ana 30, Bob 30])         returns [Ana 30, Bob 30]
    //          sortedByAge([Bob 30, Ana 30])         returns [Bob 30, Ana 30]
    public static Person[] sortedByAge(Person[] people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a new array with the oldest people first. If two people have the same age, they are ordered by name,
    // alphabetically using the normal comparison of texts. The array received is not modified. Ages can be any int
    // value.
    // Example: sortedByAgeDescThenName([Ana 20, Bob 30, Cy 30])  returns [Bob 30, Cy 30, Ana 20]
    //          sortedByAgeDescThenName([Cy 30, Bob 30])          returns [Bob 30, Cy 30]
    //          sortedByAgeDescThenName([])                       returns []
    public static Person[] sortedByAgeDescThenName(Person[] people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Sorts the array that it receives, in place (the same array changes, nothing is returned), by name in
    // alphabetical order using the normal comparison of texts. If two people have the same name, they keep the same
    // order they have in the array.
    // Example: [Cy 30, Ana 20, Bob 25] becomes [Ana 20, Bob 25, Cy 30]
    //          [Ana 40, Ana 20] stays [Ana 40, Ana 20]
    public static void sortByNameInPlace(Person[] people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a comparator for people: the younger person is smaller. If both have the same age, the person whose
    // name comes first alphabetically (normal comparison of texts) is smaller. If age and name are equal, the
    // comparator returns 0. Ages can be any int value, including Integer.MIN_VALUE and Integer.MAX_VALUE.
    // Example: byAgeThenName().compare(Ana 20, Bob 30)  returns a negative number
    //          byAgeThenName().compare(Bob 20, Ana 20)  returns a positive number
    //          byAgeThenName().compare(Ana 20, Ana 20)  returns 0
    public static Comparator<Person> byAgeThenName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a new array with the words in alphabetical order (the normal comparison of texts, so "B" comes before
    // "a"), with the null elements at the end. There can be several nulls. The array received is not modified.
    // Example: sortedNullsLast([b, null, a])   returns [a, b, null]
    //          sortedNullsLast([null, null])   returns [null, null]
    //          sortedNullsLast([])             returns []
    public static String[] sortedNullsLast(String[] words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a new array with the n youngest people, the youngest first. If two people have the same age, the one
    // whose name comes first alphabetically (normal comparison of texts) goes first. If there are fewer than n
    // people, all of them are returned. n is never negative. The array received is not modified.
    // Example: youngest([Ana 30, Bob 20, Cy 25], 2)  returns [Bob 20, Cy 25]
    //          youngest([Bob 20, Ana 20, Cy 25], 1)  returns [Ana 20]
    //          youngest([Ana 30], 5)                 returns [Ana 30]
    //          youngest([Ana 30], 0)                 returns []
    public static Person[] youngest(Person[] people, int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
