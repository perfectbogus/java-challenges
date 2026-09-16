package dev.perfectbogus.optionals;

import java.util.*;

public class OptionalIntermediateChallenge {

    // CHALLENGE 1
    // Attempts to parse input as an Integer. Returns an Optional containing
    // the parsed value if input is a valid integer, or Optional.empty() if
    // parsing fails for any reason.
    public static Optional<Integer> parseIntSafely(String input) {
        Objects.requireNonNull(input, "Input cannot be null");
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
        return optional.map(String::toUpperCase).orElse("EMPTY");
    }

    // CHALLENGE 3
    // Returns a, if it is present and not blank. Otherwise returns b, if
    // it is present and not blank. Otherwise returns Optional.empty().
    // A blank string is one that is empty or contains only whitespace.
    public static Optional<String> firstNonBlank(Optional<String> a, Optional<String> b) {
        return a.filter(s -> !s.isBlank()).or(() -> b.filter(s -> !s.isBlank()));
    }

    // CHALLENGE 4
    // Looks up key in aliasMap to find an intermediate key, then looks
    // that intermediate key up in valueMap. Returns the final value if
    // both lookups succeed, or Optional.empty() if either lookup fails
    // to find anything.
    public static Optional<Integer> chainLookup(Map<String, String> aliasMap, Map<String, Integer> valueMap, String key) {
        return Optional.ofNullable(aliasMap.get(key)).flatMap(k -> Optional.ofNullable(valueMap.get(k)));
    }

    // CHALLENGE 5
    // Returns the sum of the values held by every present Optional in the
    // list, ignoring any that are empty.
    public static int sumPresentValues(List<Optional<Integer>> optionals) {
        return optionals.stream().flatMap(Optional::stream).mapToInt(Integer::intValue).sum();
    }

    // CHALLENGE 6
    // If optional is present, appends its value to presentLog. Otherwise,
    // appends the literal string "empty" to emptyLog.
    public static void logPresenceOutcome(Optional<String> optional, List<String> presentLog, List<String> emptyLog) {
        optional.ifPresentOrElse(presentLog::add, () -> emptyLog.add("empty"));
    }

    // CHALLENGE 7
    // Returns an Optional containing the result of a divided by b (using
    // integer division), or Optional.empty() if b is zero.
    public static Optional<Integer> safeDivide(int a, int b) {
        return Optional.of(b).filter(divisor -> divisor != 0).map(divisor -> a /divisor);
    }

    // CHALLENGE 8
    // Returns the value held by optional if present. If it is absent,
    // throws a NoSuchElementException whose message is errorMessage.
    public static <T> T unwrapOrThrow(Optional<T> optional, String errorMessage) {
        return optional.orElseThrow(() -> new NoSuchElementException(errorMessage));
    }

    // CHALLENGE 9
    // If optional is present, maps its value to the string
    // "Value: <n>" and keeps that result only if its length is strictly
    // greater than minLength. Returns Optional.empty() in every other
    // case (optional absent, or the mapped string too short).
    public static Optional<String> mapAndFilter(Optional<Integer> optional, int minLength) {
        return optional.map(i -> "Value: " + i).filter(s -> s.length() > minLength);
    }

    // CHALLENGE 10
    // Returns true if both a and b are present and hold equal values,
    // false otherwise.
    public static boolean areBothPresentAndEqual(Optional<Integer> a, Optional<Integer> b) {
        return a.flatMap(x -> b.map(x::equals)).orElse(false);
    }
}