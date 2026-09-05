package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class CollectorChallenges2 {

    record Employee(String name, String department, double salary) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Collectors.joining with delimiter, prefix, suffix
    //
    // Given a list of employees, format their names as a report.
    // Filter employees earning MORE than a threshold.
    // Sort names ALPHABETICALLY.
    // Join with separator ", " wrapped in prefix "[" and suffix "]".
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000]
    //         threshold=75000
    // Filter: Alice(95000>75000), Carol(85000>75000)
    // Sorted: Alice, Carol
    // Output: "[Alice, Carol]"
    //
    // Input:  [Alice/95000, Bob/60000], threshold=99999
    // Output: "[]"  ← no one qualifies!
    //
    // Key: filter → sorted → collect(joining(", ", "[", "]"))
    // ─────────────────────────────────────────────────────────────
    public static String challenge1(List<Employee> employees, double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .filter(e -> e.salary() > threshold)
                .sorted(Comparator.comparing(Employee::name))
                .map(Employee::name)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Collectors.teeing (Java 12+)
    //
    // Use Collectors.teeing() to compute TWO things in ONE pass:
    // → downstream1: sum of all salaries (summingDouble)
    // → downstream2: count of employees (counting)
    // → merger: divide sum by count to get average
    //
    // Return record SalaryStats(double sum, long count, double average)
    //
    // Collectors.teeing(downstream1, downstream2, merger):
    //   → applies BOTH collectors simultaneously to the same stream!
    //   → merger combines the two results!
    //
    // Input:  [Alice/90000, Bob/60000, Carol/90000]
    // Output: SalaryStats(sum=240000.0, count=3, average=80000.0)
    //
    // Key: collect(Collectors.teeing(
    //        summingDouble(salary),
    //        counting(),
    //        (sum, count) -> new SalaryStats(sum, count, sum/count)))
    // ─────────────────────────────────────────────────────────────
    record SalaryStats(double sum, long count, double average) {}

    public static SalaryStats challenge2(List<Employee> employees) {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        SalaryStats collect = employees.stream().collect(Collectors.teeing(
                Collectors.summingDouble(Employee::salary),
                Collectors.counting(),
                (sum, count) -> new SalaryStats(sum, count, sum / count)
        ));

        return collect;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Collectors.groupingBy + Collectors.maxBy
    //
    // Find the HIGHEST PAID employee per department.
    // Use groupingBy with maxBy as downstream collector.
    // Return Map<String, String> where:
    //   key   = department name
    //   value = name of highest paid employee in that department
    //
    // Collectors.maxBy(Comparator) → Optional<T> downstream!
    // Use collectingAndThen to unwrap Optional!
    //
    // Input:  [Alice/Eng/95000, Bob/Eng/70000, Carol/Mkt/80000, Diana/Mkt/90000]
    // Eng:    Alice(95000) wins
    // Mkt:    Diana(90000) wins
    // Output: {"Eng"="Alice", "Mkt"="Diana"}
    //
    // Key: groupingBy(department,
    //        collectingAndThen(
    //          maxBy(comparingDouble(salary)),
    //          opt -> opt.get().name()))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, String> challenge3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary)),
                        opt -> opt.map(Employee::name).orElse("")
                )
        ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Collectors.groupingBy + filtering (Java 9+)
    //
    // Count employees PER DEPARTMENT who earn ABOVE a threshold.
    // Use groupingBy with Collectors.filtering as downstream!
    //
    // Collectors.filtering(predicate, downstream):
    //   → filters elements WITHIN each group before collecting!
    //   → UNLIKE stream.filter() which removes groups entirely!
    //   → groups with count=0 are still included in the map!
    //
    // Return Map<String, Long>:
    //   key   = department
    //   value = count of employees earning > threshold in that dept
    //
    // Input:  [Alice/Eng/95000, Bob/Eng/60000, Carol/Mkt/80000],
    //         threshold=75000
    // Eng:    Alice(95000>75000)=1, Bob(60000<75000)=0 → count=1
    // Mkt:    Carol(80000>75000)=1                     → count=1
    // Output: {"Eng"=1, "Mkt"=1}
    //
    // Input:  same employees, threshold=90000
    // Eng:    Alice(95000>90000)=1, Bob(60000<90000)=0 → count=1
    // Mkt:    Carol(80000<90000)=0                     → count=0
    // Output: {"Eng"=1, "Mkt"=0}  ← Mkt still included with 0!
    //
    // Key: groupingBy(department,
    //        filtering(e -> e.salary() > threshold, counting()))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Long> challenge4(List<Employee> employees, double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Collectors.collectingAndThen + unmodifiable
    //
    // Collect employees into a SORTED UNMODIFIABLE list.
    // Sort by salary DESCENDING then name ASCENDING.
    // Use collectingAndThen to apply a final transformation.
    //
    // collectingAndThen(toList(), finisher):
    //   → first collects to a list
    //   → then applies the finisher function to the whole list!
    //
    // Return List<Employee> that:
    // → is sorted by salary DESC then name ASC
    // → throws UnsupportedOperationException on add/remove!
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/60000]
    // Sorted salary DESC → name ASC:
    //   Alice(95000), Carol(85000), Bob(60000), Diana(60000)
    //   salary=60000 tie → name ASC: Bob before Diana
    // Output: [Alice, Carol, Bob, Diana] ← unmodifiable!
    //
    // Key: collect(collectingAndThen(
    //        toList(),
    //        list -> {
    //          list.sort(bySalaryDesc.thenComparing(byName));
    //          return Collections.unmodifiableList(list);
    //        }))
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge5(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return new ArrayList<>();
    }
}