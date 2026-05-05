package dev.perfectbogus.maps;

import java.util.*;

public class MapUsage {

    static Map<Integer, Long> memo = new HashMap<>();

    /**
     * Map (interface)
     *  Hashmap         -> Fast, no order
     *  LinkedHashMap   -> Insertion order
     *  TreeMap         -> Sorted By Key
     *  HashTable       -> Legacy, thread-safe (avoid)
     */

    public static void main(String[] args) {
        Map<String, Integer> scoresHM = new HashMap<>();

        System.out.println("------ HashMap -------");

        // Adding entries
        scoresHM.put("Alice", 96);
        scoresHM.put("Bob", 87);
        scoresHM.put("Charlie", 92);

        // Reading
        System.out.println("Alice: " + scoresHM.get("Alice"));
        System.out.println("Bob: " + scoresHM.get("Bob"));
        System.out.println("Dan (by default):" + scoresHM.getOrDefault("Dan", 0));

        // Checking
        System.out.println("Contains Key Bob?: " + scoresHM.containsKey("Bob"));
        System.out.println("Contains Value 96?: " + scoresHM.containsValue(96));
        System.out.println("Size: " + scoresHM.size());

        // Removing
        System.out.println("Removing Bob: " + scoresHM.remove("Bob"));

        // Update only if key exists
        System.out.println("Update Alice value: 100");
        scoresHM.replace("Alice", 100);

        System.out.println("------ LinkedHashMap -------");
        // LinkedHashMap - Preserves Insertion Order
        Map<String, Integer> scoresLHM = new LinkedHashMap<>();

        scoresLHM.put("Charlie", 92);
        scoresLHM.put("Alice", 95);
        scoresLHM.put("Bob", 86);

        // Iterate in insertion order
        for (Map.Entry<String, Integer> entry : scoresLHM.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("------ TreeMap -------");
        // TreeMap - Sorted By key
        Map<String, Integer> scoresTM = new TreeMap<>();
        scoresTM.put("Charlie", 92);
        scoresTM.put("Alice", 95);
        scoresTM.put("Bob", 86);

        // Iterate in alphabetical order: Alice, Bob, Charlie
        for (String key : scoresTM.keySet()) {
            System.out.println(key + ": " + scoresTM.get(key));
        }

        // Iterating a Map
        System.out.println("------ Iterating a Map -------");

        System.out.println("Option 1: entrySet() // most common");
        for (Map.Entry<String, Integer> entry : scoresHM.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

        System.out.println("Option 2: keySet() // only need keys");
        for (String key : scoresHM.keySet()) {
            System.out.println(key);
        }

        System.out.println("Option 3: values() // only need values");
        for (int value : scoresHM.values()) {
            System.out.println(value);
        }

        System.out.println("Option 4: foreach with lambda (cleanest)");
        scoresHM.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Useful Advanced Methods");

        System.out.println("putIfAbsent()");
        scoresHM.putIfAbsent("Larry", 90); // adds
        scoresHM.putIfAbsent("Larry", 80); // ignored - Alice already exists

        System.out.println("computeIfAbsent() compute value only if key is missing");
        scoresHM.computeIfAbsent("Bob", k -> k.length()); // length of the key

        System.out.println("merge() - great forr counting/accumulating");
        scoresHM.merge("Alice", 1, Integer::sum);

        System.out.println("compute() - update a value based on current value");
        scoresHM.compute("Alice", (k, v) -> v == null ? 1 : v + 1);

        System.out.println("------- Immutable Maps --------");
        Map<String, Integer> config = Map.of(
                "timeout", 30,
                "retries", 3,
                "port", 8080
        );

        Map<String, Integer> largerMap = Map.ofEntries(
                Map.entry("timeout", 30),
                Map.entry("retries", 3),
                Map.entry("port", 8080),
                Map.entry("maxConn", 100)
        );

        System.out.println("------ Common Patterns -------");
        System.out.println("Frequency Counter:");
        String sentence = "hello world";
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : sentence.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }

        System.out.println("Grouping (Map of Lists)");
        String[] words = {"apple", "banana", "avocado", "blueberry", "cherry"};
        Map<Character, List<String>> grouped = new HashMap<>();

        for (String word : words) {
            char key = word.charAt(0);
            grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        for (Map.Entry<Character, List<String>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            List<String> list = entry.getValue();
            for (String word : list) {
                System.out.println(word);
            }
        }

        System.out.println("Caching/Memorization:");
        fibonacci(5);


    }

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        long result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }
}



























