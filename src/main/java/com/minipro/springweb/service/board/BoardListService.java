package com.minipro.springweb.service.board;


import com.minipro.springweb.dto.board.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardListService {
    List<BoardDTO> boardListAllData();

    BoardDTO findByBoardId(Long boardId);

    void updateHits(Long boardId);

    Page<BoardDTO> boardPaging(Pageable pageable);
}
