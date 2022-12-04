package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.Citizen;
import com.chiz.yeosutalk.domain.Member;
import com.chiz.yeosutalk.dto.MemberDto;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void beforeService() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 저장 서비스 테스트")
    void createMemberServiceTest() {
        //Given
        MemberFormDto member = new MemberFormDto();
        member.setAccountId("memberA");
        member.setPwd("1234");
        member.setName("회원A");
        member.setCitizen(Citizen.CITIZEN);

        //When
        Long memberId = memberService.createMember(member);

        //Then
        assertThat(memberId).isEqualTo(1L);
    }

    @Test
    @DisplayName("중복 회원 검증 테스트")
    void duplicateMemberServiceTest() {
        //Given
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setAccountId("memberA");
        memberFormDto.setPwd("1234");
        memberFormDto.setName("회원A");
        memberFormDto.setCitizen(Citizen.CITIZEN);
        Member member = Member.toEntity(memberFormDto);
        memberRepository.save(member);

        MemberFormDto memberFormDto2 = new MemberFormDto();
        memberFormDto2.setAccountId("memberA");
        memberFormDto2.setPwd("1234");
        memberFormDto2.setName("회원A");
        memberFormDto2.setCitizen(Citizen.CITIZEN);

        //When
        Long memberId = memberService.createMember(memberFormDto2);

        //Then
        assertThat(memberId).isEqualTo(null);
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 아이디 존재x")
    void loginFailTest_No_Id() {
        //Given
        Member member = new Member("memberA", "1234", "회원A", Citizen.CITIZEN);
        memberRepository.save(member);

        //When
        MemberDto memberDto = memberService.login("errorId", "1234");

        //Then
        assertThat(memberDto).isEqualTo(null);
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 비밀번호 존재x")
    void loginFailTest_No_Pwd() {
        //Given
        Member member = new Member("memberA", "1234", "회원A", Citizen.CITIZEN);
        memberRepository.save(member);

        //When
        MemberDto memberDto = memberService.login("memberA", "12345");

        //Then
        assertThat(memberDto).isEqualTo(null);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccessTest() {
        //Given
        Member member = new Member("memberA", "1234", "회원A", Citizen.CITIZEN);
        memberRepository.save(member);

        //When
        MemberDto memberDto = memberService.login("memberA", "1234");

        //Then
        assertThat(memberDto.getAccountId()).isEqualTo("memberA");
    }
}