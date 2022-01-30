package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.repositories.PostRepository;

import java.util.*;

@Service
public class PostService {

    private final PostRepository posts;
    private final UserService users;

    public PostService(PostRepository repo, UserService s) {
        posts = repo;
        users = s;
    }

    public List<Post> findAllByTopicId(int topicId) {
        return posts.findAllByTopicId(topicId);
    }

    public List<Post> findAllByAuthorId(int authorId) {
        return posts.findAllByAuthorId(authorId);
    }

    public List<Post> findAll() {
        return posts.findAll();
    }

    public List<Post> findAllTopics() {
        return posts.findAllTopics();
    }

    public void save(Post value, int userId, int topicId) {
        if (topicId != 0) {
            Post topic = findById(topicId);
            value.setTopic(topic);
        }
        if (userId != 0) {
            value.setAuthor(users.getUserById(userId));
        }
        posts.save(value);
    }

    public Post findById(Integer integer) {
        return posts.findById(integer).orElse(null);
    }

    public void deleteById(Integer integer) {
        posts.deleteById(integer);
    }
}
