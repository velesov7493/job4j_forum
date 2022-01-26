package ru.job4j.forum.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "tj_posts")
public class Post {

    private static final String DATE_FORMAT = "HH:mm X / dd.MM.yyyy";

    @Id
    @SequenceGenerator(name = "postsIdSeq", sequenceName = "tj_posts_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postsIdSeq")
    private int id;
    private String caption;
    private String description;
    private Calendar created;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topic")
    private Post topic;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private User author;

    public Post() {
        created = Calendar.getInstance();
    }

    public static Post of(String name) {
        Post post = new Post();
        post.caption = name;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String value) {
        caption = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(created.getTime());
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Post getTopic() {
        return topic;
    }

    public void setTopic(Post topic) {
        this.topic = topic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return
                id == post.id
                && Objects.equals(caption, post.caption)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, description, created);
    }
}
