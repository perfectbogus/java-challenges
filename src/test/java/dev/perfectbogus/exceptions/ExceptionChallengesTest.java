package dev.perfectbogus.exceptions;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Safe division with default value
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void normalDivision() {
            assertEquals(5, ExceptionChallenges.challenge1(10, 2, 0));
        }

        @Test
        void divisionByZeroReturnsDefault() {
            assertEquals(0, ExceptionChallenges.challenge1(10, 0, 0));
        }

        @Test
        void divisionByZeroCustomDefault() {
            assertEquals(-1, ExceptionChallenges.challenge1(10, 0, -1));
        }

        @Test
        void divisionByZeroPositiveDefault() {
            assertEquals(99, ExceptionChallenges.challenge1(5, 0, 99));
        }

        @Test
        void zeroDividend() {
            assertEquals(0, ExceptionChallenges.challenge1(0, 5, -1));
        }

        @Test
        void negativeResult() {
            assertEquals(-3, ExceptionChallenges.challenge1(-9, 3, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Safe String to Integer parsing
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void validInteger() {
            assertEquals(Optional.of(42), ExceptionChallenges.challenge2("42"));
        }

        @Test
        void invalidString() {
            assertTrue(ExceptionChallenges.challenge2("hello").isEmpty());
        }

        @Test
        void nullString() {
            assertTrue(ExceptionChallenges.challenge2(null).isEmpty());
        }

        @Test
        void negativeInteger() {
            assertEquals(Optional.of(-5), ExceptionChallenges.challenge2("-5"));
        }

        @Test
        void zero() {
            assertEquals(Optional.of(0), ExceptionChallenges.challenge2("0"));
        }

        @Test
        void emptyString() {
            assertTrue(ExceptionChallenges.challenge2("").isEmpty());
        }

        @Test
        void floatString() {
            assertTrue(ExceptionChallenges.challenge2("3.14").isEmpty());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Custom unchecked exception validation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void validEmployee() {
            assertEquals("Alice/95000.00",
                    ExceptionChallenges.challenge3("Alice", 95000.0));
        }

        @Test
        void nullNameThrows() {
            ExceptionChallenges.InvalidEmployeeException ex = assertThrows(
                    ExceptionChallenges.InvalidEmployeeException.class,
                    () -> ExceptionChallenges.challenge3(null, 95000.0));
            assertEquals("Name cannot be blank", ex.getMessage());
        }

        @Test
        void blankNameThrows() {
            assertThrows(ExceptionChallenges.InvalidEmployeeException.class,
                    () -> ExceptionChallenges.challenge3("  ", 95000.0));
        }

        @Test
        void negativeSalaryThrows() {
            ExceptionChallenges.InvalidEmployeeException ex = assertThrows(
                    ExceptionChallenges.InvalidEmployeeException.class,
                    () -> ExceptionChallenges.challenge3("Alice", -1.0));
            assertEquals("Salary cannot be negative", ex.getMessage());
        }

        @Test
        void zeroSalaryIsValid() {
            assertEquals("Bob/0.00",
                    ExceptionChallenges.challenge3("Bob", 0.0));
        }

        @Test
        void nullNameCheckedBeforeSalary() {
            // both invalid → name checked first!
            ExceptionChallenges.InvalidEmployeeException ex = assertThrows(
                    ExceptionChallenges.InvalidEmployeeException.class,
                    () -> ExceptionChallenges.challenge3(null, -1.0));
            assertEquals("Name cannot be blank", ex.getMessage());
        }

        @Test
        void isRuntimeException() {
            assertInstanceOf(RuntimeException.class,
                    new ExceptionChallenges.InvalidEmployeeException("test"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Classify exception type from Runnable
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void arithmeticException() {
            assertEquals("ARITHMETIC",
                    ExceptionChallenges.challenge4(() -> { int x = 1 / 0; }));
        }

        @Test
        void nullPointerException() {
            assertEquals("NULL",
                    ExceptionChallenges.challenge4(() -> {
                        String s = null;
                        s.length();
                    }));
        }

        @Test
        void arrayIndexException() {
            assertEquals("ARRAY_INDEX",
                    ExceptionChallenges.challenge4(() -> {
                        int[] arr = new int[3];
                        int x = arr[10];
                    }));
        }

        @Test
        void numberFormatException() {
            assertEquals("NUMBER_FORMAT",
                    ExceptionChallenges.challenge4(() -> Integer.parseInt("hello")));
        }

        @Test
        void noException() {
            assertEquals("NONE",
                    ExceptionChallenges.challenge4(() -> System.out.println("ok")));
        }

        @Test
        void unknownException() {
            assertEquals("UNKNOWN",
                    ExceptionChallenges.challenge4(() -> {
                        throw new UnsupportedOperationException("test");
                    }));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Process list, collect values and errors
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            ExceptionChallenges.ParseResult result =
                    ExceptionChallenges.challenge5(List.of("1","two","3","four","5"));

            assertEquals(List.of(1,3,5), result.values());
            assertEquals(List.of("Invalid: two","Invalid: four"), result.errors());
        }

        @Test
        void allValid() {
            ExceptionChallenges.ParseResult result =
                    ExceptionChallenges.challenge5(List.of("1","2","3"));

            assertEquals(List.of(1,2,3), result.values());
            assertTrue(result.errors().isEmpty());
        }

        @Test
        void allInvalid() {
            ExceptionChallenges.ParseResult result =
                    ExceptionChallenges.challenge5(List.of("a","b","c"));

            assertTrue(result.values().isEmpty());
            assertEquals(3, result.errors().size());
            assertTrue(result.errors().get(0).startsWith("Invalid: "));
        }

        @Test
        void emptyList() {
            ExceptionChallenges.ParseResult result =
                    ExceptionChallenges.challenge5(List.of());

            assertTrue(result.values().isEmpty());
            assertTrue(result.errors().isEmpty());
        }

        @Test
        void negativeNumbers() {
            ExceptionChallenges.ParseResult result =
                    ExceptionChallenges.challenge5(List.of("-1","-2","bad"));

            assertEquals(List.of(-1,-2), result.values());
            assertEquals(List.of("Invalid: bad"), result.errors());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Custom checked exception with chaining
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void validKey() throws ExceptionChallenges.ConfigurationException {
            Map<String, String> config = Map.of("timeout", "30", "port", "8080");
            assertEquals(30, ExceptionChallenges.challenge6(config, "timeout"));
            assertEquals(8080, ExceptionChallenges.challenge6(config, "port"));
        }

        @Test
        void keyNotFound() {
            Map<String, String> config = Map.of("timeout", "30");
            ExceptionChallenges.ConfigurationException ex = assertThrows(
                    ExceptionChallenges.ConfigurationException.class,
                    () -> ExceptionChallenges.challenge6(config, "missing"));

            assertTrue(ex.getMessage().contains("missing"));
            assertNotNull(ex.getCause()); // ← cause must be set!
            assertInstanceOf(NoSuchElementException.class, ex.getCause());
        }

        @Test
        void invalidIntegerValue() {
            Map<String, String> config = Map.of("timeout", "not-a-number");
            ExceptionChallenges.ConfigurationException ex = assertThrows(
                    ExceptionChallenges.ConfigurationException.class,
                    () -> ExceptionChallenges.challenge6(config, "timeout"));

            assertTrue(ex.getMessage().contains("timeout"));
            assertNotNull(ex.getCause()); // ← cause must be set!
            assertInstanceOf(NumberFormatException.class, ex.getCause());
        }

        @Test
        void isCheckedException() {
            assertInstanceOf(Exception.class,
                    new ExceptionChallenges.ConfigurationException("test",
                            new RuntimeException()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges.challenge6(null, "key"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — try-with-resources and AutoCloseable
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void normalExecution() {
            List<String> log = ExceptionChallenges.challenge7("R1", "R2");

            assertEquals("opened: R1",  log.get(0));
            assertEquals("opened: R2",  log.get(1));
            assertEquals("used: R1",    log.get(2));
            assertEquals("used: R2",    log.get(3));
            assertEquals("closed: R2",  log.get(4)); // ← closed in REVERSE order!
            assertEquals("closed: R1",  log.get(5));
            assertEquals(6, log.size());
        }

        @Test
        void brokenResourceStillClosesBoth() {
            List<String> log = ExceptionChallenges.challenge7("R1", "broken");

            assertTrue(log.contains("opened: R1"));
            assertTrue(log.contains("opened: broken"));
            assertTrue(log.contains("used: R1"));
            assertFalse(log.contains("used: broken")); // ← broken threw before use logged!
            assertTrue(log.contains("closed: broken")); // ← still closed!
            assertTrue(log.contains("closed: R1"));     // ← still closed!
        }

        @Test
        void closeOrderIsReverseOfOpen() {
            List<String> log = ExceptionChallenges.challenge7("R1", "R2");

            int openR1  = log.indexOf("opened: R1");
            int openR2  = log.indexOf("opened: R2");
            int closeR2 = log.indexOf("closed: R2");
            int closeR1 = log.indexOf("closed: R1");

            // R1 opened first, R2 opened second
            assertTrue(openR1 < openR2);
            // R2 closed first (LIFO!), R1 closed second
            assertTrue(closeR2 < closeR1);
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Safe list index access with Optional
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void validIndex() {
            Optional<String> result = ExceptionChallenges.challenge8(
                    List.of("a","b","c"), 1);
            assertEquals(Optional.of("b"), result);
        }

        @Test
        void indexTooLarge() {
            Optional<String> result = ExceptionChallenges.challenge8(
                    List.of("a","b","c"), 10);
            assertTrue(result.isEmpty());
        }

        @Test
        void negativeIndex() {
            Optional<String> result = ExceptionChallenges.challenge8(
                    List.of("a","b","c"), -1);
            assertTrue(result.isEmpty());
        }

        @Test
        void nullList() {
            Optional<String> result = ExceptionChallenges.challenge8(null, 0);
            assertTrue(result.isEmpty());
        }

        @Test
        void firstElement() {
            Optional<Integer> result = ExceptionChallenges.challenge8(
                    List.of(10,20,30), 0);
            assertEquals(Optional.of(10), result);
        }

        @Test
        void lastElement() {
            Optional<Integer> result = ExceptionChallenges.challenge8(
                    List.of(10,20,30), 2);
            assertEquals(Optional.of(30), result);
        }

        @Test
        void emptyList() {
            Optional<String> result = ExceptionChallenges.challenge8(
                    List.of(), 0);
            assertTrue(result.isEmpty());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Retry logic with exception chaining
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void successOnFirstTry() throws Exception {
            assertEquals("ok", ExceptionChallenges.challenge9(() -> "ok", 3));
        }

        @Test
        void successAfterRetries() throws Exception {
            int[] attempts = {0};
            String result = ExceptionChallenges.challenge9(() -> {
                attempts[0]++;
                if (attempts[0] < 3) throw new RuntimeException("not yet!");
                return "success";
            }, 5);
            assertEquals("success", result);
            assertEquals(3, attempts[0]); // ← succeeded on 3rd attempt!
        }

        @Test
        void allAttemptsFailThrows() {
            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> ExceptionChallenges.challenge9(
                            () -> { throw new IllegalStateException("always fails"); }, 3));

            assertTrue(ex.getMessage().contains("3")); // ← message mentions retry count!
            assertNotNull(ex.getCause()); // ← cause preserved!
            assertInstanceOf(IllegalStateException.class, ex.getCause());
        }

        @Test
        void singleRetryFails() {
            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> ExceptionChallenges.challenge9(
                            () -> { throw new RuntimeException("fail"); }, 1));

            assertTrue(ex.getMessage().contains("1"));
        }

        @Test
        void returnsNullResult() throws Exception {
            assertNull(ExceptionChallenges.challenge9(() -> null, 3));
        }

        @Test
        void nullTask() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges.challenge9(null, 3));
        }

        @Test
        void invalidMaxRetries() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges.challenge9(() -> "ok", 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Finally always runs
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void noException() {
            ExceptionChallenges.FinallyResult result =
                    ExceptionChallenges.challenge10(() -> {});

            assertEquals("COMPLETED", result.result());
            assertNull(result.exceptionType());
            assertTrue(result.finallyRan()); // ← always true!
        }

        @Test
        void withRuntimeException() {
            ExceptionChallenges.FinallyResult result =
                    ExceptionChallenges.challenge10(
                            () -> { throw new RuntimeException("boom"); });

            assertNull(result.result());
            assertEquals("RuntimeException", result.exceptionType());
            assertTrue(result.finallyRan()); // ← finally STILL ran!
        }

        @Test
        void withArithmeticException() {
            ExceptionChallenges.FinallyResult result =
                    ExceptionChallenges.challenge10(() -> { int x = 1/0; });

            assertNull(result.result());
            assertEquals("ArithmeticException", result.exceptionType());
            assertTrue(result.finallyRan());
        }

        @Test
        void withNullPointerException() {
            ExceptionChallenges.FinallyResult result =
                    ExceptionChallenges.challenge10(() -> {
                        String s = null;
                        s.length();
                    });

            assertNull(result.result());
            assertEquals("NullPointerException", result.exceptionType());
            assertTrue(result.finallyRan());
        }

        @Test
        void finallyAlwaysTrue() {
            // Run multiple times → finally always ran!
            for (int i = 0; i < 5; i++) {
                ExceptionChallenges.FinallyResult r =
                        ExceptionChallenges.challenge10(() -> {
                            if (Math.random() > 0.5) throw new RuntimeException();
                        });
                assertTrue(r.finallyRan(), "Finally must ALWAYS run!");
            }
        }

        @Test
        void nullTask() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges.challenge10(null));
        }
    }
}