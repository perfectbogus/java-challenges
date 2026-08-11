package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortDescendingTest {

    @Test
    void testBasicCase() {
        assertEquals(
                List.of(9, 6, 5, 4, 3, 2, 1, 1),
                SortDescending.sort(
                        new ArrayList<>(List.of(3,1,4,1,5,9,2,6))));
    }

    @Test
    void testAllSame() {
        assertEquals(
                List.of(5, 5, 5),
                SortDescending.sort(
                        new ArrayList<>(List.of(5, 5, 5))));
    }

    @Test
    void testSingleElement() {
        assertEquals(
                List.of(1),
                SortDescending.sort(
                        new ArrayList<>(List.of(1))));
    }

    @Test
    void testWithNegatives() {
        assertEquals(
                List.of(5, 3, 0, -1, -4),
                SortDescending.sort(
                        new ArrayList<>(List.of(3, -1, 5, 0, -4))));
    }

    @Test
    void testAlreadySorted() {
        assertEquals(
                List.of(5, 4, 3, 2, 1),
                SortDescending.sort(
                        new ArrayList<>(List.of(5, 4, 3, 2, 1))));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortDescending.sort(
                new ArrayList<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortDescending.sort(null));
    }
}