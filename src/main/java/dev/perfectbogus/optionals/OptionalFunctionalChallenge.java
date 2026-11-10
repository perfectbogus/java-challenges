package dev.perfectbogus.optionals;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OptionalFunctionalChallenge {

    // CHALLENGE 1
    // Returns the first word of the list that has "minLength" characters or more. If there is no such word, returns an
    // empty Optional. The words are never null.
    // Example: firstLongWord([hi, hello, world], 4)  returns Optional[hello]
    //          firstLongWord([hi, hello], 2)         returns Optional[hi]
    //          firstLongWord([hi, yes], 5)           returns Optional.empty
    //          firstLongWord([], 1)                  returns Optional.empty
    public static Optional<String> firstLongWord(List<String> words, int minLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns the number written in the text, if the text is a valid int, which means that Integer.parseInt accepts
    // it. If it is not valid (it has other characters, it is empty, or the number is too big or too small for an
    // int), returns an empty Optional. The text is never null.
    // Example: parseInteger("42")          returns Optional[42]
    //          parseInteger("-7")          returns Optional[-7]
    //          parseInteger("abc")         returns Optional.empty
    //          parseInteger("2147483648")  returns Optional.empty
    public static Optional<Integer> parseInteger(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a greeting: "Hello, " followed by the name and "!". If there is no name, the greeting is
    // "Hello, stranger!". If there is a name, it is used as it is, even if it is an empty text.
    // Example: greeting(Optional[ana])      returns "Hello, ana!"
    //          greeting(Optional.empty)     returns "Hello, stranger!"
    //          greeting(Optional[Mr Smith]) returns "Hello, Mr Smith!"
    public static String greeting(Optional<String> name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns the first Optional if it has a value. If it is empty, returns the second one (which can also be empty).
    // Example: firstPresent(Optional[a], Optional[b])           returns Optional[a]
    //          firstPresent(Optional.empty, Optional[b])        returns Optional[b]
    //          firstPresent(Optional[a], Optional.empty)        returns Optional[a]
    //          firstPresent(Optional.empty, Optional.empty)     returns Optional.empty
    public static Optional<String> firstPresent(Optional<String> first, Optional<String> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // If the Optional has a text with "minLength" characters or more, returns the length of that text. In any other
    // case (the text is shorter, or the Optional is empty), returns an empty Optional.
    // Example: lengthIfLongEnough(Optional[hello], 3)   returns Optional[5]
    //          lengthIfLongEnough(Optional[hello], 5)   returns Optional[5]
    //          lengthIfLongEnough(Optional[hi], 3)      returns Optional.empty
    //          lengthIfLongEnough(Optional.empty, 0)    returns Optional.empty
    public static Optional<Integer> lengthIfLongEnough(Optional<String> text, int minLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns the sum of the numbers that are present in the list of Optionals. The empty Optionals are ignored. If
    // there are no numbers, the result is 0.
    // Example: sumPresent([Optional[1], Optional.empty, Optional[2]])  returns 3
    //          sumPresent([Optional.empty, Optional.empty])            returns 0
    //          sumPresent([Optional[-5], Optional[5], Optional[7]])    returns 7
    //          sumPresent([])                                          returns 0
    public static int sumPresent(List<Optional<Integer>> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns a Predicate that is true for the texts that have more than n characters, and false for the others. The
    // length must be greater than n, not equal. n can be negative.
    // Example: longerThan(3).test("abcd")  returns true
    //          longerThan(3).test("abc")   returns false
    //          longerThan(0).test("")      returns false
    //          longerThan(-1).test("")     returns true
    public static Predicate<String> longerThan(int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns a Predicate that is true for the valid user names. A user name is valid if it has between 3 and 10
    // characters (both included) and it does not contain any space character (' '). Other characters are allowed.
    // Example: isValidUsername().test("ana")           returns true
    //          isValidUsername().test("an")            returns false
    //          isValidUsername().test("ana smith")     returns false
    //          isValidUsername().test("abcdefghijk")   returns false
    public static Predicate<String> isValidUsername() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Applies the functions of the list to the number, one after the other and in the order of the list: the first
    // function receives the start number, the second one receives the result of the first one, and so on. Returns the
    // result of the last function. Every function is used exactly once. If the list is empty, returns the start
    // number.
    // Example: applyAll([x -> x + 1, x -> x * 2], 3)  returns 8     ((3 + 1) * 2)
    //          applyAll([x -> x * 2, x -> x + 1], 3)  returns 7     ((3 * 2) + 1)
    //          applyAll([], 5)                        returns 5
    public static int applyAll(List<Function<Integer, Integer>> steps, int start) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a Supplier that gives a number each time it is called: the first call gives "start", the second call
    // gives start + 1, the third one start + 2, and so on. Each Supplier returned by this method counts on its own,
    // independently from the others.
    // Example: with s = counterFrom(5):  s.get() returns 5, then s.get() returns 6, then s.get() returns 7
    //          with a = counterFrom(0) and b = counterFrom(0):  a.get() returns 0, a.get() returns 1,
    //                                                           b.get() returns 0
    public static Supplier<Integer> counterFrom(int start) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Calls the action once for each word of the list that has "minLength" characters or more, in the same order the
    // words have in the list. A word that is repeated in the list is used each time it appears. If no word has enough
    // characters, the action is never called.
    // Example: with action = print the word
    //          forEachLongWord([hi, hello, a, world], 3, action)  calls the action with hello, then with world
    //          forEachLongWord([hi, hi], 2, action)               calls the action with hi, then with hi
    //          forEachLongWord([a, b], 5, action)                 never calls the action
    public static void forEachLongWord(List<String> words, int minLength, Consumer<String> action) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
