package dev.perfectbogus.functional.salary.report;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalaryReport {

    // 1. Group employees by department
    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // 2. Average salary per department
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(
                                Employee::getSalary
                        )
                ));
    }

    // 3. Highest paid employee per department
    public static Map<String, Optional<Employee>> highestPaidByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));
    }

    // 4. Count employees per department
    public static Map<String, Long> countByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
    }

    // 5. Total salary budget per department
    public static Map<String, Double> totalSalaryByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
    }

    // 6. Departments with average salary above threshold
    public static List<String> departmentsAboveAverageSalary(List<Employee> employees, double threshold) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        if (threshold < 0) throw new IllegalArgumentException("Threshold cannot be negative");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                )).entrySet().stream()
                .filter(e -> e.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted().toList();
    }

    // 7. Names of employees per department sorted alphabetically
    public static Map<String, List<String>> employeeNamesByDepartmentSorted(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(
                                Employee::getName,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted()
                                                .toList()
                                )
                        )
                ));
    }
}
