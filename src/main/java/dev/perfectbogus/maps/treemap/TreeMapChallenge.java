package dev.perfectbogus.maps.treemap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeMapChallenge {

    // CHALLENGE 1
    // Returns the smallest key of map. An empty map gives null.
    // Example: map = {5=e, 2=b, 9=i}  smallestKey(map) returns 2
    //          map = {}               smallestKey(map) returns null
    public static Integer smallestKey(TreeMap<Integer, String> map) {
        if (map.isEmpty()) return null;
        return map.firstKey();
    }

    // CHALLENGE 2
    // Returns the value of the entry that has the greatest key of map. An empty map gives -1.
    // Example: map = {ana=3, bob=8, cy=5}  valueOfLastKey(map) returns 5   (the greatest key is "cy")
    //          map = {}                    valueOfLastKey(map) returns -1
    public static int valueOfLastKey(TreeMap<String, Integer> map) {
        if (map.isEmpty()) return -1;
        return map.lastEntry().getValue();
    }

    // CHALLENGE 3
    // Every key of thresholds is the lowest score of a grade, and its value is the name of that grade. Returns the
    // grade of score: the grade whose key is the greatest key that is less than or equal to score. If score is lower
    // than every key, returns "None".
    // Example: thresholds = {0=F, 60=D, 70=C, 80=B, 90=A}
    //          gradeOf(thresholds, 85) returns "B"
    //          gradeOf(thresholds, 90) returns "A"
    //          gradeOf(thresholds, -4) returns "None"
    public static String gradeOf(TreeMap<Integer, String> thresholds, int score) {
        Map.Entry<Integer, String> floorEntry = thresholds.floorEntry(score);
        if (floorEntry == null) {
            return "None";
        }
        return floorEntry.getValue();
    }

    // CHALLENGE 4
    // Returns the smallest key of map that is greater than or equal to value. If there is no such key, returns null.
    // Example: map = {10=a, 20=b, 30=c}
    //          firstKeyAtLeast(map, 15) returns 20
    //          firstKeyAtLeast(map, 20) returns 20
    //          firstKeyAtLeast(map, 31) returns null
    public static Integer firstKeyAtLeast(TreeMap<Integer, String> map, int value) {
        Map.Entry<Integer, String> entry = map.ceilingEntry(value);
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    // CHALLENGE 5
    // Returns the key of map that is closest to target. If two keys are equally close, returns the smaller one. An
    // empty map gives null.
    // Example: map = {10=a, 20=b, 40=c}
    //          closestKey(map, 23) returns 20
    //          closestKey(map, 31) returns 40
    //          closestKey(map, 30) returns 20   (20 and 40 are equally close, so the smaller one)
    //          closestKey(map, 99) returns 40
    public static Integer closestKey(TreeMap<Integer, String> map, int target) {
        Integer ceil = map.ceilingKey(target);
        Integer floor = map.floorKey(target);

        if (floor == null) return ceil;
        if (ceil == null) return floor;

        long distanceToFloor = (long) target - floor;
        long distanceToCeil = (long) ceil - target;

        return distanceToFloor <= distanceToCeil ? floor : ceil;
    }

    // CHALLENGE 6
    // Returns the keys of map that are between from and to, both included, from the smallest to the greatest. If from
    // is greater than to, the result is empty. map is not modified.
    // Example: map = {1=a, 3=c, 5=e, 7=g, 9=i}
    //          keysBetween(map, 3, 7) returns [3, 5, 7]
    //          keysBetween(map, 4, 8) returns [5, 7]
    //          keysBetween(map, 8, 4) returns []
    public static List<Integer> keysBetween(TreeMap<Integer, String> map, int from, int to) {
        if (from > to) return new ArrayList<>();
        return new ArrayList<>(map.subMap(from, true, to, true).keySet());
    }

    // CHALLENGE 7
    // Removes from map, in place, the n entries with the smallest keys, and returns the removed keys from the smallest
    // to the greatest. If map has fewer than n entries, removes all of them. If n is 0 or negative, removes nothing.
    // Example: map = {a=1, b=2, c=3, d=4}, removeSmallest(map, 2) returns [a, b] and map becomes {c=3, d=4}
    //          map = {a=1}, removeSmallest(map, 5) returns [a] and map becomes {}
    //          map = {a=1}, removeSmallest(map, 0) returns [] and map stays {a=1}
    public static List<String> removeSmallest(TreeMap<String, Integer> map, int n) {
        List<String> removed = new ArrayList<>();
        while (removed.size() < n && !map.isEmpty()) {
            removed.add(map.pollFirstEntry().getKey());
        }
        return removed;
    }

    // CHALLENGE 8
    // Returns the n greatest keys of map, from the greatest to the smallest. If map has fewer than n keys, returns all
    // of them. If n is 0 or negative, the result is empty. map is not modified.
    // Example: map = {a=1, b=2, c=3, d=4}
    //          greatestKeys(map, 2) returns [d, c]
    //          greatestKeys(map, 9) returns [d, c, b, a]
    //          greatestKeys(map, 0) returns []
    public static List<String> greatestKeys(TreeMap<String, Integer> map, int n) {
        TreeMap<String, Integer> copy = new TreeMap<>(map);
        List<String> res = new ArrayList<>();
        while (res.size() < n && !copy.isEmpty()) {
            res.add(copy.pollLastEntry().getKey());
        }
        return res;
    }

    public static List<String> greatestKeysImproved(TreeMap<String, Integer> map, int n) {
        List<String> res = new ArrayList<>();
        for (String k : map.descendingKeySet()) {
            if (res.size() >= n) break;
            res.add(k);
        }
        return res;
    }

    // CHALLENGE 9
    // Counts how many times each word appears in words, ignoring upper and lower case. The result is a TreeMap that
    // ignores case when it compares keys, so result.get("APPLE") finds the count of "apple" and "Apple" too. The key
    // stored for a word is the first spelling found in words.
    // Example: countIgnoringCase([Apple, banana, apple, BANANA, Cherry]) returns {Apple=2, banana=2, Cherry=1}
    //          and for that result, result.get("CHERRY") returns 1 and result.containsKey("kiwi") is false
    //          countIgnoringCase([]) returns {}
    public static TreeMap<String, Integer> countIgnoringCase(List<String> words) {
        TreeMap<String, Integer> res = new TreeMap<>(String::compareToIgnoreCase);
        for (String w : words) {
            res.merge(w, 1, Integer::sum);
        }
        return res;
    }

    public static TreeMap<String, Integer> countIgnoringCaseStream(List<String> words) {
        return words.stream().collect(Collectors.toMap(
                Function.identity(),
                w -> 1,
                Integer::sum,
                () -> new TreeMap<>(String::compareToIgnoreCase)
        ));
    }

    // CHALLENGE 10
    // Returns a new TreeMap with the entries of map whose key is greater than or equal to from. The result is
    // independent of map: changing one of them never changes the other.
    // Example: map = {a=1, c=3, e=5, g=7}
    //          entriesFrom(map, "c") returns {c=3, e=5, g=7}
    //          entriesFrom(map, "d") returns {e=5, g=7}
    //          entriesFrom(map, "z") returns {}
    //          after result.put("x", 9), map still has only a, c, e and g
    public static TreeMap<String, Integer> entriesFrom(TreeMap<String, Integer> map, String from) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (var entry : map.descendingMap().entrySet()) {
            if (entry.getKey().compareTo(from) < 0) break;
            treeMap.put(entry.getKey(), entry.getValue());
        }
        return treeMap;
    }

    public static TreeMap<String, Integer> entriesFromStream(TreeMap<String, Integer> map, String from) {
        return new TreeMap<>(map.tailMap(from, true));
    }
}
