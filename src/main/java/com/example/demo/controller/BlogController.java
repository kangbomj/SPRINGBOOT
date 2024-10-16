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

    @GetMapping("/article_edit/{id}") //게시판 링크지정
    public String article_edit(Model model @PathVariable Long id){
        Optional<Article> list = blogService.findById(id); //선택한 게시판 글

        if(list.isPresent()){
            model.addAttribute("articlel", list.get()); //존재하면 Article 객체를 모델에 추가

        }
        else{
            //처리할 로직 추가 (예 : 오류 페이지로 리다이렉트, 예외 처리 등)
            return "error"; //오류 처리 페이지로 연결
        }
        return "article_edit"; //.HTML연결
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request){
        blogService.update(id, request);
        return "redirect:/article_list"; //글 수정 이후 .html 연결
    }
}
