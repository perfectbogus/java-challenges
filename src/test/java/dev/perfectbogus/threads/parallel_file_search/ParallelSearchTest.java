package dev.perfectbogus.threads.parallel_file_search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void testSingleFile() {
        List<String> files = List.of("the file to test");
        List<String> result = ParallelSearch.search(files, "test");
        assertEquals(1, result.size());
        assertEquals(List.of("the file to test"), result);
    }

    @Test
    void testNoSearch() {
        List<String> files = List.of("file to test 1", "file to test 2");
        List<String> result = ParallelSearch.search(files, "cat");
        assertEquals(0, result.size());
        assertEquals(List.of(), result);
    }

    @Test
    void testNullWord() {
        List<String> files = List.of("file to test");
        assertThrows(IllegalArgumentException.class, () -> {
            ParallelSearch.search(files, null);
        });
    }

    @Test
    void testFilesContainsNull() {
        List<String> files = new ArrayList<>();
        files.add("file to test");
        files.add(null);
        assertThrows(IllegalArgumentException.class, () -> {
            ParallelSearch.search(files, "test");
        });
    }

    @Test
    void testEmptyFilesList() {
        List<String> files = List.of();
        List<String> result = ParallelSearch.search(files, "cat");
        assertEquals(List.of(), result);
    }

    @Test
    void testNullFilesList() {
        assertThrows(IllegalArgumentException.class, () -> { ParallelSearch.search(null, "cat"); });
    }

    @Test
    void testPartialWordNoMatch() {
        List<String> files = List.of("concatenate", "category", "scatter");
        List<String> result = ParallelSearch.search(files, "cat");
        assertEquals(List.of(), result);
    }

    @Test
    void testCaseSensitive() {
        List<String> files = List.of("The Cat sat", "the cat sat");
        List<String> result = ParallelSearch.search(files, "cat");
        assertEquals(1, result.size());
        assertEquals("the cat sat", result.get(0));
    }

    @Test
    void testWordAtBoundaries() {
        List<String> files = List.of("cat is here", "here is cat");
        List<String> result = ParallelSearch.search(files, "cat");
        assertEquals(2, result.size());
    }

}