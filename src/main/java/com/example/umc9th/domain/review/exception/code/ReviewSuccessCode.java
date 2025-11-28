package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    MY_REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW200_1", "내 리뷰 목록 조회 성공"),
    REVIEW_CREATE_SUCCESS(HttpStatus.OK, "REVIEW200_2", "리뷰 작성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
