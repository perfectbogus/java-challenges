package dev.perfectbogus.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalProgrammingIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: applyAndThen
    // ==========================================================
    @Nested
    class ApplyAndThenTests {

        @Test
        void testAddThenMultiply() {
            Function<Integer, Integer> f = x -> x + 1;
            Function<Integer, Integer> g = x -> x * 2;
            assertEquals(8, FunctionalProgrammingIntermediateChallenge.applyAndThen(f, g, 3));
        }

        @Test
        void testSquareThenAddTen() {
            Function<Integer, Integer> f = x -> x * x;
            Function<Integer, Integer> g = x -> x + 10;
            assertEquals(26, FunctionalProgrammingIntermediateChallenge.applyAndThen(f, g, 4));
        }

        @Test
        void testIdentityThenSubtractOne() {
            Function<Integer, Integer> f = Function.identity();
            Function<Integer, Integer> g = x -> x - 1;
            assertEquals(4, FunctionalProgrammingIntermediateChallenge.applyAndThen(f, g, 5));
        }
    }

    // ==========================================================
    // CHALLENGE 2: applyCompose
    // ==========================================================
    @Nested
    class ApplyComposeTests {

        @Test
        void testAddThenMultiplyComposed() {
            Function<Integer, Integer> f = x -> x + 1;
            Function<Integer, Integer> g = x -> x * 2;
            assertEquals(7, FunctionalProgrammingIntermediateChallenge.applyCompose(f, g, 3));
        }

        @Test
        void testSquareAfterAddTen() {
            Function<Integer, Integer> f = x -> x * x;
            Function<Integer, Integer> g = x -> x + 10;
            assertEquals(196, FunctionalProgrammingIntermediateChallenge.applyCompose(f, g, 4));
        }

        @Test
        void testOrderDiffersFromAndThen() {
            Function<Integer, Integer> f = x -> x + 1;
            Function<Integer, Integer> g = x -> x * 2;
            // andThen(f,g,3) == 8, compose should differ
            assertEquals(7, FunctionalProgrammingIntermediateChallenge.applyCompose(f, g, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 3: curriedAdd
    // ==========================================================
    @Nested
    class CurriedAddTests {

        @Test
        void testPositiveAddends() {
            assertEquals(7, FunctionalProgrammingIntermediateChallenge.curriedAdd().apply(3).apply(4));
        }

        @Test
        void testMixedSignAddends() {
            assertEquals(3, FunctionalProgrammingIntermediateChallenge.curriedAdd().apply(-2).apply(5));
        }

        @Test
        void testZeros() {
            assertEquals(0, FunctionalProgrammingIntermediateChallenge.curriedAdd().apply(0).apply(0));
        }
    }

    // ==========================================================
    // CHALLENGE 4: partiallyApply
    // ==========================================================
    @Nested
    class PartiallyApplyTests {

        @Test
        void testFixedFirstArgSum() {
            Function<Integer, Integer> add10 = FunctionalProgrammingIntermediateChallenge.partiallyApply(Integer::sum, 10);
            assertEquals(15, add10.apply(5));
        }

        @Test
        void testFixedFirstArgMultiply() {
            Function<Integer, Integer> times4 = FunctionalProgrammingIntermediateChallenge.partiallyApply((a, b) -> a * b, 4);
            assertEquals(24, times4.apply(6));
        }

        @Test
        void testFixedFirstArgSubtract() {
            Function<Integer, Integer> tenMinus = FunctionalProgrammingIntermediateChallenge.partiallyApply((a, b) -> a - b, 10);
            assertEquals(7, tenMinus.apply(3));
        }
    }

    // ==========================================================
    // CHALLENGE 5: combineAnd
    // ==========================================================
    @Nested
    class CombineAndTests {
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;

        @Test
        void testPositiveAndEven() {
            assertTrue(FunctionalProgrammingIntermediateChallenge.combineAnd(isPositive, isEven).test(4));
        }

        @Test
        void testPositiveButOdd() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.combineAnd(isPositive, isEven).test(3));
        }

        @Test
        void testEvenButNegative() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.combineAnd(isPositive, isEven).test(-4));
        }
    }

    // ==========================================================
    // CHALLENGE 6: combineOr
    // ==========================================================
    @Nested
    class CombineOrTests {
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;

        @Test
        void testEvenButNegativeIsTrue() {
            assertTrue(FunctionalProgrammingIntermediateChallenge.combineOr(isPositive, isEven).test(-4));
        }

        @Test
        void testPositiveButOddIsTrue() {
            assertTrue(FunctionalProgrammingIntermediateChallenge.combineOr(isPositive, isEven).test(3));
        }

        @Test
        void testNeitherIsFalse() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.combineOr(isPositive, isEven).test(-3));
        }
    }

    // ==========================================================
    // CHALLENGE 7: negate
    // ==========================================================
    @Nested
    class NegateTests {
        Predicate<Integer> isPositive = n -> n > 0;

        @Test
        void testNegativeBecomesTrue() {
            assertTrue(FunctionalProgrammingIntermediateChallenge.negate(isPositive).test(-1));
        }

        @Test
        void testPositiveBecomesFalse() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.negate(isPositive).test(1));
        }
    }

    // ==========================================================
    // CHALLENGE 8: memoize
    // ==========================================================
    @Nested
    class MemoizeTests {

        @Test
        void testCachesRepeatedCallsWithSameArgument() {
            AtomicInteger callCount = new AtomicInteger(0);
            Function<Integer, Integer> square = x -> {
                callCount.incrementAndGet();
                return x * x;
            };
            Function<Integer, Integer> memoized = FunctionalProgrammingIntermediateChallenge.memoize(square);

            assertEquals(25, memoized.apply(5));
            assertEquals(25, memoized.apply(5));
            assertEquals(1, callCount.get());
        }

        @Test
        void testDifferentArgumentsInvokeSeparately() {
            AtomicInteger callCount = new AtomicInteger(0);
            Function<Integer, Integer> square = x -> {
                callCount.incrementAndGet();
                return x * x;
            };
            Function<Integer, Integer> memoized = FunctionalProgrammingIntermediateChallenge.memoize(square);

            assertEquals(25, memoized.apply(5));
            assertEquals(36, memoized.apply(6));
            assertEquals(2, callCount.get());
            assertEquals(25, memoized.apply(5));
            assertEquals(2, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 9: repeat
    // ==========================================================
    @Nested
    class RepeatTests {

        @Test
        void testRepeatMultipleTimes() {
            Function<Integer, Integer> doubleIt = x -> x * 2;
            assertEquals(8, FunctionalProgrammingIntermediateChallenge.repeat(doubleIt, 3).apply(1));
        }

        @Test
        void testRepeatMultipleTimes2() {
            Function<Integer, Integer> doubleIt = x -> x * 2;
            assertEquals(8, FunctionalProgrammingIntermediateChallenge.repeat2(doubleIt, 3).apply(1));
        }

        @Test
        void testZeroTimesReturnsInputUnchanged() {
            Function<Integer, Integer> increment = x -> x + 1;
            assertEquals(10, FunctionalProgrammingIntermediateChallenge.repeat(increment, 0).apply(10));
        }

        @Test
        void testRepeatSubtraction() {
            Function<Integer, Integer> decrement = x -> x - 1;
            assertEquals(5, FunctionalProgrammingIntermediateChallenge.repeat(decrement, 5).apply(10));
        }
    }

    // ==========================================================
    // CHALLENGE 10: lazy
    // ==========================================================
    @Nested
    class LazyTests {

        @Test
        void testSupplierCalledAtMostOnce() {
            AtomicInteger callCount = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                callCount.incrementAndGet();
                return 42;
            };
            Supplier<Integer> lazySupplier = FunctionalProgrammingIntermediateChallenge.lazy(supplier);

            assertEquals(42, lazySupplier.get());
            assertEquals(42, lazySupplier.get());
            assertEquals(42, lazySupplier.get());
            assertEquals(1, callCount.get());
        }

        @Test
        void testSupplierCalledAtMostOnce2() {
            AtomicInteger callCount = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                callCount.incrementAndGet();
                return 42;
            };
            Supplier<Integer> lazySupplier = FunctionalProgrammingIntermediateChallenge.lazy2(supplier);

            assertEquals(42, lazySupplier.get());
            assertEquals(42, lazySupplier.get());
            assertEquals(42, lazySupplier.get());
            assertEquals(1, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 11: parseAndDouble
    // ==========================================================
    @Nested
    class ParseAndDoubleTests {

        @Test
        void testValidPositiveNumber() {
            assertEquals(Optional.of(10), FunctionalProgrammingIntermediateChallenge.parseAndDouble("5"));
        }

        @Test
        void testInvalidNumberReturnsEmpty() {
            assertEquals(Optional.empty(), FunctionalProgrammingIntermediateChallenge.parseAndDouble("abc"));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(Optional.of(-6), FunctionalProgrammingIntermediateChallenge.parseAndDouble("-3"));
        }
    }

    // ==========================================================
    // CHALLENGE 12: getConfigValueOrDefault
    // ==========================================================
    @Nested
    class GetConfigValueOrDefaultTests {

        @Test
        void testPresentValueDoesNotInvokeSupplier() {
            Supplier<Integer> poisoned = () -> {
                throw new RuntimeException("should not be called");
            };
            assertEquals(7, FunctionalProgrammingIntermediateChallenge.getConfigValueOrDefault(Optional.of(7), poisoned));
        }

        @Test
        void testEmptyUsesSupplier() {
            assertEquals(42, FunctionalProgrammingIntermediateChallenge.getConfigValueOrDefault(Optional.empty(), () -> 42));
        }
    }

    // ==========================================================
    // CHALLENGE 13: greaterThanFactory
    // ==========================================================
    @Nested
    class GreaterThanFactoryTests {

        @Test
        void testValueAboveThreshold() {
            assertTrue(FunctionalProgrammingIntermediateChallenge.greaterThanFactory().apply(5).test(10));
        }

        @Test
        void testValueBelowThreshold() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.greaterThanFactory().apply(5).test(3));
        }

        @Test
        void testValueEqualToThresholdIsNotGreater() {
            assertFalse(FunctionalProgrammingIntermediateChallenge.greaterThanFactory().apply(5).test(5));
        }
    }

    // ==========================================================
    // CHALLENGE 14: applyTriFunction
    // ==========================================================
    @Nested
    class ApplyTriFunctionTests {

        @Test
        void testSumOfThree() {
            assertEquals(6, FunctionalProgrammingIntermediateChallenge.applyTriFunction((a, b, c) -> a + b + c, 1, 2, 3));
        }

        @Test
        void testProductOfThree() {
            assertEquals(24, FunctionalProgrammingIntermediateChallenge.applyTriFunction((a, b, c) -> a * b * c, 2, 3, 4));
        }

        @Test
        void testChainedSubtraction() {
            assertEquals(5, FunctionalProgrammingIntermediateChallenge.applyTriFunction((a, b, c) -> a - b - c, 10, 3, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 15: pipeline
    // ==========================================================
    @Nested
    class PipelineTests {

        @Test
        void testThreeStagePipeline() {
            List<Function<Integer, Integer>> functions = List.of(x -> x + 1, x -> x * 2, x -> x - 3);
            assertEquals(9, FunctionalProgrammingIntermediateChallenge.pipeline(functions).apply(5));
        }

        @Test
        void testEmptyListReturnsInputUnchanged() {
            assertEquals(7, FunctionalProgrammingIntermediateChallenge.pipeline(List.of()).apply(7));
        }

        @Test
        void testSingleFunctionPipeline() {
            List<Function<Integer, Integer>> functions = List.of(x -> x * x);
            assertEquals(16, FunctionalProgrammingIntermediateChallenge.pipeline(functions).apply(4));
        }
    }
}