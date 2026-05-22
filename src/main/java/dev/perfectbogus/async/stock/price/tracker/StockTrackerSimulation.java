package dev.perfectbogus.async.stock.price.tracker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StockTrackerSimulation {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StockTracker tracker = new StockTracker();

        tracker.addAlert("AAPL", 155.0, AlertDirection.ABOVE,
                () -> System.out.println("APPLE ABOVE $155!"));
        tracker.addAlert("APPLE", 145.0, AlertDirection.BELOW,
                () -> System.out.println("APPLE BELOW $145! SELL!"));

        List<CompletableFuture<Void>> updates = List.of(
                tracker.updatePrice("APPLE", 150.0),
                tracker.updatePrice("GOOGL", 2800.0),
                tracker.updatePrice("MSFT",  380.0),
                tracker.updatePrice("AAPL",  157.0),  // triggers ABOVE alert!
                tracker.updatePrice("AAPL",  143.0)   // triggers BELOW alert!
        );

        CompletableFuture.allOf(updates.toArray(new CompletableFuture[0])).join();

        Map<String, Integer> portfolio = Map.of(
                "APPL", 10,
                "GOOGL", 5,
                "MSFT", 20
        );

        double totalValue = tracker.getPortfolioValue(portfolio).get();
        System.out.println("Portafolio value: $" + totalValue);

        tracker.getTopGainers(3).forEach(System.out::println);
    }
}
