package com.minipro.springweb.controller.board;

import com.minipro.springweb.dto.BoardDto;
import com.minipro.springweb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // 의존성 주입
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 board-list.html에 보여준다.
        List<BoardDto> boardDtoList = boardService.boardListAll();
        model.addAttribute("boardList", boardDtoList);
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
        return "redirect:/board/list";
    }

    @GetMapping("/list/{id}")
    public String BoardDetail(@PathVariable Long id, Model model) {
        /*
            1. 해당 게시글의 조회수를 올린다.
            2. 게시글 데이터를 가져와서 board-datail.html에 출력한다.
         */
        boardService.boardUpdateHit(id);
        BoardDto boardDto = boardService.boardFindId(id);
        model.addAttribute("board", boardDto);
        return "board/board-detail";
    }

    @GetMapping("/update/{id}")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.boardFindId(id);
        model.addAttribute("board", boardDto);
        return "board/board-update";
    }

    @PostMapping("/update/save")
    public String BoardUpdate(@ModelAttribute BoardDto boardDto, Model model) {
        /*
            1. 수정이 완료되면 상세 페이지로 보여진다.
            2. 수정이 반영된 객체를 가지와서 board-detail.html로 가지고 간다.
         */
        BoardDto board = boardService.boardUpdateId(boardDto);
        model.addAttribute("board", board);
        return "board/board-detail";

    }
}
