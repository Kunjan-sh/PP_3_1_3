package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

    Set<Role> findByUsers(User user);
}
