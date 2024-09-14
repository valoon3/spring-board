package com.project.springboard.api.user.service;

import com.project.springboard.api.user.dtos.AddUserRequest;
import com.project.springboard.api.user.entities.UserEntity;
import com.project.springboard.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signup(AddUserRequest dto) throws Exception {
        return userRepository.save(UserEntity.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .username(dto.getUsername())
                // 패스워드 암호화
                .password(passwordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }


}
