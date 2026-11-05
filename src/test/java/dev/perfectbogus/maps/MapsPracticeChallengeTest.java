package dev.perfectbogus.maps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MapsPracticeChallengeTest {

    private static <K, V> List<K> keysOf(Map<K, V> map) {
        return new ArrayList<>(map.keySet());
    }

    private static <K, V> List<V> valuesOf(Map<K, V> map) {
        return new ArrayList<>(map.values());
    }

    private static TreeMap<Integer, String> schedule() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(8, "wake");
        map.put(12, "lunch");
        map.put(18, "dinner");
        return map;
    }

    // ==========================================================
    // CHALLENGE 1: wordCounts
    // ==========================================================
    @Nested
    class WordCountsTests {

        @Test
        void testRepeatedWord() {
            assertEquals(Map.of("a", 2, "b", 1), MapsPracticeChallenge.wordCounts(List.of("a", "b", "a")));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of("x", 1), MapsPracticeChallenge.wordCounts(List.of("x")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(Map.of("a", 1, "A", 1), MapsPracticeChallenge.wordCounts(List.of("a", "A")));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.wordCounts(List.of()));
        }

        @Test
        void testAllEqual() {
            assertEquals(Map.of("z", 4), MapsPracticeChallenge.wordCounts(List.of("z", "z", "z", "z")));
        }

        @Test
        void testAllDifferent() {
            assertEquals(Map.of("a", 1, "b", 1, "c", 1), MapsPracticeChallenge.wordCounts(List.of("a", "b", "c")));
        }

        @Test
        void testEmptyWord() {
            assertEquals(Map.of("", 2, "a", 1), MapsPracticeChallenge.wordCounts(List.of("", "a", "")));
        }

        @Test
        void testManyWords() {
            Map<String, Integer> r = MapsPracticeChallenge.wordCounts(
                    List.of("to", "be", "or", "not", "to", "be"));
            assertEquals(Map.of("to", 2, "be", 2, "or", 1, "not", 1), r);
        }

        @Test
        void testSizeIsTheNumberOfDifferentWords() {
            assertEquals(2, MapsPracticeChallenge.wordCounts(List.of("a", "b", "a", "b", "a")).size());
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            assertEquals(Map.of("a", 1, "a ", 1), MapsPracticeChallenge.wordCounts(List.of("a", "a ")));
        }
    }

    // ==========================================================
    // CHALLENGE 2: invert
    // ==========================================================
    @Nested
    class InvertTests {

        @Test
        void testTwoEntries() {
            assertEquals(Map.of(1, "a", 2, "b"), MapsPracticeChallenge.invert(Map.of("a", 1, "b", 2)));
        }

        @Test
        void testSingleEntry() {
            assertEquals(Map.of(10, "x"), MapsPracticeChallenge.invert(Map.of("x", 10)));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.invert(Map.of()));
        }

        @Test
        void testNegativeAndZeroValues() {
            assertEquals(Map.of(-1, "a", 0, "b"), MapsPracticeChallenge.invert(Map.of("a", -1, "b", 0)));
        }

        @Test
        void testManyEntries() {
            Map<Integer, String> r = MapsPracticeChallenge.invert(Map.of("one", 1, "two", 2, "three", 3));
            assertEquals(Map.of(1, "one", 2, "two", 3, "three"), r);
        }

        @Test
        void testOriginalIsNotModified() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1, "b", 2));
            MapsPracticeChallenge.invert(original);
            assertEquals(Map.of("a", 1, "b", 2), original);
        }

        @Test
        void testSizeIsKept() {
            assertEquals(3, MapsPracticeChallenge.invert(Map.of("a", 1, "b", 2, "c", 3)).size());
        }

        @Test
        void testEmptyKey() {
            assertEquals(Map.of(5, ""), MapsPracticeChallenge.invert(Map.of("", 5)));
        }

        @Test
        void testLargeValues() {
            assertEquals(Map.of(Integer.MAX_VALUE, "max", Integer.MIN_VALUE, "min"),
                    MapsPracticeChallenge.invert(Map.of("max", Integer.MAX_VALUE, "min", Integer.MIN_VALUE)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: mergeBalances
    // ==========================================================
    @Nested
    class MergeBalancesTests {

        @Test
        void testCommonKeyIsAdded() {
            assertEquals(Map.of("a", 1, "b", 12, "c", 5),
                    MapsPracticeChallenge.mergeBalances(Map.of("a", 1, "b", 2), Map.of("b", 10, "c", 5)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(Map.of("a", 1), MapsPracticeChallenge.mergeBalances(Map.of("a", 1), Map.of()));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(Map.of("a", 1), MapsPracticeChallenge.mergeBalances(Map.of(), Map.of("a", 1)));
        }

        @Test
        void testBothEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.mergeBalances(Map.of(), Map.of()));
        }

        @Test
        void testSameKeysOnly() {
            assertEquals(Map.of("a", 3, "b", 7),
                    MapsPracticeChallenge.mergeBalances(Map.of("a", 1, "b", 3), Map.of("a", 2, "b", 4)));
        }

        @Test
        void testNoCommonKeys() {
            assertEquals(Map.of("a", 1, "b", 2),
                    MapsPracticeChallenge.mergeBalances(Map.of("a", 1), Map.of("b", 2)));
        }

        @Test
        void testNegativeValues() {
            assertEquals(Map.of("a", -3, "b", 0),
                    MapsPracticeChallenge.mergeBalances(Map.of("a", -5, "b", 5), Map.of("a", 2, "b", -5)));
        }

        @Test
        void testNeitherMapIsModified() {
            Map<String, Integer> first = new HashMap<>(Map.of("a", 1, "b", 2));
            Map<String, Integer> second = new HashMap<>(Map.of("b", 10, "c", 5));
            MapsPracticeChallenge.mergeBalances(first, second);
            assertEquals(Map.of("a", 1, "b", 2), first);
            assertEquals(Map.of("b", 10, "c", 5), second);
        }

        @Test
        void testResultCanBeChanged() {
            HashMap<String, Integer> r = MapsPracticeChallenge.mergeBalances(Map.of("a", 1), Map.of("b", 2));
            r.put("c", 3);
            assertEquals(3, r.size());
        }

        @Test
        void testSizeIsTheNumberOfDifferentKeys() {
            assertEquals(3, MapsPracticeChallenge.mergeBalances(Map.of("a", 1, "b", 2), Map.of("b", 3, "c", 4)).size());
        }

        @Test
        void testZeroValuesAreKept() {
            assertEquals(Map.of("a", 0, "b", 0),
                    MapsPracticeChallenge.mergeBalances(Map.of("a", 0), Map.of("b", 0)));
        }
    }

    // ==========================================================
    // CHALLENGE 4: twoSum
    // ==========================================================
    @Nested
    class TwoSumTests {

        @Test
        void testFirstTwo() {
            assertArrayEquals(new int[]{0, 1}, MapsPracticeChallenge.twoSum(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        void testSamePositionCannotBeUsedTwice() {
            assertArrayEquals(new int[]{1, 2}, MapsPracticeChallenge.twoSum(new int[]{3, 2, 4}, 6));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new int[]{0, 1}, MapsPracticeChallenge.twoSum(new int[]{3, 3}, 6));
        }

        @Test
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{0, 2}, MapsPracticeChallenge.twoSum(new int[]{-1, -2, 5}, 4));
        }

        @Test
        void testLastTwo() {
            assertArrayEquals(new int[]{2, 3}, MapsPracticeChallenge.twoSum(new int[]{1, 2, 3, 4}, 7));
        }

        @Test
        void testSmallerPositionFirst() {
            assertArrayEquals(new int[]{0, 3}, MapsPracticeChallenge.twoSum(new int[]{8, 1, 2, 4}, 12));
        }

        @Test
        void testZeros() {
            assertArrayEquals(new int[]{1, 3}, MapsPracticeChallenge.twoSum(new int[]{5, 0, 7, 0}, 0));
        }

        @Test
        void testTargetZeroWithOppositeNumbers() {
            assertArrayEquals(new int[]{0, 2}, MapsPracticeChallenge.twoSum(new int[]{4, 9, -4}, 0));
        }

        @Test
        void testRepeatedNumberInTheMiddle() {
            assertArrayEquals(new int[]{2, 4}, MapsPracticeChallenge.twoSum(new int[]{1, 6, 5, 7, 5}, 10));
        }

        @Test
        void testOnlyTwoNumbers() {
            assertArrayEquals(new int[]{0, 1}, MapsPracticeChallenge.twoSum(new int[]{10, 20}, 30));
        }

        @Test
        void testHalfOfTheTargetAppearsOnceAndOnlyOnce() {
            assertArrayEquals(new int[]{1, 3}, MapsPracticeChallenge.twoSum(new int[]{5, 2, 9, 7}, 9));
        }

        @Test
        void testLargeNumbers() {
            assertArrayEquals(new int[]{1, 2},
                    MapsPracticeChallenge.twoSum(new int[]{1000000, 600000, 400000}, 1000000));
        }

        @Test
        void testResultHasTwoElements() {
            assertEquals(2, MapsPracticeChallenge.twoSum(new int[]{1, 2}, 3).length);
        }

        @Test
        void testArrayIsNotModified() {
            int[] numbers = {2, 7, 11, 15};
            MapsPracticeChallenge.twoSum(numbers, 9);
            assertArrayEquals(new int[]{2, 7, 11, 15}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 5: valuesFor
    // ==========================================================
    @Nested
    class ValuesForTests {

        @Test
        void testMixed() {
            assertEquals(List.of(7, 0, 5, 7),
                    MapsPracticeChallenge.valuesFor(Map.of("a", 5, "b", 7), List.of("b", "x", "a", "b")));
        }

        @Test
        void testNoKeys() {
            assertEquals(List.of(), MapsPracticeChallenge.valuesFor(Map.of("a", 5), List.of()));
        }

        @Test
        void testEmptyMap() {
            assertEquals(List.of(0), MapsPracticeChallenge.valuesFor(Map.of(), List.of("a")));
        }

        @Test
        void testAllMissing() {
            assertEquals(List.of(0, 0, 0), MapsPracticeChallenge.valuesFor(Map.of("a", 1), List.of("x", "y", "z")));
        }

        @Test
        void testOrderOfTheKeysIsKept() {
            assertEquals(List.of(3, 1, 2),
                    MapsPracticeChallenge.valuesFor(Map.of("a", 1, "b", 2, "c", 3), List.of("c", "a", "b")));
        }

        @Test
        void testNegativeAndZeroValues() {
            assertEquals(List.of(-4, 0), MapsPracticeChallenge.valuesFor(Map.of("a", -4, "b", 0), List.of("a", "b")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of(1, 0), MapsPracticeChallenge.valuesFor(Map.of("a", 1), List.of("a", "A")));
        }

        @Test
        void testSameKeyManyTimes() {
            assertEquals(List.of(9, 9, 9), MapsPracticeChallenge.valuesFor(Map.of("k", 9), List.of("k", "k", "k")));
        }

        @Test
        void testMapIsNotModified() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1));
            MapsPracticeChallenge.valuesFor(map, List.of("a", "x"));
            assertEquals(Map.of("a", 1), map);
        }

        @Test
        void testMissingKeyIsNotAdded() {
            Map<String, Integer> map = new HashMap<>();
            MapsPracticeChallenge.valuesFor(map, List.of("x"));
            assertTrue(map.isEmpty());
        }

        @Test
        void testLengthOfTheResult() {
            assertEquals(4, MapsPracticeChallenge.valuesFor(Map.of("a", 1), List.of("a", "b", "c", "d")).size());
        }
    }

    // ==========================================================
    // CHALLENGE 6: firstIndexOfEachWord
    // ==========================================================
    @Nested
    class FirstIndexOfEachWordTests {

        @Test
        void testMixed() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(
                    List.of("b", "a", "b", "c"));
            assertEquals(Map.of("b", 0, "a", 1, "c", 3), r);
            assertEquals(List.of("b", "a", "c"), keysOf(r));
        }

        @Test
        void testAllEqual() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(List.of("x", "x", "x"));
            assertEquals(Map.of("x", 0), r);
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.firstIndexOfEachWord(List.of()));
        }

        @Test
        void testFirstIndexIsKeptNotTheLast() {
            assertEquals(Map.of("a", 0), MapsPracticeChallenge.firstIndexOfEachWord(List.of("a", "a")));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(List.of("z", "m", "a"));
            assertEquals(List.of("z", "m", "a"), keysOf(r));
            assertEquals(List.of(0, 1, 2), valuesOf(r));
        }

        @Test
        void testRepeatedLaterDoesNotMoveTheEntry() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(
                    List.of("a", "b", "c", "a", "b"));
            assertEquals(List.of("a", "b", "c"), keysOf(r));
            assertEquals(List.of(0, 1, 2), valuesOf(r));
        }

        @Test
        void testCaseMatters() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(List.of("a", "A", "a"));
            assertEquals(List.of("a", "A"), keysOf(r));
            assertEquals(List.of(0, 1), valuesOf(r));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of("hello", 0), MapsPracticeChallenge.firstIndexOfEachWord(List.of("hello")));
        }

        @Test
        void testIndexOfALateWord() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.firstIndexOfEachWord(
                    List.of("a", "a", "a", "a", "b"));
            assertEquals(4, r.get("b"));
        }

        @Test
        void testSizeIsTheNumberOfDifferentWords() {
            assertEquals(3, MapsPracticeChallenge.firstIndexOfEachWord(List.of("a", "b", "c", "a", "b", "c")).size());
        }

        @Test
        void testValuesAreIncreasing() {
            List<Integer> values = valuesOf(MapsPracticeChallenge.firstIndexOfEachWord(
                    List.of("q", "w", "q", "e", "w", "r")));
            assertEquals(List.of(0, 1, 3, 5), values);
        }
    }

    // ==========================================================
    // CHALLENGE 7: countCharsInOrder
    // ==========================================================
    @Nested
    class CountCharsInOrderTests {

        @Test
        void testBanana() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("banana");
            assertEquals(Map.of('b', 1, 'a', 3, 'n', 2), r);
            assertEquals(List.of('b', 'a', 'n'), keysOf(r));
        }

        @Test
        void testCaseMatters() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("aAa");
            assertEquals(Map.of('a', 2, 'A', 1), r);
            assertEquals(List.of('a', 'A'), keysOf(r));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.countCharsInOrder(""));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("zyx");
            assertEquals(List.of('z', 'y', 'x'), keysOf(r));
        }

        @Test
        void testSpacesCount() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("a b a");
            assertEquals(Map.of('a', 2, ' ', 2, 'b', 1), r);
            assertEquals(List.of('a', ' ', 'b'), keysOf(r));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(Map.of('x', 1), MapsPracticeChallenge.countCharsInOrder("x"));
        }

        @Test
        void testAllEqual() {
            assertEquals(Map.of('a', 5), MapsPracticeChallenge.countCharsInOrder("aaaaa"));
        }

        @Test
        void testDigitsAndSymbols() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("1-1-2");
            assertEquals(Map.of('1', 2, '-', 2, '2', 1), r);
            assertEquals(List.of('1', '-', '2'), keysOf(r));
        }

        @Test
        void testValuesFollowTheOrderOfTheKeys() {
            LinkedHashMap<Character, Integer> r = MapsPracticeChallenge.countCharsInOrder("mississippi");
            assertEquals(List.of('m', 'i', 's', 'p'), keysOf(r));
            assertEquals(List.of(1, 4, 4, 2), valuesOf(r));
        }

        @Test
        void testTheTotalIsTheLengthOfTheText() {
            int total = MapsPracticeChallenge.countCharsInOrder("hello world").values().stream()
                    .mapToInt(Integer::intValue).sum();
            assertEquals(11, total);
        }

        @Test
        void testRepeatedLaterDoesNotMoveTheEntry() {
            assertEquals(List.of('a', 'b'), keysOf(MapsPracticeChallenge.countCharsInOrder("abbbaaab")));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortByValue
    // ==========================================================
    @Nested
    class SortByValueTests {

        @Test
        void testThreeEntries() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("a", 3, "b", 1, "c", 2));
            assertEquals(List.of("b", "c", "a"), keysOf(r));
            assertEquals(List.of(1, 2, 3), valuesOf(r));
        }

        @Test
        void testTiesGoByKey() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("z", 1, "y", 1, "x", 1));
            assertEquals(List.of("x", "y", "z"), keysOf(r));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.sortByValue(Map.of()));
        }

        @Test
        void testSingleEntry() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("a", 5));
            assertEquals(Map.of("a", 5), r);
        }

        @Test
        void testNegativeValues() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("a", 0, "b", -5, "c", 5));
            assertEquals(List.of("b", "a", "c"), keysOf(r));
        }

        @Test
        void testValueMattersMoreThanKey() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("a", 9, "z", 1));
            assertEquals(List.of("z", "a"), keysOf(r));
        }

        @Test
        void testTiesInTheMiddle() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(
                    Map.of("d", 2, "a", 3, "c", 2, "b", 1));
            assertEquals(List.of("b", "c", "d", "a"), keysOf(r));
            assertEquals(List.of(1, 2, 2, 3), valuesOf(r));
        }

        @Test
        void testUppercaseKeyComesBeforeLowercase() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(Map.of("a", 1, "B", 1));
            assertEquals(List.of("B", "a"), keysOf(r));
        }

        @Test
        void testOriginalIsNotModified() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 3, "b", 1));
            MapsPracticeChallenge.sortByValue(original);
            assertEquals(Map.of("a", 3, "b", 1), original);
        }

        @Test
        void testSameEntries() {
            Map<String, Integer> original = Map.of("a", 3, "b", 1, "c", 2);
            assertEquals(original, MapsPracticeChallenge.sortByValue(original));
        }

        @Test
        void testLargeAndSmallValues() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(
                    Map.of("max", Integer.MAX_VALUE, "min", Integer.MIN_VALUE, "zero", 0));
            assertEquals(List.of("min", "zero", "max"), keysOf(r));
        }

        @Test
        void testManyEntries() {
            Map<String, Integer> input = new LinkedHashMap<>();
            for (int i = 10; i >= 1; i--) {
                input.put("k" + (char) ('a' + i), i);
            }
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.sortByValue(input);
            assertEquals(10, r.size());
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), valuesOf(r));
        }

        @Test
        void testExtremeValuesInBothInsertionOrders() {
            Map<String, Integer> first = new LinkedHashMap<>();
            first.put("max", Integer.MAX_VALUE);
            first.put("min", Integer.MIN_VALUE);
            assertEquals(List.of("min", "max"), keysOf(MapsPracticeChallenge.sortByValue(first)));
            Map<String, Integer> second = new LinkedHashMap<>();
            second.put("min", Integer.MIN_VALUE);
            second.put("max", Integer.MAX_VALUE);
            assertEquals(List.of("min", "max"), keysOf(MapsPracticeChallenge.sortByValue(second)));
        }

        @Test
        void testInsertionOrderOfTheInputDoesNotMatter() {
            Map<String, Integer> input = new LinkedHashMap<>();
            input.put("c", 1);
            input.put("b", 1);
            input.put("a", 1);
            assertEquals(List.of("a", "b", "c"), keysOf(MapsPracticeChallenge.sortByValue(input)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: orderByLastAppearance
    // ==========================================================
    @Nested
    class OrderByLastAppearanceTests {

        @Test
        void testMixed() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("a", "b", "a", "c"));
            assertEquals(List.of("b", "a", "c"), keysOf(r));
            assertEquals(List.of(1, 2, 1), valuesOf(r));
        }

        @Test
        void testNoRepeats() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(List.of("a", "b"));
            assertEquals(List.of("a", "b"), keysOf(r));
        }

        @Test
        void testAlternating() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("b", "a", "b", "a"));
            assertEquals(List.of("b", "a"), keysOf(r));
            assertEquals(List.of(2, 2), valuesOf(r));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.orderByLastAppearance(List.of()));
        }

        @Test
        void testAllEqual() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(List.of("x", "x", "x"));
            assertEquals(Map.of("x", 3), r);
        }

        @Test
        void testRepeatedAtTheEnd() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("a", "b", "c", "a"));
            assertEquals(List.of("b", "c", "a"), keysOf(r));
            assertEquals(List.of(1, 1, 2), valuesOf(r));
        }

        @Test
        void testRepeatedWordMovesToTheEnd() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("a", "a", "b"));
            assertEquals(List.of("a", "b"), keysOf(r));
            assertEquals(List.of(2, 1), valuesOf(r));
        }

        @Test
        void testCountsAreCorrect() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("a", "b", "a", "b", "a", "c"));
            assertEquals(Map.of("a", 3, "b", 2, "c", 1), r);
            assertEquals(List.of("b", "a", "c"), keysOf(r));
        }

        @Test
        void testCaseMatters() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("a", "A", "a"));
            assertEquals(List.of("A", "a"), keysOf(r));
            assertEquals(List.of(1, 2), valuesOf(r));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of("a", 1), MapsPracticeChallenge.orderByLastAppearance(List.of("a")));
        }

        @Test
        void testFourWords() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.orderByLastAppearance(
                    List.of("d", "c", "b", "a", "d", "c"));
            assertEquals(List.of("b", "a", "d", "c"), keysOf(r));
            assertEquals(List.of(1, 1, 2, 2), valuesOf(r));
        }
    }

    // ==========================================================
    // CHALLENGE 10: zip
    // ==========================================================
    @Nested
    class ZipTests {

        @Test
        void testSameSize() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.zip(List.of("a", "b", "c"), List.of(1, 2, 3));
            assertEquals(Map.of("a", 1, "b", 2, "c", 3), r);
            assertEquals(List.of("a", "b", "c"), keysOf(r));
        }

        @Test
        void testRepeatedKey() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.zip(List.of("a", "b", "a"), List.of(1, 2, 3));
            assertEquals(Map.of("a", 3, "b", 2), r);
            assertEquals(List.of("a", "b"), keysOf(r));
        }

        @Test
        void testMoreKeysThanValues() {
            assertEquals(Map.of("a", 1, "b", 2), MapsPracticeChallenge.zip(List.of("a", "b", "c"), List.of(1, 2)));
        }

        @Test
        void testMoreValuesThanKeys() {
            assertEquals(Map.of("a", 1), MapsPracticeChallenge.zip(List.of("a"), List.of(1, 2, 3)));
        }

        @Test
        void testEmptyKeys() {
            assertEquals(Map.of(), MapsPracticeChallenge.zip(List.of(), List.of(1)));
        }

        @Test
        void testEmptyValues() {
            assertEquals(Map.of(), MapsPracticeChallenge.zip(List.of("a"), List.of()));
        }

        @Test
        void testBothEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.zip(List.of(), List.of()));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.zip(List.of("z", "m", "a"), List.of(1, 2, 3));
            assertEquals(List.of("z", "m", "a"), keysOf(r));
            assertEquals(List.of(1, 2, 3), valuesOf(r));
        }

        @Test
        void testRepeatedKeyKeepsTheFirstPosition() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.zip(
                    List.of("a", "b", "c", "a"), List.of(1, 2, 3, 4));
            assertEquals(List.of("a", "b", "c"), keysOf(r));
            assertEquals(List.of(4, 2, 3), valuesOf(r));
        }

        @Test
        void testKeyBeyondTheValuesIsLeftOut() {
            LinkedHashMap<String, Integer> r = MapsPracticeChallenge.zip(List.of("a", "b", "a"), List.of(1, 2));
            assertEquals(Map.of("a", 1, "b", 2), r);
        }

        @Test
        void testSingleEntry() {
            assertEquals(Map.of("k", 7), MapsPracticeChallenge.zip(List.of("k"), List.of(7)));
        }

        @Test
        void testAllKeysEqual() {
            assertEquals(Map.of("a", 3), MapsPracticeChallenge.zip(List.of("a", "a", "a"), List.of(1, 2, 3)));
        }
    }

    // ==========================================================
    // CHALLENGE 11: sortedWordCounts
    // ==========================================================
    @Nested
    class SortedWordCountsTests {

        @Test
        void testMixed() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("b", "a", "b"));
            assertEquals(Map.of("a", 1, "b", 2), r);
            assertEquals(List.of("a", "b"), keysOf(r));
        }

        @Test
        void testUppercaseFirst() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("b", "A"));
            assertEquals(List.of("A", "b"), keysOf(r));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.sortedWordCounts(List.of()));
        }

        @Test
        void testAlreadySortedInput() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("a", "b", "c"));
            assertEquals(List.of("a", "b", "c"), keysOf(r));
        }

        @Test
        void testReverseInput() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("c", "b", "a"));
            assertEquals(List.of("a", "b", "c"), keysOf(r));
        }

        @Test
        void testCaseMatters() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("a", "A", "a"));
            assertEquals(Map.of("a", 2, "A", 1), r);
            assertEquals(List.of("A", "a"), keysOf(r));
        }

        @Test
        void testFirstAndLastKey() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("m", "z", "a"));
            assertEquals("a", r.firstKey());
            assertEquals("z", r.lastKey());
        }

        @Test
        void testPrefixWordsComeFirst() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("abc", "ab", "b"));
            assertEquals(List.of("ab", "abc", "b"), keysOf(r));
        }

        @Test
        void testCountsFollowTheKeys() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(
                    List.of("pear", "apple", "pear", "fig", "pear"));
            assertEquals(List.of("apple", "fig", "pear"), keysOf(r));
            assertEquals(List.of(1, 1, 3), valuesOf(r));
        }

        @Test
        void testEmptyWordIsFirst() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.sortedWordCounts(List.of("a", ""));
            assertEquals("", r.firstKey());
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of("x", 1), MapsPracticeChallenge.sortedWordCounts(List.of("x")));
        }
    }

    // ==========================================================
    // CHALLENGE 12: latestBefore
    // ==========================================================
    @Nested
    class LatestBeforeTests {

        @Test
        void testBetweenTwoKeys() {
            assertEquals("lunch", MapsPracticeChallenge.latestBefore(schedule(), 13));
        }

        @Test
        void testExactKey() {
            assertEquals("lunch", MapsPracticeChallenge.latestBefore(schedule(), 12));
        }

        @Test
        void testBeforeTheFirstKey() {
            assertEquals("none", MapsPracticeChallenge.latestBefore(schedule(), 7));
        }

        @Test
        void testAfterTheLastKey() {
            assertEquals("dinner", MapsPracticeChallenge.latestBefore(schedule(), 23));
        }

        @Test
        void testExactFirstKey() {
            assertEquals("wake", MapsPracticeChallenge.latestBefore(schedule(), 8));
        }

        @Test
        void testExactLastKey() {
            assertEquals("dinner", MapsPracticeChallenge.latestBefore(schedule(), 18));
        }

        @Test
        void testJustBeforeAKey() {
            assertEquals("wake", MapsPracticeChallenge.latestBefore(schedule(), 11));
            assertEquals("lunch", MapsPracticeChallenge.latestBefore(schedule(), 17));
        }

        @Test
        void testEmptySchedule() {
            assertEquals("none", MapsPracticeChallenge.latestBefore(new TreeMap<>(), 10));
        }

        @Test
        void testNegativeHour() {
            assertEquals("none", MapsPracticeChallenge.latestBefore(schedule(), -5));
        }

        @Test
        void testSingleEntry() {
            TreeMap<Integer, String> single = new TreeMap<>(Map.of(10, "work"));
            assertEquals("none", MapsPracticeChallenge.latestBefore(single, 9));
            assertEquals("work", MapsPracticeChallenge.latestBefore(single, 10));
            assertEquals("work", MapsPracticeChallenge.latestBefore(single, 11));
        }

        @Test
        void testMapIsNotModified() {
            TreeMap<Integer, String> map = schedule();
            MapsPracticeChallenge.latestBefore(map, 13);
            assertEquals(3, map.size());
            assertEquals("wake", map.get(8));
        }

        @Test
        void testNoneIsNotConfusedWithAnActivity() {
            assertNotEquals("none", MapsPracticeChallenge.latestBefore(schedule(), 8));
        }
    }

    // ==========================================================
    // CHALLENGE 13: countInRange
    // ==========================================================
    @Nested
    class CountInRangeTests {

        private TreeMap<Integer, String> sample() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(1, "a");
            map.put(5, "b");
            map.put(9, "c");
            map.put(12, "d");
            return map;
        }

        @Test
        void testIncludesBothEnds() {
            assertEquals(3, MapsPracticeChallenge.countInRange(sample(), 5, 12));
        }

        @Test
        void testNothingInRange() {
            assertEquals(0, MapsPracticeChallenge.countInRange(sample(), 2, 4));
        }

        @Test
        void testSingleKeyRange() {
            assertEquals(1, MapsPracticeChallenge.countInRange(sample(), 9, 9));
        }

        @Test
        void testFromGreaterThanTo() {
            assertEquals(0, MapsPracticeChallenge.countInRange(sample(), 12, 5));
        }

        @Test
        void testWholeMap() {
            assertEquals(4, MapsPracticeChallenge.countInRange(sample(), 1, 12));
            assertEquals(4, MapsPracticeChallenge.countInRange(sample(), -100, 100));
        }

        @Test
        void testOnlyTheFromKeyIsIncluded() {
            assertEquals(1, MapsPracticeChallenge.countInRange(sample(), 12, 20));
        }

        @Test
        void testOnlyTheToKeyIsIncluded() {
            assertEquals(1, MapsPracticeChallenge.countInRange(sample(), -5, 1));
        }

        @Test
        void testEmptyMap() {
            assertEquals(0, MapsPracticeChallenge.countInRange(new TreeMap<>(), 0, 10));
        }

        @Test
        void testRangeWithoutKeysOfTheEnds() {
            assertEquals(2, MapsPracticeChallenge.countInRange(sample(), 4, 10));
        }

        @Test
        void testFromEqualsToWithoutKey() {
            assertEquals(0, MapsPracticeChallenge.countInRange(sample(), 6, 6));
        }

        @Test
        void testFromGreaterThanToInsideTheKeys() {
            assertEquals(0, MapsPracticeChallenge.countInRange(sample(), 9, 5));
        }

        @Test
        void testRepeatedValuesAreCountedAsDifferentEntries() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(1, "same");
            map.put(2, "same");
            map.put(3, "same");
            assertEquals(3, MapsPracticeChallenge.countInRange(map, 1, 3));
        }

        @Test
        void testMapIsNotModified() {
            TreeMap<Integer, String> map = sample();
            MapsPracticeChallenge.countInRange(map, 5, 9);
            assertEquals(4, map.size());
        }

        @Test
        void testExcludingTheEnds() {
            assertEquals(1, MapsPracticeChallenge.countInRange(sample(), 6, 11));
            assertEquals(2, MapsPracticeChallenge.countInRange(sample(), 5, 9));
        }
    }

    // ==========================================================
    // CHALLENGE 14: caseInsensitiveCounts
    // ==========================================================
    @Nested
    class CaseInsensitiveCountsTests {

        @Test
        void testMixed() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("b", "A", "a"));
            assertEquals(List.of("A", "b"), keysOf(r));
            assertEquals(List.of(2, 1), valuesOf(r));
        }

        @Test
        void testSameWord() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("Hello", "hello"));
            assertEquals(List.of("Hello"), keysOf(r));
            assertEquals(List.of(2), valuesOf(r));
        }

        @Test
        void testOrderIgnoresCase() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("B", "a", "C"));
            assertEquals(List.of("a", "B", "C"), keysOf(r));
        }

        @Test
        void testEmpty() {
            assertEquals(Map.of(), MapsPracticeChallenge.caseInsensitiveCounts(List.of()));
        }

        @Test
        void testKeyKeepsTheFirstSpelling() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("java", "JAVA", "Java"));
            assertEquals(List.of("java"), keysOf(r));
            assertEquals(List.of(3), valuesOf(r));
        }

        @Test
        void testLookupIgnoresCase() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("Apple", "apple"));
            assertEquals(2, r.get("APPLE"));
            assertEquals(2, r.get("apple"));
        }

        @Test
        void testDifferentWordsStayDifferent() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("a", "ab", "A"));
            assertEquals(List.of("a", "ab"), keysOf(r));
            assertEquals(List.of(2, 1), valuesOf(r));
        }

        @Test
        void testSingleWord() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("X"));
            assertEquals(Map.of("X", 1), r);
        }

        @Test
        void testManyWords() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(
                    List.of("Pear", "apple", "PEAR", "Fig", "APPLE", "pear"));
            assertEquals(List.of("apple", "Fig", "Pear"), keysOf(r));
            assertEquals(List.of(2, 1, 3), valuesOf(r));
        }

        @Test
        void testFirstAndLastKey() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("b", "Z", "a"));
            assertEquals("a", r.firstKey());
            assertEquals("Z", r.lastKey());
        }

        @Test
        void testLaterSpellingDoesNotReplaceTheKey() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("a", "A", "A"));
            assertEquals(List.of("a"), keysOf(r));
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            TreeMap<String, Integer> r = MapsPracticeChallenge.caseInsensitiveCounts(List.of("a", "A "));
            assertEquals(2, r.size());
        }
    }

    // ==========================================================
    // CHALLENGE 15: removeSmallest
    // ==========================================================
    @Nested
    class RemoveSmallestTests {

        private TreeMap<Integer, String> sample() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(3, "c");
            map.put(1, "a");
            map.put(2, "b");
            return map;
        }

        @Test
        void testTwoRemoved() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of("a", "b"), MapsPracticeChallenge.removeSmallest(map, 2));
            assertEquals(Map.of(3, "c"), map);
        }

        @Test
        void testZeroChangesNothing() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of(), MapsPracticeChallenge.removeSmallest(map, 0));
            assertEquals(Map.of(1, "a", 2, "b", 3, "c"), map);
        }

        @Test
        void testMoreThanTheSize() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of("a", "b", "c"), MapsPracticeChallenge.removeSmallest(map, 5));
            assertTrue(map.isEmpty());
        }

        @Test
        void testExactlyTheSize() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of("a", "b", "c"), MapsPracticeChallenge.removeSmallest(map, 3));
            assertTrue(map.isEmpty());
        }

        @Test
        void testOne() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of("a"), MapsPracticeChallenge.removeSmallest(map, 1));
            assertEquals(Map.of(2, "b", 3, "c"), map);
        }

        @Test
        void testEmptyMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            assertEquals(List.of(), MapsPracticeChallenge.removeSmallest(map, 3));
            assertTrue(map.isEmpty());
        }

        @Test
        void testAscendingOrderOfKeys() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(30, "z");
            map.put(10, "x");
            map.put(20, "y");
            assertEquals(List.of("x", "y"), MapsPracticeChallenge.removeSmallest(map, 2));
        }

        @Test
        void testNegativeKeys() {
            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(0, "zero");
            map.put(-5, "minus");
            map.put(5, "plus");
            assertEquals(List.of("minus"), MapsPracticeChallenge.removeSmallest(map, 1));
            assertEquals(List.of(0, 5), keysOf(map));
        }

        @Test
        void testRemovedOnesAreGoneAndOthersStay() {
            TreeMap<Integer, String> map = sample();
            MapsPracticeChallenge.removeSmallest(map, 2);
            assertFalse(map.containsKey(1));
            assertFalse(map.containsKey(2));
            assertEquals("c", map.get(3));
        }

        @Test
        void testCalledTwice() {
            TreeMap<Integer, String> map = sample();
            assertEquals(List.of("a"), MapsPracticeChallenge.removeSmallest(map, 1));
            assertEquals(List.of("b"), MapsPracticeChallenge.removeSmallest(map, 1));
            assertEquals(List.of("c"), MapsPracticeChallenge.removeSmallest(map, 1));
            assertEquals(List.of(), MapsPracticeChallenge.removeSmallest(map, 1));
        }

        @Test
        void testSizeAfterwards() {
            TreeMap<Integer, String> map = sample();
            MapsPracticeChallenge.removeSmallest(map, 2);
            assertEquals(1, map.size());
        }

        @Test
        void testLargerMap() {
            TreeMap<Integer, String> map = new TreeMap<>();
            for (int i = 10; i >= 1; i--) {
                map.put(i, "v" + i);
            }
            assertEquals(List.of("v1", "v2", "v3", "v4"), MapsPracticeChallenge.removeSmallest(map, 4));
            assertEquals(6, map.size());
            assertEquals(5, map.firstKey());
        }
    }
}