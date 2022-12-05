package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.domain.TourBoard;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.service.TourBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourBoardController {

    private final TourBoardService tourBoardService;

    @Autowired
    public TourBoardController(TourBoardService tourBoardService) {
        this.tourBoardService = tourBoardService;
    }

    /* 관광 게시판 게시글 생성 컨트롤러 */
    @PostMapping("/create")
    public Long createTourBoard(TourBoardFormDto tourBoardFormDto) {
        Long tourBoardId = tourBoardService.createTourBoard(tourBoardFormDto);

        return tourBoardId;
    }

    /* 관광 게시판 게시글 목록 조회 컨트롤러 */
    @GetMapping("/list")
    public List<TourBoard> listTourBoard() {
        List<TourBoard> tourBoardList = tourBoardService.tourBoardList();

        return tourBoardList;
    }
}
