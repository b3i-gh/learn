package com.b3i.learn.java.spring.tutorial.restapi.repositories;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
