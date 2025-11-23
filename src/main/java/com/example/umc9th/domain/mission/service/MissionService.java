package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission challengeMission(Long memberId, Long missionId) {

        // 이미 도전 중인지 체크
        if (memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId)) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. memberId=" + memberId));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다. missionId=" + missionId));

        // isComplete는 기본 false (도전 중)
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        // 양방향 연관관계 컬렉션 유지 (선택)
        mission.addMemberMission(memberMission);

        return memberMissionRepository.save(memberMission);
    }
}
