package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.TourComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourCommentRepository extends JpaRepository<TourComment, Long> {

    // 게시글 번호로 댓글 리스트 조회
    List<TourComment> findByTourBoardId(Long tourBoardId);
}
