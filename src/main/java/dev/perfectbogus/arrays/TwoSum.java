package dev.perfectbogus.arrays;

public class TwoSum {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        int[] results = solve(numbers, target);

        for (int result : results) {
            System.out.println(result);
        }
    }

    public static int[] solve(int[] numbers, int target){
        for (int i = 0; i<numbers.length; i++){
            for (int j = i + 1; j<numbers.length; j++){
                return new int[]{numbers[i], numbers[j]};
            }
        }
        return null;
    }
}
