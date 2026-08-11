package dev.perfectbogus.lists.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListSorting {

    public static void main(String[] args) {
        List<List<Integer>> requests = new ArrayList<>(List.of(
                List.of(100, 1, 4, 5 ),
                List.of(101, 5, 9, 10),
                List.of(102, 6, 5, 15),
                List.of(103, 2, 3, 20)
        ));

        // Lambda
        Comparator<String> compLambda = (a, b) -> a.compareTo(b);
        // Method Reference
        Comparator<String> compMethod = String::compareTo;
        // Comparator Factory
        Comparator<String> compFactory = Comparator.naturalOrder();

        List<Integer> numbers = new ArrayList<>(
                List.of(5, 2, 8, 1, 9, 3));

        numbers.sort((a, b) -> Integer.compare(b, a));

        System.out.println(numbers);

        List<String> names = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob", "Diana"));

        names.sort(String::compareTo);

        System.out.println(names);

        List<String> names2 = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob", "Diana"));

        names2.sort((a, b) -> b.compareTo(a));

        System.out.println(names2);

        List<String> names3 = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob", "Diana"));

        // Sorting by Size of the word (ascending)
        names3.sort((a, b) -> Integer.compare(a.length(), b.length()));

        System.out.println(names3);

        List<String> names4 = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob", "Diana"));
        // Sorting by Size of the word (descending)
        names4.sort((a, b) -> Integer.compare(b.length(), a.length()));

        System.out.println(names4);

        // Comparator Factory Methods
        List<Integer> nums = new ArrayList<>(List.of(5, 2, 8, 1, 9));

        nums.sort(Comparator.naturalOrder());
        System.out.println(nums);

        nums.sort(Comparator.reverseOrder());
        System.out.println(nums);

        List<String> names5 = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob", "Diana"));
        names5.sort(Comparator.naturalOrder());
        System.out.println(names5);
        names5.sort(Comparator.reverseOrder());
        System.out.println(names5);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 90000.00, 5));
        employees.add(new Employee("Bob", 85000.00, 4));
        employees.add(new Employee("Charlie", 80000.00, 3));

        employees.sort(Comparator.comparingDouble(Employee::salary));
        System.out.println(employees);

        employees.sort(Comparator.comparingInt(Employee::yoe));
        System.out.println(employees);

        employees.sort(Comparator.comparingDouble(Employee::salary).reversed());
        System.out.println(employees);

        List<String> names6 = new ArrayList<>(
                List.of("Charlie", "Alice", "Bob"));

        names6.sort(Comparator.<String, String>comparing(s -> s));
        System.out.println(names6);

        names6.sort(Comparator.<String, String>comparing(s -> s).reversed());
        System.out.println(names6);

        // Chaining Comparators
        List<Employee> employees2 = new ArrayList<>(List.of(
                new Employee("Alice",   95000.0, 5),
                new Employee("Bob",     85000.0, 3),
                new Employee("Carol",   85000.0, 7),  // same salary as Bob!
                new Employee("Diana",   72000.0, 4)
        ));

        employees2.sort(
                Comparator.comparingDouble(Employee::salary).reversed().thenComparing(Employee::name)
        );
        System.out.println(employees2);

        List<Employee> employees3 = new ArrayList<>(List.of(
                new Employee("Alice",   95000.0, 5),
                new Employee("Bob",     85000.0, 3),
                new Employee("Carol",   85000.0, 7),  // same salary as Bob!
                new Employee("Diana",   72000.0, 4)
        ));

        employees3.sort(
                Comparator.comparingDouble(Employee::salary)
                        .thenComparing(Employee::yoe)
                        .thenComparing(Employee::name)
        );

        System.out.println(employees3);

        employees3.sort(
                Comparator.comparingDouble(Employee::salary).reversed()
                        .thenComparing(Employee::yoe).reversed()
        );
        System.out.println(employees3);

        // Null Handling
        List<String> names8 = new ArrayList<>(Arrays.asList("Charlie", null, "Alice", null, "Bob"));
        names8.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(names8);
        names8.sort(Comparator.nullsFirst(Comparator.naturalOrder()));

        // Comparator with Arrays
        int [][] matrix = {{3, 1}, {1, 2}, {2, 3}, {4, 1}};
        Arrays.sort(matrix, Comparator.comparingInt(a -> a[1]));
        System.out.println(Arrays.deepToString(matrix));

        int [][] matrix2 = {{3, 1}, {1, 2}, {2, 3}, {4, 1}};
        Arrays.sort(matrix2, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
            return Integer.compare(a[1], b[1]);
        });
        System.out.println("Sort a Matrix:");
        System.out.println(Arrays.deepToString(matrix2));

    }
}
