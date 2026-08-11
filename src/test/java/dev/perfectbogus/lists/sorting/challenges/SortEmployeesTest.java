package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortEmployeesTest {

    private List<SortEmployees.Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>(List.of(
                new SortEmployees.Employee("Alice",  "Engineering", 95000),
                new SortEmployees.Employee("Bob",    "Marketing",   60000),
                new SortEmployees.Employee("Carol",  "Engineering", 85000),
                new SortEmployees.Employee("Diana",  "Marketing",   70000),
                new SortEmployees.Employee("Eve",    "Engineering", 92000)
        ));
    }

    @Test
    void testDepartmentOrder() {
        List<SortEmployees.Employee> result =
                SortEmployees.sort(employees);

        assertEquals("Engineering", result.get(0).department());
        assertEquals("Engineering", result.get(1).department());
        assertEquals("Engineering", result.get(2).department());
        assertEquals("Marketing",   result.get(3).department());
        assertEquals("Marketing",   result.get(4).department());
    }

    @Test
    void testSalaryWithinDepartment() {
        List<SortEmployees.Employee> result =
                SortEmployees.sort(employees);

        // Engineering sorted by salary descending
        assertEquals("Alice", result.get(0).name());
        assertEquals("Eve",   result.get(1).name());
        assertEquals("Carol", result.get(2).name());

        // Marketing sorted by salary descending
        assertEquals("Diana", result.get(3).name());
        assertEquals("Bob",   result.get(4).name());
    }

    @Test
    void testSingleEmployee() {
        List<SortEmployees.Employee> single =
                new ArrayList<>(List.of(
                        new SortEmployees.Employee("Alice", "HR", 50000)));
        assertEquals(1, SortEmployees.sort(single).size());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortEmployees.sort(null));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortEmployees.sort(
                new ArrayList<>()).isEmpty());
    }
}