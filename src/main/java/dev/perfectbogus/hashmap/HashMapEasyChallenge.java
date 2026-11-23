package dev.perfectbogus.hashmap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashMapEasyChallenge {

    // In every challenge that returns a map or an array, the result is new and the arguments are not modified.

    // ==========================================================
    // CHALLENGE 1
    // ==========================================================
    // Returns how many times each word appears in the array. The case matters, so "A" and "a" are different words.
    //
    // Examples:
    //   ["a", "b", "a"]  ->  {a=2, b=1}
    //   ["A", "a"]       ->  {A=1, a=1}
    //   []               ->  {}
    public static Map<String, Integer> countWords(String[] words) {
        return Arrays.stream(words).collect(Collectors.toMap(
                Function.identity(),
                e -> 1,
                Integer::sum
        ));
    }

    // ==========================================================
    // CHALLENGE 2
    // ==========================================================
    // Returns how many times each character appears in the text. Every character counts: letters, digits, spaces and
    // symbols. The case matters.
    //
    // Examples:
    //   "hello"  ->  {h=1, e=1, l=2, o=1}
    //   "a a"    ->  {a=2, ' '=1}
    //   "aA"     ->  {a=1, A=1}
    //   ""       ->  {}
    public static Map<Character, Integer> countLetters(String text) {
        return text.chars().mapToObj(c ->(char)c).collect(Collectors.toMap(
                Function.identity(),
                c -> 1,
                Integer::sum
        ));
    }

    // ==========================================================
    // CHALLENGE 3
    // ==========================================================
    // The array has at least one word. Returns the word that appears the most times. If several words appear the same
    // number of times, returns the one that comes first alphabetically (the normal comparison of texts).
    //
    // Examples:
    //   ["a", "b", "a"]            ->  "a"
    //   ["b", "a"]                 ->  "a"
    //   ["x", "y", "y", "x"]       ->  "x"
    //   ["z", "y", "y"]            ->  "y"
    public static String mostFrequent(String[] words) {
        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Integer>> byKeyDesc = Map.Entry.<String, Integer>comparingByKey().reversed();
        return Arrays.stream(words).collect(Collectors.toMap(
                Function.identity(),
                s -> 1,
                Integer::sum)).entrySet().stream().max(byValue.thenComparing(byKeyDesc)).get().getKey();
    }

    // ==========================================================
    // CHALLENGE 4
    // ==========================================================
    // Returns a map with the keys of both maps. If a key is in both maps, its value is the sum of the two values.
    //
    // Examples:
    //   ({a=1, b=2}, {b=3, c=4})  ->  {a=1, b=5, c=4}
    //   ({}, {x=1})               ->  {x=1}
    //   ({x=1}, {})               ->  {x=1}
    //   ({}, {})                  ->  {}
    public static Map<String, Integer> mergeMaps(Map<String, Integer> a, Map<String, Integer> b) {
        Map<String, Integer> merge = new HashMap<>(a);
        for (Map.Entry<String, Integer> entry : b.entrySet()) {
            merge.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        return merge;
    }

    // ==========================================================
    // CHALLENGE 5
    // ==========================================================
    // The values of the map are all different. Returns a map where every value becomes a key and every key becomes
    // its value.
    //
    // Examples:
    //   {a=1, b=2}  ->  {1=a, 2=b}
    //   {x=0}       ->  {0=x}
    //   {}          ->  {}
    public static Map<Integer, String> invert(Map<String, Integer> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                e -> e.getValue(),
                e -> e.getKey()
        ));
    }

    public static Map<Integer, String> invertReduced(Map<String, Integer> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getValue,
                Map.Entry::getKey
        ));
    }

    // ==========================================================
    // CHALLENGE 6
    // ==========================================================
    // Returns true if both texts have exactly the same characters the same number of times, in any order. The case
    // matters. Two empty texts are anagrams.
    //
    // Examples:
    //   ("listen", "silent")  ->  true
    //   ("abc", "abd")        ->  false
    //   ("aab", "abb")        ->  false
    //   ("", "")              ->  true
    public static boolean areAnagrams(String a, String b) {
        if (a.length() != b.length()) return false;

        Map<Character, Integer> mapA = a.chars().mapToObj(c ->(char)c).collect(Collectors.toMap(
                Function.identity(),
                s -> 1,
                Integer::sum
        ));
        Map<Character, Integer> mapB = b.chars().mapToObj(c ->(char)c).collect(Collectors.toMap(
                Function.identity(),
                s -> 1,
                Integer::sum
        ));

        for (Map.Entry<Character, Integer> entry : mapA.entrySet()) {
            if (mapB.get(entry.getKey()) == null) return false;
            if (!mapB.get(entry.getKey()).equals(entry.getValue())) return false;
        }
        return true;
    }

    public static boolean areAnagramsSimplified(String a, String b) {
        if (a.length() != b.length()) return false;

        Map<Character, Integer> mapA = helperMap(a);
        Map<Character, Integer> mapB = helperMap(b);

        return mapA.equals(mapB);
    }

    private static Map<Character, Integer> helperMap(String s) {
        return s.chars().mapToObj(c -> (char)c).collect(Collectors.toMap(
                Function.identity(),
                c -> 1,
                Integer::sum
        ));
    }

    // ==========================================================
    // CHALLENGE 7
    // ==========================================================
    // The array has exactly one pair of different positions whose values add up to target. Returns those two
    // positions, the smaller one first. The values can be negative or repeated. The array can have up to 300,000
    // values.
    //
    // Examples:
    //   ([2, 7, 11, 15], 9)               ->  [0, 1]
    //   ([3, 2, 4], 6)                    ->  [1, 2]
    //   ([3, 3], 6)                       ->  [0, 1]
    //   ([-1, -2, -3, -4, -5], -8)        ->  [2, 4]
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (seen.containsKey(diff)) {
                return new int[] {seen.get(diff), i};
            } else {
                seen.put(nums[i], i);
            }
        }
        throw new IllegalStateException();
    }

    // ==========================================================
    // CHALLENGE 8
    // ==========================================================
    // Groups the words by their length: each key is a length and its value is the list of words with that length, in
    // the order they have in the original list. Repeated words are kept.
    //
    // Examples:
    //   ["a", "bb", "c", "dd"]  ->  {1=[a, c], 2=[bb, dd]}
    //   ["x", "x"]              ->  {1=[x, x]}
    //   []                      ->  {}
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                String::length
        ));
    }

    // ==========================================================
    // CHALLENGE 9
    // ==========================================================
    // Returns the position of the first character that appears exactly once in the text. If there is no such
    // character, returns -1. The case matters.
    //
    // Examples:
    //   "leetcode"      ->  0
    //   "loveleetcode"  ->  2
    //   "aabb"          ->  -1
    //   ""              ->  -1
    public static int firstUniqueIndex(String text) {
        Set<Character> read = new HashSet<>();
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (read.add(text.charAt(i))) {
                map.put(text.charAt(i), i);
            } else {
                map.remove(text.charAt(i));
            }
        }
        if (map.isEmpty()) return -1;
        return map.pollFirstEntry().getValue();
    }

    // ==========================================================
    // CHALLENGE 10
    // ==========================================================
    // Returns a map with only the entries whose value is at least min.
    //
    // Examples:
    //   ({a=1, b=5, c=3}, 3)  ->  {b=5, c=3}
    //   ({a=1}, 2)            ->  {}
    //   ({a=2}, 2)            ->  {a=2}
    //   ({}, 0)               ->  {}
    public static Map<String, Integer> removeBelow(Map<String, Integer> map, int min) {
        return map.entrySet().stream().filter(e -> e.getValue() >= min).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    public static Map<String, Integer> removeBelowSimplified(Map<String, Integer> map, int min) {
        Map<String, Integer> result = new HashMap<>(map);
        result.values().removeIf(v -> v < min);
        return result;
    }

    // ==========================================================
    // CHALLENGE 11
    // ==========================================================
    // Every entry has the form "name:amount", where the amount is a whole number between 0 and 1,000,000. Returns the
    // total amount of each name. The case matters.
    //
    // Examples:
    //   ["ana:10", "bob:5", "ana:7"]  ->  {ana=17, bob=5}
    //   ["x:0"]                       ->  {x=0}
    //   ["a:1", "A:2"]                ->  {a=1, A=2}
    //   []                            ->  {}
    public static Map<String, Integer> totalsByName(String[] entries) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12
    // ==========================================================
    // Returns true if the note can be written using characters of the magazine, using each character of the magazine
    // at most once. The case matters. An empty note can always be written.
    //
    // Examples:
    //   ("a", "b")    ->  false
    //   ("aa", "aab") ->  true
    //   ("aa", "ab")  ->  false
    //   ("", "")      ->  true
    public static boolean canBuildNote(String note, String magazine) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13
    // ==========================================================
    // The stock says how many units of each item are in a shop. Every name in sold is one unit sold. Returns the new
    // stock after the sales. Items that reach 0 units are removed from the result. Every sold item is in the stock
    // and there are always enough units.
    //
    // Examples:
    //   ({apple=3, pear=1}, [apple, pear, apple])  ->  {apple=1}
    //   ({a=1}, [])                                ->  {a=1}
    //   ({a=2, b=2}, [b])                          ->  {a=2, b=1}
    //   ({a=1}, [a])                               ->  {}
    public static Map<String, Integer> updateStock(Map<String, Integer> stock, String[] sold) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14
    // ==========================================================
    // Returns the position where each character appears for the first time in the text.
    //
    // Examples:
    //   "abca"  ->  {a=0, b=1, c=2}
    //   "aab"   ->  {a=0, b=2}
    //   "zz"    ->  {z=0}
    //   ""      ->  {}
    public static Map<Character, Integer> firstIndexOfEachChar(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15
    // ==========================================================
    // Returns true if two different positions have the same value and are at most k positions apart. k is not
    // negative. The array can have up to 300,000 values and k can be up to 300,000.
    //
    // Examples:
    //   ([1, 2, 3, 1], 3)           ->  true
    //   ([1, 0, 1, 1], 1)           ->  true
    //   ([1, 2, 3, 1, 2, 3], 2)     ->  false
    //   ([1, 1], 0)                 ->  false
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
