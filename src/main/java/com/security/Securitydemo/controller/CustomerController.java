package com.security.Securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @GetMapping("/admin/hello")
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/user/goodbye")
    public String sayGood(){
        return "goodBye";
    }
}
