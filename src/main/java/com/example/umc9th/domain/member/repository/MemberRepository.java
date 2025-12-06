package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 Member 조회 (로그인용)
    Optional<Member> findByEmail(String email);

    // 이메일 중복 체크용 (회원가입 시)
    boolean existsByEmail(String email);
}

