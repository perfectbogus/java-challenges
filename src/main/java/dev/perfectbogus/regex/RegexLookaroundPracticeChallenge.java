package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLookaroundPracticeChallenge {

    // Practice set for lookahead and lookbehind assertions. Challenges 1-10 are easy and challenges 11-20 are a little
    // harder. Solve every challenge with java.util.regex using lookaround assertions (positive or negative), not with
    // manual loops, indexOf or substring arithmetic. A "word" is a maximal run of letters A-Z or a-z, and a "number"
    // is a maximal run of digits 0-9, unless a challenge says otherwise. The inputs are never null.

    // CHALLENGE 1
    // Returns, in order, the contents of the square brackets whose content is made only of one or more letters or
    // digits (A-Z, a-z, 0-9). The brackets are not part of the result. "[ab] [c d] [] [1]" gives [ab, 1]. Brackets
    // with any other content (spaces, punctuation, other brackets) are skipped.
    public static List<String> extractBracketContents(String text) {
        Matcher m = Pattern.compile("(?<=\\[)[a-zA-Z0-9]+(?=\\])").matcher(text);
        List<String> l = new ArrayList<>();
        while (m.find()) {
            l.add(m.group());
        }
        return l;
    }

    // CHALLENGE 2
    // Replaces every digit that is directly preceded by a letter (A-Z or a-z) with '#'. "a12b3" becomes "a#2b#": the
    // '2' is preceded by a digit, not by a letter, so it stays. Everything else is unchanged.
    public static String hashDigitsAfterLetters(String text) {
        return text.replaceAll("(?i)(?<=[a-z])\\d","#");
    }

    // CHALLENGE 3
    // Replaces every letter (A-Z or a-z) that is directly followed by a digit with '_'. "a1b22" becomes "_1_22".
    // Everything else is unchanged.
    public static String underscoreLettersBeforeDigits(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Splits s into pieces, starting a new piece before every uppercase letter (A-Z). The pieces keep all their
    // characters. "HelloWorldFoo" gives [Hello, World, Foo], "helloWorld" gives [hello, World] and "ABC" gives
    // [A, B, C]. No piece is empty, and an empty s returns an empty list.
    public static List<String> splitBeforeUppercase(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Splits text into pieces, ending a piece after every comma, so each piece keeps the comma that ends it.
    // "a,b,c" gives the pieces "a,", "b," and "c", and "a,,b" gives the pieces "a,", "," and "b". No piece is empty,
    // and an empty text returns an empty list.
    public static List<String> splitAfterCommas(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns how many digits in text have no digit directly before them and no digit directly after them.
    // "a1b22c333d4" has 2 of them (the '1' and the '4'). An empty text returns 0.
    public static int countIsolatedDigits(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns true if the first character of text is a letter (A-Z or a-z) and its last character is a digit (0-9).
    // The text may contain line breaks anywhere, and a line break at the end of the text is its last character (so it
    // is not a digit). A text of one character, or an empty text, returns false.
    public static boolean startsWithLetterEndsWithDigit(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns true if name ends with the exact text ".txt" (lowercase) and does not start with a '.'. name contains
    // no line breaks. "notes.txt" is true; ".hidden.txt", "a.TXT", ".txt", "txt" and "a.txt.bak" are false.
    public static boolean isVisibleTextFile(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Removes every run of one or more space characters (' ') that is directly followed by one of the characters
    // , . ! or ?. "Hello , world !" becomes "Hello, world!" and "a  ,b" becomes "a,b". Spaces that are not followed
    // by one of those characters, and every other character, are unchanged.
    public static String removeSpacesBeforePunctuation(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Inserts one space between every lowercase letter (a-z) and an uppercase letter (A-Z) that directly follows it.
    // "helloWorld" becomes "hello World" and "fooBarBaz" becomes "foo Bar Baz". Runs of capitals and every other
    // character stay as they are ("ABC" stays "ABC" and "HelloWORLD" becomes "Hello WORLD").
    public static String spaceBetweenLowerAndUpper(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Replaces with '*' every letter (A-Z or a-z) that has a letter directly before it and a letter directly after
    // it, so the first and the last letter of every word stay, and words of one or two letters are not changed.
    // "hello world" becomes "h***o w***d" and "ab cde" becomes "ab c*e". Digits and other characters are never
    // changed, and they end a word ("abc123def" becomes "a*c123d*f").
    public static String maskInnerLetters(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // digits contains only digit characters (it may be empty). Returns it with a ',' inserted between the groups of
    // three digits, counted from the right: "1234567" becomes "1,234,567", "1234" becomes "1,234", and "123" or
    // fewer digits are returned unchanged.
    public static String groupDigitsWithCommas(String digits) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns true if pin is made of exactly 4 digits (0-9), does not start with '0', and its four digits are not all
    // the same digit. "1234" and "1112" are true; "0123", "1111", "7777", "123", "12345" and "12a4" are false.
    public static boolean isValidPin(String pin) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns true if no digit in text is directly followed by the same digit; characters that are not digits are
    // ignored for this rule and may be anywhere in between. "1213" and "1 1" are true; "1223" and "a11b" are false.
    // The text may contain line breaks (a line break between two equal digits separates them). An empty text
    // returns true.
    public static boolean hasNoDigitRepeatedInARow(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Replaces every run of two or more spaces (' ') with a single space. "a   b" becomes "a b", "  a" becomes " a"
    // and "a  " becomes "a ". Single spaces, tabs, line breaks and every other character are unchanged.
    public static String collapseSpaces(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 16
    // The parentheses in text are balanced and never nested. Replaces with '_' every space character (' ') that is
    // inside a pair of parentheses: "a (b c) d (e f)" becomes "a (b_c) d (e_f)", and "( a )" becomes "(_a_)". Spaces
    // that are outside the parentheses, and every other character, are unchanged. The text may contain line breaks.
    public static String underscoreSpacesInParentheses(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 17
    // The parentheses in text are balanced and never nested. Splits text at every ';' that is not inside a pair of
    // parentheses, and keeps all the pieces, including empty ones: "a;b;c" gives [a, b, c], "a;(b;c);d" gives
    // [a, (b;c), d], "a;;b" gives [a, , b], "a;" gives [a, ] and ";" gives [, ]. An empty text returns a list with a
    // single empty piece. The ';' characters that split the text are not part of any piece.
    public static List<String> splitSemicolonsOutsideParentheses(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 18
    // Returns, in order, every word that is directly followed by exactly one space and then the same word again
    // (case-sensitive; the second one must be a whole word too). "the the cat" gives [the]; "a a a" gives [a, a];
    // "Hello hello", "the then" and "athe the" give nothing. Words separated by two spaces, punctuation or a line
    // break are not repeated words.
    public static List<String> extractRepeatedWords(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 19
    // Returns, in order, every capitalized word that is NOT at the start of a sentence. A word is capitalized when
    // its first letter is uppercase (A-Z), and it starts a sentence when it is at the very beginning of the text or
    // directly after a period and one space (". "). "Hello World. This is Paris" gives [World, Paris]. A word that
    // only has uppercase letters after its first letter ("iPhone") is not capitalized, and the whole word is
    // returned ("NASA" gives [NASA] when it is not at the start of a sentence).
    public static List<String> extractCapitalizedMidSentence(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 20
    // Returns the last word of text (the last maximal run of letters A-Z or a-z) or Optional.empty() when there are
    // no letters. "Hello big world!" gives "world", "abc1" gives "abc" and "x y1 z2" gives "z". The text may contain
    // line breaks ("first\nlast" gives "last").
    public static Optional<String> findLastWord(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
