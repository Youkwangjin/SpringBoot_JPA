package com.minipro.springweb.controller.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 기본 페이지를 요청하는 메서드
    @GetMapping("/")
    public String index() {
        return "/index";
    }
}
