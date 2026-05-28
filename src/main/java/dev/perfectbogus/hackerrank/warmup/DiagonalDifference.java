package dev.perfectbogus.hackerrank.warmup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiagonalDifference {

    static void main(String[] args) {
        List<List<Integer>> arr = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        int result = solution(arr);
//        System.out.println(result);

        List<Integer> data = List.of(1, -2, -7, 9, 1, -8, -5);
        List<Integer> data2 = List.of(1, 2, 3, -1, -2, -3, 0, 0);
//        plusMinus(data2);

//        stairCase2(6);
        List<Double> a = List.of(
                8.0,
                10.7,
                17.1,
                11.2,
                13.5,
                9.9,
                14.9,
                9.4,
                9.4,
                3.1,
                12.7
        );

        int count = countPeaks(a);
        System.out.println("result: " + count);



    }

    public static int countPeaks(List<Double> arr) {
        int count = 0;
        for (int i = 1; i < arr.size() - 1; i++) {
            double left = arr.get(i - 1);
            double current = arr.get(i);
            double right = arr.get(i + 1);

            System.out.println("Current: " + current);
            System.out.println("left: " + left);
            System.out.println("Right: " + right);
            if ( diffUp(current, left) && diffUp(current, right)) {
                System.out.println("c:" + current);
                System.out.println("l:" + left);
                System.out.println("r:" + right);
                count++;
            }
        }
        return count;
    }

    private static boolean diffUp(double a, double b) {
        double c = Math.abs(a - b);
        System.out.println("diff:" + c);
        return c >= 5.0;
    }

    private static int solution(List<List<Integer>> arr) {
        int col = 0;
        int row = 0;
        int colUp = arr.size() - 1;
        int rowUp = 0;

        int sumLeft = 0;
        int sumRight = 0;
        while (col < arr.size() && row < arr.size()) {
            List<Integer> rowsLeft = arr.get(row);
            sumLeft += rowsLeft.get(col);

            List<Integer> rowsRight = arr.get(rowUp);
            sumRight += rowsRight.get(colUp);

            col++;
            row++;
            colUp--;
            rowUp++;
        }
        return Math.abs(sumLeft - sumRight);
    }

    private static void plusMinus(List<Integer> arr) {
        if (arr == null) throw new IllegalArgumentException("arr cannot be null");
        if (arr.isEmpty()) throw new IllegalArgumentException("arr cannot be empty");

        // Write your code here
        Map<String, Long> sums = arr.stream()
                .collect(Collectors.groupingBy(
                        n -> n > 0 ? "positive" : n < 0 ? "negative" : "zero",
                        Collectors.counting()
                ));

        double p = (double) sums.getOrDefault("positive", 0L);
        double n = (double) sums.getOrDefault("negative", 0L);
        double z = (double) sums.getOrDefault("zero", 0L);

        System.out.printf("%.6f\n", p);
        System.out.printf("%.6f\n", n);
        System.out.printf("%.6f\n", z);
    }

    public static void staircase(int n) {
        if (n < 0) throw new IllegalArgumentException("n cannot be negative");

        int h = 1;
        // Write your code here
        for (int i = n; i > 0; i--) {
            String e = " ".repeat(i-1);
            String hash = "#".repeat(h);
            System.out.println(e + hash);
            h++;
        }
    }

    public static void stairCase2(int n ){
        int count = n;
        int h = 1;
        while(count > 0) {
            String empties = " ".repeat(count - 1);
            String hashes = "#".repeat(h);
            System.out.println(empties + hashes);
            h++;
            count--;
        }
    }
}
