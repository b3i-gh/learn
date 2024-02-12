package com.b3i.learn.java.spring.tutorial.restapi.services.impl;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;
import com.b3i.learn.java.spring.tutorial.restapi.repositories.BookRepository;
import com.b3i.learn.java.spring.tutorial.restapi.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
