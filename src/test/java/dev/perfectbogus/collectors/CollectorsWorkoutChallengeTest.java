package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollectorsWorkoutChallengeTest {

    private static CollectorsWorkoutChallenge.Order o(String customer, String city, int amount) {
        return new CollectorsWorkoutChallenge.Order(customer, city, amount);
    }

    // ==========================================================
    // CHALLENGE 1: firstSeenCities
    // ==========================================================
    @Nested
    class FirstSeenCitiesTests {

        @Test
        void testRepeatedCity() {
            LinkedHashSet<String> result = CollectorsWorkoutChallenge.firstSeenCities(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 50), o("cy", "Lima", 30)));
            assertEquals(List.of("Lima", "Quito"), new ArrayList<>(result));
        }

        @Test
        void testOrderOfFirstAppearance() {
            LinkedHashSet<String> result = CollectorsWorkoutChallenge.firstSeenCities(
                    List.of(o("bob", "Quito", 50), o("ana", "Lima", 100)));
            assertEquals(List.of("Quito", "Lima"), new ArrayList<>(result));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectorsWorkoutChallenge.firstSeenCities(List.of()).isEmpty());
        }

        @Test
        void testOneOrder() {
            assertEquals(List.of("Rome"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("ana", "Rome", 1)))));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals(List.of("Zurich", "Atlanta", "Madrid"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("a", "Zurich", 1), o("b", "Atlanta", 1),
                            o("c", "Madrid", 1)))));
        }

        @Test
        void testOrderIsNotTheLastAppearance() {
            assertEquals(List.of("A", "B", "C"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("x", "A", 1), o("x", "B", 1), o("x", "C", 1),
                            o("x", "A", 1), o("x", "B", 1)))));
        }

        @Test
        void testAllTheSameCity() {
            LinkedHashSet<String> result = CollectorsWorkoutChallenge.firstSeenCities(
                    List.of(o("a", "Lima", 1), o("b", "Lima", 2), o("c", "Lima", 3)));
            assertEquals(List.of("Lima"), new ArrayList<>(result));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(List.of("Lima", "lima"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("a", "Lima", 1), o("b", "lima", 1)))));
        }

        @Test
        void testSpacesMatter() {
            assertEquals(List.of(" Lima", "Lima"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("a", " Lima", 1), o("b", "Lima", 1)))));
        }

        @Test
        void testNewCityAtTheEndOfABigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "same", 1));
            }
            orders.add(o("c", "last", 1));
            assertEquals(List.of("same", "last"), new ArrayList<>(CollectorsWorkoutChallenge.firstSeenCities(orders)));
        }

        @Test
        void testEmptyCityName() {
            assertEquals(List.of("", "Lima"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("a", "", 1), o("b", "Lima", 1),
                            o("c", "", 1)))));
        }

        @Test
        void testAmountsAndCustomersDoNotMatter() {
            assertEquals(List.of("Lima", "Quito"), new ArrayList<>(
                    CollectorsWorkoutChallenge.firstSeenCities(List.of(o("ana", "Lima", 5), o("ana", "Lima", 9),
                            o("zed", "Quito", 0), o("ana", "Lima", 9)))));
        }

        @Test
        void testListIsNotModified() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 50)));
            CollectorsWorkoutChallenge.firstSeenCities(orders);
            assertEquals(List.of(o("ana", "Lima", 100), o("bob", "Quito", 50)), orders);
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "city" + (199_999 - i % 1000), 1));
            }
            List<String> result = new ArrayList<>(CollectorsWorkoutChallenge.firstSeenCities(orders));
            assertEquals(1000, result.size());
            assertEquals("city199999", result.get(0));
            assertEquals("city199000", result.get(999));
        }
    }

    // ==========================================================
    // CHALLENGE 2: totalAmount
    // ==========================================================
    @Nested
    class TotalAmountTests {

        @Test
        void testTwoOrders() {
            assertEquals(150L, CollectorsWorkoutChallenge.totalAmount(List.of(o("ana", "Lima", 100),
                    o("bob", "Quito", 50))));
        }

        @Test
        void testSumBiggerThanAnInt() {
            assertEquals(4_000_000_000L, CollectorsWorkoutChallenge.totalAmount(
                    List.of(o("ana", "Lima", 2_000_000_000), o("bob", "Lima", 2_000_000_000))));
        }

        @Test
        void testEmptyList() {
            assertEquals(0L, CollectorsWorkoutChallenge.totalAmount(List.of()));
        }

        @Test
        void testOneOrder() {
            assertEquals(7L, CollectorsWorkoutChallenge.totalAmount(List.of(o("ana", "Lima", 7))));
        }

        @Test
        void testZeroAmounts() {
            assertEquals(0L, CollectorsWorkoutChallenge.totalAmount(List.of(o("a", "x", 0), o("b", "y", 0))));
        }

        @Test
        void testEmptyCustomerName() {
            assertEquals(5L, CollectorsWorkoutChallenge.totalAmount(List.of(o("", "x", 5))));
        }

        @Test
        void testManySmallOrders() {
            assertEquals(1_000L, CollectorsWorkoutChallenge.totalAmount(
                    java.util.Collections.nCopies(1000, o("a", "x", 1))));
        }

        @Test
        void testSumJustOverAnInt() {
            assertEquals(2_147_483_648L, CollectorsWorkoutChallenge.totalAmount(
                    List.of(o("a", "x", Integer.MAX_VALUE), o("b", "y", 1))));
        }

        @Test
        void testSumOfThreeBigOrders() {
            assertEquals(6_000_000_000L, CollectorsWorkoutChallenge.totalAmount(List.of(
                    o("a", "x", 2_000_000_000), o("b", "x", 2_000_000_000), o("c", "x", 2_000_000_000))));
        }

        @Test
        void testSumJustBelowTheIntLimit() {
            assertEquals(2_147_483_647L, CollectorsWorkoutChallenge.totalAmount(
                    List.of(o("a", "x", Integer.MAX_VALUE), o("b", "y", 0))));
        }

        @Test
        void testSameCityAndCustomerAreAllAdded() {
            assertEquals(30L, CollectorsWorkoutChallenge.totalAmount(
                    List.of(o("ana", "Lima", 10), o("ana", "Lima", 10), o("ana", "Lima", 10))));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                orders.add(o("a", "x", 4_000_000));
            }
            assertEquals(4_000_000_000_000L, CollectorsWorkoutChallenge.totalAmount(orders));
        }
    }

    // ==========================================================
    // CHALLENGE 3: orderCountsByCityInOrder
    // ==========================================================
    @Nested
    class OrderCountsByCityInOrderTests {

        @Test
        void testRepeatedCity() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("ana", "Lima", 1), o("bob", "Quito", 2), o("cy", "Lima", 3)));
            assertEquals(Map.of("Lima", 2L, "Quito", 1L), result);
            assertEquals(List.of("Lima", "Quito"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testOrderOfFirstAppearance() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("bob", "Quito", 2), o("ana", "Lima", 1)));
            assertEquals(Map.of("Quito", 1L, "Lima", 1L), result);
            assertEquals(List.of("Quito", "Lima"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectorsWorkoutChallenge.orderCountsByCityInOrder(List.of()).isEmpty());
        }

        @Test
        void testOneOrder() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("ana", "Rome", 5)));
            assertEquals(Map.of("Rome", 1L), result);
        }

        @Test
        void testAllTheSameCity() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("a", "Lima", 1), o("b", "Lima", 1), o("c", "Lima", 1)));
            assertEquals(Map.of("Lima", 3L), result);
        }

        @Test
        void testOrderIsNotAlphabetical() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("a", "Zurich", 1), o("b", "Atlanta", 1), o("c", "Madrid", 1)));
            assertEquals(List.of("Zurich", "Atlanta", "Madrid"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testOrderIsNotTheLastAppearance() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "C", 1), o("x", "A", 1), o("x", "B", 1)));
            assertEquals(List.of("A", "B", "C"), new ArrayList<>(result.keySet()));
            assertEquals(Map.of("A", 2L, "B", 2L, "C", 1L), result);
        }

        @Test
        void testOrderIsNotTheOrderOfCounts() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "B", 1), o("x", "B", 1), o("x", "C", 1),
                            o("x", "C", 1)));
            assertEquals(List.of("A", "B", "C"), new ArrayList<>(result.keySet()));
            assertEquals(Map.of("A", 1L, "B", 3L, "C", 2L), result);
        }

        @Test
        void testAmountsAreNotCounted() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("a", "Lima", 500), o("b", "Lima", 700)));
            assertEquals(Map.of("Lima", 2L), result);
        }

        @Test
        void testTheCaseMatters() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("a", "Lima", 1), o("b", "lima", 1)));
            assertEquals(Map.of("Lima", 1L, "lima", 1L), result);
        }

        @Test
        void testCountsAreLong() {
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(
                    List.of(o("a", "Lima", 1)));
            assertEquals((Object) 1L, result.get("Lima"));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "city" + (999 - i % 1000), 1));
            }
            LinkedHashMap<String, Long> result = CollectorsWorkoutChallenge.orderCountsByCityInOrder(orders);
            assertEquals(1000, result.size());
            assertEquals("city999", result.keySet().iterator().next());
            assertEquals((Object) 200L, result.get("city500"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: greatestAmount
    // ==========================================================
    @Nested
    class GreatestAmountTests {

        @Test
        void testThreeOrders() {
            assertEquals(250, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 250), o("cy", "Lima", 30))));
        }

        @Test
        void testOnlyZero() {
            assertEquals(0, CollectorsWorkoutChallenge.greatestAmount(List.of(o("ana", "Lima", 0))));
        }

        @Test
        void testEmptyList() {
            assertEquals(-1, CollectorsWorkoutChallenge.greatestAmount(List.of()));
        }

        @Test
        void testOneOrder() {
            assertEquals(9, CollectorsWorkoutChallenge.greatestAmount(List.of(o("ana", "Lima", 9))));
        }

        @Test
        void testGreatestFirst() {
            assertEquals(300, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 300), o("b", "x", 20), o("c", "x", 10))));
        }

        @Test
        void testGreatestLast() {
            assertEquals(300, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 10), o("b", "x", 20), o("c", "x", 300))));
        }

        @Test
        void testRepeatedGreatest() {
            assertEquals(50, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 50), o("b", "x", 50), o("c", "x", 5))));
        }

        @Test
        void testZerosAndPositives() {
            assertEquals(1, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 0), o("b", "x", 1), o("c", "x", 0))));
        }

        @Test
        void testAllZeros() {
            assertEquals(0, CollectorsWorkoutChallenge.greatestAmount(List.of(o("a", "x", 0), o("b", "x", 0))));
        }

        @Test
        void testBiggestInt() {
            assertEquals(Integer.MAX_VALUE, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 5), o("b", "x", Integer.MAX_VALUE))));
        }

        @Test
        void testGreatestIsNotTheLargestSum() {
            assertEquals(60, CollectorsWorkoutChallenge.greatestAmount(
                    List.of(o("a", "x", 40), o("a", "x", 40), o("b", "y", 60))));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                orders.add(o("a", "x", i));
            }
            assertEquals(999_999, CollectorsWorkoutChallenge.greatestAmount(orders));
        }
    }

    // ==========================================================
    // CHALLENGE 5: customerTotalsByCity
    // ==========================================================
    @Nested
    class CustomerTotalsByCityTests {

        @Test
        void testTwoCities() {
            assertEquals(Map.of("Lima", Map.of("ana", 120, "bob", 50), "Quito", Map.of("ana", 5)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(List.of(o("ana", "Lima", 100),
                            o("bob", "Lima", 50), o("ana", "Lima", 20), o("ana", "Quito", 5))));
        }

        @Test
        void testOneOrder() {
            assertEquals(Map.of("Rome", Map.of("ana", 7)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(List.of(o("ana", "Rome", 7))));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectorsWorkoutChallenge.customerTotalsByCity(List.of()).isEmpty());
        }

        @Test
        void testSameCustomerInTwoCitiesIsNotAdded() {
            assertEquals(Map.of("Lima", Map.of("ana", 10), "Quito", Map.of("ana", 20)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(
                            List.of(o("ana", "Lima", 10), o("ana", "Quito", 20))));
        }

        @Test
        void testTwoCustomersInTheSameCityAreNotAdded() {
            assertEquals(Map.of("Lima", Map.of("ana", 10, "bob", 20)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(
                            List.of(o("ana", "Lima", 10), o("bob", "Lima", 20))));
        }

        @Test
        void testSameCustomerAndCityAreAdded() {
            assertEquals(Map.of("Lima", Map.of("ana", 60)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(
                            List.of(o("ana", "Lima", 10), o("ana", "Lima", 20), o("ana", "Lima", 30))));
        }

        @Test
        void testAmountsNotCounted() {
            assertEquals(Map.of("Lima", Map.of("ana", 1000)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(List.of(o("ana", "Lima", 1000))));
        }

        @Test
        void testInnerMapIsNotTheCount() {
            assertEquals(Map.of("Lima", Map.of("ana", 0)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(List.of(o("ana", "Lima", 0))));
        }

        @Test
        void testMoreCombinations() {
            assertEquals(Map.of("A", Map.of("x", 3, "y", 4), "B", Map.of("x", 5), "C", Map.of("z", 6)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(List.of(o("x", "A", 1), o("y", "A", 4),
                            o("x", "B", 5), o("z", "C", 6), o("x", "A", 2))));
        }

        @Test
        void testCitiesAndCustomersAreNotSwapped() {
            Map<String, Map<String, Integer>> result = CollectorsWorkoutChallenge.customerTotalsByCity(
                    List.of(o("ana", "Lima", 10)));
            assertTrue(result.containsKey("Lima"));
            assertFalse(result.containsKey("ana"));
            assertEquals(Map.of("ana", 10), result.get("Lima"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(Map.of("Lima", Map.of("ana", 1, "Ana", 2)),
                    CollectorsWorkoutChallenge.customerTotalsByCity(
                            List.of(o("ana", "Lima", 1), o("Ana", "Lima", 2))));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("customer" + i % 10, "city" + i % 100, 1));
            }
            Map<String, Map<String, Integer>> result = CollectorsWorkoutChallenge.customerTotalsByCity(orders);
            assertEquals(100, result.size());
            assertEquals(Map.of("customer0", 2000), result.get("city0"));
            assertEquals(Map.of("customer9", 2000), result.get("city99"));
        }
    }

    // ==========================================================
    // CHALLENGE 6: customersByCityText
    // ==========================================================
    @Nested
    class CustomersByCityTextTests {

        @Test
        void testTwoCities() {
            assertEquals(Map.of("Lima", "ana, cy", "Quito", "bob"),
                    CollectorsWorkoutChallenge.customersByCityText(
                            List.of(o("ana", "Lima", 1), o("bob", "Quito", 2), o("cy", "Lima", 3))));
        }

        @Test
        void testRepeatedCustomer() {
            assertEquals(Map.of("Lima", "ana, ana"), CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("ana", "Lima", 1), o("ana", "Lima", 2))));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectorsWorkoutChallenge.customersByCityText(List.of()).isEmpty());
        }

        @Test
        void testOneOrder() {
            assertEquals(Map.of("Rome", "ana"),
                    CollectorsWorkoutChallenge.customersByCityText(List.of(o("ana", "Rome", 1))));
        }

        @Test
        void testOrderOfTheList() {
            assertEquals(Map.of("Lima", "zed, ana, mia"), CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("zed", "Lima", 1), o("ana", "Lima", 2), o("mia", "Lima", 3))));
        }

        @Test
        void testSeparatorHasASpace() {
            assertEquals("a, b", CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("a", "X", 1), o("b", "X", 2))).get("X"));
        }

        @Test
        void testNoBracketsOrExtraSeparators() {
            assertEquals("ana", CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("ana", "X", 1))).get("X"));
        }

        @Test
        void testAmountsAreNotShown() {
            assertEquals(Map.of("Lima", "ana"), CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("ana", "Lima", 12345))));
        }

        @Test
        void testRepeatedNotInOrderOfFirst() {
            assertEquals(Map.of("A", "x, y, x", "B", "y"), CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("x", "A", 1), o("y", "A", 1), o("y", "B", 1), o("x", "A", 1))));
        }

        @Test
        void testCitiesAreKeys() {
            Map<String, String> result = CollectorsWorkoutChallenge.customersByCityText(
                    List.of(o("ana", "Lima", 1), o("bob", "Quito", 2)));
            assertEquals(2, result.size());
            assertEquals("ana", result.get("Lima"));
            assertEquals("bob", result.get("Quito"));
        }

        @Test
        void testManyOrdersInOneCity() {
            assertEquals("a, b, c, d, e, f", CollectorsWorkoutChallenge.customersByCityText(List.of(
                    o("a", "X", 1), o("b", "X", 1), o("c", "X", 1), o("d", "X", 1), o("e", "X", 1),
                    o("f", "X", 1))).get("X"));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 300_000; i++) {
                orders.add(o("c" + i % 3, "city" + i % 2, 1));
            }
            Map<String, String> result = CollectorsWorkoutChallenge.customersByCityText(orders);
            assertEquals(2, result.size());
            assertTrue(result.get("city0").startsWith("c0, c2, c1, c0"));
            assertTrue(result.get("city1").startsWith("c1, c0, c2, c1"));
            assertEquals(150_000 * 2 + 149_999 * 2, result.get("city0").length());
        }
    }

    // ==========================================================
    // CHALLENGE 7: totalsAboveAndBelow
    // ==========================================================
    @Nested
    class TotalsAboveAndBelowTests {

        @Test
        void testBothGroups() {
            assertEquals(Map.of(false, 50, true, 300), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 50), o("cy", "Lima", 200)), 100));
        }

        @Test
        void testOnlyTheLowGroup() {
            assertEquals(Map.of(false, 10, true, 0),
                    CollectorsWorkoutChallenge.totalsAboveAndBelow(List.of(o("ana", "Lima", 10)), 100));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(false, 0, true, 0), CollectorsWorkoutChallenge.totalsAboveAndBelow(List.of(), 5));
        }

        @Test
        void testOnlyTheHighGroup() {
            assertEquals(Map.of(false, 0, true, 300), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 200)), 100));
        }

        @Test
        void testTheLimitBelongsToTheHighGroup() {
            assertEquals(Map.of(false, 99, true, 100), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 100), o("b", "x", 99)), 100));
        }

        @Test
        void testBothKeysAlwaysPresent() {
            Map<Boolean, Integer> result = CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 1)), 100);
            assertEquals(2, result.size());
            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        void testSumsNotCounts() {
            assertEquals(Map.of(false, 0, true, 5000), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 5000)), 1));
        }

        @Test
        void testLimitZeroPutsEverythingInTrue() {
            assertEquals(Map.of(false, 0, true, 7), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 0), o("b", "x", 7)), 0));
        }

        @Test
        void testHighLimitPutsEverythingInFalse() {
            assertEquals(Map.of(false, 7, true, 0), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 3), o("b", "x", 4)), 1000));
        }

        @Test
        void testManyOrdersOfEachGroup() {
            assertEquals(Map.of(false, 6, true, 150), CollectorsWorkoutChallenge.totalsAboveAndBelow(
                    List.of(o("a", "x", 1), o("b", "x", 2), o("c", "x", 3), o("d", "x", 50), o("e", "x", 50),
                            o("f", "x", 50)), 50));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                orders.add(o("a", "x", i % 2 == 0 ? 120 : 1));
            }
            assertEquals(Map.of(false, 500_000, true, 60_000_000),
                    CollectorsWorkoutChallenge.totalsAboveAndBelow(orders, 100));
        }
    }

    // ==========================================================
    // CHALLENGE 8: cityWithMostOrders
    // ==========================================================
    @Nested
    class CityWithMostOrdersTests {

        @Test
        void testClearWinner() {
            assertEquals("Quito", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("ana", "Lima", 1), o("bob", "Quito", 2), o("cy", "Quito", 3))));
        }

        @Test
        void testTieFirstWins() {
            assertEquals("Lima", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("ana", "Lima", 1), o("bob", "Quito", 2))));
        }

        @Test
        void testEmptyList() {
            assertEquals("none", CollectorsWorkoutChallenge.cityWithMostOrders(List.of()));
        }

        @Test
        void testOneOrder() {
            assertEquals("Rome", CollectorsWorkoutChallenge.cityWithMostOrders(List.of(o("ana", "Rome", 1))));
        }

        @Test
        void testTieFirstWinsInverted() {
            assertEquals("Quito", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("bob", "Quito", 2), o("ana", "Lima", 1))));
        }

        @Test
        void testTieFirstAppearanceNotFirstToReachTheCount() {
            assertEquals("A", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "B", 1), o("x", "A", 1))));
        }

        @Test
        void testThreeWayTieFirstWins() {
            assertEquals("Zurich", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("x", "Zurich", 1), o("x", "Atlanta", 1), o("x", "Madrid", 1))));
        }

        @Test
        void testTieBetweenLaterCitiesFirstOfThemWins() {
            assertEquals("B", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "C", 1), o("x", "C", 1), o("x", "B", 1))));
        }

        @Test
        void testLastCityWins() {
            assertEquals("C", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "C", 1), o("x", "C", 1))));
        }

        @Test
        void testAmountsDoNotMatter() {
            assertEquals("Lima", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("a", "Lima", 1), o("b", "Lima", 1), o("c", "Quito", 1_000_000))));
        }

        @Test
        void testCustomersDoNotMatter() {
            assertEquals("Lima", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("a", "Lima", 1), o("a", "Lima", 1), o("a", "Lima", 1), o("b", "Quito", 1),
                            o("c", "Quito", 1))));
        }

        @Test
        void testNotAlphabetical() {
            assertEquals("Zurich", CollectorsWorkoutChallenge.cityWithMostOrders(
                    List.of(o("a", "Atlanta", 1), o("b", "Zurich", 1), o("c", "Zurich", 1))));
        }

        @Test
        void testCityCalledNone() {
            assertEquals("none", CollectorsWorkoutChallenge.cityWithMostOrders(List.of(o("a", "none", 1))));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "city" + (i % 1000), 1));
            }
            orders.add(o("c", "city777", 1));
            assertEquals("city777", CollectorsWorkoutChallenge.cityWithMostOrders(orders));
        }

        @Test
        void testBigListTie() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "city" + (999 - i % 1000), 1));
            }
            assertEquals("city999", CollectorsWorkoutChallenge.cityWithMostOrders(orders));
        }
    }

    // ==========================================================
    // CHALLENGE 9: bigOrderCounter
    // ==========================================================
    @Nested
    class BigOrderCounterTests {

        private final List<CollectorsWorkoutChallenge.Order> orders = List.of(
                o("ana", "Lima", 100), o("bob", "Quito", 50), o("cy", "Lima", 300));

        @Test
        void testSomeOrders() {
            assertEquals((Object) 2L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(100)));
        }

        @Test
        void testNoOrders() {
            assertEquals((Object) 0L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(301)));
        }

        @Test
        void testEmptyStream() {
            assertEquals((Object) 0L,
                    Stream.<CollectorsWorkoutChallenge.Order>empty().collect(
                            CollectorsWorkoutChallenge.bigOrderCounter(0)));
        }

        @Test
        void testAllOrders() {
            assertEquals((Object) 3L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(0)));
        }

        @Test
        void testTheLimitCounts() {
            assertEquals((Object) 1L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(300)));
            assertEquals((Object) 1L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(101)));
        }

        @Test
        void testJustBelowTheLimit() {
            assertEquals((Object) 3L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(50)));
            assertEquals((Object) 2L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(51)));
        }

        @Test
        void testTwoCollectorsAreIndependent() {
            var first = CollectorsWorkoutChallenge.bigOrderCounter(100);
            var second = CollectorsWorkoutChallenge.bigOrderCounter(100);
            assertEquals((Object) 2L, orders.stream().collect(first));
            assertEquals((Object) 2L, orders.stream().collect(second));
        }

        @Test
        void testSameCollectorTwice() {
            var counter = CollectorsWorkoutChallenge.bigOrderCounter(100);
            assertEquals((Object) 2L, orders.stream().collect(counter));
            assertEquals((Object) 2L, orders.stream().collect(counter));
        }

        @Test
        void testDifferentLimits() {
            assertEquals((Object) 3L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(1)));
            assertEquals((Object) 2L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(60)));
            assertEquals((Object) 1L, orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(200)));
        }

        @Test
        void testWorksAfterAFilter() {
            assertEquals((Object) 1L, orders.stream().filter(order -> order.city().equals("Lima"))
                    .collect(CollectorsWorkoutChallenge.bigOrderCounter(200)));
        }

        @Test
        void testWorksOnAParallelStream() {
            List<CollectorsWorkoutChallenge.Order> big = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                big.add(o("a", "x", i));
            }
            assertEquals((Object) 50_000L,
                    big.parallelStream().collect(CollectorsWorkoutChallenge.bigOrderCounter(50_000)));
        }

        @Test
        void testCountsAreLong() {
            Long result = orders.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(100));
            assertEquals((Object) 2L, result);
        }

        @Test
        void testBigStream() {
            List<CollectorsWorkoutChallenge.Order> big = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                big.add(o("a", "x", i % 10));
            }
            assertEquals((Object) 500_000L, big.stream().collect(CollectorsWorkoutChallenge.bigOrderCounter(5)));
        }
    }

    // ==========================================================
    // CHALLENGE 10: lastCityByCustomer
    // ==========================================================
    @Nested
    class LastCityByCustomerTests {

        @Test
        void testLastCityWins() {
            assertEquals(Map.of("ana", "Rome", "bob", "Quito"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Lima", 1), o("bob", "Quito", 2), o("ana", "Rome", 3))));
        }

        @Test
        void testSameCityTwice() {
            assertEquals(Map.of("ana", "Lima"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Lima", 1), o("ana", "Lima", 2))));
        }

        @Test
        void testEmptyList() {
            assertTrue(CollectorsWorkoutChallenge.lastCityByCustomer(List.of()).isEmpty());
        }

        @Test
        void testOneOrder() {
            assertEquals(Map.of("ana", "Rome"),
                    CollectorsWorkoutChallenge.lastCityByCustomer(List.of(o("ana", "Rome", 1))));
        }

        @Test
        void testFirstCityIsNotKept() {
            assertEquals(Map.of("ana", "C"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "A", 1), o("ana", "B", 1), o("ana", "C", 1))));
        }

        @Test
        void testLastIsNotTheGreatestAmount() {
            assertEquals(Map.of("ana", "A"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "B", 900), o("ana", "A", 1))));
        }

        @Test
        void testLastIsNotTheAlphabeticallyLast() {
            assertEquals(Map.of("ana", "Atlanta"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Zurich", 1), o("ana", "Atlanta", 1))));
        }

        @Test
        void testSeveralCustomersInterleaved() {
            assertEquals(Map.of("a", "Z", "b", "Y", "c", "X"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("a", "A", 1), o("b", "B", 1), o("c", "X", 1), o("a", "Z", 1), o("b", "Y", 1))));
        }

        @Test
        void testReturnedToTheFirstCity() {
            assertEquals(Map.of("ana", "Lima"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Lima", 1), o("ana", "Rome", 1), o("ana", "Lima", 1))));
        }

        @Test
        void testCustomersAreKeysNotCities() {
            Map<String, String> result = CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Lima", 1), o("bob", "Lima", 2)));
            assertEquals(2, result.size());
            assertEquals("Lima", result.get("ana"));
            assertEquals("Lima", result.get("bob"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(Map.of("ana", "Lima", "Ana", "Rome"), CollectorsWorkoutChallenge.lastCityByCustomer(
                    List.of(o("ana", "Lima", 1), o("Ana", "Rome", 1))));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("customer" + i % 100, "city" + i, 1));
            }
            Map<String, String> result = CollectorsWorkoutChallenge.lastCityByCustomer(orders);
            assertEquals(100, result.size());
            assertEquals("city199900", result.get("customer0"));
            assertEquals("city199999", result.get("customer99"));
        }
    }

    // ==========================================================
    // CHALLENGE 11: cityTotalsText
    // ==========================================================
    @Nested
    class CityTotalsTextTests {

        @Test
        void testRepeatedCity() {
            assertEquals("Lima=130, Quito=50", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("ana", "Lima", 100), o("bob", "Quito", 50), o("cy", "Lima", 30))));
        }

        @Test
        void testOrderOfFirstAppearance() {
            assertEquals("Quito=50, Lima=1", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("bob", "Quito", 50), o("ana", "Lima", 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals("", CollectorsWorkoutChallenge.cityTotalsText(List.of()));
        }

        @Test
        void testOneOrder() {
            assertEquals("Rome=7", CollectorsWorkoutChallenge.cityTotalsText(List.of(o("ana", "Rome", 7))));
        }

        @Test
        void testNoBracketsOrExtraSeparators() {
            String text = CollectorsWorkoutChallenge.cityTotalsText(List.of(o("a", "X", 1), o("b", "Y", 2)));
            assertEquals("X=1, Y=2", text);
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals("Zurich=1, Atlanta=2, Madrid=3", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("a", "Zurich", 1), o("b", "Atlanta", 2), o("c", "Madrid", 3))));
        }

        @Test
        void testOrderIsNotTheLastAppearance() {
            assertEquals("A=3, B=3, C=1", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("x", "A", 1), o("x", "B", 1), o("x", "C", 1), o("x", "A", 2), o("x", "B", 2))));
        }

        @Test
        void testOrderIsNotTheOrderOfTotals() {
            assertEquals("A=1, B=100, C=10", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("x", "A", 1), o("x", "B", 100), o("x", "C", 10))));
        }

        @Test
        void testTotalsNotCounts() {
            assertEquals("Lima=0", CollectorsWorkoutChallenge.cityTotalsText(List.of(o("a", "Lima", 0))));
            assertEquals("Lima=1000", CollectorsWorkoutChallenge.cityTotalsText(List.of(o("a", "Lima", 1000))));
        }

        @Test
        void testSameCityManyOrders() {
            assertEquals("Lima=60", CollectorsWorkoutChallenge.cityTotalsText(
                    List.of(o("a", "Lima", 10), o("b", "Lima", 20), o("c", "Lima", 30))));
        }

        @Test
        void testSeparatorHasASpace() {
            assertTrue(CollectorsWorkoutChallenge.cityTotalsText(List.of(o("a", "X", 1), o("b", "Y", 2)))
                    .contains(", "));
        }

        @Test
        void testBigList() {
            List<CollectorsWorkoutChallenge.Order> orders = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                orders.add(o("c", "city" + (9 - i % 10), 1));
            }
            String text = CollectorsWorkoutChallenge.cityTotalsText(orders);
            assertTrue(text.startsWith("city9=20000, city8=20000, city7=20000"));
            assertTrue(text.endsWith("city0=20000"));
        }
    }
}