package com.b3i.learn.java.spring.tutorial.restapi.domain.dto;

import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String isbn;
    private String title;
    private AuthorDTO author;
}
