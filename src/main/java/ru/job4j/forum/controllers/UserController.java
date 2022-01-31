package ru.job4j.forum.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService users;
    private final PasswordEncoder encoder;

    public UserController(UserService service, PasswordEncoder passwordEncoder) {
        users = service;
        encoder = passwordEncoder;
    }

    private void handleError(HttpSession session, Model model) {
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error);
            session.removeAttribute("error");
        }
    }

    @GetMapping("/login")
    public String loginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "success", required = false) String success,
            Authentication auth, HttpSession session, Model model
    ) {
        if (error != null) {
            model.addAttribute("error", "Неверный логин и/или пароль!");
        }
        if (logout != null) {
            model.addAttribute("message", "Вы покидаете форум. До свидания.");
        }
        if (success != null) {
            session.setAttribute("user", users.getCurrentUser(auth));
            return "redirect:/";
        }
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(
            Authentication auth,
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(req, resp, auth);
        }
        return "redirect:/login?logout=true";
    }

    @GetMapping("/register")
    public String registerForm(HttpSession session, Model model) {
        handleError(session, model);
        return "user/register";
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
        user.setPassword(encoder.encode(user.getPassword()));
        if (users.saveUser(user) == null) {
            session.setAttribute(
                    "error",
                    "Провал сохранения пользователя: указанный email или login уже используется!"
            );
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}