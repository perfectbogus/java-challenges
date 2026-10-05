package dev.perfectbogus.completable.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureIntermediateChallenge2 {

    // CHALLENGE 1
    // Returns a future that completes with mapper applied to the value produced by source. Both source.get() and
    // mapper.apply(...) must run on threads of the supplied executor and never on the calling thread, no matter how
    // quickly source finishes. The calling thread must not be blocked waiting for either of them. If either of them
    // throws, the returned future completes exceptionally.
    public static <T, R> CompletableFuture<R> mapAsync(Supplier<T> source, Function<T, R> mapper, Executor executor) {
        return CompletableFuture.supplyAsync(() -> mapper.apply(source.get()), executor);
    }

    // CHALLENGE 2
    // Starts first with input; when that future completes, starts second with its value; the returned future completes
    // with the value of the future that second returned. The returned future must be a plain CompletableFuture<C>
    // (not a future of a future), the calling thread must never block waiting for either stage, and second must not
    // be invoked at all if the future returned by first fails (the returned future then fails as well).
    public static <A, B, C> CompletableFuture<C> chain(A input, Function<A, CompletableFuture<B>> first,
                                                       Function<B, CompletableFuture<C>> second) {
        return first.apply(input).thenCompose(second);
    }

    // CHALLENGE 3
    // Returns a future that completes with combiner.apply(valueOfA, valueOfB) once both a and b have completed
    // normally, passing the values in that order (a's value first). The calling thread must not be blocked waiting
    // for a or b, and the returned future must fail if either a or b fails.
    public static <A, B, R> CompletableFuture<R> combineBoth(CompletableFuture<A> a, CompletableFuture<B> b,
                                                             BiFunction<A, B, R> combiner) {

        return a.thenCombine(b, combiner);
    }

    // CHALLENGE 4
    // Returns a future that completes with the list of all the futures' values, in the same order as the futures
    // appear in the input (not in the order in which they complete), once every one of them has completed normally.
    // An empty input list yields an already-completed future holding an empty list. If any input future fails, the
    // returned future fails. The calling thread must not be blocked waiting for any of the futures.
    public static <T> CompletableFuture<List<T>> allOfList(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenApply(
                ignore -> {
                    List<T> results = new ArrayList<>();
                    for (CompletableFuture<T> f : futures) {
                        results.add(f.join());
                    }
                    return results;
                });
    }

    // CHALLENGE 5
    // Returns a future that completes with the value of the first input future to complete NORMALLY. Futures that
    // fail are ignored for as long as at least one other future could still succeed. The returned future must not
    // wait for the remaining futures once one of them has succeeded. If every input future fails, the returned future
    // completes exceptionally. If futures is empty, the returned future completes exceptionally with an
    // IllegalArgumentException. The calling thread must not be blocked.
    public static <T> CompletableFuture<T> firstSuccessful(List<CompletableFuture<T>> futures) {
        AtomicInteger fails = new AtomicInteger(0);
        CompletableFuture<T> resultFuture = new CompletableFuture<>();

        if (futures.isEmpty())
            resultFuture.completeExceptionally(new IllegalArgumentException("futures must not be empty"));

        for (CompletableFuture<T> f : futures) {
            f.whenComplete((value, ex) -> {
                if (ex == null) {
                    resultFuture.complete(value);
                } else {
                    fails.incrementAndGet();
                }
                if (fails.get() == futures.size()) {
                    resultFuture.completeExceptionally(ex);
                }
            });
        }
        return resultFuture;
    }

    // CHALLENGE 6
    // Returns a future holding the integer parsed from the string that source produces. If source fails, or if the
    // string it produces is not a valid integer, the returned future completes normally with defaultValue instead.
    // The returned future itself must never complete exceptionally because of either of those two problems.
    public static CompletableFuture<Integer> parseOrDefault(CompletableFuture<String> source, int defaultValue) {
        return source.thenApply(Integer::parseInt).exceptionally(ex -> defaultValue);
    }

    // CHALLENGE 7
    // Returns a future that always completes NORMALLY with a description of how future ended: "OK:" followed by
    // String.valueOf(value) if future completed normally, or "FAIL:" followed by the simple class name of the
    // original exception if it failed (for example "FAIL:IllegalStateException"). The original exception is the one
    // that was actually thrown or supplied by the code that failed, regardless of how many stages the failure travelled
    // through before reaching future, and regardless of whether future was completed exceptionally directly or because
    // a stage feeding it threw.
    public static <T> CompletableFuture<String> describeOutcome(CompletableFuture<T> future) {
        return future.handle((v, ex) -> ex != null ? "FAIL:" + ex : "OK:" + v.toString());
    }

    // CHALLENGE 8
    // Returns a future that completes with source's value if source completes normally within timeoutMillis
    // milliseconds, with fallback if source has not completed by then, and exceptionally if source fails within that
    // time. No thread may be blocked while waiting for the timeout to elapse. The future passed in as source must not
    // be completed, cancelled or otherwise altered by this method, even when the timeout fires.
    public static <T> CompletableFuture<T> withFallbackOnTimeout(CompletableFuture<T> source, long timeoutMillis,
                                                                 T fallback) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Calls task.get() to start an attempt and returns a future that completes with that attempt's value if it
    // succeeds. If an attempt fails, task.get() is called again to start a fresh attempt, up to maxAttempts attempts
    // in total (the first one included). Attempts are strictly sequential: a new attempt is never started before the
    // previous one has failed. If every attempt fails, the returned future fails with the exception of the last
    // attempt. Throws IllegalArgumentException immediately (not through the returned future) if maxAttempts is less
    // than 1. The calling thread must not be blocked while waiting for an attempt to complete.
    public static <T> CompletableFuture<T> retry(Supplier<CompletableFuture<T>> task, int maxAttempts) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Used by CHALLENGE 10. You may add private fields and a constructor if you need them.
    public static class AsyncCache<K, V> {

        // CHALLENGE 10
        // Returns a future for the value associated with key, calling loader.apply(key) to start loading it when it is
        // not already available. Calls for the same key made while a load is still in flight, even from different
        // threads at the same moment, must share that single load (loader invoked once for all of them) and all receive
        // its result. Once a load has succeeded, later calls for that key return the cached value without invoking
        // loader again. A load that fails must not stay cached: after its failure, a later call for that key starts a
        // new load by invoking loader again, whether the failed future was already failed when loader returned it or
        // failed later. Loads for different keys are independent of each other. This method must not block the
        // calling thread waiting for a load to finish.
        public CompletableFuture<V> get(K key, Function<K, CompletableFuture<V>> loader) {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}