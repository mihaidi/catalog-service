package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//example of sliced tests
//limit the
//context to the beans used by the speciﬁc controller under test
@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc; // lets you test web endpoints without loading a tomcat server


    @MockitoBean
    private BookService bookService;


    @Test
    void whenGetBookNotExistingReturn404() throws Exception {

        String isbn = "1234567890";

        Mockito.when(bookService.viewBookDetail(isbn))
                .thenThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/%s".formatted(isbn)))
                .andExpect(status().isNotFound());

    }
}
