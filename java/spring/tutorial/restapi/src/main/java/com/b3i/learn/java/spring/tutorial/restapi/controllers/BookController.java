package com.b3i.learn.java.spring.tutorial.restapi.controllers;

import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.AuthorDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.BookDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;
import com.b3i.learn.java.spring.tutorial.restapi.mappers.Mapper;
import com.b3i.learn.java.spring.tutorial.restapi.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDTO> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDTO> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDTO bookDTO){
        BookEntity bookEntity = bookMapper.mapFrom(bookDTO);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBook = bookService.createUpdateBook(isbn, bookEntity);
        BookDTO savedUpdatedBookDTO = bookMapper.mapTo(savedBook);
        if(bookExists){
            return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public Page<BookDTO> findBooks(Pageable pageable){
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDTO bookDTO = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO bookDTO){
        if(!bookService.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            BookEntity bookEntity = bookMapper.mapFrom(bookDTO);
            BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
            return new ResponseEntity<>(
                    bookMapper.mapTo(updatedBookEntity),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
