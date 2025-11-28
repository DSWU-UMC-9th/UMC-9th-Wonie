package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MY_CHALLENGING_MISSION_LIST_SUCCESS(HttpStatus.OK, "MISSION200_1", "진행 중인 미션 목록 조회 성공"),
    MISSION_CHALLENGE_SUCCESS(HttpStatus.OK, "MISSION200_2", "미션 도전 성공"),
    STORE_MISSION_LIST_SUCCESS(HttpStatus.OK, "MISSION200_3", "가게의 미션 목록 조회 성공");  // 🔽 추가

    private final HttpStatus status;
    private final String code;
    private final String message;
}
