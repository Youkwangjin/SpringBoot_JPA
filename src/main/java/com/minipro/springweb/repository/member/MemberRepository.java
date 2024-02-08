package com.minipro.springweb.repository.member;


import com.minipro.springweb.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    // 특정 쿼리를 수행하기 위해서는 추가적인 추상메서드를 정의해줘야 한다.

    /*
        1. (select * from member where user_id = ?)
        2. Optional 일종의 null 방지를 이용
        3. Repository 에서 주고 받는 인스턴스는 Entity 타입이다.
     */

}
