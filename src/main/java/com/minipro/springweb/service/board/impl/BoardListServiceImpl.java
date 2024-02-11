package com.minipro.springweb.service.board.impl;

import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.entity.board.BoardEntity;
import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardListServiceImpl implements BoardListService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardDTO> boardListAllData() {
        /*
            1. Entity 여러 개가 담긴 List 객체를 DTO가 여러 개 담긴 List 객체로 변환 작업
         */
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Override
    public BoardDTO findByBoardId(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        /*
            1. Optional 객체 내부에 값이 존재하는지 여부를 확인한다.
            2. 값이 존재한다면 get() 메소드를 사용하여 Optional 객체 내부의 MemberEntity 객체에 접근한다.
            3. DTO 형태로 변환하여 반환
         */
        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }
    }

    @Transactional // JpaRepository 가 제공하는 메서드가 아닌 별도로 추가된 메서드를 사용할 시 @Transactional 사용한다.
    @Override
    public void updateHits(Long boardId) {
        boardRepository.updateHits(boardId);
    }
}
