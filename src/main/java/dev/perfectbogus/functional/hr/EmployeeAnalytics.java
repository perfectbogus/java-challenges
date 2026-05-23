package dev.perfectbogus.functional.hr;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeAnalytics {

    // 1. Count employees by status
    public static Map<EmployeeStatus, Long> countEmployeesByStatus(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::status,
                        Collectors.counting()
                ));
    }

    // 2. Get all unique skills across all employees, sorted alphabetically
    public static List<String> getAllUniqueSkills(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");

        return employees.stream()
                .flatMap(e -> e.skills().stream())
                .distinct()
                .sorted()
                .toList();
    }

    // 3. Average salary per department
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));
    }

    // 4. Highest paid employee per department
    public static Map<String, Optional<Employee>> highestPaidByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                ));
    }

    // 5. Active employee names grouped by department
    public static Map<String, List<String>> activeEmployeeNamesByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .filter(e -> e.status() == EmployeeStatus.ACTIVE)
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(
                                Employee::name,
                                Collectors.toList()
                        )
                ));
    }

    // 6. Total salary cost per department sorted by highest cost first
    public static Map<String, Double> totalSalaryCostByDepartment(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // 7. Skill frequency map sorted by most common first
    public static Map<String, Long> skillFrequencyMap(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return employees.stream()
                .flatMap(e -> e.skills().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e, a) -> e,
                        LinkedHashMap::new
                ));
    }

    private static final Predicate<Employee> ACTIVE_EMPLOYEE = (e -> e.status() == EmployeeStatus.ACTIVE);
    private static final Predicate<Employee> FIVE_YEARS = (e -> e.yearsOfExperience() > 5);

    // 8. Senior employees eligible for promotion
    // Criteria: ACTIVE status, more than 5 years of experience, salary below department average
    public static Map<String, List<String>> employeesEligibleForPromotion(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        Map<String, Double> avgPerDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)
                ));
        return employees.stream()
                .filter(ACTIVE_EMPLOYEE)
                .filter(FIVE_YEARS)
                .filter(e -> e.salary() < avgPerDept.get(e.department()))
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(
                                Employee::name,
                                Collectors.toList()
                        )
                ));
    }
}