package dev.perfectbogus.collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorEasyChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Count words longer than N characters
    // Key concept: filter + counting()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            assertEquals(3L, CollectorEasyChallenges.challenge1(
                    List.of("hello","hi","world","java","is","fun"), 3));
        }

        @Test
        void noneQualify() {
            assertEquals(0L, CollectorEasyChallenges.challenge1(
                    List.of("hi","is","go","no"), 3));
        }

        @Test
        void allQualify() {
            assertEquals(3L, CollectorEasyChallenges.challenge1(
                    List.of("hello","world","java"), 3));
        }

        @Test
        void exactlyAtBoundary() {
            // strictly GREATER than n — length=3 with n=3 should NOT count
            assertEquals(0L, CollectorEasyChallenges.challenge1(List.of("cat","dog","ant"), 3));
        }

        @Test
        void nEqualsZero() {
            // all words longer than 0
            assertEquals(3L, CollectorEasyChallenges.challenge1(List.of("a","b","c"), 0));
        }

        @Test
        void emptyList() {
            assertEquals(0L, CollectorEasyChallenges.challenge1(List.of(), 3));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge1(null, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Join employee names into formatted string
    // Key concept: filter + map + sorted + joining()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000, 4)
            );
        }

        @Test
        void basicCase() {
            assertEquals("Employees: [Alice, Carol]",
                    CollectorEasyChallenges.challenge2(employees, 80000));
        }

        @Test
        void allQualify() {
            assertEquals("Employees: [Alice, Bob, Carol, Diana]",
                    CollectorEasyChallenges.challenge2(employees, 0));
        }

        @Test
        void noneQualify() {
            assertEquals("Employees: []",
                    CollectorEasyChallenges.challenge2(employees, 200000));
        }

        @Test
        void exactlyAtThreshold() {
            // >= threshold → Alice(95000), Carol(85000), Diana(70000) if threshold=70000
            assertEquals("Employees: [Alice, Carol, Diana]",
                    CollectorEasyChallenges.challenge2(employees, 70000));
        }

        @Test
        void singleEmployee() {
            assertEquals("Employees: [Alice]",
                    CollectorEasyChallenges.challenge2(employees, 90000));
        }

        @Test
        void emptyList() {
            assertEquals("Employees: []",
                    CollectorEasyChallenges.challenge2(List.of(), 50000));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorEasyChallenges.challenge2(null, 50000));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Partition employees into senior and junior
    // Key concept: partitioningBy()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 10),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000,  2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000,  8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000,  3),
                    new CollectorEasyChallenges.Employee("Eve",    "Engineering", 92000,  5),
                    new CollectorEasyChallenges.Employee("Frank",  "HR",          55000,  1)
            );
        }

        @Test
        void seniorCount() {
            Map<Boolean, List<CollectorEasyChallenges.Employee>> result =
                    CollectorEasyChallenges.challenge3(employees);

            assertEquals(3, result.get(true).size());  // senior: Alice, Carol, Eve
            assertEquals(3, result.get(false).size()); // junior: Bob, Diana, Frank
        }

        @Test
        void seniorNames() {
            Map<Boolean, List<CollectorEasyChallenges.Employee>> result =
                    CollectorEasyChallenges.challenge3(employees);

            List<String> seniorNames = result.get(true).stream()
                    .map(CollectorEasyChallenges.Employee::name).toList();
            assertTrue(seniorNames.contains("Alice"));
            assertTrue(seniorNames.contains("Carol"));
            assertTrue(seniorNames.contains("Eve"));
        }

        @Test
        void juniorNames() {
            Map<Boolean, List<CollectorEasyChallenges.Employee>> result =
                    CollectorEasyChallenges.challenge3(employees);

            List<String> juniorNames = result.get(false).stream()
                    .map(CollectorEasyChallenges.Employee::name).toList();
            assertTrue(juniorNames.contains("Bob"));
            assertTrue(juniorNames.contains("Diana"));
            assertTrue(juniorNames.contains("Frank"));
        }

        @Test
        void exactlyAtBoundary() {
            // years=5 → senior (>= 5)
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Eve", "Engineering", 92000, 5));

            Map<Boolean, List<CollectorEasyChallenges.Employee>> result =
                    CollectorEasyChallenges.challenge3(single);

            assertEquals(1, result.get(true).size());
            assertEquals(0, result.get(false).size());
        }

        @Test
        void alwaysReturnsBothKeys() {
            // partitioningBy always returns both true and false keys!
            Map<Boolean, List<CollectorEasyChallenges.Employee>> result =
                    CollectorEasyChallenges.challenge3(List.of());

            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sum total salary per department
    // Key concept: groupingBy + summingDouble()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000, 4),
                    new CollectorEasyChallenges.Employee("Eve",    "Engineering", 92000, 9)
            );
        }

        @Test
        void basicCase() {
            Map<String, Double> result = CollectorEasyChallenges.challenge4(employees);

            assertEquals(272000.0, result.get("Engineering"), 0.01);
            assertEquals(130000.0, result.get("Marketing"),   0.01);
        }

        @Test
        void singleDepartment() {
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5));

            Map<String, Double> result = CollectorEasyChallenges.challenge4(single);

            assertEquals(1, result.size());
            assertEquals(95000.0, result.get("Engineering"), 0.01);
        }

        @Test
        void emptyList() {
            assertTrue(CollectorEasyChallenges.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Get highest paid employee name per department
    // Key concept: groupingBy + collectingAndThen + maxBy
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000, 4),
                    new CollectorEasyChallenges.Employee("Eve",    "Engineering", 92000, 9)
            );
        }

        @Test
        void basicCase() {
            Map<String, String> result = CollectorEasyChallenges.challenge5(employees);

            assertEquals("Alice", result.get("Engineering")); // 95000 highest
            assertEquals("Diana", result.get("Marketing"));   // 70000 highest
        }

        @Test
        void returnsStringNotOptional() {
            Map<String, String> result = CollectorEasyChallenges.challenge5(employees);

            // Must return String not Optional<Employee>!
            assertInstanceOf(String.class, result.get("Engineering"));
        }

        @Test
        void singleEmployee() {
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5));

            Map<String, String> result = CollectorEasyChallenges.challenge5(single);
            assertEquals("Alice", result.get("Engineering"));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorEasyChallenges.challenge5(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Collect names to unmodifiable sorted list
    // Key concept: sorted + collectingAndThen + unmodifiableList
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Eve",   "Engineering", 92000, 9),
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Carol", "Marketing",   85000, 8),
                    new CollectorEasyChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Diana", "HR",          70000, 4)
            );
        }

        @Test
        void sortedAlphabetically() {
            List<String> result = CollectorEasyChallenges.challenge6(employees);
            assertEquals(List.of("Alice","Bob","Carol","Diana","Eve"), result);
        }

        @Test
        void isUnmodifiable() {
            List<String> result = CollectorEasyChallenges.challenge6(employees);
            assertThrows(UnsupportedOperationException.class, () -> result.add("Zara"));
        }

        @Test
        void containsAllNames() {
            List<String> result = CollectorEasyChallenges.challenge6(employees);
            assertEquals(5, result.size());
            assertTrue(result.containsAll(List.of("Alice","Bob","Carol","Diana","Eve")));
        }

        @Test
        void emptyList() {
            List<String> result = CollectorEasyChallenges.challenge6(List.of());
            assertTrue(result.isEmpty());
            assertThrows(UnsupportedOperationException.class, () -> result.add("Alice"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Count employees per department
    // Key concept: groupingBy + counting()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000, 4),
                    new CollectorEasyChallenges.Employee("Eve",    "Engineering", 92000, 9),
                    new CollectorEasyChallenges.Employee("Frank",  "HR",          55000, 1)
            );
        }

        @Test
        void basicCase() {
            Map<String, Long> result = CollectorEasyChallenges.challenge7(employees);

            assertEquals(3L, result.get("Engineering"));
            assertEquals(2L, result.get("Marketing"));
            assertEquals(1L, result.get("HR"));
        }

        @Test
        void singleDepartment() {
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",   "Engineering", 85000, 3));

            Map<String, Long> result = CollectorEasyChallenges.challenge7(single);
            assertEquals(1, result.size());
            assertEquals(2L, result.get("Engineering"));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorEasyChallenges.challenge7(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Average salary per department
    // Key concept: groupingBy + averagingDouble()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "Marketing",   70000, 4),
                    new CollectorEasyChallenges.Employee("Eve",    "Engineering", 92000, 9)
            );
        }

        @Test
        void basicCase() {
            Map<String, Double> result = CollectorEasyChallenges.challenge8(employees);

            assertEquals(90666.67, result.get("Engineering"), 0.01);
            assertEquals(65000.0,  result.get("Marketing"),   0.01);
        }

        @Test
        void singleEmployeePerDept() {
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5));

            Map<String, Double> result = CollectorEasyChallenges.challenge8(single);
            assertEquals(95000.0, result.get("Engineering"), 0.01);
        }

        @Test
        void emptyList() {
            assertTrue(CollectorEasyChallenges.challenge8(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Collect to Map: name → salary
    // Key concept: toMap(keyExtractor, valueExtractor)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol", "Engineering", 85000, 8)
            );
        }

        @Test
        void basicCase() {
            Map<String, Double> result = CollectorEasyChallenges.challenge9(employees);

            assertEquals(3, result.size());
            assertEquals(95000.0, result.get("Alice"), 0.01);
            assertEquals(60000.0, result.get("Bob"),   0.01);
            assertEquals(85000.0, result.get("Carol"), 0.01);
        }

        @Test
        void keysAreNames() {
            Map<String, Double> result = CollectorEasyChallenges.challenge9(employees);

            assertTrue(result.containsKey("Alice"));
            assertTrue(result.containsKey("Bob"));
            assertTrue(result.containsKey("Carol"));
        }

        @Test
        void singleEmployee() {
            List<CollectorEasyChallenges.Employee> single = List.of(
                    new CollectorEasyChallenges.Employee("Alice", "Engineering", 95000, 5));

            Map<String, Double> result = CollectorEasyChallenges.challenge9(single);
            assertEquals(1, result.size());
            assertEquals(95000.0, result.get("Alice"), 0.01);
        }

        @Test
        void emptyList() {
            assertTrue(CollectorEasyChallenges.challenge9(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Get department names as sorted unmodifiable Set
    // Key concept: map + collectingAndThen + toCollection(TreeSet::new)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<CollectorEasyChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorEasyChallenges.Employee("Alice",  "Engineering", 95000, 5),
                    new CollectorEasyChallenges.Employee("Bob",    "Marketing",   60000, 2),
                    new CollectorEasyChallenges.Employee("Carol",  "Engineering", 85000, 8),
                    new CollectorEasyChallenges.Employee("Diana",  "HR",          70000, 4),
                    new CollectorEasyChallenges.Employee("Eve",    "HR",          55000, 1)
            );
        }

        @Test
        void correctDepartments() {
            Set<String> result = CollectorEasyChallenges.challenge10(employees);

            assertEquals(3, result.size());
            assertTrue(result.contains("Engineering"));
            assertTrue(result.contains("Marketing"));
            assertTrue(result.contains("HR"));
        }

        @Test
        void isSorted() {
            Set<String> result = CollectorEasyChallenges.challenge10(employees);

            List<String> asList = new ArrayList<>(result);
            assertEquals("Engineering", asList.get(0));
            assertEquals("HR",          asList.get(1));
            assertEquals("Marketing",   asList.get(2));
        }

        @Test
        void isUnmodifiable() {
            Set<String> result = CollectorEasyChallenges.challenge10(employees);
            assertThrows(UnsupportedOperationException.class, () -> result.add("Finance"));
        }

        @Test
        void noDuplicates() {
            // Engineering appears 2 times → only once in result
            Set<String> result = CollectorEasyChallenges.challenge10(employees);
            assertEquals(3, result.size()); // not 5!
        }

        @Test
        void emptyList() {
            Set<String> result = CollectorEasyChallenges.challenge10(List.of());
            assertTrue(result.isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorEasyChallenges.challenge10(null));
        }
    }
}