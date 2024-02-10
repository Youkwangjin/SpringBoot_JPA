package com.minipro.springweb.controller.member;


import com.minipro.springweb.service.member.MemberDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MemberDeleteController {

    private final MemberDeleteService memberDeleteService;

    @GetMapping("/member/delete/{userId}")
    public String deleteByUserId(@PathVariable Long userId){
        memberDeleteService.deleteById(userId);
        return "redirect:/member/list/";
    }
}
