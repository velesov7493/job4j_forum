package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

	private final Map<Integer, Post> posts = new HashMap<>();
	private final AtomicInteger generator = new AtomicInteger(0);

	public PostService() {
		Post p = Post.of("Пример темы");
		p.setDescription("Текст сообщения темы");
		addPost(p);
	}

	public Collection<Post> getAll() {
		return posts.values();
	}

	private void addPost(Post value) {
		value.setId(generator.incrementAndGet());
		posts.put(value.getId(), value);
	}

	private void updatePost(Post value) {
		posts.put(value.getId(), value);
	}

	public void savePost(Post value) {
		if (value.getId() == 0) {
			addPost(value);
		} else {
			updatePost(value);
		}
	}

	public Post getPostById(int postId) {
		return posts.get(postId);
	}
}
