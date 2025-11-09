package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/me")
    public ResponseEntity<List<Review>> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestHeader("Authorization") String token
    ) {
        Long memberId = extractMemberIdFromToken(token); // JWT 파싱
        return ResponseEntity.ok(reviewService.getMyReviews(memberId, storeName, rating));
    }

    private Long extractMemberIdFromToken(String token) {
        return 1L; // 테스트용
    }
}

