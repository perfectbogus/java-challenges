package dev.perfectbogus.threads.parallel_word;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelWordCountTest {

    @Test
    void testOneSentence() {
        List<String> sentences = List.of("test sentence1", "test sentence2");
        assertEquals(4, ParallelWordCount.count(sentences));
    }

    @Test
    void testEmptySentences() {
        assertEquals(0, ParallelWordCount.count(List.of()));
    }

    @Test
    void testMultipleSpaces() {
        assertEquals(2, ParallelWordCount.count(List.of("hello  world")));
    }

    // Single word sentences
    @Test
    void testSingleWords() {
        assertEquals(3, ParallelWordCount.count(List.of("a", "b", "c")));
    }

    // Blank sentences
    @Test
    void testBlankSentence() {
        assertEquals(0, ParallelWordCount.count(List.of("   ")));
    }

    // Null list
    @Test
    void testNullList() {
        assertThrows(IllegalArgumentException.class,
                () -> ParallelWordCount.count(null));
    }

    // Null element inside list
    @Test
    void testNullElement() {
        List<String> data = new ArrayList<>();
        data.add("hello");
        data.add(null);
        assertThrows(IllegalArgumentException.class,
                () -> ParallelWordCount.count(data));
    }

}