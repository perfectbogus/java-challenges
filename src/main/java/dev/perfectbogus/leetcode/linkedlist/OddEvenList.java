package dev.perfectbogus.leetcode.linkedlist;

public class OddEvenList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7 )))))));

        ListNode result = oddEvenList(head);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;


        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }
}
