package com.minipro.springweb.service.member;

import com.minipro.springweb.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberUpdateService {
    MemberDTO updateForm(String myEmail);
    void memberUpdateData(MemberDTO memberDTO);
}