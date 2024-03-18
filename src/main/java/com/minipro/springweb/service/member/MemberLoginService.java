package com.minipro.springweb.service.member;


import com.minipro.springweb.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberLoginService {
    MemberDTO memberLogin(MemberDTO memberDTO);
}
