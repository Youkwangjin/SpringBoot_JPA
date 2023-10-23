package com.minipro.springweb.service;

import com.minipro.springweb.dto.BoardDto;
import com.minipro.springweb.entity.BoardEntity;
import com.minipro.springweb.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
}
