package com.example.umc9th.global.page;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    PAGE400_1(HttpStatus.BAD_REQUEST, "PAGE400_1", "유효하지 않은 페이지 번호입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

