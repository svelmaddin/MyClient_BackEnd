package com.example.MyClientApp.dto;

import com.example.MyClientApp.model.Gender;
import com.example.MyClientApp.model.Role;
import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String email,
        String name,
        String surname,
        String phoneNumber,
        Role role,
        Gender gender,
        boolean active,
        byte[] profilePhoto
) {
}
