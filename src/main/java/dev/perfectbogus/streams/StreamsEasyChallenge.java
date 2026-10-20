package dev.perfectbogus.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsEasyChallenge {

    // Used by challenges 1, 2, 3, 7, 8, 9 and 11. In the examples an employee is written as
    // name(department, age, salary), for example Ana(IT, 30, 5000).
    public record Employee(String name, String department, int age, int salary) {
    }

    // CHALLENGE 1
    // Returns the names of the employees whose age is greater than age (an employee with exactly that age is not
    // included), in the same order they have in employees.
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 25, 4000), Cy(IT, 40, 6000)]
    //          namesOlderThan(employees, 28) returns [Ana, Cy]
    //          namesOlderThan(employees, 40) returns []
    public static List<String> namesOlderThan(List<Employee> employees, int age) {
        return employees.stream().filter(e -> e.age() > age).map(Employee::name).toList();
    }

    // CHALLENGE 2
    // Returns true if every employee is 18 or older. An empty list gives true.
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 18, 4000)]  allAdults(employees) returns true
    //          employees = [Ana(IT, 30, 5000), Bob(HR, 17, 4000)]  allAdults(employees) returns false
    //          employees = []                                      allAdults(employees) returns true
    public static boolean allAdults(List<Employee> employees) {
        return employees.stream().allMatch(e -> e.age() >= 18);
    }

    // CHALLENGE 3
    // Returns the name of the first employee (in the order of the list) whose name starts with the character letter.
    // The letter must be exactly the same, so "a" and "A" are different. If there is no such employee, returns "none".
    // Example: employees = [Bob(HR, 25, 4000), Ana(IT, 30, 5000), Alex(IT, 28, 4500)]
    //          firstNameStartingWith(employees, 'A') returns "Ana"
    //          firstNameStartingWith(employees, 'a') returns "none"
    //          firstNameStartingWith(employees, 'Z') returns "none"
    public static String firstNameStartingWith(List<Employee> employees, char letter) {
        return employees.stream()
                .map(Employee::name)
                .filter(name -> name.charAt(0) == letter)
                .findFirst()
                .orElse("none");
    }

    // CHALLENGE 4
    // Returns the squares of the even numbers between 1 and n (n included), from the smallest to the greatest. If
    // there are no even numbers in that range, the result is empty.
    // Example: evenSquares(7) returns [4, 16, 36]
    //          evenSquares(2) returns [4]
    //          evenSquares(1) returns []
    //          evenSquares(0) returns []
    public static List<Integer> evenSquares(int n) {
        return IntStream.range(1, n + 1).filter(i -> i % 2 == 0).map(i -> i * i).boxed().toList();
    }

    public static List<Integer> evenSquaresImproved(int n) {
        return IntStream.rangeClosed(1, n).filter(i -> i % 2 == 0).map(i -> i * i).boxed().toList();
    }

    // CHALLENGE 5
    // Returns the sum of the digits of number. number is never negative.
    // Example: sumOfDigits(1234) returns 10
    //          sumOfDigits(907)  returns 16
    //          sumOfDigits(0)    returns 0
    public static int sumOfDigitsNoFunctional(int number) {
        String digits = String.valueOf(number);
        int sum = 0;
        for (char n : digits.toCharArray()) {
            sum += Character.getNumericValue(n);
        }
        return sum;
    }

    public static int sumOfDigits(int number) {
        return String.valueOf(number).chars().map(Character::getNumericValue).sum();
    }

    // CHALLENGE 6
    // Returns one page of items. The pages are numbered from 1 and every page has pageSize items, except maybe the
    // last one. A page after the last one is empty. pageNumber and pageSize are at least 1. items is not modified.
    // Example: items = [a, b, c, d, e]
    //          page(items, 1, 2) returns [a, b]
    //          page(items, 2, 2) returns [c, d]
    //          page(items, 3, 2) returns [e]
    //          page(items, 4, 2) returns []
    public static List<String> page(List<String> items, int pageNumber, int pageSize) {
        return items.stream().skip((long) (pageNumber - 1) * pageSize).limit(pageSize).toList();
    }

    // CHALLENGE 7
    // Returns the names of the employees separated by ", " and between square brackets, in the order of the list.
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 25, 4000)]  formatNames(employees) returns "[Ana, Bob]"
    //          employees = [Ana(IT, 30, 5000)]                     formatNames(employees) returns "[Ana]"
    //          employees = []                                      formatNames(employees) returns "[]"
    public static String formatNames(List<Employee> employees) {
        return employees.stream().map(Employee::name).collect(Collectors.joining(", ", "[", "]"));
    }

    // CHALLENGE 8
    // Returns, for each department, the sum of the salaries of its employees. A department that has no employees does
    // not appear in the result.
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 25, 4000), Cy(IT, 40, 6000)]
    //          totalSalaryByDepartment(employees) returns {IT=11000, HR=4000}
    //          totalSalaryByDepartment([])        returns {}
    public static Map<String, Integer> totalSalaryByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.summingInt(Employee::salary)
        ));
    }

    // CHALLENGE 9
    // Returns an array with two numbers: the age of the youngest employee and the age of the oldest one, in that
    // order. If employees is empty, returns [0, 0].
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 25, 4000), Cy(IT, 40, 6000)]
    //          minAndMaxAge(employees) returns [25, 40]
    //          minAndMaxAge([])        returns [0, 0]
    public static int[] minAndMaxAge(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the numbers that come before the first negative number, in the same order. Numbers that come after the
    // first negative number are not included, even if they are positive. If there are no negative numbers, returns
    // all of them. Zero is not negative.
    // Example: untilFirstNegative([1, 2, -3, 4]) returns [1, 2]
    //          untilFirstNegative([5, 0, 7])     returns [5, 0, 7]
    //          untilFirstNegative([-1, 5])       returns []
    public static List<Integer> untilFirstNegative(List<Integer> numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11 (intermediate)
    // Returns, for each department that has at least two employees, the name of the employee with the highest salary
    // in that department. If two employees of a department have the highest salary, the one that comes first in the
    // list is used. Departments with only one employee do not appear in the result.
    // Example: employees = [Ana(IT, 30, 5000), Bob(HR, 25, 4000), Cy(IT, 40, 6000), Di(IT, 35, 6000),
    //                       Eve(HR, 28, 4500), Flo(OPS, 50, 7000)]
    //          topEarnerByDepartment(employees) returns {IT=Cy, HR=Eve}
    //          (Cy and Di earn the same in IT, and Cy comes first. OPS has only Flo, so it is left out.)
    public static Map<String, String> topEarnerByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
