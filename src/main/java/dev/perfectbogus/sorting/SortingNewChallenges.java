package dev.perfectbogus.sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingNewChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Sort strings by the number of DIGIT characters they contain ASC,
    // then alphabetically ASC for ties.
    //
    // Input:  ["abc123", "hello", "a1b2c3", "xyz", "test99", "world1"]
    //
    // Digit counts:
    //   abc123  → 3
    //   hello   → 0
    //   a1b2c3  → 3
    //   xyz     → 0
    //   test99  → 2
    //   world1  → 1
    //
    // digits=0: hello, xyz       → alpha: hello, xyz
    // digits=1: world1
    // digits=2: test99
    // digits=3: a1b2c3, abc123   → alpha: a1b2c3, abc123
    //
    // Output: ["hello","xyz","world1","test99","a1b2c3","abc123"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        Comparator<String> byNDigits = Comparator.comparingInt(s -> (int) s.chars().filter(Character::isDigit).count());
        Comparator<String> byAlpha = Comparator.naturalOrder();

        words.sort(byNDigits.thenComparing(byAlpha));

        return words;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Sort Map entries by the KEY'S FIRST CHARACTER ASC,
    // then by VALUE DESC for ties.
    //
    // Input:  {"banana"=3, "cherry"=5, "blueberry"=1, "avocado"=4, "apricot"=7, "coconut"=2}
    //
    // first='a': avocado(4), apricot(7)  → value DESC: apricot(7), avocado(4)
    // first='b': banana(3), blueberry(1) → value DESC: banana(3), blueberry(1)
    // first='c': cherry(5), coconut(2)   → value DESC: cherry(5), coconut(2)
    //
    // Output: [(apricot,7),(avocado,4),(banana,3),(blueberry,1),(cherry,5),(coconut,2)]
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge2(Map<String, Integer> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, Integer>> byFirstLetter =
                Map.Entry.comparingByKey(Comparator.comparing(s -> s.charAt(0)));
        Comparator<Map.Entry<String, Integer>> byValueDesc =
                Map.Entry.<String, Integer>comparingByValue().reversed();

        return map.entrySet().stream().sorted(byFirstLetter.thenComparing(byValueDesc)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Sort a 2D array by each row's RANGE (max element - min element) ASC,
    // then by first element ASC for ties.
    //
    // Input:  [[3,7,1],[5,5,5],[2,9,4],[1,3,2],[8,2,6]]
    //
    // Ranges:
    //   [3,7,1] → 7-1=6
    //   [5,5,5] → 5-5=0
    //   [2,9,4] → 9-2=7
    //   [1,3,2] → 3-1=2
    //   [8,2,6] → 8-2=6
    //
    // range=0: [5,5,5]
    // range=2: [1,3,2]
    // range=6: [3,7,1],[8,2,6] → first elem ASC: [3,7,1],[8,2,6]
    // range=7: [2,9,4]
    //
    // Output: [[5,5,5],[1,3,2],[3,7,1],[8,2,6],[2,9,4]]
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge3(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        Map<int[], Integer> map = new IdentityHashMap<>();
        for (int[] row : matrix) {
            IntSummaryStatistics stats = Arrays.stream(row).summaryStatistics();
            int range = stats.getMax() - stats.getMin();
            map.put(row, range);
        }

        Comparator<int[]> byRange = Comparator.comparingInt(map::get);
        Comparator<int[]> byFirstElem = Comparator.comparingInt(a -> a[0]);

        Arrays.sort(matrix, byRange.thenComparing(byFirstElem));

        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Sort employees by NET SALARY after tax DESC, then name ASC.
    // Tax rules: salary > 80000 → tax = 30%, otherwise → tax = 20%
    // Net salary = salary * (1 - taxRate)
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:
    //   Alice   Engineering 95000  net = 95000 * 0.70 = 66500
    //   Bob     Marketing   60000  net = 60000 * 0.80 = 48000
    //   Carol   Engineering 85000  net = 85000 * 0.70 = 59500
    //   Diana   HR          80000  net = 80000 * 0.80 = 64000 ← 80000 NOT > 80000!
    //   Eve     Marketing   75000  net = 75000 * 0.80 = 60000
    //
    // net DESC: Alice(66500), Diana(64000), Eve(60000), Carol(59500), Bob(48000)
    //
    // Output: [Alice, Diana, Eve, Carol, Bob]
    //
    // Hint: compute net = salary > 80000 ? salary * 0.70 : salary * 0.80
    //       comparingDouble((Employee e) -> netSalary(e)).reversed() + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, String department, double salary) {}

    public static List<Employee> challenge4(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Sort words by count of DISTINCT characters DESC,
    // then word LENGTH ASC, then alphabetically ASC for remaining ties.
    //
    // Input:  ["hello","world","java","stream","collect","map"]
    //
    // Distinct char counts:
    //   hello   → {h,e,l,o}   = 4 distinct
    //   world   → {w,o,r,l,d} = 5 distinct
    //   java    → {j,a,v}     = 3 distinct (a appears twice)
    //   stream  → {s,t,r,e,a,m} = 6 distinct
    //   collect → {c,o,l,e,t} = 5 distinct
    //   map     → {m,a,p}     = 3 distinct
    //
    // distinct=6: stream
    // distinct=5: world(5), collect(7) → length ASC: world, collect
    // distinct=4: hello
    // distinct=3: java(4), map(3)      → length ASC: map(3), java(4)
    //
    // Output: ["stream","world","collect","hello","map","java"]
    //
    // Hint: (int) word.chars().distinct().count()
    //       comparingInt(distinctCount).reversed()
    //       .thenComparingInt(String::length)
    //       .thenComparing(naturalOrder())
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge5(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Sort Map<String, List<Integer>> entries by:
    // 1. MAX value in the list DESC
    // 2. LIST SIZE ASC (tie on max)
    // 3. KEY alphabetically ASC (tie on max + size)
    //
    // Input:
    //   "alice"   → [3, 9, 2]    max=9, size=3
    //   "bob"     → [5, 7]       max=7, size=2
    //   "carol"   → [1, 9, 4, 6] max=9, size=4
    //   "diana"   → [8, 2]       max=8, size=2
    //   "eve"     → [7, 3, 5]    max=7, size=3
    //
    // max=9: alice(size=3), carol(size=4) → size ASC: alice,carol
    // max=8: diana(size=2)
    // max=7: bob(size=2), eve(size=3)     → size ASC: bob,eve
    //
    // Output: [(alice,[3,9,2]),(carol,[1,9,4,6]),(diana,[8,2]),(bob,[5,7]),(eve,[7,3,5])]
    //
    // Hint: comparingInt((Map.Entry<String,List<Integer>> e) ->
    //           e.getValue().stream().mapToInt(Integer::intValue).max().orElse(0)).reversed()
    //       .thenComparingInt(e -> e.getValue().size())
    //       .thenComparing(Map.Entry.comparingByKey())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, List<Integer>>> challenge6(Map<String, List<Integer>> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Sort products using TWO priority maps:
    // Category priority: Electronics=0, Clothing=1, Food=2
    // Rating tier:       "PREMIUM"(>=4.5)=0, "STANDARD"(>=3.0)=1, "BUDGET"(<3.0)=2
    //
    // Sort order:
    // 1. Category priority ASC
    // 2. Rating tier priority ASC (within same category)
    // 3. Price ASC (within same category + tier)
    //
    // record Product(String name, String category, double price, double rating)
    //
    // Input:
    //   ("Phone",   "Electronics", 999.0, 4.8)  → Electronics(0), PREMIUM(0)
    //   ("Shirt",   "Clothing",     49.0, 2.5)  → Clothing(1),    BUDGET(2)
    //   ("Laptop",  "Electronics", 1299.0, 3.2) → Electronics(0), STANDARD(1)
    //   ("Apple",   "Food",          1.5, 4.7)  → Food(2),        PREMIUM(0)
    //   ("Tablet",  "Electronics",  499.0, 4.6) → Electronics(0), PREMIUM(0)
    //   ("Jeans",   "Clothing",      89.0, 4.5) → Clothing(1),    PREMIUM(0)
    //
    // Electronics+PREMIUM: Phone(999),Tablet(499) → price ASC: Tablet,Phone
    // Electronics+STANDARD: Laptop(1299)
    // Clothing+PREMIUM: Jeans(89)
    // Clothing+BUDGET: Shirt(49)
    // Food+PREMIUM: Apple(1.5)
    //
    // Output: [Tablet, Phone, Laptop, Jeans, Shirt, Apple]
    //
    // Hint: Map<String,Integer> categoryPriority + Map<String,Integer> tierPriority
    //       String tier = rating >= 4.5 ? "PREMIUM" : rating >= 3.0 ? "STANDARD" : "BUDGET"
    //       comparingInt(categoryPriority) + thenComparingInt(tierPriority) + thenComparingDouble(price)
    // ─────────────────────────────────────────────────────────────
    record Product(String name, String category, double price, double rating) {}

    public static List<Product> challenge7(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("Products cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Sort 2D array by count of values ABOVE THE ROW AVERAGE DESC,
    // then ROW SUM ASC, then FIRST ELEMENT ASC for remaining ties.
    //
    // Input:  [[1,2,9],[4,4,4],[2,3,7],[1,1,1],[5,8,2]]
    //
    // Row averages and above-avg counts:
    //   [1,2,9] avg=4.0:  9>4     → count=1
    //   [4,4,4] avg=4.0:  none>4  → count=0
    //   [2,3,7] avg=4.0:  7>4     → count=1
    //   [1,1,1] avg=1.0:  none>1  → count=0
    //   [5,8,2] avg=5.0:  8>5     → count=1
    //
    // count=1: [1,2,9]=12, [2,3,7]=12, [5,8,2]=15 → sum ASC: tie on 12 → first elem: [1,2,9],[2,3,7],[5,8,2]
    // count=0: [4,4,4]=12, [1,1,1]=3 → sum ASC: [1,1,1],[4,4,4]
    //
    // Output: [[1,2,9],[2,3,7],[5,8,2],[1,1,1],[4,4,4]]
    //
    // Hint: double avg = Arrays.stream(row).average().orElse(0)
    //       int aboveCount = (int) Arrays.stream(row).filter(v -> v > avg).count()
    //       Precompute with IdentityHashMap
    //       comparingInt(aboveCount).reversed() + thenComparingInt(sum) + thenComparingInt(first)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge8(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Sort employees by DEPARTMENT NAME LENGTH DESC,
    // then SALARY DESC, then NAME ASC.
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:
    //   Alice   Engineering 95000   dept length=11
    //   Bob     Marketing   60000   dept length=9
    //   Carol   Engineering 85000   dept length=11
    //   Diana   HR          70000   dept length=2
    //   Eve     Marketing   75000   dept length=9
    //   Frank   Operations  80000   dept length=10
    //
    // deptLen=11 (Engineering): Alice(95000), Carol(85000) → salary DESC
    // deptLen=10 (Operations):  Frank(80000)
    // deptLen=9  (Marketing):   Bob(60000), Eve(75000) → salary DESC: Eve,Bob
    // deptLen=2  (HR):          Diana(70000)
    //
    // Output: [Alice, Carol, Frank, Eve, Bob, Diana]
    //
    // Hint: comparingInt((Employee e) -> e.department().length()).reversed()
    //       .thenComparing(comparingDouble(Employee::salary).reversed())
    //       .thenComparing(Employee::name)
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge9(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Sort students by WEIGHTED GPA DESC, then by CLASS RANK within
    // their school (computed from classmates) ASC, then NAME ASC.
    //
    // record Student(String name, String school, Map<String, Integer> subjectScores)
    // Subject weights: "Math"=0.4, "Science"=0.3, "English"=0.2, "History"=0.1
    // weightedGPA = sum(score * weight) for each subject
    //
    // Class rank = position of student when classmates in SAME SCHOOL
    //              are sorted by weightedGPA DESC (rank 1 = highest GPA in school)
    //              Students with SAME GPA share the SAME rank.
    //
    // Input:
    //   Alice  SchoolA {Math=90, Science=80, English=70, History=60}
    //          GPA = 90*0.4+80*0.3+70*0.2+60*0.1 = 36+24+14+6 = 80.0
    //
    //   Bob    SchoolA {Math=70, Science=90, English=80, History=100}
    //          GPA = 70*0.4+90*0.3+80*0.2+100*0.1 = 28+27+16+10 = 81.0
    //
    //   Carol  SchoolB {Math=95, Science=85, English=90, History=80}
    //          GPA = 95*0.4+85*0.3+90*0.2+80*0.1 = 38+25.5+18+8 = 89.5
    //
    //   Diana  SchoolB {Math=95, Science=85, English=90, History=80}
    //          GPA = same as Carol = 89.5
    //
    //   Eve    SchoolA {Math=85, Science=75, English=95, History=70}
    //          GPA = 85*0.4+75*0.3+95*0.2+70*0.1 = 34+22.5+19+7 = 82.5
    //
    // SchoolA GPAs: Eve=82.5(rank1), Bob=81.0(rank2), Alice=80.0(rank3)
    // SchoolB GPAs: Carol=89.5(rank1), Diana=89.5(rank1) ← SAME rank!
    //
    // Sort by GPA DESC → rank ASC → name ASC:
    //   Carol  89.5 rank1(SchoolB)
    //   Diana  89.5 rank1(SchoolB) → same GPA+rank → name ASC: Carol,Diana ✓
    //   Eve    82.5 rank1(SchoolA)
    //   Bob    81.0 rank2(SchoolA)
    //   Alice  80.0 rank3(SchoolA)
    //
    // Output: [Carol, Diana, Eve, Bob, Alice]
    //
    // Hint:
    // Step 1 — define subject weights: Map<String,Double> weights
    // Step 2 — compute GPA: subjects.entrySet().stream()
    //          .mapToDouble(e -> e.getValue() * weights.getOrDefault(e.getKey(), 0.0)).sum()
    // Step 3 — group students by school: Map<String, List<Student>>
    // Step 4 — for each school, sort by GPA DESC and assign rank
    //          (students with same GPA share same rank)
    //          Rank = count of students in same school with STRICTLY HIGHER GPA + 1
    // Step 5 — precompute GPA and rank per student in IdentityHashMap
    // Step 6 — comparingDouble(GPA).reversed() + comparingInt(rank) + comparing(name)
    // ─────────────────────────────────────────────────────────────
    record Student(String name, String school, Map<String, Integer> subjectScores) {}

    public static List<Student> challenge10(List<Student> students) {
        if (students == null) throw new IllegalArgumentException("Students cannot be null");
        // TODO
        return new ArrayList<>();
    }
}