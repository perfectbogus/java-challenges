package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorsIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: joinWithBrackets
    // ==========================================================
    @Nested
    class JoinWithBracketsTests {

        @Test
        void testMultipleNames() {
            assertEquals("[Ann, Bo, Cy]", CollectorsIntermediateChallenge.joinWithBrackets(List.of("Ann", "Bo", "Cy")));
        }

        @Test
        void testSingleName() {
            assertEquals("[Solo]", CollectorsIntermediateChallenge.joinWithBrackets(List.of("Solo")));
        }

        @Test
        void testEmptyList() {
            assertEquals("[]", CollectorsIntermediateChallenge.joinWithBrackets(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: partitionEvenOdd
    // ==========================================================
    @Nested
    class PartitionEvenOddTests {

        @Test
        void testMixedNumbers() {
            Map<Boolean, List<Integer>> result = CollectorsIntermediateChallenge.partitionEvenOdd(List.of(1, 2, 3, 4, 5, 6));
            assertEquals(List.of(2, 4, 6), result.get(true));
            assertEquals(List.of(1, 3, 5), result.get(false));
        }

        @Test
        void testAllEven() {
            Map<Boolean, List<Integer>> result = CollectorsIntermediateChallenge.partitionEvenOdd(List.of(2, 4, 6));
            assertEquals(List.of(2, 4, 6), result.get(true));
            assertTrue(result.get(false).isEmpty());
        }

        @Test
        void testEmptyList() {
            Map<Boolean, List<Integer>> result = CollectorsIntermediateChallenge.partitionEvenOdd(List.of());
            assertTrue(result.get(true).isEmpty());
            assertTrue(result.get(false).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 3: countByLength
    // ==========================================================
    @Nested
    class CountByLengthTests {

        @Test
        void testBasicCounting() {
            Map<Integer, Long> result = CollectorsIntermediateChallenge.countByLength(List.of("cat", "dog", "bird", "fox"));
            assertEquals(3L, result.get(3));
            assertEquals(1L, result.get(4));
        }

        @Test
        void testAllSameLength() {
            Map<Integer, Long> result = CollectorsIntermediateChallenge.countByLength(List.of("aa", "bb", "cc"));
            assertEquals(3L, result.get(2));
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(CollectorsIntermediateChallenge.countByLength(List.of()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 4: groupByLengthUppercase
    // ==========================================================
    @Nested
    class GroupByLengthUppercaseTests {

        @Test
        void testGroupsAndUppercases() {
            Map<Integer, List<String>> result = CollectorsIntermediateChallenge.groupByLengthUppercase(List.of("cat", "dog", "bird"));
            assertEquals(List.of("CAT", "DOG"), result.get(3));
            assertEquals(List.of("BIRD"), result.get(4));
        }

        @Test
        void testPreservesRelativeOrderWithinGroup() {
            Map<Integer, List<String>> result = CollectorsIntermediateChallenge.groupByLengthUppercase(List.of("zz", "aa", "bb"));
            assertEquals(List.of("ZZ", "AA", "BB"), result.get(2));
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(CollectorsIntermediateChallenge.groupByLengthUppercase(List.of()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 5: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testCountsDuplicates() {
            Map<String, Integer> result = CollectorsIntermediateChallenge.countOccurrences(List.of("a", "b", "a", "c", "a", "b"));
            assertEquals(3, result.get("a"));
            assertEquals(2, result.get("b"));
            assertEquals(1, result.get("c"));
        }

        @Test
        void testNoDuplicates() {
            Map<String, Integer> result = CollectorsIntermediateChallenge.countOccurrences(List.of("x", "y", "z"));
            assertEquals(1, result.get("x"));
            assertEquals(1, result.get("y"));
            assertEquals(1, result.get("z"));
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(CollectorsIntermediateChallenge.countOccurrences(List.of()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 6: getStatistics
    // ==========================================================
    @Nested
    class GetStatisticsTests {

        @Test
        void testBasicStatistics() {
            IntSummaryStatistics stats = CollectorsIntermediateChallenge.getStatistics(List.of(1, 2, 3, 4, 5));
            assertEquals(5, stats.getCount());
            assertEquals(15, stats.getSum());
            assertEquals(1, stats.getMin());
            assertEquals(5, stats.getMax());
            assertEquals(3.0, stats.getAverage());
        }

        @Test
        void testSingleValue() {
            IntSummaryStatistics stats = CollectorsIntermediateChallenge.getStatistics(List.of(42));
            assertEquals(1, stats.getCount());
            assertEquals(42, stats.getMin());
            assertEquals(42, stats.getMax());
        }

        @Test
        void testEmptyListCountIsZero() {
            IntSummaryStatistics stats = CollectorsIntermediateChallenge.getStatistics(List.of());
            assertEquals(0, stats.getCount());
            assertEquals(0, stats.getSum());
        }
    }

    // ==========================================================
    // CHALLENGE 7: reduceToMax
    // ==========================================================
    @Nested
    class ReduceToMaxTests {

        @Test
        void testFindsMax() {
            assertEquals(Optional.of(9), CollectorsIntermediateChallenge.reduceToMax(List.of(3, 9, 1, 7)));
        }

        @Test
        void testSingleElement() {
            assertEquals(Optional.of(5), CollectorsIntermediateChallenge.reduceToMax(List.of(5)));
        }

        @Test
        void testEmptyListReturnsEmptyOptional() {
            assertEquals(Optional.empty(), CollectorsIntermediateChallenge.reduceToMax(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: toImmutableList
    // ==========================================================
    @Nested
    class ToImmutableListTests {

        @Test
        void testContainsSameElements() {
            List<String> result = CollectorsIntermediateChallenge.toImmutableList(List.of("a", "b", "c"));
            assertEquals(List.of("a", "b", "c"), result);
        }

        @Test
        void testResultIsUnmodifiable() {
            List<String> result = CollectorsIntermediateChallenge.toImmutableList(List.of("a", "b"));
            assertThrows(UnsupportedOperationException.class, () -> result.add("c"));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), CollectorsIntermediateChallenge.toImmutableList(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 9: countDistinctFirstLetters
    // ==========================================================
    @Nested
    class CountDistinctFirstLettersTests {

        @Test
        void testCountsDistinctLetters() {
            assertEquals(3, CollectorsIntermediateChallenge.countDistinctFirstLetters(List.of("apple", "banana", "cherry")));
        }

        @Test
        void testDuplicateFirstLettersCountedOnce() {
            assertEquals(1, CollectorsIntermediateChallenge.countDistinctFirstLetters(List.of("apple", "avocado", "ant")));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, CollectorsIntermediateChallenge.countDistinctFirstLetters(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: groupAndJoinByLength
    // ==========================================================
    @Nested
    class GroupAndJoinByLengthTests {

        @Test
        void testGroupsAndJoins() {
            Map<Integer, String> result = CollectorsIntermediateChallenge.groupAndJoinByLength(List.of("cat", "dog", "bird"));
            assertEquals("cat, dog", result.get(3));
            assertEquals("bird", result.get(4));
        }

        @Test
        void testPreservesRelativeOrderWithinGroup() {
            Map<Integer, String> result = CollectorsIntermediateChallenge.groupAndJoinByLength(List.of("zz", "aa", "bb"));
            assertEquals("zz, aa, bb", result.get(2));
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(CollectorsIntermediateChallenge.groupAndJoinByLength(List.of()).isEmpty());
        }
    }
}