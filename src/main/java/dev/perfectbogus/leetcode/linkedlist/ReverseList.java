package dev.perfectbogus.leetcode.linkedlist;

public class ReverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7 )))))));
        ListNode result = reverseList(head);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        return prev;
    }
}
