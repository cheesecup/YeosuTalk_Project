package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.TourBoardComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourBoardCommRepository extends JpaRepository<TourBoardComments, Long> {

    // 게시글 번호로 댓글 리스트 조회
    List<TourBoardComments> findByTourBoardId(Long tourBoardId);
}
