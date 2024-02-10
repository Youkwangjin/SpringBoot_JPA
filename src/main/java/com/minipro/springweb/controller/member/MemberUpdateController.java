package com.minipro.springweb.controller.member;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.service.member.MemberUpdateService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;

    @GetMapping("/member/update")
    public String memberUpdatePage(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("sessionUser");
        MemberDTO memberDTO = memberUpdateService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "/member/member-update";
    }

    @PostMapping("/member/info/update")
    public String memberUpdate(@ModelAttribute MemberDTO memberDTO){
        memberUpdateService.memberUpdateData(memberDTO);
        return "redirect:/member/list/";
    }
}
