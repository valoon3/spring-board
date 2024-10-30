package com.project.springboard.api.auth;

import com.project.springboard.api.auth.constant.GrantType;
import com.project.springboard.api.auth.constant.TokenType;
import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    // 액세스 토큰의 만료 시간 (밀리초 단위)
    private final String accessTokenExpirationTime;

    // 리프레시 토큰의 만료 시간 (밀리초 단위)
    private final String refreshTokenExpirationTime;

    // JWT 토큰을 생성 및 검증할 때 사용하는 비밀 키
    private final String tokenSecret;

    public JwtToken createJwtTokenDto(Long memberId) {
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(memberId, accessTokenExpireTime);
        String refreshToken = createRefreshToken(memberId, refreshTokenExpireTime);

        return JwtToken.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    /**
     * 액세스 토큰의 만료 시간을 생성합니다.
     *
     * @return 생성된 액세스 토큰의 만료 시간
     */
    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    /**
     * 리프레시 토큰의 만료 시간을 생성합니다.
     *
     * @return 생성된 리프레시 토큰의 만료 시간
     */
    public Date createRefreshTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    /**
     * 주어진 사용자 ID, 역할, 만료 시간을 바탕으로 액세스 토큰을 생성합니다.
     *
     * @param memberId 사용자 ID
     * @param roles    사용자 역할
     * @param expirationTime 토큰 만료 시간
     * @return 생성된 액세스 토큰
     */
    public String createAccessToken(Long memberId, Date expirationTime) {
        // 액세스 토큰에 사용자의 역할 정보를 포함시키는 이유:
        // 사용자의 권한(roles)을 액세스 토큰에 포함시킴으로써, 서버는 토큰만으로도 사용자의 권한을 확인할 수 있습니다.
        // 이렇게 하면, 매 요청마다 데이터베이스에서 사용자의 권한을 조회할 필요가 없어져 성능이 향상됩니다.
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())    // 토큰 제목 설정
                .setIssuedAt(new Date())                // 토큰 발급 시간 설정
                .setExpiration(expirationTime)          // 토큰 만료 시간 설정
                .claim("memberId", memberId)            // 사용자 ID 설정
//                .claim("roles", roles)                  // 사용자 역할 설정
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))  // 서명 알고리즘 및 비밀 키 설정
                .setHeaderParam("typ", "JWT")           // 토큰 유형 설정
                .compact();
        return accessToken;
    }

    /**
     * 주어진 사용자 ID와 만료 시간을 바탕으로 리프레시 토큰을 생성합니다.
     *
     * @param memberId 사용자 ID
     * @param expirationTime 토큰 만료 시간
     * @return 생성된 리프레시 토큰
     */
    public String createRefreshToken(Long memberId, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())   // 토큰 제목 설정
                .setIssuedAt(new Date())                // 토큰 발급 시간 설정
                .setExpiration(expirationTime)          // 토큰 만료 시간 설정
                .claim("memberId", memberId)            // 사용자 ID 설정
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))  // 서명 알고리즘 및 비밀 키 설정
                .setHeaderParam("typ", "JWT")           // 토큰 유형 설정
                .compact();
        return refreshToken;
    }

    public void validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);  // 토큰을 파싱하고 검증
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰", e);
            e.printStackTrace();
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            e.printStackTrace();
        }
    }


    private Claims getClams(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();  // 토큰에서 클레임을 파싱하고 추출
            return claims;
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);  // 유효하지 않은 토큰인 경우 로그 기록
            throw new UnauthorizedException(ErrorType.NOT_VALID_TOKEN);  // 유효하지 않은 토큰 예외 발생
        }
    }

}
