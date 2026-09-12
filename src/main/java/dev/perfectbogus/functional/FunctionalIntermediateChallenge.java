package dev.perfectbogus.functional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class FunctionalIntermediateChallenge {

    // Custom functional interface: takes three arguments and produces a result
    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    // Custom functional interface: like Function<T, R> but allowed to throw a checked exception
    @FunctionalInterface
    public interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }

    // CHALLENGE 1: Curried Addition
    // Returns a curried version of integer addition: a function that takes
    // the first operand and returns another function which takes the second
    // operand and produces the sum.
    public static Function<Integer, Function<Integer, Integer>> curriedAdd() {
        return i ->  j -> i + j ;
    }

    // CHALLENGE 2: Apply a TriFunction
    // Applies the given three-argument function to a, b and c and returns the result.
    public static Integer applyTriFunction(TriFunction<Integer, Integer, Integer, Integer> f, int a, int b, int c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3: Memoize
    // Returns a new function that behaves like the given function, but caches
    // results per input so the underlying function is only ever invoked once
    // for each distinct argument.
    public static Function<Integer, Integer> memoize(Function<Integer, Integer> function) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4: Pipeline
    // Builds a single function that applies every function in the list, in
    // order, feeding each result into the next. An empty list should behave
    // as the identity function.
    public static Function<Integer, Integer> pipeline(List<Function<Integer, Integer>> functions) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5: Safe Apply
    // Applies the given throwing function to input. If it completes normally,
    // returns an Optional containing the result. If it throws any exception,
    // returns Optional.empty() instead of propagating the exception.
    public static <T, R> Optional<R> safeApply(ThrowingFunction<T, R> function, T input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
