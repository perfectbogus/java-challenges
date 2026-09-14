package dev.perfectbogus.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class CompletableFutureIntermediateChallenge {

    // CHALLENGE 1
    // Returns a future that completes once every future in the given list
    // has completed. The returned future's own result can be ignored by
    // callers; it exists only to signal that all of them are done.
    public static CompletableFuture<Void> combineAll(List<CompletableFuture<?>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    // CHALLENGE 2
    // Returns the result of whichever future in the list completes first.
    public static Object firstCompleted(List<CompletableFuture<String>> futures) {
        return CompletableFuture.anyOf(futures.toArray(new CompletableFuture[0])).join();
    }

    // CHALLENGE 3
    // Returns a future describing the outcome of the given future as a
    // String: "Success: <result>" if it completed normally, or
    // "Failed: <message>" (using the exception's message) if it completed
    // exceptionally.
    public static CompletableFuture<String> describeOutcome(CompletableFuture<Integer> future) {
        return future.handle(
                (result, ex) -> ex != null ? "Failed: " + ex.getMessage() : "Success: " + result);
    }

    // CHALLENGE 4
    // Once future completes, appends its result to log if it completed
    // normally, or the literal string "error" if it completed
    // exceptionally. Does not alter the outcome of future itself.
    public static void onCompleteLog(CompletableFuture<String> future, List<String> log) {
        future.whenComplete((result, ex) -> log.add(ex != null ? "error" : result));
    }

    // CHALLENGE 5
    // Attempts to complete future exceptionally with error. Returns true if
    // this call caused the future to transition to a completed state, or
    // false if the future was already completed.
    public static boolean failManually(CompletableFuture<String> future, Throwable error) {
        return future.completeExceptionally(error);
    }

    // CHALLENGE 6
    // Applies mapper to the result of future. If future completes
    // normally and mapper succeeds, the returned future completes with
    // that result. If future fails, or mapper throws while computing its
    // result, the returned future completes with fallback instead.
    public static CompletableFuture<Integer> chainWithRecovery(CompletableFuture<Integer> future, Function<Integer, Integer> mapper, int fallback) {
        return future.thenApplyAsync(mapper).exceptionally(t -> fallback);
    }

    // CHALLENGE 7
    // Applies mapper to the result of future, ensuring that the mapping
    // work is executed using the given executor rather than the default
    // one.
    public static CompletableFuture<Integer> applyWithExecutor(CompletableFuture<Integer> future, Function<Integer, Integer> mapper, Executor executor) {
        return future.thenApplyAsync(mapper, executor);
    }

    // CHALLENGE 8
    // Waits for every future in the list to complete, then returns their
    // results as a list, preserving the original order.
    public static <T> List<T> joinAll(List<CompletableFuture<T>> futures) {
        List<T> results = new ArrayList<>();
        for (CompletableFuture<T> f : futures) {
            results.add(f.join());
        }
        return results;
    }

    public static <T> List<T> joinAllStream(List<CompletableFuture<T>> futures) {
        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    // CHALLENGE 9
    // Returns a future that completes with the result of future if it
    // completes within timeoutMillis, or with fallback if that time
    // elapses before future completes.
    public static CompletableFuture<String> withTimeoutFallback(CompletableFuture<String> future, long timeoutMillis, String fallback) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns how many of the given futures completed exceptionally.
    // Assumes every future in the list has already reached a terminal
    // state.
    public static int countCompletedExceptionally(List<CompletableFuture<?>> futures) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
