package dev.perfectbogus.functional.banking;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankingAnalytics {

    private static final Predicate<Transaction> APPROVED = (t -> t.status() == TransactionStatus.APPROVED);
    private static final Predicate<Transaction> DECLINED = (t -> t.status() == TransactionStatus.DECLINED);
    // 1. APPROVED amount statistics per category
    // Returns Map<category, DoubleSummaryStatistics> — statistics computed only over APPROVED transactions.
    // Use Collectors.filtering as the downstream inside groupingBy. Do NOT pre-filter the stream.
    public static Map<String, DoubleSummaryStatistics> approvedStatsByCategory(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.filtering(APPROVED, Collectors.summarizingDouble(Transaction::amount))
                ));
    }

    // 2. Count DECLINED transactions per account
    // Returns Map<accountId, Long> — every account must appear even if its count is zero.
    // Use Collectors.filtering(DECLINED, counting) as downstream inside groupingBy. Do NOT pre-filter.
    public static Map<String, Long> declinedCountByAccount(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::accountId,
                        Collectors.filtering(DECLINED, Collectors.counting())
                ));
    }

    // 3. Partition by transaction type with summary statistics
    // Returns Map<Boolean, DoubleSummaryStatistics>:
    //   true  → DoubleSummaryStatistics of amount for all DEBIT transactions
    //   false → DoubleSummaryStatistics of amount for all CREDIT transactions
    // Use partitioningBy(type == DEBIT) with summarizingDouble as downstream.
    public static Map<Boolean, DoubleSummaryStatistics> partitionByTypeWithStats(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 4. Most recent APPROVED transaction ID per category
    // Returns Map<category, transactionId> — the transactionId of the APPROVED transaction
    // with the highest year; break ties by highest month.
    // Use Collectors.filtering + maxBy(Comparator.comparingInt(year).thenComparingInt(month)).
    // Unwrap Optional inside collectingAndThen — return "N/A" if no APPROVED transaction exists.
    public static Map<String, String> mostRecentApprovedByCategory(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 5. Accounts where all transactions are APPROVED and total amount exceeds threshold
    // Returns an unmodifiable List<String> of accountId values sorted alphabetically.
    // Both conditions must hold: allMatch(APPROVED) AND sum(amount) > threshold.
    public static List<String> fullyApprovedAccountsAboveThreshold(List<Transaction> transactions, double threshold) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 6. Unique labels per currency as an unmodifiable Set
    // Returns Map<currency, Set<String>> — all distinct labels from every transaction in that currency.
    // Flatten List<String> labels using Collectors.flatMapping. Set must be unmodifiable.
    public static Map<String, Set<String>> labelsByCurrency(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 7. Monthly spend summary per year using teeing
    // Returns Map<Integer, String> of year → "transactions=N, total=$X.XX".
    // Compute count and total amount in a single pass per year using Collectors.teeing.
    // Include all transactions regardless of status. Format total to exactly 2 decimal places.
    public static Map<Integer, String> yearlyTransactionSummary(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 8. APPROVED amount statistics per category per account
    // Returns Map<category, Map<accountId, DoubleSummaryStatistics>>.
    // The innermost statistics must only reflect APPROVED transactions.
    // Use nested groupingBy where the innermost downstream is:
    //   Collectors.filtering(APPROVED, summarizingDouble(amount))
    // Do NOT pre-filter the stream before collecting.
    public static Map<String, Map<String, DoubleSummaryStatistics>> approvedStatsByCategoryAndAccount(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 9. Classify accounts as "healthy" or "at-risk"
    // Returns Map<accountId, String>:
    //   "healthy"  → every transaction has status != DECLINED (may include PENDING or REVERSED)
    //   "at-risk"  → at least one transaction has status == DECLINED
    // Use groupingBy(accountId) + collectingAndThen(toList, list -> string).
    public static Map<String, String> classifyAccounts(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 10. APPROVED revenue share per category as a formatted percentage
    // Returns Map<category, "XX.XX%"> — each category's share of total APPROVED revenue.
    // Two-pass approach:
    //   Pass 1: compute grand total using filter(APPROVED) + mapToDouble + sum
    //   Pass 2: group by category, use Collectors.filtering(APPROVED, summingDouble) inside
    //           collectingAndThen to divide by grand total and format as "XX.XX%"
    public static Map<String, String> approvedRevenueShareByCategory(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 11. Best month per category by APPROVED spend
    // Returns Map<category, Integer> — the month number (1-12) with the highest total
    // APPROVED amount for that category.
    // Inside each category group, build a Map<Integer, Double> of month → total APPROVED spend
    // using Collectors.filtering + nested groupingBy(month, summingDouble) inside collectingAndThen,
    // then find the month key with the max value.
    // Return -1 if no APPROVED transactions exist for a category.
    public static Map<String, Integer> bestMonthByCategoryApprovedSpend(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }

    // 12. Total APPROVED spend: currency → TransactionType → customerId → total amount
    // Returns Map<currency, Map<TransactionType, Map<customerId, Double>>>.
    // Use three levels of groupingBy where the innermost downstream is:
    //   Collectors.filtering(APPROVED, summingDouble(amount))
    // Do NOT pre-filter the stream. After collecting, remove entries where total == 0.0
    // so only customers with at least one APPROVED transaction appear in the result.
    public static Map<String, Map<TransactionType, Map<String, Double>>> approvedSpendByCurrencyTypeAndCustomer(List<Transaction> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO: implement
        return null;
    }
}
