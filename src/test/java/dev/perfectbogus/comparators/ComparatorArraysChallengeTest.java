package dev.perfectbogus.comparators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorArraysChallengeTest {

    private static String[] words(String... words) {
        return words;
    }

    private static ComparatorArraysChallenge.Person p(String name, int age) {
        return new ComparatorArraysChallenge.Person(name, age);
    }

    private static ComparatorArraysChallenge.Person[] people(ComparatorArraysChallenge.Person... people) {
        return people;
    }

    // ==========================================================
    // CHALLENGE 1: sortedByLength
    // ==========================================================
    @Nested
    class SortedByLengthTests {

        @Test
        void testSorts() {
            assertArrayEquals(words("a", "bb", "ccc"),
                    ComparatorArraysChallenge.sortedByLength(words("ccc", "a", "bb")));
        }

        @Test
        void testEqualLengthsKeepTheirOrder() {
            assertArrayEquals(words("c", "bb", "aa"),
                    ComparatorArraysChallenge.sortedByLength(words("bb", "aa", "c")));
        }

        @Test
        void testEqualLengthsKeepTheirOrderInTheOtherDirection() {
            assertArrayEquals(words("c", "aa", "bb"),
                    ComparatorArraysChallenge.sortedByLength(words("aa", "bb", "c")));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(words(), ComparatorArraysChallenge.sortedByLength(words()));
        }

        @Test
        void testEmptyTextsComeFirst() {
            assertArrayEquals(words("", "", "a"), ComparatorArraysChallenge.sortedByLength(words("a", "", "")));
        }

        @Test
        void testSingleWord() {
            assertArrayEquals(words("solo"), ComparatorArraysChallenge.sortedByLength(words("solo")));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(words("a", "bb", "ccc"),
                    ComparatorArraysChallenge.sortedByLength(words("a", "bb", "ccc")));
        }

        @Test
        void testLengthDoesNotMeanAlphabetical() {
            assertArrayEquals(words("z", "aaa"), ComparatorArraysChallenge.sortedByLength(words("aaa", "z")));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            String[] original = words("ccc", "a", "bb");
            ComparatorArraysChallenge.sortedByLength(original);
            assertArrayEquals(words("ccc", "a", "bb"), original);
        }

        @Test
        void testResultIsANewArray() {
            String[] original = words("a", "bb");
            assertNotSame(original, ComparatorArraysChallenge.sortedByLength(original));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortedIgnoringCase
    // ==========================================================
    @Nested
    class SortedIgnoringCaseTests {

        @Test
        void testSorts() {
            assertArrayEquals(words("A", "b", "c"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("b", "A", "c")));
        }

        @Test
        void testEqualWordsIgnoringCaseKeepTheirOrder() {
            assertArrayEquals(words("A", "a", "b", "B"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("b", "A", "a", "B")));
        }

        @Test
        void testEqualWordsIgnoringCaseKeepTheirOrderInTheOtherDirection() {
            assertArrayEquals(words("a", "A", "B", "b"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("B", "a", "A", "b")));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(words(), ComparatorArraysChallenge.sortedIgnoringCase(words()));
        }

        @Test
        void testUppercaseDoesNotComeFirst() {
            assertArrayEquals(words("apple", "Banana", "cherry"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("cherry", "Banana", "apple")));
        }

        @Test
        void testLongerWordsWithTheSamePrefix() {
            assertArrayEquals(words("ab", "ABC", "abd"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("abd", "ABC", "ab")));
        }

        @Test
        void testOriginalCaseIsKept() {
            assertArrayEquals(words("HELLO", "World"),
                    ComparatorArraysChallenge.sortedIgnoringCase(words("World", "HELLO")));
        }

        @Test
        void testSingleWord() {
            assertArrayEquals(words("Zed"), ComparatorArraysChallenge.sortedIgnoringCase(words("Zed")));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            String[] original = words("b", "A", "c");
            ComparatorArraysChallenge.sortedIgnoringCase(original);
            assertArrayEquals(words("b", "A", "c"), original);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortedByLastCharacter
    // ==========================================================
    @Nested
    class SortedByLastCharacterTests {

        @Test
        void testSorts() {
            assertArrayEquals(words("ba", "ab", "cb"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("ab", "ba", "cb")));
        }

        @Test
        void testSameLastCharacterAlphabetical() {
            assertArrayEquals(words("aa", "ba", "zz"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("zz", "ba", "aa")));
            assertArrayEquals(words("aa", "ab", "zz"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("zz", "aa", "ab")));
        }

        @Test
        void testTieIsDecidedWithTheNormalComparisonSoUppercaseGoesFirst() {
            assertArrayEquals(words("Ba", "ba"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("ba", "Ba")));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(words(), ComparatorArraysChallenge.sortedByLastCharacter(words()));
        }

        @Test
        void testSingleWord() {
            assertArrayEquals(words("x"), ComparatorArraysChallenge.sortedByLastCharacter(words("x")));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            assertArrayEquals(words("aB", "ba"), ComparatorArraysChallenge.sortedByLastCharacter(words("ba", "aB")));
        }

        @Test
        void testOneCharacterWords() {
            assertArrayEquals(words("a", "b", "c"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("c", "a", "b")));
        }

        @Test
        void testTheTieIsDecidedByTheWholeWordNotByLength() {
            assertArrayEquals(words("aaaa", "ba"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("ba", "aaaa")));
        }

        @Test
        void testFirstCharacterIsNotUsedFirst() {
            assertArrayEquals(words("zb", "aac"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("aac", "zb")));
        }

        @Test
        void testRepeatedWords() {
            assertArrayEquals(words("ab", "ab", "ac"),
                    ComparatorArraysChallenge.sortedByLastCharacter(words("ac", "ab", "ab")));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            String[] original = words("ab", "ba");
            ComparatorArraysChallenge.sortedByLastCharacter(original);
            assertArrayEquals(words("ab", "ba"), original);
        }
    }

    // ==========================================================
    // CHALLENGE 4: longestFirstThenAlphabetical
    // ==========================================================
    @Nested
    class LongestFirstThenAlphabeticalTests {

        @Test
        void testSorts() {
            assertArrayEquals(words("ccc", "aa", "bb", "a"),
                    ComparatorArraysChallenge.longestFirstThenAlphabetical(words("bb", "a", "ccc", "aa")));
        }

        @Test
        void testSameLengthAlphabetical() {
            assertArrayEquals(words("a", "b"), ComparatorArraysChallenge.longestFirstThenAlphabetical(words("b", "a")));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(words(), ComparatorArraysChallenge.longestFirstThenAlphabetical(words()));
        }

        @Test
        void testSingleWord() {
            assertArrayEquals(words("x"), ComparatorArraysChallenge.longestFirstThenAlphabetical(words("x")));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            assertArrayEquals(words("B", "a"), ComparatorArraysChallenge.longestFirstThenAlphabetical(words("a", "B")));
        }

        @Test
        void testLengthWinsOverAlphabeticalOrder() {
            assertArrayEquals(words("zzz", "a"),
                    ComparatorArraysChallenge.longestFirstThenAlphabetical(words("a", "zzz")));
        }

        @Test
        void testEmptyTextsComeLast() {
            assertArrayEquals(words("a", "", ""),
                    ComparatorArraysChallenge.longestFirstThenAlphabetical(words("", "a", "")));
        }

        @Test
        void testManyWords() {
            assertArrayEquals(words("pear", "plum", "fig", "kiw", "a"),
                    ComparatorArraysChallenge.longestFirstThenAlphabetical(words("a", "plum", "kiw", "pear", "fig")));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            String[] original = words("bb", "a", "ccc");
            ComparatorArraysChallenge.longestFirstThenAlphabetical(original);
            assertArrayEquals(words("bb", "a", "ccc"), original);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortedByAge
    // ==========================================================
    @Nested
    class SortedByAgeTests {

        @Test
        void testSorts() {
            assertArrayEquals(people(p("Bob", 20), p("Cy", 25), p("Ana", 30)),
                    ComparatorArraysChallenge.sortedByAge(people(p("Ana", 30), p("Bob", 20), p("Cy", 25))));
        }

        @Test
        void testEqualAgesKeepTheirOrder() {
            assertArrayEquals(people(p("Ana", 30), p("Bob", 30)),
                    ComparatorArraysChallenge.sortedByAge(people(p("Ana", 30), p("Bob", 30))));
        }

        @Test
        void testEqualAgesKeepTheirOrderInTheOtherDirection() {
            assertArrayEquals(people(p("Bob", 30), p("Ana", 30)),
                    ComparatorArraysChallenge.sortedByAge(people(p("Bob", 30), p("Ana", 30))));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(people(), ComparatorArraysChallenge.sortedByAge(people()));
        }

        @Test
        void testNegativeAges() {
            assertArrayEquals(people(p("A", -5), p("B", 0), p("C", 5)),
                    ComparatorArraysChallenge.sortedByAge(people(p("C", 5), p("A", -5), p("B", 0))));
        }

        @Test
        void testExtremeAgesDoNotOverflow() {
            assertArrayEquals(people(p("Min", Integer.MIN_VALUE), p("Max", Integer.MAX_VALUE)),
                    ComparatorArraysChallenge.sortedByAge(people(p("Max", Integer.MAX_VALUE),
                            p("Min", Integer.MIN_VALUE))));
        }

        @Test
        void testNameDoesNotMatter() {
            assertArrayEquals(people(p("Zed", 1), p("Abe", 2)),
                    ComparatorArraysChallenge.sortedByAge(people(p("Abe", 2), p("Zed", 1))));
        }

        @Test
        void testMixedWithRepeatedAges() {
            assertArrayEquals(people(p("D", 1), p("B", 2), p("C", 2), p("A", 3)),
                    ComparatorArraysChallenge.sortedByAge(people(p("A", 3), p("B", 2), p("C", 2), p("D", 1))));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            ComparatorArraysChallenge.Person[] original = people(p("Ana", 30), p("Bob", 20));
            ComparatorArraysChallenge.sortedByAge(original);
            assertArrayEquals(people(p("Ana", 30), p("Bob", 20)), original);
        }

        @Test
        void testResultIsANewArray() {
            ComparatorArraysChallenge.Person[] original = people(p("Ana", 30), p("Bob", 20));
            assertNotSame(original, ComparatorArraysChallenge.sortedByAge(original));
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortedByAgeDescThenName
    // ==========================================================
    @Nested
    class SortedByAgeDescThenNameTests {

        @Test
        void testSorts() {
            assertArrayEquals(people(p("Bob", 30), p("Cy", 30), p("Ana", 20)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("Ana", 20), p("Bob", 30), p("Cy", 30))));
        }

        @Test
        void testSameAgeOrderedByName() {
            assertArrayEquals(people(p("Bob", 30), p("Cy", 30)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("Cy", 30), p("Bob", 30))));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(people(), ComparatorArraysChallenge.sortedByAgeDescThenName(people()));
        }

        @Test
        void testNamesAreNotReversed() {
            assertArrayEquals(people(p("Ana", 5), p("Zed", 5)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("Zed", 5), p("Ana", 5))));
        }

        @Test
        void testAgeWinsOverName() {
            assertArrayEquals(people(p("Zed", 40), p("Ana", 10)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("Ana", 10), p("Zed", 40))));
        }

        @Test
        void testUppercaseNamesComeFirst() {
            assertArrayEquals(people(p("Bob", 7), p("amy", 7)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("amy", 7), p("Bob", 7))));
        }

        @Test
        void testNegativeAges() {
            assertArrayEquals(people(p("B", 0), p("A", -1), p("C", -9)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(people(p("C", -9), p("A", -1), p("B", 0))));
        }

        @Test
        void testExtremeAgesDoNotOverflow() {
            assertArrayEquals(people(p("Max", Integer.MAX_VALUE), p("Min", Integer.MIN_VALUE)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(
                            people(p("Min", Integer.MIN_VALUE), p("Max", Integer.MAX_VALUE))));
        }

        @Test
        void testManyPeople() {
            assertArrayEquals(people(p("B", 3), p("D", 3), p("A", 2), p("C", 1)),
                    ComparatorArraysChallenge.sortedByAgeDescThenName(
                            people(p("C", 1), p("D", 3), p("A", 2), p("B", 3))));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            ComparatorArraysChallenge.Person[] original = people(p("Ana", 20), p("Bob", 30));
            ComparatorArraysChallenge.sortedByAgeDescThenName(original);
            assertArrayEquals(people(p("Ana", 20), p("Bob", 30)), original);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortByNameInPlace
    // ==========================================================
    @Nested
    class SortByNameInPlaceTests {

        @Test
        void testSortsTheSameArray() {
            ComparatorArraysChallenge.Person[] array = people(p("Cy", 30), p("Ana", 20), p("Bob", 25));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Ana", 20), p("Bob", 25), p("Cy", 30)), array);
        }

        @Test
        void testEqualNamesKeepTheirOrder() {
            ComparatorArraysChallenge.Person[] array = people(p("Ana", 40), p("Ana", 20));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Ana", 40), p("Ana", 20)), array);
        }

        @Test
        void testEqualNamesKeepTheirOrderInTheOtherDirection() {
            ComparatorArraysChallenge.Person[] array = people(p("Ana", 20), p("Ana", 40), p("Abe", 1));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Abe", 1), p("Ana", 20), p("Ana", 40)), array);
        }

        @Test
        void testEmptyArray() {
            ComparatorArraysChallenge.Person[] array = people();
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertEquals(0, array.length);
        }

        @Test
        void testSinglePerson() {
            ComparatorArraysChallenge.Person[] array = people(p("Ana", 20));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Ana", 20)), array);
        }

        @Test
        void testAgeDoesNotMatter() {
            ComparatorArraysChallenge.Person[] array = people(p("Bob", 1), p("Ana", 99));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Ana", 99), p("Bob", 1)), array);
        }

        @Test
        void testUppercaseNamesComeFirst() {
            ComparatorArraysChallenge.Person[] array = people(p("amy", 1), p("Bob", 1));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("Bob", 1), p("amy", 1)), array);
        }

        @Test
        void testTheSameObjectsAreKept() {
            ComparatorArraysChallenge.Person bob = p("Bob", 1);
            ComparatorArraysChallenge.Person ana = p("Ana", 1);
            ComparatorArraysChallenge.Person[] array = people(bob, ana);
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertSame(ana, array[0]);
            assertSame(bob, array[1]);
        }

        @Test
        void testManyPeople() {
            ComparatorArraysChallenge.Person[] array = people(p("D", 1), p("B", 2), p("E", 3), p("A", 4), p("C", 5));
            ComparatorArraysChallenge.sortByNameInPlace(array);
            assertArrayEquals(people(p("A", 4), p("B", 2), p("C", 5), p("D", 1), p("E", 3)), array);
        }
    }

    // ==========================================================
    // CHALLENGE 8: byAgeThenName
    // ==========================================================
    @Nested
    class ByAgeThenNameTests {

        @Test
        void testYoungerIsSmaller() {
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Ana", 20), p("Bob", 30)) < 0);
        }

        @Test
        void testOlderIsGreater() {
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Ana", 30), p("Bob", 20)) > 0);
        }

        @Test
        void testSameAgeNameDecides() {
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Bob", 20), p("Ana", 20)) > 0);
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Ana", 20), p("Bob", 20)) < 0);
        }

        @Test
        void testEqualPeopleGiveZero() {
            assertEquals(0, ComparatorArraysChallenge.byAgeThenName().compare(p("Ana", 20), p("Ana", 20)));
        }

        @Test
        void testAgeWinsOverName() {
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Zed", 10), p("Ana", 20)) < 0);
        }

        @Test
        void testExtremeAgesDoNotOverflow() {
            Comparator<ComparatorArraysChallenge.Person> c = ComparatorArraysChallenge.byAgeThenName();
            assertTrue(c.compare(p("A", Integer.MIN_VALUE), p("A", Integer.MAX_VALUE)) < 0);
            assertTrue(c.compare(p("A", Integer.MAX_VALUE), p("A", Integer.MIN_VALUE)) > 0);
        }

        @Test
        void testBigDifferencesDoNotOverflow() {
            Comparator<ComparatorArraysChallenge.Person> c = ComparatorArraysChallenge.byAgeThenName();
            assertTrue(c.compare(p("A", -2_000_000_000), p("A", 2_000_000_000)) < 0);
            assertTrue(c.compare(p("A", 2_000_000_000), p("A", -2_000_000_000)) > 0);
        }

        @Test
        void testUppercaseNamesComeFirst() {
            assertTrue(ComparatorArraysChallenge.byAgeThenName().compare(p("Zed", 5), p("amy", 5)) < 0);
        }

        @Test
        void testWorksToSortAnArray() {
            ComparatorArraysChallenge.Person[] array = people(p("Bob", 30), p("Ana", 30), p("Cy", 20));
            java.util.Arrays.sort(array, ComparatorArraysChallenge.byAgeThenName());
            assertArrayEquals(people(p("Cy", 20), p("Ana", 30), p("Bob", 30)), array);
        }

        @Test
        void testReversedComparatorSortsInTheOppositeOrder() {
            ComparatorArraysChallenge.Person[] array = people(p("Ana", 20), p("Bob", 30));
            java.util.Arrays.sort(array, ComparatorArraysChallenge.byAgeThenName().reversed());
            assertArrayEquals(people(p("Bob", 30), p("Ana", 20)), array);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortedNullsLast
    // ==========================================================
    @Nested
    class SortedNullsLastTests {

        @Test
        void testSorts() {
            assertArrayEquals(words("a", "b", null), ComparatorArraysChallenge.sortedNullsLast(words("b", null, "a")));
        }

        @Test
        void testOnlyNulls() {
            assertArrayEquals(words(null, null), ComparatorArraysChallenge.sortedNullsLast(words(null, null)));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(words(), ComparatorArraysChallenge.sortedNullsLast(words()));
        }

        @Test
        void testNoNulls() {
            assertArrayEquals(words("a", "b", "c"), ComparatorArraysChallenge.sortedNullsLast(words("c", "a", "b")));
        }

        @Test
        void testSeveralNullsAtTheStart() {
            assertArrayEquals(words("x", "y", null, null),
                    ComparatorArraysChallenge.sortedNullsLast(words(null, "y", null, "x")));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            assertArrayEquals(words("B", "a", null), ComparatorArraysChallenge.sortedNullsLast(words(null, "a", "B")));
        }

        @Test
        void testNullInTheMiddle() {
            assertArrayEquals(words("a", "b", "c", null),
                    ComparatorArraysChallenge.sortedNullsLast(words("c", null, "a", "b")));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertArrayEquals(words("", "a", null), ComparatorArraysChallenge.sortedNullsLast(words(null, "a", "")));
        }

        @Test
        void testSingleNull() {
            assertArrayEquals(words((String) null), ComparatorArraysChallenge.sortedNullsLast(words((String) null)));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            String[] original = words("b", null, "a");
            ComparatorArraysChallenge.sortedNullsLast(original);
            assertArrayEquals(words("b", null, "a"), original);
        }
    }

    // ==========================================================
    // CHALLENGE 10: youngest
    // ==========================================================
    @Nested
    class YoungestTests {

        @Test
        void testTwoYoungest() {
            assertArrayEquals(people(p("Bob", 20), p("Cy", 25)),
                    ComparatorArraysChallenge.youngest(people(p("Ana", 30), p("Bob", 20), p("Cy", 25)), 2));
        }

        @Test
        void testTieOrderedByName() {
            assertArrayEquals(people(p("Ana", 20)),
                    ComparatorArraysChallenge.youngest(people(p("Bob", 20), p("Ana", 20), p("Cy", 25)), 1));
        }

        @Test
        void testNLargerThanTheArray() {
            assertArrayEquals(people(p("Ana", 30)), ComparatorArraysChallenge.youngest(people(p("Ana", 30)), 5));
        }

        @Test
        void testZero() {
            assertArrayEquals(people(), ComparatorArraysChallenge.youngest(people(p("Ana", 30)), 0));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(people(), ComparatorArraysChallenge.youngest(people(), 3));
        }

        @Test
        void testNEqualToTheLength() {
            assertArrayEquals(people(p("B", 1), p("A", 2)),
                    ComparatorArraysChallenge.youngest(people(p("A", 2), p("B", 1)), 2));
        }

        @Test
        void testTheSizeOfTheResultIsNNotTheLength() {
            assertEquals(2, ComparatorArraysChallenge.youngest(
                    people(p("A", 1), p("B", 2), p("C", 3), p("D", 4)), 2).length);
        }

        @Test
        void testTheTieIsCutInTheMiddle() {
            assertArrayEquals(people(p("A", 5), p("B", 5)),
                    ComparatorArraysChallenge.youngest(people(p("C", 5), p("B", 5), p("A", 5)), 2));
        }

        @Test
        void testExtremeAgesDoNotOverflow() {
            assertArrayEquals(people(p("Min", Integer.MIN_VALUE)),
                    ComparatorArraysChallenge.youngest(
                            people(p("Max", Integer.MAX_VALUE), p("Min", Integer.MIN_VALUE)), 1));
        }

        @Test
        void testOriginalArrayIsNotModified() {
            ComparatorArraysChallenge.Person[] original = people(p("Ana", 30), p("Bob", 20));
            ComparatorArraysChallenge.youngest(original, 1);
            assertArrayEquals(people(p("Ana", 30), p("Bob", 20)), original);
        }
    }
}