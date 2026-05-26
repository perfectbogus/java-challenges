package dev.perfectbogus.exceptions.errorhandling;

public record PaymentRequest (
        String accountId,
        double amount,
        String currency
) {}
