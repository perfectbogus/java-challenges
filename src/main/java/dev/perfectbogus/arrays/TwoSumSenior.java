package dev.perfectbogus.arrays;

import java.util.HashMap;
import java.util.Map;

public class TwoSumSenior {

    public static int[] solve(int[] numbers, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }

            seen.put(numbers[i], i);
        }

        throw new IllegalArgumentException("No valid pair found for target: " + target);
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = solve(numbers, target);
        System.out.println(result[0] + ", " + result[1]);
    }
}
