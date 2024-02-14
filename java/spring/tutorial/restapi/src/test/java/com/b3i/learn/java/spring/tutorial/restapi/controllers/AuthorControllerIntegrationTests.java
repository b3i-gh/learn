package com.b3i.learn.java.spring.tutorial.restapi.controllers;

import com.b3i.learn.java.spring.tutorial.restapi.TestDataUtil;
import com.b3i.learn.java.spring.tutorial.restapi.domain.dto.AuthorDTO;
import com.b3i.learn.java.spring.tutorial.restapi.domain.entities.AuthorEntity;
import com.b3i.learn.java.spring.tutorial.restapi.services.AuthorService;
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
public class AuthorControllerIntegrationTests {
    private AuthorService authorService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper =new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Mario Rossi")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("80")
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        authorService.saveAuthor(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Aria Montgomery")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value("80")
        );
    }

    @Test
    public void testThatFindOneAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFindOneAuthorReturnsHttpStatus404WhenAuthorDoesntExist() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        authorService.saveAuthor(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Aria Montgomery")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("80")
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorDTO testAuthorDTOA = TestDataUtil.createTestAuthorDTOA();
        String authorDTOJson = objectMapper.writeValueAsString(testAuthorDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDTOJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenAuthorDoesntExist() throws Exception{
        AuthorDTO testAuthorDTOA = TestDataUtil.createTestAuthorDTOA();
        String authorDTOJson = objectMapper.writeValueAsString(testAuthorDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDTOJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorEntity authorDTO = TestDataUtil.createTestAuthorEntityB();
        authorDTO.setId(savedAuthor.getId());

        String authorDTOUpdateJson = objectMapper.writeValueAsString(authorDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDTOUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorDTO.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDTO.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturns200() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorDTO testAuthorDTOA = TestDataUtil.createTestAuthorDTOA();
        testAuthorDTOA.setName(("UPDATED"));
        String authorDTOJson = objectMapper.writeValueAsString(testAuthorDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDTOJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorDTO testAuthorDTOA = TestDataUtil.createTestAuthorDTOA();
        testAuthorDTOA.setName(("UPDATED"));
        String authorDTOJson = objectMapper.writeValueAsString(testAuthorDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDTOJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(("UPDATED"))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDTOA.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturns204ForNonExistingAuthor() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/9999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAuthorReturns204ForExistingAuthor() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
