package ru.job4j.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
