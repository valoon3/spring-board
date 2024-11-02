package com.project.springboard.api.member.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfoDto {

    @NotNull
    private String grantType; // 토큰 타입

    @NotNull
    private String accessToken; // 엑세스 토큰
}
