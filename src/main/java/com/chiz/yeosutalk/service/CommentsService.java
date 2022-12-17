package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.dto.CommentDto;
import com.chiz.yeosutalk.repository.TourBoardCommRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourBoardCommService {

    private final TourBoardCommRepository tourBoardCommRepository;

    @Autowired
    public TourBoardCommService(TourBoardCommRepository tourBoardCommRepository) {
        this.tourBoardCommRepository = tourBoardCommRepository;
    }

    // 댓글 작성 서비스
    public void createComment(CommentDto commentDto) {

    }

    // 게시글에 달린 댓글 전체 조회

    // 댓글 삭제

}
