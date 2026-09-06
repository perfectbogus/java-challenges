package dev.perfectbogus.generics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class GenericChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — Generic method: swap two list elements
    //
    // Swap the elements at index i and index j in the list.
    // Return a NEW list with the elements swapped.
    // Original list must NOT be modified.
    //
    // Input:  list=["a","b","c","d"], i=0, j=3 → ["d","b","c","a"]
    // Input:  list=[1,2,3,4,5], i=1, j=3       → [1,4,3,2,5]
    //
    // Throw IllegalArgumentException if:
    // → list is null
    // → i or j out of bounds
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge1(List<T> list, int i, int j) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        if (i < 0 || i >= list.size() || j < 0 || j >= list.size())
            throw new IllegalArgumentException("Index out of bounds");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — Generic method with bound: find max element
    //
    // Find the MAXIMUM element in a list using Comparable bound.
    // T must extend Comparable<T> so we can compare elements!
    //
    // Input:  [3, 1, 4, 1, 5, 9, 2, 6]          → 9
    // Input:  ["banana","apple","cherry"]          → "cherry"
    // Input:  [3.14, 2.71, 1.41]                  → 3.14
    //
    // Throw IllegalArgumentException if:
    // → list is null
    // → list is empty
    // ─────────────────────────────────────────────────────────────
    public static <T extends Comparable<T>> T challenge2(List<T> list) {
        if (list == null)    throw new IllegalArgumentException("List cannot be null");
        if (list.isEmpty()) throw new IllegalArgumentException("List cannot be empty");
        return list.get(0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — Generic method: filter list by predicate
    //
    // Filter a list keeping only elements that match the predicate.
    // Return a NEW List<T> with matching elements in original order.
    //
    // Input:  list=[1,2,3,4,5], predicate=n -> n % 2 == 0 → [2,4]
    // Input:  list=["hi","hello","hey"], predicate=s -> s.length()>2
    //         → ["hello","hey"]
    // Input:  list=[1,2,3], predicate=n -> n > 10 → []
    //
    // Throw IllegalArgumentException if list or predicate is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge3(List<T> list, Predicate<T> predicate) {
        if (list == null)      throw new IllegalArgumentException("List cannot be null");
        if (predicate == null) throw new IllegalArgumentException("Predicate cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — Generic Pair<A, B> class
    //
    // Implement a generic Pair class with:
    //   Pair<A, B>(A first, B second)
    //   A first()                       → returns first element
    //   B second()                      → returns second element
    //   Pair<B, A> swap()               → returns NEW Pair with elements swapped!
    //   String toString()               → "(first, second)"
    //
    // Input:  new Pair<>("Alice", 95000)
    //   first()   → "Alice"
    //   second()  → 95000
    //   swap()    → Pair<Integer,String>(95000, "Alice")
    //   toString()→ "(Alice, 95000)"
    // ─────────────────────────────────────────────────────────────
    static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first  = first;
            this.second = second;
        }

        A first()  { return null; }
        B second() { return null; }

        Pair<B, A> swap() { return new Pair<>(null, null); }

        @Override
        public String toString() { return ""; }
    }

    public static <A, B> Pair<A, B> challenge4(A first, B second) {
        return new Pair<>(first, second);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Wildcard: sum any list of Numbers
    //
    // Use wildcard "? extends Number" to accept ANY numeric list.
    // Sum all elements using Number.doubleValue().
    //
    // Input:  List<Integer> [1,2,3,4,5]   → 15.0
    // Input:  List<Double>  [1.5,2.5,3.0] → 7.0
    // Input:  List<Long>    [100L,200L]    → 300.0
    // Input:  empty list                   → 0.0
    //
    // Throw IllegalArgumentException if list is null.
    //
    // Key: method signature must use "? extends Number"!
    // ─────────────────────────────────────────────────────────────
    public static double challenge5(List<? extends Number> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        return 0.0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Generic Stack<T> implementation
    //
    // Implement a generic Stack using ArrayList internally:
    //   void push(T item)     → add to top of stack
    //   T pop()               → remove and return top (throws if empty!)
    //   T peek()              → return top WITHOUT removing (throws if empty!)
    //   boolean isEmpty()     → true if no elements
    //   int size()            → number of elements
    //
    // Throw NoSuchElementException with "Stack is empty" if pop/peek on empty!
    //
    // Input:  push(1), push(2), push(3)
    //   peek() → 3     (3 still on stack)
    //   pop()  → 3     (3 removed)
    //   pop()  → 2
    //   size() → 1
    //   pop()  → 1
    //   isEmpty() → true
    //   pop() → throws NoSuchElementException("Stack is empty")
    // ─────────────────────────────────────────────────────────────
    static class Stack<T> {
        private final List<T> items = new ArrayList<>();

        void push(T item) { }

        T pop() { return null; }

        T peek() { return null; }

        boolean isEmpty() { return true; }

        int size() { return 0; }
    }

    public static <T> Stack<T> challenge6() {
        return new Stack<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — Generic zip: combine two lists into pairs
    //
    // Zip two lists into a List<Pair<A,B>> by pairing elements
    // at the same index together.
    // If lists have different sizes → zip up to SHORTER list length.
    //
    // Input:  list1=["a","b","c"], list2=[1,2,3]
    //         → [("a",1), ("b",2), ("c",3)]
    //
    // Input:  list1=["a","b","c"], list2=[1,2]
    //         → [("a",1), ("b",2)]   ← shorter list wins!
    //
    // Input:  list1=[], list2=[1,2,3]
    //         → []
    //
    // Throw IllegalArgumentException if either list is null.
    // ─────────────────────────────────────────────────────────────
    public static <A, B> List<Pair<A, B>> challenge7(List<A> list1, List<B> list2) {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Lists cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Generic Result<T> type
    //
    // Implement a Result<T> class representing success or failure:
    //   static Result<T> ok(T value)     → success with value
    //   static Result<T> error(String msg)→ failure with message
    //   boolean isSuccess()              → true if ok
    //   T getValue()                     → return value (null if error)
    //   String getError()                → return error msg (null if ok)
    //   Result<R> map(Function<T,R> fn)  → transform value if ok,
    //                                       propagate error if not!
    //
    // Input:  Result.ok(42)
    //   isSuccess() → true
    //   getValue()  → 42
    //   getError()  → null
    //   map(n -> n * 2) → Result.ok(84)
    //
    // Input:  Result.error("not found")
    //   isSuccess() → false
    //   getValue()  → null
    //   getError()  → "not found"
    //   map(n -> n * 2) → Result.error("not found") ← propagated!
    // ─────────────────────────────────────────────────────────────
    static class Result<T> {
        private final T      value;
        private final String error;
        private final boolean success;

        private Result(T value, String error, boolean success) {
            this.value   = value;
            this.error   = error;
            this.success = success;
        }

        static <T> Result<T> ok(T value)        { return new Result<>(null, null, false); }
        static <T> Result<T> error(String msg)   { return new Result<>(null, null, false); }

        boolean isSuccess() { return false; }
        T getValue()        { return null; }
        String getError()   { return null; }

        <R> Result<R> map(Function<T, R> fn) { return Result.error(""); }
    }

    public static <T> Result<T> challenge8ok(T value)       { return Result.ok(value); }
    public static <T> Result<T> challenge8error(String msg) { return Result.error(msg); }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Generic method: flatten nested lists
    //
    // Flatten a List<List<T>> into a single List<T>
    // preserving the order of elements (outer list order first,
    // then inner list order).
    //
    // Input:  [[1,2,3],[4,5],[6]] → [1,2,3,4,5,6]
    // Input:  [["a","b"],["c"]]   → ["a","b","c"]
    // Input:  [[],[1],[],[2,3]]   → [1,2,3]  ← empty sublists skipped!
    // Input:  []                  → []
    //
    // Throw IllegalArgumentException if the outer list is null.
    // ─────────────────────────────────────────────────────────────
    public static <T> List<T> challenge9(List<List<T>> lists) {
        if (lists == null) throw new IllegalArgumentException("List cannot be null");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Generic method: groupBy using a classifier
    //
    // Group elements of a list into a Map<K, List<T>> using a
    // classifier Function<T, K> to determine the key for each element.
    //
    // Input:  list=["apple","banana","avocado","blueberry","cherry"]
    //         classifier=s -> s.charAt(0)  (first character)
    // Output: {'a'=["apple","avocado"], 'b'=["banana","blueberry"],
    //          'c'=["cherry"]}
    //
    // Input:  list=[1,2,3,4,5,6]
    //         classifier=n -> n % 2 == 0 ? "even" : "odd"
    // Output: {"even"=[2,4,6], "odd"=[1,3,5]}
    //
    // Throw IllegalArgumentException if list or classifier is null.
    // ─────────────────────────────────────────────────────────────
    public static <T, K> Map<K, List<T>> challenge10(List<T> list,
                                                     Function<T, K> classifier) {
        if (list == null)       throw new IllegalArgumentException("List cannot be null");
        if (classifier == null) throw new IllegalArgumentException("Classifier cannot be null");
        return new HashMap<>();
    }
}