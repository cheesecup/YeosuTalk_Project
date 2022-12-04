package com.chiz.yeosutalk.dto;

import com.chiz.yeosutalk.domain.Citizen;
import com.chiz.yeosutalk.domain.Member;
import lombok.Getter;
import lombok.Setter;

/* 회원 정보 응답 DTO */
@Getter
@Setter
public class MemberDto {

    private Long id;

    private String accountId;

    private String pwd;

    private String name;

    private Citizen citizen;

    private String nickname;

    public MemberDto() {
    }

    public MemberDto(Long id, String accountId, String pwd, String name, Citizen citizen, String nickname) {
        this.id = id;
        this.accountId = accountId;
        this.pwd = pwd;
        this.name = name;
        this.citizen = citizen;
        this.nickname = nickname;
    }

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getAccountId(), member.getPwd(), member.getName(), member.getCitizen(), member.getNickname());
    }
}
