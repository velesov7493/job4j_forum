package ru.job4j.forum.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.repositories.PostRepository;

import java.util.*;

@Service
public class PostService {

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

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

    public Post save(Post value, int userId, int topicId) {
        Post result = null;
        try {
            if (topicId != 0) {
                Post topic = findById(topicId);
                value.setTopic(topic);
            }
            if (userId != 0) {
                value.setAuthor(users.getUserById(userId));
            }
            result = posts.save(value);
        } catch (Throwable ex) {
            LOG.error("Ошибка сохранения сообщения/темы: ", ex);
        }
        return result;
    }

    public Post findById(Integer integer) {
        return posts.findById(integer).orElse(null);
    }

    public void deleteById(Integer postId) {
        try {
            posts.deleteById(postId);
        } catch (Throwable ex) {
            LOG.error("Ошибка удаления сообщения/темы: ", ex);
        }
    }
}
