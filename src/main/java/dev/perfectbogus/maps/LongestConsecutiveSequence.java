package dev.perfectbogus.maps;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static int count(int[] array) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        if (array.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int j : array) {
            set.add(j);
        }

        int max = 0;
        for (int j : set) {
            if (!set.contains(j - 1)) {
                int length = 1;
                while (set.contains(j + length)) {
                    length++;
                }
                max = Math.max(max, length);
            }
        }
        return max;
    }

    public static int naiveApproach(int[] data) {
        Arrays.sort(data);
        int count = 1, max = 1;
        for (int i = 1; i < data.length; i++) {
            if (data[i] == data[i-1] + 1) count++;
            else count = 1;
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = new int[] {100, 4, 200 , 1, 3, 2};
        int result = naiveApproach(data);
        System.out.println(result);
    }
}
