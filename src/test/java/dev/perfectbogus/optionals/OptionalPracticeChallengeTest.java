package dev.perfectbogus.optionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class OptionalPracticeChallengeTest {

    // ==========================================================
    // CHALLENGE 1: parseInteger
    // ==========================================================
    @Nested
    class ParseIntegerTests {

        @Test
        void testValidNumbers() {
            assertEquals(Optional.of(42), OptionalPracticeChallenge.parseInteger("42"));
            assertEquals(Optional.of(-7), OptionalPracticeChallenge.parseInteger("-7"));
            assertEquals(Optional.of(5), OptionalPracticeChallenge.parseInteger("+5"));
            assertEquals(Optional.of(0), OptionalPracticeChallenge.parseInteger("0"));
        }

        @Test
        void testNullIsEmpty() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger(null));
        }

        @Test
        void testNotNumbersAreEmpty() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("abc"));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("12a"));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("4.5"));
        }

        @Test
        void testBlankAndSurroundingSpacesAreEmpty() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger(""));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("   "));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger(" 42"));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("42 "));
        }

        @Test
        void testRangeOfInt() {
            assertEquals(Optional.of(Integer.MAX_VALUE), OptionalPracticeChallenge.parseInteger("2147483647"));
            assertEquals(Optional.of(Integer.MIN_VALUE), OptionalPracticeChallenge.parseInteger("-2147483648"));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("2147483648"));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.parseInteger("-2147483649"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: displayName
    // ==========================================================
    @Nested
    class DisplayNameTests {

        @Test
        void testReturnsTheNameWhenPresent() {
            assertEquals("Ana", OptionalPracticeChallenge.displayName(Optional.of("Ana")));
        }

        @Test
        void testReturnsAnonymousWhenEmpty() {
            assertEquals("Anonymous", OptionalPracticeChallenge.displayName(Optional.empty()));
        }

        @Test
        void testAnEmptyStringIsStillAName() {
            assertEquals("", OptionalPracticeChallenge.displayName(Optional.of("")));
        }
    }

    // ==========================================================
    // CHALLENGE 3: lengthOrZero
    // ==========================================================
    @Nested
    class LengthOrZeroTests {

        @Test
        void testLengthOfThePresentText() {
            assertEquals(5, OptionalPracticeChallenge.lengthOrZero(Optional.of("hello")));
            assertEquals(3, OptionalPracticeChallenge.lengthOrZero(Optional.of(" a ")));
        }

        @Test
        void testEmptyOptionalGivesZero() {
            assertEquals(0, OptionalPracticeChallenge.lengthOrZero(Optional.empty()));
        }

        @Test
        void testEmptyStringGivesZero() {
            assertEquals(0, OptionalPracticeChallenge.lengthOrZero(Optional.of("")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: nonBlank
    // ==========================================================
    @Nested
    class NonBlankTests {

        @Test
        void testKeepsNormalText() {
            assertEquals(Optional.of("hello"), OptionalPracticeChallenge.nonBlank("hello"));
        }

        @Test
        void testKeepsNormalTextChained() {
            assertEquals(Optional.of("hello"), OptionalPracticeChallenge.nonBlankChained("hello"));
        }

        @Test
        void testDoesNotTrimTheText() {
            assertEquals(Optional.of("  hi "), OptionalPracticeChallenge.nonBlank("  hi "));
        }

        @Test
        void testNullEmptyAndBlankAreEmpty() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.nonBlank(null));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.nonBlank(""));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.nonBlank("   "));
            assertEquals(Optional.empty(), OptionalPracticeChallenge.nonBlank("\t\n"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: describe
    // ==========================================================
    @Nested
    class DescribeTests {

        @Test
        void testDescribesAPresentNumber() {
            assertEquals("Value: 5", OptionalPracticeChallenge.describe(Optional.of(5)));
            assertEquals("Value: -3", OptionalPracticeChallenge.describe(Optional.of(-3)));
        }

        @Test
        void testZeroIsAValue() {
            assertEquals("Value: 0", OptionalPracticeChallenge.describe(Optional.of(0)));
        }

        @Test
        void testEmptyOptional() {
            assertEquals("No value", OptionalPracticeChallenge.describe(Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 6: maxOf
    // ==========================================================
    @Nested
    class MaxOfTests {

        @Test
        void testFindsTheGreatestNumber() {
            assertEquals(Optional.of(9), OptionalPracticeChallenge.maxOf(List.of(3, 9, 2, 7)));
        }

        @Test
        void testAllNegativeNumbers() {
            assertEquals(Optional.of(-2), OptionalPracticeChallenge.maxOf(List.of(-5, -2, -9)));
        }

        @Test
        void testSingleElementAndRepeatedMaximum() {
            assertEquals(Optional.of(4), OptionalPracticeChallenge.maxOf(List.of(4)));
            assertEquals(Optional.of(8), OptionalPracticeChallenge.maxOf(List.of(8, 1, 8)));
        }

        @Test
        void testEmptyListIsEmpty() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.maxOf(List.of()));
        }

        @Test
        void testDoesNotModifyTheList() {
            List<Integer> nums = new ArrayList<>(List.of(3, 1, 2));

            OptionalPracticeChallenge.maxOf(nums);

            assertEquals(List.of(3, 1, 2), nums);
        }
    }

    // ==========================================================
    // CHALLENGE 7: firstAvailable
    // ==========================================================
    @Nested
    class FirstAvailableTests {

        @Test
        void testPrimaryWinsWhenBothHaveAValue() {
            assertEquals(Optional.of("a"),
                    OptionalPracticeChallenge.firstAvailable(Optional.of("a"), Optional.of("b")));
        }

        @Test
        void testFallbackIsUsedWhenPrimaryIsEmpty() {
            assertEquals(Optional.of("b"),
                    OptionalPracticeChallenge.firstAvailable(Optional.empty(), Optional.of("b")));
        }

        @Test
        void testPrimaryAloneAndBothEmpty() {
            assertEquals(Optional.of("a"),
                    OptionalPracticeChallenge.firstAvailable(Optional.of("a"), Optional.empty()));
            assertEquals(Optional.empty(),
                    OptionalPracticeChallenge.firstAvailable(Optional.empty(), Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: requirePositive
    // ==========================================================
    @Nested
    class RequirePositiveTests {

        @Test
        void testReturnsAPositiveValue() {
            assertEquals(5, OptionalPracticeChallenge.requirePositive(Optional.of(5)));
            assertEquals(1, OptionalPracticeChallenge.requirePositive(Optional.of(1)));
        }

        @Test
        void testEmptyThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> OptionalPracticeChallenge.requirePositive(Optional.empty()));
        }

        @Test
        void testZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> OptionalPracticeChallenge.requirePositive(Optional.of(0)));
        }

        @Test
        void testNegativeThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> OptionalPracticeChallenge.requirePositive(Optional.of(-3)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: isAdult
    // ==========================================================
    @Nested
    class IsAdultTests {

        @Test
        void testEighteenOrMoreIsAdult() {
            assertTrue(OptionalPracticeChallenge.isAdult(Optional.of(18)));
            assertTrue(OptionalPracticeChallenge.isAdult(Optional.of(45)));
        }

        @Test
        void testUnderEighteenIsNotAdult() {
            assertFalse(OptionalPracticeChallenge.isAdult(Optional.of(17)));
            assertFalse(OptionalPracticeChallenge.isAdult(Optional.of(0)));
        }

        @Test
        void testUnknownAgeIsNotAdult() {
            assertFalse(OptionalPracticeChallenge.isAdult(Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: presentValues
    // ==========================================================
    @Nested
    class PresentValuesTests {

        @Test
        void testSkipsEmptyOptionalsAndKeepsOrder() {
            assertEquals(List.of("a", "c", "d"), OptionalPracticeChallenge.presentValues(
                    List.of(Optional.of("a"), Optional.empty(), Optional.of("c"), Optional.of("d"))));
        }

        @Test
        void testKeepsRepeatedValues() {
            assertEquals(List.of("x", "x", "y"), OptionalPracticeChallenge.presentValues(
                    List.of(Optional.of("x"), Optional.empty(), Optional.of("x"), Optional.of("y"))));
        }

        @Test
        void testAnEmptyStringIsAValue() {
            assertEquals(List.of(""), OptionalPracticeChallenge.presentValues(
                    List.of(Optional.of(""), Optional.empty())));
        }

        @Test
        void testOnlyEmptyOptionalsAndEmptyList() {
            assertEquals(List.of(), OptionalPracticeChallenge.presentValues(
                    List.of(Optional.empty(), Optional.empty())));
            assertEquals(List.of(), OptionalPracticeChallenge.presentValues(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 11: ownerCity
    // ==========================================================
    @Nested
    class OwnerCityTests {

        @Test
        void testFullChain() {
            OptionalPracticeChallenge.Company company = new OptionalPracticeChallenge.Company("Acme",
                    new OptionalPracticeChallenge.User("Ana",
                            new OptionalPracticeChallenge.Address("Main St 1", "Lima")));

            assertEquals(Optional.of("Lima"), OptionalPracticeChallenge.ownerCity(company));
        }

        @Test
        void testNullCompany() {
            assertEquals(Optional.empty(), OptionalPracticeChallenge.ownerCity(null));
        }

        @Test
        void testNullOwner() {
            assertEquals(Optional.empty(),
                    OptionalPracticeChallenge.ownerCity(new OptionalPracticeChallenge.Company("Acme", null)));
        }

        @Test
        void testNullAddress() {
            OptionalPracticeChallenge.Company company = new OptionalPracticeChallenge.Company("Acme",
                    new OptionalPracticeChallenge.User("Ana", null));

            assertEquals(Optional.empty(), OptionalPracticeChallenge.ownerCity(company));
        }

        @Test
        void testNullCity() {
            OptionalPracticeChallenge.Company company = new OptionalPracticeChallenge.Company("Acme",
                    new OptionalPracticeChallenge.User("Ana",
                            new OptionalPracticeChallenge.Address("Main St 1", null)));

            assertEquals(Optional.empty(), OptionalPracticeChallenge.ownerCity(company));
        }

        @Test
        void testAnEmptyCityIsStillACity() {
            OptionalPracticeChallenge.Company company = new OptionalPracticeChallenge.Company("Acme",
                    new OptionalPracticeChallenge.User("Ana",
                            new OptionalPracticeChallenge.Address("Main St 1", "")));

            assertEquals(Optional.of(""), OptionalPracticeChallenge.ownerCity(company));
        }
    }

    // ==========================================================
    // CHALLENGE 12: resolveSetting
    // ==========================================================
    @Nested
    class ResolveSettingTests {

        private int calls;

        private String supply() {
            calls++;
            return "default";
        }

        @Test
        void testEnvironmentWins() {
            calls = 0;

            String result = OptionalPracticeChallenge.resolveSetting("port",
                    Map.of("port", "8080"), Map.of("port", "9090"), this::supply);

            assertEquals("8080", result);
            assertEquals(0, calls);
        }

        @Test
        void testFileIsUsedWhenEnvironmentLacksTheKey() {
            calls = 0;

            String result = OptionalPracticeChallenge.resolveSetting("port",
                    Map.of("host", "x"), Map.of("port", "9090"), this::supply);

            assertEquals("9090", result);
            assertEquals(0, calls);
        }

        @Test
        void testBlankEnvironmentValueIsSkipped() {
            calls = 0;

            String result = OptionalPracticeChallenge.resolveSetting("port",
                    Map.of("port", "   "), Map.of("port", "9090"), this::supply);

            assertEquals("9090", result);
            assertEquals(0, calls);
        }

        @Test
        void testNullValuesAreSkipped() {
            calls = 0;
            Map<String, String> env = new HashMap<>();
            env.put("port", null);
            Map<String, String> file = new HashMap<>();
            file.put("port", null);

            String result = OptionalPracticeChallenge.resolveSetting("port", env, file, this::supply);

            assertEquals("default", result);
            assertEquals(1, calls);
        }

        @Test
        void testDefaultIsUsedExactlyOnceWhenNothingIsUsable() {
            calls = 0;

            String result = OptionalPracticeChallenge.resolveSetting("port",
                    Map.of("port", ""), Map.of(), this::supply);

            assertEquals("default", result);
            assertEquals(1, calls);
        }

        @Test
        void testValueIsReturnedAsItIsWithoutTrimming() {
            calls = 0;

            String result = OptionalPracticeChallenge.resolveSetting("name",
                    Map.of("name", " bob "), Map.of(), this::supply);

            assertEquals(" bob ", result);
            assertEquals(0, calls);
        }
    }

    // ==========================================================
    // CHALLENGE 13: pricesOfAll
    // ==========================================================
    @Nested
    class PricesOfAllTests {

        private final Map<String, Integer> prices = Map.of("a", 10, "b", 20, "c", 30);

        private Function<String, Optional<Integer>> recording(List<String> calls) {
            return id -> {
                calls.add(id);
                return Optional.ofNullable(prices.get(id));
            };
        }

        @Test
        void testAllPricesInOrder() {
            List<String> calls = new ArrayList<>();

            Optional<List<Integer>> result =
                    OptionalPracticeChallenge.pricesOfAll(List.of("c", "a", "b"), recording(calls));

            assertEquals(Optional.of(List.of(30, 10, 20)), result);
            assertEquals(List.of("c", "a", "b"), calls);
        }

        @Test
        void testOneMissingPriceGivesEmpty() {
            List<String> calls = new ArrayList<>();

            Optional<List<Integer>> result =
                    OptionalPracticeChallenge.pricesOfAll(List.of("a", "zzz", "b"), recording(calls));

            assertEquals(Optional.empty(), result);
        }

        @Test
        void testStopsLookingUpAfterTheFirstMissingPrice() {
            List<String> calls = new ArrayList<>();

            OptionalPracticeChallenge.pricesOfAll(List.of("a", "b", "zzz", "c", "yyy"), recording(calls));

            assertEquals(List.of("a", "b", "zzz"), calls);
        }

        @Test
        void testMissingFirstPriceMeansASingleLookup() {
            List<String> calls = new ArrayList<>();

            Optional<List<Integer>> result =
                    OptionalPracticeChallenge.pricesOfAll(List.of("zzz", "a", "b"), recording(calls));

            assertEquals(Optional.empty(), result);
            assertEquals(List.of("zzz"), calls);
        }

        @Test
        void testRepeatedIdsAreLookedUpOncePerOccurrence() {
            List<String> calls = new ArrayList<>();

            Optional<List<Integer>> result =
                    OptionalPracticeChallenge.pricesOfAll(List.of("a", "a", "b"), recording(calls));

            assertEquals(Optional.of(List.of(10, 10, 20)), result);
            assertEquals(List.of("a", "a", "b"), calls);
        }

        @Test
        void testEmptyIdsGiveAPresentEmptyList() {
            List<String> calls = new ArrayList<>();

            Optional<List<Integer>> result = OptionalPracticeChallenge.pricesOfAll(List.of(), recording(calls));

            assertTrue(result.isPresent());
            assertEquals(List.of(), result.get());
            assertEquals(List.of(), calls);
        }
    }
}