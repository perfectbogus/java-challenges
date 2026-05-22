package dev.perfectbogus.functional.order.analytics;

public class Order {
    private final String orderId;
    private final String customer;
    private final String category;
    private final double value;
    private final int quantity;
    private final OrderStatus status;

    public Order(String orderId, String customer, String category, double value, int quantity, OrderStatus status) {
        this.orderId = orderId;
        this.customer = customer;
        this.category = category;
        this.value = value;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
