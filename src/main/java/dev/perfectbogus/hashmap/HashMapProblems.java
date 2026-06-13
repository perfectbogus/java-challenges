package dev.perfectbogus.hashmap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashMapProblems {

    // Task 1 — HashSet
    // Return true if any value appears more than once in arr.
    // Use HashSet.add() which returns false if the element already exists.
    public static boolean containsDuplicate(int[] arr) {
        // TODO: implement
        Set<Integer> seen = new HashSet<>();
        for (int j : arr) {
            boolean exists = !seen.add(j);
            if (exists) {
                return true;
            }
        }
        return false;
    }

    // Task 2 — HashSet
    // Return the length of the longest sequence of consecutive integers.
    // Add all elements to a HashSet first.
    // A sequence only starts when num-1 is NOT in the set.
    // Then count upward: num+1, num+2... while present in the set.
    public static int longestConsecutiveSequence(int[] arr) {
        // TODO: implement
        Set<Integer> set = new HashSet<>(arr.length);
        for (int i : arr) {
            set.add(i);
        }

        int longest = 0;
        for (int i : arr) {
            if (!set.contains(i - 1)) {
                int j = i;
                int length = 1;
                while (set.contains(j + 1)) {
                    j++;
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    // Task 3 — Frequency map
    // Return the index of the first character that appears exactly once.
    // Pass 1: build frequency map. Pass 2: find first char with frequency 1.
    // Return -1 if no such character exists.
    public static int firstNonRepeatingChar(String s) {
        // TODO: implement
        return -1;
    }

    // Task 4 — Frequency map
    // Return true if t is an anagram of s (same chars, same frequencies).
    // Build frequency map from s, decrement for each char in t.
    // Return false if any count goes below zero or lengths differ.
    public static boolean isAnagram(String s, String t) {
        // TODO: implement
        return false;
    }

    // Task 5 — Frequency map + PriorityQueue
    // Return the k most frequent elements in any order.
    // Build frequency map first, then use a min-heap of size k.
    public static List<Integer> topKFrequent(int[] arr, int k) {
        // TODO: implement
        return List.of();
    }

    // Task 6 — Complement lookup
    // Return int[2] with the indices of two numbers that add up to target.
    // For each element compute complement = target - arr[i].
    // Check if complement already exists in a HashMap<Integer, Integer> (value → index).
    // Assume exactly one solution exists.
    public static int[] twoSum(int[] arr, int target) {
        // TODO: implement
        return new int[0];
    }

    // Task 7 — Prefix sum + HashMap
    // Return the total number of subarrays whose sum equals k.
    // Use HashMap<Integer, Integer> (prefixSum → count).
    // At each index check if prefixSum - k exists in the map.
    // Initialise map with {0: 1}.
    public static int subarraySumEqualsK(int[] arr, int k) {
        // TODO: implement
        return 0;
    }

    // Task 8 — Prefix sum + HashMap (combined)
    // Return the length of the longest subarray with equal 0s and 1s.
    // Replace 0 with -1 first. Use HashMap<Integer, Integer> (prefixSum → firstIndex).
    // If prefixSum was seen before, subarray between firstIndex+1 and current index
    // has sum 0 (equal 0s and 1s). Initialise map with {0: -1}.
    public static int longestEqualZerosAndOnes(int[] arr) {
        // TODO: implement
        return 0;
    }
}