package dev.perfectbogus.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumIntermediateChallenge {

    // Enums and a record used by the challenges below.

    public enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
    }

    public enum HttpStatus {
        OK(200), CREATED(201), NOT_FOUND(404), FORBIDDEN(403), SERVER_ERROR(500);

        private final int code;

        HttpStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum Operation {
        PLUS("+") {
            public int apply(int a, int b) {
                return a + b;
            }
        },
        MINUS("-") {
            public int apply(int a, int b) {
                return a - b;
            }
        },
        TIMES("*") {
            public int apply(int a, int b) {
                return a * b;
            }
        },
        DIVIDE("/") {
            public int apply(int a, int b) {
                return a / b;
            }
        };

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public abstract int apply(int a, int b);

        public String getSymbol() {
            return symbol;
        }
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Permission {
        READ, WRITE, EXECUTE, DELETE
    }

    public record Task(String name, Priority priority) {
    }

    // CHALLENGE 1
    // Returns whether day falls on a weekend (Saturday or Sunday).
    public static boolean isWeekendDay(Weekday day) {
        return day.isWeekend();
    }

    // CHALLENGE 2
    // Searches every HttpStatus constant for one whose code equals code,
    // returning it wrapped in an Optional, or Optional.empty() if none
    // match.
    public static Optional<HttpStatus> findStatusByCode(int code) {
        for (HttpStatus h : HttpStatus.values()) {
            if (h.getCode() == code) {
                return Optional.of(h);
            }
        }
        return Optional.empty();
    }

    // CHALLENGE 3
    // Applies the given arithmetic operation to a and b.
    public static int applyOperation(Operation op, int a, int b) {
        return switch (op) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case TIMES -> a * b;
            case DIVIDE -> {
                if (b != 0) {
                    yield a / b;
                } else {
                    throw new ArithmeticException("Divisor cannot be null");
                }
            }
            default -> throw new IllegalArgumentException("Op does not exists " + op);
        };
    }

    // CHALLENGE 4
    // Classifies status into "Success" (OK or CREATED), "Client Error"
    // (NOT_FOUND or FORBIDDEN), or "Server Error" (SERVER_ERROR).
    public static String describeStatus(HttpStatus status) {
        return switch (status) {
            case OK, CREATED -> "Success";
            case NOT_FOUND, FORBIDDEN -> "Client Error";
            case SERVER_ERROR -> "Server Error";
            default -> throw new IllegalArgumentException("HttpStatus does not exists: " + status);
        };
    }

    // CHALLENGE 5
    // Returns a map from each Weekday that appears in days to how many
    // times it appears. Iterating the returned map's keys must visit them
    // in Weekday's natural (declaration) order, regardless of the order
    // they appeared in days.
    public static EnumMap<Weekday, Integer> countOccurrences(List<Weekday> days) {
        return days.stream().collect(Collectors.toMap(
                Function.identity(),
                e -> 1,
                Integer::sum,
                () -> new EnumMap<>(Weekday.class)
        ));
    }

    // CHALLENGE 6
    // Returns the highest-severity priority present in priorities
    // (HIGH outranks MEDIUM, which outranks LOW), based on Priority's
    // natural ordering.
    public static Priority highestPriority(List<Priority> priorities) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns every Permission except those listed in excluded.
    public static EnumSet<Permission> allPermissionsExcept(Permission... excluded) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns whether granted contains every permission in required.
    public static boolean hasAllPermissions(EnumSet<Permission> granted, EnumSet<Permission> required) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Attempts to parse name as a Weekday constant (matching
    // Enum.valueOf's exact-name rules). Returns Optional.empty() if name
    // does not match any constant, instead of throwing.
    public static Optional<Weekday> parseWeekdaySafely(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the Weekday that follows day, wrapping from SUNDAY back
    // around to MONDAY.
    public static Weekday nextWeekday(Weekday day) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns the sum of the ordinal() values of every priority in
    // priorities.
    public static int sumOrdinalValues(List<Priority> priorities) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns the symbol associated with op (for example "+" for PLUS).
    public static String symbolForOperation(Operation op) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Groups tasks by their priority. Iterating the returned map's keys
    // must visit priorities in their natural (declaration) order,
    // regardless of the order tasks appeared in the input.
    public static Map<Priority, List<Task>> groupTasksByPriority(List<Task> tasks) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns a new list containing every status in statuses, sorted by
    // their numeric code in ascending order.
    public static List<HttpStatus> sortStatusesByCode(List<HttpStatus> statuses) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns a list of "<name>: <symbol>" strings (for example
    // "PLUS: +"), one for every Operation constant, in declaration
    // order.
    public static List<String> describeAllOperations() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
