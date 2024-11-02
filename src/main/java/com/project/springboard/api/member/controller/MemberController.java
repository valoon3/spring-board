package com.project.springboard.api.member.controller;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.member.dtos.AddMemberRequest;
import com.project.springboard.api.member.dtos.LoginRequest;
import com.project.springboard.api.member.service.MemberService;
import com.project.springboard.common.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;

    @PostMapping("/login")
    public BaseResponse<JwtToken> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success(userService.login(request));
    }

    @PostMapping("/signup")
    public BaseResponse<Long> signup(@Valid @RequestBody AddMemberRequest addMemberRequest) {
        return BaseResponse.success(userService.signup(addMemberRequest));
    }
}
