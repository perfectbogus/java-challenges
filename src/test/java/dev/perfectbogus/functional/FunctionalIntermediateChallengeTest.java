package dev.perfectbogus.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: curriedAdd
    // ==========================================================
    @Nested
    class CurriedAddTests {

        @Test
        void testBasicAddition() {
            assertEquals(7, FunctionalIntermediateChallenge.curriedAdd().apply(3).apply(4));
        }

        @Test
        void testWithZero() {
            assertEquals(5, FunctionalIntermediateChallenge.curriedAdd().apply(0).apply(5));
        }

        @Test
        void testWithNegativeNumbers() {
            assertEquals(-3, FunctionalIntermediateChallenge.curriedAdd().apply(-1).apply(-2));
        }

        @Test
        void testPartialApplicationReusable() {
            Function<Integer, Integer> addFive = FunctionalIntermediateChallenge.curriedAdd().apply(5);
            assertEquals(8, addFive.apply(3));
            assertEquals(15, addFive.apply(10));
        }
    }

    // ==========================================================
    // CHALLENGE 2: applyTriFunction
    // ==========================================================
    @Nested
    class ApplyTriFunctionTests {

        @Test
        void testSumOfThree() {
            FunctionalIntermediateChallenge.TriFunction<Integer, Integer, Integer, Integer> sum =
                    (a, b, c) -> a + b + c;
            assertEquals(6, FunctionalIntermediateChallenge.applyTriFunction(sum, 1, 2, 3));
        }

        @Test
        void testMultiplyThenAdd() {
            FunctionalIntermediateChallenge.TriFunction<Integer, Integer, Integer, Integer> f =
                    (a, b, c) -> a * b + c;
            assertEquals(23, FunctionalIntermediateChallenge.applyTriFunction(f, 4, 5, 3));
        }

        @Test
        void testWithNegativeValues() {
            FunctionalIntermediateChallenge.TriFunction<Integer, Integer, Integer, Integer> f =
                    (a, b, c) -> a - b - c;
            assertEquals(-1, FunctionalIntermediateChallenge.applyTriFunction(f, 4, 2, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 3: memoize
    // ==========================================================
    @Nested
    class MemoizeTests {

        @Test
        void testReturnsCorrectResult() {
            Function<Integer, Integer> square = n -> n * n;
            Function<Integer, Integer> memoized = FunctionalIntermediateChallenge.memoize(square);
            assertEquals(16, memoized.apply(4));
        }

        @Test
        void testUnderlyingFunctionCalledOnceForRepeatedInput() {
            AtomicInteger callCount = new AtomicInteger(0);
            Function<Integer, Integer> countingSquare = n -> {
                callCount.incrementAndGet();
                return n * n;
            };
            Function<Integer, Integer> memoized = FunctionalIntermediateChallenge.memoize(countingSquare);

            assertEquals(9, memoized.apply(3));
            assertEquals(9, memoized.apply(3));
            assertEquals(9, memoized.apply(3));
            assertEquals(1, callCount.get());
        }

        @Test
        void testDifferentInputsComputedSeparately() {
            AtomicInteger callCount = new AtomicInteger(0);
            Function<Integer, Integer> countingSquare = n -> {
                callCount.incrementAndGet();
                return n * n;
            };
            Function<Integer, Integer> memoized = FunctionalIntermediateChallenge.memoize(countingSquare);

            assertEquals(4, memoized.apply(2));
            assertEquals(9, memoized.apply(3));
            assertEquals(2, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 4: pipeline
    // ==========================================================
    @Nested
    class PipelineTests {

        @Test
        void testSingleFunction() {
            Function<Integer, Integer> pipeline = FunctionalIntermediateChallenge.pipeline(
                    List.of(n -> n + 1)
            );
            assertEquals(6, pipeline.apply(5));
        }

        @Test
        void testMultipleFunctionsAppliedInOrder() {
            Function<Integer, Integer> pipeline = FunctionalIntermediateChallenge.pipeline(
                    List.of(n -> n + 1, n -> n * 2, n -> n - 3)
            );
            // 5 -> +1 = 6 -> *2 = 12 -> -3 = 9
            assertEquals(9, pipeline.apply(5));
        }

        @Test
        void testEmptyListIsIdentity() {
            Function<Integer, Integer> pipeline = FunctionalIntermediateChallenge.pipeline(List.of());
            assertEquals(42, pipeline.apply(42));
        }

        @Test
        void testOrderMatters() {
            Function<Integer, Integer> pipelineA = FunctionalIntermediateChallenge.pipeline(
                    List.of(n -> n + 10, n -> n * 2)
            );
            Function<Integer, Integer> pipelineB = FunctionalIntermediateChallenge.pipeline(
                    List.of(n -> n * 2, n -> n + 10)
            );
            assertEquals(20, pipelineA.apply(0));
            assertEquals(10, pipelineB.apply(0));
        }
    }

    // ==========================================================
    // CHALLENGE 5: safeApply
    // ==========================================================
    @Nested
    class SafeApplyTests {

        @Test
        void testSuccessfulApplication() {
            FunctionalIntermediateChallenge.ThrowingFunction<String, Integer> parse = Integer::parseInt;
            assertEquals(Optional.of(42), FunctionalIntermediateChallenge.safeApply(parse, "42"));
        }

        @Test
        void testExceptionResultsInEmptyOptional() {
            FunctionalIntermediateChallenge.ThrowingFunction<String, Integer> parse = Integer::parseInt;
            assertEquals(Optional.empty(), FunctionalIntermediateChallenge.safeApply(parse, "not a number"));
        }

        @Test
        void testCustomThrowingLogic() {
            FunctionalIntermediateChallenge.ThrowingFunction<Integer, Integer> divideTenBy = n -> 10 / n;
            assertEquals(Optional.of(5), FunctionalIntermediateChallenge.safeApply(divideTenBy, 2));
            assertEquals(Optional.empty(), FunctionalIntermediateChallenge.safeApply(divideTenBy, 0));
        }
    }
}