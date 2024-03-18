package com.minipro.springweb.controller.board;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.service.board.BoardInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BoardInsertController {

    private final BoardInsertService boardInsertService;

    @GetMapping("/board/insert")
    public String boardInsertPage() {
        return "/board/board-insert";
    }

    @PostMapping("/board/insert/save")
    public String boardInsert(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardInsertService.boardInsertData(boardDTO);
        return "redirect:/board/list";
    }
}
