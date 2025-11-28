package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionChallengeResDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.page.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(
            summary = "진행 중인 미션 목록 조회",
            description = """
                    하드코딩된 테스트 유저(예: id=1)가 '도전 중(isComplete = false)'인 미션 목록을 페이징하여 조회합니다.
                    - page는 1 이상 정수 (프론트에서 1,2,3,... 로 전달)
                    - 한 페이지에 10개씩 조회
                    """
    )
    @GetMapping("/me/challenging")
    public ApiResponse<MissionResDTO.MyMissionPageDTO> getMyChallengingMissions(
            @ValidPage(defaultValue = 1)
            @RequestParam(name = "page", defaultValue = "1")
            @Parameter(description = "1 이상의 페이지 번호(1,2,3,...). 한 페이지에 10개씩 조회합니다.")
            Integer page
    ) {
        Long memberId = TEST_MEMBER_ID;

        MissionResDTO.MyMissionPageDTO dto =
                missionService.getMyChallengingMissions(memberId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.MY_CHALLENGING_MISSION_LIST_SUCCESS, dto);
    }


    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = """
                    특정 가게(storeId)의 모든 미션을 페이징하여 조회합니다.
                    - page는 1 이상 정수 (프론트에서 1,2,3,... 로 전달)
                    - 한 페이지에 10개씩 조회
                    """
    )
    @GetMapping("/stores/{storeId}")
    public ApiResponse<MissionResDTO.StoreMissionPageDTO> getStoreMissions(
            @PathVariable
            @Parameter(description = "가게 ID", example = "1")
            Long storeId,

            @ValidPage(defaultValue = 1)
            @RequestParam(name = "page", defaultValue = "1")
            @Parameter(description = "1 이상의 페이지 번호(1,2,3,...). 한 페이지에 10개씩 조회합니다.")
            Integer page
    ) {
        MissionResDTO.StoreMissionPageDTO dto =
                missionService.getStoreMissions(storeId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.STORE_MISSION_LIST_SUCCESS, dto);
    }
}
