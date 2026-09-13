package dev.perfectbogus.completable;

import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureBeginnerChallenge {

    // CHALLENGE 1
    // Returns an already-completed future holding value.
    public static <T> CompletableFuture<T> createCompleted(T value) {
        return CompletableFuture.completedFuture(value);
    }

    // CHALLENGE 2
    // Returns a future that will be completed asynchronously with the
    // result produced by supplier.
    public static CompletableFuture<String> supplyValue(Supplier<String> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

    // CHALLENGE 3
    // Returns a new future whose result is mapper applied to the result
    // of future, once future completes.
    public static CompletableFuture<Integer> transformValue(CompletableFuture<Integer> future, Function<Integer, Integer> mapper) {
        return future.thenApplyAsync(mapper);
    }

    // CHALLENGE 4
    // Passes the result of future to consumer once future completes, and
    // returns only after consumer has run.
    public static void consumeValue(CompletableFuture<String> future, Consumer<String> consumer) {
        try {
            consumer.accept(future.get());
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }

    // CHALLENGE 5
    // Runs action once future completes (its result, if any, is ignored),
    // and returns only after action has run.
    public static void runAfterCompletion(CompletableFuture<?> future, Runnable action) {
        future.thenRun(action).join();
    }

    // CHALLENGE 6
    // Returns the result of future if it completes normally, or
    // defaultValue if it completes exceptionally.
    public static String getValueOrDefault(CompletableFuture<String> future, String defaultValue) {
        return future.exceptionally(t -> defaultValue).join();
    }

    // CHALLENGE 7
    // Returns a new future whose result is combiner applied to the results
    // of f1 and f2, once both have completed.
    public static CompletableFuture<Integer> combineTwoFutures(CompletableFuture<Integer> f1, CompletableFuture<Integer> f2, BiFunction<Integer, Integer, Integer> combiner) {
        return f1.thenCombine(f2, combiner);
    }

    // CHALLENGE 8
    // Returns a new future produced by feeding the result of future into
    // mapper, which itself returns a future (the two stages should be
    // flattened rather than nested).
    public static CompletableFuture<Integer> chainFutures(CompletableFuture<Integer> future, Function<Integer, CompletableFuture<Integer>> mapper) {
        return future.thenCompose(mapper);
    }

    // CHALLENGE 9
    // Returns whether future has already reached a terminal state
    // (completed normally, exceptionally, or was cancelled).
    public static boolean isFutureDone(CompletableFuture<?> future) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Attempts to complete future with value. Returns true if this call
    // caused the future to transition to a completed state, or false if
    // the future was already completed.
    public static boolean completeManually(CompletableFuture<String> future, String value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}