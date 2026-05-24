package dev.perfectbogus.functional.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ECommerceAnalyticsTest {

    private List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = List.of(
                new Order("O001", "C001", "USA",     "Electronics", 1200.00, 2, 2022, OrderStatus.COMPLETED,  List.of("premium", "tech", "gift")),
                new Order("O002", "C001", "USA",     "Electronics",  350.00, 1, 2022, OrderStatus.COMPLETED,  List.of("tech", "budget")),
                new Order("O003", "C002", "USA",     "Clothing",     180.00, 3, 2022, OrderStatus.COMPLETED,  List.of("fashion", "sale")),
                new Order("O004", "C002", "USA",     "Clothing",     220.00, 2, 2023, OrderStatus.CANCELLED,  List.of("fashion", "premium")),
                new Order("O005", "C003", "Germany", "Electronics",  950.00, 1, 2022, OrderStatus.COMPLETED,  List.of("tech", "premium")),
                new Order("O006", "C003", "Germany", "Electronics",  430.00, 2, 2023, OrderStatus.COMPLETED,  List.of("tech", "budget")),
                new Order("O007", "C004", "Germany", "Books",         85.00, 4, 2022, OrderStatus.COMPLETED,  List.of("education", "classic")),
                new Order("O008", "C004", "Germany", "Books",        120.00, 3, 2023, OrderStatus.COMPLETED,  List.of("education", "new-release")),
                new Order("O009", "C005", "UK",      "Clothing",     310.00, 2, 2022, OrderStatus.COMPLETED,  List.of("fashion", "premium")),
                new Order("O010", "C005", "UK",      "Clothing",     275.00, 1, 2023, OrderStatus.REFUNDED,   List.of("fashion", "sale")),
                new Order("O011", "C006", "UK",      "Electronics",  680.00, 1, 2023, OrderStatus.COMPLETED,  List.of("tech", "gift")),
                new Order("O012", "C006", "UK",      "Books",         95.00, 2, 2022, OrderStatus.PENDING,    List.of("education")),
                new Order("O013", "C007", "France",  "Electronics", 1450.00, 3, 2023, OrderStatus.COMPLETED,  List.of("premium", "tech", "gift")),
                new Order("O014", "C007", "France",  "Clothing",     390.00, 2, 2022, OrderStatus.COMPLETED,  List.of("fashion", "premium")),
                new Order("O015", "C008", "France",  "Books",        210.00, 5, 2023, OrderStatus.COMPLETED,  List.of("education", "classic")),
                new Order("O016", "C008", "France",  "Books",         75.00, 1, 2022, OrderStatus.CANCELLED,  List.of("classic")),
                new Order("O017", "C009", "Japan",   "Electronics",  870.00, 2, 2022, OrderStatus.COMPLETED,  List.of("tech", "premium")),
                new Order("O018", "C009", "Japan",   "Electronics",  540.00, 1, 2023, OrderStatus.COMPLETED,  List.of("tech", "budget")),
                new Order("O019", "C010", "Japan",   "Clothing",     155.00, 3, 2023, OrderStatus.COMPLETED,  List.of("fashion", "sale")),
                new Order("O020", "C010", "Japan",   "Clothing",     195.00, 2, 2022, OrderStatus.COMPLETED,  List.of("fashion"))
        );
    }

    // -------------------------------------------------------------------------
    // Task 1 — Revenue summary statistics per category
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - revenueSummaryByCategory")
    class Task1 {

        @Test
        @DisplayName("Should return correct statistics for Electronics")
        void shouldReturnCorrectElectronicsStats() {
            var result = ECommerceAnalytics.revenueSummaryByCategory(orders);

            // Electronics: O001(1200)+O002(350)+O005(950)+O006(430)+O011(680)+O013(1450)+O017(870)+O018(540) = 8 orders
            var stats = result.get("Electronics");
            assertEquals(8, stats.getCount());
            assertEquals(6470.00, stats.getSum(),   0.01);
            assertEquals(350.00,  stats.getMin(),   0.01);
            assertEquals(1450.00, stats.getMax(),   0.01);
            assertEquals(808.75,  stats.getAverage(), 0.01);
        }

        @Test
        @DisplayName("Should return correct statistics for Books")
        void shouldReturnCorrectBooksStats() {
            var result = ECommerceAnalytics.revenueSummaryByCategory(orders);

            // Books: O007(85)+O008(120)+O012(95)+O015(210)+O016(75) = 5 orders
            var stats = result.get("Books");
            assertEquals(5,      stats.getCount());
            assertEquals(585.00, stats.getSum(),    0.01);
            assertEquals(75.00,  stats.getMin(),    0.01);
            assertEquals(210.00, stats.getMax(),    0.01);
            assertEquals(117.00, stats.getAverage(), 0.01);
        }

        @Test
        @DisplayName("Should contain all categories")
        void shouldContainAllCategories() {
            var result = ECommerceAnalytics.revenueSummaryByCategory(orders);
            assertTrue(result.containsKey("Electronics"));
            assertTrue(result.containsKey("Clothing"));
            assertTrue(result.containsKey("Books"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.revenueSummaryByCategory(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Order IDs joined per country
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - orderIdsJoinedByCountry")
    class Task2 {

        @Test
        @DisplayName("Should return sorted and joined order IDs per country")
        void shouldReturnJoinedIds() {
            var result = ECommerceAnalytics.orderIdsJoinedByCountry(orders);

            assertEquals("O001, O002, O003, O004", result.get("USA"));
            assertEquals("O005, O006, O007, O008", result.get("Germany"));
            assertEquals("O009, O010, O011, O012", result.get("UK"));
            assertEquals("O013, O014, O015, O016", result.get("France"));
            assertEquals("O017, O018, O019, O020", result.get("Japan"));
        }

        @Test
        @DisplayName("Should return String values not List")
        void shouldReturnStringValues() {
            var result = ECommerceAnalytics.orderIdsJoinedByCountry(orders);
            result.values().forEach(v -> assertInstanceOf(String.class, v));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.orderIdsJoinedByCountry(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Partition orders by completion
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - partitionByCompletion")
    class Task3 {

        @Test
        @DisplayName("Should correctly partition and compute stats for COMPLETED orders")
        void shouldComputeStatsForCompleted() {
            var result = ECommerceAnalytics.partitionByCompletion(orders);

            // COMPLETED: O001,O002,O003,O005,O006,O007,O008,O009,O011,O013,O014,O015,O017,O018,O019,O020 = 16
            var completed = result.get(true);
            assertEquals(16, completed.getCount());
        }

        @Test
        @DisplayName("Should correctly partition and compute stats for non-COMPLETED orders")
        void shouldComputeStatsForNonCompleted() {
            var result = ECommerceAnalytics.partitionByCompletion(orders);

            // Non-COMPLETED: O004(CANCELLED),O010(REFUNDED),O012(PENDING),O016(CANCELLED) = 4
            var nonCompleted = result.get(false);
            assertEquals(4, nonCompleted.getCount());
        }

        @Test
        @DisplayName("Should always return both true and false keys")
        void shouldAlwaysHaveBothKeys() {
            var result = ECommerceAnalytics.partitionByCompletion(orders);
            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.partitionByCompletion(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Top revenue country per category
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - topRevenueCountryByCategory")
    class Task4 {

        @Test
        @DisplayName("Should return the country with highest revenue per category")
        void shouldReturnTopRevenueCountry() {
            var result = ECommerceAnalytics.topRevenueCountryByCategory(orders);

            // Electronics by country:
            //   USA:     O001(1200)+O002(350) = 1550
            //   Germany: O005(950)+O006(430)  = 1380
            //   UK:      O011(680)            =  680
            //   France:  O013(1450)           = 1450
            //   Japan:   O017(870)+O018(540)  = 1410
            // Winner: USA (1550)
            assertEquals("USA", result.get("Electronics"));

            // Clothing by country:
            //   USA:    O003(180)+O004(220) = 400
            //   UK:     O009(310)+O010(275) = 585
            //   France: O014(390)           = 390
            //   Japan:  O019(155)+O020(195) = 350
            // Winner: UK (585)
            assertEquals("UK", result.get("Clothing"));

            // Books by country:
            //   Germany: O007(85)+O008(120) = 205
            //   UK:      O012(95)           =  95
            //   France:  O015(210)+O016(75) = 285
            // Winner: France (285)
            assertEquals("France", result.get("Books"));
        }

        @Test
        @DisplayName("Should return String not Optional")
        void shouldReturnString() {
            var result = ECommerceAnalytics.topRevenueCountryByCategory(orders);
            result.values().forEach(v -> assertInstanceOf(String.class, v));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.topRevenueCountryByCategory(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Assign customer tier based on total spend
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - assignCustomerTiers")
    class Task5 {

        private Map<String, List<Order>> ordersByCustomer;

        @BeforeEach
        void buildMap() {
            ordersByCustomer = orders.stream()
                    .collect(Collectors.groupingBy(Order::customerId));
        }

        @Test
        @DisplayName("Should assign correct tier per customer")
        void shouldAssignCorrectTiers() {
            var result = ECommerceAnalytics.assignCustomerTiers(ordersByCustomer);

            // C001: O001(1200)+O002(350) = 1550 → SILVER (>=1500? No, 1550 >= 1500 → GOLD? No < 3000 → GOLD)
            assertEquals(CustomerTier.GOLD,     result.get("C001")); // 1550 → GOLD

            // C002: O003(180)+O004(220) = 400 → BRONZE
            assertEquals(CustomerTier.BRONZE,   result.get("C002")); // 400 → BRONZE

            // C003: O005(950)+O006(430) = 1380 → SILVER
            assertEquals(CustomerTier.SILVER,   result.get("C003")); // 1380 → SILVER

            // C007: O013(1450)+O014(390) = 1840 → GOLD
            assertEquals(CustomerTier.GOLD,     result.get("C007")); // 1840 → GOLD

            // C009: O017(870)+O018(540) = 1410 → SILVER
            assertEquals(CustomerTier.SILVER,   result.get("C009")); // 1410 → SILVER
        }

        @Test
        @DisplayName("Should contain an entry for every customer")
        void shouldContainAllCustomers() {
            var result = ECommerceAnalytics.assignCustomerTiers(ordersByCustomer);
            assertEquals(10, result.size());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.assignCustomerTiers(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Unique tags per category × status
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - tagsByCategoryAndStatus")
    class Task6 {

        @Test
        @DisplayName("Should return correct tags for Electronics × COMPLETED")
        void shouldReturnCorrectTagsForElectronicsCompleted() {
            var result = ECommerceAnalytics.tagsByCategoryAndStatus(orders);

            // Electronics COMPLETED: O001(premium,tech,gift) O002(tech,budget) O005(tech,premium)
            //   O006(tech,budget) O011(tech,gift) O013(premium,tech,gift) O017(tech,premium) O018(tech,budget)
            var tags = result.get("Electronics").get(OrderStatus.COMPLETED);
            assertEquals(Set.of("premium", "tech", "gift", "budget"), tags);
        }

        @Test
        @DisplayName("Should return unmodifiable sets")
        void shouldReturnUnmodifiableSets() {
            var result = ECommerceAnalytics.tagsByCategoryAndStatus(orders);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("Electronics").get(OrderStatus.COMPLETED).add("new-tag"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.tagsByCategoryAndStatus(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Revenue and order count per year using teeing
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - revenueAndCountByYear")
    class Task7 {

        @Test
        @DisplayName("Should return correctly formatted summary per year")
        void shouldReturnFormattedSummaryPerYear() {
            var result = ECommerceAnalytics.revenueAndCountByYear(orders);

            // 2022: O001+O002+O003+O005+O007+O009+O012+O014+O016+O017+O020
            //     = 1200+350+180+950+85+310+95+390+75+870+195 = 4700.00, 11 orders
            assertEquals("total=4700.00, orders=11", result.get(2022));

            // 2023: O004+O006+O008+O010+O011+O013+O015+O018+O019
            //     = 220+430+120+275+680+1450+210+540+155 = 4080.00, 9 orders
            assertEquals("total=4080.00, orders=9", result.get(2023));
        }

        @Test
        @DisplayName("Should contain both years")
        void shouldContainBothYears() {
            var result = ECommerceAnalytics.revenueAndCountByYear(orders);
            assertTrue(result.containsKey(2022));
            assertTrue(result.containsKey(2023));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.revenueAndCountByYear(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Category revenue share as formatted percentage
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - categoryRevenueShare")
    class Task8 {

        @Test
        @DisplayName("Should return correct percentage share per category")
        void shouldReturnCorrectPercentageShare() {
            var result = ECommerceAnalytics.categoryRevenueShare(orders);

            // COMPLETED orders only:
            // Electronics COMPLETED: O001+O002+O005+O006+O011+O013+O017+O018
            //   = 1200+350+950+430+680+1450+870+540 = 6470
            // Clothing COMPLETED: O003+O009+O014+O019+O020
            //   = 180+310+390+155+195 = 1230
            // Books COMPLETED: O007+O008+O015
            //   = 85+120+210 = 415
            // Grand total = 6470+1230+415 = 8115

            // Electronics: 6470/8115 * 100 = 79.73%
            assertEquals("79.73%", result.get("Electronics"));

            // Clothing: 1230/8115 * 100 = 15.16%
            assertEquals("15.16%", result.get("Clothing"));

            // Books: 415/8115 * 100 = 5.11%
            assertEquals("5.11%", result.get("Books"));
        }

        @Test
        @DisplayName("Should only include categories with COMPLETED orders")
        void shouldOnlyIncludeCompletedCategories() {
            var result = ECommerceAnalytics.categoryRevenueShare(orders);
            // All 3 categories have COMPLETED orders
            assertEquals(3, result.size());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.categoryRevenueShare(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 9 — Premium countries (all COMPLETED + revenue > threshold)
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 9 - premiumCountries")
    class Task9 {

        @Test
        @DisplayName("Should return countries where all orders are COMPLETED and revenue exceeds threshold")
        void shouldReturnPremiumCountries() {
            // Germany: O005(COMPLETED)+O006(COMPLETED)+O007(COMPLETED)+O008(COMPLETED) → all COMPLETED
            //   total = 950+430+85+120 = 1585 > 500 → qualifies
            // Japan: O017(COMPLETED)+O018(COMPLETED)+O019(COMPLETED)+O020(COMPLETED) → all COMPLETED
            //   total = 870+540+155+195 = 1760 > 500 → qualifies
            // USA, UK, France all have non-COMPLETED orders → excluded
            List<String> result = ECommerceAnalytics.premiumCountries(orders, 500.0);
            assertEquals(List.of("Germany", "Japan"), result);
        }

        @Test
        @DisplayName("Should exclude countries that have revenue below threshold")
        void shouldExcludeBelowThreshold() {
            // Raise threshold to 2000 → Germany (1585) excluded, Japan (1760) excluded
            List<String> result = ECommerceAnalytics.premiumCountries(orders, 2000.0);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return results sorted alphabetically")
        void shouldBeSortedAlphabetically() {
            List<String> result = ECommerceAnalytics.premiumCountries(orders, 500.0);
            assertEquals(result.stream().sorted().toList(), result);
        }

        @Test
        @DisplayName("Should return unmodifiable list")
        void shouldReturnUnmodifiableList() {
            List<String> result = ECommerceAnalytics.premiumCountries(orders, 500.0);
            assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.premiumCountries(null, 100.0));
        }
    }

    // -------------------------------------------------------------------------
    // Task 10 — Invert the category → tag map
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 10 - invertCategoryTagMap")
    class Task10 {

        @Test
        @DisplayName("Should correctly invert category → tags to tag → categories")
        void shouldInvertCorrectly() {
            Map<String, List<String>> input = Map.of(
                    "Electronics", List.of("tech", "premium", "gift"),
                    "Clothing",    List.of("fashion", "premium"),
                    "Books",       List.of("education", "gift")
            );

            var result = ECommerceAnalytics.invertCategoryTagMap(input);

            assertEquals(Set.of("Electronics"),              result.get("tech"));
            assertEquals(Set.of("Electronics", "Clothing"),  result.get("premium"));
            assertEquals(Set.of("Electronics", "Books"),     result.get("gift"));
            assertEquals(Set.of("Clothing"),                 result.get("fashion"));
            assertEquals(Set.of("Books"),                    result.get("education"));
        }

        @Test
        @DisplayName("Should return unmodifiable sets")
        void shouldReturnUnmodifiableSets() {
            var input = Map.of("Electronics", List.of("tech"));
            var result = ECommerceAnalytics.invertCategoryTagMap(input);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("tech").add("new-category"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.invertCategoryTagMap(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 11 — Full order report per customer using teeing
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 11 - customerReport")
    class Task11 {

        @Test
        @DisplayName("Should return correctly formatted report per customer")
        void shouldReturnCorrectReport() {
            var result = ECommerceAnalytics.customerReport(orders);

            // C001: O001+O002 → 2 orders, 1200+350=1550 spend → GOLD
            assertEquals("orders=2, spent=$1550.00, tier=GOLD", result.get("C001"));

            // C002: O003+O004 → 2 orders, 180+220=400 spend → BRONZE
            assertEquals("orders=2, spent=$400.00, tier=BRONZE", result.get("C002"));

            // C003: O005+O006 → 2 orders, 950+430=1380 spend → SILVER
            assertEquals("orders=2, spent=$1380.00, tier=SILVER", result.get("C003"));

            // C007: O013+O014 → 2 orders, 1450+390=1840 spend → GOLD
            assertEquals("orders=2, spent=$1840.00, tier=GOLD", result.get("C007"));
        }

        @Test
        @DisplayName("Should contain an entry for every customer")
        void shouldContainAllCustomers() {
            var result = ECommerceAnalytics.customerReport(orders);
            assertEquals(10, result.size());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.customerReport(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 12 — Best customer per country per year
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 12 - bestCustomerByCountryAndYear")
    class Task12 {

        @Test
        @DisplayName("Should return the highest spending customer per country and year")
        void shouldReturnBestCustomerPerCountryAndYear() {
            var result = ECommerceAnalytics.bestCustomerByCountryAndYear(orders);

            // USA COMPLETED 2022: C001(O001+O002=1550), C002(O003=180) → C001
            assertEquals("C001", result.get("USA").get(2022));

            // Germany COMPLETED 2022: C003(O005=950), C004(O007=85) → C003
            assertEquals("C003", result.get("Germany").get(2022));

            // Germany COMPLETED 2023: C003(O006=430), C004(O008=120) → C003
            assertEquals("C003", result.get("Germany").get(2023));

            // France COMPLETED 2023: C007(O013=1450), C008(O015=210) → C007
            assertEquals("C007", result.get("France").get(2023));

            // Japan COMPLETED 2022: C009(O017=870), C010(O020=195) → C009
            assertEquals("C009", result.get("Japan").get(2022));

            // Japan COMPLETED 2023: C009(O018=540), C010(O019=155) → C009
            assertEquals("C009", result.get("Japan").get(2023));
        }

        @Test
        @DisplayName("Should only consider COMPLETED orders")
        void shouldOnlyConsiderCompletedOrders() {
            var result = ECommerceAnalytics.bestCustomerByCountryAndYear(orders);
            // USA 2023: only O004 which is CANCELLED → no entry for USA 2023
            assertFalse(result.getOrDefault("USA", Map.of()).containsKey(2023));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> ECommerceAnalytics.bestCustomerByCountryAndYear(null));
        }
    }
}