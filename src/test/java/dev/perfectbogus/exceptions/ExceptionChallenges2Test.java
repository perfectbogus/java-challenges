package dev.perfectbogus.exceptions;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Wrap exception with context
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void noException() {
            assertEquals("OK", ExceptionChallenges2.challenge1(() -> {}, "FileWriter"));
        }

        @Test
        void wrapsWithContext() {
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    ExceptionChallenges2.challenge1(
                            () -> { throw new IllegalStateException("disk full"); },
                            "FileWriter"));

            assertEquals("FileWriter: disk full", ex.getMessage());
            assertInstanceOf(IllegalStateException.class, ex.getCause());
        }

        @Test
        void differentContext() {
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    ExceptionChallenges2.challenge1(
                            () -> { throw new NullPointerException("ref"); },
                            "Parser"));

            assertEquals("Parser: ref", ex.getMessage());
        }

        @Test
        void nullMessageException() {
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    ExceptionChallenges2.challenge1(
                            () -> { throw new NullPointerException(null); },
                            "Parser"));

            assertEquals("Parser: null", ex.getMessage());
        }

        @Test
        void causeIsPreserved() {
            IllegalStateException original = new IllegalStateException("original");
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    ExceptionChallenges2.challenge1(() -> { throw original; }, "Ctx"));

            assertSame(original, ex.getCause());
        }

        @Test
        void nullTask() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge1(null, "context"));
        }

        @Test
        void nullContext() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge1(() -> {}, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — MissingKeyException on missing map key
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void existingKey() {
            Map<String, Integer> map = Map.of("a", 1, "b", 2);
            assertEquals(1, ExceptionChallenges2.challenge2(map, "a"));
        }

        @Test
        void missingKeyThrows() {
            ExceptionChallenges2.MissingKeyException ex = assertThrows(
                    ExceptionChallenges2.MissingKeyException.class,
                    () -> ExceptionChallenges2.challenge2(Map.of("a", 1), "c"));

            assertEquals("c",                 ex.getKey());
            assertEquals("Key not found: c",  ex.getMessage());
        }

        @Test
        void missingKeyContainsKeyInMessage() {
            ExceptionChallenges2.MissingKeyException ex = assertThrows(
                    ExceptionChallenges2.MissingKeyException.class,
                    () -> ExceptionChallenges2.challenge2(Map.of(), "myKey"));

            assertEquals("myKey", ex.getKey());
            assertTrue(ex.getMessage().contains("myKey"));
        }

        @Test
        void isRuntimeException() {
            assertInstanceOf(RuntimeException.class,
                    new ExceptionChallenges2.MissingKeyException("x"));
        }

        @Test
        void nullMap() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge2(null, "key"));
        }

        @Test
        void nullKey() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge2(Map.of(), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — InvalidAgeException with getAge()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void validAge() {
            assertEquals("Valid age: 25", ExceptionChallenges2.challenge3(25));
        }

        @Test
        void zeroIsValid() {
            assertEquals("Valid age: 0", ExceptionChallenges2.challenge3(0));
        }

        @Test
        void negativeAgeThrows() {
            ExceptionChallenges2.InvalidAgeException ex = assertThrows(
                    ExceptionChallenges2.InvalidAgeException.class,
                    () -> ExceptionChallenges2.challenge3(-1));

            assertEquals(-1,                        ex.getAge());
            assertEquals("Age cannot be negative",  ex.getMessage());
        }

        @Test
        void tooOldThrows() {
            ExceptionChallenges2.InvalidAgeException ex = assertThrows(
                    ExceptionChallenges2.InvalidAgeException.class,
                    () -> ExceptionChallenges2.challenge3(200));

            assertEquals(200,                   ex.getAge());
            assertEquals("Age is unrealistic",  ex.getMessage());
        }

        @Test
        void boundary150IsValid() {
            assertEquals("Valid age: 150", ExceptionChallenges2.challenge3(150));
        }

        @Test
        void boundary151Throws() {
            assertThrows(ExceptionChallenges2.InvalidAgeException.class,
                    () -> ExceptionChallenges2.challenge3(151));
        }

        @Test
        void isRuntimeException() {
            assertInstanceOf(RuntimeException.class,
                    new ExceptionChallenges2.InvalidAgeException("msg", 10));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Safe cast to Optional
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void successfulCastString() {
            Optional<String> result = ExceptionChallenges2.challenge4("hello", String.class);
            assertEquals(Optional.of("hello"), result);
        }

        @Test
        void failedCast() {
            Optional<String> result = ExceptionChallenges2.challenge4(42, String.class);
            assertTrue(result.isEmpty());
        }

        @Test
        void nullObject() {
            Optional<String> result = ExceptionChallenges2.challenge4(null, String.class);
            assertTrue(result.isEmpty());
        }

        @Test
        void successfulCastInteger() {
            Optional<Integer> result = ExceptionChallenges2.challenge4(42, Integer.class);
            assertEquals(Optional.of(42), result);
        }

        @Test
        void nullClass() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge4("hello", null));
        }

        @Test
        void castToList() {
            List<String> list = List.of("a","b");
            Optional<List> result = ExceptionChallenges2.challenge4(list, List.class);
            assertTrue(result.isPresent());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Collect exception info from tasks
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<String> result = ExceptionChallenges2.challenge5(List.of(
                    () -> {},
                    () -> { throw new ArithmeticException("x"); },
                    () -> { throw new NullPointerException(); }
            ));

            assertEquals("OK",                            result.get(0));
            assertEquals("ERROR: ArithmeticException: x", result.get(1));
            assertEquals("ERROR: NullPointerException: null", result.get(2));
        }

        @Test
        void allOk() {
            List<String> result = ExceptionChallenges2.challenge5(
                    List.of(() -> {}, () -> {}, () -> {}));

            assertEquals(List.of("OK","OK","OK"), result);
        }

        @Test
        void allError() {
            List<String> result = ExceptionChallenges2.challenge5(List.of(
                    () -> { throw new RuntimeException("boom"); }
            ));

            assertEquals(1, result.size());
            assertTrue(result.get(0).startsWith("ERROR:"));
            assertTrue(result.get(0).contains("boom"));
        }

        @Test
        void emptyList() {
            assertTrue(ExceptionChallenges2.challenge5(List.of()).isEmpty());
        }

        @Test
        void orderPreserved() {
            List<String> result = ExceptionChallenges2.challenge5(List.of(
                    () -> { throw new RuntimeException("first"); },
                    () -> {},
                    () -> { throw new RuntimeException("third"); }
            ));

            assertTrue(result.get(0).contains("first"));
            assertEquals("OK", result.get(1));
            assertTrue(result.get(2).contains("third"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Exception hierarchy ParseException/ValidationException
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void allValid() {
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(List.of("1","2","3"));

            assertEquals(List.of(1,2,3), result.values());
            assertTrue(result.errors().isEmpty());
        }

        @Test
        void notNumericIsParseError() {
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(List.of("abc"));

            assertTrue(result.values().isEmpty());
            assertEquals(1, result.errors().size());
            assertTrue(result.errors().get(0).startsWith("PARSE_ERROR:"));
        }

        @Test
        void nullIsParseError() {
            List<String> data = new ArrayList<>();
            data.add(null);
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(data);

            assertTrue(result.errors().get(0).startsWith("PARSE_ERROR:"));
        }

        @Test
        void negativeIsValidationError() {
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(List.of("-5"));

            assertTrue(result.errors().get(0).startsWith("VALIDATION_ERROR:"));
            assertTrue(result.errors().get(0).contains("-5"));
        }

        @Test
        void mixedInputs() {
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(List.of("1","abc","-3","4"));

            assertEquals(List.of(1,4), result.values());
            assertEquals(2, result.errors().size());
        }

        @Test
        void parseErrorContainsLineNumber() {
            ExceptionChallenges2.DataResult result =
                    ExceptionChallenges2.challenge6(List.of("ok","bad"));

            // index 1 is "bad" → PARSE_ERROR: 1
            assertTrue(result.errors().get(0).contains("0"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Find root cause in exception chain
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void singleException() {
            RuntimeException ex = new RuntimeException("only");
            assertSame(ex, ExceptionChallenges2.challenge7(ex));
        }

        @Test
        void twoLevelChain() {
            IOException root    = new IOException("root");
            RuntimeException ex = new RuntimeException("wrapper", root);

            assertSame(root, ExceptionChallenges2.challenge7(ex));
        }

        @Test
        void threeLevelChain() {
            IOException             root    = new IOException("root");
            IllegalStateException   middle  = new IllegalStateException("middle", root);
            RuntimeException        top     = new RuntimeException("top", middle);

            assertSame(root, ExceptionChallenges2.challenge7(top));
        }

        @Test
        void rootHasNoCause() {
            Throwable root = ExceptionChallenges2.challenge7(
                    new RuntimeException("a", new RuntimeException("b",
                            new RuntimeException("c"))));

            assertNull(root.getCause());
            assertEquals("c", root.getMessage());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Retry only on specific exception types
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void successFirstTry() throws Exception {
            assertEquals("ok",
                    ExceptionChallenges2.challenge8(() -> "ok", 3, IOException.class));
        }

        @Test
        void retriesOnSpecificType() throws Exception {
            int[] count = {0};
            String result = ExceptionChallenges2.challenge8(() -> {
                count[0]++;
                if (count[0] < 3) throw new IOException("retry me");
                return "done";
            }, 5, IOException.class);

            assertEquals("done", result);
            assertEquals(3, count[0]);
        }

        @Test
        void doesNotRetryOnWrongType() {
            assertThrows(IllegalArgumentException.class, () ->
                    ExceptionChallenges2.challenge8(
                            () -> { throw new IllegalArgumentException("no retry"); },
                            3, IOException.class));
        }

        @Test
        void allRetriesExhausted() {
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    ExceptionChallenges2.challenge8(
                            () -> { throw new IOException("fail"); },
                            3, IOException.class));

            assertTrue(ex.getMessage().contains("3"));
            assertInstanceOf(IOException.class, ex.getCause());
        }

        @Test
        void multipleRetryTypes() throws Exception {
            int[] count = {0};
            String result = ExceptionChallenges2.challenge8(() -> {
                count[0]++;
                if (count[0] == 1) throw new IOException("io");
                if (count[0] == 2) throw new IllegalStateException("state");
                return "success";
            }, 5, IOException.class, IllegalStateException.class);

            assertEquals("success", result);
        }

        @Test
        void nullTask() {
            assertThrows(IllegalArgumentException.class, () ->
                    ExceptionChallenges2.challenge8(null, 3, IOException.class));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — AggregateException collecting ALL violations
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void validPassword() {
            assertEquals("Password is valid",
                    ExceptionChallenges2.challenge9("Hello1!World"));
        }

        @Test
        void collectsAllViolations() {
            ExceptionChallenges2.AggregateException ex = assertThrows(
                    ExceptionChallenges2.AggregateException.class,
                    () -> ExceptionChallenges2.challenge9("hello"));

            List<String> v = ex.getViolations();
            assertTrue(v.contains("Too short"));
            assertTrue(v.contains("Missing uppercase"));
            assertTrue(v.contains("Missing digit"));
            assertTrue(v.contains("Missing special character"));
            assertEquals(4, v.size());
        }

        @Test
        void onlyTooShort() {
            ExceptionChallenges2.AggregateException ex = assertThrows(
                    ExceptionChallenges2.AggregateException.class,
                    () -> ExceptionChallenges2.challenge9("Hi1!"));

            assertTrue(ex.getViolations().contains("Too short"));
        }

        @Test
        void messageContainsCount() {
            ExceptionChallenges2.AggregateException ex = assertThrows(
                    ExceptionChallenges2.AggregateException.class,
                    () -> ExceptionChallenges2.challenge9("hello"));

            assertTrue(ex.getMessage().contains("4")); // 4 violations
        }

        @Test
        void isRuntimeException() {
            assertInstanceOf(RuntimeException.class,
                    new ExceptionChallenges2.AggregateException(List.of("x")));
        }

        @Test
        void noUppercaseOnly() {
            ExceptionChallenges2.AggregateException ex = assertThrows(
                    ExceptionChallenges2.AggregateException.class,
                    () -> ExceptionChallenges2.challenge9("hello1!world"));

            assertTrue(ex.getViolations().contains("Missing uppercase"));
            assertFalse(ex.getViolations().contains("Too short")); // length OK
        }

        @Test
        void nullPassword() {
            assertThrows(IllegalArgumentException.class,
                    () -> ExceptionChallenges2.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Suppressed exceptions from try-with-resources
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void primaryMessageCorrect() {
            ExceptionChallenges2.SuppressedResult result =
                    ExceptionChallenges2.challenge10();

            assertEquals("primary", result.primaryMessage());
        }

        @Test
        void suppressedMessageCorrect() {
            ExceptionChallenges2.SuppressedResult result =
                    ExceptionChallenges2.challenge10();

            assertEquals(1, result.suppressedMessages().size());
            assertEquals("Close failed: R2", result.suppressedMessages().get(0));
        }

        @Test
        void logContainsAllOperations() {
            ExceptionChallenges2.SuppressedResult result =
                    ExceptionChallenges2.challenge10();

            List<String> log = result.log();
            assertTrue(log.contains("used: R1"));
            assertTrue(log.contains("used: R2"));
            assertTrue(log.contains("closed: R2"));
            assertTrue(log.contains("closed: R1"));
        }

        @Test
        void closeOrderIsReversed() {
            ExceptionChallenges2.SuppressedResult result =
                    ExceptionChallenges2.challenge10();

            List<String> log = result.log();
            int closeR2 = log.indexOf("closed: R2");
            int closeR1 = log.indexOf("closed: R1");
            assertTrue(closeR2 < closeR1); // R2 closed before R1!
        }

        @Test
        void suppressedNotPrimary() {
            ExceptionChallenges2.SuppressedResult result =
                    ExceptionChallenges2.challenge10();

            // Primary is "primary", suppressed is the close failure
            assertNotEquals(result.primaryMessage(),
                    result.suppressedMessages().get(0));
        }
    }
}