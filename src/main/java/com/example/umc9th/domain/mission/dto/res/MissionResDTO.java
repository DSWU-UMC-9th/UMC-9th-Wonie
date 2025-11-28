package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MyMissionItemDTO {
        private Long memberMissionId;
        private Long missionId;
        private Long storeId;
        private String storeName;
        private Boolean isComplete;
        private String conditional;
        private Integer point;
        private LocalDate deadline;
    }

    @Getter
    @Builder
    public static class MyMissionPageDTO {
        private List<MyMissionItemDTO> missions;
        private int currentPage;      // 1-based
        private int totalPages;
        private long totalElements;
        private boolean first;
        private boolean last;
    }


    @Getter
    @Builder
    public static class StoreMissionItemDTO {
        private Long missionId;
        private Long storeId;
        private String storeName;
        private String conditional;
        private Integer point;
        private LocalDate deadline;
    }

    @Getter
    @Builder
    public static class StoreMissionPageDTO {
        private List<StoreMissionItemDTO> missions;
        private int currentPage;      // 1-based
        private int totalPages;
        private long totalElements;
        private boolean first;
        private boolean last;
    }
}
