package com.project.springboard.api.member.repository;

import com.project.springboard.api.member.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<Role, Long> {
}
