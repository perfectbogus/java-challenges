package dev.perfectbogus.functional.ecommerce;

import java.util.List;

public record Order(
        String orderId,
        String customerId,
        String country,
        String category,
        double amount,
        int quantity,
        int year,
        OrderStatus status,
        List<String> tags
) {}
