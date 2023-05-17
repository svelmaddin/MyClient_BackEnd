package com.security.Securitydemo.dto;

import com.security.Securitydemo.model.Role;
import lombok.Builder;

@Builder
public record UserDto(
        String email,
        String username,
        String name,
        String profileImageId,
        String surname,
        Role role
) {
}
