package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("Book with isbn = %s already exits".formatted(isbn));
    }
}
