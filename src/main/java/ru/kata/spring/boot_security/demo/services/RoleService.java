package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    List<Role> findAll();

    void save(Role role);

    void delete(String rolename);
}
