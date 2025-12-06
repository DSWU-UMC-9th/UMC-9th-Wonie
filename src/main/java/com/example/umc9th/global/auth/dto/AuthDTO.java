package com.example.umc9th.global.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDTO {

    // 로그인 요청 DTO
    public record LoginReq(
            @Email
            String email,

            @NotBlank
            String password
    ) {}

    // 토큰 응답 DTO
    public record TokenRes(
            String accessToken,
            String refreshToken
    ) {}
}
