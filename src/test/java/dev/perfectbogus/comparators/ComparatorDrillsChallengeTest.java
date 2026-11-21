package dev.perfectbogus.comparators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorDrillsChallengeTest {

    @SafeVarargs
    private static <T> List<T> list(T... values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    private static ComparatorDrillsChallenge.Student s(String name, int grade) {
        return new ComparatorDrillsChallenge.Student(name, grade);
    }

    // ==========================================================
    // CHALLENGE 1: sortByLength
    // ==========================================================
    @Nested
    class SortByLengthTests {

        @Test
        void testThreeWords() {
            assertEquals(list("a", "bb", "ccc"), ComparatorDrillsChallenge.sortByLength(list("ccc", "a", "bb")));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertEquals(list("c", "bb", "aa"), ComparatorDrillsChallenge.sortByLength(list("bb", "aa", "c")));
            assertEquals(list("c", "aa", "bb"), ComparatorDrillsChallenge.sortByLength(list("aa", "c", "bb")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByLength(list()));
        }

        @Test
        void testOneWord() {
            assertEquals(list("hello"), ComparatorDrillsChallenge.sortByLength(list("hello")));
        }

        @Test
        void testAllTheSameLength() {
            assertEquals(list("b", "a", "c"), ComparatorDrillsChallenge.sortByLength(list("b", "a", "c")));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(list("a", "bb", "ccc"), ComparatorDrillsChallenge.sortByLength(list("a", "bb", "ccc")));
        }

        @Test
        void testLongestFirstInTheInput() {
            assertEquals(list("a", "bb", "ccc", "dddd"),
                    ComparatorDrillsChallenge.sortByLength(list("dddd", "ccc", "bb", "a")));
        }

        @Test
        void testLengthNotAlphabetical() {
            assertEquals(list("z", "aaa"), ComparatorDrillsChallenge.sortByLength(list("aaa", "z")));
        }

        @Test
        void testEmptyWordGoesFirst() {
            assertEquals(list("", "a"), ComparatorDrillsChallenge.sortByLength(list("a", "")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(list("a", "a", "bb"), ComparatorDrillsChallenge.sortByLength(list("bb", "a", "a")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("ccc", "a", "bb");
            ComparatorDrillsChallenge.sortByLength(original);
            assertEquals(list("ccc", "a", "bb"), original);
        }

        @Test
        void testResultIsANewList() {
            List<String> original = list("a", "bb");
            assertTrue(ComparatorDrillsChallenge.sortByLength(original) != original);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("a" + (99_999 - i));
            }
            List<String> result = ComparatorDrillsChallenge.sortByLength(words);
            assertEquals("a9", result.get(0));
            assertEquals("a0", result.get(9));
            assertEquals("a99", result.get(10));
            assertEquals("a99999", result.get(10_000));
            assertEquals("a10000", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortLongestFirst
    // ==========================================================
    @Nested
    class SortLongestFirstTests {

        @Test
        void testThreeWords() {
            assertEquals(list("ccc", "bb", "a"), ComparatorDrillsChallenge.sortLongestFirst(list("a", "bb", "ccc")));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertEquals(list("bb", "aa", "c"), ComparatorDrillsChallenge.sortLongestFirst(list("bb", "aa", "c")));
            assertEquals(list("aa", "cc", "b"), ComparatorDrillsChallenge.sortLongestFirst(list("aa", "b", "cc")));
            assertEquals(list("cc", "aa", "b"), ComparatorDrillsChallenge.sortLongestFirst(list("b", "cc", "aa")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortLongestFirst(list()));
        }

        @Test
        void testOneWord() {
            assertEquals(list("hello"), ComparatorDrillsChallenge.sortLongestFirst(list("hello")));
        }

        @Test
        void testAllTheSameLength() {
            assertEquals(list("b", "a", "c"), ComparatorDrillsChallenge.sortLongestFirst(list("b", "a", "c")));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(list("ccc", "bb", "a"), ComparatorDrillsChallenge.sortLongestFirst(list("ccc", "bb", "a")));
        }

        @Test
        void testLengthNotAlphabetical() {
            assertEquals(list("aaa", "z"), ComparatorDrillsChallenge.sortLongestFirst(list("z", "aaa")));
        }

        @Test
        void testEmptyWordGoesLast() {
            assertEquals(list("a", ""), ComparatorDrillsChallenge.sortLongestFirst(list("", "a")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(list("bb", "a", "a"), ComparatorDrillsChallenge.sortLongestFirst(list("a", "bb", "a")));
        }

        @Test
        void testSeveralTies() {
            assertEquals(list("dd", "bb", "ee", "a", "c"),
                    ComparatorDrillsChallenge.sortLongestFirst(list("a", "dd", "bb", "c", "ee")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("a", "bb", "ccc");
            ComparatorDrillsChallenge.sortLongestFirst(original);
            assertEquals(list("a", "bb", "ccc"), original);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("a" + (99_999 - i));
            }
            List<String> result = ComparatorDrillsChallenge.sortLongestFirst(words);
            assertEquals("a99999", result.get(0));
            assertEquals("a10000", result.get(89_999));
            assertEquals("a9999", result.get(90_000));
            assertEquals("a0", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortIgnoringCase
    // ==========================================================
    @Nested
    class SortIgnoringCaseTests {

        @Test
        void testFourLetters() {
            assertEquals(list("A", "a", "b", "B"),
                    ComparatorDrillsChallenge.sortIgnoringCase(list("b", "A", "a", "B")));
        }

        @Test
        void testThreeWords() {
            assertEquals(list("apple", "Banana", "Cherry"),
                    ComparatorDrillsChallenge.sortIgnoringCase(list("Banana", "apple", "Cherry")));
        }

        @Test
        void testEqualWordsKeepTheOrder() {
            assertEquals(list("a", "A"), ComparatorDrillsChallenge.sortIgnoringCase(list("a", "A")));
            assertEquals(list("A", "a"), ComparatorDrillsChallenge.sortIgnoringCase(list("A", "a")));
        }

        @Test
        void testUppercaseDoesNotGoFirst() {
            assertEquals(list("a", "B"), ComparatorDrillsChallenge.sortIgnoringCase(list("B", "a")));
            assertEquals(list("apple", "Zed"), ComparatorDrillsChallenge.sortIgnoringCase(list("Zed", "apple")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortIgnoringCase(list()));
        }

        @Test
        void testOneWord() {
            assertEquals(list("Hello"), ComparatorDrillsChallenge.sortIgnoringCase(list("Hello")));
        }

        @Test
        void testAllUppercase() {
            assertEquals(list("A", "B", "C"), ComparatorDrillsChallenge.sortIgnoringCase(list("C", "A", "B")));
        }

        @Test
        void testAllLowercase() {
            assertEquals(list("a", "b", "c"), ComparatorDrillsChallenge.sortIgnoringCase(list("c", "a", "b")));
        }

        @Test
        void testMixedCaseInsideTheWords() {
            assertEquals(list("aBc", "abD", "ABE"),
                    ComparatorDrillsChallenge.sortIgnoringCase(list("ABE", "abD", "aBc")));
        }

        @Test
        void testLongerWordAfterItsPrefix() {
            assertEquals(list("a", "Ab", "aBc"), ComparatorDrillsChallenge.sortIgnoringCase(list("aBc", "Ab", "a")));
        }

        @Test
        void testSeveralEqualWordsKeepTheOrder() {
            assertEquals(list("b", "B", "b", "C"),
                    ComparatorDrillsChallenge.sortIgnoringCase(list("C", "b", "B", "b")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("b", "A", "a", "B");
            ComparatorDrillsChallenge.sortIgnoringCase(original);
            assertEquals(list("b", "A", "a", "B"), original);
        }

        @Test
        void testBigList() {
            String[] pattern = {"b", "B", "a", "A"};
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add(pattern[i % 4]);
            }
            List<String> result = ComparatorDrillsChallenge.sortIgnoringCase(words);
            assertEquals("a", result.get(0));
            assertEquals("A", result.get(1));
            assertEquals("a", result.get(2));
            assertEquals("A", result.get(49_999));
            assertEquals("b", result.get(50_000));
            assertEquals("B", result.get(50_001));
            assertEquals("B", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortByAbsoluteValue
    // ==========================================================
    @Nested
    class SortByAbsoluteValueTests {

        @Test
        void testFiveNumbers() {
            assertEquals(list(-1, 1, 2, -3, 3),
                    ComparatorDrillsChallenge.sortByAbsoluteValue(list(-3, 2, -1, 3, 1)));
        }

        @Test
        void testSameAbsoluteValue() {
            assertEquals(list(-5, 5), ComparatorDrillsChallenge.sortByAbsoluteValue(list(5, -5)));
            assertEquals(list(-5, 5), ComparatorDrillsChallenge.sortByAbsoluteValue(list(-5, 5)));
        }

        @Test
        void testZero() {
            assertEquals(list(0, -1), ComparatorDrillsChallenge.sortByAbsoluteValue(list(0, -1)));
            assertEquals(list(0, -1), ComparatorDrillsChallenge.sortByAbsoluteValue(list(-1, 0)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByAbsoluteValue(list()));
        }

        @Test
        void testOneNumber() {
            assertEquals(list(-7), ComparatorDrillsChallenge.sortByAbsoluteValue(list(-7)));
        }

        @Test
        void testAllNegative() {
            assertEquals(list(-1, -2, -3), ComparatorDrillsChallenge.sortByAbsoluteValue(list(-3, -1, -2)));
        }

        @Test
        void testAllPositive() {
            assertEquals(list(1, 2, 3), ComparatorDrillsChallenge.sortByAbsoluteValue(list(3, 1, 2)));
        }

        @Test
        void testBigNegativeBeforeSmallPositive() {
            assertEquals(list(2, -10), ComparatorDrillsChallenge.sortByAbsoluteValue(list(-10, 2)));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(list(-2, -2, 2, 2), ComparatorDrillsChallenge.sortByAbsoluteValue(list(2, -2, 2, -2)));
        }

        @Test
        void testExtremeValues() {
            assertEquals(list(0, -1_000_000, 1_000_000),
                    ComparatorDrillsChallenge.sortByAbsoluteValue(list(1_000_000, 0, -1_000_000)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Integer> original = list(-3, 2, -1, 3, 1);
            ComparatorDrillsChallenge.sortByAbsoluteValue(original);
            assertEquals(list(-3, 2, -1, 3, 1), original);
        }

        @Test
        void testBigList() {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                nums.add(99_999 - i - 50_000);
            }
            List<Integer> result = ComparatorDrillsChallenge.sortByAbsoluteValue(nums);
            assertEquals(0, result.get(0));
            assertEquals(-1, result.get(1));
            assertEquals(1, result.get(2));
            assertEquals(49_999, result.get(99_998));
            assertEquals(-50_000, result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortEvenBeforeOdd
    // ==========================================================
    @Nested
    class SortEvenBeforeOddTests {

        @Test
        void testFourNumbers() {
            assertEquals(list(2, 4, 1, 3), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(3, 2, 1, 4)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(list(-2, 0, -3, -1), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(-3, -2, -1, 0)));
        }

        @Test
        void testNegativeOddNumbersAreOdd() {
            assertEquals(list(2, -3), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(-3, 2)));
            assertEquals(list(-4, -2, -3, -1), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(-1, -2, -3, -4)));
        }

        @Test
        void testOneNumber() {
            assertEquals(list(5), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(5)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortEvenBeforeOdd(list()));
        }

        @Test
        void testOnlyEvenNumbers() {
            assertEquals(list(2, 4, 6), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(6, 2, 4)));
        }

        @Test
        void testOnlyOddNumbers() {
            assertEquals(list(1, 3, 5), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(5, 1, 3)));
        }

        @Test
        void testZeroIsEven() {
            assertEquals(list(0, 1), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(1, 0)));
        }

        @Test
        void testBiggerEvenBeforeSmallerOdd() {
            assertEquals(list(100, 1), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(1, 100)));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(list(2, 2, 3, 3), ComparatorDrillsChallenge.sortEvenBeforeOdd(list(3, 2, 3, 2)));
        }

        @Test
        void testMixedSigns() {
            assertEquals(list(-6, -2, 4, -5, 3, 7),
                    ComparatorDrillsChallenge.sortEvenBeforeOdd(list(7, -5, 4, -6, 3, -2)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Integer> original = list(3, 2, 1, 4);
            ComparatorDrillsChallenge.sortEvenBeforeOdd(original);
            assertEquals(list(3, 2, 1, 4), original);
        }

        @Test
        void testBigList() {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                nums.add(99_999 - i);
            }
            List<Integer> result = ComparatorDrillsChallenge.sortEvenBeforeOdd(nums);
            assertEquals(0, result.get(0));
            assertEquals(2, result.get(1));
            assertEquals(99_998, result.get(49_999));
            assertEquals(1, result.get(50_000));
            assertEquals(99_999, result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortByGradeDescending
    // ==========================================================
    @Nested
    class SortByGradeDescendingTests {

        @Test
        void testThreeStudents() {
            assertEquals(list(s("bob", 90), s("ana", 80), s("cy", 80)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("ana", 80), s("bob", 90), s("cy", 80))));
        }

        @Test
        void testTiesAreAlphabetical() {
            assertEquals(list(s("ana", 80), s("cy", 80)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("cy", 80), s("ana", 80))));
            assertEquals(list(s("ana", 80), s("cy", 80)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("ana", 80), s("cy", 80))));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByGradeDescending(list()));
        }

        @Test
        void testOneStudent() {
            assertEquals(list(s("ana", 80)), ComparatorDrillsChallenge.sortByGradeDescending(list(s("ana", 80))));
        }

        @Test
        void testGradeBeatsName() {
            assertEquals(list(s("zed", 100), s("ana", 1)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("ana", 1), s("zed", 100))));
        }

        @Test
        void testTheCaseMattersInTheTies() {
            assertEquals(list(s("Bob", 80), s("alice", 80)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("alice", 80), s("Bob", 80))));
        }

        @Test
        void testSameGradeForEveryone() {
            assertEquals(list(s("a", 5), s("b", 5), s("c", 5)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("c", 5), s("a", 5), s("b", 5))));
        }

        @Test
        void testGradeZero() {
            assertEquals(list(s("b", 1), s("a", 0)),
                    ComparatorDrillsChallenge.sortByGradeDescending(list(s("a", 0), s("b", 1))));
        }

        @Test
        void testSeveralGradesAndTies() {
            assertEquals(list(s("d", 90), s("a", 70), s("c", 70), s("b", 50)),
                    ComparatorDrillsChallenge.sortByGradeDescending(
                            list(s("b", 50), s("c", 70), s("d", 90), s("a", 70))));
        }

        @Test
        void testOriginalIsNotModified() {
            List<ComparatorDrillsChallenge.Student> original = list(s("ana", 80), s("bob", 90), s("cy", 80));
            ComparatorDrillsChallenge.sortByGradeDescending(original);
            assertEquals(list(s("ana", 80), s("bob", 90), s("cy", 80)), original);
        }

        @Test
        void testBigList() {
            List<ComparatorDrillsChallenge.Student> students = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                students.add(s("n" + String.format("%05d", i), i % 101));
            }
            List<ComparatorDrillsChallenge.Student> result = ComparatorDrillsChallenge.sortByGradeDescending(students);
            assertEquals(s("n00100", 100), result.get(0));
            assertEquals(s("n00201", 100), result.get(1));
            assertEquals(s("n99990", 0), result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 7: byGradeThenName
    // ==========================================================
    @Nested
    class ByGradeThenNameTests {

        @Test
        void testLowerGradeGoesFirst() {
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("ana", 80), s("bob", 90)) < 0);
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("bob", 90), s("ana", 80)) > 0);
        }

        @Test
        void testSameGradeUsesTheName() {
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("bob", 80), s("ana", 80)) > 0);
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("ana", 80), s("bob", 80)) < 0);
        }

        @Test
        void testEqualStudents() {
            assertEquals(0, ComparatorDrillsChallenge.byGradeThenName().compare(s("ana", 80), s("ana", 80)));
        }

        @Test
        void testGradeBeatsName() {
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("zed", 1), s("ana", 2)) < 0);
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("ana", 100), s("zed", 0)) > 0);
        }

        @Test
        void testSortingAList() {
            List<ComparatorDrillsChallenge.Student> students = list(s("bob", 90), s("ana", 80), s("cy", 80));
            students.sort(ComparatorDrillsChallenge.byGradeThenName());
            assertEquals(list(s("ana", 80), s("cy", 80), s("bob", 90)), students);
        }

        @Test
        void testTheCaseMatters() {
            assertTrue(ComparatorDrillsChallenge.byGradeThenName().compare(s("Bob", 80), s("alice", 80)) < 0);
        }

        @Test
        void testResultIsNotNull() {
            assertNotNull(ComparatorDrillsChallenge.byGradeThenName());
        }

        @Test
        void testBothDirectionsAreConsistent() {
            Comparator<ComparatorDrillsChallenge.Student> comparator = ComparatorDrillsChallenge.byGradeThenName();
            List<ComparatorDrillsChallenge.Student> students = list(s("ana", 80), s("bob", 90), s("cy", 80),
                    s("ana", 90), s("zed", 0));
            for (ComparatorDrillsChallenge.Student first : students) {
                for (ComparatorDrillsChallenge.Student second : students) {
                    assertEquals(Integer.signum(comparator.compare(first, second)),
                            -Integer.signum(comparator.compare(second, first)));
                }
            }
        }

        @Test
        void testSortingGivesTheSameResultForAnyInitialOrder() {
            List<ComparatorDrillsChallenge.Student> first = list(s("a", 1), s("b", 1), s("c", 2), s("d", 0));
            List<ComparatorDrillsChallenge.Student> second = list(s("d", 0), s("c", 2), s("b", 1), s("a", 1));
            first.sort(ComparatorDrillsChallenge.byGradeThenName());
            second.sort(ComparatorDrillsChallenge.byGradeThenName());
            assertEquals(list(s("d", 0), s("a", 1), s("b", 1), s("c", 2)), first);
            assertEquals(first, second);
        }

        @Test
        void testSortingOnlyUsesTheGradeAndTheName() {
            List<ComparatorDrillsChallenge.Student> students = list(s("b", 5), s("a", 5), s("b", 4));
            students.sort(ComparatorDrillsChallenge.byGradeThenName());
            assertEquals(list(s("b", 4), s("a", 5), s("b", 5)), students);
        }

        @Test
        void testSortingAnEmptyList() {
            List<ComparatorDrillsChallenge.Student> students = list();
            students.sort(ComparatorDrillsChallenge.byGradeThenName());
            assertEquals(list(), students);
        }

        @Test
        void testBigList() {
            List<ComparatorDrillsChallenge.Student> students = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                students.add(s("n" + String.format("%05d", i), i % 101));
            }
            students.sort(ComparatorDrillsChallenge.byGradeThenName());
            assertEquals(s("n00000", 0), students.get(0));
            assertEquals(s("n00101", 0), students.get(1));
            assertEquals(s("n99989", 100), students.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 8: topStudent
    // ==========================================================
    @Nested
    class TopStudentTests {

        @Test
        void testHighestGrade() {
            assertEquals(s("bob", 90),
                    ComparatorDrillsChallenge.topStudent(list(s("ana", 80), s("bob", 90), s("cy", 85))));
        }

        @Test
        void testTieUsesTheName() {
            assertEquals(s("bob", 90),
                    ComparatorDrillsChallenge.topStudent(list(s("ana", 80), s("bob", 90), s("cy", 90))));
            assertEquals(s("ana", 85), ComparatorDrillsChallenge.topStudent(list(s("cy", 85), s("ana", 85))));
        }

        @Test
        void testOneStudent() {
            assertEquals(s("ana", 70), ComparatorDrillsChallenge.topStudent(list(s("ana", 70))));
        }

        @Test
        void testTheBestIsFirst() {
            assertEquals(s("zed", 100),
                    ComparatorDrillsChallenge.topStudent(list(s("zed", 100), s("ana", 50), s("bob", 60))));
        }

        @Test
        void testTheBestIsLast() {
            assertEquals(s("zed", 100),
                    ComparatorDrillsChallenge.topStudent(list(s("ana", 50), s("bob", 60), s("zed", 100))));
        }

        @Test
        void testThreeWayTie() {
            assertEquals(s("a", 9), ComparatorDrillsChallenge.topStudent(list(s("c", 9), s("a", 9), s("b", 9))));
        }

        @Test
        void testTheCaseMattersInTheTies() {
            assertEquals(s("Zed", 80), ComparatorDrillsChallenge.topStudent(list(s("ann", 80), s("Zed", 80))));
        }

        @Test
        void testTheHighestIsNotTheFirstAlphabetically() {
            assertEquals(s("zed", 90), ComparatorDrillsChallenge.topStudent(list(s("ana", 10), s("zed", 90))));
        }

        @Test
        void testGradeZero() {
            assertEquals(s("b", 0), ComparatorDrillsChallenge.topStudent(list(s("b", 0), s("c", 0))));
        }

        @Test
        void testOriginalIsNotModified() {
            List<ComparatorDrillsChallenge.Student> original = list(s("ana", 80), s("bob", 90), s("cy", 90));
            ComparatorDrillsChallenge.topStudent(original);
            assertEquals(list(s("ana", 80), s("bob", 90), s("cy", 90)), original);
        }

        @Test
        void testBigList() {
            List<ComparatorDrillsChallenge.Student> students = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                students.add(s("n" + String.format("%05d", 99_999 - i), i % 101));
            }
            assertEquals(s("n00010", 100), ComparatorDrillsChallenge.topStudent(students));
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortByLastLetter
    // ==========================================================
    @Nested
    class SortByLastLetterTests {

        @Test
        void testThreeWords() {
            assertEquals(list("ca", "ab", "bb"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "ca", "bb")));
        }

        @Test
        void testThreeWordsWithTies() {
            assertEquals(list("ya", "za", "xb"), ComparatorDrillsChallenge.sortByLastLetter(list("xb", "ya", "za")));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertEquals(list("bb", "ab"), ComparatorDrillsChallenge.sortByLastLetter(list("bb", "ab")));
            assertEquals(list("ab", "bb"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "bb")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByLastLetter(list()));
        }

        @Test
        void testOneWord() {
            assertEquals(list("hello"), ComparatorDrillsChallenge.sortByLastLetter(list("hello")));
        }

        @Test
        void testWordsWithOneLetter() {
            assertEquals(list("a", "b", "c"), ComparatorDrillsChallenge.sortByLastLetter(list("c", "a", "b")));
        }

        @Test
        void testLastLetterNotFirstLetter() {
            assertEquals(list("za", "ab"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "za")));
        }

        @Test
        void testUppercaseLastLetterGoesFirst() {
            assertEquals(list("aB", "ab"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "aB")));
            assertEquals(list("zB", "ab"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "zB")));
        }

        @Test
        void testWholeWordIsNotUsed() {
            assertEquals(list("zzzza", "a1b"), ComparatorDrillsChallenge.sortByLastLetter(list("a1b", "zzzza")));
        }

        @Test
        void testAllTheSameLastLetter() {
            assertEquals(list("cb", "ab", "bb"), ComparatorDrillsChallenge.sortByLastLetter(list("cb", "ab", "bb")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(list("ba", "ba", "ab"), ComparatorDrillsChallenge.sortByLastLetter(list("ab", "ba", "ba")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("ab", "ca", "bb");
            ComparatorDrillsChallenge.sortByLastLetter(original);
            assertEquals(list("ab", "ca", "bb"), original);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("w" + i);
            }
            List<String> result = ComparatorDrillsChallenge.sortByLastLetter(words);
            assertEquals("w0", result.get(0));
            assertEquals("w10", result.get(1));
            assertEquals("w99990", result.get(9_999));
            assertEquals("w1", result.get(10_000));
            assertEquals("w99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 10: sortByWordCount
    // ==========================================================
    @Nested
    class SortByWordCountTests {

        @Test
        void testThreeSentences() {
            assertEquals(list("a", "a b", "a b c"),
                    ComparatorDrillsChallenge.sortByWordCount(list("a b c", "a", "a b")));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertEquals(list("x y", "a b"), ComparatorDrillsChallenge.sortByWordCount(list("x y", "a b")));
            assertEquals(list("a b", "x y"), ComparatorDrillsChallenge.sortByWordCount(list("a b", "x y")));
        }

        @Test
        void testEmptyText() {
            assertEquals(list("", "a"), ComparatorDrillsChallenge.sortByWordCount(list("a", "")));
        }

        @Test
        void testEmptyList() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByWordCount(list()));
        }

        @Test
        void testOneSentence() {
            assertEquals(list("a b c"), ComparatorDrillsChallenge.sortByWordCount(list("a b c")));
        }

        @Test
        void testWordCountNotLength() {
            assertEquals(list("aaaaaa", "a b"), ComparatorDrillsChallenge.sortByWordCount(list("aaaaaa", "a b")));
            assertEquals(list("aaaaaa", "a b"), ComparatorDrillsChallenge.sortByWordCount(list("a b", "aaaaaa")));
        }

        @Test
        void testThreeLevels() {
            assertEquals(list("f", "a b", "c d e"),
                    ComparatorDrillsChallenge.sortByWordCount(list("a b", "c d e", "f")));
        }

        @Test
        void testSentencesWithTheSameWords() {
            assertEquals(list("c", "b a", "a b"), ComparatorDrillsChallenge.sortByWordCount(list("b a", "c", "a b")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("a b c", "a", "a b");
            ComparatorDrillsChallenge.sortByWordCount(original);
            assertEquals(list("a b c", "a", "a b"), original);
        }

        @Test
        void testBigList() {
            List<String> sentences = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                sentences.add(i + " x".repeat(i % 5));
            }
            List<String> result = ComparatorDrillsChallenge.sortByWordCount(sentences);
            assertEquals("0", result.get(0));
            assertEquals("5", result.get(1));
            assertEquals("99995", result.get(19_999));
            assertEquals("1 x", result.get(20_000));
            assertEquals("99999 x x x x", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 11: sortByDigitSum
    // ==========================================================
    @Nested
    class SortByDigitSumTests {

        @Test
        void testFourNumbers() {
            assertEquals(list(10, 100, 19, 28), ComparatorDrillsChallenge.sortByDigitSum(list(19, 28, 10, 100)));
        }

        @Test
        void testSameSumKeepsTheNumbersAscending() {
            assertEquals(list(5, 14), ComparatorDrillsChallenge.sortByDigitSum(list(5, 14)));
            assertEquals(list(5, 14), ComparatorDrillsChallenge.sortByDigitSum(list(14, 5)));
            assertEquals(list(19, 91), ComparatorDrillsChallenge.sortByDigitSum(list(91, 19)));
        }

        @Test
        void testZero() {
            assertEquals(list(0, 10), ComparatorDrillsChallenge.sortByDigitSum(list(10, 0)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByDigitSum(list()));
        }

        @Test
        void testOneNumber() {
            assertEquals(list(123), ComparatorDrillsChallenge.sortByDigitSum(list(123)));
        }

        @Test
        void testNotTheValueOfTheNumber() {
            assertEquals(list(1000, 99), ComparatorDrillsChallenge.sortByDigitSum(list(99, 1000)));
        }

        @Test
        void testPowersOfTen() {
            assertEquals(list(1, 10, 100, 1000), ComparatorDrillsChallenge.sortByDigitSum(list(1000, 100, 10, 1)));
        }

        @Test
        void testBigNumbers() {
            assertEquals(list(Integer.MAX_VALUE, 999_999_999),
                    ComparatorDrillsChallenge.sortByDigitSum(list(999_999_999, Integer.MAX_VALUE)));
        }

        @Test
        void testBiggestInt() {
            assertEquals(list(1, Integer.MAX_VALUE), ComparatorDrillsChallenge.sortByDigitSum(
                    list(Integer.MAX_VALUE, 1)));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(list(11, 11, 29), ComparatorDrillsChallenge.sortByDigitSum(list(29, 11, 11)));
        }

        @Test
        void testSeveralSums() {
            assertEquals(list(2, 11, 20, 3, 12, 4), ComparatorDrillsChallenge.sortByDigitSum(
                    list(4, 12, 3, 20, 11, 2)));
        }

        @Test
        void testOriginalIsNotModified() {
            List<Integer> original = list(19, 28, 10, 100);
            ComparatorDrillsChallenge.sortByDigitSum(original);
            assertEquals(list(19, 28, 10, 100), original);
        }

        @Test
        void testBigList() {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                nums.add(99_999 - i);
            }
            List<Integer> result = ComparatorDrillsChallenge.sortByDigitSum(nums);
            assertEquals(0, result.get(0));
            assertEquals(1, result.get(1));
            assertEquals(10, result.get(2));
            assertEquals(10_000, result.get(5));
            assertEquals(2, result.get(6));
            assertEquals(99_998, result.get(99_998));
            assertEquals(99_999, result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 12: sortNullsLast
    // ==========================================================
    @Nested
    class SortNullsLastTests {

        @Test
        void testOneNull() {
            assertEquals(list("a", "b", null), ComparatorDrillsChallenge.sortNullsLast(list("b", null, "a")));
        }

        @Test
        void testOnlyNulls() {
            assertEquals(list(null, null), ComparatorDrillsChallenge.sortNullsLast(list(null, null)));
        }

        @Test
        void testNoNulls() {
            assertEquals(list("B", "a"), ComparatorDrillsChallenge.sortNullsLast(list("a", "B")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortNullsLast(list()));
        }

        @Test
        void testNullFirstInTheInput() {
            assertEquals(list("a", "b", null), ComparatorDrillsChallenge.sortNullsLast(list(null, "b", "a")));
        }

        @Test
        void testSeveralNulls() {
            assertEquals(list("a", "b", "c", null, null),
                    ComparatorDrillsChallenge.sortNullsLast(list(null, "c", null, "a", "b")));
        }

        @Test
        void testOneNullOnly() {
            assertEquals(list((String) null), ComparatorDrillsChallenge.sortNullsLast(list((String) null)));
        }

        @Test
        void testOneWord() {
            assertEquals(list("x"), ComparatorDrillsChallenge.sortNullsLast(list("x")));
        }

        @Test
        void testEmptyStringIsNotNull() {
            assertEquals(list("", "a", null), ComparatorDrillsChallenge.sortNullsLast(list(null, "a", "")));
        }

        @Test
        void testRepeatedWords() {
            assertEquals(list("a", "a", "b", null), ComparatorDrillsChallenge.sortNullsLast(list("b", "a", null, "a")));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(list("Zed", "apple", null),
                    ComparatorDrillsChallenge.sortNullsLast(list("apple", null, "Zed")));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("b", null, "a");
            ComparatorDrillsChallenge.sortNullsLast(original);
            assertEquals(list("b", null, "a"), original);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add(i % 3 == 0 ? null : "w" + i);
            }
            List<String> result = ComparatorDrillsChallenge.sortNullsLast(words);
            assertEquals("w1", result.get(0));
            assertEquals("w99998", result.get(66_665));
            assertNull(result.get(66_666));
            assertNull(result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 13: sortKeysByValue
    // ==========================================================
    @Nested
    class SortKeysByValueTests {

        private Map<String, Integer> map(Object... keysAndValues) {
            Map<String, Integer> result = new HashMap<>();
            for (int i = 0; i < keysAndValues.length; i += 2) {
                result.put((String) keysAndValues[i], (Integer) keysAndValues[i + 1]);
            }
            return result;
        }

        @Test
        void testThreeKeys() {
            assertEquals(list("b", "c", "a"), ComparatorDrillsChallenge.sortKeysByValue(map("a", 1, "b", 3, "c", 3)));
        }

        @Test
        void testOneKey() {
            assertEquals(list("x"), ComparatorDrillsChallenge.sortKeysByValue(map("x", 5)));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortKeysByValue(map()));
        }

        @Test
        void testValueBeatsKey() {
            assertEquals(list("z", "a"), ComparatorDrillsChallenge.sortKeysByValue(map("a", 1, "z", 2)));
        }

        @Test
        void testTiesAreAlphabetical() {
            assertEquals(list("a", "b", "c"), ComparatorDrillsChallenge.sortKeysByValue(map("c", 1, "a", 1, "b", 1)));
        }

        @Test
        void testNegativeValues() {
            assertEquals(list("b", "c", "a"), ComparatorDrillsChallenge.sortKeysByValue(map("a", -5, "b", 0, "c", -1)));
        }

        @Test
        void testTheCaseMattersInTheTies() {
            assertEquals(list("B", "a"), ComparatorDrillsChallenge.sortKeysByValue(map("a", 1, "B", 1)));
        }

        @Test
        void testManyDifferentValues() {
            assertEquals(list("e", "d", "c", "b", "a"),
                    ComparatorDrillsChallenge.sortKeysByValue(map("a", 1, "b", 2, "c", 3, "d", 4, "e", 5)));
        }

        @Test
        void testBigValues() {
            assertEquals(list("a", "b"),
                    ComparatorDrillsChallenge.sortKeysByValue(map("a", Integer.MAX_VALUE, "b", Integer.MIN_VALUE)));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(list("a", "b"), ComparatorDrillsChallenge.sortKeysByValue(map("b", 1000, "a", 1000)));
        }

        @Test
        void testMapIsNotModified() {
            Map<String, Integer> scores = map("a", 1, "b", 3, "c", 3);
            ComparatorDrillsChallenge.sortKeysByValue(scores);
            assertEquals(map("a", 1, "b", 3, "c", 3), scores);
        }

        @Test
        void testBigMap() {
            Map<String, Integer> scores = new HashMap<>();
            for (int i = 0; i < 100_000; i++) {
                scores.put("k" + String.format("%05d", i), i % 100);
            }
            List<String> result = ComparatorDrillsChallenge.sortKeysByValue(scores);
            assertEquals(100_000, result.size());
            assertEquals("k00099", result.get(0));
            assertEquals("k00199", result.get(1));
            assertEquals("k99900", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 14: sortByFrequency
    // ==========================================================
    @Nested
    class SortByFrequencyTests {

        @Test
        void testSixWords() {
            assertEquals(list("b", "a", "c"),
                    ComparatorDrillsChallenge.sortByFrequency(list("b", "a", "b", "c", "a", "b")));
        }

        @Test
        void testTiesAreAlphabetical() {
            assertEquals(list("x", "y"), ComparatorDrillsChallenge.sortByFrequency(list("y", "x")));
            assertEquals(list("x", "y"), ComparatorDrillsChallenge.sortByFrequency(list("x", "y")));
        }

        @Test
        void testEmpty() {
            assertEquals(list(), ComparatorDrillsChallenge.sortByFrequency(list()));
        }

        @Test
        void testOneWord() {
            assertEquals(list("a"), ComparatorDrillsChallenge.sortByFrequency(list("a")));
        }

        @Test
        void testSameWordManyTimes() {
            assertEquals(list("a"), ComparatorDrillsChallenge.sortByFrequency(list("a", "a", "a")));
        }

        @Test
        void testFrequencyBeatsAlphabeticalOrder() {
            assertEquals(list("z", "a"), ComparatorDrillsChallenge.sortByFrequency(list("a", "z", "z")));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(list("B", "a"), ComparatorDrillsChallenge.sortByFrequency(list("a", "B")));
            assertEquals(list("a", "A"), ComparatorDrillsChallenge.sortByFrequency(list("A", "a", "a")));
        }

        @Test
        void testSeveralLevels() {
            assertEquals(list("c", "b", "a"),
                    ComparatorDrillsChallenge.sortByFrequency(list("a", "b", "b", "c", "c", "c")));
        }

        @Test
        void testTiesInTheMiddle() {
            assertEquals(list("d", "a", "b", "c"),
                    ComparatorDrillsChallenge.sortByFrequency(list("c", "b", "a", "d", "d", "c", "b", "a", "d")));
        }

        @Test
        void testEachWordAppearsOnce() {
            assertEquals(3, ComparatorDrillsChallenge.sortByFrequency(list("a", "a", "b", "b", "c")).size());
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> original = list("b", "a", "b", "c", "a", "b");
            ComparatorDrillsChallenge.sortByFrequency(original);
            assertEquals(list("b", "a", "b", "c", "a", "b"), original);
        }

        @Test
        void testBigListWithTheSameFrequency() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("w" + i % 1000);
            }
            List<String> result = ComparatorDrillsChallenge.sortByFrequency(words);
            assertEquals(1000, result.size());
            assertEquals("w0", result.get(0));
            assertEquals("w1", result.get(1));
            assertEquals("w10", result.get(2));
            assertEquals("w100", result.get(3));
            assertEquals("w999", result.get(999));
        }

        @Test
        void testBigListWithDifferentFrequencies() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add("w" + i % 1000);
            }
            for (int i = 0; i < 150; i++) {
                words.add("z");
            }
            for (int i = 0; i < 100; i++) {
                words.add("x");
            }
            List<String> result = ComparatorDrillsChallenge.sortByFrequency(words);
            assertEquals(1002, result.size());
            assertEquals("z", result.get(0));
            assertEquals("w0", result.get(1));
            assertEquals("w999", result.get(1_000));
            assertEquals("x", result.get(1_001));
        }
    }

    // ==========================================================
    // CHALLENGE 15: isSorted
    // ==========================================================
    @Nested
    class IsSortedTests {

        @Test
        void testSortedNaturalOrder() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "b", "b"), Comparator.naturalOrder()));
        }

        @Test
        void testNotSortedNaturalOrder() {
            assertFalse(ComparatorDrillsChallenge.isSorted(list("b", "a"), Comparator.naturalOrder()));
        }

        @Test
        void testByLength() {
            assertFalse(ComparatorDrillsChallenge.isSorted(list("bb", "a"), Comparator.comparingInt(String::length)));
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "bb"), Comparator.comparingInt(String::length)));
        }

        @Test
        void testReverseOrder() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("b", "a"), Comparator.reverseOrder()));
            assertFalse(ComparatorDrillsChallenge.isSorted(list("a", "b"), Comparator.reverseOrder()));
        }

        @Test
        void testEmpty() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list(), Comparator.naturalOrder()));
        }

        @Test
        void testOneWord() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("z"), Comparator.naturalOrder()));
        }

        @Test
        void testAllEqual() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "a", "a"), Comparator.naturalOrder()));
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "b", "c"), (x, y) -> 0));
        }

        @Test
        void testOnlyTheLastPairIsWrong() {
            assertFalse(ComparatorDrillsChallenge.isSorted(list("a", "b", "c", "b"), Comparator.naturalOrder()));
        }

        @Test
        void testOnlyTheFirstPairIsWrong() {
            assertFalse(ComparatorDrillsChallenge.isSorted(list("b", "a", "c", "d"), Comparator.naturalOrder()));
        }

        @Test
        void testOnlyTheMiddlePairIsWrong() {
            assertFalse(ComparatorDrillsChallenge.isSorted(list("a", "c", "b", "d"), Comparator.naturalOrder()));
        }

        @Test
        void testTheComparatorIsUsed() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("B", "a"), Comparator.naturalOrder()));
            assertFalse(ComparatorDrillsChallenge.isSorted(list("B", "a"), String.CASE_INSENSITIVE_ORDER));
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "B"), String.CASE_INSENSITIVE_ORDER));
        }

        @Test
        void testEqualNeighboursAreAccepted() {
            assertTrue(ComparatorDrillsChallenge.isSorted(list("a", "A", "b"), String.CASE_INSENSITIVE_ORDER));
        }

        @Test
        void testOriginalIsNotModified() {
            List<String> words = list("b", "a", "c");
            ComparatorDrillsChallenge.isSorted(words, Comparator.naturalOrder());
            assertEquals(list("b", "a", "c"), words);
        }

        @Test
        void testBigList() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                words.add(String.format("w%06d", i));
            }
            assertTrue(ComparatorDrillsChallenge.isSorted(words, Comparator.naturalOrder()));
            words.set(99_998, "w099999");
            words.set(99_999, "w099998");
            assertFalse(ComparatorDrillsChallenge.isSorted(words, Comparator.naturalOrder()));
            words.set(99_998, "w099998");
            words.set(99_999, "w099999");
            words.set(50_000, "w000000");
            assertFalse(ComparatorDrillsChallenge.isSorted(words, Comparator.naturalOrder()));
        }
    }
}