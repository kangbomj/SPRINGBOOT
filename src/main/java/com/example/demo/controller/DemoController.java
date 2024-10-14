//컨트롤러 폴더
package com.example.demo.controller;


import com.example.demo.model.service.TestService; //대소문자 구분할것
import com.example.demo.model.domain.TestDB; //이거 피피티에 없는데 미소파가 임포트하라고해서 추가함

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 어노테이션 명시
public class DemoController {

    @Autowired
    TestService testService; //DemoController클래스 아래 객체 생성

    //4주차 수정
    @GetMapping("/testdb")
    public String getAllTestDBs(Model model){
        TestDB test = testService.findByName("홍길동");
        model.addAttribute("data4", test);
        System.out.println("데이터 출력 디버그 : " + test);
        return "testdb";
    }

    @GetMapping("/hello") //전송방식 GET
    public String hello(Model model){
        model.addAttribute("data", "반갑습니다."); //model 설정 
        //앞서 처리한 페이지를 다른 페이지로 전달
        return "hello"; //hello.html 연결
    }

    @GetMapping("/about_detailed")
    public String about() {
    return "about_detailed";
    }



 //4주차 
     @GetMapping("/test1")
     public String thymeleaf_test1(Model model){
         model.addAttribute("data1", "<h2> 반갑습니다 </h2>");
         model.addAttribute("data2", "태그의 속성 값");
         model.addAttribute("link", 01);
         model.addAttribute("name", "홍길동");
         model.addAttribute("para1", "001");
         //상품 카테고리같은거.. 대분류는 para1, 소분류는 para2이런식으로 ...?
         model.addAttribute("para2", "002");
         return "thymeleaf_test";
         //교수님은 return "temp1"; 이라고 함.
     }


}


//public 선언할 때 대문자(Public) X 소문자로 써야함.