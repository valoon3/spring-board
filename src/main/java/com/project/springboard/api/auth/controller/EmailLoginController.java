package com.project.springboard.api.auth.controller;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.auth.dto.EmailLoginRequestDto;
import com.project.springboard.api.auth.service.EmailLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/auth"))
@RequiredArgsConstructor
public class EmailLoginController {

    private final EmailLoginService emailLoginService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(
            @RequestBody @Validated EmailLoginRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                emailLoginService.login(requestDto)
        );
    }
}
