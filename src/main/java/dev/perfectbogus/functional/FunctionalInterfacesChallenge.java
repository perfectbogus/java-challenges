package dev.perfectbogus.functional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class FunctionalInterfacesChallenge {

    /**
     * CHALLENGE 1: Function<T, R>
     *
     * Applies the given function to the input and returns the result.
     */
    public static Integer transform(Function<String, Integer> f, String input) {
        return f.apply(input);
    }

    /**
     * CHALLENGE 2: BiFunction<T, U, R>
     *
     * Applies the given two-argument function to a and b and returns the result.
     */
    public static Integer combineValues(BiFunction<Integer, Integer, Integer> f, int a, int b) {
        return f.apply(a, b);
    }

    /**
     * CHALLENGE 3: Predicate<T>
     *
     * Evaluates the given predicate against value.
     */
    public static boolean checkCondition(Predicate<Integer> predicate, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 4: BiPredicate<T, U>
     *
     * Evaluates the given two-argument predicate against a and b.
     */
    public static boolean checkRelation(BiPredicate<Integer, Integer> predicate, int a, int b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 5: Supplier<T>
     *
     * Obtains and returns a value from the given supplier.
     */
    public static String getValue(Supplier<String> supplier) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 6: Consumer<T>
     *
     * Passes every element of numbers to the given consumer, in order.
     */
    public static void applyToEach(List<Integer> numbers, Consumer<Integer> consumer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 7: BiConsumer<T, U>
     *
     * Passes key and value to the given two-argument consumer.
     */
    public static void applyBiConsumer(BiConsumer<String, Integer> action, String key, Integer value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 8: UnaryOperator<T>
     *
     * Applies the given unary operator to value and returns the result.
     */
    public static Integer applyOperator(UnaryOperator<Integer> operator, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 9: BinaryOperator<T>
     *
     * Applies the given binary operator to a and b and returns the result.
     */
    public static Integer applyBinaryOperator(BinaryOperator<Integer> operator, int a, int b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 10: Runnable
     *
     * Executes the given task.
     */
    public static void execute(Runnable task) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 11: Callable<V>
     *
     * Executes the given task and returns its result. Any exception thrown
     * by the task should propagate to the caller.
     */
    public static Integer execute(Callable<Integer> task) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 12: Comparator<T>
     *
     * Returns a new list containing the elements of list sorted according
     * to the given comparator. The original list must not be modified.
     */
    public static List<String> sortWith(List<String> list, Comparator<String> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 13: Predicate composition
     *
     * Returns true only if value satisfies both p1 and p2.
     */
    public static boolean checkCombined(Predicate<Integer> p1, Predicate<Integer> p2, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 14: ToIntFunction<T>
     *
     * Applies the given function to input and returns the primitive int result.
     */
    public static int mapToInt(ToIntFunction<String> function, String input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * CHALLENGE 15: IntPredicate
     *
     * Evaluates the given primitive int predicate against value.
     */
    public static boolean checkIntCondition(IntPredicate predicate, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}