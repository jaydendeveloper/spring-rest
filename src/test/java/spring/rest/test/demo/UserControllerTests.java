package spring.rest.test.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import spring.rest.test.demo.models.User;
import tools.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest()
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void shouldAddUser() throws Exception {
        User mockUser = new User(1, "John", "Doe", "john@doe.com", "Password123");

        this.mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(mockUser))
                .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    @Order(2)
    void shouldReturnUser() throws Exception {
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    @Order(3)
    void shouldUpdate() throws Exception {
        User mockUser = new User(1, "Roland", "Doe", "john@doe.com", "Password123");

        this.mockMvc.perform(put("/users/1")
                .content(objectMapper.writeValueAsString(mockUser))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Roland"));
    }

    @Test
    @Order(4)
    void shouldDelete() throws Exception {
        this.mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldReturnCount() throws Exception {
        this.mockMvc.perform(get("/users/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").isNumber());
    }

    @Test
    void shouldReturnRandomUser() throws Exception {
        this.mockMvc.perform(get("/random-user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }
}