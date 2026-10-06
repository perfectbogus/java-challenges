package dev.perfectbogus.sorting;

import java.util.Comparator;
import java.util.List;

public class SortingComparatorsIntermediateChallenge {

    public record Employee(String name, String department, int level, double salary) {
    }

    // Unless a challenge says otherwise, every method that receives a list must leave it unchanged (same elements in
    // the same order) and return a NEW list. The input lists may be immutable.

    // CHALLENGE 1
    // Sorts the employees by department ascending (plain String order, case-sensitive), then by level DESCENDING,
    // then by salary DESCENDING, then by name ascending (plain String order). Salaries are doubles and may differ by
    // less than 1 (for example 100.25 and 100.75). Employees that are equal on all four fields keep their original
    // relative order.
    public static List<Employee> sortEmployees(List<Employee> employees) {
        Comparator<Employee> byDept = Comparator.comparing(Employee::department);
        Comparator<Employee> byLevelDesc = Comparator.comparingInt(Employee::level).reversed();
        Comparator<Employee> bySalaryDesc = Comparator.comparingDouble(Employee::salary).reversed();
        Comparator<Employee> byName = Comparator.comparing(Employee::name);
        return employees.stream().sorted(
                byDept.thenComparing(byLevelDesc).thenComparing(bySalaryDesc).thenComparing(byName)
        ).toList();
    }

    // CHALLENGE 2
    // Every name has the form "First Last" or "First Middle Last" (words separated by single spaces, any number of
    // middle words). The last name is the last word and the first name is everything before it ("Mary Ann Smith" has
    // first name "Mary Ann" and last name "Smith"). A name made of a single word has that word as its last name and
    // an empty first name. Sorts by last name ascending, then by first name ascending, both ignoring case. Names that
    // are equal on both (ignoring case) keep their original relative order.
    public static List<String> sortByLastNameThenFirstName(List<String> fullNames) {
        Comparator<String> byLastName = (a, b) -> reformatName(a).compareToIgnoreCase(reformatName(b));
        return fullNames.stream().sorted(byLastName).toList();
    }

    private static String reformatName(String fullName) {
        int idx = fullName.lastIndexOf(" ");
        if (idx == -1) {
            return fullName;
        } else {
            return fullName.substring(idx + 1) + " " + fullName.substring(0, idx);
        }
    }

    // CHALLENGE 3
    // Sorts the numbers in DESCENDING order and places every null at the end of the result (nulls are never at the
    // start, and the non-null numbers are still in descending order). All int values are possible, including
    // Integer.MIN_VALUE and Integer.MAX_VALUE. The number of nulls in the result equals the number in the input.
    public static List<Integer> sortDescendingNullsLast(List<Integer> numbers) {
        Comparator<Integer> byDescAndNullsEnd = Comparator.nullsLast(Comparator.reverseOrder());
        return numbers.stream().sorted(byDescAndNullsEnd).toList();
    }

