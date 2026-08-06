package dev.perfectbogus.leetcode.heap;

import java.util.HashSet;
import java.util.PriorityQueue;

public class SmallestInfiniteSet {
    private int current;
    private PriorityQueue<Integer> minHeap;
    private HashSet<Integer> addedBack;

    public SmallestInfiniteSet() {
        this.current = 1;
        this.minHeap = new PriorityQueue<>();
        this.addedBack = new HashSet<>();
    }

    public int popSmallest() {
        if (!minHeap.isEmpty()) {
            int smallest = minHeap.poll();
            addedBack.remove(smallest);
            return smallest;
        }
        return current++;
    }

    public void addBack(int num) {
        if (num < current && !addedBack.contains(num)) {
            minHeap.offer(num);
            addedBack.add(num);
        }
    }

    
}
