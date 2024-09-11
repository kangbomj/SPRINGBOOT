package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 어노테이션 명시
public class DemoController {
    @GetMapping("/hello") //전송방식 GET
    public String hello(Model model){
        model.addAttribute("data", "반갑습니다."); //model 설정 
        //앞서 처리한 페이지를 다른 페이지로 전달
        return "hello"; //hello.html 연결
    }
}

//public 선언할 때 대문자(Public) X 소문자로 써야함.