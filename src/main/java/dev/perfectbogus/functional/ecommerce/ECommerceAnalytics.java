package dev.perfectbogus.functional.ecommerce;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ECommerceAnalytics {

    // 1. Revenue summary statistics per category
    // Returns Map<category, DoubleSummaryStatistics> — count, sum, min, max, avg of amount
    public static Map<String, DoubleSummaryStatistics> revenueSummaryByCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.summarizingDouble(Order::amount)
                ));
    }

    // 2. Order IDs joined per country
    // Returns Map<country, "ID1, ID2, ID3"> — IDs sorted alphabetically, joined with ", "
    public static Map<String, String> orderIdsJoinedByCountry(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::country,
                        Collectors.mapping(
                                Order::orderId,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted()
                                                .collect(Collectors.joining(", "))
                                )
                        )
                ));
    }

    // 3. Partition orders by completion
    // true  → COMPLETED orders DoubleSummaryStatistics on amount
    // false → all other statuses DoubleSummaryStatistics on amount
    private static final Predicate<Order> COMPLETED = (o -> o.status() == OrderStatus.COMPLETED);
    public static Map<Boolean, DoubleSummaryStatistics> partitionByCompletion(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.partitioningBy(
                        COMPLETED,
                        Collectors.summarizingDouble(Order::amount)
                ));
    }

    // 4. Top revenue country per category
    // Returns Map<category, countryName> — country with highest total revenue per category
    // Returns "N/A" if no orders found
    public static Map<String, String> topRevenueCountryByCategory(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(Order::country, Collectors.summingDouble(Order::amount)),
                                map -> map.entrySet().stream()
                                        .max(Map.Entry.comparingByValue())
                                        .map(Map.Entry::getKey)
                                        .orElse("N/A")
                        )
                ));
    }

    // 5. Assign customer tier based on total spend
    // Input: Map<customerId, List<Order>>
    // Returns Map<customerId, CustomerTier>
    // Use reduce to sum spend, then assign tier:
    //   < 500   → BRONZE
    //   < 1500  → SILVER
    //   < 3000  → GOLD
    //   >= 3000 → PLATINUM
    public static Map<String, CustomerTier> assignCustomerTiers(Map<String, List<Order>> ordersByCustomer) {
        if (ordersByCustomer == null) throw new IllegalArgumentException("Map cannot be null");
        return ordersByCustomer.entrySet().stream()
                .map(entry -> {
                    double totalSpend = entry.getValue().stream()
                            .reduce(0d, (acc, o) -> acc + o.amount(), Double::sum);
                    return Map.entry(entry.getKey(), totalSpend);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> tierFromSpend(e.getValue())
                ));
    }

    // 6. Unique tags per category × status combination
    // Returns Map<category, Map<OrderStatus, Set<String>>> — unmodifiable sets
    public static Map<String, Map<OrderStatus, Set<String>>> tagsByCategoryAndStatus(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.groupingBy(
                                Order::status,
                                Collectors.collectingAndThen(
                                        Collectors.flatMapping(
                                                o -> o.tags().stream(),
                                                Collectors.toSet()
                                        ),
                                        Collections::unmodifiableSet
                                )
                        )
                ));
    }

    // 7. Revenue and order count per year using teeing
    // Returns Map<year, "total=$X.XX, orders=Y"> — single pass per year
    public static Map<Integer, String> revenueAndCountByYear(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::year,
                        Collectors.teeing(
                                Collectors.summingDouble(Order::amount),
                                Collectors.counting(),
                                (sum, count) -> String.format("total=%.2f, orders=%d", sum, count)
                        )
                ));
    }

    // 8. Category revenue share as formatted percentage
    // Returns Map<category, "XX.XX%"> — share of total COMPLETED revenue
    // Only consider COMPLETED orders
    public static Map<String, String> categoryRevenueShare(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        List<Order> ordersCompleted = orders.stream().filter(COMPLETED).toList();
        double grandTotal = ordersCompleted.stream().mapToDouble(Order::amount).sum();
        return ordersCompleted.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(Order::amount),
                                categoryTotal -> String.format("%.2f%%", (categoryTotal / grandTotal) * 100 )
                        )
                ));

    }

    // 9. Countries where every order is COMPLETED and total revenue exceeds threshold
    // Returns unmodifiable List<String> sorted alphabetically
    public static List<String> premiumCountries(List<Order> orders, double threshold) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::country,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    boolean allCompleted = list.stream().allMatch(COMPLETED);
                                    double totalRevenue = list.stream().mapToDouble(Order::amount).sum();
                                    return allCompleted && totalRevenue > threshold;
                                }
                        )
                )).entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
    }

    // 10. Invert the category → tag map
    // Input:  Map<category, List<String> tags>
    // Returns Map<tag, Set<String> categories> — unmodifiable sets
    public static Map<String, Set<String>> invertCategoryTagMap(Map<String, List<String>> categoryTags) {
        if (categoryTags == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO: stream entrySet, flatMap each entry into (tag, category) pairs,
        //       then groupingBy tag + mapping category + collectingAndThen(toSet, unmodifiableSet)
        return categoryTags.entrySet().stream()
                .flatMap(
                        e ->
                                e.getValue().stream().map(tag -> Map.entry(tag, e.getKey()))
                ).collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.collectingAndThen(
                                        Collectors.toSet(),
                                        Collections::unmodifiableSet
                                )
                        )
                ));
    }

    // 11. Full order report per customer using teeing
    // Returns Map<customerId, "orders=N, spent=$X.XX, tier=TIER">
    // Use teeing to compute count and total spend in a single pass, derive tier from spend
    public static Map<String, String> customerReport(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        // TODO: groupingBy customerId + teeing(counting, summingDouble, (n, s) -> format string with tier)
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customerId,
                        Collectors.teeing(
                                Collectors.counting(),
                                Collectors.summingDouble(Order::amount),
                                (c , s) ->
                                        String.format("orders=%d, spent=$%.2f, tier=%s", c, s, tierFromSpend(s))
                        )
                ));
    }

    // 12. Best customer per country per year
    // Returns Map<country, Map<year, customerId>>
    // Only COMPLETED orders — customer with highest total spend per country+year combination
    public static Map<String, Map<Integer, String>> bestCustomerByCountryAndYear(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("Orders cannot be null");
        // TODO: filter COMPLETED, groupingBy country, then groupingBy year,
        //       then groupingBy customer + summingDouble, then find max entry per group
        return orders.stream().filter(COMPLETED)
                .collect(Collectors.groupingBy(
                        Order::country,
                        Collectors.groupingBy(
                                Order::year,
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(
                                                Order::customerId,
                                                Collectors.summingDouble(Order::amount)
                                        ),
                                        spendMap -> spendMap.entrySet().stream()
                                                .max(Map.Entry.comparingByValue())
                                                .map(Map.Entry::getKey)
                                                .orElse("N/A")
                                )
                        )
                ));
    }

    // Helper — assign tier from total spend value
    static CustomerTier tierFromSpend(double spend) {
        if (spend < 500)  return CustomerTier.BRONZE;
        if (spend < 1500) return CustomerTier.SILVER;
        if (spend < 3000) return CustomerTier.GOLD;
        return CustomerTier.PLATINUM;
    }
}
