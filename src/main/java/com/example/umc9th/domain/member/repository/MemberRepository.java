package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
        select distinct m
        from Member m
        left join fetch m.reviews
        left join fetch m.memberMissions
        where m.id = :memberId
    """)
    Optional<Member> findMemberDetail(@Param("memberId") Long memberId);
}

