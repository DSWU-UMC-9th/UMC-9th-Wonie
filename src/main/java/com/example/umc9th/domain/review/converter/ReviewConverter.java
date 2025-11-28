package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static ReviewResDTO.MyReviewItemDTO toMyReviewItemDTO(Review review) {
        return ReviewResDTO.MyReviewItemDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPageDTO toMyReviewPageDTO(Page<Review> page) {

        List<ReviewResDTO.MyReviewItemDTO> items = page.getContent()
                .stream()
                .map(ReviewConverter::toMyReviewItemDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewPageDTO.builder()
                .reviews(items)
                .currentPage(page.getNumber() + 1) // Page는 0-based
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
