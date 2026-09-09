package dev.perfectbogus.maps;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.*;
import java.util.function.*;

public class MapChallenges2 {

    record Employee(String name, String department, double salary, int yearsOfExperience) {}

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Top N most frequent words
    //
    // Given a list of sentences, count word frequency across ALL
    // sentences. Return the TOP N words by frequency DESCENDING.
    // For ties in frequency → sort ALPHABETICALLY ASCENDING.
    //
    // Use merge() for counting, then sort the entrySet.
    //
    // Input:  sentences=["the cat sat on the mat",
    //                    "the cat in the hat",
    //                    "the cat sat"], n=3
    // Frequencies: the=5, cat=3, sat=2, on=1, mat=1, in=1, hat=1
    // Top 3: ["the","cat","sat"]
    //
    // Throw IllegalArgumentException if sentences is null or n <= 0.
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge1(List<String> sentences, int n) {
        if (sentences == null) throw new IllegalArgumentException("Sentences cannot be null");
        if (n <= 0)            throw new IllegalArgumentException("n must be positive");
        Map<String, Integer> freq = new HashMap<>();
        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");
            for (String word : words) {
                freq.merge(word, 1, Integer::sum);
            }
        }

        Comparator<Map.Entry<String, Integer>> byValueDesc = Map.Entry.<String, Integer>comparingByValue().reversed();
        Comparator<Map.Entry<String, Integer>> byKey = Map.Entry.comparingByKey();

        return freq.entrySet().stream()
                .sorted(byValueDesc.thenComparing(byKey))
                .map(Map.Entry::getKey)
                .limit(n)
                .toList();

    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Invert map to multimap
    //
    // Invert a Map<K, V> to Map<V, List<K>>.
    // Multiple original keys may share the same value.
    // Lists of keys sorted ALPHABETICALLY.
    //
    // Input:  {"Alice"="Eng","Bob"="Mkt","Carol"="Eng","Diana"="Mkt"}
    // Output: {"Eng"=["Alice","Carol"], "Mkt"=["Bob","Diana"]}
    //
    // Use computeIfAbsent() to build the inverted map.
    // Throw IllegalArgumentException if map is null.
    // ─────────────────────────────────────────────────────────────
    public static <K extends Comparable<K>, V> Map<V, List<K>> challenge2(Map<K, V> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");

        Map<V, List<K>> result = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.computeIfAbsent(entry.getValue(), v -> new ArrayList<>())
                    .add(entry.getKey());
        }

