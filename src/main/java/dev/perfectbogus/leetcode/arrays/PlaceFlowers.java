package dev.perfectbogus.leetcode.arrays;

public class PlaceFlowers {
    public static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;

        System.out.println(canPlaceFlowers(flowerbed, n));

    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = n;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean leftIsZero = (i == 0) || flowerbed[i - 1] == 0;
                boolean rightIsZero = (i == flowerbed.length - 1) || flowerbed[i + 1] == 0;

                if (leftIsZero  && rightIsZero) {
                    flowerbed[i] = 1;
                    count--;
                }
            }

            if (count <= 0) {
                return true;
            }
        }
        return false;
    }
}
