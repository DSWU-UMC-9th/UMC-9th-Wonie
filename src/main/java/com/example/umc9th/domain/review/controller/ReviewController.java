package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    // 로그인 기능이 없으므로, 테스트용 유저를 하드코딩
    private static final Long TEST_MEMBER_ID = 1L;

    @Operation(summary = "내 리뷰 목록 조회", description = "하드코딩된 테스트 유저(예: id=1)의 리뷰 목록을 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<List<Review>> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating
    ) {
        Long memberId = TEST_MEMBER_ID;
        return ResponseEntity.ok(reviewService.getMyReviews(memberId, storeName, rating));
    }

    @Operation(summary = "가게에 리뷰 작성", description = "로그인 없이 테스트 유저로 특정 가게에 리뷰를 작성합니다.")
    @PostMapping("/stores/{storeId}")
    public ResponseEntity<ReviewResDTO.ReviewDetailDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        Long memberId = TEST_MEMBER_ID;

        Review review = reviewService.createReview(
                memberId,
                storeId,
                request.getRating(),
                request.getContent()
        );

        return ResponseEntity.ok(ReviewResDTO.ReviewDetailDTO.from(review));
    }
}

