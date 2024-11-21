package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
//    @Transactional(readOnly = true)
//    Set<Role> getRolesByNames(List<String> roleNames);

    void addUser(User user);

    void updateUser(User updatedUser);

    void deleteUser(Long id);

    User findByEmail(String email);

    User findById(Long id);

    List<User> getUsers();

}
