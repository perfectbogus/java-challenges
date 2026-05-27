package dev.perfectbogus.design.patterns.structural.adapter.payments;


import java.util.HashMap;
import java.util.Map;

// Task 4 — Adapter that wraps PayPalGateway and exposes PaymentProcessor.
// Stores paymentId from PayPalResponse so refundPayment can call cancelPayment().
public class PayPalAdapter implements PaymentProcessor {

    // TODO: private field for PayPalGateway adaptee
    // TODO: private Map<String, String> to store transactionId → paypalPaymentId

    // TODO: constructor accepting PayPalGateway
    public PayPalAdapter(PayPalGateway payPalGateway) {
        // TODO: implement
    }

    // TODO: call payPalGateway.sendPayment(), store paymentId from response,
    //       return response.success
    @Override
    public boolean processPayment(String currency, double amount) {
        // TODO: implement
        return false;
    }

    // TODO: look up the stored paymentId for transactionId,
    //       call payPalGateway.cancelPayment(), return false if not found
    @Override
    public boolean refundPayment(String transactionId) {
        // TODO: implement
        return false;
    }
}
