package com.minipro.springweb.service.board.impl;

import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeleteServiceImpl implements BoardDeleteService {

    private final BoardRepository boardRepository;
    @Override
    public void boardDelete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
