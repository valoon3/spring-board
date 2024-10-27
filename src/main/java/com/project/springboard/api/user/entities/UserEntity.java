package com.project.springboard.api.user.entities;

import com.project.springboard.common.util.DateUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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


    public void updateRefreshToken(String refreshToken, Date refreshTokenExpireTime) {
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = DateUtils.convertToLocalDateTime(refreshTokenExpireTime);
    }
}
