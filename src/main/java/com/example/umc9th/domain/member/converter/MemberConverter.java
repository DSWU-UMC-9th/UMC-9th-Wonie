//package com.example.umc9th.domain.member.converter;
//
//import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
//import com.example.umc9th.domain.member.entity.Member;
//import com.example.umc9th.global.auth.enums.Role;
//
//public class MemberConverter {
//
//    public static Member toMember(
//            MemberReqDTO.JoinDTO dto,
//            String password,
//            Role role
//    ){
//        return Member.builder()
//                .name(dto.name())
//                .email(dto.email()) // 추가된 코드
//                .password(password) // 추가된 코드
//                .role(role)         // 추가된 코드
//                .birth(dto.birth())
//                .detailAddress(dto.specAddress())
//                .gender(dto.gender())
//                .build();
//    }
//}
