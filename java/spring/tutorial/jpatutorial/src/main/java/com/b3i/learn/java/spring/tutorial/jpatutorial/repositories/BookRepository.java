package com.b3i.learn.java.spring.tutorial.jpatutorial.repositories;

import com.b3i.learn.java.spring.tutorial.jpatutorial.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
