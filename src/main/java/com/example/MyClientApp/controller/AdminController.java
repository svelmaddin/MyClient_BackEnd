package com.example.MyClientApp.controller;

import com.example.MyClientApp.model.User;
import com.example.MyClientApp.response.StoreResponse;
import com.example.MyClientApp.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/shops")
    public ResponseEntity<List<User>> getAllShops() {
        return ResponseEntity.ok(adminService.getAllShops());
    }

    @GetMapping("/shop/details/{id}")
    public ResponseEntity<StoreResponse> getShopDetail(@PathVariable String id) throws JsonProcessingException {
        return ResponseEntity.ok(adminService.getShopDetail(id));
    }

    @PostMapping("/shop/edit/{storeId}")
    public ResponseEntity<Void> setStoreActiveStatus(@PathVariable String storeId, boolean status) {
        adminService.setStoreStatus(storeId, status);
        return ResponseEntity.ok().build();
    }
}
