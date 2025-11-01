package com.example.umc9th.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /** 가게별 리뷰 최신순 페이지 */
    Page<Review> findByStoreIdOrderByCreatedAtDesc(Long storeId, Pageable pageable);

    /** 회원별 리뷰 최신순 페이지 */
    Page<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    /** 중복 작성 방지: 회원이 해당 가게에 리뷰를 쓴 적 있는지 */
    boolean existsByStoreIdAndMemberId(Long storeId, Long memberId);

    /** 가게별 리뷰 개수 */
    long countByStoreId(Long storeId);

    /** 가게별 평점 높은 순(동점 시 최신 우선) */
    List<Review> findByStoreIdOrderByRatingDescCreatedAtDesc(Long storeId);

    /** 가게별 평균 평점 (null 방지 coalesce) */
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
}
