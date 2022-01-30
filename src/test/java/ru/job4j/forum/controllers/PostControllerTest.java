package ru.job4j.forum.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Application;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.services.PostService;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

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

    @Test
    @WithMockUser
    public void shouldPostServiceSaveCall() throws Exception {
        mockMvc.perform(
            post("/post/save")
            .param("caption","Куплю ладу-грант. Дорого.")
            .param("description","Телефон: 89016931112")
            .param("topicId","1")
        ).andDo(print())
        .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts, times(1)).save(argument.capture(), eq(0), eq(1));
        assertThat(argument.getValue().getCaption(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("Телефон: 89016931112"));
    }
}