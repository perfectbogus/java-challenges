package dev.perfectbogus.maps.treemap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapBeginnerChallengeTest {

    private TreeMap<Integer, String> sampleMap() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "ten");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        return map;
    }

    // ==========================================================
    // CHALLENGE 1: smallestKey
    // ==========================================================
    @Nested
    class SmallestKeyTests {

        @Test
        void testReturnsSmallest() {
            assertEquals(10, TreeMapBeginnerChallenge.smallestKey(sampleMap()));
        }

        @Test
        void testSingleEntryMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(5, "five");
            assertEquals(5, TreeMapBeginnerChallenge.smallestKey(map));
        }

        @Test
        void testUnaffectedByInsertionOrder() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(100, "a");
            map.put(1, "b");
            map.put(50, "c");
            assertEquals(1, TreeMapBeginnerChallenge.smallestKey(map));
        }
    }

    // ==========================================================
    // CHALLENGE 2: largestKey
    // ==========================================================
    @Nested
    class LargestKeyTests {

        @Test
        void testReturnsLargest() {
            assertEquals(40, TreeMapBeginnerChallenge.largestKey(sampleMap()));
        }

        @Test
        void testSingleEntryMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(5, "five");
            assertEquals(5, TreeMapBeginnerChallenge.largestKey(map));
        }

        @Test
        void testUnaffectedByInsertionOrder() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(100, "a");
            map.put(1, "b");
            map.put(50, "c");
            assertEquals(100, TreeMapBeginnerChallenge.largestKey(map));
        }
    }

    // ==========================================================
    // CHALLENGE 3: ceiling
    // ==========================================================
    @Nested
    class CeilingTests {

        @Test
        void testExactMatchReturnsSameKey() {
            assertEquals(20, TreeMapBeginnerChallenge.ceiling(sampleMap(), 20));
        }

        @Test
        void testNoExactMatchReturnsNextHigher() {
            assertEquals(30, TreeMapBeginnerChallenge.ceiling(sampleMap(), 25));
        }

        @Test
        void testNoCeilingReturnsNull() {
            assertNull(TreeMapBeginnerChallenge.ceiling(sampleMap(), 100));
        }
    }

    // ==========================================================
    // CHALLENGE 4: floor
    // ==========================================================
    @Nested
    class FloorTests {

        @Test
        void testExactMatchReturnsSameKey() {
            assertEquals(20, TreeMapBeginnerChallenge.floor(sampleMap(), 20));
        }

        @Test
        void testNoExactMatchReturnsNextLower() {
            assertEquals(20, TreeMapBeginnerChallenge.floor(sampleMap(), 25));
        }

        @Test
        void testNoFloorReturnsNull() {
            assertNull(TreeMapBeginnerChallenge.floor(sampleMap(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 5: higher
    // ==========================================================
    @Nested
    class HigherTests {

        @Test
        void testExactMatchSkipsToNext() {
            assertEquals(30, TreeMapBeginnerChallenge.higher(sampleMap(), 20));
        }

        @Test
        void testNoExactMatchReturnsNextHigher() {
            assertEquals(30, TreeMapBeginnerChallenge.higher(sampleMap(), 25));
        }

        @Test
        void testNoHigherReturnsNull() {
            assertNull(TreeMapBeginnerChallenge.higher(sampleMap(), 40));
        }
    }

    // ==========================================================
    // CHALLENGE 6: lower
    // ==========================================================
    @Nested
    class LowerTests {

        @Test
        void testExactMatchSkipsToPrevious() {
            assertEquals(10, TreeMapBeginnerChallenge.lower(sampleMap(), 20));
        }

        @Test
        void testNoExactMatchReturnsNextLower() {
            assertEquals(20, TreeMapBeginnerChallenge.lower(sampleMap(), 25));
        }

        @Test
        void testNoLowerReturnsNull() {
            assertNull(TreeMapBeginnerChallenge.lower(sampleMap(), 10));
        }
    }

    // ==========================================================
    // CHALLENGE 7: keysBelow
    // ==========================================================
    @Nested
    class KeysBelowTests {

        @Test
        void testReturnsKeysStrictlyBelow() {
            assertEquals(List.of(10, 20), TreeMapBeginnerChallenge.keysBelow(sampleMap(), 30));
        }

        @Test
        void testExactMatchNotIncluded() {
            assertEquals(List.of(10, 20, 30), TreeMapBeginnerChallenge.keysBelow(sampleMap(), 40));
        }

        @Test
        void testNoKeysBelowReturnsEmptyList() {
            assertEquals(List.of(), TreeMapBeginnerChallenge.keysBelow(sampleMap(), 10));
        }
    }

    // ==========================================================
    // CHALLENGE 8: keysInRange
    // ==========================================================
    @Nested
    class KeysInRangeTests {

        @Test
        void testReturnsKeysInHalfOpenRange() {
            assertEquals(List.of(20, 30), TreeMapBeginnerChallenge.keysInRange(sampleMap(), 20, 40));
        }

        @Test
        void testToExclusiveNotIncluded() {
            assertEquals(List.of(10, 20, 30), TreeMapBeginnerChallenge.keysInRange(sampleMap(), 10, 40));
        }

        @Test
        void testEmptyRangeReturnsEmptyList() {
            assertEquals(List.of(), TreeMapBeginnerChallenge.keysInRange(sampleMap(), 21, 30));
        }
    }
}