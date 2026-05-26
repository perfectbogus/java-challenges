package dev.perfectbogus.exceptions.errorhandling;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
