package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: uniqueWordLengthsSorted
    // ==========================================================
    @Nested
    class UniqueWordLengthsSortedTests {

        @Test
        void testReturnsDistinctLengthsSortedAscending() {
            List<String> words = List.of("a", "bb", "ccc", "dd", "e");
            assertEquals(List.of(1, 2, 3), CollectIntermediateChallenge.uniqueWordLengthsSorted(words));
        }
    }

    // ==========================================================
    // CHALLENGE 2: joinWithSeparator
    // ==========================================================
    @Nested
    class JoinWithSeparatorTests {

        @Test
        void testJoinsWithCommaSpace() {
            assertEquals("a, b, c", CollectIntermediateChallenge.joinWithSeparator(List.of("a", "b", "c")));
        }
    }

    // ==========================================================
    // CHALLENGE 3: joinWithPrefixSuffix
    // ==========================================================
    @Nested
    class JoinWithPrefixSuffixTests {

        @Test
        void testJoinsWithBrackets() {
            assertEquals("[a, b, c]", CollectIntermediateChallenge.joinWithPrefixSuffix(List.of("a", "b", "c")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: distinctFirstLetters
    // ==========================================================
    @Nested
    class DistinctFirstLettersTests {

        @Test
        void testReturnsDistinctFirstCharacters() {
            List<String> words = List.of("apple", "avocado", "banana", "blueberry", "cherry");
            assertEquals(Set.of('a', 'b', 'c'), CollectIntermediateChallenge.distinctFirstLetters(words));
        }
    }

    // ==========================================================
    // CHALLENGE 5: mapNameToLength
    // ==========================================================
    @Nested
    class MapNameToLengthTests {

        @Test
        void testMapsEachWordToItsLength() {
            List<String> words = List.of("apple", "kiwi", "fig");
            Map<String, Integer> expected = Map.of("apple", 5, "kiwi", 4, "fig", 3);
            assertEquals(expected, CollectIntermediateChallenge.mapNameToLength(words));
        }
    }

    // ==========================================================
    // CHALLENGE 6: countFirstLetterOccurrences
    // ==========================================================
    @Nested
    class CountFirstLetterOccurrencesTests {

        @Test
        void testCountsWordsPerFirstLetter() {
            List<String> words = List.of("apple", "avocado", "banana", "blueberry", "cherry");
            Map<Character, Long> expected = Map.of('a', 2L, 'b', 2L, 'c', 1L);
            assertEquals(expected, CollectIntermediateChallenge.countFirstLetterOccurrences(words));
        }
    }

    // ==========================================================
    // CHALLENGE 7: groupWordsByLength
    // ==========================================================
    @Nested
    class GroupWordsByLengthTests {

        @Test
        void testGroupsWordsByLength() {
            List<String> words = List.of("a", "bb", "cc", "ddd");
            Map<Integer, List<String>> expected = Map.of(
                    1, List.of("a"),
                    2, List.of("bb", "cc"),
                    3, List.of("ddd"));
            assertEquals(expected, CollectIntermediateChallenge.groupWordsByLength(words));
        }
    }

    // ==========================================================
    // CHALLENGE 8: groupWordsByLengthSortedKeys
    // ==========================================================
    @Nested
    class GroupWordsByLengthSortedKeysTests {

        @Test
        void testKeysIterateInAscendingOrder() {
            List<String> words = List.of("ddd", "a", "cc", "bb");
            Map<Integer, List<String>> result = CollectIntermediateChallenge.groupWordsByLengthSortedKeys(words);

            assertEquals(List.of(1, 2, 3), new ArrayList<>(result.keySet()));
            assertEquals(List.of("a"), result.get(1));
            assertEquals(List.of("cc", "bb"), result.get(2));
            assertEquals(List.of("ddd"), result.get(3));
        }
    }

    // ==========================================================
    // CHALLENGE 9: groupLengthsByFirstLetter
    // ==========================================================
    @Nested
    class GroupLengthsByFirstLetterTests {

        @Test
        void testGroupsLengthsByFirstLetter() {
            List<String> words = List.of("apple", "avocado", "banana", "blueberry");
            Map<Character, List<Integer>> expected = Map.of(
                    'a', List.of(5, 7),
                    'b', List.of(6, 9));
            assertEquals(expected, CollectIntermediateChallenge.groupLengthsByFirstLetter(words));
        }
    }

    // ==========================================================
    // CHALLENGE 10: partitionByEvenLength
    // ==========================================================
    @Nested
    class PartitionByEvenLengthTests {

        @Test
        void testSplitsIntoEvenAndOddLengthWords() {
            List<String> words = List.of("a", "bb", "ccc", "dddd");
            Map<Boolean, List<String>> result = CollectIntermediateChallenge.partitionByEvenLength(words);

            assertEquals(List.of("bb", "dddd"), result.get(true));
            assertEquals(List.of("a", "ccc"), result.get(false));
        }
    }

    // ==========================================================
    // CHALLENGE 11: summarizeSalaries
    // ==========================================================
    @Nested
    class SummarizeSalariesTests {

        @Test
        void testComputesAllFiveStatistics() {
            List<CollectIntermediateChallenge.Employee> employees = List.of(
                    new CollectIntermediateChallenge.Employee("A", "Eng", 50000),
                    new CollectIntermediateChallenge.Employee("B", "Sales", 60000),
                    new CollectIntermediateChallenge.Employee("C", "Eng", 70000));

            DoubleSummaryStatistics stats = CollectIntermediateChallenge.summarizeSalaries(employees);

            assertEquals(3, stats.getCount());
            assertEquals(180000.0, stats.getSum());
            assertEquals(50000.0, stats.getMin());
            assertEquals(70000.0, stats.getMax());
            assertEquals(60000.0, stats.getAverage());
        }
    }

    // ==========================================================
    // CHALLENGE 12: totalSalaryByDepartment
    // ==========================================================
    @Nested
    class TotalSalaryByDepartmentTests {

        @Test
        void testSumsSalariesPerDepartment() {
            List<CollectIntermediateChallenge.Employee> employees = List.of(
                    new CollectIntermediateChallenge.Employee("A", "Eng", 50000),
                    new CollectIntermediateChallenge.Employee("B", "Sales", 60000),
                    new CollectIntermediateChallenge.Employee("C", "Eng", 70000));

            Map<String, Double> expected = Map.of("Eng", 120000.0, "Sales", 60000.0);
            assertEquals(expected, CollectIntermediateChallenge.totalSalaryByDepartment(employees));
        }
    }

    // ==========================================================
    // CHALLENGE 13: longestWordReducing
    // ==========================================================
    @Nested
    class LongestWordReducingTests {

        @Test
        void testReturnsLongestWord() {
            assertEquals(Optional.of("bbb"), CollectIntermediateChallenge.longestWordReducing(List.of("a", "bbb", "cc")));
        }

        @Test
        void testEmptyListReturnsEmptyOptional() {
            assertEquals(Optional.empty(), CollectIntermediateChallenge.longestWordReducing(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 14: collectToImmutableSortedList
    // ==========================================================
    @Nested
    class CollectToImmutableSortedListTests {

        @Test
        void testReturnsDistinctSortedWords() {
            List<String> words = List.of("banana", "apple", "banana", "cherry");
            assertEquals(List.of("apple", "banana", "cherry"),
                    CollectIntermediateChallenge.collectToImmutableSortedList(words));
        }

        @Test
        void testResultIsUnmodifiable() {
            List<String> result = CollectIntermediateChallenge.collectToImmutableSortedList(
                    List.of("banana", "apple", "cherry"));

            assertThrows(UnsupportedOperationException.class, () -> result.add("date"));
        }
    }

    // ==========================================================
    // CHALLENGE 15: averageAndCountTeeing
    // ==========================================================
    @Nested
    class AverageAndCountTeeingTests {

        @Test
        void testFormatsCountAndAverage() {
            List<CollectIntermediateChallenge.Employee> employees = List.of(
                    new CollectIntermediateChallenge.Employee("A", "Eng", 50000),
                    new CollectIntermediateChallenge.Employee("B", "Sales", 60000),
                    new CollectIntermediateChallenge.Employee("C", "Eng", 70000));

            assertEquals("count=3, average=60000.00", CollectIntermediateChallenge.averageAndCountTeeing(employees));
        }
    }
}