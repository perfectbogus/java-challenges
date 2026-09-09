package dev.perfectbogus.maps;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapTypeChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — HashMap: word frequency
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<String, Integer> result = MapTypeChallenges.challenge1(
                    List.of("apple","banana","apple","cherry","banana","apple"));

            assertEquals(3, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(1, result.get("cherry"));
        }

        @Test
        void allUnique() {
            Map<String, Integer> result = MapTypeChallenges.challenge1(
                    List.of("a","b","c"));

            assertEquals(1, result.get("a"));
            assertEquals(1, result.get("b"));
            assertEquals(1, result.get("c"));
        }

        @Test
        void allSame() {
            Map<String, Integer> result = MapTypeChallenges.challenge1(
                    List.of("hello","hello","hello"));

            assertEquals(3, result.get("hello"));
            assertEquals(1, result.size());
        }

        @Test
        void emptyList() {
            assertTrue(MapTypeChallenges.challenge1(List.of()).isEmpty());
        }

        @Test
        void isHashMap() {
            assertInstanceOf(HashMap.class,
                    MapTypeChallenges.challenge1(List.of("a")));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — HashMap: group by first character
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<Character, List<String>> result = MapTypeChallenges.challenge2(
                    List.of("apple","avocado","banana","blueberry","cherry"));

            assertEquals(List.of("apple","avocado"),    result.get('a'));
            assertEquals(List.of("banana","blueberry"), result.get('b'));
            assertEquals(List.of("cherry"),              result.get('c'));
        }

        @Test
        void allSameFirstChar() {
            Map<Character, List<String>> result = MapTypeChallenges.challenge2(
                    List.of("ant","ape","axe"));

            assertEquals(1, result.size());
            assertEquals(List.of("ant","ape","axe"), result.get('a'));
        }

        @Test
        void singleWord() {
            Map<Character, List<String>> result = MapTypeChallenges.challenge2(
                    List.of("hello"));

            assertEquals(List.of("hello"), result.get('h'));
        }

        @Test
        void preservesOriginalOrder() {
            Map<Character, List<String>> result = MapTypeChallenges.challenge2(
                    List.of("bob","alice","barry","anna"));

            assertEquals(List.of("bob","barry"), result.get('b'));  // original order!
            assertEquals(List.of("alice","anna"), result.get('a'));
        }

        @Test
        void emptyStringThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge2(List.of("hello","")));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — HashMap: two-sum index pairs
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            List<int[]> result = MapTypeChallenges.challenge3(
                    List.of(2,7,11,15), 9);

            assertEquals(1, result.size());
            assertArrayEquals(new int[]{0,1}, result.get(0));
        }

        @Test
        void multiplePairs() {
            List<int[]> result = MapTypeChallenges.challenge3(
                    List.of(3,2,4,1,3), 6);

            assertEquals(2, result.size());
            assertArrayEquals(new int[]{1,2}, result.get(0)); // 2+4=6 found first
            assertArrayEquals(new int[]{0,4}, result.get(1)); // 3+3=6 found second
        }

        @Test
        void noPairs() {
            List<int[]> result = MapTypeChallenges.challenge3(
                    List.of(1,2,3), 100);

            assertTrue(result.isEmpty());
        }

        @Test
        void pairAtEnds() {
            List<int[]> result = MapTypeChallenges.challenge3(
                    List.of(5,3,1,4,2), 7);

            assertEquals(2, result.size());
            assertArrayEquals(new int[]{1, 3}, result.get(0)); // 5+... wait
            assertArrayEquals(new int[]{0, 4}, result.get(1));
            // 5+?: 7-5=2, see when 2 is found at i=4, pair is [0,4]
            // let me recalculate
            // i=0: map={5→0}
            // i=1: 7-3=4, not in map, map={5→0,3→1}
            // i=2: 7-1=6, not in map, map={5→0,3→1,1→2}
            // i=3: 7-4=3, in map at 1! pair=[1,3]
            // i=4: 7-2=5, in map at 0! pair=[0,4]
        }

        @Test
        void emptyList() {
            assertTrue(MapTypeChallenges.challenge3(List.of(), 5).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge3(null, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — LinkedHashMap: first occurrence index
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge4(
                    List.of("banana","apple","cherry","apple","banana"));

            assertEquals(0, result.get("banana"));
            assertEquals(1, result.get("apple"));
            assertEquals(2, result.get("cherry"));
            assertEquals(3, result.size()); // only 3 unique words!
        }

        @Test
        void preservesInsertionOrder() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge4(
                    List.of("c","a","b","a","c"));

            List<String> keys = new ArrayList<>(result.keySet());
            assertEquals(List.of("c","a","b"), keys); // insertion order!
        }

        @Test
        void isLinkedHashMap() {
            assertInstanceOf(LinkedHashMap.class,
                    MapTypeChallenges.challenge4(List.of("a")));
        }

        @Test
        void firstIndexKept() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge4(
                    List.of("x","y","x","z"));

            assertEquals(0, result.get("x")); // ← 0, not 2!
        }

        @Test
        void allUnique() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge4(
                    List.of("a","b","c"));

            assertEquals(0, result.get("a"));
            assertEquals(1, result.get("b"));
            assertEquals(2, result.get("c"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — LinkedHashMap: ordered frequency
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge5(
                    List.of("cat","dog","cat","bird","dog","cat"));

            List<Map.Entry<String, Integer>> entries = new ArrayList<>(result.entrySet());
            assertEquals("cat",  entries.get(0).getKey()); // first seen!
            assertEquals(3,      entries.get(0).getValue());
            assertEquals("dog",  entries.get(1).getKey());
            assertEquals(2,      entries.get(1).getValue());
            assertEquals("bird", entries.get(2).getKey());
            assertEquals(1,      entries.get(2).getValue());
        }

        @Test
        void isLinkedHashMap() {
            assertInstanceOf(LinkedHashMap.class,
                    MapTypeChallenges.challenge5(List.of("a")));
        }

        @Test
        void orderIsFirstSeen() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge5(
                    List.of("z","a","z","b","a","z"));

            List<String> keys = new ArrayList<>(result.keySet());
            assertEquals(List.of("z","a","b"), keys); // z seen first!
        }

        @Test
        void allUnique() {
            LinkedHashMap<String, Integer> result = MapTypeChallenges.challenge5(
                    List.of("a","b","c"));

            result.values().forEach(v -> assertEquals(1, v));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — LinkedHashMap: last N unique items
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            List<String> result = MapTypeChallenges.challenge6(
                    List.of("a","b","c","a","d"), 3);

            assertEquals(List.of("c","a","d"), result);
        }

        @Test
        void noEviction() {
            List<String> result = MapTypeChallenges.challenge6(
                    List.of("a","b","c"), 5);

            assertEquals(List.of("a","b","c"), result);
        }

        @Test
        void reaccesMovesToEnd() {
            List<String> result = MapTypeChallenges.challenge6(
                    List.of("a","b","a"), 3);

            // "a" re-accessed → moves to end!
            assertEquals(List.of("b","a"), result);
        }

        @Test
        void singleCapacity() {
            List<String> result = MapTypeChallenges.challenge6(
                    List.of("a","b","c"), 1);

            assertEquals(List.of("c"), result); // only last accessed!
        }

        @Test
        void emptyItems() {
            assertTrue(MapTypeChallenges.challenge6(List.of(), 3).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge6(null, 3));
        }

        @Test
        void invalidN() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge6(List.of("a"), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — TreeMap: range query
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        TreeMap<Integer, String> makeMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(10, "pen");
            map.put(20, "book");
            map.put(30, "bag");
            map.put(40, "laptop");
            map.put(50, "phone");
            return map;
        }

        @Test
        void basicRange() {
            assertEquals(List.of("book","bag","laptop"),
                    MapTypeChallenges.challenge7(makeMap(), 20, 40));
        }

        @Test
        void exactBoundaries() {
            assertEquals(List.of("pen"),
                    MapTypeChallenges.challenge7(makeMap(), 10, 10));
        }

        @Test
        void noMatches() {
            assertTrue(MapTypeChallenges.challenge7(makeMap(), 25, 29).isEmpty());
        }

        @Test
        void fullRange() {
            assertEquals(List.of("pen","book","bag","laptop","phone"),
                    MapTypeChallenges.challenge7(makeMap(), 1, 100));
        }

        @Test
        void sortedAscending() {
            List<String> result = MapTypeChallenges.challenge7(makeMap(), 10, 50);
            assertEquals("pen",   result.get(0)); // cheapest first!
            assertEquals("phone", result.get(4)); // most expensive last!
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge7(null, 10, 50));
        }

        @Test
        void loGreaterThanHi() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge7(makeMap(), 50, 10));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — TreeMap: floor and ceiling
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        TreeMap<Integer, String> makeGradeMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(60, "D");
            map.put(70, "C");
            map.put(80, "B");
            map.put(90, "A");
            return map;
        }

        @Test
        void betweenKeys() {
            MapTypeChallenges.GradeBounds result =
                    MapTypeChallenges.challenge8(makeGradeMap(), 75);

            assertEquals("C", result.floorGrade());   // 70 ≤ 75
            assertEquals("B", result.ceilingGrade()); // 80 ≥ 75
        }

        @Test
        void exactKey() {
            MapTypeChallenges.GradeBounds result =
                    MapTypeChallenges.challenge8(makeGradeMap(), 60);

            assertEquals("D", result.floorGrade());
            assertEquals("D", result.ceilingGrade());
        }

        @Test
        void belowMin() {
            MapTypeChallenges.GradeBounds result =
                    MapTypeChallenges.challenge8(makeGradeMap(), 55);

            assertEquals("NONE", result.floorGrade());   // nothing below 60!
            assertEquals("D",    result.ceilingGrade()); // 60 ≥ 55
        }

        @Test
        void aboveMax() {
            MapTypeChallenges.GradeBounds result =
                    MapTypeChallenges.challenge8(makeGradeMap(), 95);

            assertEquals("A",    result.floorGrade());    // 90 ≤ 95
            assertEquals("NONE", result.ceilingGrade());  // nothing above 90!
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge8(null, 75));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — TreeMap: top N products by price
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            List<MapTypeChallenges.Product> products = List.of(
                    new MapTypeChallenges.Product("Pen",    1.5),
                    new MapTypeChallenges.Product("Laptop", 999.0),
                    new MapTypeChallenges.Product("Book",   25.0),
                    new MapTypeChallenges.Product("Phone",  699.0),
                    new MapTypeChallenges.Product("Bag",    49.0)
            );
            assertEquals(List.of("Laptop","Phone","Bag"),
                    MapTypeChallenges.challenge9(products, 3));
        }

        @Test
        void basicCase2() {
            List<MapTypeChallenges.Product> products = List.of(
                    new MapTypeChallenges.Product("Pen",    1.5),
                    new MapTypeChallenges.Product("Laptop", 999.0),
                    new MapTypeChallenges.Product("Book",   25.0),
                    new MapTypeChallenges.Product("Phone",  699.0),
                    new MapTypeChallenges.Product("Bag",    49.0)
            );
            assertEquals(List.of("Laptop","Phone","Bag"),
                    MapTypeChallenges.challenge9_2(products, 3));
        }

        @Test
        void topOne() {
            List<MapTypeChallenges.Product> products = List.of(
                    new MapTypeChallenges.Product("A", 10.0),
                    new MapTypeChallenges.Product("B", 50.0),
                    new MapTypeChallenges.Product("C", 30.0)
            );
            assertEquals(List.of("B"),
                    MapTypeChallenges.challenge9(products, 1));
        }

        @Test
        void nLargerThanList() {
            List<MapTypeChallenges.Product> products = List.of(
                    new MapTypeChallenges.Product("X", 10.0),
                    new MapTypeChallenges.Product("Y", 20.0)
            );
            List<String> result = MapTypeChallenges.challenge9(products, 10);
            assertEquals(2, result.size()); // only 2 available!
            assertEquals("Y", result.get(0)); // most expensive first!
        }

        @Test
        void descendingOrder() {
            List<MapTypeChallenges.Product> products = List.of(
                    new MapTypeChallenges.Product("Cheap",     5.0),
                    new MapTypeChallenges.Product("Medium",   50.0),
                    new MapTypeChallenges.Product("Expensive",500.0)
            );
            List<String> result = MapTypeChallenges.challenge9(products, 3);
            assertEquals("Expensive", result.get(0));
            assertEquals("Medium",    result.get(1));
            assertEquals("Cheap",     result.get(2));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge9(null, 3));
        }

        @Test
        void invalidN() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge9(List.of(), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — ConcurrentHashMap: parallel word frequency
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @RepeatedTest(5)
        void correctResultRegardlessOfThreading() throws InterruptedException {
            Map<String, Integer> result = MapTypeChallenges.challenge10(
                    List.of("apple","banana","apple","cherry","banana","apple"), 3);

            assertEquals(3, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(1, result.get("cherry"));
        }

        @Test
        void singleThread() throws InterruptedException {
            Map<String, Integer> result = MapTypeChallenges.challenge10(
                    List.of("a","b","a"), 1);

            assertEquals(2, result.get("a"));
            assertEquals(1, result.get("b"));
        }

        @Test
        void isConcurrentHashMap() throws InterruptedException {
            assertInstanceOf(java.util.concurrent.ConcurrentHashMap.class,
                    MapTypeChallenges.challenge10(List.of("a"), 1));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge10(null, 2));
        }

        @Test
        void invalidThreadCount() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge10(List.of("a"), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 11 — ConcurrentHashMap: parallel groupBy length
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge11 {

        @RepeatedTest(5)
        void correctGroupsRegardlessOfThreading() throws InterruptedException {
            Map<Integer, List<String>> result = MapTypeChallenges.challenge11(
                    List.of("hi","hello","hey","world","ok"), 2);

            assertTrue(result.get(2).containsAll(List.of("hi","ok")));
            assertTrue(result.get(5).containsAll(List.of("hello","world")));
            assertEquals(List.of("hey"), result.get(3));
        }

        @Test
        void singleThread() throws InterruptedException {
            Map<Integer, List<String>> result = MapTypeChallenges.challenge11(
                    List.of("cat","dog"), 1);

            assertTrue(result.get(3).containsAll(List.of("cat","dog")));
        }

        @Test
        void emptyList() throws InterruptedException {
            assertTrue(MapTypeChallenges.challenge11(List.of(), 2).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge11(null, 2));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 12 — ConcurrentHashMap: parallel amount accumulation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge12 {

        @RepeatedTest(5)
        void correctSumsRegardlessOfThreading() throws InterruptedException {
            List<MapTypeChallenges.Transaction> transactions = List.of(
                    new MapTypeChallenges.Transaction("Food",   100),
                    new MapTypeChallenges.Transaction("Tech",   500),
                    new MapTypeChallenges.Transaction("Food",   200),
                    new MapTypeChallenges.Transaction("Tech",   300),
                    new MapTypeChallenges.Transaction("Sports", 150)
            );
            Map<String, Long> result =
                    MapTypeChallenges.challenge12(transactions, 3);

            assertEquals(300L,  result.get("Food"));
            assertEquals(800L,  result.get("Tech"));
            assertEquals(150L,  result.get("Sports"));
        }

        @Test
        void singleTransaction() throws InterruptedException {
            Map<String, Long> result = MapTypeChallenges.challenge12(
                    List.of(new MapTypeChallenges.Transaction("Food", 42)), 1);

            assertEquals(42L, result.get("Food"));
        }

        @Test
        void emptyList() throws InterruptedException {
            assertTrue(MapTypeChallenges.challenge12(List.of(), 2).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges.challenge12(null, 2));
        }
    }
}