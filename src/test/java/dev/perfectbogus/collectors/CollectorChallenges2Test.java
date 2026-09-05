package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorChallenges2Test {

    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000),
                    new CollectorChallenges2.Employee("Carol", "Eng", 85000),
                    new CollectorChallenges2.Employee("Diana", "HR",  70000)
            );
            assertEquals("[Alice, Carol]",
                    CollectorChallenges2.challenge1(employees, 75000));
        }

        @Test
        void noneQualify() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000)
            );
            assertEquals("[]", CollectorChallenges2.challenge1(employees, 99999));
        }

        @Test
        void allQualify() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Charlie", "Eng", 90000),
                    new CollectorChallenges2.Employee("Alice",   "Mkt", 80000)
            );
            assertEquals("[Alice, Charlie]",
                    CollectorChallenges2.challenge1(employees, 0));
        }

        @Test
        void singleQualifying() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 100000));
            assertEquals("[Alice]",
                    CollectorChallenges2.challenge1(employees, 50000));
        }

        @Test
        void emptyList() {
            assertEquals("[]", CollectorChallenges2.challenge1(List.of(), 75000));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge1(null, 75000));
        }
    }

    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 90000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000),
                    new CollectorChallenges2.Employee("Carol", "Eng", 90000)
            );
            CollectorChallenges2.SalaryStats result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(240000.0, result.sum(),     0.01);
            assertEquals(3L,       result.count());
            assertEquals(80000.0,  result.average(), 0.01);
        }

        @Test
        void singleEmployee() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 80000));
            CollectorChallenges2.SalaryStats result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(80000.0, result.sum(),     0.01);
            assertEquals(1L,      result.count());
            assertEquals(80000.0, result.average(), 0.01);
        }

        @Test
        void twoEmployees() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("A", "Eng", 100000),
                    new CollectorChallenges2.Employee("B", "Eng",  50000)
            );
            CollectorChallenges2.SalaryStats result =
                    CollectorChallenges2.challenge2(employees);

            assertEquals(150000.0, result.sum(),     0.01);
            assertEquals(2L,       result.count());
            assertEquals(75000.0,  result.average(), 0.01);
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

    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 70000),
                    new CollectorChallenges2.Employee("Carol", "Mkt", 80000),
                    new CollectorChallenges2.Employee("Diana", "Mkt", 90000)
            );
            Map<String, String> result = CollectorChallenges2.challenge3(employees);

            assertEquals("Alice", result.get("Eng"));
            assertEquals("Diana", result.get("Mkt"));
        }

        @Test
        void singleEmployeePerDept() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000)
            );
            Map<String, String> result = CollectorChallenges2.challenge3(employees);

            assertEquals("Alice", result.get("Eng"));
            assertEquals("Bob",   result.get("Mkt"));
        }

        @Test
        void threeDepartments() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000),
                    new CollectorChallenges2.Employee("Carol", "HR",  75000),
                    new CollectorChallenges2.Employee("Diana", "Eng", 85000)
            );
            Map<String, String> result = CollectorChallenges2.challenge3(employees);

            assertEquals(3,       result.size());
            assertEquals("Alice", result.get("Eng"));
            assertEquals("Bob",   result.get("Mkt"));
            assertEquals("Carol", result.get("HR"));
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges2.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge3(null));
        }
    }

    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 60000),
                    new CollectorChallenges2.Employee("Carol", "Mkt", 80000)
            );
            Map<String, Long> result =
                    CollectorChallenges2.challenge4(employees, 75000);

            assertEquals(1L, result.get("Eng"));
            assertEquals(1L, result.get("Mkt"));
        }

        @Test
        void deptWithZeroCount() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 60000),
                    new CollectorChallenges2.Employee("Carol", "Mkt", 80000)
            );
            Map<String, Long> result =
                    CollectorChallenges2.challenge4(employees, 90000);

            assertEquals(1L, result.get("Eng"));
            assertEquals(0L, result.get("Mkt")); // ← zero but present!
        }

        @Test
        void allQualify() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Eng", 80000)
            );
            Map<String, Long> result =
                    CollectorChallenges2.challenge4(employees, 0);

            assertEquals(2L, result.get("Eng"));
        }

        @Test
        void noneQualify() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000));
            Map<String, Long> result =
                    CollectorChallenges2.challenge4(employees, 99999);

            assertEquals(0L, result.get("Eng")); // ← still in map with 0!
        }

        @Test
        void emptyList() {
            assertTrue(CollectorChallenges2.challenge4(List.of(), 75000).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge4(null, 75000));
        }
    }

    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000),
                    new CollectorChallenges2.Employee("Bob",   "Mkt", 60000),
                    new CollectorChallenges2.Employee("Carol", "Eng", 85000),
                    new CollectorChallenges2.Employee("Diana", "HR",  60000)
            );
            List<CollectorChallenges2.Employee> result =
                    CollectorChallenges2.challenge5(employees);

            assertEquals("Alice", result.get(0).name());
            assertEquals("Carol", result.get(1).name());
            assertEquals("Bob",   result.get(2).name());
            assertEquals("Diana", result.get(3).name());
        }

        @Test
        void isUnmodifiable() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Alice", "Eng", 95000));
            List<CollectorChallenges2.Employee> result =
                    CollectorChallenges2.challenge5(employees);

            assertThrows(UnsupportedOperationException.class,
                    () -> result.add(new CollectorChallenges2.Employee("X","Y",0)));
        }

        @Test
        void allSameSalary() {
            List<CollectorChallenges2.Employee> employees = List.of(
                    new CollectorChallenges2.Employee("Charlie", "Eng", 80000),
                    new CollectorChallenges2.Employee("Alice",   "Mkt", 80000),
                    new CollectorChallenges2.Employee("Bob",     "HR",  80000)
            );
            List<CollectorChallenges2.Employee> result =
                    CollectorChallenges2.challenge5(employees);

            assertEquals("Alice",   result.get(0).name());
            assertEquals("Bob",     result.get(1).name());
            assertEquals("Charlie", result.get(2).name());
        }

        @Test
        void emptyList() {
            List<CollectorChallenges2.Employee> result =
                    CollectorChallenges2.challenge5(List.of());

            assertTrue(result.isEmpty());
            assertThrows(UnsupportedOperationException.class,
                    () -> result.add(new CollectorChallenges2.Employee("X","Y",0)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectorChallenges2.challenge5(null));
        }
    }
}