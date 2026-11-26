package dev.perfectbogus.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

record Employee(String name, String department, int age, double salary) {
}

/**
 * 15 easy challenges about sorting with comparators.
 *
 * Unless the text says otherwise, every method returns a NEW list or array, the arguments must not be modified,
 * and when two elements are equal for the sort they keep the order they had in the original (stable sort).
 */
public class SortLabChallenge {

    // ==========================================================
    // CHALLENGE 1: sortByAge
    // ==========================================================
    // Return the employees from the youngest to the oldest.
    //
    // [ana(30), bob(25), cy(25), dan(40)] -> [bob(25), cy(25), ana(30), dan(40)]
    public static List<Employee> sortByAge(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparingInt(Employee::age)).toList();
    }

    // ==========================================================
    // CHALLENGE 2: sortBySalaryDescending
    // ==========================================================
    // Return the employees from the highest salary to the lowest. The salaries can have decimals.
    //
    // [ana(3000), bob(4000.5), cy(4000.25)] -> [bob, cy, ana]
    public static List<Employee> sortBySalaryDescending(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparingDouble(Employee::salary).reversed()).toList();
    }

    // ==========================================================
    // CHALLENGE 3: sortByDepartmentThenName
    // ==========================================================
    // Sort by department in alphabetical order. Inside the same department, sort by name in alphabetical order.
    // Alphabetical order is the normal order of the texts (uppercase letters come before lowercase letters).
    //
    // [ana(Sales), bob(IT), cy(Sales), dan(HR), eve(IT)] -> [dan(HR), bob(IT), eve(IT), ana(Sales), cy(Sales)]
    public static List<Employee> sortByDepartmentThenName(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparing(Employee::department).thenComparing(Employee::name)).toList();
    }

    // ==========================================================
    // CHALLENGE 4: sortByDepartmentThenSalary
    // ==========================================================
    // Sort by department in alphabetical order. Inside the same department, the highest salary goes first.
    // If two employees of the same department have the same salary, sort them by name in alphabetical order.
    //
    // [ana(Sales, 3000), bob(IT, 4000), cy(Sales, 3500.5), eve(IT, 5000)]
    //   -> [eve(IT, 5000), bob(IT, 4000), cy(Sales, 3500.5), ana(Sales, 3000)]
    public static List<Employee> sortByDepartmentThenSalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::department)
                        .thenComparing(Employee::salary, Comparator.reverseOrder())
                                .thenComparing(Employee::name))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 5: sortRowsByColumn
    // ==========================================================
    // Every row has a value in the given column. Return a new array with the same rows, sorted by the value
    // of that column, from the smallest to the biggest. The rows themselves are not copied and not modified.
    // Up to 100,000 rows.
    //
    // ([[3, 1], [1, 2], [2, 0]], 0) -> [[1, 2], [2, 0], [3, 1]]
    // ([[3, 1], [1, 2], [2, 0]], 1) -> [[2, 0], [3, 1], [1, 2]]
    public static int[][] sortRowsByColumn(int[][] rows, int column) {
        return Arrays.stream(rows)
                .sorted(Comparator.comparingInt(row -> row[column]))
                .toArray(int[][]::new);
    }

    // ==========================================================
    // CHALLENGE 6: sortByLengthThenAlphabetical
    // ==========================================================
    // Shorter words first. Words with the same length go in alphabetical order (normal order of the texts).
    //
    // [bb, a, ccc, aa, b] -> [a, b, aa, bb, ccc]
    // [b, A, a, B]        -> [A, B, a, b]
    public static List<String> sortByLengthThenAlphabetical(List<String> words) {
        return words.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 7: sortIgnoringCaseLowerFirst
    // ==========================================================
    // Sort in alphabetical order ignoring the case. If two words are the same ignoring the case, the one that
    // comes LATER in the normal order of the texts goes first (so "a" goes before "A").
    //
    // [b, A, a, B]                              -> [a, A, b, B]
    // [Banana, apple, Apple, cherry, apple]     -> [apple, apple, Apple, Banana, cherry]
    public static List<String> sortIgnoringCaseLowerFirst(List<String> words) {
        Comparator<String> byAlphaIgnore = (a, b) -> {
            int res = a.compareToIgnoreCase(b);
            if (res == 0) {
                return b.compareTo(a);
            }
            return res;
        };
        return words.stream().sorted(byAlphaIgnore).toList();
    }

    public static List<String> sortIgnoringCaseLowerFirstSimplified(List<String> words) {
        Comparator<String> byAlphaIgnoreCase = String.CASE_INSENSITIVE_ORDER;
        Comparator<String> byAlphaDesc = Comparator.reverseOrder();
        return words.stream().sorted(byAlphaIgnoreCase.thenComparing(byAlphaDesc)).toList();
    }

    // ==========================================================
    // CHALLENGE 8: sortEvenBeforeOdd
    // ==========================================================
    // Even numbers first, then odd numbers. Inside each group, from the smallest to the biggest.
    // The numbers can be negative (-2 is even and -3 is odd).
    //
    // [5, 2, 8, 1, 4, 3]       -> [2, 4, 8, 1, 3, 5]
    // [-3, -2, -1, 0, 1, 2]    -> [-2, 0, 2, -3, -1, 1]
    public static List<Integer> sortEvenBeforeOdd(List<Integer> numbers) {
        return numbers.stream()
                .sorted(Comparator.comparing((Integer n) -> n % 2 != 0).thenComparing(Comparator.naturalOrder()))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 9: sortNullsFirstThenLength
    // ==========================================================
    // The list can have null values. The nulls go first. Then the words, shorter first.
    // Words with the same length go in alphabetical order (normal order of the texts).
    //
    // [bb, null, a, null, ccc, aa]  -> [null, null, a, aa, bb, ccc]
    public static List<String> sortNullsFirstThenLength(List<String> words) {
        return words.stream()
                .sorted(Comparator.nullsFirst(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder())))
                .toList();
    }

    // ==========================================================
    // CHALLENGE 10: oldest
    // ==========================================================
    // Return the oldest employee. If several have the same (biggest) age, return the first of them in the list.
    // The list is not empty. Do not modify it.
    //
    // [ana(30), bob(25), dan(40), eve(40)] -> dan
    // [ana(30), eve(30)]                   -> ana
    public static Employee oldest(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 11: topEarners
    // ==========================================================
    // Return the n employees with the highest salary, the highest first.
    // Employees with the same salary go in alphabetical order by name.
    // 0 <= n <= employees.size(). Up to 100,000 employees.
    //
    // ([ana(3000), bob(4000), cy(3500.5), dan(3000), eve(5000)], 4) -> [eve, bob, cy, ana]
    public static List<Employee> topEarners(List<Employee> employees, int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12: mergeSortedLists
    // ==========================================================
    // Both lists are already sorted with the given comparator. Return one list with all the words,
    // sorted with that comparator. If a word of a and a word of b are equal for the comparator,
    // the word of a goes first. Up to 100,000 words in each list.
    //
    // ([a, c, e], [b, d], natural order)                    -> [a, b, c, d, e]
    // ([c, a], [d, b], reverse order)                       -> [d, c, b, a]
    // ([a, C], [B, c], ignoring the case)                   -> [a, B, C, c]
    public static List<String> mergeSortedLists(List<String> a, List<String> b, Comparator<String> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13: isSortedBy
    // ==========================================================
    // Return true if the employees are in order for the given comparator: every employee is smaller than or
    // equal to the next one. An empty list and a list with one employee are sorted.
    //
    // ([bob(25), cy(25), ana(30)], by age)   -> true
    // ([ana(30), bob(25)], by age)           -> false
    public static boolean isSortedBy(List<Employee> employees, Comparator<Employee> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14: byAgeDescendingThenName
    // ==========================================================
    // Return a comparator for employees: the oldest first. Employees with the same age go in alphabetical
    // order by name. Sorting a list with it must give:
    //
    // [ana(30), bob(25), cy(25), dan(40), eve(30)] -> [dan(40), ana(30), eve(30), bob(25), cy(25)]
    public static Comparator<Employee> byAgeDescendingThenName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15: sortDates
    // ==========================================================
    // Every date is written as "dd/MM/yyyy" (always two digits for the day and the month, four for the year).
    // Return the dates from the oldest to the newest.
    //
    // [25/12/2020, 01/01/2021, 31/01/2020, 15/06/2020] -> [31/01/2020, 15/06/2020, 25/12/2020, 01/01/2021]
    // [02/03/2020, 01/04/2020, 03/02/2020]             -> [03/02/2020, 02/03/2020, 01/04/2020]
    public static List<String> sortDates(List<String> dates) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}