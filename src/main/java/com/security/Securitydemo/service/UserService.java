package com.security.Securitydemo.service;

import com.security.Securitydemo.dto.UserConverter;
import com.security.Securitydemo.dto.UserDto;
import com.security.Securitydemo.exception.DuplicateResourceException;
import com.security.Securitydemo.exception.InvalidDataException;
import com.security.Securitydemo.exception.UserNotFoundException;
import com.security.Securitydemo.model.Role;
import com.security.Securitydemo.model.User;
import com.security.Securitydemo.repository.UserRepository;
import com.security.Securitydemo.request.RegisterRequest;
import com.security.Securitydemo.request.UserChangePassword;
import com.security.Securitydemo.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.security.Securitydemo.util.ErrorMessage.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter converter;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserConverter converter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
    }


    public void createUser(RegisterRequest request) {
        emailCheck(request.getEmail());
        passwordCheck(request.getPassword());
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
        User fromDb = userFromDb(id);
        return converter.userModelToDto(fromDb);
    }


    public UserDto updateUser(UserRequest userRequest, Long id) {
        User fromDb = userFromDb(id);
        if (userRequest.getName() != null
                && !userRequest.getName().equals(fromDb.getName())) {
            fromDb.setName(userRequest.getName());
        }
        if (userRequest.getSurname() != null
                && !userRequest.getSurname().equals(fromDb.getSurname())) {
            fromDb.setSurname(userRequest.getSurname());
        }
        if (userRequest.getUsername() != null
                && !userRequest.getUsername().equals(fromDb.getUsername())
                && !userRequest.getUsername().isEmpty()) {
            fromDb.setUsername(userRequest.getUsername());
        }
        userRepository.save(fromDb);
        return converter.userModelToDto(fromDb);
    }


    public void updatePassword(Long id, UserChangePassword request) {
        User fromDb = userFromDb(id);
        passwordCheck(request.getPassword());
        if (request.getPassword() != null
                && !request.getPassword().equals(fromDb.getPassword())
                && !request.getPassword().isEmpty()) {
            fromDb.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    private void emailCheck(String email) {
        String emailRegex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (userRepository.existsUserByEmail(email)) {
            throw new DuplicateResourceException(TAKEN_EMAIL);
        }
        if (email.isEmpty()) {
            throw new InvalidDataException(EMAIL_NOT_NULL);
        }
        if (!email.matches(emailRegex)) {
            throw new InvalidDataException(INVALID_EMAIL);
        }
    }

    private void passwordCheck(String password) {
        String passwordRegex = "^[a-zA-Z0-9]{6,10}$";
        if (password.isEmpty()) {
            throw new InvalidDataException(PASSWORD_NOT_NULL);
        }
        if (!password.matches(passwordRegex)) {
            throw new InvalidDataException(INVALID_PASSWORD);
        }
    }

    private User userFromDb(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USERID_NOT_FOUND + id));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(USERNAME_NOT_FOUND + username));
    }

    protected UserDto getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserDto.builder()
                .username(userDb.getUsername())
                .build();
    }

}
