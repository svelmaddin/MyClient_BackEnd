package com.example.MyClientApp.dto;

import com.example.MyClientApp.model.Gender;
import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String email,
        String name,
        String surname,
        String phoneNumber,
        Gender gender,
        boolean active,
        byte[] profilePhoto
) {
}
