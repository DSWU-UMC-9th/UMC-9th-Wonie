package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


import java.time.LocalDateTime;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDTO {
        private Float rating;
        private String content;
    }
}

