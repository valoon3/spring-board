package com.project.springboard.api.member.service;

import com.project.springboard.api.member.constant.RoleType;
import com.project.springboard.api.member.entities.Member;
import com.project.springboard.api.member.entities.Role;
import com.project.springboard.api.member.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    @Transactional
    public List<Role> createMemberRoles(Member member, Set<RoleType> roleTypes) {
        List<Role> roles = Role.createMemberRoles(member, roleTypes);
        return memberRoleRepository.saveAll(roles);
    }
}
