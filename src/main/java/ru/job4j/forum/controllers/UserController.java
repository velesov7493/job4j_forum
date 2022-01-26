package ru.job4j.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.Security;
import ru.job4j.forum.models.User;
import ru.job4j.forum.models.net.Authentication;
import ru.job4j.forum.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService users;

    public UserController(UserService service) {
        users = service;
    }

    private void handleError(HttpSession session, Model model) {
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error);
            session.removeAttribute("error");
        }
    }

    @GetMapping("/login")
    public String loginForm(HttpSession session, Model model) {
        handleError(session, model);
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(HttpSession session, Model model) {
        handleError(session, model);
        return "user/register";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute Authentication auth) {
        User u = users.login(auth);
        if (u != null) {
            session.setAttribute("user", u);
            return "redirect:/";
        } else {
            session.setAttribute("error", "Неправильный логин и/или пароль!");

            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(
            HttpSession session,
            HttpServletRequest req,
            @ModelAttribute User user
    ) {
        if (user.getPassword() == null) {
            session.setAttribute("error", "Пустой пароль!");
            return "redirect:/register";
        }
        if (!user.getPassword().equals(req.getParameter("checkPassword"))) {
            session.setAttribute("error", "Пароль не совпадает с подтверждением!");
            return "redirect:/register";
        }
        user.setEnabled(true);
        user.setPassword(Security.getSHA1(user.getPassword()));
        if (users.saveUser(user) == null) {
            session.setAttribute(
                    "error",
                    "Провал сохранения пользователя: указанный email уже используется!"
            );
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}