package com.b3i.learn.java.spring.tutorial.restapi.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="books")
public class BookEntity {
    @Id
    private String isbn;
    private String title;
    @ManyToOne
    @JoinColumn(name="author_id")
    private AuthorEntity author;

}
