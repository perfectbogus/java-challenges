package dev.perfectbogus.optionals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalPracticeChallenge {

    // ==========================================================
    // EASY
    // ==========================================================

    // CHALLENGE 1
    // Returns the int value of text when Integer.parseInt would accept it
    // ("42", "-7", "+5"). Returns an empty Optional when text is null or is
    // not a valid int: blank text, text with surrounding spaces, decimals,
    // letters, or numbers outside the range of int. Never throws.
    public static Optional<Integer> parseInteger(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // CHALLENGE 2
    // Returns the name inside the Optional exactly as it is (even if it is an
    // empty string). If the Optional is empty, returns "Anonymous".
    public static String displayName(Optional<String> name) {
        return name.orElse("Anonymous");
    }

    // CHALLENGE 3
    // Returns the length of the text inside the Optional, or 0 if the Optional
    // is empty.
    public static int lengthOrZero(Optional<String> text) {
        return text.map(String::length).orElse(0);
    }

    // CHALLENGE 4
    // Returns an Optional containing text, exactly as it is (no trimming),
    // when text is neither null nor blank (blank = empty or only whitespace).
    // Otherwise returns an empty Optional.
    public static Optional<String> nonBlank(String text) {
        if (text == null || text.isBlank()) return Optional.empty();
        return Optional.of(text);
    }

    public static Optional<String> nonBlankChained(String text) {
        return Optional.ofNullable(text).filter(t -> !t.isBlank());
    }

    // CHALLENGE 5
    // Returns "Value: " followed by the number when the Optional has a value
    // (for example "Value: 5", "Value: 0", "Value: -3"), or "No value" when
    // the Optional is empty.
    public static String describe(Optional<Integer> number) {
        return number.map(v -> String.format("Value: %d", v)).orElse("No value");
    }

    // CHALLENGE 6
    // Returns the greatest number of nums, or an empty Optional if nums is
    // empty. The numbers can be negative. Does not modify nums.
    public static Optional<Integer> maxOf(List<Integer> nums) {
        return nums.stream().max(Comparator.naturalOrder());
    }

    // CHALLENGE 7
    // Returns primary when it has a value; otherwise returns fallback (which
    // may be empty too). When both have a value, primary wins.
    public static Optional<String> firstAvailable(Optional<String> primary, Optional<String> fallback) {
        return primary.or(() -> fallback);
    }

    // CHALLENGE 8
    // Returns the number inside the Optional when it has a value and that
    // value is greater than 0. In every other case (empty Optional, zero or
    // negative number) throws an IllegalArgumentException.
    public static int requirePositive(Optional<Integer> value) {
        return value.filter(v -> v > 0).orElseThrow(() -> new IllegalArgumentException("value must be a positive number"));
    }

    // CHALLENGE 9
    // Returns true only when the Optional has an age and that age is 18 or
    // more. An empty Optional is not an adult.
    public static boolean isAdult(Optional<Integer> age) {
        return age.filter(n -> n >= 18).isPresent();
    }

    public static boolean isAdultImproved(Optional<Integer> age) {
        return age.map(n -> n >= 18).orElse(false);
    }

    // CHALLENGE 10
    // Returns the values of the non-empty Optionals of items, in the same
    // order, skipping the empty ones. Repeated values are kept. Returns an
    // empty list if items is empty or contains only empty Optionals.
    public static List<String> presentValues(List<Optional<String>> items) {
        return items.stream().flatMap(Optional::stream).toList();
    }

    // ==========================================================
    // INTERMEDIATE
    // ==========================================================

    public record Address(String street, String city) {
    }

    public record User(String name, Address address) {
    }

    public record Company(String name, User owner) {
    }

    // CHALLENGE 11
    // Returns the city of the address of the owner of the company. Any link of
    // the chain can be missing (null): the company itself, its owner, the
    // owner's address, or the city. In all those cases returns an empty
    // Optional. An empty string "" is a real city and is returned as it is.
    //
    // Hint 1: Optional.ofNullable turns a value that may be null into an
    // Optional, and map applies a function only when there is a value.
    // Hint 2: map already treats a null result of the function as "no value",
    // so the chain does not need a single if.
    public static Optional<String> ownerCity(Company company) {
        return Optional.ofNullable(company).map(Company::owner).map(User::address).map(Address::city);
    }

    // CHALLENGE 12
    // Looks the setting up in this order: the value of key in env, then the
    // value of key in file, then the result of defaultSupplier. A value is
    // used only if it is not null and not blank; blank values and missing
    // keys are skipped. defaultSupplier must be called at most once, and only
    // when neither map has a usable value (it is never called when env or
    // file already has one).
    //
    // Hint 1: Optional has a method that takes a Supplier of another Optional
    // and calls it only when the current Optional is empty.
    // Hint 2: build one Optional per source with the same helper, chain them,
    // and finish with the terminal operation that also takes a Supplier.
    public static String resolveSetting(String key, Map<String, String> env, Map<String, String> file,
                                        Supplier<String> defaultSupplier) {
        return Optional.ofNullable(env.get(key))
                .filter(v -> !v.isBlank())
                .or(() -> Optional.ofNullable(file.get(key)))
                .orElseGet(defaultSupplier);
    }

    // ==========================================================
    // ADVANCED
    // ==========================================================

    // CHALLENGE 13
    // Looks up the price of every id with priceLookup and returns all the
    // prices, in the same order as ids, inside an Optional. If at least one id
    // has no price (its lookup returns an empty Optional), returns an empty
    // Optional. An empty ids list gives an Optional containing an empty list.
    // priceLookup is called exactly once per id, in order, and it must not be
    // called for any id that comes after the first id without a price. Repeated
    // ids are looked up once per occurrence.
    //
    // Hint 1: a stream is lazy and processes elements one by one, so an
    // operation that stops the stream early also stops the lookups.
    // Hint 2: map each id to its Optional, take the elements while they have a
    // value, and compare how many you got with how many ids there were.
    // Hint 3: a plain for loop that returns as soon as one lookup is empty is
    // also a correct solution.
    public static Optional<List<Integer>> pricesOfAll(List<String> ids,
                                                      Function<String, Optional<Integer>> priceLookup) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}