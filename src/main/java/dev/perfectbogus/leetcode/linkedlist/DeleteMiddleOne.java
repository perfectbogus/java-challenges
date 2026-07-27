package dev.perfectbogus.leetcode.linkedlist;

public class DeleteMiddleOne {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6 )))))));
        deleteMiddle(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    public static ListNode deleteMiddle(ListNode head) {
        int n = 0;
        ListNode tmp = head;
        while (tmp != null) {
            n++;
            tmp = tmp.next;
        }

        if (n == 1) {
            head = null;
        }

        int m = (n/2);
        int count = 0;

        tmp = head;
        while (tmp != null) {
            if (count++ == (m-1)) {
                tmp.next = tmp.next.next;
                break;
            } else {
                tmp = tmp.next;
            }
        }

        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
            next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
