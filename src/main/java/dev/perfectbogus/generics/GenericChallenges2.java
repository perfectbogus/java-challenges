package dev.perfectbogus.generics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class GenericChallenges2 {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–5)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Generic reverse
    //
    // Return a NEW list with elements in REVERSED order.
    // Original list must NOT be modified.
    //
    // Input:  [1, 2, 3, 4, 5]      → [5, 4, 3, 2, 1]
    // Input:  ["a","b","c"]         → ["c","b","a"]
    // Input:  [42]                  → [42]
    // Input:  []                    → []
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge1(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        List<T> result = new ArrayList<>(list);
        int l = 0;
        int r = list.size() - 1;

        while (l < r) {
            T tmp = result.get(l);
            result.set(l, result.get(r));
            result.set(r, tmp);
            l++;
            r--;
        }

        return result;
    }

    public static <T> List<T> challenge1_2(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        List<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Generic distinct preserving order
    //
    // Return a NEW list with DUPLICATE elements removed.
    // Keep the FIRST occurrence of each element.
    // Preserve original order.
    //
    // Input:  [1, 2, 3, 2, 1, 4]   → [1, 2, 3, 4]
    // Input:  ["a","b","a","c","b"] → ["a","b","c"]
    // Input:  [1, 1, 1]             → [1]
    // Input:  []                    → []
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge2(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Generic firstOrDefault
    //
    // Find the FIRST element matching a predicate.
    // If no element matches → return the defaultValue.
    //
    // Input:  list=[1,2,3,4,5], pred=n->n>3, default=0 → 4
    // Input:  list=[1,2,3],     pred=n->n>10, default=0 → 0
    // Input:  list=["apple","banana","cherry"],
    //         pred=s->s.startsWith("b"), default="none" → "banana"
    // Input:  list=[], pred=any, default=42 → 42
    //
    // Throw IllegalArgumentException if list or predicate is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> T challenge3(List<T> list, Predicate<T> predicate,
                                   T defaultValue) {
        if (list == null)      throw new IllegalArgumentException("List cannot be null");
        if (predicate == null) throw new IllegalArgumentException("Predicate cannot be null");
        return defaultValue;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Generic list to Map using key extractor
    //
    // Convert a list to Map<K, T> using a key extractor function.
    // Each element becomes a value, key extracted by keyExtractor.
    // If two elements produce the same key → LAST one wins!
    //
    // Input:  list=["apple","banana","cherry"],
    //         keyExtractor=String::length
    // Output: {5="apple" or "cherry" (last wins), 6="banana"}
    //         Actually: {5="cherry", 6="banana"}
    //
    // Input:  list=[Alice/95000, Bob/60000, Carol/85000]
    //         keyExtractor=Employee::name
    // Output: {"Alice"=Alice, "Bob"=Bob, "Carol"=Carol}
    //
    // Throw IllegalArgumentException if list or keyExtractor is null.
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, double salary) {}

    public static <T, K> Map<K, T> challenge4(List<T> list,
                                              Function<T, K> keyExtractor) {
        if (list == null)         throw new IllegalArgumentException("List cannot be null");
        if (keyExtractor == null) throw new IllegalArgumentException("KeyExtractor cannot be null");
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Generic repeat
    //
    // Create a List<T> containing value repeated n times.
    //
    // Input:  value="hello", n=3  → ["hello","hello","hello"]
    // Input:  value=42,      n=5  → [42,42,42,42,42]
    // Input:  value="x",    n=0   → []
    // Input:  value=null,   n=2   → [null, null]
    //
    // Throw IllegalArgumentException if n < 0.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge5(T value, int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 6–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Generic BoundedQueue<T>
    //
    // Implement a generic bounded queue with a maximum capacity:
    //   BoundedQueue<T>(int capacity)
    //   boolean offer(T item)  → add item if not full → return true
    //                            if full → return false (don't add!)
    //   T poll()               → remove and return front (null if empty)
    //   T peek()               → return front without removing (null if empty)
    //   boolean isEmpty()      → true if no elements
    //   boolean isFull()       → true if at capacity
    //   int size()             → current number of elements
    //
    // Input:  capacity=3
    //   offer(1) → true,  size=1
    //   offer(2) → true,  size=2
    //   offer(3) → true,  size=3, isFull=true
    //   offer(4) → false, size still=3 (rejected!)
    //   poll()   → 1, size=2
    //   poll()   → 2
    //   poll()   → 3
    //   poll()   → null (empty!)
    // ─────────────────────────────────────────────────────────────
    static class BoundedQueue<T> {
        private final int      capacity;
        private final Deque<T> items;

        BoundedQueue(int capacity) {
            if (capacity <= 0)
                throw new IllegalArgumentException("Capacity must be positive");
            this.capacity = capacity;
            this.items    = new ArrayDeque<>();
        }

        boolean offer(T item) { return false; }
        T poll()              { return null; }
        T peek()              { return null; }
        boolean isEmpty()     { return true; }
        boolean isFull()      { return false; }
        int size()            { return 0; }
    }

    public static <T> BoundedQueue<T> challenge6(int capacity) {
        return new BoundedQueue<>(capacity);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Generic transformMap
    //
    // Transform the VALUES of a Map<K, V> into a new Map<K, R>
    // using a value mapper Function<V, R>.
    // Keys remain the same, only values are transformed.
    //
    // Input:  map={"Alice"=95000.0, "Bob"=60000.0},
    //         mapper=salary -> salary * 1.1  (10% raise!)
    // Output: {"Alice"=104500.0, "Bob"=66000.0}
    //
    // Input:  map={"a"=1, "b"=2, "c"=3},
    //         mapper=n -> n * n
    // Output: {"a"=1, "b"=4, "c"=9}
    //
    // Throw IllegalArgumentException if map or mapper is null.
    // ─────────────────────────────────────────────────────────────
    public static <K, V, R> Map<K, R> challenge7(Map<K, V> map,
                                                 Function<V, R> mapper) {
        if (map == null)    throw new IllegalArgumentException("Map cannot be null");
        if (mapper == null) throw new IllegalArgumentException("Mapper cannot be null");
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Generic merge two sorted lists
    //
    // Merge two SORTED lists into a single SORTED list using a Comparator.
    // Both input lists are already sorted by the given comparator.
    // The result must also be sorted.
    //
    // Use the MERGE step of merge sort (don't just concat and sort!):
    // → two pointers, take the smaller element each step!
    //
    // Input:  list1=[1,3,5,7], list2=[2,4,6,8],
    //         comparator=Integer::compareTo
    // Output: [1,2,3,4,5,6,7,8]
    //
    // Input:  list1=["apple","cherry"], list2=["banana","date"],
    //         comparator=String::compareTo
    // Output: ["apple","banana","cherry","date"]
    //
    // Input:  list1=[1,2,3], list2=[]
    // Output: [1,2,3]
    //
    // Throw IllegalArgumentException if either list or comparator is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge8(List<T> list1, List<T> list2,
                                         Comparator<T> comparator) {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Lists cannot be null");
        if (comparator == null)
            throw new IllegalArgumentException("Comparator cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Generic Either<L, R>
    //
    // Implement Either<L, R> representing a value of ONE of two types:
    //   Either.left(L value)     → creates a Left Either
    //   Either.right(R value)    → creates a Right Either
    //   boolean isLeft()         → true if Left
    //   boolean isRight()        → true if Right
    //   L getLeft()              → return left value (null if Right)
    //   R getRight()             → return right value (null if Left)
    //   Either<L2,R> mapLeft(Function<L,L2> fn)  → transform Left value
    //                                               Right stays unchanged
    //   Either<L,R2> mapRight(Function<R,R2> fn) → transform Right value
    //                                               Left stays unchanged
    //   String toString()        → "Left(value)" or "Right(value)"
    //
    // Input:  Either.left("error")
    //   isLeft()  → true
    //   isRight() → false
    //   getLeft() → "error"
    //   mapLeft(String::length) → Either.left(5)
    //   mapRight(s -> s + "!")  → Either.left("error") (unchanged!)
    //
    // Input:  Either.right(42)
    //   isRight() → true
    //   getRight()→ 42
    //   mapRight(n -> n * 2) → Either.right(84)
    //   toString() → "Right(42)"
    // ─────────────────────────────────────────────────────────────
    static class Either<L, R> {
        private final L       leftValue;
        private final R       rightValue;
        private final boolean isLeft;

        private Either(L left, R right, boolean isLeft) {
            this.leftValue  = left;
            this.rightValue = right;
            this.isLeft     = isLeft;
        }

        static <L, R> Either<L, R> left(L value)  { return new Either<>(null, null, true); }
        static <L, R> Either<L, R> right(R value) { return new Either<>(null, null, false); }

        boolean isLeft()  { return false; }
        boolean isRight() { return false; }
        L getLeft()       { return null; }
        R getRight()      { return null; }

        <L2> Either<L2, R> mapLeft(Function<L, L2> fn) {
            return Either.left(null);
        }

        <R2> Either<L, R2> mapRight(Function<R, R2> fn) {
            return Either.right(null);
        }

        @Override
        public String toString() { return ""; }
    }

    public static <L, R> Either<L, R> challenge9left(L value) {
        return Either.left(value);
    }

    public static <L, R> Either<L, R> challenge9right(R value) {
        return Either.right(value);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Generic memoize function
    //
    // Wrap a Function<T, R> with a CACHE so each input is
    // computed only ONCE. Subsequent calls with the same input
    // return the cached result WITHOUT calling the function again!
    //
    // Return the memoized function as a Function<T, R>.
    //
    // Use a HashMap<T, R> as the internal cache.
    //
    // Input:  fn = n -> { callCount++; return n * n; }
    //   apply(5) → computes 25, callCount=1
    //   apply(5) → returns 25, callCount still=1 (cached!)
    //   apply(3) → computes 9,  callCount=2
    //   apply(3) → returns 9,   callCount still=2 (cached!)
    //   apply(5) → returns 25,  callCount still=2 (cached!)
    //
    // Throw IllegalArgumentException if fn is null.
    //
    // Key: Map.computeIfAbsent(key, fn) applies fn ONLY if key absent!
    // ─────────────────────────────────────────────────────────────
    public static <T, R> Function<T, R> challenge10(Function<T, R> fn) {
        if (fn == null) throw new IllegalArgumentException("Function cannot be null");
        return t -> null;
    }
}