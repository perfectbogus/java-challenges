package dev.perfectbogus.threads;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class FirstResultWinsTest {

    @Test
    void testFirst() throws Exception {
        final String result = FirstResultWins.race();
        assertEquals("B", result);
    }

    @Test
    void testFirstResultWins() throws Exception {
        long start = System.currentTimeMillis();
        String result = FirstResultWins.race();
        long elapsed = System.currentTimeMillis() - start;

        assertEquals("B", result);

        assertTrue(elapsed < 2000, "Should complete in ~1s but took " + elapsed + "ms");
    }

    @Test
    void testAllTasksFail() {
        assertThrows(ExecutionException.class, () -> {
            try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
                List<Callable<String>> tasks = List.of(
                        () -> throw
                );
            }
        } );
    }

}