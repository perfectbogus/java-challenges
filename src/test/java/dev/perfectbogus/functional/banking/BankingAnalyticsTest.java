package dev.perfectbogus.functional.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BankingAnalyticsTest {

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactions = List.of(
                // Account A001 — Customer C001 — USD — all APPROVED
                new Transaction("T001", "A001", "C001", TransactionType.DEBIT,  "Food",          120.00, "USD", 2023, 1,  TransactionStatus.APPROVED,  List.of("grocery", "weekly")),
                new Transaction("T002", "A001", "C001", TransactionType.DEBIT,  "Food",          200.00, "USD", 2023, 3,  TransactionStatus.APPROVED,  List.of("restaurant", "weekly")),
                new Transaction("T003", "A001", "C001", TransactionType.CREDIT, "Salary",       3000.00, "USD", 2023, 1,  TransactionStatus.APPROVED,  List.of("income", "monthly")),

                // Account A002 — Customer C002 — USD — has DECLINED
                new Transaction("T004", "A002", "C002", TransactionType.DEBIT,  "Travel",        450.00, "USD", 2023, 2,  TransactionStatus.APPROVED,  List.of("flight", "business")),
                new Transaction("T005", "A002", "C002", TransactionType.DEBIT,  "Travel",        320.00, "USD", 2023, 4,  TransactionStatus.DECLINED,  List.of("hotel", "business")),
                new Transaction("T006", "A002", "C002", TransactionType.CREDIT, "Salary",       2500.00, "USD", 2023, 1,  TransactionStatus.APPROVED,  List.of("income", "monthly")),

                // Account A003 — Customer C003 — EUR — APPROVED and PENDING
                new Transaction("T007", "A003", "C003", TransactionType.DEBIT,  "Shopping",      180.00, "EUR", 2023, 5,  TransactionStatus.APPROVED,  List.of("clothing", "sale")),
                new Transaction("T008", "A003", "C003", TransactionType.DEBIT,  "Shopping",      250.00, "EUR", 2023, 8,  TransactionStatus.PENDING,   List.of("electronics", "sale")),
                new Transaction("T009", "A003", "C003", TransactionType.CREDIT, "Refund",        100.00, "EUR", 2023, 6,  TransactionStatus.APPROVED,  List.of("return", "clothing")),

                // Account A004 — Customer C004 — EUR — has DECLINED
                new Transaction("T010", "A004", "C004", TransactionType.DEBIT,  "Food",           90.00, "EUR", 2023, 2,  TransactionStatus.APPROVED,  List.of("grocery")),
                new Transaction("T011", "A004", "C004", TransactionType.DEBIT,  "Food",          150.00, "EUR", 2023, 6,  TransactionStatus.DECLINED,  List.of("restaurant")),
                new Transaction("T012", "A004", "C004", TransactionType.DEBIT,  "Entertainment", 200.00, "EUR", 2023, 9,  TransactionStatus.APPROVED,  List.of("concert", "event")),

                // Account A005 — Customer C005 — GBP — all APPROVED
                new Transaction("T013", "A005", "C005", TransactionType.DEBIT,  "Travel",        600.00, "GBP", 2022, 11, TransactionStatus.APPROVED,  List.of("flight", "holiday")),
                new Transaction("T014", "A005", "C005", TransactionType.DEBIT,  "Entertainment", 350.00, "GBP", 2022, 12, TransactionStatus.APPROVED,  List.of("concert", "holiday")),
                new Transaction("T015", "A005", "C005", TransactionType.CREDIT, "Salary",       4000.00, "GBP", 2022, 11, TransactionStatus.APPROVED,  List.of("income", "monthly")),

                // Account A006 — Customer C005 — GBP — has REVERSED
                new Transaction("T016", "A006", "C005", TransactionType.DEBIT,  "Shopping",      300.00, "GBP", 2022, 10, TransactionStatus.APPROVED,  List.of("clothing")),
                new Transaction("T017", "A006", "C005", TransactionType.DEBIT,  "Shopping",      420.00, "GBP", 2023, 2,  TransactionStatus.REVERSED,  List.of("electronics")),
                new Transaction("T018", "A006", "C005", TransactionType.DEBIT,  "Food",           80.00, "GBP", 2023, 3,  TransactionStatus.APPROVED,  List.of("grocery", "weekly")),

                // Account A007 — Customer C006 — USD — APPROVED
                new Transaction("T019", "A007", "C006", TransactionType.DEBIT,  "Entertainment", 500.00, "USD", 2023, 7,  TransactionStatus.APPROVED,  List.of("concert", "event")),
                new Transaction("T020", "A007", "C006", TransactionType.DEBIT,  "Food",          160.00, "USD", 2023, 9,  TransactionStatus.APPROVED,  List.of("restaurant", "weekly"))
        );
    }

    // -------------------------------------------------------------------------
    // Task 1 — APPROVED amount statistics per category
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - approvedStatsByCategory")
    class Task1 {

        @Test
        @DisplayName("Should only include APPROVED transactions in statistics")
        void shouldOnlyIncludeApprovedTransactions() {
            var result = BankingAnalytics.approvedStatsByCategory(transactions);

            // Food APPROVED: T001(120)+T002(200)+T010(90)+T018(80)+T020(160) = 5 transactions, sum=650
            var food = result.get("Food");
            assertEquals(5,      food.getCount());
            assertEquals(650.00, food.getSum(),    0.01);
            assertEquals(80.00,  food.getMin(),    0.01);
            assertEquals(200.00, food.getMax(),    0.01);
            assertEquals(130.00, food.getAverage(), 0.01);
        }

        @Test
        @DisplayName("Should exclude DECLINED transactions from statistics")
        void shouldExcludeDeclinedTransactions() {
            var result = BankingAnalytics.approvedStatsByCategory(transactions);

            // Travel APPROVED: T004(450)+T013(600) = 2 (T005 DECLINED excluded)
            var travel = result.get("Travel");
            assertEquals(2,      travel.getCount());
            assertEquals(1050.00, travel.getSum(), 0.01);
        }

        @Test
        @DisplayName("Should contain all categories from the transaction list")
        void shouldContainAllCategories() {
            var result = BankingAnalytics.approvedStatsByCategory(transactions);
            assertTrue(result.containsKey("Food"));
            assertTrue(result.containsKey("Travel"));
            assertTrue(result.containsKey("Shopping"));
            assertTrue(result.containsKey("Entertainment"));
            assertTrue(result.containsKey("Salary"));
            assertTrue(result.containsKey("Refund"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.approvedStatsByCategory(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Count DECLINED transactions per account
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - declinedCountByAccount")
    class Task2 {

        @Test
        @DisplayName("Should count DECLINED transactions correctly per account")
        void shouldCountDeclinedCorrectly() {
            var result = BankingAnalytics.declinedCountByAccount(transactions);

            assertEquals(0L, result.get("A001")); // all APPROVED
            assertEquals(1L, result.get("A002")); // T005 DECLINED
            assertEquals(0L, result.get("A003")); // has PENDING but no DECLINED
            assertEquals(1L, result.get("A004")); // T011 DECLINED
            assertEquals(0L, result.get("A005")); // all APPROVED
            assertEquals(0L, result.get("A006")); // has REVERSED but no DECLINED
            assertEquals(0L, result.get("A007")); // all APPROVED
        }

        @Test
        @DisplayName("Should contain every account including those with zero declined")
        void shouldContainAllAccounts() {
            var result = BankingAnalytics.declinedCountByAccount(transactions);
            assertEquals(7, result.size());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.declinedCountByAccount(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Partition by transaction type with summary statistics
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - partitionByTypeWithStats")
    class Task3 {

        @Test
        @DisplayName("Should compute correct statistics for DEBIT transactions")
        void shouldComputeDebitStats() {
            var result = BankingAnalytics.partitionByTypeWithStats(transactions);

            // DEBIT: T001,T002,T004,T005,T007,T008,T010,T011,T012,T013,T014,T016,T017,T018,T019,T020 = 16
            var debit = result.get(true);
            assertEquals(16, debit.getCount());
        }

        @Test
        @DisplayName("Should compute correct statistics for CREDIT transactions")
        void shouldComputeCreditStats() {
            var result = BankingAnalytics.partitionByTypeWithStats(transactions);

            // CREDIT: T003(3000)+T006(2500)+T009(100)+T015(4000) = 4
            var credit = result.get(false);
            assertEquals(4,       credit.getCount());
            assertEquals(9600.00, credit.getSum(),    0.01);
            assertEquals(100.00,  credit.getMin(),    0.01);
            assertEquals(4000.00, credit.getMax(),    0.01);
        }

        @Test
        @DisplayName("Should always contain both true and false keys")
        void shouldAlwaysHaveBothKeys() {
            var result = BankingAnalytics.partitionByTypeWithStats(transactions);
            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.partitionByTypeWithStats(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Most recent APPROVED transaction ID per category
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - mostRecentApprovedByCategory")
    class Task4 {

        @Test
        @DisplayName("Should return the most recent APPROVED transaction ID per category")
        void shouldReturnMostRecentApproved() {
            var result = BankingAnalytics.mostRecentApprovedByCategory(transactions);

            // Food APPROVED: T001(2023/1), T002(2023/3), T010(2023/2), T018(2023/3), T020(2023/9)
            // Most recent: T020 (2023, month 9)
            assertEquals("T020", result.get("Food"));

            // Travel APPROVED: T004(2023/2), T013(2022/11) → T004 is more recent
            assertEquals("T004", result.get("Travel"));

            // Entertainment APPROVED: T012(2023/9), T014(2022/12), T019(2023/7)
            // Most recent: T012 and T019 both in 2023 — T012 month 9 vs T019 month 7 → T012
            assertEquals("T012", result.get("Entertainment"));
        }

        @Test
        @DisplayName("Should return N/A when no APPROVED transaction exists for a category")
        void shouldReturnNAWhenNoneApproved() {
            List<Transaction> singleDeclined = List.of(
                    new Transaction("TX1", "A001", "C001", TransactionType.DEBIT,
                            "Bills", 50.0, "USD", 2023, 1, TransactionStatus.DECLINED, List.of())
            );
            var result = BankingAnalytics.mostRecentApprovedByCategory(singleDeclined);
            assertEquals("N/A", result.get("Bills"));
        }

        @Test
        @DisplayName("Should return String not Optional")
        void shouldReturnString() {
            var result = BankingAnalytics.mostRecentApprovedByCategory(transactions);
            result.values().forEach(v -> assertInstanceOf(String.class, v));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.mostRecentApprovedByCategory(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Accounts where all transactions are APPROVED and total > threshold
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - fullyApprovedAccountsAboveThreshold")
    class Task5 {

        @Test
        @DisplayName("Should return accounts where all transactions are APPROVED and total > threshold")
        void shouldReturnQualifyingAccounts() {
            // A001: all APPROVED, total=120+200+3000=3320 > 1000 → qualifies
            // A005: all APPROVED, total=600+350+4000=4950 > 1000 → qualifies
            // A007: all APPROVED, total=500+160=660 < 1000 → excluded by threshold
            // A002: has DECLINED → excluded
            // A003: has PENDING → all APPROVED? No → excluded
            // A004: has DECLINED → excluded
            // A006: has REVERSED → all APPROVED? No → excluded
            var result = BankingAnalytics.fullyApprovedAccountsAboveThreshold(transactions, 1000.0);
            assertEquals(List.of("A001", "A005"), result);
        }

        @Test
        @DisplayName("Should return empty list when no account qualifies")
        void shouldReturnEmptyWhenNoneQualify() {
            var result = BankingAnalytics.fullyApprovedAccountsAboveThreshold(transactions, 999999.0);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return results sorted alphabetically")
        void shouldBeSortedAlphabetically() {
            var result = BankingAnalytics.fullyApprovedAccountsAboveThreshold(transactions, 100.0);
            assertEquals(result.stream().sorted().toList(), result);
        }

        @Test
        @DisplayName("Should return unmodifiable list")
        void shouldReturnUnmodifiableList() {
            var result = BankingAnalytics.fullyApprovedAccountsAboveThreshold(transactions, 1000.0);
            assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.fullyApprovedAccountsAboveThreshold(null, 100.0));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Unique labels per currency as an unmodifiable Set
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - labelsByCurrency")
    class Task6 {

        @Test
        @DisplayName("Should collect all unique labels per currency")
        void shouldCollectAllUniqueLabels() {
            var result = BankingAnalytics.labelsByCurrency(transactions);

            // USD: T001(grocery,weekly)+T002(restaurant,weekly)+T003(income,monthly)+
            //      T004(flight,business)+T005(hotel,business)+T006(income,monthly)+
            //      T019(concert,event)+T020(restaurant,weekly)
            assertEquals(Set.of("grocery", "weekly", "restaurant", "income", "monthly",
                    "flight", "business", "hotel", "concert", "event"), result.get("USD"));

            // GBP: T013(flight,holiday)+T014(concert,holiday)+T015(income,monthly)+
            //      T016(clothing)+T017(electronics)+T018(grocery,weekly)
            assertEquals(Set.of("flight", "holiday", "concert", "income", "monthly",
                    "clothing", "electronics", "grocery", "weekly"), result.get("GBP"));
        }

        @Test
        @DisplayName("Should return unmodifiable sets")
        void shouldReturnUnmodifiableSets() {
            var result = BankingAnalytics.labelsByCurrency(transactions);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("USD").add("new-label"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.labelsByCurrency(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Monthly spend summary per year using teeing
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - yearlyTransactionSummary")
    class Task7 {

        @Test
        @DisplayName("Should return correct formatted summary per year")
        void shouldReturnCorrectSummary() {
            var result = BankingAnalytics.yearlyTransactionSummary(transactions);

            // 2022: T013(600)+T014(350)+T015(4000)+T016(300) = 5250.00, 4 transactions
            assertEquals("transactions=4, total=$5250.00", result.get(2022));

            // 2023: T001-T012 + T017-T020 = 16 transactions
            // sum = 120+200+3000+450+320+2500+180+250+100+90+150+200+420+80+500+160 = 8720.00
            assertEquals("transactions=16, total=$8720.00", result.get(2023));
        }

        @Test
        @DisplayName("Should contain both years")
        void shouldContainBothYears() {
            var result = BankingAnalytics.yearlyTransactionSummary(transactions);
            assertTrue(result.containsKey(2022));
            assertTrue(result.containsKey(2023));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.yearlyTransactionSummary(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — APPROVED amount statistics per category per account
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - approvedStatsByCategoryAndAccount")
    class Task8 {

        @Test
        @DisplayName("Should return correct APPROVED stats for Food per account")
        void shouldReturnCorrectFoodStats() {
            var result = BankingAnalytics.approvedStatsByCategoryAndAccount(transactions);

            // Food / A001: T001(120) + T002(200) = 2 APPROVED
            var foodA001 = result.get("Food").get("A001");
            assertEquals(2,      foodA001.getCount());
            assertEquals(320.00, foodA001.getSum(), 0.01);

            // Food / A004: T010(90) APPROVED, T011(150) DECLINED → only T010
            var foodA004 = result.get("Food").get("A004");
            assertEquals(1,     foodA004.getCount());
            assertEquals(90.00, foodA004.getSum(), 0.01);
        }

        @Test
        @DisplayName("Should exclude DECLINED transactions from statistics")
        void shouldExcludeDeclined() {
            var result = BankingAnalytics.approvedStatsByCategoryAndAccount(transactions);

            // Travel / A002: T004(450) APPROVED, T005(320) DECLINED → only T004
            var travelA002 = result.get("Travel").get("A002");
            assertEquals(1,      travelA002.getCount());
            assertEquals(450.00, travelA002.getSum(), 0.01);
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.approvedStatsByCategoryAndAccount(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 9 — Classify accounts as healthy or at-risk
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 9 - classifyAccounts")
    class Task9 {

        @Test
        @DisplayName("Should classify accounts correctly")
        void shouldClassifyCorrectly() {
            var result = BankingAnalytics.classifyAccounts(transactions);

            assertEquals("healthy",  result.get("A001")); // all APPROVED
            assertEquals("at-risk",  result.get("A002")); // has DECLINED (T005)
            assertEquals("healthy",  result.get("A003")); // has PENDING but no DECLINED
            assertEquals("at-risk",  result.get("A004")); // has DECLINED (T011)
            assertEquals("healthy",  result.get("A005")); // all APPROVED
            assertEquals("healthy",  result.get("A006")); // has REVERSED but no DECLINED
            assertEquals("healthy",  result.get("A007")); // all APPROVED
        }

        @Test
        @DisplayName("Should contain every account")
        void shouldContainAllAccounts() {
            var result = BankingAnalytics.classifyAccounts(transactions);
            assertEquals(7, result.size());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.classifyAccounts(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 10 — APPROVED revenue share per category as a formatted percentage
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 10 - approvedRevenueShareByCategory")
    class Task10 {

        @Test
        @DisplayName("Should return correct percentage share per category")
        void shouldReturnCorrectPercentages() {
            var result = BankingAnalytics.approvedRevenueShareByCategory(transactions);

            // APPROVED only:
            // Food:          T001(120)+T002(200)+T010(90)+T018(80)+T020(160) = 650
            // Travel:        T004(450)+T013(600)                             = 1050
            // Shopping:      T007(180)+T016(300)                             = 480
            // Entertainment: T012(200)+T014(350)+T019(500)                   = 1050
            // Salary:        T003(3000)+T006(2500)+T015(4000)                = 9500
            // Refund:        T009(100)                                       = 100
            // Grand total = 650+1050+480+1050+9500+100 = 12830

            assertEquals("5.07%",  result.get("Food"));          // 650/12830
            assertEquals("8.19%",  result.get("Travel"));        // 1050/12830
            assertEquals("3.74%",  result.get("Shopping"));      // 480/12830
            assertEquals("8.19%",  result.get("Entertainment")); // 1050/12830
            assertEquals("74.04%", result.get("Salary"));        // 9500/12830
            assertEquals("0.78%",  result.get("Refund"));        // 100/12830
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.approvedRevenueShareByCategory(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 11 — Best month per category by APPROVED spend
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 11 - bestMonthByCategoryApprovedSpend")
    class Task11 {

        @Test
        @DisplayName("Should return the month with highest APPROVED spend per category")
        void shouldReturnBestMonth() {
            var result = BankingAnalytics.bestMonthByCategoryApprovedSpend(transactions);

            // Food APPROVED by month:
            //   month 1: T001(120) = 120
            //   month 2: T010(90)  = 90
            //   month 3: T002(200)+T018(80) = 280
            //   month 9: T020(160) = 160
            // Best month: 3 (280)
            assertEquals(3, result.get("Food"));

            // Travel APPROVED by month:
            //   month 2: T004(450) = 450
            //   month 11: T013(600) = 600
            // Best month: 11 (600)
            assertEquals(11, result.get("Travel"));

            // Entertainment APPROVED by month:
            //   month 7:  T019(500) = 500
            //   month 9:  T012(200) = 200
            //   month 12: T014(350) = 350
            // Best month: 7 (500)
            assertEquals(7, result.get("Entertainment"));
        }

        @Test
        @DisplayName("Should return -1 when no APPROVED transaction exists for a category")
        void shouldReturnNegativeOneWhenNoneApproved() {
            List<Transaction> singleDeclined = List.of(
                    new Transaction("TX1", "A001", "C001", TransactionType.DEBIT,
                            "Bills", 50.0, "USD", 2023, 1, TransactionStatus.DECLINED, List.of())
            );
            var result = BankingAnalytics.bestMonthByCategoryApprovedSpend(singleDeclined);
            assertEquals(-1, result.get("Bills"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.bestMonthByCategoryApprovedSpend(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 12 — Total APPROVED spend: currency → type → customer
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 12 - approvedSpendByCurrencyTypeAndCustomer")
    class Task12 {

        @Test
        @DisplayName("Should return correct APPROVED totals for USD / DEBIT customers")
        void shouldReturnCorrectUsdDebitTotals() {
            var result = BankingAnalytics.approvedSpendByCurrencyTypeAndCustomer(transactions);

            // USD / DEBIT APPROVED:
            //   C001: T001(120)+T002(200) = 320
            //   C002: T004(450) only (T005 DECLINED)
            //   C006: T019(500)+T020(160) = 660
            var usdDebit = result.get("USD").get(TransactionType.DEBIT);
            assertEquals(320.00, usdDebit.get("C001"), 0.01);
            assertEquals(450.00, usdDebit.get("C002"), 0.01);
            assertEquals(660.00, usdDebit.get("C006"), 0.01);
        }

        @Test
        @DisplayName("Should return correct APPROVED totals for GBP / CREDIT customers")
        void shouldReturnCorrectGbpCreditTotals() {
            var result = BankingAnalytics.approvedSpendByCurrencyTypeAndCustomer(transactions);

            // GBP / CREDIT APPROVED: T015(4000) → C005
            var gbpCredit = result.get("GBP").get(TransactionType.CREDIT);
            assertEquals(4000.00, gbpCredit.get("C005"), 0.01);
        }

        @Test
        @DisplayName("Should not include customers with zero APPROVED spend")
        void shouldNotIncludeZeroApprovedSpend() {
            var result = BankingAnalytics.approvedSpendByCurrencyTypeAndCustomer(transactions);

            // USD / DEBIT: C002 has T005 DECLINED → their DEBIT APPROVED total is 450 (T004 only)
            // C002 should still appear since T004 is APPROVED
            var usdDebit = result.get("USD").get(TransactionType.DEBIT);
            assertTrue(usdDebit.containsKey("C002"));

            // But a customer with ONLY declined transactions should NOT appear
            // (verified structurally — no such customer exists in this test data,
            //  but zero-value entries should be removed)
            usdDebit.values().forEach(v -> assertTrue(v > 0.0));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BankingAnalytics.approvedSpendByCurrencyTypeAndCustomer(null));
        }
    }
}