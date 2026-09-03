package dev.perfectbogus.exceptions;

import java.util.*;
import java.util.concurrent.Callable;

public class ExceptionChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Perform safe integer division.
    // If denominator is 0 → catch ArithmeticException → return defaultValue.
    // Otherwise return the result of a / b.
    //
    // Input:  a=10, b=2,  defaultValue=0 → 5
    // Input:  a=10, b=0,  defaultValue=0 → 0   (ArithmeticException caught!)
    // Input:  a=10, b=0,  defaultValue=-1 → -1  (custom default!)
    // ─────────────────────────────────────────────────────────────
    public static int challenge1(int a, int b, int defaultValue) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return defaultValue;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Safely parse a String to Integer.
    // If parsing succeeds → return Optional.of(result).
    // If string is null → return Optional.empty().
    // If string is invalid number → catch NumberFormatException
    //    → return Optional.empty().
    //
    // Input:  "42"    → Optional[42]
    // Input:  "hello" → Optional.empty()
    // Input:  null    → Optional.empty()
    // Input:  "-5"    → Optional[-5]
    // ─────────────────────────────────────────────────────────────
    public static Optional<Integer> challenge2(String s) {
        return Optional.empty();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Validate employee data and throw a custom UNCHECKED exception.
    //
    // Create class InvalidEmployeeException extends RuntimeException
    //   with a constructor: InvalidEmployeeException(String message)
    //
    // Rules:
    // → name is null or blank → throw InvalidEmployeeException("Name cannot be blank")
    // → salary < 0            → throw InvalidEmployeeException("Salary cannot be negative")
    // → both invalid          → throw for name first!
    // → valid input           → return "name/salary" formatted as "Alice/95000.00"
    //
    // Input:  "Alice", 95000.0 → "Alice/95000.00"
    // Input:  null, 95000.0   → throws InvalidEmployeeException("Name cannot be blank")
    // Input:  "Alice", -1.0   → throws InvalidEmployeeException("Salary cannot be negative")
    // ─────────────────────────────────────────────────────────────
    public static class InvalidEmployeeException extends RuntimeException {
        public InvalidEmployeeException(String message) {
            super(message);
        }
    }

    public static String challenge3(String name, double salary) {
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Run a Runnable task and classify the exception it throws.
    // Return a String describing the exception type:
    // → ArithmeticException           → "ARITHMETIC"
    // → NullPointerException          → "NULL"
    // → ArrayIndexOutOfBoundsException→ "ARRAY_INDEX"
    // → NumberFormatException         → "NUMBER_FORMAT"
    // → no exception thrown           → "NONE"
    // → any other exception           → "UNKNOWN"
    //
    // Input:  () -> { int x = 1/0; }           → "ARITHMETIC"
    // Input:  () -> { String s = null; s.length(); } → "NULL"
    // Input:  () -> { System.out.println("ok"); }    → "NONE"
    // ─────────────────────────────────────────────────────────────
    public static String challenge4(Runnable task) {
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Process a list of strings and parse each one to Integer.
    // Collect results and errors separately.
    // Return record ParseResult(List<Integer> values, List<String> errors)
    // → values: successfully parsed integers (in order)
    // → errors: error messages for failed parses: "Invalid: X" (in order)
    //
    // Input:  ["1","two","3","four","5"]
    // Output: ParseResult([1,3,5], ["Invalid: two","Invalid: four"])
    //
    // Rules:
    // → catch NumberFormatException per item
    // → continue processing after each error (don't stop!)
    // ─────────────────────────────────────────────────────────────
    record ParseResult(List<Integer> values, List<String> errors) {}

    public static ParseResult challenge5(List<String> items) {
        if (items == null) throw new IllegalArgumentException("Items cannot be null");
        return new ParseResult(new ArrayList<>(), new ArrayList<>());
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Create a CUSTOM CHECKED exception and use exception chaining.
    //
    // class ConfigurationException extends Exception
    //   constructor: ConfigurationException(String message, Throwable cause)
    //
    // Given a Map<String, String> config and a key:
    // → if key exists AND value is valid integer → return parsed int
    // → if key does not exist → throw ConfigurationException(
    //      "Key not found: " + key, new NoSuchElementException(key))
    // → if value is not a valid integer → throw ConfigurationException(
    //      "Invalid value for key: " + key, original NumberFormatException)
    //
    // Rules:
    // → ConfigurationException is CHECKED → must declare throws!
    // → always chain the original exception as the cause!
    // ─────────────────────────────────────────────────────────────
    public static class ConfigurationException extends Exception {
        public ConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static int challenge6(Map<String, String> config, String key)
            throws ConfigurationException {
        if (config == null || key == null)
            throw new IllegalArgumentException("Config and key cannot be null");
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Implement a resource tracker using try-with-resources.
    // Create class TrackedResource implements AutoCloseable:
    //   → constructor: TrackedResource(String name, List<String> log)
    //     adds "opened: name" to log
    //   → close(): adds "closed: name" to log
    //   → use(): adds "used: name" to log
    //     if name is "broken" → throws IllegalStateException("Resource is broken!")
    //
    // Open TWO resources in one try-with-resources block:
    //   resource1 = TrackedResource("R1", log)
    //   resource2 = TrackedResource("R2", log)
    // Call use() on both inside the block.
    // If resource2 is "broken" → exception thrown but BOTH still closed!
    //
    // Return the log list.
    //
    // Input:  r1="R1", r2="R2" (normal)
    // Output: ["opened: R1","opened: R2","used: R1","used: R2","closed: R2","closed: R1"]
    //
    // Input:  r1="R1", r2="broken" (broken resource)
    // Output: ["opened: R1","opened: broken","used: R1","closed: broken","closed: R1"]
    //         ← broken.use() throws → skips used: broken → but both still closed!
    // ─────────────────────────────────────────────────────────────
    public static class TrackedResource implements AutoCloseable {
        private final String name;
        private final List<String> log;

        public TrackedResource(String name, List<String> log) {
            this.name = name;
            this.log  = log;
            log.add("opened: " + name);
        }

        public void use() {
            if (name.equals("broken"))
                throw new IllegalStateException("Resource is broken!");
            log.add("used: " + name);
        }

        @Override
        public void close() {
            log.add("closed: " + name);
        }
    }

    public static List<String> challenge7(String r1Name, String r2Name) {
        List<String> log = new ArrayList<>();
        return log;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Safely access a list element by index.
    // Return Optional<T>:
    // → valid index   → Optional.of(element)
    // → invalid index → catch IndexOutOfBoundsException
    //                    → return Optional.empty()
    // → null list     → return Optional.empty() (no exception!)
    //
    // Input:  list=["a","b","c"], index=1   → Optional["b"]
    // Input:  list=["a","b","c"], index=10  → Optional.empty()
    // Input:  list=["a","b","c"], index=-1  → Optional.empty()
    // Input:  null, 0                        → Optional.empty()
    // ─────────────────────────────────────────────────────────────
    public static <T> Optional<T> challenge8(List<T> list, int index) {
        return Optional.empty();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Execute a task with retry logic.
    // Use Callable<T> (can throw Exception).
    // Try to execute up to maxRetries times.
    // → if task succeeds → return result immediately!
    // → if task throws   → retry (up to maxRetries total attempts)
    // → if ALL attempts fail → throw RuntimeException(
    //      "All " + maxRetries + " attempts failed", lastException)
    //
    // Input:  task always succeeds          → return result
    // Input:  task fails twice then succeeds → return result on 3rd try
    // Input:  task always fails, maxRetries=3
    //          → throw RuntimeException("All 3 attempts failed", cause)
    //
    // Rules:
    // → attempt count starts at 1
    // → always preserve last exception as the cause!
    // ─────────────────────────────────────────────────────────────
    public static <T> T challenge9(Callable<T> task, int maxRetries) {
        if (task == null)     throw new IllegalArgumentException("Task cannot be null");
        if (maxRetries <= 0)  throw new IllegalArgumentException("maxRetries must be positive");
        return null;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Verify that finally ALWAYS runs using an AtomicBoolean flag.
    // Execute a Runnable task inside a try-finally block.
    // Set finallyRan[0] = true in the finally block.
    //
    // Return record FinallyResult(Object result, String exceptionType, boolean finallyRan)
    // → result:        return value if task completed normally ("COMPLETED")
    //                  null if exception was thrown
    // → exceptionType: null if no exception
    //                  simple class name if exception thrown (e.g. "ArithmeticException")
    // → finallyRan:    ALWAYS true! (finally always runs!)
    //
    // Input:  () -> {} (no exception)
    // Output: FinallyResult("COMPLETED", null, true)
    //
    // Input:  () -> { throw new RuntimeException("boom"); }
    // Output: FinallyResult(null, "RuntimeException", true)
    //
    // Rules:
    // → catch ALL exceptions (catch Exception!)
    // → finally block MUST set finallyRan[0] = true
    // → return the record after the try-finally
    // ─────────────────────────────────────────────────────────────
    record FinallyResult(String result, String exceptionType, boolean finallyRan) {}

    public static FinallyResult challenge10(Runnable task) {
        if (task == null) throw new IllegalArgumentException("Task cannot be null");
        return new FinallyResult(null, null, false);
    }
}