package dev.perfectbogus.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class AsteroidCollition {

    public static void main(String[] args) {
        int[] data = {1, 2, -3, 5, 3};
        int[] result = asteroidCollision(data);
        for (int x : result) {
            System.out.println(x);
        }
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> st = new ArrayDeque<>();

        int i = 0;
        while (i < asteroids.length) {
            if (asteroids[i] > 0) {
                st.push(asteroids[i]);
            } else {
                if (st.isEmpty() || st.peek() < 0) {
                    st.push(asteroids[i]);
                } else if (Math.abs(st.peek()) == Math.abs(asteroids[i])) {
                    st.pop();
                } else if (Math.abs(st.peek()) < Math.abs(asteroids[i])) {
                    st.pop();
                    continue;
                }
            }
            i++;
        }

        int[] result = new int[st.size()];
        int j = st.size() - 1;
        while (!st.isEmpty()) {
            result[j--] = st.pop();
        }

        return result;
    }
}
