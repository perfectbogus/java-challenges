package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorEasyChallenges {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Count words longer than N characters
    //
    // Given a list of words and an integer n,
    // count how many words have length STRICTLY GREATER than n.
    //
    // Input:  ["hello","hi","world","java","is","fun"], n=3
    // Output: 3  ("hello"=5, "world"=5, "java"=4)
    //
    // Hint: filter(length > n) + Collectors.counting()
    // ─────────────────────────────────────────────────────────────
    public static long challenge1(List<String> words, int n) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return words.stream().filter(e -> e.length() > n).count();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Join employee names into formatted string
    //
    // Given a list of employees, collect names of employees
    // with salary >= threshold into a formatted string:
    // "Employees: [name1, name2, name3]"
    // Names sorted alphabetically.
    //
    // Input:  employees with salaries [95000,60000,85000,70000], threshold=80000
    // Output: "Employees: [Alice, Carol]"
    //
    // Hint: filter(salary >= threshold) + map(name) + sorted()
    //       + Collectors.joining(", ", "Employees: [", "]")
    // ─────────────────────────────────────────────────────────────
    public static String challenge2(List<Employee> employees, double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        Comparator<Employee> byName = Comparator.comparing(Employee::name);

        return employees.stream().filter(e ->
                        e.salary() >= threshold)
                .sorted(byName)
                .map(Employee::name)
                .collect(Collectors.joining(", ", "Employees: [", "]"));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Partition employees into senior and junior
    //
    // Given a list of employees, partition them into:
    // TRUE  = senior (yearsOfExperience >= 5)
    // FALSE = junior (yearsOfExperience < 5)
    //
    // Input:  employees with years [10, 2, 8, 3, 5, 1]
    // Output: {
    //   true  → [Alice(10), Carol(8), Eve(5)]   ← senior
    //   false → [Bob(2), Diana(3), Frank(1)]     ← junior
    // }
    //
    // Hint: Collectors.partitioningBy(yearsOfExperience >= 5)
    // ─────────────────────────────────────────────────────────────
    public static Map<Boolean, List<Employee>> challenge3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream().collect(
                Collectors.partitioningBy(e -> e.yearsOfExperience() >= 5)
        );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Sum total salary budget per department
    //
    // Given a list of employees, compute total salary per department.
    //
    // Input:
    //   Alice   Engineering 95000
    //   Bob     Marketing   60000
    //   Carol   Engineering 85000
    //   Diana   Marketing   70000
    //   Eve     Engineering 92000
    //
    // Output: {Engineering=272000.0, Marketing=130000.0}
    //
    // Hint: Collectors.groupingBy(department, Collectors.summingDouble(salary))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge4(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().mapToDouble(Employee::salary).sum()
                        )
                )
        );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Get highest paid employee name per department
    //
    // Given a list of employees, find the name of the highest paid
    // employee in each department. Return Map<String, String>
    // (department → employee name) — NOT Optional!
    //
    // Input: (same as challenge 4)
    // Output: {Engineering="Alice", Marketing="Diana"}
    //
    // Hint: groupingBy(dept,
    //         collectingAndThen(
    //           maxBy(comparingDouble(salary)),
    //           opt -> opt.map(name).orElse("NONE")
    //         ))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, String> challenge5(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::salary)),
                                opt -> opt.map(Employee::name).orElse("NONE")
                        )
                )
        );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Collect names to unmodifiable sorted list
    //
    // Given a list of employees, collect ALL names into an
    // unmodifiable list, sorted alphabetically.
    //
    // Input:  [Eve, Alice, Carol, Bob, Diana]
    // Output: unmodifiable [Alice, Bob, Carol, Diana, Eve]
    //
    // Hint: map(name) + sorted()
    //       + Collectors.collectingAndThen(toList(), Collections::unmodifiableList)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge6(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream().map(Employee::name).sorted(Comparator.naturalOrder()).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Count employees per department
    //
    // Given a list of employees, count how many employees
    // are in each department.
    //
    // Input: (same as challenge 4)
    // Output: {Engineering=3, Marketing=2}
    //
    // Hint: Collectors.groupingBy(department, Collectors.counting())
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Long> challenge7(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().count()
                        )
                )
        );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Average salary per department
    //
    // Given a list of employees, compute the average salary
    // per department.
    //
    // Input: (same as challenge 4)
    // Output: {Engineering=90666.67, Marketing=65000.0}
    //
    // Hint: Collectors.groupingBy(department, Collectors.averagingDouble(salary))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge8(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Collect to Map: name → salary
    //
    // Given a list of employees, collect into a Map where:
    // KEY   = employee name
    // VALUE = employee salary
    //
    // Input: [Alice=95000, Bob=60000, Carol=85000]
    // Output: {Alice=95000.0, Bob=60000.0, Carol=85000.0}
    //
    // Hint: Collectors.toMap(Employee::name, Employee::salary)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge9(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Get all department names as sorted unmodifiable Set
    //
    // Given a list of employees, collect all unique department names
    // into a sorted, unmodifiable TreeSet.
    //
    // Input: employees in Engineering, Marketing, HR, Engineering, HR
    // Output: unmodifiable TreeSet{"Engineering", "HR", "Marketing"}
    //         (sorted alphabetically, no duplicates!)
    //
    // Hint: map(department)
    //       + Collectors.collectingAndThen(
    //           Collectors.toCollection(TreeSet::new),
    //           Collections::unmodifiableSet
    //         )
    // ─────────────────────────────────────────────────────────────
    public static Set<String> challenge10(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashSet<>();
    }
}