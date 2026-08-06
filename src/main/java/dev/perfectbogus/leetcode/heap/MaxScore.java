package dev.perfectbogus.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxScore {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 3, 2};
        int[] nums2 = {2, 1, 3, 4};
        int k = 3;

//        System.out.println(maxScore(nums1, nums2, k));
        System.out.println(maxScore2(nums1, nums2, k));
    }

    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] efs = new int[n][2];

        for (int i = 0; i < n; i++) {
            efs[i] = new int[] {nums2[i], nums1[i]};
        }

        Arrays.sort(efs, (a, b) -> b[0] - a[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);

        long res = 0L;
        long sumS = 0L;

        for (int[] es: efs) {
            pq.offer(es[1]);
            sumS = (sumS + es[1]);

            if (pq.size() > k) {
                sumS -= pq.poll();
            } else if (pq.size() == k) {
                res = Math.max(res, (sumS * es[0]));
            }
        }

        return res;
    }

    record Pair(int a, int b) {}

    public static long maxScore2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums1[i], nums2[i]);
            System.out.println(pairs[i].toString());
        }

        Arrays.sort(pairs, (x, y) -> {
            System.out.println("x: " + x.toString() + " y: " + y.toString());
            return y.b - x.b;
        });

        System.out.println("After Sort:");
        for (Pair pair : pairs) {
            System.out.println(pair.toString());
        }

        Queue<Integer> q = new PriorityQueue<>(k + 1);
        long res = 0;
        long sum = 0;

        for (Pair p : pairs) {
            q.add(p.a);
            sum += p.a;
            if (q.size() > k) {
                sum -= q.poll();
            } else if (q.size() == k) {
                res = Math.max(res, sum * p.b);
            }
        }

        return res;
    }
}
