package com.example.MyClientApp.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String email,
        String username,
        String name,
        byte[] profilePhoto,
        String surname
) {
}
