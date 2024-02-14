package com.b3i.learn.java.spring.tutorial.restapi.controllers;

import com.b3i.learn.java.spring.tutorial.restapi.TestDataUtil;
import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.AuthorDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.BookDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.BookEntity;
import com.b3i.learn.java.spring.tutorial.restapi.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private BookService bookService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService){
        this.mockMvc = mockMvc;
        objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void TestThatCreateBookReturnsHttpStatus201Created() throws Exception{
        BookDTO bookDTO = TestDataUtil.createTestBookDTOA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDTO.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void TestThatCreateBookReturnsCreatedBook() throws Exception{
        BookDTO bookDTO = TestDataUtil.createTestBookDTOA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDTO.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDTO.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDTO.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("978-1-2345-6789-1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The shadow in the attic")
        );
    }

    @Test
    public void testThatFindOneBookReturnsHttpStatus200WhenBookExists() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFindOneBookReturnsHttpStatus404WhenBookDoesntExist() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/book/xyz")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetBookReturnsBookWhenBookExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The shadow in the attic")
        );
    }

    @Test
    public void testThatFullUpdateBookReturnsHttpStatus200WhenBookExists() throws Exception{
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDTO testBookA = TestDataUtil.createTestBookDTOA(null);
        testBookA.setIsbn(savedBookEntity.getIsbn());
        String bookJson = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + savedBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDTO testBookA = TestDataUtil.createTestBookDTOA(null);
        testBookA.setIsbn(savedBookEntity.getIsbn());
        testBookA.setTitle(("UPDATED"));
        String bookJson = objectMapper.writeValueAsString(testBookA);


        String authorDTOUpdateJson = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + savedBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200WhenBookExists() throws Exception{
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDTO testBookA = TestDataUtil.createTestBookDTOA(null);
        testBookA.setTitle(("UPDATED"));
        String bookJson = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + savedBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception{
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDTO testBookA = TestDataUtil.createTestBookDTOA(null);
        testBookA.setTitle(("UPDATED"));
        String bookJson = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + savedBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBookEntityA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatDeleteBookReturns204ForNonExistingBook() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/jkljiojio")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteBookReturns204ForExistingBook() throws Exception{
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + testBookEntityA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
