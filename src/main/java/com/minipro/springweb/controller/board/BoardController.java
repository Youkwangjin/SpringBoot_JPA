package com.minipro.springweb.controller.board;

import com.minipro.springweb.dto.BoardDto;
import com.minipro.springweb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // 의존성 주입
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardForm() {
        return "board/board-list";
    }

    @GetMapping("/insert/save")
    public String boardInsert() {
        return "board/board-write";
    }

    @PostMapping("/insert/save")
    public String boardInfoInsert(@ModelAttribute BoardDto boardDto) {
        /*
            1. ModelAttribute에 의해서 BoardDto 클래스 객체를 찾는다.
            2. HTML의 name과 Dto의 필드값이 동일하면 Spring에서 필드에 대한 setter 메서드를 호출하면서 데이터값을 담아준다.
         */
        boardService.boardInfoInsertSave(boardDto);
        return "board/board-list";
    }
}
