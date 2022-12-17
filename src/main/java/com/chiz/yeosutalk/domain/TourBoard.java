package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.TourBoardFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TourBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_board_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Column(columnDefinition = "int default 0")
    private int likeCount; // default 0

    private String writer; // 사용자 닉네임

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public TourBoard() {
    }

    public TourBoard(Long id, String title, String content, int likeCount, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writer = writer;
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
