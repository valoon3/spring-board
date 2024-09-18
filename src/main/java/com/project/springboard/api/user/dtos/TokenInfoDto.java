package com.project.springboard.api.user.dtos;

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
    private String grantType;

    @NotNull
    private String accessToken;
}
