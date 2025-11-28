package com.example.umc9th.global.page;

import com.example.umc9th.global.apiPayload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PageExceptionAdvice {

    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidPageException(InvalidPageException e) {

        // result 자리에 e.getMessage()를 넣어 줄지, null 넣을지는 취향
        ApiResponse<String> body = ApiResponse.onFailure(PageErrorCode.PAGE400_1, e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
