package com.project.springboard.api.member.entities;

import com.project.springboard.api.note.entities.Note;
import com.project.springboard.common.entity.BaseEntity;
import com.project.springboard.common.util.DateUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_email",
                columnNames = {"email"}
        )
})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    // 이메일 로그인 방식
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private boolean isLocked = true;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    // MemberRole과의 일대다 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;


    public void updateRefreshToken(String refreshToken, Date refreshTokenExpireTime) {
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = DateUtils.convertToLocalDateTime(refreshTokenExpireTime);
    }

    public static Member createMember(
            String email,
            String password,
            String nickname,
            String username
    ) {
        return Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .nickname(nickname)
                .isLocked(true)
                .build();
    }
}
