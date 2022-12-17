package com.chiz.yeosutalk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourBoardFormDto {

    private String title;

    private String content;

    private String writer; // 사용자 닉네임

    private String accountId; // 사용자 계정

    public TourBoardFormDto() {
    }

    public TourBoardFormDto(String title, String content, String writer, String accountId) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.accountId = accountId;
    }
}
