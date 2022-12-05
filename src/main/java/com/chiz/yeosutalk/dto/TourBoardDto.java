package com.chiz.yeosutalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TourBoardDto {

    private Long id;

    private String title;

    private String likeCount;

    private String writer;

    private LocalDateTime createdAt;

    public TourBoardDto() {
    }

    public TourBoardDto(Long id, String title, String likeCount, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.likeCount = likeCount;
        this.writer = writer;
        this.createdAt = createdAt;
    }
}
