package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortOrdersTest {

    private List<SortOrders.Order> orders;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>(List.of(
                new SortOrders.Order("O001",
                        SortOrders.Status.DELIVERED, 150.0),
                new SortOrders.Order("O002",
                        SortOrders.Status.PENDING,   200.0),
                new SortOrders.Order("O003",
                        SortOrders.Status.DELIVERED, 300.0),
                new SortOrders.Order("O004",
                        SortOrders.Status.CANCELLED, 100.0),
                new SortOrders.Order("O005",
                        SortOrders.Status.PENDING,   200.0)
        ));
    }

    @Test
    void testStatusOrder() {
        List<SortOrders.Order> result =
                SortOrders.sort(orders);

        assertEquals(SortOrders.Status.DELIVERED,
                result.get(0).status());
        assertEquals(SortOrders.Status.DELIVERED,
                result.get(1).status());
        assertEquals(SortOrders.Status.PENDING,
                result.get(2).status());
        assertEquals(SortOrders.Status.PENDING,
                result.get(3).status());
        assertEquals(SortOrders.Status.CANCELLED,
                result.get(4).status());
    }

    @Test
    void testValueWithinStatus() {
        List<SortOrders.Order> result =
                SortOrders.sort(orders);

        // DELIVERED — highest value first
        assertEquals("O003", result.get(0).orderId());
        assertEquals("O001", result.get(1).orderId());

        // PENDING — same value → orderId ascending
        assertEquals("O002", result.get(2).orderId());
        assertEquals("O005", result.get(3).orderId());

        // CANCELLED — only one
        assertEquals("O004", result.get(4).orderId());
    }

    @Test
    void testSingleOrder() {
        List<SortOrders.Order> single =
                new ArrayList<>(List.of(
                        new SortOrders.Order("O001",
                                SortOrders.Status.DELIVERED, 100.0)));
        assertEquals(1, SortOrders.sort(single).size());
    }

    @Test
    void testEmptyList() {
        assertTrue(SortOrders.sort(
                new ArrayList<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortOrders.sort(null));
    }

    @Test
    void testAllSameStatus() {
        List<SortOrders.Order> allDelivered =
                new ArrayList<>(List.of(
                        new SortOrders.Order("O003",
                                SortOrders.Status.DELIVERED, 100.0),
                        new SortOrders.Order("O001",
                                SortOrders.Status.DELIVERED, 300.0),
                        new SortOrders.Order("O002",
                                SortOrders.Status.DELIVERED, 200.0)
                ));
        List<SortOrders.Order> result =
                SortOrders.sort(allDelivered);

        assertEquals("O001", result.get(0).orderId()); // 300
        assertEquals("O002", result.get(1).orderId()); // 200
        assertEquals("O003", result.get(2).orderId()); // 100
    }
}