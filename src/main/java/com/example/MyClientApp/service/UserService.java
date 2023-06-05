package com.example.MyClientApp.service;

import com.example.MyClientApp.dto.UserConverter;
import com.example.MyClientApp.dto.UserDto;
import com.example.MyClientApp.exception.CustomException;
import com.example.MyClientApp.model.Role;
import com.example.MyClientApp.model.User;
import com.example.MyClientApp.repository.UserRepository;
import com.example.MyClientApp.request.RegisterRequest;
import com.example.MyClientApp.request.UserChangePassword;
import com.example.MyClientApp.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    protected void createUser(RegisterRequest request) {
        validationService.validationCheckRegister(request);
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getEmail())
                .email(request.getEmail())
                .role(Role.valueOf("USER"))
                .build();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public void uploadPhoto(MultipartFile file) {
        User user = currentUser();
        try {
            byte[] fileContent = file.getBytes();
            user.setProfilePhoto(fileContent);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] downloadProfilePhoto() {
        User user = currentUser();
        byte[] profilePhoto = user.getProfilePhoto();
        if (profilePhoto == null) {
            throw new CustomException("Profile photo is empty!", "profile Photo");
        }
        return profilePhoto;
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

    public List<UserDto> getUserList() {
        return userRepository.findAll()
                .stream().map(converter::userModelToDto).collect(Collectors.toList());
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
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
