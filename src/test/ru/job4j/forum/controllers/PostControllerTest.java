package ru.job4j.forum.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHomePage() throws Exception {
        mockMvc.perform(
            get("/home"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("post/topicList")
        );
    }

    @Test
    @WithMockUser
    public void shouldReturnTopicPosts() throws Exception {
        mockMvc.perform(
            get("/topic/1/posts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("post/postList")
        );
    }

    @Test
    @WithMockUser
    public void shouldReturnPostEditPage() throws Exception {
        mockMvc.perform(
            get("/post/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("post/edit")
        );
    }

    @Test
    @WithMockUser
    public void shouldReturnTopic1PostEditPage() throws Exception {
        mockMvc.perform(
            get("/topic/1/add-post"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("post/edit")
        );
    }

    @Test
    @WithMockUser
    public void shouldReturnTopicPostEditPage() throws Exception {
        mockMvc.perform(
            get("/topic/add"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("post/edit")
        );
    }
}
