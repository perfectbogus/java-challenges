package dev.perfectbogus.comparators;

import java.util.*;
import java.util.stream.*;

public class TypeWitnessChallenges {

    private static Comparator<Map.Entry<String, Integer>> byLengthKey;

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Sort Strings by Length DESC then Alpha ASC
    //
    // Given a list of strings sort by LENGTH descending.
    // For same length → alphabetically ascending.
    //
    // Input:  ["fig","banana","kiwi","apple","plum","date"]
    // Output: ["banana","apple","kiwi","plum","date","fig"]
    //          len=6    len=5   len=4  len=4  len=4  len=3
    //                           kiwi<plum<date alphabetically
    //
    // ⚠️ TYPE WITNESS NEEDED:
    // Comparator.comparingInt(String::length).reversed()
    // → won't compile without type witness!
    // Comparator.<String>comparingInt(String::length).reversed() ✓
    // OR
    // Comparator.comparingInt((String s) -> s.length()).reversed() ✓
    //
    // Hint: comparingInt(length).reversed() + thenComparing(natural)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge1(List<String> words) {
        if (words == null)
            throw new IllegalArgumentException("Words cannot be null");
        // TODO
        words.sort(
                Comparator.comparingInt(String::length).reversed()
                        .thenComparing(String::compareTo)
        );
        return words;
    }

    public static List<String> challenge1_2(List<String> words) {
        if (words == null)
            throw new IllegalArgumentException("Words cannot be null");
        // TODO
        words.sort(
                Comparator.comparingInt((String s) -> s.length()).reversed()
                        .thenComparing(Comparator.naturalOrder())
        );
        return words;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Sort Map Entries by Value DESC then Key DESC
    //
    // Given a Map<String, Integer> return sorted list of entries
    // by VALUE descending then KEY descending for ties.
    //
    // Input:  {apple=5, banana=3, cherry=5, date=1, elderberry=3}
    // Output: [(cherry,5),(apple,5),(elderberry,3),(banana,3),(date,1)]
    //          val=5: cherry>apple DESC alphabetically
    //          val=3: elderberry>banana DESC alphabetically
    //
    // ⚠️ TYPE WITNESS NEEDED:
    // Map.Entry.comparingByValue().reversed()
    // → needs: Map.Entry.<String, Integer>comparingByValue().reversed()
    //
    // Map.Entry.comparingByKey().reversed()
    // → needs: Map.Entry.<String, Integer>comparingByKey().reversed()
    //
    // Hint: comparingByValue().reversed()
    //       .thenComparing(comparingByKey().reversed())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge2(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");
        // TODO
        return map.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Integer>comparingByValue().reversed()
                                .thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed())
                ).toList();
    }

    public static List<Map.Entry<String, Integer>> challenge2_2(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, Integer>> byValueDesc = Map.Entry.<String, Integer>comparingByValue().reversed();
        Comparator<Map.Entry<String, Integer>> byKeyDesc   = Map.Entry.<String, Integer>comparingByKey().reversed();

        return map.entrySet().stream()
                .sorted(byValueDesc.thenComparing(byKeyDesc))
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Sort Employees by Salary DESC then Experience DESC
    //
    // Sort employees by SALARY descending.
    // For same salary → years of experience descending.
    // For same experience → name alphabetically ascending.
    //
    // Input:
    //   ("Alice",  95000, 5)
    //   ("Bob",    85000, 8)
    //   ("Carol",  95000, 3)
    //   ("Diana",  85000, 8)
    //   ("Eve",    72000, 6)
    //
    // Output:
    //   Alice  95000 5  ← salary=95000, exp=5>3 DESC... wait
    //   Carol  95000 3     salary=95000, exp: 5>3 DESC → Alice first
    //   Bob    85000 8  ← salary=85000, exp=8=8 → name ASC
    //   Diana  85000 8
    //   Eve    72000 6
    //
    // ⚠️ TYPE WITNESS NEEDED on BOTH reversed():
    // Comparator.comparingDouble(Employee::salary).reversed()
    // → needs: Comparator.comparingDouble((Employee e) -> e.salary()).reversed()
    //
    // Comparator.comparingInt(Employee::yearsExp).reversed()
    // → needs: Comparator.comparingInt((Employee e) -> e.yearsExp()).reversed()
    //
    // Hint: comparingDouble(salary).reversed()
    //       .thenComparing(comparingInt(exp).reversed())
    //       .thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, double salary, int yearsExp) {}

    public static List<Employee> challenge3(List<Employee> employees) {
        if (employees == null)
            throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        employees.sort(
                Comparator.comparingDouble(Employee::salary).reversed()
                        .thenComparing(Comparator.comparingInt(Employee::yearsExp).reversed())
                        .thenComparing(Employee::name)
        );
        return employees;
    }

    public static List<Employee> challenge3_2(List<Employee> employees) {
        if (employees == null)
            throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        employees.sort(
                Comparator.comparingDouble((Employee e) -> e.salary()).reversed()
                        .thenComparing(Comparator.comparingInt((Employee e) -> e.yearsExp()).reversed())
                        .thenComparing(e -> e.name())
        );
        return employees;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Sort 2D Array by Row Max DESC then Row Min ASC
    //
    // Sort rows by MAX element descending.
    // For same max → MIN element ascending.
    // For same min → first element ascending.
    //
    // Input:  [[3,1,4],[5,2,1],[3,1,2],[5,0,3],[2,6,1]]
    //
    // Max values: [4, 5, 3, 5, 6]
    // Sort max DESC: 6,5,5,4,3
    //   max=6: [2,6,1]
    //   max=5: [5,2,1] min=1, [5,0,3] min=0 → min ASC: [5,0,3] first
    //   max=4: [3,1,4]
    //   max=3: [3,1,2]
    //
    // Output: [[2,6,1],[5,0,3],[5,2,1],[3,1,4],[3,1,2]]
    //
    // ⚠️ TYPE WITNESS NEEDED:
    // Comparator.comparingInt(row -> Arrays.stream(row).max()...).reversed()
    // → needs: Comparator.comparingInt((int[] row) -> ...).reversed()
    //
    // Hint: comparingInt(maxElement).reversed()
    //       .thenComparingInt(minElement)
    //       .thenComparingInt(firstElement)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge4_2(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        Arrays.sort(matrix,
                Comparator.comparingInt((int[] row) -> Arrays.stream(row).max().orElse(0)).reversed()
                        .thenComparingInt((int[] row) -> Arrays.stream(row).min().orElse(0))
                        .thenComparingInt((int[] row) -> row[0]));
        return matrix;
    }

    public static int[][] challenge4(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        Map<int[], int[]> cache = new IdentityHashMap<>();

        for (int[] row : matrix) {
            int max = Arrays.stream(row).max().orElse(0);
            int min = Arrays.stream(row).min().orElse(0);
            cache.put(row, new int[]{min, max});
        }

        Arrays.sort(matrix, (int[] a, int[] b) -> {
            int[] cacheA = cache.get(a);
            int[] cacheB = cache.get(b);
            if (cacheA[1] != cacheB[1]) return Integer.compare(cacheB[1], cacheA[1]);
            if (cacheA[0] != cacheB[0]) return Integer.compare(cacheA[0], cacheB[0]);
            return Integer.compare(a[0], b[0]);
        });

        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Sort Map Entries by Key Length DESC
    //               then Value ASC then Key Alpha ASC
    //
    // Given a Map<String, Integer> return sorted list of entries:
    // 1. Key LENGTH descending
    // 2. Value ascending (tie on length)
    // 3. Key alphabetically ascending (tie on length + value)
    //
    // Input:  {cat=3, elephant=1, dog=5, ant=2, bear=4, eel=5}
    //
    // Key lengths:
    //   elephant=8: val=1
    //   bear=4:     val=4
    //   cat=3:      val=3
    //   dog=3:      val=5  len=3, val ASC: cat(3)<eel... wait
    //   ant=3:      val=2  len=3, val ASC: ant(2),cat(3),dog(5)
    //   eel=3:      val=5  len=3, eel=5=dog=5 → alpha: dog<eel
    //
    // Output: [(elephant,1),(bear,4),(ant,2),(cat,3),(dog,5),(eel,5)]
    //
    // ⚠️ TYPE WITNESS NEEDED:
    // Map.Entry.comparingByKey(Comparator.comparingInt(String::length))
    //          .reversed()
    // → needs: Map.Entry.<String,Integer>comparingByKey(...)
    //          .reversed()
    //
    // Hint: comparingByKey(comparingInt(length)).reversed()
    //       .thenComparing(comparingByValue())
    //       .thenComparing(comparingByKey())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge5(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");
        // TODO
        return map.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Integer>comparingByKey(Comparator.<String>comparingInt(s -> s.length()).reversed())
                                .thenComparing(Map.Entry.comparingByValue())
                                .thenComparing(Map.Entry.comparingByKey())
                ).toList();
    }

    public static List<Map.Entry<String, Integer>> challenge5_2(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");

        Comparator<Map.Entry<String, Integer>> byLengthKey =
                Map.Entry.<String, Integer>comparingByKey(Comparator.comparingInt(String::length)).reversed();
        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> byKey = Map.Entry.comparingByKey();
        // TODO
        return map.entrySet().stream()
                .sorted(byLengthKey.thenComparing(byValue).thenComparing(byKey)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — PriorityQueue Sorted by Score DESC then Name ASC
    //
    // Given a list of players, use a PriorityQueue with a Comparator
    // that sorts by SCORE descending, then NAME ascending.
    // Poll all elements into a result list.
    //
    // Input:
    //   ("Alice", 850)
    //   ("Bob",   920)
    //   ("Carol", 850)
    //   ("Diana", 920)
    //   ("Eve",   750)
    //
    // Output:
    //   Bob   920  ← score=920, Bob<Diana alpha
    //   Diana 920
    //   Alice 850  ← score=850, Alice<Carol alpha
    //   Carol 850
    //   Eve   750
    //
    // ⚠️ TYPE WITNESS NEEDED inside PriorityQueue constructor:
    // new PriorityQueue<>(
    //     Comparator.comparingInt(Player::score).reversed()
    //               .thenComparing(Player::name)
    // )
    // → Comparator.comparingInt((Player p) -> p.score()).reversed()
    //   OR
    // → Comparator.<Player>comparingInt(Player::score).reversed()
    //
    // Hint: PriorityQueue<Player> pq = new PriorityQueue<>(comparator)
    //       pq.addAll(players) → poll all into result list
    // ─────────────────────────────────────────────────────────────
    record Player(String name, int score) {}

    public static List<Player> challenge6_2(List<Player> players) {
        if (players == null)
            throw new IllegalArgumentException("Players cannot be null");
        // TODO
        Queue<Player> q = new PriorityQueue<>(
                Comparator.comparingInt(Player::score).reversed()
                        .thenComparing(Player::name)
        );

        q.addAll(players);

        List<Player> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        return result;
    }

    public static List<Player> challenge6(List<Player> players) {
        if (players == null)
            throw new IllegalArgumentException("Players cannot be null");
        // TODO
        Comparator<Player> byScoreDesc = Comparator.comparingInt(Player::score).reversed();
        Comparator<Player> byName = Comparator.comparing(Player::name);

        Queue<Player> q = new PriorityQueue<>(byScoreDesc.thenComparing(byName));

        q.addAll(players);

        List<Player> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Sort Products by Stock DESC then Price DESC
    //               then Name ASC
    //
    // Sort products:
    // 1. Stock (quantity) DESCENDING
    // 2. Price DESCENDING (tie on stock)
    // 3. Name alphabetically ASCENDING (tie on stock + price)
    //
    // Input:
    //   ("Phone",  999.0, 50)
    //   ("Tablet", 499.0, 50)
    //   ("Laptop", 1299.0, 30)
    //   ("Watch",  299.0, 50)
    //   ("Cable",  9.99,  100)
    //
    // Output:
    //   Cable  9.99   100  ← stock=100 highest
    //   Phone  999.0   50  ← stock=50, price=999 highest
    //   Tablet 499.0   50  ← stock=50, price=499
    //   Watch  299.0   50  ← stock=50, price=299
    //   Laptop 1299.0  30  ← stock=30 lowest
    //
    // ⚠️ TYPE WITNESS NEEDED on BOTH reversed():
    // Comparator.comparingInt(Product::stock).reversed()
    // → (Product p) -> p.stock() or <Product>comparingInt
    //
    // Comparator.comparingDouble(Product::price).reversed()
    // → (Product p) -> p.price() or <Product>comparingDouble
    //
    // Hint: comparingInt(stock).reversed()
    //       .thenComparing(comparingDouble(price).reversed())
    //       .thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    record Product(String name, double price, int stock) {}

    public static List<Product> challenge7(List<Product> products) {
        if (products == null)
            throw new IllegalArgumentException("Products cannot be null");
        // TODO
        products.sort(
                Comparator.comparingInt(Product::stock).reversed()
                        .thenComparing(Comparator.comparingDouble(Product::price).reversed())
                        .thenComparing(Product::name)
        );
        return products;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Sort Map Entries into TreeMap by Value DESC
    //
    // Given a Map<String, Integer>, collect into a TreeMap
    // where keys are sorted by VALUE descending using a custom
    // Comparator that looks up values.
    // For same value → key alphabetically ascending.
    //
    // Input:  {apple=3, banana=1, cherry=5, date=3, elderberry=2}
    //
    // Output TreeMap iteration order:
    //   cherry=5   ← highest value
    //   apple=3    ← value=3, apple<date alpha
    //   date=3
    //   elderberry=2
    //   banana=1   ← lowest value
    //
    // ⚠️ TYPE WITNESS NEEDED in TreeMap constructor:
    // new TreeMap<>(
    //     Comparator.comparingInt(map::get).reversed()
    //               .thenComparing(...)
    // )
    // → Comparator.<String>comparingInt(map::get).reversed()
    //
    // Hint:
    // Map<String, Integer> sorted = new TreeMap<>(
    //     Comparator.<String>comparingInt(map::get)
    //               .reversed()
    //               .thenComparing(Comparator.naturalOrder())
    // );
    // sorted.putAll(map);
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge8(Map<String, Integer> map) {
        if (map == null)
            throw new IllegalArgumentException("Map cannot be null");

        Map<String, Integer> treeMap = new TreeMap<>(
                Comparator.<String>comparingInt(map::get)
                        .reversed()
                        .thenComparing(Comparator.naturalOrder())
        );

        treeMap.putAll(map);

        return treeMap;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Sort 2D Array by Row Sum DESC
    //               then Row Length DESC then First Element ASC
    //
    // Sort rows:
    // 1. Row SUM descending
    // 2. Row LENGTH descending (tie on sum)
    // 3. First element ascending (tie on sum + length)
    //
    // Input:  [[1,2,3],[4,1],[2,3,1],[6],[1,2,3,0]]
    //
    // Sums:    [6, 5, 6, 6, 6]
    // Lengths: [3, 2, 3, 1, 4]
    //
    // sum=6: [1,2,3] len=3, [6] len=1, [1,2,3,0] len=4, [2,3,1] len=3
    //   len DESC: [1,2,3,0](4), [1,2,3](3), [2,3,1](3), [6](1)
    //   len=3 tie: first element ASC: [1,2,3](1) before [2,3,1](2)
    // sum=5: [4,1] len=2
    //
    // Output: [[1,2,3,0],[1,2,3],[2,3,1],[6],[4,1]]
    //
    // ⚠️ TYPE WITNESS NEEDED on BOTH reversed():
    // Comparator.comparingInt(row -> Arrays.stream(row).sum()).reversed()
    // → Comparator.comparingInt((int[] row) -> ...).reversed()
    //
    // Comparator.comparingInt(row -> row.length).reversed()
    // → Comparator.comparingInt((int[] row) -> row.length).reversed()
    //
    // Hint: comparingInt(sum).reversed()
    //       .thenComparing(comparingInt(length).reversed())
    //       .thenComparingInt(firstElement)
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge9(int[][] matrix) {
        if (matrix == null)
            throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Sort Books by Rating DESC then Pages DESC
    //                then Title ASC then Author ASC
    //
    // Sort books:
    // 1. Rating DESCENDING (highest rated first)
    // 2. Pages DESCENDING (longer book first if same rating)
    // 3. Title alphabetically ASCENDING (tie on rating + pages)
    // 4. Author alphabetically ASCENDING (last tie-breaker)
    //
    // Input:
    //   ("Clean Code",    "Martin",  4.5, 431)
    //   ("Refactoring",   "Fowler",  4.5, 448)
    //   ("Design Patterns","GoF",    4.5, 395)
    //   ("The Pragmatic", "Thomas",  4.8, 352)
    //   ("SICP",          "Abelson", 4.7, 657)
    //   ("Clean Code 2",  "Martin",  4.5, 431)
    //
    // Output:
    //   The Pragmatic  Thomas  4.8 352  ← rating=4.8 highest
    //   SICP           Abelson 4.7 657  ← rating=4.7
    //   Refactoring    Fowler  4.5 448  ← rating=4.5, pages=448 highest
    //   Clean Code     Martin  4.5 431  ← pages=431, title C<C2 alpha
    //   Clean Code 2   Martin  4.5 431  ← same pages, title
    //   Design Patterns GoF   4.5 395  ← pages=395 lowest
    //
    // ⚠️ TYPE WITNESS NEEDED on BOTH reversed():
    // Comparator.comparingDouble(Book::rating).reversed()
    // → Comparator.comparingDouble((Book b) -> b.rating()).reversed()
    //
    // Comparator.comparingInt(Book::pages).reversed()
    // → Comparator.comparingInt((Book b) -> b.pages()).reversed()
    //
    // Hint: comparingDouble(rating).reversed()
    //       .thenComparing(comparingInt(pages).reversed())
    //       .thenComparing(title)
    //       .thenComparing(author)
    // ─────────────────────────────────────────────────────────────
    record Book(String title, String author, double rating, int pages) {}

    public static List<Book> challenge10(List<Book> books) {
        if (books == null)
            throw new IllegalArgumentException("Books cannot be null");
        // TODO
        return new ArrayList<>();
    }
}