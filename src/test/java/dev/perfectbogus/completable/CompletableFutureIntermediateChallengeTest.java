package dev.perfectbogus.completable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: combineAll
    // ==========================================================
    @Nested
    class CombineAllTests {

        @Test
        void testCompletesWhenAllDone() {
            CompletableFuture<String> f1 = CompletableFuture.completedFuture("a");
            CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(1);
            CompletableFuture<Boolean> f3 = CompletableFuture.completedFuture(true);
            CompletableFutureIntermediateChallenge.combineAll(List.of(f1, f2, f3)).join();
            assertTrue(f1.isDone() && f2.isDone() && f3.isDone());
        }

        @Test
        void testEmptyListCompletesImmediately() {
            assertDoesNotThrow(() -> CompletableFutureIntermediateChallenge.combineAll(List.of()).join());
        }

        @Test
        void testIndividualResultsStillAccessible() {
            CompletableFuture<String> f1 = CompletableFuture.completedFuture("first");
            CompletableFuture<String> f2 = CompletableFuture.completedFuture("second");
            CompletableFutureIntermediateChallenge.combineAll(List.of(f1, f2)).join();
            assertEquals("first", f1.join());
            assertEquals("second", f2.join());
        }
    }

    // ==========================================================
    // CHALLENGE 2: firstCompleted
    // ==========================================================
    @Nested
    class FirstCompletedTests {

        @Test
        void testReturnsTheOnlyCompletedOne() {
            CompletableFuture<String> pending = new CompletableFuture<>();
            CompletableFuture<String> done = CompletableFuture.completedFuture("winner");
            assertEquals("winner", CompletableFutureIntermediateChallenge.firstCompleted(List.of(pending, done)));
        }

        @Test
        void testOrderOfArgumentDoesNotMatter() {
            CompletableFuture<String> done = CompletableFuture.completedFuture("only-one");
            CompletableFuture<String> pending = new CompletableFuture<>();
            assertEquals("only-one", CompletableFutureIntermediateChallenge.firstCompleted(List.of(done, pending)));
        }

        @Test
        void testSingleCompletedFutureInList() {
            CompletableFuture<String> done = CompletableFuture.completedFuture("solo");
            assertEquals("solo", CompletableFutureIntermediateChallenge.firstCompleted(List.of(done)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: describeOutcome
    // ==========================================================
    @Nested
    class DescribeOutcomeTests {

        @Test
        void testDescribesSuccess() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(99);
            assertEquals("Success: 99", CompletableFutureIntermediateChallenge.describeOutcome(future).join());
        }

        @Test
        void testDescribesFailure() {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(new RuntimeException("bad input"));
            assertEquals("Failed: bad input", CompletableFutureIntermediateChallenge.describeOutcome(future).join());
        }

        @Test
        void testDescribesZeroAsSuccess() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(0);
            assertEquals("Success: 0", CompletableFutureIntermediateChallenge.describeOutcome(future).join());
        }
    }

    // ==========================================================
    // CHALLENGE 4: onCompleteLog
    // ==========================================================
    @Nested
    class OnCompleteLogTests {

        @Test
        void testLogsResultOnSuccess() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("payload");
            List<String> log = new ArrayList<>();
            CompletableFutureIntermediateChallenge.onCompleteLog(future, log);
            assertEquals(List.of("payload"), log);
        }

        @Test
        void testLogsErrorOnFailure() {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(new RuntimeException("oops"));
            List<String> log = new ArrayList<>();
            CompletableFutureIntermediateChallenge.onCompleteLog(future, log);
            assertEquals(List.of("error"), log);
        }

        @Test
        void testDoesNotAlterOriginalFutureResult() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("untouched");
            List<String> log = new ArrayList<>();
            CompletableFutureIntermediateChallenge.onCompleteLog(future, log);
            assertEquals("untouched", future.join());
        }
    }

    // ==========================================================
    // CHALLENGE 5: failManually
    // ==========================================================
    @Nested
    class FailManuallyTests {

        @Test
        void testFirstFailureReturnsTrue() {
            CompletableFuture<String> future = new CompletableFuture<>();
            assertTrue(CompletableFutureIntermediateChallenge.failManually(future, new RuntimeException("boom")));
        }

        @Test
        void testFutureIsCompletedExceptionally() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureIntermediateChallenge.failManually(future, new RuntimeException("boom"));
            assertTrue(future.isCompletedExceptionally());
        }

        @Test
        void testJoinThrowsAfterFailure() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureIntermediateChallenge.failManually(future, new RuntimeException("boom"));
            assertThrows(CompletionException.class, future::join);
        }

        @Test
        void testSecondFailureReturnsFalse() {
            CompletableFuture<String> future = new CompletableFuture<>();
            CompletableFutureIntermediateChallenge.failManually(future, new RuntimeException("first"));
            assertFalse(CompletableFutureIntermediateChallenge.failManually(future, new RuntimeException("second")));
        }
    }

    // ==========================================================
    // CHALLENGE 6: chainWithRecovery
    // ==========================================================
    @Nested
    class ChainWithRecoveryTests {

        @Test
        void testNormalMappingSucceeds() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(5);
            Function<Integer, Integer> doubleIt = n -> n * 2;
            assertEquals(10, CompletableFutureIntermediateChallenge.chainWithRecovery(future, doubleIt, -1).join());
        }

        @Test
        void testFallbackUsedWhenMapperThrows() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(10);
            Function<Integer, Integer> divideByZero = n -> 100 / (n - 10);
            assertEquals(-1, CompletableFutureIntermediateChallenge.chainWithRecovery(future, divideByZero, -1).join());
        }

        @Test
        void testFallbackUsedWhenFutureAlreadyFailed() {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(new RuntimeException("upstream failure"));
            Function<Integer, Integer> mapper = n -> n + 1;
            assertEquals(-1, CompletableFutureIntermediateChallenge.chainWithRecovery(future, mapper, -1).join());
        }
    }

    // ==========================================================
    // CHALLENGE 7: applyWithExecutor
    // ==========================================================
    @Nested
    class ApplyWithExecutorTests {

        @Test
        void testResultIsCorrect() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(5);
            Function<Integer, Integer> square = n -> n * n;
            AtomicInteger executionCount = new AtomicInteger(0);
            Executor countingExecutor = command -> {
                executionCount.incrementAndGet();
                command.run();
            };
            assertEquals(25, CompletableFutureIntermediateChallenge.applyWithExecutor(future, square, countingExecutor).join());
        }

        @Test
        void testGivenExecutorIsUsed() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(2);
            Function<Integer, Integer> increment = n -> n + 1;
            AtomicInteger executionCount = new AtomicInteger(0);
            Executor countingExecutor = command -> {
                executionCount.incrementAndGet();
                command.run();
            };
            CompletableFutureIntermediateChallenge.applyWithExecutor(future, increment, countingExecutor).join();
            assertEquals(1, executionCount.get());
        }

        @Test
        void testExecutorNotUsedTwice() {
            CompletableFuture<Integer> future = CompletableFuture.completedFuture(3);
            Function<Integer, Integer> identity = Function.identity();
            AtomicInteger executionCount = new AtomicInteger(0);
            Executor countingExecutor = command -> {
                executionCount.incrementAndGet();
                command.run();
            };
            CompletableFutureIntermediateChallenge.applyWithExecutor(future, identity, countingExecutor).join();
            assertEquals(1, executionCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 8: joinAll
    // ==========================================================
    @Nested
    class JoinAllTests {

        @Test
        void testReturnsAllResultsInOrder() {
            List<CompletableFuture<Integer>> futures = List.of(
                    CompletableFuture.completedFuture(1),
                    CompletableFuture.completedFuture(2),
                    CompletableFuture.completedFuture(3)
            );
            assertEquals(List.of(1, 2, 3), CompletableFutureIntermediateChallenge.joinAll(futures));
        }

        @Test
        void testEmptyListReturnsEmptyList() {
            assertEquals(List.of(), CompletableFutureIntermediateChallenge.joinAll(List.of()));
        }

        @Test
        void testWorksWithStrings() {
            List<CompletableFuture<String>> futures = List.of(
                    CompletableFuture.completedFuture("a"),
                    CompletableFuture.completedFuture("b")
            );
            assertEquals(List.of("a", "b"), CompletableFutureIntermediateChallenge.joinAll(futures));
        }
    }

    // ==========================================================
    // CHALLENGE 9: withTimeoutFallback
    // ==========================================================
    @Nested
    class WithTimeoutFallbackTests {

        @Test
        void testReturnsResultWhenCompletedInTime() {
            CompletableFuture<String> future = CompletableFuture.completedFuture("fast");
            assertEquals("fast", CompletableFutureIntermediateChallenge.withTimeoutFallback(future, 200, "fallback").join());
        }

        @Test
        void testReturnsFallbackWhenTimeoutElapses() {
            CompletableFuture<String> future = new CompletableFuture<>();
            assertEquals("fallback", CompletableFutureIntermediateChallenge.withTimeoutFallback(future, 50, "fallback").join());
        }
    }

    // ==========================================================
    // CHALLENGE 10: countCompletedExceptionally
    // ==========================================================
    @Nested
    class CountCompletedExceptionallyTests {

        @Test
        void testCountsOnlyFailedOnes() {
            CompletableFuture<Integer> success = CompletableFuture.completedFuture(1);
            CompletableFuture<Integer> failure1 = new CompletableFuture<>();
            failure1.completeExceptionally(new RuntimeException("err1"));
            CompletableFuture<Integer> failure2 = new CompletableFuture<>();
            failure2.completeExceptionally(new RuntimeException("err2"));
            assertEquals(2, CompletableFutureIntermediateChallenge.countCompletedExceptionally(List.of(success, failure1, failure2)));
        }

        @Test
        void testAllSuccessfulReturnsZero() {
            CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(1);
            CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(2);
            assertEquals(0, CompletableFutureIntermediateChallenge.countCompletedExceptionally(List.of(f1, f2)));
        }

        @Test
        void testAllFailedReturnsFullCount() {
            CompletableFuture<Integer> f1 = new CompletableFuture<>();
            f1.completeExceptionally(new RuntimeException("err1"));
            CompletableFuture<Integer> f2 = new CompletableFuture<>();
            f2.completeExceptionally(new RuntimeException("err2"));
            assertEquals(2, CompletableFutureIntermediateChallenge.countCompletedExceptionally(List.of(f1, f2)));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, CompletableFutureIntermediateChallenge.countCompletedExceptionally(List.of()));
        }
    }
}