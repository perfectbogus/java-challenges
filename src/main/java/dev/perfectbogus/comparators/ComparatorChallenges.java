package dev.perfectbogus.comparators;

import java.util.*;
import java.util.stream.Collectors;

public class ComparatorChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Sort 2D Matrix by Second Column ASC
    //               then First Column DESC for ties
    //
    // Given a 2D matrix sort rows by second column ascending.
    // If second column values are equal sort by first column descending.
    //
    // Input:  [[3,2],[1,4],[2,2],[5,1],[4,3]]
    // Output: [[5,1],[3,2],[2,2],[4,3],[1,4]]
    //          ↑col1  ↑col1=2 first=3>2  ↑col1  ↑col1
    //
    // Hint: comparingInt(col[1]) + thenComparingInt(col[0] reversed)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge1(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");

        // TODO
        Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> a[1])
                .thenComparing(Comparator.comparingInt((int[] a) -> a[0]).reversed()));

        return matrix;
    }

    public static int[][] challenge1_2(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("matrix is null");

        Arrays.sort(matrix, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        });

        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Sort Map by Key Length ASC
    //               then Value DESC for ties
    //
    // Given a Map<String, Integer> return sorted list of entries
    // by key LENGTH ascending. If lengths are equal sort by
    // value descending.
    //
    // Input:  {cat=3, elephant=8, dog=5, ant=1, bear=4}
    // Output: [(ant,1),(cat,3),(dog,5),(bear,4),(elephant,8)]
    //          ↑len=3  ↑len=3  ↑len=3  ↑len=4   ↑len=8
    //          ant<cat<dog same length → value DESC: 3>1 wait
    //
    // Note: cat=3,ant=1,dog=5 all length 3 → sort value DESC: dog(5),cat(3),ant(1)
    //
    // Hint: comparingByKey(comparingInt(length))
    //       .thenComparing(comparingByValue().reversed())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge2(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, Integer>> cKeyLength = Map.Entry.comparingByKey(Comparator.comparingInt(String::length));
        Comparator<Map.Entry<String, Integer>> cValue = Map.Entry.<String, Integer>comparingByValue().reversed();

        return map.entrySet().stream().sorted(
                cKeyLength.thenComparing(cValue)
        ).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Sort Students by Grade Letter Priority Map
    //               then Name ASC for ties
    //
    // Sort students using a PRIORITY MAP for grades:
    //   A=0 (highest priority/first)
    //   B=1
    //   C=2
    //   D=3
    //   F=4 (lowest priority/last)
    //
    // For same grade → sort by name alphabetically ascending.
    //
    // Input:  [("Alice",'B'), ("Bob",'A'), ("Carol",'B'),
    //          ("Diana",'C'), ("Eve",'A')]
    // Output: [("Bob",'A'), ("Eve",'A'), ("Alice",'B'),
    //          ("Carol",'B'), ("Diana",'C')]
    //
    // Hint: Map<Character,Integer> priority + comparingInt(priority)
    //       + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Student(String name, char grade) {}

    public static List<Student> challenge3(List<Student> students) {
        if (students == null)
            throw new IllegalArgumentException("Students cannot be null");

        // TODO
        Map<Character, Integer> gradesMap = new HashMap<>(Map.of(
                'A', 0,
                'B', 1,
                'C', 2,
                'D', 3,
                'E', 4,
                'F', 5
        ));
//          You need to define the withness by Type Comparator or
//        students.sort(
//                Comparator.<Student>comparingInt( a -> gradesMap.get(a.grade()))
//                        .thenComparing(Student::name)
//        );

        // By type in the lambda
        students.sort(
                Comparator.comparingInt((Student a) -> gradesMap.get(a.grade()))
                        .thenComparing(Student::name)
        );

        return students;
    }

    public static List<Student> challenge3_2(List<Student> students) {
        if (students == null) throw new IllegalArgumentException("students is null");

        Map<Character, Integer> gradesMap = new HashMap<>(Map.of(
                'A', 0,
                'B', 1,
                'C', 2,
                'D', 3,
                'E', 4,
                'F', 5
        ));

        Comparator<Student> byGrade = Comparator.comparingInt(a -> gradesMap.get(a.grade()));
        Comparator<Student> byName = Comparator.comparing(Student::name);

        students.sort(byGrade.thenComparing(byName));

        return students;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Sort 2D Intervals by Duration ASC
    //               then Start ASC for ties
    //
    // Given a 2D array of intervals [start, end],
    // sort by DURATION (end - start) ascending.
    // If durations are equal sort by start ascending.
    //
    // Input:  [[1,5],[2,4],[3,6],[1,3],[4,6]]
    // Output: [[1,3],[2,4],[4,6],[1,5],[3,6]]
    //         dur=2  dur=2  dur=2  dur=4  dur=3... wait
    //
    // Durations: [4, 2, 3, 2, 2]
    // [1,3]=2, [2,4]=2, [4,6]=2 → tie → sort by start: 1,2,4
    // [3,6]=3
    // [1,5]=4
    //
    // Output: [[1,3],[2,4],[4,6],[3,6],[1,5]]
    //
    // Hint: comparingInt(end-start) + thenComparingInt(start)
    // ─────────────────────────────────────────────────────────────

    public static int[][] challenge4(int[][] intervals) {
        if (intervals == null)
            throw new IllegalArgumentException("Intervals cannot be null");

        // TODO
        Arrays.sort(intervals,
                Comparator.comparingInt((int[] a) -> a[1] - a[0])
                        .thenComparingInt((int[] a) -> a[0])
        );

        return intervals;
    }

    public static int[][] challenge4_2(int[][] intervals) {
        if (intervals == null)
            throw new IllegalArgumentException("Intervals cannot be null");

        // TODO
        Arrays.sort(intervals, (a, b) -> {
            int durationA = a[1] - a[0];
            int durationB = b[1] - b[0];
            if (durationA != durationB) return Integer.compare(durationA, durationB);
            return Integer.compare(a[0], b[0]);

        });

        return intervals;
    }

    public static int[][] challenge4_3(int[][] intervals) {
        if (intervals == null)
            throw new IllegalArgumentException("Intervals cannot be null");

        Comparator<int[]> byDuration = Comparator.comparingInt(a -> a[1] - a[0]);
        Comparator<int[]> byStart = Comparator.comparingInt(a -> a[0]);

        Arrays.sort(intervals, byDuration.thenComparing(byStart));

        return intervals;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Sort Products by Category Priority Map
    //               then Price ASC then Name ASC
    //
    // Sort products using a PRIORITY MAP for categories:
    //   Electronics = 0 (first)
    //   Clothing    = 1
    //   Food        = 2 (last)
    //
    // For same category → price ascending.
    // For same price    → name alphabetically.
    //
    // Input:
    //   ("Phone",    "Electronics", 999.0)
    //   ("Shirt",    "Clothing",     29.9)
    //   ("Laptop",   "Electronics", 1299.0)
    //   ("Apple",    "Food",          0.5)
    //   ("Jeans",    "Clothing",     59.9)
    //   ("Tablet",   "Electronics",  499.0)
    //
    // Output:
    //   Tablet   Electronics  499.0
    //   Phone    Electronics  999.0
    //   Laptop   Electronics 1299.0
    //   Shirt    Clothing      29.9
    //   Jeans    Clothing      59.9
    //   Apple    Food           0.5
    //
    // Hint: Map<String,Integer> categoryPriority
    //       + comparingInt(priority) + thenComparingDouble(price)
    //       + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Product(String name, String category, double price) {}

    public static List<Product> challenge5(List<Product> products) {
        if (products == null)
            throw new IllegalArgumentException("Products cannot be null");

        // TODO
        Map<String, Integer> priorityMap = new HashMap<>(Map.of(
                "Electronics", 0,
                "Clothing", 1,
                "Food", 2
        ));

        Comparator<Product> byPriority = Comparator.comparing( e -> priorityMap.get(e.category()));
        Comparator<Product> byPrice = Comparator.comparingDouble(Product::price);
        Comparator<Product> byName = Comparator.comparing(Product::name);

        products.sort(byPriority.thenComparing(byPrice).thenComparing(byName));

        return products;
    }

    public static List<Product> challenge5_2(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("products cannot be null");

        Map<String, Integer> priorityMap = new HashMap<>(Map.of(
                "Electronics", 0,
                "Clothing", 1,
                "Food", 2
        ));

        products.sort(
                Comparator.comparing((Product e) -> priorityMap.get(e.category()))
                        .thenComparingDouble(Product::price)
                        .thenComparing(Product::name)
        );

        return products;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Sort Map Entries by Value ASC
    //               then Key Length ASC then Key Alpha ASC
    //
    // Given a Map<String, Integer> return sorted list of entries:
    // 1. Value ascending
    // 2. Key length ascending (tie on value)
    // 3. Key alphabetically ascending (tie on value + length)
    //
    // Input:  {banana=2, fig=2, apple=5, cat=2, kiwi=5}
    // Output: [(cat,2),(fig,2),(banana,2),(apple,5),(kiwi,5)]
    //         value=2:  cat(3)<fig(3)<banana(6) length then alpha
    //         value=5:  apple(5)<kiwi(4)... wait kiwi len=4 < apple len=5
    //         value=5:  kiwi(4) before apple(5) by length!
    //
    // Hint: comparingByValue()
    //       .thenComparing(comparingByKey(comparingInt(length)))
    //       .thenComparing(comparingByKey())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge6(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");

        // TODO
        return map.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Integer>comparingByValue()
                                .thenComparing(Map.Entry.comparingByKey(Comparator.comparingInt(String::length)))
                                .thenComparing(Map.Entry.comparingByKey()))
                .toList();
    }

    public static List<Map.Entry<String, Integer>> challenge6_2(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");

        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> byLengthKey = Map.Entry.comparingByKey(Comparator.comparingInt(String::length));
        Comparator<Map.Entry<String, Integer>> byKey = Map.Entry.comparingByKey();

        // TODO
        return map.entrySet().stream()
                .sorted(byValue.thenComparing(byLengthKey).thenComparing(byKey))
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Sort 2D Matrix by Even Count DESC
    //               then Row Sum ASC for ties
    //
    // Sort rows by number of EVEN elements descending.
    // If even count is equal sort by row sum ascending.
    //
    // Input:  [[1,2,4],[3,5,7],[2,4,6],[1,3,5],[1,2,3]]
    //
    // Even counts:
    //   [1,2,4] → 2 evens (2,4)
    //   [3,5,7] → 0 evens
    //   [2,4,6] → 3 evens (2,4,6)
    //   [1,3,5] → 0 evens
    //   [1,2,3] → 1 even  (2)
    //
    // Sort even count DESC: 3,2,1,0,0
    //   [2,4,6]=3  sum=12
    //   [1,2,4]=2  sum=7
    //   [1,2,3]=1  sum=6
    //   [3,5,7]=0  sum=15 → tie on 0 evens → sum ASC: 15 vs...
    //   [1,3,5]=0  sum=9
    //
    // Output: [[2,4,6],[1,2,4],[1,2,3],[1,3,5],[3,5,7]]
    //          3evens  2evens  1even   0evens  0evens
    //                                  sum=9    sum=15
    //
    // Hint: count evens with stream filter
    //       comparingInt(evenCount).reversed()
    //       + thenComparingInt(rowSum)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge7(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");

        // TODO
        Arrays.sort(matrix, (int[] a, int[] b) -> {
            long aEvens = Arrays.stream(a).filter(d -> d % 2 == 0).count();
            long bEvens = Arrays.stream(b).filter(d -> d % 2 == 0).count();
            if (aEvens != bEvens) return Long.compare(bEvens, aEvens);
            return Integer.compare(Arrays.stream(a).sum(), Arrays.stream(b).sum());
        });
        return matrix;
    }

    public static int[][] challenge7_2(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");

        // TODO
        Comparator<int[]> byEven =
                Comparator.comparingLong((int[] a) ->
                        Arrays.stream(a).filter(d -> d % 2 == 0).count())
                        .reversed();

        Comparator<int[]> bySum =
                Comparator.comparingInt(a ->
                        Arrays.stream(a).sum());

        Arrays.sort(matrix, byEven.thenComparing(bySum));
        return matrix;
    }

    public static int[][] challenge7_3(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");

        // TODO
        // Optimization to calculate one time all sums and even count
        Map<int[], long[]> cache = new IdentityHashMap<>();
        for (int[] row : matrix) {
            long evenCount = Arrays.stream(row).filter(n -> n % 2 == 0).count();
            long sumRow = Arrays.stream(row).sum();
            cache.put(row, new long[]{evenCount, sumRow});
        }

        Arrays.sort(matrix, (a, b) -> {
            long[] cacheA = cache.get(a);
            long[] cacheB = cache.get(b);
            if (cacheA[0] != cacheB[0]) return Long.compare(cacheB[0], cacheA[0]);
            return Long.compare(cacheA[1], cacheB[1]);
        });

        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Sort Tasks by Priority Map
    //               then Deadline ASC then Name ASC
    //
    // Sort tasks using a PRIORITY MAP:
    //   HIGH   = 0 (most urgent/first)
    //   MEDIUM = 1
    //   LOW    = 2 (least urgent/last)
    //
    // For same priority → deadline ascending (earlier first).
    // For same deadline → name alphabetically.
    //
    // Input:
    //   ("Deploy",  "HIGH",   3)
    //   ("Test",    "MEDIUM", 1)
    //   ("Review",  "HIGH",   1)
    //   ("Meeting", "LOW",    2)
    //   ("Fix Bug", "HIGH",   1)
    //   ("Docs",    "MEDIUM", 3)
    //
    // Output:
    //   Fix Bug  HIGH   1  ← HIGH earliest, Fix Bug < Review alpha
    //   Review   HIGH   1
    //   Deploy   HIGH   3
    //   Test     MEDIUM 1
    //   Docs     MEDIUM 3
    //   Meeting  LOW    2
    //
    // Hint: Map<String,Integer> taskPriority
    //       + comparingInt(priority) + thenComparingInt(deadline)
    //       + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Task(String name, String priority, int deadline) {}

    public static List<Task> challenge8(List<Task> tasks) {
        if (tasks == null)
            throw new IllegalArgumentException("Tasks cannot be null");

        Map<String, Integer> priorityMap = new HashMap<>(Map.of(
                "HIGH", 0,
                "MEDIUM", 1,
                "LOW", 2
        ));

        // TODO
        tasks.sort(
                Comparator.comparingInt((Task t) -> priorityMap.get(t.priority()))
                        .thenComparingInt(Task::deadline)
                        .thenComparing(Task::name));
        return tasks;
    }

    public static List<Task> challenge8_2(List<Task> tasks) {
        if (tasks == null)
            throw new IllegalArgumentException("Tasks cannot be null");

        Map<String, Integer> priorityMap = new HashMap<>(Map.of(
                "HIGH", 0,
                "MEDIUM", 1,
                "LOW", 2
        ));

        Comparator<Task> byPriority = Comparator.comparingInt(t -> priorityMap.get(t.priority()));
        Comparator<Task> byDeadline = Comparator.comparingInt(Task::deadline);
        Comparator<Task> byName = Comparator.comparing(Task::name);

        // TODO
        tasks.sort(byPriority.thenComparing(byDeadline).thenComparing(byName));
        return tasks;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Sort 2D Matrix Lexicographically
    //               (compare element by element like a dictionary)
    //
    // Sort rows lexicographically — compare element by element.
    // First difference found determines the order.
    // If one row is a prefix of another → shorter row comes first.
    //
    // Input:  [[3,1,4],[1,5,9],[1,5,2],[2,6,5],[1,5,9]]
    //
    // Compare [1,5,2] vs [1,5,9]:
    //   element 0: 1==1 → tie
    //   element 1: 5==5 → tie
    //   element 2: 2 < 9 → [1,5,2] comes first!
    //
    // Output: [[1,5,2],[1,5,9],[1,5,9],[2,6,5],[3,1,4]]
    //
    // Hint: (a, b) → loop comparing element by element
    //       Integer.compare(a[i], b[i]) when a[i] != b[i]
    //       Integer.compare(a.length, b.length) if one is prefix
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge9(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Sort Cities by Continent Priority Map
    //                then Population DESC then Name ASC
    //
    // Sort cities using a PRIORITY MAP for continents:
    //   Europe       = 0 (first)
    //   Asia         = 1
    //   Americas     = 2
    //   Africa       = 3 (last)
    //
    // For same continent → population descending (largest first).
    // For same population → city name alphabetically.
    //
    // Input:
    //   ("Paris",     "Europe",   2_161_000)
    //   ("Tokyo",     "Asia",    13_960_000)
    //   ("Lagos",     "Africa",  14_862_000)
    //   ("New York",  "Americas", 8_336_000)
    //   ("London",    "Europe",   8_982_000)
    //   ("Shanghai",  "Asia",    24_870_000)
    //   ("São Paulo", "Americas",12_325_000)
    //   ("Cairo",     "Africa",  20_076_000)
    //
    // Output:
    //   London    Europe    8_982_000  ← Europe largest pop
    //   Paris     Europe    2_161_000
    //   Shanghai  Asia     24_870_000  ← Asia largest pop
    //   Tokyo     Asia     13_960_000
    //   São Paulo Americas 12_325_000  ← Americas largest pop
    //   New York  Americas  8_336_000
    //   Cairo     Africa   20_076_000  ← Africa largest pop
    //   Lagos     Africa   14_862_000
    //
    // Hint: Map<String,Integer> continentPriority
    //       + comparingInt(priority) + thenComparingInt(pop reversed)
    //       + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record City(String name, String continent, int population) {}

    public static List<City> challenge10(List<City> cities) {
        if (cities == null)
            throw new IllegalArgumentException("Cities cannot be null");
        // TODO
        return new ArrayList<>();
    }
}