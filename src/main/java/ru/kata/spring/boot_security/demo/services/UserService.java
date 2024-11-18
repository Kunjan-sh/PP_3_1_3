package ru.kata.spring.boot_security.demo.services;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    @Transactional(readOnly = true)
    Set<Role> getRolesByNames(List<String> roleNames);

    void addUser(User user);

    void updateUser(User updatedUser);

    void deleteUser(String username);

    User findByUsername(String username);

    List<User> getUsers();
}
