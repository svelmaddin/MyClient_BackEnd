package com.example.MyClientApp.controller;

import com.example.MyClientApp.dto.UserDto;
import com.example.MyClientApp.request.CreateShopRequest;
import com.example.MyClientApp.request.UserChangePassword;
import com.example.MyClientApp.request.UserRequest;
import com.example.MyClientApp.service.StoreService;
import com.example.MyClientApp.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.MyClientApp.util.ErrorMessage.PHOTO_UPLOAD_SUCCESS;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final StoreService storeService;

    public UserController(UserService userService, StoreService storeService) {
        this.userService = userService;
        this.storeService = storeService;
    }

    @PostMapping("/editUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok().body(userService.updateUser(request));
    }

    @PostMapping("/profilePhoto")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        userService.uploadPhoto(file);
        return ResponseEntity.ok(PHOTO_UPLOAD_SUCCESS);
    }

    @GetMapping("/profilePhoto")
    public ResponseEntity<byte[]> downloadProfilePhoto() {
        byte[] profilePhoto = userService.downloadProfilePhoto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("profilePhoto.jpg").build());
        return new ResponseEntity<>(profilePhoto, headers, HttpStatus.OK);
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

    @GetMapping("/api/admin/userList")
    public ResponseEntity<List<UserDto>> getUserListForAdmin() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @DeleteMapping("/api/admin/deleteUser")
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }


    //forAdmin
    @PostMapping("/createShop")
    public ResponseEntity<Void> createShop(@RequestBody CreateShopRequest request) {
        storeService.createShop(request);
        return ResponseEntity.ok().build();
    }

}
