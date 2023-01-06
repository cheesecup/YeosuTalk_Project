package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.TourBoardDto;
import com.chiz.yeosutalk.dto.TourBoardFormDto;
import com.chiz.yeosutalk.service.TourBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tour")
public class TourBoardController {

    private final TourBoardService tourBoardService;

    @Autowired
    public TourBoardController(TourBoardService tourBoardService) {
        this.tourBoardService = tourBoardService;
    }

    /* 관광 게시판 게시글 생성 뷰 컨트롤러 */
    @GetMapping("/create")
    public String createTourBoardForm() {
        return "tourBoards/createTourBoardForm";
    }

    /* 관광 게시판 게시글 생성 컨트롤러 */
    @PostMapping("/create")
    @ResponseBody
    public Long createTourBoard(TourBoardFormDto tourBoardFormDto) {
        Long tourBoardId = tourBoardService.createTourBoard(tourBoardFormDto);

        return tourBoardId;
    }

    /* 관광 게시판 게시글 뷰 컨트롤러 */
    @GetMapping("/list")
    public String tourBoardList(Model model) {
        List<TourBoardDto> tourBoardList = tourBoardService.tourBoardList();

        model.addAttribute("boards", tourBoardList);

        return "tourBoards/tourBoardList";
    }

    /* 관광 게시판 게시글 상세내용 조회 컨트롤러 */
    @GetMapping("/info/{id}")
    public String tourPostInfo(@PathVariable("id") Long id, Model model) {
        TourBoardDto boardInfo = tourBoardService.postInfo(id);
        model.addAttribute("info", boardInfo);
        return "tourBoards/tourBoardInfo";
    }

    /* 관광 게시판 게시글 수정 컨트롤러*/
    @PostMapping("/update")
    @ResponseBody
    public Long updateTourPost(Long id, TourBoardFormDto tourBoardFormDto) {
        Long updatePostId = tourBoardService.updateTourPost(id, tourBoardFormDto);

        return updatePostId;
    }

    /* 관광 게시판 게시글 삭제 컨트롤러 */
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean deleteTourPost(Long id) {
        boolean result = tourBoardService.deleteTourPost(id);
        return result;
    }

    /* 관광 게시판 게시글 검색 */
    @GetMapping("/search")
    @ResponseBody
    public List<TourBoardDto> searchList(@RequestParam String category,
                                      @RequestParam String keyword) {
        List<TourBoardDto> tourBoardList = tourBoardService.searchTourBoardList(category, keyword);

        return tourBoardList;
    }
}
