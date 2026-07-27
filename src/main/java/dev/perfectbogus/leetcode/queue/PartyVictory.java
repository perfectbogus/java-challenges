package dev.perfectbogus.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class PartyVictory {

    public static void main(String[] args) {
        String s1 = "RD";
        System.out.println(predictPartyVictory(s1));

        String s2 = "RDD";
        System.out.println(predictPartyVictory(s2));
    }

    public static String predictPartyVictory(String senate) {
        Queue<Integer> rq = new ArrayDeque<>();
        Queue<Integer> dq = new ArrayDeque<>();

        char[] letters = senate.toCharArray();
        int n = letters.length;

        for (int i = 0; i < n; i++) {
            if (letters[i] == 'R') {
                rq.offer(i);
            } else {
                dq.offer(i);
            }
        }

        while (!rq.isEmpty() && !dq.isEmpty()) {
            int r = rq.poll();
            int d = dq.poll();

            if (r < d) {
                rq.offer(n + r);
            } else {
                dq.offer(n + d);
            }
        }

        return rq.isEmpty() ? "Dire" : "Radiant";
    }
}
