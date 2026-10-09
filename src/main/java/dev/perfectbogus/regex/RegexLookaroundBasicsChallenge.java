package dev.perfectbogus.regex;

import java.util.List;

public class RegexLookaroundBasicsChallenge {

    // An easy set to practise the basic lookaround assertions, one idea per challenge. Solve every challenge with
    // java.util.regex using a lookahead or a lookbehind assertion (positive or negative), not with manual loops,
    // indexOf or substring arithmetic. A "word" is a maximal run of letters A-Z or a-z, and a "number" is a maximal
    // run of digits 0-9, unless a challenge says otherwise. The inputs are never null.

    // CHALLENGE 1
    // Returns, in order, every word that is directly followed by a colon (':'). The colon is not part of the word.
    // "name: Ann, age: 30" gives [name, age]. A word followed by anything else (a space, a digit, the end of the
    // text) is not returned.
    public static List<String> extractWordsBeforeColon(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns, in order, every number that is directly followed by the two letters "kg" (lowercase). The "kg" is not
    // part of the number. "5kg of rice and 12kg of beans" gives [5, 12]. A number followed by a space and then "kg",
    // or by "KG", or by anything else, is not returned.
    public static List<String> extractNumbersBeforeKg(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns, in order, every number that is directly preceded by the text "id=". The "id=" is not part of the
    // number. "id=42 and id=7" gives [42, 7]. Only the digits are returned, so "id=12ab" gives [12]. An "id=" that
    // is not followed by a digit returns nothing.
    public static List<String> extractIdValues(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Replaces every lowercase 'q' that is NOT directly followed by a lowercase 'u' with '#'. Every 'q' that is
    // followed by 'u' stays as it is, and so does every other character (including the uppercase 'Q'). "queen and
    // Iraq" becomes "queen and Ira#". A 'q' at the very end of the text is not followed by anything, so it is replaced.
    public static String hideQWithoutU(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Removes every comma that is NOT directly preceded by a digit. Commas that are preceded by a digit stay, and
    // so does every other character. "1,000 apples, pears" becomes "1,000 apples pears". A comma at the start of the
    // text or right after another comma is not preceded by a digit either, so it is removed.
    public static String removeCommasNotAfterDigits(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Inserts a '-' between every two digits that are next to each other. "1234" becomes "1-2-3-4" and "ab12cd345"
    // becomes "ab1-2cd3-4-5". Digits that are next to a letter or to the start or the end of the text get nothing.
    public static String dashBetweenDigits(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Inserts one space after every comma that is not already followed by a space. "a,b,c" becomes "a, b, c". A
    // comma that is already followed by a space (one or more) is left alone, and a comma at the very end of the text
    // also gets a space after it ("a," becomes "a, "). Nothing is ever removed.
    public static String spaceAfterCommas(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns, in order, every word that is directly followed by exactly one space and then the whole word "Smith"
    // (case-sensitive; "Smithson" is a different word). "John Smith and Mary Smith" gives [John, Mary]. The space and
    // "Smith" are not part of the result. A word followed by two spaces, or by punctuation, before "Smith" is not
    // returned.
    public static List<String> extractFirstNamesBeforeSmith(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns true if text contains at least one letter (A-Z or a-z) AND at least one digit (0-9), in any order and
    // anywhere in the text. The text may contain line breaks. An empty text returns false.
    public static boolean containsLetterAndDigit(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns true if text contains at least one digit (0-9) and contains no whitespace character at all (no space,
    // tab or line break). The text may contain line breaks. An empty text returns false.
    public static boolean hasDigitAndNoWhitespace(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}