// // 7주차 게시판 보드 추가 후 오류떠서 주석처리
// // 5주차 페이지 리디렉트 연습문제떄문에 전체주석처리
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.RestController;
// import com.example.demo.model.domain.Article;
// import com.example.demo.model.service.AddArticleRequest;
// import com.example.demo.model.service.BlogService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// @RequiredArgsConstructor
// //@RestController // @Controller + @ResponseBody인데 6주차 코드에서 쪼개준거임
// @Controller
// public class BlogRestController {

//     private final BlogService blogService;

//     // @PostMapping("/api/articles")
//     // public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest request) {
//     //     Article saveArticle = blogService.save(request);
//     //     return ResponseEntity.status(HttpStatus.CREATED)
//     //             .body(saveArticle);
//     // }

//     // 웹 브라우저 기본 사이트 접근 이후 루트의 favicon.ico 파일 자동 요청
//     // static 폴더에 파일이 존재하지 않으면
//     // 에러발생 : MethodArgumentTypeMismatchException
//     @GetMapping("/favicon.ico")
//     @ResponseBody // @RestContrller에서 Controller랑 ResponseBody로 쪼개줘서 리디렉션하게함.
//     public void favicon() {
//         // 아무작업도 하지않음
//     }

// }
