package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.TourCommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TourComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_comment_id")
    private Long id;

    @Lob
    private String comment;

    private String commenter; // 작성자, nickname

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_board_id")
    private TourBoard tourBoard;

    public TourComment() {
    }

    public TourComment(String comment, String commenter) {
        this.comment = comment;
        this.commenter = commenter;
    }

    public static TourComment toEntity(TourCommentDto tourCommentDto) {
        TourComment tourComment = new TourComment(tourCommentDto.getComment(), tourCommentDto.getCommenter());
        return tourComment;
    }
}
