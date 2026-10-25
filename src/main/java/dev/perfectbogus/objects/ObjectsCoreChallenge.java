package dev.perfectbogus.objects;

import java.util.List;
import java.util.Objects;

public class ObjectsCoreChallenge {

    // CHALLENGE 1
    // Returns true if a and b are both null, or if both are not null and have the same content. The case matters.
    // Example: sameOrBothNull("a", "a")    returns true
    //          sameOrBothNull(null, null)  returns true
    //          sameOrBothNull("a", null)   returns false
    //          sameOrBothNull(null, "a")   returns false
    //          sameOrBothNull("a", "A")    returns false
    public static boolean sameOrBothNull(String a, String b) {
        return Objects.equals(a, b);
    }

    // CHALLENGE 2
    // Returns the text that represents value, or "nothing" when value is null. An empty text is not null.
    // Example: describe(5)             returns "5"
    //          describe("hi")          returns "hi"
    //          describe(List.of(1, 2)) returns "[1, 2]"
    //          describe("")            returns ""
    //          describe(null)          returns "nothing"
    public static String describe(Object value) {
        return Objects.toString(value, "nothing");
    }

    // CHALLENGE 3
    // Returns how many elements of items are null. The list can contain null elements. The text "null" and the empty
    // text are not null.
    // Example: countNulls([null, a, null])  returns 2
    //          countNulls([a, b])           returns 0
    //          countNulls(["null", null])   returns 1
    //          countNulls([])               returns 0
    public static int countNulls(List<String> items) {
        int i = 0;
        for (String item : items) {
            if (Objects.isNull(item)) i++;
        }
        return i;
    }

    public static int countNullsImproved(List<String> items) {
        return (int) items.stream().filter(Objects::isNull).count();
    }

    // CHALLENGE 4
    // Returns name. If name is null, throws a NullPointerException whose message is exactly "name must not be null".
    // An empty text is valid and is returned as it is.
    // Example: checkName("Ana")  returns "Ana"
    //          checkName("")     returns ""
    //          checkName(null)   throws NullPointerException("name must not be null")
    public static String checkName(String name) {
        return Objects.requireNonNull(name, "name must not be null");
    }

    // CHALLENGE 5
    // Returns first if it is not null. Otherwise returns second. If both are null, throws a NullPointerException.
    // An empty text is not null.
    // Example: firstNonNull("a", "b")    returns "a"
    //          firstNonNull(null, "b")   returns "b"
    //          firstNonNull("", "b")     returns ""
    //          firstNonNull(null, null)  throws NullPointerException
    public static String firstNonNullImproved(String first, String second) {
        if (!Objects.isNull(first) && !Objects.isNull(second)) {
            return first;
        } else if (Objects.isNull(first) && !Objects.isNull(second)) {
            return second;
        } else if (!Objects.isNull(first) && Objects.isNull(second)) {
            return first;
        } else {
            throw new NullPointerException();
        }
    }

    public static String firstNonNull(String first, String second) {
        return Objects.requireNonNullElse(first, second);
    }

