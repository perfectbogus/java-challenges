package dev.perfectbogus.regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAdvancedChallenge {

    // CHALLENGE 1
    // Returns true if email is a syntactically valid address under this definition (false otherwise):
    //   - local part: one or more of [A-Za-z0-9._%+-]
    //   - then a literal '@'
    //   - then a domain made of one or more labels separated by '.', each label being one or more of
    //     [A-Za-z0-9-]
    //   - the final label (the TLD) must consist only of letters and be at least 2 characters long
    //   - the entire string must match this definition exactly, with nothing extra before or after
    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,}");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // CHALLENGE 2
    // Returns, in the order they appear in text, every integer found in it. A number is an optional
    // leading '-' immediately followed by one or more digit characters; the '-' counts as part of the
    // number only when it is immediately followed by a digit. Numbers are separated from one another by
    // any characters that are not digits or '-' (the test inputs for this challenge never place a '-'
    // directly between two digit runs, so there is no ambiguity about where one number ends and the next
    // begins).
    public static List<Integer> extractAllIntegers(String text) {
        Pattern p = Pattern.compile("(-?[0-9])+");
        Matcher m = p.matcher(text);
        List<Integer> results = new ArrayList<>();
        while (m.find()) {
            int parse = Integer.parseInt(m.group());
            results.add(parse);
        }
        return results;
    }

    // CHALLENGE 3
    // date is guaranteed to be in exactly the form yyyy-mm-dd (4 digits, '-', 2 digits, '-', 2 digits).
    // Using three named capturing groups named "year", "month", and "day" (not manual substring or split
    // calls in the method body), returns a Map with exactly those three keys, each mapped to the matching
    // substring of date as a String (not parsed into a number).
    public static Map<String, String> parseDateComponents(String date) {
        Pattern p = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
        Matcher m = p.matcher(date);
        Map<String, String> map = new HashMap<>();
        if (m.matches()) {
            map.put("year", m.group("year"));
            map.put("month", m.group("month"));
            map.put("day", m.group("day"));
        }
        return map;
    }

    // CHALLENGE 4
    // Searches text for the first word (a maximal run of word characters) that is immediately followed by
    // one or more whitespace characters and then that exact same word again, with the repetition detected
    // using a backreference to the first capture rather than by capturing two separate groups and comparing
    // them in Java code. The comparison is case-sensitive, so "Hello hello" does not count as a repetition.
    // Returns the repeated word wrapped in an Optional if one is found, or Optional.empty() otherwise.
    public static Optional<String> findRepeatedWord(String text) {
        Pattern p = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
        Matcher m = p.matcher(text);
        while (m.find()) {
            return Optional.of(m.group(1));
        }
        return Optional.empty();
    }

    // CHALLENGE 5
    // Returns true only if password satisfies all of the following, checked using a single regular
    // expression built from lookahead assertions (one lookahead per condition) rather than several separate
    // checks combined with Java's && operator:
    //   - length is at least 8 characters
    //   - contains at least one digit
    //   - contains at least one lowercase letter
    //   - contains at least one uppercase letter
    //   - contains at least one of the special characters: @ # $ % ^ & + =
    public static boolean isStrongPassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        Matcher m = Pattern.compile(pattern).matcher(password);
        return m.matches();
    }

    // CHALLENGE 6
    // digits is guaranteed to consist only of decimal digit characters. Returns a string of the same
    // length in which every digit has been replaced with '*' except for the last four, which are left
    // unchanged; if digits has 4 or fewer characters there is nothing to mask and it is returned unchanged.
    // Must be implemented using Pattern/Matcher (or String.replaceAll) with a lookahead assertion, not by
    // manually indexing into or building a substring of digits.
    public static String maskAllButLastFourDigits(String digits) {
        return digits.replaceAll("\\d(?=\\d{4})", "*");
    }

    // CHALLENGE 7
    // Returns, in the order they appear in text, every numeric amount (one or more digits, optionally
    // followed by a decimal point and one or more digits) that is immediately preceded by a '$' character.
    // The '$' itself must not be included in the results. Must be implemented using a lookbehind assertion
    // to require the preceding '$', rather than by separately locating each '$' and taking a substring
    // afterward in Java code.
    public static List<String> extractAmountsAfterDollarSign(String text) {
        final String pattern = "(?<=\\$)\\d+(\\.\\d+)?";
        Matcher m = Pattern.compile(pattern).matcher(text);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            System.out.println(m.group());
            list.add(m.group());
        }
        return list;
    }

    // CHALLENGE 8
    // Returns, in the order they appear in text, every whole word (a maximal run of word characters) that
    // does NOT end with the literal suffix "ing". Must be implemented using a single regular expression
    // with a negative lookahead that rules out words ending in "ing" directly, rather than matching every
    // word and then filtering the results afterward with something like String.endsWith in Java code.
    public static List<String> extractWordsNotEndingInIng(String text) {
        final String pattern = "\\b(?!\\w*ing\\b)\\w+\\b";
        Matcher m = Pattern.compile(pattern).matcher(text);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    // CHALLENGE 9
    // html contains zero or more simple tags of the form <name>content</name>, where name is one or more
    // letters, with no attributes and no nesting. Returns the content of the first such tag pair found,
    // wrapped in an Optional, or Optional.empty() if there is none. The closing tag's name must be required
    // to match the opening tag's name via a backreference (not by separately capturing both names and
    // comparing them in Java code), and the content must be captured with a non-greedy quantifier so that
    // it stops at the nearest matching closing tag rather than extending as far as possible through any
    // later tags of the same name.
    public static Optional<String> extractFirstMatchingTagContent(String html) {
        final String regex = "<(\\w+)>(.*?)<(/\\1)>";
        Matcher m = Pattern.compile(regex).matcher(html);
        if (m.find()) {
            return Optional.of(m.group(2));
        }
        return Optional.empty();
    }

    // CHALLENGE 10
    // Splits text into tokens using any of the following as a delimiter: a run of one or more commas
    // and/or semicolons, or a run of one or more whitespace characters. Consecutive delimiters, and any
    // leading or trailing delimiters, must not produce empty tokens in the result. Returns the tokens, in
    // order, with no further trimming needed. Must be implemented with a single regular expression passed
    // to String.split (or Pattern.split), not with manual loops or repeated calls to split on one delimiter
    // at a time.
    public static List<String> splitOnMultipleDelimiters(String text) {
        String[] split = text.split("[,;\\s]+");
        return Arrays.stream(split).filter(s -> !s.isEmpty()).toList();
    }
}
