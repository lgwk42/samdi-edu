package com.study.board.controller;

import com.study.board.service.BoardService;
import com.study.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm(){

        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {
        boardService.write(board, file);

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
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
    public String boardDelete(Integer id, Model model){
        boardService.boardDelete(id);

        model.addAttribute("message","글이 삭제되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board",boardService.boardView(id));

        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file, Model model) throws Exception {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        model.addAttribute("message","글이 수정되었습니다");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }
}
