package com.project.springboard.api.member.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    String email;
    String password;
}
