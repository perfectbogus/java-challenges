package dev.perfectbogus.maps;

import java.util.HashMap;
import java.util.Map;

public class SumEqualsK {

    public static int sum(int[] array, int k) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int i : array) {
            int val = array[i];
            int prefixSum = map.get(i) + val;
            if (!map.containsKey(val - k)) {
                map.put(prefixSum, 1);
            } else {
                count++;
            }
        }
        return count;

    }


    public static int naiveSum(int[] array, int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] data = {1, 1, 1};
        int k = 2;
        System.out.println(naiveSum(data, k));
    }
}
