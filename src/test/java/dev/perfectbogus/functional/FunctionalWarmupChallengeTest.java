package dev.perfectbogus.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalWarmupChallengeTest {

    // ==========================================================
    // CHALLENGE 1: adder
    // ==========================================================
    @Nested
    class AdderTests {

        @Test
        void testAddsN() {
            assertEquals(8, FunctionalWarmupChallenge.adder(5).apply(3));
        }

        @Test
        void testNegativeN() {
            assertEquals(8, FunctionalWarmupChallenge.adder(-2).apply(10));
        }

        @Test
        void testZero() {
            assertEquals(7, FunctionalWarmupChallenge.adder(0).apply(7));
        }

        @Test
        void testTheSameFunctionCanBeUsedManyTimes() {
            Function<Integer, Integer> add10 = FunctionalWarmupChallenge.adder(10);
            assertEquals(11, add10.apply(1));
            assertEquals(12, add10.apply(2));
            assertEquals(11, add10.apply(1));
        }

        @Test
        void testTwoAddersAreIndependent() {
            Function<Integer, Integer> add1 = FunctionalWarmupChallenge.adder(1);
            Function<Integer, Integer> add100 = FunctionalWarmupChallenge.adder(100);
            assertEquals(6, add1.apply(5));
            assertEquals(105, add100.apply(5));
            assertEquals(6, add1.apply(5));
        }

        @Test
        void testNegativeInput() {
            assertEquals(-5, FunctionalWarmupChallenge.adder(3).apply(-8));
        }

        @Test
        void testResultIsNotAlwaysTheSame() {
            Function<Integer, Integer> add4 = FunctionalWarmupChallenge.adder(4);
            assertNotEquals(add4.apply(1), add4.apply(2));
        }
    }

    // ==========================================================
    // CHALLENGE 2: applyTwice
    // ==========================================================
    @Nested
    class ApplyTwiceTests {

        @Test
        void testAddition() {
            assertEquals(7, FunctionalWarmupChallenge.applyTwice(x -> x + 3, 1));
        }

        @Test
        void testSquare() {
            assertEquals(81, FunctionalWarmupChallenge.applyTwice(x -> x * x, 3));
        }

        @Test
        void testSubtraction() {
            assertEquals(-2, FunctionalWarmupChallenge.applyTwice(x -> x - 1, 0));
        }

        @Test
        void testDoubling() {
            assertEquals(20, FunctionalWarmupChallenge.applyTwice(x -> x * 2, 5));
        }

        @Test
        void testIdentity() {
            assertEquals(9, FunctionalWarmupChallenge.applyTwice(x -> x, 9));
        }

        @Test
        void testFunctionIsCalledExactlyTwice() {
            int[] calls = {0};
            FunctionalWarmupChallenge.applyTwice(x -> {
                calls[0]++;
                return x + 1;
            }, 0);
            assertEquals(2, calls[0]);
        }

        @Test
        void testSecondCallReceivesTheFirstResult() {
            List<Integer> received = new ArrayList<>();
            FunctionalWarmupChallenge.applyTwice(x -> {
                received.add(x);
                return x * 10 + 1;
            }, 4);
            assertEquals(List.of(4, 41), received);
        }

        @Test
        void testFunctionThatIgnoresItsInput() {
            assertEquals(5, FunctionalWarmupChallenge.applyTwice(x -> 5, 100));
        }
    }

    // ==========================================================
    // CHALLENGE 3: trimmedLength
    // ==========================================================
    @Nested
    class TrimmedLengthTests {

        @Test
        void testSpacesAtBothEnds() {
            assertEquals(2, FunctionalWarmupChallenge.trimmedLength().apply("  hi  "));
        }

        @Test
        void testSpacesInsideAreKept() {
            assertEquals(3, FunctionalWarmupChallenge.trimmedLength().apply(" a b "));
        }

        @Test
        void testOnlySpaces() {
            assertEquals(0, FunctionalWarmupChallenge.trimmedLength().apply("   "));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, FunctionalWarmupChallenge.trimmedLength().apply(""));
        }

        @Test
        void testNoSpaces() {
            assertEquals(5, FunctionalWarmupChallenge.trimmedLength().apply("hello"));
        }

        @Test
        void testSpacesOnlyAtTheStart() {
            assertEquals(3, FunctionalWarmupChallenge.trimmedLength().apply("    abc"));
        }

        @Test
        void testSpacesOnlyAtTheEnd() {
            assertEquals(3, FunctionalWarmupChallenge.trimmedLength().apply("abc    "));
        }

        @Test
        void testTheFunctionCanBeReused() {
            Function<String, Integer> f = FunctionalWarmupChallenge.trimmedLength();
            assertEquals(1, f.apply(" x "));
            assertEquals(4, f.apply("word"));
            assertEquals(1, f.apply("x"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: inRange
    // ==========================================================
    @Nested
    class InRangeTests {

        @Test
        void testInside() {
            assertTrue(FunctionalWarmupChallenge.inRange(1, 5).test(3));
        }

        @Test
        void testLowIsIncluded() {
            assertTrue(FunctionalWarmupChallenge.inRange(1, 5).test(1));
        }

        @Test
        void testHighIsIncluded() {
            assertTrue(FunctionalWarmupChallenge.inRange(1, 5).test(5));
        }

        @Test
        void testJustBelow() {
            assertFalse(FunctionalWarmupChallenge.inRange(1, 5).test(0));
        }

        @Test
        void testJustAbove() {
            assertFalse(FunctionalWarmupChallenge.inRange(1, 5).test(6));
        }

        @Test
        void testNegativeRange() {
            assertTrue(FunctionalWarmupChallenge.inRange(-3, -1).test(-2));
            assertFalse(FunctionalWarmupChallenge.inRange(-3, -1).test(0));
            assertFalse(FunctionalWarmupChallenge.inRange(-3, -1).test(-4));
        }

        @Test
        void testSingleNumberRange() {
            assertTrue(FunctionalWarmupChallenge.inRange(4, 4).test(4));
            assertFalse(FunctionalWarmupChallenge.inRange(4, 4).test(3));
            assertFalse(FunctionalWarmupChallenge.inRange(4, 4).test(5));
        }

        @Test
        void testFarOutside() {
            assertFalse(FunctionalWarmupChallenge.inRange(10, 20).test(1000));
            assertFalse(FunctionalWarmupChallenge.inRange(10, 20).test(-1000));
        }

        @Test
        void testRangeAcrossZero() {
            assertTrue(FunctionalWarmupChallenge.inRange(-2, 2).test(0));
            assertTrue(FunctionalWarmupChallenge.inRange(-2, 2).test(-2));
            assertTrue(FunctionalWarmupChallenge.inRange(-2, 2).test(2));
        }
    }

    // ==========================================================
    // CHALLENGE 5: goodWord
    // ==========================================================
    @Nested
    class GoodWordTests {

        @Test
        void testGoodWord() {
            assertTrue(FunctionalWarmupChallenge.goodWord().test("cat"));
        }

        @Test
        void testTooShort() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test("ca"));
        }

        @Test
        void testStartsWithLowercaseX() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test("xray"));
        }

        @Test
        void testStartsWithUppercaseX() {
            assertTrue(FunctionalWarmupChallenge.goodWord().test("Xray"));
        }

        @Test
        void testContainsXButDoesNotStartWithIt() {
            assertTrue(FunctionalWarmupChallenge.goodWord().test("axe"));
            assertTrue(FunctionalWarmupChallenge.goodWord().test("box"));
        }

        @Test
        void testEmptyText() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test(""));
        }

        @Test
        void testSingleX() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test("x"));
        }

        @Test
        void testExactlyThreeCharacters() {
            assertTrue(FunctionalWarmupChallenge.goodWord().test("abc"));
        }

        @Test
        void testTwoCharactersNotX() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test("ab"));
        }

        @Test
        void testShortAndStartsWithX() {
            assertFalse(FunctionalWarmupChallenge.goodWord().test("xy"));
        }

        @Test
        void testLongWord() {
            assertTrue(FunctionalWarmupChallenge.goodWord().test("extraordinary"));
            assertFalse(FunctionalWarmupChallenge.goodWord().test("xylophone"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: keepIf
    // ==========================================================
    @Nested
    class KeepIfTests {

        @Test
        void testKeepsTheMatchingItems() {
            assertEquals(List.of("bb", "dd"),
                    FunctionalWarmupChallenge.keepIf(List.of("a", "bb", "ccc", "dd"), s -> s.length() == 2));
        }

        @Test
        void testNothingMatches() {
            assertEquals(List.of(), FunctionalWarmupChallenge.keepIf(List.of("a", "b"), s -> false));
        }

        @Test
        void testEverythingMatches() {
            assertEquals(List.of("a", "b"), FunctionalWarmupChallenge.keepIf(List.of("a", "b"), s -> true));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), FunctionalWarmupChallenge.keepIf(List.of(), s -> true));
        }

        @Test
        void testOrderIsKept() {
            assertEquals(List.of("z", "m", "a"),
                    FunctionalWarmupChallenge.keepIf(List.of("z", "x", "m", "y", "a"),
                            s -> s.equals("z") || s.equals("m") || s.equals("a")));
        }

        @Test
        void testDuplicatesAreKept() {
            assertEquals(List.of("a", "a"), FunctionalWarmupChallenge.keepIf(List.of("a", "b", "a"),
                    s -> s.equals("a")));
        }

        @Test
        void testTestIsCalledOncePerItem() {
            int[] calls = {0};
            FunctionalWarmupChallenge.keepIf(List.of("a", "b", "c", "d"), s -> {
                calls[0]++;
                return s.equals("b");
            });
            assertEquals(4, calls[0]);
        }

        @Test
        void testOriginalListIsNotModified() {
            List<String> items = new ArrayList<>(List.of("a", "bb", "ccc"));
            FunctionalWarmupChallenge.keepIf(items, s -> s.length() > 1);
            assertEquals(List.of("a", "bb", "ccc"), items);
        }

        @Test
        void testResultIsANewList() {
            List<String> items = new ArrayList<>(List.of("a", "b"));
            List<String> result = FunctionalWarmupChallenge.keepIf(items, s -> true);
            assertNotSame(items, result);
        }

        @Test
        void testTestReceivesTheItems() {
            List<String> seen = new ArrayList<>();
            FunctionalWarmupChallenge.keepIf(List.of("p", "q", "r"), s -> {
                seen.add(s);
                return true;
            });
            assertEquals(List.of("p", "q", "r"), seen);
        }
    }

    // ==========================================================
    // CHALLENGE 7: pipeline
    // ==========================================================
    @Nested
    class PipelineTests {

        @Test
        void testAddThenDouble() {
            List<Function<Integer, Integer>> steps = List.of(x -> x + 1, x -> x * 2);
            assertEquals(8, FunctionalWarmupChallenge.pipeline(steps).apply(3));
        }

        @Test
        void testDoubleThenAdd() {
            List<Function<Integer, Integer>> steps = List.of(x -> x * 2, x -> x + 1);
            assertEquals(7, FunctionalWarmupChallenge.pipeline(steps).apply(3));
        }

        @Test
        void testNoStepsGivesTheSameNumber() {
            assertEquals(3, FunctionalWarmupChallenge.pipeline(List.of()).apply(3));
            assertEquals(-9, FunctionalWarmupChallenge.pipeline(List.of()).apply(-9));
        }

        @Test
        void testSingleStep() {
            List<Function<Integer, Integer>> steps = List.of(x -> x - 10);
            assertEquals(-5, FunctionalWarmupChallenge.pipeline(steps).apply(5));
        }

        @Test
        void testThreeSteps() {
            List<Function<Integer, Integer>> steps = List.of(x -> x - 1, x -> x * 5, x -> x - 3);
            assertEquals(17, FunctionalWarmupChallenge.pipeline(steps).apply(5));
        }

        @Test
        void testThePipelineCanBeUsedManyTimes() {
            List<Function<Integer, Integer>> steps = List.of(x -> x + 1, x -> x * 2);
            Function<Integer, Integer> f = FunctionalWarmupChallenge.pipeline(steps);
            assertEquals(8, f.apply(3));
            assertEquals(0, f.apply(-1));
            assertEquals(8, f.apply(3));
        }

        @Test
        void testEachStepReceivesTheResultOfThePreviousOne() {
            List<Integer> seen = new ArrayList<>();
            List<Function<Integer, Integer>> steps = List.of(
                    x -> {
                        seen.add(x);
                        return x + 10;
                    },
                    x -> {
                        seen.add(x);
                        return x + 100;
                    },
                    x -> {
                        seen.add(x);
                        return x + 1000;
                    });
            assertEquals(1111, FunctionalWarmupChallenge.pipeline(steps).apply(1));
            assertEquals(List.of(1, 11, 111), seen);
        }

        @Test
        void testSubtractionShowsTheOrder() {
            List<Function<Integer, Integer>> steps = List.of(x -> 10 - x, x -> x * x);
            assertEquals(49, FunctionalWarmupChallenge.pipeline(steps).apply(3));
        }

        @Test
        void testStepsListIsNotModified() {
            List<Function<Integer, Integer>> steps = new ArrayList<>(List.of(x -> x + 1, x -> x * 2));
            FunctionalWarmupChallenge.pipeline(steps).apply(1);
            assertEquals(2, steps.size());
        }
    }

    // ==========================================================
    // CHALLENGE 8: fold
    // ==========================================================
    @Nested
    class FoldTests {

        @Test
        void testSum() {
            assertEquals(6, FunctionalWarmupChallenge.fold(List.of(1, 2, 3), 0, (acc, n) -> acc + n));
        }

        @Test
        void testSubtractionShowsTheOrder() {
            assertEquals(4, FunctionalWarmupChallenge.fold(List.of(1, 2, 3), 10, (acc, n) -> acc - n));
        }

        @Test
        void testBuildsANumberFromTheDigits() {
            assertEquals(123, FunctionalWarmupChallenge.fold(List.of(1, 2, 3), 0, (acc, n) -> acc * 10 + n));
        }

        @Test
        void testEmptyListGivesStart() {
            assertEquals(7, FunctionalWarmupChallenge.fold(List.of(), 7, (acc, n) -> acc + n));
        }

        @Test
        void testStartIsUsed() {
            assertEquals(106, FunctionalWarmupChallenge.fold(List.of(1, 2, 3), 100, (acc, n) -> acc + n));
        }

        @Test
        void testProduct() {
            assertEquals(24, FunctionalWarmupChallenge.fold(List.of(2, 3, 4), 1, (acc, n) -> acc * n));
        }

        @Test
        void testSingleNumber() {
            assertEquals(15, FunctionalWarmupChallenge.fold(List.of(5), 10, (acc, n) -> acc + n));
        }

        @Test
        void testOpReceivesTheAccumulatorFirst() {
            assertEquals(-4, FunctionalWarmupChallenge.fold(List.of(1, 3), 0, (acc, n) -> acc - n));
        }

        @Test
        void testOpIsCalledOnceForEachNumber() {
            int[] calls = {0};
            FunctionalWarmupChallenge.fold(List.of(4, 5, 6, 7), 0, (acc, n) -> {
                calls[0]++;
                return acc + n;
            });
            assertEquals(4, calls[0]);
        }

        @Test
        void testMaximumWithAStart() {
            assertEquals(9, FunctionalWarmupChallenge.fold(List.of(3, 9, 2), Integer.MIN_VALUE, Math::max));
        }

        @Test
        void testListIsNotModified() {
            List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
            FunctionalWarmupChallenge.fold(numbers, 0, (acc, n) -> acc + n);
            assertEquals(List.of(1, 2, 3), numbers);
        }
    }

    // ==========================================================
    // CHALLENGE 9: counter
    // ==========================================================
    @Nested
    class CounterTests {

        @Test
        void testFirstValueIsOne() {
            assertEquals(1, FunctionalWarmupChallenge.counter().get());
        }

        @Test
        void testCountsUp() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            assertEquals(1, c.get());
            assertEquals(2, c.get());
            assertEquals(3, c.get());
        }

        @Test
        void testNewCounterStartsAgain() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            c.get();
            c.get();
            Supplier<Integer> d = FunctionalWarmupChallenge.counter();
            assertEquals(1, d.get());
        }

        @Test
        void testCountersAreIndependent() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            Supplier<Integer> d = FunctionalWarmupChallenge.counter();
            assertEquals(1, c.get());
            assertEquals(2, c.get());
            assertEquals(1, d.get());
            assertEquals(3, c.get());
            assertEquals(2, d.get());
        }

        @Test
        void testManyCalls() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            int last = 0;
            for (int i = 0; i < 1000; i++) {
                last = c.get();
            }
            assertEquals(1000, last);
        }

        @Test
        void testOldCounterKeepsItsValueWhenANewOneIsCreated() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            c.get();
            FunctionalWarmupChallenge.counter().get();
            assertEquals(2, c.get());
        }

        @Test
        void testEveryValueIsDifferent() {
            Supplier<Integer> c = FunctionalWarmupChallenge.counter();
            int first = c.get();
            int second = c.get();
            assertNotEquals(first, second);
            assertEquals(1, second - first);
        }
    }

    // ==========================================================
    // CHALLENGE 10: partial
    // ==========================================================
    @Nested
    class PartialTests {

        @Test
        void testFirstArgumentIsFixed() {
            assertEquals(7, FunctionalWarmupChallenge.partial((a, b) -> a - b, 10).apply(3));
        }

        @Test
        void testOrderOfTheArguments() {
            assertEquals(-7, FunctionalWarmupChallenge.partial((a, b) -> a - b, 3).apply(10));
        }

        @Test
        void testMultiplication() {
            assertEquals(20, FunctionalWarmupChallenge.partial((a, b) -> a * b, 4).apply(5));
        }

        @Test
        void testTheFunctionCanBeUsedManyTimes() {
            Function<Integer, Integer> f = FunctionalWarmupChallenge.partial((a, b) -> a * 10 + b, 5);
            assertEquals(51, f.apply(1));
            assertEquals(59, f.apply(9));
            assertEquals(51, f.apply(1));
        }

        @Test
        void testTwoPartialsAreIndependent() {
            Function<Integer, Integer> f = FunctionalWarmupChallenge.partial((a, b) -> a - b, 100);
            Function<Integer, Integer> g = FunctionalWarmupChallenge.partial((a, b) -> a - b, 50);
            assertEquals(99, f.apply(1));
            assertEquals(49, g.apply(1));
            assertEquals(99, f.apply(1));
        }

        @Test
        void testFReceivesBothArguments() {
            List<Integer> seen = new ArrayList<>();
            FunctionalWarmupChallenge.partial((a, b) -> {
                seen.add(a);
                seen.add(b);
                return 0;
            }, 8).apply(2);
            assertEquals(List.of(8, 2), seen);
        }

        @Test
        void testFIsNotCalledUntilTheFunctionIsUsed() {
            int[] calls = {0};
            FunctionalWarmupChallenge.partial((a, b) -> {
                calls[0]++;
                return a + b;
            }, 1);
            assertEquals(0, calls[0]);
        }

        @Test
        void testFIsCalledOncePerUse() {
            int[] calls = {0};
            Function<Integer, Integer> f = FunctionalWarmupChallenge.partial((a, b) -> {
                calls[0]++;
                return a + b;
            }, 1);
            f.apply(1);
            f.apply(2);
            assertEquals(2, calls[0]);
        }

        @Test
        void testAdditionWithZero() {
            assertEquals(6, FunctionalWarmupChallenge.partial((a, b) -> a + b, 0).apply(6));
        }

        @Test
        void testNegativeFirstArgument() {
            assertEquals(-13, FunctionalWarmupChallenge.partial((a, b) -> a - b, -10).apply(3));
        }
    }
}