package dev.perfectbogus.concurrency.inventory;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {

    @Test
    void testAddNoExistingProduct() {
        InventorySystem inventory = new InventorySystem();

        inventory.addStock("Apple", 10);
        assertEquals(1, inventory.size());
    }

    @Test
    void testUpdateExistingProduct() {
        InventorySystem inventory = new InventorySystem();
        inventory.addStock("Apple", 10);
        inventory.addStock("Apple", 10);
        assertEquals(1, inventory.size());
        assertEquals(20, inventory.getStock("Apple"));
    }

    @Test
    void testSellNonExistingProduct() {
        InventorySystem inventory = InventorySystemFactory();
        String nonExistingProduct = "Duck";
        assertFalse(inventory.sell(nonExistingProduct, 10));
    }

    @Test
    void testSellAvailableProduct() {
        InventorySystem inventory = InventorySystemFactory();
        String product = "Apple";
        boolean result = inventory.sell(product, 5);
        assertTrue(result);
        assertEquals(5, inventory.getStock(product));
        assertEquals(5, inventory.getTotalSales());
    }

    @Test
    void testSellMoreThanStock() {
        InventorySystem inventory = InventorySystemFactory();
        String product = "Apple";
        boolean result = inventory.sell(product, 20);
        assertFalse(result);
    }

    @Test
    void testDiscontinueWhenStock() {
        InventorySystem inventory = InventorySystemFactory();
        String product = "Apple";
        assertFalse(inventory.discontinue(product));
    }

    @Test
    void testDiscontinueNoExistingProduct() {
        InventorySystem inventory = InventorySystemFactory();
        String product = "Elephant";
        assertFalse(inventory.discontinue(product));
    }

    @Test
    void testDiscontinueNoStock() {
        InventorySystem inventory = InventorySystemFactory();
        String product = "Apple";
        final int sell_quantity = 10;
        inventory.sell(product, sell_quantity);
        assertFalse(inventory.discontinue(product));
    }

    @Test
    void testGetLowStockProducts() {
        InventorySystem inventory = InventorySystemFactory();
        inventory.addStock("Apple", 10);
        inventory.addStock("Banana", 5);
        inventory.addStock("Carrot", 1);
        List<String> products = inventory.getLowStockProducts(16);
        List<String> expectedList = List.of("Banana","Carrot");

        assertEquals(expectedList.size(), products.size());
        for (String expected : expectedList) {
            assertTrue(products.contains(expected));
        }
    }

    @Test
    void testGetStock() {
        InventorySystem inventory = InventorySystemFactory();
        int expected = 30;
        assertEquals(expected, inventory.getTotalStock());
    }

    @Test
    void testTotalSales() {
        InventorySystem inventory = InventorySystemFactory();
        int expected = 5;
        inventory.sell("Apple", 5);
        assertEquals(expected, inventory.getTotalSales());
    }

    @Test
    void testNoOverselling() throws InterruptedException {
        InventorySystem inventory = new InventorySystem();
        inventory.addStock("Apple", 50);
        AtomicInteger successCount = new AtomicInteger(0);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    if (inventory.sell("Apple", 1)) {
                        successCount.incrementAndGet();
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(50, successCount.get());
        assertEquals(50, inventory.getTotalSales());
        assertEquals(0, inventory.getStock("Apple"));
    }

    @Test
    void testConcurrentAddStock() throws InterruptedException {
        InventorySystem inventory = new InventorySystem();
        int threadCount = 10;
        int addPerThread = 100;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < addPerThread; j++) {
                    inventory.addStock("Apple", 1);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        assertEquals(1_000, inventory.getStock("Apple"));
    }


    private InventorySystem InventorySystemFactory() {
        InventorySystem inventory = new InventorySystem();
        inventory.addStock("Apple", 10);
        inventory.addStock("Banana", 10);
        inventory.addStock("Carrot", 10);
        return inventory;
    }

}