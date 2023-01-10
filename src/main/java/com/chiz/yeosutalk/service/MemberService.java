package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.Member;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원 저장 서비스 */
    public Long createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        /* 중복 검사, 중복 시=true 사용 가능=false */
        if(duplicateMember(memberFormDto.getAccountId())) {
            return null;
        }

        Member member = Member.toEntity(memberFormDto);
        String encoderPwd = passwordEncoder.encode(member.getPwd());
        member.setPwd(encoderPwd);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    /* 중복 회원 검증 로직 */
    public boolean duplicateMember(String accountId) {
        Member member = memberRepository.findByAccountId(accountId);

        if (member == null) {
            return false;
        } else {
            return true;
        }
    }

}
