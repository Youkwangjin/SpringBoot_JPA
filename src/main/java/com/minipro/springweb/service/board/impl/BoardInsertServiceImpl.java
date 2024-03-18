package com.minipro.springweb.service.board.impl;


import com.minipro.springweb.dto.board.BoardDTO;
import com.minipro.springweb.entity.board.BoardEntity;
import com.minipro.springweb.entity.board.BoardFileEntity;
import com.minipro.springweb.repository.board.BoardFileRepository;
import com.minipro.springweb.repository.board.BoardRepository;
import com.minipro.springweb.service.board.BoardInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
/*
    1. DTO -> Entity (Controller -> Service)
    2. Entity -> DTO (Service -> Repository)
 */
public class BoardInsertServiceImpl implements BoardInsertService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    @Override
    public void boardInsertData(BoardDTO boardDTO) throws IOException {
        /*
            1. 파일 첨부 여부에 따라 로직 분리
         */
        if (boardDTO.getBoardFile().isEmpty()){
            // 첨부 파일이 없는 경우
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity); // insert 저장.
        } else {
            /*
                1. 첨부 파일이 있는 경우
                2. DTO에 담긴 파일을 꺼내고 파일의 이름을 가져온다.
                3. 서버 저장용 이름으로 만들고 저장 경로 설정한다.
                4. 해당 경로에 파일 저장하고 board_table에 해당 데이터 save 처리
                5. board_file_table에 해당 데이터 save 처리
             */
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C:/work/SpringBoard/src/main/resources/static/spring_img/" + storedFileName;
            boardFile.transferTo(new File(savePath));
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveBoardId = boardRepository.save(boardEntity).getBoardId();
            BoardEntity board = boardRepository.findById(saveBoardId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }
}
