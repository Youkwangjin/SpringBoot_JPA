package com.minipro.springweb.controller.board;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.service.board.BoardListService;
import com.minipro.springweb.service.board.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardUpdateController {

    private final BoardListService boardListService;
    private final BoardUpdateService boardUpdateService;

    @GetMapping("/board/update/{boardId}")
    public String boardUpdateForm(@PathVariable Long boardId, Model model){
        BoardDTO boardDTO = boardListService.findByBoardId(boardId);
        System.out.println(boardDTO);
        model.addAttribute("boardUpdate", boardDTO);
        System.out.println(model);
        return "/board/board-update";
    }

    @PostMapping("/board/update/save")
    public String boardUpdateData(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardUpdateService.boardUpdate(boardDTO);
        model.addAttribute("board", board);
        return "redirect:/board/list";
    }
}
