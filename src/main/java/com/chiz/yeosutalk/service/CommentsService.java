package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.domain.Comments;
import com.chiz.yeosutalk.dto.CommentDto;
import com.chiz.yeosutalk.repository.CommentsRepository;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final TourBoardRepository tourBoardRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository,
                           TourBoardRepository tourBoardRepository) {
        this.commentsRepository = commentsRepository;
        this.tourBoardRepository = tourBoardRepository;
    }

    // 댓글 작성 서비스
    public CommentDto createComment(CommentDto commentDto) {
        Comments comments = Comments.toEntity(commentDto);
        TourBoard tourBoard = tourBoardRepository.findById(commentDto.getTourBoardId()).orElseThrow(EntityNotFoundException::new);
        comments.setTourBoard(tourBoard);

        Comments savedComment = commentsRepository.save(comments);
        commentDto.setCreatedAt(savedComment.getCreatedAt());

        return commentDto;
    }

    // 게시글에 달린 댓글 전체 조회

    // 댓글 삭제

}
