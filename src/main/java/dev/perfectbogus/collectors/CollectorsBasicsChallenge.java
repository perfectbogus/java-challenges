package dev.perfectbogus.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsBasicsChallenge {

    // Used by all challenges. Fully implemented — nothing to do here.
    public static class Book {
        private final String title;
        private final String author;
        private final String genre;
        private final int pages;
        private final double price;

        public Book(String title, String author, String genre, int pages, double price) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.pages = pages;
            this.price = price;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getGenre() { return genre; }
        public int getPages() { return pages; }
        public double getPrice() { return price; }
    }

    // CHALLENGE 1
    // Returns the titles of all books with at least minPages pages, in the
    // same order as in books. Does not modify books.
    public static List<String> titlesOfLongBooks(List<Book> books, int minPages) {
        return books.stream().filter(b -> b.getPages() >= minPages).map(Book::getTitle).collect(Collectors.toList());
    }

    // CHALLENGE 2
    // Returns the set of distinct genres that appear in books.
    // Returns an empty set if books is empty.
    public static Set<String> distinctGenres(List<Book> books) {
        return books.stream().map(Book::getGenre).collect(Collectors.toSet());
    }

    // CHALLENGE 3
    // Returns all titles joined into one String, in the same order as in books,
    // separated by ", " (comma and one space), with no leading or trailing
    // separator. Returns "" if books is empty.
    public static String joinTitles(List<Book> books) {
        return books.stream().map(Book::getTitle).collect(Collectors.joining(", "));
    }

    // CHALLENGE 4
    // Returns a map from each title to its number of pages.
    // Titles are unique within books. Returns an empty map if books is empty.
    public static Map<String, Integer> pagesByTitle(List<Book> books) {
        return books.stream().collect(Collectors.toMap(
                Book::getTitle,
                Book::getPages
        ));
    }

    // CHALLENGE 5
    // Returns how many books there are in each genre.
    // Only genres that appear in books are keys.
    public static Map<String, Long> countByGenre(List<Book> books) {
        return books.stream().collect(Collectors.groupingBy(
                Book::getGenre,
                Collectors.counting()
        ));
    }

    // CHALLENGE 6
    // Returns the titles of the books of each genre, in the same order as in
    // books. Only genres that appear in books are keys.
    public static Map<String, List<String>> titlesByGenre(List<Book> books) {
        return books.stream().collect(Collectors.groupingBy(
                Book::getGenre,
                Collectors.mapping(
                        Book::getTitle,
                        Collectors.toList()
                )
        ));
    }

    // CHALLENGE 7
    // Splits the books into two lists: the key true holds the books with more
    // than 300 pages, the key false holds all the others (300 pages exactly
    // counts as not more than 300). Each list keeps the order of books.
    // Both keys are always present, even if one of the lists is empty.
    public static Map<Boolean, List<Book>> partitionByLongBook(List<Book> books) {
        return books.stream().collect(Collectors.partitioningBy(b -> b.getPages() > 300));
    }

    // CHALLENGE 8
    // Returns the total number of pages written by each author (the sum of the
    // pages of all their books). Only authors that appear in books are keys.
    public static Map<String, Integer> totalPagesByAuthor(List<Book> books) {
        return books.stream().collect(Collectors.toMap(
                Book::getAuthor,
                Book::getPages,
                Integer::sum
        ));
    }

    // CHALLENGE 9
    // Returns the average price of the books of each genre.
    // Only genres that appear in books are keys.
    public static Map<String, Double> averagePriceByGenre(List<Book> books) {
        return books.stream().collect(Collectors.groupingBy(
                Book::getGenre,
                Collectors.averagingDouble(Book::getPrice)
        ));
    }

    // CHALLENGE 10
    // Returns the book with the lowest price. If several books share the lowest
    // price, returns the first of them in books. Returns an empty Optional if
    // books is empty.
    public static Optional<Book> cheapestBook(List<Book> books) {
        Comparator<Book> byPrice = Comparator.comparingDouble(Book::getPrice);
        return books.stream().min(byPrice);
    }
}