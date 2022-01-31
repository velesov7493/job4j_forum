package ru.job4j.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
