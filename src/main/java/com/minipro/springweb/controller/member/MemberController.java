package com.minipro.springweb.controller.member;

import com.minipro.springweb.dto.MemberDto;
import com.minipro.springweb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        memberService.memberSave(memberDto);
        return "index";
    }
    
    @GetMapping("/member/login")
    public String memberLogin() {
        return "member/member-login";
    }

    @PostMapping("/member/login/go")
    public String memberLoginGo(@ModelAttribute MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.memberLogin(memberDto);
        if(loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginId", loginResult.getUserid());
            return "member/member-mypage";
        } else {
            return "index";
        }
    }

    @GetMapping("/member/list")
    public String memberFindAll(Model model) {
        // 하나의 데이터가 아닌 여러 개의 데이터를 가져온다.
        List<MemberDto> memberDtoList =  memberService.memberFindAll();
        // 어떠한 html로 가져갈 데이터가 있다면 Model 사용
        model.addAttribute("memberList", memberDtoList);
        return "member/member-list";
    }

    @GetMapping("/member/show/{userid}")
    public String memberFindById(@PathVariable String userid, Model model) { // PathVariable은 경로상에 있는 값을 가지고 올 때 사용.
        // 한명의 데이터를 가지고 오니까 List 타입이 아닌 Dto 타입
        MemberDto memberDto = memberService.memberFindById(userid);
        model.addAttribute("member", memberDto);
        return "member/member-detail";
    }

    @GetMapping("/member/update")
    public String memberUpdateForm(HttpSession session, Model model) {
        String myId = (String) session.getAttribute("loginId");
        MemberDto memberDto = memberService.memberUpdateForm(myId);
        model.addAttribute("memberUpdate", memberDto);

        return "member/member-update";
    }

    @PostMapping("/member/info/update")
    public String memberInfoUpdate(@ModelAttribute MemberDto memberDto) {
        memberService.memberInfoUpdate(memberDto);
        return "redirect:/member/show/" + memberDto.getUserid();
    }

    @GetMapping("/member/delete/{userid}")
    public String memberDeleteById(@PathVariable String userid) {
        memberService.memberDeleteById(userid);
        return "redirect:/member/list";

    }

    @GetMapping("/member/logout")
    public String memberLogout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
