package com.project.springboard.api.user.controller;

import com.project.springboard.api.user.dtos.AddUserRequest;
import com.project.springboard.api.user.service.UserService;
import com.project.springboard.common.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse<Long> signup(@Valid @RequestBody AddUserRequest addUserRequest) {
        return BaseResponse.success(userService.signup(addUserRequest));
    }

//    @PostMapping("/login")
//    public BaseResponse<UserEntity> login(@RequestBody LoginRequest loginRequest) {
//        return BaseResponse.success(userService.login(loginRequest));
//    }
}
