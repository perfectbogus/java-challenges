package dev.perfectbogus.optionals;

import java.util.List;
import java.util.Optional;

public class OptionalUsageChallenge {

    // Used by challenges 4, 5, 6, 10 and 11. In the examples a person is written as name(email, age, address).
    // email, age and address can be null when they are not known. The name is never null in challenges 4, 5, 6
    // and 11. An address is written as street/city, and its street and city can also be null.
    public record Address(String street, String city) {
    }

    public record Person(String name, String email, Integer age, Address address) {
    }

    // CHALLENGE 1
    // Returns name, or "unknown" when name is null. An empty text is a valid name and is returned as it is.
    // Example: nameOrDefault("Ana")  returns "Ana"
    //          nameOrDefault("")     returns ""
    //          nameOrDefault(null)   returns "unknown"
    public static String nameOrDefault(String name) {
        return Optional.ofNullable(name).orElse("unknown");
    }

    // CHALLENGE 2
    // Returns the whole number written in text, or an empty Optional when text is null or is not a whole number.
    // Spaces around the number are not allowed, so " 5" is not a valid number.
    // Example: parseNumber("42")    returns Optional[42]
    //          parseNumber("-7")    returns Optional[-7]
    //          parseNumber("4.5")   returns Optional.empty
    //          parseNumber("abc")   returns Optional.empty
    //          parseNumber(null)    returns Optional.empty
    public static Optional<Integer> parseNumber(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> parseNumberImproved(String text) {
        if (text == null) return Optional.empty();
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // CHALLENGE 3
    // Returns the number of characters of the text inside the Optional, or 0 when the Optional is empty.
    // Example: lengthOrZero(Optional.of("hello"))  returns 5
    //          lengthOrZero(Optional.of(""))       returns 0
    //          lengthOrZero(Optional.empty())      returns 0
    public static int lengthOrZero(Optional<String> text) {
        return text.map(String::length).orElse(0);
    }

    // CHALLENGE 4
    // Returns the domain of the email of the person: everything after the "@". If the person has no email (null),
    // returns "no email". Every email that is not null has exactly one "@".
    // Example: person = Ana(ana@mail.com, 30, null)  emailDomain(person) returns "mail.com"
    //          person = Bob(null, 25, null)          emailDomain(person) returns "no email"
    public static String emailDomain(Person person) {
        return Optional.ofNullable(person).map(Person::email).map(s -> s.split("@")[1]).orElse("no email");
    }

    public static String emailDomainImproved(Person person) {
        return Optional.ofNullable(person.email())
                .map(email -> email.substring(email.indexOf('@') + 1))
                .orElse("no email");
    }

    // CHALLENGE 5
    // Returns true if the person is 18 or older. A person without age (null) is not an adult, because we do not
    // know it.
    // Example: Ana(null, 30, null)  isAdult(ana) returns true
    //          Bob(null, 18, null)  isAdult(bob) returns true
    //          Cy(null, 17, null)   isAdult(cy) returns false
    //          Di(null, null, null) isAdult(di) returns false
    public static boolean isAdult(Person person) {
        return Optional.ofNullable(person.age()).filter(i -> i >= 18).isPresent();
    }

    // CHALLENGE 6
    // Returns the first person in the list that has exactly that name (the case matters, so "ana" and "Ana" are
    // different), or an empty Optional when nobody has it.
    // Example: people = [Ana(null, 30, null), Bob(null, 25, null), Ana(null, 40, null)]
    //          findByName(people, "Ana") returns Optional[the Ana of age 30]
    //          findByName(people, "ana") returns Optional.empty
    //          findByName([], "Ana")     returns Optional.empty
    public static Optional<Person> findByName(List<Person> people, String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the values that are present in the list of Optionals, in the same order. The empty Optionals are
    // left out.
    // Example: presentValues([Optional[a], Optional.empty, Optional[b]]) returns [a, b]
    //          presentValues([Optional.empty])                            returns []
    //          presentValues([])                                          returns []
    public static List<String> presentValues(List<Optional<String>> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Adds one line to the end of log. If value has a text, the line is "found: " followed by that text. If value
    // is empty, the line is "missing". Exactly one line is added each time.
    // Example: log = [], logValue(Optional.of("x"), log)    log becomes [found: x]
    //          log = [found: x], logValue(Optional.empty(), log)   log becomes [found: x, missing]
    public static void logValue(Optional<String> value, List<String> log) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns the text of first if it has one. Otherwise returns the text of second if it has one. If both are
    // empty, returns "none".
    // Example: firstAvailable(Optional.of("a"), Optional.of("b"))  returns "a"
    //          firstAvailable(Optional.empty(), Optional.of("b"))  returns "b"
    //          firstAvailable(Optional.of("a"), Optional.empty())  returns "a"
    //          firstAvailable(Optional.empty(), Optional.empty())  returns "none"
    public static String firstAvailable(Optional<String> first, Optional<String> second) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the name of the person. If the name is null, throws an IllegalArgumentException whose message is
    // exactly "name is required". An empty name is valid and is returned as it is.
    // Example: requireName(Person("Ana", null, null, null))  returns "Ana"
    //          requireName(Person("", null, null, null))     returns ""
    //          requireName(Person(null, null, null, null))   throws IllegalArgumentException("name is required")
    public static String requireName(Person person) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11 (medium)
    // Looks for the oldest person that has an age (people with age null are ignored) and returns the city of that
    // person in upper case. If two people share the greatest age, the one that comes first in the list is used.
    // The result is empty when nobody has an age, or when the oldest person has no address, or when the address of
    // the oldest person has no city. It never looks at other people to replace the oldest one.
    // Example: people = [Ana(null, 30, Main St/Lima), Bob(null, 45, Oak St/Quito), Cy(null, null, Elm St/Rome)]
    //          cityOfOldest(people) returns Optional[QUITO]
    //          people = [Ana(null, 30, Main St/Lima), Bob(null, 45, null)]
    //          cityOfOldest(people) returns Optional.empty  (Bob is the oldest and has no address)
    //          people = [Ana(null, null, Main St/Lima)]
    //          cityOfOldest(people) returns Optional.empty  (nobody has an age)
    public static Optional<String> cityOfOldest(List<Person> people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
