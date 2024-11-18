package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsersPage(Model model) {
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
    public String addUser(@ModelAttribute User user, @RequestParam(value = "roles", required = false) List<String> roleNames) {
        user.setRoles(userService.getRolesByNames(roleNames));
        userService.addUser(user);
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
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "roles", required = false) List<String> roleNames) {
        user.setRoles(userService.getRolesByNames(roleNames));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
