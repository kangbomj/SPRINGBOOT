package com.example.demo.controller;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.BlogService;
//import com.example.demo.model.repository.BlogRepository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;//@Autowired 사용
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 어노테이션 명시
public class BlogController {
    @Autowired
    BlogService blogService;

    //DemoController.java를 재활용한다. 5주차 10페이지

    @GetMapping("/article_list")
    public String article_list(Model model){
        List<Article> list = blogService.findAll(); //게시판 리스트
        model.addAttribute("articles", list); //모델에 추가
        //articles라는게 어딨노 일단 주석
        //addAttributes라는 건 없다.
        return "article_list"; //.HTML 연결
    }

}
