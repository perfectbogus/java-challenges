package dev.perfectbogus.generics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class GenericChallenges3 {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–3)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Generic count matching elements
    //
    // Count how many elements in the list satisfy the predicate.
    // Return 0 if no elements match.
    //
    // Input:  list=[1,2,3,4,5], predicate=n -> n % 2 == 0 → 2
    // Input:  list=["apple","banana","cherry"],
    //         predicate=s -> s.length() > 5             → 2
    // Input:  list=[1,2,3], predicate=n -> n > 10       → 0
    //
    // Throw IllegalArgumentException if list or predicate is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> int challenge1(List<T> list, Predicate<T> predicate) {
        if (list == null)      throw new IllegalArgumentException("List cannot be null");
        if (predicate == null) throw new IllegalArgumentException("Predicate cannot be null");
        return (int) list.stream().filter(predicate).count();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Generic rotate right
    //
    // Return a NEW list rotated RIGHT by k positions.
    // Each element shifts k positions to the right.
    // Elements that fall off the end wrap to the beginning.
    //
    // Input:  list=[1,2,3,4,5], k=2 → [4,5,1,2,3]
    //   position 0 ← was position 3 (moved right 2)
    //   position 1 ← was position 4
    //   position 2 ← was position 0
    //   ...
    //
    // Input:  list=["a","b","c","d"], k=1 → ["d","a","b","c"]
    // Input:  list=[1,2,3], k=0          → [1,2,3]
    // Input:  list=[1,2,3], k=3          → [1,2,3] (full rotation!)
    // Input:  list=[1,2,3], k=5          → same as k=2 (k % size)
    //
    // Throw IllegalArgumentException if list is null or k < 0.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge2(List<T> list, int k) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        if (k < 0)        throw new IllegalArgumentException("k must be non-negative");
        if (k == 0) return list;
        if (list.isEmpty()) return new ArrayList<>();

        int shift = k % list.size();
        int idx = list.size() - shift;

        List<T> result = new ArrayList<>(list.subList(idx, list.size()));
        result.addAll(list.subList(0, idx));

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Generic chunk (partition into sublists)
    //
    // Split a list into consecutive chunks of given size.
    // Last chunk may be smaller if list size is not divisible!
    // Return List<List<T>>.
    //
    // Input:  list=[1,2,3,4,5,6,7], chunkSize=3
    // Output: [[1,2,3],[4,5,6],[7]]  ← last chunk smaller!
    //
    // Input:  list=["a","b","c","d"], chunkSize=2
    // Output: [["a","b"],["c","d"]]
    //
    // Input:  list=[1,2,3], chunkSize=10
    // Output: [[1,2,3]]  ← single chunk!
    //
    // Input:  list=[], chunkSize=3
    // Output: []
    //
    // Throw IllegalArgumentException if list is null or chunkSize <= 0.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<List<T>> challenge3(List<T> list, int chunkSize) {
        if (list == null)    throw new IllegalArgumentException("List cannot be null");
        if (chunkSize <= 0)  throw new IllegalArgumentException("chunkSize must be positive");
        if (list.isEmpty()) return new ArrayList<>();

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, list.size());
            result.add(new ArrayList<>(list.subList(i, end)));
        }

        return result;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 4–9)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Generic LRU Cache
    //
    // Implement a generic Least Recently Used (LRU) cache:
    //   LRUCache<K,V>(int capacity)
    //   V get(K key)         → return value or null if absent
    //                          accessing a key makes it "most recent"!
    //   void put(K key, V value) → add/update entry
    //                              if at capacity → evict LEAST recently used!
    //   int size()           → current number of entries
    //   boolean containsKey(K key) → key in cache?
    //
    // Use LinkedHashMap(capacity, 0.75f, true) with removeEldestEntry!
    // → accessOrder=true makes LinkedHashMap maintain access order!
    // → removeEldestEntry: return size() > capacity to evict oldest!
    //
    // Input:  capacity=2
    //   put("a", 1)  → cache={a=1}
    //   put("b", 2)  → cache={a=1, b=2}
    //   get("a")     → 1, "a" is now most recent!
    //   put("c", 3)  → capacity exceeded → evict LRU = "b"!
    //                  cache={a=1, c=3}
    //   get("b")     → null (evicted!)
    // ─────────────────────────────────────────────────────────────
    static class LRUCache<K, V> {
        private final int              capacity;
        private final LinkedHashMap<K, V> cache;

        LRUCache(int capacity) {
            if (capacity <= 0)
                throw new IllegalArgumentException("Capacity must be positive");
            this.capacity = capacity;
            this.cache    = new LinkedHashMap<>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    return size() > capacity; // ← implement eviction policy!
                }
            };
        }

        V get(K key)              { return cache.get(key); }
        void put(K key, V value)  { cache.put(key, value); }
        int size()                { return cache.size(); }
        boolean containsKey(K key){ return cache.containsKey(key); }
    }

    public static <K, V> LRUCache<K, V> challenge4(int capacity) {
        return new LRUCache<>(capacity);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Generic pipeline
    //
    // Apply a LIST of functions to an initial value as a pipeline.
    // Each function's output becomes the next function's input.
    // If the list is empty → return the initial value unchanged.
    //
    // Input:  value=5,
    //         fns=[n->n*2, n->n+3, n->n*n]
    //   step1: 5*2=10
    //   step2: 10+3=13
    //   step3: 13*13=169
    // Output: 169
    //
    // Input:  value="hello",
    //         fns=[String::toUpperCase, s->s+"!"]
    // Output: "HELLO!"
    //
    // Input:  fns=[]  → return value unchanged
    //
    // Throw IllegalArgumentException if fns list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> T challenge5(T value, List<Function<T, T>> fns) {
        if (fns == null) throw new IllegalArgumentException("Functions list cannot be null");
        if (fns.isEmpty()) return value;

        Function<T, T> all = Function.identity();
        for (Function<T, T> fn : fns) {
            all = all.andThen(fn);
        }

        return all.apply(value);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Generic sliding window
    //
    // Return ALL consecutive sublists (windows) of given size.
    // Each window shifts one position to the right.
    //
    // Input:  list=[1,2,3,4,5], windowSize=3
    // Output: [[1,2,3],[2,3,4],[3,4,5]]
    //
    // Input:  list=["a","b","c","d"], windowSize=2
    // Output: [["a","b"],["b","c"],["c","d"]]
    //
    // Input:  list=[1,2,3], windowSize=3 → [[1,2,3]] (one full window)
    // Input:  list=[1,2],   windowSize=3 → [] (window larger than list!)
    //
    // Throw IllegalArgumentException if list is null or windowSize <= 0.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<List<T>> challenge6(List<T> list, int windowSize) {
        if (list == null)    throw new IllegalArgumentException("List cannot be null");
        if (windowSize <= 0) throw new IllegalArgumentException("windowSize must be positive");

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i <= list.size() - windowSize; i++) {
            result.add(new ArrayList<>(list.subList(i, i + windowSize)));
        }

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Generic MultiMap
    //
    // Implement a MultiMap where each key maps to MULTIPLE values:
    //   void add(K key, V value)        → add value to key's list
    //   List<V> getAll(K key)           → return all values for key
    //                                     (empty list if key not found)
    //   boolean remove(K key, V value)  → remove ONE occurrence of value
    //                                     return true if removed, false if not found
    //   boolean containsKey(K key)      → key has at least one value?
    //   int size()                      → total number of KEY-VALUE pairs
    //
    // Input:
    //   add("a", 1), add("a", 2), add("b", 3)
    //   getAll("a")      → [1, 2]
    //   getAll("b")      → [3]
    //   getAll("c")      → []  ← empty list, not null!
    //   size()           → 3
    //   remove("a", 1)   → true, "a" → [2]
    //   remove("a", 99)  → false (99 not in "a"'s list)
    //   containsKey("a") → true
    // ─────────────────────────────────────────────────────────────
    static class MultiMap<K, V> {
        private final Map<K, List<V>> map = new HashMap<>();

        void add(K key, V value)            {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        List<V> getAll(K key)               {
            return map.getOrDefault(key, List.of());
        }

        boolean remove(K key, V value)      {
            if (map.containsKey(key)) {
                return map.get(key).remove(value);
            }
            return false;
        }

        boolean containsKey(K key)          {
            return map.containsKey(key);
        }

        int size() {
            return map.values().stream()
                    .mapToInt(List::size)
                    .sum();
        }
    }

    public static <K, V> MultiMap<K, V> challenge7() {
        return new MultiMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Generic Binary Search Tree (BST)
    //
    // Implement a generic BST where T extends Comparable<T>:
    //   void insert(T value)    → insert value maintaining BST order
    //   boolean contains(T value)→ true if value is in tree
    //   List<T> inOrder()       → in-order traversal (LEFT-ROOT-RIGHT)
    //                             → returns elements SORTED ASC!
    //
    // BST property:
    //   left child  < parent
    //   right child > parent
    //   duplicates  → ignored (don't insert if already exists)
    //
    // Input:  insert 5,3,7,1,4,6,8
    //   tree structure:
    //         5
    //        / \
    //       3   7
    //      / \ / \
    //     1  4 6  8
    //
    //   contains(4) → true
    //   contains(9) → false
    //   inOrder()   → [1,3,4,5,6,7,8] ← sorted!
    // ─────────────────────────────────────────────────────────────
    static class BST<T extends Comparable<T>> {
        private Node<T> root;

        private static class Node<T> {
            T value;
            Node<T> left, right;
            Node(T value) { this.value = value; }
        }

        void insert(T value)       {
            root = insertNode(root, value);
        }

        private Node<T> insertNode(Node<T> node, T value) {
            if (node == null) return new Node<>(value);

            int cmp = value.compareTo(node.value);

            if (cmp < 0) {
                node.left = insertNode(node.left, value);
            } else if (cmp > 0) {
                node.right = insertNode(node.right, value);
            }

            return node;
        }

        boolean contains(T value)  {
            return containsNode(root, value);
        }

        private boolean containsNode(Node<T> node, T value) {
            if (node == null) return false;

            int cmp = value.compareTo(node.value);

            if (cmp < 0) return containsNode(node.left, value);
            if (cmp > 0) return containsNode(node.right, value);
            else return true;
        }


        List<T> inOrder()          {
            List<T> result = new ArrayList<>();
            inOrderHelper(root, result);
            return result;
        }

        private void inOrderHelper(Node<T> node, List<T> result) {
            if (node == null) return;
            inOrderHelper(node.left, result);
            result.add(node.value);
            inOrderHelper(node.right, result);
        }
    }

    public static <T extends Comparable<T>> BST<T> challenge8() {
        return new BST<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Generic Lazy<T> — deferred computation
    //
    // Implement a Lazy<T> that computes its value ONLY ON FIRST ACCESS
    // and caches it for subsequent accesses.
    //
    //   static Lazy<T> of(Supplier<T> supplier) → factory method
    //   T get()                                  → compute on first call,
    //                                              return cached on subsequent!
    //   boolean isComputed()                     → has get() been called?
    //
    // The supplier must be called AT MOST ONCE regardless of how many
    // times get() is called!
    //
    // Input:  AtomicInteger count = new AtomicInteger(0)
    //         Lazy<Integer> lazy = Lazy.of(() -> { count.incrementAndGet(); return 42; })
    //   isComputed() → false   (not yet computed!)
    //   get()        → 42,     count=1
    //   isComputed() → true
    //   get()        → 42,     count still=1 (cached!)
    //   get()        → 42,     count still=1 (cached!)
    // ─────────────────────────────────────────────────────────────
    static class Lazy<T> {
        private final Supplier<T> supplier;
        private       T           value;
        private       boolean     computed = false;

        private Lazy(Supplier<T> supplier) {
            if (supplier == null)
                throw new IllegalArgumentException("Supplier cannot be null");
            this.supplier = supplier;
        }

        static <T> Lazy<T> of(Supplier<T> supplier) {
            return new Lazy<>(supplier);
        }

        T get()              {
            if (!computed) {
                value = supplier.get();
                computed = true;
            }
            return value;
        }
        boolean isComputed() {
            return computed;
        }
    }

    public static <T> Lazy<T> challenge9(Supplier<T> supplier) {
        return Lazy.of(supplier);
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Generic directed Graph<T> with BFS and cycle detection
    //
    // Implement a generic DIRECTED graph:
    //   void addVertex(T vertex)          → add a vertex
    //   void addEdge(T from, T to)        → add directed edge from→to
    //                                       auto-adds vertices if missing!
    //   List<T> bfs(T start)              → BFS traversal from start
    //                                       return vertices in visit order
    //   List<T> dfs(T start)              → DFS traversal from start
    //                                       return vertices in visit order
    //   boolean isReachable(T from, T to) → can we reach 'to' from 'from'?
    //   boolean hasCycle()                → does the graph have any cycle?
    //
    // Hint — Graph representation:
    //   Map<T, List<T>> adjacencyList = new HashMap<>()
    //   addEdge(a, b) → adjacencyList.computeIfAbsent(a, k -> new ArrayList<>()).add(b)
    //
    // Hint — BFS: use Queue<T> (LinkedList)
    //   Set<T> visited = new LinkedHashSet<>()  ← preserves insertion order!
    //   queue.offer(start), visited.add(start)
    //   while(!queue.isEmpty()):
    //     current = queue.poll()
    //     for each neighbor of current:
    //       if !visited → visited.add, queue.offer
    //   return new ArrayList<>(visited)
    //
    // Hint — DFS: use Stack<T> or recursion
    //   Set<T> visited = new LinkedHashSet<>()
    //   dfsHelper(start, visited):
    //     visited.add(start)
    //     for each neighbor: if !visited → dfsHelper(neighbor, visited)
    //
    // Hint — Cycle detection (DFS with THREE states):
    //   WHITE=0 (unvisited), GRAY=1 (in stack), BLACK=2 (done)
    //   Map<T, Integer> color = new HashMap<>() (all start WHITE)
    //   for each vertex v: if color=WHITE → dfsColor(v)
    //   dfsColor(v):
    //     color.put(v, GRAY)  ← mark as in-progress!
    //     for each neighbor n of v:
    //       if color=GRAY → CYCLE FOUND! (back edge!)
    //       if color=WHITE → dfsColor(n)
    //     color.put(v, BLACK) ← done!
    //
    // Input:  addEdge(1→2), addEdge(2→3), addEdge(3→4)
    //   bfs(1)         → [1,2,3,4]
    //   dfs(1)         → [1,2,3,4]
    //   isReachable(1,4)→ true
    //   isReachable(4,1)→ false (directed!)
    //   hasCycle()     → false
    //
    // Input with cycle: addEdge(1→2), addEdge(2→3), addEdge(3→1)
    //   hasCycle()     → true  ← 1→2→3→1!
    // ─────────────────────────────────────────────────────────────
    static class Graph<T> {
        private final Map<T, List<T>> adjacencyList = new HashMap<>();

        void addVertex(T vertex) {
            adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        }

        void addEdge(T from, T to) {
            addVertex(from);
            addVertex(to);
            adjacencyList.get(from).add(to);
        }

        List<T> bfs(T start)              { return new ArrayList<>(); }
        List<T> dfs(T start)              { return new ArrayList<>(); }
        boolean isReachable(T from, T to) { return false; }
        boolean hasCycle()                { return false; }
    }

    public static <T> Graph<T> challenge10() {
        return new Graph<>();
    }
}