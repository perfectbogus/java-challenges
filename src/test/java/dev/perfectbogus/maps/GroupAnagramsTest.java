package dev.perfectbogus.maps;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnagramsTest {

    @Test
    void testSimpleCase() {
        final String[] data = {"eat", "tea", "tan", "ate", "nat", "bat"};
        final Map<String, List<String>> expected = Map.ofEntries(
                Map.entry("aet", List.of("eat", "tea", "ate")),
                Map.entry("ant", List.of("tan", "nat")),
                Map.entry("abt", List.of("bat"))
        );
        final Map<String, List<String>> result = GroupAnagrams.group(data);
        assertGroupsEqual(expected, result);
    }

    @Test
    void testSingleWord() {
        Map<String, List<String>> result = GroupAnagrams.group(new String[]{"eat", "tea", "ate"});
        assertEquals(1, result.size());
    }

    @Test
    void testEmptyStrings() {
        Map<String, List<String>> result = GroupAnagrams.group(new String[]{"", ""});
        assertEquals(1, result.size());
        assertEquals(2, result.get("").size());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> GroupAnagrams.group(null));
    }

    @Test
    void testEmptyArray() {
        assertTrue(GroupAnagrams.group(new String[]{}).isEmpty());
    }

    @Test
    void testNullElement() {
        assertThrows(IllegalArgumentException.class, () -> GroupAnagrams.group(new String[]{"eat", null}));
    }

    private void assertGroupsEqual(Map<String, List<String>> expected, Map<String, List<String>> actual) {
        assertEquals(expected.size(), actual.size());
        expected.forEach((key, expectedList) -> {
            List<String> actualList = new ArrayList<>(actual.get(key));
            List<String> sortedExpected = new ArrayList<>(expectedList);
            Collections.sort(actualList);
            Collections.sort(sortedExpected);
            assertEquals(sortedExpected, actualList);
        });
    }


}