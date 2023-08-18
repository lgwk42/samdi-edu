package com.study.board.controller;

import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/board/signUp")
    public String signUp(){
        return "signUp";
    }
}
