package com.security.Securitydemo.dto;

import com.security.Securitydemo.model.User;

public class UserConverter {
    User dtoToModel(UserDto from){
        User user = new User();
        user.setUsername(from.username());
        user.setEmail(from.email());
        return user;
    }
}
