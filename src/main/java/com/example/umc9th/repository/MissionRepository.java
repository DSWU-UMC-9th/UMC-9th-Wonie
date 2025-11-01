package com.example.umc9th.repository;


import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 메서드 이름 기반
    Page<Mission> findByStoreIdOrderByCreatedAtDesc(Long storeId, Pageable pageable);

    List<Mission> findByStoreIdAndDeadlineGreaterThanEqualOrderByDeadlineAsc(Long storeId, LocalDate from);

    long countByStoreId(Long storeId);

    @Query(value = """
            select m
            from Mission m
            join fetch m.store s
            where s.id = :storeId
            order by m.createdAt desc
            """,
            countQuery = "select count(m) from Mission m where m.store.id = :storeId")
    Page<Mission> findPageWithStoreByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    @Query("""
            select m
            from Mission m
            join fetch m.store
            where m.id = :id
            """)
    Optional<Mission> findDetailWithStore(@Param("id") Long id);
}
