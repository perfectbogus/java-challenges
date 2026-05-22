package dev.perfectbogus.functional.salary.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SalaryReportTest {

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = List.of(
                new Employee("Alice",   "Engineering", 95000, 5),
                new Employee("Bob",     "Engineering", 85000, 3),
                new Employee("Charlie", "Marketing",   60000, 2),
                new Employee("Diana",   "Marketing",   70000, 4),
                new Employee("Eve",     "Engineering", 92000, 6),
                new Employee("Frank",   "HR",          55000, 1),
                new Employee("Grace",   "HR",          58000, 2)
        );
    }

    // 1. Group employees by department
    @Test
    void testGroupByDepartment() {
        Map<String, List<Employee>> result =
                SalaryReport.groupByDepartment(employees);

        assertEquals(3, result.size());
        assertEquals(3, result.get("Engineering").size());
        assertEquals(2, result.get("Marketing").size());
        assertEquals(2, result.get("HR").size());
    }

    // 2. Average salary per department
    @Test
    void testAverageSalaryByDepartment() {
        Map<String, Double> result =
                SalaryReport.averageSalaryByDepartment(employees);

        assertEquals(90666.67, result.get("Engineering"), 0.01);
        assertEquals(65000.00, result.get("Marketing"),   0.01);
        assertEquals(56500.00, result.get("HR"),          0.01);
    }

    // 3. Highest paid employee per department
    @Test
    void testHighestPaidByDepartment() {
        Map<String, Optional<Employee>> result =
                SalaryReport.highestPaidByDepartment(employees);

        assertEquals("Alice",   result.get("Engineering").get().getName());
        assertEquals("Diana",   result.get("Marketing").get().getName());
        assertEquals("Grace",   result.get("HR").get().getName());
    }

    // 4. Count employees per department
    @Test
    void testCountByDepartment() {
        Map<String, Long> result =
                SalaryReport.countByDepartment(employees);

        assertEquals(3L, result.get("Engineering"));
        assertEquals(2L, result.get("Marketing"));
        assertEquals(2L, result.get("HR"));
    }

    // 5. Total salary budget per department
    @Test
    void testTotalSalaryByDepartment() {
        Map<String, Double> result =
                SalaryReport.totalSalaryByDepartment(employees);

        assertEquals(272000.0, result.get("Engineering"), 0.01);
        assertEquals(130000.0, result.get("Marketing"),   0.01);
        assertEquals(113000.0, result.get("HR"),          0.01);
    }

    // 6. Departments with average salary above threshold
    @Test
    void testDepartmentsAboveAverageSalary() {
        List<String> result =
                SalaryReport.departmentsAboveAverageSalary(employees, 60000);

        System.out.println(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("Engineering"));
        assertTrue(result.contains("Marketing"));
        assertFalse(result.contains("HR"));
    }

    // 7. Names of employees per department sorted alphabetically
    @Test
    void testEmployeeNamesByDepartmentSorted() {
        Map<String, List<String>> result =
                SalaryReport.employeeNamesByDepartmentSorted(employees);

        assertEquals(List.of("Alice", "Bob", "Eve"),
                result.get("Engineering"));
        assertEquals(List.of("Charlie", "Diana"),
                result.get("Marketing"));
        assertEquals(List.of("Frank", "Grace"),
                result.get("HR"));
    }

    // Edge cases
    @Test
    void testEmptyList() {
        assertTrue(SalaryReport.groupByDepartment(List.of()).isEmpty());
        assertTrue(SalaryReport.averageSalaryByDepartment(List.of()).isEmpty());
        assertTrue(SalaryReport.countByDepartment(List.of()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SalaryReport.groupByDepartment(null));
        assertThrows(IllegalArgumentException.class,
                () -> SalaryReport.averageSalaryByDepartment(null));
        assertThrows(IllegalArgumentException.class,
                () -> SalaryReport.countByDepartment(null));
    }

}