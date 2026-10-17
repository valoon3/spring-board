# spring-board

JWT 인증과 역할 기반 권한을 붙인 게시판 API입니다. Spring Security 구성, 토큰 발급·검증 필터, 예외 응답 형식을 직접 구성해 보려고 만들었습니다.

## 스택

- Java 17, Spring Boot 3.3
- Spring Security, jjwt 0.11
- Spring Data JPA, QueryDSL 5, PostgreSQL
- Thymeleaf (화면 일부)

## 구조

```
api/auth     이메일 로그인, JWT 발급(TokenManager, JwtUtil), Authorization 헤더 파싱
api/member   회원 가입·조회, 역할(Role, RoleType) 관리
api/note     게시글(Note)과 댓글(NoteComment)
config       WebSecurityConfig, JwtAuthenticationFilter, JwtExceptionHandlerFilter,
             JPA Auditing, 초기 회원 Seeder
common       공통 응답(BaseResponse), BaseEntity / BaseTimeEntity
error        ErrorType 과 예외 클래스, ErrorResponse
```

## 요청 흐름

1. 이메일·비밀번호로 로그인하면 access token과 refresh token을 발급합니다.
2. 이후 요청은 `JwtAuthenticationFilter` 가 `Authorization: Bearer` 헤더를 읽어 인증 객체를 만듭니다.
3. 토큰 만료·변조는 `JwtExceptionHandlerFilter` 가 잡아 `ErrorResponse` 형식으로 응답합니다.
4. 역할은 회원과 다대다로 두고(`Member`–`Role`), 권한 검사는 Security 설정에서 처리합니다.

## 실행

```bash
# src/main/resources/application.properties 에 PostgreSQL 접속 정보와 JWT 시크릿을 넣은 뒤
./gradlew bootRun
```

기동 시 `MemberSeeder` 가 테스트 회원을 넣습니다.
