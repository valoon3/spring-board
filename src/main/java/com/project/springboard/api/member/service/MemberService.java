package com.project.springboard.api.member.service;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.auth.TokenManager;
import com.project.springboard.api.member.dtos.AddMemberRequest;
import com.project.springboard.api.member.dtos.LoginRequest;
import com.project.springboard.api.member.entities.Member;
import com.project.springboard.api.member.repository.MemberRepository;
import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    public JwtToken login(LoginRequest request) {
        // 사용자 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(ErrorType.MEMBER_NOT_EXISTS));

        // 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException(ErrorType.NOT_VALID_PASSWORD);
        }

        // refresh token 업데이트
        JwtToken jwtTokenDto = tokenManager.createJwtTokenDto(member.getMemberId());
        member.updateRefreshToken(jwtTokenDto.getRefreshToken(), jwtTokenDto.getRefreshTokenExpireTime());

        return jwtTokenDto;
    }

    public Long signup(AddMemberRequest addMemberRequest) {
        return memberRepository.save(Member.builder()
                .email(addMemberRequest.getEmail())
                .nickname(addMemberRequest.getNickname())
                .username(addMemberRequest.getUsername())
                // 패스워드 암호화
                .password(passwordEncoder.encode(addMemberRequest.getPassword()))
                .build())
                .getMemberId();
    }
}
