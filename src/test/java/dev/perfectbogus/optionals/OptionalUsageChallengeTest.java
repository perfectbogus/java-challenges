package dev.perfectbogus.optionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalUsageChallengeTest {

    private static OptionalUsageChallenge.Person person(String name, String email, Integer age,
                                                        OptionalUsageChallenge.Address address) {
        return new OptionalUsageChallenge.Person(name, email, age, address);
    }

    private static OptionalUsageChallenge.Person aged(String name, Integer age) {
        return person(name, null, age, null);
    }

    private static OptionalUsageChallenge.Address address(String street, String city) {
        return new OptionalUsageChallenge.Address(street, city);
    }

    // ==========================================================
    // CHALLENGE 1: nameOrDefault
    // ==========================================================
    @Nested
    class NameOrDefaultTests {

        @Test
        void testReturnsTheName() {
            assertEquals("Ana", OptionalUsageChallenge.nameOrDefault("Ana"));
        }

        @Test
        void testNullGivesUnknown() {
            assertEquals("unknown", OptionalUsageChallenge.nameOrDefault(null));
        }

        @Test
        void testEmptyTextIsReturnedAsItIs() {
            assertEquals("", OptionalUsageChallenge.nameOrDefault(""));
        }

        @Test
        void testSpacesAreReturnedAsTheyAre() {
            assertEquals("  ", OptionalUsageChallenge.nameOrDefault("  "));
        }

        @Test
        void testTheWordUnknownIsReturnedAsItIs() {
            assertEquals("unknown", OptionalUsageChallenge.nameOrDefault("unknown"));
        }

        @Test
        void testTheCaseIsKept() {
            assertEquals("aNa", OptionalUsageChallenge.nameOrDefault("aNa"));
        }

        @Test
        void testLongName() {
            assertEquals("Maria de los Angeles", OptionalUsageChallenge.nameOrDefault("Maria de los Angeles"));
        }
    }

    // ==========================================================
    // CHALLENGE 2: parseNumber
    // ==========================================================
    @Nested
    class ParseNumberTests {

        @Test
        void testPositiveNumber() {
            assertEquals(Optional.of(42), OptionalUsageChallenge.parseNumber("42"));
        }

        @Test
        void testNegativeNumber() {
            assertEquals(Optional.of(-7), OptionalUsageChallenge.parseNumber("-7"));
        }

        @Test
        void testZero() {
            assertEquals(Optional.of(0), OptionalUsageChallenge.parseNumber("0"));
        }

        @Test
        void testDecimalIsNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber("4.5"));
        }

        @Test
        void testLettersAreNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber("abc"));
        }

        @Test
        void testMixedTextIsNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber("12abc"));
        }

        @Test
        void testNullGivesEmpty() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber(null));
        }

        @Test
        void testEmptyTextIsNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber(""));
        }

        @Test
        void testSpaceBeforeIsNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber(" 5"));
        }

        @Test
        void testSpaceAfterIsNotValid() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.parseNumber("5 "));
        }

        @Test
        void testTheResultIsNeverNull() {
            assertNotNull(OptionalUsageChallenge.parseNumber("x"));
            assertNotNull(OptionalUsageChallenge.parseNumber(null));
        }
    }

    // ==========================================================
    // CHALLENGE 3: lengthOrZero
    // ==========================================================
    @Nested
    class LengthOrZeroTests {

        @Test
        void testLengthOfText() {
            assertEquals(5, OptionalUsageChallenge.lengthOrZero(Optional.of("hello")));
        }

        @Test
        void testEmptyOptionalGivesZero() {
            assertEquals(0, OptionalUsageChallenge.lengthOrZero(Optional.empty()));
        }

        @Test
        void testEmptyTextGivesZero() {
            assertEquals(0, OptionalUsageChallenge.lengthOrZero(Optional.of("")));
        }

        @Test
        void testSpacesCount() {
            assertEquals(3, OptionalUsageChallenge.lengthOrZero(Optional.of("   ")));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(1, OptionalUsageChallenge.lengthOrZero(Optional.of("x")));
        }

        @Test
        void testLongText() {
            assertEquals(11, OptionalUsageChallenge.lengthOrZero(Optional.of("hello world")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: emailDomain
    // ==========================================================
    @Nested
    class EmailDomainTests {

        @Test
        void testDomainOfEmail() {
            assertEquals("mail.com", OptionalUsageChallenge.emailDomain(person("Ana", "ana@mail.com", 30, null)));
        }

        @Test
        void testNoEmail() {
            assertEquals("no email", OptionalUsageChallenge.emailDomain(person("Bob", null, 25, null)));
        }

        @Test
        void testDomainWithSeveralDots() {
            assertEquals("y.co.uk", OptionalUsageChallenge.emailDomain(person("Cy", "x@y.co.uk", null, null)));
        }

        @Test
        void testShortEmail() {
            assertEquals("b", OptionalUsageChallenge.emailDomain(person("Di", "a@b", null, null)));
        }

        @Test
        void testTheCaseOfTheDomainIsKept() {
            assertEquals("MAIL.com", OptionalUsageChallenge.emailDomain(person("Eve", "eve@MAIL.com", null, null)));
        }

        @Test
        void testLocalPartIsNotPartOfTheResult() {
            assertEquals("z.org", OptionalUsageChallenge.emailDomain(person("Flo", "long.name+tag@z.org", 1, null)));
        }

        @Test
        void testOtherFieldsDoNotMatter() {
            OptionalUsageChallenge.Person p = person("Gus", "gus@g.io", null, address("S", "C"));
            assertEquals("g.io", OptionalUsageChallenge.emailDomain(p));
        }

        @Test
        void testNoEmailWithAddress() {
            OptionalUsageChallenge.Person p = person("Hal", null, 50, address("S", "C"));
            assertEquals("no email", OptionalUsageChallenge.emailDomain(p));
        }
    }

    // ==========================================================
    // CHALLENGE 5: isAdult
    // ==========================================================
    @Nested
    class IsAdultTests {

        @Test
        void testAdult() {
            assertTrue(OptionalUsageChallenge.isAdult(aged("Ana", 30)));
        }

        @Test
        void testExactlyEighteenIsAdult() {
            assertTrue(OptionalUsageChallenge.isAdult(aged("Bob", 18)));
        }

        @Test
        void testSeventeenIsNotAdult() {
            assertFalse(OptionalUsageChallenge.isAdult(aged("Cy", 17)));
        }

        @Test
        void testNoAgeIsNotAdult() {
            assertFalse(OptionalUsageChallenge.isAdult(aged("Di", null)));
        }

        @Test
        void testZeroIsNotAdult() {
            assertFalse(OptionalUsageChallenge.isAdult(aged("Eve", 0)));
        }

        @Test
        void testNineteenIsAdult() {
            assertTrue(OptionalUsageChallenge.isAdult(aged("Flo", 19)));
        }

        @Test
        void testVeryOldIsAdult() {
            assertTrue(OptionalUsageChallenge.isAdult(aged("Gus", 120)));
        }

        @Test
        void testOtherFieldsDoNotMatter() {
            assertFalse(OptionalUsageChallenge.isAdult(person("Hal", "h@h.com", null, address("S", "C"))));
            assertFalse(OptionalUsageChallenge.isAdult(person("Ivy", "i@i.com", 5, address("S", "C"))));
            assertTrue(OptionalUsageChallenge.isAdult(person("Jo", null, 40, null)));
        }
    }

    // ==========================================================
    // CHALLENGE 6: findByName
    // ==========================================================
    @Nested
    class FindByNameTests {

        private List<OptionalUsageChallenge.Person> people() {
            return List.of(aged("Ana", 30), aged("Bob", 25), aged("Ana", 40));
        }

        @Test
        void testFindsAPerson() {
            Optional<OptionalUsageChallenge.Person> result = OptionalUsageChallenge.findByName(people(), "Bob");
            assertTrue(result.isPresent());
            assertEquals(25, result.get().age());
        }

        @Test
        void testFirstMatchIsReturned() {
            Optional<OptionalUsageChallenge.Person> result = OptionalUsageChallenge.findByName(people(), "Ana");
            assertTrue(result.isPresent());
            assertEquals(30, result.get().age());
        }

        @Test
        void testTheSamePersonObjectIsReturned() {
            List<OptionalUsageChallenge.Person> list = people();
            assertSame(list.get(1), OptionalUsageChallenge.findByName(list, "Bob").get());
        }

        @Test
        void testNotFound() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(people(), "Zed"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(people(), "ana"));
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(people(), "ANA"));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(List.of(), "Ana"));
        }

        @Test
        void testPartOfANameIsNotAMatch() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(people(), "An"));
            assertEquals(Optional.empty(), OptionalUsageChallenge.findByName(people(), "Anas"));
        }

        @Test
        void testPersonWithMissingFieldsIsFound() {
            List<OptionalUsageChallenge.Person> list = List.of(person("Cy", null, null, null));
            assertTrue(OptionalUsageChallenge.findByName(list, "Cy").isPresent());
        }

        @Test
        void testListIsNotModified() {
            List<OptionalUsageChallenge.Person> list = new ArrayList<>(people());
            OptionalUsageChallenge.findByName(list, "Ana");
            assertEquals(3, list.size());
        }
    }

    // ==========================================================
    // CHALLENGE 7: presentValues
    // ==========================================================
    @Nested
    class PresentValuesTests {

        @Test
        void testSkipsTheEmptyOnes() {
            List<Optional<String>> values = List.of(Optional.of("a"), Optional.empty(), Optional.of("b"));
            assertEquals(List.of("a", "b"), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testAllEmpty() {
            List<Optional<String>> values = List.of(Optional.empty(), Optional.empty());
            assertEquals(List.of(), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testEmptyList() {
            assertEquals(List.of(), OptionalUsageChallenge.presentValues(List.of()));
        }

        @Test
        void testAllPresent() {
            List<Optional<String>> values = List.of(Optional.of("x"), Optional.of("y"), Optional.of("z"));
            assertEquals(List.of("x", "y", "z"), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testOrderIsKept() {
            List<Optional<String>> values = List.of(Optional.of("z"), Optional.empty(), Optional.of("a"));
            assertEquals(List.of("z", "a"), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testRepeatedValuesAreKept() {
            List<Optional<String>> values = List.of(Optional.of("a"), Optional.of("a"), Optional.empty(),
                    Optional.of("a"));
            assertEquals(List.of("a", "a", "a"), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testEmptyTextIsAValue() {
            List<Optional<String>> values = List.of(Optional.of(""), Optional.empty());
            assertEquals(List.of(""), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testEmptyOnesAtTheStartAndEnd() {
            List<Optional<String>> values = List.of(Optional.empty(), Optional.of("m"), Optional.empty());
            assertEquals(List.of("m"), OptionalUsageChallenge.presentValues(values));
        }

        @Test
        void testResultIsNeverNull() {
            assertNotNull(OptionalUsageChallenge.presentValues(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: logValue
    // ==========================================================
    @Nested
    class LogValueTests {

        @Test
        void testPresentValue() {
            List<String> log = new ArrayList<>();
            OptionalUsageChallenge.logValue(Optional.of("x"), log);
            assertEquals(List.of("found: x"), log);
        }

        @Test
        void testEmptyValue() {
            List<String> log = new ArrayList<>();
            OptionalUsageChallenge.logValue(Optional.empty(), log);
            assertEquals(List.of("missing"), log);
        }

        @Test
        void testLinesAreAddedAtTheEnd() {
            List<String> log = new ArrayList<>(List.of("found: x"));
            OptionalUsageChallenge.logValue(Optional.empty(), log);
            assertEquals(List.of("found: x", "missing"), log);
        }

        @Test
        void testSeveralCalls() {
            List<String> log = new ArrayList<>();
            OptionalUsageChallenge.logValue(Optional.of("a"), log);
            OptionalUsageChallenge.logValue(Optional.empty(), log);
            OptionalUsageChallenge.logValue(Optional.of("b"), log);
            OptionalUsageChallenge.logValue(Optional.empty(), log);
            assertEquals(List.of("found: a", "missing", "found: b", "missing"), log);
        }

        @Test
        void testEmptyTextIsStillFound() {
            List<String> log = new ArrayList<>();
            OptionalUsageChallenge.logValue(Optional.of(""), log);
            assertEquals(List.of("found: "), log);
        }

        @Test
        void testExactlyOneLineIsAdded() {
            List<String> log = new ArrayList<>(List.of("old1", "old2"));
            OptionalUsageChallenge.logValue(Optional.of("v"), log);
            assertEquals(3, log.size());
            OptionalUsageChallenge.logValue(Optional.empty(), log);
            assertEquals(4, log.size());
        }

        @Test
        void testOldLinesAreKept() {
            List<String> log = new ArrayList<>(List.of("old1", "old2"));
            OptionalUsageChallenge.logValue(Optional.of("v"), log);
            assertEquals(List.of("old1", "old2", "found: v"), log);
        }

        @Test
        void testTextWithSpaces() {
            List<String> log = new ArrayList<>();
            OptionalUsageChallenge.logValue(Optional.of("two words"), log);
            assertEquals(List.of("found: two words"), log);
        }
    }

    // ==========================================================
    // CHALLENGE 9: firstAvailable
    // ==========================================================
    @Nested
    class FirstAvailableTests {

        @Test
        void testBothPresentGivesTheFirst() {
            assertEquals("a", OptionalUsageChallenge.firstAvailable(Optional.of("a"), Optional.of("b")));
        }

        @Test
        void testOnlySecondPresent() {
            assertEquals("b", OptionalUsageChallenge.firstAvailable(Optional.empty(), Optional.of("b")));
        }

        @Test
        void testOnlyFirstPresent() {
            assertEquals("a", OptionalUsageChallenge.firstAvailable(Optional.of("a"), Optional.empty()));
        }

        @Test
        void testBothEmpty() {
            assertEquals("none", OptionalUsageChallenge.firstAvailable(Optional.empty(), Optional.empty()));
        }

        @Test
        void testEmptyTextInFirstIsStillPresent() {
            assertEquals("", OptionalUsageChallenge.firstAvailable(Optional.of(""), Optional.of("b")));
        }

        @Test
        void testEmptyTextInSecondIsStillPresent() {
            assertEquals("", OptionalUsageChallenge.firstAvailable(Optional.empty(), Optional.of("")));
        }

        @Test
        void testTheWordNoneInAnOptionalIsReturned() {
            assertEquals("none", OptionalUsageChallenge.firstAvailable(Optional.of("none"), Optional.of("b")));
        }

        @Test
        void testSameValueInBoth() {
            assertEquals("same", OptionalUsageChallenge.firstAvailable(Optional.of("same"), Optional.of("same")));
        }
    }

    // ==========================================================
    // CHALLENGE 10: requireName
    // ==========================================================
    @Nested
    class RequireNameTests {

        @Test
        void testReturnsTheName() {
            assertEquals("Ana", OptionalUsageChallenge.requireName(person("Ana", null, null, null)));
        }

        @Test
        void testEmptyNameIsValid() {
            assertEquals("", OptionalUsageChallenge.requireName(person("", null, null, null)));
        }

        @Test
        void testNullNameThrowsIllegalArgumentException() {
            boolean thrown = false;
            try {
                OptionalUsageChallenge.requireName(person(null, null, null, null));
            } catch (IllegalArgumentException e) {
                thrown = true;
            }
            assertTrue(thrown);
        }

        @Test
        void testMessageOfTheException() {
            String message = null;
            try {
                OptionalUsageChallenge.requireName(person(null, "a@b.c", 20, null));
            } catch (IllegalArgumentException e) {
                message = e.getMessage();
            }
            assertEquals("name is required", message);
        }

        @Test
        void testOtherFieldsDoNotMatter() {
            OptionalUsageChallenge.Person p = person("Bob", "b@b.com", 33, address("S", "C"));
            assertEquals("Bob", OptionalUsageChallenge.requireName(p));
        }

        @Test
        void testSpacesAreAValidName() {
            assertEquals("  ", OptionalUsageChallenge.requireName(person("  ", null, null, null)));
        }

        @Test
        void testTheExceptionIsNotAnotherType() {
            Throwable caught = null;
            try {
                OptionalUsageChallenge.requireName(person(null, null, null, null));
            } catch (RuntimeException e) {
                caught = e;
            }
            assertNotNull(caught);
            assertSame(IllegalArgumentException.class, caught.getClass());
        }
    }

    // ==========================================================
    // CHALLENGE 11 (medium): cityOfOldest
    // ==========================================================
    @Nested
    class CityOfOldestTests {

        @Test
        void testExampleWithTheOldestHavingACity() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 30, address("Main St", "Lima")),
                    person("Bob", null, 45, address("Oak St", "Quito")),
                    person("Cy", null, null, address("Elm St", "Rome")));
            assertEquals(Optional.of("QUITO"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testOldestWithoutAddressGivesEmpty() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 30, address("Main St", "Lima")),
                    person("Bob", null, 45, null));
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testNobodyHasAnAge() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, null, address("Main St", "Lima")));
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(List.of()));
        }

        @Test
        void testOldestWithAddressButNoCityGivesEmpty() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 30, address("Main St", "Lima")),
                    person("Bob", null, 45, address("Oak St", null)));
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTieTheFirstOneIsUsed() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 50, address("S", "Lima")),
                    person("Bob", null, 50, address("S", "Quito")));
            assertEquals(Optional.of("LIMA"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTieWhenTheFirstHasNoCityGivesEmpty() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 50, null),
                    person("Bob", null, 50, address("S", "Quito")));
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTieWhenOnlyTheSecondHasNoCity() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 50, address("S", "Lima")),
                    person("Bob", null, 50, null));
            assertEquals(Optional.of("LIMA"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTieOfThreeUsesTheFirst() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("A", null, 10, address("S", "Z")),
                    person("B", null, 60, address("S", "Rome")),
                    person("C", null, 60, address("S", "Paris")),
                    person("D", null, 60, address("S", "Oslo")));
            assertEquals(Optional.of("ROME"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testCityIsInUpperCase() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 30, address("Main St", "new york")));
            assertEquals(Optional.of("NEW YORK"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testSingleOldPerson() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 99, address(null, "Oslo")));
            assertEquals(Optional.of("OSLO"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testPeopleWithoutAgeAreIgnoredEvenWithACity() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, null, address("S", "Lima")),
                    person("Bob", null, 20, address("S", "Quito")),
                    person("Cy", null, null, address("S", "Rome")));
            assertEquals(Optional.of("QUITO"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTheOldestIsInTheLastPosition() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 20, address("S", "Lima")),
                    person("Bob", null, 30, address("S", "Quito")),
                    person("Cy", null, 70, address("S", "Rome")));
            assertEquals(Optional.of("ROME"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testTheOldestIsInTheFirstPosition() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 80, address("S", "Lima")),
                    person("Bob", null, 30, address("S", "Quito")),
                    person("Cy", null, 70, address("S", "Rome")));
            assertEquals(Optional.of("LIMA"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testAgeZeroIsStillAnAge() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, null, address("S", "Lima")),
                    person("Bob", null, 0, address("S", "Quito")));
            assertEquals(Optional.of("QUITO"), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testYoungerPeopleDoNotReplaceAnOldestWithoutCity() {
            List<OptionalUsageChallenge.Person> people = List.of(
                    person("Ana", null, 20, address("S", "Lima")),
                    person("Bob", null, 30, address("S", "Quito")),
                    person("Cy", null, 70, address("S", null)));
            assertEquals(Optional.empty(), OptionalUsageChallenge.cityOfOldest(people));
        }

        @Test
        void testListIsNotModified() {
            List<OptionalUsageChallenge.Person> people = new ArrayList<>(List.of(
                    person("Ana", null, 20, address("S", "Lima")),
                    person("Bob", null, 30, address("S", "Quito"))));
            OptionalUsageChallenge.cityOfOldest(people);
            assertEquals(2, people.size());
            assertEquals("Ana", people.get(0).name());
        }

        @Test
        void testTheResultIsNeverNull() {
            assertNotNull(OptionalUsageChallenge.cityOfOldest(List.of()));
            assertNotNull(OptionalUsageChallenge.cityOfOldest(List.of(aged("Ana", 3))));
        }
    }
}