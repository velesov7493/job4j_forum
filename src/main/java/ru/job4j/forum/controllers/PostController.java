package ru.job4j.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.PostService;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

	private final PostService posts;

	public PostController(PostService service) {
		posts = service;
	}

	@GetMapping({"/", "/home"})
	public String indexPage(Model model) {
		model.addAttribute("posts", posts.getAll());
		return "post/list";
	}

	@GetMapping("/post/{id}")
	public String editPost(@PathVariable(name = "id") int postId, Model model) {
		model.addAttribute("post", posts.getPostById(postId));
		return "post/edit";
	}

	@GetMapping("/post/add")
	public String editPost() {
		return "post/edit";
	}

	@PostMapping("/post/save")
	public String savePost(HttpSession session, @ModelAttribute Post post) {
		User u = (User) session.getAttribute("user");
		if (u == null) {
			return "redirect:/login";
		}
		posts.savePost(post);
		return "redirect:/";
	}
}