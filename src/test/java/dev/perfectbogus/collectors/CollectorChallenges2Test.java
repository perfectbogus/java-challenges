package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — toMap with merge → find dept with highest payroll
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 80000, 3),
                    new CollectorChallenges2.Employee("Carol", "Mkt", 70000, 4),
                    new CollectorChallenges2.Employee("Diana", "Mkt", 60000, 2),
                    new CollectorChallenges2.Employee("Eve",   "HR",  75000, 7)
            );
            assertEquals("Eng=170000.00",
                    CollectorChallenges2.challenge1(employees));
        }

        @Test
        void singleDept() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 80000, 3)
            );
            assertEquals("Eng=170000.00",
                    CollectorChallenges2.challenge1(employees));
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000, 5));
            assertEquals("Eng=95000.00",
                    CollectorChallenges2.challenge1(employees));
        }

        @Test
        void threeDepts() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "HR",  200000, 5),
                    new CollectorChallenges2.Employee("B", "Eng",  90000, 3),
                    new CollectorChallenges2.Employee("C", "Mkt",  80000, 4)
            );
            assertEquals("HR=200000.00",
                    CollectorChallenges2.challenge1(employees));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge1(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge1(List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — groupingBy + summarizingDouble → top dept by avg
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 70000, 3),
                    new CollectorChallenges2.Employee("Carol", "Mkt", 60000, 4)  // ← 60000 not 80000!
            );
            // Eng avg = (90000+70000)/2 = 80000 ← clear winner!
            // Mkt avg = 60000

            CollectorChallenges2.StatsResult result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(2L,      result.statsMap().get("Eng").getCount());
            assertEquals(80000.0, result.statsMap().get("Eng").getAverage(), 0.01);
            assertTrue(result.topDept().startsWith("Eng:"));
            assertTrue(result.topDept().contains("avg=80000.00"));
            assertTrue(result.topDept().contains("count=2"));
        }

        @Test
        void clearWinner() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "HR",  200000, 5),
                    new CollectorChallenges2.Employee("B", "Eng",  50000, 3)
            );
            CollectorChallenges2.StatsResult result =
                    CollectorChallenges2.challenge2(employees);

            assertTrue(result.topDept().startsWith("HR:"));
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 80000, 5));

            CollectorChallenges2.StatsResult result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(1, result.statsMap().size());
            assertTrue(result.topDept().startsWith("Eng:"));
            assertTrue(result.topDept().contains("count=1"));
        }

        @Test
        void statsMapContainsAllDepts() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt", 80000, 3),
                    new CollectorChallenges2.Employee("C", "HR",  70000, 4)
            );
            CollectorChallenges2.StatsResult result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(3, result.statsMap().size());
            assertTrue(result.statsMap().containsKey("Eng"));
            assertTrue(result.statsMap().containsKey("Mkt"));
            assertTrue(result.statsMap().containsKey("HR"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge2(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge2(List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — partitioningBy + mapping + joining
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000, 2),
                    new CollectorChallenges2.Employee("Carol", "Eng", 85000, 3),
                    new CollectorChallenges2.Employee("Diana", "HR",  70000, 7)
            );
            CollectorChallenges2.PartitionReport result =
                    CollectorChallenges2.challenge3(employees, 75000);

            assertEquals("Alice | Carol", result.aboveThreshold());
            assertEquals("Bob | Diana",   result.belowOrEqual());
        }

        @Test
        void noneAbove() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000, 2)
            );
            CollectorChallenges2.PartitionReport result =
                    CollectorChallenges2.challenge3(employees, 99999);

            assertEquals("NONE",           result.aboveThreshold());
            assertEquals("Alice | Bob",    result.belowOrEqual());
        }

        @Test
        void noneBelow() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 80000, 2)
            );
            CollectorChallenges2.PartitionReport result =
                    CollectorChallenges2.challenge3(employees, 0);

            assertEquals("Alice | Bob", result.aboveThreshold());
            assertEquals("NONE",        result.belowOrEqual());
        }

        @Test
        void sortedAlphabetically() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Zara",  "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("Alice", "Mkt", 80000, 2)
            );
            CollectorChallenges2.PartitionReport result =
                    CollectorChallenges2.challenge3(employees, 0);

            assertEquals("Alice | Zara", result.aboveThreshold()); // sorted!
        }

        @Test
        void exactThresholdGoesBelow() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 75000, 5)
            );
            CollectorChallenges2.PartitionReport result =
                    CollectorChallenges2.challenge3(employees, 75000);

            assertEquals("NONE",  result.aboveThreshold()); // 75000 NOT > 75000!
            assertEquals("Alice", result.belowOrEqual());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge3(null, 75000));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — teeing to find min AND max in one pass
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000, 5),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000, 2),
                    new CollectorChallenges2.Employee("Carol", "Eng", 85000, 3),
                    new CollectorChallenges2.Employee("Diana", "HR",  70000, 7)
            );
            assertEquals("lowest=Bob(60000) highest=Alice(95000)",
                    CollectorChallenges2.challenge4(employees));
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 80000, 5));
            assertEquals("lowest=Alice(80000) highest=Alice(80000)",
                    CollectorChallenges2.challenge4(employees));
        }

        @Test
        void twoEmployees() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Zara",  "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("Alice", "Mkt", 50000, 2)
            );
            assertEquals("lowest=Alice(50000) highest=Zara(90000)",
                    CollectorChallenges2.challenge4(employees));
        }

        @Test
        void largerList() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 70000,  5),
                    new CollectorChallenges2.Employee("B", "Mkt", 30000,  2),
                    new CollectorChallenges2.Employee("C", "HR",  90000,  3),
                    new CollectorChallenges2.Employee("D", "Eng", 50000,  7),
                    new CollectorChallenges2.Employee("E", "Mkt", 110000, 1)
            );
            assertEquals("lowest=B(30000) highest=E(110000)",
                    CollectorChallenges2.challenge4(employees));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge4(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge4(List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Custom Collector.of() for median salary
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void oddSizeList() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 95000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt", 60000, 2),
                    new CollectorChallenges2.Employee("C", "Eng", 85000, 3),
                    new CollectorChallenges2.Employee("D", "HR",  70000, 7),
                    new CollectorChallenges2.Employee("E", "Mkt", 80000, 4)
            );
            // sorted: [60000,70000,80000,85000,95000] → median=80000
            assertEquals(80000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void evenSizeList() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 90000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt", 60000, 2),
                    new CollectorChallenges2.Employee("C", "Eng", 80000, 3),
                    new CollectorChallenges2.Employee("D", "HR",  70000, 7)
            );
            // sorted: [60000,70000,80000,90000] → (70000+80000)/2=75000
            assertEquals(75000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 80000, 5));
            assertEquals(80000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void twoEmployees() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 100000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt",  50000, 2)
            );
            // even size → (50000+100000)/2 = 75000
            assertEquals(75000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void allSameSalary() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 80000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt", 80000, 2),
                    new CollectorChallenges2.Employee("C", "HR",  80000, 3)
            );
            assertEquals(80000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void threeEmployees() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 30000, 5),
                    new CollectorChallenges2.Employee("B", "Mkt", 10000, 2),
                    new CollectorChallenges2.Employee("C", "HR",  20000, 3)
            );
            // sorted: [10000,20000,30000] → median=20000
            assertEquals(20000.0, CollectorChallenges2.challenge5(employees), 0.01);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge5(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge5(List.of()));
        }
    }
}