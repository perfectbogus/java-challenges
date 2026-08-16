package dev.perfectbogus.collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Group by first char, count per group
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<Character, Long> result = CollectorChallenges.challenge1(
                    List.of("apple","avocado","banana","blueberry","cherry","apricot","coconut"));

            assertEquals(3L, result.get('a'));
            assertEquals(2L, result.get('b'));
            assertEquals(2L, result.get('c'));
            assertEquals(3, result.size());
        }

        @Test
        void singleGroupAllSameFirstChar() {
            Map<Character, Long> result = CollectorChallenges.challenge1(
                    List.of("ant","ape","ark","arm"));

            assertEquals(1, result.size());
            assertEquals(4L, result.get('a'));
        }

        @Test
        void allDifferentFirstChars() {
            Map<Character, Long> result = CollectorChallenges.challenge1(
                    List.of("apple","banana","cherry"));

            assertEquals(3, result.size());
            assertEquals(1L, result.get('a'));
            assertEquals(1L, result.get('b'));
            assertEquals(1L, result.get('c'));
        }

        @Test
        void singleWord() {
            Map<Character, Long> result = CollectorChallenges.challenge1(List.of("hello"));

            assertEquals(1, result.size());
            assertEquals(1L, result.get('h'));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge1(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Partition into above/at-or-below threshold, sum each
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(
                    List.of(1, 5, 8, 3, 9, 2, 7, 4, 6), 5);

            assertEquals(30, result.get(true));  // 8+9+7+6=30
            assertEquals(15, result.get(false)); // 1+5+3+2+4=15
        }

        @Test
        void exactlyAtBoundaryGoesToFalse() {
            // threshold=5 → 5 is NOT > 5 → goes to false
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(
                    List.of(5, 6), 5);

            assertEquals(6,  result.get(true));
            assertEquals(5,  result.get(false)); // 5 is at threshold → false
        }

        @Test
        void allAboveThreshold() {
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(
                    List.of(8, 9, 10), 5);

            assertEquals(27, result.get(true));
            assertEquals(0,  result.get(false));
        }

        @Test
        void allAtOrBelowThreshold() {
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(
                    List.of(1, 2, 3), 5);

            assertEquals(0,  result.get(true));
            assertEquals(6,  result.get(false));
        }

        @Test
        void partitioningAlwaysReturnsBothKeys() {
            // partitioningBy always provides both true and false keys
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(List.of(1), 100);

            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        void emptyList() {
            Map<Boolean, Integer> result = CollectorChallenges.challenge2(List.of(), 5);
            assertEquals(0, result.get(true));
            assertEquals(0, result.get(false));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge2(null, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Group by dept → sorted list of names
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        private List<CollectorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "HR",          90000, 8)
            );
        }

        @Test
        void basicCase() {
            Map<String, List<String>> result = CollectorChallenges.challenge3(employees);

            assertEquals(List.of("Alice","Carol"), result.get("Engineering"));
            assertEquals(List.of("Bob","Diana"),   result.get("Marketing"));
            assertEquals(List.of("Eve"),           result.get("HR"));
        }

        @Test
        void namesSortedAlphabetically() {
            List<CollectorChallenges.Employee> reversed = List.of(
                    new CollectorChallenges.Employee("Zara", "HR", 90000, 5),
                    new CollectorChallenges.Employee("Alice","HR", 80000, 3),
                    new CollectorChallenges.Employee("Mia",  "HR", 70000, 2)
            );
            Map<String, List<String>> result = CollectorChallenges.challenge3(reversed);

            assertEquals(List.of("Alice","Mia","Zara"), result.get("HR"));
        }

        @Test
        void singleDepartment() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5)
            );
            Map<String, List<String>> result = CollectorChallenges.challenge3(single);

            assertEquals(1, result.size());
            assertEquals(List.of("Alice"), result.get("Engineering"));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Group by first letter → comma-joined sorted words
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            Map<Character, String> result = CollectorChallenges.challenge4(
                    List.of("cherry","apple","banana","avocado","blueberry","coconut","apricot"));

            assertEquals("apple, apricot, avocado", result.get('a'));
            assertEquals("banana, blueberry",       result.get('b'));
            assertEquals("cherry, coconut",         result.get('c'));
        }

        @Test
        void singleWordPerGroup() {
            Map<Character, String> result = CollectorChallenges.challenge4(
                    List.of("apple","banana","cherry"));

            assertEquals("apple",  result.get('a'));
            assertEquals("banana", result.get('b'));
            assertEquals("cherry", result.get('c'));
        }

        @Test
        void joinedInAlphaOrder() {
            Map<Character, String> result = CollectorChallenges.challenge4(
                    List.of("zoo","zap","zebra","zip"));

            assertEquals("zap, zebra, zip, zoo", result.get('z'));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Department with highest average salary
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "HR",          90000, 8)
            );
            // Avgs: Engineering=90000, Marketing=65000, HR=90000
            // Tie: Engineering & HR both 90000 → alpha first: "Engineering"
            assertEquals("Engineering", CollectorChallenges.challenge5(employees));
        }

        @Test
        void clearWinner() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Marketing",   62000, 3)
            );
            // Eng avg=95000, Mkt avg=61000
            assertEquals("Engineering", CollectorChallenges.challenge5(employees));
        }

        @Test
        void tieResolvesAlphabetically() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("A", "Zeta",  80000, 1),
                    new CollectorChallenges.Employee("B", "Alpha", 80000, 1)
            );
            // Both avg=80000 → "Alpha" comes before "Zeta"
            assertEquals("Alpha", CollectorChallenges.challenge5(employees));
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5)
            );
            assertEquals("Engineering", CollectorChallenges.challenge5(single));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge5(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges.challenge5(List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Dept → names CSV sorted by salary DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<CollectorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "Engineering", 92000, 6)
            );
        }

        @Test
        void basicCase() {
            Map<String, String> result = CollectorChallenges.challenge6(employees);

            assertEquals("Alice, Eve, Carol", result.get("Engineering")); // 95000,92000,85000
            assertEquals("Diana, Bob",        result.get("Marketing"));   // 70000,60000
        }

        @Test
        void singleEmployeePerDept() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5)
            );
            Map<String, String> result = CollectorChallenges.challenge6(single);
            assertEquals("Alice", result.get("Engineering"));
        }

        @Test
        void twoEmployeesSameNamesDifferentSalary() {
            List<CollectorChallenges.Employee> two = List.of(
                    new CollectorChallenges.Employee("Zara",  "HR", 50000, 1),
                    new CollectorChallenges.Employee("Alice", "HR", 80000, 3)
            );
            Map<String, String> result = CollectorChallenges.challenge6(two);
            assertEquals("Alice, Zara", result.get("HR")); // salary DESC: 80000 first
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge6(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — teeing: total salary + count above average
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "Engineering", 92000, 6)
            );
            // total=402000, avg=80400
            // above avg (>80400): Alice(95000),Carol(85000),Eve(92000) → count=3
            CollectorChallenges.Result result = CollectorChallenges.challenge7(employees);

            assertEquals(402000.0, result.totalSalary(),       0.01);
            assertEquals(3L,       result.countAboveAverage());
        }

        @Test
        void noneAboveAverage() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 80000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   80000, 2)
            );
            // total=160000, avg=80000
            // above avg (>80000): NONE (both equal avg, not strictly above)
            CollectorChallenges.Result result = CollectorChallenges.challenge7(employees);

            assertEquals(160000.0, result.totalSalary(), 0.01);
            assertEquals(0L,       result.countAboveAverage());
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5)
            );
            // total=95000, avg=95000, above avg: none (not strictly above own avg)
            CollectorChallenges.Result result = CollectorChallenges.challenge7(single);

            assertEquals(95000.0, result.totalSalary(), 0.01);
            assertEquals(0L,      result.countAboveAverage());
        }

        @Test
        void allAboveAverage() {
            List<CollectorChallenges.Employee> employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Eng", 100000, 5),
                    new CollectorChallenges.Employee("Bob",   "Eng",  80000, 2),
                    new CollectorChallenges.Employee("Carol", "Eng",  60000, 3)
            );
            // total=240000, avg=80000
            // above avg (>80000): Alice(100000) only → count=1
            CollectorChallenges.Result result = CollectorChallenges.challenge7(employees);

            assertEquals(240000.0, result.totalSalary(), 0.01);
            assertEquals(1L,       result.countAboveAverage());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Nested groupingBy: dept → seniority → count
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        private List<CollectorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 8),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "Engineering", 92000, 5),
                    new CollectorChallenges.Employee("Frank", "HR",          55000, 1)
            );
        }

        @Test
        void engineeringCounts() {
            Map<String, Map<String, Long>> result = CollectorChallenges.challenge8(employees);

            Map<String, Long> engMap = result.get("Engineering");
            assertEquals(2L, engMap.get("SENIOR")); // Alice(8yrs),Eve(5yrs)
            assertEquals(1L, engMap.get("JUNIOR")); // Carol(3yrs)
        }

        @Test
        void marketingCounts() {
            Map<String, Map<String, Long>> result = CollectorChallenges.challenge8(employees);

            Map<String, Long> mktMap = result.get("Marketing");
            assertEquals(1L, mktMap.get("SENIOR")); // Diana(7yrs)
            assertEquals(1L, mktMap.get("JUNIOR")); // Bob(2yrs)
        }

        @Test
        void hrAllJunior() {
            Map<String, Map<String, Long>> result = CollectorChallenges.challenge8(employees);

            Map<String, Long> hrMap = result.get("HR");
            // Frank has 1 year → JUNIOR
            // HR may not have SENIOR key at all
            assertEquals(1L, hrMap.getOrDefault("JUNIOR", 0L));
            assertEquals(0L, hrMap.getOrDefault("SENIOR", 0L));
        }

        @Test
        void exactlyAtBoundary5YearsIsSenior() {
            List<CollectorChallenges.Employee> boundary = List.of(
                    new CollectorChallenges.Employee("Test", "Dept", 80000, 5) // exactly 5 → SENIOR
            );
            Map<String, Map<String, Long>> result = CollectorChallenges.challenge8(boundary);

            assertEquals(1L, result.get("Dept").get("SENIOR"));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge8(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — LinkedHashMap ordered by dept total salary DESC, values sorted by salary DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private List<CollectorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "Engineering", 92000, 6)
            );
        }

        @Test
        void departmentOrderByTotalSalary() {
            LinkedHashMap<String, List<CollectorChallenges.Employee>> result =
                    CollectorChallenges.challenge9(employees);

            List<String> keys = new ArrayList<>(result.keySet());
            // Engineering total=272000, Marketing total=130000
            assertEquals("Engineering", keys.get(0)); // richest dept first
            assertEquals("Marketing",   keys.get(1));
        }

        @Test
        void employeesSortedBySalaryDesc() {
            LinkedHashMap<String, List<CollectorChallenges.Employee>> result =
                    CollectorChallenges.challenge9(employees);

            List<CollectorChallenges.Employee> eng = result.get("Engineering");
            assertEquals("Alice", eng.get(0).name()); // 95000
            assertEquals("Eve",   eng.get(1).name()); // 92000
            assertEquals("Carol", eng.get(2).name()); // 85000

            List<CollectorChallenges.Employee> mkt = result.get("Marketing");
            assertEquals("Diana", mkt.get(0).name()); // 70000
            assertEquals("Bob",   mkt.get(1).name()); // 60000
        }

        @Test
        void isLinkedHashMap() {
            LinkedHashMap<String, List<CollectorChallenges.Employee>> result =
                    CollectorChallenges.challenge9(employees);

            assertInstanceOf(LinkedHashMap.class, result);
        }

        @Test
        void singleDepartment() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3)
            );
            LinkedHashMap<String, List<CollectorChallenges.Employee>> result =
                    CollectorChallenges.challenge9(single);

            assertEquals(1, result.size());
            assertEquals("Alice", result.get("Engineering").get(0).name());
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge9(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Build DeptSummary per department
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<CollectorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5),
                    new CollectorChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new CollectorChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new CollectorChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new CollectorChallenges.Employee("Eve",   "Engineering", 92000, 6)
            );
        }

        @Test
        void engineeringSummary() {
            Map<String, CollectorChallenges.DeptSummary> result = CollectorChallenges.challenge10(employees);

            CollectorChallenges.DeptSummary eng = result.get("Engineering");
            assertEquals(3L,       eng.employeeCount());
            assertEquals(272000.0, eng.totalSalary(),   0.01);
            assertEquals(90666.67, eng.averageSalary(), 0.01);
            assertEquals("Alice",  eng.highestPaidName()); // 95000
            assertEquals("Carol",  eng.lowestPaidName());  // 85000
            assertEquals("Alice, Eve, Carol", eng.namesByRank()); // 95000,92000,85000
        }

        @Test
        void marketingSummary() {
            Map<String, CollectorChallenges.DeptSummary> result = CollectorChallenges.challenge10(employees);

            CollectorChallenges.DeptSummary mkt = result.get("Marketing");
            assertEquals(2L,       mkt.employeeCount());
            assertEquals(130000.0, mkt.totalSalary(),   0.01);
            assertEquals(65000.0,  mkt.averageSalary(), 0.01);
            assertEquals("Diana",  mkt.highestPaidName()); // 70000
            assertEquals("Bob",    mkt.lowestPaidName());  // 60000
            assertEquals("Diana, Bob", mkt.namesByRank());
        }

        @Test
        void singleEmployeeDept() {
            List<CollectorChallenges.Employee> single = List.of(
                    new CollectorChallenges.Employee("Alice", "Engineering", 95000, 5)
            );
            Map<String, CollectorChallenges.DeptSummary> result = CollectorChallenges.challenge10(single);

            CollectorChallenges.DeptSummary eng = result.get("Engineering");
            assertEquals(1L,      eng.employeeCount());
            assertEquals(95000.0, eng.totalSalary(), 0.01);
            assertEquals(95000.0, eng.averageSalary(), 0.01);
            assertEquals("Alice", eng.highestPaidName());
            assertEquals("Alice", eng.lowestPaidName());
            assertEquals("Alice", eng.namesByRank());
        }

        @Test
        void correctDepartmentCount() {
            Map<String, CollectorChallenges.DeptSummary> result = CollectorChallenges.challenge10(employees);

            assertEquals(2, result.size()); // Engineering + Marketing
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges.challenge10(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectorChallenges.challenge10(null));
        }
    }
}