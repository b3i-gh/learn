package com.b3i.learn.java.spring.tutorial.restapi.services;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);
}
