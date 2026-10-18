package dev.perfectbogus.comparators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorSortChallengeTest {

    private static ComparatorSortChallenge.Student student(String name, int grade) {
        return new ComparatorSortChallenge.Student(name, grade);
    }

    private static Integer[] sortedIntegers(Comparator<Integer> comparator, Integer... values) {
        Integer[] copy = values.clone();
        Arrays.sort(copy, comparator);
        return copy;
    }

    private static String[] sortedStrings(Comparator<String> comparator, String... values) {
        String[] copy = values.clone();
        Arrays.sort(copy, comparator);
        return copy;
    }

    private static int sign(int number) {
        return Integer.compare(number, 0);
    }

    // ==========================================================
    // CHALLENGE 1: sortInPlace
    // ==========================================================
    @Nested
    class SortInPlaceTests {

        @Test
        void testSortsTheSameArray() {
            int[] numbers = {5, 3, 9, 1};

            ComparatorSortChallenge.sortInPlace(numbers);

            assertArrayEquals(new int[]{1, 3, 5, 9}, numbers);
        }

        @Test
        void testKeepsRepeatedNumbersAndNegatives() {
            int[] numbers = {2, 2, -4};

            ComparatorSortChallenge.sortInPlace(numbers);

            assertArrayEquals(new int[]{-4, 2, 2}, numbers);
        }

        @Test
        void testAlreadySortedAndReversed() {
            int[] sorted = {1, 2, 3};
            int[] reversed = {3, 2, 1};

            ComparatorSortChallenge.sortInPlace(sorted);
            ComparatorSortChallenge.sortInPlace(reversed);

            assertArrayEquals(new int[]{1, 2, 3}, sorted);
            assertArrayEquals(new int[]{1, 2, 3}, reversed);
        }

        @Test
        void testExtremeValues() {
            int[] numbers = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

            ComparatorSortChallenge.sortInPlace(numbers);

            assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, numbers);
        }

        @Test
        void testLongerArray() {
            int[] numbers = {8, 3, 10, 1, 9, 2, 7, 4, 6, 5, 0};

            ComparatorSortChallenge.sortInPlace(numbers);

            assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, numbers);
        }

        @Test
        void testEmptyAndSingle() {
            int[] empty = {};
            int[] single = {7};

            ComparatorSortChallenge.sortInPlace(empty);
            ComparatorSortChallenge.sortInPlace(single);

            assertArrayEquals(new int[]{}, empty);
            assertArrayEquals(new int[]{7}, single);
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortRange
    // ==========================================================
    @Nested
    class SortRangeTests {

        @Test
        void testSortsOnlyTheMiddle() {
            int[] numbers = {9, 8, 7, 6, 5};

            ComparatorSortChallenge.sortRange(numbers, 1, 4);

            assertArrayEquals(new int[]{9, 6, 7, 8, 5}, numbers);
        }

        @Test
        void testWholeArray() {
            int[] numbers = {4, 3, 2, 1};

            ComparatorSortChallenge.sortRange(numbers, 0, 4);

            assertArrayEquals(new int[]{1, 2, 3, 4}, numbers);
        }

        @Test
        void testEmptyRangeChangesNothing() {
            int[] numbers = {4, 3, 2, 1};

            ComparatorSortChallenge.sortRange(numbers, 2, 2);

            assertArrayEquals(new int[]{4, 3, 2, 1}, numbers);
        }

        @Test
        void testOnlyTheBeginning() {
            int[] numbers = {3, 2, 1, 0};

            ComparatorSortChallenge.sortRange(numbers, 0, 2);

            assertArrayEquals(new int[]{2, 3, 1, 0}, numbers);
        }

        @Test
        void testOnlyTheEnd() {
            int[] numbers = {3, 2, 1, 0};

            ComparatorSortChallenge.sortRange(numbers, 2, 4);

            assertArrayEquals(new int[]{3, 2, 0, 1}, numbers);
        }

        @Test
        void testRangeOfOnePositionChangesNothing() {
            int[] numbers = {5, 4, 3};

            ComparatorSortChallenge.sortRange(numbers, 1, 2);

            assertArrayEquals(new int[]{5, 4, 3}, numbers);
        }

        @Test
        void testNumbersOutsideTheRangeStayEvenIfTheyAreOutOfOrder() {
            int[] numbers = {5, 9, 1, 0, 7};

            ComparatorSortChallenge.sortRange(numbers, 1, 3);

            assertArrayEquals(new int[]{5, 1, 9, 0, 7}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortedLetters
    // ==========================================================
    @Nested
    class SortedLettersTests {

        @Test
        void testSortsTheCharacters() {
            assertEquals("aaabnn", ComparatorSortChallenge.sortedLetters("banana"));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            assertEquals("Dgo", ComparatorSortChallenge.sortedLetters("Dog"));
            assertEquals("ABbc", ComparatorSortChallenge.sortedLetters("bAcB"));
        }

        @Test
        void testSpacesAndDigitsAreSortedToo() {
            assertEquals("  2ab", ComparatorSortChallenge.sortedLetters("b a 2"));
        }

        @Test
        void testAlreadySorted() {
            assertEquals("abc", ComparatorSortChallenge.sortedLetters("abc"));
        }

        @Test
        void testEmptyAndSingle() {
            assertEquals("", ComparatorSortChallenge.sortedLetters(""));
            assertEquals("z", ComparatorSortChallenge.sortedLetters("z"));
        }

        @Test
        void testRepeatedCharacters() {
            assertEquals("aaaa", ComparatorSortChallenge.sortedLetters("aaaa"));
            assertEquals("  eeefhhkllort", ComparatorSortChallenge.sortedLetters("hello there kf"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: positionToInsert
    // ==========================================================
    @Nested
    class PositionToInsertTests {

        private final int[] sorted = {10, 20, 30, 40};

        @Test
        void testValueThatIsInTheArray() {
            assertEquals(0, ComparatorSortChallenge.positionToInsert(sorted, 10));
            assertEquals(2, ComparatorSortChallenge.positionToInsert(sorted, 30));
            assertEquals(3, ComparatorSortChallenge.positionToInsert(sorted, 40));
        }

        @Test
        void testValueBetweenTwoNumbers() {
            assertEquals(1, ComparatorSortChallenge.positionToInsert(sorted, 15));
            assertEquals(2, ComparatorSortChallenge.positionToInsert(sorted, 25));
            assertEquals(3, ComparatorSortChallenge.positionToInsert(sorted, 35));
        }

        @Test
        void testValueBeforeTheFirstAndAfterTheLast() {
            assertEquals(0, ComparatorSortChallenge.positionToInsert(sorted, 5));
            assertEquals(4, ComparatorSortChallenge.positionToInsert(sorted, 99));
        }

        @Test
        void testEmptyArray() {
            assertEquals(0, ComparatorSortChallenge.positionToInsert(new int[]{}, 7));
        }

        @Test
        void testOneNumber() {
            int[] one = {5};

            assertEquals(0, ComparatorSortChallenge.positionToInsert(one, 5));
            assertEquals(0, ComparatorSortChallenge.positionToInsert(one, 3));
            assertEquals(1, ComparatorSortChallenge.positionToInsert(one, 9));
        }

        @Test
        void testNegativeNumbers() {
            int[] numbers = {-10, -5, 0, 5};

            assertEquals(1, ComparatorSortChallenge.positionToInsert(numbers, -7));
            assertEquals(1, ComparatorSortChallenge.positionToInsert(numbers, -5));
            assertEquals(0, ComparatorSortChallenge.positionToInsert(numbers, -20));
        }

        @Test
        void testExtremeValues() {
            int[] numbers = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};

            assertEquals(0, ComparatorSortChallenge.positionToInsert(numbers, Integer.MIN_VALUE));
            assertEquals(2, ComparatorSortChallenge.positionToInsert(numbers, Integer.MAX_VALUE));
            assertEquals(2, ComparatorSortChallenge.positionToInsert(numbers, 1));
        }

        @Test
        void testTheArrayIsNotModified() {
            int[] numbers = {10, 20, 30, 40};

            ComparatorSortChallenge.positionToInsert(numbers, 25);

            assertArrayEquals(new int[]{10, 20, 30, 40}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 5: median
    // ==========================================================
    @Nested
    class MedianTests {

        @Test
        void testOddAmountOfNumbers() {
            assertEquals(3.0, ComparatorSortChallenge.median(new int[]{5, 1, 3}), 0.0001);
        }

        @Test
        void testEvenAmountOfNumbers() {
            assertEquals(2.5, ComparatorSortChallenge.median(new int[]{4, 1, 3, 2}), 0.0001);
            assertEquals(1.5, ComparatorSortChallenge.median(new int[]{1, 2}), 0.0001);
        }

        @Test
        void testNumbersMustBeSortedFirst() {
            assertEquals(4.0, ComparatorSortChallenge.median(new int[]{9, 1, 2, 8, 5, 3}), 0.0001);
            assertEquals(7.0, ComparatorSortChallenge.median(new int[]{9, 1, 7, 8, 5}), 0.0001);
        }

        @Test
        void testOneNumberAndEmpty() {
            assertEquals(7.0, ComparatorSortChallenge.median(new int[]{7}), 0.0001);
            assertEquals(0.0, ComparatorSortChallenge.median(new int[]{}), 0.0001);
        }

        @Test
        void testNegativeAndRepeatedNumbers() {
            assertEquals(-2.5, ComparatorSortChallenge.median(new int[]{-5, -1, -3, -2}), 0.0001);
            assertEquals(2.0, ComparatorSortChallenge.median(new int[]{2, 2}), 0.0001);
        }

        @Test
        void testHugeNumbersDoNotOverflow() {
            assertEquals(Integer.MAX_VALUE - 1.0,
                    ComparatorSortChallenge.median(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE - 2}), 0.0001);
            assertEquals(Integer.MIN_VALUE + 1.0,
                    ComparatorSortChallenge.median(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 2}), 0.0001);
        }

        @Test
        void testTheInputIsNotModified() {
            int[] numbers = {9, 1, 2, 8, 5, 3};

            ComparatorSortChallenge.median(numbers);

            assertArrayEquals(new int[]{9, 1, 2, 8, 5, 3}, numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortedByLastLetter
    // ==========================================================
    @Nested
    class SortedByLastLetterTests {

        @Test
        void testSortsByTheLastCharacter() {
            assertArrayEquals(new String[]{"tea", "bird", "dog", "cat"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"dog", "cat", "bird", "tea"}));
        }

        @Test
        void testSameLastCharacterKeepsTheOriginalOrder() {
            assertArrayEquals(new String[]{"aa", "ab", "cb"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"ab", "cb", "aa"}));
            assertArrayEquals(new String[]{"a", "apple", "be"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"apple", "a", "be"}));
        }

        @Test
        void testAllWordsWithTheSameLastCharacter() {
            assertArrayEquals(new String[]{"zed", "bad", "mad"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"zed", "bad", "mad"}));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            assertArrayEquals(new String[]{"aB", "ba", "Ac"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"Ac", "ba", "aB"}));
        }

        @Test
        void testOneLetterWords() {
            assertArrayEquals(new String[]{"a", "b", "c"},
                    ComparatorSortChallenge.sortedByLastLetter(new String[]{"c", "a", "b"}));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new String[]{}, ComparatorSortChallenge.sortedByLastLetter(new String[]{}));
        }

        @Test
        void testReturnsANewArrayAndTheInputIsNotModified() {
            String[] input = {"dog", "cat", "bird", "tea"};

            String[] result = ComparatorSortChallenge.sortedByLastLetter(input);

            assertTrue(result != input);
            assertArrayEquals(new String[]{"dog", "cat", "bird", "tea"}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortedByGrade
    // ==========================================================
    @Nested
    class SortedByGradeTests {

        @Test
        void testHighestGradeFirst() {
            ComparatorSortChallenge.Student[] input = {
                    student("Ana", 70), student("Bob", 90), student("Cy", 70), student("Di", 85)};

            ComparatorSortChallenge.Student[] expected = {
                    student("Bob", 90), student("Di", 85), student("Ana", 70), student("Cy", 70)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGrade(input));
        }

        @Test
        void testSameGradeKeepsTheOriginalOrderEvenIfItIsNotAlphabetical() {
            ComparatorSortChallenge.Student[] input = {student("Zed", 80), student("Amy", 80), student("Max", 80)};

            ComparatorSortChallenge.Student[] expected = {student("Zed", 80), student("Amy", 80), student("Max", 80)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGrade(input));
        }

        @Test
        void testTiesAreNotReversed() {
            ComparatorSortChallenge.Student[] input = {
                    student("Bob", 60), student("Ana", 90), student("Cy", 60), student("Di", 90)};

            ComparatorSortChallenge.Student[] expected = {
                    student("Ana", 90), student("Di", 90), student("Bob", 60), student("Cy", 60)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGrade(input));
        }

        @Test
        void testAlreadySortedAndReversed() {
            ComparatorSortChallenge.Student[] high = {student("A", 100), student("B", 50), student("C", 0)};
            ComparatorSortChallenge.Student[] low = {student("C", 0), student("B", 50), student("A", 100)};

            assertArrayEquals(high, ComparatorSortChallenge.sortedByGrade(high));
            assertArrayEquals(high, ComparatorSortChallenge.sortedByGrade(low));
        }

        @Test
        void testEmptyAndSingle() {
            assertArrayEquals(new ComparatorSortChallenge.Student[]{},
                    ComparatorSortChallenge.sortedByGrade(new ComparatorSortChallenge.Student[]{}));
            ComparatorSortChallenge.Student[] one = {student("Ana", 70)};
            assertArrayEquals(one, ComparatorSortChallenge.sortedByGrade(one));
        }

        @Test
        void testReturnsANewArrayAndTheInputIsNotModified() {
            ComparatorSortChallenge.Student[] input = {student("Ana", 70), student("Bob", 90)};

            ComparatorSortChallenge.Student[] result = ComparatorSortChallenge.sortedByGrade(input);

            assertTrue(result != input);
            assertEquals(student("Ana", 70), input[0]);
            assertEquals(student("Bob", 90), input[1]);
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortedByGradeThenName
    // ==========================================================
    @Nested
    class SortedByGradeThenNameTests {

        @Test
        void testHighestGradeFirstThenAlphabetical() {
            ComparatorSortChallenge.Student[] input = {
                    student("Cy", 70), student("Bob", 90), student("Ana", 70), student("Di", 90)};

            ComparatorSortChallenge.Student[] expected = {
                    student("Bob", 90), student("Di", 90), student("Ana", 70), student("Cy", 70)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGradeThenName(input));
        }

        @Test
        void testAllWithTheSameGradeAreAlphabetical() {
            ComparatorSortChallenge.Student[] input = {student("Zed", 80), student("Amy", 80), student("Max", 80)};

            ComparatorSortChallenge.Student[] expected = {student("Amy", 80), student("Max", 80), student("Zed", 80)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGradeThenName(input));
        }

        @Test
        void testTheGradeIsMoreImportantThanTheName() {
            ComparatorSortChallenge.Student[] input = {student("Ana", 70), student("Zed", 95)};

            ComparatorSortChallenge.Student[] expected = {student("Zed", 95), student("Ana", 70)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGradeThenName(input));
        }

        @Test
        void testSameNameWithDifferentGrades() {
            ComparatorSortChallenge.Student[] input = {student("Ana", 70), student("Ana", 90)};

            ComparatorSortChallenge.Student[] expected = {student("Ana", 90), student("Ana", 70)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGradeThenName(input));
        }

        @Test
        void testUppercaseNamesComeBeforeLowercaseNames() {
            ComparatorSortChallenge.Student[] input = {student("bob", 80), student("Zed", 80)};

            ComparatorSortChallenge.Student[] expected = {student("Zed", 80), student("bob", 80)};
            assertArrayEquals(expected, ComparatorSortChallenge.sortedByGradeThenName(input));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(new ComparatorSortChallenge.Student[]{},
                    ComparatorSortChallenge.sortedByGradeThenName(new ComparatorSortChallenge.Student[]{}));
        }

        @Test
        void testReturnsANewArrayAndTheInputIsNotModified() {
            ComparatorSortChallenge.Student[] input = {student("Cy", 70), student("Bob", 90)};

            ComparatorSortChallenge.Student[] result = ComparatorSortChallenge.sortedByGradeThenName(input);

            assertTrue(result != input);
            assertEquals(student("Cy", 70), input[0]);
            assertEquals(student("Bob", 90), input[1]);
        }
    }

    // ==========================================================
    // CHALLENGE 9: closestTo
    // ==========================================================
    @Nested
    class ClosestToTests {

        @Test
        void testSortsByTheDistanceToTheTarget() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(10);

            assertArrayEquals(new Integer[]{10, 9, 8, 12, 15, 4}, sortedIntegers(comparator, 4, 12, 9, 15, 10, 8));
        }

        @Test
        void testEqualDistanceSmallerNumberFirst() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(0);

            assertArrayEquals(new Integer[]{-1, 2, -3, 3}, sortedIntegers(comparator, -3, 3, -1, 2));
        }

        @Test
        void testCompareReturnsTheRightSign() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(10);

            assertTrue(comparator.compare(9, 12) < 0);
            assertTrue(comparator.compare(12, 9) > 0);
            assertTrue(comparator.compare(8, 12) < 0);
            assertTrue(comparator.compare(12, 8) > 0);
            assertEquals(0, sign(comparator.compare(10, 10)));
        }

        @Test
        void testEqualNumbersAreEqualEvenWhenTheyAreDifferentObjects() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(0);

            assertEquals(0, sign(comparator.compare(Integer.valueOf(1000), Integer.valueOf(1000))));
            assertEquals(0, sign(comparator.compare(Integer.valueOf(-1000), Integer.valueOf(-1000))));
        }

        @Test
        void testNegativeTarget() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(-5);

            assertArrayEquals(new Integer[]{-5, -6, -4, -10, 0}, sortedIntegers(comparator, 0, -10, -4, -6, -5));
        }

        @Test
        void testRepeatedNumbers() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(3);

            assertArrayEquals(new Integer[]{3, 3, 1}, sortedIntegers(comparator, 3, 1, 3));
        }

        @Test
        void testTheOrderDoesNotDependOnTheOriginalPosition() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(50);

            assertArrayEquals(new Integer[]{50, 49, 51, 48, 52},
                    sortedIntegers(comparator, 52, 48, 51, 49, 50));
            assertArrayEquals(new Integer[]{50, 49, 51, 48, 52},
                    sortedIntegers(comparator, 50, 49, 51, 48, 52));
        }

        @Test
        void testTheTwoDirectionsAreConsistent() {
            Comparator<Integer> comparator = ComparatorSortChallenge.closestTo(7);
            int[] values = {-3, 0, 4, 6, 7, 8, 9, 14, 20};

            for (int a : values) {
                for (int b : values) {
                    assertEquals(sign(comparator.compare(a, b)), -sign(comparator.compare(b, a)));
                }
            }
        }
    }

    // ==========================================================
    // CHALLENGE 10: nullsLastAlphabetical
    // ==========================================================
    @Nested
    class NullsLastAlphabeticalTests {

        @Test
        void testNullsGoLast() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertArrayEquals(new String[]{"apple", "fig", "pear", null, null},
                    sortedStrings(comparator, "pear", null, "apple", null, "fig"));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertArrayEquals(new String[]{"B", "a", "b", null}, sortedStrings(comparator, "b", null, "B", "a"));
        }

        @Test
        void testCompareWithNull() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertTrue(comparator.compare(null, "a") > 0);
            assertTrue(comparator.compare("a", null) < 0);
            assertTrue(comparator.compare("", null) < 0);
            assertTrue(comparator.compare(null, "") > 0);
        }

        @Test
        void testTwoNullsAreEqual() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertEquals(0, sign(comparator.compare(null, null)));
        }

        @Test
        void testEqualStringsAreEqualEvenWhenTheyAreDifferentObjects() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertEquals(0, sign(comparator.compare(new String("abc"), new String("abc"))));
        }

        @Test
        void testNormalAlphabeticalOrderBetweenStrings() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertTrue(comparator.compare("apple", "banana") < 0);
            assertTrue(comparator.compare("banana", "apple") > 0);
            assertTrue(comparator.compare("app", "apple") < 0);
        }

        @Test
        void testWithoutNullsAndWithOnlyNulls() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertArrayEquals(new String[]{"a", "b", "c"}, sortedStrings(comparator, "c", "a", "b"));
            assertArrayEquals(new String[]{null, null}, sortedStrings(comparator, null, null));
            assertArrayEquals(new String[]{}, sortedStrings(comparator));
        }

        @Test
        void testNullInTheMiddleAndAtTheStart() {
            Comparator<String> comparator = ComparatorSortChallenge.nullsLastAlphabetical();

            assertArrayEquals(new String[]{"a", "z", null}, sortedStrings(comparator, null, "z", "a"));
            assertArrayEquals(new String[]{"a", "z", null}, sortedStrings(comparator, "z", null, "a"));
        }
    }
}