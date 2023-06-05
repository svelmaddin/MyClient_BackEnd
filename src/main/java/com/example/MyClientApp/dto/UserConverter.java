package com.example.MyClientApp.dto;

import com.example.MyClientApp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto userModelToDto(User from) {
        return UserDto.builder()
                .id(from.getId())
                .name(from.getName())
                .surname(from.getSurname())
                .email(from.getEmail())
                .profilePhoto(from.getProfilePhoto())
                .build();
    }
}
