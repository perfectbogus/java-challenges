package dev.perfectbogus.maps.treemap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapBeginnerChallenge {

    // CHALLENGE 1
    // Returns the smallest key currently in map.
    public static Integer smallestKey(TreeMap<Integer, String> map) {
        return map.firstKey();
    }

    // CHALLENGE 2
    // Returns the largest key currently in map.
    public static Integer largestKey(TreeMap<Integer, String> map) {
        return map.lastKey();
    }

    // CHALLENGE 3
    // Returns the smallest key in map that is greater than or equal to key,
    // or null if no such key exists.
    public static Integer ceiling(TreeMap<Integer, String> map, int key) {
        return map.ceilingKey(key);
    }

    // CHALLENGE 4
    // Returns the largest key in map that is less than or equal to key,
    // or null if no such key exists.
    public static Integer floor(TreeMap<Integer, String> map, int key) {
        return map.floorKey(key);
    }

    // CHALLENGE 5
    // Returns the smallest key in map that is strictly greater than key,
    // or null if no such key exists.
    public static Integer higher(TreeMap<Integer, String> map, int key) {
        return map.higherKey(key);
    }

    // CHALLENGE 6
    // Returns the largest key in map that is strictly less than key,
    // or null if no such key exists.
    public static Integer lower(TreeMap<Integer, String> map, int key) {
        return map.lowerKey(key);
    }

    // CHALLENGE 7
    // Returns, in ascending order, every key in map that is strictly less
    // than key.
    public static List<Integer> keysBelow(TreeMap<Integer, String> map, int key) {
        return map.headMap(key).keySet().stream().toList();
    }

    // CHALLENGE 8
    // Returns, in ascending order, every key in map that is greater than
    // or equal to fromInclusive and strictly less than toExclusive.
    public static List<Integer> keysInRange(TreeMap<Integer, String> map, int fromInclusive, int toExclusive) {
        return map.subMap(fromInclusive, toExclusive).keySet().stream().toList();
    }
}