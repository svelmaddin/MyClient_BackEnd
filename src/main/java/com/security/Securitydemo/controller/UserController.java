package com.security.Securitydemo.controller;

import com.security.Securitydemo.dto.UserDto;
import com.security.Securitydemo.request.UserChangePassword;
import com.security.Securitydemo.request.UserRequest;
import com.security.Securitydemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,
                                              @RequestBody UserRequest request) {
        return ResponseEntity.ok().body(userService.updateUser(request,userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findUserById(userId));
    }

    @PatchMapping("/editPassword/{userId}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long userId,
                                                   @Valid @RequestBody UserChangePassword request) {
        userService.updatePassword(userId, request);
        return ResponseEntity.ok().build();
    }

}
