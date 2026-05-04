package dev.perfectbogus.deques;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseStringTest {

    @Test
    void testReverse() {
        assertEquals("olleh", ReverseString.reverse("hello"));
        assertEquals("a", ReverseString.reverse("a"));
        assertEquals("", ReverseString.reverse(""));
        assertEquals("12321", ReverseString.reverse("12321"));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> ReverseString.reverse(null));
    }

}