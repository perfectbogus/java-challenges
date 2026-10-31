package dev.perfectbogus.streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamsBasicsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: range
    // ==========================================================
    @Nested
    class RangeTests {

        @Test
        void testRange() {
            assertEquals(List.of(1, 2, 3, 4), StreamsBasicsChallenge.range(1, 4));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(-2, -1, 0, 1), StreamsBasicsChallenge.range(-2, 1));
        }

        @Test
        void testSingleNumber() {
            assertEquals(List.of(3), StreamsBasicsChallenge.range(3, 3));
        }

        @Test
        void testFromGreaterThanToGivesEmptyList() {
            assertEquals(List.of(), StreamsBasicsChallenge.range(5, 2));
        }

        @Test
        void testTheLastNumberIsIncluded() {
            List<Integer> result = StreamsBasicsChallenge.range(0, 10);
            assertEquals(11, result.size());
            assertEquals(10, result.get(10));
        }

        @Test
        void testTheFirstNumberIsIncluded() {
            assertEquals(7, StreamsBasicsChallenge.range(7, 9).get(0));
        }

        @Test
        void testOnlyNegativeNumbers() {
            assertEquals(List.of(-5, -4, -3), StreamsBasicsChallenge.range(-5, -3));
        }

        @Test
        void testFromZero() {
            assertEquals(List.of(0, 1), StreamsBasicsChallenge.range(0, 1));
        }

        @Test
        void testAroundTheMaximumValue() {
            assertEquals(List.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE),
                    StreamsBasicsChallenge.range(Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
        }

        @Test
        void testAroundTheMinimumValue() {
            assertEquals(List.of(Integer.MIN_VALUE, Integer.MIN_VALUE + 1),
                    StreamsBasicsChallenge.range(Integer.MIN_VALUE, Integer.MIN_VALUE + 1));
        }

        @Test
        void testBigRange() {
            List<Integer> result = StreamsBasicsChallenge.range(1, 10_000);
            assertEquals(10_000, result.size());
            assertEquals(10_000, result.get(9_999));
        }
    }

    // ==========================================================
    // CHALLENGE 2: uniqueUppercase
    // ==========================================================
    @Nested
    class UniqueUppercaseTests {

        @Test
        void testUniqueUppercase() {
            assertEquals(List.of("A", "B", "C"),
                    StreamsBasicsChallenge.uniqueUppercase(List.of("a", "b", "A", "c", "b")));
        }

        @Test
        void testAllEqual() {
            assertEquals(List.of("X"), StreamsBasicsChallenge.uniqueUppercase(List.of("x", "x", "x")));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamsBasicsChallenge.uniqueUppercase(List.of()));
        }

        @Test
        void testOrderOfFirstAppearance() {
            assertEquals(List.of("Z", "A", "M"),
                    StreamsBasicsChallenge.uniqueUppercase(List.of("z", "a", "m", "a", "z")));
        }

        @Test
        void testNotSorted() {
            assertEquals(List.of("B", "A"), StreamsBasicsChallenge.uniqueUppercase(List.of("b", "a")));
        }

        @Test
        void testDifferentCaseCountsAsTheSameWord() {
            assertEquals(List.of("HELLO"), StreamsBasicsChallenge.uniqueUppercase(List.of("Hello", "hELLO", "HELLO")));
        }

        @Test
        void testWordsAreFullyUppercase() {
            assertEquals(List.of("ONE TWO"), StreamsBasicsChallenge.uniqueUppercase(List.of("one two")));
        }

        @Test
        void testEmptyTexts() {
            assertEquals(List.of(""), StreamsBasicsChallenge.uniqueUppercase(List.of("", "")));
        }

        @Test
        void testSpacesMatter() {
            assertEquals(List.of("A", "A "), StreamsBasicsChallenge.uniqueUppercase(List.of("a", "a ")));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "A"));
            StreamsBasicsChallenge.uniqueUppercase(words);
            assertEquals(List.of("a", "A"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 3: flatten
    // ==========================================================
    @Nested
    class FlattenTests {

        @Test
        void testFlatten() {
            assertEquals(List.of("a", "b", "c", "d", "e"),
                    StreamsBasicsChallenge.flatten(List.of(List.of("a", "b"), List.of("c"), List.of("d", "e"))));
        }

        @Test
        void testEmptyInnerLists() {
            assertEquals(List.of("x"), StreamsBasicsChallenge.flatten(List.of(List.of(), List.of("x"), List.of())));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamsBasicsChallenge.flatten(List.of()));
        }

        @Test
        void testOnlyEmptyInnerLists() {
            assertEquals(List.of(), StreamsBasicsChallenge.flatten(List.of(List.of(), List.of())));
        }

        @Test
        void testOrderIsKept() {
            assertEquals(List.of("z", "y", "x", "w"),
                    StreamsBasicsChallenge.flatten(List.of(List.of("z", "y"), List.of("x", "w"))));
        }

        @Test
        void testRepeatedElementsAreKept() {
            assertEquals(List.of("a", "a", "a"),
                    StreamsBasicsChallenge.flatten(List.of(List.of("a"), List.of("a", "a"))));
        }

        @Test
        void testSingleInnerList() {
            assertEquals(List.of("a", "b"), StreamsBasicsChallenge.flatten(List.of(List.of("a", "b"))));
        }

        @Test
        void testManyElements() {
            List<String> inner = new ArrayList<>();
            for (int i = 0; i < 60; i++) {
                inner.add("e" + i);
            }
            List<String> result = StreamsBasicsChallenge.flatten(List.of(inner, inner, inner));
            assertEquals(180, result.size());
            assertEquals("e59", result.get(179));
        }

        @Test
        void testInnerListsAreNotModified() {
            List<String> inner = new ArrayList<>(List.of("a"));
            StreamsBasicsChallenge.flatten(List.of(inner, List.of("b")));
            assertEquals(List.of("a"), inner);
        }

        @Test
        void testResultIsNotTheInnerList() {
            List<String> inner = new ArrayList<>(List.of("a"));
            List<String> result = StreamsBasicsChallenge.flatten(List.of(inner));
            inner.add("later");
            assertEquals(List.of("a"), result);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sumOfSquares
    // ==========================================================
    @Nested
    class SumOfSquaresTests {

        @Test
        void testSumOfSquares() {
            assertEquals(14, StreamsBasicsChallenge.sumOfSquares(List.of(1, 2, 3)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(18, StreamsBasicsChallenge.sumOfSquares(List.of(-3, 3)));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, StreamsBasicsChallenge.sumOfSquares(List.of()));
        }

        @Test
        void testSingleNumber() {
            assertEquals(49, StreamsBasicsChallenge.sumOfSquares(List.of(7)));
        }

        @Test
        void testZeros() {
            assertEquals(0, StreamsBasicsChallenge.sumOfSquares(List.of(0, 0)));
        }

        @Test
        void testIsNotTheSquareOfTheSum() {
            assertEquals(2, StreamsBasicsChallenge.sumOfSquares(List.of(1, -1)));
        }

        @Test
        void testBiggestNumbers() {
            assertEquals(2_000_000, StreamsBasicsChallenge.sumOfSquares(List.of(1000, -1000)));
        }

        @Test
        void testManyNumbers() {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                numbers.add(1000);
            }
            assertEquals(1_000_000_000, StreamsBasicsChallenge.sumOfSquares(numbers));
        }

        @Test
        void testIsNotTheSumOfTheAbsoluteValues() {
            assertEquals(25, StreamsBasicsChallenge.sumOfSquares(List.of(-5)));
        }
    }

    // ==========================================================
    // CHALLENGE 5: average
    // ==========================================================
    @Nested
    class AverageTests {

        @Test
        void testDecimals() {
            assertEquals(2.3333, StreamsBasicsChallenge.average(List.of(1, 2, 4)), 0.001);
        }

        @Test
        void testHalf() {
            assertEquals(1.5, StreamsBasicsChallenge.average(List.of(1, 2)), 0.0001);
        }

        @Test
        void testEmptyList() {
            assertEquals(0.0, StreamsBasicsChallenge.average(List.of()), 0.0);
        }

        @Test
        void testSingleNumber() {
            assertEquals(7.0, StreamsBasicsChallenge.average(List.of(7)), 0.0001);
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-0.5, StreamsBasicsChallenge.average(List.of(-3, 2)), 0.0001);
        }

        @Test
        void testBigNumbersDoNotOverflow() {
            assertEquals(2_000_000_000.0, StreamsBasicsChallenge.average(List.of(2_000_000_000, 2_000_000_000)),
                    0.0001);
        }

        @Test
        void testIntegerDivisionIsNotUsed() {
            assertEquals(0.5, StreamsBasicsChallenge.average(List.of(0, 1)), 0.0001);
        }

        @Test
        void testManyNumbers() {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                numbers.add(i);
            }
            assertEquals(50.5, StreamsBasicsChallenge.average(numbers), 0.0001);
        }

        @Test
        void testAllZeros() {
            assertEquals(0.0, StreamsBasicsChallenge.average(List.of(0, 0, 0)), 0.0);
        }
    }

    // ==========================================================
    // CHALLENGE 6: powersOfTwo
    // ==========================================================
    @Nested
    class PowersOfTwoTests {

        @Test
        void testFivePowers() {
            assertEquals(List.of(1, 2, 4, 8, 16), StreamsBasicsChallenge.powersOfTwo(5));
        }

        @Test
        void testOnePower() {
            assertEquals(List.of(1), StreamsBasicsChallenge.powersOfTwo(1));
        }

        @Test
        void testZeroPowers() {
            assertEquals(List.of(), StreamsBasicsChallenge.powersOfTwo(0));
        }

        @Test
        void testTwoPowers() {
            assertEquals(List.of(1, 2), StreamsBasicsChallenge.powersOfTwo(2));
        }

        @Test
        void testTheSizeIsCount() {
            assertEquals(10, StreamsBasicsChallenge.powersOfTwo(10).size());
        }

        @Test
        void testLastOfTen() {
            List<Integer> result = StreamsBasicsChallenge.powersOfTwo(10);
            assertEquals(512, result.get(9));
        }

        @Test
        void testMaximumCount() {
            List<Integer> result = StreamsBasicsChallenge.powersOfTwo(30);
            assertEquals(30, result.size());
            assertEquals(1 << 29, result.get(29));
        }

        @Test
        void testEachNumberIsTheDoubleOfThePrevious() {
            List<Integer> result = StreamsBasicsChallenge.powersOfTwo(20);
            for (int i = 1; i < result.size(); i++) {
                assertEquals(result.get(i - 1) * 2, result.get(i));
            }
        }

        @Test
        void testCallsAreIndependent() {
            assertEquals(List.of(1, 2, 4), StreamsBasicsChallenge.powersOfTwo(3));
            assertEquals(List.of(1, 2, 4), StreamsBasicsChallenge.powersOfTwo(3));
        }
    }

    // ==========================================================
    // CHALLENGE 7: noneNegative
    // ==========================================================
    @Nested
    class NoneNegativeTests {

        @Test
        void testNoNegatives() {
            assertTrue(StreamsBasicsChallenge.noneNegative(List.of(0, 3, 7)));
        }

        @Test
        void testWithANegative() {
            assertFalse(StreamsBasicsChallenge.noneNegative(List.of(4, -1, 2)));
        }

        @Test
        void testEmptyList() {
            assertTrue(StreamsBasicsChallenge.noneNegative(List.of()));
        }

        @Test
        void testZeroIsNotNegative() {
            assertTrue(StreamsBasicsChallenge.noneNegative(List.of(0)));
        }

        @Test
        void testNegativeAtTheStart() {
            assertFalse(StreamsBasicsChallenge.noneNegative(List.of(-1, 5, 6)));
        }

        @Test
        void testNegativeAtTheEnd() {
            assertFalse(StreamsBasicsChallenge.noneNegative(List.of(5, 6, -1)));
        }

        @Test
        void testOnlyNegatives() {
            assertFalse(StreamsBasicsChallenge.noneNegative(List.of(-1, -2)));
        }

        @Test
        void testMinimumValue() {
            assertFalse(StreamsBasicsChallenge.noneNegative(List.of(1, Integer.MIN_VALUE)));
        }

        @Test
        void testMaximumValue() {
            assertTrue(StreamsBasicsChallenge.noneNegative(List.of(Integer.MAX_VALUE)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: countWords
    // ==========================================================
    @Nested
    class CountWordsTests {

        @Test
        void testCountsAllTheWords() {
            assertEquals(3, StreamsBasicsChallenge.countWords(List.of("one two", "three")));
        }

        @Test
        void testEmptySentenceHasNoWords() {
            assertEquals(5, StreamsBasicsChallenge.countWords(List.of("a b c d", "", "e")));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, StreamsBasicsChallenge.countWords(List.of()));
        }

        @Test
        void testOnlyEmptySentences() {
            assertEquals(0, StreamsBasicsChallenge.countWords(List.of("", "")));
        }

        @Test
        void testOneWord() {
            assertEquals(1, StreamsBasicsChallenge.countWords(List.of("solo")));
        }

        @Test
        void testRepeatedWordsCountEachTime() {
            assertEquals(4, StreamsBasicsChallenge.countWords(List.of("a a", "a a")));
        }

        @Test
        void testManySentences() {
            List<String> sentences = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                sentences.add("x y z");
            }
            assertEquals(300, StreamsBasicsChallenge.countWords(sentences));
        }

        @Test
        void testDifferentLengthSentences() {
            assertEquals(6, StreamsBasicsChallenge.countWords(List.of("a", "b c", "d e f")));
        }

        @Test
        void testLongWords() {
            assertEquals(2, StreamsBasicsChallenge.countWords(List.of("extraordinarily long")));
        }
    }

    // ==========================================================
    // CHALLENGE 9: product
    // ==========================================================
    @Nested
    class ProductTests {

        @Test
        void testProduct() {
            assertEquals(24, StreamsBasicsChallenge.product(List.of(2, 3, 4)));
        }

        @Test
        void testWithZero() {
            assertEquals(0, StreamsBasicsChallenge.product(List.of(5, 0, 9)));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(-6, StreamsBasicsChallenge.product(List.of(-2, 3)));
        }

        @Test
        void testEmptyListGivesOne() {
            assertEquals(1, StreamsBasicsChallenge.product(List.of()));
        }

        @Test
        void testSingleNumber() {
            assertEquals(7, StreamsBasicsChallenge.product(List.of(7)));
        }

        @Test
        void testTwoNegativeNumbers() {
            assertEquals(6, StreamsBasicsChallenge.product(List.of(-2, -3)));
        }

        @Test
        void testOnes() {
            assertEquals(1, StreamsBasicsChallenge.product(List.of(1, 1, 1)));
        }

        @Test
        void testIsNotTheSum() {
            assertEquals(4, StreamsBasicsChallenge.product(List.of(2, 2)));
            assertEquals(9, StreamsBasicsChallenge.product(List.of(3, 3)));
        }

        @Test
        void testBiggerProduct() {
            assertEquals(3_628_800, StreamsBasicsChallenge.product(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        }

        @Test
        void testTheFirstNumberCounts() {
            assertEquals(6, StreamsBasicsChallenge.product(List.of(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3)));
        }

        @Test
        void testSingleZero() {
            assertEquals(0, StreamsBasicsChallenge.product(List.of(0)));
        }
    }

    // ==========================================================
    // CHALLENGE 10: indicesOf
    // ==========================================================
    @Nested
    class IndicesOfTests {

        @Test
        void testSeveralPositions() {
            assertEquals(List.of(0, 2, 4), StreamsBasicsChallenge.indicesOf(List.of("a", "b", "a", "c", "a"), "a"));
        }

        @Test
        void testNotFound() {
            assertEquals(List.of(), StreamsBasicsChallenge.indicesOf(List.of("a", "b"), "z"));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), StreamsBasicsChallenge.indicesOf(List.of(), "a"));
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of(1), StreamsBasicsChallenge.indicesOf(List.of("A", "a", "B"), "a"));
        }

        @Test
        void testTheResultAreThePositionsNotTheWords() {
            assertEquals(List.of(3), StreamsBasicsChallenge.indicesOf(List.of("x", "y", "z", "t"), "t"));
        }

        @Test
        void testEveryPosition() {
            assertEquals(List.of(0, 1, 2), StreamsBasicsChallenge.indicesOf(List.of("a", "a", "a"), "a"));
        }

        @Test
        void testSinglePosition() {
            assertEquals(List.of(0), StreamsBasicsChallenge.indicesOf(List.of("a"), "a"));
        }

        @Test
        void testLastPosition() {
            assertEquals(List.of(2), StreamsBasicsChallenge.indicesOf(List.of("b", "c", "a"), "a"));
        }

        @Test
        void testEmptyTextAsTarget() {
            assertEquals(List.of(1), StreamsBasicsChallenge.indicesOf(List.of("a", "", "b"), ""));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "b", "a"));
            StreamsBasicsChallenge.indicesOf(words, "a");
            assertEquals(List.of("a", "b", "a"), words);
        }
    }
}