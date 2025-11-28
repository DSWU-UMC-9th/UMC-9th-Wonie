package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    private MissionConverter() {
    }

    public static MissionResDTO.MyMissionItemDTO toMyMissionItemDTO(MemberMission memberMission) {
        var mission = memberMission.getMission();
        var store = mission.getStore();

        return MissionResDTO.MyMissionItemDTO.builder()
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

    public static MissionResDTO.MyMissionPageDTO toMyMissionPageDTO(Page<MemberMission> page) {

        List<MissionResDTO.MyMissionItemDTO> items = page.getContent()
                .stream()
                .map(MissionConverter::toMyMissionItemDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionPageDTO.builder()
                .missions(items)
                .currentPage(page.getNumber() + 1)   // Page는 0-based → 1-based로 변환
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }


    public static MissionResDTO.StoreMissionItemDTO toStoreMissionItemDTO(Mission mission) {
        var store = mission.getStore();

        return MissionResDTO.StoreMissionItemDTO.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.StoreMissionPageDTO toStoreMissionPageDTO(Page<Mission> page) {

        List<MissionResDTO.StoreMissionItemDTO> items = page.getContent()
                .stream()
                .map(MissionConverter::toStoreMissionItemDTO)
                .collect(Collectors.toList());

        return MissionResDTO.StoreMissionPageDTO.builder()
                .missions(items)
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
