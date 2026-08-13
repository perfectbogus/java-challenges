package dev.perfectbogus.comparators;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionConversionChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — List<String> → Map<Character, List<String>>
    //
    // Given a list of words, convert to a map where:
    // KEY   = first letter of each word
    // VALUE = list of words starting with that letter, sorted alphabetically
    //
    // Input:  ["banana","apple","avocado","blueberry","cherry","apricot"]
    // Output: {
    //   'a' → ["apple", "apricot", "avocado"]
    //   'b' → ["banana", "blueberry"]
    //   'c' → ["cherry"]
    // }
    //
    // Hint: Collectors.groupingBy(first letter, sorted mapping)
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, List<String>> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return words.stream()
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted().toList()
                        )
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Map<String, List<Integer>> → Map<String, Integer>
    //
    // Given a map of department → list of salaries,
    // convert to a map of department → total salary budget.
    //
    // Input:  {
    //   "Engineering" → [95000, 85000, 92000]
    //   "Marketing"   → [60000, 70000]
    //   "HR"          → [55000, 58000]
    // }
    // Output: {
    //   "Engineering" → 272000
    //   "Marketing"   → 130000
    //   "HR"          → 113000
    // }
    //
    // Hint: entrySet().stream() → toMap(key, entry → sum of list)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge2(Map<String, List<Integer>> salaries) {
        if (salaries == null) throw new IllegalArgumentException("Salaries cannot be null");
        // TODO
        return salaries.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(Integer::intValue).sum()
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — List<Employee> → Map<String, Employee>
    //
    // Given a list of employees, convert to a map of name → Employee.
    // If two employees have the same name keep the one with HIGHER salary.
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:  [("Alice",Eng,95000), ("Bob",Mkt,60000),
    //          ("Alice",HR,85000),  ("Bob",Eng,75000)]
    // Output: {
    //   "Alice" → ("Alice", Eng, 95000)  ← higher salary wins
    //   "Bob"   → ("Bob",   Eng, 75000)  ← higher salary wins
    // }
    //
    // Hint: toMap(name, identity, mergeFunction keeping higher salary)
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, String department, double salary) {}

    public static Map<String, Employee> challenge3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Function.identity(),
                        (a, b) -> a.salary() >= b.salary() ? a : b
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Map<String, Integer> → List<String>
    //
    // Given a map of word → count, expand it into a list where
    // each word appears COUNT times, sorted alphabetically within each group.
    // Final list sorted by word alphabetically.
    //
    // Input:  {"apple"=3, "banana"=1, "cherry"=2}
    // Output: ["apple","apple","apple","banana","cherry","cherry"]
    //
    // Hint: entrySet().stream()
    //       flatMap(e -> Collections.nCopies(e.getValue(), e.getKey()).stream())
    //       sorted()
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(Map<String, Integer> wordCount) {
        if (wordCount == null) throw new IllegalArgumentException("WordCount cannot be null");
        // TODO
        return wordCount.entrySet().stream()
                .flatMap(e -> Collections.nCopies(e.getValue(), e.getKey()).stream())
                .sorted()
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — List<String> → LinkedHashMap<String, Long>
    //
    // Given a list of words (may contain duplicates),
    // convert to a LinkedHashMap of word → frequency.
    // Ordered by FIRST OCCURRENCE in the original list.
    //
    // Input:  ["apple","banana","apple","cherry","banana","apple"]
    // Output: LinkedHashMap{
    //   "apple"  → 3   ← first seen at index 0
    //   "banana" → 2   ← first seen at index 1
    //   "cherry" → 1   ← first seen at index 4
    // }
    //
    // ⚠️ LinkedHashMap preserves INSERTION order
    //    Regular HashMap does NOT guarantee order!
    //
    // Hint: iterate and merge into LinkedHashMap
    //       OR stream with groupingBy into LinkedHashMap
    // ─────────────────────────────────────────────────────────────
    public static LinkedHashMap<String, Long> challenge5(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                LinkedHashMap::new,
                Collectors.counting()
        ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Map<String, Map<String, Integer>> → Map<String, Integer>
    //
    // Given a nested map of continent → (country → population),
    // flatten it into a single map of country → population.
    //
    // Input:  {
    //   "Europe"   → {"France"=67M, "Germany"=83M}
    //   "Asia"     → {"Japan"=125M, "China"=1400M}
    //   "Americas" → {"Brazil"=215M}
    // }
    // Output: {
    //   "France"=67M, "Germany"=83M,
    //   "Japan"=125M, "China"=1400M,
    //   "Brazil"=215M
    // }
    //
    // Hint: values().stream()
    //       flatMap(innerMap -> innerMap.entrySet().stream())
    //       collect(toMap(key, value))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge6(Map<String, Map<String, Integer>> nested) {
        if (nested == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Map<String, Integer> → Map<Integer, List<String>>
    //
    // Given a map of word → frequency, INVERT it to
    // frequency → list of words with that frequency.
    // Words within each group sorted alphabetically.
    //
    // Input:  {"apple"=3, "banana"=1, "cherry"=3, "date"=2, "elderberry"=1}
    // Output: {
    //   3 → ["apple", "cherry"]      ← sorted alpha
    //   2 → ["date"]
    //   1 → ["banana", "elderberry"] ← sorted alpha
    // }
    //
    // Hint: entrySet().stream()
    //       groupingBy(value, mapping(key, sorted toList))
    // ─────────────────────────────────────────────────────────────
    public static Map<Integer, List<String>> challenge7(Map<String, Integer> wordFreq) {
        if (wordFreq == null) throw new IllegalArgumentException("WordFreq cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — List<String> → TreeMap<Integer, List<String>>
    //
    // Given a list of words, convert to a TreeMap where:
    // KEY   = word length (TreeMap sorts keys ascending automatically!)
    // VALUE = list of words with that length, sorted alphabetically
    //
    // Input:  ["fig","banana","kiwi","apple","plum","date","cherry"]
    // Output: TreeMap{
    //   3 → ["fig"]
    //   4 → ["date","kiwi","plum"]
    //   5 → ["apple"]
    //   6 → ["banana","cherry"]
    // }
    //
    // ⚠️ Must return TreeMap not HashMap — keys must be sorted!
    //
    // Hint: groupingBy(length) into TreeMap using
    //       Collectors.groupingBy(classifier, TreeMap::new, downstream)
    // ─────────────────────────────────────────────────────────────
    public static TreeMap<Integer, List<String>> challenge8(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new TreeMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Two Map<String,Integer> → Merged Map<String,Integer>
    //
    // Given two maps, merge them into one map where:
    // - Keys only in map1 → keep their value
    // - Keys only in map2 → keep their value
    // - Keys in BOTH maps → SUM their values
    //
    // Input:  map1 = {apple=3, banana=2, cherry=5}
    //         map2 = {banana=4, cherry=1, date=7}
    // Output: {apple=3, banana=6, cherry=6, date=7}
    //          ↑ only map1  ↑ 2+4   ↑ 5+1   ↑ only map2
    //
    // Hint: start with new HashMap<>(map1)
    //       then map2.forEach((k,v) -> result.merge(k, v, Integer::sum))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge9(Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map1 == null) throw new IllegalArgumentException("Map1 cannot be null");
        if (map2 == null) throw new IllegalArgumentException("Map2 cannot be null");
        // TODO
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — List<String> → Map<String, Map<Character, Long>>
    //
    // Given a list of words, convert to a nested map where:
    // OUTER KEY = word
    // INNER MAP = character → frequency within that word
    //
    // Input:  ["hello", "world"]
    // Output: {
    //   "hello" → {'h'=1, 'e'=1, 'l'=2, 'o'=1}
    //   "world" → {'w'=1, 'o'=1, 'r'=1, 'l'=1, 'd'=1}
    // }
    //
    // Hint: toMap(word, word →
    //         word.chars().mapToObj(c -> (char)c)
    //             .collect(groupingBy(identity(), counting())))
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Map<Character, Long>> challenge10(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new HashMap<>();
    }
}