package dev.perfectbogus.lists.sorting.challenges;

import java.util.*;

public class SortOrders {

    enum Status { DELIVERED, PENDING, CANCELLED }

    record Order(String orderId, Status status, double value) {}

    public static List<Order> sort(List<Order> orders) {
        if (orders == null) throw new IllegalArgumentException("orders is null");
        // TODO
        // Step 1 — define status priority map
        //   DELIVERED=0, PENDING=1, CANCELLED=2
        Map<Status, Integer> statusPriority = new HashMap<>(Map.of(
                Status.DELIVERED, 0,
                Status.PENDING, 1,
                Status.CANCELLED, 2
        ));

        Comparator<Order> cStatus = Comparator.comparingInt(order -> statusPriority.get(order.status()));
        Comparator<Order> cValue = Comparator.comparingDouble(Order::value).reversed();
        Comparator<Order> cId = Comparator.comparing(Order::orderId);

        orders.sort(cStatus.thenComparing(cValue).thenComparing(cId));

//        orders.sort(
//                Comparator.<Order>comparingInt(order -> statusPriority.get(order.status()))
//                        .thenComparing(Comparator.comparingDouble(Order::value).reversed())
//                        .thenComparing(Comparator.comparing(Order::orderId)));
//
//        orders.sort(
//                Comparator.comparingInt((Order order) -> statusPriority.get(order.status()))
//                        .thenComparing(Comparator.comparingDouble(Order::value).reversed())
//                        .thenComparing(Order::orderId));

        // Step 2 — comparingInt(statusPriority)
        //          thenComparingDouble(value reversed)
        //          thenComparing(orderId)
        return orders;
    }
}