package com.b3i.learn.java.spring.tutorial.restapi.controllers;

import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.AuthorDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import com.b3i.learn.java.spring.tutorial.restapi.mappers.Mapper;
import com.b3i.learn.java.spring.tutorial.restapi.services.AuthorService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDTO> authorMapper;
    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDTO> authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity =  authorService.saveAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDTO> listAuthors(){
        List<AuthorEntity> authors = authorService.findAll();
        return authors
                .stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorEntity -> {
            AuthorDTO authorDTO = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO){
        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            authorDTO.setId(id);
            AuthorEntity authorEntity = authorMapper.mapFrom(authorDTO);
            AuthorEntity savedAuthorEntity = authorService.saveAuthor(authorEntity);
            return new ResponseEntity<>(
                    authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO){
        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            AuthorEntity authorEntity = authorMapper.mapFrom(authorDTO);
            AuthorEntity updatedAuthorEntity = authorService.partialUpdate(id, authorEntity);
            return new ResponseEntity<>(
                    authorMapper.mapTo(updatedAuthorEntity),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id){
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
