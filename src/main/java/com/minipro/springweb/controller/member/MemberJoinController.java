package com.minipro.springweb.controller.member;


import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.service.member.MemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberJoinController {

    private final MemberJoinService memberJoinService;

    @GetMapping("/member/join")
    public String memberJoinPage() {
        return "/member/member-join";
    }

    @PostMapping("/member/join/save")
    public String memberJoin(@ModelAttribute MemberDTO memberDTO) {
        memberJoinService.memberJoinSave(memberDTO);
        return "/member/member-login";
    }
}
