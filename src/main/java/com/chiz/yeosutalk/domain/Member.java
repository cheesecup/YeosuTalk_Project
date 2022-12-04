package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String accountId;

    @Column(nullable = false, length = 100)
    private String pwd;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Citizen citizen;

    private String nickname;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    public Member() {
    }

    public Member(Long id, String accountId, String pwd, String name, Citizen citizen, String nickname, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.accountId = accountId;
        this.pwd = pwd;
        this.name = name;
        this.citizen = citizen;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
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
