package dev.perfectbogus.functional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalWarmupChallenge {

    // CHALLENGE 1
    // Returns a function that adds n to the number it receives.
    // Example: adder(5).apply(3)    returns 8
    //          adder(-2).apply(10)  returns 8
    //          adder(0).apply(7)    returns 7
    public static Function<Integer, Integer> adder(int n) {
        return a -> n + a;
    }

    // CHALLENGE 2
    // Applies the function f to x, and then applies f again to that result. Returns the final result. f is called
    // exactly two times.
    // Example: applyTwice(x -> x + 3, 1)  returns 7
    //          applyTwice(x -> x * x, 3)  returns 81
    //          applyTwice(x -> x - 1, 0)  returns -2
    public static int applyTwice(Function<Integer, Integer> f, int x) {
        return f.apply(f.apply(x));
    }

    // CHALLENGE 3
    // Returns a function that removes the spaces at the beginning and at the end of a text, and then gives the
    // number of characters of what is left. The spaces inside the text are kept.
    // Example: trimmedLength().apply("  hi  ")  returns 2
    //          trimmedLength().apply(" a b ")    returns 3
    //          trimmedLength().apply("   ")      returns 0
    public static Function<String, Integer> trimmedLength() {
        return text -> text.trim().length();
    }

    public static Function<String, Integer> trimmedLengthImproved() {
        Function<String, String> trim = String::trim;
        return trim.andThen(String::length);
    }

    // CHALLENGE 4
    // Returns a test that is true for the numbers between low and high, both of them included. low is never greater
    // than high.
    // Example: inRange(1, 5).test(3)   returns true
    //          inRange(1, 5).test(5)   returns true
    //          inRange(1, 5).test(6)   returns false
    //          inRange(-3, -1).test(-2) returns true
    public static Predicate<Integer> inRange(int low, int high) {
        return a -> a >= low && a <= high;
    }

    // CHALLENGE 5
    // Returns a test that is true for the texts that have at least 3 characters and do not start with the lowercase
    // letter 'x'. A text that starts with the uppercase letter 'X' is fine.
    // Example: goodWord().test("cat")   returns true
    //          goodWord().test("ca")    returns false
    //          goodWord().test("xray")  returns false
    //          goodWord().test("Xray")  returns true
    public static Predicate<String> goodWord() {
        return text -> text.length() >= 3 && !text.startsWith("x");
    }

    public static Predicate<String> goodWordImproved() {
        Predicate<String> longEnough = text -> text.length() >= 3;
        Predicate<String> startsWithX = text -> text.startsWith("x");
        return longEnough.and(startsWithX.negate());
    }

    // CHALLENGE 6
    // Returns a new list with only the items for which test is true, in the same order. test is called exactly one
    // time for each item. items is not modified.
    // Example: keepIf([a, bb, ccc, dd], s -> s.length() == 2)  returns [bb, dd]
    //          keepIf([a, b], s -> false)                      returns []
    //          keepIf([], s -> true)                           returns []
    public static List<String> keepIf(List<String> items, Predicate<String> test) {
        return items.stream().filter(test).toList();
    }

    // CHALLENGE 7
    // Returns a function that applies all the steps to the number it receives, one after the other, in the order of
    // the list: the first step receives the number, the second step receives the result of the first one, and so
    // on. With no steps, the function returns the same number. steps is not modified.
    // Example: pipeline([x -> x + 1, x -> x * 2]).apply(3)  returns 8
    //          pipeline([x -> x * 2, x -> x + 1]).apply(3)  returns 7
    //          pipeline([]).apply(3)                        returns 3
    public static Function<Integer, Integer> pipeline(List<Function<Integer, Integer>> steps) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Combines the numbers from left to right with op, starting with start. The first call is op(start, first
    // number), the second call is op(result, second number), and so on. With no numbers, returns start.
    // Example: fold([1, 2, 3], 0, (acc, n) -> acc + n)       returns 6
    //          fold([1, 2, 3], 10, (acc, n) -> acc - n)      returns 4
    //          fold([1, 2, 3], 0, (acc, n) -> acc * 10 + n)  returns 123
    //          fold([], 7, (acc, n) -> acc + n)              returns 7
    public static int fold(List<Integer> numbers, int start, BinaryOperator<Integer> op) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a counter: a supplier that gives 1 the first time get() is called, 2 the second time, 3 the third
    // time, and so on. Every call of counter() creates a new counter that starts again at 1, and the counters do not
    // affect each other.
    // Example: Supplier<Integer> c = counter();  c.get() gives 1, then 2, then 3
    //          Supplier<Integer> d = counter();  d.get() gives 1, even if c was used before
    public static Supplier<Integer> counter() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a function that calls f with first as the first argument and with the number it receives as the second
    // argument.
    // Example: partial((a, b) -> a - b, 10).apply(3)  returns 7
    //          partial((a, b) -> a - b, 3).apply(10)  returns -7
    //          partial((a, b) -> a * b, 4).apply(5)   returns 20
    public static Function<Integer, Integer> partial(BiFunction<Integer, Integer, Integer> f, int first) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
