package dev.perfectbogus.leetcode.two.pointers;

public class WaterContainer {

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxArea(heights);
        System.out.println(result);
    }

    public static int maxArea(int[] height) {
        if (height.length < 1) return 0;

        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        int base = height.length - 1;

        while (l < r) {
            int minHeight = Math.min(height[l], height[r]);
            int area = base * minHeight;
            maxArea = Math.max(maxArea, area);

            if (height[l] >= height[r]) {
                r--;
            } else {
                l++;
            }
            base--;
        }

        return maxArea;
    }
}
