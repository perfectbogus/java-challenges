package dev.perfectbogus.design.patterns.structural.adapter.payments;

import java.util.UUID;

// Task 2 — Legacy Adaptee. PROVIDED — do NOT modify this class.
// The client cannot use this directly because its interface
// does not match PaymentProcessor.
public class StripeGateway {

    // Returns a transaction ID string on success, null on failure.
    // amount must be provided in CENTS (e.g. $10.00 → 1000.0)
    public String charge(double amountInCents, String currencyCode) {
        if (amountInCents <= 0 || currencyCode == null || currencyCode.isBlank()) {
            return null;
        }
        return "stripe-txn-" + UUID.randomUUID();
    }

    // Returns true if the charge was successfully reversed.
    public boolean reverseCharge(String chargeId) {
        return chargeId != null && chargeId.startsWith("stripe-txn-");
    }
}
