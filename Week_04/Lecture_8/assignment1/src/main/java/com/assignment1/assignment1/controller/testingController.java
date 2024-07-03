package com.assignment1.assignment1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testingController {

    @GetMapping("/test")
    public String test() {
        return "Testing Server Spring Boot";
    }
}
