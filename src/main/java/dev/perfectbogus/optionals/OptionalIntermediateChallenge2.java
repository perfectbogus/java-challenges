package dev.perfectbogus.optionals;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class OptionalIntermediateChallenge2 {

    // Used by CHALLENGE 8. Fully implemented — nothing to do here.
    public static class Address {
        private final String zipCode; // may be null

        public Address(String zipCode) {
            this.zipCode = zipCode;
        }

        public Optional<String> getZipCode() {
            return Optional.ofNullable(zipCode);
        }
    }

    // Used by CHALLENGE 8. Fully implemented — nothing to do here.
    public static class Person {
        private final Address address; // may be null

        public Person(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
    }

    // Used by CHALLENGE 3. Fully implemented — nothing to do here.
    // Returns the first character of s, or an empty Optional if s is null
    // or empty.
    public static Optional<Character> firstCharOf(String s) {
        if (s == null || s.isEmpty()) return Optional.empty();
        return Optional.of(s.charAt(0));
    }

    // CHALLENGE 1
    // Parses s as an int and returns it wrapped in an Optional. Returns an
    // empty Optional if s is null or is not a valid integer (instead of
    // letting any exception propagate).
    public static Optional<Integer> safeParseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // CHALLENGE 2
    // Returns an Optional containing the uppercased string if opt has a
    // value, or an empty Optional if opt is empty.
    public static Optional<String> upperCaseIfPresent(Optional<String> opt) {
        return opt.map(String::toUpperCase);
    }

    // CHALLENGE 3
    // Returns an Optional containing the first character of opt's value
    // (using firstCharOf above), or an empty Optional if opt itself is
    // empty or its value's first character is unavailable. Must not
    // produce a nested Optional (i.e. the result type is Optional<Character>,
    // not Optional<Optional<Character>>).
    public static Optional<Character> firstCharacter(Optional<String> opt) {
        return opt.flatMap(s -> firstCharOf(s));
    }

    // CHALLENGE 4
    // Returns opt unchanged if it holds a positive value (> 0), or an
    // empty Optional otherwise (including when opt was already empty).
    public static Optional<Integer> keepIfPositive(Optional<Integer> opt) {
        return opt.filter(i -> i > 0);
    }

    // CHALLENGE 5
    // Returns opt's value if present. Otherwise, invokes fallbackSupplier
    // and returns the value it produces. fallbackSupplier must not be
    // invoked at all when opt already has a value.
    public static int orElseWithSideEffect(Optional<Integer> opt, Supplier<Integer> fallbackSupplier) {
        return opt.orElseGet(fallbackSupplier);
    }

    // CHALLENGE 6
    // Returns opt's value if present. Otherwise, invokes exceptionSupplier
    // to obtain an exception and throws it. exceptionSupplier must not be
    // invoked at all when opt already has a value.
    public static String orElseThrowCustom(Optional<String> opt, Supplier<? extends RuntimeException> exceptionSupplier) {
        return opt.orElseThrow(exceptionSupplier);
    }

    // CHALLENGE 7
    // If username has a value, appends "Found: " followed by that value
    // to log. Otherwise, appends "Not found" to log.
    public static void logUserStatus(Optional<String> username, List<String> log) {
        username.ifPresentOrElse(
                u -> log.add("Found: " + u),
                () -> log.add("Not found")
        );
    }

    // CHALLENGE 8
    // Returns person's address's zip code, or an empty Optional if
    // person has no address, or person's address has no zip code.
    public static Optional<String> getZipCode(Person person) {
        return person.getAddress().flatMap(Address::getZipCode);
    }

    // CHALLENGE 9
    // Returns an Optional containing the sum of a's and b's values if
    // both are present. Returns an empty Optional if either (or both) is
    // empty.
    public static Optional<Integer> combineIfBothPresent(Optional<Integer> a, Optional<Integer> b) {
        return a.flatMap(av -> b.map(bv -> av + bv));
    }

    // CHALLENGE 10
    // Returns primary if it has a value. Otherwise, invokes
    // fallbackSupplier and returns whatever Optional it produces.
    // fallbackSupplier must not be invoked at all when primary already
    // has a value.
    public static Optional<String> firstNonEmpty(Optional<String> primary, Supplier<Optional<String>> fallbackSupplier) {
        return primary.or(fallbackSupplier);
    }

    // CHALLENGE 11
    // Returns a new list containing the values of every present Optional
    // in list, in the same order, skipping any empty ones entirely. Does
    // not modify list.
    public static List<String> flattenPresentValues(List<Optional<String>> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Tries each key in keysInPriorityOrder, in order, against map, and
    // returns an Optional containing the value of the first key that is
    // present in map. Returns an empty Optional if none of the keys are
    // present in map.
    public static Optional<Integer> findValueForFirstExistingKey(Map<String, Integer> map, List<String> keysInPriorityOrder) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
