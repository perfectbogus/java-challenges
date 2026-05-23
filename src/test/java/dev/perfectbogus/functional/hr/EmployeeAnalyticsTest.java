package dev.perfectbogus.functional.hr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAnalyticsTest {

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = List.of(
                new Employee("E001", "Alice",   "Engineering", "Senior Dev",    95000.0, 8,  EmployeeStatus.ACTIVE,   List.of("Java", "Python", "SQL")),
                new Employee("E002", "Bob",     "Engineering", "Junior Dev",    60000.0, 2,  EmployeeStatus.ACTIVE,   List.of("Java", "JavaScript")),
                new Employee("E003", "Carol",   "Engineering", "Tech Lead",    110000.0, 12, EmployeeStatus.ACTIVE,   List.of("Java", "Python", "Kubernetes")),
                new Employee("E004", "David",   "HR",          "HR Manager",    72000.0, 6,  EmployeeStatus.ACTIVE,   List.of("Excel", "Communication")),
                new Employee("E005", "Eve",     "HR",          "HR Analyst",    55000.0, 3,  EmployeeStatus.ON_LEAVE, List.of("Excel", "SQL")),
                new Employee("E006", "Frank",   "Marketing",   "Marketing Lead",80000.0, 7,  EmployeeStatus.ACTIVE,   List.of("SEO", "Communication", "SQL")),
                new Employee("E007", "Grace",   "Marketing",   "Designer",      65000.0, 4,  EmployeeStatus.RESIGNED, List.of("Figma", "CSS")),
                new Employee("E008", "Henry",   "Engineering", "Mid Dev",       75000.0, 5,  EmployeeStatus.ON_LEAVE, List.of("Python", "SQL", "Docker")),
                new Employee("E009", "Iris",    "HR",          "Recruiter",     50000.0, 1,  EmployeeStatus.ACTIVE,   List.of("Communication", "Excel")),
                new Employee("E010", "Jack",    "Marketing",   "SEO Analyst",   58000.0, 2,  EmployeeStatus.ACTIVE,   List.of("SEO", "SQL"))
        );
    }

    // -------------------------------------------------------------------------
    // Task 1 — Count employees by status
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - countEmployeesByStatus")
    class Task1 {

        @Test
        @DisplayName("Should return correct count for each status")
        void shouldReturnCorrectCountPerStatus() {
            Map<EmployeeStatus, Long> result = EmployeeAnalytics.countEmployeesByStatus(employees);

            assertEquals(7L, result.get(EmployeeStatus.ACTIVE));
            assertEquals(2L, result.get(EmployeeStatus.ON_LEAVE));
            assertEquals(1L, result.get(EmployeeStatus.RESIGNED));
        }

        @Test
        @DisplayName("Should return empty map for empty list")
        void shouldReturnEmptyMapForEmptyList() {
            Map<EmployeeStatus, Long> result = EmployeeAnalytics.countEmployeesByStatus(List.of());
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.countEmployeesByStatus(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Get all unique skills sorted alphabetically
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - getAllUniqueSkills")
    class Task2 {

        @Test
        @DisplayName("Should return distinct skills sorted alphabetically")
        void shouldReturnDistinctSortedSkills() {
            List<String> result = EmployeeAnalytics.getAllUniqueSkills(employees);

            List<String> expected = List.of(
                    "CSS", "Communication", "Docker", "Excel",
                    "Figma", "Java", "JavaScript", "Kubernetes",
                    "Python", "SEO", "SQL"
            );
            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Should return no duplicates")
        void shouldContainNoDuplicates() {
            List<String> result = EmployeeAnalytics.getAllUniqueSkills(employees);
            long distinct = result.stream().distinct().count();
            assertEquals(result.size(), distinct);
        }

        @Test
        @DisplayName("Should return empty list for empty employees")
        void shouldReturnEmptyListForEmptyInput() {
            List<String> result = EmployeeAnalytics.getAllUniqueSkills(List.of());
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.getAllUniqueSkills(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Average salary per department
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - averageSalaryByDepartment")
    class Task3 {

        @Test
        @DisplayName("Should calculate correct average salary per department")
        void shouldCalculateCorrectAverages() {
            Map<String, Double> result = EmployeeAnalytics.averageSalaryByDepartment(employees);

            // Engineering: (95000 + 60000 + 110000 + 75000) / 4 = 85000.0
            assertEquals(85000.0, result.get("Engineering"), 0.01);

            // HR: (72000 + 55000 + 50000) / 3 = 59000.0
            assertEquals(59000.0, result.get("HR"), 0.01);

            // Marketing: (80000 + 65000 + 58000) / 3 = 67666.67
            assertEquals(67666.67, result.get("Marketing"), 0.01);
        }

        @Test
        @DisplayName("Should contain all departments")
        void shouldContainAllDepartments() {
            Map<String, Double> result = EmployeeAnalytics.averageSalaryByDepartment(employees);
            assertTrue(result.containsKey("Engineering"));
            assertTrue(result.containsKey("HR"));
            assertTrue(result.containsKey("Marketing"));
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.averageSalaryByDepartment(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Highest paid employee per department
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - highestPaidByDepartment")
    class Task4 {

        @Test
        @DisplayName("Should return highest paid employee per department")
        void shouldReturnHighestPaidPerDepartment() {
            Map<String, Optional<Employee>> result = EmployeeAnalytics.highestPaidByDepartment(employees);

            assertEquals("Carol",  result.get("Engineering").map(Employee::name).orElse(""));
            assertEquals("David",  result.get("HR").map(Employee::name).orElse(""));
            assertEquals("Frank",  result.get("Marketing").map(Employee::name).orElse(""));
        }

        @Test
        @DisplayName("Should return Optional.empty for a department with no employees")
        void shouldReturnEmptyOptionalIfNoEmployees() {
            Map<String, Optional<Employee>> result = EmployeeAnalytics.highestPaidByDepartment(List.of());
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.highestPaidByDepartment(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Active employees names grouped by department
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - activeEmployeeNamesByDepartment")
    class Task5 {

        @Test
        @DisplayName("Should return only ACTIVE employee names per department")
        void shouldReturnOnlyActiveEmployees() {
            Map<String, List<String>> result = EmployeeAnalytics.activeEmployeeNamesByDepartment(employees);

            // Engineering active: Alice, Bob, Carol (Henry is ON_LEAVE)
            assertTrue(result.get("Engineering").containsAll(List.of("Alice", "Bob", "Carol")));
            assertFalse(result.get("Engineering").contains("Henry"));

            // HR active: David, Iris (Eve is ON_LEAVE)
            assertTrue(result.get("HR").containsAll(List.of("David", "Iris")));
            assertFalse(result.get("HR").contains("Eve"));

            // Marketing active: Frank, Jack (Grace is RESIGNED)
            assertTrue(result.get("Marketing").containsAll(List.of("Frank", "Jack")));
            assertFalse(result.get("Marketing").contains("Grace"));
        }

        @Test
        @DisplayName("Should not include resigned or on-leave employees")
        void shouldExcludeNonActiveEmployees() {
            Map<String, List<String>> result = EmployeeAnalytics.activeEmployeeNamesByDepartment(employees);
            result.values().forEach(names -> {
                assertFalse(names.contains("Henry"));
                assertFalse(names.contains("Eve"));
                assertFalse(names.contains("Grace"));
            });
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.activeEmployeeNamesByDepartment(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Total salary cost per department sorted by highest cost
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - totalSalaryCostByDepartment")
    class Task6 {

        @Test
        @DisplayName("Should calculate correct total salary per department")
        void shouldCalculateCorrectTotals() {
            Map<String, Double> result = EmployeeAnalytics.totalSalaryCostByDepartment(employees);

            // Engineering: 95000 + 60000 + 110000 + 75000 = 340000
            assertEquals(340000.0, result.get("Engineering"), 0.01);

            // HR: 72000 + 55000 + 50000 = 177000
            assertEquals(177000.0, result.get("HR"), 0.01);

            // Marketing: 80000 + 65000 + 58000 = 203000
            assertEquals(203000.0, result.get("Marketing"), 0.01);
        }

        @Test
        @DisplayName("Should be sorted by highest cost first")
        void shouldBeSortedByHighestCostFirst() {
            Map<String, Double> result = EmployeeAnalytics.totalSalaryCostByDepartment(employees);
            List<String> keys = List.copyOf(result.keySet());

            // Engineering (340000) > Marketing (203000) > HR (177000)
            assertEquals("Engineering", keys.get(0));
            assertEquals("Marketing",   keys.get(1));
            assertEquals("HR",          keys.get(2));
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.totalSalaryCostByDepartment(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Skill frequency map sorted by most common first
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - skillFrequencyMap")
    class Task7 {

        @Test
        @DisplayName("Should count skill frequency correctly")
        void shouldCountSkillsCorrectly() {
            Map<String, Long> result = EmployeeAnalytics.skillFrequencyMap(employees);

            // SQL appears in: Alice, Eve, Frank, Henry, Jack = 5
            assertEquals(5L, result.get("SQL"));

            // Java appears in: Alice, Bob, Carol = 3
            assertEquals(3L, result.get("Java"));

            // Python appears in: Alice, Carol, Henry = 3
            assertEquals(3L, result.get("Python"));

            // Communication appears in: David, Frank, Iris = 3
            assertEquals(3L, result.get("Communication"));

            // Excel appears in: David, Eve, Iris = 3
            assertEquals(3L, result.get("Excel"));

            // SEO appears in: Frank, Jack = 2
            assertEquals(2L, result.get("SEO"));

            // Kubernetes appears in: Carol = 1
            assertEquals(1L, result.get("Kubernetes"));
        }

        @Test
        @DisplayName("Should be sorted by most common skill first")
        void shouldBeSortedByFrequencyDescending() {
            Map<String, Long> result = EmployeeAnalytics.skillFrequencyMap(employees);
            List<Long> values = List.copyOf(result.values());

            for (int i = 0; i < values.size() - 1; i++) {
                assertTrue(values.get(i) >= values.get(i + 1),
                        "Map should be sorted by frequency descending");
            }
        }

        @Test
        @DisplayName("SQL should be the most common skill")
        void sqlShouldBeMostCommon() {
            Map<String, Long> result = EmployeeAnalytics.skillFrequencyMap(employees);
            String mostCommon = result.keySet().iterator().next();
            assertEquals("SQL", mostCommon);
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.skillFrequencyMap(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Senior employees eligible for promotion
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - employeesEligibleForPromotion")
    class Task8 {

        @Test
        @DisplayName("Should return only ACTIVE employees with > 5 years exp and below dept avg salary")
        void shouldReturnEligibleEmployees() {
            Map<String, List<String>> result = EmployeeAnalytics.employeesEligibleForPromotion(employees);

            // Engineering avg salary = 85000
            // Active employees with > 5 yrs AND salary < 85000:
            //   Alice: 8 yrs, 95000 → above avg → NOT eligible
            //   Bob:   2 yrs, 60000 → below 5 yrs → NOT eligible
            //   Carol: 12 yrs,110000 → above avg → NOT eligible
            //   Henry: ON_LEAVE → excluded
            // Engineering → no eligible employees

            // HR avg salary = 59000
            // Active employees with > 5 yrs AND salary < 59000:
            //   David: 6 yrs, 72000 → above avg → NOT eligible
            //   Iris:  1 yr,  50000 → below 5 yrs → NOT eligible
            //   Eve:   ON_LEAVE → excluded
            // HR → no eligible employees

            // Marketing avg salary = 67666.67
            // Active employees with > 5 yrs AND salary < 67666.67:
            //   Frank: 7 yrs, 80000 → above avg → NOT eligible
            //   Jack:  2 yrs, 58000 → below 5 yrs → NOT eligible
            //   Grace: RESIGNED → excluded
            // Marketing → no eligible employees

            // With the default test data → result should be empty
            assertTrue(result.isEmpty() || result.values().stream().allMatch(List::isEmpty));
        }

        @Test
        @DisplayName("Should return eligible employee when conditions are met")
        void shouldReturnEligibleWhenConditionsMet() {
            // Add a custom employee: ACTIVE, 7 yrs experience, salary 50000
            // Engineering avg with this employee: (340000 + 50000) / 5 = 78000
            // This employee: 7 yrs > 5, salary 50000 < 78000 → ELIGIBLE
            List<Employee> custom = new java.util.ArrayList<>(employees);
            custom.add(new Employee("E011", "Zara", "Engineering", "Dev",
                    50000.0, 7, EmployeeStatus.ACTIVE, List.of("Java")));

            Map<String, List<String>> result = EmployeeAnalytics.employeesEligibleForPromotion(custom);

            assertTrue(result.containsKey("Engineering"));
            assertTrue(result.get("Engineering").contains("Zara"));
        }

        @Test
        @DisplayName("Should not include ON_LEAVE or RESIGNED employees")
        void shouldExcludeNonActiveEmployees() {
            Map<String, List<String>> result = EmployeeAnalytics.employeesEligibleForPromotion(employees);
            result.values().forEach(names -> {
                assertFalse(names.contains("Henry")); // ON_LEAVE
                assertFalse(names.contains("Eve"));   // ON_LEAVE
                assertFalse(names.contains("Grace")); // RESIGNED
            });
        }

        @Test
        @DisplayName("Should throw exception for null input")
        void shouldThrowForNullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EmployeeAnalytics.employeesEligibleForPromotion(null));
        }
    }
}