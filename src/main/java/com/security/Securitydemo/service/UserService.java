package com.security.Securitydemo.service;

import com.security.Securitydemo.dto.UserConverter;
import com.security.Securitydemo.dto.UserDto;
import com.security.Securitydemo.exception.DuplicateResourceException;
import com.security.Securitydemo.exception.NotNullException;
import com.security.Securitydemo.exception.UserNotFoundException;
import com.security.Securitydemo.model.Role;
import com.security.Securitydemo.model.User;
import com.security.Securitydemo.repository.UserRepository;
import com.security.Securitydemo.request.RegisterRequest;
import com.security.Securitydemo.request.UserChangePassword;
import com.security.Securitydemo.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter converter;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserConverter converter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
    }

    public void createUser(RegisterRequest request) {
        emailAndPasswordCheck(request);
        User user = new User();
        if (request.getUsername().isEmpty()) {
            user.setUsername(request.getEmail());
        } else {
            user.setUsername(request.getUsername());
        }
        user.setEmail(request.getEmail());
        user.setRole(Role.valueOf("USER"));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public UserDto findUserById(Long id) {
        User fromDb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with given id: " + id));
        return converter.userModelToDto(fromDb);
    }

    public UserDto updateUser(UserRequest userRequest, Long id) {
        User fromDb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with given id: " + id));

        if (userRequest.getName() != null
                && !userRequest.getName().equals(fromDb.getName())) {
            fromDb.setName(userRequest.getName());
        }
        if (userRequest.getSurname() != null
                && !userRequest.getSurname().equals(fromDb.getSurname())) {
            fromDb.setSurname(userRequest.getSurname());
        }
        if (userRequest.getUsername() != null && !userRequest.getUsername().equals(fromDb.getUsername())) {
            fromDb.setUsername(userRequest.getUsername());
        }
        userRepository.save(fromDb);
        return converter.userModelToDto(fromDb);
    }

    public void updatePassword(Long id, UserChangePassword request) {
        User fromDb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with given id: " + id));

        if (request.getPassword() != null) {
            fromDb.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    protected User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with given username: " + username));
    }

    protected UserDto getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserDto.builder()
                .username(userDb.getUsername())
                .build();
    }

    private void emailAndPasswordCheck(RegisterRequest request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new DuplicateResourceException("email already taken");
        }
        if (request.getEmail().isEmpty()) {
            throw new NotNullException("User Email can not be empty!");
        }
        if (request.getPassword().isEmpty()) {
            throw new NotNullException("User password can not be empty!");
        }
    }

}
