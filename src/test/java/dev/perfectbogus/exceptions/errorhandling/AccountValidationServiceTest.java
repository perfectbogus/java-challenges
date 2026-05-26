package dev.perfectbogus.exceptions.errorhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidationServiceTest {

    // -------------------------------------------------------------------------
    // Task 1 — Custom exception hierarchy
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - Custom exception hierarchy")
    class Task1 {

        @Test
        @DisplayName("ValidationException should be unchecked (RuntimeException)")
        void validationExceptionIsUnchecked() {
            assertInstanceOf(RuntimeException.class, new ValidationException("test"));
        }

        @Test
        @DisplayName("AccountNotFoundException should be unchecked (RuntimeException)")
        void accountNotFoundExceptionIsUnchecked() {
            assertInstanceOf(RuntimeException.class, new AccountNotFoundException("test"));
        }

        @Test
        @DisplayName("InsufficientFundsException should be checked (Exception)")
        void insufficientFundsExceptionIsChecked() {
            var ex = new InsufficientFundsException("test");
            assertInstanceOf(Exception.class, ex);
            assertFalse((Object) ex instanceof RuntimeException);
        }

        @Test
        @DisplayName("PaymentProcessingException should be checked (Exception)")
        void paymentProcessingExceptionIsChecked() {
            var ex = new PaymentProcessingException("test");
            assertInstanceOf(Exception.class, ex);
            assertFalse((Object) ex instanceof RuntimeException);
        }

        @Test
        @DisplayName("PaymentProcessingException should support exception chaining")
        void paymentProcessingExceptionChaining() {
            var cause = new NumberFormatException("bad input");
            var ex    = new PaymentProcessingException("wrapper", cause);
            assertEquals("wrapper",   ex.getMessage());
            assertEquals(cause,       ex.getCause());
        }

        @Test
        @DisplayName("All exceptions should preserve the message")
        void allExceptionsPreserveMessage() {
            assertEquals("msg", new ValidationException("msg").getMessage());
            assertEquals("msg", new AccountNotFoundException("msg").getMessage());
            assertEquals("msg", new InsufficientFundsException("msg").getMessage());
            assertEquals("msg", new PaymentProcessingException("msg").getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Validate username
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - validateUsername")
    class Task2 {

        @Test
        @DisplayName("Should throw ValidationException for null username")
        void shouldThrowForNull() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateUsername(null));
        }

        @Test
        @DisplayName("Should throw ValidationException for blank username")
        void shouldThrowForBlank() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateUsername("   "));
        }

        @Test
        @DisplayName("Should throw ValidationException for username shorter than 3 chars")
        void shouldThrowForTooShort() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateUsername("ab"));
        }

        @Test
        @DisplayName("Should throw ValidationException for username longer than 20 chars")
        void shouldThrowForTooLong() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateUsername("a".repeat(21)));
        }

        @Test
        @DisplayName("Should pass for a valid username of exactly 3 chars")
        void shouldPassForMinLength() {
            assertDoesNotThrow(() -> AccountValidationService.validateUsername("abc"));
        }

        @Test
        @DisplayName("Should pass for a valid username of exactly 20 chars")
        void shouldPassForMaxLength() {
            assertDoesNotThrow(() -> AccountValidationService.validateUsername("a".repeat(20)));
        }

        @Test
        @DisplayName("Should pass for a valid username")
        void shouldPassForValidUsername() {
            assertDoesNotThrow(() -> AccountValidationService.validateUsername("john_doe"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Safe integer parsing
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - safeParseInt")
    class Task3 {

        @Test
        @DisplayName("Should throw ValidationException with null message for null input")
        void shouldThrowForNull() {
            var ex = assertThrows(ValidationException.class,
                    () -> AccountValidationService.safeParseInt(null));
            assertEquals("Value cannot be null", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw ValidationException wrapping NumberFormatException for invalid input")
        void shouldThrowForInvalidInput() {
            var ex = assertThrows(ValidationException.class,
                    () -> AccountValidationService.safeParseInt("abc"));
            assertEquals("Invalid number: abc", ex.getMessage());
        }

        @Test
        @DisplayName("Should return parsed integer for valid input")
        void shouldReturnParsedInt() {
            assertEquals(42,   AccountValidationService.safeParseInt("42"));
            assertEquals(-10,  AccountValidationService.safeParseInt("-10"));
            assertEquals(0,    AccountValidationService.safeParseInt("0"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Safe account lookup
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - findAccount")
    class Task4 {

        private final UserAccount alice = new UserAccount("alice", "alice@mail.com", "pass1234", 25, "USA");
        private final Map<String, UserAccount> accounts = Map.of("alice", alice);

        @Test
        @DisplayName("Should throw ValidationException when accounts map is null")
        void shouldThrowWhenMapIsNull() {
            var ex = assertThrows(ValidationException.class,
                    () -> AccountValidationService.findAccount(null, "alice"));
            assertEquals("Arguments cannot be null", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw ValidationException when accountId is null")
        void shouldThrowWhenIdIsNull() {
            var ex = assertThrows(ValidationException.class,
                    () -> AccountValidationService.findAccount(accounts, null));
            assertEquals("Arguments cannot be null", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw AccountNotFoundException when account does not exist")
        void shouldThrowWhenNotFound() {
            var ex = assertThrows(AccountNotFoundException.class,
                    () -> AccountValidationService.findAccount(accounts, "bob"));
            assertEquals("Account not found: bob", ex.getMessage());
        }

        @Test
        @DisplayName("Should return the UserAccount when found")
        void shouldReturnAccountWhenFound() {
            var result = AccountValidationService.findAccount(accounts, "alice");
            assertEquals(alice, result);
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Process with guaranteed cleanup
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - processWithCleanup")
    class Task5 {

        @Test
        @DisplayName("Should run process and cleanup when process succeeds")
        void shouldRunBothWhenSuccessful() {
            AtomicBoolean processCalled = new AtomicBoolean(false);
            AtomicBoolean cleanupCalled = new AtomicBoolean(false);

            AccountValidationService.processWithCleanup(
                    () -> processCalled.set(true),
                    () -> cleanupCalled.set(true)
            );

            assertTrue(processCalled.get());
            assertTrue(cleanupCalled.get());
        }

        @Test
        @DisplayName("Should always run cleanup even when process throws")
        void shouldRunCleanupEvenWhenProcessThrows() {
            AtomicBoolean cleanupCalled = new AtomicBoolean(false);

            assertThrows(RuntimeException.class, () ->
                    AccountValidationService.processWithCleanup(
                            () -> { throw new RuntimeException("process failed"); },
                            () -> cleanupCalled.set(true)
                    )
            );

            assertTrue(cleanupCalled.get(), "Cleanup must run even when process throws");
        }

        @Test
        @DisplayName("Should re-throw the exception from process after cleanup")
        void shouldRethrowProcessException() {
            var ex = assertThrows(RuntimeException.class, () ->
                    AccountValidationService.processWithCleanup(
                            () -> { throw new IllegalStateException("boom"); },
                            () -> {}
                    )
            );
            assertEquals("boom", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw ValidationException when process is null")
        void shouldThrowWhenProcessIsNull() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.processWithCleanup(null, () -> {}));
        }

        @Test
        @DisplayName("Should throw ValidationException when cleanup is null")
        void shouldThrowWhenCleanupIsNull() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.processWithCleanup(() -> {}, null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Process payment with checked exceptions
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - processPayment")
    class Task6 {

        @Test
        @DisplayName("Should throw ValidationException when request is null")
        void shouldThrowForNullRequest() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.processPayment(null, 1000.0));
        }

        @Test
        @DisplayName("Should throw ValidationException when amount is zero or negative")
        void shouldThrowForInvalidAmount() {
            var req = new PaymentRequest("A001", 0.0, "USD");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.processPayment(req, 1000.0));

            var req2 = new PaymentRequest("A001", -50.0, "USD");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.processPayment(req2, 1000.0));
        }

        @Test
        @DisplayName("Should throw InsufficientFundsException when balance < amount")
        void shouldThrowInsufficientFunds() throws Exception {
            var req = new PaymentRequest("A001", 500.0, "USD");
            var ex  = assertThrows(InsufficientFundsException.class,
                    () -> AccountValidationService.processPayment(req, 100.0));
            assertTrue(ex.getMessage().contains("500"));
            assertTrue(ex.getMessage().contains("100"));
        }

        @Test
        @DisplayName("Should throw PaymentProcessingException for unsupported currency")
        void shouldThrowForUnsupportedCurrency() throws Exception {
            var req = new PaymentRequest("A001", 50.0, "JPY");
            var ex  = assertThrows(PaymentProcessingException.class,
                    () -> AccountValidationService.processPayment(req, 1000.0));
            assertTrue(ex.getMessage().contains("JPY"));
        }

        @Test
        @DisplayName("Should return new balance when payment is successful")
        void shouldReturnNewBalance() throws Exception {
            var req    = new PaymentRequest("A001", 200.0, "EUR");
            double result = AccountValidationService.processPayment(req, 1000.0);
            assertEquals(800.0, result, 0.01);
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Exception chaining
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - parseAndValidateAge")
    class Task7 {

        @Test
        @DisplayName("Should throw PaymentProcessingException wrapping NumberFormatException")
        void shouldWrapNumberFormatException() {
            var ex = assertThrows(PaymentProcessingException.class,
                    () -> AccountValidationService.parseAndValidateAge("not-a-number"));
            assertEquals("Failed to parse age: not-a-number", ex.getMessage());
            assertInstanceOf(NumberFormatException.class, ex.getCause());
        }

        @Test
        @DisplayName("Should throw ValidationException when age is below 0")
        void shouldThrowForNegativeAge() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.parseAndValidateAge("-1"));
        }

        @Test
        @DisplayName("Should throw ValidationException when age exceeds 150")
        void shouldThrowForAgeOver150() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.parseAndValidateAge("151"));
        }

        @Test
        @DisplayName("Should return parsed age for valid input")
        void shouldReturnValidAge() throws Exception {
            assertEquals(25,  AccountValidationService.parseAndValidateAge("25"));
            assertEquals(0,   AccountValidationService.parseAndValidateAge("0"));
            assertEquals(150, AccountValidationService.parseAndValidateAge("150"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Safe lookup returning Optional
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - findAccountSafe")
    class Task8 {

        private final UserAccount alice = new UserAccount("alice", "alice@mail.com", "pass1234", 25, "USA");
        private final Map<String, UserAccount> accounts = Map.of("alice", alice);

        @Test
        @DisplayName("Should return Optional.empty() when accounts map is null")
        void shouldReturnEmptyWhenMapIsNull() {
            assertTrue(AccountValidationService.findAccountSafe(null, "alice").isEmpty());
        }

        @Test
        @DisplayName("Should return Optional.empty() when accountId is null")
        void shouldReturnEmptyWhenIdIsNull() {
            assertTrue(AccountValidationService.findAccountSafe(accounts, null).isEmpty());
        }

        @Test
        @DisplayName("Should return Optional.empty() when account is not found")
        void shouldReturnEmptyWhenNotFound() {
            assertTrue(AccountValidationService.findAccountSafe(accounts, "bob").isEmpty());
        }

        @Test
        @DisplayName("Should return Optional.of(account) when account is found")
        void shouldReturnPresentOptional() {
            var result = AccountValidationService.findAccountSafe(accounts, "alice");
            assertTrue(result.isPresent());
            assertEquals(alice, result.get());
        }

        @Test
        @DisplayName("Should never throw any exception")
        void shouldNeverThrow() {
            assertDoesNotThrow(() -> AccountValidationService.findAccountSafe(null, null));
            assertDoesNotThrow(() -> AccountValidationService.findAccountSafe(accounts, "unknown"));
        }
    }

    // -------------------------------------------------------------------------
    // Task 9 — Validate full UserAccount
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 9 - validateAccount")
    class Task9 {

        @Test
        @DisplayName("Should throw ValidationException when account is null")
        void shouldThrowForNullAccount() {
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(null));
        }

        @Test
        @DisplayName("Should throw ValidationException for invalid username")
        void shouldThrowForInvalidUsername() {
            var account = new UserAccount("ab", "a@b.com", "password1", 25, "USA");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(account));
        }

        @Test
        @DisplayName("Should throw ValidationException for email without @")
        void shouldThrowForInvalidEmail() {
            var account = new UserAccount("alice", "invalid-email", "password1", 25, "USA");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(account));
        }

        @Test
        @DisplayName("Should throw ValidationException for password shorter than 8 chars")
        void shouldThrowForShortPassword() {
            var account = new UserAccount("alice", "a@b.com", "pass", 25, "USA");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(account));
        }

        @Test
        @DisplayName("Should throw ValidationException for age below 18")
        void shouldThrowForUnderageAccount() {
            var account = new UserAccount("alice", "a@b.com", "password1", 17, "USA");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(account));
        }

        @Test
        @DisplayName("Should throw ValidationException for age above 120")
        void shouldThrowForAgeOver120() {
            var account = new UserAccount("alice", "a@b.com", "password1", 121, "USA");
            assertThrows(ValidationException.class,
                    () -> AccountValidationService.validateAccount(account));
        }

        @Test
        @DisplayName("Should pass for a fully valid account")
        void shouldPassForValidAccount() {
            var account = new UserAccount("alice", "alice@mail.com", "securepass", 25, "USA");
            assertDoesNotThrow(() -> AccountValidationService.validateAccount(account));
        }
    }

    // -------------------------------------------------------------------------
    // Task 10 — Result<T>
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 10 - Result<T>")
    class Task10 {

        @Test
        @DisplayName("success() should create a successful Result")
        void shouldCreateSuccess() {
            var result = Result.success(42);
            assertTrue(result.isSuccess());
            assertEquals(42, result.getValue());
        }

        @Test
        @DisplayName("failure() should create a failed Result")
        void shouldCreateFailure() {
            var result = Result.failure("something went wrong");
            assertFalse(result.isSuccess());
            assertEquals("something went wrong", result.getError());
        }

        @Test
        @DisplayName("getValue() on a failure should throw IllegalStateException")
        void getValueOnFailureShouldThrow() {
            var result = Result.failure("error");
            var ex = assertThrows(IllegalStateException.class, result::getValue);
            assertEquals("Result is a failure", ex.getMessage());
        }

        @Test
        @DisplayName("getError() on a success should throw IllegalStateException")
        void getErrorOnSuccessShouldThrow() {
            var result = Result.success("value");
            var ex = assertThrows(IllegalStateException.class, result::getError);
            assertEquals("Result is a success", ex.getMessage());
        }

        @Test
        @DisplayName("map() on a success should apply the mapper")
        void mapOnSuccessShouldApplyMapper() {
            var result = Result.success(10);
            var mapped = result.map(v -> v * 2);
            assertTrue(mapped.isSuccess());
            assertEquals(20, mapped.getValue());
        }

        @Test
        @DisplayName("map() on a failure should propagate the failure unchanged")
        void mapOnFailureShouldPropagateFailure() {
            Result<Integer> result = Result.failure("original error");
            var mapped = result.map(v -> v * 2);
            assertFalse(mapped.isSuccess());
            assertEquals("original error", mapped.getError());
        }
    }

    // -------------------------------------------------------------------------
    // Task 11 — registerAccount using Result
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 11 - registerAccount")
    class Task11 {

        @Test
        @DisplayName("Should return Result.success when account is valid and username is free")
        void shouldReturnSuccessForValidAccount() {
            var account  = new UserAccount("alice", "alice@mail.com", "securepass", 25, "USA");
            var registry = new HashMap<String, UserAccount>();
            var result   = AccountValidationService.registerAccount(account, registry);

            assertTrue(result.isSuccess());
            assertEquals(account, result.getValue());
            assertTrue(registry.containsKey("alice"));
        }

        @Test
        @DisplayName("Should return Result.failure when account fails validation")
        void shouldReturnFailureForInvalidAccount() {
            var account  = new UserAccount("ab", "bad-email", "short", 10, "USA");
            var registry = new HashMap<String, UserAccount>();
            var result   = AccountValidationService.registerAccount(account, registry);

            assertFalse(result.isSuccess());
            assertNotNull(result.getError());
        }

        @Test
        @DisplayName("Should return Result.failure when username is already taken")
        void shouldReturnFailureWhenUsernameTaken() {
            var account  = new UserAccount("alice", "alice@mail.com", "securepass", 25, "USA");
            var registry = new HashMap<String, UserAccount>();
            registry.put("alice", account);

            var result = AccountValidationService.registerAccount(account, registry);
            assertFalse(result.isSuccess());
            assertTrue(result.getError().contains("alice"));
        }

        @Test
        @DisplayName("Should never throw any exception")
        void shouldNeverThrow() {
            var registry = new HashMap<String, UserAccount>();
            assertDoesNotThrow(() ->
                    AccountValidationService.registerAccount(null, registry));
            assertDoesNotThrow(() ->
                    AccountValidationService.registerAccount(
                            new UserAccount("ab", "bad", "x", 5, "?"), registry));
        }

        @Test
        @DisplayName("Should not add invalid account to registry")
        void shouldNotAddInvalidAccountToRegistry() {
            var account  = new UserAccount("ab", "bad-email", "short", 10, "USA");
            var registry = new HashMap<String, UserAccount>();
            AccountValidationService.registerAccount(account, registry);
            assertTrue(registry.isEmpty());
        }
    }
}