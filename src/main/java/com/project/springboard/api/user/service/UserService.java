package com.project.springboard.api.user.service;

import com.project.springboard.api.user.dtos.AddUserRequest;
import com.project.springboard.api.user.dtos.LoginRequest;
import com.project.springboard.api.user.entities.UserEntity;
import com.project.springboard.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signup(AddUserRequest addUserRequest) throws Exception {
        return userRepository.save(UserEntity.builder()
                .email(addUserRequest.getEmail())
                .nickname(addUserRequest.getNickname())
                .username(addUserRequest.getUsername())
                // 패스워드 암호화
                .password(passwordEncoder.encode(addUserRequest.getPassword()))
                .build())
                .getUserId();
    }

    public UserEntity login(LoginRequest loginRequest) {
        return loadUserByUsername(loginRequest.getEmail());
    }

    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }
}
