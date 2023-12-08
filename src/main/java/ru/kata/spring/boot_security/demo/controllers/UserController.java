package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userInfo", user);
        return "user/user_page";
    }

    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") int id, Model model){
        model.addAttribute("userInfo", userService.getUser(id));
        return "user/user_page";
    }
}