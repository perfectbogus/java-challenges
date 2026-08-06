package dev.perfectbogus.leetcode.binary.search;

import java.util.Arrays;

public class SuccessfulPairs {
    public static void main(String[] args) {
        int[] spells = new int[] {5, 1, 3};
        int[] potions = new int[] {1, 2, 3, 4, 5};
        int success = 7;

        int[] results = successfulPairs(spells, potions, success);

        for (int x : results) {
            System.out.println(x);
        }

    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];

        Arrays.sort(potions);

        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int left = 0;
            int right = m - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) spell * potions[mid];
                System.out.println("left: " + left + " right: " + right + " mid: " + mid + " product: " + product);
                if (product >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            pairs[i] = m - left;
        }

        return pairs;
    }
}
