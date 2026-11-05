package dev.perfectbogus.maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapsPracticeChallenge {

    // ==========================================================
    // HASHMAP
    // ==========================================================

    // CHALLENGE 1 (HashMap)
    // Returns a map with each word and the number of times it appears in the list. The case matters ("a" and "A" are
    // different words). Only the words that appear are in the map. An empty list gives an empty map.
    // Example: wordCounts([a, b, a])  returns {a=2, b=1}
    //          wordCounts([x])        returns {x=1}
    //          wordCounts([a, A])     returns {a=1, A=1}
    //          wordCounts([])         returns {}
    public static HashMap<String, Integer> wordCounts(List<String> words) {
        return words.stream().collect(Collectors.toMap(
                s -> s,
                s -> 1,
                Integer::sum,
                HashMap::new
        ));
    }

    public static HashMap<String, Integer> wordCountsSimplified(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                HashMap::new,
                Collectors.summingInt(w -> 1)
        ));
    }

    // CHALLENGE 2 (HashMap)
    // Returns a new map where every value of the original map is a key, and its key is the value. All the values of
    // the original map are different. The original map is not modified. An empty map gives an empty map.
    // Example: invert({a=1, b=2})  returns {1=a, 2=b}
    //          invert({x=10})      returns {10=x}
    //          invert({})          returns {}
    public static HashMap<Integer, String> invert(Map<String, Integer> original) {
        return original.entrySet().stream().collect(Collectors.toMap(
                e -> e.getValue(),
                e -> e.getKey(),
                (existing, incoming) -> existing,
                HashMap::new
        ));
    }

    public static HashMap<Integer, String> invertSimplified(Map<String, Integer> original) {
        return original.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getValue,
                Map.Entry::getKey,
                (existing, incoming) -> existing,
                HashMap::new
        ));
    }

    // CHALLENGE 3 (HashMap)
    // Returns a new map with the keys of both maps. If a key is in both maps, its value is the sum of the two values.
    // If a key is in only one map, it keeps its value. Neither of the maps received is modified.
    // Example: mergeBalances({a=1, b=2}, {b=10, c=5})  returns {a=1, b=12, c=5}
    //          mergeBalances({a=1}, {})                returns {a=1}
    //          mergeBalances({}, {})                   returns {}
    public static HashMap<String, Integer> mergeBalances(Map<String, Integer> first, Map<String, Integer> second) {
        HashMap<String, Integer> merge = new HashMap<>(first);
        second.forEach((k, v) -> merge.merge(k, v, Integer::sum));
        return merge;
    }

    // CHALLENGE 4 (HashMap)
    // Exactly two numbers of the array, at different positions, add up to the target. Returns their positions as an
    // array of two elements, the smaller position first. The numbers can be negative or repeated. The same position
    // cannot be used twice.
    // Example: twoSum([2, 7, 11, 15], 9)  returns [0, 1]
    //          twoSum([3, 2, 4], 6)       returns [1, 2]
    //          twoSum([3, 3], 6)          returns [0, 1]
    //          twoSum([-1, -2, 5], 4)     returns [0, 2]
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int j = 0; j < numbers.length; j++) {
            Integer i = seen.get(target - numbers[j]);
            if (i != null) {
                return new int[]{i, j};
            }
            seen.put(numbers[j], j);
        }
        throw new UnsupportedOperationException();
    }

    // CHALLENGE 5 (HashMap)
    // Returns, for each key of the list and in the same order, its value in the map. If a key is not in the map, its
    // value is 0. A key can appear several times in the list, and then its value is repeated. The map is not modified.
    // Example: valuesFor({a=5, b=7}, [b, x, a, b])  returns [7, 0, 5, 7]
    //          valuesFor({a=5}, [])                 returns []
    //          valuesFor({}, [a])                   returns [0]
    public static List<Integer> valuesForExtended(Map<String, Integer> map, List<String> keys) {
        List<Integer> res = new ArrayList<>();
        for (String k : keys) {
            if (!map.containsKey(k)) {
                res.add(0);
            } else {
                res.add(map.get(k));
            }
        }
        return res;
    }

    public static List<Integer> valuesFor(Map<String, Integer> map, List<String> keys) {
        List<Integer> res = new ArrayList<>();
        for (String k : keys) {
            res.add(map.getOrDefault(k, 0));
        }
        return res;
    }

    public static List<Integer> valuesForSimplified(Map<String, Integer> map, List<String> keys) {
        return keys.stream().map(k -> map.getOrDefault(k, 0)).toList();
    }

    // ==========================================================
    // LINKEDHASHMAP
    // ==========================================================

    // CHALLENGE 6 (LinkedHashMap)
    // Returns a map with each different word and the position (starting at 0) where it appears for the first time.
    // The entries are in the order in which the words appear for the first time in the list.
    // Example: firstIndexOfEachWord([b, a, b, c])  returns {b=0, a=1, c=3}
    //          firstIndexOfEachWord([x, x, x])     returns {x=0}
    //          firstIndexOfEachWord([])            returns {}
    public static LinkedHashMap<String, Integer> firstIndexOfEachWord(List<String> words) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < words.size(); i++) {
            map.putIfAbsent(words.get(i), i);
        }
        return map;
    }

    // CHALLENGE 7 (LinkedHashMap)
    // Returns a map with each different character of the text and the number of times it appears. The entries are in
    // the order in which the characters appear for the first time in the text. The case matters and spaces count
    // like any other character.
    // Example: countCharsInOrder("banana")  returns {b=1, a=3, n=2}
    //          countCharsInOrder("aAa")     returns {a=2, A=1}
    //          countCharsInOrder("")        returns {}
    public static LinkedHashMap<Character, Integer> countCharsInOrder(String text) {
        return text.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(
                Function.identity(),
                LinkedHashMap::new,
                Collectors.summingInt(c -> 1)
        ));
    }

    // CHALLENGE 8 (LinkedHashMap)
    // Returns a new map with the same entries, but ordered by value from the smallest to the greatest. If two entries
    // have the same value, the one with the smaller key (the normal comparison of texts) goes first. The map received
    // is not modified.
    // Example: sortByValue({a=3, b=1, c=2})  returns {b=1, c=2, a=3}
    //          sortByValue({z=1, y=1, x=1})  returns {x=1, y=1, z=1}
    //          sortByValue({})               returns {}
    public static LinkedHashMap<String, Integer> sortByValue(Map<String, Integer> map) {
        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> byKey = Map.Entry.comparingByKey();
        return map.entrySet().stream()
                .sorted(byValue.thenComparing(byKey))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, second) -> first,
                        LinkedHashMap::new
                ));
    }

    // CHALLENGE 9 (LinkedHashMap)
    // Returns a map with each different word and the number of times it appears. The entries are ordered by the last
    // time each word appears in the list: the word whose last appearance is the earliest goes first.
    // Example: orderByLastAppearance([a, b, a, c])  returns {b=1, a=2, c=1}
    //          orderByLastAppearance([a, b])        returns {a=1, b=1}
    //          orderByLastAppearance([b, a, b, a])  returns {b=2, a=2}
    //          orderByLastAppearance([])            returns {}
    public static LinkedHashMap<String, Integer> orderByLastAppearanceExtended(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                () -> new LinkedHashMap<>(16, 0.75f, true),
                Collectors.summingInt(w -> 1)
        ));
    }

    public static LinkedHashMap<String, Integer> orderByLastAppearance(List<String> words) {
        LinkedHashMap<String, Integer> res = new LinkedHashMap<>();
        for (String w : words) {
            Integer old = res.remove(w);
            res.put(w, old == null ? 1 : old + 1);
        }
        return res;
    }

    // CHALLENGE 10 (LinkedHashMap)
    // Returns a map that pairs the keys and the values by position: the first key with the first value, and so on. If
    // the lists have different sizes, only the positions that exist in both lists are used. If a key is repeated, its
    // value is the last one and its entry stays where the key appeared for the first time.
    // Example: zip([a, b, c], [1, 2, 3])  returns {a=1, b=2, c=3}
    //          zip([a, b, a], [1, 2, 3])  returns {a=3, b=2}
    //          zip([a, b, c], [1, 2])     returns {a=1, b=2}
    //          zip([], [1])               returns {}
    public static LinkedHashMap<String, Integer> zip(List<String> keys, List<Integer> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // TREEMAP
    // ==========================================================

    // CHALLENGE 11 (TreeMap)
    // Returns a map with each word and the number of times it appears in the list, ordered by the word in the
    // normal comparison of texts (so any uppercase letter comes before any lowercase letter).
    // Example: sortedWordCounts([b, a, b])  returns {a=1, b=2}
    //          sortedWordCounts([b, A])     returns {A=1, b=1}
    //          sortedWordCounts([])         returns {}
    public static TreeMap<String, Integer> sortedWordCounts(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12 (TreeMap)
    // The map is a schedule: each key is an hour and each value is the activity that starts at that hour. Returns the
    // activity that is happening at the given hour, which is the one with the greatest key that is smaller than or
    // equal to the hour. If no activity has started yet, returns "none". The map is not modified.
    // Example: with {8=wake, 12=lunch, 18=dinner}
    //          latestBefore(schedule, 13)  returns "lunch"
    //          latestBefore(schedule, 12)  returns "lunch"
    //          latestBefore(schedule, 7)   returns "none"
    //          latestBefore(schedule, 23)  returns "dinner"
    public static String latestBefore(TreeMap<Integer, String> schedule, int hour) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13 (TreeMap)
    // Returns how many entries of the map have a key between "from" and "to", both included. If "from" is greater
    // than "to", the result is 0. The map is not modified.
    // Example: with {1=a, 5=b, 9=c, 12=d}
    //          countInRange(map, 5, 12)  returns 3
    //          countInRange(map, 2, 4)   returns 0
    //          countInRange(map, 9, 9)   returns 1
    //          countInRange(map, 12, 5)  returns 0
    public static int countInRange(TreeMap<Integer, String> map, int from, int to) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14 (TreeMap)
    // Returns a map with each word and the number of times it appears in the list, where "a" and "A" are the same
    // word. The entries are ordered alphabetically ignoring the case. The key of each entry is written as the word
    // appears for the first time in the list.
    // Example: caseInsensitiveCounts([b, A, a])      returns {A=2, b=1}
    //          caseInsensitiveCounts([Hello, hello]) returns {Hello=2}
    //          caseInsensitiveCounts([B, a, C])      returns {a=1, B=1, C=1}
    //          caseInsensitiveCounts([])             returns {}
    public static TreeMap<String, Integer> caseInsensitiveCounts(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15 (TreeMap)
    // Removes from the map the "count" entries with the smallest keys, and returns their values in ascending order of
    // their keys. If the map has fewer than "count" entries, all of them are removed. A count of 0 changes nothing.
    // The count is never negative.
    // Example: each one starts with the map {3=c, 1=a, 2=b}
    //          removeSmallest(map, 2)  returns [a, b]     and the map is now {3=c}
    //          removeSmallest(map, 0)  returns []         and the map does not change
    //          removeSmallest(map, 5)  returns [a, b, c]  and the map is now {}
    public static List<String> removeSmallest(TreeMap<Integer, String> map, int count) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
