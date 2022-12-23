package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.TourBoardDto;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.service.TourBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<TourBoardDto> tourBoardList() {
        List<TourBoardDto> tourBoardList = tourBoardService.tourBoardList();

        return tourBoardList;
    }

    /* 관광 게시판 게시글 상세내용 조회 컨트롤러 */
    @GetMapping("/info/{id}")
    public TourBoardDto tourPostInfo(@PathVariable("id") Long id) {
        return tourBoardService.postInfo(id);
    }

    /* 관광 게시판 게시글 수정 컨트롤러*/
    @PostMapping("/update")
    public Long updateTourPost(Long id, TourBoardFormDto tourBoardFormDto) {
        Long updatePostId = tourBoardService.updateTourPost(id, tourBoardFormDto);

        return updatePostId;
    }

    /* 관광 게시판 게시글 삭제 컨트롤러 */
    @DeleteMapping("/delete")
    public boolean deleteTourPost(Long id) {
        boolean result = tourBoardService.deleteTourPost(id);
        return result;
    }

    /* 관광 게시판 게시글 검색 */
    @GetMapping("/search")
    public List<TourBoardDto> searchList(@RequestParam String category,
                                      @RequestParam String keyword) {
        List<TourBoardDto> tourBoardList = tourBoardService.searchTourBoardList(category, keyword);

        return tourBoardList;
    }
}
