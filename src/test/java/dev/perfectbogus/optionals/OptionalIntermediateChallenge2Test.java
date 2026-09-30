package dev.perfectbogus.optionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class OptionalIntermediateChallenge2Test {

    // ==========================================================
    // CHALLENGE 1: safeParseInt
    // ==========================================================
    @Nested
    class SafeParseIntTests {

        @Test
        void testValidNumberReturnsPresentOptional() {
            assertEquals(Optional.of(42), OptionalIntermediateChallenge2.safeParseInt("42"));
        }

        @Test
        void testInvalidNumberReturnsEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.safeParseInt("abc"));
        }

        @Test
        void testNullReturnsEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.safeParseInt(null));
        }
    }

    // ==========================================================
    // CHALLENGE 2: upperCaseIfPresent
    // ==========================================================
    @Nested
    class UpperCaseIfPresentTests {

        @Test
        void testPresentValueIsUppercased() {
            assertEquals(Optional.of("HELLO"), OptionalIntermediateChallenge2.upperCaseIfPresent(Optional.of("hello")));
        }

        @Test
        void testEmptyStaysEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.upperCaseIfPresent(Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: firstCharacter
    // ==========================================================
    @Nested
    class FirstCharacterTests {

        @Test
        void testReturnsFirstCharacterOfPresentValue() {
            assertEquals(Optional.of('h'), OptionalIntermediateChallenge2.firstCharacter(Optional.of("hello")));
        }

        @Test
        void testEmptyStringInsideOptionalYieldsEmptyResult() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.firstCharacter(Optional.of("")));
        }

        @Test
        void testEmptyOptionalYieldsEmptyResult() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.firstCharacter(Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: keepIfPositive
    // ==========================================================
    @Nested
    class KeepIfPositiveTests {

        @Test
        void testPositiveValueIsKept() {
            assertEquals(Optional.of(5), OptionalIntermediateChallenge2.keepIfPositive(Optional.of(5)));
        }

        @Test
        void testNonPositiveValueIsFiltered() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.keepIfPositive(Optional.of(-5)));
        }

        @Test
        void testEmptyStaysEmpty() {
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.keepIfPositive(Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: orElseWithSideEffect
    // ==========================================================
    @Nested
    class OrElseWithSideEffectTests {

        @Test
        void testPresentValueIsReturnedAndSupplierNeverInvoked() {
            AtomicInteger callCount = new AtomicInteger(0);
            int result = OptionalIntermediateChallenge2.orElseWithSideEffect(
                    Optional.of(10), () -> { callCount.incrementAndGet(); return 99; });

            assertEquals(10, result);
            assertEquals(0, callCount.get());
        }

        @Test
        void testEmptyFallsBackToSupplier() {
            AtomicInteger callCount = new AtomicInteger(0);
            int result = OptionalIntermediateChallenge2.orElseWithSideEffect(
                    Optional.empty(), () -> { callCount.incrementAndGet(); return 99; });

            assertEquals(99, result);
            assertEquals(1, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 6: orElseThrowCustom
    // ==========================================================
    @Nested
    class OrElseThrowCustomTests {

        @Test
        void testPresentValueIsReturnedAndSupplierNeverInvoked() {
            AtomicInteger callCount = new AtomicInteger(0);
            String result = OptionalIntermediateChallenge2.orElseThrowCustom(
                    Optional.of("hi"), () -> { callCount.incrementAndGet(); return new IllegalStateException("boom"); });

            assertEquals("hi", result);
            assertEquals(0, callCount.get());
        }

        @Test
        void testEmptyThrowsTheSuppliedException() {
            NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
                    () -> OptionalIntermediateChallenge2.orElseThrowCustom(
                            Optional.empty(), () -> new NoSuchElementException("missing")));

            assertEquals("missing", thrown.getMessage());
        }
    }

    // ==========================================================
    // CHALLENGE 7: logUserStatus
    // ==========================================================
    @Nested
    class LogUserStatusTests {

        @Test
        void testPresentUsernameLogsFound() {
            List<String> log = new ArrayList<>();
            OptionalIntermediateChallenge2.logUserStatus(Optional.of("bob"), log);
            assertEquals(List.of("Found: bob"), log);
        }

        @Test
        void testEmptyLogsNotFound() {
            List<String> log = new ArrayList<>();
            OptionalIntermediateChallenge2.logUserStatus(Optional.empty(), log);
            assertEquals(List.of("Not found"), log);
        }
    }

    // ==========================================================
    // CHALLENGE 8: getZipCode
    // ==========================================================
    @Nested
    class GetZipCodeTests {

        @Test
        void testFullChainReturnsZipCode() {
            OptionalIntermediateChallenge2.Person person =
                    new OptionalIntermediateChallenge2.Person(new OptionalIntermediateChallenge2.Address("12345"));
            assertEquals(Optional.of("12345"), OptionalIntermediateChallenge2.getZipCode(person));
        }

        @Test
        void testAddressWithoutZipCodeReturnsEmpty() {
            OptionalIntermediateChallenge2.Person person =
                    new OptionalIntermediateChallenge2.Person(new OptionalIntermediateChallenge2.Address(null));
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.getZipCode(person));
        }

        @Test
        void testPersonWithoutAddressReturnsEmpty() {
            OptionalIntermediateChallenge2.Person person = new OptionalIntermediateChallenge2.Person(null);
            assertEquals(Optional.empty(), OptionalIntermediateChallenge2.getZipCode(person));
        }
    }

    // ==========================================================
    // CHALLENGE 9: combineIfBothPresent
    // ==========================================================
    @Nested
    class CombineIfBothPresentTests {

        @Test
        void testBothPresentSumsValues() {
            assertEquals(Optional.of(7),
                    OptionalIntermediateChallenge2.combineIfBothPresent(Optional.of(3), Optional.of(4)));
        }

        @Test
        void testFirstMissingYieldsEmpty() {
            assertEquals(Optional.empty(),
                    OptionalIntermediateChallenge2.combineIfBothPresent(Optional.empty(), Optional.of(4)));
        }

        @Test
        void testSecondMissingYieldsEmpty() {
            assertEquals(Optional.empty(),
                    OptionalIntermediateChallenge2.combineIfBothPresent(Optional.of(3), Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: firstNonEmpty
    // ==========================================================
    @Nested
    class FirstNonEmptyTests {

        @Test
        void testPrimaryPresentSkipsFallback() {
            AtomicInteger callCount = new AtomicInteger(0);
            Optional<String> result = OptionalIntermediateChallenge2.firstNonEmpty(
                    Optional.of("primary"), () -> { callCount.incrementAndGet(); return Optional.of("fallback"); });

            assertEquals(Optional.of("primary"), result);
            assertEquals(0, callCount.get());
        }

        @Test
        void testPrimaryEmptyUsesFallback() {
            AtomicInteger callCount = new AtomicInteger(0);
            Optional<String> result = OptionalIntermediateChallenge2.firstNonEmpty(
                    Optional.empty(), () -> { callCount.incrementAndGet(); return Optional.of("fallback"); });

            assertEquals(Optional.of("fallback"), result);
            assertEquals(1, callCount.get());
        }
    }

    // ==========================================================
    // CHALLENGE 11: flattenPresentValues
    // ==========================================================
    @Nested
    class FlattenPresentValuesTests {

        @Test
        void testSkipsEmptyOptionalsPreservingOrder() {
            List<Optional<String>> list = List.of(
                    Optional.of("a"), Optional.empty(), Optional.of("b"), Optional.empty(), Optional.of("c"));

            assertEquals(List.of("a", "b", "c"), OptionalIntermediateChallenge2.flattenPresentValues(list));
        }
    }

    // ==========================================================
    // CHALLENGE 12: findValueForFirstExistingKey
    // ==========================================================
    @Nested
    class FindValueForFirstExistingKeyTests {

        @Test
        void testReturnsValueOfFirstKeyThatExists() {
            Map<String, Integer> map = new HashMap<>();
            map.put("prod", 100);
            map.put("dev", 50);

            assertEquals(Optional.of(100),
                    OptionalIntermediateChallenge2.findValueForFirstExistingKey(map, List.of("staging", "prod", "dev")));
            assertEquals(Optional.of(50),
                    OptionalIntermediateChallenge2.findValueForFirstExistingKey(map, List.of("staging", "dev", "prod")));
        }

        @Test
        void testReturnsEmptyWhenNoKeyExists() {
            Map<String, Integer> map = new HashMap<>();
            map.put("prod", 100);

            assertEquals(Optional.empty(),
                    OptionalIntermediateChallenge2.findValueForFirstExistingKey(map, List.of("staging", "qa")));
        }
    }
}