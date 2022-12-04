package com.chiz.yeosutalk.dto;

import com.chiz.yeosutalk.domain.Citizen;
import lombok.Getter;
import lombok.Setter;

/* 회원가입 폼 DTO */
@Getter
@Setter
public class MemberFormDto {

    private String accountId;

    private String pwd;

    private String name;

    private Citizen citizen;

    public MemberFormDto() {
    }
}
