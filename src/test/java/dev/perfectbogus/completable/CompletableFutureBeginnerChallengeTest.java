package dev.perfectbogus.completable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureBeginnerChallengeTest {

    // ==========================================================
    // CHALLENGE 1: createCompleted
    // ==========================================================
    @Nested
    class CreateCompletedTests {

        @Test
        void testHoldsGivenValue() {
            CompletableFuture<String> future = CompletableFutureBeginnerChallenge.createCompleted("hello");
            assertEquals("hello", future.join());
        }

        @Test
        void testIsAlreadyDone() {
            CompletableFuture<Integer> future = CompletableFutureBeginnerChallenge.createCompleted(42);
            assertTrue(future.isDone());
        }

        @Test
        void testWorksWithDifferentTypes() {
            CompletableFuture<Boolean> future = CompletableFutureBeginnerChallenge.createCompleted(true);
            assertTrue(future.join());
        }
    }

    // ==========================================================
    // CHALLENGE 2: supplyValue
    // ==========================================================
    @Nested
    class SupplyValueTests {

        @Test
        void testReturnsSuppliedValue() {
            Supplier<String> supplier = () -> "computed";
            assertEquals("computed", CompletableFutureBeginnerChallenge.supplyValue(supplier).join());
        }

        @Test
        void testEventuallyCompletes() {
            Supplier<String> supplier = () -> "done";
            assertTrue(CompletableFutureBeginnerChallenge.supplyValue(supplier).join() != null);
        }

        @Test
        void testDifferentSupplierResult() {
            Supplier<String> supplier = () -> "abc".toUpperCase();
            assertEquals("ABC", CompletableFutureBeginnerChallenge.supplyValue(supplier).join());
        }
    }

    // ==========================================================
    // CHALLENGE 3: transformValue
    // ==========================================================
    @Nested
    class TransformValueTests {

        @Test
        void testDoublesValue() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(5);
            Function<Integer, Integer> doubleIt = n -> n * 2;
            assertEquals(10, CompletableFutureBeginnerChallenge.transformValue(future, doubleIt).join());
        }

        @Test
        void testSquaresValue() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(4);
            Function<Integer, Integer> square = n -> n * n;
            assertEquals(16, CompletableFutureBeginnerChallenge.transformValue(future, square).join());
        }

        @Test
        void testIdentityMapper() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(7);
            assertEquals(7, CompletableFutureBeginnerChallenge.transformValue(future, Function.identity()).join());
        }
    }

    // ==========================================================
    // CHALLENGE 4: consumeValue
    // ==========================================================
    @Nested
    class ConsumeValueTests {

        @Test
        void testConsumerReceivesValue() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("payload");
            List<String> captured = new ArrayList<>();
            CompletableFutureBeginnerChallenge.consumeValue(future, captured::add);
            assertEquals(List.of("payload"), captured);
        }

        @Test
        void testConsumerRunsExactlyOnce() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("x");
            AtomicBoolean ranTwice = new AtomicBoolean(false);
            List<String> captured = new ArrayList<>();
            CompletableFutureBeginnerChallenge.consumeValue(future, s -> {
                if (!captured.isEmpty()) ranTwice.set(true);
                captured.add(s);
            });
            assertFalse(ranTwice.get());
        }

        @Test
        void testReturnsAfterConsumerHasRun() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("sync");
            StringBuilder sb = new StringBuilder();
            CompletableFutureBeginnerChallenge.consumeValue(future, sb::append);
            assertEquals("sync", sb.toString());
        }
    }

    // ==========================================================
    // CHALLENGE 5: runAfterCompletion
    // ==========================================================
    @Nested
    class RunAfterCompletionTests {

        @Test
        void testActionRuns() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("ignored");
            AtomicBoolean flag = new AtomicBoolean(false);
            CompletableFutureBeginnerChallenge.runAfterCompletion(future, () -> flag.set(true));
            assertTrue(flag.get());
        }

        @Test
        void testActionRunsAfterVoidFuture() {
            CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
            StringBuilder sb = new StringBuilder("before-");
            CompletableFutureBeginnerChallenge.runAfterCompletion(future, () -> sb.append("after"));
            assertEquals("before-after", sb.toString());
        }

        @Test
        void testReturnsAfterActionHasRun() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(1);
            int[] counter = {0};
            CompletableFutureBeginnerChallenge.runAfterCompletion(future, () -> counter[0]++);
            assertEquals(1, counter[0]);
        }
    }

    // ==========================================================
    // CHALLENGE 6: getValueOrDefault
    // ==========================================================
    @Nested
    class GetValueOrDefaultTests {

        @Test
        void testReturnsValueOnSuccess() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("ok");
            assertEquals("ok", CompletableFutureBeginnerChallenge.getValueOrDefault(future, "fallback"));
        }

        @Test
        void testReturnsDefaultOnFailure() {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(new RuntimeException("boom"));
            assertEquals("fallback", CompletableFutureBeginnerChallenge.getValueOrDefault(future, "fallback"));
        }

        @Test
        void testDefaultNotUsedWhenSuccessful() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("real-value");
            assertNotEquals("fallback", CompletableFutureBeginnerChallenge.getValueOrDefault(future, "fallback"));
        }
    }

    // ==========================================================
    // CHALLENGE 7: combineTwoFutures
    // ==========================================================
    @Nested
    class CombineTwoFuturesTests {

        @Test
        void testSumsTwoFutures() {
            CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(3);
            CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(4);
            BiFunction<Integer, Integer, Integer> sum = Integer::sum;
            assertEquals(7, CompletableFutureBeginnerChallenge.combineTwoFutures(f1, f2, sum).join());
        }

        @Test
        void testMultipliesTwoFutures() {
            CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(6);
            CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(7);
            BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
            assertEquals(42, CompletableFutureBeginnerChallenge.combineTwoFutures(f1, f2, multiply).join());
        }

        @Test
        void testMaxOfTwoFutures() {
            CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(2);
            CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(9);
            assertEquals(9, CompletableFutureBeginnerChallenge.combineTwoFutures(f1, f2, Math::max).join());
        }
    }

    // ==========================================================
    // CHALLENGE 8: chainFutures
    // ==========================================================
    @Nested
    class ChainFuturesTests {

        @Test
        void testChainsToAnotherFuture() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(5);
            Function<Integer, CompletableFuture<Integer>> mapper = n -> CompletableFuture.completedFuture(n * 10);
            assertEquals(50, CompletableFutureBeginnerChallenge.chainFutures(future, mapper).join());
        }

        @Test
        void testResultIsFlattenedNotNested() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(1);
            Function<Integer, CompletableFuture<Integer>> mapper = n -> CompletableFuture.completedFuture(n + 1);
            CompletableFuture<Integer> result = CompletableFutureBeginnerChallenge.chainFutures(future, mapper);
            assertEquals(Integer.valueOf(2), result.join());
        }

        @Test
        void testChainWithComputation() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(3);
            Function<Integer, CompletableFuture<Integer>> mapper = n -> CompletableFuture.completedFuture(n * n);
            assertEquals(9, CompletableFutureBeginnerChallenge.chainFutures(future, mapper).join());
        }
    }

    // ==========================================================
    // CHALLENGE 9: isFutureDone
    // ==========================================================
    @Nested
    class IsFutureDoneTests {

        @Test
        void testCompletedFutureIsDone() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("done");
            assertTrue(CompletableFutureBeginnerChallenge.isFutureDone(future));
        }

        @Test
        void testPendingFutureIsNotDone() {
            CompletableFuture<String> future = new CompletableFuture<>();
            assertFalse(CompletableFutureBeginnerChallenge.isFutureDone(future));
        }

        @Test
        void testExceptionallyCompletedFutureIsDone() {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(new RuntimeException("failed"));
            assertTrue(CompletableFutureBeginnerChallenge.isFutureDone(future));
        }
    }

    // ==========================================================
    // CHALLENGE 10: completeManually
    // ==========================================================
    @Nested
    class CompleteManuallyTests {

        @Test
        void testFirstCompletionReturnsTrue() {
            CompletableFuture<String> future = new CompletableFuture<>();
            assertTrue(CompletableFutureBeginnerChallenge.completeManually(future, "value"));
        }

        @Test
        void testValueIsSetAfterCompletion() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureBeginnerChallenge.completeManually(future, "set-value");
            assertEquals("set-value", future.join());
        }

        @Test
        void testSecondCompletionReturnsFalse() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureBeginnerChallenge.completeManually(future, "first");
            assertFalse(CompletableFutureBeginnerChallenge.completeManually(future, "second"));
        }

        @Test
        void testValueUnchangedAfterFailedSecondCompletion() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureBeginnerChallenge.completeManually(future, "first");
            CompletableFutureBeginnerChallenge.completeManually(future, "second");
            assertEquals("first", future.join());
        }
    }
}