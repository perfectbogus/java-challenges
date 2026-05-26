package dev.perfectbogus.exceptions.errorhandling;

public record UserAccount(
        String username,
        String email,
        String password,
        int age,
        String country
) {
}
