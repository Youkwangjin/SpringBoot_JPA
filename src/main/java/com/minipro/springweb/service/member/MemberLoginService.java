package com.minipro.springweb.service.member;


import com.minipro.springweb.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberLoginService {
    MemberDTO memberLogin(MemberDTO memberDTO);
}
