package com.b3i.learn.java.spring.tutorial.restapi.controllers;

import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.BookDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;
import com.b3i.learn.java.spring.tutorial.restapi.mappers.Mapper;
import com.b3i.learn.java.spring.tutorial.restapi.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDTO> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDTO> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDTO bookDTO){
        BookEntity bookEntity = bookMapper.mapFrom(bookDTO);
        BookEntity savedBook = bookService.createBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.CREATED);
    }
}
