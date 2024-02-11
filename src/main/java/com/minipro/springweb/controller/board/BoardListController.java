package com.minipro.springweb.controller.board;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardListController {

    private final BoardListService boardListService;

    @GetMapping("/board/list")
    public String boardListPage(Model model) {
        /*
            1. DB 에서 전체 게시글 데이터를 가져온다.
            2. 가져온 데이터를 모델에 담아 HTML에 출력한다.
         */
        List<BoardDTO> boardDTOList = boardListService.boardListAllData();
        model.addAttribute("boardList", boardDTOList);
        return "/board/board-list";
    }

    @GetMapping("/board/list/{boardId}")
    public String boardDetailPage(@PathVariable Long boardId, Model model) {
        boardListService.updateHits(boardId);
        BoardDTO boardDTO = boardListService.findByBoardId(boardId);
        model.addAttribute("board", boardDTO);
        return "/board/board-detail";
    }
}
