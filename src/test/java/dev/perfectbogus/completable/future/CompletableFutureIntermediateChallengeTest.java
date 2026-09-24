package dev.perfectbogus.completable.future;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: divideAsync
    // ==========================================================
    @Nested
    class DivideAsyncTests {

        @Test
        void testSuccessfulDivision() {
            assertEquals(5, CompletableFutureIntermediateChallenge.divideAsync(10, 2).join());
        }

        @Test
        void testDivisionByZeroPropagatesException() {
            CompletableFuture<Integer> future = CompletableFutureIntermediateChallenge.divideAsync(7, 0);
            CompletionException thrown = assertThrows(CompletionException.class, future::join);
            assertInstanceOf(ArithmeticException.class, thrown.getCause());
        }

        @Test
        void testNegativeResult() {
            assertEquals(-3, CompletableFutureIntermediateChallenge.divideAsync(-9, 3).join());
        }
    }

    // ==========================================================
    // CHALLENGE 2: divideWithFallback
    // ==========================================================
    @Nested
    class DivideWithFallbackTests {

        @Test
        void testSuccessfulDivisionUnaffected() {
            assertEquals(5, CompletableFutureIntermediateChallenge.divideWithFallback(10, 2, -1).join());
        }

        @Test
        void testDivisionByZeroRecoversToFallback() {
            assertEquals(-1, CompletableFutureIntermediateChallenge.divideWithFallback(10, 0, -1).join());
        }

        @Test
        void testFallbackNotUsedWhenNoException() {
            assertEquals(-3, CompletableFutureIntermediateChallenge.divideWithFallback(-9, 3, 0).join());
        }
    }

    // ==========================================================
    // CHALLENGE 3: parseIntOrDefault
    // ==========================================================
    @Nested
    class ParseIntOrDefaultTests {

        @Test
        void testValidNumber() {
            assertEquals(42, CompletableFutureIntermediateChallenge.parseIntOrDefault("42", -1).join());
        }

        @Test
        void testInvalidNumberUsesDefault() {
            assertEquals(-1, CompletableFutureIntermediateChallenge.parseIntOrDefault("abc", -1).join());
        }

        @Test
        void testEmptyStringUsesDefault() {
            assertEquals(0, CompletableFutureIntermediateChallenge.parseIntOrDefault("", 0).join());
        }
    }

    // ==========================================================
    // CHALLENGE 4: sumTwoAsync
    // ==========================================================
    @Nested
    class SumTwoAsyncTests {

        @Test
        void testBothSucceed() {
            CompletableFuture<Integer> a = CompletableFuture.completedFuture(3);
            CompletableFuture<Integer> b = CompletableFuture.completedFuture(4);
            assertEquals(7, CompletableFutureIntermediateChallenge.sumTwoAsync(a, b, -1).join());
        }

        @Test
        void testFirstFailsUsesFallback() {
            CompletableFuture<Integer> failed = new CompletableFuture<>();
            failed.completeExceptionally(new RuntimeException("boom"));
            CompletableFuture<Integer> ok = CompletableFuture.completedFuture(4);
            assertEquals(-1, CompletableFutureIntermediateChallenge.sumTwoAsync(failed, ok, -1).join());
        }

        @Test
        void testBothFailUsesFallback() {
            CompletableFuture<Integer> failedA = new CompletableFuture<>();
            failedA.completeExceptionally(new RuntimeException("boom a"));
            CompletableFuture<Integer> failedB = new CompletableFuture<>();
            failedB.completeExceptionally(new RuntimeException("boom b"));
            assertEquals(-99, CompletableFutureIntermediateChallenge.sumTwoAsync(failedA, failedB, -99).join());
        }
    }

    // ==========================================================
    // CHALLENGE 5: sumAllOrDefault
    // ==========================================================
    @Nested
    class SumAllOrDefaultTests {

        @Test
        void testAllSucceed() {
            List<CompletableFuture<Integer>> futures = List.of(
                    CompletableFuture.completedFuture(1),
                    CompletableFuture.completedFuture(2),
                    CompletableFuture.completedFuture(3)
            );
            assertEquals(6, CompletableFutureIntermediateChallenge.sumAllOrDefault(futures, -1).join());
        }

        @Test
        void testOneFailsUsesDefault() {
            CompletableFuture<Integer> failed = new CompletableFuture<>();
            failed.completeExceptionally(new RuntimeException("boom"));
            List<CompletableFuture<Integer>> futures = List.of(
                    CompletableFuture.completedFuture(1),
                    failed,
                    CompletableFuture.completedFuture(3)
            );
            assertEquals(-1, CompletableFutureIntermediateChallenge.sumAllOrDefault(futures, -1).join());
        }

        @Test
        void testEmptyListSumsToZero() {
            assertEquals(0, CompletableFutureIntermediateChallenge.sumAllOrDefault(List.of(), -1).join());
        }
    }

    // ==========================================================
    // CHALLENGE 6: unwrapOrThrow
    // ==========================================================
    @Nested
    class UnwrapOrThrowTests {

        @Test
        void testSuccessfulFutureReturnsValue() {
            assertEquals(42, CompletableFutureIntermediateChallenge.unwrapOrThrow(CompletableFuture.completedFuture(42)));
        }

        @Test
        void testFailedFutureThrowsProcessingExceptionWithOriginalCause() {
            CompletableFuture<Integer> failed = new CompletableFuture<>();
            failed.completeExceptionally(new IllegalArgumentException("bad"));

            CompletableFutureIntermediateChallenge.ProcessingException thrown = assertThrows(
                    CompletableFutureIntermediateChallenge.ProcessingException.class,
                    () -> CompletableFutureIntermediateChallenge.unwrapOrThrow(failed)
            );
            assertInstanceOf(IllegalArgumentException.class, thrown.getCause());
            assertEquals("bad", thrown.getCause().getMessage());
        }
    }

    // ==========================================================
    // CHALLENGE 7: retryOnce
    // ==========================================================
    @Nested
    class RetryOnceTests {

        @Test
        void testSucceedsOnFirstAttempt() {
            AtomicInteger callCount = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                callCount.incrementAndGet();
                return 7;
            };
            assertEquals(7, CompletableFutureIntermediateChallenge.retryOnce(supplier).join());
            assertEquals(1, callCount.get());
        }

        @Test
        void testSucceedsOnSecondAttempt() {
            AtomicInteger callCount = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                if (callCount.incrementAndGet() == 1) {
                    throw new RuntimeException("fail once");
                }
                return 99;
            };
            assertEquals(99, CompletableFutureIntermediateChallenge.retryOnce(supplier).join());
            assertEquals(2, callCount.get());
        }

        @Test
        void testFailsAfterSecondAttempt() {
            AtomicInteger callCount = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                callCount.incrementAndGet();
                throw new RuntimeException("always fails");
            };
            CompletableFuture<Integer> future = CompletableFutureIntermediateChallenge.retryOnce(supplier);
            assertThrows(CompletionException.class, future::join);
            assertEquals(2, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 8: chainWithRecovery
    // ==========================================================
    @Nested
    class ChainWithRecoveryTests {

        @Test
        void testSuccessfulChain() {
            assertEquals("Result: 40", CompletableFutureIntermediateChallenge.chainWithRecovery(5).join());
        }

        @Test
        void testFailingChainRecoversToError() {
            assertEquals("ERROR", CompletableFutureIntermediateChallenge.chainWithRecovery(0).join());
        }

        @Test
        void testAnotherSuccessfulChain() {
            assertEquals("Result: 50", CompletableFutureIntermediateChallenge.chainWithRecovery(4).join());
        }
    }

    // ==========================================================
    // CHALLENGE 9: createFailedFuture
    // ==========================================================
    @Nested
    class CreateFailedFutureTests {

        @Test
        void testFutureIsCompletedExceptionally() {
            CompletableFuture<Integer> future = CompletableFutureIntermediateChallenge.createFailedFuture("bad state");
            assertTrue(future.isCompletedExceptionally());
        }

        @Test
        void testJoiningThrowsWithOriginalMessage() {
            CompletableFuture<Integer> future = CompletableFutureIntermediateChallenge.createFailedFuture("bad state");
            CompletionException thrown = assertThrows(CompletionException.class, future::join);
            assertInstanceOf(IllegalStateException.class, thrown.getCause());
            assertEquals("bad state", thrown.getCause().getMessage());
        }
    }

    // ==========================================================
    // CHALLENGE 10: trackExceptionOccurred
    // ==========================================================
    @Nested
    class TrackExceptionOccurredTests {

        @Test
        void testSuccessLeavesFlagFalse() {
            AtomicBoolean errorOccurred = new AtomicBoolean(true);
            Supplier<Integer> supplier = () -> 5;
            assertEquals(5, CompletableFutureIntermediateChallenge.trackExceptionOccurred(supplier, errorOccurred).join());
            assertFalse(errorOccurred.get());
        }

        @Test
        void testFailureSetsFlagTrueAndStillPropagates() {
            AtomicBoolean errorOccurred = new AtomicBoolean(false);
            Supplier<Integer> supplier = () -> {
                throw new RuntimeException("oops");
            };
            CompletableFuture<Integer> future = CompletableFutureIntermediateChallenge.trackExceptionOccurred(supplier, errorOccurred);
            assertThrows(CompletionException.class, future::join);
            assertTrue(errorOccurred.get());
        }
    }
}