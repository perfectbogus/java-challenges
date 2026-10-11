package dev.perfectbogus.collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectorsBasicsChallengeTest {

    private static CollectorsBasicsChallenge.Book book(String title, String author, String genre, int pages,
                                                       double price) {
        return new CollectorsBasicsChallenge.Book(title, author, genre, pages, price);
    }

    private static List<CollectorsBasicsChallenge.Book> library() {
        return List.of(
                book("Dune", "Herbert", "SciFi", 412, 12.5),
                book("Emma", "Austen", "Romance", 300, 8.0),
                book("Persuasion", "Austen", "Romance", 250, 9.0),
                book("Neuromancer", "Gibson", "SciFi", 271, 10.0),
                book("Ulysses", "Joyce", "Classic", 730, 20.0),
                book("Dubliners", "Joyce", "Classic", 152, 8.0));
    }

    // ==========================================================
    // CHALLENGE 1: titlesOfLongBooks
    // ==========================================================
    @Nested
    class TitlesOfLongBooksTests {

        @Test
        void testKeepsOnlyBooksWithEnoughPagesInOriginalOrder() {
            assertEquals(List.of("Dune", "Emma", "Ulysses"),
                    CollectorsBasicsChallenge.titlesOfLongBooks(library(), 300));
        }

        @Test
        void testOrderFollowsInputNotAlphabet() {
            assertEquals(List.of("Zebra", "Apple", "Mango"),
                    CollectorsBasicsChallenge.titlesOfLongBooks(
                            List.of(book("Zebra", "A", "G", 400, 1), book("Short", "A", "G", 10, 1),
                                    book("Apple", "A", "G", 400, 1), book("Mango", "A", "G", 400, 1)), 300));
        }

        @Test
        void testBoundaryIsInclusive() {
            assertEquals(List.of("Emma"),
                    CollectorsBasicsChallenge.titlesOfLongBooks(
                            List.of(book("Emma", "A", "G", 300, 1), book("Odd", "A", "G", 299, 1)), 300));
        }

        @Test
        void testNothingMatchesGivesEmptyList() {
            assertEquals(List.of(), CollectorsBasicsChallenge.titlesOfLongBooks(library(), 1000));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), CollectorsBasicsChallenge.titlesOfLongBooks(List.of(), 1));
        }

        @Test
        void testDoesNotModifyInput() {
            List<CollectorsBasicsChallenge.Book> books = new ArrayList<>(library());
            CollectorsBasicsChallenge.titlesOfLongBooks(books, 300);
            assertEquals(library().size(), books.size());
        }
    }

    // ==========================================================
    // CHALLENGE 2: distinctGenres
    // ==========================================================
    @Nested
    class DistinctGenresTests {

        @Test
        void testReturnsEachGenreOnce() {
            assertEquals(Set.of("SciFi", "Romance", "Classic"), CollectorsBasicsChallenge.distinctGenres(library()));
        }

        @Test
        void testSingleGenre() {
            assertEquals(Set.of("X"), CollectorsBasicsChallenge.distinctGenres(
                    List.of(book("a", "A", "X", 1, 1), book("b", "B", "X", 2, 2))));
        }

        @Test
        void testEmptyInputGivesEmptySet() {
            assertEquals(Set.of(), CollectorsBasicsChallenge.distinctGenres(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: joinTitles
    // ==========================================================
    @Nested
    class JoinTitlesTests {

        @Test
        void testJoinsWithCommaAndSpace() {
            assertEquals("Dune, Emma, Persuasion, Neuromancer, Ulysses, Dubliners",
                    CollectorsBasicsChallenge.joinTitles(library()));
        }

        @Test
        void testSingleTitleHasNoSeparator() {
            assertEquals("Dune", CollectorsBasicsChallenge.joinTitles(List.of(book("Dune", "H", "S", 1, 1))));
        }

        @Test
        void testEmptyInputGivesEmptyString() {
            assertEquals("", CollectorsBasicsChallenge.joinTitles(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: pagesByTitle
    // ==========================================================
    @Nested
    class PagesByTitleTests {

        @Test
        void testMapsEveryTitleToItsPages() {
            Map<String, Integer> expected = Map.of("Dune", 412, "Emma", 300, "Persuasion", 250,
                    "Neuromancer", 271, "Ulysses", 730, "Dubliners", 152);
            assertEquals(expected, CollectorsBasicsChallenge.pagesByTitle(library()));
        }

        @Test
        void testEmptyInputGivesEmptyMap() {
            assertEquals(Map.of(), CollectorsBasicsChallenge.pagesByTitle(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: countByGenre
    // ==========================================================
    @Nested
    class CountByGenreTests {

        @Test
        void testCountsBooksPerGenre() {
            assertEquals(Map.of("SciFi", 2L, "Romance", 2L, "Classic", 2L),
                    CollectorsBasicsChallenge.countByGenre(library()));
        }

        @Test
        void testUnevenCounts() {
            List<CollectorsBasicsChallenge.Book> books = List.of(
                    book("a", "A", "X", 1, 1), book("b", "B", "X", 1, 1), book("c", "C", "X", 1, 1),
                    book("d", "D", "Y", 1, 1));
            assertEquals(Map.of("X", 3L, "Y", 1L), CollectorsBasicsChallenge.countByGenre(books));
        }

        @Test
        void testEmptyInputGivesEmptyMap() {
            assertEquals(Map.of(), CollectorsBasicsChallenge.countByGenre(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 6: titlesByGenre
    // ==========================================================
    @Nested
    class TitlesByGenreTests {

        @Test
        void testGroupsTitlesKeepingOriginalOrder() {
            Map<String, List<String>> expected = Map.of(
                    "SciFi", List.of("Dune", "Neuromancer"),
                    "Romance", List.of("Emma", "Persuasion"),
                    "Classic", List.of("Ulysses", "Dubliners"));
            assertEquals(expected, CollectorsBasicsChallenge.titlesByGenre(library()));
        }

        @Test
        void testOrderWithinGroupFollowsInputNotAlphabet() {
            List<CollectorsBasicsChallenge.Book> books = List.of(
                    book("Zebra", "A", "X", 1, 1), book("Apple", "B", "X", 1, 1));
            assertEquals(Map.of("X", List.of("Zebra", "Apple")), CollectorsBasicsChallenge.titlesByGenre(books));
        }

        @Test
        void testEmptyInputGivesEmptyMap() {
            assertEquals(Map.of(), CollectorsBasicsChallenge.titlesByGenre(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 7: partitionByLongBook
    // ==========================================================
    @Nested
    class PartitionByLongBookTests {

        @Test
        void testSplitsAtMoreThan300() {
            Map<Boolean, List<CollectorsBasicsChallenge.Book>> result =
                    CollectorsBasicsChallenge.partitionByLongBook(library());
            assertEquals(List.of("Dune", "Ulysses"), titles(result.get(true)));
            assertEquals(List.of("Emma", "Persuasion", "Neuromancer", "Dubliners"), titles(result.get(false)));
        }

        @Test
        void testBothKeysPresentWhenAllAreShort() {
            Map<Boolean, List<CollectorsBasicsChallenge.Book>> result =
                    CollectorsBasicsChallenge.partitionByLongBook(List.of(book("a", "A", "X", 10, 1)));
            assertEquals(Set.of(true, false), result.keySet());
            assertEquals(List.of(), result.get(true));
            assertEquals(1, result.get(false).size());
        }

        @Test
        void testBothKeysPresentForEmptyInput() {
            Map<Boolean, List<CollectorsBasicsChallenge.Book>> result =
                    CollectorsBasicsChallenge.partitionByLongBook(List.of());
            assertEquals(Set.of(true, false), result.keySet());
            assertEquals(List.of(), result.get(true));
            assertEquals(List.of(), result.get(false));
        }

        private List<String> titles(List<CollectorsBasicsChallenge.Book> books) {
            List<String> titles = new ArrayList<>();
            for (CollectorsBasicsChallenge.Book b : books) {
                titles.add(b.getTitle());
            }
            return titles;
        }
    }

    // ==========================================================
    // CHALLENGE 8: totalPagesByAuthor
    // ==========================================================
    @Nested
    class TotalPagesByAuthorTests {

        @Test
        void testSumsPagesPerAuthor() {
            assertEquals(Map.of("Herbert", 412, "Austen", 550, "Gibson", 271, "Joyce", 882),
                    CollectorsBasicsChallenge.totalPagesByAuthor(library()));
        }

        @Test
        void testAuthorWithSingleBook() {
            assertEquals(Map.of("A", 7), CollectorsBasicsChallenge.totalPagesByAuthor(
                    List.of(book("a", "A", "X", 7, 1))));
        }

        @Test
        void testEmptyInputGivesEmptyMap() {
            assertEquals(Map.of(), CollectorsBasicsChallenge.totalPagesByAuthor(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 9: averagePriceByGenre
    // ==========================================================
    @Nested
    class AveragePriceByGenreTests {

        @Test
        void testAveragesPricePerGenre() {
            Map<String, Double> result = CollectorsBasicsChallenge.averagePriceByGenre(library());
            assertEquals(Set.of("SciFi", "Romance", "Classic"), result.keySet());
            assertEquals(11.25, result.get("SciFi"), 0.0001);
            assertEquals(8.5, result.get("Romance"), 0.0001);
            assertEquals(14.0, result.get("Classic"), 0.0001);
        }

        @Test
        void testSingleBookGenreKeepsItsOwnPrice() {
            Map<String, Double> result = CollectorsBasicsChallenge.averagePriceByGenre(
                    List.of(book("a", "A", "X", 1, 5.5), book("b", "B", "Y", 1, 2.0), book("c", "C", "Y", 1, 3.0)));
            assertEquals(5.5, result.get("X"), 0.0001);
            assertEquals(2.5, result.get("Y"), 0.0001);
        }

        @Test
        void testEmptyInputGivesEmptyMap() {
            assertEquals(Map.of(), CollectorsBasicsChallenge.averagePriceByGenre(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 10: cheapestBook
    // ==========================================================
    @Nested
    class CheapestBookTests {

        @Test
        void testReturnsLowestPricedBook() {
            List<CollectorsBasicsChallenge.Book> books = List.of(
                    book("a", "A", "X", 1, 5.0), book("b", "B", "X", 1, 2.5), book("c", "C", "X", 1, 9.0));
            Optional<CollectorsBasicsChallenge.Book> result = CollectorsBasicsChallenge.cheapestBook(books);
            assertTrue(result.isPresent());
            assertSame(books.get(1), result.get());
        }

        @Test
        void testTieReturnsFirstInList() {
            List<CollectorsBasicsChallenge.Book> books = library();
            Optional<CollectorsBasicsChallenge.Book> result = CollectorsBasicsChallenge.cheapestBook(books);
            assertTrue(result.isPresent());
            assertSame(books.get(1), result.get());
        }

        @Test
        void testSingleBook() {
            CollectorsBasicsChallenge.Book only = book("a", "A", "X", 1, 5.0);
            assertSame(only, CollectorsBasicsChallenge.cheapestBook(List.of(only)).get());
        }

        @Test
        void testEmptyInputGivesEmptyOptional() {
            assertTrue(CollectorsBasicsChallenge.cheapestBook(List.of()).isEmpty());
        }
    }
}