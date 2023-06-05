package com.example.MyClientApp.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String email,
        String name,
        String surname,
        byte[] profilePhoto
) {
}
