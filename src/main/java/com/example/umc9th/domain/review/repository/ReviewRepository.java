package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 평균 평점
    @Query("select coalesce(avg(r.rating), 0) from Review r where r.store.id = :storeId")
    Double findAverageRatingByStoreId(@Param("storeId") Long storeId);

    @Query("""
           select r
           from Review r
           join fetch r.member
           join fetch r.store
           where r.id = :id
           """)
    Optional<Review> findDetailWithMemberAndStore(@Param("id") Long id);

    @Query(value = """
           select r
           from Review r
           join fetch r.member m
           where r.store.id = :storeId
           order by r.createdAt desc
           """,
            countQuery = """
           select count(r)
           from Review r
           where r.store.id = :storeId
           """)
    Page<Review> findPageWithMemberByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    @Query(value = """
           select r
           from Review r
           join fetch r.store s
           where r.member.id = :memberId
           order by r.createdAt desc
           """,
            countQuery = """
           select count(r)
           from Review r
           where r.member.id = :memberId
           """)
    Page<Review> findPageWithStoreByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
