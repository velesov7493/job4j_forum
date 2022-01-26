package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.repositories.PostRepository;

import java.util.*;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(PostRepository repo) {
        posts = repo;
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

    public Post save(Post value, User author, int topicId) {
        if (topicId != 0) {
            Post topic = findById(topicId);
            value.setTopic(topic);
        }
        value.setAuthor(author);
        return posts.save(value);
    }

    public Post findById(Integer integer) {
        return posts.findById(integer).orElse(null);
    }

    public void deleteById(Integer integer) {
        posts.deleteById(integer);
    }
}
