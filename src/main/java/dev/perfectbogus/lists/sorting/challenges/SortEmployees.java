package dev.perfectbogus.lists.sorting.challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortEmployees {

    record Employee(String name, String department,
                    double salary) {}

    public static List<Employee> sort(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("employees is null");
        // TODO
        // comparing(department) + thenComparing(salary reversed)
        employees.sort(
                Comparator.comparing(Employee::department)
                        .thenComparing(
                                Comparator.comparingDouble(Employee::salary).reversed()
                        )
        );

        return employees;
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new SortEmployees.Employee("Alice",  "Engineering", 95000),
                new SortEmployees.Employee("Bob",    "Marketing",   60000),
                new SortEmployees.Employee("Carol",  "Engineering", 85000),
                new SortEmployees.Employee("Diana",  "Marketing",   70000),
                new SortEmployees.Employee("Eve",    "Engineering", 92000)
        ));

        System.out.println(employees);

        employees.sort((a, b) -> {
            if (!a.department().equals(b.department()))
                return a.department().compareTo(b.department());
            return Double.compare(b.salary(), a.salary());
        });
        System.out.println(employees);


        employees.sort(
                Comparator.comparing(Employee::department)
                .thenComparing((a, b) -> Double.compare(b.salary(), a.salary()))
        );
    }
}
