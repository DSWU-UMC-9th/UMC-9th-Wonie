package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviews(Long memberId, String storeName, Integer rating) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.member.id.eq(memberId)); // 내가 쓴 리뷰만

        // 가게명 필터
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.eq(storeName));
        }

        // 별점 필터 (4점대 → 4.0~4.9)
        if (rating != null) {
            if (rating == 5) {
                builder.and(review.rating.eq(5f));
            } else {
                builder.and(review.rating.between(rating.floatValue(), rating + 0.9f));
            }
        }

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // fetch join으로 N+1 방지
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
