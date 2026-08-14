package dev.perfectbogus.sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class SortingHardChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — 🟡 MEDIUM
    // Sort employees by their DEPARTMENT'S TOTAL SALARY (richest dept first),
    // then by own SALARY DESC, then NAME ASC within ties
    //
    // record Employee(String name, String department, double salary)
    //
    // Step 1 — Compute total salary per department from the list
    // Step 2 — Sort employees using precomputed dept totals as primary key
    //
    // Input:
    //   Alice   Engineering 95000   → deptTotal = 95000+85000+92000 = 272000
    //   Bob     Marketing   60000   → deptTotal = 60000+70000       = 130000
    //   Carol   Engineering 85000
    //   Diana   Marketing   70000
    //   Eve     Engineering 92000
    //
    // Dept totals: Engineering=272000 (rank 1), Marketing=130000 (rank 2)
    // Output:
    //   Alice  Engineering 95000  ← richest dept, highest salary
    //   Eve    Engineering 92000
    //   Carol  Engineering 85000
    //   Diana  Marketing   70000  ← second dept, highest salary
    //   Bob    Marketing   60000
    //
    // Hint: precompute Map<String, Double> deptTotals using groupingBy + summingDouble
    //       then comparingDouble(e -> deptTotals.get(dept)).reversed()
    //       .thenComparing(comparingDouble(salary).reversed())
    //       .thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, String department, double salary) {}

    public static List<Employee> challenge1(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        Map<String, Double> deptTotal = employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(Employee::salary),
                                Function.identity()
                        )
                )
        );

        Comparator<Employee> byRichestDeptDesc = Comparator.<Employee>comparingDouble(e -> deptTotal.get(e.department)).reversed();
        Comparator<Employee> byOwnSalaryDesc = Comparator.comparingDouble(Employee::salary).reversed();
        Comparator<Employee> byName = Comparator.comparing(Employee::name);

        employees.sort(byRichestDeptDesc.thenComparing(byOwnSalaryDesc).thenComparing(byName));

        return employees;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — 🟡 MEDIUM
    // Sort words by SCRABBLE SCORE DESC then LENGTH DESC then NAME ASC
    //
    // Scrabble letter values:
    //   A,E,I,O,U,L,N,S,T,R = 1 point
    //   D,G                  = 2 points
    //   B,C,M,P              = 3 points
    //   F,H,V,W,Y            = 4 points
    //   K                    = 5 points
    //   J,X                  = 8 points
    //   Q,Z                  = 10 points
    // Score = sum of letter values (case insensitive)
    //
    // Input:  ["jazz","hello","quick","fox","cat"]
    //   jazz  → j(8)+a(1)+z(10)+z(10) = 29
    //   hello → h(4)+e(1)+l(1)+l(1)+o(1) = 8
    //   quick → q(10)+u(1)+i(1)+c(3)+k(5) = 20
    //   fox   → f(4)+o(1)+x(8) = 13
    //   cat   → c(3)+a(1)+t(1) = 5
    //
    // Output: ["jazz","quick","fox","hello","cat"]
    //          29     20      13    8       5
    //
    // Hint: Map<Character, Integer> letterValues
    //       computeScore = word.toLowerCase().chars().mapToObj(c -> (char)c).mapToInt(letterValues::get).sum()
    //       comparingInt(score).reversed() + thenComparingInt(length).reversed() + thenComparing(alpha)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");

        Map<Character, Integer> points = createMapPoints();

        Comparator<String> byPointsInWordDesc =
                Comparator.<String>comparingInt(
                        w -> w.chars().map(c -> points.get((char) c)).sum()).reversed();
        Comparator<String> byLengthDesc = Comparator.comparingInt(String::length).reversed();
        Comparator<String> byWord = Comparator.naturalOrder();

        words.sort(byPointsInWordDesc.thenComparing(byLengthDesc).thenComparing(byWord));

        return words;
    }

    private static Map<Character, Integer> createMapPoints() {
        Map<Character, Integer> points = new HashMap<>();
        addScoreGroup(points, "aeioulnstr", 1);
        addScoreGroup(points, "dg", 2);
        addScoreGroup(points, "bcmp", 3);
        addScoreGroup(points, "fhvwy", 4);
        addScoreGroup(points, "k", 5);
        addScoreGroup(points, "jx", 8);
        addScoreGroup(points, "qz", 10);
        return points;
    }

    private static void addScoreGroup(Map<Character, Integer> map, String letters, int score) {
        for (char c : letters.toCharArray()) {
            map.put(c, score);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — 🟡 MEDIUM
    // Sort Map<String, List<Integer>> entries by:
    // 1. List SIZE DESC
    // 2. List SUM ASC (tie on size)
    // 3. KEY alphabetically ASC (tie on size + sum)
    //
    // Input:
    //   "alice"   → [3,1,4]        size=3, sum=8
    //   "bob"     → [1,5]          size=2, sum=6
    //   "charlie" → [9,2,6]        size=3, sum=17
    //   "diana"   → [2,7,1]        size=3, sum=10
    //   "eve"     → [5,5]          size=2, sum=10
    //
    // size=3: alice(8), charlie(17), diana(10) → sum ASC: alice(8), diana(10), charlie(17)
    // size=2: bob(6), eve(10) → sum ASC: bob(6), eve(10)
    //
    // Output: [(alice,[3,1,4]),(diana,[2,7,1]),(charlie,[9,2,6]),(bob,[1,5]),(eve,[5,5])]
    //
    // Hint: entrySet().stream()
    //       sorted by comparingInt(e -> e.getValue().size()).reversed()
    //       .thenComparingInt(e -> e.getValue().stream().mapToInt(Integer::intValue).sum())
    //       .thenComparing(Map.Entry.comparingByKey())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, List<Integer>>> challenge3(Map<String, List<Integer>> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, List<Integer>>> byListSizeDesc =
                Map.Entry.<String, List<Integer>>comparingByValue(Comparator.comparingInt(List::size)).reversed();
        Comparator<Map.Entry<String, List<Integer>>> byListSum =
                Map.Entry.comparingByValue(Comparator.comparingInt(list -> list.stream().mapToInt(Integer::intValue).sum()));
        Comparator<Map.Entry<String, List<Integer>>> byKey =
                Map.Entry.comparingByKey();

        return map.entrySet().stream()
                .sorted(byListSizeDesc.thenComparing(byListSum).thenComparing(byKey)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — 🟡 MEDIUM
    // Sort words by their FREQUENCY in the given list DESC, then LENGTH ASC, then ALPHA ASC
    //
    // First compute frequency of each word from the list itself,
    // then sort the UNIQUE words by that frequency.
    //
    // Input:  ["apple","banana","apple","cherry","banana","apple","date","cherry"]
    //
    // Frequencies: apple=3, banana=2, cherry=2, date=1
    //
    // Sort unique words:
    //   freq=3: apple
    //   freq=2: banana(6), cherry(6) → same length → alpha: banana, cherry
    //   freq=1: date
    //
    // Output (unique words sorted): ["apple","banana","cherry","date"]
    //
    // Hint: Step 1 — build Map<String,Long> freq using groupingBy + counting
    //       Step 2 — distinct() stream sorted by freq DESC, length ASC, alpha ASC
    //       comparingLong(freq::get).reversed()
    //       .thenComparingInt(String::length)
    //       .thenComparing(Comparator.naturalOrder())
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        Map<String, Long> freq = words.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )
        );

        Comparator<String> byFreqDesc = Comparator.<String>comparingLong(c -> freq.get(c)).reversed();
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        Comparator<String> byAlpha = Comparator.naturalOrder();

        return words.stream()
                .distinct()
                .sorted(byFreqDesc.thenComparing(byLength).thenComparing(byAlpha))
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — 🔴 HARD
    // Sort intervals by OVERLAP COUNT DESC (how many other intervals each overlaps),
    // then by START ASC, then END ASC for ties
    //
    // Two intervals [a,b] and [c,d] overlap if a <= d AND c <= b
    //
    // Input:  [[1,5],[2,6],[8,10],[3,4],[7,9]]
    //
    // Overlap counts:
    //   [1,5]: overlaps [2,6]=yes, [8,10]=no, [3,4]=yes, [7,9]=no → count=2
    //   [2,6]: overlaps [1,5]=yes, [8,10]=no, [3,4]=yes, [7,9]=no → count=2
    //   [8,10]: overlaps [1,5]=no, [2,6]=no, [3,4]=no, [7,9]=yes  → count=1
    //   [3,4]: overlaps [1,5]=yes, [2,6]=yes, [8,10]=no, [7,9]=no → count=2
    //   [7,9]: overlaps [1,5]=no, [2,6]=no, [8,10]=yes, [3,4]=no  → count=1
    //
    // count=2: [1,5],[2,6],[3,4] → start ASC: [1,5],[2,6],[3,4]
    // count=1: [7,9],[8,10] → start ASC: [7,9],[8,10]
    //
    // Output: [[1,5],[2,6],[3,4],[7,9],[8,10]]
    //
    // Hint: Step 1 — precompute overlap count per row using IdentityHashMap
    //       countOverlaps = Arrays.stream(matrix).filter(other -> overlaps(a,other)).count() - 1
    //       Step 2 — sort by count DESC, start ASC, end ASC
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge5(int[][] intervals) {
        if (intervals == null) throw new IllegalArgumentException("Intervals cannot be null");
        Map<int[], Integer> overlaps = preComputeOverlaps(intervals);

        Comparator<int[]> byOverlapCountDesc =
                Comparator.<int[]>comparingInt(row -> overlaps.get(row)).reversed();
        Comparator<int[]> byStart = Comparator.comparingInt(row -> row[0]);
        Comparator<int[]> byEnd = Comparator.comparingInt(row -> row[1]);
        // TODO
        Arrays.sort(intervals, byOverlapCountDesc.thenComparing(byStart).thenComparing(byEnd));

        return intervals;
    }

    private static Map<int[], Integer> preComputeOverlaps(int[][] intervals) {
        Map<int[], Integer> countOverlaps = new IdentityHashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int count = 0;
            for (int j = 0; j < intervals.length; j++) {
                if (j == i)
                    continue;
                int a = intervals[i][0];
                int b = intervals[i][1];
                int c = intervals[j][0];
                int d = intervals[j][1];
                if (a <= d && c <= b) {
                    count++;
                }
            }
            countOverlaps.put(intervals[i], count);
        }
        return countOverlaps;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — 🔴 HARD
    // Sort employees by SALARY RELATIVE TO DEPARTMENT AVERAGE:
    // 1. Above-average employees FIRST, sorted by (salary - deptAvg) DESC
    // 2. Below-average employees LAST, sorted by (salary - deptAvg) DESC (least negative first)
    // 3. Name ASC for ties within the same difference
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:
    //   Alice  Engineering 95000  deptAvg=272000/3=90666.67  diff=+4333.33
    //   Bob    Marketing   60000  deptAvg=130000/2=65000      diff=-5000
    //   Carol  Engineering 85000  diff=-5666.67
    //   Diana  Marketing   70000  diff=+5000
    //   Eve    Engineering 92000  diff=+1333.33
    //
    // Above avg (+): Alice(+4333), Eve(+1333), Diana(+5000) → sorted by diff DESC: Diana, Alice, Eve
    // Below avg (-): Bob(-5000), Carol(-5666) → sorted by diff DESC (least negative): Bob, Carol
    //
    // Output: [Diana, Alice, Eve, Bob, Carol]
    //
    // Hint: Step 1 — compute Map<String, Double> deptAvg
    //       Step 2 — compute diff = salary - deptAvg.get(dept)
    //       Step 3 — above average first: comparingInt(above ? 0 : 1)
    //                .thenComparingDouble(diff DESC)
    //                .thenComparing(name ASC)
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge6(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        Map<String, Double> deptAvg = employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                )
        );

        Map<Employee, Double> diffsSalary = new IdentityHashMap<>();
        employees.forEach(e -> diffsSalary.put(e, e.salary() - deptAvg.get(e.department())));

        Comparator<Employee> byAboveAvg = Comparator.comparingInt(e -> diffsSalary.get(e) > 0 ? 0 : 1);
        Comparator<Employee> byDiff = Comparator.<Employee>comparingDouble(e -> diffsSalary.get(e)).reversed();
        Comparator<Employee> byName = Comparator.comparing(Employee::name);

        employees.sort(byAboveAvg.thenComparing(byDiff).thenComparing(byName));

        return employees;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — 🟡 MEDIUM
    // Sort 2D array by ROW VARIANCE DESC then ROW SUM ASC then FIRST ELEMENT ASC
    //
    // variance = average of squared differences from the mean
    // variance([1,2,3]) = mean=2, diffs=[-1,0,1], squared=[1,0,1], avg=0.667
    // variance([1,5,9]) = mean=5, diffs=[-4,0,4], squared=[16,0,16], avg=10.667
    //
    // Input:  [[1,2,3],[4,4,4],[1,5,9],[2,3,4],[7,1,1]]
    //   [1,2,3] var=0.667  sum=6
    //   [4,4,4] var=0.0    sum=12
    //   [1,5,9] var=10.667 sum=15
    //   [2,3,4] var=0.667  sum=9
    //   [7,1,1] var=8.0    sum=9
    //
    // var DESC: [1,5,9]=10.667, [7,1,1]=8.0, [1,2,3]=0.667, [2,3,4]=0.667, [4,4,4]=0.0
    // var tie (0.667): [1,2,3] sum=6 vs [2,3,4] sum=9 → sum ASC: [1,2,3],[2,3,4]
    //
    // Output: [[1,5,9],[7,1,1],[1,2,3],[2,3,4],[4,4,4]]
    //
    // Hint: compute variance with stream
    //       double mean = Arrays.stream(row).average().orElse(0)
    //       double var  = Arrays.stream(row).mapToDouble(v -> Math.pow(v-mean,2)).average().orElse(0)
    //       Precompute with IdentityHashMap for performance
    //       comparingDouble(variance).reversed() + thenComparingInt(sum) + thenComparingInt(first)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge7(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — 🔴 HARD
    // Sort words by CUSTOM ALPHABET ORDER then LENGTH ASC for ties
    //
    // Given a scrambled alphabet string (all 26 letters in custom order),
    // sort words lexicographically according to that custom order.
    // Shorter word comes first if it is a prefix of a longer word.
    //
    // Custom alphabet: "zyxwvutsrqponmlkjihgfedcba" (reversed!)
    // In this order: z=0, y=1, x=2, ... a=25
    // So 'z' comes first, 'a' comes last
    //
    // Input:  words=["apple","zoo","ant","zebra","ax"], alphabet="zyxwvutsrqponmlkjihgfedcba"
    //   z=0,y=1,x=2,w=3,v=4,u=5,t=6,s=7,r=8,q=9,p=10,o=11,n=12,m=13,l=14,k=15,j=16,i=17,h=18
    //   g=19,f=20,e=21,d=22,c=23,b=24,a=25
    //
    //   zoo   → z(0),o(11),o(11)
    //   zebra → z(0),e(21),b(24),r(8),a(25)
    //   ax    → a(25),x(2)
    //   ant   → a(25),n(12),t(6)
    //   apple → a(25),p(10),p(10),l(14),e(21)
    //
    // Compare zoo vs zebra: z=z tie, o(11) vs e(21) → o<e in custom order → zoo first
    // Compare ax vs ant: a=a, x(2) vs n(12) → x<n → ax first
    // Compare ax vs apple: a=a, x(2) vs p(10) → x<p → ax first
    //
    // Output: ["zoo","zebra","ax","apple","ant"]
    //
    // Hint: Map<Character, Integer> charOrder from alphabet string
    //       Compare char by char using charOrder.get(c)
    //       If one is prefix of other → shorter first
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge8(List<String> words, String alphabet) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        if (alphabet == null || alphabet.length() != 26) throw new IllegalArgumentException("Invalid alphabet");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — 🔴 HARD
    // DYNAMIC COMPARATOR — given a list of sort criteria as strings,
    // build a comparator dynamically and sort the employees
    //
    // record Employee(String name, String department, double salary, int yearsOfExperience)
    //
    // Criteria format: "field:DIRECTION" where
    //   field     = "name" | "department" | "salary" | "yearsOfExperience"
    //   DIRECTION = "ASC" | "DESC"
    //
    // Apply criteria in ORDER (first = primary, second = secondary, etc.)
    //
    // Input:
    //   employees = [Alice/Eng/95000/5, Bob/Mkt/60000/8, Carol/Eng/85000/3,
    //                Diana/HR/70000/8,  Eve/Eng/92000/5]
    //   criteria  = ["department:ASC", "salary:DESC", "name:ASC"]
    //
    // Step 1 — department ASC: Eng,Eng,Eng,HR,Mkt
    // Step 2 — salary DESC within dept:
    //   Eng: Alice(95000),Eve(92000),Carol(85000)
    //   HR:  Diana(70000)
    //   Mkt: Bob(60000)
    // Step 3 — name ASC for remaining ties
    //
    // Output: [Alice, Eve, Carol, Diana, Bob]
    //
    // Hint: build Comparator<Employee> dynamically
    //       start with Comparator<Employee> comp = (a,b) -> 0
    //       for each criterion: parse field+direction, build partial comparator
    //       comp = comp.thenComparing(partialComp)
    //       Use switch/if on field name, handle ASC/DESC with reversed()
    // ─────────────────────────────────────────────────────────────
    record DynEmployee(String name, String department, double salary, int yearsOfExperience) {}

    public static List<DynEmployee> challenge9(List<DynEmployee> employees, List<String> criteria) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        if (criteria == null)  throw new IllegalArgumentException("Criteria cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — 🔴 HARD
    // Sort students by PERCENTILE RANK within their CLASS DESC, then NAME ASC
    //
    // Percentile rank of a student = percentage of students in same class
    //                                 with STRICTLY LOWER score
    // percentile = (students in same class with lower score / total in class) * 100
    //
    // record Student(String name, String className, int score)
    //
    // Input:
    //   Alice   ClassA  90
    //   Bob     ClassA  75
    //   Carol   ClassA  90   ← same score as Alice
    //   Diana   ClassB  80
    //   Eve     ClassB  95
    //   Frank   ClassB  80   ← same score as Diana
    //
    // ClassA scores: [90,75,90]
    //   Alice(90):  students with lower score = [75] → 1/3 = 33.33%
    //   Bob(75):    students with lower score = []   → 0/3 = 0%
    //   Carol(90):  students with lower score = [75] → 1/3 = 33.33%
    //
    // ClassB scores: [80,95,80]
    //   Diana(80):  students with lower score = []   → 0/3 = 0%
    //   Eve(95):    students with lower score = [80,80] → 2/3 = 66.67%
    //   Frank(80):  students with lower score = []   → 0/3 = 0%
    //
    // Sort by percentile DESC then name ASC:
    //   66.67%: Eve
    //   33.33%: Alice, Carol → name ASC: Alice, Carol
    //   0%:     Bob, Diana, Frank → name ASC: Bob, Diana, Frank
    //
    // Output: [Eve, Alice, Carol, Bob, Diana, Frank]
    //
    // Hint: Step 1 — group scores by className: Map<String, List<Integer>>
    //       Step 2 — for each student: compute percentile
    //                count = classScores.get(class).stream().filter(s -> s < score).count()
    //                percentile = count * 100.0 / classScores.get(class).size()
    //       Step 3 — precompute in IdentityHashMap or regular map keyed by student
    //                comparingDouble(percentile).reversed() + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Student(String name, String className, int score) {}

    public static List<Student> challenge10(List<Student> students) {
        if (students == null) throw new IllegalArgumentException("Students cannot be null");
        // TODO
        return new ArrayList<>();
    }
}