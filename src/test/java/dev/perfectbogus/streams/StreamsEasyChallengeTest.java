package dev.perfectbogus.streams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamsEasyChallengeTest {

    private static StreamsEasyChallenge.Employee emp(String name, String department, int age, int salary) {
        return new StreamsEasyChallenge.Employee(name, department, age, salary);
    }

    // ==========================================================
    // CHALLENGE 1: namesOlderThan
    // ==========================================================
    @Nested
    class NamesOlderThanTests {

        private final List<StreamsEasyChallenge.Employee> employees = List.of(
                emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "IT", 40, 6000));

        @Test
        void testReturnsTheNamesOfTheOlderEmployees() {
            assertEquals(List.of("Ana", "Cy"), StreamsEasyChallenge.namesOlderThan(employees, 28));
        }

        @Test
        void testAnEmployeeWithExactlyThatAgeIsNotIncluded() {
            assertEquals(List.of("Cy"), StreamsEasyChallenge.namesOlderThan(employees, 30));
            assertEquals(List.of("Ana", "Cy"), StreamsEasyChallenge.namesOlderThan(employees, 29));
        }

        @Test
        void testNoOneIsOlder() {
            assertEquals(List.of(), StreamsEasyChallenge.namesOlderThan(employees, 40));
        }

        @Test
        void testEveryoneIsOlder() {
            assertEquals(List.of("Ana", "Bob", "Cy"), StreamsEasyChallenge.namesOlderThan(employees, 10));
        }

        @Test
        void testKeepsTheOrderOfTheListAndRepeatedNames() {
            List<StreamsEasyChallenge.Employee> list = List.of(
                    emp("Zed", "IT", 50, 1), emp("Amy", "IT", 60, 1), emp("Zed", "HR", 70, 1));

            assertEquals(List.of("Zed", "Amy", "Zed"), StreamsEasyChallenge.namesOlderThan(list, 10));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamsEasyChallenge.namesOlderThan(List.of(), 20));
        }
    }

    // ==========================================================
    // CHALLENGE 2: allAdults
    // ==========================================================
    @Nested
    class AllAdultsTests {

        @Test
        void testEveryoneIsAnAdult() {
            assertTrue(StreamsEasyChallenge.allAdults(List.of(emp("Ana", "IT", 30, 1), emp("Bob", "HR", 55, 1))));
        }

        @Test
        void testEighteenIsAnAdult() {
            assertTrue(StreamsEasyChallenge.allAdults(List.of(emp("Ana", "IT", 30, 1), emp("Bob", "HR", 18, 1))));
        }

        @Test
        void testSeventeenIsNotAnAdult() {
            assertFalse(StreamsEasyChallenge.allAdults(List.of(emp("Ana", "IT", 30, 1), emp("Bob", "HR", 17, 1))));
        }

        @Test
        void testOneMinorAtTheStartInTheMiddleAndAtTheEnd() {
            assertFalse(StreamsEasyChallenge.allAdults(
                    List.of(emp("A", "IT", 10, 1), emp("B", "IT", 30, 1), emp("C", "IT", 30, 1))));
            assertFalse(StreamsEasyChallenge.allAdults(
                    List.of(emp("A", "IT", 30, 1), emp("B", "IT", 10, 1), emp("C", "IT", 30, 1))));
            assertFalse(StreamsEasyChallenge.allAdults(
                    List.of(emp("A", "IT", 30, 1), emp("B", "IT", 30, 1), emp("C", "IT", 10, 1))));
        }

        @Test
        void testEveryoneIsAMinor() {
            assertFalse(StreamsEasyChallenge.allAdults(List.of(emp("A", "IT", 10, 1), emp("B", "IT", 12, 1))));
        }

        @Test
        void testEmptyListGivesTrue() {
            assertTrue(StreamsEasyChallenge.allAdults(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: firstNameStartingWith
    // ==========================================================
    @Nested
    class FirstNameStartingWithTests {

        private final List<StreamsEasyChallenge.Employee> employees = List.of(
                emp("Bob", "HR", 25, 4000), emp("Ana", "IT", 30, 5000), emp("Alex", "IT", 28, 4500));

        @Test
        void testReturnsTheFirstOneThatMatches() {
            assertEquals("Ana", StreamsEasyChallenge.firstNameStartingWith(employees, 'A'));
        }

        @Test
        void testTheFirstOneInTheListNotTheAlphabeticalOne() {
            List<StreamsEasyChallenge.Employee> list = List.of(
                    emp("Alex", "IT", 28, 4500), emp("Ana", "IT", 30, 5000));

            assertEquals("Alex", StreamsEasyChallenge.firstNameStartingWith(list, 'A'));
        }

        @Test
        void testUppercaseAndLowercaseAreDifferent() {
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(employees, 'a'));
            assertEquals("Bob", StreamsEasyChallenge.firstNameStartingWith(employees, 'B'));
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(employees, 'b'));
        }

        @Test
        void testALetterInTheMiddleOfANameDoesNotCount() {
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(employees, 'n'));
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(employees, 'x'));
        }

        @Test
        void testNoEmployeeStartsWithThatLetter() {
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(employees, 'Z'));
        }

        @Test
        void testTheMatchIsTheLastOneOfTheList() {
            assertEquals("Alex", StreamsEasyChallenge.firstNameStartingWith(
                    List.of(emp("Bob", "HR", 1, 1), emp("Cy", "HR", 1, 1), emp("Alex", "HR", 1, 1)), 'A'));
        }

        @Test
        void testEmptyList() {
            assertEquals("none", StreamsEasyChallenge.firstNameStartingWith(List.of(), 'A'));
        }
    }

    // ==========================================================
    // CHALLENGE 4: evenSquares
    // ==========================================================
    @Nested
    class EvenSquaresTests {

        @Test
        void testSquaresOfTheEvenNumbers() {
            assertEquals(List.of(4, 16, 36), StreamsEasyChallenge.evenSquares(7));
        }

        @Test
        void testNIsEven() {
            assertEquals(List.of(4, 16, 36, 64), StreamsEasyChallenge.evenSquares(8));
            assertEquals(List.of(4), StreamsEasyChallenge.evenSquares(2));
        }

        @Test
        void testNIsIncluded() {
            assertEquals(List.of(4, 16, 36, 64, 100), StreamsEasyChallenge.evenSquares(10));
        }

        @Test
        void testNoEvenNumbers() {
            assertEquals(List.of(), StreamsEasyChallenge.evenSquares(1));
            assertEquals(List.of(), StreamsEasyChallenge.evenSquares(0));
        }

        @Test
        void testNegativeN() {
            assertEquals(List.of(), StreamsEasyChallenge.evenSquares(-5));
        }

        @Test
        void testTheOddSquaresAreNotThere() {
            assertEquals(List.of(4, 16), StreamsEasyChallenge.evenSquares(5));
        }
    }

    // ==========================================================
    // CHALLENGE 5: sumOfDigits
    // ==========================================================
    @Nested
    class SumOfDigitsTests {

        @Test
        void testSumsTheDigits() {
            assertEquals(10, StreamsEasyChallenge.sumOfDigits(1234));
        }

        @Test
        void testNumberWithAZero() {
            assertEquals(16, StreamsEasyChallenge.sumOfDigits(907));
            assertEquals(1, StreamsEasyChallenge.sumOfDigits(1000));
        }

        @Test
        void testZeroAndOneDigit() {
            assertEquals(0, StreamsEasyChallenge.sumOfDigits(0));
            assertEquals(7, StreamsEasyChallenge.sumOfDigits(7));
        }

        @Test
        void testRepeatedDigits() {
            assertEquals(45, StreamsEasyChallenge.sumOfDigits(99999));
            assertEquals(3, StreamsEasyChallenge.sumOfDigits(111));
        }

        @Test
        void testTheBiggestInt() {
            assertEquals(46, StreamsEasyChallenge.sumOfDigits(Integer.MAX_VALUE));
        }
    }

    // ==========================================================
    // CHALLENGE 6: page
    // ==========================================================
    @Nested
    class PageTests {

        private final List<String> items = List.of("a", "b", "c", "d", "e");

        @Test
        void testFirstPage() {
            assertEquals(List.of("a", "b"), StreamsEasyChallenge.page(items, 1, 2));
        }

        @Test
        void testMiddlePage() {
            assertEquals(List.of("c", "d"), StreamsEasyChallenge.page(items, 2, 2));
        }

        @Test
        void testLastPageWithFewerItems() {
            assertEquals(List.of("e"), StreamsEasyChallenge.page(items, 3, 2));
        }

        @Test
        void testPageAfterTheLastOne() {
            assertEquals(List.of(), StreamsEasyChallenge.page(items, 4, 2));
            assertEquals(List.of(), StreamsEasyChallenge.page(items, 100, 2));
        }

        @Test
        void testPageSizeBiggerThanTheList() {
            assertEquals(List.of("a", "b", "c", "d", "e"), StreamsEasyChallenge.page(items, 1, 10));
            assertEquals(List.of(), StreamsEasyChallenge.page(items, 2, 10));
        }

        @Test
        void testPageOfOneItem() {
            assertEquals(List.of("d"), StreamsEasyChallenge.page(items, 4, 1));
        }

        @Test
        void testHugePageNumbersDoNotOverflow() {
            assertEquals(List.of(), StreamsEasyChallenge.page(items, 1073741825, 4));
            assertEquals(List.of(), StreamsEasyChallenge.page(items, Integer.MAX_VALUE, 2));
        }

        @Test
        void testEmptyListAndTheListIsNotModified() {
            List<String> copy = new ArrayList<>(items);

            assertEquals(List.of(), StreamsEasyChallenge.page(List.of(), 1, 3));
            StreamsEasyChallenge.page(copy, 2, 2);

            assertEquals(items, copy);
        }
    }

    // ==========================================================
    // CHALLENGE 7: formatNames
    // ==========================================================
    @Nested
    class FormatNamesTests {

        @Test
        void testNamesBetweenBrackets() {
            assertEquals("[Ana, Bob]", StreamsEasyChallenge.formatNames(
                    List.of(emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000))));
        }

        @Test
        void testOneEmployee() {
            assertEquals("[Ana]", StreamsEasyChallenge.formatNames(List.of(emp("Ana", "IT", 30, 5000))));
        }

        @Test
        void testEmptyList() {
            assertEquals("[]", StreamsEasyChallenge.formatNames(List.of()));
        }

        @Test
        void testKeepsTheOrderOfTheList() {
            assertEquals("[Zed, Amy, Max]", StreamsEasyChallenge.formatNames(
                    List.of(emp("Zed", "IT", 1, 1), emp("Amy", "IT", 1, 1), emp("Max", "IT", 1, 1))));
        }

        @Test
        void testNamesWithSpaces() {
            assertEquals("[Ana Maria, Bob]", StreamsEasyChallenge.formatNames(
                    List.of(emp("Ana Maria", "IT", 1, 1), emp("Bob", "IT", 1, 1))));
        }
    }

    // ==========================================================
    // CHALLENGE 8: totalSalaryByDepartment
    // ==========================================================
    @Nested
    class TotalSalaryByDepartmentTests {

        @Test
        void testSumsTheSalariesOfEachDepartment() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "IT", 40, 6000));

            assertEquals(Map.of("IT", 11000, "HR", 4000), StreamsEasyChallenge.totalSalaryByDepartment(employees));
        }

        @Test
        void testOneDepartment() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "IT", 25, 4000), emp("Cy", "IT", 40, 6000));

            assertEquals(Map.of("IT", 15000), StreamsEasyChallenge.totalSalaryByDepartment(employees));
        }

        @Test
        void testOneEmployeePerDepartment() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "OPS", 40, 6000));

            assertEquals(Map.of("IT", 5000, "HR", 4000, "OPS", 6000),
                    StreamsEasyChallenge.totalSalaryByDepartment(employees));
        }

        @Test
        void testTheDepartmentsAreMixedInTheList() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("A", "IT", 1, 100), emp("B", "HR", 1, 200), emp("C", "IT", 1, 300),
                    emp("D", "HR", 1, 400), emp("E", "IT", 1, 500));

            assertEquals(Map.of("IT", 900, "HR", 600), StreamsEasyChallenge.totalSalaryByDepartment(employees));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), StreamsEasyChallenge.totalSalaryByDepartment(List.of()));
        }

        @Test
        void testTheDepartmentNameIsCaseSensitive() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 100), emp("Bob", "it", 25, 200));

            assertEquals(Map.of("IT", 100, "it", 200), StreamsEasyChallenge.totalSalaryByDepartment(employees));
        }
    }

    // ==========================================================
    // CHALLENGE 9: minAndMaxAge
    // ==========================================================
    @Nested
    class MinAndMaxAgeTests {

        @Test
        void testYoungestAndOldest() {
            assertArrayEquals(new int[]{25, 40}, StreamsEasyChallenge.minAndMaxAge(List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "IT", 40, 6000))));
        }

        @Test
        void testTheOrderOfTheListDoesNotMatter() {
            assertArrayEquals(new int[]{20, 60}, StreamsEasyChallenge.minAndMaxAge(List.of(
                    emp("A", "IT", 60, 1), emp("B", "IT", 35, 1), emp("C", "IT", 20, 1))));
            assertArrayEquals(new int[]{20, 60}, StreamsEasyChallenge.minAndMaxAge(List.of(
                    emp("A", "IT", 20, 1), emp("B", "IT", 35, 1), emp("C", "IT", 60, 1))));
        }

        @Test
        void testOneEmployee() {
            assertArrayEquals(new int[]{30, 30},
                    StreamsEasyChallenge.minAndMaxAge(List.of(emp("Ana", "IT", 30, 5000))));
        }

        @Test
        void testSameAge() {
            assertArrayEquals(new int[]{33, 33}, StreamsEasyChallenge.minAndMaxAge(List.of(
                    emp("A", "IT", 33, 1), emp("B", "HR", 33, 1))));
        }

        @Test
        void testEmptyListGivesZeros() {
            assertArrayEquals(new int[]{0, 0}, StreamsEasyChallenge.minAndMaxAge(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: untilFirstNegative
    // ==========================================================
    @Nested
    class UntilFirstNegativeTests {

        @Test
        void testStopsAtTheFirstNegativeNumber() {
            assertEquals(List.of(1, 2), StreamsEasyChallenge.untilFirstNegative(List.of(1, 2, -3, 4)));
        }

        @Test
        void testNumbersAfterTheFirstNegativeAreNotIncluded() {
            assertEquals(List.of(1), StreamsEasyChallenge.untilFirstNegative(List.of(1, -2, 3, 4, -5, 6)));
        }

        @Test
        void testNoNegativeNumbers() {
            assertEquals(List.of(5, 0, 7), StreamsEasyChallenge.untilFirstNegative(List.of(5, 0, 7)));
        }

        @Test
        void testZeroIsNotNegative() {
            assertEquals(List.of(0, 0), StreamsEasyChallenge.untilFirstNegative(List.of(0, 0, -1)));
        }

        @Test
        void testTheFirstNumberIsNegative() {
            assertEquals(List.of(), StreamsEasyChallenge.untilFirstNegative(List.of(-1, 5)));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamsEasyChallenge.untilFirstNegative(List.of()));
        }

        @Test
        void testKeepsRepeatedNumbersAndTheirOrder() {
            assertEquals(List.of(3, 3, 1, 3),
                    StreamsEasyChallenge.untilFirstNegative(List.of(3, 3, 1, 3, -9, 3)));
        }
    }

    // ==========================================================
    // CHALLENGE 11 (intermediate): topEarnerByDepartment
    // ==========================================================
    @Nested
    class TopEarnerByDepartmentTests {

        @Test
        void testExampleFromTheStatement() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "IT", 40, 6000),
                    emp("Di", "IT", 35, 6000), emp("Eve", "HR", 28, 4500), emp("Flo", "OPS", 50, 7000));

            assertEquals(Map.of("IT", "Cy", "HR", "Eve"), StreamsEasyChallenge.topEarnerByDepartment(employees));
        }

        @Test
        void testDepartmentsWithOneEmployeeAreLeftOut() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "HR", 25, 4000), emp("Cy", "OPS", 40, 6000));

            assertEquals(Map.of(), StreamsEasyChallenge.topEarnerByDepartment(employees));
        }

        @Test
        void testTheFirstOneWinsATie() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 5000), emp("Bob", "IT", 25, 5000), emp("Cy", "IT", 40, 5000));

            assertEquals(Map.of("IT", "Ana"), StreamsEasyChallenge.topEarnerByDepartment(employees));
        }

        @Test
        void testTheFirstOneWinsATieOnlyAmongTheBest() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("Ana", "IT", 30, 4000), emp("Bob", "IT", 25, 6000), emp("Cy", "IT", 40, 6000),
                    emp("Di", "IT", 40, 5000));

            assertEquals(Map.of("IT", "Bob"), StreamsEasyChallenge.topEarnerByDepartment(employees));
        }

        @Test
        void testTheTopEarnerCanBeAnywhereInTheList() {
            assertEquals(Map.of("IT", "Ana"), StreamsEasyChallenge.topEarnerByDepartment(List.of(
                    emp("Ana", "IT", 1, 9000), emp("Bob", "IT", 1, 1000), emp("Cy", "IT", 1, 2000))));
            assertEquals(Map.of("IT", "Cy"), StreamsEasyChallenge.topEarnerByDepartment(List.of(
                    emp("Ana", "IT", 1, 1000), emp("Bob", "IT", 1, 2000), emp("Cy", "IT", 1, 9000))));
            assertEquals(Map.of("IT", "Bob"), StreamsEasyChallenge.topEarnerByDepartment(List.of(
                    emp("Ana", "IT", 1, 1000), emp("Bob", "IT", 1, 9000), emp("Cy", "IT", 1, 2000))));
        }

        @Test
        void testSeveralDepartmentsMixedInTheList() {
            List<StreamsEasyChallenge.Employee> employees = List.of(
                    emp("A", "IT", 1, 100), emp("B", "HR", 1, 900), emp("C", "IT", 1, 300),
                    emp("D", "HR", 1, 400), emp("E", "OPS", 1, 500), emp("F", "OPS", 1, 800));

            assertEquals(Map.of("IT", "C", "HR", "B", "OPS", "F"),
                    StreamsEasyChallenge.topEarnerByDepartment(employees));
        }

        @Test
        void testOnlyTwoEmployeesAreEnough() {
            assertEquals(Map.of("HR", "Bob"), StreamsEasyChallenge.topEarnerByDepartment(List.of(
                    emp("Ana", "HR", 1, 100), emp("Bob", "HR", 1, 200), emp("Cy", "IT", 1, 999))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), StreamsEasyChallenge.topEarnerByDepartment(List.of()));
        }
    }
}