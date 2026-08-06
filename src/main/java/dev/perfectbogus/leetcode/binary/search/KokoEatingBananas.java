package dev.perfectbogus.leetcode.binary.search;

public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = new int[]{3, 6, 7, 11};
        int h = 8;

        System.out.println(minEatingSpeed(piles, h));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int end = 0;

        for (int pile : piles) {
            end = Math.max(end, pile);
        }

        return findK(piles, h, 1, end, Integer.MAX_VALUE);
    }

    public static long canFinish(int[] piles, int mid) {
        long th = 0L;

        for (int pile : piles) {
            th = (long) (th + Math.ceil((double) pile / mid));
        }

        return th;
    }

    public static int findK(int[] piles, int h, int start, int end, int min) {
        if (start > end) return min;

        int mid = start + (end - start) / 2;

        long hours = canFinish(piles, mid);

        if (hours <= h) {
            min = Math.min(min, mid);
            return findK(piles, h, start, mid - 1, min);
        } else {
            return findK(piles, h, mid + 1, end, min);
        }
    }
}
