package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Tag(name = "Mission API", description = "미션 도전 관련 API")
public class MissionController {

    private final MissionService missionService;

    // 로그인 기능이 없으므로, 테스트용 유저를 하드코딩
    private static final Long TEST_MEMBER_ID = 1L;

    @Operation(summary = "미션 도전하기", description = "로그인 없이 테스트 유저로 특정 미션에 도전합니다.")
    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<MissionChallengeResDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        Long memberId = TEST_MEMBER_ID;

        MemberMission memberMission = missionService.challengeMission(memberId, missionId);
        return ResponseEntity.ok(MissionChallengeResDTO.from(memberMission));
    }
}
