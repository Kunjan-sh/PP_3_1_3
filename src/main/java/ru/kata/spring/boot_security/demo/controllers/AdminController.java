package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Model model, Principal principal) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "admin";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User newUser) {
        userService.addUser(newUser);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}