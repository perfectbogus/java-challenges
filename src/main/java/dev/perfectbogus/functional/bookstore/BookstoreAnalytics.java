package dev.perfectbogus.functional.bookstore;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookstoreAnalytics {

    // 1. Titles per genre sorted alphabetically
    // Returns Map<genre, List<title>> — titles sorted A-Z within each genre
    public static Map<String, List<String>> titlesByGenreSorted(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.mapping(
                                Book::title,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted().toList()
                                )
                        )
                ));
    }

    // 2. Authors per genre as an unmodifiable Set
    // Returns Map<genre, Set<author>> — set must be unmodifiable
    public static Map<String, Set<String>> authorsByGenre(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy + mapping + collectingAndThen(toSet, Collections::unmodifiableSet)
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.mapping(
                                Book::author,
                                Collectors.collectingAndThen(
                                        Collectors.toSet(),
                                        Collections::unmodifiableSet
                                )
                        )
                ));
    }

    // 3. Most expensive book title per genre
    // Returns Map<genre, title> — use "N/A" if no book found
    public static Map<String, String> mostExpensiveTitleByGenre(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy + collectingAndThen(maxBy(...), opt -> opt.map(Book::title).orElse("N/A"))
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Book::price)),
                                opt -> opt.map(Book::title).orElse("N/A")
                        )
                ));
    }

    // 4. Genres where every book is AVAILABLE
    // Returns List<String> of genre names — sorted alphabetically
    private static final Predicate<Book> BOOK_AVAILABLE = (b -> b.status() == BookStatus.AVAILABLE);
    public static List<String> genresWithAllBooksAvailable(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy + collectingAndThen(toList, list -> allMatch(...))
        //       then stream entries, filter true values, extract keys, sort
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().allMatch(BOOK_AVAILABLE)
                        )
                )).entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // 5. Authors who write across multiple genres
    // Returns List<String> of author names — sorted alphabetically
    public static List<String> authorsWithMultipleGenres(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy(author) + mapping(genre, toSet())
        //       then filter authors whose genre set size > 1
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::author,
                        Collectors.mapping(
                                Book::genre,
                                Collectors.toSet()
                        )
                )).entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // 6. Genre → titles as a comma-separated String (sorted alphabetically)
    // Returns Map<genre, "Title1, Title2, Title3">
    public static Map<String, String> titlesSummaryByGenre(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy + mapping(title, toList())
        //       + collectingAndThen(..., list -> sorted stream joined with ", ")
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.mapping(
                                Book::title,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted()
                                                .collect(Collectors.joining(", "))
                                )
                        )
                ));
    }

    // 7. Unique tags per genre as an unmodifiable Set
    // Returns Map<genre, Set<tag>> — flattened from all books in that genre, unmodifiable
    public static Map<String, Set<String>> tagsByGenre(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using groupingBy + Collectors.flatMapping(b -> b.tags().stream(), toSet())
        //       + collectingAndThen(..., Collections::unmodifiableSet)
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::genre,
                        Collectors.flatMapping(
                                book -> book.tags().stream(),
                                Collectors.collectingAndThen(
                                        Collectors.toSet(),
                                        Collections::unmodifiableSet
                                )
                        )
                ));
    }

    // 8. Book catalog: isbn → formatted summary string
    // Returns Map<isbn, "Title by Author (Genre, $price)"> — unmodifiable
    public static Map<String, String> bookCatalog(List<Book> books) {
        if (books == null) throw new IllegalArgumentException("Books cannot be null");
        // TODO: implement using Collectors.toMap(Book::isbn, b -> formatted string)
        //       + collectingAndThen(..., Collections::unmodifiableMap)
        return books.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                Book::isbn,
                                b -> b.title() + " by " + b.author() + " (" + b.genre() + ", $" + b.price() + ")"
                        ),
                        Collections::unmodifiableMap
                ));
    }

}
