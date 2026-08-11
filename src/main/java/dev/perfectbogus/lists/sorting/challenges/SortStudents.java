package dev.perfectbogus.lists.sorting.challenges;

import java.util.List;

public class SortStudents {

    record Student(String name, int grade){}

    public static List<Student> sort(List<Student> students) {
        if (students == null) throw new IllegalArgumentException("List Students is null");

        students.sort((a, b) -> {
            if (a.grade() != b.grade()) return Integer.compare(b.grade(), a.grade());
            return a.name().compareTo(b.name());
        });

        return List.copyOf(students);
    }
}
