package dev.perfectbogus.functional.order.analytics;

public record Order(
        String orderId,
        String customer,
        String category,
        double value,
        int quantity,
        OrderStatus status) {
}
