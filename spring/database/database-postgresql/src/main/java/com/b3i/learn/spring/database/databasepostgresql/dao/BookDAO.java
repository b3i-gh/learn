package com.b3i.learn.spring.database.databasepostgresql.dao;

import com.b3i.learn.spring.database.databasepostgresql.domain.Author;
import com.b3i.learn.spring.database.databasepostgresql.domain.Book;

import java.util.List;
import java.util.Optional;
public interface BookDAO {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();
}
