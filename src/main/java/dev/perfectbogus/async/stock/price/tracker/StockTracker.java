package dev.perfectbogus.async.stock.price.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StockTracker {

    private final ConcurrentHashMap<String, Double> prices = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<Double>> priceHistory = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<PriceAlert>> alerts = new ConcurrentHashMap<>();

    public CompletableFuture<Void> updatePrice(String symbol, double price) {
        if (symbol == null) throw new IllegalArgumentException("symbol cannot be null");
        if (price < 0) throw new IllegalArgumentException("price cannot be negative");

        return CompletableFuture.runAsync(() -> {
            prices.put(symbol, price);
            priceHistory.computeIfAbsent(symbol,
                    k -> Collections.synchronizedList(new ArrayList<>())
            ).add(price);
            checkAlerts(symbol, price);
        }).exceptionally(ex -> {
            System.err.println("Failed to update " + symbol + ": " + ex.getMessage());
            return null;
        });
    }

    private void checkAlerts(String symbol, double newPrice) {
        List<PriceAlert> symbolAlerts = alerts.get(symbol);
        if (symbolAlerts != null) {
            symbolAlerts.stream()
                    .filter(alert -> alert.isTriggered(newPrice))
                    .forEach(PriceAlert::fire);
        }
    }

    public double getPrice(String symbol) {
        if (symbol == null) throw new IllegalArgumentException("symbol cannot be null");
        return prices.getOrDefault(symbol, 0.0);
    }

    public void addAlert(String symbol, double threshold, AlertDirection direction, Runnable callback) {
        if (symbol == null) throw new IllegalArgumentException("Symbol cannot be null");
        if (direction == null) throw new IllegalArgumentException("Direction cannot be null");
        if (callback == null) throw new IllegalArgumentException("Callback cannot be null");
        if (threshold < 0) throw new IllegalArgumentException("Threshold cannot be negative");

        alerts.computeIfAbsent(
                symbol, k -> Collections.synchronizedList(new ArrayList<>())
        ).add(new PriceAlert(threshold, direction, callback));
    }

    public CompletableFuture<Double> getPortfolioValue(Map<String, Integer> portfolio) {
        if (portfolio == null) throw new IllegalArgumentException("Portfolio cannot be null");
        if (portfolio.isEmpty()) return CompletableFuture.completedFuture(0.0);

        List<CompletableFuture<Double>> futures = portfolio.entrySet()
                .stream()
                .map(e -> CompletableFuture.supplyAsync(() ->
                        getPrice(e.getKey()) * e.getValue()))
                .toList();
        return CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        ).thenApply(v -> futures.stream()
                .mapToDouble(CompletableFuture::join)
                .sum()
        );
    }

    public List<Double> getPriceHistory(String symbol) {
        if (symbol == null) throw new IllegalArgumentException("symbol cannot be null");
        List<Double> history = priceHistory.get(symbol);
        return history == null ? Collections.emptyList() : List.copyOf(history);
    }

    public List<String> getTopGainers(int n) {
        if (n < 1) throw new IllegalArgumentException("n must be positive");
        return priceHistory.entrySet().stream()
                .filter(e -> e.getValue().size() >= 2)
                .map(e -> {
                    List<Double> history = e.getValue();
                    double first = history.getFirst();
                    double current = prices.get(e.getKey());
                    double gain = (current - first) / first * 100;
                    return Map.entry(e.getKey(), gain);
                })
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
