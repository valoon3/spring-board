package com.project.springboard.api.member.service;

import com.project.springboard.api.auth.JwtToken;
import com.project.springboard.api.auth.TokenManager;
import com.project.springboard.api.member.constant.RoleType;
import com.project.springboard.api.member.dtos.LoginRequest;
import com.project.springboard.api.member.entities.Member;
import com.project.springboard.api.member.repository.MemberRepository;
import com.project.springboard.api.member.repository.MemberRoleRepository;
import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;

    private final MemberRoleService memberRoleService;

    public JwtToken login(LoginRequest request) {
        // 사용자 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(ErrorType.MEMBER_NOT_EXISTS));

        String roles = member.getRoles().stream()
                .map(role -> role.getRoleType().name())
                .reduce((role1, role2) -> role1 + "," + role2)
                .orElse("");

        // 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException(ErrorType.NOT_VALID_PASSWORD);
        }

        // refresh token 업데이트
        JwtToken jwtTokenDto = tokenManager.createJwtTokenDto(member.getMemberId(), roles);
        member.updateRefreshToken(jwtTokenDto.getRefreshToken(), jwtTokenDto.getRefreshTokenExpireTime());

        return jwtTokenDto;
    }

    @Transactional
    public Long signup(String email, String password, String nickname, String username) {
        Member member = Member.createMember(
                email,
                passwordEncoder.encode(password),
                nickname,
                username
        );

        memberRepository.save(member);
        memberRoleService.createMemberRoles(member, RoleType.getRoleTypeList(RoleType.ROLE_USER));

        return member.getMemberId();
    }
}
