package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class CollectorChallenges2 {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Collectors.toMap() with merge function
    //
    // Given a list of employees where MULTIPLE employees can share
    // the same department, use Collectors.toMap() (NOT groupingBy!)
    // to build a Map<String, Double> of dept → TOTAL payroll.
    //
    // toMap(keyMapper, valueMapper, mergeFunction):
    //   → mergeFunction resolves duplicate key collisions!
    //   → (existing, incoming) -> existing + incoming
    //
    // Then find the department with the HIGHEST total payroll.
    // Return formatted: "DEPT=total" (total rounded to 2 decimals)
    //
    // Input:  [Alice/Eng/90000, Bob/Eng/80000,
    //          Carol/Mkt/70000, Diana/Mkt/60000, Eve/HR/75000]
    // Payrolls:
    //   Eng = 90000+80000 = 170000
    //   Mkt = 70000+60000 = 130000
    //   HR  = 75000
    // Output: "Eng=170000.00"
    // ─────────────────────────────────────────────────────────────
    public static String challenge1(List<Employee> employees) {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        Map<String, Double> map = employees.stream().collect(Collectors.toMap(
                Employee::department,
                Employee::salary,
                (existing, incoming) -> existing + incoming
        ));

        Map.Entry<String, Double> max = Map.entry("", 0.0);
        for (Map.Entry<String, Double> e : map.entrySet()) {
            if (e.getValue() > max.getValue()) {
                max = e;
            }
        }
        return String.format("%s=%.2f", max.getKey(), max.getValue());
    }

    public static String challenge1_2(List<Employee> employees) {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        return employees.stream()
                .collect(Collectors.toMap(
                    Employee::department,
                    Employee::salary,
                    (existing, incoming) -> existing + incoming))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> String.format("%s=%.2f", e.getKey(), e.getValue()))
                .orElse("");

    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Collectors.groupingBy + summarizingDouble
    //
    // Group employees by department and compute DoubleSummaryStatistics
    // of their salaries using Collectors.summarizingDouble().
    //
    // DoubleSummaryStatistics gives: count, sum, min, max, average
    //
    // Return Map<String, DoubleSummaryStatistics>:
    //   key   = department
    //   value = salary statistics for that department
    //
    // Then, from the returned map find the department with the
    // HIGHEST average salary and return it as a formatted string:
    // "DEPT: avg=X.XX count=N"
    //
    // Input:  [Alice/Eng/90000, Bob/Eng/70000, Carol/Mkt/80000]
    // Stats:
    //   Eng: count=2, sum=160000, min=70000, max=90000, avg=80000
    //   Mkt: count=1, sum=80000,  min=80000, max=80000, avg=80000
    // Highest avg: Eng (tie → alphabetically first = Eng)
    // Output: "Eng: avg=80000.00 count=2"
    //
    // Return record StatsResult(
    //   Map<String, DoubleSummaryStatistics> statsMap,
    //   String topDept)
    // ─────────────────────────────────────────────────────────────
    record StatsResult(Map<String, DoubleSummaryStatistics> statsMap, String topDept) {}

    public static StatsResult challenge2(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");

        Map<String, DoubleSummaryStatistics> statsMap = employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.summarizingDouble(Employee::salary)
        ));

        String topDept = statsMap.entrySet().stream()
                .max(Comparator.comparingDouble(
                                (Map.Entry<String, DoubleSummaryStatistics> e) ->
                                        e.getValue().getAverage())
                        .thenComparing(Comparator.<Map.Entry<String, DoubleSummaryStatistics>,
                                String>comparing(Map.Entry::getKey).reversed()))
                .map(e ->
                        String.format(
                                "%s: avg=%.2f count=%d",
                                e.getKey(),
                                e.getValue().getAverage(),
                                e.getValue().getCount()))
                .orElse("");
        return new StatsResult(statsMap, topDept);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Collectors.partitioningBy + mapping + joining
    //
    // Partition employees into two groups by salary threshold,
    // then for each group collect SORTED NAMES joined as a string.
    //
    // Return record PartitionReport(String aboveThreshold,
    //                               String belowOrEqual)
    // → aboveThreshold: names of employees earning > threshold,
    //                   sorted alpha, joined by " | "
    // → belowOrEqual:   names earning <= threshold,
    //                   sorted alpha, joined by " | "
    // → empty group → return "NONE"
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000],
    //         threshold=75000
    // above:  Alice(95000), Carol(85000) → sorted: Alice,Carol
    //         → "Alice | Carol"
    // below:  Bob(60000), Diana(70000)   → sorted: Bob,Diana
    //         → "Bob | Diana"
    //
    // Input with threshold=99999:
    // above: empty → "NONE"
    // below: all → "Alice | Bob | Carol | Diana"
    //
    // Key: partitioningBy(salary>threshold,
    //        mapping(name, collectingAndThen(toList(), sort+join)))
    // ─────────────────────────────────────────────────────────────
    record PartitionReport(String aboveThreshold, String belowOrEqual) {}

    public static PartitionReport challenge3(List<Employee> employees,
                                             double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        Map<Boolean, String> map = employees.stream().collect(Collectors.partitioningBy(
                e -> e.salary() > threshold,
                Collectors.mapping(
                        Employee::name,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list ->
                                    list.isEmpty() ? "NONE"
                                            : list.stream().sorted(Comparator.naturalOrder())
                                            .collect(Collectors.joining(" | "))


                        )
                )
        ));
        return new PartitionReport(map.get(true), map.get(false));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Collectors.teeing to find min AND max together
    //
    // Use Collectors.teeing() to find BOTH the lowest paid AND
    // highest paid employee in a SINGLE stream pass.
    //
    // downstream1: Collectors.minBy(comparingDouble(salary))
    // downstream2: Collectors.maxBy(comparingDouble(salary))
    // merger:      (min, max) -> new SalaryRange(min.get(), max.get())
    //
    // Return record SalaryRange(Employee lowest, Employee highest)
    //
    // Then use the result to build a formatted string:
    // "lowest=NAME(SALARY) highest=NAME(SALARY)"
    // where SALARY is formatted as integer (no decimals)
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000]
    // Output: "lowest=Bob(60000) highest=Alice(95000)"
    //
    // Key: collect(teeing(minBy(salary), maxBy(salary),
    //        (min,max) -> new SalaryRange(min.get(), max.get())))
    // ─────────────────────────────────────────────────────────────
    record SalaryRange(Employee lowest, Employee highest) {}

    public static String challenge4(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        SalaryRange sr = employees.stream().collect(Collectors.teeing(
                Collectors.minBy(Comparator.comparingDouble(Employee::salary)),
                Collectors.maxBy(Comparator.comparingDouble(Employee::salary)),
                (min, max) -> new SalaryRange(min.get(), max.get())
        ));

        return String.format("lowest=%s(%.0f) highest=%s(%.0f)", sr.lowest().name(), sr.lowest().salary(), sr.highest().name(), sr.highest().salary());
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Custom Collector using Collector.of()
    //
    // Build a CUSTOM COLLECTOR that computes the MEDIAN salary
    // from a stream of employees.
    //
    // Collector.of(supplier, accumulator, combiner, finisher)
    //   supplier:    () -> new ArrayList<Double>()  ← mutable container
    //   accumulator: (list, e) -> list.add(e.salary())
    //   combiner:    (list1, list2) -> { list1.addAll(list2); return list1; }
    //   finisher:    list -> { sort; return middle element(s) }
    //
    // Median rules (same as challenge 2 in previous set):
    //   odd size  → middle element of sorted list
    //   even size → average of two middle elements
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000, Eve/80000]
    //   sorted salaries: [60000,70000,80000,85000,95000]
    //   size=5 (odd) → middle = 80000.0
    // Output: 80000.0
    //
    // Input:  [Alice/90000, Bob/60000, Carol/80000, Diana/70000]
    //   sorted salaries: [60000,70000,80000,90000]
    //   size=4 (even) → (70000+80000)/2 = 75000.0
    // Output: 75000.0
    //
    // Key: stream.collect(Collector.of(
    //        ArrayList::new,
    //        (list, e) -> list.add(e.salary()),
    //        (l1, l2) -> { l1.addAll(l2); return l1; },
    //        list -> computeMedian(list)))
    // ─────────────────────────────────────────────────────────────
    public static double challenge5(List<Employee> employees) {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        return 0.0;
    }
}