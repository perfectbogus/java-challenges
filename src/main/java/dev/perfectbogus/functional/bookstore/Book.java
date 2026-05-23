package dev.perfectbogus.functional.bookstore;

import java.util.List;

public record Book(
        String isbn,
        String title,
        String author,
        String genre,
        double price,
        int stock,
        int yearPublished,
        BookStatus status,
        List<String> tags
) {}
