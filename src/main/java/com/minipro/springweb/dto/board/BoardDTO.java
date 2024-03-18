package com.minipro.springweb.dto.board;

import com.minipro.springweb.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    /*
        1. boardFile = 실제 파일을 담아서 Controller 에게 넘겨줄 수 있다.
        2. originalFileName = 원본 파일 이름, storedFileName = 서버 저장용 파일 이름
        3. fileAttached = 파일 첨부 여부 (첨부 1, 미첨부 0)
     */
    private MultipartFile boardFile;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached;

    public BoardDTO(Long boardId, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreateTime) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreateTime = boardCreateTime;
    }

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
        if (boardEntity.getFileAttached() == 0) {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
            /*
                1. 파일 이름을 가져가야 한다.
                2. originalFileName, storedFileName => board_file_table에 저장
                3. 부모 Entity 객체가 자식 Entity 객체를 직접적으로 접근 가능 (JPA 강점)
             */
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());

        }
        return boardDTO;
    }
}
