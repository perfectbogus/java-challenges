package dev.perfectbogus.leetcode.graphs;

import java.util.ArrayList;
import java.util.List;

public class KeysAndRooms {

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> room0 = List.of(1, 3);
        List<Integer> room1 = List.of(3, 0, 1);
        List<Integer> room2 = List.of(2);
        List<Integer> room3 = List.of(0);
        rooms.add(room0);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        System.out.println(canVisitAllRooms(rooms));

        List<List<Integer>> rooms2 = new ArrayList<>();
        List<Integer> room02 = List.of(1);
        List<Integer> room12 = List.of(2);
        List<Integer> room22 = List.of(3);
        List<Integer> room32 = List.of(0);
        rooms2.add(room02);
        rooms2.add(room12);
        rooms2.add(room22);
        rooms2.add(room32);

        System.out.println(canVisitAllRooms(rooms2));

    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] vis = new boolean[rooms.size()];

        dfs(0, vis, rooms);

        for (boolean r : vis) {
            if (!r) return false;
        }
        return true;
    }

    public static void dfs(int vertex, boolean[] vis, List<List<Integer>> rooms) {
        vis[vertex] = true;
        List<Integer> keys = rooms.get(vertex);
        for (int k : keys) {
            if (!vis[k]) {
                dfs(k, vis, rooms);
            }
        }
    }
}
