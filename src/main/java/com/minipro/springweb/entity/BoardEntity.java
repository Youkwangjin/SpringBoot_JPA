package com.minipro.springweb.entity;

import com.minipro.springweb.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// DB의 테이블 역할을 하는 클래스(Service, Repository 에서만 사용한다.)
// Entity도 일종의 객체이기 때문에 객체의 담긴 값을 사용하기 위해 setter, getter를 사용한다.
@Entity
@Getter
@Setter
@Table(name = "board")
public class BoardEntity extends BoardBaseEntity{
    @Id // PK 컬럼 지정.(필수!)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column(length = 20, nullable = false) // NOT_NULL
    private String boardwriter;
    @Column // 따로 설정해주지 않으면 크기 255, NULL 허용
    private String boardpass;
    @Column
    private String boardtitle;
    @Column(length = 500)
    private String boardcontents;
    @Column
    private int boardhit;
    
    // BoardDto에 담겨온 값들을 Entity 객체로 옮겨 담는 작업
    public static BoardEntity toSaveEntity(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardwriter(boardDto.getBoardwriter());
        boardEntity.setBoardpass(boardDto.getBoardpass());
        boardEntity.setBoardtitle(boardDto.getBoardtitle());
        boardEntity.setBoardcontents(boardDto.getBoardcontents());
        boardEntity.setBoardhit(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setBoardwriter(boardDto.getBoardwriter());
        boardEntity.setBoardpass(boardDto.getBoardpass());
        boardEntity.setBoardtitle(boardDto.getBoardtitle());
        boardEntity.setBoardcontents(boardDto.getBoardcontents());
        boardEntity.setBoardhit(boardDto.getBoardhit());
        return boardEntity;
    }

}
