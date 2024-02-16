package com.minipro.springweb.controller.board;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String boardDetailPage(@PathVariable Long boardId, Model model,
                                  @PageableDefault(page = 1) Pageable pageable) {
        boardListService.updateHits(boardId);
        BoardDTO boardDTO = boardListService.findByBoardId(boardId);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/board/board-detail";
    }
    
    @GetMapping("/board/list/paging")
    public String boardPaging(@PageableDefault(page = 1)Pageable pageable, Model model) {
        /* 
            1. /board/paging?page=1 이런식으로 구현
            2. pageable 객체는 파라미터(page=1)에서 지정된 값을 받아주는 역할을 한다.
            3. 기본값은 1로 지정, page 갯수는 20개로 지정
            4. 현재 사용자가 3페이지를 보고 있다고 가정하면 3페이지는 색상이 다르게 표현
         */
        Page<BoardDTO> boardList = boardListService.boardPaging(pageable);

        /*
            1. 사용자에게 보여지게 하는 갯수는 3개로 지정 ex) 1 2 3
            2. 현재 사용자가 요청한 페이지를 blockLimit으로 나누어서 소숫점을 올린다.
            3.
         */
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/board/board-paging-list";
    }
}