    // CHALLENGE 6
    // Returns true if all the elements of items have the same content, or if items has zero or one element. The list
    // can contain null elements, and two null elements are the same. The case matters.
    // Example: allSame([a, a, a])   returns true
    //          allSame([a, a, b])   returns false
    //          allSame([null, null]) returns true
    //          allSame([a, null])   returns false
    //          allSame([])          returns true
    public static boolean allSame(List<String> items) {
        if (items.isEmpty()) return true;
        String item = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (!Objects.equals(item, items.get(i))) return false;
        }
        return true;
    }

    // CHALLENGE 7
    // Returns an array with two booleans. The first one is true if a and b are the very same object (the same
    // reference). The second one is true if a and b have the same content, or if both are null.
    // Example: s = new String("x"), t = new String("x")  compareTexts(s, t) returns [false, true]
    //          s = new String("x")                        compareTexts(s, s) returns [true, true]
    //          compareTexts("x", "y")                     returns [false, false]
    //          compareTexts(null, null)                   returns [true, true]
    //          compareTexts(null, "x")                    returns [false, false]
    public static boolean[] compareTexts(String a, String b) {
        return new boolean[]{ a == b, Objects.equals(a, b)};
    }

    // Used by challenges 8, 9 and 10. A point is written as (x, y).
    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        // CHALLENGE 8
        // Two points are equal when they have the same x and the same y. A point is never equal to null or to an
        // object that is not a Point, and equals must not throw an exception for them.
        // Example: (1, 2) equals (1, 2)    returns true
        //          (1, 2) equals (2, 1)    returns false
        //          (1, 2) equals null      returns false
        //          (1, 2) equals "(1, 2)"  returns false
        @Override
        public boolean equals(Object other) {
            if (other == this) return true;
            if (other instanceof Point) {
                Point o = (Point) other;
                return this.x == o.x() && this.y == o.y();
            }
            return false;
        }

        // CHALLENGE 9
        // Returns the hash code of the point. Two equal points must have the same hash code. Different points should
        // usually have different hash codes: a hash code that is the same number for every point is correct, but it
        // is not useful. Points like (1, 2) and (2, 1) should not be mixed up.
        // Example: (1, 2) hashCode and (1, 2) hashCode  give the same number
        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        // CHALLENGE 10
        // Returns the point as a text: the x, a comma, a space and the y, between parentheses.
        // Example: new Point(3, 4)    toString returns "(3, 4)"
        //          new Point(-1, 0)   toString returns "(-1, 0)"
        @Override
        public String toString() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // Used by challenge 11.
    public static class Person implements Comparable<Person> {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }

        // CHALLENGE 11
        // The younger person comes first. If both have the same age, the name decides, in alphabetical order (the
        // names are never null and the comparison is the normal one of texts). Returns a negative number if this
        // person comes before other, a positive number if it comes after, and 0 if both have the same age and the
        // same name. The age can be any int, even a very big or very small one.
        // Example: Ana(30) compareTo Bob(25)  returns a positive number
        //          Ana(30) compareTo Bob(30)  returns a negative number
        //          Ana(30) compareTo Ana(30)  returns 0
        @Override
        public int compareTo(Person other) {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // Used by challenges 12 and 13. The title and the author can be null.
    public static class Book {
        private final String title;
        private final String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String title() {
            return title;
        }

        public String author() {
            return author;
        }

        // CHALLENGE 12
        // Two books are equal when they have the same title and the same author. A null title is equal to another
        // null title, and the same for the author. The case matters. A book is never equal to null or to an object
        // that is not a Book, and equals must not throw an exception, even when a title or an author is null.
        // Example: Book("Dune", "Herbert") equals Book("Dune", "Herbert")  returns true
        //          Book(null, null) equals Book(null, null)                returns true
        //          Book("Dune", null) equals Book("Dune", "Herbert")       returns false
        @Override
        public boolean equals(Object other) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        // CHALLENGE 13
        // Returns the hash code of the book. Two equal books must have the same hash code, and it must not throw an
        // exception when the title or the author is null. Different books should usually have different hash codes.
        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // Used by challenge 14. An amount of money is immutable: it never changes after it is created.
    public static class Money {
        private final long cents;

        public Money(long cents) {
            this.cents = cents;
        }

        public long cents() {
            return cents;
        }

        // CHALLENGE 14
        // Returns a new Money with the sum of this amount and other. Neither this amount nor other change.
        // Example: Money(150) plus Money(25)  returns a new Money(175); the first one is still 150 and the second
        //                                     one is still 25
        public Money plus(Money other) {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // CHALLENGE 15
    // A team keeps the names of its members, in the same order. Once a team is created, nobody from outside can
    // change its members: changing the list that was used to create the team does not change the team, and changing
    // the list returned by members() does not change the team either (the change may be refused with an exception,
    // or it may affect only that returned list). The names are never null.
    // Example: list = [Ana, Bob], team = new Team(list), then list.add("Cy")  team.members() is still [Ana, Bob]
    //          team.members().add("Di") either throws an exception or changes nothing in the team
    public static class Team {

        public Team(List<String> members) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        public List<String> members() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
