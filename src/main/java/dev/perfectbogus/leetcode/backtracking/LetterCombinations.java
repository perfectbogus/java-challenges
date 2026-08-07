package dev.perfectbogus.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    public static void main(String[] args) {
        String digits = "23";

        List<String> res = letterCombinations(digits);

        System.out.println(res);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        if (digits == null || digits.isEmpty()) return res;


        Map<Character, List<Character>> digitToLetters = Map.of(
                '2', List.of('a', 'b', 'c'),
                '3', List.of('d', 'e', 'f'),
                '4', List.of('g', 'h', 'i'),
                '5', List.of('j', 'k', 'l'),
                '6', List.of('m', 'n', 'o'),
                '7', List.of('p', 'q', 'r', 's'),
                '8', List.of('t', 'u', 'v'),
                '9', List.of('w', 'x', 'y', 'z')
        );

        backtracking(digits, 0, new StringBuilder(), res, digitToLetters);

        return res;
    }

    public static void backtracking(String digits, int idx, StringBuilder comb, List<String> res, Map<Character, List<Character>> digitToLetters) {
        if (idx == digits.length()) {
            res.add(comb.toString());
            return;
        }

        List<Character> letters = digitToLetters.get(digits.charAt(idx));
        for(char letter : letters) {
            comb.append(letter);
            backtracking(digits, idx + 1, comb, res, digitToLetters);
            comb.deleteCharAt(comb.length() - 1);
        }
    }
}
