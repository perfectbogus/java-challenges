package dev.perfectbogus.maps;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordFrequencyTest {

    @Test
    void testFrequency() {
        final String words = "the cat sat on the mat the cat";
        Map<String, Integer> expected = Map.ofEntries(
                Map.entry("the", 3),
                Map.entry("cat", 2),
                Map.entry("sat", 1),
                Map.entry("on", 1),
                Map.entry("mat", 1)
        );
        assertEquals(expected, WordFrequency.calculate(words));
    }

    @Test
    void testMultipleSpaces() {
        Map<String, Integer> result = WordFrequency.calculate("the   cat");
        assertEquals(1, result.get("the"));
        assertEquals(1, result.get("cat"));
    }

    @Test
    void testSingleWord() {
        Map<String, Integer> result = WordFrequency.calculate("hello");
        assertEquals(1, result.get("hello"));
    }

    @Test
    void testCaseSensitivity() {
        Map<String, Integer> result = WordFrequency.calculate("The the THE");
        assertEquals(3, result.size());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> WordFrequency.calculate(null));
    }

    @Test
    void testBlankInput() {
        assertTrue(WordFrequency.calculate("    ").isEmpty());
    }
}