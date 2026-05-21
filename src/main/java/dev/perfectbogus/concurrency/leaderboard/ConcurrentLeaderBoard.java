package dev.perfectbogus.concurrency.leaderboard;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentLeaderBoard {

    private final Map<String, Integer> scores = new ConcurrentHashMap<>();

    public void submitScore(String player, int score) {
        scores.merge(player, score, Math::max);
    }

    public List<String> getTopN(int n) {

    }

    public int getScore(String player) {
        return scores.getOrDefault(player, 0);
    }

    public int size() {
        return scores.size();
    }
}
