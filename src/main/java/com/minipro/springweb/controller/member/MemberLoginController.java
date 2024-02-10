package com.minipro.springweb.controller.member;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.service.member.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberLoginController {

    private final MemberLoginService memberLoginService;

    @GetMapping("/member/login")
    public String memberLoginPage() {
        return "member/member-login";
    }

    @PostMapping("/member/login")
    public String memberLogin(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberLoginService.memberLogin(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("sessionUser", loginResult.getUserEmail());
            return "member/member-mypage";
        } else {
            // login 실패
            return "member/member-login";
        }
    }
}
