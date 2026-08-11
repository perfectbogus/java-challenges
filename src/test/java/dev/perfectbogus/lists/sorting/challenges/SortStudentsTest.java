package dev.perfectbogus.lists.sorting.challenges;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortStudentsTest {

    @Test
    void testBasicSort() {
        List<SortStudents.Student> students = new ArrayList<>(List.of(
                new SortStudents.Student("Alice",  85),
                new SortStudents.Student("Bob",    92),
                new SortStudents.Student("Carol",  85),
                new SortStudents.Student("Diana",  92)
        ));
        List<SortStudents.Student> result =
                SortStudents.sort(students);

        assertEquals("Bob",   result.get(0).name());
        assertEquals("Diana", result.get(1).name());
        assertEquals("Alice", result.get(2).name());
        assertEquals("Carol", result.get(3).name());
    }

    @Test
    void testAllSameGrade() {
        List<SortStudents.Student> students = new ArrayList<>(List.of(
                new SortStudents.Student("Charlie", 80),
                new SortStudents.Student("Alice",   80),
                new SortStudents.Student("Bob",     80)
        ));
        List<SortStudents.Student> result =
                SortStudents.sort(students);

        assertEquals("Alice",   result.get(0).name());
        assertEquals("Bob",     result.get(1).name());
        assertEquals("Charlie", result.get(2).name());
    }

    @Test
    void testSingleStudent() {
        List<SortStudents.Student> students = new ArrayList<>(List.of(
                new SortStudents.Student("Alice", 90)
        ));
        assertEquals(1, SortStudents.sort(students).size());
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> SortStudents.sort(null));
    }

    @Test
    void testEmptyList() {
        assertTrue(SortStudents.sort(new ArrayList<>()).isEmpty());
    }
}