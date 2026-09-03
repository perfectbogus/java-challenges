package dev.perfectbogus.exceptions;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class ExceptionChallenges2 {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–5)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Run a task and wrap any thrown exception with context information.
    // catch ANY exception → wrap it in RuntimeException with:
    //   message = context + ": " + original message
    //   cause   = original exception
    // If no exception thrown → return "OK"
    //
    // Input:  task=() -> {throw new IllegalStateException("disk full")},
    //         context="FileWriter"
    // → throws RuntimeException("FileWriter: disk full", cause=IllegalStateException)
    //
    // Input:  task=() -> {}, context="FileWriter"
    // → return "OK"
    //
    // Input:  task=() -> {throw new NullPointerException(null)},
    //         context="Parser"
    // → throws RuntimeException("Parser: null", cause=NullPointerException)
    // ─────────────────────────────────────────────────────────────
    public static String challenge1(Runnable task, String context) {
        if (task    == null) throw new IllegalArgumentException("Task cannot be null");
        if (context == null) throw new IllegalArgumentException("Context cannot be null");
        try {
            task.run();
            return "OK";
        } catch (Exception e) {
            throw new RuntimeException(context + ": " + e.getMessage(), e);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Safe map lookup that throws a custom unchecked exception
    // when the key is not found.
    //
    // Create class: MissingKeyException extends RuntimeException
    //   constructor: MissingKeyException(String key)
    //   method:      String getKey()  ← returns the missing key
    //   message:     "Key not found: " + key
    //
    // If key exists   → return the value
    // If key missing  → throw MissingKeyException(key)
    // If map is null  → throw IllegalArgumentException
    // If key is null  → throw IllegalArgumentException
    //
    // Input:  {a=1, b=2}, "a" → 1
    // Input:  {a=1, b=2}, "c" → throws MissingKeyException("c")
    // ─────────────────────────────────────────────────────────────
    public static class MissingKeyException extends RuntimeException {
        // You implement this class!
        // constructor: MissingKeyException(String key)
        // method: String getKey()
        private final String key;
        public MissingKeyException(String key) {
            super("Key not found: " + key);
            this.key = key;
        }
        public String getKey() { return key; }
    }

    public static <V> V challenge2(Map<String, V> map, String key) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (map.get(key) == null) throw new MissingKeyException(key);

        return map.get(key);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Validate age and throw a custom exception with extra data.
    //
    // Create class: InvalidAgeException extends RuntimeException
    //   constructor: InvalidAgeException(String message, int age)
    //   method:      int getAge()  ← returns the invalid age
    //
    // Rules:
    //   age < 0   → throw InvalidAgeException("Age cannot be negative", age)
    //   age > 150 → throw InvalidAgeException("Age is unrealistic", age)
    //   otherwise → return "Valid age: " + age
    //
    // Input:  -1  → throws InvalidAgeException("Age cannot be negative", -1)
    // Input:  200 → throws InvalidAgeException("Age is unrealistic", 200)
    // Input:  25  → "Valid age: 25"
    // ─────────────────────────────────────────────────────────────
    public static class InvalidAgeException extends RuntimeException {
        // You implement this class!
        // constructor: InvalidAgeException(String message, int age)
        // method: int getAge()
        private final int age;
        public InvalidAgeException(String message, int age) {
            super(message);
            this.age = age;
        }
        public int getAge() { return age; }
    }

    public static String challenge3(int age) {
        if (age < 0) throw new InvalidAgeException("Age cannot be negative", age);
        if (age > 150) throw new InvalidAgeException("Age is unrealistic", age);
        return "Valid age: " + age;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Safely cast an Object to a target type using generics.
    // Return Optional<T>:
    // → cast succeeds → Optional.of(castedValue)
    // → ClassCastException thrown → Optional.empty()
    // → obj is null → Optional.empty()
    //
    // Input:  obj="hello", clazz=String.class    → Optional["hello"]
    // Input:  obj=42,      clazz=String.class    → Optional.empty()
    // Input:  obj=null,    clazz=String.class    → Optional.empty()
    // Input:  obj=42,      clazz=Integer.class   → Optional[42]
    // ─────────────────────────────────────────────────────────────
    public static <T> Optional<T> challenge4(Object obj, Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");
        if (obj == null) return Optional.empty();

        try {
            return Optional.of(clazz.cast(obj));
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Run a list of tasks and collect exception information.
    // For each task return either:
    // → "OK" if no exception
    // → "ERROR: ClassName: message" if exception thrown
    //   (use e.getClass().getSimpleName() for class name)
    //   (use e.getMessage() for message, or "null" if message is null)
    //
    // Input:  [() -> {}, () -> {throw new ArithmeticException("x")},
    //          () -> {throw new NullPointerException()}]
    // Output: ["OK",
    //          "ERROR: ArithmeticException: x",
    //          "ERROR: NullPointerException: null"]
    //
    // Rules:
    // → process ALL tasks even if one throws
    // → preserve original order
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge5(List<Runnable> tasks) {
        if (tasks == null) throw new IllegalArgumentException("Tasks cannot be null");
        List<String> info = new ArrayList<>();
        for (Runnable task : tasks) {
            try {
                task.run();
                info.add("OK");
            } catch (RuntimeException e) {
                info.add("ERROR: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
        return info;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 6–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Exception hierarchy — handle different subtypes differently.
    //
    // Create three classes:
    //   DataException extends Exception
    //     constructor: DataException(String message)
    //
    //   ParseException extends DataException
    //     constructor: ParseException(String message, int lineNumber)
    //     method:      int getLineNumber()
    //
    //   ValidationException extends DataException
    //     constructor: ValidationException(String message, String field)
    //     method:      String getField()
    //
    // Given a list of data strings, validate each one:
    // → string is null → throw ParseException("Null input at line " + index, index)
    // → string is not numeric → throw ParseException("Not a number at line " + index, index)
    // → parsed value < 0 → throw ValidationException("Negative value in field: " + s, s)
    // → valid → add parsed int to result list
    //
    // Catch ParseException and ValidationException SEPARATELY:
    // → ParseException    → add "PARSE_ERROR: " + e.getLineNumber() to errors
    // → ValidationException → add "VALIDATION_ERROR: " + e.getField() to errors
    //
    // Return record DataResult(List<Integer> values, List<String> errors)
    // ─────────────────────────────────────────────────────────────
    public static class DataException extends Exception {
        // You implement this class!
        public DataException(String message) { super(message); }
    }

    public static class ParseException extends DataException {
        // You implement this class!
        private final int lineNumber;
        public ParseException(String message, int lineNumber) {
            super(message);
            this.lineNumber = lineNumber;
        }
        public int getLineNumber() { return lineNumber; }
    }

    public static class ValidationException extends DataException {
        // You implement this class!
        private final String field;
        public ValidationException(String message, String field) {
            super(message);
            this.field = field;
        }
        public String getField() { return field; }
    }

    record DataResult(List<Integer> values, List<String> errors) {}

    public static DataResult challenge6(List<String> data) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        List<Integer> list = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            try {
                String s = data.get(i);
                if (s == null) throw new ParseException("Null input at line " + i, i);

                int parsed = 0;
                try {
                    parsed = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    throw new ParseException("Not a number at line " + i, i);
                }
                if (parsed < 0) throw new ValidationException("Negative value in field: " + s, s);
                list.add(parsed);
            } catch (ParseException e) {
                errors.add("PARSE_ERROR: " + e.getLineNumber());
            } catch (ValidationException e) {
                errors.add("VALIDATION_ERROR: " + e.getField());
            }
        }

        return new DataResult(list, errors);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Traverse an exception cause chain to find the ROOT CAUSE.
    // The root cause is the FIRST exception with NO further cause
    // (getCause() returns null).
    //
    // Input:  RuntimeException → IllegalStateException → IOException
    //         (root = IOException, getCause() = null)
    // Output: IOException instance
    //
    // Input:  single exception with no cause
    // Output: that exception itself
    //
    // Rules:
    // → follow getCause() chain until getCause() == null
    // → return that Throwable
    // → throw IllegalArgumentException if t is null
    // ─────────────────────────────────────────────────────────────
    public static Throwable challenge7(Throwable t) {
        if (t == null) throw new IllegalArgumentException("Throwable cannot be null");
        Throwable e = t;
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Retry a Callable only for SPECIFIC exception types.
    // → maxRetries total attempts
    // → only retry if thrown exception IS an instance of one of retryOn types
    // → if NOT a retryOn type → re-throw immediately (don't retry!)
    // → if all retryOn retries exhausted → throw RuntimeException(
    //      "Failed after " + maxRetries + " attempts", lastException)
    //
    // Input:  task throws IOException (retryOn=[IOException.class]), maxRetries=3
    //   → retries 3 times → throws RuntimeException
    //
    // Input:  task throws IllegalArgumentException (retryOn=[IOException.class])
    //   → NOT retryOn → re-throws IllegalArgumentException immediately!
    //
    // Input:  task succeeds on 2nd try, retryOn=[IOException.class], maxRetries=3
    //   → returns result ✓
    // ─────────────────────────────────────────────────────────────
    @SafeVarargs
    public static <T> T challenge8(Callable<T> task, int maxRetries, Class<? extends Exception>... retryOn) throws Exception {
        if (task      == null) throw new IllegalArgumentException("Task cannot be null");
        if (maxRetries <= 0)   throw new IllegalArgumentException("maxRetries must be positive");
        if (retryOn   == null) throw new IllegalArgumentException("retryOn cannot be null");

        Exception lastException = null;
        for (int attemp = 1; attemp <= maxRetries; attemp++) {
            try {
                return task.call();
            } catch (Exception e) {
                lastException = e;

                boolean shouldRetry = false;
                for (Class<? extends Exception> allowed : retryOn) {
                    if (allowed != null && allowed.isInstance(e)) {
                        shouldRetry = true;
                        break;
                    }
                }

                if (!shouldRetry) throw e;
            }

        }

        throw new RuntimeException("Failed after " + maxRetries + " attempts", lastException);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Validate ALL rules and collect ALL violations before throwing.
    //
    // Create class: AggregateException extends RuntimeException
    //   constructor: AggregateException(List<String> violations)
    //   method:      List<String> getViolations()
    //   message:     violations.size() + " validation error(s)"
    //
    // Validate a password against ALL rules simultaneously:
    // → length < 8           → add "Too short"
    // → no uppercase letter  → add "Missing uppercase"
    // → no digit             → add "Missing digit"
    // → no special character → add "Missing special character"
    //   (special = not letter or digit)
    //
    // If NO violations → return "Password is valid"
    // If violations exist → throw AggregateException(violations)
    //   (throw AFTER collecting ALL violations, not on first failure!)
    //
    // Input:  "hello" → throws AggregateException with 3 violations:
    //   ["Too short","Missing uppercase","Missing digit","Missing special character"]
    // Input:  "Hello1!" → return "Password is valid"
    // ─────────────────────────────────────────────────────────────
    public static class AggregateException extends RuntimeException {
        // You implement this class!
        // constructor: AggregateException(List<String> violations)
        // method: List<String> getViolations()
        public AggregateException(List<String> violations) {
            super(violations.size() + " validation error(s)");
        }
        public List<String> getViolations() { return new ArrayList<>(); }
    }

    public static String challenge9(String password) {
        if (password == null) throw new IllegalArgumentException("Password cannot be null");
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Demonstrate SUPPRESSED exceptions from try-with-resources.
    //
    // Create class: FailingResource implements AutoCloseable
    //   constructor: FailingResource(String name, boolean failOnClose)
    //   method:      void use()
    //     → always adds "used: " + name to shared log
    //   method:      void close()
    //     → always adds "closed: " + name to shared log
    //     → if failOnClose=true → ALSO throws RuntimeException("Close failed: " + name)
    //
    // Open TWO FailingResources in try-with-resources:
    //   r1 = FailingResource("R1", false)
    //   r2 = FailingResource("R2", true)  ← fails on close!
    // Call use() on both.
    // Also throw a primary exception from the try block: new RuntimeException("primary")
    //
    // When BOTH the try block AND close() throw:
    // → primary exception is the main exception!
    // → close() exception is SUPPRESSED (attached to primary!)
    //
    // Return record SuppressedResult(
    //   String primaryMessage,           ← main exception message
    //   List<String> suppressedMessages, ← suppressed exception messages
    //   List<String> log                 ← resource operation log
    // )
    //
    // Expected:
    //   primaryMessage    = "primary"
    //   suppressedMessages= ["Close failed: R2"]
    //   log               = ["used: R1","used: R2","closed: R2","closed: R1"]
    //            (R2 close fails but runs! R1 close still runs after!)
    // ─────────────────────────────────────────────────────────────
    public static class FailingResource implements AutoCloseable {
        // You implement this class!
        // constructor: FailingResource(String name, boolean failOnClose, List<String> log)
        // void use()   → log.add("used: " + name)
        // void close() → log.add("closed: " + name)
        //                if failOnClose → throw new RuntimeException("Close failed: " + name)
        private final String       name;
        private final boolean      failOnClose;
        private final List<String> log;

        public FailingResource(String name, boolean failOnClose, List<String> log) {
            this.name        = name;
            this.failOnClose = failOnClose;
            this.log         = log;
        }
        public void use() { log.add("used: " + name); }

        @Override
        public void close() {
            log.add("closed: " + name);
            if (failOnClose)
                throw new RuntimeException("Close failed: " + name);
        }
    }

    record SuppressedResult(String primaryMessage,
                            List<String> suppressedMessages,
                            List<String> log) {}

    public static SuppressedResult challenge10() {
        List<String> log = new ArrayList<>();
        return new SuppressedResult("", new ArrayList<>(), log);
    }
}