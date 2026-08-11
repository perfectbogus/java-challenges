package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortCaseInsensitiveTest {

    @Test
    void testBasicCase() {
        List<String> result = SortCaseInsensitive.sort(
                new ArrayList<>(List.of(
                        "Banana","apple","APPLE","cherry","BANANA")));

        // apple group comes first (case insensitive)
        // internal order not guaranteed when length also equal!
        assertTrue(result.indexOf("apple") < result.indexOf("cherry"));
        assertTrue(result.indexOf("APPLE") < result.indexOf("cherry"));

        // banana group comes second
        assertTrue(result.indexOf("Banana") < result.indexOf("cherry"));
        assertTrue(result.indexOf("BANANA") < result.indexOf("cherry"));

        // cherry always last
        assertEquals("cherry", result.get(4));
    }

    @Test
    void testAllLowercase() {
        assertEquals(
                List.of("apple","banana","cherry"),
                SortCaseInsensitive.sort(
                        new ArrayList<>(List.of("cherry","apple","banana"))));
    }

    @Test
    void testAllUppercase() {
        assertEquals(
                List.of("APPLE","BANANA","CHERRY"),
                SortCaseInsensitive.sort(
                        new ArrayList<>(List.of("CHERRY","APPLE","BANANA"))));
    }

    @Test
    void testSingleWord() {
        assertEquals(
                List.of("Hello"),
                SortCaseInsensitive.sort(
                        new ArrayList<>(List.of("Hello"))));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortCaseInsensitive.sort(
                new ArrayList<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortCaseInsensitive.sort(null));
    }
}