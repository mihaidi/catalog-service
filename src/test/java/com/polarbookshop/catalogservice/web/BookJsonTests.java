package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> jacksonTester;


    @Test
    void testSerialize() throws IOException {

        Book book = new Book("1234567890", "Title", "Author", 20D);

        JsonContent<Book> jsonContent = jacksonTester.write(book);

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());

        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());

        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws IOException {

        String content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 20.0
                }
                """;

        ObjectContent<Book> parse = jacksonTester.parse(content);

        assertThat(parse)
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 20D));

    }
}
