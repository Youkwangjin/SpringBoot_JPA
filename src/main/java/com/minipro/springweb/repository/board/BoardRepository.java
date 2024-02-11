package com.minipro.springweb.repository.board;

import com.minipro.springweb.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    /*
        1. 조회수 증가 update board set board_hits = board_hits + 1 where board_id = ?
        2. Entity 기준으로 Query 문을 작성한다.
        3. update, delete 쿼리문을 작성할 때에 Modifying 어노테이션을 붙여준다.
     */
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.boardId = :boardId")
    void updateHits(@Param("boardId") Long BoardId);
}
