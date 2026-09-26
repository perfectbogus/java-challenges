package dev.perfectbogus.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlingIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: divideExact
    // ==========================================================
    @Nested
    class DivideExactTests {

        @Test
        void testNormalDivision() {
            assertEquals(5, ExceptionHandlingIntermediateChallenge.divideExact(10, 2));
        }

        @Test
        void testDivisionByZeroThrowsCustomException() {
            ExceptionHandlingIntermediateChallenge.DivisionByZeroException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.DivisionByZeroException.class,
                    () -> ExceptionHandlingIntermediateChallenge.divideExact(7, 0)
            );
            assertEquals("Cannot divide by zero", thrown.getMessage());
        }

        @Test
        void testNegativeResult() {
            assertEquals(-3, ExceptionHandlingIntermediateChallenge.divideExact(-9, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 2: parsePositiveInt
    // ==========================================================
    @Nested
    class ParsePositiveIntTests {

        @Test
        void testValidPositiveNumber() throws Exception {
            assertEquals(42, ExceptionHandlingIntermediateChallenge.parsePositiveInt("42"));
        }

        @Test
        void testInvalidFormatWrapsCause() {
            ExceptionHandlingIntermediateChallenge.ValidationException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.ValidationException.class,
                    () -> ExceptionHandlingIntermediateChallenge.parsePositiveInt("abc")
            );
            assertEquals("Invalid number format: abc", thrown.getMessage());
            assertInstanceOf(NumberFormatException.class, thrown.getCause());
        }

        @Test
        void testZeroIsRejected() {
            ExceptionHandlingIntermediateChallenge.ValidationException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.ValidationException.class,
                    () -> ExceptionHandlingIntermediateChallenge.parsePositiveInt("0")
            );
            assertEquals("Value must be positive: 0", thrown.getMessage());
        }

        @Test
        void testNegativeIsRejected() {
            assertThrows(
                    ExceptionHandlingIntermediateChallenge.ValidationException.class,
                    () -> ExceptionHandlingIntermediateChallenge.parsePositiveInt("-5")
            );
        }

        @Test
        void testValidationExceptionIsAppException() {
            assertInstanceOf(
                    ExceptionHandlingIntermediateChallenge.AppException.class,
                    assertThrows(
                            ExceptionHandlingIntermediateChallenge.ValidationException.class,
                            () -> ExceptionHandlingIntermediateChallenge.parsePositiveInt("bad")
                    )
            );
        }
    }

    // ==========================================================
    // CHALLENGE 3: validateAge
    // ==========================================================
    @Nested
    class ValidateAgeTests {

        @Test
        void testValidAge() {
            assertEquals(30, ExceptionHandlingIntermediateChallenge.validateAge(30));
        }

        @Test
        void testBoundaryZeroIsValid() {
            assertEquals(0, ExceptionHandlingIntermediateChallenge.validateAge(0));
        }

        @Test
        void testBoundary150IsValid() {
            assertEquals(150, ExceptionHandlingIntermediateChallenge.validateAge(150));
        }

        @Test
        void testNegativeAgeThrows() {
            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> ExceptionHandlingIntermediateChallenge.validateAge(-1)
            );
            assertEquals("Age must be between 0 and 150", thrown.getMessage());
        }

        @Test
        void testTooLargeAgeThrows() {
            assertThrows(IllegalArgumentException.class, () -> ExceptionHandlingIntermediateChallenge.validateAge(151));
        }
    }

    // ==========================================================
    // CHALLENGE 4: lookupUser
    // ==========================================================
    @Nested
    class LookupUserTests {

        @Test
        void testUserFound() throws Exception {
            Map<String, Integer> users = Map.of("alice", 1, "bob", 2);
            assertEquals(1, ExceptionHandlingIntermediateChallenge.lookupUser(users, "alice"));
        }

        @Test
        void testUserNotFoundThrows() {
            Map<String, Integer> users = Map.of("alice", 1);
            ExceptionHandlingIntermediateChallenge.NotFoundException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.NotFoundException.class,
                    () -> ExceptionHandlingIntermediateChallenge.lookupUser(users, "carol")
            );
            assertEquals("User not found: carol", thrown.getMessage());
        }

        @Test
        void testNotFoundExceptionIsAppException() {
            Map<String, Integer> users = Map.of();
            assertInstanceOf(
                    ExceptionHandlingIntermediateChallenge.AppException.class,
                    assertThrows(
                            ExceptionHandlingIntermediateChallenge.NotFoundException.class,
                            () -> ExceptionHandlingIntermediateChallenge.lookupUser(users, "dave")
                    )
            );
        }
    }

    // ==========================================================
    // CHALLENGE 5: safeDivideAll
    // ==========================================================
    @Nested
    class SafeDivideAllTests {

        @Test
        void testAllValidDivisions() {
            assertArrayEquals(
                    new int[] {5, 3, 2},
                    ExceptionHandlingIntermediateChallenge.safeDivideAll(new int[] {10, 9, 8}, new int[] {2, 3, 4})
            );
        }

        @Test
        void testZeroDenominatorBecomesSentinel() {
            assertArrayEquals(
                    new int[] {5, Integer.MIN_VALUE, 2},
                    ExceptionHandlingIntermediateChallenge.safeDivideAll(new int[] {10, 9, 8}, new int[] {2, 0, 4})
            );
        }

        @Test
        void testAllZeroDenominators() {
            assertArrayEquals(
                    new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE},
                    ExceptionHandlingIntermediateChallenge.safeDivideAll(new int[] {1, 2}, new int[] {0, 0})
            );
        }

        @Test
        void testEmptyArrays() {
            assertArrayEquals(new int[] {}, ExceptionHandlingIntermediateChallenge.safeDivideAll(new int[] {}, new int[] {}));
        }
    }

    // ==========================================================
    // CHALLENGE 6: parseFlexible
    // ==========================================================
    @Nested
    class ParseFlexibleTests {

        @Test
        void testValidNumberWithWhitespace() {
            assertEquals(42, ExceptionHandlingIntermediateChallenge.parseFlexible("  42  "));
        }

        @Test
        void testNullReturnsMinusOne() {
            assertEquals(-1, ExceptionHandlingIntermediateChallenge.parseFlexible(null));
        }

        @Test
        void testInvalidTextReturnsMinusOne() {
            assertEquals(-1, ExceptionHandlingIntermediateChallenge.parseFlexible("abc"));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(-7, ExceptionHandlingIntermediateChallenge.parseFlexible(" -7 "));
        }
    }

    // ==========================================================
    // CHALLENGE 7: useResources
    // ==========================================================
    @Nested
    class UseResourcesTests {

        @Test
        void testOpenUseAndCloseOrder() {
            List<String> log = new ArrayList<>();
            ExceptionHandlingIntermediateChallenge.useResources(log);
            assertEquals(List.of("open:A", "open:B", "using", "close:B", "close:A"), log);
        }
    }

    // ==========================================================
    // CHALLENGE 8: useFailingResource
    // ==========================================================
    @Nested
    class UseFailingResourceTests {

        @Test
        void testBodyExceptionPropagatesWithSuppressedClose() {
            IllegalStateException thrown = assertThrows(
                    IllegalStateException.class,
                    ExceptionHandlingIntermediateChallenge::useFailingResource
            );
            assertEquals("body failure", thrown.getMessage());
            Throwable[] suppressed = thrown.getSuppressed();
            assertEquals(1, suppressed.length);
            assertInstanceOf(RuntimeException.class, suppressed[0]);
            assertEquals("Failed to close resource", suppressed[0].getMessage());
        }
    }

    // ==========================================================
    // CHALLENGE 9: readConfigValue
    // ==========================================================
    @Nested
    class ReadConfigValueTests {

        @Test
        void testValidValue() {
            Map<String, String> config = Map.of("port", "8080");
            assertEquals(8080, ExceptionHandlingIntermediateChallenge.readConfigValue(config, "port"));
        }

        @Test
        void testMissingKeyThrows() {
            Map<String, String> config = Map.of("port", "8080");
            ExceptionHandlingIntermediateChallenge.ConfigurationException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.ConfigurationException.class,
                    () -> ExceptionHandlingIntermediateChallenge.readConfigValue(config, "timeout")
            );
            assertEquals("Missing configuration key: timeout", thrown.getMessage());
        }

        @Test
        void testInvalidValueWrapsCause() {
            Map<String, String> config = Map.of("port", "not-a-number");
            ExceptionHandlingIntermediateChallenge.ConfigurationException thrown = assertThrows(
                    ExceptionHandlingIntermediateChallenge.ConfigurationException.class,
                    () -> ExceptionHandlingIntermediateChallenge.readConfigValue(config, "port")
            );
            assertEquals("Invalid configuration value for key: port", thrown.getMessage());
            assertInstanceOf(NumberFormatException.class, thrown.getCause());
        }
    }

    // ==========================================================
    // CHALLENGE 10: computeWithCleanup
    // ==========================================================
    @Nested
    class ComputeWithCleanupTests {

        @Test
        void testSuccessfulComputationStillRunsCleanup() {
            List<String> log = new ArrayList<>();
            int result = ExceptionHandlingIntermediateChallenge.computeWithCleanup(5, log);
            assertEquals(20, result);
            assertEquals(List.of("start", "cleanup"), log);
        }

        @Test
        void testFailingComputationStillRunsCleanupAndPropagates() {
            List<String> log = new ArrayList<>();
            assertThrows(ArithmeticException.class, () -> ExceptionHandlingIntermediateChallenge.computeWithCleanup(0, log));
            assertEquals(List.of("start", "cleanup"), log);
        }
    }

    // ==========================================================
    // CHALLENGE 11: BankAccount.withdraw
    // ==========================================================
    @Nested
    class BankAccountWithdrawTests {

        @Test
        void testConstructorRejectsNegativeBalance() {
            assertThrows(IllegalArgumentException.class, () -> new ExceptionHandlingIntermediateChallenge.BankAccount(-10));
        }

        @Test
        void testSuccessfulWithdrawal() throws Exception {
            ExceptionHandlingIntermediateChallenge.BankAccount account =
                    new ExceptionHandlingIntermediateChallenge.BankAccount(100);
            double newBalance = account.withdraw(40);
            assertEquals(60, newBalance);
            assertEquals(60, account.getBalance());
        }

        @Test
        void testWithdrawalExceedingBalanceThrowsAndLeavesBalanceUnchanged() {
            ExceptionHandlingIntermediateChallenge.BankAccount account =
                    new ExceptionHandlingIntermediateChallenge.BankAccount(50);
            assertThrows(
                    ExceptionHandlingIntermediateChallenge.InsufficientFundsException.class,
                    () -> account.withdraw(100)
            );
            assertEquals(50, account.getBalance());
        }
    }

    // ==========================================================
    // CHALLENGE 12: getRootCause
    // ==========================================================
    @Nested
    class GetRootCauseTests {

        @Test
        void testNoCauseReturnsSameThrowable() {
            RuntimeException t = new RuntimeException("solo");
            assertSame(t, ExceptionHandlingIntermediateChallenge.getRootCause(t));
        }

        @Test
        void testSingleLevelCause() {
            Throwable root = new IllegalArgumentException("root cause");
            Throwable wrapper = new RuntimeException("wrapper", root);
            assertSame(root, ExceptionHandlingIntermediateChallenge.getRootCause(wrapper));
        }

        @Test
        void testMultiLevelChain() {
            Throwable root = new NumberFormatException("deepest");
            Throwable middle = new IllegalStateException("middle", root);
            Throwable top = new RuntimeException("top", middle);
            assertSame(root, ExceptionHandlingIntermediateChallenge.getRootCause(top));
        }
    }
}