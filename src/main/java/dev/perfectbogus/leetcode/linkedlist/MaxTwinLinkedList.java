package dev.perfectbogus.leetcode.linkedlist;

public class MaxTwinLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8) )))))));

        System.out.println(maxTwin(head));
    }

    public static int maxTwin(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        int max = Integer.MIN_VALUE;

        while (prev != null) {
            int sum = prev.val + head.val;
            max = Math.max(max, sum);

            prev = prev.next;
            head = head.next;
        }

        return max;
    }
}
