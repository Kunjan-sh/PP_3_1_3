package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    void addUser(String username, String password, Integer age, List<Long> roleIds);

    void updateUser(String username, String password, Integer age, List<Long> roleIds);

    void deleteUser(String username);

    User findByUsername(String username);

    List<User> getUsers();
}
