package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLookaroundChallenge {

    // The goal of this set is to practise lookahead and lookbehind assertions (positive and negative). Solve every
    // challenge with java.util.regex using lookaround assertions, not with manual loops, indexOf or substring
    // arithmetic. Unless a challenge says otherwise, the inputs are never null.

    // CHALLENGE 1
    // Inserts one space at every boundary between a letter (A-Z or a-z only) and a digit (0-9), in either direction
    // ("abc123def" becomes "abc 123 def"). Nothing else changes: existing spaces and every other character stay
    // exactly where they are, so no space is added next to a character that is neither a letter nor a digit.
    public static String separateLettersAndDigits(String s) {
        final String regex = "(?<=[0-9])(?=[a-zA-Z])|(?<=[a-zA-Z])(?=[0-9])";
        return s.replaceAll(regex, " ");
    }

    // CHALLENGE 2
    // Returns, in order, every word that comes directly after the word "not", separated from it by exactly one space.
    // "not" is compared ignoring case and must be a whole word: it is not preceded by a letter, digit or underscore
    // ("knot" and "cannot" do not count; "nothing" does not count either). A word is the maximal run of letters
    // (A-Z, a-z) that starts right after "not ". The word "not" itself can be returned when it follows another "not".
    public static List<String> wordsAfterNot(String text) {
        final String regex = "(?<=\\bnot) ([a-zA-Z]+)";
        Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
        List<String> results = new ArrayList<>();
        while (m.find()) {
            results.add(m.group(1));
        }
        return results;
    }

    // CHALLENGE 3
    // email contains exactly one '@', with at least one character before it and at least one after it, and no line
    // breaks. Replaces every character of the part before the '@' with '*' except its first and last character
    // ("john.doe@mail.com" becomes "j******e@mail.com"). A part of one or two characters has no characters in the
    // middle, so it stays unchanged. The part after the '@' is never changed.
    public static String maskEmailLocalPart(String email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns, in order, the contents found between "<<" and ">>". Each content is the SHORTEST non-empty sequence of
    // characters (no line breaks) that is directly preceded by "<<" and directly followed by ">>". The text is scanned
    // from left to right, and characters that were already returned as part of one content are never returned again
    // as part of another. A content may itself contain '<' and '>' characters ("<<a>b>>" has the content "a>b").
    public static List<String> extractDoubleAngleContents(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Splits a camelCase or PascalCase identifier into its words. s contains only letters (A-Z, a-z). A new word
    // starts (a) where a lowercase letter is followed by an uppercase letter, and (b) where an uppercase letter is
    // followed by an uppercase letter that is itself followed by a lowercase letter, which ends a run of capitals
    // ("parseHTTPResponse" gives [parse, HTTP, Response], "HTTPServer" gives [HTTP, Server], "ABC" gives [ABC]).
    // The words keep their original case. An empty s returns an empty list.
    public static List<String> splitCamelCase(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // expr contains only digits and the operators + - * / (no spaces), in any order, so it may start or end with an
    // operator or contain several operators in a row. Returns its tokens in order: every maximal run of digits is one
    // token and every operator character is a token of its own ("12+3*4" gives [12, +, 3, *, 4], "--3" gives
    // [-, -, 3]). No token is empty. An empty expr returns an empty list.
    public static List<String> tokenizeExpression(String expr) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns, in order, every maximal run of digits in text that is NOT directly followed by '%'. A run that is
    // followed by '%' is skipped completely: "50% of 200" gives [200], not [5, 200]. Digits separated by anything else
    // (for example "3.5") are different runs, so "3.5%" gives [3].
    public static List<String> extractNumbersNotPercentages(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns, in order, the amounts in text that are directly preceded by one of these markers (case-sensitive):
    // "$", "€", "USD " (with one trailing space) or "EUR " (with one trailing space). An amount is the longest
    // sequence of one or more digits optionally followed by a '.' and one or more digits ("$12.50" has the amount
    // "12.50"). The marker is not part of the result. If the character right after the longest sequence is a letter
    // (A-Z or a-z), the amount is rejected completely: "$12.5k" gives nothing, not "12". Any other character after it
    // (a space, a comma, a period, the end of the text) is fine, so "pay $12." gives [12].
    public static List<String> extractAmountsWithCurrency(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // target is a non-empty literal string (its characters have no special meaning, so "." matches only a dot).
    // Returns the start index of every occurrence of target in text, in ascending order, counting occurrences that
    // overlap each other ("aaaa" contains "aa" at 0, 1 and 2). If there is no occurrence the result is empty.
    public static List<Integer> indexesOfOverlapping(String text, String target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns true only if username satisfies all of these rules, checked case-insensitively where letters matter:
    //   - between 4 and 16 characters long
    //   - made only of ASCII letters, ASCII digits and underscores
    //   - starts with a letter
    //   - does not end with an underscore
    //   - has no two underscores in a row
    //   - does not contain the text "admin" in any combination of upper and lower case
    //   - contains at most 3 digits in total
    public static boolean isValidUsername(String username) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns true if no letter (A-Z or a-z) appears more than once in s, ignoring case, so "Dermatoglyphics" is
    // true and "Hello" is false. Characters that are not ASCII letters (spaces, digits, hyphens, line breaks...) are
    // ignored and may repeat. An empty s returns true.
    public static boolean isIsogram(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // number is a decimal number: an optional leading '-', one or more digits, and optionally a '.' followed by one
    // or more digits; it is at most 60 characters long. Returns it with a ',' inserted so that the digits of the
    // integer part are in groups of three counted from the right ("-1234567.891234" becomes "-1,234,567.891234").
    // The leading '-' and the digits after the '.' are never grouped, and a number whose integer part has three
    // digits or fewer is returned unchanged.
    public static String formatWithThousands(String number) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns true if every word of words appears in text as a whole word, in any order and ignoring case. A word
    // appears as a whole word when the occurrence is not directly preceded and not directly followed by a letter
    // (A-Z, a-z), digit or underscore, so "java" is not found in "javascript" but is found in "(java)". Words are
    // literal text: they are non-empty and may contain characters that are special in regular expressions, such as
    // "c++" or "a.b". Duplicated words are fine. An empty words list returns true.
    public static boolean containsAllWords(String text, List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // A token is a maximal run of letters (A-Z, a-z), digits and underscores in code. A token is an identifier when
    // its first character is not a digit ("count2" and "_tmp" are identifiers, "2fast" is not, and neither is any
    // part of it). Returns, in order and with repetitions, the identifiers of code that are NOT equal (case-sensitive,
    // the whole token) to one of keywords. A token that merely starts or ends with a keyword ("iffy", "elsewhere")
    // is not excluded. keywords may be empty, and its words are literal text that contains only letters.
    public static List<String> extractIdentifiersExcludingKeywords(String code, List<String> keywords) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // text has an even number of double-quote characters (") and may contain line breaks; it is at most 1000
    // characters long. Replaces with '_' every space character (' ') that is not inside a quoted section. A quoted
    // section goes from an opening quote to the next quote, both quote characters included, so the spaces between
    // them are kept. Tabs, line breaks and every other character are never changed.
    public static String replaceSpacesOutsideQuotes(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
