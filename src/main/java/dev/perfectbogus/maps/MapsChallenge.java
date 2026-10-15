package dev.perfectbogus.maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapsChallenge {

    // CHALLENGE 1
    // Returns the capital stored for country in capitals, or the text "Unknown" when capitals has no entry for country.
    // Example: capitals = {Peru=Lima, Chile=Santiago}
    //          capitalOf(capitals, "Peru")   returns "Lima"
    //          capitalOf(capitals, "Brazil") returns "Unknown"
    public static String capitalOf(Map<String, String> capitals, String country) {
        return capitals.getOrDefault(country, "Unknown");
    }

    // CHALLENGE 2
    // Returns how many times each character appears in text. A character that does not appear in text is not a key of
    // the result. An empty text gives an empty map.
    // Example: countCharacters("hello") returns {h=1, e=1, l=2, o=1}
    //          countCharacters("")      returns {}
    public static Map<Character, Integer> countCharacters(String text) {
        return text.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toMap(
                        Function.identity(),
                        c -> 1,
                        Integer::sum
                ));
    }

    // CHALLENGE 3
    // Returns a new map in which every value of map is a key and every key of map is the value of that key. All the
    // values of map are different. map is not modified.
    // Example: invert({a=1, b=2}) returns {1=a, 2=b}
    //          invert({})         returns {}
    public static Map<Integer, String> invert(Map<String, Integer> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getValue,
                Map.Entry::getKey,
                (existing, incoming) -> existing
        ));
    }

    // CHALLENGE 4
    // Returns the sum of all the values of stock. An empty map gives 0.
    // Example: totalOfValues({apple=3, pear=4, kiwi=10}) returns 17
    //          totalOfValues({})                         returns 0
    public static int totalOfValues(Map<String, Integer> stock) {
        return stock.values().stream().mapToInt(Integer::intValue).sum();
    }

    // CHALLENGE 5
    // Returns the keys of scores whose value is strictly greater than threshold, sorted alphabetically.
    // Example: keysAbove({bob=70, ana=90, cy=85}, 80) returns [ana, cy]
    //          keysAbove({bob=70, ana=90, cy=85}, 90) returns []
    public static List<String> keysAbove(Map<String, Integer> scores, int threshold) {
        return scores.entrySet().stream()
                .filter(e -> e.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // CHALLENGE 6
    // Returns a new map with all the entries of a and b. When a key is in both maps, its value in the result is the sum
    // of its two values. Neither a nor b is modified.
    // Example: mergeSum({a=1, b=2}, {b=10, c=3}) returns {a=1, b=12, c=3}
    //          mergeSum({a=1}, {})               returns {a=1}
    public static Map<String, Integer> mergeSum(Map<String, Integer> a, Map<String, Integer> b) {
        Map<String, Integer> merge = new HashMap<>(a);
        b.forEach((key, value) -> merge.merge(key, value, Integer::sum));
        return merge;
    }

    // CHALLENGE 7
    // Returns the key with the greatest value. If several keys share the greatest value, returns the smallest of them
    // in alphabetical order. The values can be negative. An empty map gives null.
    // Example: keyWithMaxValue({a=3, b=9, c=9}) returns "b"
    //          keyWithMaxValue({x=-5, y=-2})    returns "y"
    //          keyWithMaxValue({})              returns null
    public static String keyWithMaxValue(Map<String, Integer> map) {
        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> byAlpha = Map.Entry.comparingByKey(Comparator.reverseOrder());
        return map.entrySet().stream().max(byValue.thenComparing(byAlpha)).map(Map.Entry::getKey).orElse(null);
    }

    // CHALLENGE 8
    // Removes from map, in place, every entry whose value is less than min, and returns how many entries were removed.
    // Example: map = {a=1, b=5, c=2}, removeBelow(map, 2) returns 1 and map becomes {b=5, c=2}
    //          map = {a=1, b=5, c=2}, removeBelow(map, 0) returns 0 and map stays {a=1, b=5, c=2}
    public static int removeBelow(Map<String, Integer> map, int min) {
        int originalSize = map.size();
        map.entrySet().removeIf(e -> e.getValue() < min);
        return originalSize - map.size();
    }

    // CHALLENGE 9
    // For every key of keys that is not yet in map, puts that key in map with the value 0, in place. A key that is
    // already in map keeps its current value. Returns how many keys were added (a key repeated in keys is added once).
    // Example: map = {a=5}, keys = [a, b, c, b]: returns 2 and map becomes {a=5, b=0, c=0}
    //          map = {a=5}, keys = []:           returns 0 and map stays {a=5}
    public static int addMissingKeys(Map<String, Integer> map, List<String> keys) {
        int originalSize = map.size();
        for (String k : keys) {
            map.putIfAbsent(k, 0);
        }
        return map.size() - originalSize;
    }

    // CHALLENGE 10
    // Groups words by length: every key of the result is a length and its value is the list of the words of that
    // length, in the same order as in words. A length with no words is not a key of the result.
    // Example: groupByLength([a, bb, cc, d]) returns {1=[a, d], 2=[bb, cc]}
    //          groupByLength([])             returns {}
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length
        ));
    }

    // CHALLENGE 11
    // Replaces, in place, the value of every entry of map with twice that value. The keys do not change.
    // Example: map = {a=1, b=5, c=-3} becomes {a=2, b=10, c=-6}
    //          map = {} stays {}
    public static void doubleValues(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            entry.setValue(entry.getValue() * 2);
        }
    }

    public static void doubleValuesFunctional(Map<String, Integer> map) {
        map.replaceAll((k, v) -> v * 2);
    }

    // CHALLENGE 12
    // Returns true when a and b contain exactly the same keys, whatever their values are.
    // Example: sameKeys({a=1, b=2}, {b=9, a=7}) returns true
    //          sameKeys({a=1}, {a=1, b=2})      returns false
    //          sameKeys({}, {})                 returns true
    public static boolean sameKeys(Map<String, Integer> a, Map<String, Integer> b) {
        if (a.size() != b.size()) return false;
        for (Map.Entry<String, Integer> entry : a.entrySet()) {
            if (!b.containsKey(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    // CHALLENGE 13
    // Returns the entries of map as text: every entry is written as key=value, the entries are sorted alphabetically
    // by key and separated by a comma and a space. An empty map gives an empty text.
    // Example: entriesToText({b=2, a=1, c=3}) returns "a=1, b=2, c=3"
    //          entriesToText({x=7})            returns "x=7"
    //          entriesToText({})               returns ""
    public static String entriesToText(Map<String, Integer> map) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns a new map with the same entries as map, whose iteration order is by key in reverse alphabetical order
    // (z before a). map is not modified.
    // Example: sortedByKeyDescending({a=1, c=3, b=2}) returns a map that iterates c=3, then b=2, then a=1
    public static Map<String, Integer> sortedByKeyDescending(Map<String, Integer> map) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Builds a map that pairs the element at each position of keys with the element at the same position of values.
    // If the lists have different sizes, only the positions that exist in both lists are used. If a key is repeated,
    // the last value wins.
    // Example: zip([a, b, c], [1, 2, 3]) returns {a=1, b=2, c=3}
    //          zip([a, b, c], [1, 2])    returns {a=1, b=2}
    //          zip([a, a], [1, 2])       returns {a=2}
    public static Map<String, Integer> zip(List<String> keys, List<Integer> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
