package dev.perfectbogus.maps;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapTypeChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — IdentityHashMap: count unique references
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void sameContentDifferentRefs() {
            // same content but DIFFERENT references!
            String s1 = new String("hello");
            String s2 = new String("hello"); // ← different reference!

            assertEquals(2,
                    MapTypeChallenges2.challenge1(List.of(s1, s2)));
        }

        @Test
        void aliasedReferences() {
            String s1 = new String("hello");
            String s2 = s1; // ← SAME reference!

            assertEquals(1,
                    MapTypeChallenges2.challenge1(List.of(s1, s2)));
        }

        @Test
        void mixedReferences() {
            String s1 = new String("hello");
            String s2 = new String("hello");
            String s3 = s1; // ← alias of s1

            // s1, s2 are different refs → 2 unique refs
            assertEquals(2,
                    MapTypeChallenges2.challenge1(Arrays.asList(s1, s2, s3, s1)));
        }

        @Test
        void hashMapWouldCountDifferently() {
            // Demonstrates why IdentityHashMap is needed!
            String s1 = new String("x");
            String s2 = new String("x");

            // HashMap would count 1 (equals!)
            // IdentityHashMap should count 2 (different refs!)
            assertEquals(2,
                    MapTypeChallenges2.challenge1(Arrays.asList(s1, s2)));
        }

        @Test
        void singleElement() {
            String s = new String("hello");
            assertEquals(1, MapTypeChallenges2.challenge1(List.of(s)));
        }

        @Test
        void emptyList() {
            assertEquals(0,
                    MapTypeChallenges2.challenge1(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — IdentityHashMap: object processing tracker
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void processesUniqueReferences() {
            MapTypeChallenges2.Item item1 = new MapTypeChallenges2.Item("x", 1);
            MapTypeChallenges2.Item item2 = new MapTypeChallenges2.Item("x", 1);
            // item1.equals(item2) = true (same content!)
            // item1 == item2     = false (different refs!)

            // IdentityHashMap → processes BOTH (different refs)!
            assertEquals(2,
                    MapTypeChallenges2.challenge2(Arrays.asList(item1, item2)));
        }

        @Test
        void skipsAlreadyVisited() {
            MapTypeChallenges2.Item item1 = new MapTypeChallenges2.Item("x", 1);
            MapTypeChallenges2.Item item3 = item1; // ← alias!

            // item1 and item3 are same reference → process only once!
            assertEquals(1,
                    MapTypeChallenges2.challenge2(Arrays.asList(item1, item3)));
        }

        @Test
        void mixedScenario() {
            MapTypeChallenges2.Item item1 = new MapTypeChallenges2.Item("x", 1);
            MapTypeChallenges2.Item item2 = new MapTypeChallenges2.Item("x", 1);
            MapTypeChallenges2.Item item3 = item1;

            // item1 → process (new ref)
            // item2 → process (different ref from item1!)
            // item3 → skip    (same ref as item1!)
            // item2 → skip    (already visited!)
            assertEquals(2,
                    MapTypeChallenges2.challenge2(
                            Arrays.asList(item1, item2, item3, item2)));
        }

        @Test
        void emptyList() {
            assertEquals(0,
                    MapTypeChallenges2.challenge2(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — IdentityHashMap: detect aliased objects
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void detectsAliases() {
            String s1 = new String("a");
            String s2 = new String("b");
            String s3 = s1; // ← alias of s1!
            String s4 = s2; // ← alias of s2!

            List<Object> list = Arrays.asList(s1, s2, s3, s4);
            Map<String, List<Integer>> result =
                    MapTypeChallenges2.challenge3(list);

            // Two alias groups: {s1/s3}→[0,2] and {s2/s4}→[1,3]
            assertEquals(2, result.size());
            // Each group has exactly 2 indices
            result.values().forEach(indices ->
                    assertEquals(2, indices.size()));
        }

        @Test
        void noAliases() {
            String s1 = new String("a");
            String s2 = new String("b");
            String s3 = new String("c");

            Map<String, List<Integer>> result =
                    MapTypeChallenges2.challenge3(Arrays.asList(s1, s2, s3));

            // All different refs → no aliases → empty result!
            assertTrue(result.isEmpty());
        }

        @Test
        void tripleAlias() {
            String s1 = new String("hello");
            String s2 = s1;
            String s3 = s1;

            Map<String, List<Integer>> result =
                    MapTypeChallenges2.challenge3(Arrays.asList(s1, s2, s3));

            assertEquals(1, result.size()); // one alias group
            List<Integer> indices = result.values().iterator().next();
            assertEquals(3, indices.size()); // three indices: 0,1,2
            assertTrue(indices.containsAll(List.of(0,1,2)));
        }

        @Test
        void emptyList() {
            assertTrue(
                    MapTypeChallenges2.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — EnumMap: task count per priority
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<MapTypeChallenges2.Task> tasks = List.of(
                    new MapTypeChallenges2.Task("A", MapTypeChallenges2.Priority.HIGH),
                    new MapTypeChallenges2.Task("B", MapTypeChallenges2.Priority.LOW),
                    new MapTypeChallenges2.Task("C", MapTypeChallenges2.Priority.HIGH),
                    new MapTypeChallenges2.Task("D", MapTypeChallenges2.Priority.MEDIUM),
                    new MapTypeChallenges2.Task("E", MapTypeChallenges2.Priority.LOW)
            );
            EnumMap<MapTypeChallenges2.Priority, Long> result =
                    MapTypeChallenges2.challenge4(tasks);

            assertEquals(2L, result.get(MapTypeChallenges2.Priority.LOW));
            assertEquals(1L, result.get(MapTypeChallenges2.Priority.MEDIUM));
            assertEquals(2L, result.get(MapTypeChallenges2.Priority.HIGH));
        }

        @Test
        void allPrioritiesPresentEvenIfZero() {
            // Only HIGH tasks → LOW and MEDIUM should still be in map with 0!
            List<MapTypeChallenges2.Task> tasks = List.of(
                    new MapTypeChallenges2.Task("A", MapTypeChallenges2.Priority.HIGH)
            );
            EnumMap<MapTypeChallenges2.Priority, Long> result =
                    MapTypeChallenges2.challenge4(tasks);

            assertEquals(3, result.size()); // all 3 priorities!
            assertEquals(0L, result.get(MapTypeChallenges2.Priority.LOW));
            assertEquals(0L, result.get(MapTypeChallenges2.Priority.MEDIUM));
            assertEquals(1L, result.get(MapTypeChallenges2.Priority.HIGH));
        }

        @Test
        void enumDeclarationOrder() {
            // EnumMap iterates in enum declaration order: LOW→MEDIUM→HIGH!
            List<MapTypeChallenges2.Task> tasks = List.of(
                    new MapTypeChallenges2.Task("A", MapTypeChallenges2.Priority.HIGH),
                    new MapTypeChallenges2.Task("B", MapTypeChallenges2.Priority.LOW)
            );
            EnumMap<MapTypeChallenges2.Priority, Long> result =
                    MapTypeChallenges2.challenge4(tasks);

            List<MapTypeChallenges2.Priority> keys = new ArrayList<>(result.keySet());
            assertEquals(MapTypeChallenges2.Priority.LOW,    keys.get(0));
            assertEquals(MapTypeChallenges2.Priority.MEDIUM, keys.get(1));
            assertEquals(MapTypeChallenges2.Priority.HIGH,   keys.get(2));
        }

        @Test
        void isEnumMap() {
            EnumMap<MapTypeChallenges2.Priority, Long> result =
                    MapTypeChallenges2.challenge4(List.of());

            assertInstanceOf(EnumMap.class, result);
        }

        @Test
        void emptyList() {
            EnumMap<MapTypeChallenges2.Priority, Long> result =
                    MapTypeChallenges2.challenge4(List.of());

            // All priorities with 0!
            assertEquals(0L, result.get(MapTypeChallenges2.Priority.LOW));
            assertEquals(0L, result.get(MapTypeChallenges2.Priority.MEDIUM));
            assertEquals(0L, result.get(MapTypeChallenges2.Priority.HIGH));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — EnumMap: highest salary per department
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<MapTypeChallenges2.DeptEmployee> employees = List.of(
                    new MapTypeChallenges2.DeptEmployee("Alice", MapTypeChallenges2.Department.ENGINEERING, 90000),
                    new MapTypeChallenges2.DeptEmployee("Bob",   MapTypeChallenges2.Department.ENGINEERING, 70000),
                    new MapTypeChallenges2.DeptEmployee("Carol", MapTypeChallenges2.Department.MARKETING,   80000),
                    new MapTypeChallenges2.DeptEmployee("Diana", MapTypeChallenges2.Department.HR,          75000)
            );
            EnumMap<MapTypeChallenges2.Department, Double> result =
                    MapTypeChallenges2.challenge5(employees);

            assertEquals(90000.0, result.get(MapTypeChallenges2.Department.ENGINEERING), 0.01);
            assertEquals(80000.0, result.get(MapTypeChallenges2.Department.MARKETING),   0.01);
            assertEquals(75000.0, result.get(MapTypeChallenges2.Department.HR),          0.01);
            assertNull(result.get(MapTypeChallenges2.Department.FINANCE)); // no FINANCE employees!
        }

        @Test
        void enumDeclarationOrder() {
            // HR=0, ENGINEERING=1, MARKETING=2, FINANCE=3
            List<MapTypeChallenges2.DeptEmployee> employees = List.of(
                    new MapTypeChallenges2.DeptEmployee("A", MapTypeChallenges2.Department.MARKETING,   50000),
                    new MapTypeChallenges2.DeptEmployee("B", MapTypeChallenges2.Department.ENGINEERING, 60000),
                    new MapTypeChallenges2.DeptEmployee("C", MapTypeChallenges2.Department.HR,          70000)
            );
            EnumMap<MapTypeChallenges2.Department, Double> result =
                    MapTypeChallenges2.challenge5(employees);

            List<MapTypeChallenges2.Department> keys = new ArrayList<>(result.keySet());
            // Enum order: HR first, then ENGINEERING, then MARKETING
            assertEquals(MapTypeChallenges2.Department.HR,          keys.get(0));
            assertEquals(MapTypeChallenges2.Department.ENGINEERING, keys.get(1));
            assertEquals(MapTypeChallenges2.Department.MARKETING,   keys.get(2));
        }

        @Test
        void singleEmployee() {
            List<MapTypeChallenges2.DeptEmployee> employees = List.of(
                    new MapTypeChallenges2.DeptEmployee("Alice", MapTypeChallenges2.Department.FINANCE, 95000));

            EnumMap<MapTypeChallenges2.Department, Double> result =
                    MapTypeChallenges2.challenge5(employees);

            assertEquals(95000.0, result.get(MapTypeChallenges2.Department.FINANCE), 0.01);
            assertEquals(1, result.size());
        }

        @Test
        void isEnumMap() {
            assertInstanceOf(EnumMap.class,
                    MapTypeChallenges2.challenge5(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — EnumMap: priority weighted score
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            List<MapTypeChallenges2.ScoredTask> tasks = List.of(
                    new MapTypeChallenges2.ScoredTask("Alpha", MapTypeChallenges2.Priority.HIGH,   10),
                    new MapTypeChallenges2.ScoredTask("Beta",  MapTypeChallenges2.Priority.LOW,    20),
                    new MapTypeChallenges2.ScoredTask("Gamma", MapTypeChallenges2.Priority.MEDIUM, 15)
            );
            // Alpha: 10×3=30, Beta: 20×1=20, Gamma: 15×2=30 → total=80
            assertEquals(80, MapTypeChallenges2.challenge6(tasks));
        }

        @Test
        void allHighPriority() {
            List<MapTypeChallenges2.ScoredTask> tasks = List.of(
                    new MapTypeChallenges2.ScoredTask("A", MapTypeChallenges2.Priority.HIGH, 10),
                    new MapTypeChallenges2.ScoredTask("B", MapTypeChallenges2.Priority.HIGH, 5)
            );
            // 10×3=30, 5×3=15 → total=45
            assertEquals(45, MapTypeChallenges2.challenge6(tasks));
        }

        @Test
        void allLowPriority() {
            List<MapTypeChallenges2.ScoredTask> tasks = List.of(
                    new MapTypeChallenges2.ScoredTask("A", MapTypeChallenges2.Priority.LOW, 100)
            );
            // 100×1=100
            assertEquals(100, MapTypeChallenges2.challenge6(tasks));
        }

        @Test
        void emptyList() {
            assertEquals(0, MapTypeChallenges2.challenge6(List.of()));
        }

        @Test
        void singleMediumTask() {
            List<MapTypeChallenges2.ScoredTask> tasks = List.of(
                    new MapTypeChallenges2.ScoredTask("X", MapTypeChallenges2.Priority.MEDIUM, 7)
            );
            // 7×2=14
            assertEquals(14, MapTypeChallenges2.challenge6(tasks));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapTypeChallenges2.challenge6(null));
        }
    }
}