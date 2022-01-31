package ru.job4j.forum.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

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
        User result = null;
        try {
            value.setRole(findRoleById(2));
            result = users.save(value);
        } catch (Throwable ex) {
            LOG.error("Ошибка сохранения пользователя: ", ex);
        }
        return result;
    }

    public void deleteUserById(int userId) {
        users.deleteById(userId);
    }

    public List<Role> findAllRoles() {
        return roles.findAll();
    }

    public Role saveRole(Role value) {
        Role result = null;
        try {
            result = roles.save(value);
        } catch (Throwable ex) {
            LOG.error("Ошибка сохранения роли: ", ex);
        }
        return result;
    }

    public Role findRoleById(int roleId) {
        return roles.findById(roleId).orElse(null);
    }

    public void deleteById(int roleId) {
        try {
            roles.deleteById(roleId);
        } catch (Throwable ex) {
            LOG.error("Ошибка удаления роли: ", ex);
        }
    }
}
