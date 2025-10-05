package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsAreCorrectThenValidationSucceeds() {

        Book book = new Book("1234567890", "Title", "Author", 20D);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFailed() {
        Book book = new Book("1", "Title", "Author", 10D);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid");
    }
}
