package com.minipro.springweb.service.board;


import org.springframework.stereotype.Service;

@Service
public interface BoardDeleteService {
    void boardDelete(Long boardId);
}
