package com.example.MyClientApp.service;

import com.example.MyClientApp.dto.UserConverter;
import com.example.MyClientApp.dto.UserDto;
import com.example.MyClientApp.exception.CustomException;
import com.example.MyClientApp.model.User;
import com.example.MyClientApp.repository.UserRepository;
import com.example.MyClientApp.request.RegisterRequest;
import com.example.MyClientApp.request.UserChangePassword;
import com.example.MyClientApp.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.MyClientApp.service.AuthService.getLoggedInUsername;

import static com.example.MyClientApp.util.ErrorMessage.USERNAME_NOT_FOUND;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter converter;
    private final ValidationService validationService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserConverter converter,
                       ValidationService validationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
        this.validationService = validationService;
    }


    protected void createUser(RegisterRequest request) {
        validationService.emailCheck(request.getEmail());
        validationService.usernameCheck(request.getUsername());
        validationService.passwordCheck(request.getPassword(), request.getConfirmPas());
        User user = new User();
        user.setUsername(request.getEmail());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public UserDto updateUser(UserRequest userRequest) {
        validationService.usernameCheck(userRequest.getUsername());
        User fromDb = currentUser();
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


    public void updatePassword(UserChangePassword request) {
        User fromDb = currentUser();
        validationService.passwordCheck(request.getPassword(), request.getConfirmPas());
        if (request.getPassword() != null
                && !request.getPassword().equals(fromDb.getPassword())
                && !request.getPassword().isEmpty()) {
            fromDb.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    public UserDto findUserById() {
        User fromDb = currentUser();
        return converter.userModelToDto(fromDb);
    }

    protected User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(
                        () -> new CustomException(USERNAME_NOT_FOUND + username, "username"));
    }

    protected UserDto getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserDto.builder()
                .username(userDb.getUsername())
                .build();
    }

    private User currentUser() {
        String username = getLoggedInUsername();
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new CustomException(USERNAME_NOT_FOUND + username, "username"));
    }

}
