package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.TourBoardFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TourBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Column(columnDefinition = "int default 0")
    private int likeCount; // default 0

    private String writer; // user nickname

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    public TourBoard() {
    }

    public TourBoard(Long id, String title, String content, int likeCount, String writer, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writer = writer;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public TourBoard(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public TourBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static TourBoard toEntity(TourBoardFormDto tourBoardFormDto) {
        return new TourBoard(tourBoardFormDto.getTitle(), tourBoardFormDto.getContent(), tourBoardFormDto.getWriter());
    }
}
