package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedCollectorsChallengeTest {

    private static AdvancedCollectorsChallenge.Employee emp(String name, String department, int salary) {
        return new AdvancedCollectorsChallenge.Employee(name, department, salary);
    }

    private static List<AdvancedCollectorsChallenge.Employee> team() {
        return List.of(emp("ana", "Sales", 1000), emp("bob", "Sales", 3000), emp("cy", "IT", 2000),
                emp("dee", "IT", 2000), emp("eve", "HR", 1500));
    }

    // ==========================================================
    // CHALLENGE 1: namesByDepartment
    // ==========================================================
    @Nested
    class NamesByDepartmentTests {

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", List.of("ana", "bob"), "IT", List.of("cy", "dee"), "HR", List.of("eve")),
                    AdvancedCollectorsChallenge.namesByDepartment(team()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("HR", List.of("eve")),
                    AdvancedCollectorsChallenge.namesByDepartment(List.of(emp("eve", "HR", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.namesByDepartment(List.of()));
        }

        @Test
        void testNamesKeepTheOrderOfTheList() {
            assertEquals(Map.of("A", List.of("zoe", "ana", "mia")),
                    AdvancedCollectorsChallenge.namesByDepartment(
                            List.of(emp("zoe", "A", 1), emp("ana", "A", 2), emp("mia", "A", 3))));
        }

        @Test
        void testDepartmentsMixedInTheList() {
            assertEquals(Map.of("A", List.of("a1", "a2", "a3"), "B", List.of("b1", "b2")),
                    AdvancedCollectorsChallenge.namesByDepartment(List.of(emp("a1", "A", 1), emp("b1", "B", 1),
                            emp("a2", "A", 1), emp("b2", "B", 1), emp("a3", "A", 1))));
        }

        @Test
        void testRepeatedNamesStay() {
            assertEquals(Map.of("A", List.of("ana", "ana")),
                    AdvancedCollectorsChallenge.namesByDepartment(List.of(emp("ana", "A", 1), emp("ana", "A", 2))));
        }

        @Test
        void testSameNameInDifferentDepartments() {
            assertEquals(Map.of("A", List.of("ana"), "B", List.of("ana")),
                    AdvancedCollectorsChallenge.namesByDepartment(List.of(emp("ana", "A", 1), emp("ana", "B", 1))));
        }

        @Test
        void testTheCaseOfTheDepartmentMatters() {
            assertEquals(Map.of("Sales", List.of("ana"), "sales", List.of("bob")),
                    AdvancedCollectorsChallenge.namesByDepartment(
                            List.of(emp("ana", "Sales", 1), emp("bob", "sales", 1))));
        }

        @Test
        void testOneDepartmentForEveryone() {
            assertEquals(Map.of("A", List.of("x", "y", "z")),
                    AdvancedCollectorsChallenge.namesByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 1), emp("z", "A", 1))));
        }

        @Test
        void testEveryoneInTheirOwnDepartment() {
            assertEquals(Map.of("A", List.of("x"), "B", List.of("y"), "C", List.of("z")),
                    AdvancedCollectorsChallenge.namesByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "B", 1), emp("z", "C", 1))));
        }

        @Test
        void testSalaryDoesNotChangeTheResult() {
            assertEquals(Map.of("A", List.of("x", "y")),
                    AdvancedCollectorsChallenge.namesByDepartment(List.of(emp("x", "A", 9), emp("y", "A", 1))));
        }

        @Test
        void testNumberOfDepartments() {
            assertEquals(3, AdvancedCollectorsChallenge.namesByDepartment(team()).size());
        }
    }

    // ==========================================================
    // CHALLENGE 2: totalSalaryByDepartment
    // ==========================================================
    @Nested
    class TotalSalaryByDepartmentTests {

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", 4000, "IT", 4000, "HR", 1500),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(team()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("HR", 1500),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(List.of(emp("eve", "HR", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.totalSalaryByDepartment(List.of()));
        }

        @Test
        void testExample() {
            assertEquals(Map.of("Sales", 4000, "IT", 2000),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("ana", "Sales", 1000), emp("cy", "IT", 2000), emp("bob", "Sales", 3000))));
        }

        @Test
        void testDepartmentsAreNotMixed() {
            assertEquals(Map.of("A", 1, "B", 100),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "B", 100))));
        }

        @Test
        void testThreeInTheSameDepartment() {
            assertEquals(Map.of("A", 60),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "A", 10), emp("y", "A", 20), emp("z", "A", 30))));
        }

        @Test
        void testZeroSalaries() {
            assertEquals(Map.of("A", 0),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "A", 0), emp("y", "A", 0))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals(Map.of("A", -5),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "A", -10), emp("y", "A", 5))));
        }

        @Test
        void testTheTotalIsNotTheAverage() {
            assertEquals(Map.of("A", 300),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "A", 100), emp("y", "A", 200))));
        }

        @Test
        void testTheTotalIsNotTheCount() {
            assertEquals(Map.of("A", 7),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(List.of(emp("x", "A", 7))));
        }

        @Test
        void testTheCaseOfTheDepartmentMatters() {
            assertEquals(Map.of("a", 1, "A", 2),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(
                            List.of(emp("x", "a", 1), emp("y", "A", 2))));
        }

        @Test
        void testMixedOrder() {
            assertEquals(Map.of("A", 4, "B", 6),
                    AdvancedCollectorsChallenge.totalSalaryByDepartment(List.of(emp("1", "A", 1), emp("2", "B", 2),
                            emp("3", "A", 3), emp("4", "B", 4))));
        }
    }

    // ==========================================================
    // CHALLENGE 3: averageSalaryByDepartment
    // ==========================================================
    @Nested
    class AverageSalaryByDepartmentTests {

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", 2000.0, "IT", 2000.0, "HR", 1500.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(team()));
        }

        @Test
        void testExample() {
            assertEquals(Map.of("Sales", 1500.5, "IT", 2000.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("ana", "Sales", 1000), emp("cy", "IT", 2000), emp("bob", "Sales", 2001))));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("HR", 1500.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(List.of(emp("eve", "HR", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.averageSalaryByDepartment(List.of()));
        }

        @Test
        void testTheAverageHasDecimals() {
            assertEquals(Map.of("A", 1.5),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 2))));
        }

        @Test
        void testNotAnIntegerDivision() {
            assertEquals(Map.of("A", 1000.5),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", 1000), emp("y", "A", 1001))));
        }

        @Test
        void testThreeEmployees() {
            assertEquals(Map.of("A", 2.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 2), emp("z", "A", 3))));
        }

        @Test
        void testRepeatingDecimals() {
            assertEquals(Map.of("A", 55.0 / 3),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", 10), emp("y", "A", 20), emp("z", "A", 25))));
        }

        @Test
        void testBigSalariesDoNotOverflow() {
            assertEquals(Map.of("A", 2.0E9),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", 2_000_000_000), emp("y", "A", 2_000_000_000))));
        }

        @Test
        void testDepartmentsAreNotMixed() {
            assertEquals(Map.of("A", 10.0, "B", 100.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(List.of(emp("x", "A", 10),
                            emp("y", "B", 100), emp("z", "A", 10), emp("w", "B", 100))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals(Map.of("A", -1.5),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(
                            List.of(emp("x", "A", -1), emp("y", "A", -2))));
        }

        @Test
        void testZeroSalaries() {
            assertEquals(Map.of("A", 0.0),
                    AdvancedCollectorsChallenge.averageSalaryByDepartment(List.of(emp("x", "A", 0))));
        }
    }

    // ==========================================================
    // CHALLENGE 4: topEarnerByDepartment
    // ==========================================================
    @Nested
    class TopEarnerByDepartmentTests {

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", "bob", "IT", "cy", "HR", "eve"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(team()));
        }

        @Test
        void testExample() {
            assertEquals(Map.of("Sales", "bob", "IT", "cy"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("ana", "Sales", 1000), emp("bob", "Sales", 3000), emp("cy", "IT", 2000))));
        }

        @Test
        void testTieKeepsTheFirst() {
            assertEquals(Map.of("Sales", "ana"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("ana", "Sales", 500), emp("bob", "Sales", 500))));
        }

        @Test
        void testTieOfThreeKeepsTheFirst() {
            assertEquals(Map.of("A", "x"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", 5), emp("y", "A", 5), emp("z", "A", 5))));
        }

        @Test
        void testTieBetweenTheBestOnesOnly() {
            assertEquals(Map.of("A", "y"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 9), emp("z", "A", 9))));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("HR", "eve"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(List.of(emp("eve", "HR", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.topEarnerByDepartment(List.of()));
        }

        @Test
        void testTheBestIsTheFirst() {
            assertEquals(Map.of("A", "x"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", 9), emp("y", "A", 1), emp("z", "A", 2))));
        }

        @Test
        void testTheBestIsTheLast() {
            assertEquals(Map.of("A", "z"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 2), emp("z", "A", 9))));
        }

        @Test
        void testTheBestIsInTheMiddle() {
            assertEquals(Map.of("A", "y"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", 1), emp("y", "A", 9), emp("z", "A", 2))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals(Map.of("A", "y"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(
                            List.of(emp("x", "A", -5), emp("y", "A", -1))));
        }

        @Test
        void testDepartmentsAreNotMixed() {
            assertEquals(Map.of("A", "x", "B", "w"),
                    AdvancedCollectorsChallenge.topEarnerByDepartment(List.of(emp("x", "A", 5), emp("y", "B", 100),
                            emp("z", "A", 4), emp("w", "B", 101))));
        }

        @Test
        void testTheNameIsReturnedNotTheSalary() {
            assertEquals("ana", AdvancedCollectorsChallenge.topEarnerByDepartment(
                    List.of(emp("ana", "A", 3))).get("A"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: splitBySalary
    // ==========================================================
    @Nested
    class SplitBySalaryTests {

        @Test
        void testTeam() {
            assertEquals(Map.of(true, List.of("bob", "cy", "dee"), false, List.of("ana", "eve")),
                    AdvancedCollectorsChallenge.splitBySalary(team(), 2000));
        }

        @Test
        void testExample() {
            assertEquals(Map.of(false, List.of("ana"), true, List.of("bob", "cy")),
                    AdvancedCollectorsChallenge.splitBySalary(
                            List.of(emp("ana", "Sales", 1000), emp("bob", "IT", 3000), emp("cy", "IT", 2000)), 2000));
        }

        @Test
        void testNobodyReachesTheLimit() {
            assertEquals(Map.of(false, List.of("ana"), true, List.of()),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "Sales", 1000)), 5000));
        }

        @Test
        void testEverybodyReachesTheLimit() {
            assertEquals(Map.of(false, List.of(), true, List.of("ana", "bob")),
                    AdvancedCollectorsChallenge.splitBySalary(
                            List.of(emp("ana", "A", 10), emp("bob", "A", 20)), 5));
        }

        @Test
        void testEmptyListHasBothKeys() {
            assertEquals(Map.of(false, List.of(), true, List.of()),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(), 100));
        }

        @Test
        void testTheLimitItselfGoesToTrue() {
            assertEquals(Map.of(false, List.of(), true, List.of("ana")),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "A", 100)), 100));
        }

        @Test
        void testOneBelowTheLimitGoesToFalse() {
            assertEquals(Map.of(false, List.of("ana"), true, List.of()),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "A", 99)), 100));
        }

        @Test
        void testNamesKeepTheOrderOfTheList() {
            assertEquals(Map.of(false, List.of("z", "b"), true, List.of("y", "a")),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("z", "A", 1), emp("y", "A", 9),
                            emp("b", "A", 2), emp("a", "A", 8)), 5));
        }

        @Test
        void testZeroLimitWithZeroSalary() {
            assertEquals(Map.of(false, List.of(), true, List.of("ana")),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "A", 0)), 0));
        }

        @Test
        void testNegativeLimit() {
            assertEquals(Map.of(false, List.of("ana"), true, List.of("bob")),
                    AdvancedCollectorsChallenge.splitBySalary(
                            List.of(emp("ana", "A", -10), emp("bob", "A", -5)), -5));
        }

        @Test
        void testRepeatedNamesStay() {
            assertEquals(Map.of(false, List.of("ana", "ana"), true, List.of("bob", "bob")),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "A", 1), emp("bob", "A", 9),
                            emp("ana", "A", 2), emp("bob", "A", 8)), 5));
        }

        @Test
        void testDepartmentsDoNotMatter() {
            assertEquals(Map.of(false, List.of("a"), true, List.of("b")),
                    AdvancedCollectorsChallenge.splitBySalary(List.of(emp("a", "X", 1), emp("b", "Y", 10)), 5));
        }

        @Test
        void testTheNamesAreReturnedNotTheEmployees() {
            Object first = AdvancedCollectorsChallenge.splitBySalary(List.of(emp("ana", "A", 10)), 5).get(true).get(0);
            assertEquals("ana", first);
        }

        @Test
        void testBothKeysAreAlwaysThere() {
            assertEquals(2, AdvancedCollectorsChallenge.splitBySalary(List.of(emp("a", "X", 1)), 0).size());
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortedNamesByDepartment
    // ==========================================================
    @Nested
    class SortedNamesByDepartmentTests {

        @Test
        void testExample() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("zoe", "Sales", 1), emp("ana", "Sales", 2), emp("cy", "IT", 3)));
            assertEquals(Map.of("IT", Set.of("cy"), "Sales", Set.of("ana", "zoe")), result);
        }

        @Test
        void testTheDepartmentsAreInOrder() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(team());
            assertEquals(List.of("HR", "IT", "Sales"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testTheNamesAreInOrder() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("zoe", "A", 1), emp("mia", "A", 1), emp("ana", "A", 1)));
            assertEquals(List.of("ana", "mia", "zoe"), new ArrayList<>(result.get("A")));
        }

        @Test
        void testNamesAreOrderedAlphabeticallyNotByLength() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("bo", "A", 1), emp("amanda", "A", 1), emp("cy", "A", 1)));
            assertEquals(List.of("amanda", "bo", "cy"), new ArrayList<>(result.get("A")));
        }

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", Set.of("ana", "bob"), "IT", Set.of("cy", "dee"), "HR", Set.of("eve")),
                    AdvancedCollectorsChallenge.sortedNamesByDepartment(team()));
        }

        @Test
        void testRepeatedNameAppearsOnce() {
            assertEquals(Map.of("B", Set.of("ana")),
                    AdvancedCollectorsChallenge.sortedNamesByDepartment(
                            List.of(emp("ana", "B", 1), emp("ana", "B", 2))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.sortedNamesByDepartment(List.of()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("A", Set.of("x")),
                    AdvancedCollectorsChallenge.sortedNamesByDepartment(List.of(emp("x", "A", 1))));
        }

        @Test
        void testUppercaseComesFirstInNames() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("ana", "A", 1), emp("Zoe", "A", 1)));
            assertEquals(List.of("Zoe", "ana"), new ArrayList<>(result.get("A")));
        }

        @Test
        void testUppercaseComesFirstInDepartments() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("x", "b", 1), emp("y", "Z", 1)));
            assertEquals(List.of("Z", "b"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testSameNameInTwoDepartments() {
            assertEquals(Map.of("A", Set.of("ana"), "B", Set.of("ana")),
                    AdvancedCollectorsChallenge.sortedNamesByDepartment(
                            List.of(emp("ana", "A", 1), emp("ana", "B", 1))));
        }

        @Test
        void testInputInDescendingOrder() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("c", "Z", 1), emp("b", "Y", 1), emp("a", "X", 1)));
            assertEquals(List.of("X", "Y", "Z"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testFirstAndLastWorkOnTheResult() {
            TreeMap<String, TreeSet<String>> result = AdvancedCollectorsChallenge.sortedNamesByDepartment(
                    List.of(emp("c", "A", 1), emp("a", "A", 1), emp("b", "A", 1)));
            assertEquals("a", result.get("A").first());
            assertEquals("c", result.get("A").last());
        }
    }

    // ==========================================================
    // CHALLENGE 7: salarySpread
    // ==========================================================
    @Nested
    class SalarySpreadTests {

        @Test
        void testExample() {
            assertEquals(2000, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("ana", "Sales", 1000), emp("bob", "IT", 3000), emp("cy", "IT", 2000))));
        }

        @Test
        void testTeam() {
            assertEquals(2000, AdvancedCollectorsChallenge.salarySpread(team()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(0, AdvancedCollectorsChallenge.salarySpread(List.of(emp("ana", "Sales", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, AdvancedCollectorsChallenge.salarySpread(List.of()));
        }

        @Test
        void testAllEqual() {
            assertEquals(0, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", 7), emp("b", "Y", 7), emp("c", "Z", 7))));
        }

        @Test
        void testTwoEmployees() {
            assertEquals(5, AdvancedCollectorsChallenge.salarySpread(List.of(emp("a", "X", 5), emp("b", "X", 10))));
        }

        @Test
        void testTheHighestComesFirst() {
            assertEquals(9, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", 10), emp("b", "X", 5), emp("c", "X", 1))));
        }

        @Test
        void testTheHighestComesLast() {
            assertEquals(9, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", 1), emp("b", "X", 5), emp("c", "X", 10))));
        }

        @Test
        void testTheLowestIsNotZero() {
            assertEquals(20, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", 100), emp("b", "X", 120))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals(150, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", -100), emp("b", "X", 50))));
        }

        @Test
        void testAllNegative() {
            assertEquals(8, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", -10), emp("b", "X", -2))));
        }

        @Test
        void testBigSalaries() {
            assertEquals(2_000_000_000, AdvancedCollectorsChallenge.salarySpread(
                    List.of(emp("a", "X", 0), emp("b", "X", 2_000_000_000))));
        }

        @Test
        void testRepeatedHighestAndLowest() {
            assertEquals(8, AdvancedCollectorsChallenge.salarySpread(List.of(emp("a", "X", 2), emp("b", "X", 10),
                    emp("c", "X", 2), emp("d", "X", 10), emp("e", "X", 6))));
        }
    }

    // ==========================================================
    // CHALLENGE 8: salaryReport
    // ==========================================================
    @Nested
    class SalaryReportTests {

        @Test
        void testExample() {
            assertEquals("count=3 min=1000 max=3000 sum=6000 average=2000.0", AdvancedCollectorsChallenge.salaryReport(
                    List.of(emp("ana", "Sales", 1000), emp("bob", "IT", 3000), emp("cy", "IT", 2000))));
        }

        @Test
        void testTeam() {
            assertEquals("count=5 min=1000 max=3000 sum=9500 average=1900.0",
                    AdvancedCollectorsChallenge.salaryReport(team()));
        }

        @Test
        void testOneEmployee() {
            assertEquals("count=1 min=1500 max=1500 sum=1500 average=1500.0",
                    AdvancedCollectorsChallenge.salaryReport(List.of(emp("ana", "Sales", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals("count=0 min=0 max=0 sum=0 average=0.0", AdvancedCollectorsChallenge.salaryReport(List.of()));
        }

        @Test
        void testAverageWithDecimals() {
            assertEquals("count=2 min=1000 max=2001 sum=3001 average=1500.5", AdvancedCollectorsChallenge.salaryReport(
                    List.of(emp("a", "X", 1000), emp("b", "X", 2001))));
        }

        @Test
        void testAllEqual() {
            assertEquals("count=2 min=7 max=7 sum=14 average=7.0",
                    AdvancedCollectorsChallenge.salaryReport(List.of(emp("a", "X", 7), emp("b", "X", 7))));
        }

        @Test
        void testZeroSalaries() {
            assertEquals("count=2 min=0 max=0 sum=0 average=0.0",
                    AdvancedCollectorsChallenge.salaryReport(List.of(emp("a", "X", 0), emp("b", "X", 0))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals("count=2 min=-10 max=-2 sum=-12 average=-6.0",
                    AdvancedCollectorsChallenge.salaryReport(List.of(emp("a", "X", -10), emp("b", "X", -2))));
        }

        @Test
        void testMinAndMaxInTheMiddle() {
            assertEquals("count=4 min=1 max=9 sum=20 average=5.0", AdvancedCollectorsChallenge.salaryReport(
                    List.of(emp("a", "X", 5), emp("b", "X", 1), emp("c", "X", 9), emp("d", "X", 5))));
        }

        @Test
        void testTheSumDoesNotOverflow() {
            assertEquals("count=2 min=1000000000 max=2000000000 sum=3000000000 average=1.5E9",
                    AdvancedCollectorsChallenge.salaryReport(
                            List.of(emp("a", "X", 1_000_000_000), emp("b", "X", 2_000_000_000))));
        }

        @Test
        void testSmallAverage() {
            assertEquals("count=4 min=0 max=1 sum=1 average=0.25", AdvancedCollectorsChallenge.salaryReport(
                    List.of(emp("a", "X", 0), emp("b", "X", 0), emp("c", "X", 0), emp("d", "X", 1))));
        }

        @Test
        void testDepartmentsDoNotMatter() {
            assertEquals("count=2 min=1 max=3 sum=4 average=2.0", AdvancedCollectorsChallenge.salaryReport(
                    List.of(emp("a", "X", 1), emp("b", "Y", 3))));
        }
    }

    // ==========================================================
    // CHALLENGE 9: lowestSalaryByDepartment
    // ==========================================================
    @Nested
    class LowestSalaryByDepartmentTests {

        @Test
        void testExample() {
            assertEquals(Map.of("IT", 2000, "Sales", 800), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("ana", "Sales", 1000), emp("cy", "IT", 2000), emp("bob", "Sales", 800))));
        }

        @Test
        void testTeam() {
            assertEquals(Map.of("HR", 1500, "IT", 2000, "Sales", 1000),
                    AdvancedCollectorsChallenge.lowestSalaryByDepartment(team()));
        }

        @Test
        void testTheDepartmentsAreInOrder() {
            TreeMap<String, Integer> result = AdvancedCollectorsChallenge.lowestSalaryByDepartment(team());
            assertEquals(List.of("HR", "IT", "Sales"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(Map.of("HR", 1500),
                    AdvancedCollectorsChallenge.lowestSalaryByDepartment(List.of(emp("eve", "HR", 1500))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.lowestSalaryByDepartment(List.of()));
        }

        @Test
        void testTheLowestComesFirst() {
            assertEquals(Map.of("A", 1), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", 1), emp("y", "A", 5), emp("z", "A", 9))));
        }

        @Test
        void testTheLowestComesLast() {
            assertEquals(Map.of("A", 1), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", 9), emp("y", "A", 5), emp("z", "A", 1))));
        }

        @Test
        void testTheLowestIsInTheMiddle() {
            assertEquals(Map.of("A", 1), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", 5), emp("y", "A", 1), emp("z", "A", 9))));
        }

        @Test
        void testNegativeSalaries() {
            assertEquals(Map.of("A", -7), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", -3), emp("y", "A", -7), emp("z", "A", 4))));
        }

        @Test
        void testRepeatedLowest() {
            assertEquals(Map.of("A", 2), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", 2), emp("y", "A", 2))));
        }

        @Test
        void testDepartmentsAreNotMixed() {
            assertEquals(Map.of("A", 10, "B", 1), AdvancedCollectorsChallenge.lowestSalaryByDepartment(List.of(
                    emp("x", "A", 10), emp("y", "B", 1), emp("z", "A", 20), emp("w", "B", 30))));
        }

        @Test
        void testUppercaseDepartmentsComeFirst() {
            TreeMap<String, Integer> result = AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "b", 1), emp("y", "Z", 1)));
            assertEquals(List.of("Z", "b"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testZeroIsTheLowest() {
            assertEquals(Map.of("A", 0), AdvancedCollectorsChallenge.lowestSalaryByDepartment(
                    List.of(emp("x", "A", 0), emp("y", "A", 5))));
        }
    }

    // ==========================================================
    // CHALLENGE 10: lettersByWordLength
    // ==========================================================
    @Nested
    class LettersByWordLengthTests {

        @Test
        void testExample() {
            assertEquals(Map.of(1, Set.of('c'), 2, Set.of('a', 'b'), 3, Set.of('x', 'y', 'z')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("ab", "ba", "xyz", "c")));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(Map.of(1, Set.of('a'), 2, Set.of('a', 'A')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("aA", "a")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.lettersByWordLength(List.of()));
        }

        @Test
        void testOneWord() {
            assertEquals(Map.of(3, Set.of('c', 'a', 't')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("cat")));
        }

        @Test
        void testRepeatedLettersInAWord() {
            assertEquals(Map.of(5, Set.of('h', 'e', 'l', 'o')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("hello")));
        }

        @Test
        void testTheLettersOfSeveralWordsArePutTogether() {
            assertEquals(Map.of(3, Set.of('c', 'a', 't', 'd', 'o', 'g')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("cat", "dog")));
        }

        @Test
        void testWordsWithTheSameLetters() {
            assertEquals(Map.of(3, Set.of('a', 'b', 'c')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("abc", "cba", "bca")));
        }

        @Test
        void testLengthsAreNotMixed() {
            assertEquals(Map.of(1, Set.of('a'), 2, Set.of('b', 'c')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("a", "bc", "cb")));
        }

        @Test
        void testSpacesAndDigitsCount() {
            assertEquals(Map.of(3, Set.of('a', ' ', 'b', '1')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("a b", "a1b")));
        }

        @Test
        void testEmptyWord() {
            assertEquals(Map.of(0, Set.of(), 1, Set.of('a')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("", "a")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(Map.of(2, Set.of('o', 'k')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("ok", "ok", "ok")));
        }

        @Test
        void testOnlyLengthsThatAppear() {
            assertEquals(2, AdvancedCollectorsChallenge.lettersByWordLength(List.of("a", "abcd")).size());
        }

        @Test
        void testThreeLengths() {
            assertEquals(Map.of(1, Set.of('z'), 2, Set.of('a', 'b'), 4, Set.of('t', 'e', 's')),
                    AdvancedCollectorsChallenge.lettersByWordLength(List.of("ab", "z", "test", "ba")));
        }
    }

    // ==========================================================
    // CHALLENGE 11: highEarnersByDepartment
    // ==========================================================
    @Nested
    class HighEarnersByDepartmentTests {

        @Test
        void testExample() {
            assertEquals(Map.of("Sales", 2L, "IT", 0L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("ana", "Sales", 1000), emp("bob", "Sales", 3000), emp("cy", "IT", 500)), 1000));
        }

        @Test
        void testTeam() {
            assertEquals(Map.of("Sales", 1L, "IT", 2L, "HR", 0L),
                    AdvancedCollectorsChallenge.highEarnersByDepartment(team(), 2000));
        }

        @Test
        void testSalaryEqualToTheMinimumCounts() {
            assertEquals(Map.of("HR", 1L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("eve", "HR", 1500)), 1500));
        }

        @Test
        void testOneBelowTheMinimumDoesNotCount() {
            assertEquals(Map.of("HR", 0L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("eve", "HR", 1499)), 1500));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), AdvancedCollectorsChallenge.highEarnersByDepartment(List.of(), 100));
        }

        @Test
        void testNobodyReachesTheMinimum() {
            assertEquals(Map.of("A", 0L, "B", 0L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 1), emp("y", "B", 2)), 100));
        }

        @Test
        void testEverybodyReachesTheMinimum() {
            assertEquals(Map.of("A", 2L, "B", 1L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 10), emp("y", "B", 20), emp("z", "A", 30)), 5));
        }

        @Test
        void testDepartmentWithOnlyLowEarnersStaysInTheMap() {
            Map<String, Long> result = AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 1), emp("y", "B", 100)), 50);
            assertTrue(result.containsKey("A"));
            assertEquals((Object) 0L, result.get("A"));
        }

        @Test
        void testOnlyTheCountIsReturned() {
            assertEquals((Object) 3L, AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 100), emp("y", "A", 200), emp("z", "A", 300)), 100).get("A"));
        }

        @Test
        void testNegativeMinimum() {
            assertEquals(Map.of("A", 2L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", -5), emp("y", "A", 0), emp("z", "A", -50)), -5));
        }

        @Test
        void testZeroMinimumWithZeroSalary() {
            assertEquals(Map.of("A", 1L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 0)), 0));
        }

        @Test
        void testDepartmentsAreNotMixed() {
            assertEquals(Map.of("A", 1L, "B", 2L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("1", "A", 5), emp("2", "B", 5), emp("3", "A", 1), emp("4", "B", 9)), 5));
        }

        @Test
        void testRepeatedNamesAreDifferentEmployees() {
            assertEquals(Map.of("A", 2L), AdvancedCollectorsChallenge.highEarnersByDepartment(
                    List.of(emp("x", "A", 10), emp("x", "A", 10)), 10));
        }
    }
}