package dev.perfectbogus.threads.retry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryTaskTest {

    @Test
    void testEventuallySucceeds() {
        assertDoesNotThrow(() -> {
            String result = RetryTask.executeWithRetry(10);
            assertEquals("success", result);
        });
    }

    @Test
    void testMaxRetriesExceeded() {
        assertThrows(RuntimeException.class,
                () -> RetryTask.executeWithRetry(0));
    }

    @Test
    void testInvalidMaxRetries() {
        assertThrows(IllegalArgumentException.class,
                () -> RetryTask.executeWithRetry(-1));
    }

}