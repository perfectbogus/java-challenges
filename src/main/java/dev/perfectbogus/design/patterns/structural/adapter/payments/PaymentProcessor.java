package dev.perfectbogus.design.patterns.structural.adapter.payments;

// Task 1 — Target interface.
// This is what the client code uses — it never talks to gateways directly.
// Both adapters must implement this contract.
public interface PaymentProcessor {
    // TODO: boolean processPayment(String currency, double amount)
    // TODO: boolean refundPayment(String transactionId)
    boolean processPayment(String currency, double amount);
    boolean refundPayment(String transactionId);
}
