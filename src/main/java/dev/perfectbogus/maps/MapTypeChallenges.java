package dev.perfectbogus.maps;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class MapTypeChallenges {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — HashMap (Challenges 1–3)
    // Focus: O(1) access, frequency counting, grouping, unordered storage
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — HashMap: word frequency counter
    //
    // Count how many times each word appears in the list.
    // Use merge() to accumulate counts.
    // Return Map<String, Integer> of word → count.
    //
    // Input:  ["apple","banana","apple","cherry","banana","apple"]
    // Output: {"apple"=3, "banana"=2, "cherry"=1}
    //
    // Must use HashMap (unordered, O(1) access).
    // Throw IllegalArgumentException if words is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — HashMap: group strings by first character
    //
    // Group a list of strings by their FIRST CHARACTER.
    // Return Map<Character, List<String>> where each key is a
    // first character and value is all strings starting with it,
    // in their ORIGINAL ORDER.
    //
    // Use computeIfAbsent() to build groups.
    //
    // Input:  ["apple","avocado","banana","blueberry","cherry"]
    // Output: {'a'=["apple","avocado"],
    //          'b'=["banana","blueberry"],
    //          'c'=["cherry"]}
    //
    // Must use HashMap.
    // Throw IllegalArgumentException if words is null or any word is empty.
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, List<String>> challenge2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        if (words.stream().anyMatch(String::isEmpty))
            throw new IllegalArgumentException("Words cannot be empty strings");
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — HashMap: two-sum index pairs
    //
    // Given a list of integers and a target sum, find all PAIRS
    // of INDICES (i, j) where i < j and list[i] + list[j] = target.
    //
    // Use a HashMap<Integer, Integer> to store value → index
    // for O(n) lookup instead of O(n²)!
    //
    // Return List<int[]> of [i, j] pairs in order of discovery.
    //
    // Input:  list=[2,7,11,15], target=9
    //   2+7=9 → indices [0,1] ✓
    // Output: [[0,1]]
    //
    // Input:  list=[3,2,4,1,3], target=6
    //   3+3=6 → [0,4] ✓, 2+4=6 → [1,2] ✓
    // Output: [[0,4],[1,2]] wait...
    //   Actually process in order:
    //   i=0: map={3→0}
    //   i=1: target-2=4, not in map → map={3→0,2→1}
    //   i=2: target-4=2, 2 in map at 1 → pair [1,2]! map={3→0,2→1,4→2}
    //   i=3: target-1=5, not in map → map={...,1→3}
    //   i=4: target-3=3, 3 in map at 0 → pair [0,4]!
    // Output: [[1,2],[0,4]]
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static List<int[]> challenge3(List<Integer> list, int target) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — LinkedHashMap (Challenges 4–6)
    // Focus: insertion order, access order, ordered processing
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — LinkedHashMap: first occurrence index
    //
    // Given a list of strings, return a LinkedHashMap<String, Integer>
    // where key = word and value = index of its FIRST appearance.
    // Preserve INSERTION ORDER (first-seen order).
    //
    // Use putIfAbsent() so only the FIRST index is stored.
    //
    // Input:  ["banana","apple","cherry","apple","banana"]
    // Output: {"banana"=0, "apple"=1, "cherry"=2}
    //   (LinkedHashMap → insertion order preserved!)
    //   (apple and banana appear again but indices NOT updated!)
    //
    // Throw IllegalArgumentException if words is null.
    // ─────────────────────────────────────────────────────────────
    public static LinkedHashMap<String, Integer> challenge4(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        return new LinkedHashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — LinkedHashMap: ordered frequency report
    //
    // Count word frequencies but preserve the order words were
    // FIRST SEEN (insertion order, not alphabetical!).
    // Return LinkedHashMap<String, Integer> of word → count
    // in first-occurrence order.
    //
    // Input:  ["cat","dog","cat","bird","dog","cat"]
    // Output: {"cat"=3, "dog"=2, "bird"=1}
    //   → "cat" first seen at index 0 → appears first!
    //   → "dog" first seen at index 1 → appears second!
    //   → "bird" first seen at index 3 → appears third!
    //
    // Must use LinkedHashMap (NOT HashMap → order matters!).
    // Use merge() to count.
    // Throw IllegalArgumentException if words is null.
    // ─────────────────────────────────────────────────────────────
    public static LinkedHashMap<String, Integer> challenge5(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        return new LinkedHashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — LinkedHashMap: sliding window last N unique
    //
    // Given a list of strings representing items being accessed,
    // return a LinkedHashMap containing only the LAST N UNIQUE items
    // in the order they were most recently accessed.
    //
    // If an item is accessed again → move it to the END (most recent).
    // Only keep the N most recently accessed unique items.
    //
    // Use LinkedHashMap with removeEldestEntry to limit size!
    //
    // Input:  items=["a","b","c","a","d"], n=3
    //   access "a" → {a}
    //   access "b" → {a,b}
    //   access "c" → {a,b,c}
    //   access "a" → remove "a", re-add at end → {b,c,a}
    //   access "d" → {b,c,a,d} → evict oldest "b" → {c,a,d}
    // Output (keys in order): ["c","a","d"]
    //
    // Return List<String> of keys in order (oldest → newest access).
    // Throw IllegalArgumentException if items is null or n <= 0.
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge6(List<String> items, int n) {
        if (items == null) throw new IllegalArgumentException("Items cannot be null");
        if (n <= 0)        throw new IllegalArgumentException("n must be positive");
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — TreeMap (Challenges 7–9)
    // Focus: sorted keys, range queries, floor/ceiling, navigation
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — TreeMap: range query on sorted keys
    //
    // Given a TreeMap<Integer, String> of price → product,
    // return all products with price between lo and hi INCLUSIVE.
    // Return List<String> sorted by price ASCENDING (TreeMap gives this!)
    //
    // Use TreeMap.subMap(lo, true, hi, true) for the range!
    //
    // Input:  map={10="pen",20="book",30="bag",40="laptop",50="phone"}
    //         lo=20, hi=40
    // Output: ["book","bag","laptop"]  ← prices 20,30,40 inclusive!
    //
    // Input:  lo=25, hi=35
    // Output: ["bag"]  ← only price 30 in range!
    //
    // Throw IllegalArgumentException if map is null or lo > hi.
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge7(TreeMap<Integer, String> map,
                                          int lo, int hi) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        if (lo > hi)     throw new IllegalArgumentException("lo must be <= hi");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — TreeMap: floor and ceiling lookup
    //
    // Given a TreeMap<Integer, String> of scores → grades and a
    // target score, find:
    // → floorEntry: highest score <= target (the grade you'd get!)
    // → ceilingEntry: lowest score >= target (next grade up!)
    //
    // Return record GradeBounds(String floorGrade, String ceilingGrade)
    // → use "NONE" if no floor or ceiling exists!
    //
    // Input:  map={60="D",70="C",80="B",90="A"}, target=75
    //   floor(75)   = 70 → "C"
    //   ceiling(75) = 80 → "B"
    // Output: GradeBounds("C","B")
    //
    // Input:  target=60 → floor=60→"D", ceiling=60→"D"
    // Output: GradeBounds("D","D")
    //
    // Input:  target=55 → floor=none, ceiling=60→"D"
    // Output: GradeBounds("NONE","D")
    //
    // Use floorEntry(key) and ceilingEntry(key) on TreeMap!
    // Throw IllegalArgumentException if map is null.
    // ─────────────────────────────────────────────────────────────
    record GradeBounds(String floorGrade, String ceilingGrade) {}

    public static GradeBounds challenge8(TreeMap<Integer, String> map, int target) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        return new GradeBounds("NONE", "NONE");
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — TreeMap: top N most expensive products
    //
    // Given a list of products (name, price), insert them into a
    // TreeMap<Double, String> (price → name) and return the TOP N
    // most expensive product NAMES in DESCENDING price order.
    //
    // Use TreeMap.descendingMap() or pollLastEntry() to get top N!
    //
    // record Product(String name, double price)
    //
    // Input:  [Pen/1.5, Laptop/999.0, Book/25.0, Phone/699.0, Bag/49.0]
    //         n=3
    // TreeMap sorted ASC: {1.5=Pen, 25.0=Book, 49.0=Bag, 699.0=Phone, 999.0=Laptop}
    // Top 3 DESC: Laptop(999), Phone(699), Bag(49)
    // Output: ["Laptop","Phone","Bag"]
    //
    // Throw IllegalArgumentException if products is null or n <= 0.
    // ─────────────────────────────────────────────────────────────
    record Product(String name, double price) {}

    public static List<String> challenge9(List<Product> products, int n) {
        if (products == null) throw new IllegalArgumentException("Products cannot be null");
        if (n <= 0)           throw new IllegalArgumentException("n must be positive");
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION D — ConcurrentHashMap (Challenges 10–12)
    // Focus: thread-safety, atomic operations, parallel processing
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — ConcurrentHashMap: parallel word frequency
    //
    // Count word frequencies using N threads in parallel.
    // Each thread processes a chunk of the word list.
    // Use ConcurrentHashMap.merge() for thread-safe counting!
    //
    // Input:  words=["apple","banana","apple","cherry","banana","apple"]
    //         threadCount=3
    // Output: {"apple"=3, "banana"=2, "cherry"=1}
    //   (same result regardless of thread count!)
    //
    // Must use ConcurrentHashMap (NOT HashMap → not thread-safe!).
    // Split words into threadCount chunks, each processed by a thread.
    // Use merge(word, 1, Integer::sum) for atomic increment.
    // Join all threads before returning.
    //
    // Throw IllegalArgumentException if words is null or threadCount <= 0.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge10(List<String> words,
                                                   int threadCount)
            throws InterruptedException {
        if (words == null)    throw new IllegalArgumentException("Words cannot be null");
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        return new ConcurrentHashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 11 — ConcurrentHashMap: atomic computeIfAbsent grouping
    //
    // Group strings by length using N threads in parallel.
    // Each thread processes a portion of the list.
    // Use ConcurrentHashMap + CopyOnWriteArrayList for thread safety!
    //
    // Return Map<Integer, List<String>>:
    //   key   = string length
    //   value = all strings of that length (order may vary!)
    //
    // Input:  words=["hi","hello","hey","world","ok"], threadCount=2
    // Output: {2=["hi","ok"], 5=["hello","world"], 3=["hey"]}
    //   (exact order within lists may vary due to threading!)
    //
    // Use computeIfAbsent(length, k -> new CopyOnWriteArrayList<>()).add(word)
    // Throw IllegalArgumentException if words is null or threadCount <= 0.
    // ─────────────────────────────────────────────────────────────
    public static Map<Integer, List<String>> challenge11(List<String> words,
                                                         int threadCount)
            throws InterruptedException {
        if (words == null)    throw new IllegalArgumentException("Words cannot be null");
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        return new ConcurrentHashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 12 — ConcurrentHashMap: atomic long accumulation
    //
    // Given a list of transactions (category → amount),
    // accumulate total amount per category using N threads.
    // Use ConcurrentHashMap with atomic operations.
    //
    // record Transaction(String category, long amount)
    //
    // Use merge(category, amount, Long::sum) for atomic addition!
    //
    // Input:  transactions=[Food/100,Tech/500,Food/200,Tech/300,Sports/150]
    //         threadCount=3
    // Output: {"Food"=300, "Tech"=800, "Sports"=150}
    //   (result always correct regardless of thread interleaving!)
    //
    // Must use ConcurrentHashMap and join all threads before returning.
    // Throw IllegalArgumentException if transactions is null or threadCount <= 0.
    // ─────────────────────────────────────────────────────────────
    record Transaction(String category, long amount) {}

    public static Map<String, Long> challenge12(List<Transaction> transactions,
                                                int threadCount)
            throws InterruptedException {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        if (threadCount  <= 0)    throw new IllegalArgumentException("threadCount must be positive");
        return new ConcurrentHashMap<>();
    }
}