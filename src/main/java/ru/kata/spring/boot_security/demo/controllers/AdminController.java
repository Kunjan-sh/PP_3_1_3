package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String admin(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/addUser")
    public String showAddUserPage() {
        return "addUser";
    }

    @GetMapping("/deleteUser")
    public String showDeleteUserPage() {
        return "deleteUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        if (roleIds != null && !roleIds.isEmpty()) {
            userService.addUser(user.getUsername(), user.getPassword(), user.getAge(), roleIds);
        }
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute User user) {
        userService.deleteUser(user.getUsername());
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUser() {
        return "/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            User existingUser = userService.findByUsername(user.getUsername());
            if (existingUser != null) {
                roleIds = existingUser.getRoles().stream()
                        .map(Role::getId)
                        .collect(Collectors.toList());
            }
        }
        userService.updateUser(user.getUsername(), user.getPassword(), user.getAge(), roleIds);
        return "redirect:/admin";
    }
}
