package com.b3i.learn.java.spring.tutorial.restapi.services;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity saveAuthor(AuthorEntity authorEntity);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
