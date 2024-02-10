package com.minipro.springweb.controller.member;

import com.minipro.springweb.dto.member.MemberDTO;
import com.minipro.springweb.service.member.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberListController {

    private final MemberListService memberListService;

    @GetMapping("/member/list/")
    public String memberListPage(Model model) {
        /*
            1. DB 에서 저장되어 있는 모든 데이터를 가져오기 때문에 List 타입
            2. 어떠한 HTML로 가져갈 데이터가 있으면 Model 사용
         */
        List<MemberDTO> memberDTOList = memberListService.memberListAll();
        model.addAttribute("memberList", memberDTOList);
        return "/member/member-list";
    }

    @GetMapping("/member/{userId}")
    public String memberFindByUserId(@PathVariable Long userId, Model model) { // 경로상에 있는 userId 값을 담아올 때 @PathVariable 사용
        MemberDTO memberDTO = memberListService.findByUserId(userId);
        model.addAttribute("member", memberDTO);
        return "/member/member-detail";
    }
}
