package com.minipro.springweb.controller.board;


import com.minipro.springweb.service.board.BoardDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardDeleteController {

    private final BoardDeleteService boardDeleteService;

    @GetMapping("/board/delete/{boardId}")
    public String boardDelete(@PathVariable Long boardId) {
        boardDeleteService.boardDelete(boardId);
        return "redirect:/board/list";
    }
}
