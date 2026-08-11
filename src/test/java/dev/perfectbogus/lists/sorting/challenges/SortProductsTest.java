package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortProductsTest {

    private List<SortProducts.Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>(List.of(
                new SortProducts.Product("Apple",      1.50),
                new SortProducts.Product("Banana",     0.75),
                new SortProducts.Product("Cherry",     1.50),
                new SortProducts.Product("Date",       0.75),
                new SortProducts.Product("Elderberry", 3.00)
        ));
    }

    @Test
    void testPriceOrder() {
        List<SortProducts.Product> result =
                SortProducts.sort(products);

        assertEquals(0.75, result.get(0).price(), 0.001);
        assertEquals(0.75, result.get(1).price(), 0.001);
        assertEquals(1.50, result.get(2).price(), 0.001);
        assertEquals(1.50, result.get(3).price(), 0.001);
        assertEquals(3.00, result.get(4).price(), 0.001);
    }

    @Test
    void testNameTieBreaker() {
        List<SortProducts.Product> result =
                SortProducts.sort(products);

        assertEquals("Banana", result.get(0).name());
        assertEquals("Date",   result.get(1).name());
        assertEquals("Apple",  result.get(2).name());
        assertEquals("Cherry", result.get(3).name());
    }

    @Test
    void testSingleProduct() {
        List<SortProducts.Product> single =
                new ArrayList<>(List.of(
                        new SortProducts.Product("Apple", 1.50)));
        assertEquals(1, SortProducts.sort(single).size());
    }

    @Test
    void testEmptyList() {
        assertTrue(SortProducts.sort(
                new ArrayList<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortProducts.sort(null));
    }
}