package com.example.umc9th.domain.member.dto.res;

import com.example.umc9th.domain.member.entity.Member;

public class MemberResDTO {

    // 회원가입 응답 DTO
    public record JoinDTO(
            Long memberId,
            String name,
            String email
    ) {
        // Entity -> DTO 변환용 팩토리 메서드
        public static JoinDTO from(Member member) {
            return new JoinDTO(
                    member.getId(),
                    member.getName(),
                    member.getEmail()
            );
        }
    }
}
