package dev.perfectbogus.functional.order.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalytics {

    // 1. Total revenue per category (value)
    public static Map<String, Double> totalRevenueByCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.summingDouble(Order::value)
                ));
    }

    // 2. Count orders by status
    public static Map<OrderStatus, Long> orderCountByStatus(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::status,
                        Collectors.counting()
                ));
    }

    // 3. Top N customers by total revenue (value × quantity)
    public static List<String> topCustomersByRevenue(List<Order> orders, int n) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.summingDouble(Order::value)
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

    // 4. Average order value per category
    public static Map<String, Double> averageOrderValueByCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.averagingDouble(Order::value)
                ));
    }

    // 5. Delivered orders grouped by customer
    public static Map<String, List<Order>> deliveredOrdersByCustomer(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .filter(e -> e.status() == OrderStatus.DELIVERED)
                .collect(Collectors.groupingBy(
                        Order::customer
                ));
    }

    // 6. Most popular category by number of orders
    public static String mostPopularCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.counting()
                )).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("NONE");
    }

    // 7. Order IDs where value > threshold
    public static List<String> orderIdsWithHighValue(List<Order> orders, double threshold) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .filter(e -> e.value() > threshold)
                .map(Order::orderId)
                .toList();
    }

    // 8. Total items sold per category (sum of quantities)
    public static Map<String, Integer> totalItemsSoldByCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.summingInt(Order::quantity)
                ));
    }
}
