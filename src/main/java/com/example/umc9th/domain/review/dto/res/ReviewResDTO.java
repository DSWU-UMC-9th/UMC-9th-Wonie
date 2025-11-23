package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

import com.example.umc9th.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewDetailDTO {
        private Long id;
        private String content;
        private Float rating;
        private String storeName;
        private LocalDateTime createdAt;

        public static ReviewDetailDTO from(Review review) {
            return ReviewDetailDTO.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .rating(review.getRating())
                    .storeName(review.getStore().getName())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }
}
