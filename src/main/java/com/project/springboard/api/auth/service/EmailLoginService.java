package com.project.springboard.api.auth.service;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.auth.dto.EmailLoginRequestDto;
import com.project.springboard.api.user.entities.UserEntity;
import com.project.springboard.api.user.repository.UserRepository;
import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailLoginService {

    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtToken login(EmailLoginRequestDto requestDto) {
        // 사용자 조회
        Optional<UserEntity> optionalMember = userRepository.findByEmail(requestDto.getEmail());
        if (optionalMember.isEmpty()) {
            throw new EntityNotFoundException(ErrorType.MEMBER_NOT_EXISTS);
        }
        UserEntity user = optionalMember.get();

        // 비밀번호 확인
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException(ErrorType.NOT_VALID_PASSWORD);
        }
    }

}
