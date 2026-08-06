package dev.perfectbogus.leetcode.heap;

public class MainHeap {

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet();

        set.addBack(2);
        System.out.println(set.popSmallest());
        set.popSmallest();
        set.popSmallest();
        set.addBack(1);
        System.out.println(set.popSmallest());
        set.popSmallest();
    }
}
