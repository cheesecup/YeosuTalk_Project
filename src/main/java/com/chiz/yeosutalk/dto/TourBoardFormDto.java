package com.chiz.yeosutalk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourBoardFormDto {

    private String title;

    private String content;

    private String writer;

    public TourBoardFormDto() {
    }

    public TourBoardFormDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
