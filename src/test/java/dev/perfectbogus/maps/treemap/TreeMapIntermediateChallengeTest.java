package dev.perfectbogus.maps.treemap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: buildCaseInsensitiveMap
    // ==========================================================
    @Nested
    class BuildCaseInsensitiveMapTests {

        @Test
        void testNoCollisionsKeepsAllEntries() {
            Map<String, Integer> source = Map.of("Banana", 2, "apple", 1, "Cherry", 3);
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.buildCaseInsensitiveMap(source);
            assertEquals(3, result.size());
        }

        @Test
        void testOrderingIsAlphabeticalIgnoringCase() {
            Map<String, Integer> source = new LinkedHashMap<>();
            source.put("banana", 2);
            source.put("Apple", 1);
            source.put("cherry", 3);
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.buildCaseInsensitiveMap(source);
            assertEquals(List.of("Apple", "banana", "cherry"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testKeysDifferingOnlyByCaseCollapse() {
            Map<String, Integer> source = new LinkedHashMap<>();
            source.put("Apple", 1);
            source.put("apple", 2);
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.buildCaseInsensitiveMap(source);
            assertEquals(1, result.size());
        }

        @Test
        void testLaterValueWinsOnCollision() {
            Map<String, Integer> source = new LinkedHashMap<>();
            source.put("Apple", 1);
            source.put("apple", 2);
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.buildCaseInsensitiveMap(source);
            assertEquals(2, result.get("APPLE"));
        }

        @Test
        void testLookupIsCaseInsensitive() {
            Map<String, Integer> source = Map.of("Java", 100);
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.buildCaseInsensitiveMap(source);
            assertEquals(100, result.get("java"));
            assertEquals(100, result.get("JAVA"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: drainDescending
    // ==========================================================
    @Nested
    class DrainDescendingTests {

        @Test
        void testReturnsKeysInDescendingOrder() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            assertEquals(List.of(30, 20, 10), TreeMapIntermediateChallenge.drainDescending(map));
        }

        @Test
        void testMapIsEmptyAfterDraining() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(1, "a");
            map.put(2, "b");
            TreeMapIntermediateChallenge.drainDescending(map);
            assertTrue(map.isEmpty());
        }

        @Test
        void testSingleEntryMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(5, "five");
            assertEquals(List.of(5), TreeMapIntermediateChallenge.drainDescending(map));
        }

        @Test
        void testEmptyMapReturnsEmptyList() {
            TreeMap<Integer, String> map = new TreeMap<>();
            assertEquals(List.of(), TreeMapIntermediateChallenge.drainDescending(map));
        }
    }

    // ==========================================================
    // CHALLENGE 3: keysDescendingNonDestructive
    // ==========================================================
    @Nested
    class KeysDescendingNonDestructiveTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            return map;
        }

        @Test
        void testReturnsDescendingOrder() {
            assertEquals(List.of(30, 20, 10), TreeMapIntermediateChallenge.keysDescendingNonDestructive(sampleMap()));
        }

        @Test
        void testMapIsUnmodified() {
            TreeMap<Integer, String> map = sampleMap();
            TreeMapIntermediateChallenge.keysDescendingNonDestructive(map);
            assertEquals(3, map.size());
        }

        @Test
        void testEmptyMapReturnsEmptyList() {
            assertEquals(List.of(), TreeMapIntermediateChallenge.keysDescendingNonDestructive(new TreeMap<>()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: ceilingEntry
    // ==========================================================
    @Nested
    class CeilingEntryTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            return map;
        }

        @Test
        void testExactMatchReturnsSameEntry() {
            Map.Entry<Integer, String> entry = TreeMapIntermediateChallenge.ceilingEntry(sampleMap(), 20);
            assertEquals(20, entry.getKey());
            assertEquals("twenty", entry.getValue());
        }

        @Test
        void testNoExactMatchReturnsNextHigherEntry() {
            Map.Entry<Integer, String> entry = TreeMapIntermediateChallenge.ceilingEntry(sampleMap(), 25);
            assertEquals(30, entry.getKey());
            assertEquals("thirty", entry.getValue());
        }

        @Test
        void testNoCeilingReturnsNull() {
            assertNull(TreeMapIntermediateChallenge.ceilingEntry(sampleMap(), 100));
        }
    }

    // ==========================================================
    // CHALLENGE 5: floorEntry
    // ==========================================================
    @Nested
    class FloorEntryTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            return map;
        }

        @Test
        void testExactMatchReturnsSameEntry() {
            Map.Entry<Integer, String> entry = TreeMapIntermediateChallenge.floorEntry(sampleMap(), 20);
            assertEquals(20, entry.getKey());
            assertEquals("twenty", entry.getValue());
        }

        @Test
        void testNoExactMatchReturnsNextLowerEntry() {
            Map.Entry<Integer, String> entry = TreeMapIntermediateChallenge.floorEntry(sampleMap(), 25);
            assertEquals(20, entry.getKey());
            assertEquals("twenty", entry.getValue());
        }

        @Test
        void testNoFloorReturnsNull() {
            assertNull(TreeMapIntermediateChallenge.floorEntry(sampleMap(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 6: headMapInclusive
    // ==========================================================
    @Nested
    class HeadMapInclusiveTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            return map;
        }

        @Test
        void testBoundaryKeyIsIncluded() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.headMapInclusive(sampleMap(), 20);
            assertEquals(List.of(10, 20), new ArrayList<>(result.keySet()));
        }

        @Test
        void testKeyAboveBoundaryExcluded() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.headMapInclusive(sampleMap(), 20);
            assertFalse(result.containsKey(30));
        }

        @Test
        void testBoundaryBelowAllKeysReturnsEmpty() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.headMapInclusive(sampleMap(), 5);
            assertTrue(result.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 7: tailMapExclusive
    // ==========================================================
    @Nested
    class TailMapExclusiveTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            return map;
        }

        @Test
        void testBoundaryKeyIsExcluded() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.tailMapExclusive(sampleMap(), 20);
            assertEquals(List.of(30), new ArrayList<>(result.keySet()));
        }

        @Test
        void testKeyBelowBoundaryExcluded() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.tailMapExclusive(sampleMap(), 20);
            assertFalse(result.containsKey(10));
        }

        @Test
        void testBoundaryAboveAllKeysReturnsEmpty() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.tailMapExclusive(sampleMap(), 30);
            assertTrue(result.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 8: mergeSorted
    // ==========================================================
    @Nested
    class MergeSortedTests {

        @Test
        void testDisjointKeysAreAllPresent() {
            TreeMap<String, Integer> map1 = new TreeMap<>(Map.of("apple", 1, "banana", 2));
            TreeMap<String, Integer> map2 = new TreeMap<>(Map.of("cherry", 3));
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.mergeSorted(map1, map2);
            assertEquals(3, result.size());
        }

        @Test
        void testOverlappingKeysAreSummed() {
            TreeMap<String, Integer> map1 = new TreeMap<>(Map.of("apple", 5));
            TreeMap<String, Integer> map2 = new TreeMap<>(Map.of("apple", 10));
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.mergeSorted(map1, map2);
            assertEquals(15, result.get("apple"));
        }

        @Test
        void testResultIsSorted() {
            TreeMap<String, Integer> map1 = new TreeMap<>(Map.of("cherry", 1));
            TreeMap<String, Integer> map2 = new TreeMap<>(Map.of("apple", 2, "banana", 3));
            TreeMap<String, Integer> result = TreeMapIntermediateChallenge.mergeSorted(map1, map2);
            assertEquals(List.of("apple", "banana", "cherry"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testOriginalMapsUnmodified() {
            TreeMap<String, Integer> map1 = new TreeMap<>(Map.of("apple", 1));
            TreeMap<String, Integer> map2 = new TreeMap<>(Map.of("apple", 10));
            TreeMapIntermediateChallenge.mergeSorted(map1, map2);
            assertEquals(1, map1.get("apple"));
            assertEquals(10, map2.get("apple"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: buildWithReverseOrder
    // ==========================================================
    @Nested
    class BuildWithReverseOrderTests {

        @Test
        void testFirstKeyIsLargest() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.buildWithReverseOrder(
                    List.of(10, 30, 20), List.of("ten", "thirty", "twenty")
            );
            assertEquals(30, result.firstKey());
        }

        @Test
        void testLastKeyIsSmallest() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.buildWithReverseOrder(
                    List.of(10, 30, 20), List.of("ten", "thirty", "twenty")
            );
            assertEquals(10, result.lastKey());
        }

        @Test
        void testIterationOrderIsDescending() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.buildWithReverseOrder(
                    List.of(1, 3, 2), List.of("one", "three", "two")
            );
            assertEquals(List.of(3, 2, 1), new ArrayList<>(result.keySet()));
        }

        @Test
        void testValuesMappedCorrectly() {
            TreeMap<Integer, String> result = TreeMapIntermediateChallenge.buildWithReverseOrder(
                    List.of(1, 2), List.of("one", "two")
            );
            assertEquals("one", result.get(1));
            assertEquals("two", result.get(2));
        }
    }

    // ==========================================================
    // CHALLENGE 10: countInRange
    // ==========================================================
    @Nested
    class CountInRangeTests {

        private TreeMap<Integer, String> sampleMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "ten");
            map.put(20, "twenty");
            map.put(30, "thirty");
            map.put(40, "forty");
            return map;
        }

        @Test
        void testBothBoundariesIncluded() {
            assertEquals(3, TreeMapIntermediateChallenge.countInRange(sampleMap(), 10, 30));
        }

        @Test
        void testExactSingleKeyRange() {
            assertEquals(1, TreeMapIntermediateChallenge.countInRange(sampleMap(), 20, 20));
        }

        @Test
        void testRangeWithNoMatchingKeys() {
            assertEquals(0, TreeMapIntermediateChallenge.countInRange(sampleMap(), 21, 29));
        }

        @Test
        void testRangeCoveringEntireMap() {
            assertEquals(4, TreeMapIntermediateChallenge.countInRange(sampleMap(), 0, 100));
        }
    }
}