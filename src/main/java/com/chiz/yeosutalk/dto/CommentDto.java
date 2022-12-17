package com.chiz.yeosutalk.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private String comment;

    private String commenter;

    private Long tourBoardId;

    private LocalDateTime createdAt;

    public CommentDto() {
    }

    public CommentDto(String comment, String commenter, Long tourBoardId) {
        this.comment = comment;
        this.commenter = commenter;
        this.tourBoardId = tourBoardId;
    }

    public CommentDto(String comment, String commenter, Long tourBoardId, LocalDateTime createdAt) {
        this.comment = comment;
        this.commenter = commenter;
        this.tourBoardId = tourBoardId;
        this.createdAt = createdAt;
    }
}
