package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 가게별 페이지 + Store 즉시로딩 + countQuery
    @Query(value = """
           select m
           from Mission m
           join fetch m.store s
           where s.id = :storeId
           order by m.createdAt desc
           """,
            countQuery = "select count(m) from Mission m where m.store.id = :storeId")
    Page<Mission> findPageWithStoreByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    // 단건 상세 + Store 즉시로딩
    @Query("""
           select m
           from Mission m
           join fetch m.store
           where m.id = :id
           """)
    Optional<Mission> findDetailWithStore(@Param("id") Long id);

    // 특정 날짜 이후 진행중(가게별) + 마감일 오름차순
    @Query("""
           select m
           from Mission m
           where m.store.id = :storeId
             and m.deadline >= :from
           order by m.deadline asc
           """)
    List<Mission> findUpcomingByStore(@Param("storeId") Long storeId, @Param("from") LocalDate from);

    // 가게별 미션 개수
    @Query("select count(m) from Mission m where m.store.id = :storeId")
    long countByStore(@Param("storeId") Long storeId);
}
