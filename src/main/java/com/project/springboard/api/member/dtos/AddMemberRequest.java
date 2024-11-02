package com.project.springboard.api.member.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddMemberRequest {
    String email;
    String password;
    String username;
    String nickname;
}
