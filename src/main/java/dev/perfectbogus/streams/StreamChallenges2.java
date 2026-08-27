package dev.perfectbogus.streams;

import java.util.*;
import java.util.stream.*;

public class StreamChallenges2 {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–6)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Given a list of employees, compute salary statistics IN ONE PASS
    // using summaryStatistics(). Return a record SalaryStats.
    //
    // record SalaryStats(double min, double max, double sum, long count)
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000, Diana/70000]
    // Output: SalaryStats(min=60000.0, max=95000.0, sum=310000.0, count=4)
    //
    // Key operation: mapToDouble(salary).summaryStatistics()
    // ─────────────────────────────────────────────────────────────
    record SalaryStats(double min, double max, double sum, long count) {}

    public static SalaryStats challenge1(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO — mapToDouble(salary).summaryStatistics()
        //        return new SalaryStats(stats.getMin(), stats.getMax(), stats.getSum(), stats.getCount())
        return new SalaryStats(0, 0, 0, 0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Given a list of integers, return the TOP N largest numbers
    // in DESCENDING order. If N > list size → return all sorted DESC.
    //
    // Input:  numbers=[3,1,5,12,2,11,7,4,9,8], n=4
    // Output: [12,11,9,8]
    //
    // Input:  numbers=[3,1,5], n=10
    // Output: [5,3,1]  ← n > size → return all sorted DESC
    //
    // Key operations: sorted(reversed) + limit(n) + toList()
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge2(List<Integer> numbers, int n) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        if (n <= 0)          throw new IllegalArgumentException("n must be positive");
        // TODO — sorted(Comparator.reverseOrder()) + limit(n) + toList()
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Given a list of distinct words, build a Map<String, Integer>
    // where key = word and value = word length.
    // Use Collectors.toMap().
    //
    // Input:  ["apple","banana","cherry","fig"]
    // Output: {"apple"=5, "banana"=6, "cherry"=6, "fig"=3}
    //
    // Key operation: collect(Collectors.toMap(identity, String::length))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge3(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — collect(Collectors.toMap(Function.identity(), String::length))
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Given a list of Optional<String>, extract ALL present (non-empty)
    // values into a flat list, preserving order.
    // Use flatMap(Optional::stream) — the cleanest Java 9+ approach!
    //
    // Input:  [Optional["apple"], Optional.empty(), Optional["banana"],
    //          Optional.empty(), Optional["cherry"]]
    // Output: ["apple","banana","cherry"]
    //
    // Key operation: flatMap(Optional::stream) — converts each Optional
    //                to a Stream of 0 or 1 elements!
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(List<Optional<String>> optionals) {
        if (optionals == null) throw new IllegalArgumentException("Optionals cannot be null");
        // TODO — stream().flatMap(Optional::stream).toList()
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Given a list of employees and a salary threshold, partition them
    // into two groups and return ONLY NAMES (not full Employee objects):
    // → true  = names of employees earning ABOVE threshold (salary > threshold)
    // → false = names of employees earning AT OR BELOW threshold
    // Names within each group sorted ALPHABETICALLY.
    //
    // Input:  employees=[Alice/95000,Bob/60000,Carol/85000,Diana/70000],
    //         threshold=75000
    // above (>75000): Alice,Carol → sorted: Alice,Carol
    // at or below:    Bob,Diana   → sorted: Bob,Diana
    // Output: {true=["Alice","Carol"], false=["Bob","Diana"]}
    //
    // Key operations: partitioningBy(salary > threshold,
    //                   mapping(name, toList()))
    //                 + sort each list!
    // ─────────────────────────────────────────────────────────────
    public static Map<Boolean, List<String>> challenge5(List<Employee> employees, double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO — Collectors.partitioningBy(e -> e.salary() > threshold,
        //                  Collectors.mapping(Employee::name, Collectors.toList()))
        //        then sort each list!
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Given a list of strings, use IntStream.range() to pair each
    // element with its 0-based INDEX and return a formatted list.
    // Format: "index:value"
    //
    // Input:  ["apple","banana","cherry"]
    // Output: ["0:apple","1:banana","2:cherry"]
    //
    // Input:  ["java","streams","rocks"]
    // Output: ["0:java","1:streams","2:rocks"]
    //
    // Key operation: IntStream.range(0, words.size())
    //                .mapToObj(i -> i + ":" + words.get(i))
    //                .toList()
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge6(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — IntStream.range(0, words.size()).mapToObj(i -> i + ":" + words.get(i)).toList()
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 7–9)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Merge TWO lists using Stream.concat(), remove duplicates,
    // sort alphabetically and return as unmodifiable list.
    //
    // Input:  list1=["banana","apple","cherry","date"]
    //         list2=["cherry","elderberry","apple","fig"]
    //
    // concat: ["banana","apple","cherry","date","cherry","elderberry","apple","fig"]
    // distinct: ["banana","apple","cherry","date","elderberry","fig"]
    // sorted:   ["apple","banana","cherry","date","elderberry","fig"]
    // Output:   ["apple","banana","cherry","date","elderberry","fig"]  ← unmodifiable!
    //
    // Key operations: Stream.concat(s1, s2)
    //                 .distinct().sorted()
    //                 .collect(collectingAndThen(toList(), unmodifiableList))
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge7(List<String> list1, List<String> list2) {
        if (list1 == null || list2 == null) throw new IllegalArgumentException("Lists cannot be null");
        // TODO — Stream.concat(list1.stream(), list2.stream())
        //        .distinct().sorted()
        //        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList))
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Given a list of words, build a Map<Integer, Set<String>>
    // where key = word length, value = SET of words with that length.
    // Use nested collectors: groupingBy + mapping + toSet.
    //
    // Input:  ["apple","fig","grape","bee","mango","ant","cherry"]
    //   len=3: {fig,bee,ant}
    //   len=5: {apple,grape,mango}
    //   len=6: {cherry}
    //
    // Output: {3={"fig","bee","ant"}, 5={"apple","grape","mango"}, 6={"cherry"}}
    //
    // Key operations: groupingBy(String::length,
    //                   mapping(Function.identity(), toSet()))
    // ─────────────────────────────────────────────────────────────
    public static Map<Integer, Set<String>> challenge8(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — groupingBy(String::length, mapping(identity, toSet()))
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Given a list of sentences, find the TOP 3 most frequent words
    // across ALL sentences. Return in FREQUENCY DESC order.
    // For same frequency → alphabetically ASC. Words are case-sensitive.
    //
    // Input:  ["the cat sat on the mat",
    //          "the cat in the hat",
    //          "the cat sat"]
    //
    // All words: [the,cat,sat,on,the,mat,the,cat,in,the,hat,the,cat,sat]
    // Frequencies: the=5,cat=3,sat=2,on=1,mat=1,in=1,hat=1
    //
    // Top 3: ["the","cat","sat"]
    //
    // Key operations: flatMap(split) → groupingBy(identity,counting())
    //                 → entrySet().stream() sorted by freq DESC + alpha ASC
    //                 → limit(3) → map(key) → toList()
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge9(List<String> sentences, int topN) {
        if (sentences == null) throw new IllegalArgumentException("Sentences cannot be null");
        if (topN <= 0)         throw new IllegalArgumentException("topN must be positive");
        // TODO — flatMap sentence → words, groupingBy counting,
        //        sort entrySet by freq DESC then alpha ASC, limit(topN), map keys
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Generate the COLLATZ SEQUENCE starting from n using Stream.iterate()
    // with the THREE-ARGUMENT version (with a predicate!).
    // Return the full sequence INCLUDING n and ending WITH 1.
    //
    // Collatz rules:
    //   if n is EVEN → next = n / 2
    //   if n is ODD  → next = 3 * n + 1
    //   stop when n reaches 1
    //
    // Input:  n=6
    // 6 → 3 → 10 → 5 → 16 → 8 → 4 → 2 → 1
    // Output: [6,3,10,5,16,8,4,2,1]
    //
    // Input:  n=1
    // Output: [1]
    //
    // Input:  n=12
    // 12→6→3→10→5→16→8→4→2→1
    // Output: [12,6,3,10,5,16,8,4,2,1]
    //
    // Hint:
    // Step 1 — Use THREE-ARGUMENT Stream.iterate():
    //   Stream.iterate(seed, hasNext, next)
    //   → seed    = n (starting value)
    //   → hasNext = predicate to CONTINUE (not stop condition!)
    //   → next    = function to compute next value
    //
    // Step 2 — hasNext should continue WHILE current value != 1
    //   BUT this would EXCLUDE the 1 at the end!
    //   → Solution: iterate WHILE value > 1, then add 1 manually at the end!
    //   OR
    //   → Use TWO-ARGUMENT iterate + takeWhile for the non-1 part!
    //
    // Step 3 — next function (Collatz step):
    //   n -> n % 2 == 0 ? n / 2 : 3 * n + 1
    //
    // Step 4 — Combine sequence + terminal 1:
    //   Stream.concat(
    //     Stream.iterate(n, val -> val > 1, val -> val%2==0 ? val/2 : 3*val+1),
    //     Stream.of(1L)   ← always append 1 at the end!
    //   ).toList()
    //   ⚠️ If n==1 → sequence is just [1], handle separately!
    // ─────────────────────────────────────────────────────────────
    public static List<Long> challenge10(long n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive");
        // TODO — see hints above
        return new ArrayList<>();
    }
}