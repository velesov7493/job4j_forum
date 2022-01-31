package ru.job4j.forum.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Application;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.UserService;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService users;

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

    @Test
    public void shouldUserServiceSaveCall() throws Exception {
        mockMvc.perform(
                post("/register")
                        .param("name", "Власов Александр Сергеевич")
                        .param("login", "sysdba")
                        .param("email", "velesov7493@yandex.ru")
                        .param("password", "masterkey")
                        .param("checkPassword", "masterkey")
        ).andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(users, times(1)).saveUser(argument.capture());
        assertThat(argument.getValue().getName(), is("Власов Александр Сергеевич"));
        assertThat(argument.getValue().getLogin(), is("sysdba"));
        assertThat(argument.getValue().getEmail(), is("velesov7493@yandex.ru"));
    }
}
