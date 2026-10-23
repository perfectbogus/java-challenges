package dev.perfectbogus.optionals;

import java.util.*;
import java.util.function.Supplier;

public class OptionalDrillsChallenge {

    // CHALLENGE 1
    // Returns the text inside an Optional, or an empty Optional when text is null, is empty or has only spaces.
    // Any other text is returned as it is, with its spaces.
    // Example: nonBlank("hello")    returns Optional[hello]
    //          nonBlank("  hi ")    returns Optional[  hi ]
    //          nonBlank("")         returns Optional.empty
    //          nonBlank("   ")      returns Optional.empty
    //          nonBlank(null)       returns Optional.empty
    public static Optional<String> nonBlank(String text) {
        if (text == null) return Optional.empty();
        if (text.isBlank()) return Optional.empty();
        return Optional.of(text);
    }

    public static Optional<String> nonBlankImproved(String text) {
        return Optional.ofNullable(text).filter(t -> !t.isBlank());
    }

    // CHALLENGE 2
    // Returns the price of item in the map, or an empty Optional when the map has no price for it. The map can have
    // an item whose price is null, and that item has no price either.
    // Example: prices = {pen=2, desk=150}  priceOf(prices, "pen")    returns Optional[2]
    //                                      priceOf(prices, "chair")  returns Optional.empty
    //          prices = {ghost=null}       priceOf(prices, "ghost")  returns Optional.empty
    public static Optional<Integer> priceOf(Map<String, Integer> prices, String item) {
        return Optional.ofNullable(prices.get(item));
    }

    // CHALLENGE 3
    // Returns the text of cached if it has one. Otherwise asks loader for the text and returns it. loader must be
    // used only when cached is empty: if cached has a text, loader is never called.
    // Example: valueOrLoad(Optional.of("old"), loader)  returns "old" and loader is not called
    //          valueOrLoad(Optional.empty(), loader)    returns what loader.get() returns, and loader is called once
    public static String valueOrLoad(Optional<String> cached, Supplier<String> loader) {
        return cached.orElseGet(loader);
    }

    // CHALLENGE 4
    // Returns true if every Optional in the list has a value. An empty list gives true.
    // Example: allPresent([Optional[a], Optional[b]])       returns true
    //          allPresent([Optional[a], Optional.empty])    returns false
    //          allPresent([])                               returns true
    public static boolean allPresent(List<Optional<String>> values) {
        return values.stream().allMatch(Optional::isPresent);
    }

    // CHALLENGE 5
    // Returns the longest word of the list, or an empty Optional when the list is empty. If several words have the
    // greatest length, the first one in the list is returned.
    // Example: longestWord([cat, horse, dog, mouse])  returns Optional[horse]
    //          longestWord([a])                       returns Optional[a]
    //          longestWord([])                        returns Optional.empty
    public static Optional<String> longestWord(List<String> words) {
        return words.stream().max(Comparator.comparingInt(String::length));
    }

    // CHALLENGE 6
    // Returns "Hello, " followed by the name, followed by "!". If the Optional has no value, returns
    // "Hello, stranger!".
    // Example: greeting(Optional.of("Ana"))  returns "Hello, Ana!"
    //          greeting(Optional.of(""))     returns "Hello, !"
    //          greeting(Optional.empty())    returns "Hello, stranger!"
    public static String greeting(Optional<String> name) {
        return name.map(v -> "Hello, " + v + "!").orElse("Hello, stranger!");
    }

    // CHALLENGE 7
    // Returns the sum of a and b when both have a number. If one of them is empty, or both, returns an empty
    // Optional.
    // Example: addOptionals(Optional.of(2), Optional.of(3))    returns Optional[5]
    //          addOptionals(Optional.of(2), Optional.empty())  returns Optional.empty
    //          addOptionals(Optional.empty(), Optional.of(3))  returns Optional.empty
    //          addOptionals(Optional.empty(), Optional.empty()) returns Optional.empty
    public static Optional<Integer> addOptionals(Optional<Integer> a, Optional<Integer> b) {
        if (a.isPresent() && b.isPresent()) {
            return Optional.of(a.get() + b.get());
        }
        return Optional.empty();
    }

    public static Optional<Integer> addOptionalsImproved(Optional<Integer> a, Optional<Integer> b) {
        return a.flatMap(x -> b.map(y -> x + y));
    }

    // CHALLENGE 8
    // Returns the first element of the list that is not null, or an empty Optional when there is none. The list can
    // contain null elements. An empty text is not null.
    // Example: firstNonNull([null, null, b, c])  returns Optional[b]
    //          firstNonNull([a, null])           returns Optional[a]
    //          firstNonNull([null, ""])          returns Optional[]  (the empty text)
    //          firstNonNull([null, null])        returns Optional.empty
    //          firstNonNull([])                  returns Optional.empty
    public static Optional<String> firstNonNull(List<String> values) {
        return values.stream().filter(Objects::nonNull).findFirst();
    }

    // CHALLENGE 9
    // Returns true if a and b are both empty, or if both have a value and the two values are equal (the case
    // matters). Returns false in any other case.
    // Example: sameValue(Optional.of("x"), Optional.of("x"))   returns true
    //          sameValue(Optional.empty(), Optional.empty())   returns true
    //          sameValue(Optional.of("x"), Optional.of("X"))   returns false
    //          sameValue(Optional.of("x"), Optional.empty())   returns false
    public static boolean sameValue(Optional<String> a, Optional<String> b) {
        if (a.isEmpty() && b.isEmpty()) {
            return true;
        } else if (a.isPresent() && b.isPresent()) {
            return a.get().equals(b.get());
        } else {
            return false;
        }
    }

    public static boolean sameValueImproved(Optional<String> a, Optional<String> b) {
        return a.equals(b);
    }

    // CHALLENGE 10
    // Returns the smallest even number of the array, or an empty OptionalInt when there is no even number. Zero and
    // negative numbers can be even too.
    // Example: smallestEven([7, 4, 9, 2, 6])  returns OptionalInt[2]
    //          smallestEven([-3, -4, 5])      returns OptionalInt[-4]
    //          smallestEven([1, 3, 5])        returns OptionalInt.empty
    //          smallestEven([])               returns OptionalInt.empty
    public static OptionalInt smallestEven(int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
