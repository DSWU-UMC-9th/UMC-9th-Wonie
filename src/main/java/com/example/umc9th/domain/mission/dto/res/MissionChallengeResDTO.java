package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.entity.MemberMission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionChallengeResDTO {

    private Long memberMissionId;
    private Long missionId;
    private Long storeId;
    private String storeName;
    private Boolean isComplete;
    private String conditional; // 미션 조건 설명
    private Integer point;
    private LocalDate deadline;

    public static MissionChallengeResDTO from(MemberMission memberMission) {
        var mission = memberMission.getMission();
        var store = mission.getStore();

        return MissionChallengeResDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .isComplete(memberMission.getIsComplete())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
