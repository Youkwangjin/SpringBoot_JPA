package com.minipro.springweb.service.board.impl;

import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.entity.board.BoardEntity;
import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Transactional
    public BoardDTO findByBoardId(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        /*
            1. Optional 객체 내부에 값이 존재하는지 여부를 확인한다.
            2. 값이 존재한다면 get() 메소드를 사용하여 Optional 객체 내부의 MemberEntity 객체에 접근한다.
            3. DTO 형태로 변환하여 반환
         */
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    @Transactional // JpaRepository 가 제공하는 메서드가 아닌 별도로 추가된 메서드를 사용할 시 @Transactional 사용한다.
    @Override
    public void updateHits(Long boardId) {
        boardRepository.updateHits(boardId);
    }

    @Override
    public Page<BoardDTO> boardPaging(Pageable pageable) {
        /*
            1. findAll() 호출하는 동시에 몇 페이지를 보고 싶은지 설정하는 메서드
            2. pageLimit은 한 페이지에 보여줄 글 갯수를 정할 때 사용한다.
            3. Sort.by 기준으로 정렬해서 해당 페이지 값을 가져온다.
            4. (Sort.Direction.DESC, "id") 에서 "id"는 'Entity'에서 작성한 이름을 기준으로 한다.
            5. 즉, 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
         */
        int page = pageable.getPageNumber() - 1; // -1를 한 이유는 page 위치에 있는 값은 0부터 시작하기 때문
        int pageLimit = 3;
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardId")));
        /*
            1. boardEntities는 Entity 객체로 담겨있음으로 DTO 타입으로 변환해야 한다.
            2. map 메서드는 Page 객체에서 제공해주는 메서드이다.
            3. board는 entity객체의 매개변수를 하나씩 꺼내서 DTO 객체로 옮겨 담는다.
            4. 페이지 목록을 보여주기 위한 데이터 id, writer, title, hits, createdTime
         */
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(
                board.getBoardId(),
                board.getBoardWriter(),
                board.getBoardTitle(),
                board.getBoardHits(),
                board.getCreateTime()));
        return boardDTOS;

    }
}
