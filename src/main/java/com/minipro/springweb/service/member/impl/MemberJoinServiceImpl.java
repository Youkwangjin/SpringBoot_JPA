package com.minipro.springweb.service.member.impl;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.entity.member.MemberEntity;
import com.minipro.springweb.repository.member.MemberRepository;
import com.minipro.springweb.service.member.MemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // @Service => Spring 관리해주는 객체(Bean)으로 등록해준다.
@RequiredArgsConstructor
public class MemberJoinServiceImpl implements MemberJoinService {

    private final MemberRepository memberRepository;
    @Override
    public void memberJoinSave(MemberDTO memberDTO) {
        /*
            1. Controller 에서 DTO 객체를 넘겨 받았다.
            2. Repository 로 DB 작업을 할때는 반드시 Entity 객체로 넘겨줘야 한다. (DTO => Entity 변환)
            3. Repository save 메서드 호출 (조건 : Entity 객체로 넘겨줘야 한다.)
         */
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
