package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedCollectorsChallenge {

    // An employee has a name, the department where he or she works and a salary. In the examples below, an employee is
    // written like this: ana(Sales, 1000). This record is already written, do not change it.
    public record Employee(String name, String department, int salary) {
    }

    // CHALLENGE 1
    // Returns a map from each department to the names of its employees, in the same order they have in the list. Only
    // the departments that appear are in the map. An empty list gives an empty map.
    // Example: namesByDepartment([ana(Sales, 1000), cy(IT, 2000), bob(Sales, 3000)])  returns {Sales=[ana, bob],
    //                                                                                          IT=[cy]}
    //          namesByDepartment([eve(HR, 1500)])                                       returns {HR=[eve]}
    //          namesByDepartment([])                                                    returns {}
    public static Map<String, List<String>> namesByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.mapping(
                        Employee::name,
                        Collectors.toList()
                )
        ));
    }

    // CHALLENGE 2
    // Returns a map from each department to the sum of the salaries of its employees. An empty list gives an empty
    // map.
    // Example: totalSalaryByDepartment([ana(Sales, 1000), cy(IT, 2000), bob(Sales, 3000)])  returns {Sales=4000,
    //                                                                                                IT=2000}
    //          totalSalaryByDepartment([eve(HR, 1500)])                                     returns {HR=1500}
    //          totalSalaryByDepartment([])                                                  returns {}
    public static Map<String, Integer> totalSalaryByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.toMap(
                Employee::department,
                Employee::salary,
                Integer::sum,
                HashMap::new
        ));
    }

    public static Map<String, Integer> totalSalaryByDepartmentSimplified(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.summingInt(Employee::salary)
        ));
    }
    // CHALLENGE 3
    // Returns a map from each department to the average salary of its employees, as a decimal number. An empty list
    // gives an empty map.
    // Example: averageSalaryByDepartment([ana(Sales, 1000), cy(IT, 2000), bob(Sales, 2001)])  returns {Sales=1500.5,
    //                                                                                                  IT=2000.0}
    //          averageSalaryByDepartment([eve(HR, 1500)])                                     returns {HR=1500.0}
    //          averageSalaryByDepartment([])                                                  returns {}
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns a map from each department to the name of its employee with the highest salary. If two employees of the
    // same department have the highest salary, the one that comes first in the list is chosen. An empty list gives an
    // empty map.
    // Example: topEarnerByDepartment([ana(Sales, 1000), bob(Sales, 3000), cy(IT, 2000)])  returns {Sales=bob, IT=cy}
    //          topEarnerByDepartment([ana(Sales, 500), bob(Sales, 500)])                  returns {Sales=ana}
    //          topEarnerByDepartment([])                                                  returns {}
    public static Map<String, String> topEarnerByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Splits the names of the employees in two groups: the key true has the names of the employees whose salary is
    // "limit" or more, and the key false has the names of the rest. The names keep the order they have in the list.
    // The map always has both keys, also when one of the groups (or both) is empty.
    // Example: splitBySalary([ana(Sales, 1000), bob(IT, 3000), cy(IT, 2000)], 2000)  returns {false=[ana],
    //                                                                                          true=[bob, cy]}
    //          splitBySalary([ana(Sales, 1000)], 5000)                                returns {false=[ana], true=[]}
    //          splitBySalary([], 100)                                                 returns {false=[], true=[]}
    public static Map<Boolean, List<String>> splitBySalary(List<Employee> employees, int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a map from each department to the set of the names of its employees. The departments are ordered
    // alphabetically in the map, and the names of each department are ordered alphabetically in its set (the normal
    // comparison of texts). If a department has the same name twice, it appears once in its set.
    // Example: sortedNamesByDepartment([zoe(Sales, 1), ana(Sales, 2), cy(IT, 3)])  returns {IT=[cy],
    //                                                                                       Sales=[ana, zoe]}
    //          sortedNamesByDepartment([ana(B, 1), ana(B, 2)])                     returns {B=[ana]}
    //          sortedNamesByDepartment([])                                         returns {}
    public static TreeMap<String, TreeSet<String>> sortedNamesByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the difference between the highest salary and the lowest salary of all the employees. If the list has
    // one employee, or is empty, the result is 0.
    // Example: salarySpread([ana(Sales, 1000), bob(IT, 3000), cy(IT, 2000)])  returns 2000
    //          salarySpread([ana(Sales, 1500)])                               returns 0
    //          salarySpread([])                                               returns 0
    public static int salarySpread(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a text with the statistics of the salaries, written exactly like this: "count=3 min=1000 max=3000
    // sum=6000 average=2000.0". The count is the number of employees, the min and the max are the lowest and the
    // highest salary, the sum is the total, and the average is a decimal number written the way Java writes it by
    // default. If the list is empty, the text is "count=0 min=0 max=0 sum=0 average=0.0".
    // Example: salaryReport([ana(Sales, 1000), bob(IT, 3000), cy(IT, 2000)])  returns
    //                                                          "count=3 min=1000 max=3000 sum=6000 average=2000.0"
    //          salaryReport([ana(Sales, 1500)])                               returns
    //                                                          "count=1 min=1500 max=1500 sum=1500 average=1500.0"
    //          salaryReport([])                                               returns
    //                                                          "count=0 min=0 max=0 sum=0 average=0.0"
    public static String salaryReport(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a map from each department to the lowest salary of its employees. The departments are ordered
    // alphabetically in the map (the normal comparison of texts). An empty list gives an empty map.
    // Example: lowestSalaryByDepartment([ana(Sales, 1000), cy(IT, 2000), bob(Sales, 800)])  returns {IT=2000,
    //                                                                                                Sales=800}
    //          lowestSalaryByDepartment([eve(HR, 1500)])                                    returns {HR=1500}
    //          lowestSalaryByDepartment([])                                                 returns {}
    public static TreeMap<String, Integer> lowestSalaryByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a map from each length to the set of the different letters that appear in the words of that length. The
    // letters of all the words with the same length are put together in one set. The case matters ('a' and 'A' are
    // different letters). Only the lengths that appear are in the map. An empty list gives an empty map.
    // Example: lettersByWordLength([ab, ba, xyz, c])  returns {1=[c], 2=[a, b], 3=[x, y, z]}
    //          lettersByWordLength([aA, a])           returns {1=[a], 2=[a, A]}
    //          lettersByWordLength([])                returns {}
    public static Map<Integer, Set<Character>> lettersByWordLength(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns a map from each department to how many of its employees have a salary of "minSalary" or more. Every
    // department that appears in the list is in the map, also the ones where nobody reaches the minimum (their count
    // is 0). An empty list gives an empty map.
    // Example: highEarnersByDepartment([ana(Sales, 1000), bob(Sales, 3000), cy(IT, 500)], 1000)
    //                                                                                  returns {Sales=2, IT=0}
    //          highEarnersByDepartment([eve(HR, 1500)], 1500)                          returns {HR=1}
    //          highEarnersByDepartment([], 100)                                        returns {}
    public static Map<String, Long> highEarnersByDepartment(List<Employee> employees, int minSalary) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
