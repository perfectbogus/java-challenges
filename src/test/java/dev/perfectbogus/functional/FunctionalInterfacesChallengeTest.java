package dev.perfectbogus.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalInterfacesChallengeTest {

    // ==========================================================
    // CHALLENGE 1: Function<T, R>
    // ==========================================================
    @Nested
    class TransformTests {

        @Test
        void testStringLength() {
            Function<String, Integer> length = String::length;
            assertEquals(5, FunctionalInterfacesChallenge.transform(length, "hello"));
        }

        @Test
        void testParseToInt() {
            Function<String, Integer> parse = Integer::parseInt;
            assertEquals(42, FunctionalInterfacesChallenge.transform(parse, "42"));
        }

        @Test
        void testEmptyStringLength() {
            Function<String, Integer> length = String::length;
            assertEquals(0, FunctionalInterfacesChallenge.transform(length, ""));
        }
    }

    // ==========================================================
    // CHALLENGE 2: BiFunction<T, U, R>
    // ==========================================================
    @Nested
    class CombineValuesTests {

        @Test
        void testAddition() {
            BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
            assertEquals(7, FunctionalInterfacesChallenge.combineValues(add, 3, 4));
        }

        @Test
        void testMultiplication() {
            BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
            assertEquals(12, FunctionalInterfacesChallenge.combineValues(multiply, 3, 4));
        }

        @Test
        void testMax() {
            BiFunction<Integer, Integer, Integer> max = Math::max;
            assertEquals(9, FunctionalInterfacesChallenge.combineValues(max, 9, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 3: Predicate<T>
    // ==========================================================
    @Nested
    class CheckConditionTests {

        @Test
        void testIsEvenTrue() {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            assertTrue(FunctionalInterfacesChallenge.checkCondition(isEven, 4));
        }

        @Test
        void testIsEvenFalse() {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            assertFalse(FunctionalInterfacesChallenge.checkCondition(isEven, 3));
        }

        @Test
        void testIsPositive() {
            Predicate<Integer> isPositive = n -> n > 0;
            assertFalse(FunctionalInterfacesChallenge.checkCondition(isPositive, -1));
        }
    }

    // ==========================================================
    // CHALLENGE 4: BiPredicate<T, U>
    // ==========================================================
    @Nested
    class CheckRelationTests {

        @Test
        void testGreaterThan() {
            BiPredicate<Integer, Integer> greaterThan = (a, b) -> a > b;
            assertTrue(FunctionalInterfacesChallenge.checkRelation(greaterThan, 5, 3));
        }

        @Test
        void testEquals() {
            BiPredicate<Integer, Integer> equal = Integer::equals;
            assertTrue(FunctionalInterfacesChallenge.checkRelation(equal, 7, 7));
        }

        @Test
        void testLessThanFalse() {
            BiPredicate<Integer, Integer> lessThan = (a, b) -> a < b;
            assertFalse(FunctionalInterfacesChallenge.checkRelation(lessThan, 10, 5));
        }
    }

    // ==========================================================
    // CHALLENGE 5: Supplier<T>
    // ==========================================================
    @Nested
    class GetValueTests {

        @Test
        void testConstantSupplier() {
            Supplier<String> supplier = () -> "hello";
            assertEquals("hello", FunctionalInterfacesChallenge.getValue(supplier));
        }

        @Test
        void testComputedSupplier() {
            Supplier<String> supplier = () -> "java".toUpperCase();
            assertEquals("JAVA", FunctionalInterfacesChallenge.getValue(supplier));
        }

        @Test
        void testEmptySupplier() {
            Supplier<String> supplier = () -> "";
            assertEquals("", FunctionalInterfacesChallenge.getValue(supplier));
        }
    }

    // ==========================================================
    // CHALLENGE 6: Consumer<T>
    // ==========================================================
    @Nested
    class ApplyToEachTests {

        @Test
        void testAccumulatesInOrder() {
            List<Integer> numbers = List.of(1, 2, 3);
            List<Integer> results = new ArrayList<>();
            FunctionalInterfacesChallenge.applyToEach(numbers, results::add);
            assertEquals(List.of(1, 2, 3), results);
        }

        @Test
        void testConsumerTransformsBeforeStoring() {
            List<Integer> numbers = List.of(1, 2, 3);
            List<Integer> doubled = new ArrayList<>();
            FunctionalInterfacesChallenge.applyToEach(numbers, n -> doubled.add(n * 2));
            assertEquals(List.of(2, 4, 6), doubled);
        }

        @Test
        void testEmptyListNoInvocations() {
            List<Integer> numbers = List.of();
            AtomicInteger callCount = new AtomicInteger(0);
            FunctionalInterfacesChallenge.applyToEach(numbers, n -> callCount.incrementAndGet());
            assertEquals(0, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 7: BiConsumer<T, U>
    // ==========================================================
    @Nested
    class ApplyBiConsumerTests {

        @Test
        void testPutsIntoMap() {
            Map<String, Integer> map = new HashMap<>();
            BiConsumer<String, Integer> put = map::put;
            FunctionalInterfacesChallenge.applyBiConsumer(put, "age", 30);
            assertEquals(30, map.get("age"));
        }

        @Test
        void testAppendsToStringBuilder() {
            StringBuilder sb = new StringBuilder();
            BiConsumer<String, Integer> append = (k, v) -> sb.append(k).append("=").append(v);
            FunctionalInterfacesChallenge.applyBiConsumer(append, "score", 100);
            assertEquals("score=100", sb.toString());
        }

        @Test
        void testOverwritesExistingKey() {
            Map<String, Integer> map = new HashMap<>();
            map.put("count", 1);
            FunctionalInterfacesChallenge.applyBiConsumer(map::put, "count", 5);
            assertEquals(5, map.get("count"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: UnaryOperator<T>
    // ==========================================================
    @Nested
    class ApplyOperatorTests {

        @Test
        void testNegate() {
            UnaryOperator<Integer> negate = n -> -n;
            assertEquals(-5, FunctionalInterfacesChallenge.applyOperator(negate, 5));
        }

        @Test
        void testIncrement() {
            UnaryOperator<Integer> increment = n -> n + 1;
            assertEquals(11, FunctionalInterfacesChallenge.applyOperator(increment, 10));
        }

        @Test
        void testIdentity() {
            assertEquals(7, FunctionalInterfacesChallenge.applyOperator(UnaryOperator.identity(), 7));
        }
    }

    // ==========================================================
    // CHALLENGE 9: BinaryOperator<T>
    // ==========================================================
    @Nested
    class ApplyBinaryOperatorTests {

        @Test
        void testSum() {
            BinaryOperator<Integer> sum = Integer::sum;
            assertEquals(9, FunctionalInterfacesChallenge.applyBinaryOperator(sum, 4, 5));
        }

        @Test
        void testMinBy() {
            BinaryOperator<Integer> min = BinaryOperator.minBy(Comparator.naturalOrder());
            assertEquals(2, FunctionalInterfacesChallenge.applyBinaryOperator(min, 2, 8));
        }

        @Test
        void testMaxBy() {
            BinaryOperator<Integer> max = BinaryOperator.maxBy(Comparator.naturalOrder());
            assertEquals(8, FunctionalInterfacesChallenge.applyBinaryOperator(max, 2, 8));
        }
    }

    // ==========================================================
    // CHALLENGE 10: Runnable
    // ==========================================================
    @Nested
    class ExecuteRunnableTests {

        @Test
        void testFlagGetsSet() {
            AtomicBoolean flag = new AtomicBoolean(false);
            Runnable task = () -> flag.set(true);
            FunctionalInterfacesChallenge.execute(task);
            assertTrue(flag.get());
        }

        @Test
        void testCounterIncrements() {
            AtomicInteger counter = new AtomicInteger(0);
            Runnable task = counter::incrementAndGet;
            FunctionalInterfacesChallenge.execute(task);
            FunctionalInterfacesChallenge.execute(task);
            assertEquals(2, counter.get());
        }

        @Test
        void testStringBuilderMutated() {
            StringBuilder sb = new StringBuilder("start");
            Runnable task = () -> sb.append("-done");
            FunctionalInterfacesChallenge.execute(task);
            assertEquals("start-done", sb.toString());
        }
    }

    // ==========================================================
    // CHALLENGE 11: Callable<V>
    // ==========================================================
    @Nested
    class ExecuteCallableTests {

        @Test
        void testReturnsConstant() throws Exception {
            Callable<Integer> task = () -> 42;
            assertEquals(42, FunctionalInterfacesChallenge.execute(task));
        }

        @Test
        void testReturnsComputedValue() throws Exception {
            Callable<Integer> task = () -> 6 * 7;
            assertEquals(42, FunctionalInterfacesChallenge.execute(task));
        }

        @Test
        void testExceptionPropagates() {
            Callable<Integer> task = () -> {
                throw new IllegalStateException("boom");
            };
            assertThrows(IllegalStateException.class, () -> FunctionalInterfacesChallenge.execute(task));
        }
    }

    // ==========================================================
    // CHALLENGE 12: Comparator<T>
    // ==========================================================
    @Nested
    class SortWithTests {

        @Test
        void testNaturalOrder() {
            List<String> input = List.of("banana", "apple", "cherry");
            List<String> result = FunctionalInterfacesChallenge.sortWith(input, Comparator.naturalOrder());
            assertEquals(List.of("apple", "banana", "cherry"), result);
        }

        @Test
        void testReverseOrder() {
            List<String> input = List.of("banana", "apple", "cherry");
            List<String> result = FunctionalInterfacesChallenge.sortWith(input, Comparator.reverseOrder());
            assertEquals(List.of("cherry", "banana", "apple"), result);
        }

        @Test
        void testSortByLength() {
            List<String> input = List.of("ccc", "a", "bb");
            List<String> result = FunctionalInterfacesChallenge.sortWith(input, Comparator.comparingInt(String::length));
            assertEquals(List.of("a", "bb", "ccc"), result);
        }

        @Test
        void testOriginalListNotModified() {
            List<String> input = new ArrayList<>(List.of("banana", "apple", "cherry"));
            List<String> originalCopy = new ArrayList<>(input);
            FunctionalInterfacesChallenge.sortWith(input, Comparator.naturalOrder());
            assertEquals(originalCopy, input);
        }
    }

    // ==========================================================
    // CHALLENGE 13: Predicate composition
    // ==========================================================
    @Nested
    class CheckCombinedTests {

        @Test
        void testBothMatch() {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            Predicate<Integer> isPositive = n -> n > 0;
            assertTrue(FunctionalInterfacesChallenge.checkCombined(isEven, isPositive, 4));
        }

        @Test
        void testOnlyOneMatches() {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            Predicate<Integer> isPositive = n -> n > 0;
            assertFalse(FunctionalInterfacesChallenge.checkCombined(isEven, isPositive, -4));
        }

        @Test
        void testNeitherMatches() {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            Predicate<Integer> isPositive = n -> n > 0;
            assertFalse(FunctionalInterfacesChallenge.checkCombined(isEven, isPositive, -3));
        }
    }

    // ==========================================================
    // CHALLENGE 14: ToIntFunction<T>
    // ==========================================================
    @Nested
    class MapToIntTests {

        @Test
        void testStringLength() {
            ToIntFunction<String> length = String::length;
            assertEquals(5, FunctionalInterfacesChallenge.mapToInt(length, "hello"));
        }

        @Test
        void testParseInt() {
            ToIntFunction<String> parse = Integer::parseInt;
            assertEquals(123, FunctionalInterfacesChallenge.mapToInt(parse, "123"));
        }

        @Test
        void testEmptyString() {
            ToIntFunction<String> length = String::length;
            assertEquals(0, FunctionalInterfacesChallenge.mapToInt(length, ""));
        }
    }

    // ==========================================================
    // CHALLENGE 15: IntPredicate
    // ==========================================================
    @Nested
    class CheckIntConditionTests {

        @Test
        void testIsPositiveTrue() {
            IntPredicate isPositive = n -> n > 0;
            assertTrue(FunctionalInterfacesChallenge.checkIntCondition(isPositive, 5));
        }

        @Test
        void testIsPositiveFalse() {
            IntPredicate isPositive = n -> n > 0;
            assertFalse(FunctionalInterfacesChallenge.checkIntCondition(isPositive, -5));
        }

        @Test
        void testIsZero() {
            IntPredicate isZero = n -> n == 0;
            assertTrue(FunctionalInterfacesChallenge.checkIntCondition(isZero, 0));
        }
    }
}