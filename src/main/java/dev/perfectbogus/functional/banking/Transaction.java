package dev.perfectbogus.functional.banking;

import java.util.List;

public record Transaction(
        String transactionId,
        String accountId,
        String customerId,
        TransactionType type,
        String category,
        double amount,
        String currency,
        int year,
        int month,
        TransactionStatus status,
        List<String> labels
) {}
