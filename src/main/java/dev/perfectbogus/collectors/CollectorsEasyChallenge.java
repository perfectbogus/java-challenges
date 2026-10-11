package dev.perfectbogus.collectors;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CollectorsEasyChallenge {

    // Used by all challenges. Fully implemented — nothing to do here.
    public static class Student {
        private final String name;
        private final String city;
        private final String course;
        private final int score;
        private final int age;

        public Student(String name, String city, String course, int score, int age) {
            this.name = name;
            this.city = city;
            this.course = course;
            this.score = score;
            this.age = age;
        }

        public String getName() { return name; }
        public String getCity() { return city; }
        public String getCourse() { return course; }
        public int getScore() { return score; }
        public int getAge() { return age; }
    }

    // CHALLENGE 1
    // Returns the names of all students in upper case, in the same order as
    // in students. Returns an empty list if students is empty.
    public static List<String> upperCaseNames(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns the distinct cities of the students as a Set whose iteration
    // order is alphabetical (natural String order).
    public static Set<String> sortedDistinctCities(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns the names of the students in the same order as in students.
    // The returned list must be unmodifiable: any attempt to add to it or
    // remove from it throws UnsupportedOperationException.
    public static List<String> unmodifiableNames(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns the names joined with " | " (space, bar, space) between them, in
    // the same order as in students, wrapped in square brackets.
    // Example: "[Ann | Bob | Cy]". Returns "[]" if students is empty.
    public static String bracketedNames(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a map from each student's name to their score.
    // Names are unique within students.
    public static Map<String, Integer> scoreByName(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns the sum of the scores of all students. Returns 0 if students
    // is empty.
    public static int totalScore(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the average score of all students. Returns 0.0 if students is
    // empty.
    public static double averageScore(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns how many students are 18 years old or older.
    public static long countAdults(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns the count, sum, minimum, maximum and average of the scores of
    // all students in a single IntSummaryStatistics object.
    public static IntSummaryStatistics scoreStatistics(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns how many students attend each course. The keys of the returned
    // map must come out in alphabetical order when iterated.
    public static Map<String, Long> countByCourseSorted(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns the names of the students of each course, as a Set per course.
    // Only courses that appear in students are keys.
    public static Map<String, Set<String>> namesByCourse(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns the student with the highest score in each course. If several
    // students of a course share the highest score, the first of them in
    // students is chosen. Only courses that appear in students are keys.
    public static Map<String, Optional<Student>> topStudentByCourse(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns how many students passed and how many did not. A student passes
    // with a score of 60 or more. The key true holds the number that passed,
    // the key false the number that did not. Both keys are always present.
    public static Map<Boolean, Long> countPassedAndFailed(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Splits the names of the students by age: the key true holds the names of
    // the students who are 18 or older, the key false the names of the others.
    // Each list keeps the order of students. Both keys are always present.
    public static Map<Boolean, List<String>> namesByAdulthood(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns, for each city, the name of the first student from that city in
    // students. Only cities that appear in students are keys.
    public static Map<String, String> firstNameByCity(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 16
    // Returns, for each city, the names of its students joined with ", " in
    // the same order as in students. Example value: "Ann, Cy".
    // Only cities that appear in students are keys.
    public static Map<String, String> joinedNamesByCity(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 17
    // Returns, for each course, how many of its students passed (score of 60
    // or more). Every course that appears in students is a key, including
    // courses where nobody passed, whose value is 0.
    public static Map<String, Long> passedCountByCourse(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 18
    // Returns the number of distinct cities among the students.
    // Returns 0 if students is empty.
    public static int distinctCityCount(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 19
    // Returns the number of students and the sum of their scores as one
    // String in the format "<count>:<total>". Example: "3:210".
    // Returns "0:0" if students is empty.
    public static String countAndTotal(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 20
    // Returns the names of the students for each distinct score. The keys of
    // the returned map must come out from the highest score to the lowest when
    // iterated. The names of each score keep the order of students.
    public static Map<Integer, List<String>> namesByScoreDescending(List<Student> students) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}