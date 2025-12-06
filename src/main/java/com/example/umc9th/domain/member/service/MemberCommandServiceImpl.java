package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto) {

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 엔티티 생성: 모든 NOT NULL 필드 채우기
        Member member = Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .phoneNumber(dto.phoneNumber())
                .gender(dto.gender())
                .birth(dto.birth())
                .specAddress(dto.specAddress())
                .role(Role.ROLE_USER)
                .build();

        Member saved = memberRepository.save(member);

        return new MemberResDTO.JoinDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }
}
