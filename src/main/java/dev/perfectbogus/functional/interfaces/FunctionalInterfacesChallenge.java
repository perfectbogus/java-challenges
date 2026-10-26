package dev.perfectbogus.functional.interfaces;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class FunctionalInterfacesChallenge {

    // CHALLENGE 1
    // Returns a consumer that adds each text it receives to the end of target. Nothing is added when the consumer is
    // created, only when it receives a text.
    // Example: list = [], c = addTo(list), c.accept("a"), c.accept("b")  list is now [a, b]
    //          c.accept("a") once more                                     list is now [a, b, a]
    public static Consumer<String> addTo(List<String> target) {
        return target::add;
    }

    public static Consumer<String> addToImproved(List<String> target) {
        return text -> target.add(text);
    }

    // CHALLENGE 2
    // Returns a consumer that gives each text it receives first to the consumer first and then to the consumer
    // second. Both receive the same text, and each of them is called exactly once for every text. Nothing is called
    // when the new consumer is created.
    // Example: log = [], first adds "1:" + text to log, second adds "2:" + text to log
    //          both(first, second).accept("x")  log is now [1:x, 2:x]
    public static Consumer<String> both(Consumer<String> first, Consumer<String> second) {
        return t -> {
            first.accept(t);
            second.accept(t);
        };
    }

    public static Consumer<String> bothImproved(Consumer<String> first, Consumer<String> second) {
        return first.andThen(second);
    }

    // CHALLENGE 3
    // Returns an operator that repeats the text it receives the given number of times, one after the other. times is
    // never negative, and 0 gives an empty text.
    // Example: repeat(3).apply("ab")  returns "ababab"
    //          repeat(1).apply("ab")  returns "ab"
    //          repeat(0).apply("ab")  returns ""
    public static UnaryOperator<String> repeat(int times) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns an operator that receives two texts and gives the longer one. If both have the same length, it gives
    // the first one.
    // Example: longer().apply("a", "bb")    returns "bb"
    //          longer().apply("bb", "a")    returns "bb"
    //          longer().apply("ab", "cd")   returns "ab"
    public static BinaryOperator<String> longer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a test that receives a text and a number, and is true when the text has more characters than the number.
    // Example: lengthAbove().test("hello", 3)  returns true
    //          lengthAbove().test("hello", 5)  returns false
    //          lengthAbove().test("", -1)      returns true
    public static BiPredicate<String, Integer> lengthAbove() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a test that is true for the numbers that can be divided by d without a remainder. d is never 0. Zero
    // can be divided by any d, and negative numbers can be divided too.
    // Example: divisibleBy(3).test(9)    returns true
    //          divisibleBy(3).test(10)   returns false
    //          divisibleBy(3).test(-6)   returns true
    //          divisibleBy(3).test(0)    returns true
    public static IntPredicate divisibleBy(int d) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns a function that counts the vowels of a text: the letters a, e, i, o and u, in lowercase or uppercase.
    // The letter y is not a vowel.
    // Example: vowelCount().applyAsInt("banana")  returns 3
    //          vowelCount().applyAsInt("AEIOU")   returns 5
    //          vowelCount().applyAsInt("rhythm")  returns 0
    //          vowelCount().applyAsInt("")        returns 0
    public static ToIntFunction<String> vowelCount() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns an operator that divides its first number by its second number, dropping the decimals (7 / 2 is 3, and
    // -7 / 2 is -3). When the second number is 0, it gives 0 instead of failing.
    // Example: safeDivide().applyAsInt(7, 2)   returns 3
    //          safeDivide().applyAsInt(-7, 2)  returns -3
    //          safeDivide().applyAsInt(5, 0)   returns 0
    public static IntBinaryOperator safeDivide() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // A function that receives three values. It is used by challenge 9.
    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    // CHALLENGE 9
    // Returns a TriFunction that receives a value, a minimum and a maximum (the minimum is never greater than the
    // maximum). It gives the minimum if the value is smaller than the minimum, the maximum if the value is greater
    // than the maximum, and the value itself in any other case.
    // Example: clamp().apply(5, 1, 10)   returns 5
    //          clamp().apply(-3, 1, 10)  returns 1
    //          clamp().apply(15, 1, 10)  returns 10
    //          clamp().apply(10, 1, 10)  returns 10
    public static TriFunction<Integer, Integer, Integer, Integer> clamp() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // A step that changes a value into another value of the same type. It is used by challenge 10.
    @FunctionalInterface
    public interface Transformer<T> {
        T transform(T value);

        // CHALLENGE 10
        // Returns a new transformer that applies this transformer to a value, and then applies next to the result.
        // Nothing is called when the new transformer is created, and neither this transformer nor next change.
        // Example: addOne = x -> x + 1, double = x -> x * 2
        //          addOne.then(double).transform(3)  returns 8
        //          double.then(addOne).transform(3)  returns 7
        default Transformer<T> then(Transformer<T> next) {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
