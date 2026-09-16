package dev.perfectbogus.optionals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OptionalIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: parseIntSafely
    // ==========================================================
    @Nested
    class ParseIntSafelyTests {

        @Test
        void testValidNumber() {
            assertEquals(Optional.of(42), OptionalIntermediateChallenge.parseIntSafely("42"));
        }

        @Test
        void testInvalidNumberReturnsEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.parseIntSafely("not-a-number"));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(Optional.of(-7), OptionalIntermediateChallenge.parseIntSafely("-7"));
        }

        @Test
        void testEmptyStringReturnsEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.parseIntSafely(""));
        }
    }

    // ==========================================================
    // CHALLENGE 2: describe
    // ==========================================================
    @Nested
    class DescribeTests {

        @Test
        void testPresentValueIsUppercased() {
            assertEquals("HELLO", OptionalIntermediateChallenge.describe(Optional.of("hello")));
        }

        @Test
        void testAbsentValueReturnsEmptyLiteral() {
            assertEquals("EMPTY", OptionalIntermediateChallenge.describe(Optional.empty()));
        }

        @Test
        void testAlreadyUppercaseValue() {
            assertEquals("JAVA", OptionalIntermediateChallenge.describe(Optional.of("JAVA")));
        }
    }

    // ==========================================================
    // CHALLENGE 3: firstNonBlank
    // ==========================================================
    @Nested
    class FirstNonBlankTests {

        @Test
        void testFirstIsUsedWhenNonBlank() {
            assertEquals(Optional.of("first"), OptionalIntermediateChallenge.firstNonBlank(Optional.of("first"), Optional.of("second")));
        }

        @Test
        void testFallsBackWhenFirstIsBlank() {
            assertEquals(Optional.of("second"), OptionalIntermediateChallenge.firstNonBlank(Optional.of("   "), Optional.of("second")));
        }

        @Test
        void testFallsBackWhenFirstIsAbsent() {
            assertEquals(Optional.of("second"), OptionalIntermediateChallenge.firstNonBlank(Optional.empty(), Optional.of("second")));
        }

        @Test
        void testEmptyWhenBothBlankOrAbsent() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.firstNonBlank(Optional.empty(), Optional.of("  ")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: chainLookup
    // ==========================================================
    @Nested
    class ChainLookupTests {

        @Test
        void testBothLookupsSucceed() {
            Map<String, String> aliasMap = Map.of("admin", "userA");
            Map<String, Integer> valueMap = Map.of("userA", 42);
            assertEquals(Optional.of(42), OptionalIntermediateChallenge.chainLookup(aliasMap, valueMap, "admin"));
        }

        @Test
        void testFirstLookupFails() {
            Map<String, String> aliasMap = Map.of("admin", "userA");
            Map<String, Integer> valueMap = Map.of("userA", 42);
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.chainLookup(aliasMap, valueMap, "guest"));
        }

        @Test
        void testSecondLookupFails() {
            Map<String, String> aliasMap = Map.of("admin", "userB");
            Map<String, Integer> valueMap = Map.of("userA", 42);
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.chainLookup(aliasMap, valueMap, "admin"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: sumPresentValues
    // ==========================================================
    @Nested
    class SumPresentValuesTests {

        @Test
        void testSumsOnlyPresentValues() {
            List<Optional<Integer>> optionals = List.of(Optional.of(1), Optional.empty(), Optional.of(2), Optional.of(3));
            assertEquals(6, OptionalIntermediateChallenge.sumPresentValues(optionals));
        }

        @Test
        void testAllEmptyReturnsZero() {
            List<Optional<Integer>> optionals = List.of(Optional.empty(), Optional.empty());
            assertEquals(0, OptionalIntermediateChallenge.sumPresentValues(optionals));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, OptionalIntermediateChallenge.sumPresentValues(List.of()));
        }

        @Test
        void testAllPresent() {
            List<Optional<Integer>> optionals = List.of(Optional.of(10), Optional.of(20));
            assertEquals(30, OptionalIntermediateChallenge.sumPresentValues(optionals));
        }
    }

    // ==========================================================
    // CHALLENGE 6: logPresenceOutcome
    // ==========================================================
    @Nested
    class LogPresenceOutcomeTests {

        @Test
        void testPresentValueLogged() {
            List<String> presentLog = new ArrayList<>();
            List<String> emptyLog = new ArrayList<>();
            OptionalIntermediateChallenge.logPresenceOutcome(Optional.of("hello"), presentLog, emptyLog);
            assertEquals(List.of("hello"), presentLog);
            assertTrue(emptyLog.isEmpty());
        }

        @Test
        void testAbsentValueLogged() {
            List<String> presentLog = new ArrayList<>();
            List<String> emptyLog = new ArrayList<>();
            OptionalIntermediateChallenge.logPresenceOutcome(Optional.empty(), presentLog, emptyLog);
            assertEquals(List.of("empty"), emptyLog);
            assertTrue(presentLog.isEmpty());
        }

        @Test
        void testOnlyOneLogIsEverAppended() {
            List<String> presentLog = new ArrayList<>();
            List<String> emptyLog = new ArrayList<>();
            OptionalIntermediateChallenge.logPresenceOutcome(Optional.of("x"), presentLog, emptyLog);
            assertEquals(1, presentLog.size() + emptyLog.size());
        }
    }

    // ==========================================================
    // CHALLENGE 7: safeDivide
    // ==========================================================
    @Nested
    class SafeDivideTests {

        @Test
        void testNormalDivision() {
            assertEquals(Optional.of(5), OptionalIntermediateChallenge.safeDivide(10, 2));
        }

        @Test
        void testDivisionByZeroReturnsEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.safeDivide(10, 0));
        }

        @Test
        void testIntegerDivisionTruncates() {
            assertEquals(Optional.of(3), OptionalIntermediateChallenge.safeDivide(7, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 8: unwrapOrThrow
    // ==========================================================
    @Nested
    class UnwrapOrThrowTests {

        @Test
        void testReturnsValueWhenPresent() {
            assertEquals("hello", OptionalIntermediateChallenge.unwrapOrThrow(Optional.of("hello"), "missing"));
        }

        @Test
        void testThrowsWhenAbsent() {
            assertThrows(NoSuchElementException.class,
                    () -> OptionalIntermediateChallenge.unwrapOrThrow(Optional.empty(), "missing value"));
        }

        @Test
        void testExceptionMessageMatches() {
            NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                    () -> OptionalIntermediateChallenge.unwrapOrThrow(Optional.empty(), "custom error message"));
            assertEquals("custom error message", ex.getMessage());
        }
    }

    // ==========================================================
    // CHALLENGE 9: mapAndFilter
    // ==========================================================
    @Nested
    class MapAndFilterTests {

        @Test
        void testMappedStringPassesFilter() {
            // "Value: 100" has length 10, minLength 5 -> passes
            assertEquals(Optional.of("Value: 100"), OptionalIntermediateChallenge.mapAndFilter(Optional.of(100), 5));
        }

        @Test
        void testMappedStringFailsFilter() {
            // "Value: 1" has length 8, minLength 20 -> filtered out
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.mapAndFilter(Optional.of(1), 20));
        }

        @Test
        void testAbsentInputStaysEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge.mapAndFilter(Optional.empty(), 0));
        }
    }

    // ==========================================================
    // CHALLENGE 10: areBothPresentAndEqual
    // ==========================================================
    @Nested
    class AreBothPresentAndEqualTests {

        @Test
        void testBothPresentAndEqual() {
            assertTrue(OptionalIntermediateChallenge.areBothPresentAndEqual(Optional.of(5), Optional.of(5)));
        }

        @Test
        void testBothPresentButDifferent() {
            assertFalse(OptionalIntermediateChallenge.areBothPresentAndEqual(Optional.of(5), Optional.of(9)));
        }

        @Test
        void testOneAbsent() {
            assertFalse(OptionalIntermediateChallenge.areBothPresentAndEqual(Optional.of(5), Optional.empty()));
        }

        @Test
        void testBothAbsent() {
            assertFalse(OptionalIntermediateChallenge.areBothPresentAndEqual(Optional.empty(), Optional.empty()));
        }
    }
}