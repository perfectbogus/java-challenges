package dev.perfectbogus.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));

        int[] nums2 = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums2, 4));

        System.out.println(findKthLargestMinHeap(nums2, 4));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(5);
        minHeap.offer(7);
        minHeap.offer(4);
        System.out.println("Printing minHeap:");
        while (!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int x : nums) {
            maxHeap.offer(x);
        }

        int res = 0;
        for (int i = 0; i < k; i++) {
            res = maxHeap.poll();
        }

        return res;
    }

    public static int findKthLargestMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
