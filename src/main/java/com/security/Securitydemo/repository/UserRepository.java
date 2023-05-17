package com.security.Securitydemo.repository;

import com.security.Securitydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsUserByEmail(String email);
}
