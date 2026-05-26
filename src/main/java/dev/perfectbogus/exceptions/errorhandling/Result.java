package dev.perfectbogus.exceptions.errorhandling;

import java.util.function.Function;

// Task 10 — Result<T> represents either a success value or a failure message.
// It is an alternative to throwing exceptions — errors are values, not control flow.
public class Result<T> {
    // TODO: add private fields to hold value and errorMessage
    private final T value;
    private final String errorMessage;

    // TODO: private constructor (force usage of static factories below)
    public Result(T value, String errorMessage) {
        this.value = value;
        this.errorMessage = errorMessage;
    }

    // TODO: static factory — creates a successful Result holding the given value
    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    // TODO: static factory — creates a failed Result holding the given error message
    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(null, errorMessage);
    }

    // TODO: returns true if this Result represents a success, false if failure
    public boolean isSuccess() {
        return errorMessage == null;
    }

    // TODO: returns the value if successful
    //       throws IllegalStateException("Result is a failure") if this is a failure
    public T getValue() {
        if (!isSuccess()) throw new IllegalStateException("Result is a failure");
        return value;
    }

    // TODO: returns the error message if this is a failure
    //       throws IllegalStateException("Result is a success") if this is a success
    public String getError() {
        if (isSuccess()) throw new IllegalStateException("Result is a success");
        return errorMessage;
    }

    // TODO: if successful, apply mapper to the value and return Result.success(mappedValue)
    //       if failure, return a failure Result with the same error message — do not apply mapper
    public <U> Result<U> map(Function<T, U> mapper) {
        if (isSuccess()) {
            return Result.success(mapper.apply(value));
        } else {
            return Result.failure(errorMessage);
        }
    }
}
