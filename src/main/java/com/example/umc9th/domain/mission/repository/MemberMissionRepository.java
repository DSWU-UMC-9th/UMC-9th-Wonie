package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    // 진행 중인 미션 목록 (isComplete = false), 페이징
    Page<MemberMission> findByMember_IdAndIsCompleteFalse(Long memberId, Pageable pageable);
}
