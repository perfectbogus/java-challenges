package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollectingAndThenChallengeTest {

    private static boolean canAdd(List<String> list) {
        try {
            list.add("new");
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    private static boolean canRemove(List<String> list) {
        try {
            list.remove(0);
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    private static boolean canSet(List<String> list) {
        try {
            list.set(0, "new");
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    private static boolean canPut(Map<Integer, List<String>> map) {
        try {
            map.put(99, List.of("new"));
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    private static boolean canRemoveKey(Map<Integer, List<String>> map) {
        try {
            map.remove(1);
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    // ==========================================================
    // CHALLENGE 1: unmodifiableCopy
    // ==========================================================
    @Nested
    class UnmodifiableCopyTests {

        @Test
        void testSameElementsAndOrder() {
            assertEquals(List.of("a", "b", "c"), CollectingAndThenChallenge.unmodifiableCopy(List.of("a", "b", "c")));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), CollectingAndThenChallenge.unmodifiableCopy(List.of()));
        }

        @Test
        void testCannotAdd() {
            List<String> result = CollectingAndThenChallenge.unmodifiableCopy(new ArrayList<>(List.of("a")));
            assertFalse(canAdd(result));
        }

        @Test
        void testCannotRemove() {
            List<String> result = CollectingAndThenChallenge.unmodifiableCopy(new ArrayList<>(List.of("a", "b")));
            assertFalse(canRemove(result));
        }

        @Test
        void testCannotSet() {
            List<String> result = CollectingAndThenChallenge.unmodifiableCopy(new ArrayList<>(List.of("a", "b")));
            assertFalse(canSet(result));
        }

        @Test
        void testIsACopyNotAView() {
            List<String> original = new ArrayList<>(List.of("a", "b"));
            List<String> result = CollectingAndThenChallenge.unmodifiableCopy(original);
            original.add("c");
            original.set(0, "z");
            assertEquals(List.of("a", "b"), result);
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(List.of("a", "a", "b"),
                    CollectingAndThenChallenge.unmodifiableCopy(List.of("a", "a", "b")));
        }

        @Test
        void testOrderIsKeptEvenWhenItIsNotAlphabetical() {
            assertEquals(List.of("c", "a", "b"), CollectingAndThenChallenge.unmodifiableCopy(List.of("c", "a", "b")));
        }

        @Test
        void testOriginalListIsNotModified() {
            List<String> original = new ArrayList<>(List.of("b", "a"));
            CollectingAndThenChallenge.unmodifiableCopy(original);
            assertEquals(List.of("b", "a"), original);
        }

        @Test
        void testEmptyResultCannotBeChangedEither() {
            List<String> result = CollectingAndThenChallenge.unmodifiableCopy(new ArrayList<>());
            assertFalse(canAdd(result));
        }
    }

    // ==========================================================
    // CHALLENGE 2: countDistinct
    // ==========================================================
    @Nested
    class CountDistinctTests {

        @Test
        void testWithRepeatedWords() {
            assertEquals(3, CollectingAndThenChallenge.countDistinct(List.of("a", "b", "a", "c", "b")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(2, CollectingAndThenChallenge.countDistinct(List.of("x", "X")));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectingAndThenChallenge.countDistinct(List.of()));
        }

        @Test
        void testAllDifferent() {
            assertEquals(4, CollectingAndThenChallenge.countDistinct(List.of("a", "b", "c", "d")));
        }

        @Test
        void testAllEqual() {
            assertEquals(1, CollectingAndThenChallenge.countDistinct(List.of("a", "a", "a")));
        }

        @Test
        void testSingleWord() {
            assertEquals(1, CollectingAndThenChallenge.countDistinct(List.of("a")));
        }

        @Test
        void testEmptyTextIsAWord() {
            assertEquals(2, CollectingAndThenChallenge.countDistinct(List.of("", "", "a")));
        }

        @Test
        void testSpacesMatter() {
            assertEquals(2, CollectingAndThenChallenge.countDistinct(List.of("a", "a ")));
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortedUnique
    // ==========================================================
    @Nested
    class SortedUniqueTests {

        @Test
        void testSortsAndRemovesRepeated() {
            assertEquals(List.of("apple", "fig", "pear"),
                    CollectingAndThenChallenge.sortedUnique(List.of("pear", "apple", "pear", "fig")));
        }

        @Test
        void testUppercaseComesFirst() {
            assertEquals(List.of("B", "a", "b"), CollectingAndThenChallenge.sortedUnique(List.of("b", "B", "a")));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), CollectingAndThenChallenge.sortedUnique(List.of()));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(List.of("a", "b", "c"), CollectingAndThenChallenge.sortedUnique(List.of("a", "b", "c")));
        }

        @Test
        void testReverseOrder() {
            assertEquals(List.of("a", "b", "c"), CollectingAndThenChallenge.sortedUnique(List.of("c", "b", "a")));
        }

        @Test
        void testAllEqual() {
            assertEquals(List.of("x"), CollectingAndThenChallenge.sortedUnique(List.of("x", "x", "x")));
        }

        @Test
        void testResultCanBeChanged() {
            List<String> result = CollectingAndThenChallenge.sortedUnique(List.of("b", "a"));
            result.add("z");
            assertEquals(List.of("a", "b", "z"), result);
        }

        @Test
        void testShorterPrefixComesFirst() {
            assertEquals(List.of("a", "ab", "abc"),
                    CollectingAndThenChallenge.sortedUnique(List.of("abc", "a", "ab")));
        }

        @Test
        void testOriginalListIsNotModified() {
            List<String> original = new ArrayList<>(List.of("b", "a", "b"));
            CollectingAndThenChallenge.sortedUnique(original);
            assertEquals(List.of("b", "a", "b"), original);
        }
    }

    // ==========================================================
    // CHALLENGE 4: longestWord
    // ==========================================================
    @Nested
    class LongestWordTests {

        @Test
        void testLongest() {
            assertEquals("abc", CollectingAndThenChallenge.longestWord(List.of("a", "abc", "ab")));
        }

        @Test
        void testTieKeepsTheFirst() {
            assertEquals("ab", CollectingAndThenChallenge.longestWord(List.of("ab", "cd", "e")));
        }

        @Test
        void testTieKeepsTheFirstWhenTheLastTies() {
            assertEquals("xy", CollectingAndThenChallenge.longestWord(List.of("a", "xy", "b", "zz")));
        }

        @Test
        void testEmptyListGivesEmptyText() {
            assertEquals("", CollectingAndThenChallenge.longestWord(List.of()));
        }

        @Test
        void testSingleWord() {
            assertEquals("solo", CollectingAndThenChallenge.longestWord(List.of("solo")));
        }

        @Test
        void testLongestAtTheStart() {
            assertEquals("abcd", CollectingAndThenChallenge.longestWord(List.of("abcd", "a", "ab")));
        }

        @Test
        void testLongestAtTheEnd() {
            assertEquals("abcd", CollectingAndThenChallenge.longestWord(List.of("a", "ab", "abcd")));
        }

        @Test
        void testOnlyEmptyTexts() {
            assertEquals("", CollectingAndThenChallenge.longestWord(List.of("", "")));
        }

        @Test
        void testSpacesCount() {
            assertEquals("   ", CollectingAndThenChallenge.longestWord(List.of("ab", "   ")));
        }
    }

    // ==========================================================
    // CHALLENGE 5: averageRounded
    // ==========================================================
    @Nested
    class AverageRoundedTests {

        @Test
        void testRoundsDown() {
            assertEquals(2L, (long) CollectingAndThenChallenge.averageRounded(List.of(1, 2, 4)));
        }

        @Test
        void testHalfGoesUp() {
            assertEquals(2L, (long) CollectingAndThenChallenge.averageRounded(List.of(1, 2)));
        }

        @Test
        void testRoundsUp() {
            assertEquals(3L, (long) CollectingAndThenChallenge.averageRounded(List.of(2, 3, 4, 4)));
        }

        @Test
        void testHalfGoesUpAlsoWhenTheWholePartIsEven() {
            assertEquals(3L, (long) CollectingAndThenChallenge.averageRounded(List.of(2, 3)));
            assertEquals(1L, (long) CollectingAndThenChallenge.averageRounded(List.of(0, 1)));
        }

        @Test
        void testEmptyList() {
            assertEquals(0L, (long) CollectingAndThenChallenge.averageRounded(List.of()));
        }

        @Test
        void testSingleNumber() {
            assertEquals(7L, (long) CollectingAndThenChallenge.averageRounded(List.of(7)));
        }

        @Test
        void testNegativeHalfGoesTowardsZero() {
            assertEquals(-2L, (long) CollectingAndThenChallenge.averageRounded(List.of(-2, -3)));
        }

        @Test
        void testNegativeRoundsDown() {
            assertEquals(-3L, (long) CollectingAndThenChallenge.averageRounded(List.of(-3, -3, -4)));
        }

        @Test
        void testBigNumbersDoNotOverflow() {
            assertEquals(2_000_000_000L, (long) CollectingAndThenChallenge.averageRounded(
                    List.of(2_000_000_000, 2_000_000_000)));
        }

        @Test
        void testNotTruncated() {
            assertEquals(3L, (long) CollectingAndThenChallenge.averageRounded(List.of(2, 3, 3)));
        }
    }

    // ==========================================================
    // CHALLENGE 6: bracketed
    // ==========================================================
    @Nested
    class BracketedTests {

        @Test
        void testSeveralWords() {
            assertEquals("[a,b,c]", CollectingAndThenChallenge.bracketed(List.of("a", "b", "c")));
        }

        @Test
        void testOneWord() {
            assertEquals("[x]", CollectingAndThenChallenge.bracketed(List.of("x")));
        }

        @Test
        void testEmptyList() {
            assertEquals("[]", CollectingAndThenChallenge.bracketed(List.of()));
        }

        @Test
        void testNoSpacesAreAdded() {
            assertEquals("[ab,cd]", CollectingAndThenChallenge.bracketed(List.of("ab", "cd")));
        }

        @Test
        void testSpacesInsideWordsAreKept() {
            assertEquals("[a b, c]", CollectingAndThenChallenge.bracketed(List.of("a b", " c")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals("[a,a]", CollectingAndThenChallenge.bracketed(List.of("a", "a")));
        }

        @Test
        void testOrderIsKept() {
            assertEquals("[z,y,x]", CollectingAndThenChallenge.bracketed(List.of("z", "y", "x")));
        }

        @Test
        void testEmptyWord() {
            assertEquals("[]", CollectingAndThenChallenge.bracketed(List.of("")));
        }
    }

    // ==========================================================
    // CHALLENGE 7: groupByLengthReadOnly
    // ==========================================================
    @Nested
    class GroupByLengthReadOnlyTests {

        @Test
        void testGroups() {
            assertEquals(Map.of(1, List.of("a", "c"), 2, List.of("bb", "dd"), 3, List.of("eee")),
                    CollectingAndThenChallenge.groupByLengthReadOnly(List.of("a", "bb", "c", "dd", "eee")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectingAndThenChallenge.groupByLengthReadOnly(List.of()));
        }

        @Test
        void testCannotPut() {
            Map<Integer, List<String>> result = CollectingAndThenChallenge.groupByLengthReadOnly(List.of("a"));
            assertFalse(canPut(result));
        }

        @Test
        void testCannotRemove() {
            Map<Integer, List<String>> result = CollectingAndThenChallenge.groupByLengthReadOnly(List.of("a"));
            assertFalse(canRemoveKey(result));
        }

        @Test
        void testEmptyResultCannotBeChanged() {
            Map<Integer, List<String>> result = CollectingAndThenChallenge.groupByLengthReadOnly(List.of());
            assertFalse(canPut(result));
        }

        @Test
        void testOrderInsideTheGroupsIsKept() {
            Map<Integer, List<String>> result = CollectingAndThenChallenge.groupByLengthReadOnly(
                    List.of("zz", "a", "yy", "b", "xx"));
            assertEquals(List.of("zz", "yy", "xx"), result.get(2));
            assertEquals(List.of("a", "b"), result.get(1));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(Map.of(1, List.of("a", "a")),
                    CollectingAndThenChallenge.groupByLengthReadOnly(List.of("a", "a")));
        }

        @Test
        void testEmptyTextHasLengthZero() {
            assertEquals(Map.of(0, List.of(""), 1, List.of("a")),
                    CollectingAndThenChallenge.groupByLengthReadOnly(List.of("", "a")));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "bb"));
            CollectingAndThenChallenge.groupByLengthReadOnly(words);
            assertEquals(List.of("a", "bb"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 8: allDistinct
    // ==========================================================
    @Nested
    class AllDistinctTests {

        @Test
        void testNoRepeated() {
            assertTrue(CollectingAndThenChallenge.allDistinct(List.of("a", "b", "c")));
        }

        @Test
        void testRepeated() {
            assertFalse(CollectingAndThenChallenge.allDistinct(List.of("a", "b", "a")));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectingAndThenChallenge.allDistinct(List.of()));
        }

        @Test
        void testSingleWord() {
            assertTrue(CollectingAndThenChallenge.allDistinct(List.of("a")));
        }

        @Test
        void testTwoEqualWords() {
            assertFalse(CollectingAndThenChallenge.allDistinct(List.of("a", "a")));
        }

        @Test
        void testCaseMatters() {
            assertTrue(CollectingAndThenChallenge.allDistinct(List.of("a", "A")));
        }

        @Test
        void testRepeatedAtTheEnds() {
            assertFalse(CollectingAndThenChallenge.allDistinct(List.of("x", "y", "z", "x")));
        }

        @Test
        void testRepeatedEmptyTexts() {
            assertFalse(CollectingAndThenChallenge.allDistinct(List.of("", "")));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "a"));
            CollectingAndThenChallenge.allDistinct(words);
            assertEquals(List.of("a", "a"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 9: topThree
    // ==========================================================
    @Nested
    class TopThreeTests {

        @Test
        void testTopThree() {
            assertEquals(List.of(9, 7, 5), CollectingAndThenChallenge.topThree(List.of(5, 1, 9, 3, 7)));
        }

        @Test
        void testRepeatedNumbersCountEachTime() {
            assertEquals(List.of(4, 4, 4), CollectingAndThenChallenge.topThree(List.of(4, 4, 4, 1)));
        }

        @Test
        void testTwoNumbers() {
            assertEquals(List.of(8, 2), CollectingAndThenChallenge.topThree(List.of(2, 8)));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), CollectingAndThenChallenge.topThree(List.of()));
        }

        @Test
        void testExactlyThree() {
            assertEquals(List.of(3, 2, 1), CollectingAndThenChallenge.topThree(List.of(1, 3, 2)));
        }

        @Test
        void testOneNumber() {
            assertEquals(List.of(5), CollectingAndThenChallenge.topThree(List.of(5)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(-1, -2, -3), CollectingAndThenChallenge.topThree(List.of(-5, -3, -1, -2, -9)));
        }

        @Test
        void testOriginalListIsNotModified() {
            List<Integer> numbers = new ArrayList<>(List.of(3, 1, 2, 5));
            CollectingAndThenChallenge.topThree(numbers);
            assertEquals(List.of(3, 1, 2, 5), numbers);
        }

        @Test
        void testOriginalListCanBeUnmodifiable() {
            assertEquals(List.of(4, 3, 2), CollectingAndThenChallenge.topThree(List.of(1, 4, 2, 3)));
        }

        @Test
        void testBigNumbersAndSmallNumbers() {
            assertEquals(List.of(Integer.MAX_VALUE, 0, Integer.MIN_VALUE),
                    CollectingAndThenChallenge.topThree(List.of(0, Integer.MIN_VALUE, Integer.MAX_VALUE)));
        }

        @Test
        void testRepeatedGreatest() {
            assertEquals(List.of(9, 9, 5), CollectingAndThenChallenge.topThree(List.of(9, 5, 9, 1)));
        }
    }

    // ==========================================================
    // CHALLENGE 10: maxOrDefault
    // ==========================================================
    @Nested
    class MaxOrDefaultTests {

        @Test
        void testGreatest() {
            assertEquals(9, CollectingAndThenChallenge.maxOrDefault(List.of(3, 9, 4), 0));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-2, CollectingAndThenChallenge.maxOrDefault(List.of(-5, -2), 0));
        }

        @Test
        void testEmptyListGivesTheDefault() {
            assertEquals(42, CollectingAndThenChallenge.maxOrDefault(List.of(), 42));
        }

        @Test
        void testDefaultIsIgnoredWhenThereAreNumbers() {
            assertEquals(1, CollectingAndThenChallenge.maxOrDefault(List.of(1), 100));
        }

        @Test
        void testDefaultGreaterThanTheNumbersIsIgnored() {
            assertEquals(-7, CollectingAndThenChallenge.maxOrDefault(List.of(-7, -8), 5));
        }

        @Test
        void testNegativeDefault() {
            assertEquals(-1, CollectingAndThenChallenge.maxOrDefault(List.of(), -1));
        }

        @Test
        void testGreatestAtTheEnds() {
            assertEquals(8, CollectingAndThenChallenge.maxOrDefault(List.of(8, 1, 2), 0));
            assertEquals(8, CollectingAndThenChallenge.maxOrDefault(List.of(1, 2, 8), 0));
        }

        @Test
        void testRepeatedGreatest() {
            assertEquals(5, CollectingAndThenChallenge.maxOrDefault(List.of(5, 5, 1), 0));
        }

        @Test
        void testZeroIsNotMissing() {
            assertEquals(0, CollectingAndThenChallenge.maxOrDefault(List.of(0), 9));
        }
    }

    // ==========================================================
    // CHALLENGE 11: countLongWords
    // ==========================================================
    @Nested
    class CountLongWordsTests {

        @Test
        void testCounts() {
            assertEquals(2, CollectingAndThenChallenge.countLongWords(List.of("a", "abc", "ab", "abcd"), 3));
        }

        @Test
        void testNothingPasses() {
            assertEquals(0, CollectingAndThenChallenge.countLongWords(List.of("a", "b"), 2));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectingAndThenChallenge.countLongWords(List.of(), 1));
        }

        @Test
        void testExactLengthCounts() {
            assertEquals(1, CollectingAndThenChallenge.countLongWords(List.of("abc"), 3));
        }

        @Test
        void testEverythingPasses() {
            assertEquals(3, CollectingAndThenChallenge.countLongWords(List.of("a", "b", "c"), 0));
        }

        @Test
        void testRepeatedWordsCountEachTime() {
            assertEquals(3, CollectingAndThenChallenge.countLongWords(List.of("ab", "ab", "ab"), 2));
        }

        @Test
        void testOneLessThanTheMinimumDoesNotCount() {
            assertEquals(0, CollectingAndThenChallenge.countLongWords(List.of("ab"), 3));
        }

        @Test
        void testEmptyTextsWithMinimumZero() {
            assertEquals(2, CollectingAndThenChallenge.countLongWords(List.of("", ""), 0));
        }
    }

    // ==========================================================
    // CHALLENGE 12: firstAndLast
    // ==========================================================
    @Nested
    class FirstAndLastTests {

        @Test
        void testSeveralWords() {
            assertEquals("a-c", CollectingAndThenChallenge.firstAndLast(List.of("a", "b", "c")));
        }

        @Test
        void testOneWord() {
            assertEquals("x-x", CollectingAndThenChallenge.firstAndLast(List.of("x")));
        }

        @Test
        void testEmptyList() {
            assertEquals("", CollectingAndThenChallenge.firstAndLast(List.of()));
        }

        @Test
        void testTwoWords() {
            assertEquals("a-b", CollectingAndThenChallenge.firstAndLast(List.of("a", "b")));
        }

        @Test
        void testManyWords() {
            assertEquals("one-five", CollectingAndThenChallenge.firstAndLast(
                    List.of("one", "two", "three", "four", "five")));
        }

        @Test
        void testSameFirstAndLast() {
            assertEquals("a-a", CollectingAndThenChallenge.firstAndLast(List.of("a", "b", "a")));
        }

        @Test
        void testWordsAreNotChanged() {
            assertEquals("Ab -cD", CollectingAndThenChallenge.firstAndLast(List.of("Ab ", "mid", "cD")));
        }

        @Test
        void testOneEmptyWord() {
            assertEquals("-", CollectingAndThenChallenge.firstAndLast(List.of("")));
        }
    }

    // ==========================================================
    // CHALLENGE 13: doubledSum
    // ==========================================================
    @Nested
    class DoubledSumTests {

        @Test
        void testSum() {
            assertEquals(12, Stream.of(1, 2, 3).collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-6, Stream.of(-4, 1).collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testEmptyStream() {
            assertEquals(0, Stream.<Integer>empty().collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testSingleNumber() {
            assertEquals(14, Stream.of(7).collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testTheCollectorCanBeUsedTwice() {
            var collector = CollectingAndThenChallenge.doubledSum();
            Integer first = Stream.of(1, 1).collect(collector);
            Integer second = Stream.of(5).collect(collector);
            assertEquals(4, first);
            assertEquals(10, second);
        }

        @Test
        void testWorksWithAParallelStream() {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                numbers.add(i);
            }
            assertEquals(1_001_000, numbers.parallelStream().collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testWorksWithAList() {
            assertEquals(20, List.of(4, 6).stream().collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testNotJustDoublingTheLastNumber() {
            assertEquals(30, Stream.of(10, 3, 2).collect(CollectingAndThenChallenge.doubledSum()));
        }

        @Test
        void testZeros() {
            assertEquals(0, Stream.of(0, 0).collect(CollectingAndThenChallenge.doubledSum()));
        }
    }

    // ==========================================================
    // CHALLENGE 14: upperJoined
    // ==========================================================
    @Nested
    class UpperJoinedTests {

        @Test
        void testJoinsAndUppercases() {
            assertEquals("AB-CD", Stream.of("ab", "cd").collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testSingleText() {
            assertEquals("X", Stream.of("x").collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testEmptyStream() {
            assertEquals("", Stream.<String>empty().collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testMixedCase() {
            assertEquals("HELLO-WORLD", Stream.of("Hello", "wORLD").collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testSeveralTexts() {
            assertEquals("A-B-C-D", Stream.of("a", "b", "c", "d").collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testOrderIsKept() {
            assertEquals("Z-A", Stream.of("z", "a").collect(CollectingAndThenChallenge.upperJoined()));
        }

        @Test
        void testTheCollectorCanBeUsedTwice() {
            var collector = CollectingAndThenChallenge.upperJoined();
            String first = Stream.of("a", "b").collect(collector);
            String second = Stream.of("c").collect(collector);
            assertEquals("A-B", first);
            assertEquals("C", second);
        }

        @Test
        void testSpacesAreKept() {
            assertEquals("A B- C", Stream.of("a b", " c").collect(CollectingAndThenChallenge.upperJoined()));
        }
    }

    // ==========================================================
    // CHALLENGE 15: reversedList
    // ==========================================================
    @Nested
    class ReversedListTests {

        @Test
        void testReverses() {
            assertEquals(List.of(3, 2, 1), Stream.of(1, 2, 3).collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testSingleNumber() {
            assertEquals(List.of(7), Stream.of(7).collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testEmptyStream() {
            assertEquals(List.of(), Stream.<Integer>empty().collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testNotSorted() {
            assertEquals(List.of(2, 9, 1, 5), Stream.of(5, 1, 9, 2).collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testRepeatedNumbersAreKept() {
            assertEquals(List.of(2, 2, 1), Stream.of(1, 2, 2).collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testTheCollectorCanBeUsedTwice() {
            var collector = CollectingAndThenChallenge.reversedList();
            List<Integer> first = Stream.of(1, 2).collect(collector);
            List<Integer> second = Stream.of(3, 4).collect(collector);
            assertEquals(List.of(2, 1), first);
            assertEquals(List.of(4, 3), second);
        }

        @Test
        void testTwoNumbers() {
            assertEquals(List.of(2, 1), Stream.of(1, 2).collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testWorksWithAList() {
            assertEquals(List.of(6, 4), List.of(4, 6).stream().collect(CollectingAndThenChallenge.reversedList()));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(0, -1), Stream.of(-1, 0).collect(CollectingAndThenChallenge.reversedList()));
        }
    }
}