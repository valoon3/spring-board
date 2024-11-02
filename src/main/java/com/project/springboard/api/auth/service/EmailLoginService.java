package com.project.springboard.api.auth.service;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.auth.TokenManager;
import com.project.springboard.api.auth.dto.EmailLoginRequestDto;
import com.project.springboard.api.member.entities.Member;
import com.project.springboard.api.member.repository.MemberRepository;
import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailLoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    @Transactional
    public JwtToken login(EmailLoginRequestDto requestDto) {
        // 사용자 조회
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(ErrorType.MEMBER_NOT_EXISTS));

        // 비밀번호 확인
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException(ErrorType.NOT_VALID_PASSWORD);
        }

        // refresh token 업데이트
        JwtToken jwtTokenDto = tokenManager.createJwtTokenDto(member.getMemberId());
        member.updateRefreshToken(jwtTokenDto.getRefreshToken(), jwtTokenDto.getRefreshTokenExpireTime());

        return jwtTokenDto;
    }

}
