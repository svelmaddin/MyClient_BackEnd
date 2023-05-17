package com.security.Securitydemo.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String email,
        String username,
        String name,
        String profileImageId,
        String surname
) {
}