        result.values().forEach(Collections::sort);
        return result;
    }

    public static <K extends Comparable<K>, V> Map<V, List<K>> challenge2_2(Map<K, V> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");

        return map.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Map.Entry::getKey, Collectors.toList()),
                                list -> { Collections.sort(list); return list; }
                        )
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Anagram groups
    //
    // Group words that are ANAGRAMS of each other.
    // Use sorted characters as the grouping key.
    // Within each group, words sorted ALPHABETICALLY.
    // Only include groups with 2 or more words!
    //
    // Input:  ["eat","tea","tan","ate","nat","bat"]
    // Output: {"aet"=["ate","eat","tea"], "ant"=["nat","tan"]}
    //         "abt"=["bat"] excluded (only 1 word)
    //
    // Use computeIfAbsent() to group words.
    // Throw IllegalArgumentException if words is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, List<String>> challenge3(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            map.computeIfAbsent(sortLetters(word), w -> new ArrayList<>()).add(word);
        }

        Map<String, List<String>> result = new HashMap<>();

        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            if (e.getValue().size() > 1) {
                result.put(e.getKey(), e.getValue());
            }
        }

        result.values().forEach(Collections::sort);

        return result;
    }

    public static Map<String, List<String>> challenge3_2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");

        return words.stream()
                .collect(Collectors.groupingBy(
                        word -> sortLetters(word)
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .peek(e -> Collections.sort(e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private static String sortLetters(String word) {
        if (word == null) throw new IllegalArgumentException("Word cannot be null");
        char[] letter = word.toCharArray();
        Arrays.sort(letter);
        return new String(letter);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Map difference report
    //
    // Compare two maps and return a DiffReport:
    // → onlyInLeft:  keys in map1 but NOT in map2
    // → onlyInRight: keys in map2 but NOT in map1
    // → different:   keys in BOTH but with DIFFERENT values
    //
    // record DiffReport<K>(Set<K> onlyInLeft,
    //                      Set<K> onlyInRight, Set<K> different)
    //
    // Input:  map1={"a"=1,"b"=2,"c"=3,"d"=4}
    //         map2={"b"=2,"c"=99,"e"=5}
    // onlyInLeft:  {"a","d"}
    // onlyInRight: {"e"}
    // different:   {"c"}  (3≠99; b=2=2 same!)
    //
    // Throw IllegalArgumentException if either map is null.
    // ─────────────────────────────────────────────────────────────
    record DiffReport<K>(Set<K> onlyInLeft, Set<K> onlyInRight, Set<K> different) {}

    public static <K, V> DiffReport<K> challenge4(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null || map2 == null) throw new IllegalArgumentException("Maps cannot be null");

        Set<K> different = new HashSet<>();
        Set<K> onlyInLeft = onlyOn(map1, map2, different);
        Set<K> onlyInRight = onlyOn(map2, map1, different);

        return new DiffReport<>(onlyInLeft, onlyInRight, different);
    }

    private static <K, V> Set<K> onlyOn(Map<K, V> map1, Map<K,V> map2, Set<K> diff) {
        Set<K> only = new HashSet<>();
        for (Map.Entry<K, V> e : map1.entrySet()) {
            if (!map2.containsKey(e.getKey())) {
                only.add(e.getKey());
            } else if (!e.getValue().equals(map2.get(e.getKey()))) {
                diff.add(e.getKey());
            }
        }

        return only;
    }

    public static <K, V> DiffReport<K> challenge4_2(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null || map2 == null) throw new IllegalArgumentException("Maps cannot be null");

        Set<K> onlyInLeft = new HashSet<>(map1.keySet());
        onlyInLeft.removeAll(map2.keySet());

        Set<K> onlyInRight = new HashSet<>(map2.keySet());
        onlyInRight.removeAll(map1.keySet());

        Set<K> different = map1.keySet().stream()
                .filter(map2::containsKey)
                .filter(k -> !map1.get(k).equals(map2.get(k)))
                .collect(Collectors.toSet());

        return new DiffReport<>(onlyInLeft, onlyInRight, different);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Two-level nested map
    //
    // Given employees, build:
    // Map<String dept, Map<String tier, Double totalSalary>>
    //   tier = "SENIOR" if yearsOfExperience >= 5, else "JUNIOR"
    //
    // Use merge() to accumulate salaries.
    //
    // Input:
    //   Alice/Eng/90000/8 → Eng,SENIOR
    //   Bob/Eng/70000/3   → Eng,JUNIOR
    //   Carol/Mkt/80000/6 → Mkt,SENIOR
    //   Diana/Mkt/60000/2 → Mkt,JUNIOR
    //   Eve/Eng/85000/7   → Eng,SENIOR
    //
    // Output:
    //   Eng → {SENIOR=175000.0, JUNIOR=70000.0}
    //   Mkt → {SENIOR=80000.0,  JUNIOR=60000.0}
    //
    // Throw IllegalArgumentException if employees is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Map<String, Double>> challenge5(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.groupingBy(
                        e -> e.yearsOfExperience() >= 5 ? "SENIOR" : "JUNIOR",
                        Collectors.summingDouble(Employee::salary)
                )
        ));
    }

    public static Map<String, Map<String, Double>> challenge5_2(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        Map<String, List<Employee>> map = new HashMap<>();
        for (Employee e : employees) {
            map.computeIfAbsent(e.department, w -> new ArrayList<>()).add(e);
        }

        Map<String, Map<String, Double>> result = new HashMap<>();
        for (Map.Entry<String, List<Employee>> e : map.entrySet()) {
            Map<String, Double> salariesBySeniorityMap = new HashMap<>();
            List<Employee> seniors = e.getValue().stream().filter(emp -> emp.yearsOfExperience() >= 5).toList();
            Double totalSalariesSeniors = seniors.stream().mapToDouble(Employee::salary).sum();
            List<Employee> juniors = e.getValue().stream().filter(emp -> emp.yearsOfExperience() < 5).toList();
            Double totalSalariesJuniors = juniors.stream().mapToDouble(Employee::salary).sum();
            salariesBySeniorityMap.put("SENIOR", totalSalariesSeniors);
            salariesBySeniorityMap.put("JUNIOR", totalSalariesJuniors);
            result.put(e.getKey(), salariesBySeniorityMap);
        }

        return result;
    }

    public static Map<String, Map<String, Double>> challenge5_3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        Map<String, List<Employee>> map = new HashMap<>();
        for (Employee e : employees) {
            map.computeIfAbsent(e.department(), w -> new ArrayList<>()).add(e);
        }

        Map<String, Map<String, Double>> result = new HashMap<>();
        for (Map.Entry<String, List<Employee>> entry : map.entrySet()) {
            Map<String, Double> collect = entry.getValue().stream().collect(Collectors.groupingBy(
                    e -> e.yearsOfExperience() >= 5 ? "SENIOR" : "JUNIOR",
                    Collectors.collectingAndThen(
                            Collectors.toList(),
                            list -> list.stream().mapToDouble(Employee::salary).sum()
                    )
            ));

            result.put(entry.getKey(), collect);
        }

        return result;
    }

    public static Map<String, Map<String, Double>> challenge5_4(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        Map<String, Map<String, Double>> result = new HashMap<>();

        for (Employee e : employees) {
            String tier = e.yearsOfExperience() >= 5 ? "SENIOR" : "JUNIOR";

            result.computeIfAbsent(e.department(), k -> new HashMap<>()).merge(tier, e.salary(), Double::sum);
        }

        return result;
    }
    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Element positions index
    //
    // Given a list, build a Map<T, List<Integer>> where:
    // → key   = element value
    // → value = list of ALL indices where element appears (ascending)
    //
    // Input:  ["a","b","a","c","b","a"]
    // Output: {"a"=[0,2,5], "b"=[1,4], "c"=[3]}
    //
    // Input:  [1,2,3,2,1]
    // Output: {1=[0,4], 2=[1,3], 3=[2]}
    //
    // Use computeIfAbsent() to build index lists.
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> Map<T, List<Integer>> challenge6(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        Map<T, List<Integer>> map = new HashMap<>();
        int idx = 0;
        for (T value : list) {
            map.computeIfAbsent(value, w -> new ArrayList<>()).add(idx++);
        }
        return map;
    }

    public static <T> Map<T, List<Integer>> challenge6_2(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");

        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> list.get(i),
                        Collectors.toList()
                ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Running cumulative sum (LinkedHashMap!)
    //
    // Given a LinkedHashMap<String, Double> of label→value,
    // return a NEW LinkedHashMap where each value is replaced
    // by the CUMULATIVE SUM up to and including that entry.
    // Preserve INSERTION ORDER.
    //
    // Input:  {"Jan"=100.0,"Feb"=150.0,"Mar"=200.0,"Apr"=50.0}
    // Output: {"Jan"=100.0,"Feb"=250.0,"Mar"=450.0,"Apr"=500.0}
    //
    // Throw IllegalArgumentException if map is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge7(LinkedHashMap<String, Double> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        LinkedHashMap<String, Double> replace = new LinkedHashMap<>();
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            sum += entry.getValue();
            replace.put(entry.getKey(), sum);
        }
        return replace;
    }

    public static Map<String, Double> challenge7_2(LinkedHashMap<String, Double> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        AtomicReference<Double> running = new AtomicReference<>(0.0);

        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> running.updateAndGet(sum -> sum + e.getValue()),
                (existing, incoming) -> existing,
                LinkedHashMap::new
        ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Frequency of frequencies
    //
    // Given a list, count element frequencies, then group elements
    // by their frequency count.
    //
    // Return Map<Integer, List<T>>:
    //   key   = frequency count
    //   value = list of elements with that frequency (sorted naturally)
    //
    // Input:  ["apple","banana","apple","cherry","banana","apple"]
    //   apple=3, banana=2, cherry=1
    // Output: {3=["apple"], 2=["banana"], 1=["cherry"]}
    //
    // Input:  [1,2,3,2,1,3,1]
    //   1=3, 2=2, 3=2
    // Output: {3=[1], 2=[2,3]}
    //
    // Step 1: build frequency map using merge()
    // Step 2: group elements by frequency using computeIfAbsent()
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T extends Comparable<T>> Map<Integer, List<T>> challenge8(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        Map<T, Long> freq = list.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));

        return freq.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getValue().intValue(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                entries -> entries.stream()
                                        .map(Map.Entry::getKey)
                                        .sorted()
                                        .toList()
                        )
                ));
    }

    public static <T extends Comparable<T>> Map<Integer, List<T>> challenge8_2(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");

        Map<T, Integer> freq = new HashMap<>();
        for (T value : list) {
            freq.merge(value, 1, Integer::sum);
        }

        Map<Integer, List<T>> result = new HashMap<>();
        for (Map.Entry<T, Integer> entry : freq.entrySet()) {
            result.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
        }

        result.values().forEach(Collections::sort);

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Merge maps with custom policy
    //
    // Merge two maps using a mergeFunction to resolve duplicate keys.
    // → key only in map1 → keep map1 value
    // → key only in map2 → keep map2 value
    // → key in BOTH → apply mergeFunction(map1Value, map2Value)
    //
    // Use Map.merge() to implement cleanly.
    //
    // Input:  map1={"a"=10,"b"=20,"c"=30}
    //         map2={"b"=5,"c"=15,"d"=25}
    //         mergeFunction=Integer::sum
    // Output: {"a"=10,"b"=25,"c"=45,"d"=25}
    //
    // Throw IllegalArgumentException if any argument is null.
    // ─────────────────────────────────────────────────────────────
    public static <K, V> Map<K, V> challenge9(Map<K, V> map1, Map<K, V> map2, BiFunction<V, V, V> mergeFunction) {
        if (map1 == null || map2 == null) throw new IllegalArgumentException("Maps cannot be null");
        if (mergeFunction == null) throw new IllegalArgumentException("MergeFunction cannot be null");

        Map<K, V> result = new HashMap<>(map1);
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            result.merge(entry.getKey(), entry.getValue(), mergeFunction);
        }

        return result;
    }

    public static <K, V> Map<K, V> challenge9_2(Map<K, V> map1, Map<K, V> map2, BiFunction<V, V, V> mergeFunction) {
        if (map1 == null || map2 == null) throw new IllegalArgumentException("Maps cannot be null");
        if (mergeFunction == null) throw new IllegalArgumentException("MergeFunction cannot be null");
        Map<K, V> result = new HashMap<>(map1);
        map2.forEach((k, v) -> result.merge(k, v, mergeFunction));
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Word co-occurrence map
    //
    // For each word, find all OTHER words that appear in the SAME
    // sentence. Return Map<String, Set<String>>.
    //
    // Input:  ["hello world","hello java","java rocks"]
    // hello → {world, java}
    // world → {hello}
    // java  → {hello, rocks}
    // rocks → {java}
    //
    // Rules:
    // → a word does NOT co-occur with itself!
    // → use Set to avoid duplicates!
    // → use computeIfAbsent() to build sets
    //
    // Throw IllegalArgumentException if sentences is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Set<String>> challenge10(List<String> sentences) {
        if (sentences == null) throw new IllegalArgumentException("Sentences cannot be null");
        Map<String, Set<String>> result = new HashMap<>();
        for (String sentence : sentences) {
            if (sentence == null || sentence.isBlank()) continue;

            List<String> words = Arrays.stream(sentence.trim().split("\\s+"))
                    .distinct()
                    .toList();

            for (int i = 0; i < words.size(); i++) {
                String wordA = words.get(i);
                for (int j = 0; j < words.size(); j++) {
                    if (i != j) {
                        String wordB = words.get(j);
                        result.computeIfAbsent(wordA, k -> new HashSet<>()).add(wordB);
                    }
                }
            }
        }
        return result;
    }
}