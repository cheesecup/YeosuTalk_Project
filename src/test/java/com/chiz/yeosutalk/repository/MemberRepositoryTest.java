package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.Citizen;
import com.chiz.yeosutalk.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

//    /* 테스트 메서드 실행전 저장소 데이터 삭제 */
//    @BeforeEach
//    void before() {
//        memberRepository.deleteAll();
//    }

    @Test
    @DisplayName("DB에 회원 정보 저장 테스트")
    void createMemberTestInDB() {
        //Given
        Member member = new Member();
        member.setAccountId("memberA");
        member.setPwd("1234");
        member.setName("회원A");
        member.setCitizen(Citizen.yes);

        //When
        Member savedMember = memberRepository.save(member);

        //Then
        assertThat(savedMember.getName()).isEqualTo(member.getName());
    }

    @Test
    @DisplayName("모든 회원 조회")
    void findAllMember() {
        //Given
        long beforeMemberNum = memberRepository.count();

        Member member1 = new Member();
        member1.setAccountId("member1");
        member1.setPwd("1234");
        member1.setName("회원1");
        member1.setCitizen(Citizen.yes);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setAccountId("member2");
        member2.setPwd("1234");
        member2.setName("회원2");
        member2.setCitizen(Citizen.non);
        memberRepository.save(member2);

        //When
        List<Member> memberList = memberRepository.findAll();

        //Then
        assertThat(memberList.size()).isEqualTo(beforeMemberNum + 2);
    }

    @Test
    @DisplayName("회원 닉네임으로 단건 조회")
    void findMemberByName() {
        //Given
        Member member1 = new Member();
        member1.setAccountId("nickname1");
        member1.setPwd("1234");
        member1.setName("회원1");
        member1.setNickname("닉네임1");
        member1.setCitizen(Citizen.yes);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setAccountId("nickname2");
        member2.setPwd("1234");
        member2.setName("회원2");
        member2.setNickname("닉네임2");
        member2.setCitizen(Citizen.non);
        memberRepository.save(member2);

        //When
        Member findMember = memberRepository.findByNickname(member2.getNickname());

        //Then
        assertThat(findMember.getNickname()).isEqualTo(member2.getNickname());

    }
}