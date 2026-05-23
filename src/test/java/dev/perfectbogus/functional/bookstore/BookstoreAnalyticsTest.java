package dev.perfectbogus.functional.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreAnalyticsTest {

    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = List.of(
                new Book("B001", "Clean Code",              "Robert Martin",        "Technology", 45.99, 10, 2008, BookStatus.AVAILABLE,     List.of("programming", "best-practices", "java")),
                new Book("B002", "The Clean Coder",         "Robert Martin",        "Fiction",    35.99,  8, 2011, BookStatus.AVAILABLE,     List.of("fiction", "professional", "career")),
                new Book("B003", "Design Patterns",         "Gang of Four",         "Technology", 55.00,  3, 1994, BookStatus.AVAILABLE,     List.of("programming", "patterns", "oop")),
                new Book("B004", "Dune",                    "Frank Herbert",        "Sci-Fi",     29.99, 20, 1965, BookStatus.AVAILABLE,     List.of("space", "epic", "classic")),
                new Book("B005", "Foundation",              "Isaac Asimov",         "Sci-Fi",     24.99, 15, 1951, BookStatus.AVAILABLE,     List.of("space", "classic", "robots")),
                new Book("B006", "Neuromancer",             "William Gibson",       "Sci-Fi",     19.99,  0, 1984, BookStatus.OUT_OF_STOCK,  List.of("cyberpunk", "classic")),
                new Book("B007", "1984",                    "George Orwell",        "Fiction",    14.99, 25, 1949, BookStatus.AVAILABLE,     List.of("dystopia", "classic", "politics")),
                new Book("B008", "Brave New World",         "Aldous Huxley",        "Fiction",    12.99, 18, 1932, BookStatus.AVAILABLE,     List.of("dystopia", "classic")),
                new Book("B009", "The Great Gatsby",        "F. Scott Fitzgerald",  "Fiction",     9.99,  0, 1925, BookStatus.DISCONTINUED,  List.of("classic", "american-literature")),
                new Book("B010", "Refactoring",             "Robert Martin",        "Technology", 52.99,  7, 2018, BookStatus.AVAILABLE,     List.of("programming", "best-practices", "java"))
        );
    }

    // -------------------------------------------------------------------------
    // Task 1 — Titles per genre sorted alphabetically
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - titlesByGenreSorted")
    class Task1 {

        @Test
        @DisplayName("Should return titles sorted alphabetically per genre")
        void shouldReturnSortedTitlesPerGenre() {
            Map<String, List<String>> result = BookstoreAnalytics.titlesByGenreSorted(books);

            assertEquals(List.of("Clean Code", "Design Patterns", "Refactoring"), result.get("Technology"));
            assertEquals(List.of("Dune", "Foundation", "Neuromancer"),            result.get("Sci-Fi"));
            assertEquals(List.of("1984", "Brave New World", "The Clean Coder", "The Great Gatsby"), result.get("Fiction"));
        }

        @Test
        @DisplayName("Should contain all genres")
        void shouldContainAllGenres() {
            Map<String, List<String>> result = BookstoreAnalytics.titlesByGenreSorted(books);
            assertTrue(result.containsKey("Technology"));
            assertTrue(result.containsKey("Sci-Fi"));
            assertTrue(result.containsKey("Fiction"));
        }

        @Test
        @DisplayName("Should return empty map for empty list")
        void shouldReturnEmptyMapForEmptyList() {
            assertTrue(BookstoreAnalytics.titlesByGenreSorted(List.of()).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.titlesByGenreSorted(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Authors per genre as an unmodifiable Set
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - authorsByGenre")
    class Task2 {

        @Test
        @DisplayName("Should return correct unique authors per genre")
        void shouldReturnUniqueAuthorsPerGenre() {
            Map<String, Set<String>> result = BookstoreAnalytics.authorsByGenre(books);

            // Technology: Robert Martin (B001, B010), Gang of Four (B003)
            assertEquals(Set.of("Robert Martin", "Gang of Four"), result.get("Technology"));

            // Sci-Fi: Frank Herbert, Isaac Asimov, William Gibson
            assertEquals(Set.of("Frank Herbert", "Isaac Asimov", "William Gibson"), result.get("Sci-Fi"));

            // Fiction: Robert Martin, George Orwell, Aldous Huxley, F. Scott Fitzgerald
            assertEquals(Set.of("Robert Martin", "George Orwell", "Aldous Huxley", "F. Scott Fitzgerald"), result.get("Fiction"));
        }

        @Test
        @DisplayName("Should return an unmodifiable Set")
        void shouldReturnUnmodifiableSet() {
            Map<String, Set<String>> result = BookstoreAnalytics.authorsByGenre(books);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("Technology").add("New Author"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.authorsByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Most expensive book title per genre
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - mostExpensiveTitleByGenre")
    class Task3 {

        @Test
        @DisplayName("Should return the title of the most expensive book per genre")
        void shouldReturnMostExpensiveTitlePerGenre() {
            Map<String, String> result = BookstoreAnalytics.mostExpensiveTitleByGenre(books);

            // Technology: Design Patterns ($55.00)
            assertEquals("Design Patterns", result.get("Technology"));

            // Sci-Fi: Dune ($29.99)
            assertEquals("Dune", result.get("Sci-Fi"));

            // Fiction: The Clean Coder ($35.99)
            assertEquals("The Clean Coder", result.get("Fiction"));
        }

        @Test
        @DisplayName("Should return N/A for genre with no books")
        void shouldReturnNAForEmpty() {
            Map<String, String> result = BookstoreAnalytics.mostExpensiveTitleByGenre(List.of());
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return String not Optional")
        void shouldReturnStringNotOptional() {
            Map<String, String> result = BookstoreAnalytics.mostExpensiveTitleByGenre(books);
            assertInstanceOf(String.class, result.get("Technology"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.mostExpensiveTitleByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Genres where every book is AVAILABLE
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - genresWithAllBooksAvailable")
    class Task4 {

        @Test
        @DisplayName("Should return only genres where all books are AVAILABLE")
        void shouldReturnOnlyFullyAvailableGenres() {
            List<String> result = BookstoreAnalytics.genresWithAllBooksAvailable(books);

            // Technology: all AVAILABLE (B001, B003, B010)  → included
            // Sci-Fi: B006 is OUT_OF_STOCK                  → excluded
            // Fiction: B009 is DISCONTINUED                 → excluded
            assertEquals(List.of("Technology"), result);
        }

        @Test
        @DisplayName("Should return results sorted alphabetically")
        void shouldBeSortedAlphabetically() {
            List<String> result = BookstoreAnalytics.genresWithAllBooksAvailable(books);
            List<String> sorted = result.stream().sorted().toList();
            assertEquals(sorted, result);
        }

        @Test
        @DisplayName("Should return empty list if no genre qualifies")
        void shouldReturnEmptyIfNoneQualify() {
            List<Book> allUnavailable = List.of(
                    new Book("X001", "Book A", "Author A", "Drama", 10.0, 0, 2020, BookStatus.OUT_OF_STOCK, List.of())
            );
            assertTrue(BookstoreAnalytics.genresWithAllBooksAvailable(allUnavailable).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.genresWithAllBooksAvailable(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Authors who write across multiple genres
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - authorsWithMultipleGenres")
    class Task5 {

        @Test
        @DisplayName("Should return only authors present in more than one genre")
        void shouldReturnMultiGenreAuthors() {
            List<String> result = BookstoreAnalytics.authorsWithMultipleGenres(books);

            // Robert Martin: Technology (B001, B010) + Fiction (B002) → 2 genres → included
            // All others: single genre → excluded
            assertEquals(List.of("Robert Martin"), result);
        }

        @Test
        @DisplayName("Should return result sorted alphabetically")
        void shouldBeSortedAlphabetically() {
            List<String> result = BookstoreAnalytics.authorsWithMultipleGenres(books);
            assertEquals(result.stream().sorted().toList(), result);
        }

        @Test
        @DisplayName("Should return empty list when no author spans multiple genres")
        void shouldReturnEmptyWhenNoMultiGenreAuthors() {
            List<Book> singleGenre = List.of(
                    new Book("X001", "Book A", "Author A", "Drama",  10.0, 5, 2020, BookStatus.AVAILABLE, List.of()),
                    new Book("X002", "Book B", "Author B", "Comedy", 12.0, 3, 2021, BookStatus.AVAILABLE, List.of())
            );
            assertTrue(BookstoreAnalytics.authorsWithMultipleGenres(singleGenre).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.authorsWithMultipleGenres(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — Genre → titles as a comma-separated String
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - titlesSummaryByGenre")
    class Task6 {

        @Test
        @DisplayName("Should return comma-separated sorted titles per genre")
        void shouldReturnCommaSeparatedSortedTitles() {
            Map<String, String> result = BookstoreAnalytics.titlesSummaryByGenre(books);

            assertEquals("Clean Code, Design Patterns, Refactoring",                       result.get("Technology"));
            assertEquals("Dune, Foundation, Neuromancer",                                   result.get("Sci-Fi"));
            assertEquals("1984, Brave New World, The Clean Coder, The Great Gatsby",        result.get("Fiction"));
        }

        @Test
        @DisplayName("Should return String values not List")
        void shouldReturnStringValues() {
            Map<String, String> result = BookstoreAnalytics.titlesSummaryByGenre(books);
            result.values().forEach(v -> assertInstanceOf(String.class, v));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.titlesSummaryByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Unique tags per genre as an unmodifiable Set
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - tagsByGenre")
    class Task7 {

        @Test
        @DisplayName("Should collect all unique tags from every book in a genre")
        void shouldCollectAllUniqueTagsPerGenre() {
            Map<String, Set<String>> result = BookstoreAnalytics.tagsByGenre(books);

            // Technology (B001, B003, B010): programming, best-practices, java, patterns, oop
            assertTrue(result.get("Technology").containsAll(Set.of("programming", "best-practices", "java", "patterns", "oop")));
            assertEquals(5, result.get("Technology").size());

            // Sci-Fi (B004, B005, B006): space, epic, classic, robots, cyberpunk
            assertTrue(result.get("Sci-Fi").containsAll(Set.of("space", "epic", "classic", "robots", "cyberpunk")));
            assertEquals(5, result.get("Sci-Fi").size());

            // Fiction (B002, B007, B008, B009): fiction, professional, career, dystopia, classic, politics, american-literature
            assertTrue(result.get("Fiction").containsAll(Set.of("fiction", "professional", "career", "dystopia", "classic", "politics", "american-literature")));
            assertEquals(7, result.get("Fiction").size());
        }

        @Test
        @DisplayName("Should return an unmodifiable Set")
        void shouldReturnUnmodifiableSet() {
            Map<String, Set<String>> result = BookstoreAnalytics.tagsByGenre(books);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("Technology").add("new-tag"));
        }

        @Test
        @DisplayName("Should contain no duplicate tags within a genre")
        void shouldContainNoDuplicateTags() {
            Map<String, Set<String>> result = BookstoreAnalytics.tagsByGenre(books);
            // Sets are inherently unique — just verify the size matches distinct count
            result.forEach((genre, tags) -> assertEquals(tags.size(), tags.stream().distinct().count()));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.tagsByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Book catalog: isbn → formatted summary string
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - bookCatalog")
    class Task8 {

        @Test
        @DisplayName("Should return correctly formatted string for each isbn")
        void shouldReturnFormattedStringPerIsbn() {
            Map<String, String> result = BookstoreAnalytics.bookCatalog(books);

            assertEquals("Clean Code by Robert Martin (Technology, $45.99)",        result.get("B001"));
            assertEquals("The Clean Coder by Robert Martin (Fiction, $35.99)",      result.get("B002"));
            assertEquals("Design Patterns by Gang of Four (Technology, $55.0)",     result.get("B003"));
            assertEquals("Dune by Frank Herbert (Sci-Fi, $29.99)",                  result.get("B004"));
            assertEquals("1984 by George Orwell (Fiction, $14.99)",                 result.get("B007"));
            assertEquals("Refactoring by Robert Martin (Technology, $52.99)",       result.get("B010"));
        }

        @Test
        @DisplayName("Should contain an entry for every book")
        void shouldContainEntryForEveryBook() {
            Map<String, String> result = BookstoreAnalytics.bookCatalog(books);
            assertEquals(books.size(), result.size());
            books.forEach(b -> assertTrue(result.containsKey(b.isbn())));
        }

        @Test
        @DisplayName("Should return an unmodifiable Map")
        void shouldReturnUnmodifiableMap() {
            Map<String, String> result = BookstoreAnalytics.bookCatalog(books);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.put("B999", "Test"));
        }

        @Test
        @DisplayName("Should return empty unmodifiable map for empty list")
        void shouldReturnEmptyMapForEmptyList() {
            Map<String, String> result = BookstoreAnalytics.bookCatalog(List.of());
            assertTrue(result.isEmpty());
            assertThrows(UnsupportedOperationException.class,
                    () -> result.put("X", "Y"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> BookstoreAnalytics.bookCatalog(null));
        }
    }
}