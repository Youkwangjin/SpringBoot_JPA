package com.minipro.springweb.service;

import com.minipro.springweb.dto.BoardDto;
import com.minipro.springweb.entity.BoardEntity;
import com.minipro.springweb.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
    1. Service 클래스에서는 Dto -> Entity 타입 변환 (Entity 클래스에서 작업)
    2. Entity 타입으로 가져온 데이터를 Dto 타입으로 변환 (Dto 클래스에서 작업)
    3. 예를 들어, Controller로부터 넘겨받을 때는 Dto로 넘겨받는다. 그리고 나서 Repository로 넘겨줄 때는 Entity타입으로 넘겨준다.
    4. DB 데이터를 조회할 때는 Repository로부터 Entity타입으로 Service 클래스가 받아온다. Controller로 Return 줄 때는 Dto 타입으로 반환한다.
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void boardInfoInsertSave(BoardDto boardDto) {
        /*
            1. 보통 save()는 기본적으로 매게변수를 Entity 클래스 타입으로 받도록 되어있다.
            2. Return을 줄 때도 Entity 클래스 타입으로 준다.
         */
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);

    }

    public List<BoardDto> boardListAll() {
        // Entity 객체에서 넘어온 객체를 Dto에게 주기 위해 형 변환을 해줘야 한다.
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        // Entity 객체를 Dto타입으로 변환 하고 그 값들을 List에 담는다.
        for (BoardEntity boardEntity: boardEntityList) {
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;

    }

    @Transactional
    public void boardUpdateHit(Long id) {
        boardRepository.updateHit(id);

    }

    public BoardDto boardFindId(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
            return boardDto;
        } else {
            return null;
        }
    }

    public BoardDto boardUpdate(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(boardEntity);
        return boardFindId(boardDto.getId());
    }

    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDto> paging(Pageable pageable) {
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
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));


        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부
        // 목록: id, writer, title, hits, createdTime

        Page<BoardDto> boardDTOS = boardEntities.map(board -> new BoardDto(board.getId(), board.getBoardwriter(), board.getBoardtitle(), board.getBoardhit(), board.getCreatedtime()));
        return boardDTOS;


    }
}
