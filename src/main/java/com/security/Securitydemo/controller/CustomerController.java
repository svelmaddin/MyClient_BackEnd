package com.security.Securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/goodbye")
    public String sayGood(){
        return "goodBye";
    }
}
