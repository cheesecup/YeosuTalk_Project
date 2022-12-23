package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.domain.TourComment;
import com.chiz.yeosutalk.dto.TourCommentDto;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import com.chiz.yeosutalk.repository.TourCommentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    private final TourCommentRepository tourCommentRepository;
    private final TourBoardRepository tourBoardRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CommentsService(TourCommentRepository tourCommentRepository,
                           TourBoardRepository tourBoardRepository,
                           JPAQueryFactory jpaQueryFactory) {
        this.tourCommentRepository = tourCommentRepository;
        this.tourBoardRepository = tourBoardRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 댓글 작성 서비스
    public TourCommentDto createComment(TourCommentDto tourCommentDto) {
        TourComment tourComment = TourComment.toEntity(tourCommentDto);
        TourBoard tourBoard = tourBoardRepository.findById(tourCommentDto.getTourBoardId()).orElseThrow(EntityNotFoundException::new);
        tourComment.setTourBoard(tourBoard);

        TourComment savedTourComment = tourCommentRepository.save(tourComment);
        tourCommentDto.setCreatedAt(savedTourComment.getCreatedAt());

        return tourCommentDto;
    }

    // 게시글에 달린 댓글 전체 조회
    public List<TourCommentDto> listComments(Long tourBoardId) {
        List<TourComment> comments = tourCommentRepository.findByTourBoardId(tourBoardId);
        List<TourCommentDto> commentsDto = comments.stream()
                .map(m -> new TourCommentDto(m.getComment(), m.getCommenter(), m.getTourBoard().getId(), m.getCreatedAt()))
                .collect(Collectors.toList());

        return commentsDto;
    }

    // 댓글 삭제

}
