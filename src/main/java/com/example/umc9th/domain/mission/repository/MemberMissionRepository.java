package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 존재 여부(중복 방지)
    @Query("select (count(mm) > 0) from MemberMission mm where mm.member.id = :memberId and mm.mission.id = :missionId")
    boolean existsByMemberIdAndMissionId(@Param("memberId") Long memberId, @Param("missionId") Long missionId);

    // 회원별 페이지 + 미션/스토어 즉시로딩 + countQuery
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

    // 미완료 목록(회원) + 마감일 오름차순
    @Query("""
           select mm
           from MemberMission mm
           join mm.mission m
           where mm.member.id = :memberId and mm.isComplete = false
           order by m.deadline asc
           """)
    List<MemberMission> findIncompleteByMemberOrderByDeadline(@Param("memberId") Long memberId);

    // 완료 개수(회원)
    @Query("select count(mm) from MemberMission mm where mm.member.id = :memberId and mm.isComplete = true")
    long countCompletedByMember(@Param("memberId") Long memberId);

    // 매장별 미완료 멤버미션
    @Query("""
           select mm
           from MemberMission mm
           where mm.mission.store.id = :storeId and mm.isComplete = false
           """)
    List<MemberMission> findIncompleteByStore(@Param("storeId") Long storeId);

    // 완료 처리
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MemberMission mm set mm.isComplete = true where mm.id = :id")
    int markComplete(@Param("id") Long id);
}
