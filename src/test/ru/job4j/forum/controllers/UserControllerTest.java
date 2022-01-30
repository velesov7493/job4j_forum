package ru.job4j.forum.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnLoginPage() throws Exception {
        mockMvc.perform(
            get("/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("user/login")
        );
    }

    @Test
    public void shouldReturnRegisterPage() throws Exception {
        mockMvc.perform(
            get("/register"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("user/register")
        );
    }
}
