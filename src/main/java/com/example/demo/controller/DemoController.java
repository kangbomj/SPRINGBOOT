package com.example.demo.controller;

import com.example.demo.model.service.TestService;
import com.example.demo.model.domain.TestDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @Autowired
    TestService testService;

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB test1 = testService.findByName("홍길동");
        TestDB test2 = testService.findByName("이휴먼");
        TestDB test3 = testService.findByName("강보미");

        model.addAttribute("data4", test1);
        model.addAttribute("data5", test2);
        model.addAttribute("data6", test3);

        System.out.println("데이터 출력 디버그 : " + test1);
        System.out.println("데이터 출력 디버그 : " + test2);
        System.out.println("데이터 출력 디버그 : " + test3);

        return "testdb";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "반갑습니다.");
        return "hello";
    }

    @GetMapping("/about_detailed")
    public String about() {
        return "about_detailed";
    }

    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
        model.addAttribute("data1", "<h2> 반갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "홍길동");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", "002");
        return "thymeleaf_test";
    }
}