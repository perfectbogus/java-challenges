package dev.perfectbogus.sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class SortingMixChallenges2 {

    record Employee(String name, String department, double salary) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Sort employees by their TAX-ADJUSTED NET SALARY descending,
    // then by NAME ascending for ties.
    //
    // Tax brackets:
    //   salary > 100000 → tax = 40%  → net = salary * 0.60
    //   salary >  70000 → tax = 30%  → net = salary * 0.70
    //   salary >  50000 → tax = 20%  → net = salary * 0.80
    //   otherwise       → tax = 10%  → net = salary * 0.90
    //
    // Input:
    //   Alice/Engineering/120000  net = 120000*0.60 = 72000
    //   Bob/Marketing/45000       net =  45000*0.90 = 40500
    //   Carol/Engineering/75000   net =  75000*0.70 = 52500
    //   Diana/HR/55000            net =  55000*0.80 = 44000
    //   Eve/Marketing/100000      net = 100000*0.70 = 70000
    //   Frank/Engineering/70000   net =  70000*0.70 = 49000
    //
    // Output: [Alice, Eve, Carol, Frank, Diana, Bob]
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge1(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        Comparator<Employee> byTaxAdjustedDesc = Comparator.comparingDouble(SortingMixChallenges2::getTaxAdjNetSalary).reversed();
        Comparator<Employee> byName = Comparator.comparing(Employee::name);
        employees.sort(byTaxAdjustedDesc.thenComparing(byName));
        return employees;
    }

    public static double getTaxAdjNetSalary(Employee e) {
        double rate = e.salary() > 100_000 ? 0.60
                : e.salary() > 70_000 ? 0.70
                : e.salary() > 50_000 ? 0.80
                : 0.90;
        return e.salary() * rate;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Sort Map<String, List<Integer>> entries by the RANGE (max - min)
    // of the list DESC, then by LIST SUM ASC for ties,
    // then by KEY alphabetically ASC for remaining ties.
    //
    // Input:
    //   "alice" → [3,9,2]      range=7, sum=14
    //   "bob"   → [5,5,5]      range=0, sum=15
    //   "carol" → [1,8,4,2]    range=7, sum=15
    //   "diana" → [10,1]       range=9, sum=11
    //   "eve"   → [3,3]        range=0, sum=6
    //
    // range DESC: diana(9), alice(7), carol(7), eve(0), bob(0)
    // range=7 tie → sum ASC: alice(14), carol(15)
    // range=0 tie → sum ASC: eve(6), bob(15)
    //
    // Output: [(diana,[10,1]),(alice,[3,9,2]),(carol,[1,8,4,2]),(eve,[3,3]),(bob,[5,5,5])]
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, List<Integer>>> challenge2(Map<String, List<Integer>> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Map<String, long[]> statsMap = map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    IntSummaryStatistics stats = entry.getValue().stream()
                            .mapToInt(Integer::intValue)
                            .summaryStatistics();
                    return new long[]{stats.getMax() - stats.getMin(), stats.getSum()};
                }
        ));

        final int RANGE = 0;
        Comparator<Map.Entry<String, List<Integer>>> byRangeDesc =
                Comparator.<Map.Entry<String, List<Integer>>>comparingLong( e ->
                        statsMap.get(e.getKey())[RANGE]
                ).reversed();

        final int SUM = 1;
        Comparator<Map.Entry<String, List<Integer>>> bySub = Comparator.comparingLong( e ->
                statsMap.get(e.getKey())[SUM]
        );

        Comparator<Map.Entry<String, List<Integer>>> byKey = Map.Entry.comparingByKey(Comparator.naturalOrder());

        return map.entrySet().stream().sorted(byRangeDesc.thenComparing(bySub).thenComparing(byKey)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Sort a 2D array by count of EVEN numbers per row DESC,
    // then by ROW SUM ASC for ties,
    // then by FIRST ELEMENT ASC for remaining ties.
    //
    // Input:  [[3,7,5],[2,4,6],[1,2,3],[8,2,4],[7,1,1]]
    //
    // Even counts:
    //   [3,7,5] → 0 evens  sum=15
    //   [2,4,6] → 3 evens  sum=12
    //   [1,2,3] → 1 even   sum=6
    //   [8,2,4] → 3 evens  sum=14
    //   [7,1,1] → 0 evens  sum=9
    //
    // evens=3: [2,4,6]=12,[8,2,4]=14 → sum ASC: [2,4,6],[8,2,4]
    // evens=1: [1,2,3]=6
    // evens=0: [3,7,5]=15,[7,1,1]=9  → sum ASC: [7,1,1],[3,7,5]
    //
    // Output: [[2,4,6],[8,2,4],[1,2,3],[7,1,1],[3,7,5]]
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge3(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Sort strings by their LONGEST CONSECUTIVE SAME CHARACTER RUN
    // descending, then by WORD LENGTH ascending,
    // then alphabetically ascending for remaining ties.
    //
    // Input:  ["aabbbcc","abcde","aaaa","aab","bbccdd","ab"]
    //
    // Longest runs:
    //   "aabbbcc" → run=3 (bbb)
    //   "abcde"   → run=1
    //   "aaaa"    → run=4
    //   "aab"     → run=2 (aa)
    //   "bbccdd"  → run=2
    //   "ab"      → run=1
    //
    // run=4: aaaa
    // run=3: aabbbcc
    // run=2: aab(3),bbccdd(6) → len ASC: aab,bbccdd
    // run=1: ab(2),abcde(5)   → len ASC: ab,abcde
    //
    // Output: ["aaaa","aabbbcc","aab","bbccdd","ab","abcde"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Sort employees by SALARY RANK within their DEPARTMENT ASC,
    // then by SALARY DESC for same rank across departments,
    // then by NAME ASC for remaining ties.
    //
    // Rank = count of employees in same dept with STRICTLY HIGHER salary + 1
    // Same salary in same dept = same rank!
    //
    // Input:
    //   Alice/Engineering/95000   rank in Eng = 1 (no one higher)
    //   Bob/Marketing/60000       rank in Mkt = 2 (Diana is higher)
    //   Carol/Engineering/85000   rank in Eng = 3 (Alice+Eve higher)
    //   Diana/Marketing/70000     rank in Mkt = 1
    //   Eve/Engineering/95000     rank in Eng = 1 (same as Alice!)
    //   Frank/HR/75000            rank in HR  = 1
    //
    // Sort by rank ASC → salary DESC → name ASC:
    //   rank=1: Alice(95000),Eve(95000),Diana(70000),Frank(75000)
    //           salary DESC: Alice(95000),Eve(95000),Frank(75000),Diana(70000)
    //           name tie: Alice,Eve
    //   rank=2: Bob(60000)
    //   rank=3: Carol(85000)
    //
    // Output: [Alice, Eve, Frank, Diana, Bob, Carol]
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge5(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }
}