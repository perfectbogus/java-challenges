package dev.perfectbogus.maps.treemap;
import java.util.*;

public class TreeMapIntermediateChallenge {

    // CHALLENGE 1
    // Builds a new TreeMap containing every entry from source, but treats
    // keys as equal regardless of case (so "Apple" and "apple" refer to
    // the same key). When two source keys differ only by case, the one
    // encountered later while iterating source's entrySet wins.
    public static TreeMap<String, Integer> buildCaseInsensitiveMap(Map<String, Integer> source) {
        Comparator<String> byCase = Comparator.comparing(String::toLowerCase);
        TreeMap<String, Integer> result = new TreeMap<>(byCase);
        result.putAll(source);
        return result;
    }

    // CHALLENGE 2
    // Repeatedly removes the entry with the largest key from map until it
    // is empty, returning the keys in the order they were removed (i.e.
    // descending order). map is left empty after this call.
    public static List<Integer> drainDescending(TreeMap<Integer, String> map) {
        List<Integer> result = new ArrayList<>();
        while (!map.isEmpty()) {
            Map.Entry<Integer, String> entry = map.pollLastEntry();
            result.add(entry.getKey());
        }
        return result;
    }

    // CHALLENGE 3
    // Returns every key in map in descending order, without modifying map.
    public static List<Integer> keysDescendingNonDestructive(TreeMap<Integer, String> map) {
        return map.descendingKeySet().stream().toList();
    }

    // CHALLENGE 4
    // Returns the entry (key and value) whose key is the smallest key in
    // map that is greater than or equal to key, or null if no such entry
    // exists.
    public static Map.Entry<Integer, String> ceilingEntry(TreeMap<Integer, String> map, int key) {
        return map.ceilingEntry(key);
    }

    // CHALLENGE 5
    // Returns the entry (key and value) whose key is the largest key in
    // map that is less than or equal to key, or null if no such entry
    // exists.
    public static Map.Entry<Integer, String> floorEntry(TreeMap<Integer, String> map, int key) {
        return map.floorEntry(key);
    }

    // CHALLENGE 6
    // Returns a new TreeMap containing every entry of map whose key is
    // less than or equal to key (i.e. key itself is included, unlike the
    // beginner headMap challenge).
    public static TreeMap<Integer, String> headMapInclusive(TreeMap<Integer, String> map, int key) {
        return new TreeMap<>(map.headMap(key, true));
    }

    // CHALLENGE 7
    // Returns a new TreeMap containing every entry of map whose key is
    // strictly greater than key (i.e. key itself is excluded).
    public static TreeMap<Integer, String> tailMapExclusive(TreeMap<Integer, String> map, int key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a new TreeMap containing every key from map1 and map2. When
    // a key appears in both, the resulting value is the sum of the two
    // values.
    public static TreeMap<String, Integer> mergeSorted(TreeMap<String, Integer> map1, TreeMap<String, Integer> map2) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Builds a new TreeMap from the given parallel lists of keys and
    // values (keys.get(i) maps to values.get(i)), ordered so that
    // iterating the map visits keys from largest to smallest.
    public static TreeMap<Integer, String> buildWithReverseOrder(List<Integer> keys, List<String> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns how many keys in map fall between fromInclusive and
    // toInclusive, with both endpoints counted if present.
    public static int countInRange(TreeMap<Integer, String> map, int fromInclusive, int toInclusive) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
