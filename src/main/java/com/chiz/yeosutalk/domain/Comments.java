package com.chiz.yeosutalk.domain;

import com.chiz.yeosutalk.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TourBoardComments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_board_comments_id")
    private Long id;

    @Lob
    private String comment;

    private String commenter; // 작성자, nickname

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_board_id")
    private TourBoard tourBoard;

    public TourBoardComments() {
    }

    public TourBoardComments(String comment, String commenter) {
        this.comment = comment;
        this.commenter = commenter;
    }

    public static TourBoardComments toEntity(CommentDto commentDto) {
        TourBoardComments tourBoardComments = new TourBoardComments(commentDto.getComment(), commentDto.getCommenter());
        return tourBoardComments;
    }
}
