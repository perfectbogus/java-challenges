package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class CollectorsFunctionsChallengeTest {

    private static CollectorsFunctionsChallenge.Product p(String name, String category, int price, int stock) {
        return new CollectorsFunctionsChallenge.Product(name, category, price, stock);
    }

    // shop = [Pen(Office, 2, 100), Desk(Home, 150, 5), Clip(Office, 1, 500)]
    private static List<CollectorsFunctionsChallenge.Product> shop() {
        return List.of(p("Pen", "Office", 2, 100), p("Desk", "Home", 150, 5), p("Clip", "Office", 1, 500));
    }

    // ==========================================================
    // CHALLENGE 1: distinctCategories
    // ==========================================================
    @Nested
    class DistinctCategoriesTests {

        @Test
        void testReturnsTheCategories() {
            assertEquals(Set.of("Office", "Home"), CollectorsFunctionsChallenge.distinctCategories(shop()));
        }

        @Test
        void testRepeatedCategoriesAppearOnce() {
            Set<String> result = CollectorsFunctionsChallenge.distinctCategories(List.of(
                    p("A", "X", 1, 1), p("B", "X", 1, 1), p("C", "X", 1, 1)));

            assertEquals(Set.of("X"), result);
            assertEquals(1, result.size());
        }

        @Test
        void testOneProduct() {
            assertEquals(Set.of("Home"),
                    CollectorsFunctionsChallenge.distinctCategories(List.of(p("Desk", "Home", 150, 5))));
        }

        @Test
        void testUppercaseAndLowercaseAreDifferentCategories() {
            assertEquals(Set.of("Home", "home"), CollectorsFunctionsChallenge.distinctCategories(List.of(
                    p("A", "Home", 1, 1), p("B", "home", 1, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Set.of(), CollectorsFunctionsChallenge.distinctCategories(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: namesUnmodifiable
    // ==========================================================
    @Nested
    class NamesUnmodifiableTests {

        @Test
        void testNamesInTheOrderOfTheList() {
            assertEquals(List.of("Pen", "Desk", "Clip"), CollectorsFunctionsChallenge.namesUnmodifiable(shop()));
        }

        @Test
        void testAddingThrows() {
            List<String> result = CollectorsFunctionsChallenge.namesUnmodifiable(shop());
            boolean threw = false;

            try {
                result.add("Extra");
            } catch (UnsupportedOperationException e) {
                threw = true;
            }

            assertTrue(threw, "add should throw UnsupportedOperationException");
        }

        @Test
        void testRemovingThrows() {
            List<String> result = CollectorsFunctionsChallenge.namesUnmodifiable(shop());
            boolean threw = false;

            try {
                result.remove(0);
            } catch (UnsupportedOperationException e) {
                threw = true;
            }

            assertTrue(threw, "remove should throw UnsupportedOperationException");
        }

        @Test
        void testRepeatedNamesAreKept() {
            assertEquals(List.of("Pen", "Pen"), CollectorsFunctionsChallenge.namesUnmodifiable(
                    List.of(p("Pen", "Office", 2, 1), p("Pen", "Home", 3, 1))));
        }

        @Test
        void testEmptyListGivesAnEmptyListThatCannotBeModified() {
            List<String> result = CollectorsFunctionsChallenge.namesUnmodifiable(List.of());
            boolean threw = false;

            try {
                result.add("Extra");
            } catch (UnsupportedOperationException e) {
                threw = true;
            }

            assertEquals(0, result.size());
            assertTrue(threw, "add should throw UnsupportedOperationException");
        }
    }

    // ==========================================================
    // CHALLENGE 3: initials
    // ==========================================================
    @Nested
    class InitialsTests {

        @Test
        void testFirstCharacterOfEachWord() {
            assertEquals("abc", CollectorsFunctionsChallenge.initials(List.of("apple", "banana", "cherry")));
        }

        @Test
        void testOneWord() {
            assertEquals("K", CollectorsFunctionsChallenge.initials(List.of("Kiwi")));
        }

        @Test
        void testEmptyList() {
            assertEquals("", CollectorsFunctionsChallenge.initials(List.of()));
        }

        @Test
        void testUppercaseIsKeptAsItIs() {
            assertEquals("Ab", CollectorsFunctionsChallenge.initials(List.of("Apple", "banana")));
        }

        @Test
        void testRepeatedInitialsAndOneLetterWords() {
            assertEquals("aaxb", CollectorsFunctionsChallenge.initials(List.of("ant", "apple", "x", "bee")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: countAtLeast
    // ==========================================================
    @Nested
    class CountAtLeastTests {

        @Test
        void testCountsTheExpensiveProducts() {
            assertEquals(2L, CollectorsFunctionsChallenge.countAtLeast(shop(), 2));
        }

        @Test
        void testAPriceEqualToTheLimitIsCounted() {
            assertEquals(3L, CollectorsFunctionsChallenge.countAtLeast(shop(), 1));
            assertEquals(1L, CollectorsFunctionsChallenge.countAtLeast(shop(), 150));
        }

        @Test
        void testNoProductIsExpensiveEnough() {
            assertEquals(0L, CollectorsFunctionsChallenge.countAtLeast(shop(), 151));
        }

        @Test
        void testLimitBelowEveryPrice() {
            assertEquals(3L, CollectorsFunctionsChallenge.countAtLeast(shop(), -10));
        }

        @Test
        void testEmptyList() {
            assertEquals(0L, CollectorsFunctionsChallenge.countAtLeast(List.of(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 5: totalStock
    // ==========================================================
    @Nested
    class TotalStockTests {

        @Test
        void testSumsTheStock() {
            assertEquals(605, CollectorsFunctionsChallenge.totalStock(shop()));
        }

        @Test
        void testProductsWithoutStock() {
            assertEquals(7, CollectorsFunctionsChallenge.totalStock(List.of(
                    p("A", "X", 1, 0), p("B", "X", 1, 7), p("C", "Y", 1, 0))));
        }

        @Test
        void testOneProduct() {
            assertEquals(42, CollectorsFunctionsChallenge.totalStock(List.of(p("A", "X", 1, 42))));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectorsFunctionsChallenge.totalStock(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 6: averagePrice
    // ==========================================================
    @Nested
    class AveragePriceTests {

        @Test
        void testAveragePrice() {
            assertEquals(51.0, CollectorsFunctionsChallenge.averagePrice(shop()), 0.0001);
        }

        @Test
        void testTheAverageHasDecimals() {
            assertEquals(1.5, CollectorsFunctionsChallenge.averagePrice(List.of(
                    p("Pen", "Office", 1, 5), p("Cup", "Home", 2, 5))), 0.0001);
        }

        @Test
        void testOneProduct() {
            assertEquals(7.0, CollectorsFunctionsChallenge.averagePrice(List.of(p("A", "X", 7, 1))), 0.0001);
        }

        @Test
        void testRepeatedPrices() {
            assertEquals(4.0, CollectorsFunctionsChallenge.averagePrice(List.of(
                    p("A", "X", 4, 1), p("B", "X", 4, 1), p("C", "X", 4, 1))), 0.0001);
        }

        @Test
        void testEmptyListGivesZero() {
            assertEquals(0.0, CollectorsFunctionsChallenge.averagePrice(List.of()), 0.0001);
        }
    }

    // ==========================================================
    // CHALLENGE 7: cheapest
    // ==========================================================
    @Nested
    class CheapestTests {

        @Test
        void testReturnsTheCheapestProduct() {
            assertEquals(Optional.of(p("Clip", "Office", 1, 500)), CollectorsFunctionsChallenge.cheapest(shop()));
        }

        @Test
        void testTheFirstOneWinsATie() {
            assertEquals(Optional.of(p("Pen", "Office", 2, 5)), CollectorsFunctionsChallenge.cheapest(List.of(
                    p("Pen", "Office", 2, 5), p("Cup", "Home", 2, 7))));
        }

        @Test
        void testTheFirstOneWinsATieAmongTheCheapest() {
            assertEquals(Optional.of(p("B", "X", 1, 1)), CollectorsFunctionsChallenge.cheapest(List.of(
                    p("A", "X", 5, 1), p("B", "X", 1, 1), p("C", "X", 1, 1), p("D", "X", 3, 1))));
        }

        @Test
        void testOnlyThePriceMatters() {
            assertEquals(Optional.of(p("Desk", "Home", 3, 900)), CollectorsFunctionsChallenge.cheapest(List.of(
                    p("Pen", "Office", 9, 1), p("Desk", "Home", 3, 900))));
        }

        @Test
        void testOneProduct() {
            assertEquals(Optional.of(p("A", "X", 5, 1)),
                    CollectorsFunctionsChallenge.cheapest(List.of(p("A", "X", 5, 1))));
        }

        @Test
        void testEmptyListGivesAnEmptyOptional() {
            assertEquals(Optional.empty(), CollectorsFunctionsChallenge.cheapest(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortedUniqueNames
    // ==========================================================
    @Nested
    class SortedUniqueNamesTests {

        @Test
        void testSortedWithoutRepeatedNames() {
            TreeSet<String> result = CollectorsFunctionsChallenge.sortedUniqueNames(List.of(
                    p("Pen", "Office", 2, 100), p("Desk", "Home", 150, 5), p("Pen", "Home", 3, 10)));

            assertEquals(List.of("Desk", "Pen"), new ArrayList<>(result));
        }

        @Test
        void testAlphabeticalOrder() {
            TreeSet<String> result = CollectorsFunctionsChallenge.sortedUniqueNames(shop());

            assertEquals(List.of("Clip", "Desk", "Pen"), new ArrayList<>(result));
            assertEquals("Clip", result.first());
            assertEquals("Pen", result.last());
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            TreeSet<String> result = CollectorsFunctionsChallenge.sortedUniqueNames(List.of(
                    p("pen", "X", 1, 1), p("Zed", "X", 1, 1), p("Ant", "X", 1, 1)));

            assertEquals(List.of("Ant", "Zed", "pen"), new ArrayList<>(result));
        }

        @Test
        void testAllNamesEqual() {
            TreeSet<String> result = CollectorsFunctionsChallenge.sortedUniqueNames(List.of(
                    p("Pen", "X", 1, 1), p("Pen", "X", 2, 2), p("Pen", "Y", 3, 3)));

            assertEquals(1, result.size());
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectorsFunctionsChallenge.sortedUniqueNames(List.of()).size());
        }
    }

    // ==========================================================
    // CHALLENGE 9: priceByName
    // ==========================================================
    @Nested
    class PriceByNameTests {

        @Test
        void testMapsEachNameToItsPrice() {
            assertEquals(Map.of("Pen", 2, "Desk", 150, "Clip", 1), CollectorsFunctionsChallenge.priceByName(shop()));
        }

        @Test
        void testOneProduct() {
            assertEquals(Map.of("Desk", 150),
                    CollectorsFunctionsChallenge.priceByName(List.of(p("Desk", "Home", 150, 5))));
        }

        @Test
        void testTwoProductsWithTheSamePriceAndCategory() {
            assertEquals(Map.of("A", 5, "B", 5),
                    CollectorsFunctionsChallenge.priceByName(List.of(p("A", "X", 5, 1), p("B", "X", 5, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.priceByName(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: totalLengthByInitial
    // ==========================================================
    @Nested
    class TotalLengthByInitialTests {

        @Test
        void testSumsTheLengthsOfWordsWithTheSameInitial() {
            assertEquals(Map.of('a', 12, 'b', 6),
                    CollectorsFunctionsChallenge.totalLengthByInitial(List.of("apple", "avocado", "banana")));
        }

        @Test
        void testRepeatedWordsDoNotFail() {
            assertEquals(Map.of('o', 4, 'O', 2),
                    CollectorsFunctionsChallenge.totalLengthByInitial(List.of("ox", "Ox", "ox")));
        }

        @Test
        void testManyWordsWithTheSameInitial() {
            assertEquals(Map.of('c', 12),
                    CollectorsFunctionsChallenge.totalLengthByInitial(List.of("cat", "cow", "cub", "cap")));
        }

        @Test
        void testEveryWordHasADifferentInitial() {
            assertEquals(Map.of('a', 1, 'b', 2, 'c', 3),
                    CollectorsFunctionsChallenge.totalLengthByInitial(List.of("a", "bb", "ccc")));
        }

        @Test
        void testOneWord() {
            assertEquals(Map.of('z', 3), CollectorsFunctionsChallenge.totalLengthByInitial(List.of("zoo")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.totalLengthByInitial(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 11: countByCategory
    // ==========================================================
    @Nested
    class CountByCategoryTests {

        @Test
        void testCountsTheProductsOfEachCategory() {
            assertEquals(Map.of("Office", 2L, "Home", 1L), CollectorsFunctionsChallenge.countByCategory(shop()));
        }

        @Test
        void testOneCategory() {
            assertEquals(Map.of("X", 3L), CollectorsFunctionsChallenge.countByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "X", 1, 1), p("C", "X", 1, 1))));
        }

        @Test
        void testOneProductPerCategory() {
            assertEquals(Map.of("X", 1L, "Y", 1L), CollectorsFunctionsChallenge.countByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "Y", 1, 1))));
        }

        @Test
        void testTwoIdenticalProductsAreBothCounted() {
            assertEquals(Map.of("X", 2L), CollectorsFunctionsChallenge.countByCategory(List.of(
                    p("A", "X", 1, 1), p("A", "X", 1, 1))));
        }

        @Test
        void testTheCategoriesAreMixedInTheList() {
            assertEquals(Map.of("X", 3L, "Y", 2L), CollectorsFunctionsChallenge.countByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "Y", 1, 1), p("C", "X", 1, 1), p("D", "Y", 1, 1),
                    p("E", "X", 1, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.countByCategory(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 12: namesByCategory
    // ==========================================================
    @Nested
    class NamesByCategoryTests {

        @Test
        void testGroupsTheNamesByCategory() {
            assertEquals(Map.of("Office", List.of("Pen", "Clip"), "Home", List.of("Desk")),
                    CollectorsFunctionsChallenge.namesByCategory(shop()));
        }

        @Test
        void testKeepsTheOrderOfTheListInsideACategory() {
            assertEquals(Map.of("X", List.of("Zed", "Amy", "Max")),
                    CollectorsFunctionsChallenge.namesByCategory(List.of(
                            p("Zed", "X", 1, 1), p("Amy", "X", 1, 1), p("Max", "X", 1, 1))));
        }

        @Test
        void testRepeatedNamesAreKept() {
            assertEquals(Map.of("X", List.of("Pen", "Pen")), CollectorsFunctionsChallenge.namesByCategory(List.of(
                    p("Pen", "X", 1, 1), p("Pen", "X", 2, 2))));
        }

        @Test
        void testTheCategoriesAreMixedInTheList() {
            assertEquals(Map.of("X", List.of("A", "C"), "Y", List.of("B", "D")),
                    CollectorsFunctionsChallenge.namesByCategory(List.of(
                            p("A", "X", 1, 1), p("B", "Y", 1, 1), p("C", "X", 1, 1), p("D", "Y", 1, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.namesByCategory(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 13: totalStockByCategorySorted
    // ==========================================================
    @Nested
    class TotalStockByCategorySortedTests {

        @Test
        void testSumsTheStockOfEachCategory() {
            assertEquals(Map.of("Home", 5, "Office", 600),
                    CollectorsFunctionsChallenge.totalStockByCategorySorted(shop()));
        }

        @Test
        void testTheCategoriesAreInAlphabeticalOrder() {
            TreeMap<String, Integer> result = CollectorsFunctionsChallenge.totalStockByCategorySorted(shop());

            assertEquals(List.of("Home", "Office"), new ArrayList<>(result.keySet()));
            assertEquals("Home", result.firstKey());
        }

        @Test
        void testOrderDoesNotDependOnTheOrderOfTheProducts() {
            TreeMap<String, Integer> result = CollectorsFunctionsChallenge.totalStockByCategorySorted(List.of(
                    p("A", "Zoo", 1, 1), p("B", "Art", 1, 2), p("C", "Music", 1, 3), p("D", "Zoo", 1, 4)));

            assertEquals(List.of("Art", "Music", "Zoo"), new ArrayList<>(result.keySet()));
            assertEquals(5, result.get("Zoo"));
        }

        @Test
        void testUppercaseComesBeforeLowercase() {
            TreeMap<String, Integer> result = CollectorsFunctionsChallenge.totalStockByCategorySorted(List.of(
                    p("A", "apple", 1, 1), p("B", "Zebra", 1, 1)));

            assertEquals(List.of("Zebra", "apple"), new ArrayList<>(result.keySet()));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectorsFunctionsChallenge.totalStockByCategorySorted(List.of()).size());
        }
    }

    // ==========================================================
    // CHALLENGE 14: averagePriceByCategory
    // ==========================================================
    @Nested
    class AveragePriceByCategoryTests {

        @Test
        void testAveragesThePricesOfEachCategory() {
            assertEquals(Map.of("Office", 1.5, "Home", 150.0),
                    CollectorsFunctionsChallenge.averagePriceByCategory(shop()));
        }

        @Test
        void testOneProductPerCategory() {
            assertEquals(Map.of("X", 4.0, "Y", 9.0), CollectorsFunctionsChallenge.averagePriceByCategory(List.of(
                    p("A", "X", 4, 1), p("B", "Y", 9, 1))));
        }

        @Test
        void testTheAverageIsNotRoundedDown() {
            assertEquals(Map.of("X", 2.5), CollectorsFunctionsChallenge.averagePriceByCategory(List.of(
                    p("A", "X", 2, 1), p("B", "X", 3, 1))));
            assertEquals(Map.of("X", 0.5), CollectorsFunctionsChallenge.averagePriceByCategory(List.of(
                    p("A", "X", 0, 1), p("B", "X", 1, 1))));
        }

        @Test
        void testTheCategoriesAreMixedInTheList() {
            assertEquals(Map.of("X", 20.0, "Y", 5.0), CollectorsFunctionsChallenge.averagePriceByCategory(List.of(
                    p("A", "X", 10, 1), p("B", "Y", 5, 1), p("C", "X", 30, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.averagePriceByCategory(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 15: countByPriceLimit
    // ==========================================================
    @Nested
    class CountByPriceLimitTests {

        @Test
        void testSplitsByTheLimit() {
            assertEquals(Map.of(false, 1L, true, 2L), CollectorsFunctionsChallenge.countByPriceLimit(shop(), 2));
        }

        @Test
        void testEveryProductIsBelowTheLimit() {
            Map<Boolean, Long> result = CollectorsFunctionsChallenge.countByPriceLimit(shop(), 1000);

            assertEquals(Map.of(false, 3L, true, 0L), result);
            assertTrue(result.containsKey(true), "the key true must be there");
        }

        @Test
        void testEveryProductIsAboveTheLimit() {
            Map<Boolean, Long> result = CollectorsFunctionsChallenge.countByPriceLimit(shop(), 0);

            assertEquals(Map.of(false, 0L, true, 3L), result);
            assertTrue(result.containsKey(false), "the key false must be there");
        }

        @Test
        void testAPriceEqualToTheLimitCountsAsTrue() {
            assertEquals(Map.of(false, 2L, true, 1L), CollectorsFunctionsChallenge.countByPriceLimit(shop(), 150));
        }

        @Test
        void testEmptyListHasBothKeys() {
            assertEquals(Map.of(false, 0L, true, 0L), CollectorsFunctionsChallenge.countByPriceLimit(List.of(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 16: priceStatistics
    // ==========================================================
    @Nested
    class PriceStatisticsTests {

        @Test
        void testCountAndSum() {
            IntSummaryStatistics stats = CollectorsFunctionsChallenge.priceStatistics(shop());

            assertEquals(3L, stats.getCount());
            assertEquals(153L, stats.getSum());
        }

        @Test
        void testMinAndMax() {
            IntSummaryStatistics stats = CollectorsFunctionsChallenge.priceStatistics(shop());

            assertEquals(1, stats.getMin());
            assertEquals(150, stats.getMax());
        }

        @Test
        void testAverage() {
            assertEquals(51.0, CollectorsFunctionsChallenge.priceStatistics(shop()).getAverage(), 0.0001);
        }

        @Test
        void testOneProduct() {
            IntSummaryStatistics stats = CollectorsFunctionsChallenge.priceStatistics(List.of(p("A", "X", 7, 1)));

            assertEquals(1L, stats.getCount());
            assertEquals(7, stats.getMin());
            assertEquals(7, stats.getMax());
            assertEquals(7.0, stats.getAverage(), 0.0001);
        }

        @Test
        void testEmptyList() {
            IntSummaryStatistics stats = CollectorsFunctionsChallenge.priceStatistics(List.of());

            assertEquals(0L, stats.getCount());
            assertEquals(0L, stats.getSum());
        }
    }

    // ==========================================================
    // CHALLENGE 17: inventoryValue
    // ==========================================================
    @Nested
    class InventoryValueTests {

        @Test
        void testAddsPriceTimesStock() {
            assertEquals(1450, CollectorsFunctionsChallenge.inventoryValue(shop()));
        }

        @Test
        void testProductsWithoutStockAddNothing() {
            assertEquals(30, CollectorsFunctionsChallenge.inventoryValue(List.of(
                    p("A", "X", 100, 0), p("B", "X", 10, 3))));
        }

        @Test
        void testOneProduct() {
            assertEquals(60, CollectorsFunctionsChallenge.inventoryValue(List.of(p("A", "X", 12, 5))));
        }

        @Test
        void testItIsNotThePriceSumTimesTheStockSum() {
            assertEquals(2 * 10 + 3 * 1, CollectorsFunctionsChallenge.inventoryValue(List.of(
                    p("A", "X", 2, 10), p("B", "X", 3, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, CollectorsFunctionsChallenge.inventoryValue(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 18: inStockNamesByCategory
    // ==========================================================
    @Nested
    class InStockNamesByCategoryTests {

        @Test
        void testOnlyNamesOfProductsWithStock() {
            assertEquals(Map.of("Office", List.of("Pen"), "Home", List.of("Lamp")),
                    CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                            p("Pen", "Office", 2, 100), p("Desk", "Home", 150, 0), p("Lamp", "Home", 30, 4))));
        }

        @Test
        void testACategoryWithoutStockStaysWithAnEmptyList() {
            Map<String, List<String>> result = CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                    p("Pen", "Office", 2, 100), p("Desk", "Home", 150, 0)));

            assertEquals(Map.of("Office", List.of("Pen"), "Home", List.of()), result);
            assertTrue(result.containsKey("Home"), "the category Home must be there");
        }

        @Test
        void testStockOfOneCountsAndStockOfZeroDoesNot() {
            assertEquals(Map.of("X", List.of("B")), CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                    p("A", "X", 1, 0), p("B", "X", 1, 1))));
        }

        @Test
        void testKeepsTheOrderOfTheList() {
            assertEquals(Map.of("X", List.of("Zed", "Amy", "Max")),
                    CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                            p("Zed", "X", 1, 5), p("Skip", "X", 1, 0), p("Amy", "X", 1, 5), p("Max", "X", 1, 5))));
        }

        @Test
        void testEveryProductHasStock() {
            assertEquals(Map.of("X", List.of("A", "B"), "Y", List.of("C")),
                    CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                            p("A", "X", 1, 1), p("B", "X", 1, 2), p("C", "Y", 1, 3))));
        }

        @Test
        void testNoProductHasStock() {
            assertEquals(Map.of("X", List.of(), "Y", List.of()),
                    CollectorsFunctionsChallenge.inStockNamesByCategory(List.of(
                            p("A", "X", 1, 0), p("B", "Y", 1, 0))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.inStockNamesByCategory(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 19: priciestNameByCategory
    // ==========================================================
    @Nested
    class PriciestNameByCategoryTests {

        @Test
        void testTheNameOfTheMostExpensiveProductOfEachCategory() {
            assertEquals(Map.of("Office", "Pen", "Home", "Desk"),
                    CollectorsFunctionsChallenge.priciestNameByCategory(shop()));
        }

        @Test
        void testTheFirstOneWinsATie() {
            assertEquals(Map.of("Office", "Pen"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("Pen", "Office", 2, 1), p("Cup", "Office", 2, 9))));
        }

        @Test
        void testTheFirstOneWinsATieOnlyAmongTheMostExpensive() {
            assertEquals(Map.of("X", "B"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "X", 9, 1), p("C", "X", 9, 1), p("D", "X", 5, 1))));
        }

        @Test
        void testTheMostExpensiveCanBeAnywhere() {
            assertEquals(Map.of("X", "A"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("A", "X", 9, 1), p("B", "X", 1, 1), p("C", "X", 2, 1))));
            assertEquals(Map.of("X", "C"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "X", 2, 1), p("C", "X", 9, 1))));
        }

        @Test
        void testACategoryWithOneProduct() {
            assertEquals(Map.of("X", "A", "Y", "B"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("A", "X", 1, 1), p("B", "Y", 2, 1))));
        }

        @Test
        void testOnlyThePriceMatters() {
            assertEquals(Map.of("X", "Cheap"), CollectorsFunctionsChallenge.priciestNameByCategory(List.of(
                    p("Zzz", "X", 1, 999), p("Cheap", "X", 5, 1))));
        }

        @Test
        void testEmptyList() {
            assertEquals(Map.of(), CollectorsFunctionsChallenge.priciestNameByCategory(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 20: priceRange
    // ==========================================================
    @Nested
    class PriceRangeTests {

        @Test
        void testLowestAndHighestPrice() {
            assertEquals("1-150", CollectorsFunctionsChallenge.priceRange(shop()));
        }

        @Test
        void testOneProduct() {
            assertEquals("2-2", CollectorsFunctionsChallenge.priceRange(List.of(p("Pen", "Office", 2, 100))));
        }

        @Test
        void testTheOrderOfTheListDoesNotMatter() {
            assertEquals("3-90", CollectorsFunctionsChallenge.priceRange(List.of(
                    p("A", "X", 90, 1), p("B", "X", 10, 1), p("C", "X", 3, 1))));
            assertEquals("3-90", CollectorsFunctionsChallenge.priceRange(List.of(
                    p("A", "X", 3, 1), p("B", "X", 10, 1), p("C", "X", 90, 1))));
        }

        @Test
        void testAllPricesEqual() {
            assertEquals("5-5", CollectorsFunctionsChallenge.priceRange(List.of(
                    p("A", "X", 5, 1), p("B", "X", 5, 1))));
        }

        @Test
        void testTheNumbersAreComparedAsNumbersNotAsText() {
            assertEquals("9-100", CollectorsFunctionsChallenge.priceRange(List.of(
                    p("A", "X", 100, 1), p("B", "X", 9, 1), p("C", "X", 20, 1))));
        }

        @Test
        void testEmptyListGivesNone() {
            assertEquals("none", CollectorsFunctionsChallenge.priceRange(List.of()));
        }
    }
}