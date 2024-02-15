package com.minipro.springweb.service.board.impl;

import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.entity.board.BoardEntity;
import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardListService;
import com.minipro.springweb.service.board.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUpdateServiceImpl implements BoardUpdateService {

    private final BoardRepository boardRepository;
    private final BoardListService boardListService;


    @Override
    public BoardDTO boardUpdate(BoardDTO boardDTO) {
        /*
            1. Repository save 메서드는 데이터가 있으면 update 쿼리, 없으면 insert 쿼리를 날려준다.
         */
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardListService.findByBoardId(boardDTO.getBoardId());
    }
}
