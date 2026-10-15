package dev.perfectbogus.maps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: capitalOf
    // ==========================================================
    @Nested
    class CapitalOfTests {

        @Test
        void testReturnsTheStoredCapital() {
            Map<String, String> capitals = Map.of("Peru", "Lima", "Chile", "Santiago");

            assertEquals("Lima", MapsChallenge.capitalOf(capitals, "Peru"));
            assertEquals("Santiago", MapsChallenge.capitalOf(capitals, "Chile"));
        }

        @Test
        void testUnknownCountry() {
            Map<String, String> capitals = Map.of("Peru", "Lima");

            assertEquals("Unknown", MapsChallenge.capitalOf(capitals, "Brazil"));
        }

        @Test
        void testEmptyMap() {
            assertEquals("Unknown", MapsChallenge.capitalOf(Map.of(), "Peru"));
        }

        @Test
        void testKeysAreCaseSensitive() {
            Map<String, String> capitals = Map.of("Peru", "Lima");

            assertEquals("Unknown", MapsChallenge.capitalOf(capitals, "peru"));
        }

        @Test
        void testTheMapIsNotModified() {
            Map<String, String> capitals = new HashMap<>(Map.of("Peru", "Lima"));

            MapsChallenge.capitalOf(capitals, "Brazil");

            assertEquals(Map.of("Peru", "Lima"), capitals);
        }
    }

    // ==========================================================
    // CHALLENGE 2: countCharacters
    // ==========================================================
    @Nested
    class CountCharactersTests {

        @Test
        void testCountsEveryCharacter() {
            assertEquals(Map.of('h', 1, 'e', 1, 'l', 2, 'o', 1), MapsChallenge.countCharacters("hello"));
        }

        @Test
        void testEmptyText() {
            assertEquals(Map.of(), MapsChallenge.countCharacters(""));
        }

        @Test
        void testSameCharacterRepeated() {
            assertEquals(Map.of('a', 5), MapsChallenge.countCharacters("aaaaa"));
        }

        @Test
        void testUpperAndLowerCaseAreDifferentCharacters() {
            assertEquals(Map.of('a', 2, 'A', 1), MapsChallenge.countCharacters("aAa"));
        }

        @Test
        void testSpacesAndSymbolsAreCountedToo() {
            assertEquals(Map.of('a', 1, ' ', 2, '!', 1, 'b', 1), MapsChallenge.countCharacters("a b !"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: invert
    // ==========================================================
    @Nested
    class InvertTests {

        @Test
        void testSwapsKeysAndValues() {
            assertEquals(Map.of(1, "a", 2, "b"), MapsChallenge.invert(Map.of("a", 1, "b", 2)));
        }

        @Test
        void testEmptyMap() {
            assertEquals(Map.of(), MapsChallenge.invert(Map.of()));
        }

        @Test
        void testSingleEntryAndNegativeValues() {
            assertEquals(Map.of(-7, "x"), MapsChallenge.invert(Map.of("x", -7)));
            assertEquals(Map.of(0, "zero", 100, "hundred"),
                    MapsChallenge.invert(Map.of("zero", 0, "hundred", 100)));
        }

        @Test
        void testTheMapIsNotModified() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 2));

            MapsChallenge.invert(map);

            assertEquals(Map.of("a", 1, "b", 2), map);
        }
    }

    // ==========================================================
    // CHALLENGE 4: totalOfValues
    // ==========================================================
    @Nested
    class TotalOfValuesTests {

        @Test
        void testSumsTheValues() {
            assertEquals(17, MapsChallenge.totalOfValues(Map.of("apple", 3, "pear", 4, "kiwi", 10)));
        }

        @Test
        void testEmptyMap() {
            assertEquals(0, MapsChallenge.totalOfValues(Map.of()));
        }

        @Test
        void testNegativeValuesAndZeros() {
            assertEquals(-2, MapsChallenge.totalOfValues(Map.of("a", -5, "b", 0, "c", 3)));
        }

        @Test
        void testEqualValuesAreAllCounted() {
            assertEquals(12, MapsChallenge.totalOfValues(Map.of("a", 4, "b", 4, "c", 4)));
        }
    }

    // ==========================================================
    // CHALLENGE 5: keysAbove
    // ==========================================================
    @Nested
    class KeysAboveTests {

        @Test
        void testKeysAboveTheThresholdSorted() {
            assertEquals(List.of("ana", "cy"), MapsChallenge.keysAbove(Map.of("bob", 70, "ana", 90, "cy", 85), 80));
        }

        @Test
        void testTheThresholdItselfIsNotIncluded() {
            assertEquals(List.of(), MapsChallenge.keysAbove(Map.of("bob", 70, "ana", 90, "cy", 85), 90));
            assertEquals(List.of("ana"), MapsChallenge.keysAbove(Map.of("bob", 70, "ana", 90, "cy", 85), 89));
        }

        @Test
        void testNothingMatchesAndEmptyMap() {
            assertEquals(List.of(), MapsChallenge.keysAbove(Map.of("a", 1), 5));
            assertEquals(List.of(), MapsChallenge.keysAbove(Map.of(), 0));
        }

        @Test
        void testAllKeysMatchAndNegativeThreshold() {
            assertEquals(List.of("a", "b", "c"), MapsChallenge.keysAbove(Map.of("c", 3, "a", 1, "b", 2), -1));
        }

        @Test
        void testResultIsSortedEvenWhenTheMapIsNot() {
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("zed", 5);
            map.put("amy", 6);
            map.put("mia", 7);

            assertEquals(List.of("amy", "mia", "zed"), MapsChallenge.keysAbove(map, 0));
        }
    }

    // ==========================================================
    // CHALLENGE 6: mergeSum
    // ==========================================================
    @Nested
    class MergeSumTests {

        @Test
        void testSumsTheValuesOfCommonKeys() {
            assertEquals(Map.of("a", 1, "b", 12, "c", 3),
                    MapsChallenge.mergeSum(Map.of("a", 1, "b", 2), Map.of("b", 10, "c", 3)));
        }

        @Test
        void testOneMapEmpty() {
            assertEquals(Map.of("a", 1), MapsChallenge.mergeSum(Map.of("a", 1), Map.of()));
            assertEquals(Map.of("a", 1), MapsChallenge.mergeSum(Map.of(), Map.of("a", 1)));
            assertEquals(Map.of(), MapsChallenge.mergeSum(Map.of(), Map.of()));
        }

        @Test
        void testNoCommonKeys() {
            assertEquals(Map.of("a", 1, "b", 2), MapsChallenge.mergeSum(Map.of("a", 1), Map.of("b", 2)));
        }

        @Test
        void testNegativeValuesAndZeroSum() {
            assertEquals(Map.of("a", 0), MapsChallenge.mergeSum(Map.of("a", 5), Map.of("a", -5)));
        }

        @Test
        void testTheInputsAreNotModified() {
            Map<String, Integer> a = new HashMap<>(Map.of("a", 1, "b", 2));
            Map<String, Integer> b = new HashMap<>(Map.of("b", 10, "c", 3));

            MapsChallenge.mergeSum(a, b);

            assertEquals(Map.of("a", 1, "b", 2), a);
            assertEquals(Map.of("b", 10, "c", 3), b);
        }

        @Test
        void testTheResultIsIndependentFromTheInputs() {
            Map<String, Integer> a = new HashMap<>(Map.of("a", 1));

            Map<String, Integer> result = MapsChallenge.mergeSum(a, Map.of());
            result.put("z", 26);

            assertEquals(Map.of("a", 1), a);
        }
    }

    // ==========================================================
    // CHALLENGE 7: keyWithMaxValue
    // ==========================================================
    @Nested
    class KeyWithMaxValueTests {

        @Test
        void testKeyOfTheGreatestValue() {
            assertEquals("b", MapsChallenge.keyWithMaxValue(Map.of("a", 3, "b", 9, "c", 5)));
        }

        @Test
        void testTiesGoToTheSmallestKey() {
            assertEquals("b", MapsChallenge.keyWithMaxValue(Map.of("a", 3, "b", 9, "c", 9)));
            assertEquals("a", MapsChallenge.keyWithMaxValue(Map.of("c", 1, "b", 1, "a", 1)));
        }

        @Test
        void testTiesAreResolvedAlphabeticallyEvenWhenTheMapIsOrderedOtherwise() {
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("zed", 4);
            map.put("amy", 4);
            map.put("mia", 4);

            assertEquals("amy", MapsChallenge.keyWithMaxValue(map));
        }

        @Test
        void testNegativeValues() {
            assertEquals("y", MapsChallenge.keyWithMaxValue(Map.of("x", -5, "y", -2)));
        }

        @Test
        void testSingleEntryAndEmptyMap() {
            assertEquals("only", MapsChallenge.keyWithMaxValue(Map.of("only", 0)));
            assertNull(MapsChallenge.keyWithMaxValue(Map.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: removeBelow
    // ==========================================================
    @Nested
    class RemoveBelowTests {

        @Test
        void testRemovesTheSmallValuesInPlace() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 5, "c", 2));

            int removed = MapsChallenge.removeBelow(map, 2);

            assertEquals(1, removed);
            assertEquals(Map.of("b", 5, "c", 2), map);
        }

        @Test
        void testNothingToRemove() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 5, "c", 2));

            assertEquals(0, MapsChallenge.removeBelow(map, 0));
            assertEquals(Map.of("a", 1, "b", 5, "c", 2), map);
        }

        @Test
        void testRemovesEverything() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 5, "c", 2));

            assertEquals(3, MapsChallenge.removeBelow(map, 100));
            assertEquals(Map.of(), map);
        }

        @Test
        void testSeveralEntriesAreRemoved() {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < 20; i++) {
                map.put("k" + i, i);
            }

            int removed = MapsChallenge.removeBelow(map, 15);

            assertEquals(15, removed);
            assertEquals(5, map.size());
            assertTrue(map.containsKey("k15"));
            assertFalse(map.containsKey("k14"));
        }

        @Test
        void testEmptyMapAndNegativeValues() {
            assertEquals(0, MapsChallenge.removeBelow(new HashMap<>(), 5));
            Map<String, Integer> map = new HashMap<>(Map.of("a", -3, "b", 0));

            assertEquals(1, MapsChallenge.removeBelow(map, -1));
            assertEquals(Map.of("b", 0), map);
        }
    }

    // ==========================================================
    // CHALLENGE 9: addMissingKeys
    // ==========================================================
    @Nested
    class AddMissingKeysTests {

        @Test
        void testAddsOnlyTheMissingKeys() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 5));

            int added = MapsChallenge.addMissingKeys(map, List.of("a", "b", "c", "b"));

            assertEquals(2, added);
            assertEquals(Map.of("a", 5, "b", 0, "c", 0), map);
        }

        @Test
        void testExistingValuesAreKept() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 5, "b", 7));

            assertEquals(0, MapsChallenge.addMissingKeys(map, List.of("a", "b")));
            assertEquals(Map.of("a", 5, "b", 7), map);
        }

        @Test
        void testAnExistingKeyWithValueZeroIsNotCountedAsAdded() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 0));

            assertEquals(1, MapsChallenge.addMissingKeys(map, List.of("a", "b")));
            assertEquals(Map.of("a", 0, "b", 0), map);
        }

        @Test
        void testEmptyKeys() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 5));

            assertEquals(0, MapsChallenge.addMissingKeys(map, List.of()));
            assertEquals(Map.of("a", 5), map);
        }

        @Test
        void testEmptyMap() {
            Map<String, Integer> map = new HashMap<>();

            assertEquals(3, MapsChallenge.addMissingKeys(map, List.of("x", "y", "z")));
            assertEquals(Map.of("x", 0, "y", 0, "z", 0), map);
        }
    }

    // ==========================================================
    // CHALLENGE 10: groupByLength
    // ==========================================================
    @Nested
    class GroupByLengthTests {

        @Test
        void testGroupsWordsByLength() {
            assertEquals(Map.of(1, List.of("a", "d"), 2, List.of("bb", "cc")),
                    MapsChallenge.groupByLength(List.of("a", "bb", "cc", "d")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), MapsChallenge.groupByLength(List.of()));
        }

        @Test
        void testKeepsTheOrderOfTheInput() {
            assertEquals(Map.of(3, List.of("zip", "abc", "mno")),
                    MapsChallenge.groupByLength(List.of("zip", "abc", "mno")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(Map.of(2, List.of("hi", "hi", "yo")),
                    MapsChallenge.groupByLength(List.of("hi", "hi", "yo")));
        }

        @Test
        void testLengthsWithoutWordsAreMissing() {
            Map<Integer, List<String>> result = MapsChallenge.groupByLength(List.of("a", "abcd"));

            assertEquals(Set.of(1, 4), result.keySet());
            assertFalse(result.containsKey(2));
        }

        @Test
        void testEmptyWordsHaveLengthZero() {
            assertEquals(Map.of(0, List.of(""), 1, List.of("a")), MapsChallenge.groupByLength(List.of("a", "")));
        }
    }

    // ==========================================================
    // CHALLENGE 11: doubleValues
    // ==========================================================
    @Nested
    class DoubleValuesTests {

        @Test
        void testDoublesEveryValue() {
            Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 5, "c", -3));

            MapsChallenge.doubleValues(map);

            assertEquals(Map.of("a", 2, "b", 10, "c", -6), map);
        }

        @Test
        void testEmptyMap() {
            Map<String, Integer> map = new HashMap<>();

            MapsChallenge.doubleValues(map);

            assertEquals(Map.of(), map);
        }

        @Test
        void testZeroStaysZeroAndKeysDoNotChange() {
            Map<String, Integer> map = new HashMap<>(Map.of("x", 0, "y", 21));

            MapsChallenge.doubleValues(map);

            assertEquals(Map.of("x", 0, "y", 42), map);
            assertEquals(Set.of("x", "y"), map.keySet());
        }

        @Test
        void testTheOrderOfALinkedMapIsKept() {
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("z", 1);
            map.put("a", 2);
            map.put("m", 3);

            MapsChallenge.doubleValues(map);

            assertEquals(List.of("z", "a", "m"), new ArrayList<>(map.keySet()));
            assertEquals(List.of(2, 4, 6), new ArrayList<>(map.values()));
        }
    }

    // ==========================================================
    // CHALLENGE 12: sameKeys
    // ==========================================================
    @Nested
    class SameKeysTests {

        @Test
        void testSameKeysDifferentValues() {
            assertTrue(MapsChallenge.sameKeys(Map.of("a", 1, "b", 2), Map.of("b", 9, "a", 7)));
        }

        @Test
        void testOneHasMoreKeys() {
            assertFalse(MapsChallenge.sameKeys(Map.of("a", 1), Map.of("a", 1, "b", 2)));
            assertFalse(MapsChallenge.sameKeys(Map.of("a", 1, "b", 2), Map.of("a", 1)));
        }

        @Test
        void testSameSizeDifferentKeys() {
            assertFalse(MapsChallenge.sameKeys(Map.of("a", 1, "b", 2), Map.of("a", 1, "c", 2)));
        }

        @Test
        void testEmptyMaps() {
            assertTrue(MapsChallenge.sameKeys(Map.of(), Map.of()));
            assertFalse(MapsChallenge.sameKeys(Map.of(), Map.of("a", 1)));
        }

        @Test
        void testSameValuesButDifferentKeys() {
            assertFalse(MapsChallenge.sameKeys(Map.of("a", 1), Map.of("b", 1)));
        }
    }

    // ==========================================================
    // CHALLENGE 13: entriesToText
    // ==========================================================
    @Nested
    class EntriesToTextTests {

        @Test
        void testSortedByKey() {
            assertEquals("a=1, b=2, c=3", MapsChallenge.entriesToText(Map.of("b", 2, "a", 1, "c", 3)));
        }

        @Test
        void testSingleEntry() {
            assertEquals("x=7", MapsChallenge.entriesToText(Map.of("x", 7)));
        }

        @Test
        void testEmptyMap() {
            assertEquals("", MapsChallenge.entriesToText(Map.of()));
        }

        @Test
        void testNegativeValues() {
            assertEquals("a=-1, b=0", MapsChallenge.entriesToText(Map.of("b", 0, "a", -1)));
        }

        @Test
        void testSortingIsByKeyNotByValueNorInsertionOrder() {
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("zed", 1);
            map.put("amy", 9);
            map.put("mia", 5);

            assertEquals("amy=9, mia=5, zed=1", MapsChallenge.entriesToText(map));
        }

        @Test
        void testUpperCaseKeysComeBeforeLowerCaseKeys() {
            assertEquals("B=2, a=1", MapsChallenge.entriesToText(Map.of("a", 1, "B", 2)));
        }
    }

    // ==========================================================
    // CHALLENGE 14: sortedByKeyDescending
    // ==========================================================
    @Nested
    class SortedByKeyDescendingTests {

        @Test
        void testIterationOrderIsReverseAlphabetical() {
            Map<String, Integer> result = MapsChallenge.sortedByKeyDescending(Map.of("a", 1, "c", 3, "b", 2));

            assertEquals(List.of("c", "b", "a"), new ArrayList<>(result.keySet()));
            assertEquals(List.of(3, 2, 1), new ArrayList<>(result.values()));
        }

        @Test
        void testTheEntriesAreTheSame() {
            Map<String, Integer> input = Map.of("a", 1, "c", 3, "b", 2);

            assertEquals(input, MapsChallenge.sortedByKeyDescending(input));
        }

        @Test
        void testEmptyMap() {
            assertEquals(Map.of(), MapsChallenge.sortedByKeyDescending(Map.of()));
        }

        @Test
        void testTheInputIsNotModified() {
            Map<String, Integer> input = new LinkedHashMap<>();
            input.put("a", 1);
            input.put("b", 2);

            MapsChallenge.sortedByKeyDescending(input);

            assertEquals(List.of("a", "b"), new ArrayList<>(input.keySet()));
        }

        @Test
        void testLongerKeysAndCommonPrefixes() {
            Map<String, Integer> result =
                    MapsChallenge.sortedByKeyDescending(Map.of("ab", 1, "a", 2, "abc", 3, "b", 4));

            assertEquals(List.of("b", "abc", "ab", "a"), new ArrayList<>(result.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 15: zip
    // ==========================================================
    @Nested
    class ZipTests {

        @Test
        void testPairsByPosition() {
            assertEquals(Map.of("a", 1, "b", 2, "c", 3), MapsChallenge.zip(List.of("a", "b", "c"), List.of(1, 2, 3)));
        }

        @Test
        void testMoreKeysThanValues() {
            assertEquals(Map.of("a", 1, "b", 2), MapsChallenge.zip(List.of("a", "b", "c"), List.of(1, 2)));
        }

        @Test
        void testMoreValuesThanKeys() {
            assertEquals(Map.of("a", 1), MapsChallenge.zip(List.of("a"), List.of(1, 2, 3)));
        }

        @Test
        void testTheLastValueWinsForARepeatedKey() {
            assertEquals(Map.of("a", 2), MapsChallenge.zip(List.of("a", "a"), List.of(1, 2)));
            assertEquals(Map.of("a", 3, "b", 2), MapsChallenge.zip(List.of("a", "b", "a"), List.of(1, 2, 3)));
        }

        @Test
        void testEmptyLists() {
            assertEquals(Map.of(), MapsChallenge.zip(List.of(), List.of()));
            assertEquals(Map.of(), MapsChallenge.zip(List.of("a"), List.of()));
            assertEquals(Map.of(), MapsChallenge.zip(List.of(), List.of(1)));
        }

        @Test
        void testTheResultCanBeModified() {
            Map<String, Integer> result = MapsChallenge.zip(List.of("a"), List.of(1));

            result.put("b", 2);

            assertEquals(Map.of("a", 1, "b", 2), result);
        }
    }
}