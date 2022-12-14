package com.chiz.yeosutalk.dto;

import com.chiz.yeosutalk.domain.TourBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TourBoardDto {

    private Long id;

    private String title;

    private String content;

    private int likeCount;

    private String writer;

    private LocalDateTime createdAt;

    public TourBoardDto() {
    }

    public TourBoardDto(Long id, String title, String content, int likeCount, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    public static TourBoardDto toTourBoardDto(TourBoard tourBoard) {
        return new TourBoardDto(tourBoard.getId(), tourBoard.getTitle(), tourBoard.getContent(), tourBoard.getLikeCount(), tourBoard.getWriter(), tourBoard.getCreatedAt());
    }
}
