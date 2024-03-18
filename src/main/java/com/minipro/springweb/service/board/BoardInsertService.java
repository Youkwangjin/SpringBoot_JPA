package com.minipro.springweb.service.board;

import com.minipro.springweb.dto.board.BoardDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BoardInsertService {
    void boardInsertData(BoardDTO boardDTO) throws IOException;
}
