package com.minipro.springweb.service.member;

import com.minipro.springweb.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service // @Service => Spring 관리해주는 객체(Bean)으로 등록해준다.
public interface MemberJoinService {
    void memberJoinSave(MemberDTO memberDTO);
}
