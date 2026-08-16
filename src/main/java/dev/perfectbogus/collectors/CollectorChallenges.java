package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class CollectorChallenges {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–2)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Given a list of words, group them by FIRST CHARACTER and count
    // how many words start with each character.
    // Return Map<Character, Long>.
    //
    // Input:  ["apple","avocado","banana","blueberry","cherry","apricot","coconut"]
    // Output: {'a'=3, 'b'=2, 'c'=2}
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, Long> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return words.stream().collect(
                Collectors.groupingBy(
                        s -> s.charAt(0),
                        Collectors.counting()
                )
        );
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Given a list of integers and a threshold, partition them into
    // above-threshold (true) and at-or-below-threshold (false),
    // and compute the SUM of each partition.
    // Return Map<Boolean, Integer>.
    //
    // Input:  numbers=[1,5,8,3,9,2,7,4,6], threshold=5
    // above (>5): [8,9,7,6] → sum=30
    // at-or-below (<=5): [1,5,3,2,4] → sum=15
    // Output: {true=30, false=15}
    // ─────────────────────────────────────────────────────────────
    public static Map<Boolean, Integer> challenge2(List<Integer> numbers, int threshold) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        return numbers.stream().collect(
                Collectors.partitioningBy(n -> n > threshold,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l -> l.stream().mapToInt(Integer::intValue).sum()
                        ))
        );
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 3–9)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Group employees by department, collect to a map where the VALUE
    // is the list of employee NAMES sorted alphabetically.
    // Return Map<String, List<String>>.
    //
    // Input:
    //   Alice/Engineering/95000  Bob/Marketing/60000
    //   Carol/Engineering/85000  Diana/Marketing/70000
    //   Eve/HR/90000
    //
    // Output: {
    //   "Engineering" → ["Alice", "Carol"]
    //   "Marketing"   → ["Bob", "Diana"]
    //   "HR"          → ["Eve"]
    // }
    // ─────────────────────────────────────────────────────────────
    public static Map<String, List<String>> challenge3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Group words by their FIRST LETTER, join all words in each group
    // into a single comma-separated string, sorted alphabetically within each group.
    // Return Map<Character, String>.
    //
    // Input:  ["cherry","apple","banana","avocado","blueberry","coconut","apricot"]
    // Output: {
    //   'a' → "apple, apricot, avocado"
    //   'b' → "banana, blueberry"
    //   'c' → "cherry, coconut"
    // }
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, String> challenge4(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Find the name of the department with the HIGHEST AVERAGE salary.
    // Return String (department name).
    //
    // Input:
    //   Alice/Engineering/95000  Bob/Marketing/60000
    //   Carol/Engineering/85000  Diana/Marketing/70000
    //   Eve/HR/90000
    //
    // Averages: Engineering=(95000+85000)/2=90000, Marketing=65000, HR=90000
    // Tie on avg=90000: Engineering, HR → pick alphabetically first: "Engineering"
    // Output: "Engineering"
    //
    // (If multiple departments tie on avg salary, return the alphabetically first name)
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Group employees by department, collect to a map where the VALUE
    // is a comma-separated string of employee names sorted by SALARY DESC.
    // Return Map<String, String>.
    //
    // Input:
    //   Alice/Engineering/95000  Bob/Marketing/60000
    //   Carol/Engineering/85000  Diana/Marketing/70000
    //   Eve/Engineering/92000
    //
    // Output: {
    //   "Engineering" → "Alice, Eve, Carol"    ← sorted 95000,92000,85000
    //   "Marketing"   → "Diana, Bob"           ← sorted 70000,60000
    // }
    // ─────────────────────────────────────────────────────────────
    public static Map<String, String> challenge6(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Use Collectors.teeing() to simultaneously compute in ONE stream pass:
    // → The TOTAL salary of ALL employees
    // → The COUNT of employees earning ABOVE the overall average salary
    //
    // Return a record Result(double totalSalary, long countAboveAverage)
    //
    // Input: [Alice/95000, Bob/60000, Carol/85000, Diana/70000, Eve/92000]
    // total = 402000
    // avg   = 402000/5 = 80400
    // above avg (>80400): Alice(95000), Carol(85000), Eve(92000) → count=3
    //
    // ⚠️ You must compute avg WITHIN the collector chain — not before!
    // Hint: use teeing with:
    //   collector1 → summingDouble(salary)       for totalSalary
    //   collector2 → toList()                     for all employees
    //   merger     → compute avg from list, count above avg, return Result
    // ─────────────────────────────────────────────────────────────
    record Result(double totalSalary, long countAboveAverage) {}

    public static Result challenge7(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO
        return new Result(0, 0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Use NESTED groupingBy to build a two-level map:
    // OUTER KEY = department
    // INNER KEY = seniority label: "SENIOR" (yearsOfExperience >= 5) or "JUNIOR" (< 5)
    // VALUE     = count of employees in that department + seniority group
    // Return Map<String, Map<String, Long>>.
    //
    // Input:
    //   Alice/Engineering/95000/8   Bob/Marketing/60000/2
    //   Carol/Engineering/85000/3   Diana/Marketing/70000/7
    //   Eve/Engineering/92000/5     Frank/HR/55000/1
    //
    // Output: {
    //   "Engineering" → {"SENIOR"=2(Alice,Eve), "JUNIOR"=1(Carol)}
    //   "Marketing"   → {"SENIOR"=1(Diana),     "JUNIOR"=1(Bob)}
    //   "HR"          → {"JUNIOR"=1(Frank)}
    // }
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Map<String, Long>> challenge8(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Given a list of employees, collect into a LinkedHashMap<String, List<Employee>>
    // where:
    // KEY   = department name
    // VALUE = list of employees in that department sorted by salary DESC
    //
    // The LinkedHashMap must be ordered by DEPARTMENT'S TOTAL SALARY DESC
    // (department with highest total salary budget appears first).
    //
    // Input:
    //   Alice/Engineering/95000   Bob/Marketing/60000
    //   Carol/Engineering/85000   Diana/Marketing/70000
    //   Eve/Engineering/92000
    //
    // Totals: Engineering=272000, Marketing=130000
    // Output: LinkedHashMap {
    //   "Engineering" → [Alice(95000), Eve(92000), Carol(85000)]  ← total=272000
    //   "Marketing"   → [Diana(70000), Bob(60000)]                ← total=130000
    // }
    // ─────────────────────────────────────────────────────────────
    public static LinkedHashMap<String, List<Employee>> challenge9(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new LinkedHashMap<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // For each department, build a DeptSummary using complex collectors.
    // Return Map<String, DeptSummary>.
    //
    // record DeptSummary(
    //     long   employeeCount,
    //     double totalSalary,
    //     double averageSalary,
    //     String highestPaidName,   ← name of highest salary employee
    //     String lowestPaidName,    ← name of lowest salary employee
    //     String namesByRank        ← names sorted by salary DESC, comma-separated
    // )
    //
    // Input:
    //   Alice/Engineering/95000   Bob/Marketing/60000
    //   Carol/Engineering/85000   Diana/Marketing/70000
    //   Eve/Engineering/92000
    //
    // Output: {
    //   "Engineering" → DeptSummary(
    //       count=3, total=272000, avg=90666.67,
    //       highest="Alice", lowest="Carol",
    //       namesByRank="Alice, Eve, Carol")
    //   "Marketing" → DeptSummary(
    //       count=2, total=130000, avg=65000,
    //       highest="Diana", lowest="Bob",
    //       namesByRank="Diana, Bob")
    // }
    //
    // Hint:
    // Step 1 — groupingBy(department) as outer key
    // Step 2 — use Collectors.teeing() as downstream to compute TWO things at once:
    //   collector1 → Collectors.toList() to get all employees in dept
    //   collector2 → Collectors.toList() (same, for flexibility)
    //   merger (list1, list2) → build DeptSummary from list1:
    //     count    = list1.size()
    //     total    = list1.stream().mapToDouble(salary).sum()
    //     avg      = total / count
    //     highest  = list1.stream().max(comparingDouble(salary)).map(name).orElse("")
    //     lowest   = list1.stream().min(comparingDouble(salary)).map(name).orElse("")
    //     byRank   = list1.stream().sorted(salary DESC).map(name).collect(joining(", "))
    //
    // Alternative without teeing:
    //   groupingBy(department, collectingAndThen(toList(), list -> buildSummary(list)))
    //   ← use collectingAndThen with a finisher function that builds DeptSummary from list
    //   This is the simpler approach!
    // ─────────────────────────────────────────────────────────────
    record DeptSummary(long employeeCount, double totalSalary, double averageSalary,
                       String highestPaidName, String lowestPaidName, String namesByRank) {}

    public static Map<String, DeptSummary> challenge10(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new HashMap<>();
    }
}