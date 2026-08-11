package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortNullsLastTest {

    @Test
    void testBasicCase() {
        List<String> result = SortNullsLast.sort(
                new ArrayList<>(Arrays.asList(
                        "banana", null, "apple", null, "cherry")));

        assertEquals("apple",  result.get(0));
        assertEquals("banana", result.get(1));
        assertEquals("cherry", result.get(2));
        assertNull(result.get(3));
        assertNull(result.get(4));
    }

    @Test
    void testNoNulls() {
        assertEquals(
                List.of("apple","banana","cherry"),
                SortNullsLast.sort(
                        new ArrayList<>(List.of(
                                "cherry","apple","banana"))));
    }

    @Test
    void testAllNulls() {
        List<String> result = SortNullsLast.sort(
                new ArrayList<>(Arrays.asList(null, null, null)));
        assertNull(result.get(0));
        assertNull(result.get(1));
        assertNull(result.get(2));
    }

    @Test
    void testSingleNull() {
        List<String> result = SortNullsLast.sort(
                new ArrayList<>(Arrays.asList((String) null))); // explicit!
        assertNull(result.get(0));
    }

    @Test
    void testNullFirst() {
        List<String> result = SortNullsLast.sort(
                new ArrayList<>(Arrays.asList(null, "apple")));
        assertEquals("apple", result.get(0));
        assertNull(result.get(1));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortNullsLast.sort(
                new ArrayList<>()).isEmpty());
    }
}