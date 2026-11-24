package dev.perfectbogus.hashmap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashMapEasyChallengeTest {

    private static Map<String, Integer> map(Object... keysAndValues) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            result.put((String) keysAndValues[i], (Integer) keysAndValues[i + 1]);
        }
        return result;
    }

    private static Map<Character, Integer> charMap(Object... keysAndValues) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            result.put((Character) keysAndValues[i], (Integer) keysAndValues[i + 1]);
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: countWords
    // ==========================================================
    @Nested
    class CountWordsTests {

        @Test
        void testRepeatedWords() {
            assertEquals(map("a", 2, "b", 1), HashMapEasyChallenge.countWords(new String[] {"a", "b", "a"}));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(map("A", 1, "a", 1), HashMapEasyChallenge.countWords(new String[] {"A", "a"}));
        }

        @Test
        void testEmpty() {
            assertEquals(map(), HashMapEasyChallenge.countWords(new String[] {}));
        }

        @Test
        void testOneWord() {
            assertEquals(map("hello", 1), HashMapEasyChallenge.countWords(new String[] {"hello"}));
        }

        @Test
        void testAllTheSame() {
            assertEquals(map("x", 4), HashMapEasyChallenge.countWords(new String[] {"x", "x", "x", "x"}));
        }

        @Test
        void testAllDifferent() {
            assertEquals(map("a", 1, "b", 1, "c", 1), HashMapEasyChallenge.countWords(new String[] {"c", "a", "b"}));
        }

        @Test
        void testRepeatedWordsNotNextToEachOther() {
            assertEquals(map("a", 3, "b", 2),
                    HashMapEasyChallenge.countWords(new String[] {"a", "b", "a", "b", "a"}));
        }

        @Test
        void testEmptyWord() {
            assertEquals(map("", 2, "a", 1), HashMapEasyChallenge.countWords(new String[] {"", "a", ""}));
        }

        @Test
        void testWordsWithSpaces() {
            assertEquals(map("a b", 2, "ab", 1), HashMapEasyChallenge.countWords(new String[] {"a b", "ab", "a b"}));
        }

        @Test
        void testWordsEqualByContentNotByReference() {
            Map<String, Integer> result = HashMapEasyChallenge.countWords(
                    new String[] {new String("hello"), new String("hello")});
            assertEquals(map("hello", 2), result);
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"b", "a", "b"};
            HashMapEasyChallenge.countWords(words);
            assertArrayEquals(new String[] {"b", "a", "b"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "w" + i % 100;
            }
            Map<String, Integer> result = HashMapEasyChallenge.countWords(words);
            assertEquals(100, result.size());
            assertEquals(1000, result.get("w0"));
            assertEquals(1000, result.get("w99"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: countLetters
    // ==========================================================
    @Nested
    class CountLettersTests {

        @Test
        void testHello() {
            assertEquals(charMap('h', 1, 'e', 1, 'l', 2, 'o', 1), HashMapEasyChallenge.countLetters("hello"));
        }

        @Test
        void testSpacesCount() {
            assertEquals(charMap('a', 2, ' ', 1), HashMapEasyChallenge.countLetters("a a"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(charMap('a', 1, 'A', 1), HashMapEasyChallenge.countLetters("aA"));
        }

        @Test
        void testEmpty() {
            assertEquals(charMap(), HashMapEasyChallenge.countLetters(""));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals(charMap('1', 2, '!', 1), HashMapEasyChallenge.countLetters("1!1"));
        }

        @Test
        void testOneCharacter() {
            assertEquals(charMap('z', 1), HashMapEasyChallenge.countLetters("z"));
        }

        @Test
        void testAllTheSame() {
            assertEquals(charMap('x', 5), HashMapEasyChallenge.countLetters("xxxxx"));
        }

        @Test
        void testAllDifferent() {
            assertEquals(charMap('a', 1, 'b', 1, 'c', 1), HashMapEasyChallenge.countLetters("cab"));
        }

        @Test
        void testRepeatedNotNextToEachOther() {
            assertEquals(charMap('a', 3, 'b', 2), HashMapEasyChallenge.countLetters("ababa"));
        }

        @Test
        void testCharactersBiggerThanTheCacheOfCharacters() {
            assertEquals(charMap('é', 2, 'ñ', 1), HashMapEasyChallenge.countLetters("éñé"));
        }

        @Test
        void testSizeOfTheResult() {
            assertEquals(4, HashMapEasyChallenge.countLetters("hello").size());
        }

        @Test
        void testBigText() {
            Map<Character, Integer> result = HashMapEasyChallenge.countLetters("abc".repeat(333_333) + "a");
            assertEquals(charMap('a', 333_334, 'b', 333_333, 'c', 333_333), result);
        }
    }

    // ==========================================================
    // CHALLENGE 3: mostFrequent
    // ==========================================================
    @Nested
    class MostFrequentTests {

        @Test
        void testClearWinner() {
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"a", "b", "a"}));
        }

        @Test
        void testTieIsAlphabetical() {
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"b", "a"}));
            assertEquals("x", HashMapEasyChallenge.mostFrequent(new String[] {"x", "y", "y", "x"}));
            assertEquals("x", HashMapEasyChallenge.mostFrequent(new String[] {"y", "x", "x", "y"}));
        }

        @Test
        void testOneWord() {
            assertEquals("hello", HashMapEasyChallenge.mostFrequent(new String[] {"hello"}));
        }

        @Test
        void testWinnerIsNotTheFirstAlphabetically() {
            assertEquals("z", HashMapEasyChallenge.mostFrequent(new String[] {"a", "z", "z"}));
            assertEquals("y", HashMapEasyChallenge.mostFrequent(new String[] {"z", "y", "y"}));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals("B", HashMapEasyChallenge.mostFrequent(new String[] {"a", "B"}));
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"B", "a", "a"}));
        }

        @Test
        void testAllDifferent() {
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"c", "a", "b"}));
        }

        @Test
        void testWinnerIsTheLastWord() {
            assertEquals("m", HashMapEasyChallenge.mostFrequent(new String[] {"a", "b", "m", "m"}));
        }

        @Test
        void testThreeWayTie() {
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"c", "b", "a", "c", "b", "a"}));
        }

        @Test
        void testLongerWordAfterItsPrefix() {
            assertEquals("a", HashMapEasyChallenge.mostFrequent(new String[] {"ab", "a"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"b", "a", "b"};
            HashMapEasyChallenge.mostFrequent(words);
            assertArrayEquals(new String[] {"b", "a", "b"}, words);
        }

        @Test
        void testBigArrayWithAWinner() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = i % 5 == 0 ? "q" : "w" + i;
            }
            assertEquals("q", HashMapEasyChallenge.mostFrequent(words));
        }

        @Test
        void testBigArrayWithATie() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "w" + i % 1000;
            }
            assertEquals("w0", HashMapEasyChallenge.mostFrequent(words));
        }
    }

    // ==========================================================
    // CHALLENGE 4: mergeMaps
    // ==========================================================
    @Nested
    class MergeMapsTests {

        @Test
        void testSharedKey() {
            assertEquals(map("a", 1, "b", 5, "c", 4),
                    HashMapEasyChallenge.mergeMaps(map("a", 1, "b", 2), map("b", 3, "c", 4)));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(map("x", 1), HashMapEasyChallenge.mergeMaps(map(), map("x", 1)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(map("x", 1), HashMapEasyChallenge.mergeMaps(map("x", 1), map()));
        }

        @Test
        void testBothEmpty() {
            assertEquals(map(), HashMapEasyChallenge.mergeMaps(map(), map()));
        }

        @Test
        void testNoSharedKeys() {
            assertEquals(map("a", 1, "b", 2), HashMapEasyChallenge.mergeMaps(map("a", 1), map("b", 2)));
        }

        @Test
        void testAllKeysShared() {
            assertEquals(map("a", 11, "b", 22), HashMapEasyChallenge.mergeMaps(map("a", 1, "b", 2),
                    map("a", 10, "b", 20)));
        }

        @Test
        void testNegativeValues() {
            assertEquals(map("a", -2, "b", 0), HashMapEasyChallenge.mergeMaps(map("a", -5, "b", 2),
                    map("a", 3, "b", -2)));
        }

        @Test
        void testZeroValuesKeepTheKey() {
            assertEquals(map("a", 0, "b", 0), HashMapEasyChallenge.mergeMaps(map("a", 0), map("b", 0)));
        }

        @Test
        void testTheOrderOfTheArgumentsDoesNotMatter() {
            assertEquals(HashMapEasyChallenge.mergeMaps(map("a", 1, "b", 2), map("b", 3, "c", 4)),
                    HashMapEasyChallenge.mergeMaps(map("b", 3, "c", 4), map("a", 1, "b", 2)));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(map("a", 2000), HashMapEasyChallenge.mergeMaps(map("a", 1000), map("a", 1000)));
        }

        @Test
        void testOriginalsAreNotModified() {
            Map<String, Integer> first = map("a", 1, "b", 2);
            Map<String, Integer> second = map("b", 3, "c", 4);
            HashMapEasyChallenge.mergeMaps(first, second);
            assertEquals(map("a", 1, "b", 2), first);
            assertEquals(map("b", 3, "c", 4), second);
        }

        @Test
        void testBigMaps() {
            Map<String, Integer> first = new HashMap<>();
            Map<String, Integer> second = new HashMap<>();
            for (int i = 0; i < 100_000; i++) {
                first.put("k" + i, 1);
                second.put("k" + (i + 50_000), 2);
            }
            Map<String, Integer> result = HashMapEasyChallenge.mergeMaps(first, second);
            assertEquals(150_000, result.size());
            assertEquals(1, result.get("k0"));
            assertEquals(3, result.get("k50000"));
            assertEquals(2, result.get("k149999"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: invert
    // ==========================================================
    @Nested
    class InvertTests {

        private Map<Integer, String> intMap(Object... keysAndValues) {
            Map<Integer, String> result = new HashMap<>();
            for (int i = 0; i < keysAndValues.length; i += 2) {
                result.put((Integer) keysAndValues[i], (String) keysAndValues[i + 1]);
            }
            return result;
        }

        @Test
        void testTwoEntries() {
            assertEquals(intMap(1, "a", 2, "b"), HashMapEasyChallenge.invert(map("a", 1, "b", 2)));
        }

        @Test
        void testOneEntry() {
            assertEquals(intMap(0, "x"), HashMapEasyChallenge.invert(map("x", 0)));
        }

        @Test
        void testEmpty() {
            assertEquals(intMap(), HashMapEasyChallenge.invert(map()));
        }

        @Test
        void testNegativeValues() {
            assertEquals(intMap(-1, "a", 5, "b"), HashMapEasyChallenge.invert(map("a", -1, "b", 5)));
        }

        @Test
        void testManyEntries() {
            assertEquals(intMap(1, "a", 2, "b", 3, "c", 4, "d"),
                    HashMapEasyChallenge.invert(map("a", 1, "b", 2, "c", 3, "d", 4)));
        }

        @Test
        void testTheCaseOfTheKeysIsKept() {
            assertEquals(intMap(1, "A", 2, "a"), HashMapEasyChallenge.invert(map("A", 1, "a", 2)));
        }

        @Test
        void testSizeOfTheResult() {
            assertEquals(3, HashMapEasyChallenge.invert(map("a", 1, "b", 2, "c", 3)).size());
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            Map<Integer, String> result = HashMapEasyChallenge.invert(map("a", 1000, "b", 100_000));
            assertEquals("a", result.get(1000));
            assertEquals("b", result.get(100_000));
        }

        @Test
        void testEmptyKey() {
            assertEquals(intMap(7, ""), HashMapEasyChallenge.invert(map("", 7)));
        }

        @Test
        void testOriginalIsNotModified() {
            Map<String, Integer> original = map("a", 1, "b", 2);
            HashMapEasyChallenge.invert(original);
            assertEquals(map("a", 1, "b", 2), original);
        }

        @Test
        void testBigMap() {
            Map<String, Integer> original = new HashMap<>();
            for (int i = 0; i < 100_000; i++) {
                original.put("k" + i, i);
            }
            Map<Integer, String> result = HashMapEasyChallenge.invert(original);
            assertEquals(100_000, result.size());
            assertEquals("k5", result.get(5));
            assertEquals("k99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 6: areAnagrams
    // ==========================================================
    @Nested
    class AreAnagramsTests {

        @Test
        void testAnagrams() {
            assertTrue(HashMapEasyChallenge.areAnagrams("listen", "silent"));
        }

        @Test
        void testAnagramsSimplified() {
            assertTrue(HashMapEasyChallenge.areAnagramsSimplified("listen", "silent"));
        }

        @Test
        void testDifferentLettersSimplified() {
            assertFalse(HashMapEasyChallenge.areAnagramsSimplified("abc", "abd"));
        }

        @Test
        void testDifferentLetters() {
            assertFalse(HashMapEasyChallenge.areAnagrams("abc", "abd"));
        }

        @Test
        void testSameLettersDifferentCounts() {
            assertFalse(HashMapEasyChallenge.areAnagrams("aab", "abb"));
        }

        @Test
        void testEmptyTexts() {
            assertTrue(HashMapEasyChallenge.areAnagrams("", ""));
        }

        @Test
        void testDifferentLengths() {
            assertFalse(HashMapEasyChallenge.areAnagrams("a", "aa"));
            assertFalse(HashMapEasyChallenge.areAnagrams("ab", "a"));
            assertFalse(HashMapEasyChallenge.areAnagrams("", "a"));
        }

        @Test
        void testSameText() {
            assertTrue(HashMapEasyChallenge.areAnagrams("abc", "abc"));
        }

        @Test
        void testOneLetter() {
            assertTrue(HashMapEasyChallenge.areAnagrams("a", "a"));
            assertFalse(HashMapEasyChallenge.areAnagrams("a", "b"));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(HashMapEasyChallenge.areAnagrams("Ab", "ab"));
            assertTrue(HashMapEasyChallenge.areAnagrams("Ab", "bA"));
        }

        @Test
        void testRepeatedLetters() {
            assertTrue(HashMapEasyChallenge.areAnagrams("aabbcc", "cbcbaa"));
            assertFalse(HashMapEasyChallenge.areAnagrams("aabbcc", "aabbcd"));
        }

        @Test
        void testTheLettersOfTheSecondAreAlsoChecked() {
            assertFalse(HashMapEasyChallenge.areAnagrams("aabb", "aacc"));
            assertFalse(HashMapEasyChallenge.areAnagrams("aacc", "aabb"));
        }

        @Test
        void testSpacesAndSymbols() {
            assertTrue(HashMapEasyChallenge.areAnagrams("a b!", "!b a"));
            assertFalse(HashMapEasyChallenge.areAnagrams("a b", "ab"));
        }

        @Test
        void testBigTexts() {
            String first = "ab".repeat(250_000);
            String second = "b".repeat(250_000) + "a".repeat(250_000);
            assertTrue(HashMapEasyChallenge.areAnagrams(first, second));
            assertFalse(HashMapEasyChallenge.areAnagrams(first, second.substring(1) + "c"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: twoSum
    // ==========================================================
    @Nested
    class TwoSumTests {

        @Test
        void testFirstTwoValues() {
            assertArrayEquals(new int[] {0, 1}, HashMapEasyChallenge.twoSum(new int[] {2, 7, 11, 15}, 9));
        }

        @Test
        void testPairInTheMiddle() {
            assertArrayEquals(new int[] {1, 2}, HashMapEasyChallenge.twoSum(new int[] {3, 2, 4}, 6));
        }

        @Test
        void testRepeatedValues() {
            assertArrayEquals(new int[] {0, 1}, HashMapEasyChallenge.twoSum(new int[] {3, 3}, 6));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[] {2, 4}, HashMapEasyChallenge.twoSum(new int[] {-1, -2, -3, -4, -5}, -8));
        }

        @Test
        void testTheSmallerPositionComesFirst() {
            assertArrayEquals(new int[] {0, 2}, HashMapEasyChallenge.twoSum(new int[] {4, 3, 2}, 6));
            assertArrayEquals(new int[] {1, 3}, HashMapEasyChallenge.twoSum(new int[] {10, 5, 20, 15}, 20));
        }

        @Test
        void testTargetZero() {
            assertArrayEquals(new int[] {0, 2}, HashMapEasyChallenge.twoSum(new int[] {-3, 8, 3}, 0));
        }

        @Test
        void testZeros() {
            assertArrayEquals(new int[] {0, 3}, HashMapEasyChallenge.twoSum(new int[] {0, 4, 3, 0}, 0));
        }

        @Test
        void testTheSameValueIsNotUsedTwice() {
            assertArrayEquals(new int[] {1, 2}, HashMapEasyChallenge.twoSum(new int[] {3, 2, 4}, 6));
            assertArrayEquals(new int[] {0, 2}, HashMapEasyChallenge.twoSum(new int[] {5, 1, 5}, 10));
        }

        @Test
        void testPairAtTheEnd() {
            assertArrayEquals(new int[] {3, 4}, HashMapEasyChallenge.twoSum(new int[] {1, 2, 3, 40, 60}, 100));
        }

        @Test
        void testBigValues() {
            assertArrayEquals(new int[] {0, 1}, HashMapEasyChallenge.twoSum(
                    new int[] {1_000_000_000, 1_000_000_000, 5}, 2_000_000_000));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {2, 7, 11, 15};
            HashMapEasyChallenge.twoSum(nums, 9);
            assertArrayEquals(new int[] {2, 7, 11, 15}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[300_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            assertArrayEquals(new int[] {299_998, 299_999}, HashMapEasyChallenge.twoSum(nums, 599_997));
        }
    }

    // ==========================================================
    // CHALLENGE 8: groupByLength
    // ==========================================================
    @Nested
    class GroupByLengthTests {

        private Map<Integer, List<String>> groups(Object... keysAndLists) {
            Map<Integer, List<String>> result = new HashMap<>();
            for (int i = 0; i < keysAndLists.length; i += 2) {
                result.put((Integer) keysAndLists[i], new ArrayList<>(Arrays.asList((String[]) keysAndLists[i + 1])));
            }
            return result;
        }

        @Test
        void testTwoGroups() {
            assertEquals(groups(1, new String[] {"a", "c"}, 2, new String[] {"bb", "dd"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("a", "bb", "c", "dd"))));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(groups(1, new String[] {"x", "x"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("x", "x"))));
        }

        @Test
        void testEmpty() {
            assertEquals(groups(), HashMapEasyChallenge.groupByLength(new ArrayList<>()));
        }

        @Test
        void testOrderInsideTheGroup() {
            assertEquals(groups(2, new String[] {"zz", "aa", "mm"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("zz", "aa", "mm"))));
        }

        @Test
        void testEmptyWord() {
            assertEquals(groups(0, new String[] {""}, 1, new String[] {"a"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("", "a"))));
        }

        @Test
        void testManyLengths() {
            assertEquals(groups(1, new String[] {"a"}, 2, new String[] {"bb"}, 3, new String[] {"ccc"},
                            4, new String[] {"dddd"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("dddd", "ccc", "bb", "a"))));
        }

        @Test
        void testGroupsAreNotMixed() {
            Map<Integer, List<String>> result = HashMapEasyChallenge.groupByLength(
                    new ArrayList<>(Arrays.asList("a", "bb", "c", "dd", "e")));
            assertEquals(List.of("a", "c", "e"), result.get(1));
            assertEquals(List.of("bb", "dd"), result.get(2));
            assertEquals(2, result.size());
        }

        @Test
        void testOneWord() {
            assertEquals(groups(5, new String[] {"hello"}),
                    HashMapEasyChallenge.groupByLength(new ArrayList<>(Arrays.asList("hello"))));
        }

        @Test
        void testSeparateListsPerGroup() {
            Map<Integer, List<String>> result = HashMapEasyChallenge.groupByLength(
                    new ArrayList<>(Arrays.asList("a", "bb")));
            assertEquals(1, result.get(1).size());
            assertEquals(1, result.get(2).size());
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = new ArrayList<>(Arrays.asList("a", "bb", "c", "dd"));
            HashMapEasyChallenge.groupByLength(original);
            assertEquals(List.of("a", "bb", "c", "dd"), original);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("w" + i);
            }
            Map<Integer, List<String>> result = HashMapEasyChallenge.groupByLength(words);
            assertEquals(5, result.size());
            assertEquals(10, result.get(2).size());
            assertEquals("w0", result.get(2).get(0));
            assertEquals(90_000, result.get(6).size());
            assertEquals("w10000", result.get(6).get(0));
            assertEquals("w99999", result.get(6).get(89_999));
        }
    }

    // ==========================================================
    // CHALLENGE 9: firstUniqueIndex
    // ==========================================================
    @Nested
    class FirstUniqueIndexTests {

        @Test
        void testFirstCharacterIsUnique() {
            assertEquals(0, HashMapEasyChallenge.firstUniqueIndex("leetcode"));
        }

        @Test
        void testUniqueInTheMiddle() {
            assertEquals(2, HashMapEasyChallenge.firstUniqueIndex("loveleetcode"));
        }

        @Test
        void testNoUniqueCharacter() {
            assertEquals(-1, HashMapEasyChallenge.firstUniqueIndex("aabb"));
        }

        @Test
        void testEmpty() {
            assertEquals(-1, HashMapEasyChallenge.firstUniqueIndex(""));
        }

        @Test
        void testOneCharacter() {
            assertEquals(0, HashMapEasyChallenge.firstUniqueIndex("a"));
        }

        @Test
        void testUniqueIsTheLastCharacter() {
            assertEquals(6, HashMapEasyChallenge.firstUniqueIndex("abcabcd"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(0, HashMapEasyChallenge.firstUniqueIndex("aA"));
            assertEquals(2, HashMapEasyChallenge.firstUniqueIndex("aaB"));
        }

        @Test
        void testRepeatedNotNextToEachOther() {
            assertEquals(-1, HashMapEasyChallenge.firstUniqueIndex("abab"));
        }

        @Test
        void testSpacesAndDigits() {
            assertEquals(1, HashMapEasyChallenge.firstUniqueIndex("1 1a"));
        }

        @Test
        void testSeveralUniqueCharacters() {
            assertEquals(2, HashMapEasyChallenge.firstUniqueIndex("aabcd"));
        }

        @Test
        void testCharacterThatAppearsThreeTimes() {
            assertEquals(3, HashMapEasyChallenge.firstUniqueIndex("aaab"));
        }

        @Test
        void testBigText() {
            String repeated = "ab".repeat(100_000);
            assertEquals(-1, HashMapEasyChallenge.firstUniqueIndex(repeated));
            assertEquals(200_000, HashMapEasyChallenge.firstUniqueIndex(repeated + "c"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: removeBelow
    // ==========================================================
    @Nested
    class RemoveBelowTests {

        @Test
        void testSomeEntriesAreRemoved() {
            assertEquals(map("b", 5, "c", 3), HashMapEasyChallenge.removeBelow(map("a", 1, "b", 5, "c", 3), 3));
        }

        @Test
        void testEverythingIsRemoved() {
            assertEquals(map(), HashMapEasyChallenge.removeBelow(map("a", 1), 2));
        }

        @Test
        void testSomeEntriesAreRemovedSimplified() {
            assertEquals(map("b", 5, "c", 3), HashMapEasyChallenge.removeBelowSimplified(map("a", 1, "b", 5, "c", 3), 3));
        }

        @Test
        void testEverythingIsRemovedSimplified() {
            assertEquals(map(), HashMapEasyChallenge.removeBelowSimplified(map("a", 1), 2));
        }

        @Test
        void testValueEqualToMinIsKept() {
            assertEquals(map("a", 2), HashMapEasyChallenge.removeBelow(map("a", 2), 2));
        }

        @Test
        void testEmpty() {
            assertEquals(map(), HashMapEasyChallenge.removeBelow(map(), 0));
        }

        @Test
        void testNothingIsRemoved() {
            assertEquals(map("a", 5, "b", 6), HashMapEasyChallenge.removeBelow(map("a", 5, "b", 6), 1));
        }

        @Test
        void testNegativeMin() {
            assertEquals(map("a", -1, "b", 0), HashMapEasyChallenge.removeBelow(map("a", -1, "b", 0, "c", -5), -1));
        }

        @Test
        void testZero() {
            assertEquals(map("b", 0, "c", 1), HashMapEasyChallenge.removeBelow(map("a", -1, "b", 0, "c", 1), 0));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(map("a", 1000), HashMapEasyChallenge.removeBelow(map("a", 1000, "b", 999), 1000));
            assertEquals(map("a", 100_000), HashMapEasyChallenge.removeBelow(map("a", 100_000, "b", 5), 100_000));
        }

        @Test
        void testKeysAreKept() {
            assertEquals(map("A", 3, "a", 4), HashMapEasyChallenge.removeBelow(map("A", 3, "a", 4, "b", 1), 3));
        }

        @Test
        void testResultIsANewMap() {
            Map<String, Integer> original = map("a", 5);
            assertTrue(HashMapEasyChallenge.removeBelow(original, 1) != original);
        }

        @Test
        void testOriginalIsNotModified() {
            Map<String, Integer> original = map("a", 1, "b", 5, "c", 3);
            HashMapEasyChallenge.removeBelow(original, 3);
            assertEquals(map("a", 1, "b", 5, "c", 3), original);
        }

        @Test
        void testBigMap() {
            Map<String, Integer> original = new HashMap<>();
            for (int i = 0; i < 100_000; i++) {
                original.put("k" + i, i);
            }
            Map<String, Integer> result = HashMapEasyChallenge.removeBelow(original, 50_000);
            assertEquals(50_000, result.size());
            assertEquals(50_000, result.get("k50000"));
            assertNull(result.get("k49999"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: totalsByName
    // ==========================================================
    @Nested
    class TotalsByNameTests {

        @Test
        void testRepeatedName() {
            assertEquals(map("ana", 17, "bob", 5),
                    HashMapEasyChallenge.totalsByName(new String[] {"ana:10", "bob:5", "ana:7"}));
        }

        @Test
        void testZeroAmount() {
            assertEquals(map("x", 0), HashMapEasyChallenge.totalsByName(new String[] {"x:0"}));
        }

        @Test
        void testRepeatedNameExtended() {
            assertEquals(map("ana", 17, "bob", 5),
                    HashMapEasyChallenge.totalsByNameExtended(new String[] {"ana:10", "bob:5", "ana:7"}));
        }

        @Test
        void testZeroAmountExtended() {
            assertEquals(map("x", 0), HashMapEasyChallenge.totalsByNameExtended(new String[] {"x:0"}));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(map("a", 1, "A", 2), HashMapEasyChallenge.totalsByName(new String[] {"a:1", "A:2"}));
        }

        @Test
        void testEmpty() {
            assertEquals(map(), HashMapEasyChallenge.totalsByName(new String[] {}));
        }

        @Test
        void testOneEntry() {
            assertEquals(map("ana", 10), HashMapEasyChallenge.totalsByName(new String[] {"ana:10"}));
        }

        @Test
        void testAmountsWithSeveralDigits() {
            assertEquals(map("a", 1_000_100), HashMapEasyChallenge.totalsByName(new String[] {"a:1000000", "a:100"}));
        }

        @Test
        void testNamesWithSpaces() {
            assertEquals(map("ana maria", 15, "ana", 1),
                    HashMapEasyChallenge.totalsByName(new String[] {"ana maria:5", "ana:1", "ana maria:10"}));
        }

        @Test
        void testNamesWithDigits() {
            assertEquals(map("a1", 3, "a10", 4), HashMapEasyChallenge.totalsByName(new String[] {"a1:3", "a10:4"}));
        }

        @Test
        void testSameNameManyTimes() {
            assertEquals(map("x", 10), HashMapEasyChallenge.totalsByName(
                    new String[] {"x:1", "x:2", "x:3", "x:4"}));
        }

        @Test
        void testEmptyName() {
            assertEquals(map("", 7), HashMapEasyChallenge.totalsByName(new String[] {":3", ":4"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] entries = {"ana:10", "bob:5", "ana:7"};
            HashMapEasyChallenge.totalsByName(entries);
            assertArrayEquals(new String[] {"ana:10", "bob:5", "ana:7"}, entries);
        }

        @Test
        void testBigArray() {
            String[] entries = new String[100_000];
            for (int i = 0; i < entries.length; i++) {
                entries[i] = "n" + i % 10 + ":1000";
            }
            Map<String, Integer> result = HashMapEasyChallenge.totalsByName(entries);
            assertEquals(10, result.size());
            assertEquals(10_000_000, result.get("n0"));
            assertEquals(10_000_000, result.get("n9"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: canBuildNote
    // ==========================================================
    @Nested
    class CanBuildNoteTests {

        @Test
        void testMissingCharacter() {
            assertFalse(HashMapEasyChallenge.canBuildNote("a", "b"));
        }

        @Test
        void testRepeatedCharacterIsAvailable() {
            assertTrue(HashMapEasyChallenge.canBuildNote("aa", "aab"));
        }

        @Test
        void testRepeatedCharacterIsNotEnough() {
            assertFalse(HashMapEasyChallenge.canBuildNote("aa", "ab"));
        }

        @Test
        void testEmptyTexts() {
            assertTrue(HashMapEasyChallenge.canBuildNote("", ""));
        }

        @Test
        void testEmptyNote() {
            assertTrue(HashMapEasyChallenge.canBuildNote("", "abc"));
        }

        @Test
        void testEmptyMagazine() {
            assertFalse(HashMapEasyChallenge.canBuildNote("a", ""));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(HashMapEasyChallenge.canBuildNote("A", "a"));
            assertTrue(HashMapEasyChallenge.canBuildNote("Aa", "aA"));
        }

        @Test
        void testSameText() {
            assertTrue(HashMapEasyChallenge.canBuildNote("hello", "hello"));
        }

        @Test
        void testMagazineInAnyOrder() {
            assertTrue(HashMapEasyChallenge.canBuildNote("abc", "cbaxyz"));
        }

        @Test
        void testNoteLongerThanTheMagazine() {
            assertFalse(HashMapEasyChallenge.canBuildNote("aaaa", "aaa"));
        }

        @Test
        void testSpacesCount() {
            assertTrue(HashMapEasyChallenge.canBuildNote("a b", "b a"));
            assertFalse(HashMapEasyChallenge.canBuildNote("a b", "ab"));
        }

        @Test
        void testOnlyTheLastCharacterIsMissing() {
            assertFalse(HashMapEasyChallenge.canBuildNote("abcz", "abcdefg"));
        }

        @Test
        void testBigTexts() {
            assertTrue(HashMapEasyChallenge.canBuildNote("a".repeat(200_000), "a".repeat(200_000)));
            assertFalse(HashMapEasyChallenge.canBuildNote("a".repeat(200_000), "a".repeat(199_999)));
        }
    }

    // ==========================================================
    // CHALLENGE 13: updateStock
    // ==========================================================
    @Nested
    class UpdateStockTests {

        @Test
        void testSeveralSales() {
            assertEquals(map("apple", 1), HashMapEasyChallenge.updateStock(map("apple", 3, "pear", 1),
                    new String[] {"apple", "pear", "apple"}));
        }

        @Test
        void testNoSales() {
            assertEquals(map("a", 1), HashMapEasyChallenge.updateStock(map("a", 1), new String[] {}));
        }

        @Test
        void testSeveralSalesExtended() {
            assertEquals(map("apple", 1), HashMapEasyChallenge.updateStockExtended(map("apple", 3, "pear", 1),
                    new String[] {"apple", "pear", "apple"}));
        }

        @Test
        void testNoSalesExtended() {
            assertEquals(map("a", 1), HashMapEasyChallenge.updateStockExtended(map("a", 1), new String[] {}));
        }

        @Test
        void testOneSale() {
            assertEquals(map("a", 2, "b", 1),
                    HashMapEasyChallenge.updateStock(map("a", 2, "b", 2), new String[] {"b"}));
        }

        @Test
        void testItemIsRemovedAtZero() {
            assertEquals(map(), HashMapEasyChallenge.updateStock(map("a", 1), new String[] {"a"}));
        }

        @Test
        void testEmptyStock() {
            assertEquals(map(), HashMapEasyChallenge.updateStock(map(), new String[] {}));
        }

        @Test
        void testSameItemManyTimes() {
            assertEquals(map("a", 1),
                    HashMapEasyChallenge.updateStock(map("a", 5), new String[] {"a", "a", "a", "a"}));
        }

        @Test
        void testEverythingIsSold() {
            assertEquals(map(),
                    HashMapEasyChallenge.updateStock(map("a", 2, "b", 1), new String[] {"a", "b", "a"}));
        }

        @Test
        void testItemsNotSoldAreKept() {
            assertEquals(map("a", 3, "c", 7), HashMapEasyChallenge.updateStock(map("a", 3, "b", 1, "c", 7),
                    new String[] {"b"}));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(map("a", 1, "A", 2),
                    HashMapEasyChallenge.updateStock(map("a", 2, "A", 2), new String[] {"a"}));
        }

        @Test
        void testTheCaseMatchesTheSoldItem() {
            assertEquals(map("a", 3, "A", 1),
                    HashMapEasyChallenge.updateStock(map("a", 3, "A", 2), new String[] {"A"}));
        }

        @Test
        void testBigQuantities() {
            assertEquals(map("a", 999_999),
                    HashMapEasyChallenge.updateStock(map("a", 1_000_000), new String[] {"a"}));
        }

        @Test
        void testOriginalsAreNotModified() {
            Map<String, Integer> stock = map("apple", 3, "pear", 1);
            String[] sold = {"apple", "pear", "apple"};
            HashMapEasyChallenge.updateStock(stock, sold);
            assertEquals(map("apple", 3, "pear", 1), stock);
            assertArrayEquals(new String[] {"apple", "pear", "apple"}, sold);
        }

        @Test
        void testBigStock() {
            Map<String, Integer> stock = new HashMap<>();
            String[] sold = new String[75_000];
            for (int i = 0; i < 50_000; i++) {
                stock.put("item" + i, 2);
                sold[i] = "item" + i;
            }
            for (int i = 0; i < 25_000; i++) {
                sold[i + 50_000] = "item" + i;
            }
            Map<String, Integer> result = HashMapEasyChallenge.updateStock(stock, sold);
            assertEquals(25_000, result.size());
            assertEquals(1, result.get("item25000"));
            assertNull(result.get("item0"));
        }
    }

    // ==========================================================
    // CHALLENGE 14: firstIndexOfEachChar
    // ==========================================================
    @Nested
    class FirstIndexOfEachCharTests {

        @Test
        void testRepeatedCharacter() {
            assertEquals(charMap('a', 0, 'b', 1, 'c', 2), HashMapEasyChallenge.firstIndexOfEachChar("abca"));
        }

        @Test
        void testRepeatedAtTheStart() {
            assertEquals(charMap('a', 0, 'b', 2), HashMapEasyChallenge.firstIndexOfEachChar("aab"));
        }

        @Test
        void testAllTheSame() {
            assertEquals(charMap('z', 0), HashMapEasyChallenge.firstIndexOfEachChar("zz"));
        }

        @Test
        void testEmpty() {
            assertEquals(charMap(), HashMapEasyChallenge.firstIndexOfEachChar(""));
        }

        @Test
        void testOneCharacter() {
            assertEquals(charMap('q', 0), HashMapEasyChallenge.firstIndexOfEachChar("q"));
        }

        @Test
        void testAllDifferent() {
            assertEquals(charMap('c', 0, 'a', 1, 'b', 2), HashMapEasyChallenge.firstIndexOfEachChar("cab"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(charMap('a', 0, 'A', 1), HashMapEasyChallenge.firstIndexOfEachChar("aAaA"));
        }

        @Test
        void testSpacesAndSymbols() {
            assertEquals(charMap('a', 0, ' ', 1, '!', 3), HashMapEasyChallenge.firstIndexOfEachChar("a a!"));
        }

        @Test
        void testNewCharacterAtTheEnd() {
            assertEquals(charMap('a', 0, 'b', 4), HashMapEasyChallenge.firstIndexOfEachChar("aaaab"));
        }

        @Test
        void testNotTheLastIndex() {
            assertEquals(charMap('a', 0, 'b', 1), HashMapEasyChallenge.firstIndexOfEachChar("abbaab"));
        }

        @Test
        void testSizeOfTheResult() {
            assertEquals(3, HashMapEasyChallenge.firstIndexOfEachChar("abcabc").size());
        }

        @Test
        void testBigText() {
            assertEquals(charMap('a', 0, 'b', 500_000), HashMapEasyChallenge.firstIndexOfEachChar(
                    "a".repeat(500_000) + "b"));
            assertEquals(charMap('a', 0, 'b', 1), HashMapEasyChallenge.firstIndexOfEachChar("ab".repeat(300_000)));
        }
    }

    // ==========================================================
    // CHALLENGE 15: containsNearbyDuplicate
    // ==========================================================
    @Nested
    class ContainsNearbyDuplicateTests {

        @Test
        void testDuplicateAtTheLimit() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3));
        }

        @Test
        void testDuplicateOneStepAway() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 0, 1, 1}, 1));
        }

        @Test
        void testDuplicatesTooFar() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 2));
        }

        @Test
        void testKZero() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 1}, 0));
        }

        @Test
        void testEmpty() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {}, 5));
        }

        @Test
        void testOneValue() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {7}, 5));
        }

        @Test
        void testNoDuplicates() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 4}, 10));
        }

        @Test
        void testDistanceOneMoreThanK() {
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 2));
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3));
        }

        @Test
        void testTheNearestPreviousOccurrenceIsUsed() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1, 1}, 1));
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {5, 1, 9, 9, 1, 1}, 1));
        }

        @Test
        void testNegativeValuesAndZeros() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {-1, 0, -1}, 2));
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {0, 0}, 1));
        }

        @Test
        void testSeveralValuesRepeated() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 1, 2, 1}, 2));
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3, 1}, 2));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {100_000, 5, 100_000}, 2));
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(new int[] {100_000, 5, 100_000}, 1));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {1, 2, 3, 1};
            HashMapEasyChallenge.containsNearbyDuplicate(nums, 3);
            assertArrayEquals(new int[] {1, 2, 3, 1}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[300_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(nums, 300_000));
            nums[250_000] = 150_000;
            assertFalse(HashMapEasyChallenge.containsNearbyDuplicate(nums, 99_999));
            assertTrue(HashMapEasyChallenge.containsNearbyDuplicate(nums, 100_000));
        }
    }
}