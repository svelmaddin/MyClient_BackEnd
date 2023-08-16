package com.example.MyClientApp.repository;

import com.example.MyClientApp.model.Role;
import com.example.MyClientApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    List<User> findAllByRole(Role role);
}
