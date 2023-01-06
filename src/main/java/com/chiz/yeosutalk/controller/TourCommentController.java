package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.TourCommentDto;
import com.chiz.yeosutalk.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tour-comment")
public class TourCommentController {

    private final CommentsService commentsService;

    @Autowired
    public TourCommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/create")
    @ResponseBody
    public TourCommentDto createComment(TourCommentDto tourCommentDto) {
        TourCommentDto comment = commentsService.createComment(tourCommentDto);

        return comment;
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public List<TourCommentDto> listTourComments(@PathVariable("id") Long toruBoardId) {
        List<TourCommentDto> tourComments = commentsService.listComments(toruBoardId);

        return tourComments;
    }
}
