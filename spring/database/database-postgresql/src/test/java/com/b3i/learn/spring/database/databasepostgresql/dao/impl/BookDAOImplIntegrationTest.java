package com.b3i.learn.spring.database.databasepostgresql.dao.impl;

import com.b3i.learn.spring.database.databasepostgresql.TestDataUtil;
import com.b3i.learn.spring.database.databasepostgresql.dao.AuthorDAO;
import com.b3i.learn.spring.database.databasepostgresql.domain.Author;
import com.b3i.learn.spring.database.databasepostgresql.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOImplIntegrationTest {
    private BookDAOImpl underTest;
    private AuthorDAO authorDAO;
    @Autowired
    public BookDAOImplIntegrationTest(BookDAOImpl underTest, AuthorDAO authorDAO){
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }
    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result =underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);
        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);
        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());
        underTest.create(bookC);
        List<Book> result =underTest.find();
        assertThat(result).hasSize(3).contains(bookA, bookB, bookC);
    }
}
