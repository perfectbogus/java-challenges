package dev.perfectbogus.optionals;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalIntermediateChallenge {

    // CHALLENGE 1
    // Attempts to parse input as an Integer. Returns an Optional containing
    // the parsed value if input is a valid integer, or Optional.empty() if
    // parsing fails for any reason.
    public static Optional<Integer> parseIntSafely(String input) {
        try {
            int i = Integer.parseInt(input);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // CHALLENGE 2
    // Returns the uppercased value inside optional if present, or the
    // literal string "EMPTY" if it is absent.
    public static String describe(Optional<String> optional) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a, if it is present and not blank. Otherwise returns b, if
    // it is present and not blank. Otherwise returns Optional.empty().
    // A blank string is one that is empty or contains only whitespace.
    public static Optional<String> firstNonBlank(Optional<String> a, Optional<String> b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Looks up key in aliasMap to find an intermediate key, then looks
    // that intermediate key up in valueMap. Returns the final value if
    // both lookups succeed, or Optional.empty() if either lookup fails
    // to find anything.
    public static Optional<Integer> chainLookup(Map<String, String> aliasMap, Map<String, Integer> valueMap, String key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the sum of the values held by every present Optional in the
    // list, ignoring any that are empty.
    public static int sumPresentValues(List<Optional<Integer>> optionals) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // If optional is present, appends its value to presentLog. Otherwise,
    // appends the literal string "empty" to emptyLog.
    public static void logPresenceOutcome(Optional<String> optional, List<String> presentLog, List<String> emptyLog) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns an Optional containing the result of a divided by b (using
    // integer division), or Optional.empty() if b is zero.
    public static Optional<Integer> safeDivide(int a, int b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the value held by optional if present. If it is absent,
    // throws a NoSuchElementException whose message is errorMessage.
    public static <T> T unwrapOrThrow(Optional<T> optional, String errorMessage) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // If optional is present, maps its value to the string
    // "Value: <n>" and keeps that result only if its length is strictly
    // greater than minLength. Returns Optional.empty() in every other
    // case (optional absent, or the mapped string too short).
    public static Optional<String> mapAndFilter(Optional<Integer> optional, int minLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns true if both a and b are present and hold equal values,
    // false otherwise.
    public static boolean areBothPresentAndEqual(Optional<Integer> a, Optional<Integer> b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}