package dev.perfectbogus.sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class SortingComparatorsIntermediateChallengeTest {

    private static SortingComparatorsIntermediateChallenge.Employee emp(String name, String department, int level,
                                                                        double salary) {
        return new SortingComparatorsIntermediateChallenge.Employee(name, department, level, salary);
    }

    private static List<String> names(List<SortingComparatorsIntermediateChallenge.Employee> employees) {
        return employees.stream().map(SortingComparatorsIntermediateChallenge.Employee::name)
                .collect(Collectors.toList());
    }

    private static List<String> asStrings(List<int[]> arrays) {
        return arrays.stream().map(Arrays::toString).collect(Collectors.toList());
    }

    private static int sign(int value) {
        return Integer.signum(value);
    }

    @Nested
    class SortEmployeesTests {

        @Test
        void testSortsByDepartmentLevelDescendingSalaryDescendingThenName() {
            List<SortingComparatorsIntermediateChallenge.Employee> input = new ArrayList<>(List.of(
                    emp("Zoe", "Sales", 2, 50.0),
                    emp("Ann", "Sales", 3, 40.0),
                    emp("Cid", "Sales", 3, 60.0),
                    emp("Bob", "Sales", 3, 60.0),
                    emp("Eve", "Engineering", 1, 10.0),
                    emp("Dan", "Engineering", 1, 10.0)));
            List<String> before = names(input);

            List<SortingComparatorsIntermediateChallenge.Employee> result =
                    SortingComparatorsIntermediateChallenge.sortEmployees(input);

            assertEquals(List.of("Dan", "Eve", "Bob", "Cid", "Ann", "Zoe"), names(result));
            assertEquals(before, names(input));
        }

        @Test
        void testSalariesThatDifferByLessThanOneAreOrderedCorrectly() {
            List<String> result = names(SortingComparatorsIntermediateChallenge.sortEmployees(List.of(
                    emp("Low", "A", 1, 100.25),
                    emp("High", "A", 1, 100.75),
                    emp("Mid", "A", 1, 100.5))));

            assertEquals(List.of("High", "Mid", "Low"), result);
        }

        @Test
        void testDepartmentOrderIsCaseSensitive() {
            List<String> result = names(SortingComparatorsIntermediateChallenge.sortEmployees(List.of(
                    emp("lower", "alpha", 1, 1.0),
                    emp("upper", "Zeta", 1, 1.0))));

            assertEquals(List.of("upper", "lower"), result);
        }

        @Test
        void testEmployeesEqualOnAllFieldsKeepTheirOriginalOrder() {
            SortingComparatorsIntermediateChallenge.Employee first = emp("Same", "A", 1, 5.0);
            SortingComparatorsIntermediateChallenge.Employee second = emp("Same", "A", 1, 5.0);

            List<SortingComparatorsIntermediateChallenge.Employee> result =
                    SortingComparatorsIntermediateChallenge.sortEmployees(List.of(first, second));

            assertTrue(result.get(0) == first && result.get(1) == second);
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortEmployees(List.of()));
        }
    }

    @Nested
    class SortByLastNameThenFirstNameTests {

        @Test
        void testSortsByLastNameThenFirstNameIgnoringCase() {
            List<String> input = new ArrayList<>(List.of("john smith", "Anna Zed", "Bob SMITH", "Adam smith"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortByLastNameThenFirstName(input);

            assertEquals(List.of("Adam smith", "Bob SMITH", "john smith", "Anna Zed"), result);
            assertEquals(List.of("john smith", "Anna Zed", "Bob SMITH", "Adam smith"), input);
        }

        @Test
        void testMiddleWordsBelongToTheFirstName() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortByLastNameThenFirstName(
                    List.of("Mary Ann Smith", "Mary Smith", "Mary Beth Smith", "Zed Adams"));

            assertEquals(List.of("Zed Adams", "Mary Smith", "Mary Ann Smith", "Mary Beth Smith"), result);
        }

        @Test
        void testSingleWordNameHasAnEmptyFirstName() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortByLastNameThenFirstName(
                    List.of("Ann Madonna", "Madonna", "Zoe Madonna"));

            assertEquals(List.of("Madonna", "Ann Madonna", "Zoe Madonna"), result);
        }

        @Test
        void testNamesEqualIgnoringCaseKeepTheirOriginalOrder() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortByLastNameThenFirstName(
                    List.of("ann lee", "Ann Lee", "ANN LEE"));

            assertEquals(List.of("ann lee", "Ann Lee", "ANN LEE"), result);
        }
    }

    @Nested
    class SortDescendingNullsLastTests {

        @Test
        void testNullsAreAtTheEndAndTheRestIsDescending() {
            List<Integer> input = new ArrayList<>(Arrays.asList(3, null, 10, 1, null, 7));

            List<Integer> result = SortingComparatorsIntermediateChallenge.sortDescendingNullsLast(input);

            assertEquals(Arrays.asList(10, 7, 3, 1, null, null), result);
            assertEquals(Arrays.asList(3, null, 10, 1, null, 7), input);
        }

        @Test
        void testExtremeValuesAreOrderedCorrectly() {
            List<Integer> result = SortingComparatorsIntermediateChallenge.sortDescendingNullsLast(
                    Arrays.asList(0, Integer.MIN_VALUE, null, Integer.MAX_VALUE, -1, 1));

            assertEquals(Arrays.asList(Integer.MAX_VALUE, 1, 0, -1, Integer.MIN_VALUE, null), result);
        }

        @Test
        void testOnlyNullsAndEmptyList() {
            assertEquals(Arrays.asList(null, null),
                    SortingComparatorsIntermediateChallenge.sortDescendingNullsLast(Arrays.asList(null, null)));
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortDescendingNullsLast(List.of()));
        }
    }

    @Nested
    class SortedDistinctIgnoringCaseTests {

        @Test
        void testRemovesCaseInsensitiveDuplicatesKeepingTheFirstSpelling() {
            List<String> input = new ArrayList<>(List.of("pear", "Apple", "apple", "PEAR", "banana", "APPLE"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortedDistinctIgnoringCase(input);

            assertEquals(List.of("Apple", "banana", "pear"), result);
            assertEquals(List.of("pear", "Apple", "apple", "PEAR", "banana", "APPLE"), input);
        }

        @Test
        void testSortOrderIgnoresCase() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortedDistinctIgnoringCase(
                    List.of("b", "C", "a", "D"));

            assertEquals(List.of("a", "b", "C", "D"), result);
        }

        @Test
        void testExactDuplicatesAreRemovedToo() {
            assertEquals(List.of("x"), SortingComparatorsIntermediateChallenge.sortedDistinctIgnoringCase(
                    List.of("x", "x", "x")));
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortedDistinctIgnoringCase(List.of()));
        }
    }

    @Nested
    class SortWithPinnedFirstTests {

        @Test
        void testPinnedItemsComeFirstInPinnedOrderThenTheRestIgnoringCase() {
            List<String> items = new ArrayList<>(List.of("delta", "Charlie", "bravo", "echo", "alpha", "Foxtrot"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortWithPinnedFirst(
                    items, List.of("echo", "alpha"));

            assertEquals(List.of("echo", "alpha", "bravo", "Charlie", "delta", "Foxtrot"), result);
            assertEquals(List.of("delta", "Charlie", "bravo", "echo", "alpha", "Foxtrot"), items);
        }

        @Test
        void testCopiesOfAPinnedItemStayTogether() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortWithPinnedFirst(
                    List.of("b", "x", "a", "b", "y", "a", "b"), List.of("b", "a"));

            assertEquals(List.of("b", "b", "b", "a", "a", "x", "y"), result);
        }

        @Test
        void testPinnedMatchingIsCaseSensitive() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortWithPinnedFirst(
                    List.of("Zebra", "zebra", "apple"), List.of("zebra"));

            assertEquals(List.of("zebra", "apple", "Zebra"), result);
        }

        @Test
        void testUnpinnedItemsEqualIgnoringCaseKeepTheirOriginalOrder() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortWithPinnedFirst(
                    List.of("b", "B", "a", "A"), List.of("missing"));

            assertEquals(List.of("a", "A", "b", "B"), result);
        }

        @Test
        void testNothingPinned() {
            assertEquals(List.of("a", "b"), SortingComparatorsIntermediateChallenge.sortWithPinnedFirst(
                    List.of("b", "a"), List.of()));
        }
    }

    @Nested
    class SortByCustomAlphabetTests {

        @Test
        void testFollowsTheGivenAlphabet() {
            List<String> input = new ArrayList<>(List.of("cab", "abc", "bca", "acb", "bac", "cba"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortByCustomAlphabet(input, "cba");

            assertEquals(List.of("cba", "cab", "bca", "bac", "acb", "abc"), result);
            assertEquals(List.of("cab", "abc", "bca", "acb", "bac", "cba"), input);
        }

        @Test
        void testAProperPrefixComesFirst() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortByCustomAlphabet(
                    List.of("hello", "hell", "h", "he"), "ehlo");

            assertEquals(List.of("h", "he", "hell", "hello"), result);
        }

        @Test
        void testReversedAlphabetGivesReverseDictionaryOrder() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortByCustomAlphabet(
                    List.of("apple", "banana", "cherry", "app"), "zyxwvutsrqponmlkjihgfedcba");

            assertEquals(List.of("cherry", "banana", "app", "apple"), result);
        }

        @Test
        void testEqualWordsKeepTheirOriginalOrderAndEmptyListIsFine() {
            assertEquals(List.of("a", "a"),
                    SortingComparatorsIntermediateChallenge.sortByCustomAlphabet(List.of("a", "a"), "ab"));
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortByCustomAlphabet(List.of(), "ab"));
        }
    }

    @Nested
    class SortByFrequencyTests {

        @Test
        void testMostFrequentFirstThenAlphabetical() {
            List<String> input = new ArrayList<>(List.of("b", "a", "c", "b", "c", "b", "d", "c", "a"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortByFrequency(input);

            assertEquals(List.of("b", "c", "a", "d"), result);
            assertEquals(List.of("b", "a", "c", "b", "c", "b", "d", "c", "a"), input);
        }

        @Test
        void testMostFrequentFirstThenAlphabeticalImproved() {
            List<String> input = new ArrayList<>(List.of("b", "a", "c", "b", "c", "b", "d", "c", "a"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortByFrequencyImproved(input);

            assertEquals(List.of("b", "c", "a", "d"), result);
            assertEquals(List.of("b", "a", "c", "b", "c", "b", "d", "c", "a"), input);
        }

        @Test
        void testTiesAreResolvedAlphabeticallyNotByFirstOccurrence() {
            assertEquals(List.of("apple", "mango", "zebra"),
                    SortingComparatorsIntermediateChallenge.sortByFrequency(List.of("zebra", "mango", "apple")));
        }

        @Test
        void testWordsAreCaseSensitive() {
            assertEquals(List.of("a", "A"),
                    SortingComparatorsIntermediateChallenge.sortByFrequency(List.of("A", "a", "a")));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortByFrequency(List.of()));
        }
    }

    @Nested
    class SortPointsByDistanceFromOriginTests {

        @Test
        void testNearestFirstWithTiesByXThenY() {
            List<int[]> input = new ArrayList<>(List.of(
                    new int[] {3, 4}, new int[] {0, 1}, new int[] {-3, 4}, new int[] {4, -3}, new int[] {1, 0}));

            List<int[]> result = SortingComparatorsIntermediateChallenge.sortPointsByDistanceFromOrigin(input);

            assertEquals(List.of("[0, 1]", "[1, 0]", "[-3, 4]", "[3, 4]", "[4, -3]"), asStrings(result));
            assertEquals(List.of("[3, 4]", "[0, 1]", "[-3, 4]", "[4, -3]", "[1, 0]"), asStrings(input));
        }

        @Test
        void testLargeCoordinatesDoNotOverflow() {
            List<int[]> result = SortingComparatorsIntermediateChallenge.sortPointsByDistanceFromOrigin(List.of(
                    new int[] {100_000, 100_000}, new int[] {1, 1}, new int[] {70_000, 70_000}));

            assertEquals(List.of("[1, 1]", "[70000, 70000]", "[100000, 100000]"), asStrings(result));
        }

        @Test
        void testDistancesThatDifferByOneInSquaredFormAreStillOrdered() {
            List<int[]> result = SortingComparatorsIntermediateChallenge.sortPointsByDistanceFromOrigin(List.of(
                    new int[] {-1_000_000_000, 1}, new int[] {1_000_000_000, 0}));

            assertEquals(List.of("[1000000000, 0]", "[-1000000000, 1]"), asStrings(result));
        }

        @Test
        void testIdenticalPointsKeepTheirOriginalOrder() {
            int[] first = {2, 2};
            int[] second = {2, 2};

            List<int[]> result = SortingComparatorsIntermediateChallenge.sortPointsByDistanceFromOrigin(
                    List.of(first, second));

            assertTrue(result.get(0) == first && result.get(1) == second);
        }
    }

    @Nested
    class BySumAscendingTests {

        @Test
        void testOrdersBySum() {
            List<int[]> input = new ArrayList<>(List.of(
                    new int[] {5}, new int[] {1, 1}, new int[] {}, new int[] {-4, 3}));

            input.sort(SortingComparatorsIntermediateChallenge.bySumAscending());

            assertEquals(List.of("[-4, 3]", "[]", "[1, 1]", "[5]"), asStrings(input));
        }

        @Test
        void testOrdersBySumImproved() {
            List<int[]> input = new ArrayList<>(List.of(
                    new int[] {5}, new int[] {1, 1}, new int[] {}, new int[] {-4, 3}));

            input.sort(SortingComparatorsIntermediateChallenge.bySumAscendingImproved());

            assertEquals(List.of("[-4, 3]", "[]", "[1, 1]", "[5]"), asStrings(input));
        }

        @Test
        void testSumsOutsideTheIntRangeAreComparedCorrectly() {
            Comparator<int[]> comparator = SortingComparatorsIntermediateChallenge.bySumAscending();
            int[] huge = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            int[] tiny = {Integer.MIN_VALUE, Integer.MIN_VALUE};
            int[] five = {5};

            assertTrue(comparator.compare(huge, five) > 0);
            assertTrue(comparator.compare(five, huge) < 0);
            assertTrue(comparator.compare(tiny, five) < 0);
            assertTrue(comparator.compare(five, tiny) > 0);
            assertTrue(comparator.compare(tiny, huge) < 0);
        }

        @Test
        void testEqualSumsCompareAsZero() {
            Comparator<int[]> comparator = SortingComparatorsIntermediateChallenge.bySumAscending();

            assertEquals(0, comparator.compare(new int[] {1, 4}, new int[] {5}));
            assertEquals(0, comparator.compare(new int[] {}, new int[] {7, -7}));
            assertEquals(0, comparator.compare(new int[] {Integer.MAX_VALUE, 1}, new int[] {Integer.MAX_VALUE, 1}));
        }

        @Test
        void testComparatorIsAntisymmetricAndTransitive() {
            Comparator<int[]> comparator = SortingComparatorsIntermediateChallenge.bySumAscending();
            List<int[]> samples = List.of(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE}, new int[] {1},
                    new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE}, new int[] {}, new int[] {-1, 1},
                    new int[] {Integer.MAX_VALUE}, new int[] {Integer.MIN_VALUE, -1});

            for (int[] a : samples) {
                for (int[] b : samples) {
                    assertEquals(sign(comparator.compare(a, b)), -sign(comparator.compare(b, a)));
                    for (int[] c : samples) {
                        if (comparator.compare(a, b) <= 0 && comparator.compare(b, c) <= 0) {
                            assertTrue(comparator.compare(a, c) <= 0);
                        }
                    }
                }
            }
        }
    }

    @Nested
    class SortNumericStringsTests {

        @Test
        void testSortsByNumericValueNotAlphabetically() {
            List<String> input = new ArrayList<>(List.of("10", "9", "100", "2", "1"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortNumericStrings(input);

            assertEquals(List.of("1", "2", "9", "10", "100"), result);
            assertEquals(List.of("10", "9", "100", "2", "1"), input);
        }

        @Test
        void testNumbersBeyondTheRangeOfLong() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortNumericStrings(List.of(
                    "123456789012345678901234567891", "123456789012345678901234567890", "99999999999999999999",
                    "9223372036854775808", "9223372036854775807"));

            assertEquals(List.of("9223372036854775807", "9223372036854775808", "99999999999999999999",
                    "123456789012345678901234567890", "123456789012345678901234567891"), result);
        }

        @Test
        void testLeadingZerosAreIgnoredForTheValueButKeptInTheSpelling() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortNumericStrings(List.of(
                    "0010", "9", "007", "7", "0", "000", "08"));

            assertEquals(List.of("0", "000", "007", "7", "08", "9", "0010"), result);
        }

        @Test
        void testEqualValuesKeepTheirOriginalOrder() {
            assertEquals(List.of("0007", "7", "007"),
                    SortingComparatorsIntermediateChallenge.sortNumericStrings(List.of("0007", "7", "007")));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortNumericStrings(List.of()));
        }
    }

    @Nested
    class SortVersionsTests {

        @Test
        void testSegmentsAreComparedNumerically() {
            List<String> input = new ArrayList<>(List.of("1.10", "1.2", "1.9", "2", "1.2.1", "1.10.1"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortVersions(input);

            assertEquals(List.of("1.2", "1.2.1", "1.9", "1.10", "1.10.1", "2"), result);
            assertEquals(List.of("1.10", "1.2", "1.9", "2", "1.2.1", "1.10.1"), input);
        }

        @Test
        void testSegmentsAreComparedNumericallyAnother() {
            List<String> input = new ArrayList<>(List.of("1.10", "1.2", "1.9", "2", "1.2.1", "1.10.1"));

            List<String> result = SortingComparatorsIntermediateChallenge.sortVersionsAnother(input);

            assertEquals(List.of("1.2", "1.2.1", "1.9", "1.10", "1.10.1", "2"), result);
            assertEquals(List.of("1.10", "1.2", "1.9", "2", "1.2.1", "1.10.1"), input);
        }

        @Test
        void testMissingSegmentsCountAsZeroAndEqualVersionsKeepTheirOrder() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortVersions(List.of(
                    "1.0.0", "1", "1.0", "0.9", "1.0.0.1"));

            assertEquals(List.of("0.9", "1.0.0", "1", "1.0", "1.0.0.1"), result);
        }

        @Test
        void testLongSegmentsUpToNineDigits() {
            List<String> result = SortingComparatorsIntermediateChallenge.sortVersions(List.of(
                    "999999999", "1000", "100000000.1", "100000000"));

            assertEquals(List.of("1000", "100000000", "100000000.1", "999999999"), result);
        }

        @Test
        void testSingleAndEmpty() {
            assertEquals(List.of("3"), SortingComparatorsIntermediateChallenge.sortVersions(List.of("3")));
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.sortVersions(List.of()));
        }
    }

    @Nested
    class AlphanumericComparatorTests {

        private List<String> sorted(String... values) {
            List<String> list = new ArrayList<>(Arrays.asList(values));
            list.sort(SortingComparatorsIntermediateChallenge.alphanumericComparator());
            return list;
        }

        @Test
        void testDigitRunsComparedNumerically() {
            assertEquals(List.of("file1", "file2", "file10", "file20"), sorted("file10", "file2", "file20", "file1"));
        }

        @Test
        void testNonDigitRunsComparedIgnoringCase() {
            assertEquals(List.of("apple1", "Banana1", "cherry1"), sorted("cherry1", "Banana1", "apple1"));
        }

        @Test
        void testDigitRunsComeBeforeLetterRuns() {
            assertEquals(List.of("1a", "a1", "b"), sorted("b", "a1", "1a"));
        }

        @Test
        void testFewerRunsComeFirstWhenOneStringIsAPrefixOfTheRuns() {
            assertEquals(List.of("file", "file1", "file1a", "file1a2"), sorted("file1a2", "file1a", "file1", "file"));
        }

        @Test
        void testLeadingZerosAndFallbackToPlainCompareTo() {
            Comparator<String> comparator = SortingComparatorsIntermediateChallenge.alphanumericComparator();

            assertEquals(List.of("a01b", "a1b", "a2b"), sorted("a2b", "a1b", "a01b"));
            assertTrue(comparator.compare("File", "file") < 0);
            assertTrue(comparator.compare("file", "File") > 0);
            assertEquals(0, comparator.compare("same7", "same7"));
        }

        @Test
        void testNumbersBeyondTheRangeOfLong() {
            assertEquals(List.of("v9223372036854775807", "v9223372036854775808", "v99999999999999999999"),
                    sorted("v99999999999999999999", "v9223372036854775808", "v9223372036854775807"));
        }

        @Test
        void testComparatorIsAntisymmetricAndTransitive() {
            Comparator<String> comparator = SortingComparatorsIntermediateChallenge.alphanumericComparator();
            List<String> samples = List.of("", "a", "A", "a1", "a01", "a1b", "ab1", "1", "01", "10", "9", "1a",
                    "file", "File", "file2", "file10", "x9x", "x10x", "x10", "10x");

            for (String a : samples) {
                for (String b : samples) {
                    assertEquals(sign(comparator.compare(a, b)), -sign(comparator.compare(b, a)));
                    for (String c : samples) {
                        if (comparator.compare(a, b) <= 0 && comparator.compare(b, c) <= 0) {
                            assertTrue(comparator.compare(a, c) <= 0, a + " <= " + b + " <= " + c);
                        }
                    }
                }
            }
        }
    }

    @Nested
    class SortByKeysTests {

        private List<SortingComparatorsIntermediateChallenge.Employee> staff() {
            return List.of(
                    emp("Cid", "Sales", 2, 30.0),
                    emp("Ann", "Sales", 3, 50.0),
                    emp("Bob", "Engineering", 3, 40.0),
                    emp("Dan", "Engineering", 1, 40.0),
                    emp("Eve", "Sales", 3, 20.0));
        }

        @Test
        void testSortsByKeysInOrderWithDescendingPrefix() {
            List<SortingComparatorsIntermediateChallenge.Employee> input = new ArrayList<>(staff());

            List<String> result = names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    input, List.of("department", "-level", "name")));

            assertEquals(List.of("Bob", "Dan", "Ann", "Eve", "Cid"), result);
            assertEquals(names(staff()), names(input));
        }

        @Test
        void testDescendingSalaryThenAscendingName() {
            List<String> result = names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    staff(), List.of("-salary", "name")));

            assertEquals(List.of("Ann", "Bob", "Dan", "Cid", "Eve"), result);
        }

        @Test
        void testDescendingKeysUseTheRealOrderOfStringsAndFractionalSalaries() {
            List<SortingComparatorsIntermediateChallenge.Employee> input = List.of(
                    emp("a", "d", 1, 100.25), emp("b", "d", 1, 100.75), emp("c", "d", 1, 100.5));

            assertEquals(List.of("b", "c", "a"), names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    input, List.of("-salary"))));
            assertEquals(List.of("c", "b", "a"), names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    input, List.of("-name"))));
            assertEquals(List.of("a", "c", "b"), names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    input, List.of("salary"))));
        }

        @Test
        void testNumericKeysAreComparedNumerically() {
            List<String> result = names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    List.of(emp("a", "d", 10, 100.5), emp("b", "d", 9, 100.25), emp("c", "d", 100, 99.75)),
                    List.of("level")));

            assertEquals(List.of("b", "a", "c"), result);
        }

        @Test
        void testTiesKeepTheOriginalOrderAndEmptyKeysKeepEverythingAsIs() {
            assertEquals(List.of("Bob", "Dan"), names(SortingComparatorsIntermediateChallenge.sortByKeys(
                    staff().subList(2, 4), List.of("department", "salary"))));
            assertEquals(names(staff()), names(SortingComparatorsIntermediateChallenge.sortByKeys(staff(), List.of())));
        }

        @Test
        void testUnknownKeysAreRejectedEvenForAnEmptyList() {
            assertThrows(IllegalArgumentException.class, () -> SortingComparatorsIntermediateChallenge.sortByKeys(
                    staff(), List.of("name", "age")));
            assertThrows(IllegalArgumentException.class, () -> SortingComparatorsIntermediateChallenge.sortByKeys(
                    List.of(), List.of("-height")));
            assertThrows(IllegalArgumentException.class, () -> SortingComparatorsIntermediateChallenge.sortByKeys(
                    staff().subList(0, 1), List.of("Name")));
        }
    }

    @Nested
    class MergeSortedTests {

        private record Item(int key, String tag) {
        }

        private final Comparator<Item> byKey = Comparator.comparingInt(Item::key);

        @Test
        void testMergesSeveralSortedLists() {
            List<List<Integer>> input = new ArrayList<>(List.of(
                    new ArrayList<>(List.of(1, 4, 9)), new ArrayList<>(List.of(2, 3, 10, 11)),
                    new ArrayList<>(List.of(0, 5))));

            List<Integer> result = SortingComparatorsIntermediateChallenge.mergeSorted(
                    input, Comparator.naturalOrder());

            assertEquals(List.of(0, 1, 2, 3, 4, 5, 9, 10, 11), result);
            assertEquals(List.of(1, 4, 9), input.get(0));
            assertEquals(List.of(2, 3, 10, 11), input.get(1));
            assertEquals(List.of(0, 5), input.get(2));
        }

        @Test
        void testEqualElementsComeFromTheLowerIndexedListFirst() {
            List<Item> result = SortingComparatorsIntermediateChallenge.mergeSorted(List.of(
                    List.of(new Item(1, "a0"), new Item(2, "a1")),
                    List.of(new Item(1, "b0"), new Item(2, "b1")),
                    List.of(new Item(1, "c0"))), byKey);

            assertEquals(List.of("a0", "b0", "c0", "a1", "b1"),
                    result.stream().map(Item::tag).collect(Collectors.toList()));
        }

        @Test
        void testEqualElementsOfTheSameListKeepTheirOrder() {
            List<Item> result = SortingComparatorsIntermediateChallenge.mergeSorted(List.of(
                    List.of(new Item(5, "a0"), new Item(5, "a1"), new Item(5, "a2")),
                    List.of(new Item(5, "b0"))), byKey);

            assertEquals(List.of("a0", "a1", "a2", "b0"),
                    result.stream().map(Item::tag).collect(Collectors.toList()));
        }

        @Test
        void testEmptyListsAndEmptyOuterList() {
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.mergeSorted(
                    List.<List<Integer>>of(), Comparator.naturalOrder()));
            assertEquals(List.of(1, 2), SortingComparatorsIntermediateChallenge.mergeSorted(
                    List.of(List.<Integer>of(), List.of(1), List.<Integer>of(), List.of(2)),
                    Comparator.naturalOrder()));
        }

        @Test
        void testUsesTheGivenComparatorEvenWhenItIsNotTheNaturalOrder() {
            List<String> result = SortingComparatorsIntermediateChallenge.mergeSorted(
                    List.of(List.of("zz", "yy", "a"), List.of("xx", "b")), Comparator.<String>reverseOrder());

            assertEquals(List.of("zz", "yy", "xx", "b", "a"), result);
        }
    }

    @Nested
    @Timeout(30)
    class TopKTests {

        private record Item(int key, int id) {
        }

        private final Comparator<Item> byKey = Comparator.comparingInt(Item::key);

        @Test
        void testReturnsTheGreatestElementsGreatestFirst() {
            List<Integer> input = new ArrayList<>(List.of(5, 1, 9, 3, 7, 9, 2));

            List<Integer> result = SortingComparatorsIntermediateChallenge.topK(input, 3, Comparator.naturalOrder());

            assertEquals(List.of(9, 9, 7), result);
            assertEquals(List.of(5, 1, 9, 3, 7, 9, 2), input);
        }

        @Test
        void testTiesAtTheBoundaryFavourTheEarlierElement() {
            List<Item> items = List.of(new Item(5, 0), new Item(9, 1), new Item(5, 2), new Item(9, 3), new Item(5, 4));

            List<Item> result = SortingComparatorsIntermediateChallenge.topK(items, 3, byKey);

            assertEquals(List.of(new Item(9, 1), new Item(9, 3), new Item(5, 0)), result);
        }

        @Test
        void testKLargerThanTheListReturnsEverythingSorted() {
            assertEquals(List.of(3, 2, 1), SortingComparatorsIntermediateChallenge.topK(
                    List.of(2, 3, 1), 10, Comparator.naturalOrder()));
        }

        @Test
        void testZeroAndNegativeK() {
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.topK(
                    List.of(1, 2), 0, Comparator.naturalOrder()));
            assertEquals(List.of(), SortingComparatorsIntermediateChallenge.topK(
                    List.<Integer>of(), 3, Comparator.naturalOrder()));
            assertThrows(IllegalArgumentException.class, () -> SortingComparatorsIntermediateChallenge.topK(
                    List.of(1, 2), -1, Comparator.<Integer>naturalOrder()));
        }

        @Test
        void testMatchesAStableDescendingSortOnRandomDataWithManyTies() {
            Random random = new Random(7);
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                items.add(new Item(random.nextInt(20), i));
            }
            List<Item> expected = new ArrayList<>(items);
            expected.sort(byKey.reversed());

            for (int k : new int[] {1, 7, 50, 499, 500}) {
                assertEquals(expected.subList(0, k), SortingComparatorsIntermediateChallenge.topK(items, k, byKey));
            }
        }

        @Test
        void testDoesNotSortTheWholeListWhenKIsTiny() {
            Random random = new Random(11);
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                items.add(random.nextInt());
            }
            AtomicLong calls = new AtomicLong();
            Comparator<Integer> counting = (a, b) -> {
                calls.incrementAndGet();
                return Integer.compare(a, b);
            };
            List<Integer> expected = new ArrayList<>(items);
            expected.sort(Collections.reverseOrder());

            List<Integer> result = SortingComparatorsIntermediateChallenge.topK(items, 3, counting);

            assertEquals(expected.subList(0, 3), result);
            assertTrue(calls.get() < 600_000, "too many comparisons: " + calls.get());
        }
    }
}