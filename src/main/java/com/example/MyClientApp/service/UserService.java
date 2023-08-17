package com.example.MyClientApp.service;

import com.example.MyClientApp.dto.UserConverter;
import com.example.MyClientApp.dto.UserDto;
import com.example.MyClientApp.exception.CustomException;
import com.example.MyClientApp.model.Role;
import com.example.MyClientApp.model.User;
import com.example.MyClientApp.repository.UserRepository;
import com.example.MyClientApp.request.*;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDto> getUserList() {
        return userRepository.findAll()
                .stream().map(converter::userModelToDto).collect(Collectors.toList());
    }

    @Transactional
    protected void createUser(RegisterRequest request) {
        validationService.validationCheckRegister(request);
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getEmail())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .active(true)
                .role(Role.CUSTOMER)
                .build();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public UserDto updateUser(UserRequest userRequest) {
        validationService.updateUserValidationCheck(userRequest);
        User fromDb = currentUser();
        if (userRequest.getName() != null
                && !userRequest.getName().equals(fromDb.getName())) {
            fromDb.setName(userRequest.getName());
        }
        if (userRequest.getSurname() != null
                && !userRequest.getSurname().equals(fromDb.getSurname())) {
            fromDb.setSurname(userRequest.getSurname());
        }
        if (userRequest.getEmail() != null
                && !userRequest.getEmail().equals(fromDb.getEmail())
                && !userRequest.getEmail().isEmpty()) {
            fromDb.setEmail(userRequest.getEmail());
            fromDb.setUsername(userRequest.getEmail());
        }
        userRepository.save(fromDb);
        return converter.userModelToDto(fromDb);
    }

    @Transactional
    public void updatePassword(UserChangePassword request) {
        User fromDb = currentUser();
        validationService.updatePasswordValidationCheck(request);
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

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> findByRole(Role role) {
        return userRepository.findAllByRole(role).stream().map(converter::userModelToDto).collect(Collectors.toList());
    }

    protected UserDto getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserDto.builder()
                .id(userDb.getId())
                .email(userDb.getEmail())
                .name(userDb.getName())
                .surname(userDb.getSurname())
                .profilePhoto(userDb.getProfilePhoto())
                .build();
    }

    protected User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(
                        () -> new CustomException(USERNAME_NOT_FOUND + username, "username"));
    }

    protected User currentUser() {
        String username = getLoggedInUsername();
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new CustomException(USERNAME_NOT_FOUND + username, "username"));
    }

}
