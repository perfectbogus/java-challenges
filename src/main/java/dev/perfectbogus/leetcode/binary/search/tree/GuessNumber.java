package dev.perfectbogus.leetcode.binary.search.tree;

public class GuessNumber {

    static int pick = 7;

    public static void main(String[] args) {
        System.out.println(guessNumber(10));
    }

    public static int guessNumber(int n) {
        int beg = 1;
        int end = n;

        while (beg <= end) {
            int mid = beg + (end - beg)/2;
            System.out.println("Guessing: " + mid);
            int res = guess(mid);

            if (res == 0) {
                return mid;
            } else if (res == 1) {
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }

    public static int guess(int n) {
        if (n > pick) {
            return -1;
        } else if (n < pick) {
            return 1;
        } else {
            return 0;
        }
    }
}
