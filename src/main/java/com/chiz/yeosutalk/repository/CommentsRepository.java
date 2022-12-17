package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    // 게시글 번호로 댓글 리스트 조회
    List<Comments> findByTourBoardId(Long tourBoardId);
}