    // CHALLENGE 4
    // Returns the words sorted ascending ignoring case, where words that are equal ignoring case appear only once.
    // The spelling that is kept for each group is the one that occurs FIRST in the input. The input contains no
    // nulls.
    public static List<String> sortedDistinctIgnoringCase(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // The items that also appear in pinned (compared with equals, so case-sensitive) come first, in the order in
    // which they appear in pinned; if an item occurs several times in items, all its copies stay together at its
    // position. Every other item follows, sorted ascending ignoring case, with items that are equal ignoring case
    // keeping their original relative order. pinned has no duplicates, and entries of pinned that do not occur in
    // items are ignored.
    public static List<String> sortWithPinnedFirst(List<String> items, List<String> pinned) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // alphabet lists the letters of an invented alphabet from the smallest to the largest (every letter appears once
    // in it, and every character of every word is in it). Sorts the words in dictionary order according to that
    // alphabet: the words are compared character by character using the position of each character in alphabet, and
    // a word that is a proper prefix of another one comes first. Equal words keep their original relative order.
    public static List<String> sortByCustomAlphabet(List<String> words, String alphabet) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns each distinct word once (words are compared with equals, so case-sensitive), ordered by how many times
    // it occurs in the input, most frequent first; words that occur the same number of times are ordered ascending in
    // plain String order.
    public static List<String> sortByFrequency(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Every point is an int[] {x, y} with |x| and |y| at most 2_000_000_000. Sorts the points by their exact distance
    // to the origin (0, 0), nearest first. Points at exactly the same distance are ordered by x ascending and then
    // by y ascending, and identical points keep their original relative order.
    public static List<int[]> sortPointsByDistanceFromOrigin(List<int[]> points) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a comparator that orders int arrays by the sum of all their elements, ascending. The elements can be
    // any int values, so the sum of one array may be outside the range of int. An empty array has sum 0. Arrays with
    // the same sum compare as equal (return 0), even if their contents differ. The comparator must obey the usual
    // contract: compare(a, b) and compare(b, a) have opposite signs, and the ordering is transitive.
    public static Comparator<int[]> bySumAscending() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Every string is a non-empty sequence of decimal digits, of ANY length (far more digits than a long can hold),
    // and may have leading zeros ("007" has the value 7). Sorts the strings ascending by their numeric value.
    // Strings with the same numeric value ("7", "007", "0007") keep their original relative order and their original
    // spelling.
    public static List<String> sortNumericStrings(List<String> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Every version is one or more non-negative integers (up to 9 digits each) separated by single dots, like "2",
    // "1.10" or "1.2.3.4". Sorts them ascending by comparing the segments numerically from left to right ("1.10"
    // comes after "1.9"). A missing segment counts as 0, so "1.0" and "1.0.0" are equal; versions that are equal
    // keep their original relative order and spelling.
    public static List<String> sortVersions(List<String> versions) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns a comparator for "natural" ordering of names such as "file2" and "file10". Each string is split into
    // maximal runs of digits and maximal runs of non-digits ("a12bc" -> "a", "12", "bc"). Two strings are compared run
    // by run from the left: two digit runs compare by numeric value (any length, leading zeros ignored); two non-digit
    // runs compare ignoring case; when the runs are of different kinds the digit run comes first. If one string has
    // run left over after all the runs of the other were equal, the string with fewer runs comes first. If the
    // strings are still tied, fall back to String.compareTo, so only identical strings compare as 0. The comparator
    // must obey the usual contract (opposite signs when swapped, transitive).
    public static Comparator<String> alphanumericComparator() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Sorts the employees by the given keys, applied in order. A key is one of "name", "department", "level" or
    // "salary", optionally prefixed with "-" to sort that key in descending order ("-salary"). Strings compare in
    // plain String order, numbers numerically. Employees tied on all the keys keep their original relative order,
    // and an empty key list returns the employees in their original order. If any key (after removing its optional
    // "-") is not one of the four names, throws IllegalArgumentException, even when the list is empty or has one
    // element.
    public static List<Employee> sortByKeys(List<Employee> employees, List<String> keys) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Every inner list is already sorted according to comparator. Returns one list with all the elements of all the
    // lists, sorted according to comparator. When two elements compare as equal, the one that comes from the list
    // with the lower index goes first; two equal elements of the same list keep their order. The inner lists may be
    // empty or immutable, the outer list may be empty, and none of them may be modified.
    public static <T> List<T> mergeSorted(List<List<T>> sortedLists, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns the k greatest elements according to comparator, greatest first. The result must be exactly the first k
    // elements of the input after sorting it in descending order with a stable sort (elements that compare as equal
    // keep their original relative order). If k is larger than the list size, all the elements are returned; k = 0
    // returns an empty list; a negative k throws IllegalArgumentException. The input list must not be modified. The
    // list can contain hundreds of thousands of elements while k is tiny, so the number of calls to comparator.compare
    // must stay far below what sorting the whole list would need.
    public static <T> List<T> topK(List<T> items, int k, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}