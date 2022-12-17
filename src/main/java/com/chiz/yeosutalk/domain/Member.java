package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String accountId;

    private String pwd;

    private String name;

    @Enumerated(EnumType.STRING)
    private Citizen citizen;

    private String nickname;

    public Member() {
    }

    public Member(Long id, String accountId, String pwd, String name, Citizen citizen, String nickname) {
        this.id = id;
        this.accountId = accountId;
        this.pwd = pwd;
        this.name = name;
        this.citizen = citizen;
        this.nickname = nickname;
    }

    public Member(String accountId, String pwd, String name, Citizen citizen) {
        this.accountId = accountId;
        this.pwd = pwd;
        this.name = name;
        this.citizen = citizen;
    }

    public static Member toEntity(MemberFormDto memberFormDto) {
        return new Member(memberFormDto.getAccountId(), memberFormDto.getPwd(), memberFormDto.getName(), memberFormDto.getCitizen());
    }
}
