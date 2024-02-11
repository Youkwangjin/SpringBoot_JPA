package com.minipro.springweb.entity.board;


import com.minipro.springweb.dto.board.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "board")
public class BoardEntity extends BoardBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_writer", length = 20, nullable = false) // 크기는 20, Not Null
    private String boardWriter;

    @Column(name = "board_password", nullable = false)
    private String boardPassword;

    @Column(name = "board_title", nullable = false)
    private String boardTitle;

    @Column(name = "board_contents", length = 300, nullable = false)
    private String boardContents;

    @Column(name = "board_hits")
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
