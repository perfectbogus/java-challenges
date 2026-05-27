package dev.perfectbogus.design.patterns.structural.adapter.payments;


import java.util.UUID;

// Task 2 — Response object returned by PayPalGateway. PROVIDED — do NOT modify.
class PayPalResponse {
    public final boolean success;
    public final String  paymentId;

    public PayPalResponse(boolean success, String paymentId) {
        this.success   = success;
        this.paymentId = paymentId;
    }
}

// Task 2 — Legacy Adaptee. PROVIDED — do NOT modify this class.
// The client cannot use this directly because its interface
// does not match PaymentProcessor.
public class PayPalGateway {

    // Returns a PayPalResponse with success flag and a payment ID.
    public PayPalResponse sendPayment(String currency, double amount) {
        if (currency == null || currency.isBlank() || amount <= 0) {
            return new PayPalResponse(false, null);
        }
        return new PayPalResponse(true, "paypal-pay-" + UUID.randomUUID());
    }

    // Returns true if the payment was successfully cancelled.
    public boolean cancelPayment(String paymentId) {
        return paymentId != null && paymentId.startsWith("paypal-pay-");
    }
}
