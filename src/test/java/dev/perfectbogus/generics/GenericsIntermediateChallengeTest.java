package dev.perfectbogus.generics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenericsIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: firstElement
    // ==========================================================
    @Nested
    class FirstElementTests {

        @Test
        void testReturnsFirstElement() {
            assertEquals(10, GenericsIntermediateChallenge.firstElement(List.of(10, 20, 30)));
        }

        @Test
        void testReturnsNullForEmptyList() {
            assertNull(GenericsIntermediateChallenge.firstElement(new ArrayList<Integer>()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: max
    // ==========================================================
    @Nested
    class MaxTests {

        @Test
        void testReturnsLargestElement() {
            assertEquals(9, GenericsIntermediateChallenge.max(List.of(3, 7, 2, 9, 4)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: countGreaterThan
    // ==========================================================
    @Nested
    class CountGreaterThanTests {

        @Test
        void testCountsElementsAboveThreshold() {
            assertEquals(2L, GenericsIntermediateChallenge.countGreaterThan(List.of(1, 5, 3, 8, 2), 3));
        }
    }

    // ==========================================================
    // CHALLENGE 4: sumNumbers
    // ==========================================================
    @Nested
    class SumNumbersTests {

        @Test
        void testSumsMixedNumberTypes() {
            List<Number> numbers = List.of(1, 2.5, 3L);
            assertEquals(6.5, GenericsIntermediateChallenge.sumNumbers(numbers));
        }
    }

    // ==========================================================
    // CHALLENGE 5: addIntegers
    // ==========================================================
    @Nested
    class AddIntegersTests {

        @Test
        void testAppendsIntegersAscending() {
            List<Number> list = new ArrayList<>();
            GenericsIntermediateChallenge.addIntegers(list, 3);
            assertEquals(List.of(1, 2, 3), list);
        }
    }

    // ==========================================================
    // CHALLENGE 6: copy
    // ==========================================================
    @Nested
    class CopyTests {

        @Test
        void testAppendsSourceElementsToDest() {
            List<Integer> source = List.of(1, 2, 3);
            List<Number> dest = new ArrayList<>(List.of(0));
            GenericsIntermediateChallenge.copy(source, dest);
            assertEquals(List.of(0, 1, 2, 3), dest);
        }
    }

    // ==========================================================
    // CHALLENGE 7: describeAll
    // ==========================================================
    @Nested
    class DescribeAllTests {

        @Test
        void testJoinsElementsOfMixedTypes() {
            assertEquals("[1, two, 3.0]", GenericsIntermediateChallenge.describeAll(List.of(1, "two", 3.0)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: swapFirstAndLast
    // ==========================================================
    @Nested
    class SwapFirstAndLastTests {

        @Test
        void testSwapsFirstAndLastInPlace() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4));
            GenericsIntermediateChallenge.swapFirstAndLast(list);
            assertEquals(List.of(4, 2, 3, 1), list);
        }
    }

    // ==========================================================
    // CHALLENGE 9: swap
    // ==========================================================
    @Nested
    class SwapTests {

        @Test
        void testSwapsPairComponents() {
            GenericsIntermediateChallenge.Pair<String, Integer> pair =
                    new GenericsIntermediateChallenge.Pair<>("a", 1);
            assertEquals(new GenericsIntermediateChallenge.Pair<>(1, "a"),
                    GenericsIntermediateChallenge.swap(pair));
        }
    }

    // ==========================================================
    // CHALLENGE 10: combinePairs
    // ==========================================================
    @Nested
    class CombinePairsTests {

        @Test
        void testCombinesEachPairInOrder() {
            List<GenericsIntermediateChallenge.Pair<String, Integer>> pairs = List.of(
                    new GenericsIntermediateChallenge.Pair<>("a", 1),
                    new GenericsIntermediateChallenge.Pair<>("b", 2));

            List<String> result = GenericsIntermediateChallenge.combinePairs(pairs, (s, i) -> s + i);
            assertEquals(List.of("a1", "b2"), result);
        }
    }

    // ==========================================================
    // CHALLENGE 11: clamp
    // ==========================================================
    @Nested
    class ClampTests {

        @Test
        void testValueWithinRange() {
            assertEquals(5, GenericsIntermediateChallenge.clamp(5, 1, 10));
        }

        @Test
        void testValueBelowRange() {
            assertEquals(1, GenericsIntermediateChallenge.clamp(-3, 1, 10));
        }

        @Test
        void testValueAboveRange() {
            assertEquals(10, GenericsIntermediateChallenge.clamp(15, 1, 10));
        }
    }

    // ==========================================================
    // CHALLENGE 12: filterByType
    // ==========================================================
    @Nested
    class FilterByTypeTests {

        @Test
        void testKeepsOnlyMatchingType() {
            List<Object> list = List.of(1, "two", 3, "four", 5.0);
            assertEquals(List.of(1, 3), GenericsIntermediateChallenge.filterByType(list, Integer.class));
        }
    }

    // ==========================================================
    // CHALLENGE 13: isSorted
    // ==========================================================
    @Nested
    class IsSortedTests {

        @Test
        void testSortedListReturnsTrue() {
            assertTrue(GenericsIntermediateChallenge.isSorted(List.of(1, 2, 2, 5)));
        }

        @Test
        void testUnsortedListReturnsFalse() {
            assertFalse(GenericsIntermediateChallenge.isSorted(List.of(1, 3, 2)));
        }

        @Test
        void testEmptyListReturnsTrue() {
            assertTrue(GenericsIntermediateChallenge.isSorted(new ArrayList<Integer>()));
        }
    }

    // ==========================================================
    // CHALLENGE 14: mergeSorted
    // ==========================================================
    @Nested
    class MergeSortedTests {

        @Test
        void testMergesTwoSortedLists() {
            List<Integer> a = List.of(1, 3, 5);
            List<Integer> b = List.of(2, 3, 6);
            assertEquals(List.of(1, 2, 3, 3, 5, 6), GenericsIntermediateChallenge.mergeSorted(a, b));
        }
    }

    // ==========================================================
    // CHALLENGE 15: maxByKey
    // ==========================================================
    @Nested
    class MaxByKeyTests {

        @Test
        void testReturnsElementWithLargestKey() {
            assertEquals("bbb", GenericsIntermediateChallenge.maxByKey(List.of("a", "bbb", "cc"), String::length));
        }

        @Test
        void testTieReturnsFirstEncountered() {
            assertEquals("xx", GenericsIntermediateChallenge.maxByKey(List.of("xx", "yy", "z"), String::length));
        }
    }
}