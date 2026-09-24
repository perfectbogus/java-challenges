package dev.perfectbogus.completable.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class CompletableFutureIntermediateChallenge {

    // A custom unchecked exception used by CHALLENGE 6 to wrap the real
    // cause of a failed CompletableFuture, instead of the
    // CompletionException/ExecutionException wrapper the JDK normally
    // surfaces it through.
    public static class ProcessingException extends RuntimeException {
        public ProcessingException(Throwable cause) {
            super(cause);
        }
    }

    // CHALLENGE 1
    // Returns a CompletableFuture that computes a / b asynchronously. If b
    // is zero, the returned future completes exceptionally with the
    // ArithmeticException the division throws, with no recovery attempted.
    public static CompletableFuture<Integer> divideAsync(int a, int b) {
        return CompletableFuture.supplyAsync(() -> a/b);
    }

    // CHALLENGE 2
    // Returns a CompletableFuture that computes a / b asynchronously. If
    // the division throws (for example when b is zero), the returned
    // future completes successfully with fallback instead of propagating
    // the exception.
    public static CompletableFuture<Integer> divideWithFallback(int a, int b, int fallback) {
        return CompletableFuture.supplyAsync(() -> a/b)
                .exceptionally(ex -> fallback);
    }

    // CHALLENGE 3
    // Returns a CompletableFuture that parses s as an integer
    // asynchronously. If parsing fails (s is not a valid integer), the
    // returned future completes successfully with defaultValue instead of
    // failing.
    public static CompletableFuture<Integer> parseIntOrDefault(String s, int defaultValue) {
        return CompletableFuture.supplyAsync(() -> Integer.parseInt(s)).exceptionally(ex -> defaultValue);
    }

    // CHALLENGE 4
    // Returns a CompletableFuture that adds together the results of a and
    // b. If either a or b completes exceptionally, the returned future
    // completes successfully with fallback instead of propagating the
    // failure.
    public static CompletableFuture<Integer> sumTwoAsync(CompletableFuture<Integer> a, CompletableFuture<Integer> b, int fallback) {
        return a.thenCombine(b, Integer::sum).exceptionally(ex -> fallback);
    }

    // CHALLENGE 5
    // Returns a CompletableFuture that sums the results of every future in
    // futures. If every future completes successfully, the returned future
    // completes with the total sum (0 for an empty list). If any future in
    // futures completes exceptionally, the returned future completes
    // successfully with defaultValue instead.
    public static CompletableFuture<Integer> sumAllOrDefault(List<CompletableFuture<Integer>> futures, int defaultValue) {
        CompletableFuture<Integer> all = CompletableFuture.completedFuture(0);
        for (CompletableFuture<Integer> cf : futures) {
            all = all.thenCombine(cf, Integer::sum);
        }
        return all.exceptionally(ex -> defaultValue);
    }

    public static CompletableFuture<Integer> sumAllOrDefault2(List<CompletableFuture<Integer>> futures, int defaultValue) {
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));

        return allDone.handle((v, ex) -> {
            if (ex != null) {
                return defaultValue;
            }
            return futures.stream().mapToInt(CompletableFuture::join).sum();
        });
    }

    // CHALLENGE 6
    // Blocks until future completes. If it completed successfully, returns
    // its value. If it completed exceptionally, unwraps the underlying
    // cause (rather than the CompletionException wrapper) and throws it
    // wrapped in a ProcessingException.
    public static int unwrapOrThrow(CompletableFuture<Integer> future) {
        try {
            return future.join();
        } catch (CompletionException e) {
            throw new ProcessingException(e.getCause());
        }
    }

    // CHALLENGE 7
    // Calls supplier asynchronously. If that first call throws, calls
    // supplier a second time (also asynchronously) as a retry. If the
    // second call also throws, the returned future completes exceptionally
    // with the exception from that second call. If either call succeeds,
    // the returned future completes with that value. supplier is never
    // called more than twice.
    public static CompletableFuture<Integer> retryOnce(Supplier<Integer> supplier) {
        return CompletableFuture.supplyAsync(supplier)
                .handle((result, ex) -> ex == null
                        ? CompletableFuture.completedFuture(result)
                        : CompletableFuture.supplyAsync(supplier))
                .thenCompose(cf -> cf);
    }

    // CHALLENGE 8
    // Returns a CompletableFuture<String> computed by dividing 100 by
    // input, doubling the result, and formatting it as
    // "Result: <value>". If any step throws (for example when input is
    // zero), the returned future completes successfully with "ERROR"
    // instead.
    public static CompletableFuture<String> chainWithRecovery(int input) {
        return CompletableFuture.supplyAsync(() -> "Result: " + ((100 / input) * 2)).exceptionally(ex -> "ERROR");
    }

    // CHALLENGE 9
    // Returns a new CompletableFuture<Integer> that is already completed
    // exceptionally with an IllegalStateException whose message is
    // message.
    public static CompletableFuture<Integer> createFailedFuture(String message) {
        CompletableFuture<Integer> failed = new CompletableFuture<>();
        failed.completeExceptionally(new IllegalStateException(message));
        return failed;
    }

    // CHALLENGE 10
    // Calls supplier asynchronously. Regardless of whether it succeeds or
    // throws, sets errorOccurred to true if it threw and to false if it
    // succeeded, without altering the outcome of the returned future: a
    // successful call still completes the future with its value, and a
    // failed call still leaves the returned future completing
    // exceptionally with the original exception.
    public static CompletableFuture<Integer> trackExceptionOccurred(Supplier<Integer> supplier, AtomicBoolean errorOccurred) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}