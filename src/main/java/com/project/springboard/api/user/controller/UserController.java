package com.project.springboard.api.user.controller;

import com.project.springboard.api.user.dtos.AddUserRequest;
import com.project.springboard.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login() {

        return "asdf";
    }

    @PostMapping("/signup")
    public Long signup(@ModelAttribute AddUserRequest addUserRequest) throws Exception {
        return userService.signup(addUserRequest);
    }
}
