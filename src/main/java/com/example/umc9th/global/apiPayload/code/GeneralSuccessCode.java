package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "SUCCESS_200", "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "SUCCESS_201", "요청이 성공적으로 처리되었으며, 리소스가 생성되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "SUCCESS_204", "요청이 성공적으로 처리되었으며, 반환할 데이터가 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
