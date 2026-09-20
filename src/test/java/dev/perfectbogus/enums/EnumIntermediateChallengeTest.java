package dev.perfectbogus.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EnumIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: isWeekendDay
    // ==========================================================
    @Nested
    class IsWeekendDayTests {

        @Test
        void testSaturdayIsWeekend() {
            assertTrue(EnumIntermediateChallenge.isWeekendDay(EnumIntermediateChallenge.Weekday.SATURDAY));
        }

        @Test
        void testSundayIsWeekend() {
            assertTrue(EnumIntermediateChallenge.isWeekendDay(EnumIntermediateChallenge.Weekday.SUNDAY));
        }

        @Test
        void testWeekdayIsNotWeekend() {
            assertFalse(EnumIntermediateChallenge.isWeekendDay(EnumIntermediateChallenge.Weekday.WEDNESDAY));
        }
    }

    // ==========================================================
    // CHALLENGE 2: findStatusByCode
    // ==========================================================
    @Nested
    class FindStatusByCodeTests {

        @Test
        void testFindsExistingCode() {
            assertEquals(Optional.of(EnumIntermediateChallenge.HttpStatus.NOT_FOUND),
                    EnumIntermediateChallenge.findStatusByCode(404));
        }

        @Test
        void testFindsFirstDeclaredStatus() {
            assertEquals(Optional.of(EnumIntermediateChallenge.HttpStatus.OK),
                    EnumIntermediateChallenge.findStatusByCode(200));
        }

        @Test
        void testUnknownCodeReturnsEmpty() {
            assertEquals(Optional.empty(), EnumIntermediateChallenge.findStatusByCode(999));
        }
    }

    // ==========================================================
    // CHALLENGE 3: applyOperation
    // ==========================================================
    @Nested
    class ApplyOperationTests {

        @Test
        void testPlus() {
            assertEquals(7, EnumIntermediateChallenge.applyOperation(EnumIntermediateChallenge.Operation.PLUS, 3, 4));
        }

        @Test
        void testMinus() {
            assertEquals(-1, EnumIntermediateChallenge.applyOperation(EnumIntermediateChallenge.Operation.MINUS, 3, 4));
        }

        @Test
        void testTimes() {
            assertEquals(12, EnumIntermediateChallenge.applyOperation(EnumIntermediateChallenge.Operation.TIMES, 3, 4));
        }

        @Test
        void testDivide() {
            assertEquals(2, EnumIntermediateChallenge.applyOperation(EnumIntermediateChallenge.Operation.DIVIDE, 8, 4));
        }
    }

    // ==========================================================
    // CHALLENGE 4: describeStatus
    // ==========================================================
    @Nested
    class DescribeStatusTests {

        @Test
        void testOkIsSuccess() {
            assertEquals("Success", EnumIntermediateChallenge.describeStatus(EnumIntermediateChallenge.HttpStatus.OK));
        }

        @Test
        void testCreatedIsSuccess() {
            assertEquals("Success", EnumIntermediateChallenge.describeStatus(EnumIntermediateChallenge.HttpStatus.CREATED));
        }

        @Test
        void testNotFoundIsClientError() {
            assertEquals("Client Error", EnumIntermediateChallenge.describeStatus(EnumIntermediateChallenge.HttpStatus.NOT_FOUND));
        }

        @Test
        void testForbiddenIsClientError() {
            assertEquals("Client Error", EnumIntermediateChallenge.describeStatus(EnumIntermediateChallenge.HttpStatus.FORBIDDEN));
        }

        @Test
        void testServerErrorIsServerError() {
            assertEquals("Server Error", EnumIntermediateChallenge.describeStatus(EnumIntermediateChallenge.HttpStatus.SERVER_ERROR));
        }
    }

    // ==========================================================
    // CHALLENGE 5: countOccurrences
    // ==========================================================
    @Nested
    class CountOccurrencesTests {

        @Test
        void testCountsCorrectly() {
            List<EnumIntermediateChallenge.Weekday> days = List.of(
                    EnumIntermediateChallenge.Weekday.FRIDAY,
                    EnumIntermediateChallenge.Weekday.MONDAY,
                    EnumIntermediateChallenge.Weekday.FRIDAY
            );
            EnumMap<EnumIntermediateChallenge.Weekday, Integer> result = EnumIntermediateChallenge.countOccurrences(days);
            assertEquals(2, result.get(EnumIntermediateChallenge.Weekday.FRIDAY));
            assertEquals(1, result.get(EnumIntermediateChallenge.Weekday.MONDAY));
        }

        @Test
        void testIterationFollowsDeclarationOrder() {
            List<EnumIntermediateChallenge.Weekday> days = List.of(
                    EnumIntermediateChallenge.Weekday.FRIDAY,
                    EnumIntermediateChallenge.Weekday.MONDAY,
                    EnumIntermediateChallenge.Weekday.WEDNESDAY
            );
            EnumMap<EnumIntermediateChallenge.Weekday, Integer> result = EnumIntermediateChallenge.countOccurrences(days);
            assertEquals(
                    List.of(EnumIntermediateChallenge.Weekday.MONDAY, EnumIntermediateChallenge.Weekday.WEDNESDAY, EnumIntermediateChallenge.Weekday.FRIDAY),
                    new ArrayList<>(result.keySet())
            );
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(EnumIntermediateChallenge.countOccurrences(List.of()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 6: highestPriority
    // ==========================================================
    @Nested
    class HighestPriorityTests {

        @Test
        void testFindsHigh() {
            List<EnumIntermediateChallenge.Priority> priorities = List.of(
                    EnumIntermediateChallenge.Priority.LOW,
                    EnumIntermediateChallenge.Priority.HIGH,
                    EnumIntermediateChallenge.Priority.MEDIUM
            );
            assertEquals(EnumIntermediateChallenge.Priority.HIGH, EnumIntermediateChallenge.highestPriority(priorities));
        }

        @Test
        void testAllSamePriority() {
            List<EnumIntermediateChallenge.Priority> priorities = List.of(
                    EnumIntermediateChallenge.Priority.MEDIUM,
                    EnumIntermediateChallenge.Priority.MEDIUM
            );
            assertEquals(EnumIntermediateChallenge.Priority.MEDIUM, EnumIntermediateChallenge.highestPriority(priorities));
        }

        @Test
        void testSingleElement() {
            assertEquals(EnumIntermediateChallenge.Priority.LOW,
                    EnumIntermediateChallenge.highestPriority(List.of(EnumIntermediateChallenge.Priority.LOW)));
        }
    }

    // ==========================================================
    // CHALLENGE 7: allPermissionsExcept
    // ==========================================================
    @Nested
    class AllPermissionsExceptTests {

        @Test
        void testExcludesGivenPermissions() {
            EnumSet<EnumIntermediateChallenge.Permission> result =
                    EnumIntermediateChallenge.allPermissionsExcept(EnumIntermediateChallenge.Permission.DELETE);
            assertFalse(result.contains(EnumIntermediateChallenge.Permission.DELETE));
        }

        @Test
        void testIncludesEverythingElse() {
            EnumSet<EnumIntermediateChallenge.Permission> result =
                    EnumIntermediateChallenge.allPermissionsExcept(EnumIntermediateChallenge.Permission.DELETE);
            assertEquals(3, result.size());
            assertTrue(result.contains(EnumIntermediateChallenge.Permission.READ));
            assertTrue(result.contains(EnumIntermediateChallenge.Permission.WRITE));
            assertTrue(result.contains(EnumIntermediateChallenge.Permission.EXECUTE));
        }

        @Test
        void testNoExclusionsReturnsEverything() {
            EnumSet<EnumIntermediateChallenge.Permission> result = EnumIntermediateChallenge.allPermissionsExcept();
            assertEquals(4, result.size());
        }
    }

    // ==========================================================
    // CHALLENGE 8: hasAllPermissions
    // ==========================================================
    @Nested
    class HasAllPermissionsTests {

        @Test
        void testTrueWhenAllPresent() {
            EnumSet<EnumIntermediateChallenge.Permission> granted = EnumSet.of(
                    EnumIntermediateChallenge.Permission.READ, EnumIntermediateChallenge.Permission.WRITE, EnumIntermediateChallenge.Permission.EXECUTE
            );
            EnumSet<EnumIntermediateChallenge.Permission> required = EnumSet.of(
                    EnumIntermediateChallenge.Permission.READ, EnumIntermediateChallenge.Permission.WRITE
            );
            assertTrue(EnumIntermediateChallenge.hasAllPermissions(granted, required));
        }

        @Test
        void testFalseWhenMissingOne() {
            EnumSet<EnumIntermediateChallenge.Permission> granted = EnumSet.of(EnumIntermediateChallenge.Permission.READ);
            EnumSet<EnumIntermediateChallenge.Permission> required = EnumSet.of(
                    EnumIntermediateChallenge.Permission.READ, EnumIntermediateChallenge.Permission.DELETE
            );
            assertFalse(EnumIntermediateChallenge.hasAllPermissions(granted, required));
        }

        @Test
        void testEmptyRequiredIsAlwaysTrue() {
            EnumSet<EnumIntermediateChallenge.Permission> granted = EnumSet.noneOf(EnumIntermediateChallenge.Permission.class);
            assertTrue(EnumIntermediateChallenge.hasAllPermissions(granted, EnumSet.noneOf(EnumIntermediateChallenge.Permission.class)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: parseWeekdaySafely
    // ==========================================================
    @Nested
    class ParseWeekdaySafelyTests {

        @Test
        void testValidName() {
            assertEquals(Optional.of(EnumIntermediateChallenge.Weekday.MONDAY), EnumIntermediateChallenge.parseWeekdaySafely("MONDAY"));
        }

        @Test
        void testInvalidNameReturnsEmpty() {
            assertEquals(Optional.empty(), EnumIntermediateChallenge.parseWeekdaySafely("NOTADAY"));
        }

        @Test
        void testLowercaseNameReturnsEmpty() {
            assertEquals(Optional.empty(), EnumIntermediateChallenge.parseWeekdaySafely("monday"));
        }
    }

    // ==========================================================
    // CHALLENGE 10: nextWeekday
    // ==========================================================
    @Nested
    class NextWeekdayTests {

        @Test
        void testMidWeek() {
            assertEquals(EnumIntermediateChallenge.Weekday.TUESDAY, EnumIntermediateChallenge.nextWeekday(EnumIntermediateChallenge.Weekday.MONDAY));
        }

        @Test
        void testWrapsFromSundayToMonday() {
            assertEquals(EnumIntermediateChallenge.Weekday.MONDAY, EnumIntermediateChallenge.nextWeekday(EnumIntermediateChallenge.Weekday.SUNDAY));
        }

        @Test
        void testFridayToSaturday() {
            assertEquals(EnumIntermediateChallenge.Weekday.SATURDAY, EnumIntermediateChallenge.nextWeekday(EnumIntermediateChallenge.Weekday.FRIDAY));
        }
    }

    // ==========================================================
    // CHALLENGE 11: sumOrdinalValues
    // ==========================================================
    @Nested
    class SumOrdinalValuesTests {

        @Test
        void testSumsOrdinals() {
            // LOW=0, MEDIUM=1, HIGH=2 -> 0 + 1 + 2 = 3
            List<EnumIntermediateChallenge.Priority> priorities = List.of(
                    EnumIntermediateChallenge.Priority.LOW, EnumIntermediateChallenge.Priority.MEDIUM, EnumIntermediateChallenge.Priority.HIGH
            );
            assertEquals(3, EnumIntermediateChallenge.sumOrdinalValues(priorities));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, EnumIntermediateChallenge.sumOrdinalValues(List.of()));
        }

        @Test
        void testRepeatedValues() {
            // HIGH=2, HIGH=2 -> 4
            List<EnumIntermediateChallenge.Priority> priorities = List.of(
                    EnumIntermediateChallenge.Priority.HIGH, EnumIntermediateChallenge.Priority.HIGH
            );
            assertEquals(4, EnumIntermediateChallenge.sumOrdinalValues(priorities));
        }
    }

    // ==========================================================
    // CHALLENGE 12: symbolForOperation
    // ==========================================================
    @Nested
    class SymbolForOperationTests {

        @Test
        void testPlusSymbol() {
            assertEquals("+", EnumIntermediateChallenge.symbolForOperation(EnumIntermediateChallenge.Operation.PLUS));
        }

        @Test
        void testDivideSymbol() {
            assertEquals("/", EnumIntermediateChallenge.symbolForOperation(EnumIntermediateChallenge.Operation.DIVIDE));
        }
    }

    // ==========================================================
    // CHALLENGE 13: groupTasksByPriority
    // ==========================================================
    @Nested
    class GroupTasksByPriorityTests {

        @Test
        void testGroupsCorrectly() {
            List<EnumIntermediateChallenge.Task> tasks = List.of(
                    new EnumIntermediateChallenge.Task("a", EnumIntermediateChallenge.Priority.HIGH),
                    new EnumIntermediateChallenge.Task("b", EnumIntermediateChallenge.Priority.LOW),
                    new EnumIntermediateChallenge.Task("c", EnumIntermediateChallenge.Priority.HIGH)
            );
            Map<EnumIntermediateChallenge.Priority, List<EnumIntermediateChallenge.Task>> result =
                    EnumIntermediateChallenge.groupTasksByPriority(tasks);
            assertEquals(2, result.get(EnumIntermediateChallenge.Priority.HIGH).size());
            assertEquals(1, result.get(EnumIntermediateChallenge.Priority.LOW).size());
        }

        @Test
        void testIterationFollowsDeclarationOrder() {
            List<EnumIntermediateChallenge.Task> tasks = List.of(
                    new EnumIntermediateChallenge.Task("a", EnumIntermediateChallenge.Priority.HIGH),
                    new EnumIntermediateChallenge.Task("b", EnumIntermediateChallenge.Priority.LOW)
            );
            Map<EnumIntermediateChallenge.Priority, List<EnumIntermediateChallenge.Task>> result =
                    EnumIntermediateChallenge.groupTasksByPriority(tasks);
            assertEquals(
                    List.of(EnumIntermediateChallenge.Priority.LOW, EnumIntermediateChallenge.Priority.HIGH),
                    new ArrayList<>(result.keySet())
            );
        }

        @Test
        void testEmptyListReturnsEmptyMap() {
            assertTrue(EnumIntermediateChallenge.groupTasksByPriority(List.of()).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 14: sortStatusesByCode
    // ==========================================================
    @Nested
    class SortStatusesByCodeTests {

        @Test
        void testSortsAscendingByCode() {
            List<EnumIntermediateChallenge.HttpStatus> statuses = List.of(
                    EnumIntermediateChallenge.HttpStatus.NOT_FOUND,
                    EnumIntermediateChallenge.HttpStatus.OK,
                    EnumIntermediateChallenge.HttpStatus.FORBIDDEN
            );
            assertEquals(
                    List.of(EnumIntermediateChallenge.HttpStatus.OK, EnumIntermediateChallenge.HttpStatus.FORBIDDEN, EnumIntermediateChallenge.HttpStatus.NOT_FOUND),
                    EnumIntermediateChallenge.sortStatusesByCode(statuses)
            );
        }

        @Test
        void testOriginalListNotModified() {
            List<EnumIntermediateChallenge.HttpStatus> statuses = new ArrayList<>(List.of(
                    EnumIntermediateChallenge.HttpStatus.NOT_FOUND, EnumIntermediateChallenge.HttpStatus.OK
            ));
            List<EnumIntermediateChallenge.HttpStatus> originalCopy = new ArrayList<>(statuses);
            EnumIntermediateChallenge.sortStatusesByCode(statuses);
            assertEquals(originalCopy, statuses);
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), EnumIntermediateChallenge.sortStatusesByCode(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 15: describeAllOperations
    // ==========================================================
    @Nested
    class DescribeAllOperationsTests {

        @Test
        void testContainsAllFourDescriptions() {
            assertEquals(
                    List.of("PLUS: +", "MINUS: -", "TIMES: *", "DIVIDE: /"),
                    EnumIntermediateChallenge.describeAllOperations()
            );
        }

        @Test
        void testSizeMatchesNumberOfConstants() {
            assertEquals(4, EnumIntermediateChallenge.describeAllOperations().size());
        }
    }
}