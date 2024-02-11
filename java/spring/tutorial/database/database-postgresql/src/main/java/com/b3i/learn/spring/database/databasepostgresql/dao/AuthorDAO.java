package com.b3i.learn.spring.database.databasepostgresql.dao;

import com.b3i.learn.spring.database.databasepostgresql.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);
}
