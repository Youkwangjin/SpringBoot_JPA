package com.minipro.springweb.service.board.impl;

import com.minipro.springweb.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUpdateServiceImpl {

    private final BoardRepository boardRepository;
}
