package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank String name,
            @Email String email,
            @NotBlank String password,
            @NotBlank String phoneNumber,
            @NotNull Gender gender,
            @NotNull LocalDate birth,
            @NotNull String specAddress
    ) {}
}