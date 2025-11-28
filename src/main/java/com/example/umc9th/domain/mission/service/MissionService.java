package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    private static final int PAGE_SIZE = 10;

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

        mission.addMemberMission(memberMission);

        return memberMissionRepository.save(memberMission);
    }

    @Transactional
    public MissionResDTO.MyMissionPageDTO getMyChallengingMissions(Long memberId, int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findByMember_IdAndIsCompleteFalse(memberId, pageable);

        return MissionConverter.toMyMissionPageDTO(memberMissionPage);
    }

    @Transactional
    public MissionResDTO.StoreMissionPageDTO getStoreMissions(Long storeId, int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));

        Page<Mission> missionPage =
                missionRepository.findByStore_Id(storeId, pageable);

        return MissionConverter.toStoreMissionPageDTO(missionPage);
    }
}
