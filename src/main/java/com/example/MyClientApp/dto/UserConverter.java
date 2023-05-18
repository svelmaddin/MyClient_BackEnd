package com.example.MyClientApp.dto;

import com.example.MyClientApp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User dtoToModel(UserDto from) {
        User user = new User();
        user.setUsername(from.username());
        user.setEmail(from.email());
        user.setName(from.name());
        user.setSurname(from.surname());
        return user;
    }

    public UserDto userModelToDto(User from) {
        return UserDto.builder()
                .name(from.getName())
                .surname(from.getSurname())
                .email(from.getEmail())
                .username(from.getUsername())
                .profileImageId(from.getProfileImageId())
                .build();
    }
}
