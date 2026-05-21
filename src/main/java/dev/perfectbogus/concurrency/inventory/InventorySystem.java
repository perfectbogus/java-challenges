package dev.perfectbogus.concurrency.inventory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InventorySystem {

    private final ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

    private final AtomicInteger totalSales = new AtomicInteger(0);
    private static final int ZERO_INVENTORY = 0;

    // 1. Add Product to inventory
    //      if product exists -> add to existing stock
    //      if product is new -> initialize with given quantity
    public void addStock(String product, int quantity) {
        // TODO - use merge() with Integer::sum
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        if (quantity < 1) throw new IllegalArgumentException("quantity must be bigger than Zero");

        inventory.merge(product, quantity, Integer::sum);
    }

    // 2. Sell a product - reduce stock by quantity
    //      Returns true -> sale successful
    //      Returns false -> insufficient stock
    //      Must be atomic - no overselling!
    public boolean sell(String product, int quantity) {
        // TODO - use compute() to atomically check and deduct
        if (product == null) throw new IllegalArgumentException("Product cannot be null");

        AtomicBoolean success = new AtomicBoolean(false);
        inventory.computeIfPresent(product, (key, current) -> {
            if (current >= quantity) {
                success.set(true);
                totalSales.addAndGet(quantity);
                int remaining = current - quantity;
                return remaining == 0 ? null : remaining;
            }
            return current;
        });
        return success.get();
    }

    // 4 Find all product below stock threshold
    public List<String> getLowStockProducts(int threshold) {
        if (threshold < 0) throw new IllegalArgumentException("Threshold cannot be negative");

        return inventory.entrySet().stream()
                .filter(e -> e.getValue() < threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // 5 Get total number of items across all products
    public int getTotalStock() {
        return inventory.reduceValues(1, Integer::sum);
    }

    // 6: Get Current stock for one product
    public int getStock(String product) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null");

        return inventory.getOrDefault(product, 0);
    }

    // 7: Get full inventory snapshot
    public Map<String, Integer> getSnapshot() {
        return Map.copyOf(inventory);
    }

    // 8: Get total successful sales count
    public int getTotalSales() {
        return totalSales.get();
    }

    public boolean discontinue(String product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");

        return inventory.remove(product, ZERO_INVENTORY);
    }

    public int size() {
        return inventory.size();
    }

}
