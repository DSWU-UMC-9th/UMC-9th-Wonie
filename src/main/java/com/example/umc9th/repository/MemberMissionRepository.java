package com.example.umc9th.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 메서드 이름 기반
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);

    List<MemberMission> findByMemberIdAndIsCompleteFalseOrderByMissionDeadlineAsc(Long memberId);

    long countByMemberIdAndIsCompleteTrue(Long memberId);

    List<MemberMission> findByMissionStoreIdAndIsCompleteFalse(Long storeId);

    @Query(value = """
            select mm
            from MemberMission mm
            join fetch mm.mission m
            join fetch m.store s
            where mm.member.id = :memberId
            order by m.deadline asc
            """,
            countQuery = """
            select count(mm)
            from MemberMission mm
            where mm.member.id = :memberId
            """)
    Page<MemberMission> findPageWithMissionAndStoreByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MemberMission mm set mm.isComplete = true where mm.id = :id")
    int markComplete(@Param("id") Long id);
}
