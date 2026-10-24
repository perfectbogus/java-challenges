package dev.perfectbogus.objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ObjectsCoreChallengeTest {

    private static ObjectsCoreChallenge.Point pt(int x, int y) {
        return new ObjectsCoreChallenge.Point(x, y);
    }

    private static ObjectsCoreChallenge.Person person(String name, int age) {
        return new ObjectsCoreChallenge.Person(name, age);
    }

    private static ObjectsCoreChallenge.Book book(String title, String author) {
        return new ObjectsCoreChallenge.Book(title, author);
    }

    // ==========================================================
    // CHALLENGE 1: sameOrBothNull
    // ==========================================================
    @Nested
    class SameOrBothNullTests {

        @Test
        void testSameContent() {
            assertTrue(ObjectsCoreChallenge.sameOrBothNull("a", "a"));
        }

        @Test
        void testBothNull() {
            assertTrue(ObjectsCoreChallenge.sameOrBothNull(null, null));
        }

        @Test
        void testSecondNull() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull("a", null));
        }

        @Test
        void testFirstNull() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull(null, "a"));
        }

        @Test
        void testDifferentCase() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull("a", "A"));
        }

        @Test
        void testSameContentDifferentObjects() {
            assertTrue(ObjectsCoreChallenge.sameOrBothNull(new String("x"), new String("x")));
        }

        @Test
        void testEmptyTexts() {
            assertTrue(ObjectsCoreChallenge.sameOrBothNull("", ""));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull("", null));
            assertFalse(ObjectsCoreChallenge.sameOrBothNull(null, ""));
        }

        @Test
        void testDifferentTexts() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull("abc", "abd"));
        }

        @Test
        void testSpacesMatter() {
            assertFalse(ObjectsCoreChallenge.sameOrBothNull("a", "a "));
        }
    }

    // ==========================================================
    // CHALLENGE 2: describe
    // ==========================================================
    @Nested
    class DescribeTests {

        @Test
        void testNumber() {
            assertEquals("5", ObjectsCoreChallenge.describe(5));
        }

        @Test
        void testText() {
            assertEquals("hi", ObjectsCoreChallenge.describe("hi"));
        }

        @Test
        void testList() {
            assertEquals("[1, 2]", ObjectsCoreChallenge.describe(List.of(1, 2)));
        }

        @Test
        void testEmptyText() {
            assertEquals("", ObjectsCoreChallenge.describe(""));
        }

        @Test
        void testNull() {
            assertEquals("nothing", ObjectsCoreChallenge.describe(null));
        }

        @Test
        void testDecimalNumber() {
            assertEquals("3.5", ObjectsCoreChallenge.describe(3.5));
        }

        @Test
        void testBoolean() {
            assertEquals("false", ObjectsCoreChallenge.describe(false));
        }

        @Test
        void testTheCaseOfTheTextIsKept() {
            assertEquals("HELLO", ObjectsCoreChallenge.describe("HELLO"));
            assertEquals("Ana", ObjectsCoreChallenge.describe("Ana"));
        }

        @Test
        void testTheWordNothingIsNotChanged() {
            assertEquals("nothing", ObjectsCoreChallenge.describe("nothing"));
        }

        @Test
        void testTheTextNullIsNotNull() {
            assertEquals("null", ObjectsCoreChallenge.describe("null"));
        }

        @Test
        void testObjectWithItsOwnToString() {
            Object thing = new Object() {
                @Override
                public String toString() {
                    return "custom";
                }
            };
            assertEquals("custom", ObjectsCoreChallenge.describe(thing));
        }

        @Test
        void testEmptyList() {
            assertEquals("[]", ObjectsCoreChallenge.describe(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: countNulls
    // ==========================================================
    @Nested
    class CountNullsTests {

        @Test
        void testSomeNulls() {
            assertEquals(2, ObjectsCoreChallenge.countNulls(Arrays.asList(null, "a", null)));
        }

        @Test
        void testNoNulls() {
            assertEquals(0, ObjectsCoreChallenge.countNulls(List.of("a", "b")));
        }

        @Test
        void testTheTextNullIsNotNull() {
            assertEquals(1, ObjectsCoreChallenge.countNulls(Arrays.asList("null", null)));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, ObjectsCoreChallenge.countNulls(new ArrayList<>()));
        }

        @Test
        void testAllNull() {
            assertEquals(3, ObjectsCoreChallenge.countNulls(Arrays.asList(null, null, null)));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertEquals(1, ObjectsCoreChallenge.countNulls(Arrays.asList("", null, "")));
        }

        @Test
        void testNullsAtTheEnds() {
            assertEquals(2, ObjectsCoreChallenge.countNulls(Arrays.asList(null, "x", "y", null)));
        }

        @Test
        void testListIsNotModified() {
            List<String> items = new ArrayList<>(Arrays.asList(null, "a"));
            ObjectsCoreChallenge.countNulls(items);
            assertEquals(2, items.size());
        }
    }

    // ==========================================================
    // CHALLENGE 4: checkName
    // ==========================================================
    @Nested
    class CheckNameTests {

        @Test
        void testReturnsTheName() {
            assertEquals("Ana", ObjectsCoreChallenge.checkName("Ana"));
        }

        @Test
        void testEmptyNameIsValid() {
            assertEquals("", ObjectsCoreChallenge.checkName(""));
        }

        @Test
        void testNullThrowsNullPointerException() {
            boolean thrown = false;
            try {
                ObjectsCoreChallenge.checkName(null);
            } catch (NullPointerException e) {
                thrown = true;
            }
            assertTrue(thrown);
        }

        @Test
        void testMessageOfTheException() {
            String message = "none";
            try {
                ObjectsCoreChallenge.checkName(null);
            } catch (NullPointerException e) {
                message = e.getMessage();
            }
            assertEquals("name must not be null", message);
        }

        @Test
        void testSameObjectIsReturned() {
            String name = new String("Bob");
            assertSame(name, ObjectsCoreChallenge.checkName(name));
        }

        @Test
        void testSpacesAreValid() {
            assertEquals("  ", ObjectsCoreChallenge.checkName("  "));
        }

        @Test
        void testTheExceptionIsNotAnotherType() {
            Throwable caught = null;
            try {
                ObjectsCoreChallenge.checkName(null);
            } catch (RuntimeException e) {
                caught = e;
            }
            assertNotNull(caught);
            assertSame(NullPointerException.class, caught.getClass());
        }
    }

    // ==========================================================
    // CHALLENGE 5: firstNonNull
    // ==========================================================
    @Nested
    class FirstNonNullTests {

        @Test
        void testFirstIsUsed() {
            assertEquals("a", ObjectsCoreChallenge.firstNonNull("a", "b"));
        }

        @Test
        void testSecondIsUsedWhenFirstIsNull() {
            assertEquals("b", ObjectsCoreChallenge.firstNonNull(null, "b"));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertEquals("", ObjectsCoreChallenge.firstNonNull("", "b"));
        }

        @Test
        void testSecondCanBeNullWhenFirstIsNotNull() {
            assertEquals("a", ObjectsCoreChallenge.firstNonNull("a", null));
        }

        @Test
        void testBothNullThrowsNullPointerException() {
            boolean thrown = false;
            try {
                ObjectsCoreChallenge.firstNonNull(null, null);
            } catch (NullPointerException e) {
                thrown = true;
            }
            assertTrue(thrown);
        }

        @Test
        void testSameObjectIsReturned() {
            String first = new String("x");
            assertSame(first, ObjectsCoreChallenge.firstNonNull(first, "y"));
        }

        @Test
        void testSameObjectIsReturnedFromSecond() {
            String second = new String("y");
            assertSame(second, ObjectsCoreChallenge.firstNonNull(null, second));
        }

        @Test
        void testEmptyTextInSecond() {
            assertEquals("", ObjectsCoreChallenge.firstNonNull(null, ""));
        }
    }

    // ==========================================================
    // CHALLENGE 6: allSame
    // ==========================================================
    @Nested
    class AllSameTests {

        @Test
        void testAllEqual() {
            assertTrue(ObjectsCoreChallenge.allSame(List.of("a", "a", "a")));
        }

        @Test
        void testOneDifferentAtTheEnd() {
            assertFalse(ObjectsCoreChallenge.allSame(List.of("a", "a", "b")));
        }

        @Test
        void testBothNull() {
            assertTrue(ObjectsCoreChallenge.allSame(Arrays.asList(null, null)));
        }

        @Test
        void testNullAndText() {
            assertFalse(ObjectsCoreChallenge.allSame(Arrays.asList("a", null)));
            assertFalse(ObjectsCoreChallenge.allSame(Arrays.asList(null, "a")));
        }

        @Test
        void testEmptyList() {
            assertTrue(ObjectsCoreChallenge.allSame(new ArrayList<>()));
        }

        @Test
        void testSingleElement() {
            assertTrue(ObjectsCoreChallenge.allSame(List.of("a")));
            assertTrue(ObjectsCoreChallenge.allSame(Arrays.asList((String) null)));
        }

        @Test
        void testOneDifferentAtTheStart() {
            assertFalse(ObjectsCoreChallenge.allSame(List.of("b", "a", "a")));
        }

        @Test
        void testOneDifferentInTheMiddle() {
            assertFalse(ObjectsCoreChallenge.allSame(List.of("a", "b", "a")));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(ObjectsCoreChallenge.allSame(List.of("a", "A")));
        }

        @Test
        void testSameContentInDifferentObjects() {
            assertTrue(ObjectsCoreChallenge.allSame(List.of(new String("x"), new String("x"), new String("x"))));
        }

        @Test
        void testAllNullButOne() {
            assertFalse(ObjectsCoreChallenge.allSame(Arrays.asList(null, null, "a")));
        }

        @Test
        void testAlternatingValues() {
            assertFalse(ObjectsCoreChallenge.allSame(List.of("a", "b", "a", "b")));
        }
    }

    // ==========================================================
    // CHALLENGE 7: compareTexts
    // ==========================================================
    @Nested
    class CompareTextsTests {

        @Test
        void testSameContentDifferentObjects() {
            String s = new String("x");
            String t = new String("x");
            assertArrayEquals(new boolean[]{false, true}, ObjectsCoreChallenge.compareTexts(s, t));
        }

        @Test
        void testSameObject() {
            String s = new String("x");
            assertArrayEquals(new boolean[]{true, true}, ObjectsCoreChallenge.compareTexts(s, s));
        }

        @Test
        void testDifferentTexts() {
            assertArrayEquals(new boolean[]{false, false}, ObjectsCoreChallenge.compareTexts("x", "y"));
        }

        @Test
        void testBothNull() {
            assertArrayEquals(new boolean[]{true, true}, ObjectsCoreChallenge.compareTexts(null, null));
        }

        @Test
        void testOneNull() {
            assertArrayEquals(new boolean[]{false, false}, ObjectsCoreChallenge.compareTexts(null, "x"));
            assertArrayEquals(new boolean[]{false, false}, ObjectsCoreChallenge.compareTexts("x", null));
        }

        @Test
        void testTwoLiteralsAreTheSameObject() {
            assertArrayEquals(new boolean[]{true, true}, ObjectsCoreChallenge.compareTexts("x", "x"));
        }

        @Test
        void testTextBuiltAtRunTime() {
            String built = new StringBuilder("ab").toString();
            assertArrayEquals(new boolean[]{false, true}, ObjectsCoreChallenge.compareTexts(built, "ab"));
        }

        @Test
        void testTwoEmptyObjects() {
            assertArrayEquals(new boolean[]{false, true},
                    ObjectsCoreChallenge.compareTexts(new String(""), new String("")));
        }

        @Test
        void testDifferentCase() {
            assertArrayEquals(new boolean[]{false, false}, ObjectsCoreChallenge.compareTexts("x", "X"));
        }

        @Test
        void testResultHasTwoValues() {
            assertEquals(2, ObjectsCoreChallenge.compareTexts("a", "b").length);
        }
    }

    // ==========================================================
    // CHALLENGE 8: Point.equals
    // ==========================================================
    @Nested
    class PointEqualsTests {

        @Test
        void testSameCoordinates() {
            assertTrue(pt(1, 2).equals(pt(1, 2)));
        }

        @Test
        void testSwappedCoordinates() {
            assertFalse(pt(1, 2).equals(pt(2, 1)));
        }

        @Test
        void testDifferentX() {
            assertFalse(pt(1, 2).equals(pt(3, 2)));
        }

        @Test
        void testDifferentY() {
            assertFalse(pt(1, 2).equals(pt(1, 3)));
        }

        @Test
        void testNull() {
            assertFalse(pt(1, 2).equals(null));
        }

        @Test
        void testOtherType() {
            assertFalse(pt(1, 2).equals("(1, 2)"));
            assertFalse(pt(1, 2).equals(Integer.valueOf(1)));
        }

        @Test
        void testSameObject() {
            ObjectsCoreChallenge.Point p = pt(5, 6);
            assertTrue(p.equals(p));
        }

        @Test
        void testSymmetric() {
            ObjectsCoreChallenge.Point a = pt(-4, 9);
            ObjectsCoreChallenge.Point b = pt(-4, 9);
            assertTrue(a.equals(b));
            assertTrue(b.equals(a));
        }

        @Test
        void testSameSumDifferentPoints() {
            assertFalse(pt(0, 3).equals(pt(3, 0)));
            assertFalse(pt(1, 4).equals(pt(2, 3)));
        }

        @Test
        void testOrigin() {
            assertTrue(pt(0, 0).equals(pt(0, 0)));
            assertFalse(pt(0, 0).equals(pt(0, 1)));
        }

        @Test
        void testNegativeCoordinates() {
            assertTrue(pt(-1, -2).equals(pt(-1, -2)));
            assertFalse(pt(-1, -2).equals(pt(1, 2)));
        }

        @Test
        void testBigCoordinates() {
            assertTrue(pt(Integer.MAX_VALUE, Integer.MIN_VALUE).equals(pt(Integer.MAX_VALUE, Integer.MIN_VALUE)));
            assertFalse(pt(Integer.MAX_VALUE, 0).equals(pt(Integer.MAX_VALUE, 1)));
        }
    }

    // ==========================================================
    // CHALLENGE 9: Point.hashCode
    // ==========================================================
    @Nested
    class PointHashCodeTests {

        @Test
        void testEqualPointsHaveTheSameHashCode() {
            assertEquals(pt(1, 2).hashCode(), pt(1, 2).hashCode());
        }

        @Test
        void testNegativePoints() {
            assertEquals(pt(-7, -3).hashCode(), pt(-7, -3).hashCode());
        }

        @Test
        void testSameObjectGivesTheSameNumberEveryTime() {
            ObjectsCoreChallenge.Point p = pt(4, 5);
            assertEquals(p.hashCode(), p.hashCode());
        }

        @Test
        void testSwappedPointsAreNotAlwaysMixedUp() {
            int mixedUp = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i != j && pt(i, j).hashCode() == pt(j, i).hashCode()) {
                        mixedUp++;
                    }
                }
            }
            assertTrue(mixedUp < 10);
        }

        @Test
        void testHashCodesAreSpreadOverAGrid() {
            Set<Integer> hashes = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    hashes.add(pt(i, j).hashCode());
                }
            }
            assertTrue(hashes.size() >= 90);
        }

        @Test
        void testHashCodesAreSpreadOverNegativeValues() {
            Set<Integer> hashes = new HashSet<>();
            for (int i = -5; i < 5; i++) {
                for (int j = -5; j < 5; j++) {
                    hashes.add(pt(i, j).hashCode());
                }
            }
            assertTrue(hashes.size() >= 90);
        }

        @Test
        void testBigCoordinatesDoNotFail() {
            assertEquals(pt(Integer.MAX_VALUE, Integer.MIN_VALUE).hashCode(),
                    pt(Integer.MAX_VALUE, Integer.MIN_VALUE).hashCode());
        }

        @Test
        void testOriginAndItsNeighbours() {
            assertNotEquals(pt(0, 0).hashCode(), pt(0, 1).hashCode());
            assertNotEquals(pt(0, 0).hashCode(), pt(1, 0).hashCode());
            assertNotEquals(pt(0, 1).hashCode(), pt(1, 0).hashCode());
        }
    }

    // ==========================================================
    // CHALLENGE 10: Point.toString
    // ==========================================================
    @Nested
    class PointToStringTests {

        @Test
        void testNormalPoint() {
            assertEquals("(3, 4)", pt(3, 4).toString());
        }

        @Test
        void testNegativeAndZero() {
            assertEquals("(-1, 0)", pt(-1, 0).toString());
        }

        @Test
        void testOrigin() {
            assertEquals("(0, 0)", pt(0, 0).toString());
        }

        @Test
        void testBothNegative() {
            assertEquals("(-5, -6)", pt(-5, -6).toString());
        }

        @Test
        void testBigNumbers() {
            assertEquals("(1000, 2000000)", pt(1000, 2000000).toString());
        }

        @Test
        void testSwappedPointsAreDifferentTexts() {
            assertNotEquals(pt(1, 2).toString(), pt(2, 1).toString());
        }

        @Test
        void testConcatenationUsesToString() {
            assertEquals("p=(7, 8)", "p=" + pt(7, 8));
        }

        @Test
        void testInsideAList() {
            assertEquals("[(1, 2), (3, 4)]", List.of(pt(1, 2), pt(3, 4)).toString());
        }
    }

    // ==========================================================
    // CHALLENGE 11: Person.compareTo
    // ==========================================================
    @Nested
    class PersonCompareToTests {

        @Test
        void testOlderComesAfter() {
            assertTrue(person("Ana", 30).compareTo(person("Bob", 25)) > 0);
        }

        @Test
        void testYoungerComesBefore() {
            assertTrue(person("Bob", 25).compareTo(person("Ana", 30)) < 0);
        }

        @Test
        void testSameAgeNameDecides() {
            assertTrue(person("Ana", 30).compareTo(person("Bob", 30)) < 0);
            assertTrue(person("Bob", 30).compareTo(person("Ana", 30)) > 0);
        }

        @Test
        void testSameAgeAndName() {
            assertEquals(0, person("Ana", 30).compareTo(person("Ana", 30)));
        }

        @Test
        void testAgeHasPriorityOverName() {
            assertTrue(person("Zed", 20).compareTo(person("Amy", 21)) < 0);
            assertTrue(person("Amy", 21).compareTo(person("Zed", 20)) > 0);
        }

        @Test
        void testExtremeAgesDoNotOverflow() {
            assertTrue(person("A", -2_000_000_000).compareTo(person("A", 2_000_000_000)) < 0);
            assertTrue(person("A", 2_000_000_000).compareTo(person("A", -2_000_000_000)) > 0);
        }

        @Test
        void testNegativeAges() {
            assertTrue(person("A", -5).compareTo(person("A", -3)) < 0);
            assertTrue(person("A", -3).compareTo(person("A", -5)) > 0);
        }

        @Test
        void testSortingAList() {
            List<ObjectsCoreChallenge.Person> people = new ArrayList<>(List.of(
                    person("Cy", 30), person("Ana", 30), person("Bob", 25), person("Di", 40)));
            Collections.sort(people);
            assertEquals("Bob", people.get(0).name());
            assertEquals("Ana", people.get(1).name());
            assertEquals("Cy", people.get(2).name());
            assertEquals("Di", people.get(3).name());
        }

        @Test
        void testReflexive() {
            ObjectsCoreChallenge.Person p = person("Eve", 22);
            assertEquals(0, p.compareTo(p));
        }

        @Test
        void testUppercaseLettersComeBeforeLowercaseOnes() {
            assertTrue(person("Zed", 20).compareTo(person("amy", 20)) < 0);
            assertTrue(person("amy", 20).compareTo(person("Zed", 20)) > 0);
            assertTrue(person("Amy", 20).compareTo(person("amy", 20)) < 0);
        }

        @Test
        void testNamesAreCompared() {
            assertTrue(person("Ann", 20).compareTo(person("Anna", 20)) < 0);
            assertTrue(person("B", 20).compareTo(person("A", 20)) > 0);
        }
    }

    // ==========================================================
    // CHALLENGE 12: Book.equals
    // ==========================================================
    @Nested
    class BookEqualsTests {

        @Test
        void testSameTitleAndAuthor() {
            assertTrue(book("Dune", "Herbert").equals(book("Dune", "Herbert")));
        }

        @Test
        void testBothNull() {
            assertTrue(book(null, null).equals(book(null, null)));
        }

        @Test
        void testNullAuthorAgainstAuthor() {
            assertFalse(book("Dune", null).equals(book("Dune", "Herbert")));
            assertFalse(book("Dune", "Herbert").equals(book("Dune", null)));
        }

        @Test
        void testNullTitleAgainstTitle() {
            assertFalse(book(null, "Herbert").equals(book("Dune", "Herbert")));
            assertFalse(book("Dune", "Herbert").equals(book(null, "Herbert")));
        }

        @Test
        void testDifferentTitle() {
            assertFalse(book("Dune", "Herbert").equals(book("Emma", "Herbert")));
        }

        @Test
        void testDifferentAuthor() {
            assertFalse(book("Dune", "Herbert").equals(book("Dune", "Austen")));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(book("Dune", "Herbert").equals(book("dune", "Herbert")));
            assertFalse(book("Dune", "Herbert").equals(book("Dune", "herbert")));
        }

        @Test
        void testNullArgument() {
            assertFalse(book("Dune", "Herbert").equals(null));
            assertFalse(book(null, null).equals(null));
        }

        @Test
        void testOtherType() {
            assertFalse(book("Dune", "Herbert").equals("Dune"));
            assertFalse(book(null, null).equals(new Object()));
        }

        @Test
        void testSameObject() {
            ObjectsCoreChallenge.Book b = book(null, "X");
            assertTrue(b.equals(b));
        }

        @Test
        void testSwappedTitleAndAuthor() {
            assertFalse(book("a", "b").equals(book("b", "a")));
        }

        @Test
        void testBothNullTitlesDifferentAuthors() {
            assertFalse(book(null, "a").equals(book(null, "b")));
            assertTrue(book(null, "a").equals(book(null, "a")));
        }

        @Test
        void testSameContentDifferentObjects() {
            assertTrue(book(new String("Dune"), new String("Herbert")).equals(book("Dune", "Herbert")));
        }

        @Test
        void testNullAuthorsDifferentTitles() {
            assertFalse(book("a", null).equals(book("b", null)));
            assertTrue(book("a", null).equals(book("a", null)));
        }
    }

    // ==========================================================
    // CHALLENGE 13: Book.hashCode
    // ==========================================================
    @Nested
    class BookHashCodeTests {

        @Test
        void testEqualBooksHaveTheSameHashCode() {
            assertEquals(book("Dune", "Herbert").hashCode(), book("Dune", "Herbert").hashCode());
        }

        @Test
        void testNullFieldsDoNotFail() {
            assertEquals(book(null, null).hashCode(), book(null, null).hashCode());
        }

        @Test
        void testNullTitle() {
            assertEquals(book(null, "X").hashCode(), book(null, "X").hashCode());
        }

        @Test
        void testNullAuthor() {
            assertEquals(book("X", null).hashCode(), book("X", null).hashCode());
        }

        @Test
        void testSameContentDifferentObjects() {
            assertEquals(book(new String("Dune"), new String("H")).hashCode(), book("Dune", "H").hashCode());
        }

        @Test
        void testHashCodesAreSpread() {
            Set<Integer> hashes = new HashSet<>();
            for (int i = 0; i < 20; i++) {
                hashes.add(book("title" + i, "author").hashCode());
            }
            assertTrue(hashes.size() >= 18);
        }

        @Test
        void testAuthorsAreSpread() {
            Set<Integer> hashes = new HashSet<>();
            for (int i = 0; i < 20; i++) {
                hashes.add(book("title", "author" + i).hashCode());
            }
            assertTrue(hashes.size() >= 18);
        }

        @Test
        void testTitleAndAuthorBothCount() {
            Set<Integer> hashes = new HashSet<>();
            hashes.add(book("a", "b").hashCode());
            hashes.add(book("a", "c").hashCode());
            hashes.add(book("b", "b").hashCode());
            hashes.add(book("b", "a").hashCode());
            assertTrue(hashes.size() >= 3);
        }

        @Test
        void testNullIsNotMixedUpWithOtherValues() {
            Set<Integer> hashes = new HashSet<>();
            hashes.add(book(null, null).hashCode());
            hashes.add(book("a", null).hashCode());
            hashes.add(book(null, "a").hashCode());
            hashes.add(book("a", "a").hashCode());
            assertTrue(hashes.size() >= 3);
        }
    }

    // ==========================================================
    // CHALLENGE 14: Money.plus
    // ==========================================================
    @Nested
    class MoneyPlusTests {

        @Test
        void testSum() {
            assertEquals(175, new ObjectsCoreChallenge.Money(150).plus(new ObjectsCoreChallenge.Money(25)).cents());
        }

        @Test
        void testThisDoesNotChange() {
            ObjectsCoreChallenge.Money a = new ObjectsCoreChallenge.Money(150);
            a.plus(new ObjectsCoreChallenge.Money(25));
            assertEquals(150, a.cents());
        }

        @Test
        void testOtherDoesNotChange() {
            ObjectsCoreChallenge.Money b = new ObjectsCoreChallenge.Money(25);
            new ObjectsCoreChallenge.Money(150).plus(b);
            assertEquals(25, b.cents());
        }

        @Test
        void testResultIsANewObject() {
            ObjectsCoreChallenge.Money a = new ObjectsCoreChallenge.Money(1);
            ObjectsCoreChallenge.Money b = new ObjectsCoreChallenge.Money(2);
            ObjectsCoreChallenge.Money sum = a.plus(b);
            assertNotSame(a, sum);
            assertNotSame(b, sum);
        }

        @Test
        void testAddingZeroStillGivesANewObject() {
            ObjectsCoreChallenge.Money a = new ObjectsCoreChallenge.Money(7);
            ObjectsCoreChallenge.Money sum = a.plus(new ObjectsCoreChallenge.Money(0));
            assertEquals(7, sum.cents());
            assertNotSame(a, sum);
        }

        @Test
        void testAddingToZeroStillGivesANewObject() {
            ObjectsCoreChallenge.Money zero = new ObjectsCoreChallenge.Money(0);
            ObjectsCoreChallenge.Money five = new ObjectsCoreChallenge.Money(5);
            ObjectsCoreChallenge.Money sum = zero.plus(five);
            assertEquals(5, sum.cents());
            assertNotSame(zero, sum);
            assertNotSame(five, sum);
        }

        @Test
        void testNegativeAmounts() {
            assertEquals(-5, new ObjectsCoreChallenge.Money(-20).plus(new ObjectsCoreChallenge.Money(15)).cents());
        }

        @Test
        void testChainedSums() {
            ObjectsCoreChallenge.Money total = new ObjectsCoreChallenge.Money(1)
                    .plus(new ObjectsCoreChallenge.Money(2))
                    .plus(new ObjectsCoreChallenge.Money(3));
            assertEquals(6, total.cents());
        }

        @Test
        void testBigAmountsDoNotOverflowAnInt() {
            ObjectsCoreChallenge.Money big = new ObjectsCoreChallenge.Money(3_000_000_000L);
            assertEquals(6_000_000_000L, big.plus(big).cents());
        }

        @Test
        void testSameObjectAddedToItself() {
            ObjectsCoreChallenge.Money a = new ObjectsCoreChallenge.Money(10);
            assertEquals(20, a.plus(a).cents());
            assertEquals(10, a.cents());
        }

        @Test
        void testTheOrderDoesNotChangeTheSum() {
            ObjectsCoreChallenge.Money a = new ObjectsCoreChallenge.Money(8);
            ObjectsCoreChallenge.Money b = new ObjectsCoreChallenge.Money(9);
            assertEquals(a.plus(b).cents(), b.plus(a).cents());
        }
    }

    // ==========================================================
    // CHALLENGE 15: Team
    // ==========================================================
    @Nested
    class TeamTests {

        @Test
        void testMembersInOrder() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(List.of("Ana", "Bob"));
            assertEquals(List.of("Ana", "Bob"), team.members());
        }

        @Test
        void testChangingTheOriginalListDoesNotChangeTheTeam() {
            List<String> list = new ArrayList<>(List.of("Ana", "Bob"));
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(list);
            list.add("Cy");
            assertEquals(List.of("Ana", "Bob"), team.members());
        }

        @Test
        void testRemovingFromTheOriginalListDoesNotChangeTheTeam() {
            List<String> list = new ArrayList<>(List.of("Ana", "Bob"));
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(list);
            list.remove("Ana");
            list.set(0, "Zed");
            assertEquals(List.of("Ana", "Bob"), team.members());
        }

        @Test
        void testClearingTheOriginalListDoesNotChangeTheTeam() {
            List<String> list = new ArrayList<>(List.of("Ana", "Bob"));
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(list);
            list.clear();
            assertEquals(2, team.members().size());
        }

        @Test
        void testAddingToTheReturnedListDoesNotChangeTheTeam() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(new ArrayList<>(List.of("Ana", "Bob")));
            try {
                team.members().add("Cy");
            } catch (UnsupportedOperationException e) {
                // refusing the change is also correct
            }
            assertEquals(List.of("Ana", "Bob"), team.members());
        }

        @Test
        void testRemovingFromTheReturnedListDoesNotChangeTheTeam() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(new ArrayList<>(List.of("Ana", "Bob")));
            try {
                team.members().remove(0);
            } catch (UnsupportedOperationException e) {
                // refusing the change is also correct
            }
            try {
                team.members().clear();
            } catch (UnsupportedOperationException e) {
                // refusing the change is also correct
            }
            assertEquals(List.of("Ana", "Bob"), team.members());
        }

        @Test
        void testSettingInTheReturnedListDoesNotChangeTheTeam() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(new ArrayList<>(List.of("Ana", "Bob")));
            try {
                team.members().set(0, "Zed");
            } catch (UnsupportedOperationException e) {
                // refusing the change is also correct
            }
            assertEquals("Ana", team.members().get(0));
        }

        @Test
        void testEmptyTeam() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(new ArrayList<>());
            assertEquals(0, team.members().size());
        }

        @Test
        void testTwoTeamsFromTheSameListAreIndependent() {
            List<String> list = new ArrayList<>(List.of("Ana"));
            ObjectsCoreChallenge.Team first = new ObjectsCoreChallenge.Team(list);
            list.add("Bob");
            ObjectsCoreChallenge.Team second = new ObjectsCoreChallenge.Team(list);
            list.add("Cy");
            assertEquals(List.of("Ana"), first.members());
            assertEquals(List.of("Ana", "Bob"), second.members());
        }

        @Test
        void testTheTeamKeepsAllNames() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(List.of("a", "b", "c", "d", "e"));
            assertEquals(5, team.members().size());
            assertEquals("e", team.members().get(4));
        }

        @Test
        void testChangingTheReturnedListOfOneCallDoesNotAffectTheNextCall() {
            ObjectsCoreChallenge.Team team = new ObjectsCoreChallenge.Team(new ArrayList<>(List.of("Ana", "Bob")));
            List<String> first = team.members();
            try {
                first.add("Cy");
            } catch (UnsupportedOperationException e) {
                // refusing the change is also correct
            }
            assertEquals(2, team.members().size());
        }
    }
}