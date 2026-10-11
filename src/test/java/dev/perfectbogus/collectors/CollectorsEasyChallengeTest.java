package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorsEasyChallengeTest {

    private static CollectorsEasyChallenge.Student s(String name, String city, String course, int score, int age) {
        return new CollectorsEasyChallenge.Student(name, city, course, score, age);
    }

    private static List<CollectorsEasyChallenge.Student> school() {
        return List.of(
                s("Ann", "Lima", "Math", 90, 20),
                s("Bob", "Quito", "Math", 55, 17),
                s("Cy", "Lima", "Art", 72, 19),
                s("Dee", "Bogota", "Art", 60, 18),
                s("Eli", "Quito", "Math", 90, 22),
                s("Fay", "Bogota", "Bio", 40, 16));
    }

    private static List<CollectorsEasyChallenge.Student> none() {
        return List.of();
    }

    // ==========================================================
    // CHALLENGE 1: upperCaseNames
    // ==========================================================
    @Nested
    class UpperCaseNamesTests {

        @Test
        void testUpperCasesInOriginalOrder() {
            assertEquals(List.of("ANN", "BOB", "CY", "DEE", "ELI", "FAY"),
                    CollectorsEasyChallenge.upperCaseNames(school()));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals(List.of("ZOE", "AL"),
                    CollectorsEasyChallenge.upperCaseNames(List.of(s("Zoe", "x", "y", 1, 1), s("Al", "x", "y", 1, 1))));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), CollectorsEasyChallenge.upperCaseNames(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortedDistinctCities
    // ==========================================================
    @Nested
    class SortedDistinctCitiesTests {

        @Test
        void testDistinctAndAlphabetical() {
            assertEquals(List.of("Bogota", "Lima", "Quito"),
                    new ArrayList<>(CollectorsEasyChallenge.sortedDistinctCities(school())));
        }

        @Test
        void testSortedEvenWhenInputIsReversed() {
            List<CollectorsEasyChallenge.Student> input = List.of(
                    s("a", "Zurich", "x", 1, 1), s("b", "Oslo", "x", 1, 1), s("c", "Zurich", "x", 1, 1),
                    s("d", "Berlin", "x", 1, 1));
            assertEquals(List.of("Berlin", "Oslo", "Zurich"),
                    new ArrayList<>(CollectorsEasyChallenge.sortedDistinctCities(input)));
        }

        @Test
        void testEmptyInput() {
            assertTrue(CollectorsEasyChallenge.sortedDistinctCities(none()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 3: unmodifiableNames
    // ==========================================================
    @Nested
    class UnmodifiableNamesTests {

        @Test
        void testContainsNamesInOrder() {
            assertEquals(List.of("Ann", "Bob", "Cy", "Dee", "Eli", "Fay"),
                    CollectorsEasyChallenge.unmodifiableNames(school()));
        }

        @Test
        void testCannotAdd() {
            List<String> names = CollectorsEasyChallenge.unmodifiableNames(school());
            assertThrows(UnsupportedOperationException.class, () -> names.add("Zed"));
        }

        @Test
        void testCannotRemove() {
            List<String> names = CollectorsEasyChallenge.unmodifiableNames(school());
            assertThrows(UnsupportedOperationException.class, () -> names.remove(0));
        }

        @Test
        void testEmptyInputGivesEmptyUnmodifiableList() {
            List<String> names = CollectorsEasyChallenge.unmodifiableNames(none());
            assertTrue(names.isEmpty());
            assertThrows(UnsupportedOperationException.class, () -> names.add("Zed"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: bracketedNames
    // ==========================================================
    @Nested
    class BracketedNamesTests {

        @Test
        void testSeparatorPrefixAndSuffix() {
            assertEquals("[Ann | Bob | Cy | Dee | Eli | Fay]", CollectorsEasyChallenge.bracketedNames(school()));
        }

        @Test
        void testSingleName() {
            assertEquals("[Ann]", CollectorsEasyChallenge.bracketedNames(List.of(s("Ann", "x", "y", 1, 1))));
        }

        @Test
        void testEmptyInputGivesEmptyBrackets() {
            assertEquals("[]", CollectorsEasyChallenge.bracketedNames(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: scoreByName
    // ==========================================================
    @Nested
    class ScoreByNameTests {

        @Test
        void testMapsNamesToScores() {
            assertEquals(Map.of("Ann", 90, "Bob", 55, "Cy", 72, "Dee", 60, "Eli", 90, "Fay", 40),
                    CollectorsEasyChallenge.scoreByName(school()));
        }

        @Test
        void testEmptyInput() {
            assertEquals(Map.of(), CollectorsEasyChallenge.scoreByName(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 6: totalScore
    // ==========================================================
    @Nested
    class TotalScoreTests {

        @Test
        void testSumsAllScores() {
            assertEquals(407, CollectorsEasyChallenge.totalScore(school()));
        }

        @Test
        void testSingleStudent() {
            assertEquals(33, CollectorsEasyChallenge.totalScore(List.of(s("a", "x", "y", 33, 1))));
        }

        @Test
        void testEmptyInputGivesZero() {
            assertEquals(0, CollectorsEasyChallenge.totalScore(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 7: averageScore
    // ==========================================================
    @Nested
    class AverageScoreTests {

        @Test
        void testAverageHasDecimals() {
            assertEquals(407.0 / 6, CollectorsEasyChallenge.averageScore(school()), 0.0001);
        }

        @Test
        void testIntegerDivisionIsNotUsed() {
            assertEquals(1.5, CollectorsEasyChallenge.averageScore(
                    List.of(s("a", "x", "y", 1, 1), s("b", "x", "y", 2, 1))), 0.0001);
        }

        @Test
        void testEmptyInputGivesZero() {
            assertEquals(0.0, CollectorsEasyChallenge.averageScore(none()), 0.0001);
        }
    }

    // ==========================================================
    // CHALLENGE 8: countAdults
    // ==========================================================
    @Nested
    class CountAdultsTests {

        @Test
        void testCountsStudentsAged18OrMore() {
            assertEquals(4L, CollectorsEasyChallenge.countAdults(school()));
        }

        @Test
        void testNoAdults() {
            assertEquals(0L, CollectorsEasyChallenge.countAdults(
                    List.of(s("a", "x", "y", 1, 17), s("b", "x", "y", 1, 5))));
        }

        @Test
        void testEmptyInput() {
            assertEquals(0L, CollectorsEasyChallenge.countAdults(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 9: scoreStatistics
    // ==========================================================
    @Nested
    class ScoreStatisticsTests {

        @Test
        void testAllFiveValues() {
            IntSummaryStatistics stats = CollectorsEasyChallenge.scoreStatistics(school());
            assertEquals(6, stats.getCount());
            assertEquals(407, stats.getSum());
            assertEquals(40, stats.getMin());
            assertEquals(90, stats.getMax());
            assertEquals(407.0 / 6, stats.getAverage(), 0.0001);
        }

        @Test
        void testEmptyInputGivesEmptyStatistics() {
            IntSummaryStatistics stats = CollectorsEasyChallenge.scoreStatistics(none());
            assertEquals(0, stats.getCount());
            assertEquals(0, stats.getSum());
        }
    }

    // ==========================================================
    // CHALLENGE 10: countByCourseSorted
    // ==========================================================
    @Nested
    class CountByCourseSortedTests {

        @Test
        void testCountsAndKeysAreAlphabetical() {
            Map<String, Long> result = CollectorsEasyChallenge.countByCourseSorted(school());
            assertEquals(List.of("Art", "Bio", "Math"), new ArrayList<>(result.keySet()));
            assertEquals(2L, result.get("Art"));
            assertEquals(1L, result.get("Bio"));
            assertEquals(3L, result.get("Math"));
        }

        @Test
        void testKeysSortedEvenWhenFirstSeenLast() {
            List<CollectorsEasyChallenge.Student> input = List.of(
                    s("a", "x", "Zoology", 1, 1), s("b", "x", "Algebra", 1, 1), s("c", "x", "Zoology", 1, 1));
            Map<String, Long> result = CollectorsEasyChallenge.countByCourseSorted(input);
            assertEquals(List.of("Algebra", "Zoology"), new ArrayList<>(result.keySet()));
            assertEquals(2L, result.get("Zoology"));
        }

        @Test
        void testEmptyInput() {
            assertTrue(CollectorsEasyChallenge.countByCourseSorted(none()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 11: namesByCourse
    // ==========================================================
    @Nested
    class NamesByCourseTests {

        @Test
        void testGroupsNamesIntoSets() {
            assertEquals(Map.of(
                    "Math", Set.of("Ann", "Bob", "Eli"),
                    "Art", Set.of("Cy", "Dee"),
                    "Bio", Set.of("Fay")), CollectorsEasyChallenge.namesByCourse(school()));
        }

        @Test
        void testValuesAreSets() {
            assertTrue(CollectorsEasyChallenge.namesByCourse(school()).get("Math") instanceof Set);
        }

        @Test
        void testSameNameTwiceInACourseAppearsOnce() {
            List<CollectorsEasyChallenge.Student> input = List.of(
                    s("Ann", "x", "Math", 1, 1), s("Ann", "y", "Math", 2, 1));
            assertEquals(Map.of("Math", Set.of("Ann")), CollectorsEasyChallenge.namesByCourse(input));
        }

        @Test
        void testEmptyInput() {
            assertEquals(Map.of(), CollectorsEasyChallenge.namesByCourse(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 12: topStudentByCourse
    // ==========================================================
    @Nested
    class TopStudentByCourseTests {

        @Test
        void testHighestScorePerCourseWithTieGoingToFirst() {
            List<CollectorsEasyChallenge.Student> students = school();
            Map<String, Optional<CollectorsEasyChallenge.Student>> result =
                    CollectorsEasyChallenge.topStudentByCourse(students);
            assertEquals(Set.of("Math", "Art", "Bio"), result.keySet());
            assertSame(students.get(0), result.get("Math").get());
            assertSame(students.get(2), result.get("Art").get());
            assertSame(students.get(5), result.get("Bio").get());
        }

        @Test
        void testTopStudentIsNotJustTheFirstOne() {
            List<CollectorsEasyChallenge.Student> students = List.of(
                    s("low", "x", "Math", 10, 1), s("high", "x", "Math", 99, 1), s("mid", "x", "Math", 50, 1));
            assertSame(students.get(1), CollectorsEasyChallenge.topStudentByCourse(students).get("Math").get());
        }

        @Test
        void testEmptyInput() {
            assertTrue(CollectorsEasyChallenge.topStudentByCourse(none()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 13: countPassedAndFailed
    // ==========================================================
    @Nested
    class CountPassedAndFailedTests {

        @Test
        void testCountsBothGroups() {
            Map<Boolean, Long> result = CollectorsEasyChallenge.countPassedAndFailed(school());
            assertEquals(4L, result.get(true));
            assertEquals(2L, result.get(false));
        }

        @Test
        void testBoundaryOf60Passes() {
            Map<Boolean, Long> result = CollectorsEasyChallenge.countPassedAndFailed(
                    List.of(s("a", "x", "y", 60, 1), s("b", "x", "y", 59, 1)));
            assertEquals(1L, result.get(true));
            assertEquals(1L, result.get(false));
        }

        @Test
        void testBothKeysPresentWhenNobodyFailed() {
            Map<Boolean, Long> result = CollectorsEasyChallenge.countPassedAndFailed(
                    List.of(s("a", "x", "y", 100, 1)));
            assertEquals(1L, result.get(true));
            assertEquals(0L, result.get(false));
        }

        @Test
        void testBothKeysPresentForEmptyInput() {
            Map<Boolean, Long> result = CollectorsEasyChallenge.countPassedAndFailed(none());
            assertEquals(0L, result.get(true));
            assertEquals(0L, result.get(false));
        }
    }

    // ==========================================================
    // CHALLENGE 14: namesByAdulthood
    // ==========================================================
    @Nested
    class NamesByAdulthoodTests {

        @Test
        void testSplitsNamesAtAge18() {
            Map<Boolean, List<String>> result = CollectorsEasyChallenge.namesByAdulthood(school());
            assertEquals(List.of("Ann", "Cy", "Dee", "Eli"), result.get(true));
            assertEquals(List.of("Bob", "Fay"), result.get(false));
        }

        @Test
        void testBothKeysPresentWhenAllAreAdults() {
            Map<Boolean, List<String>> result = CollectorsEasyChallenge.namesByAdulthood(
                    List.of(s("Ann", "x", "y", 1, 30)));
            assertEquals(List.of("Ann"), result.get(true));
            assertEquals(List.of(), result.get(false));
        }

        @Test
        void testBothKeysPresentForEmptyInput() {
            Map<Boolean, List<String>> result = CollectorsEasyChallenge.namesByAdulthood(none());
            assertEquals(List.of(), result.get(true));
            assertEquals(List.of(), result.get(false));
        }
    }

    // ==========================================================
    // CHALLENGE 15: firstNameByCity
    // ==========================================================
    @Nested
    class FirstNameByCityTests {

        @Test
        void testKeepsTheFirstStudentOfEachCity() {
            assertEquals(Map.of("Lima", "Ann", "Quito", "Bob", "Bogota", "Dee"),
                    CollectorsEasyChallenge.firstNameByCity(school()));
        }

        @Test
        void testDoesNotThrowOnRepeatedCity() {
            assertEquals(Map.of("Lima", "a"), CollectorsEasyChallenge.firstNameByCity(
                    List.of(s("a", "Lima", "x", 1, 1), s("b", "Lima", "x", 1, 1), s("c", "Lima", "x", 1, 1))));
        }

        @Test
        void testEmptyInput() {
            assertEquals(Map.of(), CollectorsEasyChallenge.firstNameByCity(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 16: joinedNamesByCity
    // ==========================================================
    @Nested
    class JoinedNamesByCityTests {

        @Test
        void testJoinsNamesPerCityInOrder() {
            assertEquals(Map.of("Lima", "Ann, Cy", "Quito", "Bob, Eli", "Bogota", "Dee, Fay"),
                    CollectorsEasyChallenge.joinedNamesByCity(school()));
        }

        @Test
        void testSingleStudentCityHasNoSeparator() {
            assertEquals(Map.of("Oslo", "Ann"), CollectorsEasyChallenge.joinedNamesByCity(
                    List.of(s("Ann", "Oslo", "x", 1, 1))));
        }

        @Test
        void testEmptyInput() {
            assertEquals(Map.of(), CollectorsEasyChallenge.joinedNamesByCity(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 17: passedCountByCourse
    // ==========================================================
    @Nested
    class PassedCountByCourseTests {

        @Test
        void testCountsPassedStudentsAndKeepsCoursesWithZero() {
            assertEquals(Map.of("Math", 2L, "Art", 2L, "Bio", 0L),
                    CollectorsEasyChallenge.passedCountByCourse(school()));
        }

        @Test
        void testBoundaryOf60Passes() {
            assertEquals(Map.of("Math", 1L), CollectorsEasyChallenge.passedCountByCourse(
                    List.of(s("a", "x", "Math", 60, 1), s("b", "x", "Math", 59, 1))));
        }

        @Test
        void testEmptyInput() {
            assertEquals(Map.of(), CollectorsEasyChallenge.passedCountByCourse(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 18: distinctCityCount
    // ==========================================================
    @Nested
    class DistinctCityCountTests {

        @Test
        void testCountsEachCityOnce() {
            assertEquals(3, CollectorsEasyChallenge.distinctCityCount(school()));
        }

        @Test
        void testAllSameCity() {
            assertEquals(1, CollectorsEasyChallenge.distinctCityCount(
                    List.of(s("a", "Lima", "x", 1, 1), s("b", "Lima", "x", 1, 1))));
        }

        @Test
        void testEmptyInputGivesZero() {
            assertEquals(0, CollectorsEasyChallenge.distinctCityCount(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 19: countAndTotal
    // ==========================================================
    @Nested
    class CountAndTotalTests {

        @Test
        void testFormatsCountAndTotal() {
            assertEquals("6:407", CollectorsEasyChallenge.countAndTotal(school()));
        }

        @Test
        void testSingleStudent() {
            assertEquals("1:33", CollectorsEasyChallenge.countAndTotal(List.of(s("a", "x", "y", 33, 1))));
        }

        @Test
        void testEmptyInput() {
            assertEquals("0:0", CollectorsEasyChallenge.countAndTotal(none()));
        }
    }

    // ==========================================================
    // CHALLENGE 20: namesByScoreDescending
    // ==========================================================
    @Nested
    class NamesByScoreDescendingTests {

        @Test
        void testGroupsNamesAndKeysAreDescending() {
            Map<Integer, List<String>> result = CollectorsEasyChallenge.namesByScoreDescending(school());
            assertEquals(List.of(90, 72, 60, 55, 40), new ArrayList<>(result.keySet()));
            assertEquals(List.of("Ann", "Eli"), result.get(90));
            assertEquals(List.of("Cy"), result.get(72));
            assertEquals(List.of("Dee"), result.get(60));
            assertEquals(List.of("Bob"), result.get(55));
            assertEquals(List.of("Fay"), result.get(40));
        }

        @Test
        void testKeysAreSortedEvenWhenInputIsAscending() {
            Map<Integer, List<String>> result = CollectorsEasyChallenge.namesByScoreDescending(
                    List.of(s("a", "x", "y", 1, 1), s("b", "x", "y", 5, 1), s("c", "x", "y", 3, 1)));
            assertEquals(List.of(5, 3, 1), new ArrayList<>(result.keySet()));
        }

        @Test
        void testEmptyInput() {
            assertTrue(CollectorsEasyChallenge.namesByScoreDescending(none()).isEmpty());
        }
    }
}