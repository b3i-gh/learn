package com.b3i.learn.java.spring.tutorial.restapi.mappers.impl;

import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.BookDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;
import com.b3i.learn.java.spring.tutorial.restapi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDTO> {
  private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookEntity mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }
}
