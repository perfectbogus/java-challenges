package dev.perfectbogus.streams;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class StreamChallenges {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Given a list of sentences, use flatMap to extract all words,
    // filter words with length STRICTLY GREATER than minLength,
    // then group by FIRST CHARACTER and count how many qualifying
    // words start with each character.
    // Return Map<Character, Long>.
    //
    // Input:  sentences=["apple avocado banana", "cherry apricot fig",
    //                    "blueberry grape date"],
    //         minLength=4
    //
    // All words: [apple,avocado,banana,cherry,apricot,fig,blueberry,grape,date]
    // Keep length > 4: [apple(5),avocado(7),banana(6),cherry(6),apricot(7),blueberry(9),grape(5)]
    //
    // Group by first char and count:
    //   'a' → apple,avocado,apricot → 3
    //   'b' → banana,blueberry      → 2
    //   'c' → cherry                → 1
    //   'g' → grape                 → 1
    //
    // Output: {'a'=3, 'b'=2, 'c'=1, 'g'=1}
    //
    // Key operations: flatMap(sentence → Arrays.stream(split))
    //                 filter(length > minLength)
    //                 groupingBy(firstChar, counting())
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, Long> challenge1(List<String> sentences, int minLength) {
        if (sentences == null) throw new IllegalArgumentException("Sentences cannot be null");
        if (minLength < 0)     throw new IllegalArgumentException("minLength must be non-negative");
        // TODO
        return sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(w -> w.length() > minLength)
                .collect(Collectors.groupingBy(
                        w -> w.charAt(0),
                        Collectors.counting()
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Given a list of daily stock prices, find the MAXIMUM PROFIT
    // achievable from exactly ONE buy and ONE sell (buy before sell).
    // Use stream().reduce() — NOT sorting, NOT loops!
    //
    // Profit = sellPrice - buyPrice  (must buy BEFORE selling!)
    // If no profit is possible → return 0
    //
    // Strategy: track [minPriceSoFar, maxProfitSoFar] as an int[]
    // Use reduce to update this pair for each price:
    //   new minPrice    = Math.min(state[0], price)
    //   new maxProfit   = Math.max(state[1], price - state[0])
    //
    // Input:  [7,1,5,3,6,4]
    //   Day1: price=7 → min=7, profit=0
    //   Day2: price=1 → min=1, profit=0
    //   Day3: price=5 → min=1, profit=4  (buy@1, sell@5)
    //   Day4: price=3 → min=1, profit=4
    //   Day5: price=6 → min=1, profit=5  (buy@1, sell@6)
    //   Day6: price=4 → min=1, profit=5
    // Output: 5
    //
    // Input:  [7,6,4,3,1]  → prices only decrease → return 0
    //
    // Key operations: stream().reduce(int[] identity, accumulator, combiner)
    //                 state = [minPriceSoFar, maxProfitSoFar]
    // ─────────────────────────────────────────────────────────────
    public static int challenge2(List<Integer> prices) {
        if (prices == null) throw new IllegalArgumentException("Prices cannot be null");
        // TODO
        int[] result = prices.stream().reduce(
                new int[]{Integer.MAX_VALUE, 0},
                (track, price) -> new int[]{
                        Math.min(track[0], price),
                        Math.max(track[1], price - track[0])
                },
                (actual , next) -> new int[] {
                        Math.min(actual[0], next[0]),
                        Math.max(actual[1], next[1])
                }
        );
        return result[1];
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Given a SORTED ASCENDING list of integers and a threshold,
    // use takeWhile() and dropWhile() to split the list:
    // → "below"  = all numbers STRICTLY LESS THAN threshold
    // → "above"  = all numbers STRICTLY GREATER THAN threshold
    // (numbers equal to threshold are excluded from both!)
    // Return record SplitResult(List<Integer> below, List<Integer> above)
    //
    // Input:  numbers=[1,2,3,5,5,6,7,8,9], threshold=5
    //   below  = takeWhile(n < 5)  → [1,2,3]
    //   middle = equal to 5        → skip!
    //   above  = dropWhile(n <= 5) → [6,7,8,9]
    //
    // Output: SplitResult([1,2,3], [6,7,8,9])
    //
    // Input:  numbers=[2,4,6,8,10], threshold=5
    //   below = [2,4]
    //   above = [6,8,10]
    //
    // Key operations:
    //   below: stream().takeWhile(n -> n < threshold).toList()
    //   above: stream().dropWhile(n -> n <= threshold).toList()
    // ─────────────────────────────────────────────────────────────
    record SplitResult(List<Integer> below, List<Integer> above) {}

    public static SplitResult challenge3(List<Integer> numbers, int threshold) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — use takeWhile() for below, dropWhile() for above
        List<Integer> below = numbers.stream().takeWhile(n -> n < threshold).toList();
        List<Integer> above = numbers.stream().dropWhile(n -> n <= threshold).toList();

        return new SplitResult(below, above);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Use Stream.iterate() to generate the FIBONACCI SEQUENCE
    // and return the first N Fibonacci numbers as List<Long>.
    //
    // Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
    // F(0)=0, F(1)=1, F(n) = F(n-1) + F(n-2)
    //
    // Input:  n=8
    // Output: [0, 1, 1, 2, 3, 5, 8, 13]
    //
    // Input:  n=1
    // Output: [0]
    //
    // Key operation: Stream.iterate() with a PAIR as state!
    // Use long[] to hold [previous, current]:
    //   seed      = new long[]{0L, 1L}    ← F(0)=0, F(1)=1
    //   next pair = new long[]{pair[1], pair[0]+pair[1]}
    //   extract   = pair[0]               ← first element of pair
    //   limit(n)  to take exactly N elements
    // ─────────────────────────────────────────────────────────────
    public static List<Long> challenge4(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive");
        // TODO — Stream.iterate(seed, pair -> next pair)
        //        .limit(n).map(pair -> pair[0]).toList()
        return Stream.iterate(
                    new long[]{0L, 1L},
                    (pair) -> new long[]{pair[1], pair[0] + pair[1]})
                .limit(n)
                .map(pair -> pair[0])
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Given a list of employees, build a NESTED MAP using stream
    // collectors, then find the dept+tier combination with the
    // HIGHEST AVERAGE SALARY.
    //
    // Step 1 — build: Map<String, Map<String, Double>>
    //   outer key = department
    //   inner key = tier: "SENIOR" (yearsOfExperience >= 5) or "JUNIOR" (< 5)
    //   value     = AVERAGE salary for that dept+tier combination
    //
    // Step 2 — find the dept+tier combo with highest avg salary
    //   Return formatted string: "DEPT-TIER=avg"  (avg rounded to 2 decimal places)
    //
    // Input:
    //   Alice/Engineering/95000/8  → Engineering,SENIOR
    //   Bob/Marketing/60000/2      → Marketing,JUNIOR
    //   Carol/Engineering/85000/3  → Engineering,JUNIOR
    //   Diana/Marketing/70000/7    → Marketing,SENIOR
    //   Eve/Engineering/90000/6    → Engineering,SENIOR
    //
    // Map:
    //   Engineering → {SENIOR=(95000+90000)/2=92500, JUNIOR=85000}
    //   Marketing   → {SENIOR=70000, JUNIOR=60000}
    //
    // Highest avg: Engineering-SENIOR=92500.0
    // Output: "Engineering-SENIOR=92500.00"
    //
    // Key operations:
    //   groupingBy(dept, groupingBy(tier, averagingDouble(salary)))
    //   then flatMap over entrySet to find max by value
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(List<Employee> employees) {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO
        // Step 1 — nested groupingBy
        // Step 2 — flatMap entries to find max avg
        //          format: String.format("%.2f", avg)
        Map<String, Map<String, Double>> map = employees.stream().collect(
                Collectors.groupingBy(
                        Employee::department,
                        Collectors.groupingBy(
                                e -> e.yearsOfExperience() >= 5 ? "SENIOR" : "JUNIOR",
                                Collectors.averagingDouble(Employee::salary)
                        )
                ));

        String result = map.entrySet().stream().flatMap(outer ->
                outer.getValue().entrySet().stream().map(inner ->
                        Map.entry(
                                outer.getKey() + "-" + inner.getKey(),
                                inner.getValue()
                        )
                ))
                .max(Map.Entry.comparingByValue())
                .map(entry ->
                        String.format(
                                "%s=%.2f",
                                entry.getKey(),
                                entry.getValue()
                ))
                .orElse("");

        return result;
    }
}