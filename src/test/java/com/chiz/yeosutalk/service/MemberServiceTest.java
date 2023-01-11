package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.Citizen;
import com.chiz.yeosutalk.domain.Member;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 저장 서비스 테스트")
    void createMemberServiceTest() {
        //Given
        long before_cnt = memberRepository.count();
        MemberFormDto member = new MemberFormDto();
        member.setAccountId("memberA");
        member.setPwd("1234");
        member.setName("회원A");
        member.setCitizen(Citizen.yes);

        //When
        Long memberId = memberService.createMember(member, passwordEncoder);
        long after_cnt = memberRepository.count();

        //Then
        assertThat(after_cnt).isEqualTo(before_cnt + 1L);
    }

    @Test
    @DisplayName("중복 회원 검증 테스트")
    void duplicateMemberServiceTest() {
        //Given
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setAccountId("memberDup");
        memberFormDto.setPwd("1234");
        memberFormDto.setName("회원A");
        memberFormDto.setCitizen(Citizen.yes);

        Member member = Member.toEntity(memberFormDto);
        memberRepository.save(member);

        MemberFormDto memberFormDto2 = new MemberFormDto();
        memberFormDto2.setAccountId("memberDup");
        memberFormDto2.setPwd("1234");
        memberFormDto2.setName("회원A");
        memberFormDto2.setCitizen(Citizen.yes);

        //When
        Long memberId = memberService.createMember(memberFormDto2, passwordEncoder);

        //Then
        assertThat(memberId).isEqualTo(null);
    }
}