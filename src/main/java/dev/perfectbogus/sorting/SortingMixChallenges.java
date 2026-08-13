package dev.perfectbogus.sorting;

import java.util.*;
import java.util.stream.*;

public class SortingMixChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — 🟢 EASY
    // Sort integers by ABSOLUTE VALUE ascending then original value ASC for ties
    //
    // Input:  [-5, 3, -3, 1, -1, 4, -4, 2]
    // Output: [-1, 1, -3, 3, 2, -4, 4, -5]
    //          |1| |1| |3| |3| |2| |4| |4| |5|
    //          abs=1     abs=3     abs=2(before 3?) wait...
    //
    // abs=1: -1, 1  → original value ASC: -1 before 1
    // abs=2: 2
    // abs=3: -3, 3  → original value ASC: -3 before 3
    // abs=4: -4, 4  → original value ASC: -4 before 4
    // abs=5: -5
    //
    // Output: [-1, 1, 2, -3, 3, -4, 4, -5]
    //
    // Hint: comparingInt(Math::abs) + thenComparingInt(Integer::intValue)
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge1(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        return numbers.stream().sorted(
                Comparator.<Integer>comparingInt(Math::abs).thenComparing(Integer::intValue)
        ).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — 🟢 EASY
    // Sort strings by number of VOWELS ascending then alphabetically ASC for ties
    //
    // Vowels = a, e, i, o, u (lowercase only)
    //
    // Input:  ["hello","fig","apple","cat","rhythm","bee","cry"]
    // Output: ["cry","fig","rhythm","bee","cat","hello","apple"]
    //
    // Vowel counts:
    //   cry    → 0
    //   fig    → 1 (i)
    //   rhythm → 0 ← wait, no vowels!
    //   bee    → 2 (e,e)
    //   cat    → 1 (a)
    //   hello  → 2 (e,o)
    //   apple  → 2 (a,e)... wait apple has 2 vowels: a,e
    //
    // vowels=0: cry, rhythm → alpha: cry, rhythm
    // vowels=1: fig(i), cat(a) → alpha: cat, fig
    // vowels=2: bee(ee), hello(eo), apple(ae) → alpha: apple, bee, hello
    //
    // Output: ["cry","rhythm","cat","fig","apple","bee","hello"]
    //
    // Hint: count vowels with filter, comparingInt(vowelCount) + thenComparing(alpha)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        boolean[] lookup = new boolean[128];
        lookup['a'] = true;
        lookup['e'] = true;
        lookup['i'] = true;
        lookup['o'] = true;
        lookup['u'] = true;

        Comparator<String> byCountVowels = Comparator.comparing(s -> {
            int countVowels = 0;
            for (char c : s.toCharArray()) {
                if (lookup[c]) countVowels++;
            }
            return countVowels;
        });

        words.sort(byCountVowels.thenComparing(s -> s.charAt(0)));

        return words;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — 🟢 EASY
    // Sort Map entries by KEY last character ASC then by VALUE DESC for ties
    //
    // Input:  {"banana"=3, "apple"=5, "grape"=2, "orange"=4, "mango"=1}
    //
    // Last chars: banana→'a', apple→'e', grape→'e', orange→'e', mango→'o'
    // last='a': banana(3)
    // last='e': apple(5), grape(2), orange(4) → value DESC: apple(5),orange(4),grape(2)
    // last='o': mango(1)
    //
    // Output: [(banana,3),(apple,5),(orange,4),(grape,2),(mango,1)]
    //
    // Hint: comparingByKey using last character
    //       thenComparing(comparingByValue().reversed())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge3(Map<String, Integer> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, Integer>> byLastCharKey =
                Map.Entry.comparingByKey(
                        Comparator.comparingInt(s -> s.charAt(s.length() - 1))
                );
        Comparator<Map.Entry<String, Integer>> byValueDesc =
                Map.Entry.<String, Integer>comparingByValue().reversed();

        return map.entrySet().stream().sorted(byLastCharKey.thenComparing(byValueDesc)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — 🟡 MEDIUM
    // Sort orders by STATUS PRIORITY then TOTAL (price*quantity) DESC then ID ASC
    //
    // Status priority: DELIVERED=0, PENDING=1, CANCELLED=2
    //
    // record Order(String id, String status, double price, int quantity)
    //
    // Input:
    //   ("O1", PENDING,   10.0, 3)  total=30
    //   ("O2", DELIVERED, 50.0, 2)  total=100
    //   ("O3", CANCELLED, 20.0, 1)  total=20
    //   ("O4", DELIVERED, 30.0, 3)  total=90
    //   ("O5", PENDING,   15.0, 2)  total=30
    //   ("O6", DELIVERED, 50.0, 2)  total=100
    //
    // Status priority first:
    //   DELIVERED: O2(100), O4(90), O6(100) → total DESC, then ID ASC
    //     total=100: O2, O6 → ID ASC: O2, O6
    //     total=90:  O4
    //   PENDING: O1(30), O5(30) → total DESC same → ID ASC: O1, O5
    //   CANCELLED: O3
    //
    // Output: [O2, O6, O4, O1, O5, O3]
    //
    // Hint: priority map + comparingInt(priority)
    //       .thenComparing(comparingDouble(total).reversed())
    //       .thenComparing(id)
    // ─────────────────────────────────────────────────────────────
    record Order(String id, String status, double price, int quantity) {}

    public static List<Order> challenge4(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        Map<String, Integer> priorityMap = new HashMap<>(Map.of(
                "DELIVERED", 0,
                "PENDING", 1,
                "CANCELLED", 2
        ));

        Comparator<Order> byPriority = Comparator.comparingInt(o -> priorityMap.get(o.status()));
        Comparator<Order> byTotalDesc = Comparator.<Order>comparingDouble(o -> o.quantity() * o.price()).reversed();
        Comparator<Order> byId = Comparator.comparing(Order::id);
        // TODO
        orders.sort(byPriority.thenComparing(byTotalDesc).thenComparing(byId));

        return orders;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — 🟢 EASY
    // Sort 2D array by NUMBER OF COLUMNS ASC then ROW SUM DESC for ties
    //
    // Input:  [[1,2,3,4],[5,6],[7,8,9],[1,2],[3,4,5]]
    //
    // Columns: [4, 2, 3, 2, 3]
    // cols=2: [5,6] sum=11, [1,2] sum=3  → sum DESC: [5,6],[1,2]
    // cols=3: [7,8,9] sum=24, [3,4,5] sum=12 → sum DESC: [7,8,9],[3,4,5]
    // cols=4: [1,2,3,4] sum=10
    //
    // Output: [[5,6],[1,2],[7,8,9],[3,4,5],[1,2,3,4]]
    //
    // Hint: comparingInt(length) + thenComparing(sum.reversed())
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge5(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — 🟡 MEDIUM
    // Sort employees by SENIORITY LEVEL (priority map) then SALARY DESC then NAME ASC
    //
    // Seniority based on yearsOfExperience:
    //   >= 8 years → "SENIOR"   priority=0
    //   >= 4 years → "MID"      priority=1
    //   < 4 years  → "JUNIOR"   priority=2
    //
    // record Employee(String name, double salary, int yearsOfExperience)
    //
    // Input:
    //   ("Alice",  95000, 10)  SENIOR
    //   ("Bob",    60000, 2)   JUNIOR
    //   ("Carol",  85000, 8)   SENIOR
    //   ("Diana",  70000, 5)   MID
    //   ("Eve",    95000, 9)   SENIOR
    //   ("Frank",  65000, 4)   MID
    //
    // SENIOR (priority=0) → salary DESC then name ASC:
    //   Alice=95000, Eve=95000 → name ASC: Alice, Eve
    //   Carol=85000
    // MID (priority=1) → salary DESC then name ASC:
    //   Diana=70000, Frank=65000
    // JUNIOR (priority=2):
    //   Bob=60000
    //
    // Output: [Alice, Eve, Carol, Diana, Frank, Bob]
    //
    // Hint: compute seniority level per employee
    //       Map<String, Integer> seniorityPriority
    //       comparingInt(seniority) + comparingDouble(salary).reversed() + comparing(name)
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, double salary, int yearsOfExperience) {}

    public static List<Employee> challenge6(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — 🟢 EASY
    // Sort strings by number of UPPERCASE letters DESC then length ASC then alpha ASC
    //
    // Input:  ["Hello","WORLD","java","Hi","JAVA","cat"]
    //
    // Uppercase counts:
    //   Hello → 1 (H)
    //   WORLD → 5
    //   java  → 0
    //   Hi    → 1 (H)
    //   JAVA  → 4
    //   cat   → 0
    //
    // upper=5: WORLD
    // upper=4: JAVA
    // upper=1: Hello(5), Hi(2) → length ASC: Hi(2), Hello(5)
    // upper=0: java(4), cat(3) → length ASC: cat(3), java(4)
    //
    // Output: ["WORLD","JAVA","Hi","Hello","cat","java"]
    //
    // Hint: count uppercase with filter(Character::isUpperCase)
    //       comparingInt(upperCount).reversed() + thenComparingInt(length) + thenComparing(alpha)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge7(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — 🟡 MEDIUM
    // Sort employees by SALARY-PER-YEAR RATIO DESC then name ASC
    //
    // ratio = salary / yearsOfExperience
    // Higher ratio = more efficient hire → should come first
    //
    // record Employee(String name, double salary, int yearsOfExperience)
    //
    // Input:
    //   ("Alice",  95000, 5)   ratio = 19000.0
    //   ("Bob",    60000, 2)   ratio = 30000.0
    //   ("Carol",  85000, 10)  ratio = 8500.0
    //   ("Diana",  70000, 5)   ratio = 14000.0
    //   ("Eve",    60000, 2)   ratio = 30000.0
    //
    // ratio=30000: Bob, Eve → name ASC: Bob, Eve
    // ratio=19000: Alice
    // ratio=14000: Diana
    // ratio=8500:  Carol
    //
    // Output: [Bob, Eve, Alice, Diana, Carol]
    //
    // Hint: comparingDouble(e -> e.salary() / e.yearsOfExperience()).reversed()
    //       → needs type witness! (Employee e) -> ...
    //       + thenComparing(name)
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge8(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — 🟢 EASY
    // Sort Map entries by (VALUE mod 3) ASC then KEY alphabetically ASC
    //
    // mod 3 groups: 0, 1, 2
    //
    // Input:  {"apple"=7, "banana"=3, "cherry"=5, "date"=9, "elderberry"=4}
    //
    // Mods:
    //   apple      → 7%3=1
    //   banana     → 3%3=0
    //   cherry     → 5%3=2
    //   date       → 9%3=0
    //   elderberry → 4%3=1
    //
    // mod=0: banana, date → alpha ASC: banana, date
    // mod=1: apple, elderberry → alpha ASC: apple, elderberry
    // mod=2: cherry
    //
    // Output: [(banana,3),(date,9),(apple,7),(elderberry,4),(cherry,5)]
    //
    // Hint: comparingInt(e -> e.getValue() % 3) + thenComparing(comparingByKey())
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge9(Map<String, Integer> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — 🟡 MEDIUM
    // Sort products by CATEGORY PRIORITY then DISCOUNT PERCENTAGE DESC then NAME ASC
    //
    // Category priority: Electronics=0, Clothing=1, Food=2
    //
    // Discount = (originalPrice - currentPrice) / originalPrice * 100
    //
    // record Product(String name, String category, double originalPrice, double currentPrice)
    //
    // Input:
    //   ("Phone",   Electronics, 1000, 800)  discount=20%
    //   ("Shirt",   Clothing,    100,  70)   discount=30%
    //   ("Laptop",  Electronics, 2000, 1500) discount=25%
    //   ("Apple",   Food,        2,    1.5)  discount=25%
    //   ("Jeans",   Clothing,    150,  150)  discount=0%
    //   ("Tablet",  Electronics, 500,  500)  discount=0%
    //   ("Banana",  Food,        1,    0.75) discount=25%
    //
    // Electronics → discount DESC then name ASC:
    //   Laptop=25%, Phone=20%, Tablet=0%
    // Clothing → discount DESC then name ASC:
    //   Shirt=30%, Jeans=0%
    // Food → discount DESC then name ASC:
    //   Apple=25%, Banana=25% → name ASC: Apple, Banana
    //
    // Output: [Laptop, Phone, Tablet, Shirt, Jeans, Apple, Banana]
    //
    // Hint: compute discount = (orig - curr) / orig * 100
    //       Map<String,Integer> categoryPriority
    //       comparingInt(priority) + comparingDouble(discount).reversed() + comparing(name)
    // ─────────────────────────────────────────────────────────────
    record Product(String name, String category, double originalPrice, double currentPrice) {}

    public static List<Product> challenge10(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("Products cannot be null");
        // TODO
        return new ArrayList<>();
    }
}