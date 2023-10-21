package com.minipro.springweb.repository;


import com.minipro.springweb.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// 데이터베이스하고 작업을 해주는 최종 요소
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> { // MemberEntity의 pk타입을 적어준다.
    // 특정 쿼리들을 수행하기 위해서는 추가적인 추상메서드를 적어줘야 한다.
    // 모든 Repository 에서 주고 받는 객체는 Entity 타입으로 주고 받는다.
    // 아이디로 회원정보를 조회하는 메서드 (select * from user_table where userid = ?)
    Optional<MemberEntity> findByUserid(String userid); // Optional 일종의 null 방지
}
