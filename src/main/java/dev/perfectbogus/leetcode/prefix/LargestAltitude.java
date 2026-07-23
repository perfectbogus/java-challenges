package dev.perfectbogus.leetcode.prefix;

public class LargestAltitude {

    public static void main(String[] args) {
        int[] data = {44,32,-9,52,23,-50,50,33,-84,47,-14,84,36,-62,37,81,-36,-85,-39,67,-63,64,-47,95,91,-40,65,67,92,-28,97,100,81};

        System.out.println(largestByProcessing(data));
    }

    public static int largestByStorage(int[] gain) {
        int[] alts = new int[gain.length + 1];
        alts[0] = 0;
        for (int i = 1; i < alts.length; i++) {
            alts[i] = alts[i-1] + gain[i-1];
        }

        int max = 0;
        for (int x : alts) {
            max = Math.max(max, x);
        }

        return max;
    }

    public static int largestByProcessing(int[] gain) {
        int current = 0;
        int max = 0;

        for (int g : gain) {
            current += g;
            max = Math.max(max, current);
        }

        return max;
    }
}
