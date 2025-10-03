package com.javaprojects.jwt_login.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demo")
    public String postMethodName() {
        return "Demo";
    }

}
