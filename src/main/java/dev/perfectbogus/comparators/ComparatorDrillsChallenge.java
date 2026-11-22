package dev.perfectbogus.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ComparatorDrillsChallenge {

    // Used in challenges 6, 7 and 8. Do not change it.
    public record Student(String name, int grade) {
    }

    // In every challenge that returns a list, the result is a new list and the original is not modified.

    // ==========================================================
    // CHALLENGE 1
    // ==========================================================
    // Returns the words ordered by length, the shortest first. Words with the same length keep the order they had in
    // the original list.
    //
    // Examples:
    //   ["ccc", "a", "bb"]  ->  ["a", "bb", "ccc"]
    //   ["bb", "aa", "c"]   ->  ["c", "bb", "aa"]
    //   []                  ->  []
    public static List<String> sortByLength(List<String> words) {
        return words.stream().sorted(Comparator.comparingInt(String::length)).toList();
    }

    // ==========================================================
    // CHALLENGE 2
    // ==========================================================
    // Returns the words ordered by length, the longest first. Words with the same length keep the order they had in
    // the original list.
    //
    // Examples:
    //   ["a", "bb", "ccc"]  ->  ["ccc", "bb", "a"]
    //   ["bb", "aa", "c"]   ->  ["bb", "aa", "c"]
    //   ["aa", "b", "cc"]   ->  ["aa", "cc", "b"]
    public static List<String> sortLongestFirst(List<String> words) {
        return words.stream().sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).toList();
    }

    // ==========================================================
    // CHALLENGE 3
    // ==========================================================
    // Returns the words in alphabetical order ignoring the case. Words that are equal ignoring the case keep the
    // order they had in the original list.
    //
    // Examples:
    //   ["b", "A", "a", "B"]               ->  ["A", "a", "b", "B"]
    //   ["Banana", "apple", "Cherry"]      ->  ["apple", "Banana", "Cherry"]
    //   ["a", "A"]                         ->  ["a", "A"]
    public static List<String> sortIgnoringCase(List<String> words) {
        return words.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
    }

    // ==========================================================
    // CHALLENGE 4
    // ==========================================================
    // Returns the numbers ordered by their absolute value, the smallest first. If two numbers have the same absolute
    // value, the smaller number goes first. The numbers are between -1,000,000 and 1,000,000.
    //
    // Examples:
    //   [-3, 2, -1, 3, 1]  ->  [-1, 1, 2, -3, 3]
    //   [5, -5]            ->  [-5, 5]
    //   [0, -1]            ->  [0, -1]
    public static List<Integer> sortByAbsoluteValue(List<Integer> nums) {
        return nums.stream().sorted(Comparator.<Integer>comparingInt(Math::abs).thenComparingInt(i -> i)).toList();
    }

    // ==========================================================
    // CHALLENGE 5
    // ==========================================================
    // Returns the numbers with the even ones first and then the odd ones. Inside each group the numbers are in
    // ascending order. Zero is even, and negative numbers can be even or odd too (-2 is even, -3 is odd).
    //
    // Examples:
    //   [3, 2, 1, 4]       ->  [2, 4, 1, 3]
    //   [-3, -2, -1, 0]    ->  [-2, 0, -3, -1]
    //   [5]                ->  [5]
    public static List<Integer> sortEvenBeforeOdd(List<Integer> nums) {
        return nums.stream().sorted(Comparator.comparing((Integer i) -> i % 2 != 0).thenComparing(Comparator.naturalOrder())).toList();
    }

    // ==========================================================
    // CHALLENGE 6
    // ==========================================================
    // Returns the students ordered by grade, the highest first. Students with the same grade are ordered
    // alphabetically by name (the normal comparison of texts).
    //
    // Examples (name grade):
    //   [ana 80, bob 90, cy 80]  ->  [bob 90, ana 80, cy 80]
    //   [cy 80, ana 80]          ->  [ana 80, cy 80]
    //   []                       ->  []
    public static List<Student> sortByGradeDescending(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::grade, Comparator.reverseOrder()).thenComparing(Student::name))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 7
    // ==========================================================
    // Returns a comparator that orders students by grade, the lowest first. Students with the same grade are ordered
    // alphabetically by name. It must work as a normal comparator: negative when the first student goes before the
    // second one, positive when it goes after, and 0 when both are equal.
    //
    // Examples (name grade):
    //   compare(ana 80, bob 90)  ->  a negative number
    //   compare(bob 80, ana 80)  ->  a positive number
    //   compare(ana 80, ana 80)  ->  0
    //   sorting [bob 90, ana 80, cy 80] with it gives [ana 80, cy 80, bob 90]
    public static Comparator<Student> byGradeThenName() {
        return Comparator.comparingInt(Student::grade).thenComparing(Student::name);
    }

    // ==========================================================
    // CHALLENGE 8
    // ==========================================================
    // The list has at least one student. Returns the student with the highest grade. If several students have the
    // highest grade, returns the one whose name comes first alphabetically. The list is not modified.
    //
    // Examples (name grade):
    //   [ana 80, bob 90, cy 90]  ->  bob 90
    //   [ana 70]                 ->  ana 70
    //   [cy 85, ana 85]          ->  ana 85
    public static Student topStudent(List<Student> students) {
        return Collections.min(
                students,
                Comparator.comparing(Student::grade, Comparator.reverseOrder()).thenComparing(Student::grade)
        );
    }

    public static Student topStudentSimplified(List<Student> students) {
        return students.stream()
                .min(Comparator.comparing(Student::grade, Comparator.reverseOrder()).thenComparing(Student::name))
                .get();
    }

    public static Student topStudentExtended(List<Student> students) {
        List<Student> sorted = students.stream()
                .sorted(Comparator.comparing(Student::grade, Comparator.reverseOrder())
                        .thenComparing(Student::name))
                .toList();
        return sorted.get(0);
    }

    // ==========================================================
    // CHALLENGE 9
    // ==========================================================
    // Every word has at least one character. Returns the words ordered by their last character (by character code).
    // Words that end with the same character keep the order they had in the original list.
    //
    // Examples:
    //   ["ab", "ca", "bb"]  ->  ["ca", "ab", "bb"]
    //   ["xb", "ya", "za"]  ->  ["ya", "za", "xb"]
    //   ["bb", "ab"]        ->  ["bb", "ab"]
    public static List<String> sortByLastLetter(List<String> words) {
        return words.stream().sorted(Comparator.comparing(s -> s.charAt(s.length() - 1))).toList();
    }

    // ==========================================================
    // CHALLENGE 10
    // ==========================================================
    // Every sentence has words separated by single spaces (the empty text has 0 words). Returns the sentences ordered
    // by how many words they have, the fewest first. Sentences with the same number of words keep the order they had.
    //
    // Examples:
    //   ["a b c", "a", "a b"]  ->  ["a", "a b", "a b c"]
    //   ["x y", "a b"]         ->  ["x y", "a b"]
    //   ["a", ""]              ->  ["", "a"]
    public static List<String> sortByWordCount(List<String> sentences) {
        return sentences.stream()
                .sorted(Comparator.comparing(s -> s.isEmpty() ? 0 : s.split(" ").length))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 11
    // ==========================================================
    // The numbers are not negative. Returns them ordered by the sum of their digits, the smallest sum first. Numbers
    // with the same digit sum are ordered from the smallest to the biggest number.
    //
    // Examples:
    //   [19, 28, 10, 100]  ->  [10, 100, 19, 28]
    //   [5, 14]            ->  [5, 14]
    //   [0, 10]            ->  [0, 10]
    public static List<Integer> sortByDigitSum(List<Integer> nums) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12
    // ==========================================================
    // The list can contain null values. Returns the list with the words in alphabetical order (the normal comparison
    // of texts) and all the nulls at the end.
    //
    // Examples:
    //   ["b", null, "a"]  ->  ["a", "b", null]
    //   [null, null]      ->  [null, null]
    //   ["B", "a"]        ->  ["B", "a"]
    //   []                ->  []
    public static List<String> sortNullsLast(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13
    // ==========================================================
    // Returns the keys of the map ordered by their value, the highest value first. Keys with the same value are
    // ordered alphabetically. The map is not modified.
    //
    // Examples:
    //   {"a": 1, "b": 3, "c": 3}  ->  ["b", "c", "a"]
    //   {"x": 5}                  ->  ["x"]
    //   {}                        ->  []
    public static List<String> sortKeysByValue(Map<String, Integer> scores) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14
    // ==========================================================
    // Returns the different words ordered by how many times they appear in the list, the most frequent first. Words
    // that appear the same number of times are ordered alphabetically (the case matters). Each word appears once in
    // the result.
    //
    // Examples:
    //   ["b", "a", "b", "c", "a", "b"]  ->  ["b", "a", "c"]
    //   ["y", "x"]                      ->  ["x", "y"]
    //   []                              ->  []
    public static List<String> sortByFrequency(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15
    // ==========================================================
    // Returns true if the words are already in order according to the comparator: for every pair of neighbours, the
    // first word does not go after the second one. Empty lists and lists with one word are always ordered.
    //
    // Examples:
    //   (["a", "b", "b"], natural order)             ->  true
    //   (["b", "a"], natural order)                  ->  false
    //   (["bb", "a"], comparing by length)           ->  false
    //   (["b", "a"], reverse natural order)          ->  true
    public static boolean isSorted(List<String> words, Comparator<String> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
