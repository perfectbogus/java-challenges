package dev.perfectbogus.streams;

import java.util.*;
import java.util.stream.*;

public class EasyStreamChallenges {

    record Employee(String name, String department, double salary) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Given a list of strings, return the count of strings that
    // contain AT LEAST ONE vowel (a, e, i, o, u — lowercase only).
    //
    // Input:  ["apple","gym","rhythm","hello","cry","fly","orange"]
    // Contains vowel:  apple✓ gym✗ rhythm✗ hello✓ cry✗ fly✗ orange✓
    // Output: 3
    //
    // Key operations: filter + count
    // ─────────────────────────────────────────────────────────────
    public static long challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        Set<Character> vowels = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u'));

        return words.stream()
                .filter(w -> {
                    for (char c : w.toCharArray()) {
                        if (vowels.contains(c)) return true;
                    }
                    return false;
                })
                .count();
    }

    public static long challenge1_2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");

        Set<Character> vowels = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u'));

        return words.stream()
                .filter(w -> w.chars().mapToObj(c -> (char) c).anyMatch(vowels::contains))
                .count();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Given a list of integers, return a new list containing only
    // the ODD numbers, each multiplied by 3, in original order.
    //
    // Input:  [1,2,3,4,5,6,7,8,9,10]
    // Odd:    [1,3,5,7,9]
    // *3:     [3,9,15,21,27]
    // Output: [3,9,15,21,27]
    //
    // Key operations: filter(odd) + map(*3) + toList
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge2(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Given a list of integers, compute the SUM of the SQUARES
    // of all EVEN numbers using a stream.
    //
    // Input:  [1,2,3,4,5,6]
    // Even:   [2,4,6]
    // Squares:[4,16,36]
    // Output: 56
    //
    // Key operations: filter(even) + mapToInt(n*n) + sum()
    // ─────────────────────────────────────────────────────────────
    public static int challenge3(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Given a list of strings with possible duplicates, return a
    // sorted list of UNIQUE strings in alphabetical order.
    //
    // Input:  ["banana","apple","cherry","apple","banana","date"]
    // Unique: ["apple","banana","cherry","date"]
    // Output: ["apple","banana","cherry","date"]
    //
    // Key operations: distinct() + sorted() + toList()
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Given a list of employees, return the NAME of the employee
    // with the HIGHEST salary. If the list is empty return "NONE".
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000]
    // Output: "Alice"
    //
    // Key operations: max(comparingDouble(salary)) + map(name) + orElse
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return "NONE";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Given a list of integers, use stream MATCHING operations to
    // return a record MatchResult with three boolean fields:
    // → allPositive:  are ALL numbers > 0?
    // → anyNegative:  is ANY number < 0?
    // → noneZero:     is NO number == 0?
    //
    // Input:  [1, -2, 3, 0, 5]
    // Output: MatchResult(allPositive=false, anyNegative=true, noneZero=false)
    //
    // Input:  [1, 2, 3, 4, 5]
    // Output: MatchResult(allPositive=true, anyNegative=false, noneZero=true)
    //
    // Key operations: allMatch + anyMatch + noneMatch
    // ─────────────────────────────────────────────────────────────
    record MatchResult(boolean allPositive, boolean anyNegative, boolean noneZero) {}

    public static MatchResult challenge6(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        return new MatchResult(false, false, false);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Given a list of strings and a starting letter, find and return
    // the FIRST string (in original order) that starts with that letter.
    // Return Optional<String>.
    //
    // Input:  words=["apple","banana","apricot","cherry","avocado"], letter='a'
    // Output: Optional["apple"]   ← first match in order!
    //
    // Input:  words=["banana","cherry"], letter='z'
    // Output: Optional.empty()
    //
    // Key operations: filter(startsWith) + findFirst()
    // ─────────────────────────────────────────────────────────────
    public static Optional<String> challenge7(List<String> words, char letter) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return Optional.empty();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Given a list of employees, compute the AVERAGE salary of
    // employees in a given department. Return 0.0 if department
    // has no employees.
    //
    // Input:  employees=[Alice/Eng/95000, Bob/Mkt/60000, Carol/Eng/85000],
    //         department="Engineering"
    // Eng employees: Alice(95000), Carol(85000)
    // Output: 90000.0
    //
    // Key operations: filter(dept) + mapToDouble(salary) + average() + orElse(0.0)
    // ─────────────────────────────────────────────────────────────
    public static double challenge8(List<Employee> employees, String department) {
        if (employees  == null) throw new IllegalArgumentException("Employees cannot be null");
        if (department == null) throw new IllegalArgumentException("Department cannot be null");
        // TODO
        return 0.0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Given a list of employee names, join the names of employees
    // whose name length is GREATER THAN minLength,
    // sorted ALPHABETICALLY, using the given separator.
    // Wrap the result with prefix and suffix.
    //
    // Input:  names=["Alice","Bob","Carol","Di","Eve"], minLength=3,
    //         separator=", ", prefix="[", suffix="]"
    // Length > 3: Alice(5), Carol(5), Eve(3→NOT>3!)
    // Wait: Eve=3, NOT > 3! Only Alice(5), Carol(5)
    // Sorted alpha: Alice, Carol
    // Output: "[Alice, Carol]"
    //
    // Key operations: filter(length>minLength) + sorted() + collect(joining)
    // ─────────────────────────────────────────────────────────────
    public static String challenge9(List<String> names, int minLength,
                                    String separator, String prefix, String suffix) {
        if (names     == null) throw new IllegalArgumentException("Names cannot be null");
        if (separator == null) throw new IllegalArgumentException("Separator cannot be null");
        // TODO
        return prefix + suffix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Given a list of integers, return a PAGINATED sublist using
    // skip() and limit().
    // pageNumber is 0-based (page 0 = first page).
    // If pageNumber is beyond available data → return empty list.
    //
    // Input:  numbers=[1,2,3,4,5,6,7,8,9,10], pageSize=3, pageNumber=1
    // Page 0: [1,2,3]
    // Page 1: [4,5,6]  ← return this!
    // Page 2: [7,8,9]
    // Page 3: [10]
    //
    // Input:  numbers=[1,2,3,4,5], pageSize=3, pageNumber=5
    // Output: []  (beyond data)
    //
    // Key operations: skip(pageNumber * pageSize) + limit(pageSize) + toList()
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge10(List<Integer> numbers, int pageSize, int pageNumber) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        if (pageSize <= 0)   throw new IllegalArgumentException("pageSize must be positive");
        if (pageNumber < 0)  throw new IllegalArgumentException("pageNumber must be non-negative");
        // TODO
        return new ArrayList<>();
    }
}