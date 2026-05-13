package dev.perfectbogus.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloCallableTest {

    @Test
    void testGreetContainsName() throws Exception {
        String result = HelloCallable.greet("Alice");
        assertTrue(result.contains("Alice"));
    }

    @Test
    void testGreetContainsThreadName() throws Exception {
        String result = HelloCallable.greet("Alice");
        assertTrue(result.contains("pool"));
    }

    @Test
    void testGreetFormat() throws Exception {
        String result = HelloCallable.greet("Bob");
        assertTrue(result.startsWith("Hello from Bob on"));
    }

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> HelloCallable.greet(null));
    }

}