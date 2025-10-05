package com.polarbookshop.catalogservice.persistance;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final static Map<String, Book> BOOK_MAP = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return BOOK_MAP.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(BOOK_MAP.get(isbn));
    }

    @Override
    public boolean existByIsbn(String isbn) {
        return BOOK_MAP.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
        BOOK_MAP.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        BOOK_MAP.remove(isbn);
    }
}
