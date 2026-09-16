package dev.perfectbogus.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sortByLengthThenAlphabetically
    // ==========================================================
    @Nested
    class SortByLengthThenAlphabeticallyTests {

        @Test
        void testSortsByLengthFirst() {
            List<String> input = List.of("banana", "fig", "apple", "kiwi");
            assertEquals(List.of("fig", "kiwi", "apple", "banana"),
                    SortingIntermediateChallenge.sortByLengthThenAlphabetically(input));
        }

        @Test
        void testSameLengthSortedAlphabetically() {
            List<String> input = List.of("dog", "cat", "bee", "ant");
            assertEquals(List.of("ant", "bee", "cat", "dog"),
                    SortingIntermediateChallenge.sortByLengthThenAlphabetically(input));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingIntermediateChallenge.sortByLengthThenAlphabetically(List.of()));
        }

        @Test
        void testOriginalListNotModified() {
            List<String> input = new ArrayList<>(List.of("bb", "a", "ccc"));
            List<String> originalCopy = new ArrayList<>(input);
            SortingIntermediateChallenge.sortByLengthThenAlphabetically(input);
            assertEquals(originalCopy, input);
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortByAgeDescending
    // ==========================================================
    @Nested
    class SortByAgeDescendingTests {

        @Test
        void testSortsOldestFirst() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Alice", 30),
                    new SortingIntermediateChallenge.Person("Bob", 50),
                    new SortingIntermediateChallenge.Person("Carol", 20)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortByAgeDescending(input);
            assertEquals(List.of("Bob", "Alice", "Carol"),
                    result.stream().map(SortingIntermediateChallenge.Person::getName).toList());
        }

        @Test
        void testSingleElement() {
            List<SortingIntermediateChallenge.Person> input = List.of(new SortingIntermediateChallenge.Person("Solo", 40));
            assertEquals(input, SortingIntermediateChallenge.sortByAgeDescending(input));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingIntermediateChallenge.sortByAgeDescending(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortIgnoringCase
    // ==========================================================
    @Nested
    class SortIgnoringCaseTests {

        @Test
        void testMixedCaseSortedAlphabetically() {
            List<String> input = List.of("banana", "Apple", "cherry");
            assertEquals(List.of("Apple", "banana", "cherry"), SortingIntermediateChallenge.sortIgnoringCase(input));
        }

        @Test
        void testAllUppercase() {
            List<String> input = List.of("ZEBRA", "APPLE", "MANGO");
            assertEquals(List.of("APPLE", "MANGO", "ZEBRA"), SortingIntermediateChallenge.sortIgnoringCase(input));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), SortingIntermediateChallenge.sortIgnoringCase(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortWithNullsFirst
    // ==========================================================
    @Nested
    class SortWithNullsFirstTests {

        @Test
        void testNullsPlacedFirst() {
            List<String> input = new ArrayList<>(Arrays.asList("banana", null, "apple", null, "cherry"));
            assertEquals(Arrays.asList(null, null, "apple", "banana", "cherry"),
                    SortingIntermediateChallenge.sortWithNullsFirst(input));
        }

        @Test
        void testNoNullsBehavesLikeNormalSort() {
            List<String> input = List.of("banana", "apple");
            assertEquals(List.of("apple", "banana"), SortingIntermediateChallenge.sortWithNullsFirst(input));
        }

        @Test
        void testAllNulls() {
            List<String> input = new ArrayList<>(Arrays.asList(null, null));
            assertEquals(Arrays.asList(null, null), SortingIntermediateChallenge.sortWithNullsFirst(input));
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortInPlaceAscending
    // ==========================================================
    @Nested
    class SortInPlaceAscendingTests {

        @Test
        void testSortsAscending() {
            int[] numbers = {5, 3, 8, 1, 9};
            SortingIntermediateChallenge.sortInPlaceAscending(numbers);
            assertArrayEquals(new int[]{1, 3, 5, 8, 9}, numbers);
        }

        @Test
        void testAlreadySorted() {
            int[] numbers = {1, 2, 3};
            SortingIntermediateChallenge.sortInPlaceAscending(numbers);
            assertArrayEquals(new int[]{1, 2, 3}, numbers);
        }

        @Test
        void testEmptyArray() {
            int[] numbers = {};
            SortingIntermediateChallenge.sortInPlaceAscending(numbers);
            assertArrayEquals(new int[]{}, numbers);
        }

        @Test
        void testWithNegativeNumbers() {
            int[] numbers = {3, -1, 2, -5};
            SortingIntermediateChallenge.sortInPlaceAscending(numbers);
            assertArrayEquals(new int[]{-5, -1, 2, 3}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortDescendingInPlace
    // ==========================================================
    @Nested
    class SortDescendingInPlaceTests {

        @Test
        void testSortsDescending() {
            Integer[] numbers = {5, 3, 8, 1, 9};
            SortingIntermediateChallenge.sortDescendingInPlace(numbers);
            assertArrayEquals(new Integer[]{9, 8, 5, 3, 1}, numbers);
        }

        @Test
        void testAlreadyDescending() {
            Integer[] numbers = {3, 2, 1};
            SortingIntermediateChallenge.sortDescendingInPlace(numbers);
            assertArrayEquals(new Integer[]{3, 2, 1}, numbers);
        }

        @Test
        void testSingleElement() {
            Integer[] numbers = {7};
            SortingIntermediateChallenge.sortDescendingInPlace(numbers);
            assertArrayEquals(new Integer[]{7}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortMapEntriesByValue
    // ==========================================================
    @Nested
    class SortMapEntriesByValueTests {

        @Test
        void testSortedByValueAscending() {
            Map<String, Integer> map = Map.of("c", 3, "a", 1, "b", 2);
            List<Map.Entry<String, Integer>> result = SortingIntermediateChallenge.sortMapEntriesByValue(map);
            assertEquals(List.of("a", "b", "c"), result.stream().map(Map.Entry::getKey).toList());
        }

        @Test
        void testValuesInAscendingOrder() {
            Map<String, Integer> map = Map.of("x", 100, "y", 5, "z", 42);
            List<Map.Entry<String, Integer>> result = SortingIntermediateChallenge.sortMapEntriesByValue(map);
            assertEquals(List.of(5, 42, 100), result.stream().map(Map.Entry::getValue).toList());
        }

        @Test
        void testEmptyMap() {
            assertEquals(List.of(), SortingIntermediateChallenge.sortMapEntriesByValue(Map.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: findIndexBinarySearch
    // ==========================================================
    @Nested
    class FindIndexBinarySearchTests {

        @Test
        void testFindsExistingElement() {
            List<Integer> sortedList = List.of(1, 3, 5, 7, 9);
            assertEquals(2, SortingIntermediateChallenge.findIndexBinarySearch(sortedList, 5));
        }

        @Test
        void testFindsFirstElement() {
            List<Integer> sortedList = List.of(1, 3, 5, 7, 9);
            assertEquals(0, SortingIntermediateChallenge.findIndexBinarySearch(sortedList, 1));
        }

        @Test
        void testMissingElementReturnsNegative() {
            List<Integer> sortedList = List.of(1, 3, 5, 7, 9);
            assertTrue(SortingIntermediateChallenge.findIndexBinarySearch(sortedList, 4) < 0);
        }

        @Test
        void testEmptyListReturnsNegative() {
            assertTrue(SortingIntermediateChallenge.findIndexBinarySearch(List.of(), 1) < 0);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortByNameThenAgeDescending
    // ==========================================================
    @Nested
    class SortByNameThenAgeDescendingTests {

        @Test
        void testSortsByNameAlphabetically() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Bob", 30),
                    new SortingIntermediateChallenge.Person("Alice", 25)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortByNameThenAgeDescending(input);
            assertEquals(List.of("Alice", "Bob"),
                    result.stream().map(SortingIntermediateChallenge.Person::getName).toList());
        }

        @Test
        void testSameNameSortedByAgeDescending() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Alice", 25),
                    new SortingIntermediateChallenge.Person("Alice", 40),
                    new SortingIntermediateChallenge.Person("Alice", 30)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortByNameThenAgeDescending(input);
            assertEquals(List.of(40, 30, 25),
                    result.stream().map(SortingIntermediateChallenge.Person::getAge).toList());
        }

        @Test
        void testMixedNamesAndAges() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Bob", 20),
                    new SortingIntermediateChallenge.Person("Alice", 40),
                    new SortingIntermediateChallenge.Person("Alice", 25),
                    new SortingIntermediateChallenge.Person("Bob", 50)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortByNameThenAgeDescending(input);
            assertEquals(
                    List.of(
                            new SortingIntermediateChallenge.Person("Alice", 40),
                            new SortingIntermediateChallenge.Person("Alice", 25),
                            new SortingIntermediateChallenge.Person("Bob", 50),
                            new SortingIntermediateChallenge.Person("Bob", 20)
                    ),
                    result
            );
        }
    }

    // ==========================================================
    // CHALLENGE 10: sortPreservingInsertionOrderForTies
    // ==========================================================
    @Nested
    class SortPreservingInsertionOrderForTiesTests {

        @Test
        void testSortsByAgeAscending() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Alice", 30),
                    new SortingIntermediateChallenge.Person("Bob", 20)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortPreservingInsertionOrderForTies(input);
            assertEquals(List.of("Bob", "Alice"),
                    result.stream().map(SortingIntermediateChallenge.Person::getName).toList());
        }

        @Test
        void testTiesKeepOriginalRelativeOrder() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Carol", 25),
                    new SortingIntermediateChallenge.Person("Alice", 25),
                    new SortingIntermediateChallenge.Person("Bob", 25)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortPreservingInsertionOrderForTies(input);
            assertEquals(List.of("Carol", "Alice", "Bob"),
                    result.stream().map(SortingIntermediateChallenge.Person::getName).toList());
        }

        @Test
        void testMixedTiesAndDistinctAges() {
            List<SortingIntermediateChallenge.Person> input = List.of(
                    new SortingIntermediateChallenge.Person("Dan", 40),
                    new SortingIntermediateChallenge.Person("Eve", 20),
                    new SortingIntermediateChallenge.Person("Frank", 20),
                    new SortingIntermediateChallenge.Person("Gina", 30)
            );
            List<SortingIntermediateChallenge.Person> result = SortingIntermediateChallenge.sortPreservingInsertionOrderForTies(input);
            assertEquals(List.of("Eve", "Frank", "Gina", "Dan"),
                    result.stream().map(SortingIntermediateChallenge.Person::getName).toList());
        }
    }
}