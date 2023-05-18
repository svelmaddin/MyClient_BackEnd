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
                                                   @RequestBody UserChangePassword request) {
        userService.updatePassword(userId, request);
        return ResponseEntity.ok().build();
    }

}
