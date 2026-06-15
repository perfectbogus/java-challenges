package dev.perfectbogus.leetcode.strings;

import java.util.ArrayList;
import java.util.List;

public class Candies {

    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;

        kidsWithCandies(candies, extraCandies);
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> results = new ArrayList<>();

        int greatest = 0;
        for (int c : candies) {
            greatest = Math.max(greatest, c);
        }

        for (int c : candies) {
            results.add((c + extraCandies) >= greatest);
        }
        return results;
    }
}
