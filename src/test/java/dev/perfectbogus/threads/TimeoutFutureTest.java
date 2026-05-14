package dev.perfectbogus.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeoutFutureTest {

    @Test
    void testMinDelay() {
        String result = TimeoutFuture.fetchWithTimeout(1);
        assertEquals("result", result);
    }

    @Test
    void testExceedsTimeout() {
        String expected = "default";
        String result = TimeoutFuture.fetchWithTimeout(3);
        assertEquals(expected, result);
    }

    @Test
    void testZeroDelay() {
        assertEquals("result", TimeoutFuture.fetchWithTimeout(0));
    }

    @Test
    void testBoundaryDelay() {
        String result = TimeoutFuture.fetchWithTimeout(2);
        assertTrue(result.equals("result") || result.equals("default"));
    }

    @Test
    void testNegativeDelay() {
        assertThrows(IllegalArgumentException.class, () -> TimeoutFuture.fetchWithTimeout(-1));
    }



}