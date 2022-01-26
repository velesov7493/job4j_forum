package ru.job4j.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.models.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM tj_posts WHERE id_topic=?1", nativeQuery = true)
    List<Post> findAllByTopicId(int topicId);

    @Query(value = "SELECT * FROM tj_posts WHERE id_author=?1", nativeQuery = true)
    List<Post> findAllByAuthorId(int authorId);

    @Query("FROM Post p WHERE p.topic IS NULL")
    List<Post> findAllTopics();
}
