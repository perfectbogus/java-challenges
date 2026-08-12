package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SortMapEntriesTest {

    @Test
    void testBasicCase() {
        Map<String, Integer> map = new HashMap<>(Map.of(
                "apple",  3,
                "banana", 1,
                "cherry", 3,
                "date",   2
        ));
        List<Map.Entry<String, Integer>> result =
                SortMapEntries.sort(map);

        assertEquals("apple",  result.get(0).getKey());
        assertEquals(3,        result.get(0).getValue());
        assertEquals("cherry", result.get(1).getKey());
        assertEquals(3,        result.get(1).getValue());
        assertEquals("date",   result.get(2).getKey());
        assertEquals(2,        result.get(2).getValue());
        assertEquals("banana", result.get(3).getKey());
        assertEquals(1,        result.get(3).getValue());
    }

    @Test
    void testAllSameValue() {
        Map<String, Integer> map = new HashMap<>(Map.of(
                "charlie", 1,
                "alice",   1,
                "bob",     1
        ));
        List<Map.Entry<String, Integer>> result =
                SortMapEntries.sort(map);

        assertEquals("alice",   result.get(0).getKey());
        assertEquals("bob",     result.get(1).getKey());
        assertEquals("charlie", result.get(2).getKey());
    }

    @Test
    void testSingleEntry() {
        Map<String, Integer> map = new HashMap<>(
                Map.of("apple", 5));
        assertEquals(1, SortMapEntries.sort(map).size());
    }

    @Test
    void testEmptyMap() {
        assertTrue(SortMapEntries.sort(
                new HashMap<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortMapEntries.sort(null));
    }
}