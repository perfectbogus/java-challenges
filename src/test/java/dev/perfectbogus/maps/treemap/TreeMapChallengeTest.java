package dev.perfectbogus.maps.treemap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapChallengeTest {

    private static TreeMap<Integer, String> intMap(int... keys) {
        TreeMap<Integer, String> map = new TreeMap<>();
        for (int key : keys) {
            map.put(key, "v" + key);
        }
        return map;
    }

    private static TreeMap<String, Integer> letters(String... keys) {
        TreeMap<String, Integer> map = new TreeMap<>();
        int value = 1;
        for (String key : keys) {
            map.put(key, value++);
        }
        return map;
    }

    // ==========================================================
    // CHALLENGE 1: smallestKey
    // ==========================================================
    @Nested
    class SmallestKeyTests {

        @Test
        void testReturnsTheSmallestKey() {
            assertEquals(2, TreeMapChallenge.smallestKey(intMap(5, 2, 9)));
        }

        @Test
        void testSingleEntry() {
            assertEquals(7, TreeMapChallenge.smallestKey(intMap(7)));
        }

        @Test
        void testNegativeKeys() {
            assertEquals(-8, TreeMapChallenge.smallestKey(intMap(3, -8, 0, -2)));
        }

        @Test
        void testEmptyMapGivesNull() {
            assertNull(TreeMapChallenge.smallestKey(new TreeMap<>()));
        }

        @Test
        void testTheMapIsNotModified() {
            TreeMap<Integer, String> map = intMap(5, 2, 9);

            TreeMapChallenge.smallestKey(map);

            assertEquals(intMap(5, 2, 9), map);
        }
    }

    // ==========================================================
    // CHALLENGE 2: valueOfLastKey
    // ==========================================================
    @Nested
    class ValueOfLastKeyTests {

        @Test
        void testValueOfTheGreatestKey() {
            TreeMap<String, Integer> map = new TreeMap<>(Map.of("ana", 3, "bob", 8, "cy", 5));

            assertEquals(5, TreeMapChallenge.valueOfLastKey(map));
        }

        @Test
        void testTheGreatestValueIsNotWhatCounts() {
            TreeMap<String, Integer> map = new TreeMap<>(Map.of("a", 100, "b", 1));

            assertEquals(1, TreeMapChallenge.valueOfLastKey(map));
        }

        @Test
        void testSingleEntry() {
            assertEquals(4, TreeMapChallenge.valueOfLastKey(new TreeMap<>(Map.of("x", 4))));
        }

        @Test
        void testNegativeValue() {
            assertEquals(-3, TreeMapChallenge.valueOfLastKey(new TreeMap<>(Map.of("a", 1, "b", -3))));
        }

        @Test
        void testEmptyMapGivesMinusOne() {
            assertEquals(-1, TreeMapChallenge.valueOfLastKey(new TreeMap<>()));
        }

        @Test
        void testTheMapIsNotModified() {
            TreeMap<String, Integer> map = new TreeMap<>(Map.of("a", 1, "b", 2));

            TreeMapChallenge.valueOfLastKey(map);

            assertEquals(Map.of("a", 1, "b", 2), map);
        }
    }

    // ==========================================================
    // CHALLENGE 3: gradeOf
    // ==========================================================
    @Nested
    class GradeOfTests {

        private TreeMap<Integer, String> grades() {
            TreeMap<Integer, String> grades = new TreeMap<>();
            grades.put(0, "F");
            grades.put(60, "D");
            grades.put(70, "C");
            grades.put(80, "B");
            grades.put(90, "A");
            return grades;
        }

        @Test
        void testScoreBetweenTwoThresholds() {
            assertEquals("B", TreeMapChallenge.gradeOf(grades(), 85));
            assertEquals("D", TreeMapChallenge.gradeOf(grades(), 69));
        }

        @Test
        void testScoreEqualToAThresholdBelongsToThatGrade() {
            assertEquals("A", TreeMapChallenge.gradeOf(grades(), 90));
            assertEquals("F", TreeMapChallenge.gradeOf(grades(), 0));
            assertEquals("C", TreeMapChallenge.gradeOf(grades(), 70));
        }

        @Test
        void testScoreAboveEveryThreshold() {
            assertEquals("A", TreeMapChallenge.gradeOf(grades(), 1000));
        }

        @Test
        void testScoreBelowEveryThreshold() {
            assertEquals("None", TreeMapChallenge.gradeOf(grades(), -4));
        }

        @Test
        void testEmptyThresholds() {
            assertEquals("None", TreeMapChallenge.gradeOf(new TreeMap<>(), 50));
        }

        @Test
        void testTheMapIsNotModified() {
            TreeMap<Integer, String> grades = grades();

            TreeMapChallenge.gradeOf(grades, 85);

            assertEquals(grades(), grades);
        }
    }

    // ==========================================================
    // CHALLENGE 4: firstKeyAtLeast
    // ==========================================================
    @Nested
    class FirstKeyAtLeastTests {

        @Test
        void testKeyBetweenTwoKeys() {
            assertEquals(20, TreeMapChallenge.firstKeyAtLeast(intMap(10, 20, 30), 15));
        }

        @Test
        void testEqualKeyIsIncluded() {
            assertEquals(20, TreeMapChallenge.firstKeyAtLeast(intMap(10, 20, 30), 20));
        }

        @Test
        void testValueBelowEveryKey() {
            assertEquals(10, TreeMapChallenge.firstKeyAtLeast(intMap(10, 20, 30), -5));
        }

        @Test
        void testNoKeyIsBigEnough() {
            assertNull(TreeMapChallenge.firstKeyAtLeast(intMap(10, 20, 30), 31));
        }

        @Test
        void testEmptyMap() {
            assertNull(TreeMapChallenge.firstKeyAtLeast(new TreeMap<>(), 1));
        }
    }

    // ==========================================================
    // CHALLENGE 5: closestKey
    // ==========================================================
    @Nested
    class ClosestKeyTests {

        @Test
        void testCloserToTheLowerKey() {
            assertEquals(20, TreeMapChallenge.closestKey(intMap(10, 20, 40), 23));
        }

        @Test
        void testCloserToTheHigherKey() {
            assertEquals(40, TreeMapChallenge.closestKey(intMap(10, 20, 40), 31));
        }

        @Test
        void testTieGoesToTheSmallerKey() {
            assertEquals(20, TreeMapChallenge.closestKey(intMap(10, 20, 40), 30));
            assertEquals(1, TreeMapChallenge.closestKey(intMap(1, 3), 2));
        }

        @Test
        void testTargetIsAKey() {
            assertEquals(20, TreeMapChallenge.closestKey(intMap(10, 20, 40), 20));
        }

        @Test
        void testTargetBelowEveryKey() {
            assertEquals(10, TreeMapChallenge.closestKey(intMap(10, 20, 40), -100));
        }

        @Test
        void testTargetAboveEveryKey() {
            assertEquals(40, TreeMapChallenge.closestKey(intMap(10, 20, 40), 99));
        }

        @Test
        void testSingleKey() {
            assertEquals(7, TreeMapChallenge.closestKey(intMap(7), 1000));
        }

        @Test
        void testExtremeKeysDoNotOverflow() {
            TreeMap<Integer, String> map = intMap(Integer.MIN_VALUE, Integer.MAX_VALUE);

            assertEquals(Integer.MAX_VALUE, TreeMapChallenge.closestKey(map, 0));
            assertEquals(Integer.MIN_VALUE, TreeMapChallenge.closestKey(map, -1));
        }

        @Test
        void testEmptyMapGivesNull() {
            assertNull(TreeMapChallenge.closestKey(new TreeMap<>(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 6: keysBetween
    // ==========================================================
    @Nested
    class KeysBetweenTests {

        @Test
        void testBothLimitsAreIncluded() {
            assertEquals(List.of(3, 5, 7), TreeMapChallenge.keysBetween(intMap(1, 3, 5, 7, 9), 3, 7));
        }

        @Test
        void testLimitsThatAreNotKeys() {
            assertEquals(List.of(5, 7), TreeMapChallenge.keysBetween(intMap(1, 3, 5, 7, 9), 4, 8));
        }

        @Test
        void testFromGreaterThanToGivesEmptyList() {
            assertEquals(List.of(), TreeMapChallenge.keysBetween(intMap(1, 3, 5, 7, 9), 8, 4));
        }

        @Test
        void testSingleKeyRange() {
            assertEquals(List.of(5), TreeMapChallenge.keysBetween(intMap(1, 3, 5, 7, 9), 5, 5));
        }

        @Test
        void testRangeWithoutKeys() {
            assertEquals(List.of(), TreeMapChallenge.keysBetween(intMap(1, 3, 5, 7, 9), 10, 20));
            assertEquals(List.of(), TreeMapChallenge.keysBetween(intMap(1, 9), 2, 8));
        }

        @Test
        void testRangeCoveringEverything() {
            assertEquals(List.of(1, 3, 5), TreeMapChallenge.keysBetween(intMap(1, 3, 5), -100, 100));
        }

        @Test
        void testEmptyMap() {
            assertEquals(List.of(), TreeMapChallenge.keysBetween(new TreeMap<>(), 1, 5));
        }

        @Test
        void testTheMapIsNotModifiedAndTheListIsIndependent() {
            TreeMap<Integer, String> map = intMap(1, 3, 5);

            List<Integer> keys = TreeMapChallenge.keysBetween(map, 1, 5);
            keys.clear();

            assertEquals(intMap(1, 3, 5), map);
        }
    }

    // ==========================================================
    // CHALLENGE 7: removeSmallest
    // ==========================================================
    @Nested
    class RemoveSmallestTests {

        @Test
        void testRemovesTheSmallestKeysInOrder() {
            TreeMap<String, Integer> map = letters("a", "b", "c", "d");

            List<String> removed = TreeMapChallenge.removeSmallest(map, 2);

            assertEquals(List.of("a", "b"), removed);
            assertEquals(Map.of("c", 3, "d", 4), map);
        }

        @Test
        void testTheSmallestAreRemovedNotTheFirstInserted() {
            TreeMap<String, Integer> map = new TreeMap<>();
            map.put("z", 1);
            map.put("m", 2);
            map.put("a", 3);

            List<String> removed = TreeMapChallenge.removeSmallest(map, 1);

            assertEquals(List.of("a"), removed);
            assertEquals(Map.of("z", 1, "m", 2), map);
        }

        @Test
        void testMoreThanTheSizeRemovesEverything() {
            TreeMap<String, Integer> map = letters("a", "b");

            List<String> removed = TreeMapChallenge.removeSmallest(map, 5);

            assertEquals(List.of("a", "b"), removed);
            assertTrue(map.isEmpty());
        }

        @Test
        void testZeroRemovesNothing() {
            TreeMap<String, Integer> map = letters("a", "b");

            assertEquals(List.of(), TreeMapChallenge.removeSmallest(map, 0));
            assertEquals(Map.of("a", 1, "b", 2), map);
        }

        @Test
        void testNegativeRemovesNothing() {
            TreeMap<String, Integer> map = letters("a", "b");

            assertEquals(List.of(), TreeMapChallenge.removeSmallest(map, -3));
            assertEquals(Map.of("a", 1, "b", 2), map);
        }

        @Test
        void testExactlyTheSize() {
            TreeMap<String, Integer> map = letters("a", "b", "c");

            assertEquals(List.of("a", "b", "c"), TreeMapChallenge.removeSmallest(map, 3));
            assertTrue(map.isEmpty());
        }

        @Test
        void testEmptyMap() {
            TreeMap<String, Integer> map = new TreeMap<>();

            assertEquals(List.of(), TreeMapChallenge.removeSmallest(map, 2));
            assertTrue(map.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 8: greatestKeys
    // ==========================================================
    @Nested
    class GreatestKeysTests {

        @Test
        void testTheGreatestKeysFromTheGreatest() {
            assertEquals(List.of("d", "c"), TreeMapChallenge.greatestKeys(letters("a", "b", "c", "d"), 2));
        }

        @Test
        void testMoreThanTheSizeGivesAllKeysDescending() {
            assertEquals(List.of("d", "c", "b", "a"), TreeMapChallenge.greatestKeys(letters("a", "b", "c", "d"), 9));
        }

        @Test
        void testOrderDoesNotDependOnInsertion() {
            TreeMap<String, Integer> map = new TreeMap<>();
            map.put("b", 1);
            map.put("z", 2);
            map.put("a", 3);

            assertEquals(List.of("z", "b"), TreeMapChallenge.greatestKeys(map, 2));
        }

        @Test
        void testZeroGivesEmptyList() {
            assertEquals(List.of(), TreeMapChallenge.greatestKeys(letters("a", "b"), 0));
        }

        @Test
        void testNegativeGivesEmptyList() {
            assertEquals(List.of(), TreeMapChallenge.greatestKeys(letters("a", "b"), -1));
        }

        @Test
        void testEmptyMap() {
            assertEquals(List.of(), TreeMapChallenge.greatestKeys(new TreeMap<>(), 3));
        }

        @Test
        void testTheMapIsNotModified() {
            TreeMap<String, Integer> map = letters("a", "b", "c");

            TreeMapChallenge.greatestKeys(map, 2);

            assertEquals(Map.of("a", 1, "b", 2, "c", 3), map);
        }
    }

    // ==========================================================
    // CHALLENGE 9: countIgnoringCase
    // ==========================================================
    @Nested
    class CountIgnoringCaseTests {

        @Test
        void testCountsIgnoringCase() {
            TreeMap<String, Integer> result =
                    TreeMapChallenge.countIgnoringCase(List.of("Apple", "banana", "apple", "BANANA", "Cherry"));

            assertEquals(3, result.size());
            assertEquals(2, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(1, result.get("cherry"));
        }

        @Test
        void testTheKeyIsTheFirstSpelling() {
            TreeMap<String, Integer> result =
                    TreeMapChallenge.countIgnoringCase(List.of("Apple", "banana", "apple", "BANANA", "Cherry"));

            assertEquals(List.of("Apple", "banana", "Cherry"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testLookupIgnoresCase() {
            TreeMap<String, Integer> result = TreeMapChallenge.countIgnoringCase(List.of("Cherry", "kiwi"));

            assertEquals(1, result.get("CHERRY"));
            assertEquals(1, result.get("KiWi"));
            assertTrue(result.containsKey("cHeRrY"));
            assertFalse(result.containsKey("lemon"));
        }

        @Test
        void testKeysAreSortedIgnoringCase() {
            TreeMap<String, Integer> result = TreeMapChallenge.countIgnoringCase(List.of("banana", "Cherry", "apple"));

            assertEquals(List.of("apple", "banana", "Cherry"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testTheFirstSpellingWinsEvenIfItIsUpperCase() {
            TreeMap<String, Integer> result = TreeMapChallenge.countIgnoringCase(List.of("DOG", "dog", "Dog"));

            assertEquals(List.of("DOG"), new ArrayList<>(result.keySet()));
            assertEquals(3, result.get("dog"));
        }

        @Test
        void testEmptyList() {
            assertTrue(TreeMapChallenge.countIgnoringCase(List.of()).isEmpty());
        }

        @Test
        void testLaterLookupsOfNewWordsAlsoIgnoreCase() {
            TreeMap<String, Integer> result = TreeMapChallenge.countIgnoringCase(List.of("a"));

            result.merge("A", 1, Integer::sum);

            assertEquals(1, result.size());
            assertEquals(2, result.get("a"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: entriesFrom
    // ==========================================================
    @Nested
    class EntriesFromTests {

        private TreeMap<String, Integer> map() {
            TreeMap<String, Integer> map = new TreeMap<>();
            map.put("a", 1);
            map.put("c", 3);
            map.put("e", 5);
            map.put("g", 7);
            return map;
        }

        @Test
        void testIncludesTheKeyEqualToFrom() {
            assertEquals(Map.of("c", 3, "e", 5, "g", 7), TreeMapChallenge.entriesFrom(map(), "c"));
        }

        @Test
        void testFromThatIsNotAKey() {
            assertEquals(Map.of("e", 5, "g", 7), TreeMapChallenge.entriesFrom(map(), "d"));
        }

        @Test
        void testFromBelowEveryKey() {
            assertEquals(map(), TreeMapChallenge.entriesFrom(map(), "A"));
        }

        @Test
        void testFromAboveEveryKey() {
            assertEquals(Map.of(), TreeMapChallenge.entriesFrom(map(), "z"));
        }

        @Test
        void testEmptyMap() {
            assertEquals(Map.of(), TreeMapChallenge.entriesFrom(new TreeMap<>(), "a"));
        }

        @Test
        void testTheResultIsSorted() {
            assertEquals(List.of("c", "e", "g"), new ArrayList<>(TreeMapChallenge.entriesFrom(map(), "b").keySet()));
        }

        @Test
        void testChangingTheResultDoesNotChangeTheOriginal() {
            TreeMap<String, Integer> original = map();

            TreeMap<String, Integer> result = TreeMapChallenge.entriesFrom(original, "c");
            result.put("x", 9);
            result.remove("c");
            result.put("e", 100);

            assertEquals(map(), original);
        }

        @Test
        void testChangingTheOriginalDoesNotChangeTheResult() {
            TreeMap<String, Integer> original = map();

            TreeMap<String, Integer> result = TreeMapChallenge.entriesFrom(original, "c");
            original.put("d", 4);
            original.remove("g");
            original.put("e", 100);

            assertEquals(Map.of("c", 3, "e", 5, "g", 7), result);
        }
    }
}