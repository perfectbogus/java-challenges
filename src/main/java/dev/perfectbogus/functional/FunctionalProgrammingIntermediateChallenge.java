package dev.perfectbogus.functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalProgrammingIntermediateChallenge {

    // A functional interface for a three-argument function, since the JDK
    // only ships up to BiFunction (two arguments). Used by CHALLENGE 14.
    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    // CHALLENGE 1
    // Applies f to input, then applies g to that result, using Function's
    // andThen method (not compose).
    public static int applyAndThen(Function<Integer, Integer> f, Function<Integer, Integer> g, int input) {
        return f.andThen(g).apply(input);
    }

    // CHALLENGE 2
    // Applies g to input, then applies f to that result, using Function's
    // compose method (not andThen).
    public static int applyCompose(Function<Integer, Integer> f, Function<Integer, Integer> g, int input) {
        return f.compose(g).apply(input);
    }

    // CHALLENGE 3
    // Returns a curried addition function: a Function that takes the
    // first addend and returns another Function that takes the second
    // addend and returns their sum.
    public static Function<Integer, Function<Integer, Integer>> curriedAdd() {
        return (a) -> (b) -> a + b;
    }

    // CHALLENGE 4
    // Returns a new Function that calls fn with firstArg fixed as its
    // first argument, taking only the second argument.
    public static Function<Integer, Integer> partiallyApply(BiFunction<Integer, Integer, Integer> fn, int firstArg) {
        return (a) -> fn.apply(firstArg, a);
    }

    // CHALLENGE 5
    // Returns a Predicate that is true only when both p1 and p2 are true
    // for the same input.
    public static <T> Predicate<T> combineAnd(Predicate<T> p1, Predicate<T> p2) {
        return p1.and(p2);
    }

    // CHALLENGE 6
    // Returns a Predicate that is true when either p1 or p2 (or both) is
    // true for the same input.
    public static <T> Predicate<T> combineOr(Predicate<T> p1, Predicate<T> p2) {
        return p1.or(p2);
    }

    // CHALLENGE 7
    // Returns a Predicate that is true exactly when p is false for a
    // given input, and false exactly when p is true.
    public static <T> Predicate<T> negate(Predicate<T> p) {
        return p.negate();
    }

    // CHALLENGE 8
    // Returns a memoized version of fn: calling the returned function
    // with the same argument more than once only invokes fn the first
    // time that argument is seen, returning the cached result on every
    // later call with that same argument.
    public static Function<Integer, Integer> memoize(Function<Integer, Integer> fn) {
        Map<Integer, Integer> cache = new HashMap<>();
        return input -> cache.computeIfAbsent(input, fn);
    }

    // CHALLENGE 9
    // Returns a Function that applies fn to its input times times in a
    // row (fn.apply(fn.apply(...fn.apply(input)...))). times is always
    // >= 0; with times == 0 the returned function returns its input
    // unchanged.
    public static <T> Function<T, T> repeat(Function<T, T> fn, int times) {
        Function<T, T> all = e -> e;
        for (int i = 0; i < times; i++) {
            all = all.andThen(fn);
        }
        return all;
    }

    public static <T> Function<T, T> repeat2(Function<T, T> fn, int times) {
        return times == 0 ? e -> e : fn.andThen(repeat2(fn, times - 1));
    }

    // CHALLENGE 10
    // Returns a Supplier that calls supplier at most once: the first call
    // to the returned Supplier's get() invokes supplier and caches the
    // result; every later call returns the cached result without calling
    // supplier again.
    public static <T> Supplier<T> lazy(Supplier<T> supplier) {
        Map<Supplier<T>,T> cache = new HashMap<>();
        return () -> cache.computeIfAbsent(supplier, k -> supplier.get());
    }

//    public static <T> Supplier<T> lazy2(Supplier<T> supplier) {
//        Object[] cache = new Object[1];
//        boolean[] computed = new boolean[1];
//        return () -> {
//            if (!computed[0]) {
//                cache[0] = supplier.get();
//                computed[0] = true;
//            }
//            return (T) cache[0];
//        };
//    }

    // CHALLENGE 11
    // Attempts to parse s as an integer. If successful, returns an
    // Optional containing twice that value. If s is not a valid integer,
    // returns Optional.empty() instead of throwing.
    public static Optional<Integer> parseAndDouble(String s) {
        try {
            int i = Integer.parseInt(s);
            return Optional.of(i*2);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> parseAndDouble2(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.matches("-?\\d+"))
                .map(Integer::parseInt)
                .map(i -> i * 2);
    }

    // CHALLENGE 12
    // Returns the value inside maybeValue if present. If maybeValue is
    // empty, returns the value produced by defaultSupplier instead.
    // defaultSupplier must not be invoked at all when maybeValue is
    // present.
    public static int getConfigValueOrDefault(Optional<Integer> maybeValue, Supplier<Integer> defaultSupplier) {
        return maybeValue.orElseGet(defaultSupplier);
    }

    // CHALLENGE 13
    // Returns a Function that, given a threshold, produces a Predicate
    // testing whether a value is strictly greater than that threshold.
    public static Function<Integer, Predicate<Integer>> greaterThanFactory() {
        return (threshold) -> a -> a > threshold;
    }

    // CHALLENGE 14
    // Calls fn with a, b, and c, and returns the result.
    public static int applyTriFunction(TriFunction<Integer, Integer, Integer, Integer> fn, int a, int b, int c) {
        return fn.apply(a, b, c);
    }

    // CHALLENGE 15
    // Composes functions into a single Function that applies each one in
    // order: the first function in the list runs first, its output
    // feeding into the next, and so on. An empty list returns a Function
    // that returns its input unchanged.
    public static Function<Integer, Integer> pipeline(List<Function<Integer, Integer>> functions) {
        return functions.stream().reduce(e -> e, Function::andThen);
    }
}