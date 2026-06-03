package dev.perfectbogus.hackerrank.missing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SmallestMissing {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 4, -1, 1);

        int res = findSmallMissingPositiveBrute(numbers);
        System.out.println(res);

        int res2 = findSmallMissingPositiveSpaceOne(numbers);
        System.out.println(res2);
    }

    private static int findSmallMissingPositiveBrute(List<Integer> numbers) {
        int missing = -1;
        for (int i = 1; i < numbers.size(); i++) {
            System.out.println(i);
            if (!numbers.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    private static int findSmallMissingPositiveSet(List<Integer> numbers) {
        Set<Integer> positives = numbers.stream().filter(n -> n > 0).collect(Collectors.toSet());

        int missing = -1;
        for (int candidate = 1; candidate <= numbers.size() + 1; candidate++) {
            if (!positives.contains(candidate)) {
                missing = candidate;
                break;
            }
        }
        return missing;
    }

    private static int findSmallMissingPositiveSpaceOne(List<Integer> array) {
        if (array == null)     throw new IllegalArgumentException("Array cannot be null");
        if (array.isEmpty())   return 1;

        int n = array.size();
        List<Integer> arr = new ArrayList<>(array); // work on copy

        for (int i = 0; i < n; i++) {
            if (arr.get(i) <= 0 || arr.get(i) > n) {
                arr.set(i, n + 1);
            }
        }
        System.out.println(arr);

        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr.get(i));
            System.out.println("val: " + val);
            if (val >= 1 && val <= n) {
                int idx = val - 1;
                System.out.println("idx: " + idx);
                if (arr.get(idx) > 0) {
                    arr.set(idx, -arr.get(idx));
                }
            }
        }
        System.out.println(arr);

        for (int i = 0; i < n; i++) {
            if (arr.get(i) > 0) {
                return i + 1;               // index 1 positive → 2 missing
            }
        }
        System.out.println(arr);
        return 1;
    }
}
