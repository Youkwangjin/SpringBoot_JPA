package com.minipro.springweb.service.board;

import com.minipro.springweb.dto.board.BoardDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoardUpdateService {
    BoardDTO boardUpdate(BoardDTO boardDTO);
}
