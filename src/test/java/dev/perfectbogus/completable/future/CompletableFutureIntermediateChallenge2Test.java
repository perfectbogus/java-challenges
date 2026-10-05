package dev.perfectbogus.completable.future;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureIntermediateChallenge2Test {

    private static <T> T await(CompletableFuture<T> future) throws Exception {
        return future.get(5, TimeUnit.SECONDS);
    }

    private static void waitUntil(java.util.function.BooleanSupplier condition) throws InterruptedException {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
        while (!condition.getAsBoolean() && System.nanoTime() < deadline) {
            Thread.sleep(5);
        }
    }

    // ==========================================================
    // CHALLENGE 1: mapAsync
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class MapAsyncTests {

        private ExecutorService newPool() {
            return Executors.newFixedThreadPool(2, r -> {
                Thread t = new Thread(r, "cf-worker");
                t.setDaemon(true);
                return t;
            });
        }

        @Test
        void testComputesMappedValue() throws Exception {
            ExecutorService pool = newPool();
            try {
                CompletableFuture<Integer> result =
                        CompletableFutureIntermediateChallenge2.mapAsync(() -> "abcd", String::length, pool);
                assertEquals(4, await(result));
            } finally {
                pool.shutdownNow();
            }
        }

        @Test
        void testBothStagesRunOnExecutorThreadsEvenWhenSourceFinishesInstantly() throws Exception {
            ExecutorService pool = newPool();
            // Each task is finished before execute() returns, so the source is always already complete by the time
            // the caller gets the future back.
            Executor runAndWait = task -> {
                try {
                    pool.submit(task).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            try {
                String callerThread = Thread.currentThread().getName();
                for (int i = 0; i < 20; i++) {
                    List<String> threads = new CopyOnWriteArrayList<>();
                    CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.mapAsync(
                            () -> {
                                threads.add(Thread.currentThread().getName());
                                return "abc";
                            },
                            s -> {
                                threads.add(Thread.currentThread().getName());
                                return s.length();
                            },
                            runAndWait);
                    assertEquals(3, await(result));
                    assertEquals(2, threads.size());
                    for (String name : threads) {
                        assertEquals("cf-worker", name);
                        assertNotEquals(callerThread, name);
                    }
                }
            } finally {
                pool.shutdownNow();
            }
        }

        @Test
        void testFailureInMapperFailsTheFuture() throws Exception {
            ExecutorService pool = newPool();
            try {
                CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.mapAsync(
                        () -> "x",
                        s -> {
                            throw new IllegalStateException("mapper failed");
                        },
                        pool);
                ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
                assertTrue(e.getCause() instanceof IllegalStateException);
            } finally {
                pool.shutdownNow();
            }
        }

        @Test
        void testFailureInSourceFailsTheFuture() throws Exception {
            ExecutorService pool = newPool();
            try {
                CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.mapAsync(
                        () -> {
                            throw new IllegalArgumentException("source failed");
                        },
                        s -> 1,
                        pool);
                ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
                assertTrue(e.getCause() instanceof IllegalArgumentException);
            } finally {
                pool.shutdownNow();
            }
        }
    }

    // ==========================================================
    // CHALLENGE 2: chain
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class ChainTests {

        @Test
        void testChainsTwoAsyncStages() throws Exception {
            CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.chain(
                    "abcd",
                    s -> CompletableFuture.supplyAsync(s::length),
                    n -> CompletableFuture.supplyAsync(() -> n * 10));
            assertEquals(40, await(result));
        }

        @Test
        void testDoesNotBlockWhileFirstStageIsPending() throws Exception {
            CompletableFuture<Integer> pending = new CompletableFuture<>();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.chain(
                    "x",
                    s -> pending,
                    n -> CompletableFuture.completedFuture("got " + n));
            assertFalse(result.isDone());
            pending.complete(7);
            assertEquals("got 7", await(result));
        }

        @Test
        void testDoesNotBlockWhileSecondStageIsPending() throws Exception {
            CompletableFuture<Integer> pending = new CompletableFuture<>();
            CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.chain(
                    "x",
                    s -> CompletableFuture.completedFuture(1),
                    n -> pending);
            assertFalse(result.isDone());
            pending.complete(99);
            assertEquals(99, await(result));
        }

        @Test
        void testSecondStageIsNotInvokedWhenFirstFails() {
            AtomicInteger secondCalls = new AtomicInteger();
            CompletableFuture<Integer> failed = CompletableFuture.failedFuture(new IllegalStateException("boom"));
            CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.chain(
                    "x",
                    s -> failed,
                    n -> {
                        secondCalls.incrementAndGet();
                        return CompletableFuture.completedFuture(n);
                    });
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalStateException);
            assertEquals(0, secondCalls.get());
        }
    }

    // ==========================================================
    // CHALLENGE 3: combineBoth
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class CombineBothTests {

        @Test
        void testCombinesValuesInOrder() throws Exception {
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.combineBoth(
                    CompletableFuture.completedFuture("ab"),
                    CompletableFuture.completedFuture(3),
                    (s, n) -> s + n);
            assertEquals("ab3", await(result));
        }

        @Test
        void testWaitsForBothWithoutBlocking() throws Exception {
            CompletableFuture<String> a = new CompletableFuture<>();
            CompletableFuture<Integer> b = new CompletableFuture<>();
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.combineBoth(a, b, (s, n) -> s + n);
            assertFalse(result.isDone());
            a.complete("x");
            assertFalse(result.isDone());
            b.complete(1);
            assertEquals("x1", await(result));
        }

        @Test
        void testFailsWhenEitherFails() {
            CompletableFuture<String> a = CompletableFuture.completedFuture("x");
            CompletableFuture<Integer> b = CompletableFuture.failedFuture(new IllegalArgumentException("bad"));
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.combineBoth(a, b, (s, n) -> s + n);
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    // ==========================================================
    // CHALLENGE 4: allOfList
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class AllOfListTests {

        @Test
        void testPreservesInputOrderNotCompletionOrder() throws Exception {
            CompletableFuture<String> f1 = new CompletableFuture<>();
            CompletableFuture<String> f2 = new CompletableFuture<>();
            CompletableFuture<String> f3 = new CompletableFuture<>();
            CompletableFuture<List<String>> result =
                    CompletableFutureIntermediateChallenge2.allOfList(List.of(f1, f2, f3));
            f3.complete("c");
            f1.complete("a");
            assertFalse(result.isDone());
            f2.complete("b");
            assertEquals(List.of("a", "b", "c"), await(result));
        }

        @Test
        void testEmptyInputCompletesImmediatelyWithEmptyList() throws Exception {
            CompletableFuture<List<String>> result =
                    CompletableFutureIntermediateChallenge2.allOfList(new ArrayList<CompletableFuture<String>>());
            assertTrue(result.isDone());
            assertEquals(List.of(), await(result));
        }

        @Test
        void testFailsWhenAnyInputFails() {
            CompletableFuture<String> ok = CompletableFuture.completedFuture("ok");
            CompletableFuture<String> bad = CompletableFuture.failedFuture(new IllegalStateException("bad"));
            CompletableFuture<List<String>> result =
                    CompletableFutureIntermediateChallenge2.allOfList(List.of(ok, bad));
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }

    // ==========================================================
    // CHALLENGE 5: firstSuccessful
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class FirstSuccessfulTests {

        @Test
        void testIgnoresEarlierFailureAndReturnsLaterSuccess() throws Exception {
            CompletableFuture<String> f1 = new CompletableFuture<>();
            CompletableFuture<String> f2 = new CompletableFuture<>();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.firstSuccessful(List.of(f1, f2));
            f1.completeExceptionally(new IllegalStateException("fast failure"));
            assertFalse(result.isDone());
            f2.complete("ok");
            assertEquals("ok", await(result));
        }

        @Test
        void testDoesNotWaitForRemainingFuturesOnceOneSucceeds() throws Exception {
            CompletableFuture<String> neverCompletes = new CompletableFuture<>();
            CompletableFuture<String> fast = new CompletableFuture<>();
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.firstSuccessful(List.of(neverCompletes, fast));
            fast.complete("fast");
            assertEquals("fast", await(result));
        }

        @Test
        void testReturnsTheFirstToCompleteWhenSeveralSucceed() throws Exception {
            CompletableFuture<String> f1 = new CompletableFuture<>();
            CompletableFuture<String> f2 = new CompletableFuture<>();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.firstSuccessful(List.of(f1, f2));
            f2.complete("second");
            f1.complete("first");
            assertEquals("second", await(result));
        }

        @Test
        void testFailsWhenEveryFutureFails() {
            CompletableFuture<String> f1 = new CompletableFuture<>();
            CompletableFuture<String> f2 = new CompletableFuture<>();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.firstSuccessful(List.of(f1, f2));
            f1.completeExceptionally(new IllegalStateException("one"));
            assertFalse(result.isDone());
            f2.completeExceptionally(new IllegalStateException("two"));
            assertThrows(ExecutionException.class, () -> await(result));
        }

        @Test
        void testEmptyInputFailsWithIllegalArgumentException() {
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.firstSuccessful(new ArrayList<CompletableFuture<String>>());
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    // ==========================================================
    // CHALLENGE 6: parseOrDefault
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class ParseOrDefaultTests {

        @Test
        void testParsesValidNumber() throws Exception {
            CompletableFuture<Integer> result =
                    CompletableFutureIntermediateChallenge2.parseOrDefault(CompletableFuture.completedFuture("42"), -1);
            assertEquals(42, await(result));
        }

        @Test
        void testReturnsDefaultWhenStringIsNotANumber() throws Exception {
            CompletableFuture<Integer> result =
                    CompletableFutureIntermediateChallenge2.parseOrDefault(
                            CompletableFuture.completedFuture("abc"), -1);
            assertEquals(-1, await(result));
        }

        @Test
        void testReturnsDefaultWhenSourceFails() throws Exception {
            CompletableFuture<String> failed = CompletableFuture.failedFuture(new IllegalStateException("no data"));
            CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.parseOrDefault(failed, 7);
            assertEquals(7, await(result));
        }

        @Test
        void testWaitsForPendingSourceBeforeParsing() throws Exception {
            CompletableFuture<String> source = new CompletableFuture<>();
            CompletableFuture<Integer> result = CompletableFutureIntermediateChallenge2.parseOrDefault(source, 0);
            assertFalse(result.isDone());
            source.complete("15");
            assertEquals(15, await(result));
        }
    }

    // ==========================================================
    // CHALLENGE 7: describeOutcome
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class DescribeOutcomeTests {

        @Test
        void testDescribesSuccess() throws Exception {
            assertEquals("OK:7", await(CompletableFutureIntermediateChallenge2.describeOutcome(
                    CompletableFuture.completedFuture(7))));
        }

        @Test
        void testDescribesFailureCompletedDirectly() throws Exception {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(new IllegalStateException("direct"));
            assertEquals("FAIL:IllegalStateException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testDescribesFailureThatTravelledThroughStages() throws Exception {
            CompletableFuture<Integer> future = CompletableFuture
                    .<Integer>supplyAsync(() -> {
                        throw new IllegalArgumentException("bad input");
                    })
                    .thenApply(n -> n + 1)
                    .thenApply(n -> n * 2);
            assertEquals("FAIL:IllegalArgumentException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testReturnedFutureNeverCompletesExceptionally() throws Exception {
            CompletableFuture<Integer> failed = CompletableFuture.failedFuture(new IllegalStateException("x"));
            CompletableFuture<String> described = CompletableFutureIntermediateChallenge2.describeOutcome(failed);
            await(described);
            assertFalse(described.isCompletedExceptionally());
        }

        @Test
        void testWaitsForPendingFuture() throws Exception {
            CompletableFuture<String> pending = new CompletableFuture<>();
            CompletableFuture<String> described = CompletableFutureIntermediateChallenge2.describeOutcome(pending);
            assertFalse(described.isDone());
            pending.complete("done");
            assertEquals("OK:done", await(described));
        }

        @Test
        void testKeepsOriginalExceptionThatCarriesItsOwnCause() throws Exception {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(new IllegalStateException("outer", new java.io.IOException("inner")));
            assertEquals("FAIL:IllegalStateException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testKeepsOriginalExceptionWithItsOwnCauseAfterTravellingThroughStages() throws Exception {
            CompletableFuture<Integer> future = CompletableFuture
                    .<Integer>supplyAsync(() -> {
                        throw new RuntimeException("wrapper", new java.io.IOException("io"));
                    })
                    .thenApply(n -> n + 1);
            assertEquals("FAIL:RuntimeException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testUnwrapsSeveralLayersOfCompletionException() throws Exception {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(
                    new CompletionException(new CompletionException(new IllegalArgumentException("deep"))));
            assertEquals("FAIL:IllegalArgumentException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testCompletionExceptionWithoutCauseIsReportedAsItself() throws Exception {
            CompletableFuture<Integer> future = new CompletableFuture<>();
            future.completeExceptionally(new CompletionException("no cause here", null));
            assertEquals("FAIL:CompletionException",
                    await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }

        @Test
        void testNullValueIsDescribedAsNull() throws Exception {
            CompletableFuture<String> future = CompletableFuture.completedFuture(null);
            assertEquals("OK:null", await(CompletableFutureIntermediateChallenge2.describeOutcome(future)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: withFallbackOnTimeout
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class WithFallbackOnTimeoutTests {

        @Test
        void testUsesFallbackAfterTimeoutAndLeavesSourceUntouched() throws Exception {
            CompletableFuture<String> source = new CompletableFuture<>();
            long start = System.nanoTime();
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.withFallbackOnTimeout(source, 150, "fallback");
            assertFalse(result.isDone());
            assertEquals("fallback", await(result));
            long elapsedMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            assertTrue(elapsedMillis >= 100, "fallback arrived too early: " + elapsedMillis + " ms");
            assertFalse(source.isDone());
        }

        @Test
        void testUsesSourceValueWhenItArrivesInTime() throws Exception {
            CompletableFuture<String> source = new CompletableFuture<>();
            long start = System.nanoTime();
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.withFallbackOnTimeout(source, 3000, "fallback");
            source.complete("real");
            assertEquals("real", await(result));
            long elapsedMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            assertTrue(elapsedMillis < 2000, "should not wait for the timeout: " + elapsedMillis + " ms");
        }

        @Test
        void testPropagatesFailureThatHappensInTime() {
            CompletableFuture<String> source = CompletableFuture.failedFuture(new IllegalStateException("broken"));
            CompletableFuture<String> result =
                    CompletableFutureIntermediateChallenge2.withFallbackOnTimeout(source, 3000, "fallback");
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }

    // ==========================================================
    // CHALLENGE 9: retry
    // ==========================================================
    @Nested
    @Timeout(value = 20, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class RetryTests {

        @Test
        void testSucceedsOnFirstAttempt() throws Exception {
            AtomicInteger calls = new AtomicInteger();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.retry(
                    () -> {
                        calls.incrementAndGet();
                        return CompletableFuture.completedFuture("ok");
                    },
                    3);
            assertEquals("ok", await(result));
            assertEquals(1, calls.get());
        }

        @Test
        void testRetriesUntilAnAttemptSucceeds() throws Exception {
            AtomicInteger calls = new AtomicInteger();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.retry(
                    () -> {
                        int attempt = calls.incrementAndGet();
                        if (attempt < 3) {
                            return CompletableFuture.failedFuture(new IllegalStateException("attempt " + attempt));
                        }
                        return CompletableFuture.completedFuture("third time lucky");
                    },
                    5);
            assertEquals("third time lucky", await(result));
            assertEquals(3, calls.get());
        }

        @Test
        void testFailsWithLastExceptionAfterAllAttempts() {
            AtomicInteger calls = new AtomicInteger();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.retry(
                    () -> CompletableFuture.failedFuture(
                            new IllegalStateException("attempt " + calls.incrementAndGet())),
                    3);
            ExecutionException e = assertThrows(ExecutionException.class, () -> await(result));
            assertTrue(e.getCause() instanceof IllegalStateException);
            assertEquals("attempt 3", e.getCause().getMessage());
            assertEquals(3, calls.get());
        }

        @Test
        void testAttemptsAreSequentialAndNonBlocking() throws Exception {
            AtomicInteger calls = new AtomicInteger();
            List<CompletableFuture<String>> attempts = new CopyOnWriteArrayList<>();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.retry(
                    () -> {
                        calls.incrementAndGet();
                        CompletableFuture<String> attempt = new CompletableFuture<>();
                        attempts.add(attempt);
                        return attempt;
                    },
                    3);
            assertEquals(1, calls.get());
            assertFalse(result.isDone());

            attempts.get(0).completeExceptionally(new IllegalStateException("first failed"));
            waitUntil(() -> calls.get() >= 2);
            assertEquals(2, calls.get());
            assertFalse(result.isDone());

            attempts.get(1).complete("second worked");
            assertEquals("second worked", await(result));
            assertEquals(2, calls.get());
        }

        @Test
        void testSingleAttemptIsNeverRepeated() {
            AtomicInteger calls = new AtomicInteger();
            CompletableFuture<String> result = CompletableFutureIntermediateChallenge2.retry(
                    () -> {
                        calls.incrementAndGet();
                        return CompletableFuture.failedFuture(new IllegalStateException("only"));
                    },
                    1);
            assertThrows(ExecutionException.class, () -> await(result));
            assertEquals(1, calls.get());
        }

        @Test
        void testRejectsFewerThanOneAttemptImmediately() {
            AtomicInteger calls = new AtomicInteger();
            assertThrows(IllegalArgumentException.class, () -> CompletableFutureIntermediateChallenge2.retry(
                    () -> {
                        calls.incrementAndGet();
                        return CompletableFuture.completedFuture("never");
                    },
                    0));
            assertEquals(0, calls.get());
        }
    }

    // ==========================================================
    // CHALLENGE 10: AsyncCache.get
    // ==========================================================
    @Nested
    @Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    class AsyncCacheTests {

        @Test
        void testCallsForTheSameKeyShareOneInFlightLoad() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();
            CompletableFuture<String> pending = new CompletableFuture<>();

            CompletableFuture<String> first = cache.get("k", key -> {
                loads.incrementAndGet();
                return pending;
            });
            CompletableFuture<String> second = cache.get("k", key -> {
                loads.incrementAndGet();
                return pending;
            });
            assertEquals(1, loads.get());
            assertFalse(first.isDone());
            assertFalse(second.isDone());

            pending.complete("value");
            assertEquals("value", await(first));
            assertEquals("value", await(second));
        }

        @Test
        void testSuccessfulLoadIsCached() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();

            assertEquals("v", await(cache.get("k", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture("v");
            })));
            assertEquals("v", await(cache.get("k", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture("other");
            })));
            assertEquals(1, loads.get());
        }

        @Test
        void testDifferentKeysLoadIndependently() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();

            assertEquals("a!", await(cache.get("a", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture(key + "!");
            })));
            assertEquals("b!", await(cache.get("b", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture(key + "!");
            })));
            assertEquals(2, loads.get());
        }

        @Test
        void testLoadThatWasAlreadyFailedWhenReturnedIsNotKept() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();

            CompletableFuture<String> first = cache.get("k", key -> {
                loads.incrementAndGet();
                return CompletableFuture.failedFuture(new IllegalStateException("load failed"));
            });
            assertThrows(ExecutionException.class, () -> await(first));
            Thread.sleep(100);

            assertEquals("recovered", await(cache.get("k", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture("recovered");
            })));
            assertEquals(2, loads.get());
        }

        @Test
        void testLoadThatFailsLaterIsNotKept() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();
            CompletableFuture<String> pending = new CompletableFuture<>();

            CompletableFuture<String> first = cache.get("k", key -> {
                loads.incrementAndGet();
                return pending;
            });
            pending.completeExceptionally(new IllegalStateException("late failure"));
            assertThrows(ExecutionException.class, () -> await(first));
            Thread.sleep(100);

            assertEquals("recovered", await(cache.get("k", key -> {
                loads.incrementAndGet();
                return CompletableFuture.completedFuture("recovered");
            })));
            assertEquals(2, loads.get());
        }

        @Test
        void testConcurrentCallersForTheSameKeyTriggerExactlyOneLoad() throws Exception {
            CompletableFutureIntermediateChallenge2.AsyncCache<String, String> cache =
                    new CompletableFutureIntermediateChallenge2.AsyncCache<>();
            AtomicInteger loads = new AtomicInteger();
            CompletableFuture<String> pending = new CompletableFuture<>();
            int callers = 32;
            ExecutorService pool = Executors.newFixedThreadPool(callers);
            try {
                CountDownLatch ready = new CountDownLatch(callers);
                CountDownLatch go = new CountDownLatch(1);
                List<java.util.concurrent.Future<CompletableFuture<String>>> calls = new ArrayList<>();
                for (int i = 0; i < callers; i++) {
                    calls.add(pool.submit(() -> {
                        ready.countDown();
                        go.await();
                        return cache.get("k", key -> {
                            loads.incrementAndGet();
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            return pending;
                        });
                    }));
                }
                assertTrue(ready.await(5, TimeUnit.SECONDS));
                go.countDown();
                List<CompletableFuture<String>> futures = new ArrayList<>();
                for (java.util.concurrent.Future<CompletableFuture<String>> call : calls) {
                    futures.add(call.get(5, TimeUnit.SECONDS));
                }
                assertEquals(1, loads.get());

                pending.complete("shared");
                for (CompletableFuture<String> future : futures) {
                    assertEquals("shared", await(future));
                }
            } finally {
                pool.shutdownNow();
            }
        }
    }
}