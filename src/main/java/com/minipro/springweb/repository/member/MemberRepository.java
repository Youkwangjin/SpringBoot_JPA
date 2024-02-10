package com.minipro.springweb.repository.member;


import com.minipro.springweb.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    1. 데이터베이스와 작업을 해주는 요소 기본적으로 JpaRepository 을 받는다.
    2. Repository 로 DB 작업을 할때는 반드시 Entity 객체로 넘겨줘야 한다.
    3. 특정 쿼리를 수행하기 위해서는 추가적인 추상메서드를 정의해줘야 한다.
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    /*
        1. 이메일로 회원 정보 조회 (select * from member where user_email = ?)
        2. Optional 일종의 null 방지를 이용
        3. Repository 에서 주고 받는 인스턴스는 Entity 타입이다.
    */
    Optional<MemberEntity> findByUserEmail(String userEmail);
}
