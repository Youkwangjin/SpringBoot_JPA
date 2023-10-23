package com.minipro.springweb.repository;

import com.minipro.springweb.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repository는 Entity클래스만 받아준다.(관례)
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
