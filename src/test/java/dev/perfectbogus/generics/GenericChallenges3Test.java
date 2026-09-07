package dev.perfectbogus.generics;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class GenericChallenges3Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Generic count matching elements
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void countEvenNumbers() {
            assertEquals(2,
                    GenericChallenges3.challenge1(List.of(1,2,3,4,5), n -> n % 2 == 0));
        }

        @Test
        void countLongStrings() {
            assertEquals(2,
                    GenericChallenges3.challenge1(
                            List.of("apple","banana","cherry"), s -> s.length() > 5));
        }

        @Test
        void noneMatch() {
            assertEquals(0,
                    GenericChallenges3.challenge1(List.of(1,2,3), n -> n > 10));
        }

        @Test
        void allMatch() {
            assertEquals(4,
                    GenericChallenges3.challenge1(List.of(2,4,6,8), n -> n % 2 == 0));
        }

        @Test
        void emptyList() {
            assertEquals(0,
                    GenericChallenges3.challenge1(List.<Integer>of(), n -> true));
        }

        @Test
        void nullList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge1(null, n -> true));
        }

        @Test
        void nullPredicate() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge1(List.of(1), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Generic rotate right
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicRotate() {
            assertEquals(List.of(4,5,1,2,3),
                    GenericChallenges3.challenge2(List.of(1,2,3,4,5), 2));
        }

        @Test
        void rotateByOne() {
            assertEquals(List.of("d","a","b","c"),
                    GenericChallenges3.challenge2(List.of("a","b","c","d"), 1));
        }

        @Test
        void rotateByZero() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges3.challenge2(List.of(1,2,3), 0));
        }

        @Test
        void rotateBySize() {
            assertEquals(List.of(1,2,3),
                    GenericChallenges3.challenge2(List.of(1,2,3), 3));
        }

        @Test
        void rotateLargerThanSize() {
            // k=5 % 3 = 2 → same as rotate by 2
            assertEquals(GenericChallenges3.challenge2(List.of(1,2,3), 2),
                    GenericChallenges3.challenge2(List.of(1,2,3), 5));
        }

        @Test
        void originalNotModified() {
            List<Integer> original = new ArrayList<>(List.of(1,2,3,4,5));
            GenericChallenges3.challenge2(original, 2);
            assertEquals(List.of(1,2,3,4,5), original);
        }

        @Test
        void singleElement() {
            assertEquals(List.of(42),
                    GenericChallenges3.challenge2(List.of(42), 5));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge2(null, 2));
        }

        @Test
        void negativeK() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge2(List.of(1,2,3), -1));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Generic chunk
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicChunk() {
            assertEquals(List.of(List.of(1,2,3), List.of(4,5,6), List.of(7)),
                    GenericChallenges3.challenge3(List.of(1,2,3,4,5,6,7), 3));
        }

        @Test
        void evenDivision() {
            assertEquals(List.of(List.of("a","b"), List.of("c","d")),
                    GenericChallenges3.challenge3(List.of("a","b","c","d"), 2));
        }

        @Test
        void chunkLargerThanList() {
            assertEquals(List.of(List.of(1,2,3)),
                    GenericChallenges3.challenge3(List.of(1,2,3), 10));
        }

        @Test
        void chunkSizeOne() {
            assertEquals(List.of(List.of(1), List.of(2), List.of(3)),
                    GenericChallenges3.challenge3(List.of(1,2,3), 1));
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges3.challenge3(List.<Integer>of(), 3).isEmpty());
        }

        @Test
        void chunkSizeEqualsListSize() {
            assertEquals(List.of(List.of(1,2,3)),
                    GenericChallenges3.challenge3(List.of(1,2,3), 3));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge3(null, 3));
        }

        @Test
        void invalidChunkSize() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge3(List.of(1,2,3), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Generic LRU Cache
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicPutAndGet() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(3);
            cache.put("a", 1);
            cache.put("b", 2);
            assertEquals(1, cache.get("a"));
            assertEquals(2, cache.get("b"));
        }

        @Test
        void evictsLeastRecentlyUsed() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(2);
            cache.put("a", 1);
            cache.put("b", 2);
            cache.get("a");       // ← "a" now most recent!
            cache.put("c", 3);   // ← evicts "b" (least recent)!
            assertNull(cache.get("b"));
            assertEquals(1, cache.get("a"));
            assertEquals(3, cache.get("c"));
        }

        @Test
        void evictsOldestWhenFull() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(2);
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3); // ← evicts "a" (oldest, never accessed)
            assertNull(cache.get("a"));
        }

        @Test
        void sizeTracked() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(3);
            assertEquals(0, cache.size());
            cache.put("a", 1);
            assertEquals(1, cache.size());
            cache.put("b", 2);
            assertEquals(2, cache.size());
        }

        @Test
        void containsKey() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(2);
            cache.put("a", 1);
            assertTrue(cache.containsKey("a"));
            assertFalse(cache.containsKey("b"));
        }

        @Test
        void getMissingKeyReturnsNull() {
            GenericChallenges3.LRUCache<String, Integer> cache =
                    GenericChallenges3.challenge4(2);
            assertNull(cache.get("missing"));
        }

        @Test
        void invalidCapacity() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge4(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Generic pipeline
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void integerPipeline() {
            assertEquals(169,
                    GenericChallenges3.challenge5(5,
                            List.of(n -> n * 2, n -> n + 3, n -> n * n)));
        }

        @Test
        void stringPipeline() {
            assertEquals("HELLO!",
                    GenericChallenges3.challenge5("hello",
                            List.of(String::toUpperCase, s -> s + "!")));
        }

        @Test
        void emptyPipeline() {
            assertEquals(42,
                    GenericChallenges3.challenge5(42, List.of()));
        }

        @Test
        void singleFunction() {
            assertEquals(10,
                    GenericChallenges3.challenge5(5, List.of(n -> n * 2)));
        }

        @Test
        void identityPipeline() {
            List<java.util.function.Function<Integer, Integer>> fns =
                    List.of(n -> n, n -> n, n -> n);
            assertEquals(7, GenericChallenges3.challenge5(7, fns));
        }

        @Test
        void nullFnsList() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge5(5, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Generic sliding window
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicWindow() {
            assertEquals(List.of(List.of(1,2,3), List.of(2,3,4), List.of(3,4,5)),
                    GenericChallenges3.challenge6(List.of(1,2,3,4,5), 3));
        }

        @Test
        void windowSizeTwo() {
            assertEquals(List.of(List.of("a","b"), List.of("b","c"), List.of("c","d")),
                    GenericChallenges3.challenge6(List.of("a","b","c","d"), 2));
        }

        @Test
        void windowEqualsListSize() {
            assertEquals(List.of(List.of(1,2,3)),
                    GenericChallenges3.challenge6(List.of(1,2,3), 3));
        }

        @Test
        void windowLargerThanList() {
            assertTrue(GenericChallenges3.challenge6(List.of(1,2), 3).isEmpty());
        }

        @Test
        void windowSizeOne() {
            assertEquals(List.of(List.of(1), List.of(2), List.of(3)),
                    GenericChallenges3.challenge6(List.of(1,2,3), 1));
        }

        @Test
        void emptyList() {
            assertTrue(GenericChallenges3.challenge6(List.<Integer>of(), 3).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge6(null, 3));
        }

        @Test
        void invalidWindowSize() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge6(List.of(1,2,3), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Generic MultiMap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void addAndGetAll() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            mm.add("a", 1);
            mm.add("a", 2);
            mm.add("b", 3);

            assertEquals(List.of(1,2), mm.getAll("a"));
            assertEquals(List.of(3),   mm.getAll("b"));
        }

        @Test
        void getMissingKeyReturnsEmpty() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            assertEquals(List.of(), mm.getAll("missing"));
        }

        @Test
        void removeExisting() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            mm.add("a", 1);
            mm.add("a", 2);

            assertTrue(mm.remove("a", 1));
            assertEquals(List.of(2), mm.getAll("a"));
        }

        @Test
        void removeMissing() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            mm.add("a", 1);

            assertFalse(mm.remove("a", 99));
        }

        @Test
        void containsKey() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            mm.add("a", 1);

            assertTrue(mm.containsKey("a"));
            assertFalse(mm.containsKey("b"));
        }

        @Test
        void size() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            assertEquals(0, mm.size());
            mm.add("a", 1);
            mm.add("a", 2);
            mm.add("b", 3);
            assertEquals(3, mm.size());
            mm.remove("a", 1);
            assertEquals(2, mm.size());
        }

        @Test
        void duplicateValues() {
            GenericChallenges3.MultiMap<String, Integer> mm =
                    GenericChallenges3.challenge7();
            mm.add("a", 1);
            mm.add("a", 1); // ← duplicate!
            assertEquals(2, mm.getAll("a").size());
            assertEquals(2, mm.size());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Generic BST
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void insertAndContains() {
            GenericChallenges3.BST<Integer> bst = GenericChallenges3.challenge8();
            bst.insert(5);
            bst.insert(3);
            bst.insert(7);

            assertTrue(bst.contains(5));
            assertTrue(bst.contains(3));
            assertTrue(bst.contains(7));
            assertFalse(bst.contains(1));
        }

        @Test
        void inOrderIsSorted() {
            GenericChallenges3.BST<Integer> bst = GenericChallenges3.challenge8();
            bst.insert(5);
            bst.insert(3);
            bst.insert(7);
            bst.insert(1);
            bst.insert(4);
            bst.insert(6);
            bst.insert(8);

            assertEquals(List.of(1,3,4,5,6,7,8), bst.inOrder());
        }

        @Test
        void duplicatesIgnored() {
            GenericChallenges3.BST<Integer> bst = GenericChallenges3.challenge8();
            bst.insert(5);
            bst.insert(5);
            bst.insert(5);

            assertEquals(List.of(5), bst.inOrder());
        }

        @Test
        void stringBST() {
            GenericChallenges3.BST<String> bst = GenericChallenges3.challenge8();
            bst.insert("banana");
            bst.insert("apple");
            bst.insert("cherry");

            assertEquals(List.of("apple","banana","cherry"), bst.inOrder());
            assertTrue(bst.contains("apple"));
            assertFalse(bst.contains("mango"));
        }

        @Test
        void emptyBSTInOrder() {
            GenericChallenges3.BST<Integer> bst = GenericChallenges3.challenge8();
            assertTrue(bst.inOrder().isEmpty());
        }

        @Test
        void singleElement() {
            GenericChallenges3.BST<Integer> bst = GenericChallenges3.challenge8();
            bst.insert(42);
            assertEquals(List.of(42), bst.inOrder());
            assertTrue(bst.contains(42));
            assertFalse(bst.contains(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Generic Lazy<T>
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void computedOnFirstGet() {
            AtomicInteger count = new AtomicInteger(0);
            GenericChallenges3.Lazy<Integer> lazy =
                    GenericChallenges3.challenge9(() -> {
                        count.incrementAndGet();
                        return 42;
                    });

            assertEquals(42, lazy.get());
            assertEquals(1,  count.get());
        }

        @Test
        void cachedOnSubsequentCalls() {
            AtomicInteger count = new AtomicInteger(0);
            GenericChallenges3.Lazy<Integer> lazy =
                    GenericChallenges3.challenge9(() -> {
                        count.incrementAndGet();
                        return 42;
                    });

            lazy.get();
            lazy.get();
            lazy.get();
            assertEquals(1, count.get()); // ← computed ONCE only!
        }

        @Test
        void isComputedBeforeGet() {
            GenericChallenges3.Lazy<Integer> lazy =
                    GenericChallenges3.challenge9(() -> 42);
            assertFalse(lazy.isComputed()); // ← not yet!
        }

        @Test
        void isComputedAfterGet() {
            GenericChallenges3.Lazy<Integer> lazy =
                    GenericChallenges3.challenge9(() -> 42);
            lazy.get();
            assertTrue(lazy.isComputed()); // ← now computed!
        }

        @Test
        void differentTypes() {
            GenericChallenges3.Lazy<String> lazy =
                    GenericChallenges3.challenge9(() -> "hello world");
            assertEquals("hello world", lazy.get());
            assertTrue(lazy.isComputed());
        }

        @Test
        void nullSupplier() {
            assertThrows(IllegalArgumentException.class,
                    () -> GenericChallenges3.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Generic Graph<T> with BFS, DFS, cycle detection
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void bfsLinearGraph() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);
            g.addEdge(3, 4);

            assertEquals(List.of(1,2,3,4), g.bfs(1));
        }

        @Test
        void bfsBranchingGraph() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(1, 3);
            g.addEdge(2, 4);

            List<Integer> result = g.bfs(1);
            assertEquals(1, result.get(0)); // ← starts at 1
            assertTrue(result.contains(2));
            assertTrue(result.contains(3));
            assertTrue(result.contains(4));
            // 2 and 3 must come before 4 (BFS level order!)
            assertTrue(result.indexOf(2) < result.indexOf(4));
            assertTrue(result.indexOf(3) < result.indexOf(4));
        }

        @Test
        void dfsLinearGraph() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);
            g.addEdge(3, 4);

            assertEquals(List.of(1,2,3,4), g.dfs(1));
        }

        @Test
        void isReachableTrue() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);
            g.addEdge(3, 4);

            assertTrue(g.isReachable(1, 4));
            assertTrue(g.isReachable(1, 2));
        }

        @Test
        void isReachableFalseDirected() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);

            assertFalse(g.isReachable(3, 1)); // ← directed! can't go back!
        }

        @Test
        void noCycle() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);
            g.addEdge(3, 4);

            assertFalse(g.hasCycle());
        }

        @Test
        void hasCycle() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 2);
            g.addEdge(2, 3);
            g.addEdge(3, 1); // ← cycle! 1→2→3→1

            assertTrue(g.hasCycle());
        }

        @Test
        void selfLoop() {
            GenericChallenges3.Graph<Integer> g = GenericChallenges3.challenge10();
            g.addEdge(1, 1); // ← self loop!

            assertTrue(g.hasCycle());
        }

        @Test
        void stringGraph() {
            GenericChallenges3.Graph<String> g = GenericChallenges3.challenge10();
            g.addEdge("A", "B");
            g.addEdge("B", "C");

            assertTrue(g.isReachable("A", "C"));
            assertFalse(g.isReachable("C", "A"));
            assertFalse(g.hasCycle());
        }
    }
}