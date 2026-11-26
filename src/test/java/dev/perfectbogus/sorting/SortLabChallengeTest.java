package dev.perfectbogus.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortLabChallengeTest {

    private static final Employee ANA = emp("ana", "Sales", 30, 3000.0);
    private static final Employee BOB = emp("bob", "IT", 25, 4000.0);
    private static final Employee CY = emp("cy", "Sales", 25, 3500.5);
    private static final Employee DAN = emp("dan", "HR", 40, 3000.0);
    private static final Employee EVE = emp("eve", "IT", 30, 5000.0);

    private static Employee emp(String name, String department, int age, double salary) {
        return new Employee(name, department, age, salary);
    }

    private static List<Employee> list(Employee... employees) {
        return new ArrayList<>(Arrays.asList(employees));
    }

    private static List<String> words(String... words) {
        return new ArrayList<>(Arrays.asList(words));
    }

    private static List<Integer> numbers(Integer... numbers) {
        return new ArrayList<>(Arrays.asList(numbers));
    }

    // ==========================================================
    // CHALLENGE 1: sortByAge
    // ==========================================================
    @Nested
    class SortByAgeTests {

        @Test
        void testSample() {
            assertEquals(list(BOB, CY, ANA, EVE, DAN), SortLabChallenge.sortByAge(list(ANA, BOB, CY, DAN, EVE)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), SortLabChallenge.sortByAge(list()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(list(ANA), SortLabChallenge.sortByAge(list(ANA)));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(list(BOB, ANA, DAN), SortLabChallenge.sortByAge(list(BOB, ANA, DAN)));
        }

        @Test
        void testFromTheOldestToTheYoungest() {
            assertEquals(list(CY, EVE, ANA, DAN), SortLabChallenge.sortByAge(list(DAN, EVE, ANA, CY)));
        }

        @Test
        void testTheSameAgeKeepsTheOriginalOrder() {
            List<Employee> same = list(emp("r", "x", 20, 1), emp("q", "x", 20, 2), emp("p", "x", 20, 3));
            assertEquals(same, SortLabChallenge.sortByAge(same));
        }

        @Test
        void testOnlyTheAgeMatters() {
            Employee first = emp("zed", "Z", 20, 9999);
            Employee second = emp("abe", "A", 21, 1);
            assertEquals(list(first, second), SortLabChallenge.sortByAge(list(second, first)));
        }

        @Test
        void testExtremeAges() {
            Employee low = emp("low", "x", Integer.MIN_VALUE, 1);
            Employee high = emp("high", "x", Integer.MAX_VALUE, 1);
            assertEquals(list(low, high), SortLabChallenge.sortByAge(list(high, low)));
        }

        @Test
        void testAgesBiggerThanTheCacheOfIntegers() {
            Employee a = emp("a", "x", 1000, 1);
            Employee b = emp("b", "x", 1000, 1);
            Employee c = emp("c", "x", 999, 1);
            assertEquals(list(c, a, b), SortLabChallenge.sortByAge(list(a, b, c)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Employee> original = list(ANA, BOB, CY);
            List<Employee> result = SortLabChallenge.sortByAge(original);
            assertEquals(list(ANA, BOB, CY), original);
            assertTrue(result != original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", i % 10, 1));
            }
            List<Employee> result = SortLabChallenge.sortByAge(big);
            assertEquals(100_000, result.size());
            assertEquals("e0", result.get(0).name());
            assertEquals("e10", result.get(1).name());
            assertEquals("e1", result.get(10_000).name());
            assertEquals("e99999", result.get(99_999).name());
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortBySalaryDescending
    // ==========================================================
    @Nested
    class SortBySalaryDescendingTests {

        @Test
        void testSample() {
            assertEquals(list(EVE, BOB, CY, ANA, DAN),
                    SortLabChallenge.sortBySalaryDescending(list(ANA, BOB, CY, DAN, EVE)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), SortLabChallenge.sortBySalaryDescending(list()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(list(ANA), SortLabChallenge.sortBySalaryDescending(list(ANA)));
        }

        @Test
        void testTheSameSalaryKeepsTheOriginalOrder() {
            assertEquals(list(DAN, ANA), SortLabChallenge.sortBySalaryDescending(list(DAN, ANA)));
            assertEquals(list(ANA, DAN), SortLabChallenge.sortBySalaryDescending(list(ANA, DAN)));
        }

        @Test
        void testDecimalsAreNotIgnored() {
            Employee low = emp("low", "x", 20, 1000.25);
            Employee high = emp("high", "x", 20, 1000.75);
            assertEquals(list(high, low), SortLabChallenge.sortBySalaryDescending(list(low, high)));
            assertEquals(list(high, low), SortLabChallenge.sortBySalaryDescending(list(high, low)));
        }

        @Test
        void testTinyDifferences() {
            Employee low = emp("low", "x", 20, 0.1);
            Employee high = emp("high", "x", 20, 0.4);
            assertEquals(list(high, low), SortLabChallenge.sortBySalaryDescending(list(low, high)));
        }

        @Test
        void testAlreadySortedFromTheHighest() {
            assertEquals(list(EVE, BOB, ANA), SortLabChallenge.sortBySalaryDescending(list(EVE, BOB, ANA)));
        }

        @Test
        void testSortedFromTheLowest() {
            assertEquals(list(EVE, BOB, CY, ANA), SortLabChallenge.sortBySalaryDescending(list(ANA, CY, BOB, EVE)));
        }

        @Test
        void testNegativeAndZeroSalaries() {
            Employee a = emp("a", "x", 20, -5.5);
            Employee b = emp("b", "x", 20, 0.0);
            Employee c = emp("c", "x", 20, 7.0);
            assertEquals(list(c, b, a), SortLabChallenge.sortBySalaryDescending(list(a, b, c)));
        }

        @Test
        void testHugeSalaries() {
            Employee a = emp("a", "x", 20, 1e300);
            Employee b = emp("b", "x", 20, -1e300);
            assertEquals(list(a, b), SortLabChallenge.sortBySalaryDescending(list(b, a)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Employee> original = list(ANA, BOB, CY);
            SortLabChallenge.sortBySalaryDescending(original);
            assertEquals(list(ANA, BOB, CY), original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", 20, (i * 37) % 100_000));
            }
            List<Employee> result = SortLabChallenge.sortBySalaryDescending(big);
            assertEquals(99_999.0, result.get(0).salary());
            assertEquals(50_000.0, result.get(49_999).salary());
            assertEquals(0.0, result.get(99_999).salary());
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortByDepartmentThenName
    // ==========================================================
    @Nested
    class SortByDepartmentThenNameTests {

        @Test
        void testSample() {
            assertEquals(list(DAN, BOB, EVE, ANA, CY),
                    SortLabChallenge.sortByDepartmentThenName(list(ANA, BOB, CY, DAN, EVE)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), SortLabChallenge.sortByDepartmentThenName(list()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(list(ANA), SortLabChallenge.sortByDepartmentThenName(list(ANA)));
        }

        @Test
        void testSameDepartmentSortsByName() {
            Employee a = emp("zoe", "IT", 20, 1);
            Employee b = emp("amy", "IT", 20, 1);
            Employee c = emp("max", "IT", 20, 1);
            assertEquals(list(b, c, a), SortLabChallenge.sortByDepartmentThenName(list(a, b, c)));
        }

        @Test
        void testDepartmentIsMoreImportantThanName() {
            Employee a = emp("zoe", "A", 20, 1);
            Employee b = emp("amy", "B", 20, 1);
            assertEquals(list(a, b), SortLabChallenge.sortByDepartmentThenName(list(b, a)));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            Employee a = emp("a", "IT", 20, 1);
            Employee b = emp("B", "IT", 20, 1);
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenName(list(a, b)));
            Employee c = emp("x", "it", 20, 1);
            Employee d = emp("y", "IT", 20, 1);
            assertEquals(list(d, c), SortLabChallenge.sortByDepartmentThenName(list(c, d)));
        }

        @Test
        void testSameDepartmentAndNameKeepTheOriginalOrder() {
            Employee a = emp("same", "IT", 50, 1);
            Employee b = emp("same", "IT", 20, 2);
            assertEquals(list(a, b), SortLabChallenge.sortByDepartmentThenName(list(a, b)));
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenName(list(b, a)));
        }

        @Test
        void testAgeAndSalaryAreIgnored() {
            Employee a = emp("a", "IT", 99, 99999);
            Employee b = emp("b", "IT", 1, 1);
            assertEquals(list(a, b), SortLabChallenge.sortByDepartmentThenName(list(b, a)));
        }

        @Test
        void testManyDepartments() {
            Employee a = emp("n", "D", 1, 1);
            Employee b = emp("n", "C", 1, 1);
            Employee c = emp("n", "B", 1, 1);
            Employee d = emp("n", "A", 1, 1);
            assertEquals(list(d, c, b, a), SortLabChallenge.sortByDepartmentThenName(list(a, b, c, d)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Employee> original = list(ANA, BOB, CY, DAN, EVE);
            SortLabChallenge.sortByDepartmentThenName(original);
            assertEquals(list(ANA, BOB, CY, DAN, EVE), original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("n" + (99_999 - i), "d" + i % 2, 20, 1));
            }
            List<Employee> result = SortLabChallenge.sortByDepartmentThenName(big);
            assertEquals("d0", result.get(0).department());
            assertEquals("n1", result.get(0).name());
            assertEquals("d1", result.get(99_999).department());
            assertEquals("n99998", result.get(99_999).name());
            assertTrue(result.get(0).name().compareTo(result.get(1).name()) < 0);
            assertTrue(result.get(50_000).name().compareTo(result.get(50_001).name()) < 0);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortByDepartmentThenSalary
    // ==========================================================
    @Nested
    class SortByDepartmentThenSalaryTests {

        @Test
        void testSample() {
            assertEquals(list(DAN, EVE, BOB, CY, ANA),
                    SortLabChallenge.sortByDepartmentThenSalary(list(ANA, BOB, CY, DAN, EVE)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), SortLabChallenge.sortByDepartmentThenSalary(list()));
        }

        @Test
        void testOneEmployee() {
            assertEquals(list(ANA), SortLabChallenge.sortByDepartmentThenSalary(list(ANA)));
        }

        @Test
        void testSameSalaryGoesByName() {
            Employee a = emp("zoe", "IT", 20, 100);
            Employee b = emp("amy", "IT", 20, 100);
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenSalary(list(a, b)));
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenSalary(list(b, a)));
        }

        @Test
        void testDepartmentIsMoreImportantThanSalary() {
            Employee rich = emp("rich", "Z", 20, 1_000_000);
            Employee poor = emp("poor", "A", 20, 1);
            assertEquals(list(poor, rich), SortLabChallenge.sortByDepartmentThenSalary(list(rich, poor)));
        }

        @Test
        void testSalaryIsMoreImportantThanName() {
            Employee a = emp("aaa", "IT", 20, 1);
            Employee z = emp("zzz", "IT", 20, 2);
            assertEquals(list(z, a), SortLabChallenge.sortByDepartmentThenSalary(list(a, z)));
        }

        @Test
        void testDecimalsAreNotIgnored() {
            Employee low = emp("alow", "IT", 20, 10.25);
            Employee high = emp("zhigh", "IT", 20, 10.75);
            assertEquals(list(high, low), SortLabChallenge.sortByDepartmentThenSalary(list(low, high)));
        }

        @Test
        void testSameDepartmentSalaryAndNameKeepTheOriginalOrder() {
            Employee a = emp("same", "IT", 50, 5);
            Employee b = emp("same", "IT", 20, 5);
            assertEquals(list(a, b), SortLabChallenge.sortByDepartmentThenSalary(list(a, b)));
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenSalary(list(b, a)));
        }

        @Test
        void testSeveralInTwoDepartments() {
            Employee a = emp("a", "B", 1, 10);
            Employee b = emp("b", "B", 1, 30);
            Employee c = emp("c", "A", 1, 20);
            Employee d = emp("d", "A", 1, 20);
            Employee e = emp("e", "A", 1, 40);
            assertEquals(list(e, c, d, b, a), SortLabChallenge.sortByDepartmentThenSalary(list(a, b, c, d, e)));
        }

        @Test
        void testUppercaseNameComesFirstWhenSalaryIsTheSame() {
            Employee a = emp("a", "IT", 20, 100);
            Employee b = emp("B", "IT", 20, 100);
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenSalary(list(a, b)));
        }

        @Test
        void testUppercaseDepartmentComesFirst() {
            Employee a = emp("x", "it", 1, 1);
            Employee b = emp("y", "IT", 1, 1);
            assertEquals(list(b, a), SortLabChallenge.sortByDepartmentThenSalary(list(a, b)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Employee> original = list(ANA, BOB, CY, DAN, EVE);
            SortLabChallenge.sortByDepartmentThenSalary(original);
            assertEquals(list(ANA, BOB, CY, DAN, EVE), original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("n" + i, "d" + i % 2, 20, i));
            }
            List<Employee> result = SortLabChallenge.sortByDepartmentThenSalary(big);
            assertEquals("n99998", result.get(0).name());
            assertEquals("n0", result.get(49_999).name());
            assertEquals("n99999", result.get(50_000).name());
            assertEquals("n1", result.get(99_999).name());
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortRowsByColumn
    // ==========================================================
    @Nested
    class SortRowsByColumnTests {

        @Test
        void testFirstColumn() {
            assertArrayEquals(new int[][] {{1, 2}, {2, 0}, {3, 1}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{3, 1}, {1, 2}, {2, 0}}, 0));
        }

        @Test
        void testSecondColumn() {
            assertArrayEquals(new int[][] {{2, 0}, {3, 1}, {1, 2}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{3, 1}, {1, 2}, {2, 0}}, 1));
        }

        @Test
        void testNoRows() {
            assertArrayEquals(new int[][] {}, SortLabChallenge.sortRowsByColumn(new int[][] {}, 0));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new int[][] {{5, 6}}, SortLabChallenge.sortRowsByColumn(new int[][] {{5, 6}}, 1));
        }

        @Test
        void testTheSameValueKeepsTheOriginalOrder() {
            assertArrayEquals(new int[][] {{1, 9}, {1, 3}, {1, 5}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{1, 9}, {1, 3}, {1, 5}}, 0));
            assertArrayEquals(new int[][] {{9, 1}, {3, 1}, {5, 1}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{9, 1}, {3, 1}, {5, 1}}, 1));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[][] {{-5}, {-1}, {0}, {3}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{0}, {-1}, {3}, {-5}}, 0));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE}, {Integer.MAX_VALUE}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{Integer.MAX_VALUE}, {Integer.MIN_VALUE}}, 0));
            assertArrayEquals(new int[][] {{Integer.MIN_VALUE, 0}, {0, 0}, {Integer.MAX_VALUE, 0}},
                    SortLabChallenge.sortRowsByColumn(
                            new int[][] {{Integer.MAX_VALUE, 0}, {Integer.MIN_VALUE, 0}, {0, 0}}, 0));
        }

        @Test
        void testRowsWithManyColumns() {
            assertArrayEquals(new int[][] {{9, 9, 1, 9}, {0, 0, 2, 0}, {5, 5, 3, 5}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{5, 5, 3, 5}, {9, 9, 1, 9}, {0, 0, 2, 0}}, 2));
        }

        @Test
        void testOnlyTheChosenColumnMatters() {
            assertArrayEquals(new int[][] {{9, 1}, {1, 2}},
                    SortLabChallenge.sortRowsByColumn(new int[][] {{1, 2}, {9, 1}}, 1));
        }

        @Test
        void testOriginalIsNotModified() {
            int[][] rows = {{3, 1}, {1, 2}, {2, 0}};
            SortLabChallenge.sortRowsByColumn(rows, 0);
            assertArrayEquals(new int[][] {{3, 1}, {1, 2}, {2, 0}}, rows);
        }

        @Test
        void testTheRowsAreTheSameObjects() {
            int[] first = {2, 0};
            int[] second = {1, 0};
            int[][] result = SortLabChallenge.sortRowsByColumn(new int[][] {first, second}, 0);
            assertTrue(result[0] == second);
            assertTrue(result[1] == first);
        }

        @Test
        void testBigArray() {
            int[][] rows = new int[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new int[] {(int) ((long) i * 37 % 100_000), i};
            }
            int[][] result = SortLabChallenge.sortRowsByColumn(rows, 0);
            for (int i = 0; i < result.length; i++) {
                assertEquals(i, result[i][0]);
            }
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortByLengthThenAlphabetical
    // ==========================================================
    @Nested
    class SortByLengthThenAlphabeticalTests {

        @Test
        void testExample() {
            assertEquals(words("a", "b", "aa", "bb", "ccc"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("bb", "a", "ccc", "aa", "b")));
        }

        @Test
        void testUppercaseFirst() {
            assertEquals(words("A", "B", "a", "b"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("b", "A", "a", "B")));
        }

        @Test
        void testEmpty() {
            assertEquals(words(), SortLabChallenge.sortByLengthThenAlphabetical(words()));
        }

        @Test
        void testOneWord() {
            assertEquals(words("x"), SortLabChallenge.sortByLengthThenAlphabetical(words("x")));
        }

        @Test
        void testEmptyWordFirst() {
            assertEquals(words("", "a", "ab"), SortLabChallenge.sortByLengthThenAlphabetical(words("ab", "a", "")));
        }

        @Test
        void testLengthIsMoreImportantThanAlphabet() {
            assertEquals(words("z", "aa"), SortLabChallenge.sortByLengthThenAlphabetical(words("aa", "z")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(words("a", "a", "bb", "bb"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("bb", "a", "bb", "a")));
        }

        @Test
        void testSameLengthManyWords() {
            assertEquals(words("cat", "cow", "dog", "pig"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("pig", "cow", "dog", "cat")));
        }

        @Test
        void testWordsThatStartTheSame() {
            assertEquals(words("ab", "ac", "abc", "abd"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("abd", "ac", "abc", "ab")));
        }

        @Test
        void testNumbersAsTexts() {
            assertEquals(words("9", "10", "11", "100"),
                    SortLabChallenge.sortByLengthThenAlphabetical(words("100", "11", "9", "10")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = words("bb", "a");
            SortLabChallenge.sortByLengthThenAlphabetical(original);
            assertEquals(words("bb", "a"), original);
        }

        @Test
        void testBigList() {
            List<String> big = new ArrayList<>();
            for (int i = 99_999; i >= 0; i--) {
                big.add("w" + i);
            }
            List<String> result = SortLabChallenge.sortByLengthThenAlphabetical(big);
            assertEquals("w0", result.get(0));
            assertEquals("w9", result.get(9));
            assertEquals("w10", result.get(10));
            assertEquals("w99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortIgnoringCaseLowerFirst
    // ==========================================================
    @Nested
    class SortIgnoringCaseLowerFirstTests {

        @Test
        void testLetters() {
            assertEquals(words("a", "A", "b", "B"),
                    SortLabChallenge.sortIgnoringCaseLowerFirst(words("b", "A", "a", "B")));
        }

        @Test
        void testWords() {
            assertEquals(words("apple", "apple", "Apple", "Banana", "cherry"),
                    SortLabChallenge.sortIgnoringCaseLowerFirst(words("Banana", "apple", "Apple", "cherry", "apple")));
        }

        @Test
        void testLettersSimplified() {
            assertEquals(words("a", "A", "b", "B"),
                    SortLabChallenge.sortIgnoringCaseLowerFirstSimplified(words("b", "A", "a", "B")));
        }

        @Test
        void testWordsSimplified() {
            assertEquals(words("apple", "apple", "Apple", "Banana", "cherry"),
                    SortLabChallenge.sortIgnoringCaseLowerFirstSimplified(words("Banana", "apple", "Apple", "cherry", "apple")));
        }

        @Test
        void testEmpty() {
            assertEquals(words(), SortLabChallenge.sortIgnoringCaseLowerFirst(words()));
        }

        @Test
        void testOneWord() {
            assertEquals(words("X"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("X")));
        }

        @Test
        void testNoNeedToIgnoreTheCase() {
            assertEquals(words("a", "b", "c"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("c", "a", "b")));
        }

        @Test
        void testUppercaseWordsAreNotAllFirst() {
            assertEquals(words("a", "B", "c", "D"),
                    SortLabChallenge.sortIgnoringCaseLowerFirst(words("D", "c", "B", "a")));
        }

        @Test
        void testDifferentCaseInTheMiddle() {
            assertEquals(words("aB", "Ab"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("Ab", "aB")));
            assertEquals(words("aB", "Ab"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("aB", "Ab")));
        }

        @Test
        void testTheSameWordTwiceKeepsBoth() {
            assertEquals(words("Z", "Z"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("Z", "Z")));
        }

        @Test
        void testAllTheCasesOfOneWord() {
            assertEquals(words("abc", "abC", "aBc", "Abc", "ABC"),
                    SortLabChallenge.sortIgnoringCaseLowerFirst(words("ABC", "Abc", "aBc", "abC", "abc")));
        }

        @Test
        void testPrefixesGoFirst() {
            assertEquals(words("a", "Ab", "abc"), SortLabChallenge.sortIgnoringCaseLowerFirst(words("abc", "Ab", "a")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = words("b", "A", "a");
            SortLabChallenge.sortIgnoringCaseLowerFirst(original);
            assertEquals(words("b", "A", "a"), original);
        }

        @Test
        void testBigList() {
            List<String> big = new ArrayList<>();
            for (int i = 0; i < 50_000; i++) {
                big.add("W" + i);
                big.add("w" + i);
            }
            List<String> result = SortLabChallenge.sortIgnoringCaseLowerFirst(big);
            assertEquals("w0", result.get(0));
            assertEquals("W0", result.get(1));
            assertEquals("w1", result.get(2));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortEvenBeforeOdd
    // ==========================================================
    @Nested
    class SortEvenBeforeOddTests {

        @Test
        void testExample() {
            assertEquals(numbers(2, 4, 8, 1, 3, 5), SortLabChallenge.sortEvenBeforeOdd(numbers(5, 2, 8, 1, 4, 3)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(numbers(-2, 0, 2, -3, -1, 1),
                    SortLabChallenge.sortEvenBeforeOdd(numbers(-3, -2, -1, 0, 1, 2)));
        }

        @Test
        void testEmpty() {
            assertEquals(numbers(), SortLabChallenge.sortEvenBeforeOdd(numbers()));
        }

        @Test
        void testOnlyEven() {
            assertEquals(numbers(2, 4, 6), SortLabChallenge.sortEvenBeforeOdd(numbers(6, 2, 4)));
        }

        @Test
        void testOnlyOdd() {
            assertEquals(numbers(1, 3, 5), SortLabChallenge.sortEvenBeforeOdd(numbers(5, 1, 3)));
        }

        @Test
        void testOneNumber() {
            assertEquals(numbers(7), SortLabChallenge.sortEvenBeforeOdd(numbers(7)));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(numbers(2, 2, 1, 1, 3), SortLabChallenge.sortEvenBeforeOdd(numbers(1, 2, 3, 2, 1)));
        }

        @Test
        void testZeroIsEven() {
            assertEquals(numbers(0, 1), SortLabChallenge.sortEvenBeforeOdd(numbers(1, 0)));
        }

        @Test
        void testNegativeOddNumbersAreOdd() {
            assertEquals(numbers(4, -7, -5, -1), SortLabChallenge.sortEvenBeforeOdd(numbers(-1, -5, -7, 4)));
        }

        @Test
        void testExtremeValues() {
            assertEquals(numbers(Integer.MIN_VALUE, Integer.MAX_VALUE),
                    SortLabChallenge.sortEvenBeforeOdd(numbers(Integer.MAX_VALUE, Integer.MIN_VALUE)));
            assertEquals(numbers(Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE),
                    SortLabChallenge.sortEvenBeforeOdd(numbers(Integer.MAX_VALUE, Integer.MIN_VALUE + 1,
                            Integer.MAX_VALUE - 1)));
        }

        @Test
        void testNumbersBiggerThanTheCacheOfIntegers() {
            assertEquals(numbers(1000, 2000, 1001, 1003),
                    SortLabChallenge.sortEvenBeforeOdd(numbers(1003, 2000, 1001, 1000)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Integer> original = numbers(5, 2, 8);
            SortLabChallenge.sortEvenBeforeOdd(original);
            assertEquals(numbers(5, 2, 8), original);
        }

        @Test
        void testBigList() {
            List<Integer> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(99_999 - i);
            }
            List<Integer> result = SortLabChallenge.sortEvenBeforeOdd(big);
            assertEquals(0, result.get(0));
            assertEquals(99_998, result.get(49_999));
            assertEquals(1, result.get(50_000));
            assertEquals(99_999, result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortNullsFirstThenLength
    // ==========================================================
    @Nested
    class SortNullsFirstThenLengthTests {

        @Test
        void testExample() {
            assertEquals(words(null, null, "a", "aa", "bb", "ccc"),
                    SortLabChallenge.sortNullsFirstThenLength(words("bb", null, "a", null, "ccc", "aa")));
        }

        @Test
        void testEmpty() {
            assertEquals(words(), SortLabChallenge.sortNullsFirstThenLength(words()));
        }

        @Test
        void testOnlyNulls() {
            assertEquals(words(null, null), SortLabChallenge.sortNullsFirstThenLength(words(null, null)));
        }

        @Test
        void testNoNulls() {
            assertEquals(words("a", "bb", "ccc"), SortLabChallenge.sortNullsFirstThenLength(words("ccc", "a", "bb")));
        }

        @Test
        void testOneNullAtTheEnd() {
            assertEquals(words(null, "a"), SortLabChallenge.sortNullsFirstThenLength(words("a", null)));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertEquals(words(null, "", "a"), SortLabChallenge.sortNullsFirstThenLength(words("a", "", null)));
        }

        @Test
        void testSameLengthIsAlphabetical() {
            assertEquals(words("cat", "cow", "dog"),
                    SortLabChallenge.sortNullsFirstThenLength(words("dog", "cat", "cow")));
        }

        @Test
        void testLengthIsMoreImportantThanAlphabet() {
            assertEquals(words(null, "z", "aa"), SortLabChallenge.sortNullsFirstThenLength(words("aa", "z", null)));
            assertEquals(words("a", "c", "bb"), SortLabChallenge.sortNullsFirstThenLength(words("bb", "a", "c")));
        }

        @Test
        void testUppercaseFirstWhenSameLength() {
            assertEquals(words("B", "a"), SortLabChallenge.sortNullsFirstThenLength(words("a", "B")));
        }

        @Test
        void testNullsInTheMiddle() {
            assertEquals(words(null, null, null, "x"),
                    SortLabChallenge.sortNullsFirstThenLength(words(null, "x", null, null)));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(words(null, "a", "a", "bb"),
                    SortLabChallenge.sortNullsFirstThenLength(words("bb", "a", null, "a")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = words("b", null, "a");
            SortLabChallenge.sortNullsFirstThenLength(original);
            assertEquals(words("b", null, "a"), original);
        }

        @Test
        void testBigList() {
            List<String> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(i % 4 == 0 ? null : "w" + i);
            }
            List<String> result = SortLabChallenge.sortNullsFirstThenLength(big);
            assertEquals(100_000, result.size());
            assertNull(result.get(0));
            assertNull(result.get(24_999));
            assertEquals("w1", result.get(25_000));
            assertEquals("w99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 10: oldest
    // ==========================================================
    @Nested
    class OldestTests {

        @Test
        void testSample() {
            assertEquals(DAN, SortLabChallenge.oldest(list(ANA, BOB, CY, DAN, EVE)));
        }

        @Test
        void testTieReturnsTheFirst() {
            assertEquals(ANA, SortLabChallenge.oldest(list(ANA, EVE)));
            assertEquals(EVE, SortLabChallenge.oldest(list(EVE, ANA)));
        }

        @Test
        void testOneEmployee() {
            assertEquals(BOB, SortLabChallenge.oldest(list(BOB)));
        }

        @Test
        void testTheOldestIsFirst() {
            assertEquals(DAN, SortLabChallenge.oldest(list(DAN, ANA, BOB)));
        }

        @Test
        void testTheOldestIsLast() {
            assertEquals(DAN, SortLabChallenge.oldest(list(BOB, ANA, DAN)));
        }

        @Test
        void testTieOfThree() {
            Employee a = emp("a", "x", 50, 1);
            Employee b = emp("b", "x", 50, 2);
            Employee c = emp("c", "x", 50, 3);
            assertEquals(a, SortLabChallenge.oldest(list(a, b, c)));
        }

        @Test
        void testTieInTheMiddleOfTheList() {
            Employee young = emp("y", "x", 10, 1);
            Employee a = emp("a", "x", 60, 1);
            Employee b = emp("b", "x", 60, 1);
            assertEquals(a, SortLabChallenge.oldest(list(young, a, b, young)));
        }

        @Test
        void testAgesBiggerThanTheCacheOfIntegers() {
            Employee a = emp("a", "x", 1000, 1);
            Employee b = emp("b", "x", 1000, 1);
            assertEquals(a, SortLabChallenge.oldest(list(a, b)));
        }

        @Test
        void testExtremeAges() {
            Employee low = emp("low", "x", Integer.MIN_VALUE, 1);
            Employee high = emp("high", "x", Integer.MAX_VALUE, 1);
            assertEquals(high, SortLabChallenge.oldest(list(low, high)));
            assertEquals(high, SortLabChallenge.oldest(list(high, low)));
        }

        @Test
        void testOnlyTheAgeMatters() {
            Employee rich = emp("rich", "A", 20, 1_000_000);
            Employee old = emp("old", "Z", 21, 1);
            assertEquals(old, SortLabChallenge.oldest(list(rich, old)));
        }

        @Test
        void testListIsNotModified() {
            List<Employee> original = list(ANA, BOB, DAN);
            SortLabChallenge.oldest(original);
            assertEquals(list(ANA, BOB, DAN), original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", i % 1000, 1));
            }
            assertEquals("e999", SortLabChallenge.oldest(big).name());
        }
    }

    // ==========================================================
    // CHALLENGE 11: topEarners
    // ==========================================================
    @Nested
    class TopEarnersTests {

        @Test
        void testFour() {
            assertEquals(list(EVE, BOB, CY, ANA), SortLabChallenge.topEarners(list(ANA, BOB, CY, DAN, EVE), 4));
        }

        @Test
        void testTieByName() {
            assertEquals(list(EVE, BOB, CY, ANA, DAN), SortLabChallenge.topEarners(list(DAN, ANA, CY, BOB, EVE), 5));
        }

        @Test
        void testZero() {
            assertEquals(list(), SortLabChallenge.topEarners(list(ANA, BOB), 0));
        }

        @Test
        void testOne() {
            assertEquals(list(EVE), SortLabChallenge.topEarners(list(ANA, BOB, CY, DAN, EVE), 1));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), SortLabChallenge.topEarners(list(), 0));
        }

        @Test
        void testTheTieIsCutInTheMiddle() {
            assertEquals(list(EVE, BOB, CY, ANA), SortLabChallenge.topEarners(list(DAN, ANA, CY, BOB, EVE), 4));
        }

        @Test
        void testDecimalsAreNotIgnored() {
            Employee low = emp("alow", "x", 20, 1000.25);
            Employee high = emp("zhigh", "x", 20, 1000.75);
            assertEquals(list(high), SortLabChallenge.topEarners(list(low, high), 1));
        }

        @Test
        void testTieIgnoresTheOriginalOrder() {
            Employee a = emp("amy", "x", 20, 100);
            Employee b = emp("zoe", "x", 20, 100);
            assertEquals(list(a, b), SortLabChallenge.topEarners(list(b, a), 2));
            assertEquals(list(a), SortLabChallenge.topEarners(list(b, a), 1));
        }

        @Test
        void testUppercaseNamesComeFirstOnTies() {
            Employee a = emp("a", "x", 20, 100);
            Employee b = emp("B", "x", 20, 100);
            assertEquals(list(b, a), SortLabChallenge.topEarners(list(a, b), 2));
        }

        @Test
        void testNegativeSalaries() {
            Employee a = emp("a", "x", 20, -10);
            Employee b = emp("b", "x", 20, -5);
            assertEquals(list(b), SortLabChallenge.topEarners(list(a, b), 1));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Employee> original = list(ANA, BOB, CY, DAN, EVE);
            SortLabChallenge.topEarners(original, 2);
            assertEquals(list(ANA, BOB, CY, DAN, EVE), original);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", 20, i % 100));
            }
            List<Employee> result = SortLabChallenge.topEarners(big, 3);
            assertEquals(3, result.size());
            assertEquals("e10099", result.get(0).name());
            assertEquals("e10199", result.get(1).name());
            assertEquals("e10299", result.get(2).name());
        }
    }

    // ==========================================================
    // CHALLENGE 12: mergeSortedLists
    // ==========================================================
    @Nested
    class MergeSortedListsTests {

        @Test
        void testNaturalOrder() {
            assertEquals(words("a", "b", "c", "d", "e"),
                    SortLabChallenge.mergeSortedLists(words("a", "c", "e"), words("b", "d"),
                            Comparator.naturalOrder()));
        }

        @Test
        void testReverseOrder() {
            assertEquals(words("d", "c", "b", "a"),
                    SortLabChallenge.mergeSortedLists(words("c", "a"), words("d", "b"), Comparator.reverseOrder()));
        }

        @Test
        void testIgnoringTheCaseTheFirstListGoesFirst() {
            assertEquals(words("a", "B", "C", "c"),
                    SortLabChallenge.mergeSortedLists(words("a", "C"), words("B", "c"), String.CASE_INSENSITIVE_ORDER));
        }

        @Test
        void testTiesFromBothSides() {
            assertEquals(words("x1", "x2", "x3", "y"),
                    SortLabChallenge.mergeSortedLists(words("x1", "y"), words("x2", "x3"),
                            Comparator.comparing((String s) -> s.substring(0, 1))));
        }

        @Test
        void testSecondListFirstWhenItIsSmaller() {
            assertEquals(words("a", "b", "c", "d"),
                    SortLabChallenge.mergeSortedLists(words("c", "d"), words("a", "b"), Comparator.naturalOrder()));
        }

        @Test
        void testByLength() {
            assertEquals(words("a", "cc", "bb", "ddd"), SortLabChallenge.mergeSortedLists(words("a", "cc"),
                    words("bb", "ddd"), Comparator.comparingInt(String::length)));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(words("a", "b"), SortLabChallenge.mergeSortedLists(words(), words("a", "b"),
                    Comparator.naturalOrder()));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(words("a", "b"), SortLabChallenge.mergeSortedLists(words("a", "b"), words(),
                    Comparator.naturalOrder()));
        }

        @Test
        void testBothEmpty() {
            assertEquals(words(), SortLabChallenge.mergeSortedLists(words(), words(), Comparator.naturalOrder()));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(words("a", "a", "a", "b"), SortLabChallenge.mergeSortedLists(words("a", "a"), words("a", "b"),
                    Comparator.naturalOrder()));
        }

        @Test
        void testOriginalsAreNotModified() {
            List<String> first = words("a", "c");
            List<String> second = words("b");
            List<String> result = SortLabChallenge.mergeSortedLists(first, second, Comparator.naturalOrder());
            assertEquals(words("a", "c"), first);
            assertEquals(words("b"), second);
            assertEquals(words("a", "b", "c"), result);
        }

        @Test
        void testBigLists() {
            List<String> even = new ArrayList<>();
            List<String> odd = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                even.add(String.format("%06d", i * 2));
                odd.add(String.format("%06d", i * 2 + 1));
            }
            List<String> result = SortLabChallenge.mergeSortedLists(even, odd, Comparator.naturalOrder());
            assertEquals(200_000, result.size());
            for (int i = 0; i < result.size(); i += 997) {
                assertEquals(String.format("%06d", i), result.get(i));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 13: isSortedBy
    // ==========================================================
    @Nested
    class IsSortedByTests {

        private final Comparator<Employee> byAge = Comparator.comparingInt(Employee::age);

        @Test
        void testSortedWithTies() {
            assertTrue(SortLabChallenge.isSortedBy(list(BOB, CY, ANA), byAge));
        }

        @Test
        void testNotSorted() {
            assertFalse(SortLabChallenge.isSortedBy(list(ANA, BOB), byAge));
        }

        @Test
        void testEmpty() {
            assertTrue(SortLabChallenge.isSortedBy(list(), byAge));
        }

        @Test
        void testOneEmployee() {
            assertTrue(SortLabChallenge.isSortedBy(list(DAN), byAge));
        }

        @Test
        void testSampleOrderIsNotSortedByAge() {
            assertFalse(SortLabChallenge.isSortedBy(list(ANA, BOB, CY, DAN, EVE), byAge));
        }

        @Test
        void testSortedByAnotherComparator() {
            assertTrue(SortLabChallenge.isSortedBy(list(EVE, BOB, CY, ANA, DAN),
                    Comparator.comparingDouble(Employee::salary).reversed()));
            assertFalse(SortLabChallenge.isSortedBy(list(EVE, BOB, CY, ANA, DAN),
                    Comparator.comparingDouble(Employee::salary)));
        }

        @Test
        void testOnlyTheLastPairIsWrong() {
            assertFalse(SortLabChallenge.isSortedBy(list(BOB, CY, ANA, EVE, DAN, BOB), byAge));
        }

        @Test
        void testOnlyTheFirstPairIsWrong() {
            assertFalse(SortLabChallenge.isSortedBy(list(DAN, BOB, CY, ANA), byAge));
        }

        @Test
        void testAllEqual() {
            assertTrue(SortLabChallenge.isSortedBy(list(BOB, CY, BOB), byAge));
        }

        @Test
        void testNotJustFirstAndLast() {
            assertFalse(SortLabChallenge.isSortedBy(list(emp("a", "x", 1, 1), emp("b", "x", 5, 1),
                    emp("c", "x", 3, 1), emp("d", "x", 9, 1)), byAge));
        }

        @Test
        void testWithTheComparatorOfTheSameChallengeSet() {
            List<Employee> sorted = SortLabChallenge.sortByDepartmentThenName(list(ANA, BOB, CY, DAN, EVE));
            Comparator<Employee> comparator = Comparator.comparing(Employee::department).thenComparing(Employee::name);
            assertTrue(SortLabChallenge.isSortedBy(sorted, comparator));
            assertFalse(SortLabChallenge.isSortedBy(list(ANA, BOB, CY, DAN, EVE), comparator));
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", i / 2, 1));
            }
            assertTrue(SortLabChallenge.isSortedBy(big, byAge));
            big.set(99_998, emp("bad", "x", 0, 1));
            assertFalse(SortLabChallenge.isSortedBy(big, byAge));
        }
    }

    // ==========================================================
    // CHALLENGE 14: byAgeDescendingThenName
    // ==========================================================
    @Nested
    class ByAgeDescendingThenNameTests {

        @Test
        void testSortingASample() {
            List<Employee> sample = list(ANA, BOB, CY, DAN, EVE);
            sample.sort(SortLabChallenge.byAgeDescendingThenName());
            assertEquals(list(DAN, ANA, EVE, BOB, CY), sample);
        }

        @Test
        void testOlderComesFirst() {
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(DAN, ANA) < 0);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(ANA, DAN) > 0);
        }

        @Test
        void testSameAgeComparesNames() {
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(ANA, EVE) < 0);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(EVE, ANA) > 0);
        }

        @Test
        void testSameEmployeeIsEqual() {
            assertEquals(0, SortLabChallenge.byAgeDescendingThenName().compare(ANA, ANA));
        }

        @Test
        void testSameAgeAndNameAreEqual() {
            Employee other = emp("ana", "HR", 30, 1.0);
            assertEquals(0, SortLabChallenge.byAgeDescendingThenName().compare(ANA, other));
        }

        @Test
        void testAgeIsMoreImportantThanName() {
            Employee oldZed = emp("zed", "x", 50, 1);
            Employee youngAmy = emp("amy", "x", 20, 1);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(oldZed, youngAmy) < 0);
        }

        @Test
        void testExtremeAges() {
            Employee low = emp("low", "x", Integer.MIN_VALUE, 1);
            Employee high = emp("high", "x", Integer.MAX_VALUE, 1);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(high, low) < 0);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(low, high) > 0);
        }

        @Test
        void testUppercaseNamesComeFirst() {
            Employee upper = emp("B", "x", 20, 1);
            Employee lower = emp("a", "x", 20, 1);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(upper, lower) < 0);
        }

        @Test
        void testDepartmentAndSalaryAreIgnored() {
            Employee a = emp("a", "Z", 20, 1);
            Employee b = emp("b", "A", 20, 999);
            assertTrue(SortLabChallenge.byAgeDescendingThenName().compare(a, b) < 0);
        }

        @Test
        void testEachCallGivesAWorkingComparator() {
            Comparator<Employee> first = SortLabChallenge.byAgeDescendingThenName();
            Comparator<Employee> second = SortLabChallenge.byAgeDescendingThenName();
            assertEquals(first.compare(ANA, DAN) < 0, second.compare(ANA, DAN) < 0);
        }

        @Test
        void testSortingUsesAStableOrderForTheSameAgeAndName() {
            Employee a = emp("same", "x", 20, 1);
            Employee b = emp("same", "y", 20, 2);
            List<Employee> sample = list(b, a);
            sample.sort(SortLabChallenge.byAgeDescendingThenName());
            assertEquals(list(b, a), sample);
        }

        @Test
        void testBigList() {
            List<Employee> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(emp("e" + i, "x", i % 100, 1));
            }
            big.sort(SortLabChallenge.byAgeDescendingThenName());
            assertEquals(99, big.get(0).age());
            assertEquals("e10099", big.get(0).name());
            assertEquals(0, big.get(99_999).age());
        }
    }

    // ==========================================================
    // CHALLENGE 15: sortDates
    // ==========================================================
    @Nested
    class SortDatesTests {

        @Test
        void testExampleOne() {
            assertEquals(words("31/01/2020", "15/06/2020", "25/12/2020", "01/01/2021"),
                    SortLabChallenge.sortDates(words("25/12/2020", "01/01/2021", "31/01/2020", "15/06/2020")));
        }

        @Test
        void testExampleTwo() {
            assertEquals(words("03/02/2020", "02/03/2020", "01/04/2020"),
                    SortLabChallenge.sortDates(words("02/03/2020", "01/04/2020", "03/02/2020")));
        }

        @Test
        void testEmpty() {
            assertEquals(words(), SortLabChallenge.sortDates(words()));
        }

        @Test
        void testOneDate() {
            assertEquals(words("10/10/2010"), SortLabChallenge.sortDates(words("10/10/2010")));
        }

        @Test
        void testSameMonthAndYearSortsByDay() {
            assertEquals(words("01/05/2020", "09/05/2020", "10/05/2020", "30/05/2020"),
                    SortLabChallenge.sortDates(words("30/05/2020", "10/05/2020", "01/05/2020", "09/05/2020")));
        }

        @Test
        void testYearIsMoreImportantThanMonth() {
            assertEquals(words("31/12/1999", "01/01/2000"),
                    SortLabChallenge.sortDates(words("01/01/2000", "31/12/1999")));
        }

        @Test
        void testMonthIsMoreImportantThanDay() {
            assertEquals(words("30/01/2020", "01/02/2020"),
                    SortLabChallenge.sortDates(words("01/02/2020", "30/01/2020")));
        }

        @Test
        void testSameDateTwice() {
            assertEquals(words("05/05/2005", "05/05/2005", "06/05/2005"),
                    SortLabChallenge.sortDates(words("06/05/2005", "05/05/2005", "05/05/2005")));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(words("01/01/2000", "02/01/2000", "01/02/2000"),
                    SortLabChallenge.sortDates(words("01/01/2000", "02/01/2000", "01/02/2000")));
        }

        @Test
        void testOldYears() {
            assertEquals(words("15/03/0999", "01/01/1000", "01/01/9999"),
                    SortLabChallenge.sortDates(words("01/01/9999", "01/01/1000", "15/03/0999")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = words("02/01/2020", "01/01/2020");
            SortLabChallenge.sortDates(original);
            assertEquals(words("02/01/2020", "01/01/2020"), original);
        }

        @Test
        void testBigList() {
            List<String> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(String.format("%02d/%02d/%04d", 1 + i % 28, 1 + (i / 28) % 12, 1000 + i / 336));
            }
            List<String> result = SortLabChallenge.sortDates(big);
            assertEquals(100_000, result.size());
            assertEquals("01/01/1000", result.get(0));
            assertEquals("12/08/1297", result.get(99_999));
}
    }
}