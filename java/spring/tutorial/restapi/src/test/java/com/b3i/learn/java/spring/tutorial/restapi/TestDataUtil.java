package com.b3i.learn.java.spring.tutorial.restapi;

import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.AuthorDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.BookDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;

public class TestDataUtil {
    public static AuthorEntity createTestAuthorEntityA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Mario Rossi")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Giovanni Verdi")
                .age(33)
                .build();
    }

    public static AuthorEntity createTestAuthorEntityC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Anna Bianchi")
                .age(27)
                .build();
    }

    public static BookEntity createTestBookEntityA(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static BookDTO createTestBookDTOA(final AuthorDTO author) {
        return BookDTO.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static AuthorDTO createTestAuthorDTOA(){
        return AuthorDTO.builder()
                .id(2L)
                .name("Aria Montgomery")
                .age(80)
                .build();
    }
//
//    public static BookEntity createTestBookEntityB(final AuthorEntity author) {
//        return BookEntity.builder()
//                .isbn("978-3-8462-9751-1")
//                .title("The golden crodiga")
//                .author(author)
//                .build();
//    }
//
//    public static BookEntity createTestBookEntityC(final AuthorEntity author) {
//        return BookEntity.builder()
//                .isbn("978-2-7518-9587-2")
//                .title("Bibiribù")
//                .author(author)
//                .build();
//    }
}
