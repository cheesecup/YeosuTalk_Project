package com.chiz.yeosutalk.service;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.repository.TourBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourBoardService {

    private final TourBoardRepository tourBoardRepository;

    @Autowired
    public TourBoardService(TourBoardRepository tourBoardRepository) {
        this.tourBoardRepository = tourBoardRepository;
    }

    /* 관광 게시판 게시글 작성 서비스 */
    public Long createTourBoard(TourBoardFormDto tourBoardFormDto) {
        TourBoard tourBoard = TourBoard.toEntity(tourBoardFormDto);

        TourBoard savedTourBoard = tourBoardRepository.save(tourBoard);

        return savedTourBoard.getId();
    }

    /* 관광 게시판 게시글 목록 조회 */
    public List<TourBoard> tourBoardList() {
         return tourBoardRepository.findAll();
    }
}
