package com.example.MyClientApp.controller;

import com.example.MyClientApp.dto.UserDto;
import com.example.MyClientApp.request.UserChangePassword;
import com.example.MyClientApp.request.UserRequest;
import com.example.MyClientApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/editUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok().body(userService.updateUser(request));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById() {
        return ResponseEntity.ok().body(userService.findUserById());
    }

    @PatchMapping("/editPassword")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UserChangePassword request) {
        userService.updatePassword(request);
        return ResponseEntity.ok().build();
    }

}
