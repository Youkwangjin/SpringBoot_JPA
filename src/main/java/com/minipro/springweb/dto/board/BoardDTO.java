package com.minipro.springweb.dto.board;

import com.minipro.springweb.entity.board.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor    // 기본 생성자를 자동으로 만들어준다.
@AllArgsConstructor   // DTO 에서 설정한 필드이 메개변수로 하는 생성자를 자동으로 만들어준다.
@ToString
public class BoardDTO {

    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreateTime;
    private LocalDateTime boardUpdateTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreateTime(boardEntity.getCreateTime());
        boardDTO.setBoardUpdateTime(boardEntity.getUpdateTime());
        return boardDTO;
    }
}
