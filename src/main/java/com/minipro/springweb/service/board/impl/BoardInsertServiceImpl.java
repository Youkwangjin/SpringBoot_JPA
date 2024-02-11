package com.minipro.springweb.service.board.impl;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.entity.board.BoardEntity;
import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/*
    1. DTO -> Entity (Controller -> Service)
    2. Entity -> DTO (Service -> Repository)
 */
public class BoardInsertServiceImpl implements BoardInsertService {

    private final BoardRepository boardRepository;

    @Override
    public void boardInsertData(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // insert 저장.
    }
}
