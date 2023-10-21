package com.minipro.springweb.controller.member;

import com.minipro.springweb.dto.MemberDto;
import com.minipro.springweb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@Controller
@RequiredArgsConstructor // 생성자를 자동으로 만들어준다.
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;


    // 회원가입 페이지 출력 요청
    @GetMapping("/member/join")
    public String memberJoin() {
        return "member/member-join";
    }

    @PostMapping("/member/save")
    public String memberSave(@ModelAttribute MemberDto memberDto) {
        memberService.save(memberDto);
        return "index";
    }
    
    @GetMapping("/member/login")
    public String memberLogin() {
        return "member/member-login";
    }

    @PostMapping("/member/login/go")
    public String memberLoginGo(@ModelAttribute MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginId", loginResult.getUserid());
            return "member/member-mypage";
        } else {
            return "index";
        }
    }

    @GetMapping("/member/")
    public String memberFindAll(Model model) {
        // 하나의 데이터가 아닌 여러 개의 데이터를 가져온다.
        List<MemberDto> memberDtoList =  memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 Model 사용
        model.addAttribute("memberList", memberDtoList);
        return "member/member-list";
        
    }
}
