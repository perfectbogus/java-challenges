package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortByLengthThenAlphaTest {

    @Test
    void testBasicCase() {
        assertEquals(
                List.of("fig","date","kiwi","plum","apple","banana"),
                SortByLengthThenAlpha.sort(
                        new ArrayList<>(List.of(
                                "banana","apple","fig","kiwi","date","plum"))));
    }

    @Test
    void testAllSameLength() {
        assertEquals(
                List.of("cat","dog","fig"),
                SortByLengthThenAlpha.sort(
                        new ArrayList<>(List.of("dog","fig","cat"))));
    }

    @Test
    void testAllDifferentLength() {
        assertEquals(
                List.of("a","bb","ccc","dddd"),
                SortByLengthThenAlpha.sort(
                        new ArrayList<>(List.of("dddd","a","ccc","bb"))));
    }

    @Test
    void testSingleWord() {
        assertEquals(
                List.of("hello"),
                SortByLengthThenAlpha.sort(
                        new ArrayList<>(List.of("hello"))));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortByLengthThenAlpha.sort(
                new ArrayList<>()).isEmpty());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortByLengthThenAlpha.sort(null));
    }
}