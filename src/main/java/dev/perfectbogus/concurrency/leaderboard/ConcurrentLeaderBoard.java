package dev.perfectbogus.concurrency.leaderboard;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcurrentLeaderBoard {

    private final Map<String, Integer> scores = new ConcurrentHashMap<>();

    public void submitScore(String player, int score) {
        scores.merge(player, score, Math::max);
    }

    public List<String> getTopN(int n) {
        return scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public int getScore(String player) {
        return scores.getOrDefault(player, 0);
    }

    public int size() {
        return scores.size();
    }
}
