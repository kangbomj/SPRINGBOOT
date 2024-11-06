package com.example.demo.controller;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
//import com.example.demo.model.repository.BlogRepository;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;//@Autowired 사용
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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
    
    
    //5주차 연습문제 리디렉션
    @PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/article_list";
    }

    //6주차 에러페이지 맵핑 임의로해봄
    @GetMapping("/article_error")
    public String about() {
    return "article_error";
    }

    //6주차 8페이지
    @GetMapping("/article_edit/{id}") //게시판 링크지정
    public String article_edit(Model model, @PathVariable Long id){
        Optional<Article> list = blogService.findById(id); //선택한 게시판 글

        if(list.isPresent()){
            model.addAttribute("article", list.get()); //존재하면 Article 객체를 모델에 추가

        }
        else{
            //처리할 로직 추가 (예 : 오류 페이지로 리다이렉트, 예외 처리 등)
            //return "error"; //오류 처리 페이지로 연결
            return "/article_error"; // 오류 처리 페이지로 연결

        }
        return "article_edit"; //.HTML연결
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request){
        blogService.update(id, request);
        return "redirect:/article_list"; //글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return "redirect:/article_list";
    }

    //7주차 13페이지 보드게시판

    @GetMapping("/board_list") //새로운 게시판 링크지정
    public String board_list(Model model){
        //List<Board> list = blogService.findAll(); //게시판 전체리스트
        //model.addAttributte("articles", list); //모델에 추가
        List<Board> list = blogService.findAll(); //게시판 전체리스트
        return "board_list"; //.HTML 연결

        //보드가 없는데 이 코드 먼저 실행시키니까 안됐던거임. 리스트 모델에 추가하는거 먼저하지말고 주석처리-> 그냥 HTML만 맵핑 -> 실행 -> 데이터배이스 생성 Refresh -> 주석 해제
    }
}
