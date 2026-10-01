package dev.perfectbogus.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CollectIntermediateChallenge {

    // Used by CHALLENGE 11, 12, 15. Fully implemented — nothing to do here.
    public static class Employee {
        private final String name;
        private final String department;
        private final double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }

    // CHALLENGE 1
    // Returns the distinct lengths of the words in words, sorted
    // ascending. Does not modify words.
    public static List<Integer> uniqueWordLengthsSorted(List<String> words) {
        return words.stream().map(String::length).distinct().sorted().toList();
    }

    // CHALLENGE 2
    // Returns a single string containing every word in words, in order,
    // separated by ", ".
    public static String joinWithSeparator(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a single string containing every word in words, in order,
    // separated by ", ", wrapped in a leading "[" and a trailing "]".
    public static String joinWithPrefixSuffix(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns the set of distinct first characters among the words in
    // words. Does not modify words.
    public static Set<Character> distinctFirstLetters(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a map from each word in words to its length. words is
    // guaranteed to contain no duplicate entries.
    public static Map<String, Integer> mapNameToLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a map from each distinct first character among the words in
    // words to how many words start with that character.
    public static Map<Character, Long> countFirstLetterOccurrences(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns a map from each distinct word length present in words to
    // the list of words of that length, each list in the words' original
    // relative order.
    public static Map<Integer, List<String>> groupWordsByLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Same grouping as CHALLENGE 7 (word length -> list of words of that
    // length, each list in original relative order), but the returned
    // map must itself iterate its keys in ascending numeric order.
    public static Map<Integer, List<String>> groupWordsByLengthSortedKeys(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a map from each distinct first character among the words in
    // words to the list of lengths of the words starting with that
    // character, each list in the words' original relative order.
    public static Map<Character, List<Integer>> groupLengthsByFirstLetter(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a map with two entries: key true maps to the list of words
    // in words with even length, and key false maps to the list of words
    // with odd length, each list in the words' original relative order.
    public static Map<Boolean, List<String>> partitionByEvenLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns summary statistics (count, sum, min, max, average) over the
    // salaries of employees.
    public static DoubleSummaryStatistics summarizeSalaries(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns a map from each distinct department among employees to the
    // sum of the salaries of employees in that department.
    public static Map<String, Double> totalSalaryByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns an Optional containing the longest word in words (if two
    // words tie for longest, either is acceptable), or an empty Optional
    // if words is empty.
    public static Optional<String> longestWordReducing(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns the distinct words in words, sorted alphabetically, as an
    // unmodifiable list (attempting to modify the returned list must
    // throw UnsupportedOperationException). Does not modify words.
    public static List<String> collectToImmutableSortedList(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns a single string of the exact form
    // "count=<n>, average=<avg>" where <n> is the number of employees and
    // <avg> is the average of their salaries formatted to exactly two
    // decimal places (e.g. "count=3, average=60000.00"), computed in a
    // single pass over employees.
    public static String averageAndCountTeeing(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
