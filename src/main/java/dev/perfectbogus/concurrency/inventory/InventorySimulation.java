package dev.perfectbogus.concurrency.inventory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventorySimulation {

    private static final int N_THREADS = 4;
    private static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        InventorySystem inventory = new InventorySystem();

        inventory.addStock("Apple", 100);
        inventory.addStock("Banana", 50);
        inventory.addStock("Cherry", 30);
        inventory.addStock("Mango", 20);

        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

        // Thread 1 - Supplier: restock every product
        executor.submit(() -> {
            for(int i = 0; i < 50; i++) {
                final int applesQty = rand.nextInt(10);
                final int bananaQty = rand.nextInt(10);
                final int cherryQty = rand.nextInt(10);
                inventory.addStock("Apple", applesQty);
                inventory.addStock("Banana", bananaQty);
                inventory.addStock("Cherry", cherryQty);
                System.out.println("Resupply: Apple: " + applesQty + " Banana: " + bananaQty + " Cherries: " + cherryQty );
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });

        // Thread 2 - Customer A: buys apples and bananas
        executor.submit(() -> {
            for(int i = 0; i < 60; i++) {
                inventory.sell("Apple", 1);
                inventory.sell("Banana", 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });

        // Thread 3 - Customer B: buys cherries and manges
        executor.submit(() -> {
            for (int i = 0; i < 40; i++) {
                inventory.sell("Cherry", 1);
                inventory.sell("Mango", 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });

        // Thread 4 - Reporter: reads inventory continuously
        executor.submit(() -> {
            for (int i = 0; i <60; i++) {
                int total = inventory.getTotalStock();
                List<String> low = inventory.getLowStockProducts(10);
                System.out.println("Reporter " + i + ": ");
                System.out.println("  Total: " + total);
                System.out.println("  Low Stocks: " + low);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Final inventory: " + inventory.getSnapshot());
        System.out.println("Total sales: " + inventory.getTotalSales());
        System.out.println("Low stock items: " + inventory.getLowStockProducts(10));
        System.out.println("Total stock: " + inventory.getTotalStock());
    }
}
