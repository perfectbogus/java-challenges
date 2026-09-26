package dev.perfectbogus.exceptions;

import java.util.List;
import java.util.Map;

public class ExceptionHandlingIntermediateChallenge {

    // ==========================================================
    // Custom exception types used across the challenges below.
    // ==========================================================

    // Unchecked. Used by CHALLENGE 1.
    public static class DivisionByZeroException extends RuntimeException {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    // Checked base of a small exception hierarchy. Used by CHALLENGE 2 and
    // CHALLENGE 4 through its two subclasses below.
    public static class AppException extends Exception {
        public AppException(String message) {
            super(message);
        }
        public AppException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Checked. Used by CHALLENGE 2.
    public static class ValidationException extends AppException {
        public ValidationException(String message) {
            super(message);
        }
        public ValidationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Checked. Used by CHALLENGE 4.
    public static class NotFoundException extends AppException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    // Unchecked. Used by CHALLENGE 9.
    public static class ConfigurationException extends RuntimeException {
        public ConfigurationException(String message) {
            super(message);
        }
        public ConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Checked. Used by CHALLENGE 11.
    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    // A resource whose open/close calls append to a shared log, so that
    // try-with-resources behavior (ordering, timing) can be observed from
    // outside. Used by CHALLENGE 7. Fully implemented — nothing to do here.
    public static class LoggingResource implements AutoCloseable {
        private final String name;
        private final List<String> log;

        public LoggingResource(String name, List<String> log) {
            this.name = name;
            this.log = log;
            log.add("open:" + name);
        }

        @Override
        public void close() {
            log.add("close:" + name);
        }
    }

    // A resource whose close() always throws. Used by CHALLENGE 8. Fully
    // implemented — nothing to do here.
    public static class FailingCloseResource implements AutoCloseable {
        @Override
        public void close() {
            throw new RuntimeException("Failed to close resource");
        }
    }

    // A minimal bank account. The constructor's fail-fast validation is
    // already implemented; CHALLENGE 11 is to implement withdraw().
    public static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        // CHALLENGE 11
        // Withdraws amount from the account and returns the resulting
        // balance. If amount is greater than the current balance, throws
        // InsufficientFundsException instead of changing the balance, with
        // a message of the form "Insufficient funds: attempted to withdraw
        // <amount> but balance is <balance>" (using the values as Java
        // would print them by default).
        public double withdraw(double amount) throws InsufficientFundsException {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // ==========================================================
    // Challenges
    // ==========================================================

    // CHALLENGE 1
    // Divides a by b. If b is zero, throws DivisionByZeroException with
    // the message "Cannot divide by zero" instead of letting the JVM's
    // own ArithmeticException propagate.
    public static int divideExact(int a, int b) {
        if (b == 0) throw new DivisionByZeroException("Cannot divide by zero");
        return a/b;
    }

    // CHALLENGE 2
    // Parses s as an integer and requires it to be strictly positive.
    // If s is not a valid integer, throws ValidationException with the
    // message "Invalid number format: " + s, with the underlying parsing
    // exception set as its cause. If s parses but is zero or negative,
    // throws ValidationException with the message "Value must be
    // positive: " + s (no cause needed in this case). Otherwise returns
    // the parsed value.
    public static int parsePositiveInt(String s) throws ValidationException {
        try {
            int i = Integer.parseInt(s);
            if (i < 1) {
                throw new ValidationException("Value must be positive: " + s);
            }
            return i;
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number format: " + s, e);
        }
    }

    // CHALLENGE 3
    // Fail-fast input validation: returns age unchanged if it is between
    // 0 and 150 inclusive. Otherwise throws IllegalArgumentException with
    // the message "Age must be between 0 and 150".
    public static int validateAge(int age) {
        if (age < 0 || age > 150 ) throw new IllegalArgumentException("Age must be between 0 and 150");
        return age;
    }

    // CHALLENGE 4
    // Looks up name in users. If present, returns its value. If absent,
    // throws NotFoundException with the message "User not found: " + name.
    public static int lookupUser(Map<String, Integer> users, String name) throws NotFoundException {
        if (!users.containsKey(name)) throw new NotFoundException("User not found: " + name);
        return users.get(name);
    }

    // CHALLENGE 5
    // Divides numerators[i] by denominators[i] for every index (both
    // arrays are always the same length). Where a denominator is zero,
    // that particular division must not fail the whole operation: put
    // Integer.MIN_VALUE at that index instead and continue with the rest.
    // Returns the resulting array.
    public static int[] safeDivideAll(int[] numerators, int[] denominators) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Trims s and parses it as an integer, returning the result. If s is
    // null or, once trimmed, is not a valid integer, returns -1 instead
    // of throwing. Both failure cases must be handled by a single
    // multi-catch clause (catching both exception types together), not by
    // two separate catch blocks.
    public static int parseFlexible(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Opens two LoggingResource instances, named "A" and "B" in that
    // order, in a single try-with-resources statement, then appends
    // "using" to log inside the try block before the resources close.
    public static void useResources(List<String> log) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Opens a FailingCloseResource in a try-with-resources statement and,
    // inside the try block, throws a new IllegalStateException("body
    // failure"). Do not catch anything here — let whatever the
    // try-with-resources statement produces propagate to the caller
    // unchanged.
    public static void useFailingResource() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Looks up key in config and parses its value as an integer. If key
    // is not present in config, throws ConfigurationException with the
    // message "Missing configuration key: " + key. If the value is
    // present but not a valid integer, throws ConfigurationException with
    // the message "Invalid configuration value for key: " + key, with the
    // underlying parsing exception set as its cause. Otherwise returns
    // the parsed value.
    public static int readConfigValue(Map<String, String> config, String key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Appends "start" to log, then computes 100 / divisor. Regardless of
    // whether that computation succeeds or throws, appends "cleanup" to
    // log before this method returns or the exception leaves it. If the
    // division throws, that exception must still propagate to the caller
    // after the cleanup has run. If it succeeds, returns the computed
    // value.
    public static int computeWithCleanup(int divisor, List<String> log) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11 is BankAccount.withdraw(), declared above.

    // CHALLENGE 12
    // Walks t's cause chain (via getCause()) and returns the deepest
    // throwable in that chain: the one whose own getCause() is null. If t
    // itself has no cause, returns t.
    public static Throwable getRootCause(Throwable t) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}