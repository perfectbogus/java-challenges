package dev.perfectbogus.functional.order.analytics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderAnalyticsTest {

    private List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = List.of(
                new Order("O001", "Alice",    "Electronics", 1200.00, 2, OrderStatus.DELIVERED),
                new Order("O002", "Bob",      "Clothing",     150.00, 3, OrderStatus.DELIVERED),
                new Order("O003", "Alice",    "Electronics",  800.00, 1, OrderStatus.PENDING),
                new Order("O004", "Charlie",  "Food",          45.00, 5, OrderStatus.DELIVERED),
                new Order("O005", "Bob",      "Electronics",  950.00, 1, OrderStatus.CANCELLED),
                new Order("O006", "Diana",    "Clothing",     200.00, 2, OrderStatus.DELIVERED),
                new Order("O007", "Charlie",  "Food",          30.00, 3, OrderStatus.PENDING),
                new Order("O008", "Alice",    "Clothing",     175.00, 1, OrderStatus.DELIVERED),
                new Order("O009", "Diana",    "Electronics", 1500.00, 1, OrderStatus.DELIVERED),
                new Order("O010", "Eve",      "Food",          60.00, 4, OrderStatus.CANCELLED)
        );
    }

    @Test
    void testTotalRevenueByCategory() {
        Map<String, Double> result =
                OrderAnalytics.totalRevenueByCategory(orders);

        assertEquals(4450.00, result.get("Electronics"), 0.01);
        assertEquals(525.00,  result.get("Clothing"),    0.01);
        assertEquals(135.00,  result.get("Food"),        0.01);
    }

    @Test
    void testOrderCountByStatus() {
        Map<OrderStatus, Long> result =
                OrderAnalytics.orderCountByStatus(orders);

        assertEquals(6L, result.get(OrderStatus.DELIVERED));
        assertEquals(2L, result.get(OrderStatus.PENDING));
        assertEquals(2L, result.get(OrderStatus.CANCELLED));
    }

    @Test
    void testTopCustomersByRevenue() {
        List<String> result =
                OrderAnalytics.topCustomersByRevenue(orders, 3);

        assertEquals("Alice",   result.get(0));
        assertEquals("Diana",   result.get(1));
        assertEquals("Bob",     result.get(2));
    }

    @Test
    void testAverageOrderValueByCategory() {
        Map<String, Double> result =
                OrderAnalytics.averageOrderValueByCategory(orders);

        assertEquals(1112.50, result.get("Electronics"), 0.01);
        assertEquals(175.00,  result.get("Clothing"),    0.01);
        assertEquals(45.00,   result.get("Food"),        0.01);
    }

    @Test
    void testDeliveredOrdersByCustomer() {
        Map<String, List<Order>> result =
                OrderAnalytics.deliveredOrdersByCustomer(orders);

        assertEquals(2, result.get("Alice").size());
        assertEquals(1, result.get("Bob").size());
        assertEquals(1, result.get("Charlie").size());
        assertEquals(2, result.get("Diana").size());
        assertFalse(result.containsKey("Eve"));
    }

    @Test
    void testMostPopularCategory() {
        String result = OrderAnalytics.mostPopularCategory(orders);
        assertEquals("Electronics", result);
    }

    @Test
    void testOrdersWithHighValue() {
        List<String> result =
                OrderAnalytics.orderIdsWithHighValue(orders, 500.00);

        assertEquals(4, result.size());
        assertTrue(result.contains("O001"));
        assertTrue(result.contains("O003"));
        assertTrue(result.contains("O005"));
        assertTrue(result.contains("O009"));
    }

    @Test
    void testTotalItemsSoldByCategory() {
        Map<String, Integer> result =
                OrderAnalytics.totalItemsSoldByCategory(orders);

        assertEquals(5,  result.get("Electronics"));
        assertEquals(6,  result.get("Clothing"));
        assertEquals(12, result.get("Food"));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> OrderAnalytics.totalRevenueByCategory(null));
        assertThrows(IllegalArgumentException.class,
                () -> OrderAnalytics.orderCountByStatus(null));
        assertThrows(IllegalArgumentException.class,
                () -> OrderAnalytics.topCustomersByRevenue(null, 3));
    }

    @Test
    void testEmptyList() {
        assertTrue(OrderAnalytics.totalRevenueByCategory(List.of()).isEmpty());
        assertTrue(OrderAnalytics.orderCountByStatus(List.of()).isEmpty());
        assertEquals("NONE", OrderAnalytics.mostPopularCategory(List.of()));
    }
}