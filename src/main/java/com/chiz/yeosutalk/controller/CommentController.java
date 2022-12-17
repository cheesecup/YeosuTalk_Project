package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.CommentDto;
import com.chiz.yeosutalk.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentsService commentsService;

    @Autowired
    public CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/create")
    public CommentDto createComment(CommentDto commentDto) {
        CommentDto comment = commentsService.createComment(commentDto);

        return comment;
    }
}
