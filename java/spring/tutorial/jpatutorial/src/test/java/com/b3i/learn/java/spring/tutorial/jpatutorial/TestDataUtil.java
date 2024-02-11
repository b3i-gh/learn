package com.b3i.learn.java.spring.tutorial.jpatutorial;

import com.b3i.learn.java.spring.tutorial.jpatutorial.domain.Author;
import com.b3i.learn.java.spring.tutorial.jpatutorial.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Mario Rossi")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Giovanni Verdi")
                .age(33)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Anna Bianchi")
                .age(27)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-3-8462-9751-1")
                .title("The golden crodiga")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-2-7518-9587-2")
                .title("Bibiribù")
                .author(author)
                .build();
    }
}
