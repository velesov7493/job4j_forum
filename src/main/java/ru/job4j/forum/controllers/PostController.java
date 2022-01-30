package ru.job4j.forum.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostService posts;

    public PostController(PostService service) {
        posts = service;
    }

    @GetMapping({"/", "/home"})
    public String indexPage(Model model) {
        model.addAttribute("posts", posts.findAllTopics());
        return "post/topicList";
    }

    @GetMapping("/topic/{id}/posts")
    public String topicPosts(
            @PathVariable(name = "id") int topicId,
            Model model
    ) {
        List<Post> postList = new ArrayList<>();
        postList.add(posts.findById(topicId));
        postList.addAll(posts.findAllByTopicId(topicId));
        model.addAttribute("posts", postList);
        model.addAttribute("topicId", topicId);
        return "post/postList";
    }

    @GetMapping("/post/{id}")
    public String editPost(@PathVariable(name = "id") int postId, Model model) {
        model.addAttribute("post", posts.findById(postId));
        return "post/edit";
    }

    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable(name = "id") int postId) {
        posts.deleteById(postId);
        return "redirect:/";
    }

    @GetMapping("/topic/{id}/add-post")
    public String addPost(@PathVariable(name = "id") int topicId, Model model) {
        model.addAttribute("topicId", topicId);
        return "post/edit";
    }

    @GetMapping("/topic/add")
    public String addTopic() {
        return "post/edit";
    }

    @PostMapping("/post/save")
    public String savePost(HttpSession s, HttpServletRequest req, @ModelAttribute Post post) {
        User currentUser = (User) s.getAttribute("user");
        String sTopicId = req.getParameter("topicId");
        int topicId = sTopicId == null ? 0 : Integer.parseInt(sTopicId);
        posts.save(post, currentUser.getId(), topicId);
        return "redirect:/";
    }
}