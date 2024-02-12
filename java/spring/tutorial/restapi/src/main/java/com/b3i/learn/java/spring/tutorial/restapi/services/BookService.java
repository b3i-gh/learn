package com.b3i.learn.java.spring.tutorial.restapi.services;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}
