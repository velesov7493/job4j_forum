package ru.job4j.forum.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Role;
import ru.job4j.forum.models.User;
import ru.job4j.forum.repositories.RoleRepository;
import ru.job4j.forum.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository users;
    private final RoleRepository roles;

    public UserService(UserRepository repo1, RoleRepository repo2) {
        users = repo1;
        roles = repo2;
    }

    public User getCurrentUser(Authentication auth) {
        String login = ((UserDetails) auth.getPrincipal()).getUsername();
        return users.findByLogin(login);
    }

    public User getUserById(int userId) {
        return users.findById(userId).orElse(null);
    }

    public List<User> findAllUsers() {
        return users.findAll();
    }

    public User saveUser(User value) {
        value.setRole(findRoleById(2));
        return users.save(value);
    }

    public void deleteUserById(int userId) {
        users.deleteById(userId);
    }

    public List<Role> findAllRoles() {
        return roles.findAll();
    }

    public Role saveRole(Role value) {
        return roles.save(value);
    }

    public Role findRoleById(int roleId) {
        return roles.findById(roleId).orElse(null);
    }

    public void deleteById(int roleId) {
        roles.deleteById(roleId);
    }
}
