package dev.perfectbogus.deques;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] data = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] result = slidingWindowMaxNaive(data, 3);
//        for (int i : result) {
//            System.out.println(i);
//        }

        int[] result2 = solve(data, 3);
        for (int i : result2 ) {
            System.out.println(i);
        }
    }

    public static int[] slidingWindowMaxNaive(int[] array, int k) {
        int[] result = new int[array.length - k + 1];
        for (int i = 0; i <= array.length - k; i++ ) {
            int max = array[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, array[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public static int[] solve(int[] array, int k) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        if (k <= 0 || k > array.length) throw new IllegalArgumentException("k out of range");

        int[] result = new int[array.length - k + 1];
        Deque<Integer> indexes = new ArrayDeque<>(k);

        for (int i = 0; i < array.length; i++) {
            int value = array[i];

            if (!indexes.isEmpty() && indexes.peekFirst() < i - k + 1) {
                indexes.pollFirst();
            }

            while (!indexes.isEmpty() && array[indexes.peekLast()] <= value) {
                indexes.pollLast();
            }

            indexes.addLast(i);

            if (i >= k - 1 ) {
                result[i - k + 1] = array[indexes.peekFirst()];
            }
        }
        return result;
    }
}
