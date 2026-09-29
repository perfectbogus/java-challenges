package dev.perfectbogus.linkedhashmap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LinkedHashMapIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: keysInIterationOrder
    // ==========================================================
    @Nested
    class KeysInIterationOrderTests {

        @Test
        void testMatchesInsertionOrderNotAlphabeticalOrder() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("zeta", 1);
            map.put("alpha", 2);
            map.put("mike", 3);
            assertEquals(List.of("zeta", "alpha", "mike"),
                    LinkedHashMapIntermediateChallenge.keysInIterationOrder(map));
        }
    }

    // ==========================================================
    // CHALLENGE 2: countPreservingFirstSeenOrder
    // ==========================================================
    @Nested
    class CountPreservingFirstSeenOrderTests {

        @Test
        void testOrderIsFirstSeenNotAlphabeticalOrCount() {
            List<String> words = List.of("b", "a", "b", "c", "a", "a");
            LinkedHashMap<String, Integer> result = LinkedHashMapIntermediateChallenge.countPreservingFirstSeenOrder(words);

            assertEquals(List.of("b", "a", "c"), new ArrayList<>(result.keySet()));
            assertEquals(2, result.get("b"));
            assertEquals(3, result.get("a"));
            assertEquals(1, result.get("c"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: keyAtPosition
    // ==========================================================
    @Nested
    class KeyAtPositionTests {

        @Test
        void testReturnsKeyAtGivenInsertionIndex() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("x", 1);
            map.put("y", 2);
            map.put("z", 3);
            assertEquals("x", LinkedHashMapIntermediateChallenge.keyAtPosition(map, 0));
            assertEquals("z", LinkedHashMapIntermediateChallenge.keyAtPosition(map, 2));
        }

        @Test
        void testOutOfBoundsThrows() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("x", 1);
            map.put("y", 2);
            map.put("z", 3);
            assertThrows(IndexOutOfBoundsException.class,
                    () -> LinkedHashMapIntermediateChallenge.keyAtPosition(map, -1));
            assertThrows(IndexOutOfBoundsException.class,
                    () -> LinkedHashMapIntermediateChallenge.keyAtPosition(map, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 4: newAccessOrderedMap
    // ==========================================================
    @Nested
    class NewAccessOrderedMapTests {

        @Test
        void testGetMovesAccessedKeyToEnd() {
            LinkedHashMap<String, Integer> map = LinkedHashMapIntermediateChallenge.newAccessOrderedMap();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 3);

            map.get("a");

            assertEquals(List.of("b", "c", "a"), new ArrayList<>(map.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: keysAfterAccessSequence
    // ==========================================================
    @Nested
    class KeysAfterAccessSequenceTests {

        @Test
        void testEachGetMovesItsKeyToEndInOrder() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 3);
            map.put("d", 4);

            List<String> result = LinkedHashMapIntermediateChallenge.keysAfterAccessSequence(
                    map, List.of("b", "a", "nonexistent"));

            assertEquals(List.of("c", "d", "b", "a"), result);
        }
    }

    // ==========================================================
    // CHALLENGE 6: touchKey
    // ==========================================================
    @Nested
    class TouchKeyTests {

        @Test
        void testMovesKeyToEndPreservingItsValue() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 3);

            LinkedHashMapIntermediateChallenge.touchKey(map, "a");

            assertEquals(List.of("b", "c", "a"), new ArrayList<>(map.keySet()));
            assertEquals(1, map.get("a"));
        }

        @Test
        void testMissingKeyIsNoop() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("a", 1);
            map.put("b", 2);

            LinkedHashMapIntermediateChallenge.touchKey(map, "z");

            assertEquals(List.of("a", "b"), new ArrayList<>(map.keySet()));
            assertEquals(2, map.size());
        }
    }

    // ==========================================================
    // CHALLENGE 7: LruCache
    // ==========================================================
    @Nested
    class LruCacheTests {

        @Test
        void testEvictsLeastRecentlyUsedNotOldestInserted() {
            LinkedHashMapIntermediateChallenge.LruCache<String, Integer> cache =
                    new LinkedHashMapIntermediateChallenge.LruCache<>(3);
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3);

            cache.get("a"); // "a" is now the most recently used

            cache.put("d", 4); // cache is full: should evict "b", the least recently used

            assertFalse(cache.containsKey("b"));
            assertEquals(3, cache.size());
            assertEquals(List.of("c", "a", "d"), new ArrayList<>(cache.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: FifoCache
    // ==========================================================
    @Nested
    class FifoCacheTests {

        @Test
        void testEvictsOldestInsertedRegardlessOfReads() {
            LinkedHashMapIntermediateChallenge.FifoCache<String, Integer> cache =
                    new LinkedHashMapIntermediateChallenge.FifoCache<>(3);
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3);

            cache.get("a"); // reading "a" must NOT protect it from eviction

            cache.put("d", 4); // cache is full: should evict "a", the oldest inserted

            assertFalse(cache.containsKey("a"));
            assertEquals(3, cache.size());
            assertEquals(List.of("b", "c", "d"), new ArrayList<>(cache.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 9: mergeTwoMapsPreservingOrder
    // ==========================================================
    @Nested
    class MergeTwoMapsPreservingOrderTests {

        @Test
        void testSumsOverlappingKeysAndOrdersFirstThenSecond() {
            LinkedHashMap<String, Integer> first = new LinkedHashMap<>();
            first.put("x", 1);
            first.put("y", 2);

            LinkedHashMap<String, Integer> second = new LinkedHashMap<>();
            second.put("y", 10);
            second.put("z", 3);

            LinkedHashMap<String, Integer> result = LinkedHashMapIntermediateChallenge.mergeTwoMapsPreservingOrder(first, second);

            assertEquals(List.of("x", "y", "z"), new ArrayList<>(result.keySet()));
            assertEquals(1, result.get("x"));
            assertEquals(12, result.get("y"));
            assertEquals(3, result.get("z"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: topNByCountStableOrder
    // ==========================================================
    @Nested
    class TopNByCountStableOrderTests {

        @Test
        void testOrdersByCountDescendingWithFirstSeenTiebreak() {
            List<String> items = List.of("apple", "banana", "apple", "cherry", "banana", "apple", "date");

            LinkedHashMap<String, Integer> top3 = LinkedHashMapIntermediateChallenge.topNByCountStableOrder(items, 3);

            assertEquals(List.of("apple", "banana", "cherry"), new ArrayList<>(top3.keySet()));
            assertEquals(3, top3.get("apple"));
            assertEquals(2, top3.get("banana"));
            assertEquals(1, top3.get("cherry"));
        }

        @Test
        void testNLargerThanDistinctCountReturnsAll() {
            List<String> items = List.of("apple", "banana", "apple", "cherry", "banana", "apple", "date");

            LinkedHashMap<String, Integer> all = LinkedHashMapIntermediateChallenge.topNByCountStableOrder(items, 10);

            assertEquals(List.of("apple", "banana", "cherry", "date"), new ArrayList<>(all.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 11: reverseInsertionOrder
    // ==========================================================
    @Nested
    class ReverseInsertionOrderTests {

        @Test
        void testReversesIterationOrderKeepingValues() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 3);

            LinkedHashMap<String, Integer> result = LinkedHashMapIntermediateChallenge.reverseInsertionOrder(map);

            assertEquals(List.of("c", "b", "a"), new ArrayList<>(result.keySet()));
            assertEquals(3, result.get("c"));
            assertEquals(2, result.get("b"));
            assertEquals(1, result.get("a"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: simulateLruAccessPattern
    // ==========================================================
    @Nested
    class SimulateLruAccessPatternTests {

        @Test
        void testReturnsResidentKeysMostRecentlyUsedFirst() {
            List<String> accessSequence = List.of("a", "b", "c", "a", "d", "b");

            List<String> result = LinkedHashMapIntermediateChallenge.simulateLruAccessPattern(accessSequence, 3);

            assertEquals(List.of("b", "d", "a"), result);
        }
    }
}