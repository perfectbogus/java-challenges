package dev.perfectbogus.concurrency.leaderboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentLeaderBoardTest {

    Random rand = new Random();

    @Test
    void testConcurrentStress() {
        ConcurrentLeaderBoard board = new ConcurrentLeaderBoard();
        String[] players = {"Alice", "Bob", "Charlie", "Diana", "Eve"};

        try (ExecutorService executor = Executors.newFixedThreadPool(10)){
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    String player = players[rand.nextInt(players.length)];
                    int score = rand.nextInt(1000);
                    board.submitScore(player, score);
                });
            }

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Execution Interrupted", e);
        }

        assertEquals(5, board.size());
    }

    @Test
    void testBasicScore() {
        ConcurrentLeaderBoard board = new ConcurrentLeaderBoard();
        board.submitScore("Alice", 100);
        assertEquals(1, board.size());
        assertEquals(100, board.getScore("Alice"));
    }

    @Test
    void testHigherScore() {
        ConcurrentLeaderBoard board = new ConcurrentLeaderBoard();
        board.submitScore("Alice", 100);
        board.submitScore("Alice", 150);
        assertEquals(1, board.size());
        assertEquals(150, board.getScore("Alice"));
    }

    @Test
    void testLowerScoreIgnored() {
        ConcurrentLeaderBoard board = new ConcurrentLeaderBoard();
        int expected = 100;
        board.submitScore("Alice", 100);
        board.submitScore("Alice", 80);
        assertEquals(1, board.size());
        assertEquals(expected, board.getScore("Alice"));
    }

    @Test
    void testTopNOrdering() {
        ConcurrentLeaderBoard board = new ConcurrentLeaderBoard();
        board.submitScore("Alice", 150);
        board.submitScore("Bob", 200);
        board.submitScore("Charlie", 175);
        List<String> results = board.getTopN(2);
        assertEquals(List.of("Bob", "Charlie"), results);
    }

}