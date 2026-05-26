package dev.perfectbogus.exceptions.errorhandling;

import java.util.Map;
import java.util.Optional;

public class AccountValidationService {

    // Task 2 — Validate a username
    // Throws ValidationException if: null, blank, or length not between 3 and 20 (inclusive)
    // Returns normally if all checks pass
    public static void validateUsername(String username) {
        // TODO:
        if (username == null) throw new ValidationException("Username cannot be null");
        if (username.isBlank()) throw new ValidationException("Username cannot be black");
        if (username.length() < 3 || username.length() > 20)
            throw new ValidationException("Username length must be between 3 and 20");
    }

    // Task 3 — Safe integer parsing
    // Throws ValidationException("Value cannot be null") if value is null
    // Catches NumberFormatException from Integer.parseInt and re-throws as
    //   ValidationException("Invalid number: " + value)
    // Returns the parsed int if successful
    public static int safeParseInt(String value) {
        // TODO: implement
        if (value == null) throw new ValidationException("Value cannot be null");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number: " + value);
        }
    }

    // Task 4 — Safe account lookup
    // Throws ValidationException("Arguments cannot be null") if accounts or accountId is null
    // Throws AccountNotFoundException("Account not found: " + accountId) if key is absent
    // Returns the UserAccount if found
    public static UserAccount findAccount(Map<String, UserAccount> accounts, String accountId) {
        // TODO: implement
        if (accounts == null || accountId == null) throw new ValidationException("Arguments cannot be null");

        var account = accounts.get(accountId);
        if (account == null) throw new AccountNotFoundException("Account not found: " + accountId);
        return account;
    }

    // Task 5 — Process with guaranteed cleanup
    // Throws ValidationException("Process and cleanup cannot be null") if either arg is null
    // Runs process.run() in try, cleanup.run() always in finally
    // Re-throws any RuntimeException thrown by process after cleanup runs
    public static void processWithCleanup(Runnable process, Runnable cleanup) {
        // TODO: implement
        if (process == null || cleanup == null) throw new ValidationException("Process and cleanup cannot be null");
        try {
            process.run();
        } finally {
            cleanup.run();
        }
    }

    // Task 6 — Process a payment with checked exceptions
    // Throws ValidationException if request is null, amount <= 0, or currency is blank
    // Throws InsufficientFundsException if balance < amount
    // Throws PaymentProcessingException if currency is not USD, EUR, or GBP
    // Returns new balance (balance - amount) if all checks pass
    public static double processPayment(PaymentRequest request, double balance)
            throws InsufficientFundsException, PaymentProcessingException {
        // TODO: implement
        if (request == null) throw new ValidationException("Request cannot be null");
        if (request.amount() <= 0) throw new ValidationException("Request Amount cannot be negative");
        if (request.currency().isBlank()) throw new ValidationException("Request Currency cannot be blank");
        if (balance < request.amount()) throw new InsufficientFundsException("Insufficient funds: required " + request.amount() + ", available " + balance);
        if (!isSupportedCurrency(request.currency())) {
            throw new PaymentProcessingException("Unsupported currency: " + request.currency());
        }

        return balance - request.amount();
    }

    private static boolean isSupportedCurrency(String currency) {
        return currency.equals("USD") || currency.equals("EUR") || currency.equals("BGP");
    }

    // Task 7 — Wrap low-level exceptions (exception chaining)
    // Parses rawAge with Integer.parseInt
    // If NumberFormatException is thrown, wrap it in PaymentProcessingException:
    //   message = "Failed to parse age: " + rawAge, cause = the NumberFormatException
    // If age < 0 or age > 150, throw ValidationException("Age out of range: " + age)
    // Returns the parsed int age if valid
    public static int parseAndValidateAge(String rawAge) throws PaymentProcessingException {
        // TODO: implement
        try {
            final int age = Integer.parseInt(rawAge);
            if (age < 0 || age > 150) throw new ValidationException("Age out of range: " + age);
            return age;
        } catch (NumberFormatException e) {
            throw new PaymentProcessingException("Failed to parse age: " + rawAge, e);
        }
    }

    // Task 8 — Safe lookup returning Optional (must never throw)
    // Returns Optional.empty() if accounts or accountId is null
    // Returns Optional.empty() if accountId is not found
    // Returns Optional.of(account) if found
    public static Optional<UserAccount> findAccountSafe(Map<String, UserAccount> accounts, String accountId) {
        // TODO: implement
        if (accounts == null || accountId == null) return Optional.empty();
        var acc = accounts.get(accountId);
        if (acc == null) return Optional.empty();
        return Optional.of(acc);
    }

    // Task 9 — Validate a full UserAccount (check fields in order, throw on first failure)
    // Throws ValidationException if:
    //   account is null
    //   username is null, blank, or not between 3 and 20 chars
    //   email is null, blank, or does not contain '@'
    //   password is null or shorter than 8 characters
    //   age < 18 or age > 120
    // Returns normally if all checks pass
    public static void validateAccount(UserAccount account) {
        // TODO: implement
    }

    // Task 11 — Register an account using Result (must never throw)
    // Wraps validateAccount in try-catch → returns Result.failure(message) if invalid
    // Returns Result.failure("Username already taken: " + username) if already in registry
    // Adds account to registry and returns Result.success(account) if all checks pass
    public static Result<UserAccount> registerAccount(
            UserAccount account, Map<String, UserAccount> registry) {
        // TODO: implement
        return null;
    }
}