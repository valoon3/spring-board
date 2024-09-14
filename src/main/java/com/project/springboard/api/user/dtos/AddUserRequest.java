package com.project.springboard.api.user.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddUserRequest {
    String email;
    String password;
    String username;
    String nickname;
}
