package dev.perfectbogus.design.patterns.structural.adapter.payments;

import java.util.HashMap;
import java.util.Map;

// Task 3 — Adapter that wraps StripeGateway and exposes PaymentProcessor.
// Key translation: amount (dollars) → amountInCents (cents) before calling charge().
// Stores returned transaction IDs so refundPayment can call reverseCharge().
public class StripeAdapter implements PaymentProcessor {

    // TODO: private field for StripeGateway adaptee
    private final StripeGateway adaptee;
    // TODO: private Map<String, String> to store transactionId → stripeChargeId
    private final Map<String, String> history = new HashMap<>();
    // TODO: constructor accepting StripeGateway
    public StripeAdapter(StripeGateway stripeGateway) {
        // TODO: implement
        this.adaptee = stripeGateway;
    }

    // TODO: convert amount to cents, call stripeGateway.charge(),
    //       store the returned charge ID, return true if non-null
    @Override
    public boolean processPayment(String currency, double amount) {
        // TODO: implement
        return false;
    }

    // TODO: look up the stored charge ID for transactionId,
    //       call stripeGateway.reverseCharge(), return false if not found
    @Override
    public boolean refundPayment(String transactionId) {
        // TODO: implement
        return false;
    }
}
