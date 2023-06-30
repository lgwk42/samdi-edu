package com.study.board.controller;

import com.study.board.service.BoardService;
import com.study.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm(){

        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){
        boardService.write(board);

        return "";
    }
    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());

        return "boardList";
    }
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id){
        model.addAttribute("board", boardService.boardView(id));

        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id){

        return "boardModify";
    }
}
