package dev.perfectbogus.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalMapsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: groupBy
    // ==========================================================
    @Nested
    class GroupByTests {

        @Test
        void testGroupByLength() {
            Map<Integer, List<String>> result = FunctionalMapsChallenge.groupBy(
                    List.of("a", "bb", "c", "dd", "eee"), String::length);
            assertEquals(Map.of(1, List.of("a", "c"), 2, List.of("bb", "dd"), 3, List.of("eee")), result);
        }

        @Test
        void testGroupByRemainder() {
            Map<Integer, List<Integer>> result = FunctionalMapsChallenge.groupBy(List.of(1, 2, 3, 4, 5), n -> n % 2);
            assertEquals(Map.of(0, List.of(2, 4), 1, List.of(1, 3, 5)), result);
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), FunctionalMapsChallenge.groupBy(List.of(), Function.identity()));
        }

        @Test
        void testOrderInsideTheGroupsIsKept() {
            Map<Integer, List<String>> result = FunctionalMapsChallenge.groupBy(
                    List.of("zz", "a", "yy", "b", "xx"), String::length);
            assertEquals(List.of("zz", "yy", "xx"), result.get(2));
            assertEquals(List.of("a", "b"), result.get(1));
        }

        @Test
        void testSingleGroup() {
            Map<String, List<Integer>> result = FunctionalMapsChallenge.groupBy(List.of(1, 2, 3), n -> "all");
            assertEquals(Map.of("all", List.of(1, 2, 3)), result);
        }

        @Test
        void testEveryItemHasItsOwnGroup() {
            Map<String, List<String>> result = FunctionalMapsChallenge.groupBy(List.of("a", "b", "c"), s -> s);
            assertEquals(3, result.size());
            assertEquals(List.of("b"), result.get("b"));
        }

        @Test
        void testRepeatedItemsAreKept() {
            Map<Integer, List<String>> result = FunctionalMapsChallenge.groupBy(List.of("a", "a", "a"), String::length);
            assertEquals(Map.of(1, List.of("a", "a", "a")), result);
        }

        @Test
        void testListIsNotModified() {
            List<String> items = new ArrayList<>(List.of("a", "bb"));
            FunctionalMapsChallenge.groupBy(items, String::length);
            assertEquals(List.of("a", "bb"), items);
        }

        @Test
        void testWorksWithAnyType() {
            Map<Boolean, List<Double>> result = FunctionalMapsChallenge.groupBy(List.of(1.5, 2.5, 3.0), d -> d > 2);
            assertEquals(Map.of(false, List.of(1.5), true, List.of(2.5, 3.0)), result);
        }
    }

    // ==========================================================
    // CHALLENGE 2: countBy
    // ==========================================================
    @Nested
    class CountByTests {

        @Test
        void testCountByLength() {
            Map<Integer, Long> result = FunctionalMapsChallenge.countBy(
                    List.of("a", "bb", "c", "dd", "eee"), String::length);
            assertEquals(Map.of(1, 2L, 2, 2L, 3, 1L), result);
        }

        @Test
        void testCountByIdentity() {
            Map<String, Long> result = FunctionalMapsChallenge.countBy(List.of("x", "y", "x"), Function.identity());
            assertEquals(Map.of("x", 2L, "y", 1L), result);
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), FunctionalMapsChallenge.countBy(List.of(), Function.identity()));
        }

        @Test
        void testRepeatedItemsAreCountedEveryTime() {
            Map<String, Long> result = FunctionalMapsChallenge.countBy(List.of("a", "a", "a", "a"), s -> s);
            assertEquals(Map.of("a", 4L), result);
        }

        @Test
        void testSingleItem() {
            assertEquals(Map.of(5, 1L), FunctionalMapsChallenge.countBy(List.of("hello"), String::length));
        }

        @Test
        void testCountByParity() {
            Map<Boolean, Long> result = FunctionalMapsChallenge.countBy(List.of(1, 2, 3, 4, 5), n -> n % 2 == 0);
            assertEquals(Map.of(true, 2L, false, 3L), result);
        }

        @Test
        void testDifferentItemsWithTheSameKeyAreCounted() {
            Map<Character, Long> result = FunctionalMapsChallenge.countBy(
                    List.of("apple", "avocado", "banana"), w -> w.charAt(0));
            assertEquals(Map.of('a', 2L, 'b', 1L), result);
        }

        @Test
        void testTheValuesAreLongNumbers() {
            Map<String, Long> result = FunctionalMapsChallenge.countBy(List.of("a"), s -> s);
            assertEquals(1L, (long) result.get("a"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: toMapFirstWins
    // ==========================================================
    @Nested
    class ToMapFirstWinsTests {

        @Test
        void testFirstOneIsKept() {
            Map<Character, Integer> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of("apple", "avocado", "banana"), w -> w.charAt(0), String::length);
            assertEquals(Map.of('a', 5, 'b', 6), result);
        }

        @Test
        void testOrderOfTheListMatters() {
            Map<Character, Integer> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of("avocado", "apple"), w -> w.charAt(0), String::length);
            assertEquals(Map.of('a', 7), result);
        }

        @Test
        void testNoRepeatedKeys() {
            Map<String, Integer> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of("a", "bb", "ccc"), w -> w, String::length);
            assertEquals(Map.of("a", 1, "bb", 2, "ccc", 3), result);
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), FunctionalMapsChallenge.toMapFirstWins(
                    List.<String>of(), w -> w, String::length));
        }

        @Test
        void testRepeatedKeyDoesNotFail() {
            Map<Integer, String> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of("a", "b", "c"), w -> 1, w -> w);
            assertEquals(Map.of(1, "a"), result);
        }

        @Test
        void testThreeItemsWithTheSameKey() {
            Map<Integer, String> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of("x", "yy", "zz", "w"), String::length, w -> w);
            assertEquals(Map.of(1, "x", 2, "yy"), result);
        }

        @Test
        void testTheValueIsTheOneOfTheItemThatIsKept() {
            Map<Integer, Integer> result = FunctionalMapsChallenge.toMapFirstWins(
                    List.of(10, 11, 20, 21), n -> n / 10, n -> n);
            assertEquals(Map.of(1, 10, 2, 20), result);
        }

        @Test
        void testListIsNotModified() {
            List<String> items = new ArrayList<>(List.of("a", "b"));
            FunctionalMapsChallenge.toMapFirstWins(items, w -> 1, w -> w);
            assertEquals(List.of("a", "b"), items);
        }
    }

    // ==========================================================
    // CHALLENGE 4: mapValues
    // ==========================================================
    @Nested
    class MapValuesTests {

        @Test
        void testMultiply() {
            Map<String, Integer> result = FunctionalMapsChallenge.mapValues(Map.of("a", 1, "b", 2), v -> v * 10);
            assertEquals(Map.of("a", 10, "b", 20), result);
        }

        @Test
        void testChangesTheType() {
            Map<String, String> result = FunctionalMapsChallenge.mapValues(Map.of("a", 1), v -> "n" + v);
            assertEquals(Map.of("a", "n1"), result);
        }

        @Test
        void testEmptyMap() {
            assertEquals(Map.of(), FunctionalMapsChallenge.mapValues(Map.<String, Integer>of(), v -> v));
        }

        @Test
        void testKeysAreKept() {
            Map<String, Integer> result = FunctionalMapsChallenge.mapValues(
                    Map.of("x", 1, "y", 2, "z", 3), v -> 0);
            assertEquals(Map.of("x", 0, "y", 0, "z", 0), result);
        }

        @Test
        void testFunctionIsCalledOncePerValue() {
            int[] calls = {0};
            FunctionalMapsChallenge.mapValues(Map.of("a", 1, "b", 2, "c", 3), v -> {
                calls[0]++;
                return v;
            });
            assertEquals(3, calls[0]);
        }

        @Test
        void testOriginalMapIsNotModified() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1, "b", 2));
            FunctionalMapsChallenge.mapValues(original, v -> v + 100);
            assertEquals(Map.of("a", 1, "b", 2), original);
        }

        @Test
        void testResultIsANewMap() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1));
            Map<String, Integer> result = FunctionalMapsChallenge.mapValues(original, v -> v);
            assertNotSame(original, result);
            result.put("zzz", 9);
            assertEquals(1, original.size());
        }

        @Test
        void testTwoKeysWithTheSameValue() {
            Map<String, Integer> result = FunctionalMapsChallenge.mapValues(Map.of("a", 5, "b", 5), v -> v + 1);
            assertEquals(Map.of("a", 6, "b", 6), result);
        }

        @Test
        void testFunctionReceivesTheValueNotTheKey() {
            Map<String, Integer> result = FunctionalMapsChallenge.mapValues(Map.of("abc", 7), v -> v * v);
            assertEquals(Map.of("abc", 49), result);
        }
    }

    // ==========================================================
    // CHALLENGE 5: filterByValue
    // ==========================================================
    @Nested
    class FilterByValueTests {

        @Test
        void testKeepsTheValuesThatPass() {
            Map<String, Integer> result = FunctionalMapsChallenge.filterByValue(
                    Map.of("a", 1, "b", 5, "c", 8), v -> v > 3);
            assertEquals(Map.of("b", 5, "c", 8), result);
        }

        @Test
        void testNothingPasses() {
            assertEquals(Map.of(), FunctionalMapsChallenge.filterByValue(Map.of("a", 1), v -> v > 3));
        }

        @Test
        void testEverythingPasses() {
            Map<String, Integer> result = FunctionalMapsChallenge.filterByValue(Map.of("a", 1, "b", 2), v -> true);
            assertEquals(Map.of("a", 1, "b", 2), result);
        }

        @Test
        void testEmptyMap() {
            assertEquals(Map.of(), FunctionalMapsChallenge.filterByValue(Map.<String, Integer>of(), v -> true));
        }

        @Test
        void testTestIsCalledOncePerValue() {
            int[] calls = {0};
            FunctionalMapsChallenge.filterByValue(Map.of("a", 1, "b", 2, "c", 3), v -> {
                calls[0]++;
                return v > 1;
            });
            assertEquals(3, calls[0]);
        }

        @Test
        void testOriginalMapIsNotModified() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1, "b", 5));
            FunctionalMapsChallenge.filterByValue(original, v -> v > 3);
            assertEquals(Map.of("a", 1, "b", 5), original);
        }

        @Test
        void testTestUsesTheValueNotTheKey() {
            Map<Integer, String> result = FunctionalMapsChallenge.filterByValue(
                    Map.of(1, "keep", 2, "drop", 3, "keep"), v -> v.equals("keep"));
            assertEquals(Map.of(1, "keep", 3, "keep"), result);
        }

        @Test
        void testKeysAreKept() {
            Map<String, Integer> result = FunctionalMapsChallenge.filterByValue(Map.of("key", 4), v -> v == 4);
            assertEquals(Map.of("key", 4), result);
        }

        @Test
        void testResultIsANewMap() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1));
            Map<String, Integer> result = FunctionalMapsChallenge.filterByValue(original, v -> true);
            assertNotSame(original, result);
        }
    }

    // ==========================================================
    // CHALLENGE 6: splitByTest
    // ==========================================================
    @Nested
    class SplitByTestTests {

        @Test
        void testSplit() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(
                    List.of("a", "bb", "c", "dd"), w -> w.length() > 1);
            assertEquals(List.of("bb", "dd"), result.get(true));
            assertEquals(List.of("a", "c"), result.get(false));
        }

        @Test
        void testBothKeysAreAlwaysPresentWhenNothingPasses() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(List.of("a"), w -> w.length() > 1);
            assertEquals(List.of("a"), result.get(false));
            assertEquals(List.of(), result.get(true));
            assertEquals(2, result.size());
        }

        @Test
        void testBothKeysAreAlwaysPresentWhenEverythingPasses() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(List.of("a", "b"), w -> true);
            assertEquals(List.of("a", "b"), result.get(true));
            assertEquals(List.of(), result.get(false));
            assertEquals(2, result.size());
        }

        @Test
        void testEmptyList() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(List.of(), w -> true);
            assertEquals(Map.of(false, List.of(), true, List.of()), result);
        }

        @Test
        void testOrderIsKept() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(
                    List.of("z", "yy", "x", "ww", "v"), w -> w.length() == 2);
            assertEquals(List.of("yy", "ww"), result.get(true));
            assertEquals(List.of("z", "x", "v"), result.get(false));
        }

        @Test
        void testTestIsCalledOncePerWord() {
            int[] calls = {0};
            FunctionalMapsChallenge.splitByTest(List.of("a", "b", "c", "d"), w -> {
                calls[0]++;
                return w.equals("b");
            });
            assertEquals(4, calls[0]);
        }

        @Test
        void testRepeatedWordsAreKept() {
            Map<Boolean, List<String>> result = FunctionalMapsChallenge.splitByTest(
                    List.of("a", "a", "bb"), w -> w.equals("a"));
            assertEquals(List.of("a", "a"), result.get(true));
            assertEquals(List.of("bb"), result.get(false));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "bb"));
            FunctionalMapsChallenge.splitByTest(words, w -> w.length() > 1);
            assertEquals(List.of("a", "bb"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 7: totalByKey
    // ==========================================================
    @Nested
    class TotalByKeyTests {

        @Test
        void testSumsTheNumbers() {
            Map<String, Integer> result = FunctionalMapsChallenge.totalByKey(
                    List.of(Map.entry("a", 1), Map.entry("b", 5), Map.entry("a", 2)));
            assertEquals(Map.of("a", 3, "b", 5), result);
        }

        @Test
        void testSingleEntry() {
            assertEquals(Map.of("x", 4), FunctionalMapsChallenge.totalByKey(List.of(Map.entry("x", 4))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), FunctionalMapsChallenge.totalByKey(List.of()));
        }

        @Test
        void testNegativeNumbers() {
            Map<String, Integer> result = FunctionalMapsChallenge.totalByKey(
                    List.of(Map.entry("a", 5), Map.entry("a", -8)));
            assertEquals(Map.of("a", -3), result);
        }

        @Test
        void testManyEntriesForOneKey() {
            List<Map.Entry<String, Integer>> entries = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                entries.add(Map.entry("k", i));
            }
            assertEquals(Map.of("k", 5050), FunctionalMapsChallenge.totalByKey(entries));
        }

        @Test
        void testSeveralKeysInterleaved() {
            Map<String, Integer> result = FunctionalMapsChallenge.totalByKey(List.of(
                    Map.entry("a", 1), Map.entry("b", 10), Map.entry("a", 2), Map.entry("b", 20), Map.entry("c", 100)));
            assertEquals(Map.of("a", 3, "b", 30, "c", 100), result);
        }

        @Test
        void testZeroValues() {
            assertEquals(Map.of("a", 0), FunctionalMapsChallenge.totalByKey(
                    List.of(Map.entry("a", 0), Map.entry("a", 0))));
        }

        @Test
        void testKeysAreCaseSensitive() {
            Map<String, Integer> result = FunctionalMapsChallenge.totalByKey(
                    List.of(Map.entry("a", 1), Map.entry("A", 2)));
            assertEquals(Map.of("a", 1, "A", 2), result);
        }
    }

    // ==========================================================
    // CHALLENGE 8: averageByKey
    // ==========================================================
    @Nested
    class AverageByKeyTests {

        @Test
        void testAverages() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(
                    List.of(Map.entry("a", 1), Map.entry("b", 5), Map.entry("a", 2)));
            assertEquals(2, result.size());
            assertEquals(1.5, result.get("a"), 0.0001);
            assertEquals(5.0, result.get("b"), 0.0001);
        }

        @Test
        void testSingleEntry() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(List.of(Map.entry("x", 4)));
            assertEquals(4.0, result.get("x"), 0.0001);
        }

        @Test
        void testEmptyList() {
            assertEquals(0, FunctionalMapsChallenge.averageByKey(List.of()).size());
        }

        @Test
        void testDecimalsAreKept() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(
                    List.of(Map.entry("a", 1), Map.entry("a", 2), Map.entry("a", 2)));
            assertEquals(1.6666, result.get("a"), 0.001);
        }

        @Test
        void testNegativeNumbers() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(
                    List.of(Map.entry("a", -3), Map.entry("a", 2)));
            assertEquals(-0.5, result.get("a"), 0.0001);
        }

        @Test
        void testSeveralKeys() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(List.of(
                    Map.entry("a", 10), Map.entry("b", 1), Map.entry("a", 20), Map.entry("b", 2), Map.entry("c", 7)));
            assertEquals(15.0, result.get("a"), 0.0001);
            assertEquals(1.5, result.get("b"), 0.0001);
            assertEquals(7.0, result.get("c"), 0.0001);
        }

        @Test
        void testBigNumbersDoNotOverflow() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(List.of(
                    Map.entry("a", 2_000_000_000), Map.entry("a", 2_000_000_000)));
            assertEquals(2_000_000_000.0, result.get("a"), 0.0001);
        }

        @Test
        void testIntegerDivisionIsNotUsed() {
            Map<String, Double> result = FunctionalMapsChallenge.averageByKey(
                    List.of(Map.entry("a", 1), Map.entry("a", 2)));
            assertEquals(1.5, result.get("a"), 0.0001);
        }
    }

    // ==========================================================
    // CHALLENGE 9: mergeWith
    // ==========================================================
    @Nested
    class MergeWithTests {

        @Test
        void testSum() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("x", 1, "y", 2), Map.of("y", 10, "z", 3), (p, q) -> p + q);
            assertEquals(Map.of("x", 1, "y", 12, "z", 3), result);
        }

        @Test
        void testTheOrderOfTheArgumentsOfCombine() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("y", 2), Map.of("y", 10), (p, q) -> p - q);
            assertEquals(Map.of("y", -8), result);
        }

        @Test
        void testFirstMapEmpty() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of(), Map.of("z", 3), (p, q) -> p + q);
            assertEquals(Map.of("z", 3), result);
        }

        @Test
        void testSecondMapEmpty() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("z", 3), Map.of(), (p, q) -> p + q);
            assertEquals(Map.of("z", 3), result);
        }

        @Test
        void testBothEmpty() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.<String, Integer>of(), Map.<String, Integer>of(), (p, q) -> p + q);
            assertEquals(Map.of(), result);
        }

        @Test
        void testNoCommonKeys() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("a", 1), Map.of("b", 2), (p, q) -> 999);
            assertEquals(Map.of("a", 1, "b", 2), result);
        }

        @Test
        void testCombineIsCalledOnlyForCommonKeys() {
            int[] calls = {0};
            Map<String, Integer> a = Map.of("a", 1, "b", 2, "c", 3);
            Map<String, Integer> b = Map.of("b", 20, "c", 30, "d", 4);
            FunctionalMapsChallenge.mergeWith(a, b, (p, q) -> {
                calls[0]++;
                return p + q;
            });
            assertEquals(2, calls[0]);
        }

        @Test
        void testWithTexts() {
            Map<String, String> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("k", "left"), Map.of("k", "right"), (p, q) -> p + "+" + q);
            assertEquals(Map.of("k", "left+right"), result);
        }

        @Test
        void testOriginalMapsAreNotModified() {
            Map<String, Integer> a = new HashMap<>(Map.of("x", 1, "y", 2));
            Map<String, Integer> b = new HashMap<>(Map.of("y", 10, "z", 3));
            FunctionalMapsChallenge.mergeWith(a, b, (p, q) -> p + q);
            assertEquals(Map.of("x", 1, "y", 2), a);
            assertEquals(Map.of("y", 10, "z", 3), b);
        }

        @Test
        void testMaximumAsCombine() {
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(
                    Map.of("a", 5, "b", 1), Map.of("a", 3, "b", 9), Math::max);
            assertEquals(Map.of("a", 5, "b", 9), result);
        }

        @Test
        void testResultIsANewMap() {
            Map<String, Integer> a = new HashMap<>(Map.of("x", 1));
            Map<String, Integer> result = FunctionalMapsChallenge.mergeWith(a, Map.of(), (p, q) -> p);
            assertNotSame(a, result);
        }
    }

    // ==========================================================
    // CHALLENGE 10: maxPerGroup
    // ==========================================================
    @Nested
    class MaxPerGroupTests {

        @Test
        void testLongestPerInitial() {
            Map<Character, String> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of("apple", "avocado", "banana", "fig"), w -> w.charAt(0),
                    Comparator.comparingInt(String::length));
            assertEquals(Map.of('a', "avocado", 'b', "banana", 'f', "fig"), result);
        }

        @Test
        void testTieKeepsTheFirst() {
            Map<Character, String> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of("ab", "cd", "ax"), w -> w.charAt(0), Comparator.comparingInt(String::length));
            assertEquals(Map.of('a', "ab", 'c', "cd"), result);
        }

        @Test
        void testTieKeepsTheFirstEvenWhenTheOtherComesLater() {
            Map<Character, String> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of("zz", "za", "zb"), w -> w.charAt(0), Comparator.comparingInt(String::length));
            assertEquals(Map.of('z', "zz"), result);
        }

        @Test
        void testEmptyList() {
            Map<Character, String> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of(), w -> w.charAt(0), Comparator.comparingInt(String::length));
            assertEquals(Map.of(), result);
        }

        @Test
        void testSingleItemGroups() {
            Map<Integer, Integer> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of(1, 2, 3), n -> n, Comparator.naturalOrder());
            assertEquals(Map.of(1, 1, 2, 2, 3, 3), result);
        }

        @Test
        void testNumbersByParity() {
            Map<Integer, Integer> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of(3, 8, 5, 2, 9, 4), n -> n % 2, Comparator.naturalOrder());
            assertEquals(Map.of(0, 8, 1, 9), result);
        }

        @Test
        void testTheGreatestCanBeAtAnyPosition() {
            Map<Integer, Integer> first = FunctionalMapsChallenge.maxPerGroup(
                    List.of(9, 1, 5), n -> 0, Comparator.naturalOrder());
            Map<Integer, Integer> middle = FunctionalMapsChallenge.maxPerGroup(
                    List.of(1, 9, 5), n -> 0, Comparator.naturalOrder());
            Map<Integer, Integer> last = FunctionalMapsChallenge.maxPerGroup(
                    List.of(1, 5, 9), n -> 0, Comparator.naturalOrder());
            assertEquals(9, first.get(0));
            assertEquals(9, middle.get(0));
            assertEquals(9, last.get(0));
        }

        @Test
        void testReversedComparatorGivesTheSmallest() {
            Map<Integer, Integer> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of(4, 1, 7), n -> 0, Comparator.<Integer>naturalOrder().reversed());
            assertEquals(Map.of(0, 1), result);
        }

        @Test
        void testNegativeNumbers() {
            Map<Integer, Integer> result = FunctionalMapsChallenge.maxPerGroup(
                    List.of(-5, -2, -9), n -> 0, Comparator.naturalOrder());
            assertEquals(Map.of(0, -2), result);
        }

        @Test
        void testListIsNotModified() {
            List<String> items = new ArrayList<>(List.of("b", "a"));
            FunctionalMapsChallenge.maxPerGroup(items, w -> 0, Comparator.naturalOrder());
            assertEquals(List.of("b", "a"), items);
        }
    }

    // ==========================================================
    // CHALLENGE 11: wordsByInitial
    // ==========================================================
    @Nested
    class WordsByInitialTests {

        @Test
        void testGroupsAndJoins() {
            assertEquals(Map.of('a', "apple,avocado", 'b', "banana"),
                    FunctionalMapsChallenge.wordsByInitial(List.of("apple", "avocado", "banana")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(Map.of('A', "Ant", 'a', "ant"),
                    FunctionalMapsChallenge.wordsByInitial(List.of("Ant", "ant")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), FunctionalMapsChallenge.wordsByInitial(List.of()));
        }

        @Test
        void testOrderOfTheListIsKept() {
            assertEquals(Map.of('z', "zoo,zebra,zinc"),
                    FunctionalMapsChallenge.wordsByInitial(List.of("zoo", "zebra", "zinc")));
        }

        @Test
        void testSingleWord() {
            assertEquals(Map.of('x', "xylophone"), FunctionalMapsChallenge.wordsByInitial(List.of("xylophone")));
        }

        @Test
        void testNoSpaceAfterTheComma() {
            String joined = FunctionalMapsChallenge.wordsByInitial(List.of("a1", "a2")).get('a');
            assertEquals("a1,a2", joined);
        }

        @Test
        void testInterleavedWords() {
            assertEquals(Map.of('a', "ant,axe", 'b', "bee,bat"),
                    FunctionalMapsChallenge.wordsByInitial(List.of("ant", "bee", "axe", "bat")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(Map.of('a', "ab,ab"), FunctionalMapsChallenge.wordsByInitial(List.of("ab", "ab")));
        }

        @Test
        void testDigitsAndSymbolsAsInitials() {
            assertEquals(Map.of('1', "1a,1b", '#', "#x"),
                    FunctionalMapsChallenge.wordsByInitial(List.of("1a", "#x", "1b")));
        }

        @Test
        void testOneLetterWords() {
            assertEquals(Map.of('a', "a,a", 'b', "b"), FunctionalMapsChallenge.wordsByInitial(List.of("a", "b", "a")));
        }
    }

    // ==========================================================
    // CHALLENGE 12: countSorted
    // ==========================================================
    @Nested
    class CountSortedTests {

    @Test
        void testCountsAndSorts() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("pear", "apple", "pear", "fig"));
            assertEquals(List.of("apple", "fig", "pear"), new ArrayList<>(result.keySet()));
            assertEquals(1L, (long) result.get("apple"));
            assertEquals(1L, (long) result.get("fig"));
            assertEquals(2L, (long) result.get("pear"));
        }

        @Test
        void testUppercaseComesFirst() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("b", "B", "a"));
            assertEquals(List.of("B", "a", "b"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, FunctionalMapsChallenge.countSorted(List.of()).size());
        }

        @Test
        void testSingleWordRepeated() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("x", "x", "x"));
            assertEquals(1, result.size());
            assertEquals(3L, (long) result.get("x"));
        }

        @Test
        void testFirstAndLastKey() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("m", "z", "a", "q"));
            assertEquals("a", result.firstKey());
            assertEquals("z", result.lastKey());
        }

        @Test
        void testTheInputOrderDoesNotMatter() {
            TreeMap<String, Long> first = FunctionalMapsChallenge.countSorted(List.of("c", "b", "a"));
            TreeMap<String, Long> second = FunctionalMapsChallenge.countSorted(List.of("a", "b", "c"));
            assertEquals(new ArrayList<>(first.keySet()), new ArrayList<>(second.keySet()));
        }

        @Test
        void testCountsAreLongNumbers() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("a", "a"));
            assertEquals(2L, (long) result.get("a"));
        }

        @Test
        void testSortedByTextNotByCount() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("b", "b", "b", "a"));
            assertEquals("a", result.firstKey());
            assertEquals("b", result.lastKey());
        }

        @Test
        void testShorterPrefixComesFirst() {
            TreeMap<String, Long> result = FunctionalMapsChallenge.countSorted(List.of("ab", "a", "abc"));
            assertEquals(List.of("a", "ab", "abc"), new ArrayList<>(result.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 13: keysByValueDescending
    // ==========================================================
    @Nested
    class KeysByValueDescendingTests {

        @Test
        void testOrderByValue() {
            assertEquals(List.of("b", "c", "a"),
                    FunctionalMapsChallenge.keysByValueDescending(Map.of("a", 1, "b", 3, "c", 2)));
        }

        @Test
        void testTiesAreOrderedByKey() {
            assertEquals(List.of("c", "a", "b"),
                    FunctionalMapsChallenge.keysByValueDescending(Map.of("b", 2, "a", 2, "c", 5)));
        }

        @Test
        void testEmptyMap() {
            assertEquals(List.of(), FunctionalMapsChallenge.keysByValueDescending(Map.of()));
        }

        @Test
        void testSingleEntry() {
            assertEquals(List.of("x"), FunctionalMapsChallenge.keysByValueDescending(Map.of("x", 7)));
        }

        @Test
        void testAllTied() {
            assertEquals(List.of("a", "b", "c"),
                    FunctionalMapsChallenge.keysByValueDescending(Map.of("c", 1, "a", 1, "b", 1)));
        }

        @Test
        void testNegativeValues() {
            assertEquals(List.of("b", "a", "c"),
                    FunctionalMapsChallenge.keysByValueDescending(Map.of("a", -1, "b", 0, "c", -5)));
        }

        @Test
        void testKeyOrderDoesNotWinOverTheValue() {
            assertEquals(List.of("z", "a"), FunctionalMapsChallenge.keysByValueDescending(Map.of("a", 1, "z", 9)));
        }

        @Test
        void testOriginalMapIsNotModified() {
            Map<String, Integer> original = new HashMap<>(Map.of("a", 1, "b", 2));
            FunctionalMapsChallenge.keysByValueDescending(original);
            assertEquals(Map.of("a", 1, "b", 2), original);
        }

        @Test
        void testBigValues() {
            assertEquals(List.of("max", "min"), FunctionalMapsChallenge.keysByValueDescending(
                    Map.of("min", Integer.MIN_VALUE, "max", Integer.MAX_VALUE)));
        }

        @Test
        void testTiesAreOrderedAlphabeticallyWhateverTheInsertionOrder() {
            Map<String, Integer> map = new java.util.LinkedHashMap<>();
            map.put("d", 1);
            map.put("c", 1);
            map.put("b", 1);
            map.put("a", 1);
            assertEquals(List.of("a", "b", "c", "d"), FunctionalMapsChallenge.keysByValueDescending(map));
        }

        @Test
        void testTiesAreOrderedByTheTextNotByTheLength() {
            Map<String, Integer> map = new java.util.LinkedHashMap<>();
            map.put("b", 3);
            map.put("aa", 3);
            assertEquals(List.of("aa", "b"), FunctionalMapsChallenge.keysByValueDescending(map));
        }

        @Test
        void testSeveralTiesMixedWithOtherValues() {
            assertEquals(List.of("d", "a", "c", "b"), FunctionalMapsChallenge.keysByValueDescending(
                    Map.of("a", 5, "b", 1, "c", 5, "d", 9)));
        }
    }

    // ==========================================================
    // CHALLENGE 14: countByLength
    // ==========================================================
    @Nested
    class CountByLengthTests {

        @Test
        void testCountsByLength() {
            Map<Integer, Long> result = Stream.of("a", "bb", "c", "dd", "eee")
                    .collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(1, 2L, 2, 2L, 3, 1L), result);
        }

        @Test
        void testEmptyStream() {
            Map<Integer, Long> result = Stream.<String>empty().collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(), result);
        }

        @Test
        void testEmptyTexts() {
            Map<Integer, Long> result = Stream.of("", "", "a").collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(0, 2L, 1, 1L), result);
        }

        @Test
        void testRepeatedTextsAreCountedEveryTime() {
            Map<Integer, Long> result = Stream.of("aa", "aa", "aa").collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(2, 3L), result);
        }

        @Test
        void testWorksWithAList() {
            Map<Integer, Long> result = List.of("x", "yy").stream().collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(1, 1L, 2, 1L), result);
        }

        @Test
        void testTheCollectorCanBeUsedTwice() {
            var collector = FunctionalMapsChallenge.countByLength();
            Map<Integer, Long> first = Stream.of("a").collect(collector);
            Map<Integer, Long> second = Stream.of("bb", "cc").collect(collector);
            assertEquals(Map.of(1, 1L), first);
            assertEquals(Map.of(2, 2L), second);
        }

        @Test
        void testWorksWithAParallelStream() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                words.add(i % 2 == 0 ? "ab" : "abc");
            }
            Map<Integer, Long> result = words.parallelStream().collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(2, 500L, 3, 500L), result);
        }

        @Test
        void testSpacesCountAsCharacters() {
            Map<Integer, Long> result = Stream.of("  a", "abc", " ").collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(3, 2L, 1, 1L), result);
        }

        @Test
        void testLongTexts() {
            Map<Integer, Long> result = Stream.of("a".repeat(100), "b".repeat(100))
                    .collect(FunctionalMapsChallenge.countByLength());
            assertEquals(Map.of(100, 2L), result);
        }
    }

    // ==========================================================
    // CHALLENGE 15: evenOdd
    // ==========================================================
    @Nested
    class EvenOddTests {

        @Test
        void testSplit() {
            Map<Boolean, List<Integer>> result = Stream.of(1, 2, 3, 4, 5).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(2, 4), result.get(true));
            assertEquals(List.of(1, 3, 5), result.get(false));
        }

        @Test
        void testNegativeNumbers() {
            Map<Boolean, List<Integer>> result = Stream.of(-3, -2).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(-2), result.get(true));
            assertEquals(List.of(-3), result.get(false));
        }

        @Test
        void testEmptyStream() {
            Map<Boolean, List<Integer>> result = Stream.<Integer>empty().collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(Map.of(false, List.of(), true, List.of()), result);
        }

        @Test
        void testOnlyEvenNumbers() {
            Map<Boolean, List<Integer>> result = Stream.of(2, 4).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(2, 4), result.get(true));
            assertEquals(List.of(), result.get(false));
            assertEquals(2, result.size());
        }

        @Test
        void testOnlyOddNumbers() {
            Map<Boolean, List<Integer>> result = Stream.of(1, 3).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(), result.get(true));
            assertEquals(List.of(1, 3), result.get(false));
            assertEquals(2, result.size());
        }

        @Test
        void testZeroIsEven() {
            Map<Boolean, List<Integer>> result = Stream.of(0, 1).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(0), result.get(true));
        }

        @Test
        void testOrderIsKept() {
            Map<Boolean, List<Integer>> result = Stream.of(9, 4, 7, 2, 5, 8).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(4, 2, 8), result.get(true));
            assertEquals(List.of(9, 7, 5), result.get(false));
        }

        @Test
        void testRepeatedNumbersAreKept() {
            Map<Boolean, List<Integer>> result = Stream.of(2, 2, 3, 3).collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(2, 2), result.get(true));
            assertEquals(List.of(3, 3), result.get(false));
        }

        @Test
        void testTheCollectorCanBeUsedTwice() {
            var collector = FunctionalMapsChallenge.evenOdd();
            Map<Boolean, List<Integer>> first = Stream.of(1).collect(collector);
            Map<Boolean, List<Integer>> second = Stream.of(2).collect(collector);
            assertEquals(List.of(1), first.get(false));
            assertEquals(List.of(2), second.get(true));
        }

        @Test
        void testBigNumbers() {
            Map<Boolean, List<Integer>> result = Stream.of(Integer.MAX_VALUE, Integer.MIN_VALUE)
                    .collect(FunctionalMapsChallenge.evenOdd());
            assertEquals(List.of(Integer.MIN_VALUE), result.get(true));
            assertEquals(List.of(Integer.MAX_VALUE), result.get(false));
        }
    }
}