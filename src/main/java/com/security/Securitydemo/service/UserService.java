package com.security.Securitydemo.service;

import com.security.Securitydemo.dto.UserDto;
import com.security.Securitydemo.exception.GenericException;
import com.security.Securitydemo.model.Role;
import com.security.Securitydemo.model.User;
import com.security.Securitydemo.repository.UserRepository;
import com.security.Securitydemo.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegisterRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setRole(Role.valueOf("USER"));
        userRepository.save(user);
    }

    public UserDto getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserDto.builder()
                .username(userDb.getUsername())
                .role(userDb.getRole())
                .build();
    }

    protected User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("user not found by given id!")
                        .build());
    }
}
