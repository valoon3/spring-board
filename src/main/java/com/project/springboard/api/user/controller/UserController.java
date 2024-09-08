package com.project.springboard.api.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @PostMapping("/login")
    public String login() {

        return "asdf";
    }
}
