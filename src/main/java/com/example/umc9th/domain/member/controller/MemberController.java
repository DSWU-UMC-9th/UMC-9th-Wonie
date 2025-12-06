package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.service.MemberCommandService;
import com.example.umc9th.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    /**
     * 회원가입 (누구나 접근 가능)
     * SecurityConfig에서 "/sign-up"을 permitAll로 열어둔 상태여야 함
     */
    @PostMapping("/sign-up")
    public MemberResDTO.JoinDTO signUp(@RequestBody MemberReqDTO.JoinDTO dto) {
        return memberCommandService.signUp(dto);
    }

    /**
     * 현재 로그인한 회원 정보 확인용 테스트 엔드포인트
     * 로그인 안 되어 있으면 SecurityConfig에 의해 /login으로 튕김
     */
    @GetMapping("/me")
    public MemberResDTO.JoinDTO me(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 이 메서드는 authenticated 상태에서만 들어옴
        // userDetails.getMember()로 실제 Member 엔티티 접근 가능
        var member = userDetails.getMember();
        return new MemberResDTO.JoinDTO(member.getId(), member.getName(), member.getEmail());
    }
}
