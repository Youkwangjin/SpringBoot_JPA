package com.minipro.springweb.repository;

import com.minipro.springweb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// Repository는 Entity클래스만 받아준다.
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board set board_hit = board_hit + 1 where id = ?
    // Entity 기준으로 Query문을 작성한다.
    @Modifying // update나 delete 쿼리문을 작성할 때에 Modifying 어노테이션을 붙여준다.
    @Query(value = "update BoardEntity b set b.boardhit = b.boardhit + 1 where b.id = :id")
    void updateHit(@Param("id") Long id);
}
